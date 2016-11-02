<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
	int a=20;
	final int b=10;
	int tasu = a+b;
	int hiku = a-b;
	int kake = a*b;
	int waru = a/b;
	int amari = a%b;
	out.println("足すと"+tasu+"、");
	out.println("引くと"+hiku+"、");
	out.println("掛けると"+kake+"、");
	out.println("割ると"+waru+"、");
	out.println("余りは"+amari);	
%>