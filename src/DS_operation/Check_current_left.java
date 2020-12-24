package DS_operation;

import DS_operation.Connect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @auther DONG BQ
 * @DATE 2020/11/117:52
 */
public class Check_current_left {
    private int start_id=0,arrive_id=0;
    private boolean left_tickets_bol=false;
    //主要是查看当前 Train_name对应列车的，从站点As->Bs是否还有票，
    public Check_current_left(String train_name,String strat_name,String arrive_name,String seat_type){
        /**
         * 从<train_name>_info中查看对应的start_Station和arrive_station的编号
         */
        String sql_start_staion_id="select station_id from "+train_name+"_info where station_name='"+strat_name+"'";
        String sql_arrive_station_id="select station_id from "+train_name+"_info where station_name='"+arrive_name+"'";

        try {
            Connect connect=new Connect();
            connect.testConnection();
            Connection connection=connect.getConnection();

            PreparedStatement pstm=connection.prepareStatement(sql_start_staion_id);
            ResultSet rs1=pstm.executeQuery();
            while (rs1.next()){
                start_id=rs1.getInt("station_id");
            }

            pstm=connection.prepareStatement(sql_arrive_station_id);
            ResultSet rs2=pstm.executeQuery();
            while (rs2.next()){
                arrive_id=rs2.getInt("station_id");
            }
            if(start_id!=0 && arrive_id!=0){
                System.out.println("start id:"+start_id+"; arrive id:"+arrive_id);
                //在数据库对应表中查找从idA-idB的余票是否不为零
                String seat="select "+seat_type+"_tickets from "+train_name+"_leftickets where start_id="+start_id+" and arrive_id="+arrive_id;
                pstm=connection.prepareStatement(seat);
                ResultSet resultSet=pstm.executeQuery();
                while (resultSet.next()){
                    int left_tickets=resultSet.getInt(seat_type+"_tickets");
                    if(left_tickets!=0) left_tickets_bol=true;
                    else left_tickets_bol=false;
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }


    }

    public int getArrive_id() {
        return arrive_id;
    }

    public int getStart_id() {
        return start_id;
    }
    public boolean getLeft_tickets(){
        return left_tickets_bol;
    }

    public static void main(String[] args){
        Check_current_left check_current_left=new Check_current_left("g227","徐州东","济南","YD");
    }
}
