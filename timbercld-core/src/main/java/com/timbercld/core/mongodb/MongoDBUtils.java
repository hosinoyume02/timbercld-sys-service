package com.timbercld.core.mongodb;

import com.mongodb.BasicDBObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import javax.annotation.PostConstruct;
import java.util.List;


//@Component
public class MongoDBUtils {

    @Autowired
    private MongoTemplate mongoTemplate;

    public static MongoDBUtils mongoDBUtils;

    @PostConstruct
    public void init() {
        mongoDBUtils = this;
        mongoDBUtils.mongoTemplate = this.mongoTemplate;
    }

    /**
     * 保存数据对象，集合为数据对象中@Document 注解所配置的collection
     *
     * @param obj
     *            数据对象
     */
    public static void save(Object obj) {

        mongoDBUtils.mongoTemplate.save(obj);
    }

    /**
     * 指定集合保存数据对象
     *
     * @param obj
     *            数据对象
     * @param collectionName
     *            集合名
     */
    public static void save(Object obj, String collectionName) {

        mongoDBUtils.mongoTemplate.save(obj, collectionName);
    }

    /**
     * 根据数据对象中的id删除数据，集合为数据对象中@Document 注解所配置的collection
     *
     * @param obj
     *            数据对象
     */
    public static void remove(Object obj) {

        mongoDBUtils.mongoTemplate.remove(obj);
    }

    /**
     * 指定集合 根据数据对象中的id删除数据
     *
     * @param obj
     *            数据对象
     * @param collectionName
     *            集合名
     */
    public static void remove(Object obj, String collectionName) {

        mongoDBUtils.mongoTemplate.remove(obj, collectionName);
    }

    /**
     * 根据key，value到指定集合删除数据
     *
     * @param key
     *            键
     * @param value
     *            值
     * @param collectionName
     *            集合名
     */
    public static void removeById(String key, Object value, String collectionName) {
        Query query = Query.query(Criteria.where(key).is(value));
        mongoDBUtils.mongoTemplate.remove(query, collectionName);
    }

    /**
     * 根据key，value到指定集合删除数据
     *
     * @param key
     *            键
     * @param value
     *            值
     * @param collectionName
     *            集合名
     */
    public static void remove(String[] key, Object[] value, String collectionName) {

        Criteria criteria = null;
        for (int i = 0; i < key.length; i++) {
            if (i == 0) {
                criteria = Criteria.where(key[i]).is(value[i]);
            } else {
                criteria.and(key[i]).is(value[i]);
            }
        }
        Query query = Query.query(criteria);
        mongoDBUtils.mongoTemplate.remove(query, collectionName);
    }

    /**
     * 指定集合 修改数据，且仅修改找到的第一条数据
     *
     * @param accordingKey
     *            修改条件 key
     * @param accordingValue
     *            修改条件 value
     * @param updateKeys
     *            修改内容 key数组
     * @param updateValues
     *            修改内容 value数组
     * @param collectionName
     *            集合名
     */
    public static void updateFirst(String accordingKey, Object accordingValue, String[] updateKeys, Object[] updateValues,
                                   String collectionName) {

        Criteria criteria = Criteria.where(accordingKey).is(accordingValue);
        Query query = Query.query(criteria);
        Update update = new Update();
        for (int i = 0; i < updateKeys.length; i++) {
            update.set(updateKeys[i], updateValues[i]);
        }
        mongoDBUtils.mongoTemplate.updateFirst(query, update, collectionName);
    }

    /**
     * 指定集合 修改数据，且修改所找到的所有数据
     *
     * @param accordingKey
     *            修改条件 key
     * @param accordingValue
     *            修改条件 value
     * @param updateKeys
     *            修改内容 key数组
     * @param updateValues
     *            修改内容 value数组
     * @param collectionName
     *            集合名
     */
    public static void updateMulti(String accordingKey, Object accordingValue, String[] updateKeys, Object[] updateValues,
                                   String collectionName) {

        Criteria criteria = Criteria.where(accordingKey).is(accordingValue);
        Query query = Query.query(criteria);
        Update update = new Update();
        for (int i = 0; i < updateKeys.length; i++) {
            update.set(updateKeys[i], updateValues[i]);
        }
        mongoDBUtils.mongoTemplate.updateMulti(query, update, collectionName);
    }

