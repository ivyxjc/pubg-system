package xyz.ivyxjc.pubg.system.remediation.impl

import org.apache.dubbo.config.annotation.Service
import xyz.ivyxjc.pubg.system.remediation.api.dto.RemediationPlayerMatchDto
import xyz.ivyxjc.pubg.system.remediation.api.service.PubgPlayerHandleService

@Service(version = "1.0.0")
class PubgPlayerHandlerServiceImpl : PubgPlayerHandleService {
    override fun process(remediationPlayerMatchDto: RemediationPlayerMatchDto) {
        println("hello world")
        println(remediationPlayerMatchDto.matchList.size)
    }
}