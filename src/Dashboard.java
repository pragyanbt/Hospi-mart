import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Dashboard implements ActionListener {
    JLabel lblUser;
    JFrame dashboard;
    JButton addMed, showMed, showBooked;


    public Dashboard(){

        dashboard = new JFrame("Admin Dashboard");
        dashboard.setSize(500,400);
        dashboard.setLayout(null);
        dashboard.setVisible(true);

        lblUser = new JLabel("Welcome ");
        lblUser.setFont(new Font("arial",Font.BOLD,24));
        lblUser.setForeground(Color.RED);
        lblUser.setBounds(150,10,300,50);
        dashboard.add(lblUser);

        addMed = new JButton("Add Medicines");
        addMed.setBounds(50,100,150,25);
        addMed.addActionListener(this);
        dashboard.add(addMed);

        showMed = new JButton("Show Medicines");
        showMed.setBounds(50,150,150,25);
        showMed.addActionListener(this);
        dashboard.add(showMed);

        showBooked = new JButton("Show Booked Medicines");
        showBooked.setBounds(50,200,150,25);
        showBooked.addActionListener(this);
        dashboard.add(showBooked);

    }

    public static void main(String[] args) {
        new Dashboard();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==addMed){
            dashboard.dispose();
            new AddMedicine();
        }
        if (e.getSource()==showMed){
            dashboard.dispose();
            new ShowMedicine();
        }
        if (e.getSource()==showBooked){
            dashboard.dispose();
            new AllBooked();
        }
    }
}

