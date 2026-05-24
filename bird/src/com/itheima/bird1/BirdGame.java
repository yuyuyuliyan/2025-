package com.itheima.bird1;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

//游戏操作类
public class BirdGame extends JPanel {
    public BufferedImage bg, sImage, gImage;//背景图片缓存区
    public Ground ground;
    public Bird bird;
    public Column columns[];

    public int score = 0;
    public int state;
    public static final int START = 0;
    public static final int RUNNING = 1;
    public static final int GAMEOVER = 2;


    public BirdGame() {//构造方法
        try {
            state = START;
            bg = ImageIO.read(getClass().getResource("bg.png"));//读取背景图片
            sImage = ImageIO.read(getClass().getResource("start.png"));
            gImage = ImageIO.read(getClass().getResource("gameover.png"));
            ground = new Ground();
            bird = new Bird();
            columns = new Column[2];
            columns[0] = new Column();
            columns[1] = new Column();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    //绘制方法
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(bg, 0, 0, null);//绘制背景图片;
        g.drawImage(columns[0].cImage, columns[0].x, columns[0].y, null);
        g.drawImage(columns[1].cImage, columns[1].x, columns[1].y, null);
        g.drawImage(ground.image, ground.x, ground.y, null);
        switch (state) {
            case START:
                g.drawImage(sImage, 0, 0, null);
                break;
            case GAMEOVER:
                g.drawImage(gImage, 0, 0, null);
                break;
            default:
                break;
        }
        g.drawImage(bird.image, bird.x, bird.y, null);
        setScore(g);

    }

    public void setScore(Graphics g) {
       Font font= new Font(Font.SERIF, Font.ITALIC, 40);
        g.setFont(font);
        g.setColor(Color.WHITE);
        g.drawString(score + "", 40, 60);
    }

    public void action() {
        this.addMouseListener(new BirdMouseListener());
        while (true) {
            switch (state) {
                case START:
                    ground.step();
                    bird.fly();
                    break;
                case RUNNING:
                    ground.step();
                    bird.fly();
                    if (isHitGround()) {
                        state = GAMEOVER;
                    }for (int i =0; i < columns.length; i++) {
                        Column column = columns[i];
                        column.step();
                        if (isHitColumn(column)) {
                            state=GAMEOVER;
                        }
                        if (bird.x == column.x + column.width) {
                            score++;
                        }
                }
                    bird.dowm();
                    break;
                case GAMEOVER:
                    break;
                default:
                    break;
            }
            repaint();
            try {
                Thread.sleep(1000 / 60);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private boolean isHitColumn(Column column) {
        if (bird.x >=column.x - bird.width && bird.x <= column.x + column.width) {
            if (bird.y <=column.y + column.height / 2 - 72 || bird.y >=column.y + column.height /2 + 72 -bird.height) {
                return true;
            } else {
                return  false;
            }
        } else {
            return false;

        }
    }


    public boolean isHitGround() {
        if (bird.y < ground.y - bird.height) {
            return false;
        } else {
            return true;
        }

    }

    class BirdMouseListener extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            super.mousePressed(e);

            switch (state) {
                case START:
                    state = RUNNING;
                    break;
                case RUNNING:
                    bird.up();
                    break;
                case GAMEOVER:
                    state = START;
                    bird.x = 120;
                    bird.y = 220;
                    bird.speed = 0;
                    Column.count = 0;
                    columns[0] = new Column();
                    columns[1] = new Column();
                    score = 0;
                    break;
                default:
                    break;
            }
        }
    }
}



