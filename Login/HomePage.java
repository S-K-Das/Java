package Login;
import Portal.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HomePage extends JFrame implements ActionListener, MouseListener {

    private JButton ADMIN;
    private JButton STUDENT;
    private JLabel LoginPortal_Label;
    private JLabel jLabel1;
    private JSeparator jSeparator1;
    private JLabel BackgroundImage;

    public HomePage() {
        initComponents();
    }

    public void initComponents() {
        ADMIN = new JButton();
        STUDENT = new JButton();
        LoginPortal_Label = new JLabel();
        jLabel1 = new JLabel();
        jSeparator1 = new JSeparator();
        BackgroundImage = new JLabel();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(null);

        ADMIN.setFont(new Font("Times New Roman", Font.BOLD, 22));
        ADMIN.setText("ADMIN");
        //ADMIN.addActionListener(this);
        ADMIN.addMouseListener(this);
        getContentPane().add(ADMIN);
        ADMIN.setBounds(320, 310, 290, 70);
        ADMIN.setBackground(Color.CYAN);
        ADMIN.setForeground(Color.WHITE);

        STUDENT.setFont(new Font("Times New Roman", Font.BOLD, 22));
        STUDENT.setText("STUDENT");
        STUDENT.addActionListener(this);
        STUDENT.addMouseListener(this);
        getContentPane().add(STUDENT);
        STUDENT.setBounds(320, 400, 290, 70);
        STUDENT.setBackground(Color.CYAN);
        STUDENT.setForeground(Color.WHITE);

        LoginPortal_Label.setFont(new Font("Times New Roman", Font.BOLD, 40));
        LoginPortal_Label.setForeground(new Color(255, 255, 255));
        LoginPortal_Label.setText("Login Portal");
        getContentPane().add(LoginPortal_Label);
        LoginPortal_Label.setBounds(365, 70, 250, 50);

        jLabel1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        jLabel1.setForeground(new Color(255, 255, 255));
        jLabel1.setText("Make an Introduction!");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(380, 200, 200, 40);
        getContentPane().add(jSeparator1);
        jSeparator1.setBounds(250, 150, 430, 20);

        BackgroundImage.setBackground(Color.DARK_GRAY);
        BackgroundImage.setOpaque(true);
        getContentPane().add(BackgroundImage);
        BackgroundImage.setBounds(0, 0, 950, 600);
		ADMIN.addActionListener(this);


        setSize(new Dimension(950, 600));
        setLocationRelativeTo(null);
    }

    public void mouseClicked(MouseEvent me) {
    }

    public void mousePressed(MouseEvent me) {
    }

    public void mouseReleased(MouseEvent me) {
    }


    public void mouseEntered(MouseEvent me) {
        if (me.getSource() == ADMIN) {
            ADMIN.setBackground(Color.BLUE);
        } else if (me.getSource() == STUDENT) {
            STUDENT.setBackground(Color.BLUE);
        }
    }

    public void mouseExited(MouseEvent me) {
        if (me.getSource() == ADMIN) {
            ADMIN.setBackground(Color.CYAN);
        } else if (me.getSource() == STUDENT) {
            STUDENT.setBackground(Color.CYAN);
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == ADMIN) {
            AdminLogin page = new AdminLogin();
            page.setVisible(true);
			setVisible(false);

        } else if (e.getSource() == STUDENT) {
            StudentLogin studentLogin = new StudentLogin();
            studentLogin.setVisible(true);
			setVisible(false);
        }
    }
}