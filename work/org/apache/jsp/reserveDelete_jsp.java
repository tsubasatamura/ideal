/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.0.20
 * Generated at: 2021-06-16 14:45:16 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import model.*;
import controller.ReserveOperationSvl;

public final class reserveDelete_jsp extends org.apache.jasper.runtime.HttpJspBase
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
    _jspx_imports_packages.add("model");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = new java.util.HashSet<>();
    _jspx_imports_classes.add("controller.ReserveOperationSvl");
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
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\r\n");
      out.write("<html xmlns=\"http://www.w3.org/1999/xhtml\">\r\n");
      out.write("<head>\r\n");
      out.write("\r\n");
      out.write("<!-- メタ -->\r\n");
      out.write("<meta charset=\"UTF-8\">\r\n");
      out.write("<meta name=\"viewport\" content=\"width=device-width, user-scalable=yes, maximum-scale=1.0, minimum-scale=1.0\">\r\n");
      out.write("<meta name=\"description\" content=\"■Restaurante IDEALLE■\">\r\n");
      out.write("<meta name=\"keywords\" content=\"\">\r\n");
      out.write("<!-- メタ終わり -->\r\n");
      out.write("\r\n");
      out.write("<title>ユーザー予約取消</title>\r\n");
      out.write("\r\n");
      out.write("<!-- リンク -->\r\n");
      out.write("<link rel=\"stylesheet\" href=\"css/style.css\" type=\"text/css\" media=\"screen\"/>\r\n");
      out.write("<!-- リンク終わり -->\r\n");
      out.write("\r\n");
      out.write("<!-- スクリプト・JS -->\r\n");
      out.write("<!--[if lt IE 9]>\r\n");
      out.write("<script src=\"js/html5.js\"></script>\r\n");
      out.write("<script src=\"js/css3-mediaqueries.js\"></script>\r\n");
      out.write("<![endif]-->\r\n");
      out.write("<script src=\"js/jquery1.4.4.min.js\"></script>\r\n");
      out.write("<script src=\"js/script.js\"></script>\r\n");
      out.write("<!-- スクリプト・JS -->\r\n");
      out.write("\r\n");
      out.write("<!-- スタイル -->\r\n");
      out.write("<style>\r\n");
      out.write("<!--\r\n");
      out.write("div#type1{width:100%;display:inline-block;margin:10px auto;max-width: 650px;}\r\n");
      out.write("\r\n");
      out.write("div#type1 li a {\r\n");
      out.write("\t\t\t\tpadding:10px 15px 10px 15px;\r\n");
      out.write("\t\t\t\ttext-decoration: none;\r\n");
      out.write("\t\t\t\tcolor:#FFFFFF;\r\n");
      out.write("\t\t\t\tbackground-color: #660000;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\r\n");
      out.write("article.grid{width:31%}\r\n");
      out.write("\r\n");
      out.write("a{color: black;}\r\n");
      out.write("-->\r\n");
      out.write("</style>\r\n");
      out.write("<!-- スタイル終わり -->\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" media=\"all\" href=\"css/reserve.css\" />\r\n");
      out.write("\r\n");
      out.write("<body>\r\n");
      model.User userInfo = null;
      synchronized (session) {
        userInfo = (model.User) _jspx_page_context.getAttribute("userInfo", javax.servlet.jsp.PageContext.SESSION_SCOPE);
        if (userInfo == null){
          userInfo = new model.User();
          _jspx_page_context.setAttribute("userInfo", userInfo, javax.servlet.jsp.PageContext.SESSION_SCOPE);
        }
      }
      out.write("\r\n");
      out.write("<!-- / メニューバー始まり（ユーザーページ） -->\r\n");
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
      out.write("    <nav id=\"mainNav\" >\r\n");
      out.write("  \t\t<a class=\"menu\" id=\"menu\"><span>Top Menu</span></a>\r\n");
      out.write("\t\t\t<div class=\"panel\">\r\n");
      out.write("\t\t\t\t<ul>\r\n");
      out.write("\t\t\t\t\t<li><a href=\"home.jsp\">トップページ<br><span>Top</span></a></li>\r\n");
      out.write("\t\t\t\t\t<li><a href=\"ShowMenuSvl\">メニュー紹介<br><span>Menu</span></a></li>\r\n");
      out.write("\t\t\t\t\t<li><a href=\"ReservationStatusSvl\">予約状況<br><span>Reserve</span></a></li>\r\n");
      out.write("\t\t\t\t\t<li><a href=\"newsDisplay.jsp\">お知らせ<br><span>News</span></a></li>\r\n");
      out.write("\t\t\t\t\t<li  class=\"active\"><a href=\"UserSvl\">");
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
      out.write("<!-- / メニューバー終わり（ユーザーページ） -->\r\n");
      out.write("<!-- / ユーザーメニューバー始まり-->\r\n");
      out.write("<div align=\"center\">\r\n");
      out.write("<div id=\"type1\">\r\n");
      out.write("<nav id=\"mainNav\">\r\n");
      out.write("  \t\t<a class=\"menu\" id=\"menu\"><span>User Menu</span></a>\r\n");
      out.write("\t\t\t<div class=\"panel\">\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t<ul>\r\n");
      out.write("<li><a href=\"ReserveInsertSvl\"><strong>個人予約</strong><br><span>Reserve</span></a></li>\r\n");
      out.write("<li><form name=\"frmc2\" method=\"post\" action=\"ReserveInsertSvl\" >\r\n");
      out.write("<input type=\"hidden\" name=\"charter\" value=\"1\" />\r\n");
      out.write("<a href=\"javascript:frmc2.submit()\"><strong>貸切予約</strong><br><span>GroupReserve</span></a></form></li>\r\n");
      out.write("<li><a href=\"ReserveListSvl\">予約一覧</strong><br><span>ReserveList</span></a></li>\r\n");
      out.write("<li><a href=\"UserUpdateSvl\">お客様情報変更</strong><br><span>Information Update</span></a></li>\r\n");
      out.write("<li><a href=\"UserDeleteSvl\"><strong>お客様脱会手続き</strong><br><span>Information Delete</span></a></li>\r\n");
      out.write("<li><a href=\"UserContactSvl\">お問い合わせ</strong><br><span>Contact</span></a></li>\r\n");
      out.write("</ul>\r\n");
      out.write("      </div>\r\n");
      out.write("    </nav>\r\n");
      out.write("    </div>\r\n");
      out.write("    </div>\r\n");
      out.write("<!-- / メニューバー終わり（ホーム） -->\r\n");
      out.write("\r\n");
      out.write("<div id=\"wrapper\">\r\n");
      out.write("<article class=\"post\" align=\"center\">\r\n");
      out.write("<section class=\"content\">\r\n");
      out.write("\t");
      model.Reserve reserve = null;
      reserve = (model.Reserve) _jspx_page_context.getAttribute("reserve", javax.servlet.jsp.PageContext.REQUEST_SCOPE);
      if (reserve == null){
        reserve = new model.Reserve();
        _jspx_page_context.setAttribute("reserve", reserve, javax.servlet.jsp.PageContext.REQUEST_SCOPE);
      }
      out.write("\r\n");
      out.write("\t<h3 align=\"center\">");
      out.write(org.apache.jasper.runtime.JspRuntimeLibrary.toString((((model.User)_jspx_page_context.findAttribute("userInfo")).getUsr_name())));
      out.write("様ご予約取消\r\n");
      out.write("\t</h3>\r\n");
      out.write("\t<div class=\"message\" align=\"center\">\r\n");
      out.write("\t\t");

			if (request.getAttribute("msg") != null) {
				String msg = (String) request.getAttribute("msg");
				out.print(msg);
			}
		
      out.write("\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<div align=\"center\">\r\n");
      out.write("\t\t<form id=\"cusfrm\" name=\"cusfrm\" method=\"post\" action=\"ReserveOperationSvl\" onsubmit=\"return check();\">\r\n");
      out.write("\t\t\t<table border=\"1\">\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<th>予約番号</th>\r\n");
      out.write("\t\t\t\t\t<td>");
      out.print(reserve.getRsvId());
      out.write("</td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t");
