/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.0.20
 * Generated at: 2021-06-16 17:53:14 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class userInsert_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = null;
  }

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

final java.lang.String _jspx_method = request.getMethod();
if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method) && !javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET POST or HEAD");
return;
}

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\r\n");
      out.write("<html xmlns=\"http://www.w3.org/1999/xhtml\">\r\n");
      out.write("<head>\r\n");
      out.write("<!-- メタ -->\r\n");
      out.write("<meta charset=\"UTF-8\"/>\r\n");
      out.write("<meta name=\"viewport\" content=\"width=device-width, user-scalable=yes, maximum-scale=1.0, minimum-scale=1.0\"/>\r\n");
      out.write("<meta name=\"description\" content=\"■Restaurante IDEALLE■\"/>\r\n");
      out.write("<meta name=\"keywords\" content=\"\"/>\r\n");
      out.write("<!-- メタ終わり -->\r\n");
      out.write("<title>お客様情報登録</title>\r\n");
      out.write("<!-- リンク -->\r\n");
      out.write("<link rel=\"stylesheet\" href=\"css/style.css\" type=\"text/css\" media=\"screen\"/>\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" media=\"all\" href=\"css/tamurauser.css\"/>\r\n");
      out.write("<!-- リンク終わり -->\r\n");
      out.write("\r\n");
      out.write("<!-- スクリプト・JS -->\r\n");
      out.write("<!--[if lt IE 9]>\r\n");
      out.write("<script src=\"js/html5.js\"></script>\r\n");
      out.write("<script src=\"js/css3-mediaqueries.js\"></script>\r\n");
      out.write("<![endif]-->\r\n");
      out.write("<script src=\"js/jquery1.4.4.min.js\"></script>\r\n");
      out.write("<script src=\"js/script.js\"></script>\r\n");
      out.write("<!-- スクリプト・JS終わり -->\r\n");
      out.write("<script src=\"https://yubinbango.github.io/yubinbango/yubinbango.js\" charset=\"UTF-8\"></script>\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("<!--\r\n");
      out.write("\tfunction check(){\r\n");
      out.write("\t\tif(document.cusfrm.usrName.value==\"\"){\r\n");
      out.write("\t\t\twindow.alert(\"お名前が未入力です。\");\r\n");
      out.write("\t\t\treturn false;\r\n");
      out.write("\t\t}else if(document.cusfrm.gender.value==\"\"){\r\n");
      out.write("\t\t\twindow.alert(\"性別が未入力です。\");\r\n");
      out.write("\t\t\treturn false;\r\n");
      out.write("\t\t}else if(document.cusfrm.phone.value==\"\"){\r\n");
      out.write("\t\t\twindow.alert(\"電話番号が未入力です。\");\r\n");
      out.write("\t\t\treturn false;\r\n");
      out.write("\t\t}else if(document.cusfrm.mail.value==\"\"){\r\n");
      out.write("\t\t\t\twindow.alert(\"メールアドレスが未入力です。\");\r\n");
      out.write("\t\t\t\treturn false;\r\n");
      out.write("\t\t}else if(document.cusfrm.password.value==\"\"){\r\n");
      out.write("\t\t\twindow.alert(\"パスワードが未入力です。\");\r\n");
      out.write("\t\t\treturn false;\r\n");
      out.write("\t\t}else if(!document.cusfrm.usrName.value.match(/^[ぁ-んァ-ン一-龥]*$/)){\r\n");
      out.write("\t\t\twindow.alert(\"お名前は英数字記号以外を入力ください。\");\r\n");
      out.write("\t\t\treturn false;\r\n");
      out.write("\t\t}else if(!document.cusfrm.password.value.match(/^[a-zA-Z0-9]*$/)){\r\n");
      out.write("\t\t\twindow.alert(\"パスワードは半角英数字を入力ください。\");\r\n");
      out.write("\t\t\treturn false;\r\n");
      out.write("\t\t}else if(!document.cusfrm.phone.value.match(/^[0-9-]*$/)){\r\n");
      out.write("\t\t\twindow.alert(\"電話番号は半角数字を入力ください。\");\r\n");
      out.write("\t\t\treturn false;\r\n");
      out.write("\t\t}else if(!document.cusfrm.mail.value.match(/^[A-Za-z0-9]{1}[A-Za-z0-9_.-]*@{1}[A-Za-z0-9_.-]{1,}\\.[A-Za-z0-9]{1,}$/)){\r\n");
      out.write("\t\t\twindow.alert(\"半角を用い、正しいメールアドレスを入力ください。\");\r\n");
      out.write("\t\t\treturn false;\r\n");
      out.write("\t\t}else{\r\n");
      out.write("\t\t\tif(window.confirm(\"これでよろしいですか？\")){\r\n");
      out.write("\t\t\t\treturn true;\r\n");
      out.write("\t\t\t}else{\r\n");
      out.write("\t\t\t\treturn false;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t}\r\n");
      out.write("\t}\r\n");
      out.write("\r\n");
      out.write("//-->\r\n");
      out.write("</script>\r\n");
      out.write("<noscript>\r\n");
      out.write("\t<meta http-equiv=\"refresh\" content=\"0;URL=noscript.jsp\"/>\r\n");
      out.write("</noscript>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      model.User userInfo = null;
      synchronized (session) {
        userInfo = (model.User) _jspx_page_context.getAttribute("userInfo", javax.servlet.jsp.PageContext.SESSION_SCOPE);
        if (userInfo == null){
          userInfo = new model.User();
          _jspx_page_context.setAttribute("userInfo", userInfo, javax.servlet.jsp.PageContext.SESSION_SCOPE);
        }
      }
      out.write('\r');
      out.write('\n');

String userName=userInfo.getUsr_name();
System.out.println(userName);
if(userName==null){
      out.write("\r\n");
      out.write("<!-- メニューバー -->\r\n");
      out.write("<div id=\"header\">\r\n");
      out.write("\t<div class=\"inner\">\r\n");
      out.write("\t\t<!-- ロゴ -->\r\n");
      out.write("\t\t<div class=\"logo\">\r\n");
      out.write("\t\t\t<a href=\"home.jsp\">■Restaurante IDEALLE■<br><span>Italian Restaurante</span></a>\r\n");
      out.write("      <h1>welcome to Ideal</h1>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<!-- / ロゴ -->\r\n");
      out.write("\r\n");
      out.write("\t  <!-- メインナビゲーション -->\r\n");
      out.write("    <nav id=\"mainNav\">\r\n");
      out.write("  \t\t<a class=\"menu\" id=\"menu\"><span>Top Menu</span></a>\r\n");
      out.write("\t\t\t<div class=\"panel\">\r\n");
      out.write("\t\t\t\t<ul>\r\n");
      out.write("\t\t\t\t\t<li><a href=\"home.jsp\">トップページ<br><span>Top</span></a></li>\r\n");
      out.write("\t\t\t\t\t<li><a href=\"ShowMenuSvl\">メニュー紹介<br><span>Menu</span></a></li>\r\n");
      out.write("\t\t\t\t\t<li><a href=\"ReservationStatusSvl\">予約状況<br><span>Reserve</span></a></li>\r\n");
      out.write("\t\t\t\t\t<li><a href=\"newsDisplay.jsp\">お知らせ<br><span>News</span></a></li>\r\n");
      out.write("\t\t\t\t\t<li><a href=\"UserSvl\">ログイン<br><span>Login</span></a></li>\r\n");
      out.write("\t\t\t\t</ul>\r\n");
      out.write("      </div>\r\n");
      out.write("    </nav>\r\n");
      out.write("\t\t<!-- メインナビゲーション -->\r\n");
      out.write("\r\n");
      out.write("  </div>\r\n");
      out.write("</div>\r\n");
 }else{
      out.write("\r\n");
      out.write("<div id=\"header\">\r\n");
      out.write("\t<div class=\"inner\">\r\n");
      out.write("\t\t<!-- ロゴ -->\r\n");
      out.write("\t\t<div class=\"logo\">\r\n");
      out.write("\t\t\t<a href=\"home.jsp\">■Restaurante IDEALLE■<br><span>Italian Restaurante</span></a>\r\n");
      out.write("      <h1>welcome to Ideal</h1>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<!-- / ロゴ -->\r\n");
      out.write("\r\n");
      out.write("\t  <!-- メインナビゲーション -->\r\n");
      out.write("    <nav id=\"mainNav\">\r\n");
      out.write("  \t\t<a class=\"menu\" id=\"menu\"><span>Top Menu</span></a>\r\n");
      out.write("\t\t\t<div class=\"panel\">\r\n");
      out.write("\t\t\t\t<ul>\r\n");
      out.write("\t\t\t\t\t<li><a href=\"home.jsp\">トップページ<br><span>Top</span></a></li>\r\n");
      out.write("\t\t\t\t\t<li><a href=\"ShowMenuSvl\">メニュー紹介<br><span>Menu</span></a></li>\r\n");
      out.write("\t\t\t\t\t<li><a href=\"ReservationStatusSvl\">予約状況<br><span>Reserve</span></a></li>\r\n");
      out.write("\t\t\t\t\t<li><a href=\"newsDisplay.jsp\">お知らせ<br><span>News</span></a></li>\r\n");
      out.write("\t\t\t\t\t<li><a href=\"UserSvl\">");
      out.write(org.apache.jasper.runtime.JspRuntimeLibrary.toString((((model.User)_jspx_page_context.findAttribute("userInfo")).getUsr_name())));
      out.write("様マイページ<br><span>MyPage</span></a></li>\r\n");
      out.write("\t\t\t\t\t<li><a href=\"UserLogoffSvl\">ログアウト<br><span>Logout</span></a></li>\r\n");
      out.write("\t\t\t\t</ul>\r\n");
      out.write("      </div>\r\n");
      out.write("    </nav>\r\n");
      out.write("\t\t<!-- メインナビゲーション -->\r\n");
      out.write("\r\n");
      out.write("  </div>\r\n");
      out.write("</div>\r\n");
} 
      out.write("\r\n");
      out.write("<!-- / メニューバー終わり（ホーム） -->\r\n");
      model.IdealException msg = null;
      msg = (model.IdealException) _jspx_page_context.getAttribute("msg", javax.servlet.jsp.PageContext.REQUEST_SCOPE);
      if (msg == null){
        msg = new model.IdealException();
        _jspx_page_context.setAttribute("msg", msg, javax.servlet.jsp.PageContext.REQUEST_SCOPE);
      }
      out.write("\r\n");
      out.write("\t\t<!-- コンテンツ -->\r\n");
      out.write("<div id=\"wrapper\" align=\"center\">\r\n");
      out.write("<article class=\"post\">\r\n");
      out.write("<section class=\"content\">\r\n");
      out.write("\t\t<h1>お客様情報登録</h1>\r\n");
      out.write("\t\t<div>\r\n");
      out.write("\t\t\t当店をご利用いただき、誠にありがとうございます。\r\n");
      out.write("\t\t\t<br/>\r\n");
      out.write("\t\t\t下記の項目にご記入をお願いいたします。\r\n");
      out.write("\t\t\t<br/>\r\n");
      out.write("\t\t\tなお、お名前とe-mailとパスワードは必ず入力してください。\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t ");
 if(msg.getErrmsg()!=null){
      out.write("\r\n");
      out.write("\t\t\t<p><font color=\"red\">メールアドレスが使用されておりますので別のメールアドレスでご登録ください。</font></p>\r\n");
      out.write("\t\t");
 } 
      out.write("\r\n");
      out.write("\t\t<!-- メッセージ終了 --> <!-- フォーム開始 -->\r\n");
      out.write("\t\t<div class=\"h-adr\">\r\n");
      out.write("\t\t\t<form id=\"cusfrm\" name=\"cusfrm\" action=\"UserOperationSvl\" method=\"post\"\r\n");
      out.write("\t\t\t\tonsubmit=\"return check();\">\r\n");
      out.write("\t\t\t\t<span class=\"p-country-name\" style=\"display:none;\">Japan</span>\r\n");
      out.write("\t\t\t\t<table>\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<td id=\"index\">お名前※</td>\r\n");
      out.write("\t\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t\t\t<input type=\"text\" name=\"usrName\" />\r\n");
      out.write("\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<td id=\"index\">性別※</td>\r\n");
      out.write("\t\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t\t\t<input type=\"radio\" name=\"gender\" value=\"1\" />男性\r\n");
      out.write("\t\t\t\t\t\t\t<input type=\"radio\" name=\"gender\" value=\"2\" />女性\r\n");
      out.write("\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<td id=\"index\">生年月日</td>\r\n");
      out.write("\t\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t\t\t<input type=\"text\" name=\"birthday\" />\r\n");
      out.write("\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<td id=\"index\">郵便番号</td>\r\n");
      out.write("\t\t\t\t\t\t<td >\r\n");
      out.write("\t\t\t\t\t\t\t<input type=\"text\" class=\"p-postal-code\" name=\"postcode\" />\r\n");
      out.write("\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<td id=\"index\">住所</td>\r\n");
      out.write("\t\t\t\t\t\t<td >\r\n");
      out.write("\t\t\t\t\t\t\t<input type=\"text\" class=\"p-region p-locality p-street-address p-extended-address\" name=\"address\" />\r\n");
      out.write("\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<td id=\"index\">電話番号※</td>\r\n");
      out.write("\t\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t\t\t<input type=\"text\" name=\"phone\" />\r\n");
      out.write("\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<td id=\"index\">e-mail※</td>\r\n");
      out.write("\t\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t\t\t<input type=\"text\" name=\"mail\" />\r\n");
      out.write("\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<td id=\"index\">パスワード※</td>\r\n");
      out.write("\t\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t\t\t<input type=\"password\" name=\"password\" />\r\n");
      out.write("\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<td colspan=\"2\" class=\"submit\">\r\n");
      out.write("\t\t\t\t\t\t\t※は必修入力です。<input type=\"submit\" value=\"登録\" />\r\n");
      out.write("\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t</table>\r\n");
      out.write("\t\t\t\t<input type=\"hidden\" name=\"mode\" value=\"11\"/>\r\n");
      out.write("\t\t\t</form>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div><a href=\"./home.jsp\">ホーム</a></div>\r\n");
      out.write("</section>\r\n");
      out.write("</article>\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("\t\t<!-- フォーム終了 -->\r\n");
      out.write("\t\t<!-- / コンテンツ -->\r\n");
      out.write("<!-- フッター -->\r\n");
      out.write("<footer id=\"footer\">\r\n");
      out.write("\t<div class=\"inner\">\r\n");
      out.write("\r\n");
      out.write("  \t<!-- 3カラム -->\r\n");
      out.write("    <section class=\"gridWrapper\">\r\n");
      out.write("\r\n");
      out.write("\t\t\t<article class=\"grid\">\r\n");
      out.write("\t  \t\t<!-- ロゴ -->\r\n");
      out.write("\t\t\t\t<p class=\"logo\">\r\n");
      out.write("  \t\t\t\t<a href=\"home.jsp\">■Restaurante IDEALLE■<br><span>Italian Restaurante</span></a>\r\n");
      out.write("\t\t\t\t</p>\r\n");
      out.write("        <!-- / ロゴ -->\r\n");
      out.write("     \t</article>\r\n");
      out.write("\r\n");
      out.write("      <!-- 電話番号+受付時間 -->\r\n");
      out.write("    \t<article class=\"grid\">\r\n");
      out.write("    \t\t<p class=\"tel\"><span>電話:</span> <strong>011-206-1663</strong></p>\r\n");
      out.write("    \t\t<p class=\"open\">営業時間: 17：00～24：00</p>\r\n");
      out.write("    \t\t<p class=\"open\">定休日: 水曜日</p>\r\n");
      out.write("\t\t\t<!-- / 電話番号+受付時間 -->\r\n");
      out.write("      </article>\r\n");
      out.write("\r\n");
      out.write("    \t<article class=\"grid copyright\">\r\n");
      out.write("      \tCopyright(c) 2016 ホームページサンプル株式会社 All Rights Reserved. Design by <a href=\"http://f-tpl.com\" target=\"_blank\" rel=\"nofollow\">http://f-tpl.com</a><br/>\r\n");
      out.write("      \t<a href=\"AdminSvl\">管理者用</a>\r\n");
      out.write("      </article>\r\n");
      out.write("\r\n");
      out.write("    </section>\r\n");
      out.write("\t\t<!-- / 3カラム -->\r\n");
      out.write("\r\n");
      out.write("\t</div>\r\n");
      out.write("</footer>\r\n");
      out.write("<!-- / フッター 終わり-->\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
