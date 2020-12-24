package DS_operation;

import java.sql.Time;

/**
 * @auther DONG BQ
 * @DATE 2020/11/131:04
 */
public class Train_info_final {
    //train_id
    //train_name
    //start_station_id,name,time;
    //arrive_station_id,name,time;
    private int train_id,start_station_id,arrive_station_id;
    private String train_name,start_station_name,arrive_station_name;
    private Time start_time,arrive_time;
    private String spandtime;
    private String train_start_s,train_arrive_s;
    private int train_start_id,train_arrive_id;
    public Train_info_final(){

    }

    public void setTrain_arrive_id(int train_arrive_id) {
        this.train_arrive_id = train_arrive_id;
    }

    public void setTrain_start_id(int train_start_id) {
        this.train_start_id = train_start_id;
    }

    public void setTrain_arrive_s(String train_arrive_s) {
        this.train_arrive_s = train_arrive_s;
    }

    public void setTrain_start_s(String train_start_s) {
        this.train_start_s = train_start_s;
    }

    public void setArrive_time(Time arrive_time) {
        this.arrive_time = arrive_time;
    }

    public void setStart_time(Time start_time) {
        this.start_time = start_time;
    }

    public void setSpandtime(String spandtime) {
        this.spandtime = spandtime;
    }

    public void setTrain_id(int train_id) {
        this.train_id = train_id;
    }

    public void setTrain_name(String train_name) {
        this.train_name = train_name;
    }

    public void setStart_station_id(int start_station_id) {
        this.start_station_id = start_station_id;
    }

    public void setArrive_station_id(int arrive_station_id) {
        this.arrive_station_id = arrive_station_id;
    }

    public void setArrive_station_name(String arrive_station_name) {
        this.arrive_station_name = arrive_station_name;
    }

    public void setStart_station_name(String start_station_name) {
        this.start_station_name = start_station_name;
    }

    public int getTrain_arrive_id() {
        return train_arrive_id;
    }

    public int getTrain_start_id() {
        return train_start_id;
    }

    public int getTrain_id() {
        return train_id;
    }

    public String getTrain_name() {
        return train_name;
    }

    public int getStart_station_id() {
        return start_station_id;
    }

    public Time getArrive_time() {
        return arrive_time;
    }

    public Time getStart_time() {
        return start_time;
    }

    public String getSpandtime() {
        return spandtime;
    }

    public String getTrain_arrive_s() {
        return train_arrive_s;
    }

    public String getTrain_start_s() {
        return train_start_s;
    }

    public int getArrive_station_id() {
        return arrive_station_id;
    }

    public String getArrive_station_name() {
        return arrive_station_name;
    }

    public String getStart_station_name() {
        return start_station_name;
    }

    @Override
	public String toString() {
		return "Train_info_final [train_id=" + train_id + ", start_station_id=" + start_station_id
				+ ", arrive_station_id=" + arrive_station_id + ", train_name=" + train_name + ", start_station_name="
				+ start_station_name + ", arrive_station_name=" + arrive_station_name + ", start_time=" + start_time
				+ ", arrive_time=" + arrive_time + ", spandtime=" + spandtime + ", train_start_s=" + train_start_s
				+ ", train_arrive_s=" + train_arrive_s + ", train_start_id=" + train_start_id + ", train_arrive_id="
				+ train_arrive_id + "]";
	}
}