String charter = reserve.getCharter() == 1 ? ":貸切" : "";
      out.write("\r\n");
      out.write("\t\t\t\t\t<th>人数</th>\r\n");
      out.write("\t\t\t\t\t<td>");
      out.print(reserve.getPerson());
      out.write('名');
      out.print(charter);
      out.write("</td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<th>日付</th>\r\n");
      out.write("\t\t\t\t\t");
String dateYyMmDd = reserve.getRsvYy() + "年" + reserve.getRsvMm() + "月" + reserve.getRsvDd() + "日";
      out.write("\r\n");
      out.write("\t\t\t\t\t<td>");
      out.print(dateYyMmDd);
      out.write("</td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<th>ご予約時間</th>\r\n");
      out.write("\t\t\t\t\t<td>");
      out.print(String.format("%02d", reserve.getRsvHh()));
      out.write('：');
      out.print(String.format("%02d", reserve.getRsvMi()));
      out.write("～3時間</td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<th>コース</th>\r\n");
      out.write("\t\t\t\t\t<td>");
      out.print(reserve.getCourseName());
      out.write("</td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t");
 String tel = reserve.getTel() == 1 ? "電話" : "-"; 
      out.write("\r\n");
      out.write("\t\t\t\t\t");
 String mail = reserve.getMail() == 1 ? "メール" : "-"; 
      out.write("\r\n");
      out.write("\t\t\t\t\t<th>連絡方法(電話/メール)</th>\r\n");
      out.write("\t\t\t\t\t<td>");
      out.print(tel);
      out.write('/');
      out.print(mail);
      out.write("</td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<th>ご要望</th>\r\n");
      out.write("\t\t\t\t\t<td>");
      out.print(reserve.getExp());
      out.write("</td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<input type=\"hidden\" name=\"rsvId\" value=\"");
      out.print(reserve.getRsvId());
      out.write("\" />\r\n");
      out.write("\t\t\t\t\t<input type=\"hidden\" name=\"mode\" value=\"");
      out.print(ReserveOperationSvl.DELETE);
      out.write("\" />\r\n");
      out.write("\t\t\t\t\t<td colspan=\"2\"><input type=\"submit\" class=\"btn\" value=\"取消\" /></td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t<div>\r\n");
      out.write("\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t</table>\r\n");
      out.write("\t\t</form>\r\n");
      out.write("\t\t<div align=\"center\">\r\n");
      out.write("\t\t\t<a href=\"ReserveListSvl\">予約一覧に戻る</a>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("</section>\r\n");
      out.write("</article>\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
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
