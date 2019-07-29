package xyz.ivyxjc.pubg.system.transmission

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.annotation.KafkaListener
import xyz.ivyxjc.pubg.system.common.annotation.Processor
import xyz.ivyxjc.pubg.system.common.dao.RawMessageMapper
import xyz.ivyxjc.pubg.system.common.entity.RawMessage
import xyz.ivyxjc.pubg.system.common.processors.TransmissionProcessor
import xyz.ivyxjc.pubg.system.common.service.LeafService
import xyz.ivyxjc.pubg.system.common.utils.loggerFor


@Processor
class TransmissionProcessorImpl : TransmissionProcessor {
    companion object {
        private val log = loggerFor(TransmissionProcessorImpl::class.java)
    }

    @Autowired
    private lateinit var rawMessageMapper: RawMessageMapper

    @Autowired
    private lateinit var leafService: LeafService


    @KafkaListener()
    override fun process(message: String) {
        val rawMessage = build(message)
        val rawRes = rawMessageMapper.insertMessage(rawMessage)
        if (rawRes != 1) {
            log.error("Fail to insert Raw Message")
        }
    }

    private fun build(message: String): RawMessage {
        return RawMessage(leafService.getGuid(), message)
    }
}