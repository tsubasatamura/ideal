<?xml version="1.0" encoding="UTF-8" ?>
<%@page import="controller.AdminReserveOperationSvl"%>
<%@page import="model.*"%>
<%@page import="controller.ReserveOperationSvl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<!-- メタ -->
<meta charset="UTF-8"/>
<meta name="viewport" content="width=device-width, user-scalable=yes, maximum-scale=1.0, minimum-scale=1.0"/>
<meta name="description" content="■Restaurante IDEALLE■"/>
<meta name="keywords" content=""/>
<!-- メタ終わり -->

<title>予約変更</title>

<!-- / CSSリンク（アドミン側） -->
<link rel="stylesheet" href="css/style.css" type="text/css" media="screen"/>
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

div.gridWrapper2{
width:100%;
    margin-left: 33.5%;
	margin-right: auto;}
-->
</style>
<!-- スタイル終わり -->

<link rel="stylesheet" type="text/css" media="all" href="css/reserve.css"/>
<script type="text/javascript">
<!--
	function check(){
		if(window.confirm("ご予約を取消してよろしいですか？")){
			return true;
		}else{
			return false;
		}
	}
//-->
</script>
<noscript>
	<meta http-equiv="refresh" content="0;URL=noscript.jsp"/>
</noscript>
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
    </div>

	<!-- コンテンツ -->
	<jsp:useBean id="adminInfo" class="model.Admin" scope="session" />
	<jsp:useBean id="reserve" class="model.Reserve" scope="request" />
<div id="wrapper">
<article class="post" align="center">
<section class="content">
	<h1 align="center">予約取消</h1>
	<div class="message" align="center">
		<%
			if (request.getAttribute("msg") != null) {
				String msg = (String) request.getAttribute("msg");
				out.print(msg);
			}
		%>
	</div>
	<div align="center">
		<form id="cusfrm" name="cusfrm" method="post" action="AdminReserveOperationSvl" onsubmit="return check();">
			<table border="1">
				<tr>
					<th>予約番号</th>
					<td><%=reserve.getRsvId()%></td>
				</tr>
				<tr>
					<th>予約者氏名</th>
					<td><%=reserve.getUsrName()%>様</td>
				</tr>
				<tr>
					<%String charter = reserve.getCharter() == 1 ? ":貸切" : "";%>
					<th>人数</th>
					<td><%=reserve.getPerson()%>名<%=charter%></td>
				</tr>
				<tr>
					<th>予約日</th>
					<td><%=reserve.getRsvYy() + "年" + reserve.getRsvMm() + "月" + reserve.getRsvDd() + "日"%></td>
				</tr>
				<tr>
					<th>予約時間</th>
					<td><%=String.format("%02d", reserve.getRsvHh())%>：<%=String.format("%02d", reserve.getRsvMi())%>　～3時間</td>
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
					<input type="hidden" name="mode" value="<%=AdminReserveOperationSvl.DELETE%>" />
					<td colspan="2" class="subumitTd"><input type="submit" class="btn" value="取消" /></td>
				</tr>
			<div>

			</div>
			</table>
		</form>
		<div align="center">
			<a href="AdminReserveListSvl">予約一覧に戻る</a>
		</div>
	</div>
	</section>
	</article>
	</div>

</body>
</html>