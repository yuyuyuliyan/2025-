package com.itheima.bird1;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

//小鸟类
public class Bird {
    public BufferedImage image;
    public BufferedImage images[];
    public int x,y;
    public int width;
    public int height;
    public int index=0;

    public double speed = 0;
    public double upspeed = 20;
    public double t = 0.2;
    public double g = 9.8;
    public double s = 0;


    public Bird(){
        try {
            x=120;
            y=220;
            image= ImageIO.read(getClass().getResource("0.png"));
            images=new BufferedImage[8];
            for (int i=0;i<images.length;i++){
                images[i]=ImageIO.read(getClass().getResource(i + ".png"));
            }
            width=image.getWidth();
            height=image.getHeight();
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void fly(){
        index++;
        image = images[index/8 % 8];
    }

    public void up(){
        speed = upspeed;
    }

    public void dowm(){


        double v = speed;
        s =  v * t - g * t * t /2;
        y = y-(int)s;
        speed = v - g * t;
    }
}
