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


public class ReportGeneration extends JFrame implements ActionListener {
	private JTable table;
	private JScrollPane scroll;
	private DefaultTableModel model;
    private Container c;
    private JLabel titleLabel, usernameLabel, passwordLabel, cgpaLabel, semesterLabel, courseLabel, creditLabel;
    private JTextField usernameTf, passwordTf, cgpaTf, semesterTf, courseTf, creditTf;
    private JButton backButton, addButton, updateButton, deleteButton, clearButton;
	
	private String[] columns = {"Username", "Password", "CGPA", "Semester", "Course Enrolled", "Credit Enrolled"};
	private String[] rows = new String[6];
	private String reportDataFile = "report_data.txt";
	private String credentialsFile = "credentials_data.txt"; 

    ReportGeneration() {
        initComponents();
		loadReportData();
    }

    public void initComponents() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(950, 600);
        this.setLocationRelativeTo(null);
        this.setTitle("Report Generation");

        c = this.getContentPane();
        c.setLayout(null);
        c.setBackground(Color.DARK_GRAY);

        Font font = new Font("Times New Roman", Font.BOLD, 16);

        titleLabel = new JLabel("Report Generation");
        titleLabel.setFont(font);
        titleLabel.setBounds(400, 20, 500, 30);
		titleLabel.setForeground(Color.white);
        c.add(titleLabel);

        usernameLabel = new JLabel("Username");
        usernameLabel.setBounds(10, 90, 150, 30);
        usernameLabel.setFont(font);
		usernameLabel.setForeground(Color.white);
        c.add(usernameLabel);

        usernameTf = new JTextField();
        usernameTf.setBounds(120, 90, 190, 30);
        usernameTf.setFont(font);
        c.add(usernameTf);
		
		passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(10, 140, 150, 30);
        passwordLabel.setFont(font);
		passwordLabel.setForeground(Color.white);
        c.add(passwordLabel);

        passwordTf = new JTextField();
        passwordTf.setBounds(120, 140, 190, 30);
        passwordTf.setFont(font);
        c.add(passwordTf);
		
		cgpaLabel = new JLabel("CGPA");
        cgpaLabel.setBounds(10, 190, 150, 30);
        cgpaLabel.setFont(font);
		cgpaLabel.setForeground(Color.white);
        c.add(cgpaLabel);

        cgpaTf = new JTextField();
        cgpaTf.setBounds(120, 190, 190, 30);
        cgpaTf.setFont(font);
        c.add(cgpaTf);
		
		semesterLabel = new JLabel("Semester");
        semesterLabel.setBounds(10, 240, 150, 30);
        semesterLabel.setFont(font);
		semesterLabel.setForeground(Color.white);
        c.add(semesterLabel);

        semesterTf = new JTextField();
        semesterTf.setBounds(120, 240, 190, 30);
        semesterTf.setFont(font);
        c.add(semesterTf);
		
		courseLabel = new JLabel("Course Enrolled");
        courseLabel.setBounds(10, 290, 150, 30);
        courseLabel.setFont(font);
		courseLabel.setForeground(Color.white);
        c.add(courseLabel);
		
		courseTf = new JTextField();
        courseTf.setBounds(120, 290, 190, 30);
        courseTf.setFont(font);
        c.add(courseTf);
		
		creditLabel = new JLabel("Credit Enrolled");
        creditLabel.setBounds(10, 340, 150, 30);
        creditLabel.setFont(font);
		creditLabel.setForeground(Color.white);
        c.add(creditLabel);
		
		creditTf = new JTextField();
        creditTf.setBounds(120, 340, 190, 30);
        creditTf.setFont(font);
        c.add(creditTf);
		
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

        clearButton = new JButton("Clear   ");
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
		scroll.setBounds(335, 70, 595, 400);
		c.add(scroll);	
		
		backButton.addActionListener(this);
		addButton.addActionListener(this);
		clearButton.addActionListener(this);
		deleteButton.addActionListener(this);
		updateButton.addActionListener(this);
		
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				int numberofRow = table.getSelectedRow();
				String username = model.getValueAt(numberofRow, 0).toString();
				String password = model.getValueAt(numberofRow, 1).toString();
				String cgpa = model.getValueAt(numberofRow, 2).toString();
				String semester = model.getValueAt(numberofRow, 3).toString();
				String course = model.getValueAt(numberofRow, 4).toString();
				String credit = model.getValueAt(numberofRow, 5).toString();
				
				usernameTf.setText(username);
				passwordTf.setText(password);
				cgpaTf.setText(cgpa);
				semesterTf.setText(semester);
				courseTf.setText(course);
				creditTf.setText(credit);
			}				
		});		
    }
	
	private void loadReportData() {
        try (BufferedReader br = new BufferedReader(new FileReader(reportDataFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                model.addRow(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	private void saveReportData() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(reportDataFile))) {
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
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(reportDataFile, true))) {
            String data = String.format("%s,%s,%s,%s,%s,%s%n",
            usernameTf.getText() +
            "," +
            passwordTf.getText() +
            "," +
            cgpaTf.getText() +
            "," +
            semesterTf.getText() +
            "," +
            courseTf.getText() +
            "," +
            creditTf.getText());

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
    studentLogin.setVisible(true);
    this.dispose();
}

     public void setCredentials(String username, String password) {
    usernameTf.setText(username);
    passwordTf.setText(password);
}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == backButton) {
            AdminPortal adminPortal = new AdminPortal();
            adminPortal.setVisible(true);
            this.dispose();
        }
		
		else if(e.getSource()==addButton) {
			rows[0] = usernameTf.getText();
			rows[1] = passwordTf.getText();
			rows[2] = cgpaTf.getText();
			rows[3] = semesterTf.getText();
			rows[4] = courseTf.getText();
			rows[5] = creditTf.getText();
            model.addRow(rows);
			saveReportData();
			saveDataToFile();
			
			openStudentLogin(usernameTf.getText(), passwordTf.getText());
		}
		
		else if(e.getSource()==clearButton){
			usernameTf.setText("");
			passwordTf.setText("");
			cgpaTf.setText("");
			semesterTf.setText("");
			courseTf.setText("");
			creditTf.setText("");
		}	
		
		else if (e.getSource() == deleteButton) {
    int numberofRow = table.getSelectedRow();
    if (numberofRow >= 0) {
        model.removeRow(numberofRow);
        saveReportData();
        model.fireTableDataChanged();
    } else {
        JOptionPane.showMessageDialog(null, "No row has been selected");
    }
}
		
		else if(e.getSource()==updateButton) {
			int numberofRow = table.getSelectedRow();
			String username = usernameTf.getText();
			String password = passwordTf.getText();
			String cgpa = cgpaTf.getText();
			String semester = semesterTf.getText();
			String course = courseTf.getText();
			String credit = creditTf.getText();
			
			model.setValueAt(username, numberofRow, 0);
			model.setValueAt(password, numberofRow, 1);
			model.setValueAt(cgpa, numberofRow, 2);
			model.setValueAt(semester, numberofRow, 3);
			model.setValueAt(course, numberofRow, 4);
            model.setValueAt(credit, numberofRow, 5);			
		}	
	}
}
