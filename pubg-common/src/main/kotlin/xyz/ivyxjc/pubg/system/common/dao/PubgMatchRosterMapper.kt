package xyz.ivyxjc.pubg.system.common.dao

import org.apache.ibatis.annotations.Delete
import org.apache.ibatis.annotations.ResultMap
import org.apache.ibatis.annotations.Select
import org.springframework.stereotype.Repository
import xyz.ivyxjc.pubg.system.common.entity.PubgMatchDetailDO
import xyz.ivyxjc.pubg.system.common.entity.PubgMatchRosterDO

@Repository
interface PubgMatchRosterMapper {

    fun insertMatchRoster(matchRoster: PubgMatchRosterDO): Int

    fun insertMatchRosters(matchRosters: List<PubgMatchRosterDO>): Int

    @Delete("DELETE FROM PUBG_MATCH_ROSTER WHERE MATCH_ID=#{matchId}")
    fun deleteByMatchId(matchId: String): Int

    @ResultMap(value = ["PubgMatchRosterBaseMap"])
    @Select("SELECT * FROM PUBG_MATCH_ROSTER WHERE MATCH_ID=#{matchId}")
    fun listByMatchId(matchId: String): List<PubgMatchDetailDO>

}