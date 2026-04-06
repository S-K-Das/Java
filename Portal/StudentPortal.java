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

public class StudentPortal extends JFrame implements ActionListener {
    private Container c;
    private JLabel titleLabel;
    private JButton viewProfileButton, viewReportButton, backButton;
	private String studentUsername; 

    public StudentPortal() {
        initComponents();
    }

    public void initComponents() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(950, 600);
        this.setLocationRelativeTo(null);
        this.setTitle("Student Portal");

        c = this.getContentPane();
        c.setLayout(null);
        c.setBackground(Color.DARK_GRAY);

        Font font = new Font("Times New Roman", Font.BOLD, 25);

        titleLabel = new JLabel("Student Portal");
        titleLabel.setFont(font);
        titleLabel.setBounds(395, 50, 370, 60);
		titleLabel.setForeground(Color.white);
        c.add(titleLabel);

        viewProfileButton = new JButton("View Profile");
        viewProfileButton.setBounds(290, 250, 370, 60);
        viewProfileButton.setFont(font);
        viewProfileButton.setFocusPainted(false);
		viewProfileButton.setBackground(Color.CYAN);
		viewProfileButton.setForeground(Color.WHITE);
		viewProfileButton.addMouseListener(new ButtonMouseListener(viewProfileButton, Color.blue, Color.cyan));
        c.add(viewProfileButton);

        viewReportButton = new JButton("View Report");
        viewReportButton.setBounds(290, 370, 370, 60);
        viewReportButton.setFont(font);
        viewReportButton.setFocusPainted(false);
		viewReportButton.setBackground(Color.CYAN);
		viewReportButton.setForeground(Color.WHITE);
		viewReportButton.addMouseListener(new ButtonMouseListener(viewReportButton, Color.blue, Color.cyan));
        c.add(viewReportButton);

        backButton = new JButton("<-");
        backButton.setBounds(6, 18, 67, 43);
        backButton.setFont(font);
        c.add(backButton);

        viewProfileButton.addActionListener(this);
        viewReportButton.addActionListener(this);
        backButton.addActionListener(this);
    }

    public void setStudentUsername(String username) {
        this.studentUsername = username;
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            StudentLogin studentPage = new StudentLogin();
            studentPage.setVisible(true);
            this.dispose();
        } else if (e.getSource() == viewProfileButton) {
            ViewProfile viewProfileFrame = new ViewProfile();
            viewProfileFrame.setVisible(true);
            this.dispose();
        } else if (e.getSource() == viewReportButton) {
            ViewReport viewReportFrame = new ViewReport();
            viewReportFrame.setVisible(true);
            this.dispose();
        }
    }

    public static void main(String[] args) {
        StudentPortal studentPortal = new StudentPortal();
        studentPortal.setVisible(true);
    }
}



