package xyz.ivyxjc.pubg.system.transformation.utils

import com.google.gson.Gson
import xyz.ivyxjc.pubg.system.transformation.jo.*
import java.io.InputStream
import java.io.InputStreamReader


internal class PubgJsonParser {
    companion object {
        private val gson = Gson()

        fun parserPlayer(input: InputStream): PubgPlayerJO {
            val jsonReader = InputStreamReader(input)
            return gson.fromJson(jsonReader, PubgPlayerJO::class.java)
        }

        fun parserPlayer(input: String): PubgPlayerJO {
            return gson.fromJson(input, PubgPlayerJO::class.java)
        }

        fun parserMatch(input: InputStream): PubgMatchJO {
            val parse = MultiTypeJsonParser.Builder<JsonMatchIncluded>()
                .registerTypeElementName("type")
                .registerTargetClass(JsonMatchIncluded::class.java)
                .registerTypeElementValueWithClassType("roster", JsonMatchRoster::class.java)
                .registerTypeElementValueWithClassType("participant", JsonMatchParticipant::class.java)
                .registerTypeElementValueWithClassType("asset", JsonMatchAsset::class.java)
                .build()
            val jsonReader = InputStreamReader(input)
            return parse.parseGson.fromJson(jsonReader, PubgMatchJO::class.java)
        }

        fun parserMatch(input: String): PubgMatchJO {
            val parse = MultiTypeJsonParser.Builder<JsonMatchIncluded>()
                .registerTypeElementName("type")
                .registerTargetClass(JsonMatchIncluded::class.java)
                .registerTypeElementValueWithClassType("roster", JsonMatchRoster::class.java)
                .registerTypeElementValueWithClassType("participant", JsonMatchParticipant::class.java)
                .registerTypeElementValueWithClassType("asset", JsonMatchAsset::class.java)
                .build()
            return parse.parseGson.fromJson(input, PubgMatchJO::class.java)
        }

    }
}