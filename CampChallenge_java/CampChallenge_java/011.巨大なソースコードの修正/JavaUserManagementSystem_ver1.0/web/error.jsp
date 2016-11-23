<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="jums.JumsHelper" %>
<%@page import="javax.servlet.http.HttpSession" %>


<%
    //セッション内の情報を全て削除
    HttpSession hs = request.getSession();
    
    hs.removeAttribute("ac");
    hs.removeAttribute("Beans");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>error</title>
    </head>
    <body>
        エラーが発生しました。以下の項目を確認してください。<br>
        <%=request.getAttribute("error")%>
        <br>
        <%=JumsHelper.getInstance().home()%>
    </body>
</html>
