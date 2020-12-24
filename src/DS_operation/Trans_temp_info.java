package DS_operation;

/**
 * @auther DONG BQ
 * @DATE 2020/11/1618:54
 */
public class Trans_temp_info {
    private  int trainidA,train_start_id,train_middle_idA,train_middleidB,train_idB,train_arrive_id;
    private  String start_name,middle_name,arrive_name;
    private double distance;
    public Trans_temp_info(){};

    public void setTrain_start_id(int train_start_id) {
        this.train_start_id = train_start_id;
    }

    public void setTrain_arrive_id(int train_arrive_id) {
        this.train_arrive_id = train_arrive_id;
    }

    public void setStart_name(String start_name) {
        this.start_name = start_name;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public void setArrive_name(String arrive_name) {
        this.arrive_name = arrive_name;
    }

    public void setMiddle_name(String middle_name) {
        this.middle_name = middle_name;
    }

    public void setTrain_idB(int train_idB) {
        this.train_idB = train_idB;
    }

    public void setTrain_middle_idA(int train_middle_idA) {
        this.train_middle_idA = train_middle_idA;
    }

    public void setTrain_middleidB(int train_middleidB) {
        this.train_middleidB = train_middleidB;
    }

    public void setTrainidA(int trainidA) {
        this.trainidA = trainidA;
    }

    public int getTrain_start_id() {
        return train_start_id;
    }

    public String getMiddle_name() {
        return middle_name;
    }

    public int getTrain_arrive_id() {
        return train_arrive_id;
    }

    public double getDistance() {
        return distance;
    }

    public String getStart_name() {
        return start_name;
    }

    public String getArrive_name() {
        return arrive_name;
    }

    public int getTrain_idB() {
        return train_idB;
    }

    public int getTrain_middle_idA() {
        return train_middle_idA;
    }

    public int getTrain_middleidB() {
        return train_middleidB;
    }

    public int getTrainidA() {
        return trainidA;
    }

    @Override
    public String toString() {
        return ""+trainidA+"-"+start_name+"-"+train_start_id+"--"+train_middle_idA+" "+middle_name+" "+train_middleidB+" "+arrive_name+" "+train_arrive_id+" "+train_idB+"distance"+distance;
    }
}
