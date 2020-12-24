package DS_operation;

/**
 * @auther DONG BQ
 * @DATE 2020/11/130:16
 */
public class Temp_info {
    private  int train_id;
    private String start_name,arrive_name;
    public Temp_info(int train_id,String start_name,String arrive_name){
        this.train_id=train_id;
        this.start_name=start_name;
        this.arrive_name=arrive_name;
    }

    public int getTrain_id() {
        return train_id;
    }

    public String getArrive_name() {
        return arrive_name;
    }

    public String getStart_name() {
        return start_name;
    }

    public void setArrive_name(String arrive_name) {
        this.arrive_name = arrive_name;
    }

    public void setStart_name(String start_name) {
        this.start_name = start_name;
    }

    public void setTrain_id(int train_id) {
        this.train_id = train_id;
    }

    @Override
    public String toString() {
        return ""+train_id+":"+start_name+"-"+arrive_name;
    }
}
