package jums;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
/**
 * ユーザー情報を持ちまわるJavaBeans
 * データベースのカラムと型に対応させている(DTO)。データの挿入、取り出しどちらにも便利
 * @version 1.00
 * @author hayashi-s
 */
public class UserDataDTO {
    private int userID;
    private String name;
    private Date birthday;
    private String tell;
    private int type;
    private String comment;
    private Timestamp newDate;
    
    
    public int getUserID() {
        return userID;
    }
    public void setUserID(int userID) {
        this.userID = userID;
    }
    
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    
    public Date getBirthday(){
        return birthday;
    }
    public void setBirthday(Date birthday){
        this.birthday = birthday;
    }
    
    public String getTell(){
        return tell;
    }
    public void setTell(String tell){
        this.tell = tell;
    }
    
    public int getType(){
        return type;
    }
    public void setType(int type){
        this.type = type;
    }
    
    public String getComment(){
        return comment;
    }
    public void setComment(String comment){
        this.comment = comment;
    }
    
    public Timestamp getNewDate() {
        return newDate;
    }
    public void setNewDate(Timestamp newDate) {
        this.newDate = newDate;
    }
    
    /*
    * 　UserDataDTOのフィールドどうし（但し、userIDとnewDateは除く）が
    *　等しいかを判定し、等しくなけれればArrayListに要素を追加していく
    */
    public ArrayList<String> userList (UserDataDTO udd){
        ArrayList<String> userL = new ArrayList<String>();
        Calendar birthday_this = JumsHelper.getInstance().calendar_chg(this.birthday);
        Calendar birthday_udd = JumsHelper.getInstance().calendar_chg(udd.getBirthday());
        
        if(!(this.name.equals(udd.getName()))){
            userL.add("name");
        }
        if(!(JumsHelper.getInstance().equals_cal(birthday_this,birthday_udd))){ 
            userL.add("birthday");
        }
        if(!(this.tell.equals(udd.getTell()))){
            userL.add("tell");
        }
        if(this.type != udd.getType()){
            userL.add("type");
        }
        if(!(this.comment.equals(udd.getComment()))){
            userL.add("comment");
        }
        return userL;
    }
    
    
    
}
