package DS_operation;

import DS_operation.Connect;
import DS_operation.React_to_Cus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * @auther DONG BQ
 * @DATE 2020/10/3017:14
 */
public class Generate_Tickets_new {
    public Generate_Tickets_new(String trian_name,String startcity,String arrivecity,String startdate,String over_date,
            ArrayList<Train_insert_info> train_infos,int station_num,ArrayList<Van_info> van_infos) {
        try {
            /**
             * 获得与数据库的链接
             */
            Connect connect = new Connect();
            connect.testConnection();
            Connection connection = connect.getConnection();
            /**
             * 向火车的总表写入车相关数据
             */
            String sql_selectsum="select * from train_sum_info where train_name ='"+trian_name+"'";

            PreparedStatement preparedStatement = connection.prepareStatement(sql_selectsum);
            ResultSet set= preparedStatement.executeQuery();
            set.first();
            if(set.getRow()==0){
            String sql_insertsum="insert into train_sum_info values(?,?,?,?,?,?)";
             preparedStatement=connection.prepareStatement(sql_insertsum);
            preparedStatement.setString(1,trian_name);
            String train_type=trian_name.charAt(0)+"";
            train_type.toUpperCase();
            preparedStatement.setString(2,train_type);
            preparedStatement.setString(3,startcity);
            preparedStatement.setString(4,arrivecity);
            preparedStatement.setString(5,startdate);
            preparedStatement.setString(6,over_date);

            preparedStatement.execute();
            //如果没执行的话，表示这个已经存在过了。
            //可以在写之前查询一下
            /**
             *该部分为向数据库中新建<train_name>_info的表单以及数据
             *
            */
//            String sql_traininfos="create table if not exists "+trian_name+"_info(station_id INT NOT NULL,city VARCHAR(255) NOT NULL,station_name VARCHAR(255) NOT NULL," +
//                    "arrive_date DATE NOT NULL,arrive_time TIME NOT NULL, " +
//                    "start_date DATE NOT NULL,start_time TIME NOT NULL," +
//                    "cost DOUBLE NOT NULL,primary key(station_id))";

            String sql_traininfos="create table if not exists "+trian_name+"_info(station_id INT NOT NULL,city VARCHAR(255) NOT NULL,station_name VARCHAR(255) NOT NULL," +
                        "arrive_time TIME NOT NULL, " +
                        "start_time TIME NOT NULL," +
                        "cost DOUBLE NOT NULL,primary key(station_id))";
            preparedStatement=connection.prepareStatement(sql_traininfos);
            preparedStatement.execute();

            Iterator traininfo_it=train_infos.iterator();
            while (traininfo_it.hasNext()){
                Train_insert_info tii=(Train_insert_info)traininfo_it.next();
                int station_id=tii.getId();
                String city=tii.getCity();
                String station_name=tii.getStaion_name();

                String arrive_time=tii.getArrive_time();

                String start_time=tii.getStart_time();
                double cost=tii.getCost();
                String sql_insert="insert into "+trian_name+"_info values(?,?,?,?,?,?)";
                preparedStatement=connection.prepareStatement(sql_insert);

                preparedStatement.setInt(1,station_id);
                preparedStatement.setString(2,city);
                preparedStatement.setString(3,station_name);

                preparedStatement.setString(4,arrive_time);

                preparedStatement.setString(5,start_time);
                preparedStatement.setDouble(6,cost);

                preparedStatement.execute();
            }

             /**
             * 该部分为在数据库中新加入<train_name>_van的表格
             */
            //直接按照van_infos插入就可
            String sql_vaninfos="create table if not exists "+trian_name+"_van (van_id INT NOT NULL,van_type VARCHAR(255) NOT NULL,van_row INT NOT NULL,seat_sum INT NOT NULL,primary key(van_id))";
            preparedStatement=connection.prepareStatement(sql_vaninfos);
            preparedStatement.execute();
            Iterator van_itera_insert=van_infos.iterator();
            while (van_itera_insert.hasNext()){
                Van_info van_info=(Van_info)van_itera_insert.next();
                int van_id=van_info.getVan_id();
                String van_type=van_info.getVan_type();
                int van_row=van_info.getVan_row();
                int van_seatsum=van_info.getSeat_sum();
                String sql_insert="insert into "+trian_name+"_van values(?,?,?,?)";
                preparedStatement=connection.prepareStatement(sql_insert);
                preparedStatement.setInt(1,van_id);
                preparedStatement.setString(2,van_type);
                preparedStatement.setInt(3,van_row);
                preparedStatement.setInt(4,van_seatsum);

                preparedStatement.execute();
            }

            /**
             * 该部分为生成<train_name>_leftickets 的表格以及插入数据。
             */
            //生成车站的排列组合
            int type_sum = station_num * (station_num-1) / 2;
            int[][] station_type = new int[type_sum][2];
            int count = 0;
            for (int i = 0; i < station_num; i++) {
                for (int j = i + 1; j < station_num; j++) {
                    //数组的第一个数是起始站
                    station_type[count][0] = i+1;
                    //数组的第二个数是终止站
                    station_type[count][1] = j+1;
                    //count+1
                    count++;
                }
            }
            for(int i=0;i<type_sum;i++){
                System.out.println("("+station_type[i][0]+","+station_type[i][1]+")");
            }
            //计算对应的Van_的总数：按照van_type分类,存在HashMap中
            HashMap<String,Integer> van_map=new HashMap<>();

            Iterator van_itera=van_infos.iterator();
            //遍历van_info数组，计算每种车厢类型对应的座位总数,存放到hashmap中
            while (van_itera.hasNext()){
                Van_info van_info=(Van_info)van_itera.next();//获得指针指向的van_info对象
                String type=van_info.getVan_type();
                System.out.println(type);
                if(van_map.containsKey(type)){
                    //map中已有该主键，覆盖
                    int sum=van_map.get(type)+van_info.getSeat_sum();
                    van_map.remove(type);
                    van_map.put(type,sum);
                    System.out.println(type+": "+sum);
                }else {
                    //map中没有该主键，新建
                    int sum=van_info.getSeat_sum();
                    van_map.put(type,sum);
                    System.out.println(type+": "+sum);
                }

            }

            //对hashmap进行遍历，插入数据的语句

            //对hash
            Iterator mapit=van_map.entrySet().iterator();
            ArrayList<String> keyset=new ArrayList<>();
            String sql_tail="(?,?,";
            int count_tail=0;
            while (mapit.hasNext()){
                Map.Entry entry=(Map.Entry)mapit.next();
                String key=entry.getKey().toString();
                keyset.add(key);
                int number=Integer.parseInt(entry.getValue().toString());
                System.out.println(key+":"+number);

                count_tail++;
                sql_tail+="?";
                if(mapit.hasNext()){
                    sql_tail+=",";
                }
            }
            sql_tail+=")";
            //把key保存到keyset链表内

            System.out.println(sql_tail);
            System.out.println(keyset.size());

            String sql_create="create table if not exists "+trian_name+"_leftickets (start_id INT NOT NULL,arrive_id INT NOT NULL,";
            Iterator keyset_it=keyset.iterator();
            while (keyset_it.hasNext()){
                String key=keyset_it.next().toString();
                sql_create+=key+"_tickets INT NOT NULL,";
            }
            sql_create+="primary key(start_id,arrive_id))";

            preparedStatement=connection.prepareStatement(sql_create);
            preparedStatement.execute();



            //插入数据语句
            String sql = "insert into " + trian_name + "_leftickets values"+sql_tail;
            preparedStatement = connection.prepareStatement(sql);

            for (int i = 0; i < type_sum; i++) {
                int count_type=1;
                for(int j=1;j<=2+count_tail;j++) {
                    if (j == 1) {
                        preparedStatement.setInt(1, station_type[i][0]);
                    }
                    else if (j == 2) {
                        preparedStatement.setInt(2, station_type[i][1]);
                    }
                    else {
                        //接下来是van_type对应的数量
                        String key=keyset.get(count_type-1);
                        int num=van_map.get(key);
                        preparedStatement.setInt(j,num);
                        count_type++;
                        System.out.println(num);
                    }
                }
                preparedStatement.execute();
            }
            /**
             * 该部分为向表中新建<train_name>_takes的表单，该表单无初始数据
             */
            String sql_creat_takes="create table if not exists "+trian_name+"_takes" +
                    "(order_id VARCHAR(255) NOT NULL,customer_id VARCHAR(225) NOT NULL,taker_id VARCHAR(225) NOT NULL," +
                    "start_station VARCHAR(255) NOT NULL,arrive_station VARCHAR(255) NOT NULL," +
                    "van_id INT NOT NULL,seat_id VARCHAR(255),primary key(order_id))";
            preparedStatement=connection.prepareStatement(sql_creat_takes);
            preparedStatement.execute();
        }
            else {
             //如果没有执行成功
                System.out.println("已经存在该列车" );
                React_to_Cus rtc=new React_to_Cus(3);
        }
        }
        catch (SQLException e){
            e.printStackTrace();
        }catch (Exception e1){
            e1.printStackTrace();
        }
    }

