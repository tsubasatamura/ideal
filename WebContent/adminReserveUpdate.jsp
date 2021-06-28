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

	function check() {
		if (!$("input:radio[name='rsvHhMi']:checked").val()) {
			window.alert("時間が設定されていません");
			return false;
		}else if($('#cusfrm').find('input[name="tel"]:checked').val() === undefined
				&& $('#cusfrm').find('input[name="mail"]:checked').val() === undefined ){
			window.alert("連絡方法を必ず一つ選択してください");
			return false;
		}
	}

	$(function() {

		$('#rsv_day,#rsv_person,#rsv_charter').change(
				function() {
					var person = $('#cusfrm').find('select[name="person"] option:selected').val();
					var rsvYyMmDd = $('#cusfrm').find('input[name="rsvYyMmDd"]').val();
					var courseId = $('#cusfrm').find('select[name="courseId"] option:selected').val();
					var charter = $('#cusfrm').find('input[name="charter"]:checked').val();

					if (charter === undefined) {
						charter = 0;
						if(person > 6){
							person = 2;
						}
					}

					// alert(person + "/" + rsvYyMmDd + "/" + courseId + "/" + charter);
					$('#frm01').children('input:hidden[name="person"]').val(person);
					$('#frm01').children('input:hidden[name="rsvYyMmDd"]').val(rsvYyMmDd);
					$('#frm01').children('input:hidden[name="courseId"]').val(courseId);
					$('#frm01').children('input:hidden[name="charter"]').val(charter);
					$('#frm01').submit();
				});
	});
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
	<jsp:useBean id="courseList" class="java.util.ArrayList" scope="request" />
	<jsp:useBean id="reservableList" class="java.util.ArrayList" scope="request" />
	<%
		String dateYyMmDd = String.format("%02d", reserve.getRsvYy()) + "-" + String.format("%02d", reserve.getRsvMm()) + "-" + String.format("%02d", reserve.getRsvDd());
		String msg = "";
		if (request.getAttribute("msg") != null) {
			msg = "<div class=\"message\" align=\"center\">" + (String) request.getAttribute("msg") + "</div>";
		}
	%>
	<div id="wrapper">
		<article class="post">
			<section class="content">
	<h1 align="center">予約変更</h1>
	<%=msg%>
	<div align="center" class="reserveInsert">
		<form id="frm01" name="crform" method="post" action="AdminReserveUpdateSvl">
			<input type="hidden" name="person" value="" />
			<input type="hidden" name="rsvYyMmDd" value="" />
			<input type="hidden" name="courseId" value="" />
			<input type="hidden" name="charter" value="" />
			<input type="hidden" name="rsvId" value="<%=reserve.getRsvId()%>" />
		</form>
			<label>予約番号</label>
			<div><%=reserve.getRsvId()%></div>
			<label>予約者名</label>
			<div><%=reserve.getUsrName()%>様</div>
		<form id="cusfrm" name="cusfrm" method="post" action="AdminReserveOperationSvl" onsubmit="return check();">
			<label>人数</label>
			<div>
				<select id="rsv_person" name="person">
					<%
						String checked = "";
						int max = reserve.getCharter() != 1 ? 6 : 30;
						for (int i = 2; i <= max; i++) {
							checked = i == reserve.getPerson() ? "selected" : "";
					%>
					<option value="<%=i%>" <%=checked%>><%=i%>名
					</option>
					<%
						}
					%>
				</select>
				<div>
					<%
						checked = reserve.getCharter() == 1 ? "checked" : "";
					%>
					<input id="rsv_charter" type="checkbox" name="charter" value="1" <%=checked%> />貸切する
				</div>
			</div>
			<label>日にち</label>
			<div>
				<input id="rsv_day" name="rsvYyMmDd" type="date" value="<%=dateYyMmDd%>" min="<%=request.getAttribute("minYyMmDd")%>" max="<%=request.getAttribute("maxYyMmDd")%>"/>
				<%
					msg = "";
					if (request.getAttribute("holidayMsg") != null) {
						msg = "<p style=\"color:red\" >" + (String) request.getAttribute("holidayMsg") + "</p>";
					}
				%>
				<%=msg%>
			</div>
			<label>コース(3時間コースのみ)</label>
			<div>
				<select name="courseId">
					<%
						checked = "";
						for (int i = 0; i < courseList.size(); i++) {
							Course cs = (Course) courseList.get(i);
							checked = cs.getCourseId() == reserve.getCourseId() ? "selected" : "";
					%>
					<option value="<%=cs.getCourseId()%>" <%=checked%>><%=cs.getCourseName()%></option>
					<%
						}
					%>
				</select>
			</div>
			<label>時間(ご予約可能時間帯 17:00～21:00)</label>
			<div class="scroll-table">
			<table class="checkTime">
				<tr>
					<%
						for (int i = 17 * 60; i < 21 * 60 + 15; i += 15) {
					%>
					<td><p class="dateTime"><%=String.format("%02d", i / 60)%>:<%=String.format("%02d", i % 60)%></p></td>
					<%
						}
					%>
				</tr>
				<tr>
					<%
						int num = 0;
						int hh = 17;
						int mi = 0;
						String mark;
						String disabled;
						for (int i = 0; i < reservableList.size(); i++) {
							if (mi == 60) {
								mi = 0;
								hh = hh + 1;
							}
							num = (Integer) reservableList.get(i);
							mark = "◎";
							disabled = "";
							if (num == 0) {
								disabled = "disabled";
								mark = "×";
							}
							checked = "";
							if (hh==reserve.getRsvHh() && mi==reserve.getRsvMi() && num != 0){
								checked = "checked";
							}
					%>
					<td>
						<input type="radio" name="rsvHhMi" id="rsvHhMi_<%=i%>" value="<%=hh%>：<%=String.format("%02d", mi)%>" <%=disabled%> <%=checked%>/>
						<label style="cursor: hand; cursor: pointer;" for="rsvHhMi_<%=i%>"><%=mark%></label>
					</td>
					<%
						mi = mi + 15;
						}
					%>
				</tr>
			</table>
			</div>
			<label>連絡方法</label>
			<div>
				<% checked = reserve.getTel() == 1 ? "checked" : ""; %>
				<input type="checkbox" name="tel" value="1" <%=checked%>/>電話
				<% checked = reserve.getMail() == 1 ? "checked" : ""; %>
				<input type="checkbox" name="mail" value="1" <%=checked%>/>メール
			</div>
			<label>ご要望</label>
			<div>
				<% String exp = reserve.getExp().replace("<br/>", System.lineSeparator()); %>
				<textarea name="exp" cols="80" rows="5"><%=exp%></textarea>
			</div>
			<div>
				<input type="hidden" name="rsvId" value="<%=reserve.getRsvId()%>" />
				<input type="hidden" name="mode" value="<%=AdminReserveOperationSvl.UPDATE%>" />
				<input type="submit" class="btn" value="変更" />
			</div>
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