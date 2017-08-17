package com.aka.games;
import javax.swing.*;
import java.awt.*;

public class GTA extends JFrame{
    private ImageIcon image;
    private JLabel label;
    public void image(){
        setLayout(new FlowLayout());
        image = new ImageIcon(getClass().getResource("Busted.jpg"));
        label = new JLabel(image);
        add(label);

        /*JFrame panel = new JFrame();
        panel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.setSize(500,500);
        panel.setVisible(true);*/

        /*String ANSI_RESET = "\u001B[0m";
        String ANSI_RED = "\u001B[31m";
        System.out.println("\n" + ANSI_RED + "Busted!" + ANSI_RESET);*/
    }
}