    public static void main(String[] args){
        //车的经停信息
        ArrayList<Train_insert_info> train_insert_infos=new ArrayList<>();
        Train_insert_info insert_info1=new Train_insert_info(1,"上海","上海虹桥","2020-11-20","09:43:00","2020-11-01","09:43:00",00);
//        Train_insert_info insert_info2=new Train_insert_info(2,"潍坊","潍坊","2020-11-01","07:27:00","2020-11-01","07:27:00",10);
//        Train_insert_info insert_info3=new Train_insert_info(3,"淄博","淄博","2020-11-01","08:27:00","2020-11-01","08:27:00",20);
//        Train_insert_info insert_info4=new Train_insert_info(4,"济南","章丘","2020-11-01","09:27:00","2020-11-01","09:27:00",30);
//        Train_insert_info insert_info5=new Train_insert_info(5,"济南","济南","2020-11-01","10:27:00","2020-11-01","10:27:00",40);
//        Train_insert_info insert_info6=new Train_insert_info(6,"济南","济南西","2020-11-01","11:27:00","2020-11-01","11:27:00",50);
//        Train_insert_info insert_info7=new Train_insert_info(7,"德州","禹城东","2020-11-01","12:27:00","2020-11-01","12:27:00",60);
//        Train_insert_info insert_info8=new Train_insert_info(8,"德州","德州东","2020-11-01","13:27:00","2020-11-01","13:27:00",70);
//        Train_insert_info insert_info9=new Train_insert_info(9,"衡水","衡水北","2020-11-01","14:27:00","2020-11-01","14:27:00",80);
        train_insert_infos.add(insert_info1);
//        train_insert_infos.add(insert_info3);
//        train_insert_infos.add(insert_info4);train_insert_infos.add(insert_info5);
//        train_insert_infos.add(insert_info6);train_insert_infos.add(insert_info7);
//        train_insert_infos.add(insert_info8);train_insert_infos.add(insert_info9);
//        train_insert_infos.add(insert_info2);
        //其实这里应该是先获取到一共有多少个车厢
        ArrayList<Van_info> van_infos=new ArrayList<>();
        Van_info info1=new Van_info(1,"SW",8,24);
        Van_info info2=new Van_info(2,"YD",13,51);
        Van_info info3=new Van_info(3,"SW",8,24);
        Van_info info4=new Van_info(4,"YD",13,51);
        Van_info info5=new Van_info(5,"ED",17,85);
        Van_info info6=new Van_info(6,"ED",17,85);
        van_infos.add(info1);van_infos.add(info3);van_infos.add(info4);
        van_infos.add(info2);van_infos.add(info5);van_infos.add(info6);
        Generate_Tickets_new n=new Generate_Tickets_new("g664","西安北","北京西","2020-11-20","2020-11-20",train_insert_infos,12,van_infos);
    }
}
