package HR;
import java.sql.ResultSet;

public class JavaClient {
    public JavaClient() {
        super();
    }

    public static void main(String[] args) throws Exception{
        DataHandler datahandler = new DataHandler();
        //ResultSet rset = datahandler.getAllEmployees();
        ResultSet rset = datahandler.getEmployeesByName("King");
        System.out.println("\nResults from query: ");
        while(rset.next()){
            System.out.println(rset.getInt(1)+" "+
                rset.getString(2)+" "+
                rset.getString(3)+" "+
                rset.getString(4));
        }
    }
}
