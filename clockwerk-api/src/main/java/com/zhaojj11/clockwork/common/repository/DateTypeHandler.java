package com.zhaojj11.clockwork.common.repository;

import com.zhaojj.clockwork.common.utils.TypeUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.springframework.lang.Nullable;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

/**
 * 日期类型处理器
 *
 * @author zhaojj11
 */
@Slf4j
@MappedJdbcTypes(JdbcType.INTEGER)
public class DateTypeHandler extends BaseTypeHandler<LocalDateTime> {
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int columnIndex, LocalDateTime localDateTime, JdbcType jdbcType) throws SQLException {
        long dbValue;
        if (localDateTime == null) {
            dbValue = 0;
        } else {
            dbValue = TypeUtil.toLong(localDateTime);
        }
        preparedStatement.setLong(columnIndex, dbValue);
    }

    @Override
    public LocalDateTime getNullableResult(ResultSet resultSet, String columnName) throws SQLException {
        return dbToJava(resultSet.getLong(columnName));
    }

    @Override
    public LocalDateTime getNullableResult(ResultSet resultSet, int columnIndex) throws SQLException {
        return dbToJava(resultSet.getLong(columnIndex));
    }

    @Override
    public LocalDateTime getNullableResult(CallableStatement callableStatement, int columnIndex) throws SQLException {
        return dbToJava(callableStatement.getLong(columnIndex));
    }

    private @Nullable LocalDateTime dbToJava(long dbValue) {
        return dbValue == 0 ? null : TypeUtil.toLocalDateTime(dbValue);
    }
}
