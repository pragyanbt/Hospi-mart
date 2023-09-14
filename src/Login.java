import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login implements ActionListener {
    JFrame log_frame;
    JLabel username;
    JTextField txt_username;
    JPasswordField txt_password;
    JButton btn_login, btn_register, btn_admin;
    Font fon1;

    public Login(){
        log_frame = new JFrame("Login");
        log_frame.setSize(610,535);
        log_frame.setLayout(null);
        log_frame.setVisible(true);

        fon1=new Font("algerian",Font.BOLD,50);

        // Adding Background Image
        JLabel bg = new JLabel(new ImageIcon(getClass().getResource("Login.png")));
        bg.setBounds(0,0,600,500);
        log_frame.add(bg);

        // Username label
        username = new JLabel("Username: ");
        username.setBounds(50,50,100,20);
        log_frame.add(username);

        // Username textfield
        txt_username = new JTextField();
        txt_username.setBounds(280,195,280,32);
        txt_username.setBackground(Color.WHITE);
        txt_username.setForeground(Color.BLACK);
        txt_username.setBorder(null);
        bg.add(txt_username);


        // Password to be entered here
        txt_password = new JPasswordField();
        txt_password.setBounds(280,275,280,32);
        txt_password.setBackground(Color.WHITE);
        txt_password.setForeground(Color.BLACK);
        txt_password.setBorder(null);
        bg.add(txt_password);


        // Buttons here
        btn_login = new JButton("Login");
        btn_login.setBounds(370,343,100,26);
        btn_login.setBackground(Color.WHITE);
        btn_login.setForeground(Color.BLACK);
        btn_login.setBorder(null);
        btn_login.addActionListener(this);
        bg.add(btn_login);

        btn_register = new JButton("Sign up");
        btn_register.setBounds(420,393,80,26);
        btn_register.setBackground(Color.decode("#09aeb6"));
        btn_register.setForeground(Color.WHITE);
        btn_register.setBorder(null);
        btn_register.addActionListener(this);
        bg.add(btn_register);

        btn_admin = new JButton("Login as admin");
        btn_admin.setBounds(370,415,100,26);
        btn_admin.setBackground(Color.decode("#09aeb6"));
        btn_admin.setForeground(Color.WHITE);
        btn_admin.setBorder(null);
        btn_admin.addActionListener(this);
        bg.add(btn_admin);




    }

    public static void main(String[] args) {
        new Login();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String name = txt_username.getText();
        String pass = txt_password.getText();

        if (e.getSource()==btn_register){
            log_frame.dispose();
            new Registration();
        }

        if (e.getSource()==btn_login){
            String query = "select * from register where username='"+name+"' and pass='"+pass+"'";
            DatabaseOperation db = new DatabaseOperation();
            ResultSet rs = db.select(query);
            try {
                if (rs.next()){
                    JOptionPane.showMessageDialog(log_frame,"Login Successful");
                    new UserDashboard(name);
                    log_frame.dispose();
                }
                else {
                    JOptionPane.showMessageDialog(log_frame,"Invalid Login Credentials");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        if (e.getSource()==btn_admin){
            String query = "select * from admin where adminName='"+name+"' and adminPass='"+pass+"'";
            DatabaseOperation ad = new DatabaseOperation();
            ResultSet rs = ad.select(query);
            try {
                if (rs.next()){
                    JOptionPane.showMessageDialog(log_frame,"Login Successful as admin");
                    new Dashboard();
                    log_frame.dispose();
                }
                else {
                    JOptionPane.showMessageDialog(log_frame,"Invalid Login Credentials");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        }

    }

}
