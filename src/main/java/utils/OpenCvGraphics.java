package utils;

import org.apache.commons.io.FileUtils;
import org.opencv.core.*;
import org.opencv.core.Point;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * <p>
 *     滑块解锁破解
 * </p>
 *
 * @author yzd
 */
public class OpenCvGraphics {
    /**
     * Description:处理验证码背景图片
     * @param driver:selenium的实体对象
     * @return 滑块所需移动的距离
     **/
    public int  GrayImage(WebDriver driver) throws IOException {
        //提取验证码图片
        String url1 = driver.findElement(By.id("captcha-verify-image")).getAttribute("src");
        String url2 = driver.findElement(By.className("ggNWOG")).getAttribute("src");
        String height = driver.findElement(By.className("ggNWOG")).getAttribute("style");
        String num = height.substring(height.indexOf("top:") + 5, height.indexOf("em"));
        double em = Double.parseDouble(num);
        int v = (int)(em * 160)+5;
        File f1 = new File("c://fxb//bj.png");
        File f2 = new File("c://fxb//hk.png");
        FileUtils.copyURLToFile(new URL(url1), f1);
        FileUtils.copyURLToFile(new URL(url2), f2);
        BufferedImage sBI = ImageIO.read(f2);
        BufferedImage bgBI = ImageIO.read(f1);
        //裁剪背景图与滑块对应的区域，缩小匹配范围
        bgBI = bgBI.getSubimage(0, v, bgBI.getWidth(), sBI.getHeight());
        ImageIO.write(bgBI, "png", f1);
        //将拼图透明部分变为白色
        setWhite(sBI);
        ImageIO.write(sBI, "png", f2);
        // 加载动态库
        System.load("c://fxb//opencv_java454.dll");
        Mat s_mat = Imgcodecs.imread(f2.getPath());
        Mat b_mat = Imgcodecs.imread(f1.getPath());
        // 转灰度图像
        Mat s_newMat = new Mat();
        Mat b_newMat = new Mat();
        Imgproc.cvtColor(s_mat, s_newMat, Imgproc.COLOR_BGR2GRAY);
        Imgproc.cvtColor(b_mat, b_newMat, Imgproc.COLOR_BGR2GRAY);
        Imgcodecs.imwrite(f2.getPath(), s_newMat);
        Imgcodecs.imwrite(f1.getPath(), b_newMat);
        // 自适应阈值化
        Mat s_nMat = new Mat();
        Imgproc.adaptiveThreshold(s_newMat, s_nMat, 255, Imgproc.ADAPTIVE_THRESH_MEAN_C, Imgproc.THRESH_BINARY, 7, -4);
        Imgcodecs.imwrite(f2.getPath(), s_nMat);
        Mat b_nMat = new Mat();
        Imgproc.adaptiveThreshold(b_newMat, b_nMat, 255, Imgproc.ADAPTIVE_THRESH_MEAN_C, Imgproc.THRESH_BINARY, 7, -4);
        Imgcodecs.imwrite(f1.getPath(), b_nMat);
        b_mat = Imgcodecs.imread(f1.getPath());
        s_mat = Imgcodecs.imread(f2.getPath());
        //将处理过后的两张图进行模糊匹配
        int result_rows = b_mat.rows() - s_mat.rows() + 1;
        int result_cols = b_mat.cols() - s_mat.cols() + 1;
        Mat g_result = new Mat(result_rows, result_cols, CvType.CV_32FC1);
        Imgproc.matchTemplate(b_mat, s_mat, g_result, Imgproc.TM_CCOEFF);
        // 相关系数匹配法
        Core.normalize(g_result, g_result, 0, 1, Core.NORM_MINMAX, -1, new Mat());
        org.opencv.core.Point matchLocation = new org.opencv.core.Point();
        Core.MinMaxLocResult mmlr = Core.minMaxLoc(g_result);
        matchLocation = mmlr.maxLoc;
        // 此处使用maxLoc还是minLoc取决于使用的匹配算法
        Imgproc.rectangle(b_mat, matchLocation, new Point(matchLocation.x + s_mat.cols(), matchLocation.y + s_mat.rows()), new Scalar(0, 255, 0, 0));
        Imgcodecs.imwrite("c://fxb/dx.png", b_mat);
        int distance=(int) ((matchLocation.x + s_mat.cols() - sBI.getWidth()) * 3 / 4 - 28);
        return  distance;
    }


    /**
     * 将图片渲染成白色
     * @param image:图片路径
     **/
    public  void setWhite(BufferedImage image) throws IOException {
        if (image == null) {
            return;
        } else {
            int rgb;
            for (int i = 0; i < image.getWidth(); i++) {
                for (int j = 0; j < image.getHeight(); j++) {
                    rgb = image.getRGB(i, j);
                    int A = (rgb & 0xFF000000) >>> 24;
                    if (A < 100) {
                        image.setRGB(i, j, new Color(255, 255, 255).getRGB());
                    }
                }
            }
        }
    }
}
