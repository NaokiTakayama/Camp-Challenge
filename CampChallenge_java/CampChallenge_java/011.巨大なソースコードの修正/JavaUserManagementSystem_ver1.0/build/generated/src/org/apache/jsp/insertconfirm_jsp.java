package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import javax.servlet.http.HttpSession;
import jums.JumsHelper;
import jums.UserDataBeans;

public final class insertconfirm_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {


public boolean ifType(String s){
    boolean type = true;
    if((s.isEmpty()) || (s == null)){
        type = false;
    }        
    return type;        
}


public String emptyComment(String nameIf,boolean typeIf){
    String emptyComment = "";
    if(!typeIf)
        emptyComment = nameIf+ "が入力されていません。<br>";
    return  emptyComment;   
}

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
 
    HttpSession hs = request.getSession();
    
    UserDataBeans Beans = (UserDataBeans)hs.getAttribute("Beans");
    
    /*　このemptyTypeは、登録画面で空欄の有無の情報が格納されている。
    　emptyTypeがtrueなら空欄がなかったことになる。*/
    boolean emptyType = Boolean.getBoolean((String)hs.getAttribute("emptyType"));
    
    //セッション内の"emptyType"を削除
    hs.removeAttribute("emptyType");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>JUMS登録確認画面</title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        \n");
      out.write("    ");
      out.write("    \n");
      out.write("    ");
if(emptyType){
      out.write("\n");
      out.write("        <h1>登録確認</h1>\n");
      out.write("        名前:");
      out.print( Beans.getName());
      out.write("<br>\n");
      out.write("        生年月日:");
      out.print( Beans.getYear()+"年"+Beans.getMonth()+"月"+Beans.getDay()+"日");
      out.write("<br>\n");
      out.write("        種別:");
      out.print( Beans.getType());
      out.write("<br>\n");
      out.write("        電話番号:");
      out.print( Beans.getTell());
      out.write("<br>\n");
      out.write("        自己紹介:");
      out.print( Beans.getComment());
      out.write("<br>\n");
      out.write("        上記の内容で登録します。よろしいですか？\n");
      out.write("        <form action=\"insertresult\" method=\"POST\">\n");
      out.write("            <input type=\"hidden\" name=\"ac\"  value=\"");
      out.print(hs.getAttribute("ac"));
      out.write("\">\n");
      out.write("            <input type=\"submit\" name=\"yes\" value=\"はい\">\n");
      out.write("        </form>\n");
      out.write("    ");
}else{
      out.write("\n");
      out.write("        <h1>入力が不完全です<br><br></h1>\n");
      out.write("\n");
      out.write("        ");
      out.write("\n");
      out.write("        ");
      out.print(emptyComment("名前",ifType(Beans.getName())));
      out.write("\n");
      out.write("        ");
      out.print(emptyComment("生年月日（年）",ifType(Beans.getYear())));
      out.write("\n");
      out.write("        ");
      out.print(emptyComment("生年月日（月）",ifType(Beans.getMonth())));
      out.write("\n");
      out.write("        ");
      out.print(emptyComment("生年月日（日）",ifType(Beans.getDay())));
      out.write("\n");
      out.write("        ");
      out.print(emptyComment("種別",ifType(Beans.getType())));
      out.write("\n");
      out.write("        ");
      out.print(emptyComment("電話番号",ifType(Beans.getTell())));
      out.write("\n");
      out.write("        ");
      out.print(emptyComment("自己紹介文",ifType(Beans.getComment())));
      out.write("\n");
      out.write("\n");
      out.write("    ");
}
      out.write("\n");
      out.write("        <form action=\"insert\" method=\"POST\">\n");
      out.write("            <input type=\"submit\" name=\"no\" value=\"登録画面に戻る\">\n");
      out.write("        </form>\n");
      out.write("        <br>\n");
      out.write("        ");
      out.print(JumsHelper.getInstance().home());
      out.write("\n");
      out.write("    </body>\n");
      out.write("</html>\n");
      out.write("\n");
      out.write('\n');
      out.write('\n');
      out.write('\n');
      out.write('\n');
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
