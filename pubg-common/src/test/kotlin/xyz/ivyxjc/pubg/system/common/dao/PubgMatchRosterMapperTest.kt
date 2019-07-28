package xyz.ivyxjc.pubg.system.common.dao

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import xyz.ivyxjc.pubg.system.common.entity.PubgMatchRosterDO
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.concurrent.locks.LockSupport
import kotlin.math.pow
import kotlin.random.Random

@RunWith(SpringRunner::class)
@SpringBootTest
class PubgMatchRosterMapperTest {
    @Autowired
    private lateinit var pubgMatchRosterMapper: PubgMatchRosterMapper

    private fun prepareMatchRosters(): List<PubgMatchRosterDO> {
        val rosterCount = Random.nextInt(20, 100)
        val matchId = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli().toString()

        return MutableList(rosterCount) {
            val matchRoster = PubgMatchRosterDO()
            matchRoster.matchId = matchId
            matchRoster.win = false
            matchRoster.rank = rosterCount
            matchRoster.rosterId = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli().toString()
            LockSupport.parkNanos(10.0.pow(6.0).toLong())
            matchRoster
        }
    }

    @Test
    fun testInsertMatchRoster() {
        val data = prepareMatchRosters()
        val res = pubgMatchRosterMapper.insertMatchRoster(data[0])
        Assert.assertEquals(1, res)
    }

    @Test
    fun testInsertMatchRosters() {
        val data = prepareMatchRosters()
        val res = pubgMatchRosterMapper.insertMatchRosters(data)
        Assert.assertEquals(data.size, res)
    }

    @Test
    fun testListByMatchId() {
        val data = prepareMatchRosters()
        pubgMatchRosterMapper.insertMatchRosters(data)

        val res = pubgMatchRosterMapper.listByMatchId(data[0].matchId)
        Assert.assertEquals(data.size, res.size)
    }

    @Test
    fun testDeleteByMatchId() {
        val data = prepareMatchRosters()
        pubgMatchRosterMapper.insertMatchRosters(data)

        val res = pubgMatchRosterMapper.deleteByMatchId(data[0].matchId)
        Assert.assertEquals(data.size, res)
    }
}