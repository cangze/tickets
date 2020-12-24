package DS_operation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @auther DONG BQ
 * @DATE 2020/10/2020:28
 */
public class Connect {
    private String user="root";
    private String password="root";
    private String url="jdbc:mysql://localhost:3306/databasetry?&useSSL=false&serverTimezone=GMT%2B8";
    private String driverClass="com.mysql.cj.jdbc.Driver";

    private Connection connection=null;
    public void testConnection() throws Exception{
        try {
            Class.forName(driverClass);
        }catch (ClassNotFoundException cne){
            cne.printStackTrace();
        }

        try {
            connection= DriverManager.getConnection(url,user,password);

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
    	if(connection==null) {
    		try {
                connection= DriverManager.getConnection(url,user,password);

            }catch (SQLException e){
                e.printStackTrace();
            }
    	}
        return connection;
    }
    public void closeconnection() {
		if(connection!=null) {
			try {
				connection.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
			connection=null;
		}
	}
}
