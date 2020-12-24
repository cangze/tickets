package DS_operation;

import DS_operation.Connect;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

/**
 * @auther DONG BQ
 * @DATE 2020/11/1514:11
 */
public class Select_trans_train {

    private String start, arrive, date;
    private ArrayList<String> start_station_name = new ArrayList<>();//不包含站
    private ArrayList<String> arrive_station_name = new ArrayList<>();//不包含站
    private ArrayList<Temp_info> temp_infos = new ArrayList<>();//不包含站
    private ArrayList<Temp_info> temp_infos_final = new ArrayList<>();
    private ArrayList<Train_info_final> train_info_finals = new ArrayList<>();
    private ArrayList<Train_leftickets_info> train_leftickets_infos = new ArrayList<>();
    private Connection connection = null;
    private Connect connect=null;
    private ArrayList<Temp_trainid_station_name> start_staions=new ArrayList<>();
    private ArrayList<Temp_trainid_station_name> arrive_staions=new ArrayList<>();
    private ArrayList<Trans_temp_info> trans_temp_infos=new ArrayList<>();
    private ArrayList<Trans_info_finals> transInfoFinals=new ArrayList<>();
    public Select_trans_train(String start, String arrive, String date) {
        this.start = start;
        this.arrive = arrive;
        this.date = date;
        try {
            connect = new Connect();
            connect.testConnection();
            connection = connect.getConnection();
            FinalFilter();
            Finalfinal();
            connection.close();
            connect.closeconnection();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Connection getConnection() {
        return connection;
    }

    /**
     * 按照日期筛选符合要求的车次
     */
    public ArrayList<Integer> Filter_Date() {
        ArrayList<Integer> filter = new ArrayList<>();
        String filter_date = "select train_id from train_state where run_date='" + date + "'&& run_state='正常'";
        try {
            PreparedStatement pstm = connection.prepareStatement(filter_date);
            ResultSet rs = pstm.executeQuery();
            rs.first();
            if (rs.getRow() != 0) {//判断rs是否有值
                rs.beforeFirst();
                while (rs.next()) {
                    int id = rs.getInt("train_id");
                    filter.add(id);
                }
            }
            rs.close();
            pstm.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return filter;
    }

    /**
     * 按照时间筛选符合要求的车次
     */
    public ArrayList<Integer> Filter_Datetime() {
        // select train_id from train_sum_info
        // where start_time>'07:00:00'
        // and train_id in
        // (select train_id tt from train_state where run_date='2020-11-20' and run_state='正常');
        ArrayList<Integer> datetimefilter = new ArrayList<>();
        Date time = new Date();
        SimpleDateFormat formatt = new SimpleDateFormat("HH:mm:ss");
        String currenttime = formatt.format(time);
        String filter_time = "select train_id from train_sum_info where start_time >'" + currenttime
                + "' and train_id in"
                + "(select train_id tt from train_state where run_date='" + date + "' and run_state='正常')";
        try {
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
            rs.close();
            pstm.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return datetimefilter;

    }


    /**
     * 按照城市查找车次保存<train_id>
     */
    public ArrayList<Integer> Filter_city(String current_start,int type) {
        ArrayList<Integer> filter_start_city = new ArrayList<>();
        ArrayList<String> startname=new ArrayList<>();
        /**
         * 先找起始地点对应的station--再找train_id
         */
        if(!current_start.contains("站")) {
            if(current_start.contains("市")){
                current_start=current_start.substring(0,current_start.length()-1);
            }
            String sql1 = "select station_name from station_city where city='"+current_start+"'";
            if(connection==null){
                connection=connect.getConnection();
            }
            try{
                PreparedStatement preparedStatement=connection.prepareStatement(sql1);
                ResultSet resultSet=preparedStatement.executeQuery();
                resultSet.first();
                if(resultSet.getRow()!=0){
                    resultSet.beforeFirst();
                    while (resultSet.next()){
                        startname.add(resultSet.getString("station_name"));
                    }
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        else {
            startname.add(current_start);
        }
        System.out.println(startname);
        /**
         *按照start_station_name找到train_id
         */
        if(startname.size()!=0) {
            for (int i = 0; i < startname.size(); i++) {
                String start_t = startname.get(i);
                start_t = start_t.substring(0, start_t.length() - 1);
                String sql = "select * from train_info where station_name='" + start_t + "'";
                try {
                    PreparedStatement preparedStatement = connection.prepareStatement(sql);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    resultSet.first();
                    if (resultSet.getRow() != 0) {
                        resultSet.beforeFirst();
                        while (resultSet.next()) {
                            int train_id = resultSet.getInt("train_id");
                            int station_id = resultSet.getInt("station_id");
                            String station_name=start_t;
                            //表明是起始站还是目的地的结果
                            Temp_trainid_station_name tempTrainidStationName=new Temp_trainid_station_name(train_id,station_id,station_name);
                            if(type==0){
                                start_staions.add(tempTrainidStationName);
                            }else {
                                arrive_staions.add(tempTrainidStationName);
                            }
                            filter_start_city.add(train_id);
                        }
                    }
                    resultSet.close();
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println("起始站的train_id--station_id\n"+filter_start_city);
        return filter_start_city;

    }

    public ArrayList<Temp_trainid_station_name> getArrive_staions() {
        return arrive_staions;
    }

    public ArrayList<Temp_trainid_station_name> getStart_staions() {
        return start_staions;
    }

    /**
     * 按照车站名称查找符合要求的地址
     */
    public ArrayList<Integer> Filter_station(String start_staion, String arrive_station) {
        start_staion = start_staion.substring(0, start_staion.length() - 1);
        arrive_station = arrive_station.substring(0, arrive_station.length() - 1);
        ArrayList<Integer> filterstation = new ArrayList<>();
        String filter = "select train_id from train_info " +
                "where station_name='" + start_staion + "'" +
                " and train_id in" +
                "(select train_id tt from train_info b where station_name='" + arrive_station + "')";

        //select train_id from train_info where station_name="北京南" and train_id in
        //    -> (select train_id tt from train_info a where station_name="天津西");
        try {
            PreparedStatement ptsm = connection.prepareStatement(filter);
            ResultSet rs = ptsm.executeQuery();
            rs.first();
            if (rs.getRow() != 0) {
                rs.beforeFirst();
                while (rs.next()) {
                    int ss = rs.getInt("train_id");
                    filterstation.add(ss);
                    //  System.out.println("按照"+start_staion+"-"+arrive_station+"站点得到的train_id="+ss);
                }
            }
            rs.close();
            ptsm.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return filterstation;
    }

    public void FinalFilter(){

        ArrayList<Integer> temp=Filter_city(start,0);

        ArrayList<Integer> temp2=Filter_city(arrive,1);

        //两个的并集与 直达  做差集
        Select_train_final train_final=new Select_train_final(start,arrive,date);

        ArrayList<Integer> temp3=train_final.getFilterfinal();
        System.out.println("temp3\n"+temp3);
        /**
         该temp.removeALL()是去掉A-B直达的列车，也就是不能直达才换乘？
         * */
        temp.removeAll(temp3);
        System.out.println("temp\n"+temp);

        temp2.removeAll(temp3);
        System.out.println("temp2\n"+temp2);
        /**
         * 按照时间筛选train_id
         */
        ArrayList<Integer> filterdate=new ArrayList<>();
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

        //temp,temp2分别与filterdate取交集
        HashSet<Integer> hashSet=new HashSet<Integer>();
        //list3储存交集
        ArrayList<Integer> list3 = new ArrayList<Integer>();
        for (Integer i:temp) {
            hashSet.add(i);
        }
        for (Integer i:filterdate) {
            if(hashSet.add(i)==false){//add失败则认为有相同元素，存放到list3
                list3.add(i);
            }
        }
        System.out.println("交集结果1为\n"+list3);

        HashSet<Integer> hashSet2=new HashSet<Integer>();
        //list3储存交集
        ArrayList<Integer> list4 = new ArrayList<Integer>();
        for (Integer i:temp2) {
            hashSet2.add(i);
        }
        for (Integer i:filterdate) {
            if(hashSet2.add(i)==false){//add失败则认为有相同元素，存放到list3
                list4.add(i);
//                System.out.print(+"  ");
            }
        }
        System.out.println("交集结果2为\n"+list4);


        /**
         *
         * 现在有结果的是list3,list4
         * 按照list3对start_staions做删减
         * 按照list4对arrive_staions做删减
         * 查看上述train_id 对应的车站有没有可换乘的(换乘id>start_id)(换乘id<arrive_id)
         */
        ArrayList<Temp_trainid_station_name> trainidStationNames=(ArrayList<Temp_trainid_station_name>)getStart_staions().clone();

        ArrayList<Temp_trainid_station_name>temp_trainid_station_names=(ArrayList<Temp_trainid_station_name>)getStart_staions().clone();
//        System.out.println(temp_trainid_station_names);
        Iterator iterator=temp_trainid_station_names.iterator();
        while (iterator.hasNext()){
            Temp_trainid_station_name tempTrainidStationName=(Temp_trainid_station_name)iterator.next();
            int train_id=tempTrainidStationName.getTrain_id();
            //System.out.println(train_id+"  "+list3.indexOf(train_id));
            if(list3.indexOf(train_id)==-1){
                trainidStationNames.remove(tempTrainidStationName);
            }
        }
        System.out.println(trainidStationNames);


        ArrayList<Temp_trainid_station_name> arrive_trainidStationNames=(ArrayList<Temp_trainid_station_name>)getArrive_staions().clone();

        ArrayList<Temp_trainid_station_name> temp_trainid_station_names1=(ArrayList<Temp_trainid_station_name>)getStart_staions().clone();
//        System.out.println(temp_trainid_station_names1);
        Iterator iterator1=temp_trainid_station_names1.iterator();
        while (iterator1.hasNext()){
            Temp_trainid_station_name tempTrainidStationName=(Temp_trainid_station_name)iterator1.next();
            int train_id=tempTrainidStationName.getTrain_id();
            if(list3.indexOf(train_id)==-1){
                arrive_trainidStationNames.remove(tempTrainidStationName);
            }
        }
        System.out.println(arrive_trainidStationNames);

        /**
         * 现在trainidStationNames保存了train_id,start_station_name,id;
         * arrive_trainidStationNames保存了train_id,arrive_station_name,id;
         * 查看上述train_id 对应的车站有没有可换乘的(换乘id>start_id)(换乘id<arrive_id)
         *
         */
        trans_temp_infos=new ArrayList<>();
        Iterator startIter=trainidStationNames.iterator();

        while (startIter.hasNext()){
            Temp_trainid_station_name starttarin=(Temp_trainid_station_name)startIter.next();
            //得到所有的中转站
            int start_trainid=starttarin.getTrain_id();
            int station_id_st=starttarin.getStation_id();
            String start_name=starttarin.getStation_name();
            ArrayList<Temp_trainid_station_name> startss=new ArrayList<>();
            String stationsql="select * from train_info where train_id="+start_trainid+" and station_id>"+station_id_st;
            if(connection==null){
                connection=connect.getConnection();
            }
            try{
                PreparedStatement preparedStatement=connection.prepareStatement(stationsql);
                ResultSet resultSet=preparedStatement.executeQuery();
                resultSet.first();
                if(resultSet.getRow()!=0){
                    resultSet.beforeFirst();
                    while (resultSet.next()){
                        int current_id=resultSet.getInt("station_id");
                        String name=resultSet.getString("station_name");
                        Temp_trainid_station_name tempe=new Temp_trainid_station_name(start_trainid,current_id,name);
                        startss.add(tempe);
                    }

                    System.out.println("-----对信息A查找每个信息B---");
                    System.out.println("得到的车站信息A----"+startss);

                    Iterator arriveIter=arrive_trainidStationNames.iterator();
                    while (arriveIter.hasNext()){
                        Temp_trainid_station_name arrivetarin=(Temp_trainid_station_name)arriveIter.next();
                        int arrive_trainid=arrivetarin.getTrain_id();
                        int station_id_ar=arrivetarin.getStation_id();
                        String arrive_name=arrivetarin.getStation_name();
                        ArrayList<Temp_trainid_station_name> arivess=new ArrayList<>();
                        String stationsql2="select * from train_info where train_id="+arrive_trainid+" and station_id<"+station_id_ar;
                        //System.out.println("stationsql2"+stationsql2);
                        if(connection==null){
                            connection=connect.getConnection();
                        }
                        try {
                            PreparedStatement preparedStatement2 = connection.prepareStatement(stationsql2);
                            ResultSet resultSet2 = preparedStatement2.executeQuery();
                            resultSet2.first();
                            if (resultSet2.getRow() != 0) {
                                resultSet2.beforeFirst();
                                while (resultSet2.next()) {
                                    int current_id = resultSet2.getInt("station_id");

                                    String name = resultSet2.getString("station_name");
                                    Temp_trainid_station_name tempe = new Temp_trainid_station_name(arrive_trainid, current_id, name);
                                    arivess.add(tempe);
                                }
                              //  System.out.println("得到的车站信息B----"+arivess);
                                //比较有没有一样的站点名称
                                Iterator currentIter=startss.iterator();
                                while (currentIter.hasNext()){
                                    Temp_trainid_station_name tempTrainidStationName=(Temp_trainid_station_name)currentIter.next();
                                    String currentname=tempTrainidStationName.getStation_name();
                                    int staion_ida=tempTrainidStationName.getStation_id();
                                    
                                    Iterator currentIter2=arivess.iterator();
                                    while (currentIter2.hasNext()){
                                        Temp_trainid_station_name tempTrainidStationName2=(Temp_trainid_station_name)currentIter2.next();
                                        String currentname2=tempTrainidStationName2.getStation_name();
                                        int staion_idb=tempTrainidStationName2.getStation_id();
                                        
                                        if(currentname.equals(currentname2)){
                                            Trans_temp_info temp_info=new Trans_temp_info();
                                            temp_info.setTrainidA(start_trainid);
                                            temp_info.setStart_name(start_name);
                                            temp_info.setTrain_start_id(station_id_st);
                                            temp_info.setMiddle_name(currentname);
                                            temp_info.setTrain_middle_idA(staion_ida);
                                            
                                            temp_info.setTrain_middleidB(staion_idb);
                                            
                                            temp_info.setArrive_name(arrive_name);
                                            temp_info.setTrain_arrive_id(station_id_ar);
                                            temp_info.setTrain_idB(arrive_trainid);
                                            trans_temp_infos.add(temp_info);
                                            
                                            System.out.println("在这停顿"+temp_info);
                                        }
                                    }
                                }
                            }

                        } catch (SQLException e){
                        e.printStackTrace();
                        }
                    }
                }
            }catch (SQLException e){
                e.printStackTrace();
            }


        }

        System.out.println("最后结果"+trans_temp_infos);



    }

    public ArrayList<Trans_temp_info> getTrans_temp_infos() {
        return trans_temp_infos;
    }
    public void FinalCalculater(){
        //对trans_temp_infos进行遍历，计算距离，查询余票信息；
        ArrayList<Trans_temp_info> trans_temp_infos=(ArrayList<Trans_temp_info>)getTrans_temp_infos().clone();
        Iterator iterator=trans_temp_infos.iterator();
        while (iterator.hasNext()){
            Trans_temp_info trans_temp_info=(Trans_temp_info)iterator.next();
            String start_name=trans_temp_info.getStart_name()+"站";
            String middle_name=trans_temp_info.getMiddle_name()+"站";
            String arrive_name=trans_temp_info.getArrive_name()+"站";
            String sql="select * from station_city where station_name='"+start_name+"'";
            String sql2="select * from station_city where station_name='"+middle_name+"'";
            String sql3="select * from station_city where station_name='"+arrive_name+"'";
            String distance1="999999999.0",distance2="99999999.0";
            double[][] jingweidu=new double[3][2];
            if(connection==null){
                connection=connect.getConnection();
            }
            try{
                PreparedStatement preparedStatement=connection.prepareStatement(sql);
                ResultSet r1=preparedStatement.executeQuery();
                r1.first();
                if(r1.getRow()!=0){
                    r1.beforeFirst();
                    while (r1.next()){
                        double jingdu=r1.getDouble("jingdu");
                        double weidu=r1.getDouble("weidu");
                        jingweidu[0][0]=jingdu;
                        jingweidu[0][1]=weidu;
                    }
                }
                preparedStatement=connection.prepareStatement(sql2);
                ResultSet r2=preparedStatement.executeQuery();
                r2.first();
                if(r2.getRow()!=0){
                    r2.beforeFirst();
                    while (r2.next()){
                        double jingdu=r2.getDouble("jingdu");
                        double weidu=r2.getDouble("weidu");
                        jingweidu[1][0]=jingdu;
                        jingweidu[1][1]=weidu;
                    }
                }
                preparedStatement=connection.prepareStatement(sql3);
                ResultSet r3=preparedStatement.executeQuery();
                r3.first();
                if(r3.getRow()!=0){
                    r3.beforeFirst();
                    while (r3.next()){
                        double jingdu=r3.getDouble("jingdu");
                        double weidu=r3.getDouble("weidu");
                        jingweidu[2][0]=jingdu;
                        jingweidu[2][1]=weidu;
                    }
                }
                r1.close();r2.close();r3.close();
                preparedStatement.close();

            }catch (SQLException e){
                e.printStackTrace();
            }

            MapDistance mapDistance=new MapDistance();
            distance1=mapDistance.getDistance(jingweidu[0][0],jingweidu[0][1],jingweidu[1][0],jingweidu[1][1]);
            distance2=mapDistance.getDistance(jingweidu[1][0],jingweidu[1][1],jingweidu[2][0],jingweidu[2][1]);
            double distance=Double.parseDouble(distance1)+Double.parseDouble(distance2);
            //System.out.println(start_name+"-"+middle_name+"-"+arrive_name+":"+distance);
            trans_temp_info.setDistance(distance);
        }

        Collections.sort(trans_temp_infos, new Comparator<Trans_temp_info>() {
            @Override
            public int compare(Trans_temp_info o1, Trans_temp_info o2) {
                if(o1.getDistance()>o2.getDistance()){
                    return 1;
                }
                else if(o1.getDistance()==o2.getDistance()){
                    return 0;
                }
                else{
                    return -1;
                }
            }
        });

        System.out.println("排序后"+trans_temp_infos);
    }

    /***
     *
     * 获得每一辆列车的Leftickets数据，组织数据。
     */
    public void Finalfinal(){
        FinalCalculater();
        ArrayList<Trans_temp_info> temp_infos=(ArrayList<Trans_temp_info>)getTrans_temp_infos().clone();
        Collections.sort(temp_infos, new Comparator<Trans_temp_info>() {
            @Override
            public int compare(Trans_temp_info o1, Trans_temp_info o2) {
                if(o1.getDistance()>o2.getDistance()){
                    return 1;
                }
                else if(o1.getDistance()==o2.getDistance()){
                    return 0;
                }
                else{
                    return -1;
                }
            }
        });
        double distance=temp_infos.get(0).getDistance();
        System.out.println("最小距离为"+distance);
        Iterator tempIter=temp_infos.iterator();
        while (tempIter.hasNext()) {
            Trans_temp_info trans_temp_info = (Trans_temp_info) tempIter.next();
            double distance1 = trans_temp_info.getDistance();
            System.out.println("当前距离为"+distance1);
            if (distance1 == distance) {
                int trainida = trans_temp_info.getTrainidA();
                int trainidb = trans_temp_info.getTrain_idB();
                int startid = trans_temp_info.getTrain_start_id();
                int arriveid = trans_temp_info.getTrain_arrive_id();
                int middleida = trans_temp_info.getTrain_middle_idA();
                int middleidb = trans_temp_info.getTrain_middleidB();
                
                String start_name = trans_temp_info.getStart_name();
                String middle_name = trans_temp_info.getMiddle_name();
                String arrive_name = trans_temp_info.getArrive_name();
                //需要找到train_id对应的train_name; train_id对应的余票
                String sql1 = "select train_name from train_sum_info where train_id=" + trainida;
                String sql2 = "select train_name from train_sum_info where train_id=" + trainidb;
                String sql3 = "select count(*) countt from train_info where train_id=" + trainida;
                String sql4 = "select count(*) countt from train_info where train_id=" + trainidb;
                String sql5="select start_time from train_info where train_id="+trainida+" and station_id="+startid;
                String sql6="select arrive_time from train_info where train_id="+trainida+" and station_id="+middleida;
                String sql7="select start_time from train_info where train_id="+trainidb+" and station_id="+middleidb;
                String sql8="select arrive_time from train_info where train_id="+trainidb+" and station_id="+arriveid;
                String train_namea = "", train_nameb = "";
                Time start_time=null,middletimea=null,middletimeb=null,arrivetime=null;
                int counta = 0, countb = 0;
                try {
                    if (connection == null) {
                        connection = connect.getConnection();
                    }
                    PreparedStatement preparedStatement = connection.prepareStatement(sql1);
                    ResultSet rs1 = preparedStatement.executeQuery();
                    rs1.first();

                    train_namea = rs1.getString("train_name");
                    preparedStatement = connection.prepareStatement(sql2);
                    ResultSet rs2 = preparedStatement.executeQuery();
                    rs2.first();

                    train_nameb = rs2.getString("train_name");
                    preparedStatement = connection.prepareStatement(sql3);
                    ResultSet rs3 = preparedStatement.executeQuery();
                    rs3.first();
                    counta = rs3.getInt("countt");
                    	
                    System.out.println("这里的counta"+counta);
                    preparedStatement = connection.prepareStatement(sql4);
                    ResultSet rs4 = preparedStatement.executeQuery();
                    rs4.first();
                    countb = rs4.getInt("countt");

                    preparedStatement = connection.prepareStatement(sql5);
                    ResultSet rs5 = preparedStatement.executeQuery();
                    rs5.first();
                    start_time = rs5.getTime("start_time");

                    preparedStatement = connection.prepareStatement(sql6);
                    ResultSet rs6= preparedStatement.executeQuery();
                    rs6.first();
                    middletimea = rs6.getTime("arrive_time");

                    preparedStatement = connection.prepareStatement(sql7);
                    ResultSet rs7 = preparedStatement.executeQuery();
                    rs7.first();
                    System.out.println(middleidb+" "+trainidb);
                    middletimeb = rs7.getTime("start_time");

                    preparedStatement = connection.prepareStatement(sql8);
                    ResultSet rs8 = preparedStatement.executeQuery();
                    rs8.first();
                    arrivetime = rs8.getTime("arrive_time");

                    System.out.println(middletimea+" "+middletimeb);
                    rs1.close();
                    rs2.close();
                    rs3.close();
                    rs4.close();
                    rs5.close();rs6.close();rs7.close();rs8.close();
                    preparedStatement.close();

                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println(diffHour(middletimea,middletimeb));
                if(diffHour(middletimea,middletimeb)<0) {
                    System.out.println("s----"+middletimea+" "+middletimeb);
                    Check_current_Leftickets check_current_lefticketsa = new Check_current_Leftickets(trainida, 1, counta, startid, middleida);
                    Train_leftickets_info leftickets_infoa = check_current_lefticketsa.getbystationid();
                    int asw = leftickets_infoa.getSW();
                    int ayd = leftickets_infoa.getYD();
                    int aed = leftickets_infoa.getED();
                    int aot = leftickets_infoa.getOT();

                    Check_current_Leftickets check_current_lefticketsb = new Check_current_Leftickets(trainidb, 1, countb, middleidb, arriveid);
                    Train_leftickets_info leftickets_infob = check_current_lefticketsb.getbystationid();
                    int bsw = leftickets_infoa.getSW();
                    int byd = leftickets_infoa.getYD();
                    int bed = leftickets_infoa.getED();
                    int bot = leftickets_infoa.getOT();

                    Trans_info_finals trans_info_finals = new Trans_info_finals(trainida, trainidb, startid, middleida, middleidb, arriveid, asw, ayd, aed, aot, bsw, byd, bed, bot, train_namea, train_nameb, start_name, middle_name, arrive_name,start_time,middletimea,middletimeb,arrivetime,counta,countb);
                    System.out.println("最后的"+trans_info_finals);
                    transInfoFinals.add(trans_info_finals);
                }
            }
            else {
                break;
            }
        }
    }

    //根据经纬度计算

    public ArrayList<Trans_info_finals> getTransInfoFinals() {
        return transInfoFinals;
    }

    public static void main(String[] args) {
        Select_trans_train select_trans_train=new Select_trans_train("北京","淄博","2020-11-20");

    }
    public static int diffHour(Time begin, Time end) {
        long i = begin.getTime();
        long j = end.getTime();
        // next day
        if (i > j) {
            j += 86400000l;
        }
        int d = (int) ((i - j) / 3600000l);

        return d;
    }
}