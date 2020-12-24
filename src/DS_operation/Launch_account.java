package DS_operation;

/**
 * @auther DONG BQ
 * @DATE 2020/10/2316:46
 */
public class Launch_account {
    private String ID;
    private String passwd;
    public Launch_account(String ID,String passwd){
        this.ID=ID;
        this.passwd=passwd;
    }

    public String getID() {
        return ID;
    }

    public String getPasswd() {
        return passwd;
    }
}
