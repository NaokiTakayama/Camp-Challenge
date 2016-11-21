<%-- 
    Document   : Login
    Created on : 2016/11/18, 13:51:18
    Author     : Naoki
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ログイン画面</title>
    </head>
    <body>
        <h1>ログイン画面</h1>
        <form action="Login" method="post">
            <ul>    
                <li>ユーザID &nbsp;（半角）：<input type="text" name="userID" ><br><br></li>
                <li>パスワード &nbsp;（半角）：<input type="password" name="password"><br><br></li>
          <input type="submit" value="ログイン">
            </ul>
        </form>
    </body>
</html>
