package DS_operation;

import java.io.Serializable;

/**
 * @auther DONG BQ
 * @DATE 2020/10/3020:05
 */
public class Train_info_new implements Serializable {
    private static final long serialVersionUID = 809782578272943999L;
    private String trian_name,start_station,arrive_station,start_date,start_time,arrive_date,arrive_time;
    private int start_station_id;
    private int arrive_station_id;
    private double cost;
    private int nextday;
    private String time_cost;
    public Train_info_new(){

    }
    public Train_info_new(String trian_name,String start_station,String arrive_station,int start_station_id,int arrive_station_id
    ,String start_date,String start_time,String arrive_date,String arrive_time,double cost,int next_day,String time_cost){
        this.arrive_station=arrive_station;
        this.arrive_station_id=arrive_station_id;
        this.trian_name=trian_name;
        this.start_station=start_station;
        this.start_station_id=start_station_id;
        this.start_date=start_date;
        this.start_time=start_time;
        this.arrive_time=arrive_time;
        this.arrive_date=arrive_date;
        this.cost=cost;
        this.nextday=next_day;
        this.time_cost=time_cost;

    }

    public int getArrive_station_id() {
        return arrive_station_id;
    }

    public int getStart_station_id() {
        return start_station_id;
    }

    public String getArrive_station() {
        return arrive_station;
    }

    public String getStart_station() {
        return start_station;
    }

    public String getTrian_name() {
        return trian_name;
    }

    public String getStart_time() {
        return start_time;
    }

    public String getArrive_time() {
        return arrive_time;
    }

    public String getArrive_date() {
        return arrive_date;
    }

    public String getStart_date() {
        return start_date;
    }

    public double getCost() {
        return cost;
    }

    public int getNextday() {
        return nextday;
    }

    public String getTime_cost() {
        return time_cost;
    }

    //////////////////////////////////////////////
    public void setArrive_station(String arrive_station) {
        this.arrive_station = arrive_station;
    }

    public void setArrive_station_id(int arrive_station_id) {
        this.arrive_station_id = arrive_station_id;
    }

    public void setStart_station(String start_station) {
        this.start_station = start_station;
    }

    public void setStart_station_id(int start_station_id) {
        this.start_station_id = start_station_id;
    }

    public void setTrian_name(String trian_name) {
        this.trian_name = trian_name;
    }

    public void setArrive_date(String arrive_date) {
        this.arrive_date = arrive_date;
    }

    public void setArrive_time(String arrive_time) {
        this.arrive_time = arrive_time;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public void setNextday(int nextday) {
        this.nextday = nextday;
    }

    public void setTime_cost(String time_cost) {
        this.time_cost = time_cost;
    }

    @Override
    public String toString() {
        return trian_name+"\nstart from"+getStart_station()+"站: "+getStart_station_id()+" 出发时间:"+getStart_date()+" "+getStart_time()+"\n"
                +"arrive at "+getArrive_station()+"站: "+getArrive_station_id()+" 到达时间:"+getArrive_date()+" "+getArrive_time()
                +"\n花费："+getCost()
                +"\n到达状态: "+getNextday()
                +"\n历时："+getTime_cost();
    }
}
