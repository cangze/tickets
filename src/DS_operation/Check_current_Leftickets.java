package DS_operation;

import DS_operation.Connect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @auther DONG BQ
 * @DATE 2020/11/1313:31
 */
public class Check_current_Leftickets {
    private int train_id,strat_id,arrive_id,train_start,train_over;
    /**
     *车票进行余票信息查询
     * 1.初始化：
     * 1>获得train_id对应的车厢信息(即初始余票)
     * 2>train_start_id--train_over_id 组合表
     *
     * 2.获得orders中对应train_id的所有车票信息
     * 3.按照m-n的车票减少策略 对站点的车票信息进行减少
     *
     * 4.返回余票信息
     */
    private ArrayList<Train_leftickets_info> train_leftickets_infos=new ArrayList<>();
    private Connection connection=null;
    public Check_current_Leftickets(int train_id,int train_start,int train_over,int start_id,int arrive_id){
        this.train_id=train_id;
        this.strat_id=start_id;
        this.arrive_id=arrive_id;
        this.train_start=train_start;
        this.train_over=train_over;


        try {
            Connect connect=new Connect();
            connect.testConnection();;
            connection=connect.getConnection();
            train_leftickets_infos=intial();
            train_leftickets_infos=sub_order();
            connection.close();
            connect.closeconnection();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    //初始化信息
    public ArrayList<Train_leftickets_info> intial(){
        ArrayList<Train_leftickets_info> temp=new ArrayList<>();
        /*1.获得train_id对应的车厢总数信息*/
        String sql_trian_van="select count(*) count_s from train_seat where train_id="+train_id;

        try {
            PreparedStatement preparedStatement=connection.prepareStatement(sql_trian_van);
            ResultSet rs=preparedStatement.executeQuery();
            rs.first();
            int sum_van=rs.getInt("count_s");
            System.out.println("train_id为"+train_id+"的列车共有"+sum_van+"个车厢");

            rs.close();
            preparedStatement.close();
        }catch (SQLException e){
            e.printStackTrace();
        }

        /*2.获得每个车厢的信息*/
        int sw=0,yd=0,ed=0,ot=0;

        String sql_each_van="select * from train_seat where train_id="+train_id;
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(sql_each_van);
            ResultSet rs=preparedStatement.executeQuery();
            while (rs.next()){
                String type=rs.getString("seat_type");
                int seat_sum=rs.getInt("seat_sum");
                switch (type){
                    case "SW":
                        sw+=seat_sum;
                        break;
                    case "YD":
                        yd+=seat_sum;
                        break;
                    case "ED":
                        ed+=seat_sum;
                        break;
                    case "OT":
                        ot+=seat_sum;
                        break;
                }
            }
           // System.out.println(sw+","+yd+","+ed+","+ot);
        }catch (SQLException e){
            e.printStackTrace();
        }
        /*按照train_start,train_over初始化*/
        for (int i=1;i<train_over+1;i++){
            for(int j=i;j<train_over+1;j++){
                Train_leftickets_info train_leftickets_info=new Train_leftickets_info();
                train_leftickets_info.setTrain_id(train_id);
                train_leftickets_info.setStart_staion_id(i);
                train_leftickets_info.setArrive_station_id(j);
                train_leftickets_info.setSW(sw);
                train_leftickets_info.setYD(yd);
                train_leftickets_info.setED(ed);
                train_leftickets_info.setOT(ot);

             //   System.out.println("初始化中train_left_tickets"+train_leftickets_info);

                temp.add(train_leftickets_info);
            }
        }
        return temp;
    }

    //按照订单信息减少对应车票
    public ArrayList<Train_leftickets_info> sub_order(){
        ArrayList<Temp_tickets_info> temp_tickets_infos=new ArrayList<>();
        ArrayList<Train_leftickets_info> temp_train_left=(ArrayList<Train_leftickets_info>)train_leftickets_infos.clone();
        String sql_order="select * from orders where train_id="+train_id;
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(sql_order);
            ResultSet rs=preparedStatement.executeQuery();
            while (rs.next()){
                String order_id=rs.getString("order_id");
                int start_id=rs.getInt("start_station_id");
                int arrive_id=rs.getInt("arrive_station_id");
                String seat_type=rs.getString("seat_type");
                Temp_tickets_info temp_tickets_info=new Temp_tickets_info(order_id,train_id,start_id,arrive_id,seat_type);
                temp_tickets_infos.add(temp_tickets_info);
            }
            if(temp_tickets_infos.size()==0) {
                System.out.println("无占用");
            }else {
            System.out.println("当前车次的票数为\n"+temp_tickets_infos);}
            //在这里打印一下hashmap的值

        }catch (Exception e){
            e.printStackTrace();
        }
        //temp_tickers+infos里保存了所有与该列车有关的订单信息
        Iterator iterator=temp_tickets_infos.iterator();
        while (iterator.hasNext()){
            Temp_tickets_info temp_tickets_info=(Temp_tickets_info)iterator.next();
            int train_id=temp_tickets_info.getTrain_id();
            int start_id=temp_tickets_info.getStart_id();
            int arrive_id=temp_tickets_info.getArrive_id();
            String type=temp_tickets_info.getType();
         //   System.out.println("开始执行减少操作");
            System.out.println("执行("+start_id+","+arrive_id+")站点的遍历");
            //将数值减一
            int count=0;
            for (int i=start_id;i<arrive_id+1;i++){
                for(int j=i+1;j<arrive_id+1;j++){
                //    System.out.println("执行("+i+","+j+")站点的票减少------");
                    Iterator target= temp_train_left.iterator();

                    while (target.hasNext()){

                        Train_leftickets_info info=(Train_leftickets_info) target.next();
                        //System.out.println("正在查找："+info);
                        if(info.getTrain_id()==train_id&&info.getStart_staion_id()==i&&info.getArrive_station_id()==j){
//                            System.out.println("....执行("+start_id+","+arrive_id+")站点的遍历中...."+count);
                            System.out.println("("+i+","+j+")需要-1");
                            //这说明对应的要减1
                            count++;
                            switch (type){
                                case "SW":
                                        info.setSW(info.getSW()-1);
                                        break;
                                case "YD":
                                        info.setYD(info.getYD()-1);
                                        break;

                                case "ED":
                                        info.setED(info.getED()-1);
                                        break;
                                case "OT":
                                        info.setOT(info.getOT()-1);
                                        break;
                            }
                            break;
                        }
                    }
                }
            }

        }

        return temp_train_left;
    }

    //返回对应站点的票
    public Train_leftickets_info getbystationid(){
        Train_leftickets_info info=null;
        ArrayList<Train_leftickets_info> infos=(ArrayList<Train_leftickets_info>)train_leftickets_infos.clone();
        Iterator iterator=infos.iterator();
        while (iterator.hasNext()){
            Train_leftickets_info info1=(Train_leftickets_info)iterator.next();
            if(info1.getTrain_id()==train_id&&info1.getStart_staion_id()==strat_id&&info1.getArrive_station_id()==arrive_id){
                info=info1;
                break;
            }

        }

        return info;
    }

    public ArrayList<Train_leftickets_info> getTrain_leftickets_infos() {
        return train_leftickets_infos;
    }

    public static void main(String[] args){
        Check_current_Leftickets check_current_leftickets=new Check_current_Leftickets(3283,1,10,1,3);
        Train_leftickets_info info=check_current_leftickets.getbystationid();
        System.out.println(info);
    }
}
