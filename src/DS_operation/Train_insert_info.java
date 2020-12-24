package DS_operation;

/**
 * @auther DONG BQ
 * @DATE 2020/10/312:41
 */
public class Train_insert_info {
    private int id;
    private String city,staion_name,arrive_date,arrive_time,start_date,start_time;
    private double cost;
    public Train_insert_info(){}
    public Train_insert_info(int id,String city,String staion_name,String arrive_date,String arrive_time,String start_date,String start_time,double cost){
        this.id=id;
        this.city=city;
        this.staion_name=staion_name;
        this.arrive_date=arrive_date;
        this.arrive_time=arrive_time;
        this.start_date=start_date;
        this.start_time=start_time;
        this.cost=cost;
    }
    public void setCost(double cost) {
        this.cost = cost;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public void setArrive_time(String arrive_time) {
        this.arrive_time = arrive_time;
    }

    public void setArrive_date(String arrive_date) {
        this.arrive_date = arrive_date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setStaion_name(String staion_name) {
        this.staion_name = staion_name;
    }

    public double getCost() {
        return cost;
    }

    public String getStart_date() {
        return start_date;
    }

    public String getArrive_date() {
        return arrive_date;
    }

    public String getArrive_time() {
        return arrive_time;
    }

    public String getStart_time() {
        return start_time;
    }

    public int getId() {
        return id;
    }

    public String getCity() {
        return city;
    }

    public String getStaion_name() {
        return staion_name;
    }
}
