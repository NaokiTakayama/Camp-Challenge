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
/**
 *
 * @author Naoki
 */
public class Kadai11 extends HttpServlet {

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
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Kadai11</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Kadai11 at " + request.getContextPath() + "</h1>");
            
            request.setCharacterEncoding("UTF-8");
            
            String idString = request.getParameter("id");
            String nameSei = request.getParameter("sei");
            String nameNa = request.getParameter("na");
            String tell = request.getParameter("tell");
            String ageString = request.getParameter("age");
            String birthdayString = request.getParameter("birthday");
            
            int id = Integer.parseInt(idString);
            
            String name = nameSei + " " + nameNa;
            
            int age = Integer.parseInt(ageString);
            
            SimpleDateFormat birthdayFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date birthday_notsql = birthdayFormat.parse(birthdayString);
            java.sql.Date birthday = new java.sql.Date(birthday_notsql.getTime());
            
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            db_con = DriverManager.getConnection("jdbc:mysql://localhost:3306/profiles","takayama","nao912");
            
            db_st = db_con.prepareStatement("UPDATE profiles SET name = ?,tell = ?,age = ?,birthday = ? WHERE profilesID = ?");
            db_st.setString(1, name);
            db_st.setString(2, tell);
            db_st.setInt(3, age);
            db_st.setDate(4, birthday);
            db_st.setInt(5, id);
            
            db_st.executeUpdate();
            
            db_st.close();
            db_con.close();
            
            out.println("</body>");
            out.println("</html>");
        }catch (Exception e){
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
