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
public class ItemRecord extends HttpServlet {

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
            out.println("<title>Servlet ItemRecord</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ItemRecord at " + request.getContextPath() + "</h1>");
            
            
            request.setCharacterEncoding("UTF-8");
            
            String itemCodeString = request.getParameter("itemCode");
            String itemName = request.getParameter("itemName"); 
            String itemKind = request.getParameter("itemKind");
            String company = request.getParameter("company");
            
            boolean codeType = ifType(itemCodeString);
            boolean nameType = ifType(itemName);
            boolean kindType = ifType(itemKind);
            boolean companyType = ifType(company);
            
            boolean type = codeType && nameType && kindType && companyType;
            
            if(type){    

                int itemCode = Integer.parseInt(itemCodeString);

                Class.forName("com.mysql.jdbc.Driver").newInstance();
                db_con = DriverManager.getConnection("jdbc:mysql://localhost:3306/StockManagement1","takayama","nao912");

                db_st = db_con.prepareStatement("INSERT INTO item VALUES(?,?,?,?)");
                db_st.setInt(1, itemCode);
                db_st.setString(2, itemName);
                db_st.setString(3, itemKind);
                db_st.setString(4, company);

                db_st.executeUpdate();
                
                db_con.close();
                db_st.close();
                
                HttpSession session = request.getSession();
                UserID userLogin = (UserID) session.getAttribute("UserLogin");
                
                if(userLogin == null){
                   response.sendRedirect("Login.jsp");
                   return;
                }else{
                   response.sendRedirect("ItemRecord.jsp");
                   return;
                }
               
            }else{
                response.sendRedirect("ItemRecord.jsp");
                
            }
            
            out.println("</body>");
            out.println("</html>");
           
        } catch (SQLException e_sql){
            PrintWriter out = response.getWriter();
            out.println("接続時にエラーが発生しました：" + e_sql.toString());
        } catch (Exception e){
            PrintWriter out = response.getWriter();
            out.println("接続時にエラーが発生しました：" + e.toString());
        } finally {
            if((db_con != null) || (db_st != null)){
                try{
                    db_con.close();
                    db_st.close();
                    
                } catch (Exception e_con){
                    System.out.println(e_con.getMessage());
                }
            }
        }
    }

    public boolean ifType(String s){
        boolean type = true;
        if((s.isEmpty()) || (s == null)){
            type = false;
        }        
        return type;        
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
