package xyz.ivyxjc.pubg.system.common.serialization

import org.apache.kafka.common.serialization.Deserializer
import org.apache.kafka.common.serialization.Serializer
import xyz.ivyxjc.pubg.system.common.entity.RawMessage
import xyz.ivyxjc.pubg.system.common.utils.DEFAULT_CHARSET
import xyz.ivyxjc.pubg.system.common.utils.DEFAULT_ZONE_OFFSET
import java.nio.ByteBuffer
import java.time.Instant
import java.time.LocalDateTime

class RawMessageSerializer : Serializer<RawMessage> {

    override fun serialize(topic: String?, data: RawMessage?): ByteArray {
        topic!!
        data!!
        var dbCreatedAtLong = 0L
        if (data.dbCreatedAt != null) {
            dbCreatedAtLong = data.dbCreatedAt!!.toInstant(DEFAULT_ZONE_OFFSET).toEpochMilli()
        }
        var dbUpdatedAtLong = 0L
        if (data.dbUpdatedAt != null) {
            dbUpdatedAtLong = data.dbUpdatedAt!!.toInstant(DEFAULT_ZONE_OFFSET).toEpochMilli()
        }
        val messageBytes = toBytes(data.message)
        val dbCreatedByBytes = toBytes(data.dbCreatedBy)
        val dbCreatedFromBytes = toBytes(data.dbCreatedFrom)
        val dbUpdatedByBytes = toBytes(data.dbUpdatedBy)
        val dbUpdatedFromBytes = toBytes(data.dbUpdatedFrom)

        val size = 8 + //guid
                4 + messageBytes.size +
                8 + //createdAt
                4 + dbCreatedByBytes.size +
                4 + dbCreatedFromBytes.size +
                8 + //updatedAt
                4 + dbUpdatedByBytes.size +
                4 + dbUpdatedFromBytes.size

        val byteBuffer = ByteBuffer.allocate(size)
        byteBuffer.putLong(data.guid)
        byteBuffer.putInt(messageBytes.size)
        byteBuffer.put(messageBytes)
        byteBuffer.putLong(dbCreatedAtLong)
        byteBuffer.putInt(dbCreatedByBytes.size)
        byteBuffer.put(dbCreatedByBytes)
        byteBuffer.putInt(dbCreatedFromBytes.size)
        byteBuffer.put(dbCreatedFromBytes)
        byteBuffer.putLong(dbUpdatedAtLong)
        byteBuffer.putInt(dbUpdatedByBytes.size)
        byteBuffer.put(dbUpdatedByBytes)
        byteBuffer.putInt(dbUpdatedFromBytes.size)
        byteBuffer.put(dbUpdatedFromBytes)
        return byteBuffer.array()
    }

    override fun configure(configs: MutableMap<String, *>?, isKey: Boolean) {
        val propertyName = if (isKey) "key.serializer.encoding" else "value.serializer.encoding"
        var encodingValue: Any? = configs?.get(propertyName)
        if (encodingValue == null)
            encodingValue = configs?.get("serializer.encoding")
    }

    override fun close() {
    }

    inline fun toBytes(str: String?): ByteArray {
        if (str == null) {
            return ByteArray(0)
        }
        return str.toByteArray(DEFAULT_CHARSET)
    }
}

class RawMessageDeserializer : Deserializer<RawMessage> {

    override fun deserialize(topic: String?, data: ByteArray?): RawMessage {
        data!!
        val byteBuffer = ByteBuffer.wrap(data)
        var offset = 0
        val guid = byteBuffer.getLong(offset)
        offset += 8

        val messageSize = byteBuffer.getInt(offset)
        offset += 4
        val message = String(data.sliceArray(IntRange(offset, offset + messageSize - 1)), DEFAULT_CHARSET)
        offset += messageSize

        val res = RawMessage(guid, message)

        val dbCreatedAtLong = byteBuffer.getLong(offset)
        res.dbCreatedAt = Instant.ofEpochMilli(dbCreatedAtLong).atOffset(DEFAULT_ZONE_OFFSET).toLocalDateTime()
        offset += 8

        val dbCreatedBySize = byteBuffer.getInt(offset)
        offset += 4
        res.dbCreatedBy = String(data.sliceArray(IntRange(offset, offset + dbCreatedBySize - 1)), DEFAULT_CHARSET)
        offset += dbCreatedBySize

        val dbCreatedFromSize = byteBuffer.getInt(offset)
        offset += 4
        res.dbCreatedFrom = String(data.sliceArray(IntRange(offset, offset + dbCreatedFromSize - 1)), DEFAULT_CHARSET)
        offset += dbCreatedFromSize

        val dbUpdatedAtLong = byteBuffer.getLong(offset)
        res.dbUpdatedAt = Instant.ofEpochMilli(dbUpdatedAtLong).atOffset(DEFAULT_ZONE_OFFSET).toLocalDateTime()
        offset += 8

        val dbUpdatedBySize = byteBuffer.getInt(offset)
        offset += 4
        res.dbUpdatedBy = String(data.sliceArray(IntRange(offset, offset + dbUpdatedBySize - 1)), DEFAULT_CHARSET)
        offset += dbUpdatedBySize

        val dbUpdatedFromSize = byteBuffer.getInt(offset)
        offset += 4
        res.dbUpdatedFrom = String(data.sliceArray(IntRange(offset, offset + dbUpdatedFromSize - 1)), DEFAULT_CHARSET)
        offset += dbUpdatedFromSize

        return res
    }

    override fun configure(configs: MutableMap<String, *>?, isKey: Boolean) {
        val propertyName = if (isKey) "key.deserializer.encoding" else "value.deserializer.encoding"
        var encodingValue: Any? = configs?.get(propertyName)
        if (encodingValue == null)
            encodingValue = configs?.get("deserializer.encoding")
    }

    override fun close() {
    }
}

fun main() {
    val rawMessage = RawMessage(12323L, "hello world")
    rawMessage.dbCreatedAt = LocalDateTime.now()
    rawMessage.dbUpdatedAt = LocalDateTime.now()
    rawMessage.dbCreatedBy = "createBysdafasdf"
    rawMessage.dbUpdatedBy = "updatedByasdfsda"
    rawMessage.dbCreatedFrom = "createdFromasdfdsa"
    rawMessage.dbUpdatedFrom = "updatedFrom121"
    val serializer = RawMessageSerializer()
    val deserializer = RawMessageDeserializer()
    val byteArray = serializer.serialize("hello", rawMessage)
    val res = deserializer.deserialize("hello", byteArray)

}