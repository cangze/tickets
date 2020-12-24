package DS_operation;

/**
 * @auther DONG BQ
 * @DATE 2020/10/2319:18
 */
public class Buy_info {
    //包括用户ID,乘车人ID(身份证号),车号，出发站、终点站。
    private double MAX=88888.00;
    private String Customer_ID,taker_ID,train_name,start_station,arrive_staion,seat_type;
    private int pay_state;
    private double money;
    //在生成订单时用这个
    public  Buy_info(String customer_ID,String taker_ID,String train_name,String start_station,String arrive_staion,String seat_type,double money){
        this.Customer_ID=customer_ID;
        this.taker_ID=taker_ID;
        this.train_name=train_name;
        this.start_station=start_station;
        this.arrive_staion=arrive_staion;
        this.seat_type=seat_type;
        this.money=money;
        //0代表未付款
        this.pay_state=0;
    }
    //在返回订单时用这个
    public  Buy_info(String customer_ID,String taker_ID,String train_name,String start_station,String arrive_staion,double money,String seat_type,int pay_state){
        this.Customer_ID=customer_ID;
        this.taker_ID=taker_ID;
        this.train_name=train_name;
        this.start_station=start_station;
        this.arrive_staion=arrive_staion;
        this.seat_type=seat_type;
        this.money=money;
        //0代表未付款
        this.pay_state=pay_state;
    }

    public double getMoney() {
        return money;
    }

    public String getCustomer_ID() {
        return Customer_ID;
    }

    public String getTaker_ID() {
        return taker_ID;
    }

    public String getTrain_name() {
        return train_name;
    }

    public String getStart_station() {
        return start_station;
    }

    public String getArrive_staion() {
        return arrive_staion;
    }

    public int getPay_state() {
        return pay_state;
    }

    public String getSeat_type() {
        return seat_type;
    }
}
