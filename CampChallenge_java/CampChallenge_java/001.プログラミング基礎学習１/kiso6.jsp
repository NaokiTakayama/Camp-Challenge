<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
	int a = 3;
	if(a == 1){
		out.print("１です！");
	}else if(a == 2){
		out.print("プログラミングキャンプ！");
	}else{
		out.print("その他です！");
	}
%>