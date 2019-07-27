package xyz.ivyxjc.pubg.system.transformation.utils

import com.google.gson.Gson
import xyz.ivyxjc.pubg.system.transformation.jo.PubgMatchJO
import xyz.ivyxjc.pubg.system.transformation.jo.PubgPlayerJO
import java.io.InputStream
import java.io.InputStreamReader

class PubgJsonParser {
    companion object {
        private val gson = Gson()

        fun parserPlayer(input: InputStream): PubgPlayerJO {
            val jsonReader = InputStreamReader(input)
            return gson.fromJson(jsonReader, PubgPlayerJO::class.java)
        }

        fun parserMatch(input: InputStream): PubgMatchJO {
            val jsonReader = InputStreamReader(input)
            return gson.fromJson(jsonReader, PubgMatchJO::class.java)
        }
    }
}