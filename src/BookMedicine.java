import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class BookMedicine implements ActionListener {
    String user;
    JFrame bookFrame;
    String [] medicines = {"Medicine ID", "Medicine Name", "Medicine Price"};
    DefaultTableModel medicineModel;
    JTable medicineData;

    JLabel lblMedBook, medId, medQty;
    JTextField txtMedId, txtMedQty;
    JButton medBook;

    public BookMedicine(String user){
        this.user = user;
        bookFrame = new JFrame("Available Medicines");
        Font f = new Font("arial", Font.BOLD, 18);
        bookFrame.setSize(900, 500);
        bookFrame.setLayout(null);

        // Table for showing food items
        medicineModel = new DefaultTableModel(new String[][]{}, medicines);
        medicineData = new JTable(medicineModel);
        JScrollPane jScrollPaneFood = new JScrollPane(medicineData);
        jScrollPaneFood.setBounds(50, 20, 550, 150);
        bookFrame.add(jScrollPaneFood);
        bookFrame.setVisible(true);

        try {
            DatabaseOperation db = new DatabaseOperation();
            String query = "select * from addMedData";
            ResultSet rs = db.select(query);
            while (rs.next()) {
                medicineModel.addRow(new Object[]{rs.getString(1), rs.getString(2), rs.getString(3)});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Book Medicine over here
        lblMedBook = new JLabel("Book Medicine over here");
        lblMedBook.setBounds(650, 50, 150, 25);
        bookFrame.add(lblMedBook);

        medId = new JLabel("Medicine ID : ");
        medId.setBounds(620, 70, 100, 25);
        bookFrame.add(medId);

        txtMedId = new JTextField();
        txtMedId.setBounds(750, 70, 100, 25);
        bookFrame.add(txtMedId);

        medQty = new JLabel("Enter Quantity : ");
        medQty.setBounds(620, 100, 100, 25);
        bookFrame.add(medQty);

        txtMedQty = new JTextField();
        txtMedQty.setBounds(750, 100, 100, 25);
        bookFrame.add(txtMedQty);

        medBook = new JButton("Book Medicines");
        medBook.setBounds(620, 130, 150, 25);
        medBook.addActionListener(this);
        bookFrame.add(medBook);
    }

    private void medOrderReset(){
        txtMedId.setText("");
        txtMedQty.setText("");
    }

    public static void main(String[] args) {
        new BookMedicine("test");
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        GetUserID id = new GetUserID(user);

        String medId = txtMedId.getText();
        String medQty = txtMedQty.getText();

        MedicineOrder mo = new MedicineOrder(medId,medQty);

        if (e.getSource()==medBook){
            if (medId.length()==0 || medQty.length()==0){
                JOptionPane.showMessageDialog(medBook, "Enter all fields completely");
            } else {
                DatabaseOperation db = new DatabaseOperation();
                String query = "insert into singleUser (userid, medid, medqty) values('"+id.getId()+"', '"+mo.getMedId()+"', '"+mo.getMedQty()+"' )";
                int ans=db.insert(query);
                if (ans>0){
                    JOptionPane.showMessageDialog(medBook ,"Medicine Booked Successfully");
                    medOrderReset();
                }
            }
        }
    }
}
