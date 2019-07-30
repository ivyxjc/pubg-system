package xyz.ivyxjc.pubg.system.transformation.processor

import com.google.gson.Gson
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.annotation.KafkaHandler
import org.springframework.kafka.annotation.KafkaListener
import xyz.ivyxjc.pubg.system.common.annotation.Processor
import xyz.ivyxjc.pubg.system.common.entity.RawMessage
import xyz.ivyxjc.pubg.system.common.processors.WorkflowProcessor
import xyz.ivyxjc.pubg.system.common.utils.loggerFor
import xyz.ivyxjc.pubg.system.transformation.jo.PubgRawJO
import xyz.ivyxjc.pubg.system.transformation.service.PubgMatchService
import xyz.ivyxjc.pubg.system.transformation.service.PubgPlayerService

@Processor
@KafkaListener(id = "pubgGroup", topics = ["pubg-transform-dev"])
class PubgTransformationProcessor : WorkflowProcessor {

    companion object {
        private val log = loggerFor(PubgTransformationProcessor::class.java)
    }

    @Autowired
    private lateinit var gson: Gson

    @Autowired
    private lateinit var pubgPlayerService: PubgPlayerService

    @Autowired
    private lateinit var pubgMatchService: PubgMatchService

    @KafkaHandler
    override fun process(rawMessage: RawMessage) {
        val pubgRawJO = gson.fromJson(rawMessage.message, PubgRawJO::class.java)
        when (pubgRawJO.type) {
            "player_name" -> pubgPlayerService.processPlayerName(pubgRawJO.value)
            "player_id" -> pubgPlayerService.processPlayerName(pubgRawJO.value)
            "match_id" -> pubgPlayerService.processPlayerName(pubgRawJO.value)
            else -> {
                log.error("Not support the type: ${pubgRawJO.type}")
            }
        }
    }
}