package DS_operation;

/**
 * @auther DONG BQ
 * @DATE 2020/10/311:00
 */
public class Van_info {
    private int van_id,van_row,seat_sum;
    private String van_type;
    public Van_info(){

    }
    public Van_info(int van_id,String van_type,int van_row,int seat_sum){
        this.van_id=van_id;
        this.van_type=van_type;
        this.van_row=van_row;
        this.seat_sum=seat_sum;
    }

    public void setSeat_sum(int seat_sum) {
        this.seat_sum = seat_sum;
    }

    public void setVan_id(int van_id) {
        this.van_id = van_id;
    }

    public void setVan_row(int van_row) {
        this.van_row = van_row;
    }

    public void setVan_type(String van_type) {
        this.van_type = van_type;
    }

    public int getSeat_sum() {
        return seat_sum;
    }

    public int getVan_id() {
        return van_id;
    }

    public String getVan_type() {
        return van_type;
    }

    public int getVan_row() {
        return van_row;
    }

    @Override
    public String toString() {
        return van_id+":"+van_type+":"+seat_sum;
    }
}
