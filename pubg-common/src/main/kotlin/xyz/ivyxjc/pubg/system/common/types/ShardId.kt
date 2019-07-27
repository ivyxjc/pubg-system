package xyz.ivyxjc.pubg.system.common.types

import xyz.ivyxjc.pubg.system.common.exception.UnsupportedPlatformRegionException

enum class ShardId(val pltRegion: String) {
    STEAM("steam"),
    XBOX("xbox"),
    PSN("psn"),
    KAKAO("kakao");


    companion object {

        fun enumOf(s: String): ShardId {
            when (s) {
                "steam" -> return STEAM
                "xbox" -> return XBOX
                "psn" -> return PSN
                "kakao" -> return KAKAO
                else -> throw UnsupportedPlatformRegionException(
                    "Not support this platform region:$s"
                )
            }
        }
    }

}