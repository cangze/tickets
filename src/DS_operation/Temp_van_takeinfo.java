package DS_operation;

/**
 * @auther DONG BQ
 * @DATE 2020/11/150:27
 */
public class Temp_van_takeinfo {
    private int van_id,van_row,seat_sum,start_staion_id,arrive_station_id;
    private String van_type;
    public Temp_van_takeinfo(){

    }
    public Temp_van_takeinfo(int van_id,int start_staion_id,int arrive_station_id, String van_type,int van_row,int seat_sum){
        this.start_staion_id=start_staion_id;
        this.arrive_station_id=arrive_station_id;
        this.van_id=van_id;
        this.van_type=van_type;
        this.van_row=van_row;
        this.seat_sum=seat_sum;
    }

    public void setStart_staion_id(int start_staion_id) {
        this.start_staion_id = start_staion_id;
    }

    public void setArrive_station_id(int arrive_station_id) {
        this.arrive_station_id = arrive_station_id;
    }

    public void setSeat_sum(int seat_sum) {
        this.seat_sum = seat_sum;
    }

    public void setVan_id(int van_id) {
        this.van_id = van_id;
    }

    public void setVan_row(int van_row) {
        this.van_row = van_row;
    }

    public void setVan_type(String van_type) {
        this.van_type = van_type;
    }

    public int getSeat_sum() {
        return seat_sum;
    }

    public int getVan_id() {
        return van_id;
    }

    public String getVan_type() {
        return van_type;
    }

    public int getVan_row() {
        return van_row;
    }

    public int getStart_staion_id() {
        return start_staion_id;
    }

    public int getArrive_station_id() {
        return arrive_station_id;
    }

    @Override
    public String toString() {
        return van_id+":"+van_type+":"+seat_sum+":"+start_staion_id+":"+arrive_station_id;
    }
}
