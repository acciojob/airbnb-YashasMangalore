package com.driver1.test;

import com.driver1.AirBnbtemp;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = AirBnbtemp.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestCases {

}


