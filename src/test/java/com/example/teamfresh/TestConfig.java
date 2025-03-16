package com.example.teamfresh;

import com.example.teamfresh.domain.order.service.OrderService;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@TestConfiguration
@SpringBootTest
public class TestConfig {

}