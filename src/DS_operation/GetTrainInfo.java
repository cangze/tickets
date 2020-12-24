package DS_operation;

import java.sql.Connection;

public class GetTrainInfo {
	public GetTrainInfo(String train_name,String start_staiton,String arrive_station) {
		try {
			Connect connect=new Connect();
			connect.testConnection();
			Connection connection=connect.getConnection();
			//获取
			
			String sql1="select * from "+train_name+"_info where start_station='"+start_staiton+"' && arrive_station='"+arrive_station+"'";
			
			String sql2="select * from "+train_name+"_van";
			
			String sql3="select * from "+train_name+"_leftickets";
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
