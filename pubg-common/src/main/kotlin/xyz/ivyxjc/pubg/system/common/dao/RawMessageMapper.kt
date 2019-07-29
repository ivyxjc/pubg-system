package xyz.ivyxjc.pubg.system.common.dao

import org.apache.ibatis.annotations.ResultMap
import org.apache.ibatis.annotations.Select
import org.springframework.stereotype.Repository
import xyz.ivyxjc.pubg.system.common.entity.RawMessage

@Repository
interface RawMessageMapper {

    fun insertMessage(model: RawMessage): Int

    fun insertMessages(models: List<RawMessage>): Int

    @ResultMap("RawMessageBaseMap")
    @Select("select * FROM RAW_MESSAGE where GUID = #{guid}")
    fun queryByGuid(guid: Long): RawMessage
}