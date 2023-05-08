package com.timbercld.core.mongodb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;


//@Component
public class MongoDBSaveEventListener extends AbstractMongoEventListener<Object> {

    @Autowired
    private MongoTemplate mongo;

    /**
     * 在发起请求到mongo服务器的前置操作
     */
    @Override
    public void onBeforeConvert(BeforeConvertEvent<Object> event) {
        final Object source = event.getSource();
        if (source != null) {
            ReflectionUtils.doWithFields(source.getClass(), new ReflectionUtils.FieldCallback() {
                public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
                    ReflectionUtils.makeAccessible(field);
                    // 如果字段添加了我们自定义的IncKey注解
                    if (field.isAnnotationPresent(AutoIncKey.class)) {
                        // 通过反射设置自增ID
                        //field.set(source, getNextId(source.getClass().getSimpleName()));
                        field.set(source, getNextId(event.getCollectionName()));
                    }
                }
            });
        }
    }

    /**
     * 获取下一个自增ID
     * 这儿有根据表名进行区分
     * @param collName 这里代表数据库表名称
     */
    private Long getNextId(String collName) {
        Query query = new Query(Criteria.where("collName").is(collName));
        Update update = new Update();
        //每次自增1
        update.inc("seqId", 1);
        FindAndModifyOptions options = new FindAndModifyOptions();
        options.upsert(true);
        options.returnNew(true);
        //操作SeqInfo表,对其seqId加一并且返回最终值
        MongoDBSeqInfo seq = mongo.findAndModify(query, update, options, MongoDBSeqInfo.class);
        return seq.getSeqId();
    }

}
