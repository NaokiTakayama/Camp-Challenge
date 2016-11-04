<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
	int sougaku = Integer.parseInt(request.getParameter("sougaku"));
	int kosuu = Integer.parseInt(request.getParameter("kosuu"));
	int syubetu = Integer.parseInt(request.getParameter("syubetu"));

	//問１
	//商品種別を表示
	switch(syubetu){
		case 1:
			out.println("1.雑貨");
			break;
		case 2:
			out.println("2.生鮮食品");
			break;
		case 3:
			out.println("3.その他");
			break;
	}


	//問２
	//総額と単価を表示
	int tanka = sougaku / kosuu;
	out.println("総額＝" + sougaku + "、単価＝" + tanka);


	//問３
	//ポイントの表示
	int pointo;
	double pointo1;
	if(sougaku >= 5000){
		pointo1 = sougaku * 0.05;
	pointo = (int) pointo1;
	out.println(pointo + "ポイントです。");	
	}else if(sougaku >= 3000){
		pointo1 = sougaku * 0.04;
	pointo = (int) pointo1;
	out.println(pointo + "ポイントです。");	
	}
%>