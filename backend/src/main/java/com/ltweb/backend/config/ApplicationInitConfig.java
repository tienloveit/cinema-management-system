package com.ltweb.backend.config;

import com.ltweb.backend.service.DataSeedService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = "app.seed", name = "on-startup", havingValue = "true")
public class ApplicationInitConfig {

  private final DataSeedService dataSeedService;

  @Bean
  public ApplicationRunner applicationRunner() {
    return args -> dataSeedService.seedInitialData();
  }
}
