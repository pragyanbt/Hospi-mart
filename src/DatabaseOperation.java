import java.sql.*;

public class DatabaseOperation {
    Connection conn;
    Statement stmt;
    int val;
    int values;
    ResultSet row1;
    public DatabaseOperation(){
        try {
            //Class.forName("com.mysql.jdbc.Driver");
            // Create the connection object
            String url = "jdbc:mysql://127.0.0.1:3306/Hospital";
            String user = "root";
            String password = "IRONman@123";
            conn = DriverManager.getConnection(url,user,password);
//            if (conn!=null){
//                System.out.println("Database is connected successfully");
//            }
            //Creating statement Object
            stmt = conn.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public int insert(String query){
        //Execute Query
        try {
            val = stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return val;
    }

    public ResultSet select(String query){
        try {
            row1 = stmt.executeQuery(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return row1;
    }
    public int executeDelete(String query) {
        try {
            values=stmt.executeUpdate(query);
        }
        catch(SQLException throwables) {
            throwables.printStackTrace();
        }
        // TODO Auto-generated method stub
        return values;
    }


    public static void main(String[] args) {
        new DatabaseOperation();
    }
}

