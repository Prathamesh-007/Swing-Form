package Main;

import javax.swing.*;

public class showMessage {
    public static void message(String s) {
        JFrame j = new JFrame();
        JOptionPane.showMessageDialog(j, s);
    }
}
