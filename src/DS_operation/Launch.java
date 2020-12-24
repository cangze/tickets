package DS_operation;

import DS_operation.Connect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @auther DONG BQ
 * @DATE 2020/10/2316:45
 */
public class Launch {
    private Launch_account launch_account=null;
    private int result=0;
    private String Custom_nameString="",custom_idString="";
    public Launch(Launch_account launch_account){
        this.launch_account=launch_account;
        //数据库
        try {
            Connect connect=new Connect();
            connect.testConnection();
            Connection connection=connect.getConnection();
            //SQL
            String sql="select * from account where ID="+'\"'+launch_account.getID()+'\"';

            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            ResultSet rs=preparedStatement.executeQuery();
            String passwd=null;
            
            while (rs.next()){
                passwd=rs.getString("passwd");
                if(passwd.equals(launch_account.getPasswd())){
                    result=1;
                    Custom_nameString=rs.getString("name");
                    custom_idString=launch_account.getID();
                }
                else {
                    result=0;
                }
            }
            
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public int getResult() {
        return result;
    }

    public static void main(String[] args){
        Launch_account launch_account=new Launch_account("15589359936","123");
        Launch launch=new Launch(launch_account);
        if(launch.getResult()==0){
            System.out.println("NOT");
        }
        else if(launch.getResult()==1){
            System.out.println("Successfully launched");
        }
    }

	public String getCustom_nameString() {
		// TODO Auto-generated method stub
		return Custom_nameString;
	}
	public String getCustome_idString() {
		return custom_idString;
	}
}
