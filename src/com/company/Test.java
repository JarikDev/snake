package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.Timer;
import java.util.Iterator;
import java.util.Random;

public class Test extends JPanel implements ActionListener, KeyListener {

    boolean play = false;
    Timer timer;
    public int delay = 200;
    int rectX = 100;
    int rectY = 100;
    int velX = 10;
    int velY = 10;
    int count = 1;
    Glist glist;
    int glistBefore = 5;
    int glistAfter = 0;
    int scores = 0;


    ArrayList<Integer[]> snakeList;
    ArrayList<Victim> victimList;
    Victim victim;
    Random random;

    public Test() {
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(true);
        timer = new Timer(delay, this);
        glist = new Glist();
        victim = new Victim();
        random = new Random();
        snakeList = new ArrayList<Integer[]>();
        victimList = new ArrayList<Victim>();
        snakeList.add(new Integer[]{glist.snakeX, glist.snakeY});
        timer.start();
    }

    public Victim victimAdd(Victim victim) {
        int vicX = random.nextInt(38) * 20;
        int vicY = random.nextInt(38) * 20;
        return new Victim(vicX, vicY);
    }

    public void glistGrowth() {
        snakeList.add(new Integer[]{glist.snakeX, glist.snakeY});
        glistAfter++;

    }

    public void glistRemove() {
        snakeList.remove(0);
        glistBefore = glistAfter;
    }

    public void paintComponent(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        g.setColor(Color.RED);
        g.fillRect(0, 0, 5, 800);
        g.fillRect(0, 0, 800, 5);
        g.fillRect(0, 766, 800, 5);
        g.fillRect(789, 0, 5, 800);
        g.setColor(Color.GREEN);

        for (Integer[] snakePart : snakeList) {
            g.fillOval(snakePart[0], snakePart[1], 20, 20);


        }
        g.setColor(Color.YELLOW);
        for (Victim vic : victimList) {
            g.fillOval(vic.vicX, vic.vicY, 20, 20);
                 }
        if (play == true) {
            g.setColor(Color.cyan);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString(" Scores: " + scores, 10, 35);
        } else {
            g.setColor(Color.PINK);
            g.setFont(new Font("serif", Font.BOLD, 40));
            g.drawString("Game Over  :-(  Scores: " + scores, 190, 300);
            g.setFont(new Font("serif", Font.BOLD, 40));
            g.drawString("Press  E n t e r  for start/restart", 185, 400);
            g.setFont(new Font("serif", Font.BOLD, 40));
            g.drawString("Press  Space  for pause", 185, 500);
        }
        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (play == true) {
            glistGrowth();
            repaint();
            if (glistAfter > glistBefore) {
                glistRemove();
            }
            if (victimList.size() < 1) {
                victimList.add(victimAdd(victim));
            }
            update();
            count++;
            Integer[] snakeHead = snakeList.get(snakeList.size() - 1);
            if (snakeHead[0] > 740 || snakeHead[1] > 740 || snakeHead[0] < 0 || snakeHead[1] < 0) {
                play = false;
                System.out.println("Game Over");
            }

            Integer[] snakePart = snakeList.get(snakeList.size() - 1);
            for (int i = 0; i < snakeList.size() - 2; i++) {

                Integer[] snakePartTwo = snakeList.get(i);
                if ((int) snakePart[0] == (int) snakePartTwo[0] && (int) snakePart[1] == (int) snakePartTwo[1]) {
                    play = false;
                    break;
                }
            }
        }
    }

    public void update() {

        Integer[] snakeHead = snakeList.get(snakeList.size() - 1);
        Victim vic = victimList.get(0);
        if (vic.vicX == snakeHead[0] && vic.vicY == snakeHead[1]) {
            victimList.remove(0);
            scores += 100;
            glistBefore++;
        }

        if (glist.snakeDir == 1) {
            glist.moveSnakeU();
        }
        if (glist.snakeDir == 2) {
            glist.moveSnakeR();
        }
        if (glist.snakeDir == 3) {
            glist.moveSnakeD();
        }
        if (glist.snakeDir == 4) {
            glist.moveSnakeL();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_UP && glist.snakeDir != 3) {
            glist.snakeDir = 1;
        }

        if (e.getKeyCode() == KeyEvent.VK_DOWN && glist.snakeDir != 1) {
            glist.snakeDir = 3;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT && glist.snakeDir != 2) {
            glist.snakeDir = 4;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT && glist.snakeDir != 4) {
            glist.snakeDir = 2;
        }

        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (play == false) {
                play = true;
                if (victimList.size() > 0) {
                    victimList.remove(0);

                    for (int i = snakeList.size(); i > 0; i--) {
                        snakeList.remove(i - 1);
                    }
                }
                glist.snakeX = 600;
                glist.snakeY = 200;
                glistBefore = 5;
                glistAfter = -1;
                glist.snakeDir = 3;
                glistGrowth();
                victimList.add(victimAdd(victim));
                repaint();
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            if (play == true) {
                play = false;
            } else {
                play = true;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}