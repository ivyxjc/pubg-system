package xyz.ivyxjc.pubg.system.common.dao

import org.apache.ibatis.annotations.Delete
import org.apache.ibatis.annotations.ResultMap
import org.apache.ibatis.annotations.Select
import org.springframework.stereotype.Repository
import xyz.ivyxjc.pubg.system.common.entity.PubgMatchDetailDO
import xyz.ivyxjc.pubg.system.common.entity.PubgMatchRosterParticipantDO

@Repository
interface PubgRosterParticipantMapper {
    fun insertRosterParticipant(rosterParticipant: PubgMatchRosterParticipantDO): Int

    fun insertRosterParticipants(rosterParticipants: List<PubgMatchRosterParticipantDO>): Int

    @Delete("DELETE FROM PUBG_MATCH_ROSTER_PARTICIPANT WHERE ROSTER_ID=#{rosterId}")
    fun deleteByRosterId(rosterId: String): Int

    @ResultMap(value = ["PubgRosterParticipantBaseMap"])
    @Select("SELECT * FROM PUBG_MATCH_ROSTER_PARTICIPANT WHERE ROSTER_ID=#{rosterId}")
    fun listByRosterId(rosterId: String): List<PubgMatchDetailDO>

}