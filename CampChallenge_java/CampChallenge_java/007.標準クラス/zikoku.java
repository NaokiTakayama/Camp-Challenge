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
import java.util.*;
import java.text.*;

/**
 *
 * @author Naoki
 */
public class zikoku extends HttpServlet {

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
            out.println("<title>Servlet zikoku</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet zikoku at " + request.getContextPath() + "</h1>");
            
            //課題１
            Calendar gantan = Calendar.getInstance();
            gantan.set(2016,1,1,0,0,0);
            Date gantanTime = gantan.getTime();
            out.println("課題１：" + gantanTime.getTime() +"<br>");
            
            //課題２
            Date ima = new Date();
            SimpleDateFormat imaCal = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String imaZikan = imaCal.format(ima);
            out.println("課題２：現在、" + imaZikan + "<br>");
            
            //課題３
            Calendar juiti = Calendar.getInstance();
            juiti.set(2016,11,4,10,0,0);
            Date juitiTime = juiti.getTime();  //タイムスタンプの取得
            SimpleDateFormat juitiCal = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String juitiZikan = juitiCal.format(ima);
            out.println("課題３：" + juitiZikan +"<br>");
            
            //課題４
            Calendar zenGantan = Calendar.getInstance();
            zenGantan.set(2015,1,1,0,0,0);
            Date zenGantanTime = zenGantan.getTime();
            
            Calendar zenMisoka = Calendar.getInstance();
            zenMisoka.set(2015,12,31,23,59,59);
            Date zenMisokaTime = zenMisoka.getTime();
            
            long itinenTime = zenMisokaTime.getTime() - zenGantanTime.getTime();
            out.println("課題４：" + itinenTime + "ミリ秒<br>");
            
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
