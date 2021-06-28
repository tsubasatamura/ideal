<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<!-- メタ -->
<meta charset="UTF-8"/>
<meta name="viewport" content="width=device-width, user-scalable=yes, maximum-scale=1.0, minimum-scale=1.0"/>
<meta name="description" content="■Restaurante IDEALLE■"/>
<meta name="keywords" content=""/>
<!-- メタ終わり -->
<title>予約状況</title>

<!-- リンク -->
<link rel="stylesheet" href="css/style.css" type="text/css" media="screen"/>
<!-- リンク終わり -->

<!-- スクリプト・JS -->
<!--[if lt IE 9]>
<script src="js/html5.js"></script>
<script src="js/css3-mediaqueries.js"></script>
<![endif]-->
<script src="js/jquery1.4.4.min.js"></script>
<script src="js/script.js"></script>
<!-- スクリプト・JS終わり -->
<style>
<!--
th {
  background: #e9727e;
  border: solid 1px #ccc;
  color: #fff;
  padding: 10px;
}
td {
  border: solid 1px #ccc;
  padding: 10px;
}
@media screen and (max-width: 640px) {
  .tbl-r03 {
    width: 90%;
  }
  .tbl-r03 tr {
    display: block;
    float: left;
  }
  .tbl-r03 tr td,
  .tbl-r03 tr th {
    border-left: none;
    display: block;
    height: 50px;
  }
  .tbl-r03 thead {
    display: block;
    float: left;
    width: 30%;
  }
  .tbl-r03 thead tr {
    width: 100%;
  }
  .tbl-r03 tbody {
    display: block;
    float: left;
    width: 70%;
  }
  .tbl-r03 tbody tr {
    width: 50%;
  }
  .tbl-r03 tr td + td {
    border-left: none;
  }
  .tbl-r03 tbody td:last-child {
    border-bottom: solid 1px #ccc;
  }
}
.info {padding: 0 1.5em;width: 96%;height: 15em;overflow: scroll;}
.info dl {padding: 0.5em 0;}
.info dt {padding: 0.5em;width: 25em;}
@media screen and (min-width: 480px) {
.info dt {clear: left;float: left;}
.info dd {margin-left: 0.5em;border-bottom: 1px solid #ccc;}

-->
</style>
<link rel="stylesheet" type="text/css" media="all" href="css/reserve.css" />
</head>
<body>
<jsp:useBean id="userInfo" class="model.User" scope="session"/>
<%
String userName=userInfo.getUsr_name();
System.out.println(userName);
if(userName==null){%>
<!-- メニューバー -->
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
					<li class="active"><a href="ReservationStatusSvl">予約状況<br><span>Reserve</span></a></li>
					<li><a href="newsDisplay.jsp">お知らせ<br><span>News</span></a></li>
					<li><a href="UserSvl">ログイン<br><span>Login</span></a></li>
				</ul>
      </div>
    </nav>
		<!-- メインナビゲーション -->

  </div>
</div>
<% }else{%>
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
					<li class="active"><a href="ReservationStatusSvl">予約状況<br><span>Reserve</span></a></li>
					<li><a href="newsDisplay.jsp">お知らせ<br><span>News</span></a></li>
					<li><a href="UserSvl"><jsp:getProperty name="userInfo" property="usr_name"/>様マイページ<br><span>MyPage</span></a></li>
					<li><a href="UserLogoffSvl">ログアウト<br><span>Logout</span></a></li>
				</ul>
      </div>
    </nav>
		<!-- メインナビゲーション -->

  </div>
</div>
<%} %>
<!-- / メニューバー終わり（ホーム） -->

<jsp:useBean id="statusMap" class="java.util.HashMap" scope="request" />
	<% HCalendar mc = (HCalendar) request.getAttribute("mc"); %>
	<div id="wrapper">
		<section class="content">
		<div class="post">
			<h1 align="center">予約状況</h1>
			<div class="reservation">
			<div class="currentM">
			<a href="?year=<%=mc.getYear()%>&month=<%=mc.getMonth() - 1%>" class="prev">≪前月</a>
			<span><%=mc.getYear()%>年<%=mc.getMonth()%>月</span>
			<a href="?year=<%=mc.getYear()%>&month=<%=mc.getMonth() + 1%>" class="next">翌月≫</a>
			</div>
			<div class="scroll-table">
			<table>
				<tr>
					<th>日</th>
					<th>月</th>
					<th>火</th>
					<th>水</th>
					<th>木</th>
					<th>金</th>
					<th>土</th>
				</tr>
				<%
					String date;
				%>
				<%
					for (String[] row : mc.getData()) {
				%>
				<tr>
					<%
						for (String col : row) {
					%>
					<%
						if (col.startsWith("*")) {
									date = String.format("%02d", mc.getYear()) + "-" + String.format("%02d", mc.getMonth()) + "-" + String.format("%02d", Integer.parseInt(col.substring(1)));
									String mark = "◎";
									int status = (int) statusMap.get(date);
									if (status == 2) {
										mark = "〇";
									} else if (status == 1) {
										mark = "△";
									} else if (status == 0) {
										mark = "×";
									}
					%>
					<td class="today">
						<p><%=col.substring(1)%></p>
						<p class="mark"><%=mark%></p>
					</td>
					<%
						} else if (col != "") {
									date = String.format("%02d", mc.getYear()) + "-" + String.format("%02d", mc.getMonth()) + "-" + String.format("%02d", Integer.parseInt(col));
									String mark = "◎";
									int status = (int) statusMap.get(date);
									if (status == 2) {
										mark = "〇";
									} else if (status == 1) {
										mark = "△";
									} else if (status == 0) {
										mark = "×";
									}
					%>
					<td>
						<p><%=col%></p>
						<p class="mark"><%=mark%></p>
					</td>
					<%
						} else {
					%>
					<td><%=col%></td>
					<%
						}
					%>
					<%
						}
					%>
				</tr>
				<%
					}
				%>
			</table>
		</div>
		<div class="annotation">
		<p>◎：貸切予約（2～30名様）および個人予約（2～6名様）が可能</p>
		<p>〇：個人（2～6名様）の予約が可能</p>
		<p>×：予約不可</p>
		</div>
		</div>
		</div>
	</section>
	</div>

<!-- フッター -->
<footer id="footer">
	<div class="inner">

  	<!-- 3カラム -->
    <section class="gridWrapper">

			<article class="grid">
	  		<!-- ロゴ -->
				<p class="logo">
  				<a href="home.jsp">■Restaurante IDEALLE■<br><span>Italian Restaurante</span></a>
				</p>
        <!-- / ロゴ -->
     	</article>

      <!-- 電話番号+受付時間 -->
    	<article class="grid">
    		<p class="tel"><span>電話:</span> <strong>011-206-1663</strong></p>
    		<p class="open">営業時間: 17：00～24：00</p>
    		<p class="open">定休日: 水曜日</p>
			<!-- / 電話番号+受付時間 -->
      </article>

    	<article class="grid copyright">
      	Copyright(c) 2016 ホームページサンプル株式会社 All Rights Reserved. Design by <a href="http://f-tpl.com" target="_blank" rel="nofollow">http://f-tpl.com</a><br/>
      	<a href="AdminSvl">管理者用</a>
      </article>

    </section>
		<!-- / 3カラム -->

	</div>
</footer>
<!-- / フッター 終わり-->
</body>
</html>