package DS_operation;

import java.sql.Time;

/**
 * @auther DONG BQ
 * @DATE 2020/11/1620:02
 */
public class Trans_info_finals {
    private int trainida,trainidb,startid,middleida,middleidb,arriveid;
    private int ASW,AYD,AED,AOT,BSW,BYD,BED,BOT,counta,countb;
    private String atrainname,btrainname,startname,middlename,arrivename;
    private Time starttime,middletimea,middletimeb,arrivetime;

    public Trans_info_finals(){}

    public int getCounta() {
        return counta;
    }

    public void setCounta(int counta) {
        this.counta = counta;
    }

    public void setCountb(int countb) {
        this.countb = countb;
    }

    public int getCountb() {
        return countb;
    }

    public void setMiddletimea(Time middletimea) {
        this.middletimea = middletimea;
    }

    public void setMiddletimeb(Time middletimeb) {
        this.middletimeb = middletimeb;
    }

    public void setArrivetime(Time arrivetime) {
        this.arrivetime = arrivetime;
    }

    public void setStarttime(Time starttime) {
        this.starttime = starttime;
    }

    public Time getStarttime() {
        return starttime;
    }

    public Time getMiddletimea() {
        return middletimea;
    }

    public Time getMiddletimeb() {
        return middletimeb;
    }

    public Time getArrivetime() {
        return arrivetime;
    }

    public Trans_info_finals(int trainida, int trainidb, int startid, int middleida, int middleidb, int arriveid, int ASW, int AYD, int AED, int AOT, int BSW, int BYD, int BED, int BOT, String atrainname, String btrainname, String startname, String middlename, String arrivename, Time starttime, Time middletimea, Time middletimeb, Time arrivetime) {
        this.trainida = trainida;
        this.trainidb = trainidb;
        this.startid = startid;
        this.middleida = middleida;
        this.middleidb = middleidb;
        this.arriveid = arriveid;
        this.ASW = ASW;
        this.AYD = AYD;
        this.AED = AED;
        this.AOT = AOT;
        this.BSW = BSW;
        this.BYD = BYD;
        this.BED = BED;
        this.BOT = BOT;
        this.atrainname = atrainname;
        this.btrainname = btrainname;
        this.startname = startname;
        this.middlename = middlename;
        this.arrivename = arrivename;
        this.starttime = starttime;
        this.middletimea = middletimea;
        this.middletimeb = middletimeb;
        this.arrivetime = arrivetime;
    }

    public void setTrainida(int trainida) {
        this.trainida = trainida;
    }


    public void setTrainidb(int trainidb) {
        this.trainidb = trainidb;
    }

    public Trans_info_finals(int trainida, int trainidb, int startid, int middleida, int middleidb, int arriveid, int ASW, int AYD, int AED, int AOT, int BSW, int BYD, int BED, int BOT, String atrainname, String btrainname, String startname, String middlename, String arrivename, Time starttime, Time middletimea, Time middletimeb, Time arrivetime, int counta, int countb) {
        this.trainida = trainida;
        this.trainidb = trainidb;
        this.startid = startid;
        this.middleida = middleida;
        this.middleidb = middleidb;
        this.arriveid = arriveid;
        this.ASW = ASW;
        this.AYD = AYD;
        this.AED = AED;
        this.AOT = AOT;
        this.BSW = BSW;
        this.BYD = BYD;
        this.BED = BED;
        this.BOT = BOT;
        this.counta = counta;
        this.countb = countb;
        this.atrainname = atrainname;
        this.btrainname = btrainname;
        this.startname = startname;
        this.middlename = middlename;
        this.arrivename = arrivename;
        this.starttime = starttime;
        this.middletimea = middletimea;
        this.middletimeb = middletimeb;
        this.arrivetime = arrivetime;
    }

    public void setStartid(int startid) {
        this.startid = startid;
    }

    public void setMiddleida(int middleida) {
        this.middleida = middleida;
    }

    public void setMiddleidb(int middleidb) {
        this.middleidb = middleidb;
    }

    public void setArriveid(int arriveid) {
        this.arriveid = arriveid;
    }

    public void setASW(int ASW) {
        this.ASW = ASW;
    }

    public void setAYD(int AYD) {
        this.AYD = AYD;
    }

    public void setAOT(int AOT) {
        this.AOT = AOT;
    }

    public void setBSW(int BSW) {
        this.BSW = BSW;
    }

    public void setBYD(int BYD) {
        this.BYD = BYD;
    }

    public void setBED(int BED) {
        this.BED = BED;
    }

    public void setBOT(int BOT) {
        this.BOT = BOT;
    }

    public void setAtrainname(String atrainname) {
        this.atrainname = atrainname;
    }

    public void setBtrainname(String btrainname) {
        this.btrainname = btrainname;
    }

    public void setStartname(String startname) {
        this.startname = startname;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public void setArrivename(String arrivename) {
        this.arrivename = arrivename;
    }

    public void setAED(int AED) {
        this.AED = AED;
    }

    public int getTrainida() {
        return trainida;
    }

    public int getTrainidb() {
        return trainidb;
    }

    public int getStartid() {
        return startid;
    }

    public int getMiddleida() {
        return middleida;
    }

    public int getMiddleidb() {
        return middleidb;
    }

    public int getArriveid() {
        return arriveid;
    }

    public int getASW() {
        return ASW;
    }

    public int getAYD() {
        return AYD;
    }

    public int getAED() {
        return AED;
    }

    public int getAOT() {
        return AOT;
    }

    public int getBSW() {
        return BSW;
    }

    public int getBYD() {
        return BYD;
    }

    public int getBED() {
        return BED;
    }

    public int getBOT() {
        return BOT;
    }

    public String getAtrainname() {
        return atrainname;
    }

    public String getBtrainname() {
        return btrainname;
    }

    public String getStartname() {
        return startname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public String getArrivename() {
        return arrivename;
    }

	@Override
	public String toString() {
		return "Trans_info_finals [trainida=" + trainida + ", trainidb=" + trainidb + ", startid=" + startid
				+ ", middleida=" + middleida + ", middleidb=" + middleidb + ", arriveid=" + arriveid + ", ASW=" + ASW
				+ ", AYD=" + AYD + ", AED=" + AED + ", AOT=" + AOT + ", BSW=" + BSW + ", BYD=" + BYD + ", BED=" + BED
				+ ", BOT=" + BOT + ", counta=" + counta + ", countb=" + countb + ", atrainname=" + atrainname
				+ ", btrainname=" + btrainname + ", startname=" + startname + ", middlename=" + middlename
				+ ", arrivename=" + arrivename + ", starttime=" + starttime + ", middletimea=" + middletimea
				+ ", middletimeb=" + middletimeb + ", arrivetime=" + arrivetime + "]";
	}

    
}
