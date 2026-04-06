package Portal;
import Login.*;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JOptionPane;

public class ViewProfile extends JFrame implements ActionListener {
    private Container c;
    private JLabel titleLabel, firstNameLabel, lastNameLabel, emailLabel, contactLabel, usernameLabel, passwordLabel;
    private JTextField firstNameTf, lastNameTf, emailTf, contactTf, usernameTf, passwordTf;
	private JButton backButton;

    public ViewProfile() {
        initComponents();
        loadDataFromFile();
    }

     public void initComponents() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(950, 600);
        this.setLocationRelativeTo(null);
        this.setTitle("View Profile");

        c = this.getContentPane();
        c.setLayout(null);
        c.setBackground(Color.DARK_GRAY);

        Font font = new Font("Times New Roman", Font.BOLD, 16);

        titleLabel = new JLabel("View Profile");
        titleLabel.setFont(font);
        titleLabel.setBounds(410, 20, 500, 30);
		titleLabel.setForeground(Color.white);
        c.add(titleLabel);

        firstNameLabel = new JLabel("First Name");
        firstNameLabel.setBounds(300, 90, 150, 30);
        firstNameLabel.setFont(font);
		firstNameLabel.setForeground(Color.white);
        c.add(firstNameLabel);

        firstNameTf = new JTextField();
        firstNameTf.setBounds(405, 90, 210, 30);
        firstNameTf.setFont(font);
        c.add(firstNameTf);
		
		lastNameLabel = new JLabel("Last Name");
        lastNameLabel.setBounds(300, 140, 150, 30);
        lastNameLabel.setFont(font);
		lastNameLabel.setForeground(Color.white);
        c.add(lastNameLabel);

        lastNameTf = new JTextField();
        lastNameTf.setBounds(405, 140, 210, 30);
        lastNameTf.setFont(font);
        c.add(lastNameTf);
		
		emailLabel = new JLabel("Email");
        emailLabel.setBounds(300, 190, 150, 30);
        emailLabel.setFont(font);
		emailLabel.setForeground(Color.white);
        c.add(emailLabel);

        emailTf = new JTextField();
        emailTf.setBounds(405, 190, 210, 30);
        emailTf.setFont(font);
        c.add(emailTf);
		
		contactLabel = new JLabel("Contact No");
        contactLabel.setBounds(300, 240, 150, 30);
        contactLabel.setFont(font);
		contactLabel.setForeground(Color.white);
        c.add(contactLabel);
		
		contactTf = new JTextField();
        contactTf.setBounds(405, 240, 210, 30);
        contactTf.setFont(font);
        c.add(contactTf);
		
		usernameLabel = new JLabel("Username");
        usernameLabel.setBounds(300, 290, 150, 30);
        usernameLabel.setFont(font);
		usernameLabel.setForeground(Color.white);
        c.add(usernameLabel);
		
		usernameTf = new JTextField();
        usernameTf.setBounds(405, 290, 210, 30);
        usernameTf.setFont(font);
        c.add(usernameTf);
		
		passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(300, 340, 150, 30);
        passwordLabel.setFont(font);
		passwordLabel.setForeground(Color.white);
        c.add(passwordLabel);
		
		passwordTf = new JTextField();
        passwordTf.setBounds(405, 340, 210, 30);
        passwordTf.setFont(font);
        c.add(passwordTf);

		backButton = new JButton("<-");
        backButton.setBounds(15, 18, 67, 43);
        backButton.setFont(font);
        c.add(backButton);
		
		backButton.addActionListener(this);
	}

    private void loadDataFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("student_data.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 6) {
                    firstNameTf.setText(data[0]);
                    lastNameTf.setText(data[1]);
                    emailTf.setText(data[2]);
                    contactTf.setText(data[3]);
                    usernameTf.setText(data[4]);
                    passwordTf.setText(data[5]);
                }
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error loading data", "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    public void actionPerformed(ActionEvent e) {
		if(e.getSource() == backButton) {
            StudentPortal studentPortal = new StudentPortal();
            studentPortal.setVisible(true);
            this.dispose();
        }
  	}
}


