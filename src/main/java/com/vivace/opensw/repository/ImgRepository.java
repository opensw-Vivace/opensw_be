package com.vivace.opensw.repository;

import com.vivace.opensw.entity.Img;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImgRepository extends JpaRepository<Img, Long> {
    public Optional<Img> findByPath(String path); //검증 필요.
}
