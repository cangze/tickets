package DS_operation;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

/**
 * @auther DONG BQ
 * @DATE 2020/11/123:44
 */
public class helloworld {
    public static void main(String[] args){
        String seat_id="11A";
        char ss=seat_id.substring(0,2).charAt(0);
        System.out.println(ss-65);
        char c=(char)(3+65);
        System.out.println(c);
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");//设置日期格式
        String createTime = dateFormat.format(now);
        System.out.println(createTime);
        ArrayList<Integer> ii=new ArrayList<>();
        ii.add(1);
        ii.add(1);ii.add(1);ii.add(1);ii.add(1);ii.add(1);
        System.out.println(ii.get(0));

//                String s="c8e9aca0c6f2e5f3e8c4efe7a1a0d4e8e5a0e6ece1e7a0e9f3baa0e8eafae3f9e4eafae2eae4e3eaebfaebe3f5e7e9f3e4e3e8eaf9eaf3e2e4e6f2";
//                int length=s.length();
//                String flag="";
//                for(int i=0;i<length-1;i+=2){
//                    String s1=s.substring(i,(i+2));
//                    int decimal=Integer.parseInt(s1,16)-128;
//                    System.out.println(decimal);
//                    flag+=(char)decimal;
//                }
//                System.out.println(flag);

//        String[]s={"SW","YD","ED","OT"};
//        System.out.println(s.length);
//        try {
//            Connect connect=new Connect();
//            connect.testConnection();;
//            Connection connection=connect.getConnection();
//
//            PreparedStatement pstd=null;
//
//
//            int sum=4458;
//            int count=0;
//            for (int i=4232;i<sum+1;i++){
//                int ran_sumvan=(int)(Math.random()*9+7);
//                String sql1="insert into train_seat values(?,?,?,?,?,?)";
//                for (int j=1;j<ran_sumvan+1;j++){
//                    int random=(int)(Math.random()*4);
//                    String typp=s[random];
//                    int seat_row=0;
//                    int seat_sum=0;
//                    double basis=0;
//                    if(typp.equals("SW")){
//                        seat_row=2;
//                        seat_sum=14;
//                        basis=35.5;
//                    }else if(typp.equals("YD")){
//                        seat_row=13;
//                        seat_sum=52;
//                        basis=25.5;
//                    }else if(typp.equals("ED")){
//                        seat_row=17;
//                        seat_sum=85;
//                        basis=15.5;
//                    }else if(typp.equals("OT")){
//                        seat_row=17;
//                        seat_sum=85;
//                        basis=15.5;
//                    }
//                    pstd=connection.prepareStatement(sql1);
//                    pstd.setInt(1,i);
//                    pstd.setInt(2,j);
//                    pstd.setString(3,typp);
//                    pstd.setInt(4,seat_row);
//                    pstd.setInt(5,seat_sum);
//                    pstd.setDouble(6,basis);
//                    pstd.execute();
//                }
//                count++;
//            }
//            System.out.println(count);
//        }catch (Exception e){
//            e.printStackTrace();
//        }

        /**
         * 按照
         */
    }
}
