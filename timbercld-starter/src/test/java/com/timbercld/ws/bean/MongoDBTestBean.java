package com.timbercld.ws.bean;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



@Document(collection="mongodbTestBean")
public class MongoDBTestBean {

    @Id
    private String bid;
    private String name;
    private String age;
    private String score;

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "MongodbTestBean [bid=" + bid + ", name=" + name + ", age=" + age + ", score=" + score +"]";
    }
}
