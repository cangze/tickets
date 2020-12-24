package DS_operation;

import DS_operation.Connect;
import DS_operation.Check_current_Leftickets;
import DS_operation.Train_leftickets_info;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @auther DONG BQ
 * @DATE 2020/11/1321:00
 */
public class Confirm_Tickets {
    //在页面可以获取到的数据：train_name,start_Station_name,arrive_station_name;start_time-arrive_time;
    private int train_id,train_startid,train_arriveid,startstaion_id,arrivestation_id;
    private Connection connection;
    private String run_date;
    private Train_leftickets_info train_leftickets_info=null;
    private double[] tickets_cost=new double[4];
    /**
     * 通过以上信息，需要获得的数据：
     * 1>出发日期--train_state;
     * 2>余票信息&&价格--train_seat:seat_type--basic_cost
     */
    public Confirm_Tickets(int train_id,int train_startid,int train_arriveid,int startstaion_id,int arrivestation_id){
        this.train_id=train_id;
        this.train_startid=train_startid;
        this.train_arriveid=train_arriveid;
        this.startstaion_id=arrivestation_id;
        this.arrivestation_id=arrivestation_id;
        Connect connect=new Connect();

        try{
        connect.testConnection();
        connection=connect.getConnection();
        }catch (Exception e){
            e.printStackTrace();
        }
        train_leftickets_info=new Check_current_Leftickets(train_id,train_startid,train_arriveid,startstaion_id,arrivestation_id).getbystationid();
        String sql1="select run_date from train_state where train_id="+train_id;
        String sql2="select * from train_seat where train_id="+train_id;

        try {
            PreparedStatement preparedStatement=connection.prepareStatement(sql1);
            ResultSet rs1=preparedStatement.executeQuery();
            rs1.first();
            run_date=rs1.getString("run_date");
            System.out.println(run_date);
            rs1.close();

            preparedStatement=connection.prepareStatement(sql2);
            ResultSet rs2=preparedStatement.executeQuery();
            int pass=arrivestation_id-startstaion_id;
            while (rs2.next()){
                String seattype=rs2.getString("seat_type");
                switch (seattype){
                    case "SW":
                        tickets_cost[0]=rs2.getDouble("basic_cost")*pass;
                        break;
                    case "YD":
                        tickets_cost[1]=rs2.getDouble("basic_cost")*pass;
                        break;
                    case "ED":
                        tickets_cost[2]=rs2.getDouble("basic_cost")*pass;
                        break;
                    case "OT":
                        tickets_cost[3]=rs2.getDouble("basic_cost")*pass;
                        break;
                }
            }
            
            System.out.println(tickets_cost[0]+","+tickets_cost[1]+","+tickets_cost[2]+","+tickets_cost[3]);
            rs2.close();
            preparedStatement.close();
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public Train_leftickets_info getTrain_leftickets_info() {
		return train_leftickets_info;
	}
    public double[] getTickets_cost() {
        return tickets_cost;
    }

    public String getRun_date() {
        return run_date;
    }

    public static void main(String[] args){
        Confirm_Tickets confirm_tickets=new Confirm_Tickets(3283,1,10,1,8);
    }

}
