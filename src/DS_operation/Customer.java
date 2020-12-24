package DS_operation;

/**
 * @auther DONG BQ
 * @DATE 2020/10/2120:41
 */
public class Customer {
    private String ID;
    private String name;
    private int sex;
    private String birth;
    private String passwd;

    public Customer(String ID,String name,int sex,String birth,String passwd){
        this.ID=ID;
        this.name=name;
        this.sex=sex;
        this.birth=birth;
        this.passwd=passwd;
    }

    public String getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public int getSex() {
        return sex;
    }

    public String getBirth() {
        return birth;
    }

    public String getPasswd() {
        return passwd;
    }

    @Override
    public String toString() {
        return name+"sex:"+sex+" birth:"+birth;
    }
}
