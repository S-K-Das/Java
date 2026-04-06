package Portal;
import Login.*;

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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;


public class StudentInformation extends JFrame implements ActionListener {
    private JTable table;
    private JScrollPane scroll;
    private DefaultTableModel model;
    private Container c;
    private JLabel titleLabel, firstNameLabel, lastNameLabel, emailLabel, contactLabel, usernameLabel, passwordLabel;
    private JTextField firstNameTf, lastNameTf, emailTf, contactTf, usernameTf, passwordTf;
    private JButton backButton, addButton, updateButton, deleteButton, clearButton;
    private String[] columns = {"First name", "Last name", "Email", "Contact No", "Username", "Password"};
    private String[] rows = new String[6];
    private String studentDataFile = "student_data.txt";
    private String credentialsFile = "credentials_data.txt"; 


    public StudentInformation() {
        initComponents();
        loadStudentData();
    }

    public void initComponents() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(950, 600);
        this.setLocationRelativeTo(null);
        this.setTitle("Student Information");

        c = this.getContentPane();
        c.setLayout(null);
        c.setBackground(Color.DARK_GRAY);

        Font font = new Font("Times New Roman", Font.BOLD, 16);

        titleLabel = new JLabel("Student Information");
        titleLabel.setFont(font);
        titleLabel.setBounds(400, 20, 500, 30);
		titleLabel.setForeground(Color.white);
        c.add(titleLabel);

        firstNameLabel = new JLabel("First Name");
        firstNameLabel.setBounds(10, 90, 150, 30);
        firstNameLabel.setFont(font);
		firstNameLabel.setForeground(Color.white);
        c.add(firstNameLabel);

        firstNameTf = new JTextField();
        firstNameTf.setBounds(115, 90, 190, 30);
        firstNameTf.setFont(font);
        c.add(firstNameTf);
		
		lastNameLabel = new JLabel("Last Name");
        lastNameLabel.setBounds(10, 140, 150, 30);
        lastNameLabel.setFont(font);
		lastNameLabel.setForeground(Color.white);
        c.add(lastNameLabel);

        lastNameTf = new JTextField();
        lastNameTf.setBounds(115, 140, 190, 30);
        lastNameTf.setFont(font);
        c.add(lastNameTf);
		
		emailLabel = new JLabel("Email");
        emailLabel.setBounds(10, 190, 150, 30);
        emailLabel.setFont(font);
		emailLabel.setForeground(Color.white);
        c.add(emailLabel);

        emailTf = new JTextField();
        emailTf.setBounds(115, 190, 190, 30);
        emailTf.setFont(font);
        c.add(emailTf);
		
		contactLabel = new JLabel("Contact No");
        contactLabel.setBounds(10, 240, 150, 30);
        contactLabel.setFont(font);
		contactLabel.setForeground(Color.white);
        c.add(contactLabel);
		
		contactTf = new JTextField();
        contactTf.setBounds(115, 240, 190, 30);
        contactTf.setFont(font);
        c.add(contactTf);
		
		usernameLabel = new JLabel("Username");
        usernameLabel.setBounds(10, 290, 150, 30);
        usernameLabel.setFont(font);
		usernameLabel.setForeground(Color.white);
        c.add(usernameLabel);
		
		usernameTf = new JTextField();
        usernameTf.setBounds(115, 290, 190, 30);
        usernameTf.setFont(font);
        c.add(usernameTf);
		
		passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(10, 340, 150, 30);
        passwordLabel.setFont(font);
		passwordLabel.setForeground(Color.white);
        c.add(passwordLabel);
		
		passwordTf = new JTextField();
        passwordTf.setBounds(115, 340, 190, 30);
        passwordTf.setFont(font);
        c.add(passwordTf);

		backButton = new JButton("<-");
        backButton.setBounds(15, 18, 67, 43);
        backButton.setFont(font);
        c.add(backButton);

        addButton = new JButton("Add");
        addButton.setBounds(355, 500, 100, 30);
        addButton.setFont(font);
        c.add(addButton);

        updateButton = new JButton("Update");
        updateButton.setBounds(505, 500, 100, 30);
        updateButton.setFont(font);
        c.add(updateButton);

        deleteButton = new JButton("Delete");
        deleteButton.setBounds(655, 500, 100, 30);
        deleteButton.setFont(font);
        c.add(deleteButton);

        clearButton = new JButton("Clear");
        clearButton.setBounds(805, 500, 100, 30);
        clearButton.setFont(font);
        c.add(clearButton);
		
