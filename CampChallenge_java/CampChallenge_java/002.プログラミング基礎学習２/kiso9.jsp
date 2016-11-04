<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%
	HashMap<String,String> hMap = new HashMap<String,String>();
	
	hMap.put("1","AAA");
	hMap.put("hello","world");
	hMap.put("soeda","33");
	hMap.put("20","20");

	for(Map.Entry<String,String>val:hMap.entrySet()){
		out.print(val.getKey());
		out.print("->");
		out.print(val.getValue());
		out.print("/");
	}
%>