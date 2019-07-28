package xyz.ivyxjc.pubg.system.common.dao

import org.apache.ibatis.annotations.Delete
import org.apache.ibatis.annotations.ResultMap
import org.apache.ibatis.annotations.Select
import org.springframework.stereotype.Repository
import xyz.ivyxjc.pubg.system.common.entity.PubgPlayerMatchDO

@Repository
interface PubgPlayerMatchMapper {

    fun insertPubgPlayerMatchs(matches: List<PubgPlayerMatchDO>): Int

    @ResultMap("PubgPlayerMatchBaseMapper")
    @Select("SELECT * FROM PUBG_PLAYER_MATCH WHERE PLAYER_ID=#{playerId}")
    fun listByPlayerId(playerId: String): List<PubgPlayerMatchDO>

    @ResultMap("PubgPlayerMatchBaseMapper")
    @Select("SELECT * FROM PUBG_PLAYER_MATCH WHERE MATCH_ID=#{matchId}")
    fun queryByMatchId(matchId: String): PubgPlayerMatchDO

    @Delete("DELETE FROM PUBG_PLAYER_MATCH WHERE PLAYER_ID=#{playerId}")
    fun deleteByPlayerId(playerId: String): Int

//    todo
//    @ResultMap("PubgPlayerMatchBaseMapper")
//    @Select("SELECT * FROM PUBG_PLAYER_MATCH WHERE SHARD_ID=#{shardId} AND PLAYER_ID=#{playerId} ORDER BY DB_UPDATED_AT DESC LIMIT 0,1")
//    fun queryNewestMatch(
//        @Param("shardId") shardId: String,
//        @Param("playerId") playerId: String
//    ): PubgPlayerMatchDTO
}