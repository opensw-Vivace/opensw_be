package com.vivace.opensw.global.auth;

import com.vivace.opensw.entity.Member;
import com.vivace.opensw.service.MemberService;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import com.vivace.opensw.global.exception.CustomException;
import com.vivace.opensw.global.exception.ErrorCode;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Duration;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtUtil {
    @Value("${jwt.secret.access}")
    private String key;
    private SecretKey accessKey;
    private static final Duration ACCESS_TOKEN_EXPIRE_TIME = Duration.ofDays(1);
    private final MemberService memberService;

    @PostConstruct
    private void setSecretKey() {
        accessKey = Keys.hmacShaKeyFor(key.getBytes());
    }

    public String generateAccessToken(Authentication authentication){
        Member member = memberService.getActiveMemberByEmail(authentication.getName());
        String currentToken = member.getAccessToken();
        if (currentToken != null && validateAccessToken(currentToken)) {
            // 액세스 토큰이 있으며 유효할 경우에는 accessToken을 바꾸지 않음
            return currentToken;
        }
        String newAccessToken = generateToken(authentication);
        memberService.updateAccessToken(member, newAccessToken);
        return newAccessToken;
    }

    private String generateToken(Authentication authentication) {
        Claims claims = Jwts.claims().setSubject(authentication.getName());

        Date now = new Date();
        Date expiredDate = new Date(now.getTime()+ JwtUtil.ACCESS_TOKEN_EXPIRE_TIME.toMillis());

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiredDate)
                .signWith(accessKey, SignatureAlgorithm.HS512)
                .compact();
    }

    public boolean validateAccessToken(String accessToken){
        try {
            Jwts.parserBuilder().setSigningKey(accessKey).build().parseClaimsJws(accessToken);
            return true;
        } catch (SecurityException | MalformedJwtException | UnsupportedJwtException | IllegalArgumentException e){
            throw new CustomException(ErrorCode.INVALID_TOKEN);
        } catch (ExpiredJwtException e) {
            throw new CustomException(ErrorCode.EXPIRED_ACCESS_TOKEN);
        }
    }

    public Authentication getAuthentication(String token){
        try {
            String userPrincipal = Jwts.parserBuilder()
                    .setSigningKey(accessKey)
                    .build().parseClaimsJws(token)
                    .getBody().getSubject();
            UserDetails userDetails = new CustomUserDetails(memberService.getActiveMemberByEmail(userPrincipal));
            return new UsernamePasswordAuthenticationToken(userDetails,"",userDetails.getAuthorities());
        } catch (SecurityException | MalformedJwtException | UnsupportedJwtException | IllegalArgumentException e){
            throw new CustomException(ErrorCode.INVALID_TOKEN);
        } catch (ExpiredJwtException e){
            throw new CustomException(ErrorCode.EXPIRED_ACCESS_TOKEN);
        }
    }

}
