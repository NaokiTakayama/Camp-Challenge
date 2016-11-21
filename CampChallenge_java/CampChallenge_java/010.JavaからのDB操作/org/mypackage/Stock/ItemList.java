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
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;


/**
 *
 * @author Naoki
 */
public class ItemList extends HttpServlet {

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
            out.println("<title>Servlet ItemList</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ItemList at " + request.getContextPath() + "</h1>");
            
            Result_ItemList result = new Result_ItemList();
            
            request.setCharacterEncoding("UTF-8");
            
            String itemCodeString = request.getParameter("itemCode");
            String itemName = request.getParameter("itemName"); 
            String itemKind = request.getParameter("itemKind");
            String company = request.getParameter("company");
            
            boolean codeType = ifType(itemCodeString);
            boolean nameType = ifType(itemName);
            boolean kindType = ifType(itemKind);
            boolean companyType = ifType(company);
            
            boolean type = codeType || nameType || kindType || companyType;
            if(!type){
                response.sendRedirect("ItemList.jsp");
                
            }else{
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                db_con = DriverManager.getConnection("jdbc:mysql://localhost:3306/StockManagement1","takayama","nao912");


                db_st = sqlBun(db_con ,db_st ,itemCodeString ,itemName ,itemKind ,company);  


                db_data = db_st.executeQuery();
                while(db_data.next()){
                    result.set_itemCode(db_data.getInt("itemCode"));
                    result.set_itemName(db_data.getString("itemName"));
                    result.set_itemKind(db_data.getString("itemKind"));
                    result.set_company(db_data.getString("company"));
                }            


                db_data.close();
                db_st.close();
                db_con.close();
                
                
                HttpSession session = request.getSession();
                UserID userLogin = (UserID) session.getAttribute("UserLogin");
                
                if(userLogin == null){
                   response.sendRedirect("Login.jsp"); 
                }else{
                   request.setAttribute("RESULT", result);

                    RequestDispatcher rd = request.getRequestDispatcher("Result_ItemList.jsp");
                    rd.forward(request, response);
                }
                
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
            if((db_con != null) || (db_st != null) || (db_con != null)){
                try{
                    db_con.close();
                    db_st.close();
                    db_con.close();
                    
                } catch (Exception e_con){
                    System.out.println(e_con.getMessage());
                }
            }
        }
    }
public static PreparedStatement sqlBun(Connection db_con ,PreparedStatement db_st ,String itemCodeString,
                                        String itemName ,String itemKind ,String company)
throws SQLException{
    
    if(itemCodeString.isEmpty()){
        itemCodeString = "-1";
    }
    
    String sql = "SELECT * FROM item WHERE ";
    
    int itemCode = Integer.parseInt(itemCodeString);
    
    if(itemCode >= 1){
        sql += "itemCode = ?"; 
        
        db_st = db_con.prepareStatement(sql);
        db_st.setInt(1, itemCode);        
    }else {       
        if(!(itemName.isEmpty())){
            sql += "itemName = ?";
            
            if(!(itemKind.isEmpty())){
                sql += " AND itemKind = ?";
                
                if(!(company.isEmpty())){
                sql += " AND company = ?";
                
                db_st = db_con.prepareStatement(sql);
                db_st.setString(1, itemName);
                db_st.setString(2, itemKind);
                db_st.setString(3, company);
                }else{
                    db_st = db_con.prepareStatement(sql);
                    db_st.setString(1, itemName);
                    db_st.setString(2, itemKind);
                }
                
            }else if(itemKind.isEmpty()){
                
                if(!(company.isEmpty())){
                sql += " AND company = ?";
                
                db_st = db_con.prepareStatement(sql);
                db_st.setString(1, itemName);
                db_st.setString(2, company);
                }else{
                     db_st = db_con.prepareStatement(sql);
                     db_st.setString(1, itemName);
                }
            }           
        }else if(itemName.isEmpty()){
            
            if(!(itemKind.isEmpty())){
                sql += "itemKind = ?";
                
                if(!(company.isEmpty())){
                sql += " AND company = ?";
                
                db_st = db_con.prepareStatement(sql);
                db_st.setString(1, itemKind);
                db_st.setString(2, company);
                }else{
                    db_st = db_con.prepareStatement(sql);
                    db_st.setString(1, itemKind);
                }
                
            }else if(itemKind.isEmpty()){
                
                if(!(company.isEmpty())){
                sql += "company = ?";
                db_st = db_con.prepareStatement(sql);
                db_st.setString(1, company);
                }
            }        
        }        
    }
    return db_st;
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
