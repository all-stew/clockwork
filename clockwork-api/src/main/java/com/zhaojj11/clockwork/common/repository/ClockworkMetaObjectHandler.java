package com.zhaojj11.clockwork.common.repository;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

import static com.zhaojj11.clockwork.common.constants.StringConstant.CREATED_TIME;
import static com.zhaojj11.clockwork.common.constants.StringConstant.UPDATED_TIME;
import static java.time.ZoneId.systemDefault;

/**
 * 元对象字段填充控制器，自动填充createdTime，updatedTime
 *
 * @author zhaojj11
 */
@Component
public class ClockworkMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName(CREATED_TIME, LocalDateTime.now(systemDefault()), metaObject);
        this.setFieldValByName(UPDATED_TIME, LocalDateTime.now(systemDefault()), metaObject);
        this.strictInsertFill(metaObject, CREATED_TIME, LocalDateTime::now, LocalDateTime.class);
        this.strictInsertFill(metaObject, UPDATED_TIME, LocalDateTime::now, LocalDateTime.class);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject, UPDATED_TIME, LocalDateTime::now, LocalDateTime.class);
    }
}
