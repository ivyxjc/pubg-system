package xyz.ivyxjc.pubg.system.common.dao

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import xyz.ivyxjc.pubg.system.common.entity.PubgMatchSummaryDO
import xyz.ivyxjc.pubg.system.common.types.GameMode
import xyz.ivyxjc.pubg.system.common.types.MapName
import xyz.ivyxjc.pubg.system.common.types.ShardId
import java.time.Duration
import java.time.LocalDateTime
import java.time.ZoneOffset
import kotlin.math.abs

@RunWith(SpringRunner::class)
@SpringBootTest
class PubgMatchSummaryTest {

    @Autowired
    private lateinit var pubgMatchSummaryMapper: PubgMatchSummaryMapper

    private fun prepareMatchSummary(): PubgMatchSummaryDO {
        val matchSummary = PubgMatchSummaryDO()
        matchSummary.matchId = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli().toString()
        matchSummary.createdAt = LocalDateTime.now()
        matchSummary.shardId = ShardId.STEAM
        matchSummary.duration = 100
        matchSummary.gameMode = GameMode.DUO
        matchSummary.mapName = MapName.BALTIC_MAIN
        matchSummary.titleId = null
        matchSummary.assetUrl =
            "https://telemetry-cdn.playbattlegrounds.com/bluehole-pubg/steam/2019/07/26/15/46/93efcb34-afbc-11e9-abfa-0a586468ac85-telemetry.json"
        return matchSummary
    }

    private fun checkMatchSummary(expect: PubgMatchSummaryDO, real: PubgMatchSummaryDO) {
        Assert.assertEquals(expect.matchId, real.matchId)
        Assert.assertEquals(expect.shardId, real.shardId)
        Assert.assertEquals(expect.duration, real.duration)
        Assert.assertEquals(expect.gameMode, real.gameMode)
        Assert.assertEquals(expect.mapName, real.mapName)
        Assert.assertEquals(expect.titleId, real.titleId)
        Assert.assertEquals(expect.assetUrl, real.assetUrl)
        Assert.assertTrue(abs(Duration.between(expect.createdAt, real.createdAt).toMillis()) < 1)
    }

    @Test
    fun testInsertPubgMatchSummary() {
        val matchSummaryDO = prepareMatchSummary();
        val res = pubgMatchSummaryMapper.insertMatchSummary(matchSummaryDO)
        Assert.assertEquals(1, res)
    }

    @Test
    fun testQueryByMatchId() {
        val matchSummaryDO = prepareMatchSummary();
        val res = pubgMatchSummaryMapper.insertMatchSummary(matchSummaryDO)

        val queryRes = pubgMatchSummaryMapper.queryByMatchId(matchSummaryDO.matchId)
        checkMatchSummary(matchSummaryDO, queryRes)
    }


}