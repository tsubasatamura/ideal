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

<title>お客様ログイン</title>

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

<script type="text/javascript">
<!--
	function check() {
		if (document.frm1.name.value == "") {
			window.alert("メールアドレスが未入力です。");
			return false;
		} else if (document.frm1.pass.value == "") {
			window.alert("パスワードが未入力です。");
			return false;
		}else if(!document.frm1.name.value.match(/^[A-Za-z0-9]{1}[A-Za-z0-9_.-]*@{1}[A-Za-z0-9_.-]{1,}\.[A-Za-z0-9]{1,}$/)){
			window.alert("半角を用い、正しいメールアドレスを入力ください。");
			return false;
		} else if (!document.frm1.pass.value.match(/^[a-zA-Z0-9]*$/)) {
			window.alert("パスワードは半角英数字を入力ください。");
			return false;
		} else {
			if (window.confirm("これでよろしいですか？")) {
				return true;
			} else {
				return false;
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
					<li class="active"><a href="UserSvl">ログイン<br><span>Login</span></a></li>
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
<div id="wrapper">
<section class="content">
<article class="post">
<h1 align="center">お客様ログイン</h1>
	<div align="center">
		登録されたメールアドレスとパスワードを入力してください。<br />
		<jsp:useBean id="msg" class="model.IdealException" scope="request"/>
		<% if(msg.getErrmsg()!=null){%>
			<p><font color="red">メールアドレスかパスワードが間違っていますのでもう一度入力してください。</font></p>
		<% } %>
	</div>
	<div align="center">
		<form id="frm1" name="frm1" action="UserLoginSvl" method="post" onsubmit="return check();">
			<table border="1">
				<tr>
					<th>e-mail</th>
					<td><input type="text" name="name" /></td>
				</tr>
				<tr>
					<th>パスワード</th>
					<td><input type="password" name="pass" /></td>
				</tr>
				<tr>
					<td colspan="2" class="submit"><input type="submit" value="ログイン" /></td>
				</tr>
			</table>
		</form>
		<p>会員登録をされていない方は<a href="userInsert.jsp">コチラ</a></p>
	</div>
	<div align="center">
		<a href="home.jsp"> ホーム </a>
	</div>
</article>
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


