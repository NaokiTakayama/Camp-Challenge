/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Naoki
 */
public class Kadai5 extends HttpServlet {

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
            out.println("<title>Servlet Kadai5</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Kadai5 at " + request.getContextPath() + "</h1>");
            
            HttpSession rireki = request.getSession(true);
            
            final String result = "../../web/Kadai5.jsp";
            
            String name ,sex ,hobby;
            
            request.setCharacterEncoding("UTF-8");

            name = request.getParameter("name");
            sex = request.getParameter("sex");
            hobby = request.getParameter("hobby");

            
            
            
            if((name.equals("")) || (sex.equals("")) || (hobby.equals(""))){
                out.println("入力されていない項目があります。");
            }else{
                out.println("名前は、" + name + "。<br>");
                out.println("性別は、" + sex + "。<br>");
                out.println("趣味は、" + hobby + "。<br>");
            }
            
            rireki.setAttribute("lastName", name);
            rireki.setAttribute("lastSex", sex);
            rireki.setAttribute("lastHobby", hobby); 
            
            RequestDispatcher rd = request.getRequestDispatcher(result);
            rd.forward(request, response);
            
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
