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
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Naoki
 */
public class Kadai9 extends HttpServlet {

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
        
        Connection db_con = null;
        PreparedStatement db_st = null;
        ResultSet db_data = null;
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Ksdai9</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Ksdai9 at " + request.getContextPath() + "</h1>");
            
            request.setCharacterEncoding("UTF-8");
            
            String nameSei = request.getParameter("sei");
            String nameNa = request.getParameter("na");
            String tell = request.getParameter("tell");
            String ageString = request.getParameter("age");
            String birthdayString = request.getParameter("birthday");
            
            String name = nameSei + " " + nameNa;
            
            int age = Integer.parseInt(ageString);
            
            SimpleDateFormat birthdayFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date birthday_notsql = birthdayFormat.parse(birthdayString);
            java.sql.Date birthday = new java.sql.Date(birthday_notsql.getTime());
            
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            db_con = DriverManager.getConnection("jdbc:mysql://localhost:3306/profiles","takayama","nao912");
            db_st = db_con.prepareStatement("SELECT MAX(profilesID) FROM profiles");
            db_data = db_st.executeQuery();
            
            int yourID = 0;
            while(db_data.next()){
                yourID = db_data.getInt("MAX(profilesID)") + 1;
            }
            out.print("あなたのIDは：" + yourID);
                       
            db_st = db_con.prepareStatement("INSERT INTO profiles VALUES(?,?,?,?,?)");
            db_st.setInt(1, yourID);
            db_st.setString(2, name);
            db_st.setString(3, tell);
            db_st.setInt(4, age);
            db_st.setDate(5, birthday);
            db_st.executeUpdate();
            
            db_data.close();
            db_st.close();
            db_con.close();
            
            out.println("</body>");
            out.println("</html>");
        } catch (Exception e){
            PrintWriter out = response.getWriter();
            out.println("接続時にエラーが発生しました：" + e.toString());
        } finally {
            if(db_con != null){
                try{
                    db_con.close();
                } catch (Exception e_con){
                    System.out.println(e_con.getMessage());
                }
            }
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
