package xyz.ivyxjc.pubg.system.common.entity

import java.time.LocalDateTime

class RawMessage(val guid: Long, val message: String) {

    val dbCreatedAt: LocalDateTime? = null
    val dbCreatedBy: String? = null
    val dbCreatedFrom: String? = null
    val dbUpdatedAt: LocalDateTime? = null
    val dbUpdatedBy: String? = null
    val dbUpdatedFrom: String? = null
}


