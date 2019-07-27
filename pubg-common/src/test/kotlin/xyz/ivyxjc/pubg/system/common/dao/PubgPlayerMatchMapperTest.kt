package xyz.ivyxjc.pubg.system.common.dao

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import xyz.ivyxjc.pubg.system.common.entity.PubgPlayerMatchDTO
import xyz.ivyxjc.pubg.system.common.types.ShardId
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.concurrent.locks.LockSupport
import kotlin.random.Random

@RunWith(SpringRunner::class)
@SpringBootTest
open class PubgPlayerMatchMapperTest {

    @Autowired
    private lateinit var pubgPlayerMatchMapper: PubgPlayerMatchMapper

    companion object {
        private val SHARD_IDS = arrayOf(
            ShardId.STEAM,
            ShardId.XBOX,
            ShardId.KAKAO,
            ShardId.PSN
        )
    }

    private fun checkPubgPlayerMatchList(expect: List<PubgPlayerMatchDTO>, real: List<PubgPlayerMatchDTO>) {
        Assert.assertEquals(expect.size, real.size)
        val map =
            expect.stream().collect({ mutableMapOf<String, PubgPlayerMatchDTO>() },
                { container, item ->
                    container[item.matchId] = item
                }, { a, b ->
                    a.putAll(b)
                })
        real.forEach {
            val expectDTO = map.get(it.matchId)
            Assert.assertNotNull(expectDTO)
            checkPubgPlayerMatch(expectDTO!!, it)
        }
    }

    private fun checkPubgPlayerMatch(expect: PubgPlayerMatchDTO, real: PubgPlayerMatchDTO) {
        Assert.assertEquals(expect.playerId, real.playerId)
        Assert.assertEquals(expect.shardId, real.shardId)
        Assert.assertEquals(expect.matchId, real.matchId)
    }

    private fun preparePubgPlayerMatch(): List<PubgPlayerMatchDTO> {
        val matchCount = Random.nextInt(20, 100)
        val playerId = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli().toString()
        val res = MutableList(matchCount) {
            val pubgPlayerMatchDTO = PubgPlayerMatchDTO()
            pubgPlayerMatchDTO.playerId = playerId
            pubgPlayerMatchDTO.matchId = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli().toString()
            LockSupport.parkNanos(Math.pow(10.0, 6.0).toLong())
            pubgPlayerMatchDTO.shardId = SHARD_IDS[Random.nextInt(0, SHARD_IDS.size)]
            pubgPlayerMatchDTO
        }
        val map =
            res.stream().collect({ mutableMapOf<String, PubgPlayerMatchDTO>() },
                { container, item ->
                    container[item.matchId] = item
                }, { a, b ->
                    a.putAll(b)
                })
        Assert.assertEquals(res.size, map.values.size)
        return res

    }

    @Test
    fun testInsertPubgPlayerMatchList() {
        val pubgPlayerMatchList = preparePubgPlayerMatch()
        pubgPlayerMatchMapper.insertPubgPlayerMatchList(pubgPlayerMatchList)

        val queryRes = pubgPlayerMatchMapper.queryByPlayerId(pubgPlayerMatchList[0].playerId)
        Assert.assertEquals(pubgPlayerMatchList.size, queryRes.size)
        checkPubgPlayerMatchList(pubgPlayerMatchList, queryRes)
    }

    @Test
    fun testQueryByPlaeryId() {
        val pubgPlayerMatchList = preparePubgPlayerMatch()
        pubgPlayerMatchMapper.insertPubgPlayerMatchList(pubgPlayerMatchList)

        val queryRes = pubgPlayerMatchMapper.queryByPlayerId(pubgPlayerMatchList[0].playerId)
        Assert.assertEquals(pubgPlayerMatchList.size, queryRes.size)
        checkPubgPlayerMatchList(pubgPlayerMatchList, queryRes)
    }

    @Test
    fun testQueryByMatchId() {
        val pubgPlayerMatchList = preparePubgPlayerMatch()
        pubgPlayerMatchMapper.insertPubgPlayerMatchList(pubgPlayerMatchList)

        for (i in 0 until pubgPlayerMatchList.size) {
            val queryRes = pubgPlayerMatchMapper.queryByMatchId(pubgPlayerMatchList[i].matchId)
            checkPubgPlayerMatch(pubgPlayerMatchList[i], queryRes)
        }
    }


    @Test
    fun testDeleteByPlaeryId() {
        val pubgPlayerMatchList = preparePubgPlayerMatch()
        pubgPlayerMatchMapper.insertPubgPlayerMatchList(pubgPlayerMatchList)

        val deleteRes = pubgPlayerMatchMapper.deleteByPlayerId(pubgPlayerMatchList[0].playerId)
        Assert.assertEquals(pubgPlayerMatchList.size, deleteRes)
    }
}
