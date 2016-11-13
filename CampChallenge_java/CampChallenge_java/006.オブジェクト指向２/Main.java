/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
/**
 *
 * @author Naoki
 */
public class Main extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Main</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Main at " + request.getContextPath() + "</h1>");
            ArrayList<Integer> card = new ArrayList<>();
            int dsum ,usum;
            
            Dealer d1 = new Dealer(card);
            User u1 = new User();
            
            dsum = d1.calc(d1);
            usum = u1.calc(u1,d1);
            
            d1.dprint(dsum, out);
            u1.uprint(usum, out);
            pprint(dsum,usum,out);
            out.println("</body>");
            out.println("</html>");
        }
    }
public void pprint(int d,int u,PrintWriter out) throws IOException{
        if((d > 21) && (u > 21)){
            out.println("Dealerの勝ち");
        }else if(d == u){
            out.println("引き分け");
        }else if(d > 21){
            out.println("Userの勝ち");
        }else if((d < u) && (u <= 21)){
            out.println("Userの勝ち");
        }else{
            out.println("Dealerの勝ち");
        }
    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
