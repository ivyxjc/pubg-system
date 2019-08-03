package xyz.ivyxjc.pubg.system.transformation.processor

import com.google.gson.Gson
import org.apache.dubbo.config.annotation.Reference
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.annotation.KafkaHandler
import org.springframework.kafka.annotation.KafkaListener
import xyz.ivyxjc.pubg.system.common.annotation.Processor
import xyz.ivyxjc.pubg.system.common.entity.RawMessage
import xyz.ivyxjc.pubg.system.common.processors.WorkflowProcessor
import xyz.ivyxjc.pubg.system.common.utils.loggerFor
import xyz.ivyxjc.pubg.system.remediation.api.dto.RemediationPlayerMatchDto
import xyz.ivyxjc.pubg.system.remediation.api.service.PubgPlayerHandleService
import xyz.ivyxjc.pubg.system.transformation.jo.PubgRawJO
import xyz.ivyxjc.pubg.system.transformation.service.PubgMatchService
import xyz.ivyxjc.pubg.system.transformation.service.PubgPlayerService

@Processor
@KafkaListener(id = "pubgGroup", topics = ["pubg-transform-dev"])
internal class PubgTransformationProcessor : WorkflowProcessor {

    companion object {
        private val log = loggerFor(PubgTransformationProcessor::class.java)
    }

    @Autowired
    private lateinit var gson: Gson

    @Autowired
    private lateinit var pubgPlayerService: PubgPlayerService

    @Autowired
    private lateinit var pubgMatchService: PubgMatchService

    @Reference(interfaceClass = PubgPlayerHandleService::class, version = "1.0.0")
    private lateinit var pubgPlayerHandleService: PubgPlayerHandleService

    @KafkaHandler
    override fun process(rawMessage: RawMessage) {
        val pubgRawJO = gson.fromJson(rawMessage.message, PubgRawJO::class.java)
        val remediationPlayerMatchDto = RemediationPlayerMatchDto()
        when (pubgRawJO.type) {
            "player_name" -> {
                pubgPlayerService.processPlayerName(pubgRawJO.value, remediationPlayerMatchDto)
                pubgPlayerHandleService.process(remediationPlayerMatchDto)
            }
            "player_id" -> {
                pubgPlayerService.processPlayerId(pubgRawJO.value, remediationPlayerMatchDto)
                pubgPlayerHandleService.process(remediationPlayerMatchDto)
            }
            "match_id" -> {
                pubgMatchService.process(pubgRawJO.value)
            }
            else -> {
                log.error("Not support the type: ${pubgRawJO.type}")
            }
        }
    }
}