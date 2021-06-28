<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.text.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="width=device-width, user-scalable=yes, maximum-scale=1.0, minimum-scale=1.0"/>
<meta name="description" content="■Restaurante IDEALLE■"/>
<meta name="keywords" content=""/>
<link rel="stylesheet" type="text/css" media="all" href="css/style.css"/>
<link rel="stylesheet" type="text/css" media="all" href="css/tamura.css"/>
<link rel="stylesheet" type="text/css" media="all" href="css/tamuraholiday.css"/>
<title>定休日変更</title>
<!-- スクリプト・JS -->
<!--[if lt IE 9]>
<script src="js/html5.js"></script>
<script src="js/css3-mediaqueries.js"></script>
<![endif]-->
<script src="js/jquery1.4.4.min.js"></script>
<script src="js/script.js"></script>
<!-- スクリプト・JS終わり -->

<!-- スタイル -->
<style>
<!--
div#type1{width:100%;display:inline-block;margin:10px auto;max-width: 650px;}

div#type1 li a {
				padding:10px 4px 10px 4px;
				text-decoration: none;
				color:#FFFFFF;
				background-color: #660000;
			}

article.grid{width:31%}

a{color: black;}


div.gridWrapper2{
width:100%;
    margin-left: 33.5%;
	margin-right: auto;}
-->
</style>
<!-- スタイル終わり -->
<script type="text/javascript">
<!--
function check(h) {
	   var ElementsCount = document.hfrm.holiday.length+7; // チェックボックスの数
	   var cnt=h-1;
	   if(document.hfrm.day[cnt].checked){
	     for( i=0 ; i<ElementsCount+38 ; i++ ) {
		     if(document.hfrm.elements[i].className==h){
			     document.hfrm.elements[i].checked = true; // ON・OFFを切り替え
		     }
	     }
	   }else{
		   for( i=0 ; i<ElementsCount+38 ; i++ ) {
			     if(document.hfrm.elements[i].className==h){
				     document.hfrm.elements[i].checked = false; // ON・OFFを切り替え
			     }
	       }
	}
}
//-->
</script>
<noscript>
	<meta http-equiv="refresh" content="0;URL=noscript.jsp"/>
</noscript>
</head>
<body>
<jsp:useBean id="msg" class="model.IdealException" scope="request"/>
<!-- / メニューバー始まり（ホームと同じ） -->
<div id="header">
	<div class="inner">
		<!-- ロゴ -->
		<div class="logo">
			<a href="home.jsp">■Restaurante IDEALLE■<br><span>Italian Restaurante</span></a>
      <h1>welcome to Ideal</h1>
		</div>
		<!-- / ロゴ -->

	  <!-- メインナビゲーション -->
    <nav id="mainNav">
  		<a class="menu" id="menu"><span>Top Menu</span></a>
			<div class="panel">
				<ul>
					<li><a href="home.jsp">トップページ<br><span>Top</span></a></li>
					<li><a href="ShowMenuSvl">メニュー紹介<br><span>Menu</span></a></li>
					<li><a href="ReservationStatusSvl">予約状況<br><span>Reserve</span></a></li>
					<li><a href="newsDisplay.jsp">お知らせ<br><span>News</span></a></li>
					<li><a href="UserSvl">ログイン<br><span>Login</span></a></li>
				</ul>
      </div>
    </nav>
		<!-- メインナビゲーション -->

  </div>
</div>
<!-- / メニューバー終わり（ホームと同じ） -->

<!-- / アドミンメニューバー始まり-->
<div align="center">
<div id="type1">
<nav id="mainNav">
 <a class="menu" id="menu"><span>Admin Menu</span></a>
<div class="panel">
<ul>
<li><a href="AdminSvl"><strong>トップ</strong><br><span>Top</span></a></li>
<li><a href="MenuMaintenanceSvl"><strong>メニューメンテ</strong><br><span>Menu</span></a></li>
<li><a href="NewsSvl"><strong>お知らせ更新</strong><br><span>News</span></a></li>
<li><a href="AdminReserveListSvl">予約管理</strong><br><span>Reserve</span></a></li>
<li><a href="HolidaySvl">定休日変更</strong><br><span>Holiday</span></a></li>
<li><a href="AdminContactSvl">お問い合わせ一覧</strong><br><span>Contact</span></a></li>
<li><a href="UserListSvl">ユーザー一覧</strong><br><span>UserList</span></a></li>
<li><a href="AdminLogoffSvl">ログアウト</strong><br><span>Logout</span></a></li>
</ul>
</div>
</nav>
</div>
<!-- / アドミンメニューバー終わり-->
<div id="wrapper">
<section class="content">
<article class="post">
<h1>定休日更新</h1>
<%
	HCalendar mc=(HCalendar)request.getAttribute("mc");
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    Calendar cal = Calendar.getInstance();
%>
<% if(msg.getErrmsg()!=null){%>
	<p><jsp:getProperty name="msg" property="errmsg"/></p>
<% } %>
<h2 id="month"><%=mc.getYear() %>年<%=mc.getMonth() %>月</h2>
	<span class="before"><a href="?year=<%=mc.getYear()%>&month=<%=mc.getMonth()-1%>">&lt;&lt;前月</a></span>
	<span class="after"><a href="?year=<%=mc.getYear()%>&month=<%=mc.getMonth()+1%>">翌月&gt;&gt;</a></span>
