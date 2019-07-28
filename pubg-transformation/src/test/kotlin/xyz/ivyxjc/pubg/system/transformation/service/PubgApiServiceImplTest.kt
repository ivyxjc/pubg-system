package xyz.ivyxjc.pubg.system.transformation.service

import org.apache.commons.lang3.StringUtils
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringRunner
import xyz.ivyxjc.pubg.system.transformation.TestMainApplication

@RunWith(SpringRunner::class)
@SpringBootTest(classes = [TestMainApplication::class])
@ActiveProfiles("dev")
class PubgApiServiceImplTest {

    @Autowired
    private lateinit var pubgApiService: PubgApiService

    @Test
    fun testGetPlayerJson() {
        val res = pubgApiService.getPlayerJson("cheese_1109")
        Assert.assertTrue(StringUtils.isNotBlank(res))
        Assert.assertTrue(res!!.contains("cheese_1109"))
    }

    @Test
    fun testGetMatchJson() {
        val res = pubgApiService.getMatchJson("437a2f88-bbdb-472b-b837-3bde3dd025b2")
        Assert.assertTrue(res!!.contains("cheese_1109"))
    }
}