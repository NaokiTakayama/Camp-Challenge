/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jums;
import java.io.Serializable;
/**
 *formから得た情報を、そのまま扱うクラス
 * @author Naoki
 */
public class UserDataBeans implements Serializable{
    
    private String name;
    private String year;
    private String month;
    private String day;
    private String type;
    private String tell;
    private String comment;
    
    public UserDataBeans(){};
    
    
    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }
    
    public String getYear(){
        return this.year;
    }
    public void setYear(String year){
        this.year = year;
    }
    
    public String getMonth(){
        return this.month;
    }
    public void setMonth(String month){
        this.month = month;
    }
    
    public String getDay(){
        return this.day;
    }
    public void setDay(String day){
        this.day = day;
    }
    
    public String getType(){
        return this.type;
    }
    public void setType(String type){
        this.type = type;
    }
    
    public String getTell(){
        return this.tell;
    }
    public void setTell(String tell){
        this.tell = tell;
    }
    
    public String getComment(){
        return this.comment;
    }
    public void setComment(String comment){
        this.comment = comment;
    }

}
