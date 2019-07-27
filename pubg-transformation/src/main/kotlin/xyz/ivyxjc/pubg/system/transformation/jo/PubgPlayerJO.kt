package xyz.ivyxjc.pubg.system.transformation.jo

import org.apache.commons.lang3.builder.ToStringBuilder


/**
 * JO is the abbreviation of the Json Object, it is used to parse json got from dev.PUBG
 */

class PubgPlayerJO {
    lateinit var data: MutableList<JsonPlayerData>
}

class JsonPlayerData {
    /**
     *for player, always be 'player'
     */
    lateinit var type: String
    /**
     *playerId
     */
    lateinit var id: String

    lateinit var attributes: JsonPlayerAttributes

    lateinit var relationships: JsonPlayerRelationShips

    override fun toString(): String {
        return ToStringBuilder.reflectionToString(this)
    }
}

class JsonPlayerAttributes {
    /**
     * like steam, xbox, psn, kakao
     */
    lateinit var shardId: String
    lateinit var createdAt: String
    lateinit var updatedAt: String
    /**
     * player name
     */
    lateinit var name: String
    var stats: String? = null
    /**
     * always be 'bluehole-pubg'
     */
    lateinit var titleId: String

    /**
     * always be null
     */
    var patchVersion: String? = null

    override fun toString(): String {
        return ToStringBuilder.reflectionToString(this)
    }
}

class JsonPlayerRelationShips {
    lateinit var matches: JsonPlayerMatches

    override fun toString(): String {
        return matches.toString();
    }
}

class JsonPlayerMatches {
    lateinit var data: MutableList<JsonPlayerMatchData>

    override fun toString(): String {
        return data.toString()
    }
}

class JsonPlayerMatchData {
    lateinit var type: String
    lateinit var id: String

    override fun toString(): String {
        return ToStringBuilder.reflectionToString(this)
    }
}