    /**
     * 根据条件查询出所有结果集 集合为数据对象中@Document 注解所配置的collection
     *
     * @param obj
     *            数据对象
     * @param findKeys
     *            查询条件 key
     * @param findValues
     *            查询条件 value
     * @return
     */
    public static List<? extends Object> find(Object obj, String[] findKeys, Object[] findValues) {

        Criteria criteria = null;
        for (int i = 0; i < findKeys.length; i++) {
            if (i == 0) {
                criteria = Criteria.where(findKeys[i]).is(findValues[i]);
            } else {
                criteria.and(findKeys[i]).is(findValues[i]);
            }
        }
        Query query = Query.query(criteria);
        List<? extends Object> resultList = mongoDBUtils.mongoTemplate.find(query, obj.getClass());
        return resultList;
    }

    /**
     * 指定集合 根据条件查询出所有结果集
     *
     * @param obj
     *            数据对象
     * @param findKeys
     *            查询条件 key
     * @param findValues
     *            查询条件 value
     * @param collectionName
     *            集合名
     * @return
     */
    public static List<? extends Object> find(Object obj, String[] findKeys, Object[] findValues, String collectionName) {

        Criteria criteria = null;
        for (int i = 0; i < findKeys.length; i++) {
            if (i == 0) {
                criteria = Criteria.where(findKeys[i]).is(findValues[i]);
            } else {
                criteria.and(findKeys[i]).is(findValues[i]);
            }
        }
        Query query = Query.query(criteria);
        List<? extends Object> resultList = mongoDBUtils.mongoTemplate.find(query, obj.getClass(), collectionName);
        return resultList;
    }

    /**
     * 指定集合 根据条件查询出所有结果集 并排倒序
     *
     * @param obj
     *            数据对象
     * @param findKeys
     *            查询条件 key
     * @param findValues
     *            查询条件 value
     * @param collectionName
     *            集合名
     * @param sort
     *            排序字段
     * @return
     */
    public static List<? extends Object> find(Object obj, String[] findKeys, Object[] findValues, String collectionName ,String sort) {

        Criteria criteria = null;
        for (int i = 0; i < findKeys.length; i++) {
            if (i == 0) {
                criteria = Criteria.where(findKeys[i]).is(findValues[i]);
            } else {
                criteria.and(findKeys[i]).is(findValues[i]);
            }
        }
        Query query = Query.query(criteria);
        query.with(Sort.by(Sort.Direction.DESC, sort));
        List<? extends Object> resultList = mongoDBUtils.mongoTemplate.find(query, obj.getClass(), collectionName);
        return resultList;
    }

    /**
     * 根据条件查询出符合的第一条数据 集合为数据对象中 @Document 注解所配置的collection
     *
     * @param classtype
     *            数据对象
     * @param findKeys
     *            查询条件 key
     * @param findValues
     *            查询条件 value
     * @return
     */
    public static Object findOne(Class<?> classtype, String[] findKeys, Object[] findValues) {

        Criteria criteria = null;
        for (int i = 0; i < findKeys.length; i++) {
            if (i == 0) {
                criteria = Criteria.where(findKeys[i]).is(findValues[i]);
            } else {
                criteria.and(findKeys[i]).is(findValues[i]);
            }
        }
        Query query = Query.query(criteria);
        Object resultObj = mongoDBUtils.mongoTemplate.findOne(query, classtype);
        return resultObj;
    }

    /**
     * 指定集合 根据条件查询出符合的第一条数据
     *
     * @param classtype
     *            数据对象
     * @param findKeys
     *            查询条件 key
     * @param findValues
     *            查询条件 value
     * @param collectionName
     *            集合名
     * @return
     */
    public static Object findOne(Class<?> classtype, String[] findKeys, Object[] findValues, String collectionName) {

        Criteria criteria = null;
        for (int i = 0; i < findKeys.length; i++) {
            if (i == 0) {
                criteria = Criteria.where(findKeys[i]).is(findValues[i]);
            } else {
                criteria.and(findKeys[i]).is(findValues[i]);
            }
        }
        Query query = Query.query(criteria);
        Object resultObj = mongoDBUtils.mongoTemplate.findOne(query, classtype, collectionName);
        return resultObj;
    }

