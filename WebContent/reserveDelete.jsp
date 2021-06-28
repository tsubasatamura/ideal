<?xml version="1.0" encoding="UTF-8" ?>
<%@page import="model.*"%>
<%@page import="controller.ReserveOperationSvl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<!-- メタ -->
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, user-scalable=yes, maximum-scale=1.0, minimum-scale=1.0">
<meta name="description" content="■Restaurante IDEALLE■">
<meta name="keywords" content="">
<!-- メタ終わり -->

<title>ユーザー予約取消</title>

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
<!-- スクリプト・JS -->

<!-- スタイル -->
<style>
<!--
div#type1{width:100%;display:inline-block;margin:10px auto;max-width: 650px;}

div#type1 li a {
				padding:10px 15px 10px 15px;
				text-decoration: none;
				color:#FFFFFF;
				background-color: #660000;
			}

article.grid{width:31%}

a{color: black;}
-->
</style>
<!-- スタイル終わり -->
<link rel="stylesheet" type="text/css" media="all" href="css/reserve.css" />

<body>
<jsp:useBean id="userInfo" class="model.User" scope="session"/>
<!-- / メニューバー始まり（ユーザーページ） -->
<div id="header">
	<div class="inner">
		<!-- ロゴ -->
		<div class="logo">
			<a href="home.jsp">■Restaurante IDEALLE■<br><span>Italian Restaurante</span></a>
      <h1>welcome to Ideal</h1>
		</div>
		<!-- / ロゴ -->

	  <!-- メインナビゲーション -->
    <nav id="mainNav" >
  		<a class="menu" id="menu"><span>Top Menu</span></a>
			<div class="panel">
				<ul>
					<li><a href="home.jsp">トップページ<br><span>Top</span></a></li>
					<li><a href="ShowMenuSvl">メニュー紹介<br><span>Menu</span></a></li>
					<li><a href="ReservationStatusSvl">予約状況<br><span>Reserve</span></a></li>
					<li><a href="newsDisplay.jsp">お知らせ<br><span>News</span></a></li>
					<li  class="active"><a href="UserSvl"><jsp:getProperty name="userInfo" property="usr_name"/>様マイページ<br><span>MyPage</span></a></li>
					<li><a href="UserLogoffSvl">ログアウト<br><span>Logout</span></a></li>
				</ul>
      </div>
    </nav>
		<!-- メインナビゲーション -->

  </div>
</div>
<!-- / メニューバー終わり（ユーザーページ） -->
<!-- / ユーザーメニューバー始まり-->
<div align="center">
<div id="type1">
<nav id="mainNav">
  		<a class="menu" id="menu"><span>User Menu</span></a>
			<div class="panel">

				<ul>
<li><a href="ReserveInsertSvl"><strong>個人予約</strong><br><span>Reserve</span></a></li>
<li><form name="frmc2" method="post" action="ReserveInsertSvl" >
<input type="hidden" name="charter" value="1" />
<a href="javascript:frmc2.submit()"><strong>貸切予約</strong><br><span>GroupReserve</span></a></form></li>
<li><a href="ReserveListSvl">予約一覧</strong><br><span>ReserveList</span></a></li>
<li><a href="UserUpdateSvl">お客様情報変更</strong><br><span>Information Update</span></a></li>
<li><a href="UserDeleteSvl"><strong>お客様脱会手続き</strong><br><span>Information Delete</span></a></li>
<li><a href="UserContactSvl">お問い合わせ</strong><br><span>Contact</span></a></li>
</ul>
      </div>
    </nav>
    </div>
    </div>
<!-- / メニューバー終わり（ホーム） -->

<div id="wrapper">
<article class="post" align="center">
<section class="content">
	<jsp:useBean id="reserve" class="model.Reserve" scope="request" />
	<h3 align="center"><jsp:getProperty name="userInfo" property="usr_name" />様ご予約取消
	</h3>
	<div class="message" align="center">
		<%
			if (request.getAttribute("msg") != null) {
				String msg = (String) request.getAttribute("msg");
				out.print(msg);
			}
		%>
	</div>
	<div align="center">
		<form id="cusfrm" name="cusfrm" method="post" action="ReserveOperationSvl" onsubmit="return check();">
			<table border="1">
				<tr>
					<th>予約番号</th>
					<td><%=reserve.getRsvId()%></td>
				</tr>
				<tr>
					<%String charter = reserve.getCharter() == 1 ? ":貸切" : "";%>
					<th>人数</th>
					<td><%=reserve.getPerson()%>名<%=charter%></td>
				</tr>
				<tr>
					<th>日付</th>
					<%String dateYyMmDd = reserve.getRsvYy() + "年" + reserve.getRsvMm() + "月" + reserve.getRsvDd() + "日";%>
					<td><%=dateYyMmDd%></td>
				</tr>
				<tr>
					<th>ご予約時間</th>
					<td><%=String.format("%02d", reserve.getRsvHh())%>：<%=String.format("%02d", reserve.getRsvMi())%>～3時間</td>
				</tr>
				<tr>
					<th>コース</th>
					<td><%=reserve.getCourseName()%></td>
				</tr>
				<tr>
					<% String tel = reserve.getTel() == 1 ? "電話" : "-"; %>
					<% String mail = reserve.getMail() == 1 ? "メール" : "-"; %>
					<th>連絡方法(電話/メール)</th>
					<td><%=tel%>/<%=mail%></td>
				</tr>
				<tr>
					<th>ご要望</th>
					<td><%=reserve.getExp()%></td>
				</tr>
				<tr>
					<input type="hidden" name="rsvId" value="<%=reserve.getRsvId()%>" />
					<input type="hidden" name="mode" value="<%=ReserveOperationSvl.DELETE%>" />
					<td colspan="2"><input type="submit" class="btn" value="取消" /></td>
				</tr>
			<div>

			</div>
			</table>
		</form>
		<div align="center">
			<a href="ReserveListSvl">予約一覧に戻る</a>
		</div>
	</div>
</section>
</article>
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