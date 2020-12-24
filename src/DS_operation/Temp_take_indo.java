package DS_operation;

/**
 * @auther DONG BQ
 * @DATE 2020/11/150:44
 */
public class Temp_take_indo {
    private int carrige_id,start_id,arrive_id;
    private String seat;
    public Temp_take_indo(int carrige_id,int start_id,int arrive_id,String seat){
        this.carrige_id=carrige_id;
        this.seat=seat;
        this.start_id=start_id;
        this.arrive_id=arrive_id;
    }



    public void setStart_id(int start_id) {
        this.start_id = start_id;
    }

    public void setArrive_id(int arrive_id) {
        this.arrive_id = arrive_id;
    }

    public void setCarrige_id(int carrige_id) {
        this.carrige_id = carrige_id;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public int getCarrige_id() {
        return carrige_id;
    }

    public String getSeat() {
        return seat;
    }

    public int getArrive_id() {
        return arrive_id;
    }

    public int getStart_id() {
        return start_id;
    }


    @Override
    public String toString() {
        return ""+carrige_id+":"+seat+"||"+start_id+"-"+arrive_id;
    }
}
