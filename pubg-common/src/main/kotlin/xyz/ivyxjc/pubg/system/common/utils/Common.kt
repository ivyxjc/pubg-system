package xyz.ivyxjc.pubg.system.common.utils

import org.slf4j.LoggerFactory
import java.time.ZoneOffset

fun <T> loggerFor(clz: Class<T>) = LoggerFactory.getLogger(clz)

val DEFAULT_CHARSET = Charsets.UTF_8

val DEFAULT_ZONE_OFFSET = ZoneOffset.UTC
