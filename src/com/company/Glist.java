package com.company;

import java.awt.*;
import java.util.ArrayList;

public class Glist {
    public int snakeX = 600;
    public int snakeY = 200;
    public int snakeDir = 3;

    public void moveSnakeU() {
        snakeY -= 20;
    }

    public void moveSnakeD() {
        snakeY += 20;
    }

    public void moveSnakeL() {
        snakeX -= 20;
    }

    public void moveSnakeR() {
        snakeX += 20;
    }
}
