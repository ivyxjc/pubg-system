package xyz.ivyxjc.pubg.system.common.utils

import org.junit.Assert
import org.junit.Test

class TimeUtilsTest {

    @Test
    fun testParseDateTime() {
        val dateTimeStr = "2019-07-27T15:54:23Z"
        val res = TimeUtils.parserDateTime(dateTimeStr, "yyyy-MM-dd'T'HH:mm:ss'Z'")
        Assert.assertEquals(2019, res!!.year)
        Assert.assertEquals(7, res.monthValue)
        Assert.assertEquals(27, res.dayOfMonth)
        Assert.assertEquals(15, res.hour)
        Assert.assertEquals(54, res.minute)
        Assert.assertEquals(23, res.second)
        Assert.assertEquals(0, res.nano)
    }

}
