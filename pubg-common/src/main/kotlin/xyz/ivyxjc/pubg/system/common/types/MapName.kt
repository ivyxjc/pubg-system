package xyz.ivyxjc.pubg.system.common.types

import xyz.ivyxjc.pubg.system.common.exception.UnsupportedMapException


enum class MapName(val mapName: String) {
    DESERT_MAIN("Desert_Main"),
    ERANGEL_MAIN("Erangel_Main");


    companion object {
        fun enumOf(s: String): MapName {
            return when (s) {
                "Desert_Main" -> DESERT_MAIN
                "Erangel_Main" -> ERANGEL_MAIN
                else -> throw UnsupportedMapException("Not support this map")
            }
        }
    }
}