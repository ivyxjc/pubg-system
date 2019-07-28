package xyz.ivyxjc.pubg.system.common.entity


import xyz.ivyxjc.pubg.system.common.types.ShardId
import java.time.LocalDateTime

class PubgPlayerDO : BaseEntity() {

    lateinit var playerId: String

    var name: String? = null
    /**
     * Platform-region shard
     */
    var shardId: ShardId? = null

    var createdAt: LocalDateTime? = null

    var updatedAt: LocalDateTime? = null
}

class PubgPlayerMatchDO : BaseEntity() {
    lateinit var matchId: String

    lateinit var playerId: String

    lateinit var shardId: ShardId
}

