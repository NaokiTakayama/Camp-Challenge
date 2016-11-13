
import java.util.ArrayList;
import java.io.IOException;
import java.io.PrintWriter;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Naoki
 */
public class User extends Human{
    ArrayList<Integer> card = new ArrayList<>();
    Dealer d1 = new Dealer(card);
    
    public int open(ArrayList<Integer> ss){
        int tasu = 0;
        for(int i=0;i<ss.size();i++){
            if((ss.get(i)==1) && (tasu > 10)){
                tasu += 1;
            }else if(ss.get(i)==1){
                tasu += 10;
            }else{    
                tasu += ss.get(i);
            }    
        }
        return tasu;
    }    
    public void setCard(ArrayList<Integer> ss){
        for(int i=0;i<ss.size();i++){
            super.myCard.add(ss.get(i));
        }
    }
    public boolean checkSum(int goukei){
        boolean type;
        if(goukei <= 17){
            type = true;
        }else{
            type = false;
        }    
        return type;
    }
    
    //ゲーム進行
    public int calc(User u1 ,Dealer d1){
        while(u1.checkSum(u1.open(u1.myCard))){
            if(u1.open(u1.myCard) < 10){
                u1.setCard(d1.deal());
            }else if(u1.open(u1.myCard) < 17){
                u1.setCard(d1.hit());
            }
        }
        return u1.open(u1.myCard);
    }
    //場合分けして表示
    public void uprint(int u ,PrintWriter out) throws IOException{
        out.print("Userは、" + u + "点");
        
        if(u == 21){
            out.println("・・・<b>ブラックジャック！</b><br>");
        }else if(u > 21){
            out.println("・・・<b>バースト！</b><br>");
        }else{
            out.println("<br>");
        }
    }
}
