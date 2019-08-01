package xyz.ivyxjc.pubg.system.transformation.jo

import org.apache.commons.lang3.builder.ToStringBuilder


internal class PubgMatchJO {
    lateinit var data: JsonMatchData
    lateinit var included: MutableList<JsonMatchIncluded>
}

internal open class JsonMatchIncluded {
    lateinit var type: String
    lateinit var id: String
}

internal class JsonMatchData {
    lateinit var type: String
    lateinit var id: String
    lateinit var attributes: JsonMatchAttributes
    lateinit var relationships: JsonMatchRelationships
}

internal class JsonMatchAttributes {
    lateinit var seasonState: String
    lateinit var createdAt: String
    var tags: String? = null
    lateinit var isCustomMatch: String
    /**
     * alwasy be 'bluehold-pubg'
     */
    lateinit var titleId: String
    lateinit var shardId: String
    lateinit var mapName: String
    var duration: Int = 0
    /**
     * alwasy be null
     */
    var stats: String? = null

    lateinit var gameMode: String
    override fun toString(): String {
        return ToStringBuilder.reflectionToString(this)
    }
}

internal class JsonMatchRelationships {
    lateinit var rosters: JsonMatchRosters

    override fun toString(): String {
        return rosters.toString();
    }
}

internal class JsonMatchRosters {
    lateinit var data: MutableList<JsonMatchRostersData>

    override fun toString(): String {
        return data.toString()
    }
}

internal class JsonMatchRostersData {
    lateinit var type: String
    lateinit var id: String

    override fun toString(): String {
        return ToStringBuilder.reflectionToString(this)
    }
}

internal class JsonMatchRoster : JsonMatchIncluded() {
    lateinit var attributes: JsonMatchRosterAttributes
    lateinit var relationships: JsonMatchRosterRelations
}

internal class JsonMatchRosterAttributes {
    lateinit var stats: JsonMatchRosterAttributesStats

    lateinit var won: String

    lateinit var shardId: String
}

internal class JsonMatchRosterAttributesStats {
    var rank: Int = -1
    var teamId: Int = -1
}

internal class JsonMatchRosterRelations {
    lateinit var participants: JsonMatchRosterRelationshipParticipants
}

internal class JsonMatchRosterRelationshipParticipants {
    lateinit var data: MutableList<JsonMatchRosterRelationshipParticipantsData>
}

internal class JsonMatchRosterRelationshipParticipantsData {
    lateinit var type: String
    lateinit var id: String
}

internal class JsonMatchParticipant : JsonMatchIncluded() {
    lateinit var attributes: JsonMatchParticipantAttributes
}

internal class JsonMatchParticipantAttributes {
    lateinit var stats: JsonMatchParticipantAttributesStats
}

internal class JsonMatchParticipantAttributesStats {
    lateinit var DBNOs: String
    lateinit var assists: String
    lateinit var boosts: String
    lateinit var damageDealt: String
    lateinit var deathType: String
    lateinit var headshotKills: String
    lateinit var heals: String
    lateinit var killPlace: String
    lateinit var killStreaks: String
    lateinit var kills: String
    lateinit var longestKill: String
    lateinit var name: String
    lateinit var playerId: String
    lateinit var revives: String
    lateinit var rideDistance: String
    lateinit var roadKills: String
    lateinit var swimDistance: String
    lateinit var teamKills: String
    lateinit var timeSurvived: String
    lateinit var vehicleDestroys: String
    lateinit var walkDistance: String
    lateinit var weaponsAcquired: String
    lateinit var winPlace: String
}


internal class JsonMatchAsset : JsonMatchIncluded() {
    lateinit var attributes: JsonMatchAssetAttributes
}


internal class JsonMatchAssetAttributes {
    lateinit var name: String
    lateinit var description: String
    lateinit var createdAt: String
    lateinit var URL: String
}
