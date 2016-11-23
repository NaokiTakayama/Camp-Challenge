<%@page import="javax.servlet.http.HttpSession" %>
<%@page import="jums.JumsHelper" %>
<%@page import="jums.UserDataBeans" %>
<%
    HttpSession hs = request.getSession();
    
    UserDataBeans Beans = (UserDataBeans)hs.getAttribute("Beans");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JUMS登録結果画面</title>
    </head>
    <body>
        <h1>登録結果</h1><br>
        名前:<%= Beans.getName()%><br>
        生年月日:<%= Beans.getYear()+"年"+Beans.getMonth()+"月"+Beans.getDay()+"日"%><br>
        種別:<%= Beans.getType()%><br>
        電話番号:<%= Beans.getTell()%><br>
        自己紹介:<%= Beans.getComment()%><br><br>
        以上の内容で登録しました。<br>
        <br>
        <%=JumsHelper.getInstance().home()%>
    </body>
</html>
<%
    //UserDataBeansクラスのBeansインスタンスをセッションから削除
    hs.removeAttribute("Beans");
%>
