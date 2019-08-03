package xyz.ivyxjc.pubg.system.remediation.application

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.PropertySource

@SpringBootApplication
@EnableDubbo(scanBasePackages = ["xyz.ivyxjc.pubg.system.remediation.impl"])
@ComponentScan("xyz.ivyxjc.pubg.system.remediation.impl")
@PropertySource(value = ["classpath:\${database.config.env}.properties", "classpath:private.properties"])
open class PubgApplication

fun main() {
    SpringApplication.run(PubgApplication::class.java)
}
