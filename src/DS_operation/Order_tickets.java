package DS_operation;

import DS_operation.Connect;
import DS_operation.Temp_tickets_info;
import DS_operation.Train_leftickets_info;
import DS_operation.Van_info;

import javax.swing.text.html.HTMLDocument;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

/**
 * @auther DONG BQ
 * @DATE 2020/11/1423:32
 */
public class Order_tickets {
    private int trainid,start_staion_id,arrive_station_id;
    private String start_station,arrive_station,ticket_type_age,ticket_type,taker_name,taker_idcard,taker_tel,customid,start_time,arrive_time;
    private double cost;
    private Connection connection;
    private int train_over;
    private int carriage_id_chosen;
    private String final_seat_chosen,order_id,order_date;
    private ArrayList<Temp_van_takeinfo> final_van_infos=new ArrayList<>();
    private ArrayList<Temp_take_indo> final_take_info=new ArrayList<>();
    public Order_tickets(String customid,int trainid,int start_staion_id,int arrive_station_id,String start_station,String arrive_station
    , String start_time,String arrive_time , String taker_name,String taker_idcard,String ticket_type,String taker_tel,String ticket_type_age,double cost){
       this.customid=customid;
       this.trainid=trainid;
       this.start_staion_id=start_staion_id;
       this.arrive_station_id=arrive_station_id;
       this.start_station=start_station;
       this.arrive_station=arrive_station;
       this.taker_name=taker_name;
       this.taker_idcard=taker_idcard;
       this.taker_tel=taker_tel;
       this.ticket_type=ticket_type;
       this.ticket_type_age=ticket_type_age;
       this.start_time=start_time;
       this.arrive_time=arrive_time;
       this.cost=cost;
        Date date=new Date();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        String sdate=simpleDateFormat.format(date);
        this.order_date=sdate;
        long time=date.getTime();
        String stime=""+time;
        String order_id="wdnmd"+stime;
        this.order_id=order_id;
        try {
            Connect connect=new Connect();
            connect.testConnection();;
            connection=connect.getConnection();
        }catch (Exception e){
            e.printStackTrace();
        }
        Filtertype();
        //这时候有了所有的值，随机生成一个字符串？
        insert_into();

    }

