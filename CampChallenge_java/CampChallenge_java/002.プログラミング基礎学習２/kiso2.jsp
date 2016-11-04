<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
	String a="あ";
	int b=0;
	if(a.equals("A")){
		b=1;
	}else if(a.equals("あ")){
		b=2;
	}
	switch(b){
		case 1:
			out.println("英語");
			break;
		case 2:
			out.println("日本語");
	}
%>
