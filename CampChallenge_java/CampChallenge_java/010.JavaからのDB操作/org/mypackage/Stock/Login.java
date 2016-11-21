package org.mypackage.Stock;

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
import javax.servlet.http.HttpSession;

/**
 *
 * @author Naoki
 */
public class Login extends HttpServlet {

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
            out.println("<title>Servlet Login</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Login at " + request.getContextPath() + "</h1>");
            
            
            request.setCharacterEncoding("UTF-8");
            
            String userID = request.getParameter("userID");
            String password = request.getParameter("password");
            
            
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            db_con = DriverManager.getConnection("jdbc:mysql://localhost:3306/StockManagement1","takayama","nao912");
            
            db_st = db_con.prepareStatement("SELECT userID,password FROM user");
            
            db_data = db_st.executeQuery();
            
            boolean check = loginCheck(userID,password,db_data);
            
            String targetForward = "Menu_item.jsp"; //次の画面（商品画面）へ
            String targetBack = "Login.jsp"; //ログイン画面に戻る
            
            if(check){
                UserID userLogin = new UserID(userID,password);
                HttpSession session = request.getSession();
                session.setAttribute("UserLogin",userLogin);
                
                response.sendRedirect(targetForward);
            }else{
                response.sendRedirect(targetBack);
            }
            
            
            db_data.close();          
            db_st.close();
            db_con.close();
            
            out.println("</body>");
            out.println("</html>");
        }catch (SQLException e_sql){
            PrintWriter out = response.getWriter();
            out.println("接続時にエラーが発生しました：" + e_sql.toString());
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
public static boolean loginCheck(String userID,String password,ResultSet db_data)
throws SQLException{
    boolean check = false;
    while(db_data.next()){
        if((userID.equals(db_data.getString("userID"))) && (password.equals(db_data.getString("password")))){
            check = true;       
            break;
        }
    }  
    return check;
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
