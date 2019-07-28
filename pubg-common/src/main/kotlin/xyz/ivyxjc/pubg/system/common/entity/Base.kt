package xyz.ivyxjc.pubg.system.common.entity

import java.time.LocalDateTime

open class BaseDO {
    var dbCreatedAt: LocalDateTime? = null
    var dbCreatedBy: String? = null
    var dbCreatedFrom: String? = null
    var dbUpdatedAt: LocalDateTime? = null
    var dbUpdatedBy: String? = null
    var dbUpdatedFrom: String? = null
}