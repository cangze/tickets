package DS_operation;

import DS_operation.Connect;

import DS_operation.*;


import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/**
 * @auther DONG BQ
 * @DATE 2020/11/1118:59
 */
public class Select_train_final {
    private String start,arrive,date;
    private ArrayList<String> start_station_name=new ArrayList<>();//不包含站
    private ArrayList<String> arrive_station_name=new ArrayList<>();//不包含站
    private ArrayList<Temp_info> temp_infos=new ArrayList<>();//不包含站
    private ArrayList<Temp_info> temp_infos_final=new ArrayList<>();
    private ArrayList<Train_info_final> train_info_finals=new ArrayList<>();
    private  ArrayList<Train_leftickets_info> train_leftickets_infos=new ArrayList<>();
    private Connection connection=null;private Connect connect;
    private ArrayList<Integer> filterfinal=new ArrayList<>();
    public Select_train_final(String start,String arrive,String date){
        this.start=start;
        this.arrive=arrive;
        this.date=date;
        connect=new Connect();
        try {

            connect.testConnection();
            connection=connect.getConnection();
        }catch (Exception e){
            e.printStackTrace();
        }
        Fliterall();
        train_leftickets_infos=GetAllLeftickets();

        try {
            connection.close();
            connect.closeconnection();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    /**
     * 按照日期筛选符合要求的车次
     */
    public ArrayList<Integer> Filter_Date(){
        ArrayList<Integer> filter=new ArrayList<>();
        String filter_date="select train_id from train_state where run_date='"+date+"'&& run_state='正常'";
        try {
            PreparedStatement pstm=connection.prepareStatement(filter_date);
            ResultSet rs=pstm.executeQuery();
            rs.first();
            if(rs.getRow()!=0){//判断rs是否有值
                rs.beforeFirst();
                while (rs.next()){
                    int id=rs.getInt("train_id");
                    filter.add(id);
                }
            }
            rs.close();
            pstm.close();
        }catch (SQLException e){
            e.printStackTrace();
        }

        return filter;
    }

    /**
     * 按照时间筛选符合要求的车次
     */
    public ArrayList<Integer> Filter_Datetime(){
        //要在车站的出发时间中找！！！
        ArrayList<Integer> datetimefilter=new ArrayList<>();
        Date time=new Date();
        SimpleDateFormat formatt=new SimpleDateFormat("HH:mm:ss");
        String currenttime=formatt.format(time);
        for (String name:start_station_name) {
            String true_name=name.substring(0,name.length()-1);
            System.out.println(true_name);
            String filter_time = "select train_id from train_info where station_name='" + true_name + "' and start_time >'" + currenttime
                    + "' and train_id in"
                    + "(select train_id tt from train_state where run_date='" + date + "' and run_state='" + "正常" + "')";
            try {
                System.out.println(filter_time);
                if (connection.isClosed()) {
                    connection = connect.getConnection();
                }
                PreparedStatement pstm = connection.prepareStatement(filter_time);
                ResultSet rs = pstm.executeQuery();
                rs.first();
                if (rs.getRow() != 0) {
                    rs.beforeFirst();
                    while (rs.next()) {
                        int id = rs.getInt("train_id");
                        datetimefilter.add(id);
                    }
                }
                System.out.println("最后的datetimefilter结果为\n"+datetimefilter);
                rs.close();
                pstm.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return datetimefilter;

    }


    /**
     * 按照城市查找车次
     * 1.按照城市查找站点
     * 2.按照站点查找符合要求的地址
     */
    public ArrayList<Integer> Filter_city(){
        ArrayList<Integer> filtercity=new ArrayList<>();


        ArrayList<String> start_station=new ArrayList<>();
        ArrayList<String> arrive_station=new ArrayList<>();
        if(start.contains("站")&&(!arrive.contains("站"))){
            if(start.contains("市")) start=start.substring(0,start.length()-1);
            if(arrive.contains("市")) arrive=arrive.substring(0,arrive.length()-1);
            String arrive_s=arrive;
            String sql2="select station_name from station_city where city='"+arrive_s+"'";
            try {

                start_station.add(start);
                if(connection.isClosed()) {
                    connection=connect.getConnection();
                }

                PreparedStatement ptsm = connection.prepareStatement(sql2);
                ResultSet rs2 = ptsm.executeQuery();
                rs2.first();
                if (rs2.getRow() != 0) {
                    while (rs2.next()) {
                        String cc=rs2.getString("station_name");
                        //该station_name不包含“站”
                        //System.out.println(cc);
                        arrive_station.add(cc);
                    }
                }
                rs2.close();

                //我获得了start_station && arrive_station两数组；
                start_station_name=start_station;
                arrive_station_name=arrive_station;
                int arrive_length = arrive_station.size();

                String start_sname = start;
                for (int j = 0; j < arrive_length ; j++) {
                    String arrive_sname = arrive_station.get(j);
                    ArrayList<Integer> ids = Filter_station(start_sname, arrive_sname);
                    Iterator iterator = ids.iterator();
                    while (iterator.hasNext()) {
                        String start_name=start_sname;
                        String arrive_name=arrive_sname;
                        int train_id=(int) iterator.next();
                        Temp_info temp_info=new Temp_info(train_id,start_name,arrive_name);
                        //System.out.println("如果终点站是城市："+temp_info);

                        if (!temp_infos.contains(temp_info)){
                            temp_infos.add(temp_info);
                        }
                        filtercity.add(train_id);
                    }
                }


            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else if((!start.contains("站"))&&arrive.contains("站")){
            if(start.contains("市")) start=start.substring(0,start.length()-1);
            if(arrive.contains("市")) arrive=arrive.substring(0,arrive.length()-1);
            //start是城市
            String sql1="select station_name from station_city where city='"+start+"'";

            try {
                //带有站的！！！
                if(connection.isClosed()) {
                    connection=connect.getConnection();
                }
                arrive_station.add(arrive);

                PreparedStatement ptsm = connection.prepareStatement(sql1);
                ResultSet rs2 = ptsm.executeQuery();
                rs2.first();
                if (rs2.getRow() != 0) {
                    while (rs2.next()) {
                        String ss=rs2.getString("station_name");
                        // System.out.println(ss);
                        /**
                         * 该station_name包含“站”字
                         */
                        start_station.add(ss);
                    }
                }

                //我获得了start_station && arrive_station两数组；

                int start_length = start_station.size();

                String arrive_sname = arrive;

                start_station_name=start_station;
                arrive_station_name=arrive_station;
                for (int j = 0; j < start_length ; j++) {
                    String start_sname = start_station.get(j);
                    ArrayList<Integer> ids = Filter_station(start_sname, arrive_sname);
                    Iterator iterator = ids.iterator();
                    while (iterator.hasNext()) {
                        String start_name=start_sname;
                        String arrive_name=arrive_sname;
                        int train_id=(int) iterator.next();
                        Temp_info temp_info=new Temp_info(train_id,start_name,arrive_name);
                        //  System.out.println("如果始发站是城市:"+temp_info);
                        if (!temp_infos.contains(temp_info)){
                            temp_infos.add(temp_info);
                        }
                        filtercity.add(train_id );
                    }
                }

                rs2.close();
                ptsm.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        else if((!arrive.contains("站"))&&(!start.contains("站"))) {
            if(start.contains("市")) start=start.substring(0,start.length()-1);
            if(arrive.contains("市")) arrive=arrive.substring(0,arrive.length()-1);
            String sql1 = "select station_name from station_city where city='" + start + "'";
            String sql2 = "select station_name from station_city where city='" + arrive + "'";
            try {
                if(connection.isClosed()) {
                    connection=connect.getConnection();
                }
                PreparedStatement ptsm = connection.prepareStatement(sql1);
                ResultSet rs = ptsm.executeQuery();
                rs.first();
                if (rs.getRow() != 0) {
                    while (rs.next()) {
                        start_station.add(rs.getString("station_name"));
                    }
                }
                rs.close();
                ptsm = connection.prepareStatement(sql2);
                ResultSet rs2 = ptsm.executeQuery();
                rs2.first();
                if (rs2.getRow() != 0) {
                    while (rs2.next()) {
                        arrive_station.add(rs2.getString("station_name"));
                    }
                }

                start_station_name=start_station;
                arrive_station_name=arrive_station;
                //我获得了start_station && arrive_station两数组；
                int start_length = start_station.size();
                int arrive_length = arrive_station.size();
                System.out.println("-------------执行城市站点搜索-------------");

                for (int i = 0; i < start_length ; i++) {
                    String start_sname = start_station.get(i);
                    for (int j = 0; j < arrive_length; j++) {
                        String arrive_sname = arrive_station.get(j);
                        System.out.println("城市站点："+start_sname+":"+arrive_sname);
                        ArrayList<Integer> ids = Filter_station(start_sname, arrive_sname);
                        Iterator iterator = ids.iterator();
                        while (iterator.hasNext()) {
                            String start_name=start_sname;
                            String arrive_name=arrive_sname;
                            int train_id=(int)iterator.next();
                            Temp_info tin=new Temp_info(train_id,start_name,arrive_name);
                            if (!temp_infos.contains(tin)){
                                temp_infos.add(tin);
                            }
                            // System.out.println(tin);
                            filtercity.add(train_id);
                        }
                    }
                }
                rs.close();
                ptsm.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else {
            start_station_name.add(start);
            arrive_station_name.add(arrive);
            ArrayList<Integer> ids = Filter_station(start, arrive);
            Iterator iterator = ids.iterator();
            while (iterator.hasNext()) {
                String start_name = start.substring(0,start.length()-1);
                String arrive_name = arrive.substring(0,arrive.length()-1);;
                int train_id = (int) iterator.next();
                Temp_info temp_info = new Temp_info(train_id, start_name, arrive_name);
                //  System.out.println("如果始发站是城市:"+temp_info);
                if (!temp_infos.contains(temp_info)) {
                    temp_infos.add(temp_info);
                }
                filtercity.add(train_id);
            }

        }
        return filtercity;
    }

    /**
     * 按照车站名称查找符合要求的地址
     */
    public ArrayList<Integer> Filter_station(String start_staion,String arrive_station){
        start_staion=start_staion.substring(0,start_staion.length()-1);
        arrive_station=arrive_station.substring(0,arrive_station.length()-1);
        ArrayList<Integer> filterstation=new ArrayList<>();
        String filter="select train_id from train_info " +
                "where station_name='"+start_staion+"'" +
                " and train_id in" +
                "(select train_id tt from train_info b where station_name='"+arrive_station+"')";

        //select train_id from train_info where station_name="北京南" and train_id in
        //    -> (select train_id tt from train_info a where station_name="天津西");
        try {
            if(connection.isClosed()) {
                connection=connect.getConnection();
            }
            PreparedStatement ptsm=connection.prepareStatement(filter);
            ResultSet rs=ptsm.executeQuery();
            rs.first();
            if(rs.getRow()!=0){
                rs.beforeFirst();
                while (rs.next()){
                    int ss=rs.getInt("train_id");
                    filterstation.add(ss);
                    //  System.out.println("按照"+start_staion+"-"+arrive_station+"站点得到的train_id="+ss);
                }
            }
            rs.close();
            ptsm.close();
        }catch (SQLException e){
            e.printStackTrace();
        }



        return filterstation;
    }



    public ArrayList<Temp_info> getTemp_infos() {
        return temp_infos;
    }



    /**
     * 对时间/时间和日期 && 车站|城市的返回值取交集
     */

    public ArrayList<Train_info_new> Fliterall(){
        ArrayList<Train_info_new> atins=new ArrayList<>();
        ArrayList<Integer> filterdate=new ArrayList<>();
        ArrayList<Integer> filtercity=new ArrayList<>();

        /**
         * 按照城市查询
         */

        filtercity=Filter_city();



        /**
         * 首先判断是否与当前日期一致
         */
        Date currentdate=new Date();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        int datecompare=0;
        //datecompare=1,大于或等于 datecompare=-1小于
        //用当前日期与目标日期比较(因为当前日期<=目标日期)
        try {
            Date targetdate = simpleDateFormat.parse(date);
            datecompare=currentdate.compareTo(targetdate);
            System.out.println(datecompare);

            if(datecompare==1){
                //说明当前日期等于目标日期
                //按照日期时间筛选；
                filterdate=Filter_Datetime();

            }
            else if(datecompare==-1){
                //当前日期小于目标日期
                //按照日期筛选
                filterdate=Filter_Date();
            }

        }catch (ParseException e){
            e.printStackTrace();
        }



        /**
         * Filterdatetime的结果是所有起点为star_name_staion的且出发时间大于当前时间的train_id;
         * Filterdate的结果是所有出发日期是targetdate的train_id;
         */
//        filterfinal=(ArrayList<Integer>) filtercity.clone();
//        filterfinal.retainAll(filterdate);
//               //用set去重特点求交集
        HashSet<Integer> hashSet=new HashSet<Integer>();
        //list3储存交集
        List<Integer> list3 = new ArrayList<Integer>();
        for (Integer i:filtercity) {
            hashSet.add(i);
        }
        for (Integer i:filterdate) {
            if(hashSet.add(i)==false){//add失败则认为有相同元素，存放到list3
                list3.add(i);
//                System.out.print(+"  ");
            }
        }
        System.out.println("交集结果为\n"+list3);
        filterfinal=(ArrayList<Integer>) list3;
        System.out.println(temp_infos);
//        Iterator iterator2=filterfinal.iterator();
//        while (iterator2.hasNext()){
//            int i=(int)iterator2.next();
//            //System.out.println("最后的id"+i);
//        }
        /**
         * 根据id组织列车信息
         * 1.需要查询train_id对应的列车中:
         * 有<train_id,start_station_name,arrive_station_name>列表
         * start_station: id,start_arrive_time-->start_time
         * arrive_station:id,arrive_start_time-->arrive_time
         * 2.train_id对应的出发时间=date
         * 3.train_id对应的时间间隔?
         * 1>arrive_time<start_time:隔天了
         *
         */
        try {

            ArrayList<Temp_info>temp_infoss=new ArrayList<>();
            temp_infoss=(ArrayList<Temp_info>) temp_infos.clone();
            ArrayList<Temp_info> temp_infosss=new ArrayList<>();
            temp_infosss=(ArrayList<Temp_info>)temp_infos.clone();
            System.out.println(temp_infos.size()+"  "+temp_infoss.size()+" "+temp_infosss.size());
            Iterator iterator=temp_infoss.iterator();

            while (iterator.hasNext()) {
                //按照train_id对temp_infos里面的数据进行筛选
                Temp_info temp_info=(Temp_info) iterator.next();
                int train_id_temp=temp_info.getTrain_id();
                //System.out.println(filterdate.indexOf(train_id_temp));
                if(filterfinal.indexOf(train_id_temp)==-1){
                    temp_infosss.remove(temp_info);
                }

                // System.out.println("test1: "+temp_info);
            }
            System.out.println(temp_infosss);
            temp_infos_final=(ArrayList<Temp_info>) temp_infosss.clone();

//            temp_infos=new ArrayList<>(temp_infoss.size());
//            temp_infos.addAll(temp_infoss);
            //  System.out.println(temp_infos_final.size());


            Iterator iterator1=temp_infos_final.iterator();
            while (iterator1.hasNext()) {
                Temp_info temp_info = (Temp_info) iterator1.next();
                //System.out.println("test3:"+temp_info);
                String start_name_t = temp_info.getStart_name();
                String arrive_name_t = temp_info.getArrive_name();
                //tempinfo 中站点名称含有站
                String start_name="",arrive_name="";
                
                if(start_name_t.contains("站"))
                start_name=start_name_t.substring(0,start_name_t.length()-1);
                else {
					start_name=start_name_t;
				}
                if(arrive_name_t.contains("站"))
                arrive_name=arrive_name_t.substring(0,arrive_name_t.length()-1);
                else {
					arrive_name=arrive_name_t;
				}
                int train_id = temp_info.getTrain_id();
                String sql_start = "select * from train_info where train_id=" + train_id + " and station_name='" + start_name + "'";
                String sql_arrive = "select * from train_info where train_id=" + train_id + " and station_name='" + arrive_name + "'";
                System.out.println(sql_arrive);
                String sql_train = "select train_name,start_city,over_city from train_sum_info where train_id=" + train_id;
                PreparedStatement preparedStatement = connection.prepareStatement(sql_start);
                ResultSet set1 = preparedStatement.executeQuery();
                set1.first();
                int start_station_id = set1.getInt("station_id");
                Time start_time = set1.getTime("start_time");
                //  System.out.println("出发时间"+start_time);

                preparedStatement = connection.prepareStatement(sql_arrive);
                ResultSet set2 = preparedStatement.executeQuery();
                set2.first();
                int arrive_station_id = set2.getInt("station_id");
                //System.out.println(arrive_station_id);
                Time arrive_time = set2.getTime("arrive_time");

                preparedStatement = connection.prepareStatement(sql_train);
                ResultSet set3 = preparedStatement.executeQuery();
                set3.first();
                String train_name = set3.getString("train_name");
                String train_start=set3.getString("start_city");
                String train_arrive=set3.getString("over_city");
                String sql_ss="select station_id from train_info where train_id="+train_id+" and station_name='"+train_start+"'";
                String sql_ss2="select station_id from train_info where train_id="+train_id+" and station_name='"+train_arrive+"'";

                preparedStatement=connection.prepareStatement(sql_ss);
                ResultSet set4=preparedStatement.executeQuery();
                set4.first();
                int start_id=set4.getInt("station_id");
                preparedStatement=connection.prepareStatement(sql_ss2);
                ResultSet set5=preparedStatement.executeQuery();
                set5.first();
                int arrive_id=set5.getInt("station_id");
                if (arrive_station_id > start_station_id) {
                    System.out.println("arrive_id-start_id:"+arrive_station_id+"  "+start_station_id);
                    Train_info_final info_final = new Train_info_final();
                    info_final.setTrain_id(train_id);
                    info_final.setTrain_name(train_name);
                    info_final.setStart_station_id(start_station_id);
                    info_final.setStart_station_name(start_name_t);
                    info_final.setArrive_station_id(arrive_station_id);
                    info_final.setArrive_station_name(arrive_name_t);
                    info_final.setStart_time(start_time);
                    info_final.setArrive_time(arrive_time);
                    info_final.setTrain_start_s(train_start);
                    info_final.setTrain_arrive_s(train_arrive);
                    info_final.setTrain_start_id(start_id);
                    info_final.setTrain_arrive_id(arrive_id);
//                    System.out.println(info_final);
                    train_info_finals.add(info_final);
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(train_info_finals);
        return atins;
    }

    public ArrayList<Train_info_final> getTrain_info_finals() {
        return train_info_finals;
    }

    public ArrayList<Integer> getFilterfinal() {
        return filterfinal;
    }

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
    public ArrayList<Train_leftickets_info> GetAllLeftickets(){
        ArrayList<Train_leftickets_info> infos=new ArrayList<>();
        //train_info_finals里有所有的train_id,train_start,train_arrive,start_station_id,arrive_station_id
        Iterator iterator=train_info_finals.iterator();
        while (iterator.hasNext()){
            Train_info_final info_final=(Train_info_final)iterator.next();
            Train_leftickets_info info=new Check_current_Leftickets(info_final.getTrain_id(),info_final.getTrain_start_id(),info_final.getTrain_arrive_id(),info_final.getStart_station_id(),info_final.getArrive_station_id()).getbystationid();
            infos.add(info);
        }

        return infos;
    }

    public ArrayList<Train_leftickets_info> getTrain_leftickets_infos() {
        return train_leftickets_infos;
    }

    public static void main(String[] args){
        Select_train_final select_train_final=new Select_train_final("北京","天津","2020-11-16");
//        select_train_final.Fliterall();
        ArrayList<Train_leftickets_info> arrayList1=select_train_final.getTrain_leftickets_infos();
        Iterator iterator1=arrayList1.iterator();
        while (iterator1.hasNext()){
            Train_leftickets_info train_info_final=(Train_leftickets_info)iterator1.next();
           // System.out.println("train_TICKETS_final az:"+train_info_final);
        }

    }
}
