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
public class kadai7 extends HttpServlet {

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
            out.println("<title>Servlet kadai7</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet kadai7 at " + request.getContextPath() + "</h1>");
            String[][] profl = new String[3][4];
            profl = ppmap();
            for(int i=0;i<3;i++){
                for(int j=0;j<3;j++){
                    if(profl[i][j+1] != null){
                        out.print(profl[i][j+1]);
                    }else{
                        continue;
                    }
                }
                out.println("<br>");
	    }
            out.println("</body>");
            out.println("</html>");
        }
    }
String[][] ppmap(){
    String[][] pmap = new String[3][4];
    pmap[0][0] = "010220"; pmap[0][1] = "高山"; pmap[0][2] = "1989/09/12"; pmap[0][3] = "千葉県";
    pmap[1][0] = "010221"; pmap[1][1] = "山本"; pmap[1][2] = "1990/11/01"; pmap[1][3] = null;
    pmap[2][0] = "010222"; pmap[2][1] = "本田"; pmap[2][2] = "2000/01/23"; pmap[2][3] = "長野県";
    return pmap;
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
