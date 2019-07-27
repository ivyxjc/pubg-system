package xyz.ivyxjc.pubg.system.common.dao

import org.apache.ibatis.annotations.Delete
import org.apache.ibatis.annotations.ResultMap
import org.apache.ibatis.annotations.Select
import org.springframework.stereotype.Repository
import xyz.ivyxjc.pubg.system.common.entity.PubgPlayerDTO

@Repository
interface PubgPlayerMapper {
    @ResultMap(value = ["PubgPlayerBaseMap"])
    @Select("SELECT * FROM PUBG_PLAYER WHERE PLAYER_ID=#{id}")
    fun queryByPlayerId(id: String): PubgPlayerDTO?

    @ResultMap(value = ["PubgPlayerBaseMap"])
    @Select("SELECT * FROM PUBG_PLAYER WHERE NAME = #{name} ")
    fun queryByPlayerName(name: String): PubgPlayerDTO?

    fun insertPubgPlayer(pubgPlayer: PubgPlayerDTO)

    @Delete("DELETE FROM PUBG_PLAYER WHERE PLAYER_ID=#{playerId}")
    fun deleteByPubgPlayerId(playerId: String)

    @Delete("DELETE FROM PUBG_PLAYER WHERE NAME=#{playerName}")
    fun delteByPubgName(playerName: String): Int
}


