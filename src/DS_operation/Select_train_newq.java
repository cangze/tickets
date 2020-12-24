package DS_operation;

import DS_operation.Connect;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.SimpleTimeZone;

/**
 * @auther DONG BQ
 * @DATE 2020/10/30 18:33
 */
public class Select_train_newq {
    private ArrayList<LeftTickets_info> leftTickets_infos;
    private ArrayList<Train_info_new> train_through=new ArrayList<>();

    public Select_train_newq(String start_city,String arrive_city,String date){
        //
        //
        String sql="select distinct train_name from train_sum_info where start_date ='"+date+"'";


        //获取到的当前所有列车名单
        ArrayList<String> train_list=new ArrayList<>();

        //获取到的所有能够直达的列车名单


        //获取到的所有直达列车的余票信息
        leftTickets_infos=new ArrayList<>();

        try{
            Connect connect=new Connect();
            connect.testConnection();
            Connection connection=connect.getConnection();

            PreparedStatement pr=connection.prepareStatement(sql);
            ResultSet rs=pr.executeQuery();

            while (rs.next()){
                train_list.add(rs.getString("train_name"));
            }

            Iterator it_select=train_list.iterator();
            while (it_select.hasNext()){
                String name=it_select.next().toString();


               // String sql2="select * from "+name+"where station_name='"+start_city+"' or city='"+start_city+"'";
               //String sql2="select * from "+name+"where station_name=?";
                String sql2="select * from "+name+"_info"+" where station_name='"+start_city+"'"+" or city= '"+start_city+"'";
                String sql3="select * from "+name+"_info"+" where station_name='"+arrive_city+"'"+" or city= '"+arrive_city+"'";
                pr=connection.prepareStatement(sql2);
              //  pr.setString(1,"淄博");
                ResultSet r1=pr.executeQuery();
                while (r1.next()){
                    System.out.println(name+"start_stationid:"+r1.getInt("station_id"));
                }
                pr=connection.prepareStatement(sql3);
                ResultSet r2=pr.executeQuery();
                while (r2.next()){
                    System.out.println(name+"arrive_stationid:"+r2.getInt("station_id"));
                }
                r1.first();r2.first();
                System.out.println(r1.getRow()+"  "+r2.getRow());
                if(r1.getRow()!=0&&r2.getRow()!=0){
                    r1.beforeFirst();r2.beforeFirst();
                    while (r1.next()){
                        while (r2.next()){
                            Train_info_new tin=new Train_info_new();
                            String sql4="select * from train_sum_info where train_name='"+name+"'";
                            pr=connection.prepareStatement(sql4);
                            ResultSet r3=pr.executeQuery();
                            String start_date=null;
                            String arrive_date=null;
                            while (r3.next()){
                                start_date=r3.getDate("start_date").toString();
                                arrive_date=r3.getDate("over_date").toString();
                            }
                            String start_station_name=r1.getString("station_name");
                            int start_station_id=r1.getInt("station_id");
//                            String start_date=r1.getDate("start_date").toString();
                            Time start_time_sql=r1.getTime("start_time");
//                                    r1.getTime("start_time");
                            System.out.println(start_time_sql.toString());
                            SimpleDateFormat formatt=new SimpleDateFormat("HH:mm:ss");
                            String start_time=formatt.format(start_time_sql);

                            double costa=r1.getDouble("cost");

                            String arrive_station_name=r2.getString("station_name");
                            int arrive_station_id=r2.getInt("station_id");
//                            String arrive_date=r2.getDate("arrive_date").toString();
                            Time start_time_sql2=r2.getTime("arrive_time");
//                                    r1.getTime("start_time");
                            System.out.println(start_time_sql.toString());
                            String arrive_time=formatt.format(start_time_sql2);
                            double costb=r2.getDouble("cost");
                            if(arrive_station_id>start_station_id) {
                                String[] ss = start_date.split("-");
                                String[] as = arrive_date.split("-");
                                String[] sst = start_time.split(":");
                                String[] ast = arrive_time.split(":");
                                int nextday = 0;
                                if (start_date.equals(arrive_date)) {
                                    nextday = 0;//同一天到达
                                } else {
                                    nextday = Integer.parseInt(ss[2]) - Integer.parseInt(as[2]);

                                }
                                String time_cost;
                                //计算
                                int start_hour = Integer.parseInt(sst[0]);
                                int arrive_hour = Integer.parseInt(ast[0]);
                                int start_minutes = Integer.parseInt(sst[1]);
                                int arrive_minutes = Integer.parseInt(ast[1]);
                                int sum_minutes = Math.abs((start_hour * 60 + start_minutes) - (arrive_hour * 60 + arrive_minutes));
                                int hour = sum_minutes / 60 + nextday * 24;
                                int minutes = (sum_minutes - ((sum_minutes / 60) * 60));
                                time_cost = " " + hour + ":" + minutes;

                                tin.setTrian_name(name);
                                tin.setStart_station(start_station_name);
                                tin.setStart_station_id(start_station_id);
                                tin.setArrive_station(arrive_station_name);
                                tin.setArrive_station_id(arrive_station_id);
                                tin.setStart_date(start_date);
                                tin.setStart_time(start_time);
                                tin.setArrive_date(arrive_date);
                                tin.setArrive_time(arrive_time);
                                tin.setCost(costb - costa);
                                tin.setNextday(nextday);
                                tin.setTime_cost(time_cost);


                                Date currenttime=new Date();
                                SimpleDateFormat formattt=new SimpleDateFormat("HH:mm:ss");
                                Time time=new Time(currenttime.getTime());

                                boolean flag=smaller(time,start_time_sql);
                                System.out.println(flag);
                                if (flag){
                                    train_through.add(tin);
                                }

                            }
                        }
                    }
                }
                r1.first();r2.first();

            }
            //到此获得了所有直达的列车名字和到达时间等的信息
            if(train_through.size()!=0){
                System.out.println(train_through.size());
                  //size是有几个元素
                Iterator iterator=train_through.iterator();
                while (iterator.hasNext()) {
                    Train_info_new tin=(Train_info_new)iterator.next();
                    String sql_vans="select distinct van_type from "+tin.getTrian_name()+"_van";
                    //我们还需要查询余票-<train_name> 对应的车厢种类--方便查询余票?
                    pr=connection.prepareStatement(sql_vans);
                    ResultSet rs_vans=pr.executeQuery();
                    ArrayList<String> vans_type=new ArrayList<>();
                    //生成所有需要查询的车厢列表信息
                    while(rs_vans.next()){
                        String type=rs_vans.getString("van_type");
                        vans_type.add(type);
                       // System.out.println(type);
                    }
                     //按照该列表查询余票信息
                    String sql_tickets="select * from "+tin.getTrian_name()+"_leftickets"+" where start_id=? and arrive_id=?";
                    pr=connection.prepareStatement(sql_tickets);
                    pr.setInt(1,tin.getStart_station_id());
                    pr.setInt(2,tin.getArrive_station_id());
                    ResultSet rs_tickets=pr.executeQuery();
                    rs_tickets.first();
                      //生成iA-iB的
                    if(rs_tickets.getRow()!=0) {
                        Iterator van_itera = vans_type.iterator();
                        while (van_itera.hasNext()) {
                            String van_type = van_itera.next().toString();
                            //van_type不应包含餐车
                            if(!van_type.equals("CC")) {
                                int num = rs_tickets.getInt(van_type + "_tickets");
                                //System.out.println(num);
                                LeftTickets_info left = new LeftTickets_info(tin.getTrian_name(), van_type, num);
                                leftTickets_infos.add(left);
                            }
                            else {
                                //无动作
                            }
                          }
                    }
                    else {
                        //表示该趟列车没有找到余票
                        System.out.println("CAN'T FIND NUMBER");
                    }

                    System.out.println(tin);
                }
                Iterator left_it=leftTickets_infos.iterator();
                while (left_it.hasNext()){
                    LeftTickets_info info=(LeftTickets_info) left_it.next();
                    //按照车名分组?
                    System.out.println(info);
                }
            }
            else {//如果直达列车信息数为0，返回结果为not found

            }


        }catch (SQLException e1){
            e1.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public ArrayList<LeftTickets_info> getLeftTickets_infos() {
        return leftTickets_infos;
    }

    public ArrayList<Train_info_new> getTrain_through() {
        return train_through;
    }

    public boolean smaller(Time timea,Time timeb){
        boolean flag=false;
        //如果timea<timeb返回ture
        String[] timeAString=timea.toString().split(":");
        String[] timeBString=timeb.toString().split(":");
        int houra=Integer.parseInt(timeAString[0]);
        int hourb=Integer.parseInt(timeBString[0]);
        int minua=Integer.parseInt(timeAString[1]);
        int minub=Integer.parseInt(timeBString[1]);
        int secoa=Integer.parseInt(timeAString[2]);
        int secob=Integer.parseInt(timeBString[2]);

        if(houra<hourb){
            flag=true;
        }else{
            if(minua<minub){
                flag=true;
            }else {
                if(secoa<secob){
                    flag=true;
                }

            }
        }
        return flag;
    }

    public static void main(String[] args){
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        String createTime = dateFormat.format(now);
        System.out.println(createTime);
        Select_train_newq s=new Select_train_newq("济南","章丘", createTime);
        ArrayList<Train_info_new> at=s.getTrain_through();
        ArrayList<LeftTickets_info> al=s.getLeftTickets_infos();
        Iterator iterator=at.iterator();

        String name="";
        while (iterator.hasNext()){
            Iterator iterator1=al.iterator();
            Train_info_new tin=(Train_info_new)iterator.next();
            name=tin.getTrian_name();
            System.out.println("WEFIND");
            System.out.println(tin);
            while (iterator1.hasNext()) {
                LeftTickets_info li = (LeftTickets_info) iterator1.next();
                if(li.getTrain_name().equals(name)){
                    System.out.println(li);
                }
            }
        }
    }
}
