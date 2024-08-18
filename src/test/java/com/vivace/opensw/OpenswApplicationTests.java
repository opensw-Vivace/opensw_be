package com.vivace.opensw;

import com.vivace.opensw.model.ArtifactStatus;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OpenswApplicationTests {

	@Test
	void contextLoads() {
		System.out.println(Enum.valueOf(ArtifactStatus.class, "NOT_STARTED"));
	}

}
