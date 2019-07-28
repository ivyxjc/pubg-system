package xyz.ivyxjc.pubg.system.common.dao

import org.apache.ibatis.annotations.ResultMap
import org.apache.ibatis.annotations.Select
import org.springframework.stereotype.Repository
import xyz.ivyxjc.pubg.system.common.entity.PubgMatchSummaryDO

@Repository
interface PubgMatchSummaryMapper {
    fun insertMatchSummary(matchSummaryDO: PubgMatchSummaryDO): Int

    @Select("DELETE FROM PUBG_MATCH_SUMMARY WHERE MATCH_ID=#{matchId}")
    fun deleteByMatchId(matchId: String): Int

    @ResultMap("PubgMatchSummaryBaseMap")
    @Select("SELECT * FROM PUBG_MATCH_SUMMARY WHERE MATCH_ID=#{matchId}")
    fun queryByMatchId(matchId: String): PubgMatchSummaryDO

}