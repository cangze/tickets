package DS_operation;

/**
 * @auther DONG BQ
 * @DATE 2020/11/1618:26
 */
public class Temp_trainid_station_name {
    private int train_id,station_id;
    private String station_name;
    public Temp_trainid_station_name(int train_id,int station_id,String station_name){
        this.station_id=station_id;
        this.train_id=train_id;
        this.station_name=station_name;
    }

    public int getTrain_id() {
        return train_id;
    }

    public int getStation_id() {
        return station_id;
    }

    public String getStation_name() {
        return station_name;
    }

    public void setTrain_id(int train_id) {
        this.train_id = train_id;
    }

    public void setStation_id(int station_id) {
        this.station_id = station_id;
    }

    public void setStation_name(String station_name) {
        this.station_name = station_name;
    }

    @Override
    public String toString() {
        return ""+train_id+"--"+station_name+"--"+station_id;
    }
}
