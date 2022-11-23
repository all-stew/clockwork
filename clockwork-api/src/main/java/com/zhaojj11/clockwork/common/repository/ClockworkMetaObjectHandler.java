package com.zhaojj11.clockwork.common.repository;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * 元对象字段填充控制器，自动填充createdTime，updatedTime
 *
 * @author zhaojj11
 */
@Component
public class ClockworkMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("createdTime", LocalDateTime.now(ZoneOffset.systemDefault()), metaObject);
        this.setFieldValByName("updatedTime", LocalDateTime.now(ZoneOffset.systemDefault()), metaObject);
        this.strictInsertFill(metaObject, "createdTime", LocalDateTime::now, LocalDateTime.class);
        this.strictInsertFill(metaObject, "updatedTime", LocalDateTime::now, LocalDateTime.class);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject, "updatedTime", LocalDateTime::now, LocalDateTime.class);
    }
}
