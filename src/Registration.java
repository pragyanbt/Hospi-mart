import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Registration implements ActionListener {
    JFrame reg_frame;
    JLabel gender;
    JTextField txt_username, txt_fullname, txt_email, txt_contact, txt_address;
    JRadioButton rd_male, rd_female;
    JPasswordField txt_pass, txt_cpass;
    JButton btn_register, btn_back;
    Font fon1;


    public Registration(){
        reg_frame = new JFrame("Registration Page");
        reg_frame.setSize(815,680);
        reg_frame.setLayout(null);
        reg_frame.setVisible(true);

        fon1=new Font("algerian",Font.BOLD,50);

        JLabel bg = new JLabel(new ImageIcon(getClass().getResource("Registration.png")));
        bg.setBounds(0,0,800,650);
        reg_frame.add(bg);

        //Username
        txt_username =new JTextField();
        txt_username.setBounds(425,114,330,45);
        txt_username.setBackground(Color.WHITE);
        txt_username.setForeground(Color.BLACK);
        txt_username.setBorder(null);
        bg.add(txt_username);

        //Fullname
        txt_fullname =new JTextField();
        txt_fullname.setBounds(425,173,330,45);
        txt_fullname.setBackground(Color.WHITE);
        txt_fullname.setForeground(Color.BLACK);
        txt_fullname.setBorder(null);
        bg.add(txt_fullname);

        // Email
        txt_email =new JTextField();
        txt_email.setBounds(425,233,330,45);
        txt_email.setBackground(Color.WHITE);
        txt_email.setForeground(Color.BLACK);
        txt_email.setBorder(null);
        bg.add(txt_email);

        // Gender label
        gender = new JLabel("Gender : ");
        gender.setBounds(30,200,300,20);
        reg_frame.add(gender);

        // Gender Male or Female
        rd_male = new JRadioButton("Male");
        rd_male.setBounds(425,290,60,30);
        rd_male.setSelected(true);
        rd_male.setBackground(Color.decode("#09aeb6"));
        rd_male.setForeground(Color.WHITE);
        bg.add(rd_male);

        rd_female = new JRadioButton("Female");
        rd_female.setBounds(525,290,70,30);
        rd_female.setBackground(Color.decode("#09aeb6"));
        rd_female.setForeground(Color.WHITE);
        bg.add(rd_female);

        // Grouping Male and Female

        ButtonGroup gen = new ButtonGroup();
        gen.add(rd_male);
        gen.add(rd_female);

        //Contact No
        txt_contact = new JTextField();
        txt_contact.setBounds(425,335,330,45);
        txt_contact.setBackground(Color.WHITE);
        txt_contact.setForeground(Color.BLACK);
        txt_contact.setBorder(null);
        bg.add(txt_contact);

        //Address
        txt_address = new JTextField();
        txt_address.setBounds(425,395,330,45);
        txt_address.setBackground(Color.WHITE);
        txt_address.setForeground(Color.BLACK);
        txt_address.setBorder(null);
        bg.add(txt_address);

        //Password
        txt_pass = new JPasswordField();
        txt_pass.setBounds(425,454,330,45);
        txt_pass.setBackground(Color.WHITE);
        txt_pass.setForeground(Color.BLACK);
        txt_pass.setBorder(null);
        bg.add(txt_pass);

        //Confirm Password
        txt_cpass = new JPasswordField();
        txt_cpass.setBounds(425,513,330,45);
        txt_cpass.setBackground(Color.WHITE);
        txt_cpass.setForeground(Color.BLACK);
        txt_cpass.setBorder(null);
        bg.add(txt_cpass);

        // Making a Registration Button

        btn_register = new JButton("Register");
        btn_register.setBounds(525,588,130,30);
        btn_register.setBackground(Color.WHITE);
        btn_register.setForeground(Color.BLACK);
        btn_register.setBorder(null);
        btn_register.addActionListener(this);
        bg.add(btn_register);

        btn_back = new JButton("Login Here");
        btn_back.setBounds(505,74,100,30);
        btn_back.setBackground(Color.decode("#09aeb6"));
        btn_back.setForeground(Color.WHITE);
        btn_back.setBorder(null);
        btn_back.addActionListener(this);
        bg.add(btn_back);




    }

    public static void main(String[] args) {
        new Registration();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String username = txt_username.getText();
        String name = txt_fullname.getText();
        String email = txt_email.getText();
        String gender = "Male";
        if (rd_female.isSelected()){
            gender = "female";
        }
        String contact = txt_contact.getText();
        String address = txt_address.getText();
        String pass1 = txt_pass.getText();
        String pass2 = txt_cpass.getText();

        if (!pass1.equals(pass2)){
            JOptionPane.showMessageDialog(reg_frame,"The password does not match," +
                    "Please try again");
        }
        else {
            if (e.getSource()==btn_register) {
                DatabaseOperation db = new DatabaseOperation();
                String query = "insert into register(username, fullname, email, gender, contact, address, pass, con_pass) values('"+username+"', '"+name+"','"+email+"','"+gender+"', '"+contact+"', '"+address+"', '"+pass1+"','"+pass2+"')";
                int ans=db.insert(query);
                if (ans>0){
                    JOptionPane.showMessageDialog(reg_frame ,"Data Registered Successfully");
                }
            }
        }
        if (e.getSource()==btn_back){
            reg_frame.dispose();
            new Login();
        }
    }
}

