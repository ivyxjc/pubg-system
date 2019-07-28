package xyz.ivyxjc.pubg.system.transformation.config

import okhttp3.OkHttpClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class HttpConfig {

    @Bean
    open fun httpclient(): OkHttpClient {
        return OkHttpClient
            .Builder()
            .build()
    }
}