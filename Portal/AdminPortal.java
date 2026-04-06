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

public class AdminPortal extends JFrame implements ActionListener {
    private Container c;
    private JLabel titleLabel;
    private JButton studInfoButton, reportGenButton, backButton;

    public AdminPortal() {
        initComponents();
    }

    public void initComponents() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(950, 600);
        this.setLocationRelativeTo(null);
        this.setTitle("Admin Portal");

        c = this.getContentPane();
        c.setLayout(null);
        c.setBackground(Color.DARK_GRAY);

        Font font = new Font("Times New Roman", Font.BOLD, 25);

        titleLabel = new JLabel("Admin Portal");
        titleLabel.setFont(font);
        titleLabel.setBounds(395, 50, 370, 60);
		titleLabel.setForeground(Color.white);
        c.add(titleLabel);

        studInfoButton = new JButton("Student Information");
        studInfoButton.setBounds(290, 250, 370, 60);
        studInfoButton.setFont(font);
        studInfoButton.setFocusPainted(false);
		studInfoButton.setBackground(Color.CYAN);
		studInfoButton.setForeground(Color.WHITE);
		studInfoButton.addMouseListener(new ButtonMouseListener(studInfoButton, Color.blue, Color.cyan));
        c.add(studInfoButton);

        reportGenButton = new JButton("Report Generation");
        reportGenButton.setBounds(290, 370, 370, 60);
        reportGenButton.setFont(font);
        reportGenButton.setFocusPainted(false);
		reportGenButton.setBackground(Color.CYAN);
		reportGenButton.setForeground(Color.WHITE);
		reportGenButton.addMouseListener(new ButtonMouseListener(reportGenButton, Color.blue, Color.cyan));
        c.add(reportGenButton);

        backButton = new JButton("<-");
        backButton.setBounds(15, 18, 67, 43);
        backButton.setFont(font);
        c.add(backButton);

        studInfoButton.addActionListener(this);
        reportGenButton.addActionListener(this);
        backButton.addActionListener(this);
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
            AdminLogin adminPage = new AdminLogin();
            adminPage.setVisible(true);
            this.dispose();
        } else if (e.getSource() == studInfoButton) {
            StudentInformation studInfoFrame = new StudentInformation();
            studInfoFrame.setVisible(true);
            this.dispose();
        } else if (e.getSource() == reportGenButton) {
            ReportGeneration reportGenFrame = new ReportGeneration();
            reportGenFrame.setVisible(true);
            this.dispose();
        }
    }
}


