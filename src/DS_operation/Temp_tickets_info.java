package DS_operation;

/**
 * @auther DONG BQ
 * @DATE 2020/11/1314:41
 */
public class Temp_tickets_info {
    private int train_id,start_id,arrive_id;
    private String type;
    private String order_id;
    public Temp_tickets_info(String order_id,int train_id,int start_id,int arrive_id,String type){
        this.order_id=order_id;
        this.train_id=train_id;
        this.start_id=start_id;
        this.arrive_id=arrive_id;
        this.type=type;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public void setTrain_id(int train_id) {
        this.train_id = train_id;
    }

    public void setArrive_id(int arrive_id) {
        this.arrive_id = arrive_id;
    }

    public void setStart_id(int start_id) {
        this.start_id = start_id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOrder_id() {
        return order_id;
    }

    public int getTrain_id() {
        return train_id;
    }

    public int getStart_id() {
        return start_id;
    }

    public int getArrive_id() {
        return arrive_id;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return ""+train_id+" start from:"+start_id+"-arrive at"+arrive_id;
    }
}
