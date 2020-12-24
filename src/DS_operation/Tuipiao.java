package DS_operation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @auther DONG BQ
 * @DATE 2020/11/1610:50
 */
public class Tuipiao {
    private String order_id;
    public Tuipiao(String order_id){
        this.order_id=order_id;
        try {
            Connect connect=new Connect();
            connect.testConnection();
            Connection connection=connect.getConnection();
            String sql="delete from orders where order_id='"+order_id+"'";
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            boolean s=preparedStatement.execute();
            System.out.println(s);
            preparedStatement.close();
            connection.close();
            connect.closeconnection();
        }catch (SQLException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        Tuipiao tuipiao=new Tuipiao("wdnmd1605517856808");
    }
}
