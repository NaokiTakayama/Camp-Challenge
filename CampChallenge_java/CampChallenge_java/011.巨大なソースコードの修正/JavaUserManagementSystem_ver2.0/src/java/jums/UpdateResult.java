package jums;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author hayashi-s
 */
public class UpdateResult extends HttpServlet {

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
        PrintWriter out = response.getWriter();
        try {
            request.setCharacterEncoding("UTF-8");//リクエストパラメータの文字コードをUTF-8に変更
            
            HttpSession hs = request.getSession();
            
            //アクセスルートチェック
            String accesschk = request.getParameter("ac");
            if(accesschk ==null || (Integer)hs.getAttribute("ac")!=Integer.parseInt(accesschk)){
                throw new Exception("不正なアクセスです");
            }
           
            //フォームからの入力を取得して、JavaBeansに格納
            UserDataBeans udb = new UserDataBeans();

            udb.setName(request.getParameter("name"));
            udb.setYear(request.getParameter("year"));
            udb.setMonth(request.getParameter("month"));
            udb.setDay(request.getParameter("day"));
            udb.setType(request.getParameter("type"));
            udb.setTell(request.getParameter("tell"));
            udb.setComment(request.getParameter("comment"));
            
            //記入欄に空欄があれば変更画面に戻す
            if(!(udb.chkproperties().isEmpty())){
               request.setAttribute("mode1", "REINPUT");
               request.getRequestDispatcher("/update.jsp").forward(request, response);
            }else{
                //DTOオブジェクトにマッピング。DB専用のパラメータに変換
                UserDataDTO updateData = new UserDataDTO();
                udb.UD2DTOMapping(updateData);

                UserDataDTO udd = (UserDataDTO)hs.getAttribute("resultData");

                /*
                *　 元のデータ（udd）と送られてきたデータupdateData
                *　に変更内容があるか判定
                */
                ArrayList<String> userList = udd.userList(updateData);

                /*　
                *　 userListが空であれば（updateDataが元のデータと変化していなかったら）
                *　変更画面に戻す
                */
                if(userList.isEmpty()){
                   request.setAttribute("mode2", "REINPUT");
                   request.getRequestDispatcher("/update.jsp").forward(request, response);
                }else{
                    // uddのuserIDを取得し、updateDataにセット
                    updateData.setUserID(udd.getUserID());

                    /*
                    * 　データのアップデートを行う（この際、アップデータ箇所の情報が入った
                    *　userListと、アップデートする値を持ったupdateDataを、引数として
                    *　渡す。
                    */
                    UserDataDAO.getInstance().update(userList,updateData); 
                    UserDataDTO updateData_src = UserDataDAO.getInstance().searchByID(updateData);

                    //セッションの値の消去
                    hs.invalidate();

                    request.setAttribute("updateData", updateData_src);
                    request.getRequestDispatcher("/updateresult.jsp").forward(request, response);
                }
            }
        }catch(Exception e){
            //何らかの理由で失敗したらエラーページにエラー文を渡して表示。想定は不正なアクセスとDBエラー
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        } finally {
            out.close();
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
