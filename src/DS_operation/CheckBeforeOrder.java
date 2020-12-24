package DS_operation;

import DS_operation.Connect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @auther DONG BQ
 * @DATE 2020/11/1513:13
 */
public class CheckBeforeOrder {
    private int train_id;
    private String taker_idcard,date;
    private int result=0;
    private Connection connection;
    public CheckBeforeOrder(int train_id,String taker_idcard,String date){
        this.train_id=train_id;
        this.taker_idcard=taker_idcard;
        this.date=date;
        String sql="select * from orders where train_id="+train_id+" and taker_card_id='"+taker_idcard+"'"+" and orderDate='"+date+"'";
        try {
            Connect connect=new Connect();
            connect.testConnection();
            connection=connect.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            ResultSet resultSet=preparedStatement.executeQuery();
            resultSet.first();
            if(resultSet.getRow()!=0){
                resultSet.beforeFirst();
                while (resultSet.next()){
                    String state=resultSet.getString("order_state");
                    if(state.equals("ZC")){
                        result=1;
                    }
                }
            }else {
                result=0;
            }
            resultSet.close();;
            preparedStatement.close();
            connection.close();
            connect.closeconnection();
        }catch (SQLException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public int getResult() {
        return result;
    }
    public static void main(String[] args){
        CheckBeforeOrder checkBeforeOrder=new CheckBeforeOrder(3283,"222","2020-11-16");
        System.out.println(checkBeforeOrder.getResult());
    }
}
