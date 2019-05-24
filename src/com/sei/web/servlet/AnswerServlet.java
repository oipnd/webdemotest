package com.sei.web.servlet;



import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@WebServlet("/AnswerServlet")
public class AnswerServlet extends HttpServlet {

    /**
     * The doPost method of the servlet. <br>
     * <p>
     * This method is called when a form has its tag value method equals to post.
     *
     * @param request  the request send by the client to the server
     * @param response the response send by the server to the client
     * @throws ServletException if an error occurred
     * @throws IOException      if an error occurred
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //允许跨域访问
        response.setHeader("Access-Control-Allow-Origin", "*");
        //处理json
        BufferedReader reader = request.getReader();
        StringBuilder readerStr = new StringBuilder() ;
        // 接收用户端传来的JSON字符串（body体里的数据）
        try {
            String line;
            while ((line = reader.readLine()) != null) {
                readerStr.append(line).append('\n');
            }
        } finally {
            reader.close();
        }

        Gson gson = new Gson();
        Answers answers = gson.fromJson(readerStr.toString(), Answers.class);
        String res=answers.toString();
        String bType = answers.getBType();
        String fpath = answers.getFpath();
        String txt = answers.getTxt();

        String path = "D:/data/" + bType + "/" + fpath + "/" + "answers";
        if (bType != null && res != null) {
            dataToFile(bType, path, txt, res);
        }

        //try {
        //    BufferedReader br = new BufferedReader(new InputStreamReader(
        //            (ServletInputStream)request.getInputStream(), "utf-8"));
        //    StringBuffer sb = new StringBuffer("");
        //    String temp;
        //    while ((temp = br.readLine()) != null) {
        //        sb.append(temp);
        //    }
        //    br.close();
        //    //获取到的json字符串
        //    String acceptjson = sb.toString();
        //    Gson gson = new Gson();
        //    Answers answers = gson.fromJson(acceptjson, Answers.class);
        //    String bType = answers.getBType();
        //    String fpath = answers.getFpath();
        //    String txt = answers.getTxt();
        //    String answersRes = answers.getAnswers();
        //    String path = "C:/data/" + bType + "/" + fpath + "/" + "answers";
        //    if (bType != null && answersRes != null) {
        //        dataToFile(bType, path, txt, answersRes);
        //    }
        //} catch (Exception e) {
        //    e.printStackTrace();
        //}


    }


    public void dataToFile(String bType, String path, String text, String res) throws IOException {
        File filePath;

        filePath = new File(path);
        if (!filePath.exists()) {
            filePath.mkdirs();
        }

        File file = new File(path + "/" + text + ".txt");

        if (file.exists() || res!=null) {//保证数据完整性
            FileWriter writer = new FileWriter(path + "/" + text + ".txt", true);
            writer.write(res);
            writer.close();
        }

    }

//		path = "/home/zbcai/zbcai_data/" + bType + "/" + UID + "/" + folder;
//		filePath = new File(path);
//		if (!filePath.exists()) {
//			filePath.mkdirs();
//		}
//		File file = new File( path + "/" + text + ".txt");
//		if (file.exists() || behInfo.indexOf("CodeName") != -1) {//保证数据完整性
//			FileWriter writer = new FileWriter(path + "/" + text + ".txt", true);
//			writer.write(behInfo);
//			writer.close();
//	    }
}

