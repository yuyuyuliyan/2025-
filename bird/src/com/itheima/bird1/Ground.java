package com.itheima.bird1;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

//地面类
public class Ground {
    public BufferedImage image;//地面图片缓存区
    public int x;
    public int y;

    public Ground() {
        try {
            x = 0;
            y = 500;
            image = ImageIO.read(getClass().getResource("ground.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void step(){
        x--;
        if (x== -100){
            x=0;
        }

    }
}