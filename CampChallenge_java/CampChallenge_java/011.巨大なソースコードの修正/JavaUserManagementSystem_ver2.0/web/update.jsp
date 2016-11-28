<%@page import="java.util.ArrayList"
        import="jums.UserDataDTO"
        import="jums.JumsHelper" 
        import="javax.servlet.http.HttpSession"
        import="java.util.Calendar"
        import="java.util.Date"%>
<%
    JumsHelper jh = JumsHelper.getInstance();
    HttpSession hs = request.getSession();
    UserDataDTO udd = (UserDataDTO)hs.getAttribute("resultData");
    
    //生年月日をDate型からbirthday型に変換
    Calendar birthday_c = jh.calendar_chg(udd.getBirthday());
    
    //入力欄に空欄があるかチェック
    boolean empty_chk = false;
    if((request.getAttribute("mode1") != null)){
        empty_chk = true;
    }
    
    //入力内容が全く同じかチェック
    boolean equal_chk = false;
    if((request.getAttribute("mode2") != null)){
        equal_chk = true;
    }
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JUMS変更画面</title>
    </head>
    <body> 
        <h1>変更画面</h1>
    <%--入力内容に空欄があるかで場合分け--%>
    <%if(empty_chk){%>
            <font color="#ff0000"><strong>入力欄に空欄があります。</strong></font>
            <br><br>
    <%;}%>    
    
    <%--入力内容が全く同じかで場合分け--%>
    <%if(equal_chk){%>
            <font color="#ff0000"><strong>入力内容に変化がありません。</strong></font>
            <br><br>
    <%;}%>
    
    <form action="UpdateResult" method="POST">
        名前:
        <input type="text" name="name" value="<%= udd.getName()%>">
        <br><br>

        生年月日:　
        <select name="year">
            <option value="">----</option>
            <% for(int i=1950; i<=2010; i++){ %>
            <option value="<%=i%>" <%if(birthday_c.get(Calendar.YEAR) == i)%>selected="selected"<%;%>><%=i%></option>
            <% } %>
        </select>年
        <select name="month">
            <option value="">--</option>
            <% for(int i = 1; i<=12; i++){ %>
            <option value="<%=i%>" <%if(birthday_c.get(Calendar.MONTH)+1 == i)%>selected="selected"<%;%>><%=i%></option>
            <% } %>
        </select>月
        <select name="day">
            <option value="">--</option>
            <% for(int i = 1; i<=31; i++){ %>
            <option value="<%=i%>" <%if(birthday_c.get(Calendar.DATE) == i)%>selected="selected"<%;%>><%=i%></option>
            <% } %>
        </select>日
        <br><br>

        種別:
        <br>
            <% for(int i = 1; i<=3; i++){ %>
            <input type="radio" name="type" value="<%=i%>" <%if(udd.getType() == i)%>checked<%;%>>
                <%=jh.exTypenum(i)%><br>
            <% } %>
        <br>

        電話番号:
        <input type="text" name="tell" value="<%= udd.getTell()%>">
        <br><br>

        自己紹介文
        <br>
        <textarea name="comment" rows=10 cols=50 style="resize:none" wrap="hard"><%= udd.getComment()%></textarea><br><br>
        
        <input type="hidden" name="ac"  value="<%= hs.getAttribute("ac")%>">
        <input type="submit" name="btnSubmit" value="変更内容を適用">
    </form>
        <br>
    <form action="resultdetail.jsp" method="POST">
        <input type="hidden" name="ac"  value="<%= hs.getAttribute("ac")%>">
        <input type="submit" name="no" value="詳細情報画面に戻る">
    </form>
        <br><br>
        <%=jh.home()%>
    </body>
</html>
