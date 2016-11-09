/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Naoki
 */
import java.io.IOException;
import java.io.PrintWriter;;

public class kiso3 {
    public int[] rand(int[] r){
        for(int i=0;i<r.length;i++){
            r[i] = new java.util.Random().nextInt(100);
        }
        return r;
    }
    
    public void pprint(int[] r,PrintWriter out) throws IOException{
        for(int i=0;i<r.length;i++){
            out.print(r[i]);
            out.print("<br>");
        }
    }
    
}
