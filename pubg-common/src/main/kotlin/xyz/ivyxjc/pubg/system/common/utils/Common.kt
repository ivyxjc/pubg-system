package xyz.ivyxjc.pubg.system.common.utils

import org.slf4j.LoggerFactory

fun <T> loggerFor(clz: Class<T>) = LoggerFactory.getLogger(clz)
