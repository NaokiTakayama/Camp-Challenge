<%-- 
    Document   : Kadai11
    Created on : 2016/11/17, 16:42:17
    Author     : Naoki
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>profilesID</title>
    </head>
    <body>
        <form action="Kadai11" method="post">
            <ul>    
                <li>ID &nbsp;（半角）：<input type="text" name="id"><br><br><br><br></li>  
              
                <li>名前 &nbsp;（全角）　姓：<input type="text" name="sei" >
                                         名：<input type="text" name="na" ><br><br></li>
                <li>電話番号 &nbsp;（半角）：<input type="text" name="tell" placeholder="000-000-0000"><br><br></li>
                <li>年齢 &nbsp;（半角）：<input type="text" name="age"><br><br></li>
                <li>誕生日 &nbsp;（半角）：<input type="text" name="birthday" placeholder="1970-01-01"></li>
          <input type="submit" value="送信">
            </ul> 
        </form>
    </body>
</html>
