package com.itacademy;

import com.itacademy.config.DatabaseConfig;
import com.itacademy.config.JdbcTemplateConfig;
import com.itacademy.config.SessionFactoryConfig;
import com.itacademy.config.SwaggerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@EnableAspectJAutoProxy
@EnableTransactionManagement(proxyTargetClass = true)
@SpringBootApplication(scanBasePackages = {"com.itacademy"},
        exclude = {
                JacksonAutoConfiguration.class,
                HibernateJpaAutoConfiguration.class
        })
@Import({DatabaseConfig.class,
        JdbcTemplateConfig.class,
        SwaggerConfig.class,
        SessionFactoryConfig.class
})

public class SpringBootHibernateApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {

        SpringApplication.run(SpringBootHibernateApplication.class, args);
    }

}
