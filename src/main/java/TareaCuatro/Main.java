package TareaCuatro;

import GUI.InterfazKanban;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            InterfazKanban interfazKanban = new InterfazKanban();
            interfazKanban.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            interfazKanban.setSize(800, 600);
            interfazKanban.setVisible(true);
        });
    }
}
