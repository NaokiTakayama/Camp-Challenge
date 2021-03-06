/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.logging.*;
import java.util.*;
import java.io.File;
import java.io.FileReader;

/**
 *
 * @author Naoki
 */
public class OuyouLog extends HttpServlet {

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
            out.println("<title>Servlet OuyouLog</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet OuyouLog at " + request.getContextPath() + "</h1>");
            
            final Logger mulBigDecimal = Logger.getLogger("OuyouLog");
            final String filePath = "mulBigDecimal.log";            
            
            FileHandler fileHandler = new FileHandler(filePath);
            fileHandler.setFormatter(new SimpleFormatter());
            mulBigDecimal.addHandler(fileHandler);
            mulBigDecimal.setLevel(Level.FINE);
            mulBigDecimal.setUseParentHandlers(false);
 

            Date openDate = new Date();
            String openDateString = String.valueOf(openDate);                        
            mulBigDecimal.info("開始　" + openDateString);
                        
            
            String format = "0.000000000000000";
            DecimalFormat dFormat = new DecimalFormat(format);
            
            int a1 ,b1;
            Double a2 ,b2;
            a1 = new java.util.Random().nextInt(100);
            b1 = new java.util.Random().nextInt(100);
            
            a2 = (double)a1/1000;
            b2 = (double)b1/1000;
            
            String a3 = String.valueOf(a2);
            String b3 = String.valueOf(b2);
            
            BigDecimal a4 = new BigDecimal(a3);
            BigDecimal b4 = new BigDecimal(b3);
            
            BigDecimal mul = a4.multiply(b4);
            
            String bigMul = dFormat.format(mul);
            
            out.println(a1 + "　" + a2 + "　" + a3 + "<br>");
            out.println(b1 + "　" + b2 + "　" + b3 + "<br>");
            out.println(mul + "<br>");
            out.println(bigMul + "<br>");
            
            
            
            Date closeDate = new Date();            
            String closeDateString = String.valueOf(closeDate);                        
            mulBigDecimal.info("終了　" + closeDateString);
            
            
            File txt = new File(this.getServletContext().getRealPath(filePath));
            FileReader ftxt = new FileReader(txt);
            BufferedReader btxt = new BufferedReader(ftxt);
            
            String stxt;
            while((stxt = btxt.readLine()) != null){
                out.println(stxt + "<br>");
            }
            
            btxt.close();
            
            
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
