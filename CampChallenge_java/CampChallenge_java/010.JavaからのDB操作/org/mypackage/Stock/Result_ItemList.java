package org.mypackage.Stock;


import java.util.ArrayList;
import java.io.Serializable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Naoki
 */
public class Result_ItemList implements Serializable{
    
    private ArrayList<Integer> itemCode = new ArrayList<>();
    private ArrayList<String> itemName = new ArrayList<>();
    private ArrayList<String> itemKind = new ArrayList<>();
    private ArrayList<String> company = new ArrayList<>();
    
    public Result_ItemList() {};
    
    public int get_itemCode(int i){
        return itemCode.get(i);
    }

    public void set_itemCode(int itemCode_int){
        this.itemCode.add(itemCode_int);
    }
    
    public String get_itemName(int i){
        return itemName.get(i);
    }

    public void set_itemName(String itemName_str){
        this.itemName.add(itemName_str);
    }
    
    public String get_itemKind(int i){
        return itemKind.get(i);
    }

    public void set_itemKind(String itemKind_str){
        this.itemKind.add(itemKind_str);
    }
    
    public String get_company(int i){
        return company.get(i);
    }

    public void set_company(String company_str){
        this.company.add(company_str);
    }
    
     public int get_sizeitemCode(){
        return itemCode.size();
    }
}
