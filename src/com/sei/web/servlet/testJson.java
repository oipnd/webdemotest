package com.sei.web.servlet;
/**
 * @Author lanoipd
 * @Date 2019/5/15 20:53
 */

import com.google.gson.Gson;

/**
 * @title: testJson
 * @projectName WebDemoTest
 * @description: TODO
 * @author lanoipd
 * @date 2019/5/1520:53
 */

public class testJson {
    public static void main(String[] args) {
        String s1 = "{\"uid\":\"testfianl12234\",\"fpath\":\"testfianl12234/2019-5-24\",\"txt\":\"14-26-23\",\"answers\":\"{\\\"answers\\\": [{\\\"questionNumber\\\":\\\"1\\\", \\\"userAnswer\\\":\\\"2\\\"}]}\",\"bType\":\"pc\"}";

        Gson gson=new Gson();
        Answers answers=gson.fromJson(s1,Answers.class );
        System.out.println(answers.getUid());
        System.out.println(answers.getTxt());
        System.out.println(answers.getBType());
        System.out.println(answers.getFpath());
        System.out.println(answers.getAnswers());


    }
}
