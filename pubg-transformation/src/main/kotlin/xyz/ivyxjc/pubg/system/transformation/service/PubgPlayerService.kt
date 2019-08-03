package xyz.ivyxjc.pubg.system.transformation.service

import org.apache.commons.lang3.StringUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import xyz.ivyxjc.pubg.system.common.service.PubgPlayerRepoService
import xyz.ivyxjc.pubg.system.common.utils.loggerFor
import xyz.ivyxjc.pubg.system.remediation.api.dto.RemediationPlayerMatchDto
import xyz.ivyxjc.pubg.system.transformation.convert.PubgJsonConvert
import xyz.ivyxjc.pubg.system.transformation.utils.PubgJsonParser


@Service
internal class PubgPlayerService {

    companion object {
        private val log = loggerFor(PubgPlayerService::class.java)
    }

    @Autowired
    private lateinit var pubgApiService: PubgApiService

    @Autowired
    private lateinit var pubgPlayerRepoService: PubgPlayerRepoService

    fun processPlayerName(playerName: String, remediationPlayerMatchDto: RemediationPlayerMatchDto) {
        val playerJson = pubgApiService.getPlayerJsonByName(playerName)
        if (StringUtils.isBlank(playerJson)) {
            log.error("fail to get player json for player $playerName")
            return
        }
        val playerJO = PubgJsonParser.parserPlayer(playerJson!!)
        val pubgPlayerDO = PubgJsonConvert.convertToPubgPlayerDO(playerJO)
        val pubgPlayerMatchDOs = PubgJsonConvert.convertToPubgPlayerMatchDO(playerJO)
        pubgPlayerRepoService.insertPlayer(pubgPlayerDO, pubgPlayerMatchDOs)
        remediationPlayerMatchDto.playerId = pubgPlayerDO.playerId
        pubgPlayerMatchDOs.map {
            it.matchId
        }.toCollection(remediationPlayerMatchDto.matchList)
    }

    fun processPlayerId(playerId: String, remediationPlayerMatchDto: RemediationPlayerMatchDto) {
        val playerJson = pubgApiService.getPlayerJsonById(playerId)
        if (StringUtils.isBlank(playerJson)) {
            log.error("fail to get player json for player $playerId")
            return
        }
        val playerJO = PubgJsonParser.parserPlayer(playerJson!!)
        val pubgPlayerDO = PubgJsonConvert.convertToPubgPlayerDO(playerJO)
        val pubgPlayerMatchDOs = PubgJsonConvert.convertToPubgPlayerMatchDO(playerJO)
        pubgPlayerRepoService.insertPlayer(pubgPlayerDO, pubgPlayerMatchDOs)
        remediationPlayerMatchDto.playerId = playerId
        pubgPlayerMatchDOs.map {
            it.matchId
        }.toCollection(remediationPlayerMatchDto.matchList)
    }
}