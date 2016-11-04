<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
	String motos = request.getParameter("suuti");
	int moto = Integer.parseInt(motos);

	int moto7 = 0;
	int moto5 = 0;
	int moto3 = 0;
	int moto2 = 0;

	if((moto == 0) || (moto == 1)){
		out.println("その他");
        }else{
		while(moto % 2 == 0){
			moto = moto / 2;
			moto2 = moto2 + 1;
		}
		while(moto % 3 == 0){
			moto = moto / 3;
			moto3 = moto3 + 1;
		}	
		while(moto % 5 == 0){
			moto = moto / 5;
			moto5 = moto5 + 1;
		}	
		while(moto % 7 == 0){
			moto = moto / 7;
			moto7  =moto7 + 1;
		}	
		if(moto > 10){
			out.println("元の値1ケタの素因数その他");
		}else{
			out.println(motos + "=" + "(2^" + moto2 +")(3^" + moto3 +")(5^" + moto5 + ")(7^" + moto7 +")");
		}
	}
%>
