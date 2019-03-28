package com.itacademy.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({DatabaseConfig.class, JdbcTemplateConfig.class})
public class AppConfig {
}
