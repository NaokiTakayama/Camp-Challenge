
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Naoki
 */

abstract public class Human {
    ArrayList<Integer> myCard = new ArrayList<>();
    public abstract int open(ArrayList<Integer> ss);
    public abstract void setCard(ArrayList<Integer> tt);
    public abstract boolean checkSum(int goukei);   
}

