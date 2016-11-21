<%-- 
    Document   : ItemList
    Created on : 2016/11/18, 14:17:14
    Author     : Naoki
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>商品一覧検索</title>
    </head>
    <body>
        <h1>商品一覧検索</h1>
        <form action="ItemList" method="post">
            <ul>    
                <li>商品コード &nbsp;（半角）：<input type="text" name="itemCode" ><br><br></li>
                <li>商品名 &nbsp;（全角）：<input type="text" name="itemName"><br><br></li>
                <li>種別 &nbsp;（全角）：<input type="text" name="itemKind"><br><br></li>
                <li>販売社名 &nbsp;（全角）：<input type="text" name="company"><br><br></li>
          <input type="submit" value="検索">
            </ul>
        </form>
        <a href="Menu_item.jsp"><em>メニュー画面に戻る</em></a><br><br>
        <a href="Logout"><em>ログアウト<em/></a>
    </body>
</html>
