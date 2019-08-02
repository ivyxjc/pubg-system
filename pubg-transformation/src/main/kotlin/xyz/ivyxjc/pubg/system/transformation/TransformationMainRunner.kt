package xyz.ivyxjc.pubg.system.transformation

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo
import org.mybatis.spring.annotation.MapperScan
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.PropertySource
import org.springframework.transaction.annotation.EnableTransactionManagement

@SpringBootApplication
@EnableDubbo(scanBasePackages = ["xyz.iyvyxjc.pubg.system.remediation.impl"])
@MapperScan("xyz.ivyxjc.pubg.system.common.dao")
@ComponentScan("xyz.ivyxjc.pubg.system")
@EnableTransactionManagement
@PropertySource(value = ["classpath:\${database.config.env}.properties", "classpath:private.properties"])
open class TransformationMainRunner

fun main() {
    System.setProperty("spring.profiles.active", "dev")
    SpringApplication.run(TransformationMainRunner::class.java)
}