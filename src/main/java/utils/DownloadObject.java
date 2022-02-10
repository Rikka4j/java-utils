package utils;



import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * <p>
 *     Description：下载文件
 * </p>
 *
 * @author yzd
 * @since 2021/9/17
 */

public class DownloadObject {
    /**Description：文件下载
     * @param downUrl: 视频地址
     * @param name: 下载文件名
     * @param fileType: 类型
     * @param time: 下载日期
     */
    public void download(String downUrl,String name,String fileType,String time) {
        int byteRead;
        try {
            URL url = new URL(downUrl);
            URLConnection conn = url.openConnection();
            InputStream inStream = conn.getInputStream();
           File fileSavePath = new File("E:/DyVideo/ocean/"+time+"/"+name+ "."+fileType);
            File fileParent = fileSavePath.getParentFile();
            if (!fileParent.exists()) {
                fileParent.mkdirs();
            }
            if (fileSavePath.exists()) {
                fileSavePath.delete();
            }
            FileOutputStream fs = new FileOutputStream(fileSavePath);
            byte[] buffer = new byte[1024];
            while ((byteRead = inStream.read(buffer)) != -1) {
                fs.write(buffer, 0, byteRead);
            }
            inStream.close();
            fs.close();
        }catch (Exception e){
        }
    }
    /** Description：获取抖音视频链接转发后地址
     * @param url: 视频地址
     * @return ：真实地址
     */
    public String getRealUrl(String url){
        String location=null;
        try {
            URL serverUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) serverUrl
                    .openConnection();
            conn.setRequestMethod("GET");
            // 必须设置false，否则会自动redirect到Location的地址
            conn.setInstanceFollowRedirects(false);
            conn.addRequestProperty("Accept-Charset", "UTF-8;");
            conn.addRequestProperty("User-Agent",
                    "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.2.8) Firefox/3.6.8");
            conn.connect();
            location = conn.getHeaderField("Location");
            serverUrl = new URL(location);
            conn = (HttpURLConnection) serverUrl.openConnection();
            conn.setRequestMethod("GET");
            conn.addRequestProperty("Accept-Charset", "UTF-8;");
            conn.addRequestProperty("User-Agent",
                    "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.2.8) Firefox/3.6.8");
            conn.connect();
        } catch (Exception e) {
            System.out.println("连接超时");
        }
        return location;
    }

}