		table = new JTable();
		model = new DefaultTableModel();
		model.setColumnIdentifiers(columns);
		table.setModel(model);
		table.setFont(font);
		table.setSelectionBackground(Color.ORANGE);
		table.setBackground(Color.WHITE);
		table.setRowHeight(30);
		
        scroll = new JScrollPane(table);
		scroll.setBounds(320, 70, 610, 400);
		c.add(scroll);	
		
        backButton.addActionListener(this);
        addButton.addActionListener(this);
        clearButton.addActionListener(this);
        deleteButton.addActionListener(this);
        updateButton.addActionListener(this);

        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                int numberofRow = table.getSelectedRow();
				String firstName = model.getValueAt(numberofRow, 0).toString();
				String lastName = model.getValueAt(numberofRow, 1).toString();
				String email = model.getValueAt(numberofRow, 2).toString();
				String contact = model.getValueAt(numberofRow, 3).toString();
				String username = model.getValueAt(numberofRow, 4).toString();
				String password = model.getValueAt(numberofRow, 5).toString();
				
				firstNameTf.setText(firstName);
				lastNameTf.setText(lastName);
				emailTf.setText(email);
				contactTf.setText(contact);
				usernameTf.setText(username);
				passwordTf.setText(password);
            }
        });
    }

    private void loadStudentData() {
        try (BufferedReader br = new BufferedReader(new FileReader(studentDataFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                model.addRow(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveStudentData() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(studentDataFile))) {
            for (int i = 0; i < model.getRowCount(); i++) {
                for (int j = 0; j < model.getColumnCount(); j++) {
                    bw.write(model.getValueAt(i, j) + ",");
                }
                bw.write("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
   private void saveCredentialsToFile(String username, String password) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(credentialsFile, true))) {
            String data = String.format("%s,%s%n", username, password);
            writer.write(data);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void saveDataToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(studentDataFile, true))) {
            String data = String.format("%s,%s,%s,%s,%s,%s%n",
                    firstNameTf.getText(),
                    lastNameTf.getText(),
                    emailTf.getText(),
                    contactTf.getText(),
                    usernameTf.getText(),
                    passwordTf.getText());

            writer.write(data);
            JOptionPane.showMessageDialog(this, "Data saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error saving data", "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
        saveCredentialsToFile(usernameTf.getText(), passwordTf.getText());
    }

	
	private void openStudentLogin(String username, String password) {
    StudentLogin studentLogin = new StudentLogin();
    studentLogin.setCredentials(username, password);
    studentLogin.setVisible(false);
    this.dispose();
}

     public void setCredentials(String username, String password) {
    usernameTf.setText(username);
    passwordTf.setText(password);
}

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            AdminPortal adminPortal = new AdminPortal();
            adminPortal.setVisible(true);
            this.dispose();
        } else if (e.getSource() == addButton) {
            rows[0] = firstNameTf.getText();
            rows[1] = lastNameTf.getText();
            rows[2] = emailTf.getText();
            rows[3] = contactTf.getText();
            rows[4] = usernameTf.getText();
            rows[5] = passwordTf.getText();

            model.addRow(rows);
            saveStudentData();
            saveDataToFile();
            clearInputFields();
        } else if (e.getSource() == clearButton) {
            clearInputFields();
        } else if (e.getSource() == deleteButton) {
            int numberofRow = table.getSelectedRow();
            if (numberofRow >= 0) {
                model.removeRow(numberofRow);
                saveStudentData();
                model.fireTableDataChanged();
            } else {
                JOptionPane.showMessageDialog(null, "No row has been selected");
            }
        } else if (e.getSource() == updateButton) {
            int numberofRow = table.getSelectedRow();
            String firstName = firstNameTf.getText();
            String lastName = lastNameTf.getText();
            String email = emailTf.getText();
            String contact = contactTf.getText();
            String username = usernameTf.getText();
            String password = passwordTf.getText();

            model.setValueAt(firstName, numberofRow, 0);
            model.setValueAt(lastName, numberofRow, 1);
            model.setValueAt(email, numberofRow, 2);
            model.setValueAt(contact, numberofRow, 3);
            model.setValueAt(username, numberofRow, 4);
            model.setValueAt(password, numberofRow, 5);
        }
    }

     private void clearInputFields() {
        firstNameTf.setText("");
        lastNameTf.setText("");
        emailTf.setText("");
        contactTf.setText("");
        usernameTf.setText("");
        passwordTf.setText("");
    }
}