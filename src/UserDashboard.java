import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserDashboard implements ActionListener {
    JLabel lblUser;
    JFrame dashboard;
    JButton updateProfile, showMed, showBooked;
    String user;


    public UserDashboard(String user){
        this.user = user;

        dashboard = new JFrame("User Dashboard");
        dashboard.setSize(500,400);
        dashboard.setLayout(null);
        dashboard.setVisible(true);

        lblUser = new JLabel("Welcome " + user);
        lblUser.setFont(new Font("arial",Font.BOLD,24));
        lblUser.setForeground(Color.RED);
        lblUser.setBounds(150,10,300,50);
        dashboard.add(lblUser);

        updateProfile = new JButton("Update Profile");
        updateProfile.setBounds(50,100,150,25);
        updateProfile.addActionListener(this);
        dashboard.add(updateProfile);

        showMed = new JButton("Book Medicines");
        showMed.setBounds(50,150,150,25);
        showMed.addActionListener(this);
        dashboard.add(showMed);

        showBooked = new JButton("Show Booked Medicines");
        showBooked.setBounds(50,200,150,25);
        showBooked.addActionListener(this);
        dashboard.add(showBooked);

    }

    public static void main(String[] args) {
        new UserDashboard("test");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==updateProfile){
            new ProfileUpdate(user);
        }
        if (e.getSource()==showMed){
            new BookMedicine(user);
        }
        if (e.getSource()==showBooked){
            new ShowBooked(user);
        }
    }
}


