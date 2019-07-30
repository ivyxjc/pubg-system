package xyz.ivyxjc.pubg.system.transmission.processors

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.annotation.KafkaHandler
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.transaction.annotation.Transactional
import xyz.ivyxjc.pubg.system.common.annotation.Processor
import xyz.ivyxjc.pubg.system.common.dao.RawMessageMapper
import xyz.ivyxjc.pubg.system.common.entity.RawMessage
import xyz.ivyxjc.pubg.system.common.processors.TransmissionProcessor
import xyz.ivyxjc.pubg.system.common.service.LeafService
import xyz.ivyxjc.pubg.system.common.utils.loggerFor


@Processor
@KafkaListener(id = "pubgGroup", topics = ["pubg-transmission-dev"])
open class TransmissionProcessorImpl : TransmissionProcessor {
    companion object {
        private val log = loggerFor(TransmissionProcessorImpl::class.java)
    }

    @Autowired
    private lateinit var rawMessageMapper: RawMessageMapper

    @Autowired
    private lateinit var leafService: LeafService

    @Autowired
    private lateinit var kafkaTemplate: KafkaTemplate<String, RawMessage>;

    //todo XA
    @KafkaHandler
    @Transactional
    override fun process(message: String) {
        log.info("[TheFirstHandler] get the message: {}", message)
        val rawMessage = build(message)
        val rawRes = rawMessageMapper.insertMessage(rawMessage)
        if (rawRes != 1) {
            log.error("Fail to insert Raw Message")
        }
        kafkaTemplate.send("pubg-transform-dev", rawMessage)
    }

    private fun build(message: String): RawMessage {
        return RawMessage(leafService.getGuid(), message)
    }
}