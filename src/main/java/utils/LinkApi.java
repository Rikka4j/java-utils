package utils;

import cn.hutool.json.JSONObject;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.Random;
import java.util.Set;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
/**
 * @author yzd
 * 通过网站api获取网页返回json数据
 */
public class LinkApi {
    final String ProxyHeadKey = "Proxy-Tunnel";
    /**
     *
     * @param url:地址
     * @param proxy:代理ip
     * @return 获取到的json数据
     **/
    public JSONObject getApiJson(String url,Proxy proxy) throws InterruptedException {
        Connection connect = Jsoup.connect(url);
        connect.header("Accept", "*/*");
        connect.header("Accept-Encoding", "gzip, deflate");
        connect.header("Accept-Language","zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
        connect.header("Content-Type", "application/json;charset=UTF-8");
        connect.header("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64; rv:48.0) Gecko/20100101 Firefox/48.0");
        JSONObject apiJson;
        Random random = new Random();
        int tunnel = random.nextInt(10000);
        String ProxyHeadVal = String.valueOf(tunnel);
        try{
            //proxy 开始传入为空，获取超时后切换代理ip
            connect.timeout(10000).ignoreContentType(true).header(ProxyHeadKey, ProxyHeadVal).proxy(proxy).execute();
            Document document = connect.get();
            apiJson =new  JSONObject(document.text());
            Thread.sleep(2000);
            return apiJson;
        }catch(Exception e){
            //连接超时切换ip
            Thread.sleep(2000);
            proxy=(proxy==null)?getIp():null;
            apiJson = getApiJson(url,proxy);
            return apiJson;
        }
    }
    /**Description：获取代理ip
     * @return 代理ip
     **/
    public Proxy getIp(){
        RandomSet randomSet = new RandomSet();
        Set<String> sets = null;
        String randomIp = randomSet.getRandomElement(sets);
        String ProxyHost = randomIp.substring(0, randomIp.indexOf(":"));
        String ProxyPort = randomIp.substring(randomIp.indexOf(":")+1);
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(ProxyHost, Integer.parseInt(ProxyPort)));
        return proxy;
    }
}
