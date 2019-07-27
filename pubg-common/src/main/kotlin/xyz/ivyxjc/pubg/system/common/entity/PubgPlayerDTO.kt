package xyz.ivyxjc.pubg.system.common.entity


import xyz.ivyxjc.pubg.system.common.types.GameMode
import xyz.ivyxjc.pubg.system.common.types.MapName
import xyz.ivyxjc.pubg.system.common.types.ShardId
import java.time.LocalDateTime

class PubgPlayerDTO : BaseEntity() {

    lateinit var playerId: String

    var name: String? = null
    /**
     * Platform-region shard
     */
    var shardId: ShardId? = null

    var createdAt: LocalDateTime? = null

    var updatedAt: LocalDateTime? = null
}

class PubgPlayerMatchDTO : BaseEntity() {
    lateinit var matchId: String

    lateinit var playerId: String

    lateinit var shardId: ShardId
}

class PubgMatchDetail : BaseEntity() {
    lateinit var matchId: String
    /**
     * Time this match object was stored in the API
     */
    var createdAt: LocalDateTime? = null

    /**
     * Length of the match
     */
    var duration: Int? = null

    /**
     * Game mode played
     */
    var gameMode: GameMode? = null

    /**
     * Map name
     */
    var mapName: MapName? = null

    /**
     * Platform-region shard
     */
    var shardId: ShardId? = null

    /**
     * Identifies the studio and game
     */
    var titleId: String? = null

}