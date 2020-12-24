package DS_operation;

/**
 * @auther DONG BQ
 * @DATE 2020/11/1313:24
 */
public class Train_leftickets_info {
    private int train_id,start_staion_id,arrive_station_id;
    private int SW,YD,ED,OT;
    public Train_leftickets_info(){};

    public int getTrain_id() {
        return train_id;
    }

    public int getArrive_station_id() {
        return arrive_station_id;
    }

    public int getED() {
        return ED;
    }

    public int getOT() {
        return OT;
    }

    public int getStart_staion_id() {
        return start_staion_id;
    }

    public int getSW() {
        return SW;
    }

    public int getYD() {
        return YD;
    }

    public void setArrive_station_id(int arrive_station_id) {
        this.arrive_station_id = arrive_station_id;
    }

    public void setED(int ED) {
        this.ED = ED;
    }

    public void setOT(int OT) {
        this.OT = OT;
    }

    public void setStart_staion_id(int start_staion_id) {
        this.start_staion_id = start_staion_id;
    }

    public void setTrain_id(int train_id) {
        this.train_id = train_id;
    }

    public void setSW(int SW) {
        this.SW = SW;
    }

    public void setYD(int YD) {
        this.YD = YD;
    }

    @Override
    public String toString() {
        return ""+train_id+":"+start_staion_id+"-"+arrive_station_id+"||SW:"+SW+" YD:"+YD+" ED:"+ED+" OT:"+OT;
    }
}
