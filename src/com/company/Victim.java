package com.company;
import java.util.Random;

public class Victim {
    Random random=new Random();
    int vicX = random.nextInt(40) * 20;
    int vicY = random.nextInt(40) * 20;

    public Victim(){
        super();
    }
    public Victim(int X, int Y) {

        this.vicX = X;
        this.vicY = Y;

    }

}
