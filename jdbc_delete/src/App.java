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
                "DELETE FROM department "
                + "WHERE Id = ?");
            pst.setInt(1, 5);

            int updates = pst.executeUpdate();

            System.out.println("Done! Updates: " + updates);
        }
        catch(SQLException e){
            throw new DbIntegrityException(e.getMessage());
        }
        finally{
            DB.closeStatement(pst);
            DB.closeConnection();
        }
    }
}
