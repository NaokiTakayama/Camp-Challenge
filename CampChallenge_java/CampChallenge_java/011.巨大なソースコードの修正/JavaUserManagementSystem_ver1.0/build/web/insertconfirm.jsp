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
        <title>JUMS登録確認画面</title>
    </head>
    <body>
        
    <%--空欄の有無（ifTypeで確認）で分岐--%>    
    <%if(ifType(Beans.getName()) && ifType(Beans.getYear()) && ifType(Beans.getMonth()) && ifType(Beans.getDay())
            && ifType(Beans.getType()) && ifType(Beans.getTell()) && ifType(Beans.getComment())){%>
        <h1>登録確認</h1>
        名前:<%= Beans.getName()%><br>
        生年月日:<%= Beans.getYear()+"年"+Beans.getMonth()+"月"+Beans.getDay()+"日"%><br>
        種別:<%= Beans.getType()%><br>
        電話番号:<%= Beans.getTell()%><br>
        自己紹介:<%= Beans.getComment()%><br><br>
        上記の内容で登録します。よろしいですか？
        <form action="insertresult" method="POST">
            <input type="hidden" name="ac"  value="<%=hs.getAttribute("ac")%>">
            <input type="submit" name="yes" value="はい">
        </form>
    <%}else{%>
        <h1>入力が不完全です<br><br></h1>

        <%--空欄のある場合は、入力されていないと表示 --%>
        <%=emptyComment("名前",ifType(Beans.getName()))%>
        <%=emptyComment("生年月日（年）",ifType(Beans.getYear()))%>
        <%=emptyComment("生年月日（月）",ifType(Beans.getMonth()))%>
        <%=emptyComment("生年月日（日）",ifType(Beans.getDay()))%>
        <%=emptyComment("種別",ifType(Beans.getType()))%>
        <%=emptyComment("電話番号",ifType(Beans.getTell()))%>
        <%=emptyComment("自己紹介文",ifType(Beans.getComment()))%>

    <%}%>
    <br><br>
        <form action="insert" method="POST">
            <input type="submit" name="no" value="登録画面に戻る">
        </form>
        
        <br>
        <%=JumsHelper.getInstance().home()%>
    </body>
</html>

<%--空欄かどうかを判定するメソッド--%>
<%!
public boolean ifType(String s){
    boolean type = true;
    if((s.isEmpty()) || (s == null)){
        type = false;
    }        
    return type;        
}
%>

<%--空欄の場合、入力されていないと表示するメソッド--%>
<%!
public String emptyComment(String nameIf,boolean typeIf){
    String emptyComment = "";
    if(!typeIf)
        emptyComment = nameIf+ "が入力されていません。<br>";
    return  emptyComment;   
}
%>