package com.timbercld.ws;

import com.timbercld.ws.bean.MongoDBTestBean;
import com.timbercld.core.mongodb.MongoDBUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.UUID;


@RunWith(SpringRunner.class)
@SpringBootTest
public class MongoDBTest {

    @Test
    public void testSave1() {
        MongoDBTestBean mb = new MongoDBTestBean();
        mb.setBid("001");
        mb.setName("葵花老师傅");
        mb.setAge("26");
        mb.setScore("100");
        MongoDBUtils.save(mb);
    }

    @Test
    public void testSave2() {
        MongoDBTestBean mb = new MongoDBTestBean();
        mb.setBid(UUID.randomUUID().toString());
        mb.setName("葵花老师傅");
        mb.setAge("26");
        mb.setScore("100");
        MongoDBUtils.save(mb, "newCollection");
    }

    @Test
    public void testSaveOneOrderCollection() {
        MongoDBTestBean mb = new MongoDBTestBean();
        mb.setBid("001");
        mb.setName("葵花老师傅");
        mb.setAge("26");
        mb.setScore("100");
        MongoDBUtils.save(mb, "newCollection");
    }

    @Test
    public void testRemove1() {
        MongoDBTestBean mb = new MongoDBTestBean();
        mb.setBid("001");
        mb.setName("葵花老师傅");
        mb.setAge("26");
        mb.setScore("100");
        MongoDBUtils.remove(mb);
    }

    @Test
    public void test4Remove2() {
        MongoDBTestBean mb = new MongoDBTestBean();
        mb.setBid("a27a0652-a0ed-4d7d-a100-d5f11fe1b844");
        mb.setName("葵花老师傅");
        mb.setAge("26");
        mb.setScore("100");
        MongoDBUtils.remove(mb, "newCollection");
    }

    @Test
    public void test5Remove3() {
        MongoDBUtils.removeById("_id", "001", "mongodbTestBean");
    }

    @Test
    public void test6Update1() {
        String accordingKey = "_id";
        String accordingValue = "001";
        String[] updateKeys = { "name", "age" };
        Object[] updateValues = { "葵花老师傅2", "26" };
        String collectionName = "newCollection";
        MongoDBUtils.updateFirst(accordingKey, accordingValue, updateKeys, updateValues, collectionName);
    }

    @Test
    public void test7Update2() {
        String accordingKey = "name";
        String accordingValue = "葵花老师傅2";
        String[] updateKeys = { "age" };
        Object[] updateValues = { "260" };
        String collectionName = "newCollection";
        MongoDBUtils.updateMulti(accordingKey, accordingValue, updateKeys, updateValues, collectionName);
    }

    @Test
    public void testFind1() {
        MongoDBTestBean obj = new MongoDBTestBean();
        String[] findKeys = { "age", "name" };
        String[] findValues = { "22", "mongodbTestBean" };
        List<? extends Object> find = MongoDBUtils.find(obj, findKeys, findValues);
        System.out.println(find);
    }

    @Test
    public void test9Find2() {
        MongoDBTestBean obj = new MongoDBTestBean();
        String[] findKeys = { "name" };
        String[] findValues = { "葵花老师傅2" };
        String collectionName = "newCollection";
        List<? extends Object> find = MongoDBUtils.find(obj, findKeys, findValues, collectionName);
        System.out.println(find);
    }

    @Test
    public void test91Find3() {
        MongoDBTestBean obj = new MongoDBTestBean();
        String[] findKeys = { "name" };
        String[] findValues = { "葵花老师傅2" };
        String collectionName = "newCollection";
        Object findOne = MongoDBUtils.findOne(MongoDBTestBean.class, findKeys, findValues, collectionName);
        System.out.println(findOne);
    }

    @Test
    public void test92Find4() {
        List<? extends Object> findAll = MongoDBUtils.findAll(new MongoDBTestBean());
        System.out.println(findAll);
    }

    @Test
    public void test93Find5() {
        List<? extends Object> findAll = MongoDBUtils.findAll(new MongoDBTestBean(),"newCollection");
        System.out.println(findAll);
    }


}
