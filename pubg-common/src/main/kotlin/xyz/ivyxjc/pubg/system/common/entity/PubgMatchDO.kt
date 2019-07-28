package xyz.ivyxjc.pubg.system.common.entity

import xyz.ivyxjc.pubg.system.common.types.GameMode
import xyz.ivyxjc.pubg.system.common.types.MapName
import xyz.ivyxjc.pubg.system.common.types.ShardId
import java.time.LocalDateTime

class PubgMatchSummaryDO : BaseDO() {
    lateinit var matchId: String

    var createdAt: LocalDateTime? = null

    var customMatch: Boolean? = false

    var duration: Int? = -1

    var gameMode: GameMode? = null

    var mapName: MapName? = null

    var shardId: ShardId? = null

    var titleId: String? = null

    var assetUrl: String? = null
}

class PubgMatchDetailDO : BaseDO() {

    lateinit var matchId: String

    lateinit var playerId: String

    var shardId: ShardId? = null

    var dbnos: Int? = null

    var assists: Int? = null

    var boosts: Int? = null

    var damageDealt: Double? = null

    var deathType: String? = null

    var headshotKills: Int? = null

    var heals: Int? = null

    var killPlace: Int? = null

    var killStreaks: Int? = null

    var kills: Int? = null

    var longestKill: Double? = null

    var name: String? = null

    var revives: Int? = null

    var rideDistance: Double? = null

    var roadKills: Int? = null

    var swimDistance: Double? = null

    var teamKills: Int? = null

    var timeSurvived: Double? = null

    var vehicleDestroys: Int? = null

    var walkDistance: Double? = null

    var weaponsAcquired: Int? = null

    var winPlace: Int? = null

    var winPoints: Int? = null

    var winPointsDelta: Double? = null
}

class PubgMatchRosterDO : BaseDO() {
    lateinit var rosterId: String

    lateinit var matchId: String

    var rank: Int = -1

    var teamId: Int = -1

    var win: Boolean? = null
}

class PubgMatchRosterParticipantDO : BaseDO() {
    lateinit var rosterId: String

    lateinit var playerId: String
}


