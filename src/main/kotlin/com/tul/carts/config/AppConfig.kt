package com.tul.carts.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import javax.sql.DataSource
import org.springframework.web.servlet.config.annotation.CorsRegistry

@Configuration
class AppConfig {

    @Bean
    fun dataSource(): DataSource =
        EmbeddedDatabaseBuilder()
            .setType(EmbeddedDatabaseType.H2)
            .addScript("classpath:scripts/schema.sql")
            .build()

    @Bean
    fun corsConfig(): WebMvcConfigurer {
        return object : WebMvcConfigurer {
            override fun addCorsMappings(registry: CorsRegistry) {
                registry.addMapping("/**").allowedOrigins("http://localhost:4200").allowedMethods("*")
            }
        }
    }
}
