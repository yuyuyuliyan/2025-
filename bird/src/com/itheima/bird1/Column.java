package com.itheima.bird1;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

//管道类
public class Column {
    public int gap = 144;
    public BufferedImage cImage;
    public int x,y;
    public int width ,height;
    public static int count = 0;
    public int distance = 270;
    public Random rand = new Random();
    public Column(){
        try {
            cImage = ImageIO.read(getClass().getResource("column.png"));
            width = cImage.getWidth();
            height = cImage.getHeight();
            x = 410 + distance * count;
            y = rand.nextInt(300) + 100 - height / 2;

        }catch (IOException e) {
            throw new RuntimeException(e);
        }
        count++;
    }
    public void step() {
        x--;
        if(x == -width / 2) {
            x = x + distance * 2;
            y = rand.nextInt(300) + 100 - height / 2;
        }
    }
}
