<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="javax.sql.*" %>
<%@ page import="javax.naming.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="width=device-width, user-scalable=yes, maximum-scale=1.0, minimum-scale=1.0"/>
<meta name="description" content="■Restaurante IDEALLE■"/>
<meta name="keywords" content=""/>
<link rel="stylesheet" type="text/css" media="all" href="css/style.css"/>
<link rel="stylesheet" type="text/css" media="all" href="css/tamura.css"/>
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

/* section.content{
width:80%;
margin-left: auto;
	margin-right: auto;}
 */
div.gridWrapper2{
width:100%;
    margin-left: 33.5%;
	margin-right: auto;}
-->
</style>
<!-- スタイル終わり -->
<title>お問い合わせ返信ページ</title>
<script type="text/javascript">
<!--
	function check(){
		if(document.admfrm.admcontact.value==""){
			window.alert("文字を入力してください。");
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
<jsp:useBean id="list" type="java.util.List<model.Contact>" class="java.util.ArrayList" scope="request"/>
<jsp:useBean id="userID" class="model.User" scope="request"/>
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
<h1><%=list.get(0).getUsr_name() %>様お問い合わせ返信ページ</h1>
<form id="admfrm" name="admfrm" action="AdminContactOperationSvl" method="post" onsubmit="return check();">
<table id="contacttable">
<tr>
<td class="column">内容</td><td><textarea name="admcontact" rows="5" cols="35"></textarea></td>
</tr>
<tr>
<td colspan="2" class="submit"><input id="send" type="submit" value="送信"/></td>
</tr>
</table>
<input type="hidden" name="userID" value="<%= userID.getUsr_id()%>" />
</form>
<div>&emsp;</div>
<%if(list.size()!=0){ %>
<%for(int i=0;i<list.size();i++){ %>
<table id="contactlist">
<%if(list.get(i).getAdminFlag()==0){%>
<tr><td id="name"><%=list.get(0).getUsr_name() %>様</td><td id="date"><%=list.get(i).getDate().substring(0,16) %></td></tr>
<%	}else{ %>
<tr><td id="name" bgcolor="#CCCCCC">Restaurante IDEALLE</td><td id="date" bgcolor="#CCCCCC"><%=list.get(i).getDate().substring(0,16) %></td></tr>
<% } %>
<tr><td colspan="2" id="message"><%=list.get(i).getMessage()%></td></tr>
</table>
<%} %>
<%} %>
<div>&emsp;</div>
<p class="return"><a href="AdminContactSvl">戻る</a></p>
</article>
</section>
</div>
</body>
</html>