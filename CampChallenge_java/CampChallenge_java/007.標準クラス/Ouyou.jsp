<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.io.IOException"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="javax.servlet.ServletException"%>
<%@page import="javax.servlet.http.HttpServlet"%>
<%@page import="javax.servlet.http.HttpServletRequest"%>
<%@page import="javax.servlet.http.HttpServletResponse"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.logging.*"%>
<%@page import="java.util.*"%>
<%@page import="java.io.File"%>
<%@page import="java.io.FileReader"%>
<%@page import="java.io.*"%>

<%
	    Logger mulBigDecimal = Logger.getLogger("Ouyou");
            
            final String filePath = "C:\\xampp\\tomcat\\webapps\\camp\\fileOuyou\\mulBigDscimal.txt";
            
            FileHandler fileHandler = new FileHandler(filePath, false);
            fileHandler.setFormatter(new SimpleFormatter());
            mulBigDecimal.addHandler(fileHandler);
            mulBigDecimal.setLevel(Level.FINE);
 
            ConsoleHandler consoleHandler = new ConsoleHandler();
            consoleHandler.setLevel(Level.CONFIG);      
            mulBigDecimal.addHandler(consoleHandler);
 
            mulBigDecimal.setUseParentHandlers(false);

            
            Date openDate = new Date();
            String openDateString = String.valueOf(openDate);                        
            mulBigDecimal.info("開始　" + openDateString);
            
            
            
            String format = "0.000000000000000";
            DecimalFormat dFormat = new DecimalFormat(format);
            
            int a1 ,b1;
            Double a2 ,b2;
            a1 = new java.util.Random().nextInt(100);
            b1 = new java.util.Random().nextInt(100);
            
            a2 = (double)a1/1000;
            b2 = (double)b1/1000;
            
            String a3 = String.valueOf(a2);
            String b3 = String.valueOf(b2);
            
            BigDecimal a4 = new BigDecimal(a3);
            BigDecimal b4 = new BigDecimal(b3);
            
            BigDecimal mul = a4.multiply(b4);
            
            String bigMul = dFormat.format(mul);
            
            out.println(a1 + "　" + a2 + "　" + a3 + "<br>");
            out.println(b1 + "　" + b2 + "　" + b3 + "<br>");
            out.println(mul + "<br>");
            out.println(bigMul + "<br>");
            
            
            
            Date closeDate = new Date();            
            String closeDateString = String.valueOf(closeDate);                        
            mulBigDecimal.info("終了　" + closeDateString);
            
            
            File txt = new File(filePath);
            FileReader ftxt = new FileReader(txt);
            BufferedReader btxt = new BufferedReader(ftxt);
            
            String stxt;
            while((stxt = btxt.readLine()) != null){
                out.println(stxt + "<br>");
            }
            
            btxt.close();
%>