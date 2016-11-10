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

/**
 *
 * @author Naoki
 */
public class mojiretu extends HttpServlet {

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
            out.println("<title>Servlet mojiretu</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet mojiretu at " + request.getContextPath() + "</h1>");
            
            //課題５
            String namae = "たかやまなおき";
            int mojisu ,bytesuu;
            mojisu = namae.length();
            bytesuu = namae.getBytes("UTF-8").length;
            out.println("課題５：「たかやまなおき」の文字数は" + mojisu + "、バイト数は"+ bytesuu + "<br>");
            
            //課題６
            String mailAdress = "gus@gmail.com";
            String bubunMail;
            int atto;
            atto = mailAdress.indexOf("@");
            bubunMail = mailAdress.substring(atto);
            out.println("課題６：" + bubunMail + "<br>");
            
            //課題７
            String motoBun = "きょUはぴIえIちぴIのくみこみかんすUのがくしゅUをしてIます";
            String uBun,iBun;
            uBun = motoBun.replace("U","う");
            iBun = uBun.replace("I","い");
            out.println("課題７：" + iBun + "<br>");
            
            out.println("</body>");
            out.println("</html>");
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
