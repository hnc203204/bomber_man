package Executer;

import Enums.Move;

import javax.swing.*;

public class Execute {

    public static void main(String arg[]) {
        try {
            GameFrame window = new GameFrame();

            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            window.setResizable(false);

            window.pack();


            window.setLocationRelativeTo(null);
            window.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
