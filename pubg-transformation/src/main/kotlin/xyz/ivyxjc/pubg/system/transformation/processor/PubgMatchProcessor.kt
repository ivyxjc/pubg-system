package xyz.ivyxjc.pubg.system.transformation.processor

import org.apache.commons.lang3.StringUtils
import org.springframework.beans.factory.annotation.Autowired
import xyz.ivyxjc.pubg.system.common.annotation.Processor
import xyz.ivyxjc.pubg.system.common.entity.RawMessage
import xyz.ivyxjc.pubg.system.common.processors.WorkflowProcessor
import xyz.ivyxjc.pubg.system.common.service.PubgMatchService
import xyz.ivyxjc.pubg.system.common.utils.loggerFor
import xyz.ivyxjc.pubg.system.transformation.convert.PubgJsonConvert
import xyz.ivyxjc.pubg.system.transformation.service.PubgApiService
import xyz.ivyxjc.pubg.system.transformation.utils.PubgJsonParser

@Processor
class PubgMatchProcessor : WorkflowProcessor {
    companion object {
        val log = loggerFor(PubgPlayerProcessor::class.java)
    }

    @Autowired
    private lateinit var pubgMatchService: PubgMatchService

    @Autowired
    private lateinit var pubgApiService: PubgApiService

    override fun process(rawMessage: RawMessage) {
        val matchId = rawMessage.message
        val matchJson = pubgApiService.getMatchJson(matchId)
        if (StringUtils.isBlank(matchJson)) {
            log.error("fail to get match json for matchId $matchId")
            return
        }
        val matchJO = PubgJsonParser.parserMatch(matchJson!!)
        val summaryDO = PubgJsonConvert.convertToPubgMatchSummaryDO(matchJO)
        val detailDO = PubgJsonConvert.convertToPubgMatchDetailDO(matchJO)
        val rosterDO = PubgJsonConvert.convertToPubgMatchRosterDO(matchJO)
        val rosterParticipantDO = PubgJsonConvert.convertToPubgMatchRosterParticipantDO(matchJO)
        pubgMatchService.insertMatch(summaryDO, detailDO, rosterDO, rosterParticipantDO)
    }
}