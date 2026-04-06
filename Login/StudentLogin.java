package Login;
import Portal.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JOptionPane;

public class StudentLogin extends JFrame implements ActionListener {

    private JLabel userLabel, passLabel;
    private JTextField tf;
    private JPasswordField pf;
    private JButton loginButton, clearButton, backButton;
    private Container c;
    private Font f;
	private String loginDataFile = "login_data.txt";
    private String credentialsFile = "credentials_data.txt"; 

    public StudentLogin() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(950, 600);
        this.setLocationRelativeTo(null);
        this.setTitle(" Student Login ");

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
	
	public void setCredentials(String username, String password) {
        tf.setText(username);
        pf.setText(password);
    }

    @Override
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
                StudentPortal studentPortal = new StudentPortal();
                studentPortal.setVisible(true);
                this.dispose();
				
            } else {
				
                JOptionPane.showMessageDialog(this, "Login failed", "Error", JOptionPane.ERROR_MESSAGE);
            }
        this.dispose();
    }
	}
  private boolean isValidCredentials(String enteredUsername, String enteredPassword) {
    return isValidCredentialsFromFile(enteredUsername, enteredPassword);
}

private boolean isValidCredentialsFromFile(String enteredUsername, String enteredPassword) {
   try (BufferedReader br = new BufferedReader(new FileReader(credentialsFile))) {
        String line;
        while ((line = br.readLine()) != null) {
            String[] credentials = line.split(",");
            String username = credentials[0];
            String password = credentials[1];

            if (enteredUsername.equals(username) && enteredPassword.equals(password)) {
                return true;
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
    return false;
}
    private void openStudentPortal(String username) {
        StudentPortal studentPortal = new StudentPortal();
        studentPortal.setStudentUsername(username);
        studentPortal.setVisible(true);
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