package org.codeforworld.winterredserver.aop;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Component
public class SaveUserHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "updateOn", LocalDateTime.class, LocalDateTime.now());
        this.strictInsertFill(metaObject, "updateBy", String.class, RecordUserAspect.getCurUser());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        metaObject.setValue("updateOn", LocalDateTime.now());
        metaObject.setValue("updateBy", RecordUserAspect.getCurUser());
    }
}
