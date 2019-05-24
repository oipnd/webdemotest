package com.sei.web.servlet;
/**
 * @Author lanoipd
 * @Date 2019/5/15 21:12
 */

/**
 * @title: Answers
 * @projectName WebDemoTest
 * @description: TODO
 * @author lanoipd
 * @date 2019/5/1521:12
 */
/**
 * Copyright 2019 bejson.com
 */

/**
 * Auto-generated: 2019-05-15 21:11:33
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Answers {

    private String uid;
    private String fpath;
    private String txt;
    private String answers;
    private String bType;
    public void setUid(String uid) {
        this.uid = uid;
    }
    public String getUid() {
        return uid;
    }

    public void setFpath(String fpath) {
        this.fpath = fpath;
    }
    public String getFpath() {
        return fpath;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }
    public String getTxt() {
        return txt;
    }

    public void setAnswers(String answers) {
        this.answers = answers;
    }
    public String getAnswers() {
        return answers;
    }

    public void setBType(String bType) {
        this.bType = bType;
    }
    public String getBType() {
        return bType;
    }

    @Override
    public String toString() {
        return "Answers{" +
                "uid='" + uid + '\'' +
                ", fpath='" + fpath + '\'' +
                ", txt='" + txt + '\'' +
                ", answers='" + answers + '\'' +
                ", bType='" + bType + '\'' +
                '}';
    }
}