<form id="hfrm" name="hfrm" action="HolidayInsertSvl" method="post" onsubmit="return chekced();">
<table id="holidaytable">
      <tr>
        <th><label>日<br/><input type="checkbox" name="day" onclick="check(1);"/></label></th>
        <th><label>月<br/><input type="checkbox" name="day" onclick="check(2);"/></label></th>
        <th><label>火<br/><input type="checkbox" name="day" onclick="check(3);"/></label></th>
        <th><label>水<br/><input type="checkbox" name="day" onclick="check(4);"/></label></th>
        <th><label>木<br/><input type="checkbox" name="day" onclick="check(5);"/></label></th>
        <th><label>金<br/><input type="checkbox" name="day" onclick="check(6);"/></label></th>
        <th><label>土<br/><input type="checkbox" name="day" onclick="check(7);"/></label></th>
      </tr>
      <%for(String[] row: mc.getData()){ %>
      <tr>
      	<%for(String col:row) {%>
      		<%if (col.startsWith("*")){ %>
      		<%int cnt=0; %>
      			<%
      			int year  = mc.getYear();   // 年
      			int month = mc.getMonth()-1; // 月
      			int day  = Integer.parseInt(col.substring(1));     // 日;
      			cal.set(year, month, day);
      			Date date = cal.getTime();
      			String strDate=sdf.format(date);%>
      			<%if(Holiday.checkReserve(strDate)){ %>
					<td class="hol"><div class="con"><label><%=col.substring(1)%><br/><input class="<%=cal.get(Calendar.DAY_OF_WEEK) %>" name="holiday" type="checkbox" value="<%=strDate %>" disabled/></label></div></td>
      			<%}else{ %>
      			<%if (Holiday.checkHoliday(strDate)){%>
      				<td><label><%=col.substring(1)%><br/><input class="<%=cal.get(Calendar.DAY_OF_WEEK) %>" name="holiday" type="checkbox" value="<%=strDate %>" checked/></label></td>
      				<input type="hidden" name="delete" value="<%=strDate %>"/>
      			<%}else{ %>
      				<td><label> <%=col.substring(1)%><br/><input class="<%=cal.get(Calendar.DAY_OF_WEEK) %>" name="holiday" type="checkbox" value="<%=strDate %>" /></label></td>
			<%} %>
			<%} %>
      		<%}else if(col!=""){ %>
      			<%int cnt=0;
      			int year  = mc.getYear();   // 年
      			int month = mc.getMonth()-1; // 月
      			int day  = Integer.parseInt(col);     // 日;
      			cal.set(year, month, day);
      			Date date = cal.getTime();
      			String strDate=sdf.format(date);%>
      			<%if(Holiday.checkReserve(strDate)){ %>
      			<td class="hol"><div class="con"><label><%=col %><br/><input class="<%=cal.get(Calendar.DAY_OF_WEEK) %>" name="holiday" type="checkbox" value="<%=strDate %>" disabled/></label></div></td>
      			<%}else{ %>
      			<%if (Holiday.checkHoliday(strDate)){%>
      			<td><label><%=col %><br/><input class="<%=cal.get(Calendar.DAY_OF_WEEK) %>" name="holiday" type="checkbox" value="<%=strDate %>" checked/></label></td>
      			<input type="hidden" name="delete" value="<%=strDate %>"/>
      			<%}else{ %>
      			<td><label><%=col %><br/><input class="<%=cal.get(Calendar.DAY_OF_WEEK) %>" name="holiday" type="checkbox" value="<%=strDate %>" /></label></td>
      			<%} %>
      			<%} %>
      		<%}else{ %>
      			<td><%=col %></td>
      	    <%} %>
        <%} %>
      </tr>
      <%} %>
</table>
<div align="center"><input id="submit" type="submit" value="設定"/></div>
<p class="alert">※青枠はすでに予約が入っている日にちです。</p>
</form>
<div>&emsp;</div>
<p class="return"><a href="adminIndex.jsp">戻る</a></p>
</article>
</section>
</div>
</body>
</html>