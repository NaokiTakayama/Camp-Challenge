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
public class Kadai12 extends HttpServlet {

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
            out.println("<title>Servlet Kadai12</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Kadai12 at " + request.getContextPath() + "</h1>");
            
            request.setCharacterEncoding("UTF-8");
            
            String nameSei = request.getParameter("sei");
            String nameNa = request.getParameter("na"); 
            String ageString = request.getParameter("age");
            String birthdayString = request.getParameter("birthday");
            
            if((nameSei.equals("")) && (nameNa.equals("")) && (ageString.equals("")) && (birthdayString.equals(""))){
                out.print("入力してください。");
            }else{
                String name = nameSei + " " + nameNa;
                int age = -1;
                java.sql.Date birthday = null;
                
                if((nameSei.equals("")) || (nameNa.equals(""))){
                    name = "";
                }
                
                if(!ageString.equals("")){
                    age = Integer.parseInt(ageString);
                }
                
                if(!birthdayString.equals("")){
                    SimpleDateFormat birthdayFormat = new SimpleDateFormat("yyyy-MM-dd");
                    java.util.Date birthday_notsql = birthdayFormat.parse(birthdayString);
                    birthday = new java.sql.Date(birthday_notsql.getTime());
                }
                
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                db_con = DriverManager.getConnection("jdbc:mysql://localhost:3306/profiles","takayama","nao912");
                db_st = sqlBun(db_con,db_st,ageString,birthdayString,name,age,birthday);
                
                db_data = db_st.executeQuery();
                
                while(db_data.next()){
                    out.print("profilesID：" + db_data.getInt("profilesID"));
                    out.print("、name："+ db_data.getString("name"));
                    out.print("、tell："+ db_data.getString("tell"));
                    out.print("、age："+ db_data.getInt("age"));
                    out.print("、birthday："+ db_data.getDate("birthday") + "<br>");
                }
            }
            db_data.close();
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
public static PreparedStatement sqlBun(Connection db_con ,PreparedStatement db_st ,String ageString ,String birthdayString,
                                        String name ,int age ,Date birthday)
throws SQLException{
    String sql = "SELECT * FROM profiles WHERE ";
    
    if(!(name.equals(""))){
        sql += "name = ?";
        
        if(!(ageString.equals(""))){
            sql += " AND age = ?";
  
            if(!(birthdayString.equals(""))){
            sql += " AND birthday = ?";
            
            db_st = db_con.prepareStatement(sql);
            db_st.setString(1, name);
            db_st.setInt(2, age);
            db_st.setDate(3, birthday);
            }else{
                db_st = db_con.prepareStatement(sql);
                db_st.setString(1, name);
                db_st.setInt(2, age);
            }            
        }else if(ageString.equals("")){
            
            if(!(birthdayString.equals(""))){
            sql += " AND birthday = ?";
            
            db_st = db_con.prepareStatement(sql);
            db_st.setString(1, name);
            db_st.setDate(2, birthday);
            }else{
                db_st = db_con.prepareStatement(sql);
                db_st.setString(1, name);
            }
        }
    
    }else if(name.equals("")){
        
        if(!(ageString.equals(""))){
            sql += "age = ?";
            
            if(!(birthdayString.equals(""))){
            sql += " AND birthday = ?";
            
            db_st = db_con.prepareStatement(sql);
            db_st.setInt(1, age);
            db_st.setDate(2, birthday);
            }else{
                db_st = db_con.prepareStatement(sql);
                db_st.setInt(1, age);
            }
        }else if(ageString.equals("")){
            
            if(!(birthdayString.equals(""))){
            sql += "birthday = ?";
            
            db_st = db_con.prepareStatement(sql);
            db_st.setDate(1, birthday);
            }
        }
    }
    
    return db_st;
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
