package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.Random;

/**
 * <p>
 *
 * </p>
 *
 * @author wpc
 * @since 2021/12/16
 */
public class AutoSlider {
    /**
     * Description：模拟人工移动
     * @param driver: selenium的实体对象
     * @param element 页面滑块
     * @param distance：需要移动距离
     * @throws InterruptedException
     */
    public void move(WebDriver driver, WebElement element, int distance) throws InterruptedException {
        int randomTime = 0;
        if (distance > 90) {
            randomTime = 250;
        } else if (distance > 80 && distance <= 90) {
            randomTime = 150;
        }
        ArrayList<Integer> track = getMoveTrack(distance - 2);
        int moveY = 1;
            Actions actions = new Actions(driver);
            actions.clickAndHold(element).perform();
            Thread.sleep(200);
            for (int i = 0; i < track.size(); i++) {
                actions.moveByOffset(track.get(i), moveY).perform();
                Thread.sleep(new Random().nextInt(300) + randomTime);
            }
                Thread.sleep(200);
                actions.release(element).perform();
    }

    /**
     * 根据距离获取滑动轨迹
     *
     * @param distance :需要移动的距离
     * @return 滑块每次移动的距离
     */
    public  ArrayList<Integer> getMoveTrack(int distance) {
        ArrayList<Integer> track = new ArrayList<>();
        // 移动轨迹
        Random random = new Random();
        int current = 0;
        // 已经移动的距离
        int mid = (int) distance * 4 / 5;
        // 减速阈值
        int a = 0;
        int move = 0;
        // 每次循环移动的距离
        while (true) {
            a = 1;
            if (current <= mid) {
                move += a;
                // 不断加速
            } else {
                move -= a;
            }
            if ((current + move) < distance) {
                track.add(move);
            } else {
                track.add(distance - current);
                break;
            }
            current += move;
        }
        return track;
    }

}
