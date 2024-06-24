package com.driver1.test;

import com.driver1.AirBnb;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = AirBnb.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestCases {

}


