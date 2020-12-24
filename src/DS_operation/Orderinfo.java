package DS_operation;

import java.sql.Date;

/**
 * @auther DONG BQ
 * @DATE 2020/11/167:08
 */
public class Orderinfo {
    private String order_id,train_name;
    private Date orderDate;
    private String custom_id,taker_name,taker_card_id,taker_phone,start_station_name,arrive_station_name;
    private int carriage_id,train_id;
    private String seat_type,seat_type_age,train_seat,start_time,arrive_time;
    private double cost;



    public String getTrain_name() {
        return train_name;
    }

    public int getTrain_id() {
        return train_id;
    }

    public void setTrain_id(int train_id) {
        this.train_id = train_id;
    }

    public void setTrain_name(String train_name) {
        this.train_name = train_name;
    }

    public Orderinfo(String order_id, int train_id, String train_name, Date orderDate, String custom_id, String taker_name, String taker_card_id, String taker_phone, String start_station_name, String arrive_station_name, int carriage_id, String seat_type, String seat_type_age, String train_seat, String start_time, String arrive_time, double cost) {
        this.order_id = order_id;
        this.train_id = train_id;
        this.train_name = train_name;
        this.orderDate = orderDate;
        this.custom_id = custom_id;
        this.taker_name = taker_name;
        this.taker_card_id = taker_card_id;
        this.taker_phone = taker_phone;
        this.start_station_name = start_station_name;
        this.arrive_station_name = arrive_station_name;
        this.carriage_id = carriage_id;
        this.seat_type = seat_type;
        this.seat_type_age = seat_type_age;
        this.train_seat = train_seat;
        this.start_time = start_time;
        this.arrive_time = arrive_time;
        this.cost = cost;
    }

    public Orderinfo(String order_id, Date orderDate, String custom_id, String taker_name, String taker_card_id, String taker_phone, String start_station_name, String arrive_station_name, int carriage_id, String seat_type, String seat_type_age, String train_seat, String start_time, String arrive_time, double cost) {
        this.order_id = order_id;
        this.orderDate = orderDate;
        this.custom_id = custom_id;
        this.taker_name = taker_name;
        this.taker_card_id = taker_card_id;
        this.taker_phone = taker_phone;
        this.start_station_name = start_station_name;
        this.arrive_station_name = arrive_station_name;
        this.carriage_id = carriage_id;
        this.seat_type = seat_type;
        this.seat_type_age = seat_type_age;
        this.train_seat = train_seat;
        this.start_time = start_time;
        this.arrive_time = arrive_time;
        this.cost = cost;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public void setCustom_id(String custom_id) {
        this.custom_id = custom_id;
    }

    public void setTaker_name(String taker_name) {
        this.taker_name = taker_name;
    }

    public void setTaker_card_id(String taker_card_id) {
        this.taker_card_id = taker_card_id;
    }

    public void setTaker_phone(String taker_phone) {
        this.taker_phone = taker_phone;
    }

    public void setStart_station_name(String start_station_name) {
        this.start_station_name = start_station_name;
    }

    public void setArrive_station_name(String arrive_station_name) {
        this.arrive_station_name = arrive_station_name;
    }

    public void setCarriage_id(int carriage_id) {
        this.carriage_id = carriage_id;
    }

    public void setSeat_type(String seat_type) {
        this.seat_type = seat_type;
    }

    public void setSeat_type_age(String seat_type_age) {
        this.seat_type_age = seat_type_age;
    }

    public void setTrain_seat(String train_seat) {
        this.train_seat = train_seat;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public void setArrive_time(String arrive_time) {
        this.arrive_time = arrive_time;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getOrder_id() {
        return order_id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public String getCustom_id() {
        return custom_id;
    }

    public String getTaker_name() {
        return taker_name;
    }

    public String getTaker_card_id() {
        return taker_card_id;
    }

    public String getTaker_phone() {
        return taker_phone;
    }

    public String getStart_station_name() {
        return start_station_name;
    }

    public String getArrive_station_name() {
        return arrive_station_name;
    }

    public int getCarriage_id() {
        return carriage_id;
    }

    public String getSeat_type() {
        return seat_type;
    }

    public String getSeat_type_age() {
        return seat_type_age;
    }

    public String getTrain_seat() {
        return train_seat;
    }

    public String getStart_time() {
        return start_time;
    }

    public String getArrive_time() {
        return arrive_time;
    }

    public double getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return "Orderinfo{" +
                "order_id='" + order_id + '\'' +
                ", train_name='" + train_name + '\'' +
                ", orderDate=" + orderDate +
                ", custom_id='" + custom_id + '\'' +
                ", taker_name='" + taker_name + '\'' +
                ", taker_card_id='" + taker_card_id + '\'' +
                ", taker_phone='" + taker_phone + '\'' +
                ", start_station_name='" + start_station_name + '\'' +
                ", arrive_station_name='" + arrive_station_name + '\'' +
                ", carriage_id=" + carriage_id +
                ", train_id=" + train_id +
                ", seat_type='" + seat_type + '\'' +
                ", seat_type_age='" + seat_type_age + '\'' +
                ", train_seat='" + train_seat + '\'' +
                ", start_time='" + start_time + '\'' +
                ", arrive_time='" + arrive_time + '\'' +
                ", cost=" + cost +
                '}';
    }
}
