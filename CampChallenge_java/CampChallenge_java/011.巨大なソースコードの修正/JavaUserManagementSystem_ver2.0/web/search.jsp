<%@page import="jums.UserDataDTO"
        import="java.util.ArrayList"
        import="jums.UserDataBeans"
        import="jums.JumsHelper" 
        import="javax.servlet.http.HttpSession"%>
<%
    JumsHelper jh = JumsHelper.getInstance();
    HttpSession hs = request.getSession();
    
    /*
    * 　検索結果画面から戻ったとき、もしくはSearchResult.javaから
    *　送り返されたときのみ、初期値を表示
    */
    UserDataBeans udb = null;
    ArrayList<UserDataDTO> resultData = null;
    boolean reinput = false;
    if((request.getParameter("mode") != null && request.getParameter("mode").equals("REINPUT"))
            || ((request.getAttribute("mode")) != null)){
        reinput = true;
        udb = (UserDataBeans)hs.getAttribute("searchForm");
        resultData = (ArrayList<UserDataDTO>)hs.getAttribute("resultDataArray");
    }
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JUMSユーザー情報検索画面</title>
    </head>
    <body>
        <h1>検索画面</h1>
        
        <%-- 検索結果が存在するかで場合分け --%>    
        <%if(reinput && JumsHelper.getInstance().array_DTO(resultData)){%>
            <font color="#ff0000"><strong>検索結果が存在しません。</strong></font>
            <br><br>
        <%;}%>
        
        <%-- 検索欄が空欄かで場合分け --%>
        <%if(reinput && (udb.chkproperties().size() == 7)){%>
            <font color="#ff0000"><strong>検索したい内容を記入してください</strong></font>
            <br><br>
        <%;}%>
        
         <form action="SearchResult" method="POST">
        名前:
        <input type="text" name="name" value=<% if(reinput){out.print(udb.getName());}%>>
        <br><br>

        生年:　
        <select name="year">
            <option value="">----</option>
            <% for(int i=1950; i<=2010; i++){ %>
            <option value="<%=i%>" <%if(reinput && udb.getYear() == i)%>selected="selected"<%;%>><%=i%></option>
            <% } %>
        </select>年生まれ
        <br><br>

        種別:
        <br>
            <% for(int i = 1; i<=3; i++){ %>
            <input type="radio" name="type" value="<%=i%>" <%if(reinput && udb.getType() == i)%>checked<%;%>><%=jh.exTypenum(i)%><br>
            <% } %>
        <br>
        <input type="hidden" name="ac"  value="<%= hs.getAttribute("ac")%>">
        <input type="submit" name="btnSubmit" value="検索">
    </form>
        <br><br>
        <%=jh.home()%>
    </body>
</html>
