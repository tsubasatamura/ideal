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
<!-- メタ -->
<meta name="viewport" content="width=device-width, user-scalable=yes, maximum-scale=1.0, minimum-scale=1.0"/>
<meta name="description" content="■Restaurante IDEALLE■"/>
<meta name="keywords" content=""/>
<!-- メタ終わり -->
<!-- スクリプト・JS -->
<!--[if lt IE 9]>
<script src="js/html5.js"></script>
<script src="js/css3-mediaqueries.js"></script>
<![endif]-->
<script src="js/jquery1.4.4.min.js"></script>
<script src="js/script.js"></script>
<!-- スクリプト・JS -->
<link rel="stylesheet" type="text/css" media="all" href="css/tamura.css"/>
<link rel="stylesheet" type="text/css" media="all" href="css/style.css"/>
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
<title>お問い合わせページ</title>
<script type="text/javascript">
<!--
	function check(){
		if(document.confrm.contact.value==""){
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
<jsp:useBean id="userInfo" class="model.User" scope="session"/>
<jsp:useBean id="msg" class="model.IdealException" scope="request"/>
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
<!-- / ユーザーメニューバー終わり-->
<div id="wrapper">
<section class="content">
<article class="post">
<h1 align="center">お問い合わせページ</h1>
<% if(msg.getErrmsg()!=null){%>
	<p><jsp:getProperty name="msg" property="errmsg"/></p>
<% } %>
<form id="confrm" name="confrm" action="UserContactOperationSvl" method="post" onsubmit="return check();">
<table id="contacttable">
<tr>
<td class="column">内容</td><td><textarea name="contact" rows="5" cols="35"></textarea></td>
</tr>
<tr>
<td colspan="2" class="submit"><input id="send" type="submit" value="送信"/></td>
</tr>
</table>
</form>
<div>&emsp;</div>
<%if(list.size()!=0){ %>
<%for(int i=0;i<list.size();i++){ %>
<table id="contactlist">
<%if(list.get(i).getAdminFlag()==0){%>
<tr><td id="name"><%=userInfo.getUsr_name() %>様</td><td id="date"><%=list.get(i).getDate().substring(0,16) %></td></tr>
<%	}else{ %>
<tr><td id="name" bgcolor="#CCCCCC">Restaurante IDEALLE</td><td id="date" bgcolor="#CCCCCC"><%=list.get(i).getDate().substring(0,16) %></td></tr>
<% } %>
<tr><td colspan="2" id="message"><%=list.get(i).getMessage()%></td></tr>
</table>
<%} %>
<%} %>
<div>&emsp;</div>
<p class="return"><a href="userIndex.jsp">戻る</a></p>
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