package xyz.ivyxjc.pubg.system.common.utils

import org.apache.ibatis.type.BaseTypeHandler
import org.apache.ibatis.type.JdbcType
import xyz.ivyxjc.pubg.system.common.types.MapName
import xyz.ivyxjc.pubg.system.common.types.ShardId
import java.sql.CallableStatement
import java.sql.PreparedStatement
import java.sql.ResultSet

class ShardIdHandler : BaseTypeHandler<ShardId?>() {

    val map = mutableMapOf<String, String>()

    init {
    }

    override fun getNullableResult(rs: ResultSet?, columnName: String?): ShardId? {
        if (rs == null) {
            return null
        }
        val str = rs.getString(columnName)
        return ShardId.enumOf(map.get(str) ?: str)
    }

    override fun getNullableResult(rs: ResultSet?, columnIndex: Int): ShardId? {
        if (rs == null) {
            return null
        }
        val str = rs.getString(columnIndex)
        return ShardId.enumOf(map.get(str) ?: str)
    }

    override fun getNullableResult(cs: CallableStatement?, columnIndex: Int): ShardId? {
        if (cs == null || cs.wasNull()) {
            return null
        }
        val str = cs.getString(columnIndex)
        return ShardId.enumOf(map.get(str) ?: str)
    }

    override fun setNonNullParameter(ps: PreparedStatement?, i: Int, parameter: ShardId?, jdbcType: JdbcType?) {
        ps?.setString(i, parameter?.pltRegion)
    }
}

class MapNameHandler : BaseTypeHandler<MapName?>() {

    override fun getNullableResult(rs: ResultSet?, columnName: String?): MapName? {
        if (rs == null) {
            return null
        }
        val str = rs.getString(columnName)
        return MapName.enumOf(str)
    }

    override fun getNullableResult(rs: ResultSet?, columnIndex: Int): MapName? {
        if (rs == null) {
            return null
        }
        val str = rs.getString(columnIndex)
        return MapName.enumOf(str)
    }

    override fun getNullableResult(cs: CallableStatement?, columnIndex: Int): MapName? {
        if (cs == null || cs.wasNull()) {
            return null
        }
        val str = cs.getString(columnIndex)
        return MapName.enumOf(str)
    }

    override fun setNonNullParameter(ps: PreparedStatement?, i: Int, parameter: MapName?, jdbcType: JdbcType?) {
        ps?.setString(i, parameter?.mapName)
    }
}
