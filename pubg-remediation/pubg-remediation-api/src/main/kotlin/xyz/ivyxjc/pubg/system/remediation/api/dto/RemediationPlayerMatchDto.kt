package xyz.ivyxjc.pubg.system.remediation.api.dto

import java.io.Serializable

class RemediationPlayerMatchDto : Serializable {
    lateinit var playerId: String
    val matchList = mutableListOf<String>()
}
