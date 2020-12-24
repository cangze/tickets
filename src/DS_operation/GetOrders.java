package DS_operation;

import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * @auther DONG BQ
 * @DATE 2020/11/167:08
 */
public class GetOrders {
    private ArrayList<Orderinfo> orderinfos=new ArrayList<>();
    private String custom_id;
    public GetOrders(String custom_id){
        this.custom_id=custom_id;
        try {
            Connect connect=new Connect();
            connect.testConnection();
            Connection connection=connect.getConnection();
            String sql="select * from orders where custom_id='"+custom_id+"'";
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            ResultSet resultSet=preparedStatement.executeQuery();
            resultSet.first();
            if(resultSet.getRow()!=0){
                resultSet.beforeFirst();;
                while (resultSet.next()){
                    String order_id=resultSet.getString("order_id");
                    Date orderDate=resultSet.getDate("orderDate");
                    String taker_name=resultSet.getString("taker_name");
                    String taker_card_id=resultSet.getString("taker_card_id");
                    String taker_phone=resultSet.getString("taker_phone");
                    String start_station_name=resultSet.getString("start_staion_name");
                    String arrive_station_name=resultSet.getString("arrive_station_name");
                    String seat_type=resultSet.getString("seat_type");
                    String seat_type_age=resultSet.getString("seat_type_age");
                    String train_seat=resultSet.getString("tarin_seat");
                    String start_time=resultSet.getString("start_time");
                    String arrive_time=resultSet.getString("arrive_time");
                    int train_id=resultSet.getInt("train_id");
                    int carriage_id=resultSet.getInt("carriage_id");
                    double cost=resultSet.getDouble("cost");
                    String finalseat_type=switchtofinalSeattype(seat_type);
                    String finalseat_type_age=switchtofinalSeattypeage(seat_type_age);
                    Orderinfo orderinfo=new Orderinfo(order_id,orderDate,custom_id,taker_name,taker_card_id,taker_phone,start_station_name,arrive_station_name,carriage_id,finalseat_type,finalseat_type_age,train_seat,start_time,arrive_time,cost);
                    orderinfo.setTrain_id(train_id);

                    orderinfos.add(orderinfo);

                }
            }
            if (orderinfos.size()!=0){
                Iterator iterator=orderinfos.iterator();
                while (iterator.hasNext()){
                    Orderinfo orderinfo=(Orderinfo)iterator.next();
                    int train_id=orderinfo.getTrain_id();
                    String sql2="select * from train_sum_info where train_id="+train_id;
                    if(connection==null){
                        connection=connect.getConnection();
                    }
                    preparedStatement=connection.prepareStatement(sql2);
                    ResultSet resultSet1=preparedStatement.executeQuery();
                    resultSet1.first();
                    String train_name=resultSet1.getString("train_name");
                    orderinfo.setTrain_name(train_name);
                    System.out.println(orderinfo);
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public ArrayList<Orderinfo> getOrderinfos() {
        return orderinfos;
    }
    public static void main(String[] args){
        GetOrders getOrders=new GetOrders("15589359936");
    }

    public String switchtofinalSeattype(String seat_type){
        String finalstaring="";
        switch (seat_type){
            case "SW":
                finalstaring="商务座";
                break;
            case "YD":
                finalstaring="一等座";
                break;
            case "ED":
                finalstaring="二等座";
                break;
            case "OT":
                finalstaring="其他座次类型";
                break;

        }
        return finalstaring;
    }

    public String switchtofinalSeattypeage(String seat_type_Age){
        String finalstaring="";
        switch (seat_type_Age){
            case "CR":
                finalstaring="成人票";
                break;
            case "XS":
                finalstaring="学生票";
                break;

        }
        return finalstaring;
    }
}
