package xyz.ivyxjc.pubg.system.common.dao

import org.apache.ibatis.annotations.Delete
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.ResultMap
import org.apache.ibatis.annotations.Select
import org.springframework.stereotype.Repository
import xyz.ivyxjc.pubg.system.common.entity.PubgMatchDetailDO

@Repository
interface PubgMatchDetailMapper {

    fun insertMatchDetail(matchDetail: PubgMatchDetailDO): Int

    fun insertMatchDetails(matchDetails: List<PubgMatchDetailDO>): Int

    @Delete("DELETE FROM PUBG_MATCH_DETAIL WHERE MATCH_ID=#{matchId}")
    fun deleteByMatchId(matchId: String): Int


    @ResultMap(value = ["PubgMatchDetailBaseMap"])
    @Select("SELECT * FROM PUBG_MATCH_DETAIL WHERE MATCH_ID=#{matchId}")
    fun listByMatchId(matchId: String): List<PubgMatchDetailDO>

    @ResultMap(value = ["PubgMatchDetailBaseMap"])
    @Select("SELECT * FROM PUBG_MATCH_DETAIL WHERE PLAYER_ID=#{playerId} AND MATCH_ID=#{matchId}")
    fun queryByPlayerIdMatchId(@Param("playerId") playerId: String, @Param("matchId") matchId: String): PubgMatchDetailDO

}