package xyz.ivyxjc.pubg.system.common.dao

import org.apache.commons.lang3.RandomStringUtils
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import xyz.ivyxjc.pubg.system.common.entity.PubgPlayerDO
import xyz.ivyxjc.pubg.system.common.types.ShardId
import java.time.Duration
import java.time.LocalDateTime
import java.time.ZoneOffset
import kotlin.math.abs


@RunWith(SpringRunner::class)
@SpringBootTest
class PubgPlayerMapperTest {

    @Autowired
    private lateinit var pubgPlayerMapper: PubgPlayerMapper

    private fun preparePlayerDTO(): PubgPlayerDO {
        val pubgPlayerDTO = PubgPlayerDO()
        pubgPlayerDTO.playerId = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC).toString()
        pubgPlayerDTO.name = RandomStringUtils.random(10)
        pubgPlayerDTO.createdAt = LocalDateTime.now()
        pubgPlayerDTO.updatedAt = LocalDateTime.now()
        pubgPlayerDTO.shardId = ShardId.STEAM
        return pubgPlayerDTO
    }

    private fun checkPubgPlayerDTO(expect: PubgPlayerDO, real: PubgPlayerDO) {
        Assert.assertNotNull(expect)
        Assert.assertEquals(expect.name, real.name)
        /*
            for the reason that db just can contains 6 decimal digit,
            we need to calculate the duration rather than to assert them equal
         */
        Assert.assertTrue(abs(Duration.between(expect.createdAt, real.createdAt).toMillis()) < 1)
        Assert.assertTrue(abs(Duration.between(expect.updatedAt, real.updatedAt).toMillis()) < 1)
        Assert.assertEquals(expect.shardId, real.shardId)
    }

    @Test
    fun testInsertPubgPlayer() {
        val pubgPlayerDTO = preparePlayerDTO()
        pubgPlayerMapper.insertPubgPlayer(pubgPlayerDTO)

        val queryRes = pubgPlayerMapper.queryByPlayerId(pubgPlayerDTO.playerId)
        checkPubgPlayerDTO(pubgPlayerDTO, queryRes!!)

    }

    fun testQueryByPlayerId() {
        val pubgPlayerDTOInDb = preparePlayerDTO()
        pubgPlayerMapper.insertPubgPlayer(pubgPlayerDTOInDb)

        val pubgPlayerDTO = pubgPlayerMapper.queryByPlayerId(pubgPlayerDTOInDb.playerId)
        checkPubgPlayerDTO(pubgPlayerDTOInDb, pubgPlayerDTO!!)
    }

    fun testQueryByPlayername() {
        val pubgPlayerDTOInDb = preparePlayerDTO()
        pubgPlayerMapper.insertPubgPlayer(pubgPlayerDTOInDb)

        val pubgPlayerDTO = pubgPlayerMapper.queryByPlayerName(pubgPlayerDTOInDb.playerId)
        checkPubgPlayerDTO(pubgPlayerDTOInDb, pubgPlayerDTO!!)
    }


    fun testDeleteByPlayerId() {
        val pubgPlayerDTOInDb = preparePlayerDTO()
        pubgPlayerMapper.insertPubgPlayer(pubgPlayerDTOInDb)

        var queryRes = pubgPlayerMapper.queryByPlayerName(pubgPlayerDTOInDb.playerId)
        checkPubgPlayerDTO(pubgPlayerDTOInDb, queryRes!!)

        pubgPlayerMapper.deleteByPubgPlayerId(pubgPlayerDTOInDb.playerId)

        queryRes = pubgPlayerMapper.queryByPlayerName(pubgPlayerDTOInDb.playerId)
        Assert.assertNull(queryRes)
    }

    fun testDeleteByPlayername() {
        val pubgPlayerDTOInDb = preparePlayerDTO()
        pubgPlayerMapper.insertPubgPlayer(pubgPlayerDTOInDb)

        var queryRes = pubgPlayerMapper.queryByPlayerName(pubgPlayerDTOInDb.playerId)
        checkPubgPlayerDTO(pubgPlayerDTOInDb, queryRes!!)

        pubgPlayerMapper.deleteByPubgName(pubgPlayerDTOInDb.name!!)

        queryRes = pubgPlayerMapper.queryByPlayerName(pubgPlayerDTOInDb.playerId)
        Assert.assertNull(queryRes)
    }

}