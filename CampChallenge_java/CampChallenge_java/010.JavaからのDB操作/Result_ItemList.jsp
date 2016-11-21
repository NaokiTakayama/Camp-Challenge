<%-- 
    Document   : Result_ItemList
    Created on : 2016/11/21, 2:22:30
    Author     : Naoki
--%>
<%@page import="org.mypackage.Stock.Result_ItemList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%
          Result_ItemList result =(Result_ItemList)request.getAttribute("RESULT");  
        %>    
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>商品検索　結果</title>
    </head>
    <body>
        <h1>商品検索　結果</h1>
        <% 
            for(int i = 0;i < result.get_sizeitemCode();i++){
                out.print("商品コード：" + result.get_itemCode(i) + 
                          "、商品名：" + result.get_itemName(i) +
                          "、種別：" + result.get_itemKind(i) +
                          "会社名：" + result.get_company(i) +"<br>");
            }
        %>   
        <br><br><br>
        <a href="ItemList.jsp"><em>商品一覧検索画面に戻る</em></a><br><br>
        <a href="Menu_item.jsp"><em>メニュー画面に戻る</em></a><br><br><br><br>
        <a href="Logout"><em>ログアウト<em/></a>
    </body>
</html>
