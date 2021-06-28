<?xml version="1.0" encoding="UTF-8" ?>
<%@page import="controller.ReserveOperationSvl"%>
<%@page import="java.util.*"%>
<%@page import="model.Reserve"%>
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

<title>ユーザー予約情報一覧</title>

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
<script type="text/javascript">
<!--
	function deleteRsv(val) {
		document.frm1.rsvId.value = val;
		document.frm1.submit();
	}
	function updateRsv(val) {
		document.frm2.rsvId.value = val;
		document.frm2.submit();
	}
//-->
</script>
<noscript>
	<meta http-equiv="refresh" content="0;URL=noscript.jsp"/>
</noscript>
</head>
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

<jsp:useBean id="reserveList" type="java.util.ArrayList" scope="request" />
<%
	String msg = "";
	if (request.getAttribute("msg") != null) {
		msg = "<div class=\"message\" align=\"center\">" + (String) request.getAttribute("msg") + "</div>";
	}
%>
<div id="wrapper">
	<section class="content">
	<article class="post">

	<!-- コンテンツ -->
	<section id="main">
	<h1 align="center"><jsp:getProperty name="userInfo" property="usr_name"/>様ご予約一覧</h1>
	<%=msg%>
	<div align="center" class="reserveList">
		<table>
			<tr>
				<th rowspan="4">No.</th>
				<th>ご予約日時</th>
				<th>テーブル名</th>
				<th>貸切</th>
				<th rowspan="2">登録日時</th>
			</tr>
			<tr>
				<th>コース名</th>
				<th>人数</th>
				<th>連絡方法</th>
			</tr>
			<tr>
				<th colspan="4">ご要望</th>
			</tr>
			<tr>
				<th colspan="4">　</th>
			</tr>
			<%
				for (int i = 0; i < reserveList.size(); i++) {
					Reserve rs = (Reserve) reserveList.get(i);
					String chMark = rs.getCharter() == 1 ? "〇" : "－";
					String tel = rs.getTel() == 1 ? "電話" : "‐";
					String mail = rs.getMail() == 1 ? "メール" : "‐";
			%>
			<tr>
				<th rowspan="4"><%=rs.getRsvId()%></th>
				<td><%=rs.getRsvYy() + "-" + rs.getRsvMm() + "-" + rs.getRsvDd()
						+ " " + rs.getRsvHh() + ":"	+ String.format("%02d", rs.getRsvMi())%></td>
				<td><%=rs.getTableName()%></td>
				<td><%=chMark%></td>
				<td rowspan="2"><%=rs.getAppDate().substring(0, 16)%></td>
			</tr>
			<tr>
				<td><%=rs.getCourseName()%></td>
				<td><%=rs.getPerson()%>名</td>
				<td><%=tel%>／<%=mail%></td>
			</tr>
			<tr>
				<td colspan="4"><%=rs.getExp()%>　</td>
			</tr>
			<tr>
				<td colspan="4" class="submitLeft">
					<input type="submit" value="変更" onclick="updateRsv('<%=rs.getRsvId()%>')"></input>
					<input type="submit" value="取消" onclick="deleteRsv('<%=rs.getRsvId()%>')"></input>
				</td>
			</tr>
			<%
				}
			%>
		</table>
	</div>
	<form id="frm1" name="frm1" method="post" action="ReserveDeleteSvl">
		<input type="hidden" name="rsvId" />
	</form>
	<form id="frm2" name="frm2" method="post" action="ReserveUpdateSvl">
		<input type="hidden" name="rsvId" />
	</form>
	</section>
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