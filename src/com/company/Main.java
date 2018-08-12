package com.company;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setTitle("Glist Run");
        frame.setLocation(500, 200);
        Test test = new Test();
        frame.add(test);


    }
}
