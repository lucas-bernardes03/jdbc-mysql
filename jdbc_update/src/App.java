import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class App {
    public static void main(String[] args) {
        Connection con = null;
        PreparedStatement pst = null;

        try{
            con = DB.getConnection();
            pst = con.prepareStatement(
            "UPDATE seller "
            + "SET BaseSalary = BaseSalary + ? "
            + "WHERE (DepartmentId = ?)");
            pst.setDouble(1, 1500);
            pst.setInt(2, 3);

            int updates = pst.executeUpdate();

            System.out.println("Done! Updates: " + updates);
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        finally{
            DB.closeStatement(pst);
            DB.closeConnection();
        }
    }
}
