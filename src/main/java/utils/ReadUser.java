package utils;

import java.io.*;
import java.util.ArrayList;

/**
 *
 * 读取文本内容
 *
 * @author Administrator*/

public class ReadUser {
    public ArrayList<String> read() throws IOException {
        String path = "C:\\sc\\city.txt";
        File file = new File(path);
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
        String s = null;
        ArrayList<String> arrayList = new ArrayList<>();
        while((s = br.readLine())!=null){
            arrayList.add(s);
        }
        br.close();
        return arrayList;
    }
}
