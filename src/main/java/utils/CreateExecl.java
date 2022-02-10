package utils;



import java.io.*;
import java.util.List;

/**
 * <p>
 *  创建一个execl文件
 * </p>
 *
 * @author yzd
 * @since 2021/11/22
 */

public class CreateExecl {
        public void getExecl(String url){
            File writeFile = new File(url);
            if (!writeFile.exists()) {
                writeFile.mkdirs();
            }
            if (writeFile.exists()) {
                writeFile.delete();
            }
            try{
                //第二步：通过BufferedReader类创建一个使用默认大小输出缓冲区的缓冲字符输出流
                BufferedWriter writeText = new BufferedWriter(new FileWriter(writeFile));
                writeText.write("videoid"+",title"+",userid");
                //第三步：将文档的下一行数据赋值给lineData，并判断是否为空，若不为空则输出
                for(int i=0 ; i < 5 ; i++){
                    String one = "1";
                    String two = "2";
                    String three = "3";
                    writeText.newLine();    //换行
                    //调用write的方法将字符串写到流中
                    writeText.write(one+","+two+","+three);
                }
                //使用缓冲区的刷新方法将数据刷到目的地中
                writeText.flush();
                //关闭缓冲区，缓冲区没有调用系统底层资源，真正调用底层资源的是FileWriter对象，缓冲区仅仅是一个提高效率的作用
                //因此，此处的close()方法关闭的是被缓存的流对象
                writeText.close();
            }catch (FileNotFoundException e){
                System.out.println("没有找到指定文件");
            }catch (IOException e){
                System.out.println("文件读写出错");
            }


        }
}
