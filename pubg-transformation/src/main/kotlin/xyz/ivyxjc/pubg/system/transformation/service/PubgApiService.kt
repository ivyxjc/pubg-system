package xyz.ivyxjc.pubg.system.transformation.service

import okhttp3.OkHttpClient
import okhttp3.Request
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import xyz.ivyxjc.pubg.system.common.utils.loggerFor

internal interface PubgApiService {
    fun getPlayerJsonByName(playerName: String): String?

    fun getPlayerJsonById(playerName: String): String?

    fun getMatchJson(matchId: String): String?
}

@Service
internal class PubgApiServiceImpl : PubgApiService {

    companion object {
        val log = loggerFor(PubgApiServiceImpl::class.java)
    }

    @Value("\${pubg_authorization}")
    private lateinit var authorization: String

    @Autowired
    private lateinit var httpClient: OkHttpClient

    private lateinit var playerRequest: Request.Builder
    private lateinit var matchRequest: Request.Builder

    override fun getPlayerJsonByName(playerName: String): String? {
        playerRequest = Request.Builder()
            .url("https://api.pubg.com/shards/steam/players?filter[playerNames]=$playerName")
            .header("Accept", "application/vnd.api+json")
            .header("Authorization", authorization)
        val response = httpClient.newCall(playerRequest.build()).execute()
        if (response.code != 200) {
            log.error("fail to get player's json for $playerName, status code is ${response.code}")
            log.error("response is: $response")
        }
        return response.body?.string()
    }

    override fun getPlayerJsonById(playerId: String): String? {
        playerRequest = Request.Builder()
            .url("https://api.pubg.com/shards/steam/players?filter[playerId]=$playerId")
            .header("Accept", "application/vnd.api+json")
            .header("Authorization", authorization)
        val response = httpClient.newCall(playerRequest.build()).execute()
        if (response.code != 200) {
            log.error("fail to get player's json for $playerId, status code is ${response.code}")
            log.error("response is: $response")
        }
        return response.body?.string()
    }

    override fun getMatchJson(matchId: String): String? {
        matchRequest = Request.Builder()
            .url("https://api.pubg.com/shards/steam/matches/$matchId")
            .header("Accept", "application/json")

        val response = httpClient.newCall(matchRequest.build()).execute()
        if (response.code != 200) {
            log.error("fail to get match's json for $matchId, status code is ${response.code}")
            log.error("response is: $response")
        }
        return response.body?.string()
    }
}