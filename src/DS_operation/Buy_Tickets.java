package DS_operation;


import DS_operation.Connect;
import DS_operation.Check_current_left;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

/**
 * @auther DONG BQ
 * @DATE 2020/10/2120:33
 */
public class Buy_Tickets {
    private int MAX=65536;
    //车次信息
    private String name_of_train=null;
    //站点信息
    private String start_station=null;
    private String end_station=null;
    private String seat_type=null;
    private double money=MAX;
    //车票购票人信息
    private String customer_ID,taker_ID=null;
    private Buy_info buy_info=null;
    private int[][] stations;
    private int sum_stations=0;
    private int buy_type=0,arrive_id=0,start_id=0;
    //buytype=0表示直达，buytype=1表示换乘。
    //在用户界面查找完成转乘信息，在购票的时候，在后台直接生成两趟订单的buy即可
    //构造函数
    public Buy_Tickets(Buy_info buy_info,int buy_type) {
        this.buy_info=buy_info;
        //验证消息的合法：customer?
        this.customer_ID=buy_info.getCustomer_ID();
        this.name_of_train=buy_info.getTrain_name();
        this.taker_ID=buy_info.getTaker_ID();
        this.start_station=buy_info.getStart_station();
        this.end_station=buy_info.getArrive_staion();
        this.money=buy_info.getMoney();
        this.buy_type=buy_type;
        this.seat_type=buy_info.getSeat_type();
    }
    public boolean buy(){
        //查找当前的站点的车票数量
        Check_current_left check_current_left=new Check_current_left(name_of_train,start_station,end_station,seat_type);

         start_id=check_current_left.getStart_id();
         arrive_id=check_current_left.getArrive_id();

        sum_stations=(arrive_id-start_id+1)*(arrive_id-start_id)/2;

        if(check_current_left.getLeft_tickets()) {

            //对这个站点可能影响到的车票数量进行减
            //与车站生成的逻辑基本相同
            stations = new int[sum_stations][2];
            int count = 0;
            for (int i = start_id; i < arrive_id + 1; i++) {
                for (int j = i + 1; j < arrive_id + 1; j++) {
                    stations[count][0] = i;
                    stations[count][1] = j;
                    count++;
                }
            }
            //打印需要减的车票
//            print();

            //对每组减一
            Connect connect = new Connect();
            try {
                connect.testConnection();
                Connection connection = connect.getConnection();
                PreparedStatement preparedStatement = null;
                //这里就减一了
                for (int i = 0; i < sum_stations; i++) {
                    String sql = "UPDATE " + name_of_train + "_leftickets set "+seat_type+"_tickets="+seat_type+"_tickets-1" +
                            " where start_id=" + stations[i][0] + " and arrive_id=" + stations[i][1];
                    preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.execute();
                }

                //在购票记录上新增记录
                //customer_id,taker_id,train_name,start_id,arrive_id,van_id,seat_i(takes)
                //van_id,van_type,van_row,seat_sum
                /**
                 * 需要查询当前的车厢信息和对应的位置信息，从而分配车厢和座位
                 */
                String sql_van="select * from "+name_of_train+"_van where van_type='"+seat_type+"'";
                preparedStatement=connection.prepareStatement(sql_van);
                ResultSet van_set=preparedStatement.executeQuery();
                ArrayList<Integer> van_list=new ArrayList<>();
                while (van_set.next()){
                    //van_list中保存了该列车座次类型为"YD/ED/SW"的车厢号
                    int van_set_id=van_set.getInt("van_id");
                    System.out.println("van_set_id: "+van_set_id);
                    van_list.add(van_set_id);
                }
                System.out.println("van_list.size: "+van_list.size());


                //对每个车厢内被占用的座位进行计数
                //select count(*) van_count,van_id from g227_takes where start_id=1 and arrive_id=5 group by van_id order by van_count ASC;
                String sql_takes="select count(*) van_count,van_id from "+name_of_train+"_takes where start_station="+start_id+" and arrive_station="+arrive_id+" group by van_id order by van_count asc";
                preparedStatement=connection.prepareStatement(sql_takes);
                ResultSet van_ss=preparedStatement.executeQuery();
                //如果有结果，第一个的结果是最少的
                int min_vantakes_id=0;

                int co = 1;//co是count

                van_ss.first();
                if(van_ss.getRow()!=0) {
                    System.out.println("van_ss is not empty");
                    //van_ss有结果
                    van_ss.beforeFirst();
                    while (van_ss.next()) {
                        System.out.println(min_vantakes_id);
                        System.out.println(co);
                        if (co==1) {
                            //第一个数是co,那么，最小占有的就是van_id
                            min_vantakes_id = van_ss.getInt("van_id");
                            System.out.println(min_vantakes_id);
                        }
                        co++;
                        //把其他车厢号的从列表中取出
                        int van_id=van_ss.getInt("van_id");

                        System.out.println(van_id);
                        int i = van_list.indexOf(van_id);
                        //这辆列车中被占有的车厢号也有不属于van_list(seat_type="xx")的
                        if(i!=-1) {
                            System.out.println("i=" + i);
                            van_list.remove(i);
                        }
                        else {
                            System.out.println("van_list_remove的列车不在van_list中");
                        }
                    }
                }
                van_list.add(min_vantakes_id);
                //van_list中存有所有可用的van_id

                int chose_van=0;
                if (van_list.size()!=0){
                    //说明有空的，则剔除列表中的min_vantakes_id
                    int i=van_list.indexOf(min_vantakes_id);
                    van_list.remove(i);
                    //随便选中一个车厢
                    int size=van_list.size();
                    chose_van= new Random().nextInt(size)+1;
                }
                else {
                    //目前没有空的
                    //那么选中min对应车厢
                    chose_van=min_vantakes_id;
                }

                System.out.println("chose_van:"+chose_van);

                //获得到选中的van一共有多少行
                String sql_vanrow="select van_row from "+name_of_train+"_van where van_id="+chose_van;
                preparedStatement=connection.prepareStatement(sql_vanrow);
                ResultSet row_set=preparedStatement.executeQuery();
                int row=0;
                while (row_set.next()){
                    row=row_set.getInt("van_row");
                }
                System.out.println("row:"+row);
                //应该按照seat_type处理车厢内的初始化数据。
                HashMap<String,Integer> seat_inital=initial(row);

                System.out.println("seat_initial_size:"+seat_inital.size());

                //根据选中的van_id查找对应的seat_taken;
                String sql_seat="select seat_id from "+name_of_train+"_takes where van_id="+chose_van+" and start_station="+start_id+" and arrive_station="+arrive_id;
                preparedStatement=connection.prepareStatement(sql_seat);
                ResultSet seat_set=preparedStatement.executeQuery();
                seat_set.first();
                if(seat_set.getRow()!=0) {
                    seat_set.first();
                    while (seat_set.next()) {
                        seat_inital.remove(row_set.getString("seat_id"));
                    }
                }
                //根据hashmap的大小生成一个随机数
                int seat_choose=(int) (new Random().nextInt(seat_inital.size()+1));
                System.out.println("chose_seat:"+seat_choose);

                //复制到数组中？
                Set<String> set=seat_inital.keySet();
                String seat_chosen="";
                int seat_count=0;
                for (String str:set) {
                    if(seat_count==seat_choose){
                        seat_chosen=str;
                    }
                    seat_count++;
                }
                System.out.println("seat!:"+seat_chosen);

                String sql2 = "insert into " + name_of_train + "_takes values(?,?,?,?,?,?,?)";
                preparedStatement = connection.prepareStatement(sql2);
                preparedStatement.setString(1, buy_info.getCustomer_ID());
                preparedStatement.setString(3, buy_info.getTrain_name());
                preparedStatement.setString(2, buy_info.getTaker_ID());
                preparedStatement.setInt(4, start_id);
                preparedStatement.setInt(5, arrive_id);
                preparedStatement.setInt(6, chose_van);
                preparedStatement.setString(7, seat_chosen);
                preparedStatement.execute();


            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        else {
            System.out.println("not right");
        }
        return false;
    }
    private void print(){
        for (int i=0;i<sum_stations;i++) {
            System.out.println("("+stations[i][0]+","+stations[i][1]+")");
            System.out.println("Start_minus");
        }
    }
    public static void main(String[] args){
        //buy_information是由前端生成的
        Buy_info buy_info=new Buy_info("1544376776","155555","g227","宿州东","济南","ED",40);
        Buy_Tickets buy_tickets=new Buy_Tickets(buy_info,0);
        buy_tickets.buy();

    }
    private HashMap<String,Integer> initial(int row){
        HashMap<String,Integer> seat=new HashMap<>();
        if(seat_type.equals("SW")){
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
        else if(seat_type.equals("YD")){
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
        else if(seat_type.equals("ED")){
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

}
