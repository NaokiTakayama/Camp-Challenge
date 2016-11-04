<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
	String[] Arr = {"10","100","soeda","hayashi","-20","118","END"};
	for(int i=0;i<Arr.length;i++){
		out.println(Arr[i]);
	}
	Arr[2]="33";
	out.println(Arr[2]);	
%>
