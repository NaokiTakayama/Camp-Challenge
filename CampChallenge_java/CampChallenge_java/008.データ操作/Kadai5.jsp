<%-- 
    Document   : Kadai5
    Created on : 2016/11/14, 17:25:25
    Author     : Naoki
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="javax.servlet.http.HttpSession"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>名前、性別、趣味</title>
    </head>
    <body>
        <% 
            HttpSession rireki = request.getSession(true);
    
            String name = (String)rireki.getAttribute("lastName");
            String sex = (String)rireki.getAttribute("lastSex");
            String hobby = (String)rireki.getAttribute("lastHobby");
            
            if(sex == null)
                sex = "男";
        %>
        <form action="Kadai5" method="post">
            名前：<input type="text" name="name" value="<%=name%>"><br><br>
            性別：&nbsp男<input type="radio" name="sex" value="男" <%if(sex.equals("男"))%>checked<%;%>>&nbsp
                        女<input type="radio" name="sex" value="女" <%if(sex.equals("女"))%>checked<%;%>><br><br>
            趣味：<textarea name="hobby"><%=hobby%></textarea>
        <input type="submit" value="送信"> 
        </form>
    </body>
</html>
