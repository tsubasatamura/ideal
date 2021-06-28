<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<!-- メタ -->
<meta name="viewport" content="width=device-width, user-scalable=yes, maximum-scale=1.0, minimum-scale=1.0"/>
<meta name="description" content="■Restaurante IDEALLE■"/>
<meta name="keywords" content=""/>
<!-- メタ終わり -->
<title>顧客登録完了画面</title>
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
					<li><a href="home.jsp">トップページ<br><span>Top</span></a></li>
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
<div id="wrapper" align="center">
<article class="post">
<section class="content">
<h1>会員登録完了</h1>
<div>
<p>会員登録が完了いたしました。</p>
<p><jsp:getProperty name="userInfo" property="usr_name"/>様の会員IDは<jsp:getProperty name="userInfo" property="mail"/></p>
<p>ログインの際に必要となりますので、大切に保管してください。</p>
</div>
<div><a href="UserSvl">戻る</a></div>
</section>
</article>
</div>

		<!-- フォーム終了 -->
		<!-- / コンテンツ -->
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