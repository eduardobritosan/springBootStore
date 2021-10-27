package com.eduardo.store;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = StoreApplication.class)
@SpringBootTest
class StoreApplicationTests {

	@Test
	void contextLoads() {
		Assertions.assertTrue(true);
	}

}
