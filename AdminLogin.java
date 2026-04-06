package Login;
import Portal.*;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class AdminLogin extends JFrame implements ActionListener {

    private JLabel userLabel, passLabel;
    private JTextField tf;
    private JPasswordField pf;
    private JButton loginButton, clearButton, backButton;
    private Container c;
    private Font f;

    private static final String adminName = "ALEX";
    private static final String adminPass = "12345";

    public AdminLogin() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(950, 600);
        this.setLocationRelativeTo(null);
        this.setTitle("Admin Login");

        c = this.getContentPane();
        c.setLayout(null);
        c.setBackground(Color.DARK_GRAY);

        f = new Font("Times New Roman", Font.BOLD, 18);

        userLabel = new JLabel("Username:");
        userLabel.setBounds(280, 150, 150, 50);
		userLabel.setForeground(Color.white);
        userLabel.setFont(f);
        c.add(userLabel);

        tf = new JTextField();
        tf.setFont(f);
        tf.setBounds(400, 150, 200, 50);  
        c.add(tf);

        passLabel = new JLabel("Password:");
        passLabel.setBounds(280, 250, 150, 50);
		passLabel.setForeground(Color.white);
        passLabel.setFont(f);
        c.add(passLabel);

        pf = new JPasswordField();
        pf.setFont(f);
        pf.setBounds(400, 250, 200, 50);
        c.add(pf);

        loginButton = new JButton("Login");
        loginButton.setBounds(300, 390, 90, 50);
        loginButton.setFont(f);
        loginButton.setBackground(Color.CYAN);
        loginButton.setForeground(Color.WHITE);
        loginButton.addMouseListener(new ButtonMouseListener(loginButton, Color.blue, Color.cyan));
        c.add(loginButton);

        clearButton = new JButton("Clear");
        clearButton.setBounds(480, 390, 90, 50);
        clearButton.setFont(f);
        clearButton.setBackground(Color.CYAN);
        clearButton.setForeground(Color.WHITE);
        clearButton.addMouseListener(new ButtonMouseListener(clearButton, Color.blue, Color.cyan));
        c.add(clearButton);

        backButton = new JButton("<-");
        backButton.setBounds(15, 18, 67, 43);
        backButton.setFont(f);
        c.add(backButton);

        loginButton.addActionListener(this);
        clearButton.addActionListener(this);
        backButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            HomePage homePage = new HomePage();
            homePage.setVisible(true);
            this.dispose();
        } else if (e.getSource() == loginButton) {
            String enteredUsername = tf.getText();
            String enteredPassword = new String(pf.getPassword());

            if (isValidCredentials(enteredUsername, enteredPassword)) {
                JOptionPane.showMessageDialog(this, "Login successful", "Success", JOptionPane.INFORMATION_MESSAGE);
                AdminPortal adminPortal = new AdminPortal();
                adminPortal.setVisible(true);
                this.dispose();
				
            } else {
				
                JOptionPane.showMessageDialog(this, "Login failed", "Error", JOptionPane.ERROR_MESSAGE);
            }
			
        } else if (e.getSource() == clearButton) {
            tf.setText("");
            pf.setText("");
        }
    }

    private boolean isValidCredentials(String enteredUsername, String enteredPassword) {
        return adminName.equals(enteredUsername) && adminPass.equals(enteredPassword);
    }

    private class ButtonMouseListener extends MouseAdapter {
        private JButton button;
        private Color originalColor;
        private Color hoverColor;

        public ButtonMouseListener(JButton button, Color originalColor, Color hoverColor) {
            this.button = button;
            this.originalColor = originalColor;
            this.hoverColor = hoverColor;
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            button.setBackground(hoverColor);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            button.setBackground(originalColor);
        }
    }
}