import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class ShowMedicine implements ActionListener {
    JFrame showFrame;
    String [] medicines = {"Medicine ID", "Medicine Name", "Medicine Price"};
    DefaultTableModel medicineModel;
    JTable medicineData;
    JTextField medID;
    JButton btnBack, btnDelete;


    public ShowMedicine(){
        showFrame = new JFrame("Added Medicines");
        Font f = new Font("arial", Font.BOLD, 18);
        showFrame.setSize(650, 500);
        showFrame.setLayout(null);

        // Table for showing food items
        medicineModel = new DefaultTableModel(new String[][]{}, medicines);
        medicineData = new JTable(medicineModel);
        JScrollPane jScrollPaneFood = new JScrollPane(medicineData);
        jScrollPaneFood.setBounds(50, 50, 550, 300);
        showFrame.add(jScrollPaneFood);
        showFrame.setVisible(true);

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

        btnBack = new JButton("Back to Dashboard");
        btnBack.setBounds(150,390,150,20);
        btnBack.addActionListener(this);
        showFrame.add(btnBack);

        medID = new JTextField();
        medID.setBounds(350, 360, 150, 23);
        medID.setBackground(Color.WHITE);
        medID.setForeground(Color.BLACK);
        showFrame.add(medID);

        btnDelete = new JButton("Delete Medicine");
        btnDelete.setBounds(350,390,150,20);
        btnDelete.addActionListener(this);
        showFrame.add(btnDelete);
    }


    public static void main(String[] args) {
        new ShowMedicine();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String id = medID.getText();

        if (e.getSource()==btnBack){
            showFrame.dispose();
            new Dashboard();
        }
        if (e.getSource()==btnDelete){
            try {
                DatabaseOperation db = new DatabaseOperation();
                String query = "delete from addMedData where medid='" + id + "'";
                int rowsDeleted = db.executeDelete(query);
                if (rowsDeleted > 0) {
                    medID.setText("");
                    JOptionPane.showMessageDialog(btnDelete, "Successfully deleted");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
