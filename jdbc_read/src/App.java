import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class App {
    public static void main(String[] args) {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        try{
            con = DB.getConnection();
            st = con.createStatement();
            rs = st.executeQuery("select * from department");

            while(rs.next()){
                System.out.println(rs.getInt("Id") + " | " + rs.getString("Name"));
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        finally{
            DB.closeResultSet(rs);
            DB.closeStatement(st);
            DB.closeConnection();
        }
    }
}
