package xyz.ivyxjc.pubg.system.transformation.jo

import org.apache.commons.lang3.builder.ToStringBuilder

class PubgMatchJO {
    lateinit var data: JsonMatchData
}

class JsonMatchData {
    lateinit var type: String
    lateinit var id: String
    lateinit var attributes: JsonMatchAttributes
    lateinit var relationships: JsonMatchRelationships
}

class JsonMatchAttributes {
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

class JsonMatchRelationships {
    lateinit var rosters: JsonMatchRosters

    override fun toString(): String {
        return rosters.toString();
    }
}

class JsonMatchRosters {
    lateinit var data: MutableList<JsonMatchRostersData>

    override fun toString(): String {
        return data.toString()
    }
}

class JsonMatchRostersData {
    lateinit var type: String
    lateinit var id: String

    override fun toString(): String {
        return ToStringBuilder.reflectionToString(this)
    }
}

