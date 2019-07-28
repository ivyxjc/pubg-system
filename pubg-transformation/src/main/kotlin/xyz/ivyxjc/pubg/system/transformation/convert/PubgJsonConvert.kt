package xyz.ivyxjc.pubg.system.transformation.convert

import xyz.ivyxjc.pubg.system.common.entity.*
import xyz.ivyxjc.pubg.system.common.types.GameMode
import xyz.ivyxjc.pubg.system.common.types.MapName
import xyz.ivyxjc.pubg.system.common.types.ShardId
import xyz.ivyxjc.pubg.system.common.utils.TimeUtils
import xyz.ivyxjc.pubg.system.transformation.jo.*


class PubgJsonConvert {

    companion object {
        fun convertToPubgPlayerDO(playerJO: PubgPlayerJO): PubgPlayerDO {
            val res = PubgPlayerDO()
            res.playerId = playerJO.data[0].id
            res.name = playerJO.data[0].attributes.name
            res.shardId = ShardId.enumOf(playerJO.data[0].attributes.shardId)
            res.createdAt = TimeUtils.parserDateTime(playerJO.data[0].attributes.createdAt)
            res.updatedAt = TimeUtils.parserDateTime(playerJO.data[0].attributes.updatedAt)
            return res
        }

        fun convertToPubgPlayerMatchDO(playerJO: PubgPlayerJO): List<PubgPlayerMatchDO> {
            val res = mutableListOf<PubgPlayerMatchDO>()
            val playerId = playerJO.data[0].id
            val shardId = ShardId.enumOf(playerJO.data[0].attributes.shardId)
            playerJO.data[0].relationships.matches.data.forEach {
                val tmp = PubgPlayerMatchDO()
                tmp.matchId = it.id
                tmp.playerId = playerId
                tmp.shardId = shardId
                res.add(tmp)
            }
            return res
        }

        fun convertToPubgMatchSummaryDO(matchJO: PubgMatchJO): PubgMatchSummaryDO {
            val res = PubgMatchSummaryDO()
            res.matchId = matchJO.data.id
            res.customMatch = matchJO.data.attributes.isCustomMatch.toBoolean()
            res.createdAt = TimeUtils.parserDateTime(matchJO.data.attributes.createdAt)
            res.duration = matchJO.data.attributes.duration
            res.gameMode = GameMode.enumOf(matchJO.data.attributes.gameMode)
            res.mapName = MapName.enumOf(matchJO.data.attributes.mapName)
            res.shardId = ShardId.enumOf(matchJO.data.attributes.shardId)
            res.titleId = matchJO.data.attributes.titleId
            matchJO.included.forEach {
                if (it is JsonMatchAsset) {
                    res.assetUrl = it.attributes.URL
                }
            }
            return res
        }

        fun convertToPubgMatchDetailDO(matchJO: PubgMatchJO): List<PubgMatchDetailDO> {
            val res = mutableListOf<PubgMatchDetailDO>()
            val matchId = matchJO.data.id
            matchJO.included.filterIsInstance<JsonMatchParticipant>().forEach {
                val tmp = PubgMatchDetailDO()
                val that = it.attributes.stats
                tmp.playerId = that.playerId
                tmp.matchId = matchId
                tmp.dbnos = that.DBNOs.toInt()
                tmp.assists = that.assists.toInt()
                tmp.boosts = that.boosts.toInt()
                tmp.damageDealt = that.damageDealt.toDouble()
                tmp.deathType = that.deathType
                tmp.headshotKills = that.headshotKills.toInt()
                tmp.heals = that.heals.toInt()
                tmp.killPlace = that.killPlace.toInt()
                tmp.killStreaks = that.killStreaks.toInt()
                tmp.kills = that.kills.toInt()
                tmp.longestKill = that.longestKill.toDouble()
                tmp.name = that.name
                tmp.revives = that.revives.toInt()
                tmp.rideDistance = that.rideDistance.toDouble()
                tmp.roadKills = that.roadKills.toInt()
                tmp.swimDistance = that.swimDistance.toDouble()
                tmp.teamKills = that.teamKills.toInt()
                tmp.timeSurvived = that.timeSurvived.toDouble()
                tmp.vehicleDestroys = that.vehicleDestroys.toInt()
                tmp.walkDistance = that.walkDistance.toDouble()
                tmp.weaponsAcquired = that.weaponsAcquired.toInt()
                tmp.winPlace = that.winPlace.toInt()
                res.add(tmp)
            }
            return res
        }

        fun convertToPubgMatchRosterDO(matchJO: PubgMatchJO): List<PubgMatchRosterDO> {
            val res = mutableListOf<PubgMatchRosterDO>()
            val matchId = matchJO.data.id

            matchJO.included.filterIsInstance<JsonMatchRoster>().forEach {
                val tmp = PubgMatchRosterDO()
                tmp.matchId = matchId
                tmp.rosterId = it.id
                tmp.rank = it.attributes.stats.rank
                tmp.teamId = it.attributes.stats.teamId
                tmp.win = it.attributes.won.toBoolean()
                res.add(tmp)
            }
            return res
        }

        fun convertToPubgMatchRosterParticipantDO(matchJO: PubgMatchJO): List<PubgMatchRosterParticipantDO> {
            val res = mutableListOf<PubgMatchRosterParticipantDO>()
            val matchId = matchJO.data.id
            val participantIdPlayerIdMapper = generateParticipantPlayerIdMaper(matchJO)
            matchJO.included.filterIsInstance<JsonMatchRoster>().forEach { roster ->
                val tmpRosterId = roster.id
                roster.relationships.participants.data.forEach {
                    val tmp = PubgMatchRosterParticipantDO()
                    tmp.rosterId = tmpRosterId
                    tmp.playerId =
                        participantIdPlayerIdMapper[it.id] ?: error("Fail to find playerId based on participantId")
                    res.add(tmp)
                }

            }
            return res
        }

        private fun generateParticipantPlayerIdMaper(matchJO: PubgMatchJO): Map<String, String> {
            val res = mutableMapOf<String, String>()
            matchJO.included.filterIsInstance<JsonMatchParticipant>().forEach {
                res.put(it.id, it.attributes.stats.playerId)
            }
            return res
        }

    }

}