    /**
     * 查询出所有结果集 集合为数据对象中 @Document 注解所配置的collection
     *
     * @param obj
     *            数据对象
     * @return
     */
    public static List<? extends Object> findAll(Object obj) {

        List<? extends Object> resultList = mongoDBUtils.mongoTemplate.findAll(obj.getClass());
        return resultList;
    }

    /**
     * 指定集合 查询出所有结果集
     *
     * @param obj
     *            数据对象
     * @param collectionName
     *            集合名
     * @return
     */
    public static List<? extends Object> findAll(Object obj, String collectionName) {

        List<? extends Object> resultList = mongoDBUtils.mongoTemplate.findAll(obj.getClass(), collectionName);
        return resultList;
    }

    /**
     *
     * @param findKeys 查询条件 key
     * @param findValues 查询条件 value
     * @param collectionName 集合名
     * @return Long 数据量
     */
    public static Long count(String[] findKeys, Object[] findValues, String collectionName) {
        Criteria criteria = null;
        for (int i = 0; i < findKeys.length; i++) {
            if (i == 0) {
                criteria = Criteria.where(findKeys[i]).is(findValues[i]);
            } else {
                criteria.and(findKeys[i]).is(findValues[i]);
            }
        }
        Query query = Query.query(criteria);
        return mongoDBUtils.mongoTemplate.count(query,collectionName);
    }


    /**
     *
     * @param classtype 数据对象
     * @param findKeys  查询条件 key
     * @param findValues 查询条件 value
     * @param collectionName 集合名
     * @param page 页码
     * @param pageSize 每页数目
     * @param sort 排序条件
     * @param sd 排序规则
     * @return
     */
    public static List<? extends Object> findLimit(Class<?> classtype, String[] findKeys, Object[] findValues, String collectionName ,Long page, Integer pageSize,String sort, Sort.Direction sd) {

        Criteria criteria = null;
        for (int i = 0; i < findKeys.length; i++) {
            if (i == 0) {
                criteria = Criteria.where(findKeys[i]).is(findValues[i]);
            } else {
                criteria.and(findKeys[i]).is(findValues[i]);
            }
        }
        Query query = Query.query(criteria)
                .skip((page - 1) * pageSize)
                .limit(pageSize)
                .with(Sort.by(sd, sort));
        List<? extends Object> resultList = mongoDBUtils.mongoTemplate.find(query, classtype, collectionName);
        return resultList;
    }

    /**
     *
     * @param fields 查询指定字段
     * @param classtype 数据对象
     * @param findKeys  查询条件 key
     * @param findValues 查询条件 value
     * @param collectionName 集合名
     * @param page 页码
     * @param pageSize 每页数目
     * @param sort 排序条件
     * @param sd 排序规则
     * @return
     */
    public static List<? extends Object> findFieldsLimit(String[] fields, Class<?> classtype, String[] findKeys, Object[] findValues, String collectionName ,Long page, Integer pageSize,String sort, Sort.Direction sd) {

        Criteria criteria = null;
        for (int i = 0; i < findKeys.length; i++) {
            if (i == 0) {
                criteria = Criteria.where(findKeys[i]).is(findValues[i]);
            } else {
                criteria.and(findKeys[i]).is(findValues[i]);
            }
        }
        BasicDBObject fieldsObject = new BasicDBObject();
        for (String field : fields) {
            fieldsObject.append(field, true);
        }
        Query query = new BasicQuery("{}", fieldsObject.toJson());
        query.addCriteria(criteria).skip((page - 1) * pageSize).limit(pageSize).with(Sort.by(sd, sort));
        List<? extends Object> resultList = mongoDBUtils.mongoTemplate.find(query, classtype, collectionName);
        return resultList;
    }

}
