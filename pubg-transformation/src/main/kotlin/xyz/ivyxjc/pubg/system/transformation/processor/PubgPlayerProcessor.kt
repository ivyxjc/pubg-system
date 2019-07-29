package xyz.ivyxjc.pubg.system.transformation.processor

import org.apache.commons.lang3.StringUtils
import org.springframework.beans.factory.annotation.Autowired
import xyz.ivyxjc.pubg.system.common.annotation.Processor
import xyz.ivyxjc.pubg.system.common.entity.RawMessage
import xyz.ivyxjc.pubg.system.common.processors.WorkflowProcessor
import xyz.ivyxjc.pubg.system.common.service.PubgPlayerService
import xyz.ivyxjc.pubg.system.common.utils.loggerFor
import xyz.ivyxjc.pubg.system.transformation.convert.PubgJsonConvert
import xyz.ivyxjc.pubg.system.transformation.service.PubgApiService
import xyz.ivyxjc.pubg.system.transformation.utils.PubgJsonParser


@Processor
class PubgPlayerProcessor : WorkflowProcessor {
    companion object {
        val log = loggerFor(PubgPlayerProcessor::class.java)
    }

    @Autowired
    private lateinit var pubgPlayerService: PubgPlayerService

    @Autowired
    private lateinit var pubgApiService: PubgApiService

    override fun process(rawMessage: RawMessage) {
        val playerName = rawMessage.message
        val playerJson = pubgApiService.getPlayerJson(playerName)
        if (StringUtils.isBlank(playerJson)) {
            log.error("fail to get player json for player $playerName")
            return
        }
        val playerJO = PubgJsonParser.parserPlayer(playerJson!!)
        val pubgPlayerDO = PubgJsonConvert.convertToPubgPlayerDO(playerJO)
        val pubgPlayerMatchDO = PubgJsonConvert.convertToPubgPlayerMatchDO(playerJO)
        pubgPlayerService.insertPlayer(pubgPlayerDO, pubgPlayerMatchDO)
    }
}
