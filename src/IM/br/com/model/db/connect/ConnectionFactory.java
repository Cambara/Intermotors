package IM.br.com.model.db.connect;
import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
	
	public static Connection createConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/intermotors","root","root");
            return con;
        } catch (Exception e) {
            e.printStackTrace();
        }  
        return null;
    }

}
