<%@page import="javax.servlet.http.HttpSession" %>
<%@page import="jums.JumsHelper" %>
<%@page import="jums.UserDataBeans" %>
<%
    HttpSession hs = request.getSession(true);
    
    /*　まだBeansインスタンスが定義されていない可能性のあるため、
      以下のような初期化が必要となる。*/
    
    String name = null;
    String year= null;
    String month= null;
    String day= null;
    String type= null;
    String tell= null;
    String comment= null;
    
    UserDataBeans Beans = (UserDataBeans)hs.getAttribute("Beans");
    if(Beans != null){
        name = Beans.getName();
        year = Beans.getYear();
        month = Beans.getMonth();
        day = Beans.getDay();
        type = Beans.getType();
        tell = Beans.getTell();
        comment = Beans.getComment();
    }
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JUMS登録画面</title>
    </head>
    <body>
    <form action="insertconfirm" method="POST">
        名前:
        <input type="text" name="name" value="<%=nullCheck(name)%>">
        <br><br>

        生年月日:　
        <select name="year">
            <option value="">----</option>
            <%
            for(int i=1950; i<=2010; i++){ %>
            <option value="<%=i%>" <%if(intCheck(year) == i)%>selected="selected"<%;%>><%=i%></option>
            <% } %>
        </select>年
        <select name="month">
            <option value="">--</option>
            <%
            for(int i = 1; i<=12; i++){ %>
            <option value="<%=i%>" <%if(intCheck(month) == i)%>selected="selected"<%;%>><%=i%></option>
            <% } %>
        </select>月
        <select name="day">
            <option value="">--</option>
            <%
            for(int i = 1; i<=31; i++){ %>
            <option value="<%=i%>" <%if(intCheck(day) == i)%>selected="selected"<%;%>><%=i%></option>
            <% } %>
        </select>日
        <br><br>

        種別:
        <br>
        <input type="radio" name="type" value="1"　<%if(typeValue(type).equals("1"))%>checked<%;%>>エンジニア<br>
        <input type="radio" name="type" value="2"  <%if(typeValue(type).equals("2"))%>checked<%;%>>営業<br>
        <input type="radio" name="type" value="3"  <%if(typeValue(type).equals("3"))%>checked<%;%>>その他<br>
        <br>

        電話番号:
        <input type="text" name="tell" value="<%=nullCheck(tell)%>">
        <br><br>

        自己紹介文
        <br>
        <textarea name="comment" rows=10 cols=50 style="resize:none" wrap="hard"><%=nullCheck(comment)%></textarea><br><br>
        
        <input type="hidden" name="ac"  value="<%=hs.getAttribute("ac")%>">
        <input type="submit" name="btnSubmit" value="確認画面へ">
    </form>
        <br>
        <%=JumsHelper.getInstance().home()%>
    </body>
</html>

<%--種別の初期値の場合分けのためのメソッド--%>
<%!
public String typeValue(String type){
    try{
        String Value = "1";
        if(type.equals("2")){
            Value = "2";
        }else if(type.equals("3")){
            Value = "3";
        }
        return Value;
    }catch (NullPointerException e){
        return "1";
    }
}
%>

<%--日付の初期値の場合分けのためのメソッド--%>
<%!
    public int intCheck(String day){
        try{
            if((day.equals("")) || (day == null)){
                day = "-1";
            }
            int day_int = Integer.parseInt(day);
            return day_int;
        }catch (NullPointerException e){
            return -1;
        } 
    }
%>

<%--String型変数がnullか否かを判定し、nullの場合は空欄を返すメソッド--%>
<%!
    public String nullCheck(String str){
        try{
            String return_str = str;
            if(str == null)
                return_str = "";
            return    return_str; 
        }catch (NullPointerException e){
            return "";
        }
}
%>

