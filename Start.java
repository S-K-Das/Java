import javax.swing.*;
import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import Login.*;
import Portal.*;

public class Start {
    public static void main(String args[]) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
		 
        }

        EventQueue.invokeLater(() -> new HomePage().setVisible(true));
		





    }
}