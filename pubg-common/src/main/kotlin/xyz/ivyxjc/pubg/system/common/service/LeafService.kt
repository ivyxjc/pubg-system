package xyz.ivyxjc.pubg.system.common.service

import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.concurrent.locks.LockSupport
import kotlin.math.pow

@Service
interface LeafService {
    fun getGuid(): Long
    fun getGuids(): Array<Long>
}

@Profile("dev")
class LeafServiceDevImpl : LeafService {
    override fun getGuid(): Long {
        LockSupport.parkNanos(10.0.pow(6.0).toLong())
        return LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli()
    }

    override fun getGuids(): Array<Long> {
        TODO("not implemented")
    }
}

@Profile("prod")
class LeafServiceRealImpl : LeafService {
    override fun getGuid(): Long {
        TODO("not implemented")
    }

    override fun getGuids(): Array<Long> {
        TODO("not implemented")
    }
}

