package com.sei.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@WebServlet("/AJAXServlet")
public class AJAXServlet extends HttpServlet {

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
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST,GET,PUT,OPTIONS,DELETE");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with, Content-Type");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        //允许跨域访问
        String behInfo = request.getParameter("behInfo");
        String bType = request.getParameter("bType");
        String fpath = request.getParameter("fpath");
        String txt = request.getParameter("txt");
        String path = "/data1/" + bType + "/" + fpath;
        String os = System.getProperty("os.name");
        if (os.toLowerCase().startsWith("win")) {
            path="D:\\data\\" + bType + "\\" + fpath;
        }

        if (behInfo != null && bType != null) {
            dataToFile(behInfo, bType, path, txt);
        }


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "POST,GET,PUT,OPTIONS,DELETE");
        resp.setHeader("Access-Control-Allow-Headers", "x-requested-with, Content-Type");
        resp.setHeader("Access-Control-Allow-Credentials", "true");
        //允许跨域访问
        String behInfo = req.getParameter("behInfo");
        String bType = req.getParameter("bType");
        String fpath = req.getParameter("fpath");
        String txt = req.getParameter("txt");
        String path = "/data1/" + bType + "/" + fpath;


        if (behInfo != null && bType != null) {
            dataToFile(behInfo, bType, path, txt);
        }
    }

    public void dataToFile(String behInfo, String bType, String path, String text) throws IOException {
        File filePath;

        filePath = new File(path);
        if (!filePath.exists()) {
            filePath.mkdirs();
        }

        File file = new File(path + "/" + text + ".txt");

        if (file.exists() || behInfo.indexOf("CodeName") != -1) {
            //保证数据完整性
            FileWriter writer = new FileWriter(path + "/" + text + ".txt", true);
            writer.write(behInfo);
            writer.close();
        }

    }
}


