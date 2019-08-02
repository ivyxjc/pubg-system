package xyz.ivyxjc.pubg.system.transformation.service

import org.apache.commons.lang3.StringUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import xyz.ivyxjc.pubg.system.common.service.PubgMatchRepoService
import xyz.ivyxjc.pubg.system.common.utils.loggerFor
import xyz.ivyxjc.pubg.system.transformation.convert.PubgJsonConvert
import xyz.ivyxjc.pubg.system.transformation.utils.PubgJsonParser

@Service
internal class PubgMatchService {

    companion object {
        private val log = loggerFor(PubgMatchService::class.java)
    }

    @Autowired
    private lateinit var pubgMatchRepoService: PubgMatchRepoService

    @Autowired
    private lateinit var pubgApiService: PubgApiService

    fun process(matchId: String) {
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
        pubgMatchRepoService.insertMatch(summaryDO, detailDO, rosterDO, rosterParticipantDO)
    }

}