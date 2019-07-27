package xyz.ivyxjc.pubg.system.common.types

import xyz.ivyxjc.pubg.system.common.exception.UnsupportedGameModeException


enum class GameMode(val mode: String) {
    DUO("duo"),
    DUO_FPP("duo-fpp"),
    SOLO("solo"),
    SOLO_FPP("solo-fpp"),
    SQUAD("squad"),
    SQUAD_FPP("squad-fpp");

    companion object {
        fun enumOf(s: String?): GameMode {
            return when (s) {
                "duo" -> DUO
                "duo-fpp" -> DUO_FPP
                "solo" -> SOLO
                "solo-fpp" -> SOLO_FPP
                "squad" -> SQUAD
                "squad-fpp" -> SQUAD_FPP
                else -> throw UnsupportedGameModeException("Not support this game mode: $s")
            }
        }
    }
}