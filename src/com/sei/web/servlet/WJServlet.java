package com.sei.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;

/**
 * @ClassName WJServlet
 * @Description TODO
 * @Author yyy
 * @Date 2019/2/27 0:54
 * @Version 1.0
 **/

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/WJServlet")
public class WJServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        register(request,response);
    }
    public String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }
    public int process_str(String str) {
        int pred=0;
        int m=0,n=0;
        for (int i=0;i<str.length();i++) {
            if(str.charAt(i)=='1') {
                m=m+1;
            }
            if(str.charAt(i)=='0') {
                n=n+1;
            }
        }
        float ratio=m/(float)(m+n);
        if(ratio>=0.5) {
            pred=1;
        }
        return pred;
    }
    private void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String method = request.getParameter("method");
        String message = null;

        String username = request.getParameter("q1");
        String password1 = request.getParameter("q2");
        String password2 = request.getParameter("q2");
        String data=request.getParameter("StrData");

        String sessionCode=(String) request.getSession().getAttribute("session_vcode");
        String paramCode = request.getParameter("verifyCode");

        String count="0";
        User u = null;

        ReaderDBFile rdf = new ReaderDBFile();
        u = (User) rdf.ReaderFileUse(username, password1,count);


        WriterDBFile wdf = new WriterDBFile();
        wdf.WriterFileUse(username + "\t\t" + password1 + "\t\t"+count+"\r\n");

        String filename=DataToFile(data,username,"register","screen");
//		String str=sendPost("http://192.168.100.5:5000/predict", "image="+filename);
//		int pred=process_str(str);
        String path="/data/weiang_data/mousetrack/huawei/WebTestData/wj/" ;
        int filenum=getFile(path);
        String num= Integer.toString(filenum);
        request.setAttribute("textnum", num);
            request.getRequestDispatcher("/wenjuan.jsp").forward(request, response);
        }

//


    public String DataToFile(String s,String name,String method,String type) throws IOException{
        String path=null;
        File filePath;

        path = "D:\\data\\" + name;
        filePath = new File(path);
        if (!filePath.exists()) {
            filePath.mkdirs();
        }
        File[] fileList=new File(path).listFiles();
        int fileNum=fileList.length;
        fileNum=fileNum+1;
        File files = new File( path + "/" + name + "_"+fileNum+".txt");
        if (!files.exists()) {
            files.createNewFile();
        }
        FileWriter writer = new FileWriter(path + "/" + name + "_"+fileNum+".txt", true);// ׷��
        writer.write(s);
        writer.close();
        return path + "/" + name + "_"+fileNum+".txt";
    }

    public int getFile(String filepath) {
        int filecount=0;
        //com.bizwink.cms.util.Convert con = new com.bizwink.cms.util.Convert();
        File file = new File(filepath);
        File[] listfile = file.listFiles();
        for (int i = 0; i < listfile.length; i++) {
            //System.out.println(listfile[i].getPath());
            File downfile = new File(listfile[i].getPath());
            File[] downlistfile = downfile.listFiles();
            for(int j=0;j<downlistfile.length;j++){
                filecount++;
            }

        }
        return filecount;
    }


}

