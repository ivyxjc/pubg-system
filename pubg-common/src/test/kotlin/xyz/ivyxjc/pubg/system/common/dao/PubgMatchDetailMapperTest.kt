package xyz.ivyxjc.pubg.system.common.dao

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import xyz.ivyxjc.pubg.system.common.entity.PubgMatchDetailDO
import xyz.ivyxjc.pubg.system.common.types.ShardId
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.concurrent.locks.LockSupport
import kotlin.math.pow
import kotlin.random.Random

@RunWith(SpringRunner::class)
@SpringBootTest
class PubgMatchDetailMapperTest {

    @Autowired
    private lateinit var pubgMatchDetailMapper: PubgMatchDetailMapper

    private fun prepareMatchDetails(): List<PubgMatchDetailDO> {
        val matchCount = Random.nextInt(100, 500)
        val matchId = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli().toString()

        return MutableList(matchCount) {
            val matchDetail = PubgMatchDetailDO()
            matchDetail.matchId = matchId
            LockSupport.parkNanos(10.0.pow(6.0).toLong())
            matchDetail.playerId = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli().toString()
            matchDetail.shardId = ShardId.STEAM
            matchDetail.timeSurvived = Random.nextDouble()
            matchDetail
        }
    }

    private fun checkMatchDetail(expect: PubgMatchDetailDO, real: PubgMatchDetailDO) {
        Assert.assertEquals(expect.playerId, real.playerId)
        Assert.assertEquals(expect.matchId, real.matchId)
        Assert.assertEquals(expect.dbnos, real.dbnos)

    }

    @Test
    fun testInsertMatchDetail() {
        val data = prepareMatchDetails()
        val res = pubgMatchDetailMapper.insertMatchDetail(data[0])
        Assert.assertEquals(1, res)
    }

    @Test
    fun testInsertMatchDetails() {
        val data = prepareMatchDetails()
        val res = pubgMatchDetailMapper.insertMatchDetails(data)
        Assert.assertEquals(data.size, res)
    }

    @Test
    fun testListByMatchId() {
        val data = prepareMatchDetails()
        pubgMatchDetailMapper.insertMatchDetails(data)

        val res = pubgMatchDetailMapper.listByMatchId(data[0].matchId)
        Assert.assertEquals(data.size, res.size)
    }

    @Test
    fun testQueryByPlayerIdMatchId() {
        val data = prepareMatchDetails()
        pubgMatchDetailMapper.insertMatchDetails(data)

        val res = pubgMatchDetailMapper.queryByPlayerIdMatchId(data[10].playerId, data[10].matchId)
        checkMatchDetail(data[10], res)
    }
}