import java.sql.ResultSet;

public class GetUserID {
    String user;
    int id;

    public GetUserID(String user){
        this.user = user;


        try {
            DatabaseOperation u = new DatabaseOperation();
            ResultSet rs = u.select("select * from register where username = '"+user+"'");
            if (rs.next()){
                ResultSet rs1 = u.select("select * from register where username = '"+user+"'");
                while (rs1.next()){
                    id = Integer.parseInt(rs1.getString(1));
                    //System.out.println(rs1.getString(1));
                }
                //System.out.println(id);
            }

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static void main(String[] args) {
        new GetUserID("test");

    }
}
