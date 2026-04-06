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

public class ViewReport extends JFrame implements ActionListener {
	private Container c;
    private JLabel titleLabel, usernameLabel, passwordLabel, cgpaLabel, semesterLabel, courseLabel, creditLabel;
    private JTextField usernameTf, passwordTf, cgpaTf, semesterTf, courseTf, creditTf;
	private JButton backButton;
	
	ViewReport() {
        initComponents();
		loadDataFromFile();
    }
	
	public void initComponents() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(950, 600);
        this.setLocationRelativeTo(null);
        this.setTitle("View Report");
		
		c = this.getContentPane();
        c.setLayout(null);
        c.setBackground(Color.DARK_GRAY);
		
		Font font = new Font("Times New Roman", Font.BOLD, 16);
		
		titleLabel = new JLabel("View Report");
        titleLabel.setFont(font);
        titleLabel.setBounds(410, 20, 500, 30);
		titleLabel.setForeground(Color.white);
        c.add(titleLabel);
		
		usernameLabel = new JLabel("Username");
        usernameLabel.setBounds(300, 90, 150, 30);
        usernameLabel.setFont(font);
		usernameLabel.setForeground(Color.white);
        c.add(usernameLabel);

        usernameTf = new JTextField();
        usernameTf.setBounds(425, 90, 210, 30);
        usernameTf.setFont(font);
        c.add(usernameTf);
		
		passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(300, 140, 150, 30);
        passwordLabel.setFont(font);
		passwordLabel.setForeground(Color.white);
        c.add(passwordLabel);

        passwordTf = new JTextField();
        passwordTf.setBounds(425, 140, 210, 30);
        passwordTf.setFont(font);
        c.add(passwordTf);
		
		cgpaLabel = new JLabel("CGPA");
        cgpaLabel.setBounds(300, 190, 150, 30);
        cgpaLabel.setFont(font);
		cgpaLabel.setForeground(Color.white);
        c.add(cgpaLabel);

        cgpaTf = new JTextField();
        cgpaTf.setBounds(425, 190, 210, 30);
        cgpaTf.setFont(font);
        c.add(cgpaTf);
		
		semesterLabel = new JLabel("Semester");
        semesterLabel.setBounds(300, 240, 150, 30);
        semesterLabel.setFont(font);
		semesterLabel.setForeground(Color.white);
        c.add(semesterLabel);

        semesterTf = new JTextField();
        semesterTf.setBounds(425, 240, 210, 30);
        semesterTf.setFont(font);
        c.add(semesterTf);
		
		courseLabel = new JLabel("Course Enrolled");
        courseLabel.setBounds(300, 290, 150, 30);
        courseLabel.setFont(font);
		courseLabel.setForeground(Color.white);
        c.add(courseLabel);
		
		courseTf = new JTextField();
        courseTf.setBounds(425, 290, 210, 30);
        courseTf.setFont(font);
        c.add(courseTf);
		
		creditLabel = new JLabel("Credit Enrolled");
        creditLabel.setBounds(300, 340, 150, 30);
        creditLabel.setFont(font);
		creditLabel.setForeground(Color.white);
        c.add(creditLabel);
		
		creditTf = new JTextField();
        creditTf.setBounds(425, 340, 210, 30);
        creditTf.setFont(font);
        c.add(creditTf);
		
		backButton = new JButton("<-");
        backButton.setBounds(15, 18, 67, 43);
        backButton.setFont(font);
        c.add(backButton);
		
		backButton.addActionListener(this);
	}
	
	 private void loadDataFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("report_data.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 6) {
                    usernameTf.setText(data[0]);
                    passwordTf.setText(data[1]);
                    cgpaTf.setText(data[2]);
                    semesterTf.setText(data[3]);
                    courseTf.setText(data[4]);
                    creditTf.setText(data[5]);
					
                }
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error loading data", "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == backButton) {
            StudentPortal studentPortal = new StudentPortal();
            studentPortal.setVisible(true);
            this.dispose();
        }
  	}
}	