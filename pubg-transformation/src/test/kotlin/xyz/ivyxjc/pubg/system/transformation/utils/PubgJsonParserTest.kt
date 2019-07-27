package xyz.ivyxjc.pubg.system.transformation.utils

import org.junit.Assert
import org.junit.Test

class PubgJsonParserTest {

    @Test
    fun testParserJsonPlayer() {
        val input = PubgJsonParserTest::class.java.classLoader.getResourceAsStream("test-data/player.json")
        val res = PubgJsonParser.parserPlayer(input!!)
        Assert.assertNotNull(res.data[0])
        Assert.assertEquals("account.7c20cb2e1e3d4331adcf712cec236602", res.data[0].id)
        Assert.assertEquals("player", res.data[0].type)
        Assert.assertEquals("cheese_1109", res.data[0].attributes.name)
        Assert.assertEquals("steam", res.data[0].attributes.shardId)
        Assert.assertEquals("bluehole-pubg", res.data[0].attributes.titleId)
        Assert.assertEquals(38, res.data[0].relationships.matches.data.size)
    }

    @Test
    fun testParserJsonMatch() {
        val input = PubgJsonParserTest::class.java.classLoader.getResourceAsStream("test-data/match.json")
        val res = PubgJsonParser.parserMatch(input!!)
        Assert.assertNotNull(res.data)
        Assert.assertEquals("d24b3125-94c5-4ae3-a742-9b42d8e6fe96", res.data.id)
        Assert.assertEquals("match", res.data.type)
        Assert.assertEquals("progress", res.data.attributes.seasonState)
        Assert.assertEquals("2019-07-26T15:15:02Z", res.data.attributes.createdAt)
        Assert.assertEquals(null, res.data.attributes.tags)
        Assert.assertEquals("false", res.data.attributes.isCustomMatch)
        Assert.assertEquals("steam", res.data.attributes.shardId)
        Assert.assertEquals("Baltic_Main", res.data.attributes.mapName)
        Assert.assertEquals(1824, res.data.attributes.duration)
        Assert.assertEquals(null, res.data.attributes.stats)
        Assert.assertEquals("squad-fpp", res.data.attributes.gameMode)
        Assert.assertEquals(26, res.data.relationships.rosters.data.size)
    }
}