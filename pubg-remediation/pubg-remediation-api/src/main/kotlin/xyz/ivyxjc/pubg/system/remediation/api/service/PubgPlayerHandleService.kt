package xyz.ivyxjc.pubg.system.remediation.api.service

import xyz.ivyxjc.pubg.system.remediation.api.dto.RemediationPlayerMatchDto


interface PubgPlayerHandleService {
    fun process(remediationPlayerMatchDto: RemediationPlayerMatchDto)
}


