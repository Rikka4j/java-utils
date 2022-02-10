package utils;

import net.sf.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * <p>
 *     向接口发送数据
 * </p>
 *
 * @author yzd
 * @since 2021/9/17
 */

public class SendInfo {
/**
 *
 * @param realUrl:接口地址
 * @param obj:json 数据
 * @return method:请求方法
 **/
    public String sendPost(URL realUrl, JSONObject obj, String method) {

        PrintWriter out = null;
        BufferedReader in = null;
        StringBuilder result = new StringBuilder();
        try {
            URLConnection con = realUrl.openConnection();
            HttpURLConnection conn = (HttpURLConnection) con;
            conn.setRequestMethod(method);
            conn.setConnectTimeout(5 * 1000);
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setRequestProperty("Content-Type",
                    "application/json");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(new OutputStreamWriter(
                    conn.getOutputStream(), "utf-8"));
            out.write(obj.toString());
            out.flush();
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
            byte[] bresult = result.toString().getBytes();
            result = new StringBuilder(new String(bresult, "utf-8"));
            System.out.println("执行"+method+" 请求成功");
        } catch (Exception e) {
            System.out.println(e);
            System.out.println(obj);
            System.out.println("执行"+method+" 请求出现异常");
        }
        // 使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return "sucess";
    }
}
