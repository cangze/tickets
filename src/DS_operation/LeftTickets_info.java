package DS_operation;

/**
 * @auther DONG BQ
 * @DATE 2020/10/310:11
 */
public class LeftTickets_info {
    private String train_name;
    private String tickets_type;
    private int left_num;
    public LeftTickets_info(String train_name,String tickets_type,int left_num){
        this.train_name=train_name;
        this.tickets_type=tickets_type;
        this.left_num=left_num;
    }
    public LeftTickets_info(){}

    public void setLeft_num(int left_num) {
        this.left_num = left_num;
    }

    public void setTrain_name(String train_name) {
        this.train_name = train_name;
    }

    public void setTickets_type(String tickets_type) {
        this.tickets_type = tickets_type;
    }

    public int getLeft_num() {
        return left_num;
    }

    public String getTickets_type() {
        return tickets_type;
    }

    public String getTrain_name() {
        return train_name;
    }

    @Override
    public String toString() {
        return getTrain_name()+":"+getTickets_type()+": "+getLeft_num();
    }
}
