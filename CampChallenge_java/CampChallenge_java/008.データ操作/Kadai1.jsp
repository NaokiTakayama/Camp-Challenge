<%-- 
    Document   : Kadai1
    Created on : 2016/11/14, 14:22:03
    Author     : Naoki
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>名前、性別　趣味</title>
    </head>
    <body>
        <form action="Kadai1" method="post">
          名前：<input type="text" name="name"><br><br>
          性別：<nbsp>男<input type="radio" name="sex" value="男" checked><nbsp>
                  女<input type="radio" name="sex" value="女"><br><br>
          趣味：<textarea name="hobby"></textarea>
          <input type="submit" value="送信"> 
        </form>
    </body>
</html>
