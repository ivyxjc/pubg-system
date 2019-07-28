package xyz.ivyxjc.pubg.system.common.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import xyz.ivyxjc.pubg.system.common.dao.*
import xyz.ivyxjc.pubg.system.common.entity.*

interface PubgPlayerService {
    fun insertPlayer(player: PubgPlayerDO, playerMatch: List<PubgPlayerMatchDO>)
}

interface PubgMatchService {

    fun insertMatch(
        matchSummary: PubgMatchSummaryDO,
        matchDetailDOs: List<PubgMatchDetailDO>,
        matchRosterDOs: List<PubgMatchRosterDO>,
        matchRosterParticipantDOs: List<PubgMatchRosterParticipantDO>
    )
}

@Service
open class PubgPlayerServiceImpl : PubgPlayerService {

    @Autowired
    private lateinit var pubgPlayerMapper: PubgPlayerMapper

    @Autowired
    private lateinit var pubgPlayerMatchMapper: PubgPlayerMatchMapper

    @Transactional
    override fun insertPlayer(player: PubgPlayerDO, playerMatch: List<PubgPlayerMatchDO>) {
        pubgPlayerMapper.insertPubgPlayer(player)
        pubgPlayerMatchMapper.insertPubgPlayerMatchs(playerMatch)
    }
}

@Service
open class PubgMatchServiceImpl : PubgMatchService {

    @Autowired
    private lateinit var pubgMatchSummaryMapper: PubgMatchSummaryMapper
    @Autowired
    private lateinit var pubgMatchDetailMapper: PubgMatchDetailMapper
    @Autowired
    private lateinit var pubgMatchRosterMapper: PubgMatchRosterMapper
    @Autowired
    private lateinit var pubgMatchRosterParticipantMapper: PubgRosterParticipantMapper

    @Transactional
    override fun insertMatch(
        matchSummary: PubgMatchSummaryDO,
        matchDetailDOs: List<PubgMatchDetailDO>,
        matchRosterDOs: List<PubgMatchRosterDO>,
        matchRosterParticipantDOs: List<PubgMatchRosterParticipantDO>
    ) {
        pubgMatchSummaryMapper.insertMatchSummary(matchSummary)
        pubgMatchDetailMapper.insertMatchDetails(matchDetailDOs)
        pubgMatchRosterMapper.insertMatchRosters(matchRosterDOs)
        pubgMatchRosterParticipantMapper.insertRosterParticipants(matchRosterParticipantDOs)
    }

}

