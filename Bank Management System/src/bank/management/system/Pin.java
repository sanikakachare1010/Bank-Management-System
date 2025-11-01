package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Pin extends JFrame implements ActionListener {

    JButton b1, b2;
    JPasswordField p1, p2;
    String pin;

    Pin(String pin) {
        this.pin = pin;

        // Create a panel that paints the background image
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("icon/atm2.png"));
                Image img = icon.getImage();
                g.drawImage(img, 0, 0, getWidth(), getHeight(), this); // full fit
            }
        };
        backgroundPanel.setLayout(null);
        setContentPane(backgroundPanel);

        // --- UI Components ---
        JLabel label1 = new JLabel("CHANGE YOUR PIN");
        label1.setFont(new Font("System", Font.BOLD, 18));
        label1.setForeground(Color.WHITE);
        label1.setBounds(450, 150, 500, 35);
        backgroundPanel.add(label1);

        JLabel label2 = new JLabel("NEW PIN :");
        label2.setFont(new Font("System", Font.BOLD, 16));
        label2.setForeground(Color.WHITE);
        label2.setBounds(400, 200, 150, 35);
        backgroundPanel.add(label2);

        p1 = new JPasswordField();
        p1.setBackground(new Color(65, 125, 128));
        p1.setForeground(Color.WHITE);
        p1.setFont(new Font("Raleway", Font.BOLD, 22));
        p1.setBounds(500, 205, 180, 25);
        backgroundPanel.add(p1);

        JLabel label3 = new JLabel("Re-Enter NEW PIN :");
        label3.setFont(new Font("System", Font.BOLD, 16));
        label3.setForeground(Color.WHITE);
        label3.setBounds(400, 250, 200, 35);
        backgroundPanel.add(label3);

        p2 = new JPasswordField();
        p2.setBackground(new Color(65, 125, 128));
        p2.setForeground(Color.WHITE);
        p2.setFont(new Font("Raleway", Font.BOLD, 22));
        p2.setBounds(560, 250, 180, 25);
        backgroundPanel.add(p2);

        b1 = new JButton("CHANGE");
        b1.setBackground(new Color(65, 125, 128));
        b1.setForeground(Color.WHITE);
        b1.setBounds(600, 300, 150, 35);
        b1.addActionListener(this);
        backgroundPanel.add(b1);

        b2 = new JButton("BACK");
        b2.setBackground(new Color(65, 125, 128));
        b2.setForeground(Color.WHITE);
        b2.setBounds(600, 350, 150, 35);
        b2.addActionListener(this);
        backgroundPanel.add(b2);

        // --- Frame setup ---
        setExtendedState(JFrame.MAXIMIZED_BOTH); // fullscreen
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String pin1 = p1.getText();
            String pin2 = p2.getText();

            if (!pin1.equals(pin2)) {
                JOptionPane.showMessageDialog(null, "Entered PINs do not match");
                return;
            }

            if (e.getSource() == b1) {
                if (p1.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Enter New PIN");
                    return;
                }
                if (p2.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Re-Enter New PIN");
                    return;
                }

                Con c = new Con();
                String q1 = "update bank set pin = '" + pin1 + "' where pin = '" + pin + "'";
                String q2 = "update login set pin = '" + pin1 + "' where pin = '" + pin + "'";
                String q3 = "update signupthree set pin = '" + pin1 + "' where pin = '" + pin + "'";

                c.statement.executeUpdate(q1);
                c.statement.executeUpdate(q2);
                c.statement.executeUpdate(q3);

                JOptionPane.showMessageDialog(null, "PIN Changed Successfully");
                setVisible(false);
                new main_Class(pin);

            } else if (e.getSource() == b2) {
                new main_Class(pin);
                setVisible(false);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Pin("");
    }
}
