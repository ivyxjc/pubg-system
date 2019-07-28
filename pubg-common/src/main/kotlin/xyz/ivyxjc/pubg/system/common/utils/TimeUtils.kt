package xyz.ivyxjc.pubg.system.common.utils

import org.apache.commons.lang3.StringUtils
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class TimeUtils {
    companion object {
        fun parserDateTime(dateTimeStr: String?, pattern: String = "yyyy-MM-dd'T'HH:mm:ss'Z'"): LocalDateTime? {
            if (StringUtils.isBlank(dateTimeStr)) {
                return null
            }
            return LocalDateTime.parse(dateTimeStr, DateTimeFormatter.ofPattern(pattern))
        }
    }

}