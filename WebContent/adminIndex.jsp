<?xml version="1.0" encoding="UTF-8" ?>
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

<title>管理者処理選択画面</title>

<!-- / CSSリンク（アドミン側） -->
<link rel="stylesheet" href="css/style.css" type="text/css" media="screen">
<!-- / CSSリンク（アドミン側） -->

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

section.content{
width:80%;
margin-left: auto;
	margin-right: auto;}

div.gridWrapper2{
width:100%;
    margin-left: 33.5%;
	margin-right: auto;}
-->
</style>
<!-- スタイル終わり -->


</head>
<body>
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


<jsp:useBean id="adminInfo" class="model.Admin" scope="session"/>
</div>
<section class="content">
<div class="post">
<div id="wrapper">
<h1 align="center">管理者ページ</h1>
<section class="gridWrapper">
<article class="grid">
<div class="box">
<h3><a href="MenuMaintenanceSvl">●メニューメンテ</a></h3>
</div>
</article>
<article class="grid">
<div class="box">
<h3><a href="NewsSvl">●お知らせ更新</a></h3>
</div>
</article>
<article class="grid">
<div class="box">
<h3><a href="AdminReserveListSvl">●予約管理一覧</a></h3>
</div>
</article>
</div>
<div id="wrapper">
<section class="gridWrapper">
<article class="grid">
<div class="box">
<h3><a href="HolidaySvl">●定休日変更</a></h3>
</div>
</article>
<article class="grid">
<div class="box">
<h3><a href="AdminContactSvl">●お問い合わせ一覧</a></h3>
</div>
</article>
<article class="grid">
<div class="box">
<h3><a href="UserListSvl">●ユーザー一覧</a></h3>
</div>
</article>
</section>
</div>
<div id="wrapper">
<div class="gridWrapper2">
<section class="gridWrapper">
<article class="grid">
<div class="box">
<h3><a href="AdminLogoffSvl">●ログアウト</a></h3>
</div>
</article>
</section>
</div>
</div>
</div>
</section>

</body>
</html>