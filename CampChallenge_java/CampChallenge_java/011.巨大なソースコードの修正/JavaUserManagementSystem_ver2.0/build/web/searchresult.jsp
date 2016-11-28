<%@page import="jums.JumsHelper"
        import="jums.UserDataDTO" 
        import="java.util.ArrayList"
        import="javax.servlet.http.HttpSession"%>
<%
    JumsHelper jh = JumsHelper.getInstance();
    HttpSession hs = request.getSession();
    ArrayList<UserDataDTO> udd = (ArrayList<UserDataDTO>)hs.getAttribute("resultDataArray");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JUMS検索結果画面</title>
    </head>
    <body>
        <h1>検索結果</h1>
        <table border=1>
            <tr>
                <th>名前</th>
                <th>生年</th>
                <th>種別</th>
                <th>登録日時</th>
            </tr>
            <%for(int i=0;i<udd.size();i++){%>
            <tr>
                <td><a href="ResultDetail?id=<%= udd.get(i).getUserID()%>&ac=<%= hs.getAttribute("ac")%>"><%= udd.get(i).getName()%></a></td>
                <td><%= udd.get(i).getBirthday()%></td>
                <td><%=jh.exTypenum(udd.get(i).getType())%></td>
                <td><%= udd.get(i).getNewDate()%></td>
            </tr>
            <%}%>
        </table>
        <br><br>
    <form action="Search" method="POST">
        <input type="hidden" name="mode" value="REINPUT">
        <input type="submit" name="no" value="検索画面に戻る">
    </form>
        <br><br>
    <%=jh.home()%>
    </body>
</html>
