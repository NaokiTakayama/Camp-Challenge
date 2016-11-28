<%@page import="jums.JumsHelper"
        import="jums.UserDataDTO" 
        import="javax.servlet.http.HttpSession"%>
<%
    JumsHelper jh = JumsHelper.getInstance();
    HttpSession hs = request.getSession();
    UserDataDTO udd = (UserDataDTO)hs.getAttribute("resultData");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JUMSユーザー情報詳細画面</title>
    </head>
    <body>
        <h1>詳細情報</h1>
        名前:<%= udd.getName()%><br>
        生年月日:<%= udd.getBirthday()%><br>
        種別:<%=jh.exTypenum(udd.getType())%><br>
        電話番号:<%= udd.getTell()%><br>
        自己紹介:<%= udd.getComment()%><br>
        登録日時:<%= udd.getNewDate()%><br><br><br>
        
        <%-- inputボタンを横並びにし、さらに間隔をあけて表示する --%>
        <div style="display:inline-flex">
        <form action="Update" method="POST">
            <input type="hidden" name="ac"  value="<%= hs.getAttribute("ac")%>">
            <input type="submit" name="update" value="変更"style="width:80px">
        </form>
            <span style="margin-left:20px">
                <form action="Delete" method="POST">
                <input type="hidden" name="ac"  value="<%= hs.getAttribute("ac")%>">
                <input type="submit" name="delete" value="削除"style="width:80px">
        </form>
            </span>
        </div>    
                <br><br><br>
        <form action="searchresult.jsp" method="POST">
            <input type="hidden" name="ac"  value="<%= hs.getAttribute("ac")%>">
            <input type="submit" name="no" value="検索結果画面に戻る">
        </form>
            <br><br>
        <%=jh.home()%>
    </body>
</html>
