import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class AllBooked implements ActionListener{
    JFrame viewBooked;
    String[] medBooked = {"User ID","Username","Medicine ID","Medicine Name", "Medicine Price", "Quantity","Total Price"};
    DefaultTableModel medBookedTable;
    JTable bookedData;
    JButton btnBack;


    public AllBooked(){
        viewBooked = new JFrame("Booked");
        Font f = new Font("arial", Font.BOLD, 18);
        viewBooked.setSize(650, 500);
        viewBooked.setLayout(null);

        // Table for showing food booked items
        medBookedTable = new DefaultTableModel(new String[][]{}, medBooked);
        bookedData = new JTable(medBookedTable);
        JScrollPane jScrollPaneMedicine = new JScrollPane(bookedData);
        jScrollPaneMedicine.setBounds(50, 50, 550, 250);
        viewBooked.add(jScrollPaneMedicine);
        viewBooked.setVisible(true);

        try {
            DatabaseOperation db = new DatabaseOperation();
            String query = "select  si.userid, username, si.medid, medname, medprice, medqty , (medprice * medqty) as 'Total Price' from \n" +
                    "register as rg, addMedData as md, singleUser as si\n" +
                    "where rg.userid = si.userID and si.medid = md.medid";
            ResultSet rs = db.select(query);
            while (rs.next()) {
                medBookedTable.addRow(new Object[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7) });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        btnBack = new JButton("Back to Dashboard");
        btnBack.setBounds(200,350,150,20);
        btnBack.addActionListener(this);
        viewBooked.add(btnBack);
    }

    public static void main(String[] args) {
        new AllBooked();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==btnBack){
            viewBooked.dispose();
            new Dashboard();
        }
    }
}
