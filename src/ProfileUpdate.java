import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProfileUpdate implements ActionListener {
    String user;
    JFrame update;
    JLabel gender;
    JTextField txt_username, txt_fullname, txt_email, txt_contact, txt_address, txt_pass, txt_cpass;
    JRadioButton male, female;
    JButton btn_update;
    Font fon1;


    public ProfileUpdate(String user){
        this.user = user;

        update = new JFrame("Profile Update Page");
        update.setSize(815,680);
        update.setLayout(null);
        update.setVisible(true);

        fon1=new Font("algerian",Font.BOLD,50);

        JLabel bg = new JLabel(new ImageIcon(getClass().getResource("ProfileUpdate.png")));
        bg.setBounds(0,0,800,650);
        update.add(bg);


        // Gender Male or Female
        male = new JRadioButton("Male");
        male.setBounds(425,290,60,30);
        male.setSelected(true);
        male.setBackground(Color.decode("#09aeb6"));
        male.setForeground(Color.WHITE);
        bg.add(male);

        female = new JRadioButton("Female");
        female.setBounds(525,290,70,30);
        female.setBackground(Color.decode("#09aeb6"));
        female.setForeground(Color.WHITE);
        bg.add(female);

        // Grouping Male and Female

        ButtonGroup gen = new ButtonGroup();
        gen.add(male);
        gen.add(female);


        DatabaseOperation up = new DatabaseOperation();
        String query="select * from register where username='"+user+"'";
        ResultSet rs=up.select(query);

        try {
            while (rs.next()){
                //Getting username
                txt_username =new JTextField(rs.getString("username"));
                txt_username.setBounds(425,114,330,45);
                txt_username.setBackground(Color.WHITE);
                txt_username.setForeground(Color.BLACK);
                txt_username.setBorder(null);
                bg.add(txt_username);

                //Getting fullname
                txt_fullname =new JTextField(rs.getString("fullname"));
                txt_fullname.setBounds(425,173,330,45);
                txt_fullname.setBackground(Color.WHITE);
                txt_fullname.setForeground(Color.BLACK);
                txt_fullname.setBorder(null);
                bg.add(txt_fullname);

                //Getting email
                txt_email =new JTextField(rs.getString("email"));
                txt_email.setBounds(425,233,330,45);
                txt_email.setBackground(Color.WHITE);
                txt_email.setForeground(Color.BLACK);
                txt_email.setBorder(null);
                bg.add(txt_email);

                //Getting gender
                String gender = rs.getString("gender");

                if (gender.length()==4){
                    male.setSelected(true);
                }else {
                    female.setSelected(true);
                }

                //Getting contact
                txt_contact = new JTextField(rs.getString("contact"));
                txt_contact.setBounds(425,335,330,45);
                txt_contact.setBackground(Color.WHITE);
                txt_contact.setForeground(Color.BLACK);
                txt_contact.setBorder(null);
                bg.add(txt_contact);

                //Getting address
                txt_address = new JTextField(rs.getString("address"));
                txt_address.setBounds(425,395,330,45);
                txt_address.setBackground(Color.WHITE);
                txt_address.setForeground(Color.BLACK);
                txt_address.setBorder(null);
                bg.add(txt_address);

                //Getting password
                txt_pass = new JTextField(rs.getString("pass"));
                txt_pass.setBounds(425,454,330,45);
                txt_pass.setBackground(Color.WHITE);
                txt_pass.setForeground(Color.BLACK);
                txt_pass.setBorder(null);
                bg.add(txt_pass);

                //Getting confirm password
                txt_cpass = new JTextField(rs.getString("con_pass"));
                txt_cpass.setBounds(425,513,330,45);
                txt_cpass.setBackground(Color.WHITE);
                txt_cpass.setForeground(Color.BLACK);
                txt_cpass.setBorder(null);
                bg.add(txt_cpass);

                btn_update = new JButton("Update");
                btn_update.setBounds(525,588,130,30);
                btn_update.setBackground(Color.WHITE);
                btn_update.setForeground(Color.BLACK);
                btn_update.setBorder(null);
                btn_update.addActionListener(this);
                bg.add(btn_update);



            }
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        new ProfileUpdate("test");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String username = txt_username.getText();
        String name = txt_fullname.getText();
        String email = txt_email.getText();
        String gender = null;
        if (male.isSelected()){
            gender = male.getText();
        }
        if (female.isSelected()){
            gender = female.getText();
        }
        String contact = txt_contact.getText();
        String address = txt_address.getText();
        String pass1 = txt_pass.getText();
        String pass2 = txt_cpass.getText();

        if (!pass1.equals(pass2)){
            JOptionPane.showMessageDialog(update,"The password does not match," +
                    "Please try again");
        }
        else {
            if (e.getSource()==btn_update) {
                DatabaseOperation db = new DatabaseOperation();
                String query = "update register set username='"+username+"', fullname='"+name+"', email='"+email+"', gender='"+gender+"', contact='"+contact+"', address='"+address+"', pass='"+pass1+"', con_pass='"+pass2+"' where username='"+user+"' " ;
                int ans=db.insert(query);
                if (ans>0){
                    JOptionPane.showMessageDialog(btn_update ,"Data Updated Successfully");
                }
            }
        }
    }
}


