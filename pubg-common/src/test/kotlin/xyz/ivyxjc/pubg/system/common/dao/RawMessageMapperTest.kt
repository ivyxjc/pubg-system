package xyz.ivyxjc.pubg.system.common.dao

import org.apache.commons.lang3.RandomStringUtils
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import xyz.ivyxjc.pubg.system.common.entity.RawMessage
import kotlin.random.Random

@RunWith(SpringRunner::class)
@SpringBootTest
class RawMessageMapperTest {

    @Autowired
    private lateinit var rawMessageMapper: RawMessageMapper

    private fun prepareTransmissionModels(): List<RawMessage> {
        val count = Random.nextInt(10, 100)
        return List(count) {
            RawMessage(Random.nextLong(), RandomStringUtils.random(10000))
        }
    }

    private fun checkTransmissionModel(expect: RawMessage, real: RawMessage) {
        Assert.assertEquals(expect.guid, real.guid)
        Assert.assertEquals(expect.message, real.message)
    }

    @Test
    fun testInsertModel() {
        val model = prepareTransmissionModels()[0]
        rawMessageMapper.insertMessage(model)
    }

    @Test
    fun testInsertModels() {
        val models = prepareTransmissionModels()
        rawMessageMapper.insertMessages(models)
    }

    @Test
    fun testQueryByGuid() {
        val models = prepareTransmissionModels()
        rawMessageMapper.insertMessages(models)

        models.forEach {
            val tmp = rawMessageMapper.queryByGuid(it.guid)
            checkTransmissionModel(it, tmp)
        }

    }


}