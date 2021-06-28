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

<title>管理者ログイン</title>

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

<script type="text/javascript">
<!--
	function check() {
		if (document.frm1.name.value == "") {
			window.alert("IDが未入力です。");
			return false;
		} else if (document.frm1.pass.value == "") {
			window.alert("パスワードが未入力です。");
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

<div id="wrapper">
	<section class="content">
	<article class="post">
<h1 align="center">管理者ログイン</h1>
	<div align="center">
		管理者IDとパスワードを入力してください。<br />
		<jsp:useBean id="msg" class="model.IdealException" scope="request"/>
		<% if(msg.getErrmsg()!=null){%>
			<p><font color="red">IDかパスワードが間違っています</font></p>
		<% } %>
	</div>
	<div align="center">
		<form id="frm1" name="frm1" action="AdminLoginSvl" method="post" onsubmit="return check();">
			<table border="1">
				<tr>
					<th>管理者ID</th>
					<td><input type="text" name="id" /></td>
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
	</div>
	<div align="center">
		<a href="home.jsp"> ホーム </a>
	</div>
			</article>
		</section>
	</div>

</body>
</html>