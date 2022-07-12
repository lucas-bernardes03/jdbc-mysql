import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class App {
    public static void main(String[] args) {
        Connection con = null;
        PreparedStatement pst = null;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        try{
            con = DB.getConnection();
            pst = con.prepareStatement(
                "INSERT INTO seller "
                + "(Name, Email, BirthDate, BaseSalary, DepartmentId) "
                + "VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            
            pst.setString(1, "Carlos Maia");
            pst.setString(2, "carlin@gmail.com");
            pst.setDate(3, new java.sql.Date(sdf.parse("12/12/1987").getTime()));
            pst.setDouble(4, 5000);
            pst.setInt(5, 3);

            int updates = pst.executeUpdate();
            if(updates > 0){
                ResultSet rs = pst.getGeneratedKeys();
                while(rs.next()){
                    int id = rs.getInt(1);
                    System.out.println("Done! Id: " + id);
                }
            }
            else System.out.println("No updates made!");
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        catch(ParseException e){
            e.printStackTrace();
        }
        finally{
            DB.closeStatement(pst);
            DB.closeConnection();
        }
    }
}
