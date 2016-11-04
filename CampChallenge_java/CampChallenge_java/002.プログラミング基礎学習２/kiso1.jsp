<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
	int a=1;
	switch(a){
		case 1:
			out.println("one");
			break;
		case 2:
			out.println("two");
			break;
		default:
			out.println("想定外");
	}
%>