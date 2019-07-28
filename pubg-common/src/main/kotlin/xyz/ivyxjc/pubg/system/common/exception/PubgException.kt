package xyz.ivyxjc.pubg.system.common.exception

open class UnsupportedPubgElementException(message: String) : RuntimeException(message)

class UnsupportedGameModeException(message: String) : UnsupportedPubgElementException(message)

class UnsupportedMapException(message: String) : UnsupportedPubgElementException(message)

class UnsupportedPlatformRegionException(message: String) : UnsupportedPubgElementException(message)
