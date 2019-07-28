package xyz.ivyxjc.pubg.system.common.dao

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import xyz.ivyxjc.pubg.system.common.entity.PubgMatchRosterParticipantDO
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.concurrent.locks.LockSupport
import kotlin.math.pow
import kotlin.random.Random

@RunWith(SpringRunner::class)
@SpringBootTest
class PubgRosterParticipantMapperTest {
    @Autowired
    private lateinit var pubgRosterParticipantMapper: PubgRosterParticipantMapper

    private fun prepareRosterParticipants(): List<PubgMatchRosterParticipantDO> {
        val rosterCount = Random.nextInt(1, 10)
        val rosterId = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli().toString()

        return MutableList(rosterCount) {
            val rosterParticipant = PubgMatchRosterParticipantDO()
            rosterParticipant.rosterId = rosterId
            rosterParticipant.playerId = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli().toString()
            LockSupport.parkNanos(10.0.pow(6.0).toLong())
            rosterParticipant
        }
    }

    private fun checkRosterParticipant(expect: PubgMatchRosterParticipantDO, real: PubgMatchRosterParticipantDO) {
        Assert.assertEquals(expect.rosterId, real.rosterId)
        Assert.assertEquals(expect.playerId, real.playerId)
    }

    @Test
    fun testInsertRosterParticipant() {
        val data = prepareRosterParticipants()
        val res = pubgRosterParticipantMapper.insertRosterParticipant(data[0])
        Assert.assertEquals(1, res)
    }

    @Test
    fun testInsertRosterParticipants() {
        val data = prepareRosterParticipants()
        val res = pubgRosterParticipantMapper.insertRosterParticipants(data)
        Assert.assertEquals(data.size, res)
    }

    @Test
    fun testListByRosterId() {
        val data = prepareRosterParticipants()
        pubgRosterParticipantMapper.insertRosterParticipants(data)
        val res = pubgRosterParticipantMapper.listByRosterId(data[0].rosterId)
        Assert.assertEquals(data.size, res.size)
    }

    @Test
    fun testDeleteByRosterId() {
        val data = prepareRosterParticipants()
        pubgRosterParticipantMapper.insertRosterParticipants(data)
        val res = pubgRosterParticipantMapper.deleteByRosterId(data[0].rosterId)
        Assert.assertEquals(data.size, res)
    }
}