    /**
     * 我知道我要买的票的起点和终点
     * 1.如果订单的终点小于本起点，或者订单起点大于本终点，一定不占有该程座位
     * 2.我知道了该程中已知的所有被占有的座位，找到符合ticket_type的车厢id;
     *
     */
    public ArrayList<Temp_take_indo> suborder(){
        ArrayList<Temp_take_indo> temp_take_indos=new ArrayList<>();
        ArrayList<Temp_take_indo> result_take_indos=new ArrayList<>();
        //先得到基础信息 start_id,arrive_id,
        String sql1="select * from orders where train_id="+trainid;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql1);
            ResultSet rs=preparedStatement.executeQuery();
            rs.first();
            if(rs.getRow()!=0){
                rs.beforeFirst();
                while (rs.next()){
                    int current_start_station_id=rs.getInt("start_station_id");
                    int current_arrive_station_id=rs.getInt("arrive_station_id");
                    int current_carriage_id=rs.getInt("carriage_id");
                    String current_seat_take=rs.getString("tarin_seat");

                    Temp_take_indo temp_take_indo=new Temp_take_indo(current_carriage_id,current_start_station_id,current_arrive_station_id,current_seat_take);
                    temp_take_indos.add(temp_take_indo);
                }
                Iterator iterator=temp_take_indos.iterator();
                while (iterator.hasNext()){
                    Temp_take_indo temp_take_indo=(Temp_take_indo)iterator.next();
                    int start_id=temp_take_indo.getStart_id();
                    int over_id=temp_take_indo.getArrive_id();
                    //对每个占有的座位来说，如果其子集在该票程的范围内
                    if(start_id>arrive_station_id||over_id<start_id){

                    }else {
                        result_take_indos.add(temp_take_indo);
                    }
                }
            }
            rs.close();
            preparedStatement.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return result_take_indos;
    }

    public void Filtertype(){
        String sql="select carriage_id from train_seat where train_id="+trainid+" and seat_type='"+ticket_type+"'";
        HashMap<Integer,Integer> carrige_id =new HashMap<>();
        ArrayList<Temp_take_indo> indos=suborder();

        try {
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            ResultSet resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                int id=resultSet.getInt("carriage_id");
                carrige_id.put(id,0);
                System.out.println(id);
            }
            //在符合的车厢id中随机选择一个人数最少的车厢
            Iterator iterator=indos.iterator();
            while (iterator.hasNext()){
                Temp_take_indo temp_take_indo=(Temp_take_indo)iterator.next();
                int current_carriage_id=temp_take_indo.getCarrige_id();
                if(carrige_id.containsKey(current_carriage_id)){
                   int s= carrige_id.get(current_carriage_id)+1;
                   carrige_id.remove(current_carriage_id);
                   carrige_id.put(current_carriage_id,s);
                }
            }

            //在carriage_id中找到最少的那个
            Iterator iter = carrige_id.entrySet().iterator();
            int carriage=0;
            int max=66666;
            while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            int key =(int) entry.getKey();
            int val = (int)entry.getValue();
            if(val<max) {
                max=val;
                carriage=key;
               
            }
            System.out.println("key"+key);
            }
            System.out.println("dsa"+carriage);
            //这样我们就找到了最小的车厢（且符合seat_type）
            carriage_id_chosen=carriage;
            //查找当前carriage的情况
            String sql2="select * from train_seat where train_id="+trainid+" and carriage_id="+carriage;
            preparedStatement=connection.prepareStatement(sql2);

            ResultSet resultSet1=preparedStatement.executeQuery();
            resultSet1.first();
            int row=resultSet1.getInt("seat_row");

            //对当前车厢进行初始化
            HashMap<String,Integer> hashMap=initial(row);
            //把已占有的移除
            Iterator iterator1=indos.iterator();
            while (iterator1.hasNext()){
                Temp_take_indo temp_take_indo=(Temp_take_indo)iterator1.next();
                if(temp_take_indo.getCarrige_id()==carriage){
                    //放入一个数组
                    hashMap.remove(temp_take_indo.getSeat());
                }
            }
            //根据hashmap的大小生成一个随机数
            int seat_choose=(int) (new Random().nextInt(hashMap.size()+1));
            Set<String> set=hashMap.keySet();
            String seat_chosen="";
            int seat_count=0;
            for (String str:set) {
                if(seat_count==seat_choose){
                    seat_chosen=str;
                }
                seat_count++;
            }
            final_seat_chosen=seat_chosen;
            resultSet.close();
            resultSet1.close();
            preparedStatement.close();
        }catch (SQLException e){
            e.printStackTrace();
        }

    }
    public void insert_into(){
        String sql="insert into orders values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,order_id);
            preparedStatement.setString(2,order_date);
            preparedStatement.setString(3,customid);
            preparedStatement.setString(4,taker_name);
            preparedStatement.setString(5,taker_idcard);
            preparedStatement.setString(6,taker_tel);
            preparedStatement.setInt(7,trainid);
            preparedStatement.setInt(8,start_staion_id);
            preparedStatement.setInt(9,arrive_station_id);
            preparedStatement.setString(10,start_station);
            preparedStatement.setString(11,arrive_station);
            preparedStatement.setInt(12,carriage_id_chosen);
            preparedStatement.setString(13,ticket_type);
            preparedStatement.setString(14,ticket_type_age);
            preparedStatement.setString(15,final_seat_chosen);
            preparedStatement.setString(16,start_time);
            preparedStatement.setString(17,arrive_time);
            preparedStatement.setDouble(18,cost);
            preparedStatement.setString(19,"ZC");

            preparedStatement.execute();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    private HashMap<String,Integer> initial(int row){
        HashMap<String,Integer> seat=new HashMap<>();
        if(ticket_type.equals("SW")){
            for(int i=1;i<row+1;i++){
                String temp=""+i;
                for (int j=0;j<3;j++){
                    char s=65;
                    if(j==0){
                        s=(char)(s+0);
                    }
                    else if(j==1){
                        s=(char)(s+3);
                    }
                    else if(j==2){
                        s=(char)(s+4);
                    }
                    temp=temp+s;
                    seat.put(temp,0);
                    temp=""+i;
                }
            }
        }
        else if(ticket_type.equals("YD")){
            for(int i=1;i<row+1;i++){
                String temp=""+i;
                for (int j=0;j<4;j++){
                    char s=65;
                    if(j==0){
                        s=(char)(s+0);
                    }
                    else if(j==1){
                        s=(char)(s+2);
                    }
                    else if(j==2){
                        s=(char)(s+3);
                    }
                    else if(j==3){
                        s=(char)(s+4);
                    }
                    temp=temp+s;
                    //System.out.println("TEMP:"+temp);
                    seat.put(temp,0);
                    temp=""+i;
                }
            }
        }
        else{
            for(int i=1;i<row+1;i++){
                String temp=""+i;
                for (int j=0;j<4;j++){
                    char s=65;
                    if(j==0){
                        s=(char)(s+0);
                    }
                    else if(j==1){
                        s=(char)(s+1);
                    }
                    else if(j==2){
                        s=(char)(s+2);
                    }
                    else if(j==3){
                        s=(char)(s+3);
                    }
                    else if (j==4){
                        s=(char)(s+4);
                    }
                    temp=temp+s;
                    seat.put(temp,0);
                    temp=""+i;
                }
            }
        }

        return seat;
    }
    public static void main(String[] args){
        /*public Order_tickets(String customid,int trainid,int start_staion_id,int arrive_station_id,String start_station,String arrive_station
    ,String taker_name,String taker_idcard,String ticket_type,String taker_tel,String ticket_type_age){*/
        Order_tickets order_tickets=new Order_tickets("15589359936",3283,1,5,"上海虹桥","义乌","12:20:00","13:57:00","111","222","ED","888","XS",56.3);

    }
}
