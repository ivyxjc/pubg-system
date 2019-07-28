package xyz.ivyxjc.pubg.system.transformation.convert

import org.apache.commons.lang3.StringUtils
import org.junit.Assert
import org.junit.Test
import xyz.ivyxjc.pubg.system.common.types.GameMode
import xyz.ivyxjc.pubg.system.common.types.MapName
import xyz.ivyxjc.pubg.system.common.types.ShardId
import xyz.ivyxjc.pubg.system.transformation.utils.PubgJsonParser
import xyz.ivyxjc.pubg.system.transformation.utils.PubgJsonParserTest
import java.time.LocalDateTime

class PubgJsonConvertTest {

    @Test
    fun testParserJsonPlayer() {
        val input = PubgJsonParserTest::class.java.classLoader.getResourceAsStream("test-data/player.json")
        val playerJO = PubgJsonParser.parserPlayer(input!!)
        val res = PubgJsonConvert.convertToPubgPlayerDO(playerJO)
        Assert.assertEquals("account.7c20cb2e1e3d4331adcf712cec236602", res.playerId)
        Assert.assertEquals(LocalDateTime.of(2019, 7, 27, 9, 54, 35), res.createdAt)
        Assert.assertEquals("cheese_1109", res.name)
    }


    @Test
    fun testParserJsonPlayerMatch() {
        val input = PubgJsonParserTest::class.java.classLoader.getResourceAsStream("test-data/player.json")
        val playerJO = PubgJsonParser.parserPlayer(input!!)
        val res = PubgJsonConvert.convertToPubgPlayerMatchDO(playerJO)
        Assert.assertEquals(38, res.size)
        res.forEach {
            Assert.assertTrue(StringUtils.isNotBlank(it.matchId))
        }
    }


    @Test
    fun testParserJsonMatchSummary() {
        val input = PubgJsonParserTest::class.java.classLoader.getResourceAsStream("test-data/match.json")
        val matchJO = PubgJsonParser.parserMatch(input!!)
        val res = PubgJsonConvert.convertToPubgMatchSummaryDO(matchJO)
        Assert.assertEquals("d24b3125-94c5-4ae3-a742-9b42d8e6fe96", res.matchId)
        Assert.assertEquals(false, res.customMatch)
        Assert.assertEquals(MapName.BALTIC_MAIN, res.mapName)
        Assert.assertEquals(ShardId.STEAM, res.shardId)
        Assert.assertEquals(1824, res.duration)
        Assert.assertEquals(GameMode.SQUAD_FPP, res.gameMode)

    }

    @Test
    fun testParserJsonMatchDetail() {
        val input = PubgJsonParserTest::class.java.classLoader.getResourceAsStream("test-data/match.json")
        val matchJO = PubgJsonParser.parserMatch(input!!)
        val res = PubgJsonConvert.convertToPubgMatchDetailDO(matchJO)
        Assert.assertEquals(96, res.size)
        res.forEach {
            Assert.assertTrue(StringUtils.isNotBlank(it.playerId))
            Assert.assertTrue(StringUtils.isNotBlank(it.matchId))
        }
    }


}