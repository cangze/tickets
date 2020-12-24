package DS_operation;

import DS_operation.Connect;
import DS_operation.React_to_Cus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @auther DONG BQ
 * @DATE 2020/10/2316:23
 */
public class Register {
    //传入一个Customer对象，按照该对象想数据库中写入数据
    //DS_operation.Customer(id,name,birth,sex,passwd)
    //id--int;name--String;birth--year/month/date;sex--int

    public Register(Customer customer){
        String ID=customer.getID();
        String name=customer.getName();
        String birth=customer.getBirth();
        int sex=customer.getSex();
        String passwd=customer.getPasswd();
        boolean success=true;
     //链接到数据库
        try {
            Connect connect=new Connect();
            connect.testConnection();

            Connection connection=connect.getConnection();
            //检查是否有相同的电话号码被注册
            String sql2="select ID from account where ID="+customer.getID();
            PreparedStatement psmt=connection.prepareStatement(sql2);
            ResultSet rs=psmt.executeQuery();
            if(rs.next()){
                System.out.println("We already have the same ID");
                React_to_Cus react_to_cus=new React_to_Cus(1);
                react_to_cus.deal();
            }
            else {
                //connection是连接到databasetry数据库的
                String sql = "insert into account values(?,?,?,?,?)";
                psmt = connection.prepareStatement(sql);
                psmt.setString(1, ID);
                psmt.setString(2, name);
                psmt.setInt(3, sex);
                psmt.setString(4, birth);
                psmt.setString(5, passwd);
                success = psmt.execute();
                if (!success) {
                    System.out.println("Successfully registered");
                    React_to_Cus react_to_cus = new React_to_Cus(2);
                    react_to_cus.deal();
                }
            }
        }catch (Exception e){
            React_to_Cus react_to_cus=new React_to_Cus(65536);
            e.printStackTrace();
        }

    }

    public static void main(String[] args){
        //注册时需要提供的信息：id,name,性别，出生日期
        Customer customer=new Customer("13953342041","张萍",0,"2000/11/08","123");
        Register register=new Register(customer);
    }
}
