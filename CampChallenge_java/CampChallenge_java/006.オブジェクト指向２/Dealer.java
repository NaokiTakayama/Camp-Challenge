
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
public class Dealer extends Human{
    private final ArrayList<Integer> card = new ArrayList<>();
    
    public ArrayList<Integer> deal(){
        ArrayList<Integer> dd = new ArrayList<>();
        for(int i=0;i<2;i++){
              dd.add(this.hit().get(0));
        }
        return dd;
    }
    
    public ArrayList<Integer> hit(){
        ArrayList<Integer> hh = new ArrayList<>();
        int r;
        r= new java.util.Random().nextInt(this.card.size());
        hh.add(this.card.get(r));
        this.card.remove(r);    
        return hh;
    }
    
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
    public int calc(Dealer d1){
        while(d1.checkSum(d1.open(d1.myCard))){
            if(d1.open(d1.myCard) < 10){
                d1.setCard(d1.deal());
            }else if(d1.open(d1.myCard) < 17){
                d1.setCard(d1.hit());
            }
        }
        return d1.open(d1.myCard);
    }
    
    //場合分けして表示
    public void dprint(int d ,PrintWriter out) throws IOException{
        out.print("Dealerは、" + d + "点");
        
        if(d == 21){
            out.println("・・・<b>ブラックジャック！</b><br>");
        }else if(d > 21){
            out.println("・・・<b>バースト！</b><br>");
        }else{
            out.println("<br>");
        }
    }
    
    public Dealer(ArrayList<Integer> ss){
        for(int i=1;i<10;i++){
            for(int j=0;j<4;j++){
                this.card.add(i);
            }
        }
        for(int k=0;k<16;k++){
            this.card.add(10);
        }
        ss = this.card;       
    }
    
}
