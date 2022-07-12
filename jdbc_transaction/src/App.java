import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class App {
    public static void main(String[] args) {
        Connection con = null;
        Statement st = null;

        try{
            con = DB.getConnection();
            con.setAutoCommit(false);

            st = con.createStatement();

            int rows1 = st.executeUpdate("UPDATE seller SET BaseSalary = 2090 WHERE DepartmentId = 1 ");
            
            // int x = 5;
            // if(x > 3) throw new SQLException("Fake error");
            
            int rows2 = st.executeUpdate("UPDATE seller SET BaseSalary = 3090 WHERE DepartmentId = 2 ");
            
            con.commit();

            System.out.println("Rows 1 | " + rows1);
            System.out.println("Rows 2 | " + rows2);
        }
        catch(SQLException e){
            try {
                con.rollback();
                throw new DbException("Transaction rolled back. Caused by: " + e.getMessage());
            } catch (SQLException e1) {
                throw new DbException("Error trying to rollback. Caused by: " + e1.getMessage());
            }
        }
        finally{
            DB.closeStatement(st);
            DB.closeConnection();
        }
    }
}
