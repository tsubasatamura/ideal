<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page import="java.sql.*" %>
<%@ page import="javax.sql.*" %>
<%@ page import="javax.naming.*" %>
<%@ page import="model.*"%>
<%@ page import="java.time.LocalDateTime"%>
<%@ page import="java.time.format.DateTimeFormatter"%>
<%@ page import="java.util.Calendar"%>
<head>

<!-- メタ -->
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, user-scalable=yes, maximum-scale=1.0, minimum-scale=1.0">
<meta name="description" content="■Restaurante IDEALLE■">
<meta name="keywords" content="">
<!-- メタ終わり -->

<title>■Restaurante IDEALLE■</title>

<!-- リンク -->
<link rel="stylesheet" href="css/style.css" type="text/css" media="screen">
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
h2.heading a{
color:#fff;}

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
    width:50%;
    margin-left: auto;
	margin-right: auto;
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
    width: 50%;
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
.info {padding: 0 0.5em;width: 96%;height: 15em;overflow: scroll;}
.info dl {padding: 0.5em 0;}
.info dt {padding: 0.5em;width: 25em;}
@media screen and (min-width: 480px) {
.info dt {clear: left;float: left;}
.info dd {margin-left: 0.5em;border-bottom: 1px solid #ccc;}

-->
</style>
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
					<li  class="active"><a href="home.jsp">トップページ<br><span>Top</span></a></li>
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
					<li class="active"><a href="home.jsp">トップページ<br><span>Top</span></a></li>
					<li><a href="ShowMenuSvl">メニュー紹介<br><span>Menu</span></a></li>
					<li><a href="ReservationStatusSvl">予約状況<br><span>Reserve</span></a></li>
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
<div id="mainBanner">
<div class="inner">
<img src="images/menu/main.jpg" width="940" height="300" >
<div class="slogan">
<h2>人生の節目、特別なひとときをお客様に</h2>    	<p>貸し切り、団体予約も受け付けております。</p>    	</div>
</div>
</div>

<%
	String yobi[] = {"日","月","火","水","木","金","土"};
	LocalDateTime nowDate = LocalDateTime.now();
	DateTimeFormatter fmt01 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	String now_date = fmt01.format(nowDate);
	String now_dateYy = now_date.substring(0, 4);
	String now_dateMm = now_date.substring(5, 7);
	String now_dateDd = now_date.substring(8, 10);
%>

<div id="wrapper">
<section id="post-221" class="content">
<h2 class="heading"><a href="ReservationStatusSvl">予約状況</a></h2>
<article class="post">
<div align="center">
<table class="tbl-r03">
<thead>
		<tr>
		<%
	    	int y = Integer.parseInt(now_date.substring(0,4));
	    	int m = Integer.parseInt(now_date.substring(5, 7));
	    	int d = Integer.parseInt(now_date.substring(8, 10));
	        Calendar cal = Calendar.getInstance();
			for(int i = 0; i < 7; i++){
		        cal.set(y, m-1, d);
		%>
			<th><%= m + "/" + d %>(<%=yobi[cal.get(Calendar.DAY_OF_WEEK)-1]%>)</th>
		<%
				d++;
			}
		%>
		</tr>
		</thead>
		<thead>
		<tr>
		<%
			String date;
			String mark;
			for(int i = 0; i < 7; i++){
				date = now_date.substring(0, 7) + "-" + String.format("%02d",(Integer.parseInt(now_dateDd) + i));
				mark = "◎";
				int status = (int)Reserve.getReservationStatus(date);
				if(status == 2) {
					mark = "〇";
				}else if(status  == 1) {
					mark = "△";
				}else if(status  == 0) {
					mark = "×";
				}
		%>
			<th><%= mark %></th>
		<%}%>
		</tr>
		</thead>
	</table>
	</div>
	</article>
	</section>
	</div>


<div id="wrapper">
<section id="post-221" class="content">
<h2 class="heading"><a href="newsDisplay.jsp">お知らせ</a></h2>
<article class="post">
<div class="info">
<%
request.setCharacterEncoding("utf-8");

InitialContext ic=new InitialContext();
DataSource ds=(DataSource)ic.lookup("java:comp/env/mysql");
Connection con=ds.getConnection();

Statement st=con.createStatement();
String sql="SELECT * FROM news ORDER BY n_date DESC";
st=con.createStatement();
ResultSet rs=st.executeQuery(sql);
while(rs.next()){
%>
<dt><%=rs.getString("title")%></dt>
<dl><%=rs.getString("n_date").substring(0, 16)%></dl>
<dd><%=rs.getString("n_message")%></dd>
<%}
	ic.close();
	con.close();
	rs.close();
%>
</div>

</article>
</section>
</div>
<div id="wrapper">
<section id="post-221" class="content">
<h2 class="heading">店舗情報</h2>
<article class="post">
<p>
住所：北海道札幌市中央区北５条西2−５ JRタワーオフィスプラザさっぽろ19F
<br />
TEL：011-206-1663
<br />
営業時間：17：00～24：00
<br />
定休日：水曜日
<br />
駐車場なし。
<br />
お車ご利用の場合はお近くのコインパーキングにお停めください。
<br />
お支払い：現金・カード・電子マネー各種ご利用できます。
<iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d1457.3608076759344!2d141.35082105619185!3d43.068322198071684!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x5f0b2974c4e641d5%3A0x2fd7ce4f4c00cf1c!2z44OR44K944Kz44Oz5pWZ5a6kIEtFTuOCueOCr-ODvOODqyDmnK3luYzmoKE!5e0!3m2!1sja!2sjp!4v1622542660232!5m2!1sja!2sjp"
 width="100%" height="350" style="border:0;" allowfullscreen="" loading="lazy">
 </iframe>
 </p>
</article>
</section>
</div>
<!-- / WRAPPER -->

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


