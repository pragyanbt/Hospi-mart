import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddMedicine implements ActionListener {
    JFrame med;
    JLabel lbl_heading, medName, medPrice;
    JTextField txtMedName, txtMedPrice;
    JButton addMed, btnBack;
    Font fon1;

    public AddMedicine(){
        med = new JFrame("Add Medicine");
        med.setSize(500,400);
        med.setLayout(null);
        med.setVisible(true);

        fon1=new Font("algerian",Font.BOLD,50);

        lbl_heading =new JLabel("Add Medicine");
        lbl_heading.setFont(fon1);
        lbl_heading.setBounds(80,10,550,50);
        lbl_heading.setForeground(new Color(34,34,247));
        med.add(lbl_heading);

        // Medicine name and Text Field
        medName = new JLabel("Medicine Name : ");
        medName.setBounds(100,100,100,20);
        med.add(medName);

        txtMedName = new JTextField();
        txtMedName.setBounds(200,100,150,20);
        med.add(txtMedName);

        // Medicine Price and Text Field
        medPrice = new JLabel("Medicine Price : ");
        medPrice.setBounds(100,130,100,20);
        med.add(medPrice);

        txtMedPrice = new JTextField();
        txtMedPrice.setBounds(200,130,150,20);
        med.add(txtMedPrice);

        // Add Medicine Button and Back to Dashboard Button
        addMed = new JButton("Add Medicine");
        addMed.setBounds(200,160,150,20);
        addMed.addActionListener(this);
        med.add(addMed);

        btnBack = new JButton("Back to Dashboard");
        btnBack.setBounds(200,190,150,20);
        btnBack.addActionListener(this);
        med.add(btnBack);

    }

    private void medReset(){
        txtMedName.setText("");
        txtMedPrice.setText("");
    }

    public static void main(String[] args) {
        new AddMedicine();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String medicineName = txtMedName.getText();
        String medicinePrice = txtMedPrice.getText();

        MedicineData md = new MedicineData(medicineName, medicinePrice);

        if (e.getSource()==addMed){
            if (medicineName.length()==0 || medicinePrice.length()==0){
                JOptionPane.showMessageDialog(addMed, "Enter all fields completely");
            } else {
                DatabaseOperation db = new DatabaseOperation();
                String query = "insert into addMedData (medname, medprice) values('"+md.getMedName()+"', '"+md.getMedPrice()+"')";
                int ans=db.insert(query);
                if (ans>0){
                    JOptionPane.showMessageDialog(addMed ,"Medicine Added Successfully");
                    medReset();
                }
            }
        }
        if (e.getSource()==btnBack){
            med.dispose();
            new Dashboard();
        }
    }
}

