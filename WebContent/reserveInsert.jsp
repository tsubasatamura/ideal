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

<title>ユーザー新規予約</title>

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

<link rel="stylesheet" type="text/css" media="all" href="css/reserve.css"/>
<script type="text/javascript">
<!--
	function check() {
		if (!$("input:radio[name='rsvHhMi']:checked").val()) {
			window.alert("時間が設定されていません");
			return false;
		} else if ($('#cusfrm').find('input[name="tel"]:checked').val() === undefined
				&& $('#cusfrm').find('input[name="mail"]:checked').val() === undefined) {
			window.alert("連絡方法を必ず一つ選択してください");
			return false;
		}
	}

	$(function() {

		$('#rsv_day,#rsv_person').change(
				function() {
					var person = $('#cusfrm').find(
							'select[name="person"] option:selected').val();
					var rsvYyMmDd = $('#cusfrm')
							.find('input[name="rsvYyMmDd"]').val();
					var courseId = $('#cusfrm').find(
							'select[name="courseId"] option:selected').val();
					$('#frm01').children('input:hidden[name="person"]').val(
							person);
					$('#frm01').children('input:hidden[name="rsvYyMmDd"]').val(
							rsvYyMmDd);
					$('#frm01').children('input:hidden[name="courseId"]').val(
							courseId);
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

<jsp:useBean id="reserve"  class="model.Reserve" scope="request" />
<jsp:useBean id="courseList" class="java.util.ArrayList" scope="request" />
<jsp:useBean id="reservableList" class="java.util.ArrayList" scope="request" />
<%
	String dateYyMmDd = String.format("%02d", reserve.getRsvYy()) + "-" + String.format("%02d", reserve.getRsvMm()) + "-" + String.format("%02d", reserve.getRsvDd());
	int charter = reserve.getCharter();
	String title = charter == 1 ? "【貸切予約】" : "【個人予約】";
	String msg = "";
	if (request.getAttribute("msg") != null) {
		msg = "<div class=\"message\" align=\"center\">" + (String) request.getAttribute("msg") + "</div>";
	}
%>
<div id="wrapper">
	<article class="post">
		<section class="content">
			<h1 align="center"><jsp:getProperty name="userInfo" property="usr_name" />様<%=title%></h1>
			<%=msg%>
			<div align="center" class="reserveInsert">
				<form id="frm01" name="crform" method="post" action="ReserveInsertSvl">
					<input type="hidden" name="person" value="" />
					<input type="hidden" name="rsvYyMmDd" value="" />
					<input type="hidden" name="courseId" value="" />
					<input type="hidden" name="charter" value="<%=charter%>" />
				</form>
				<form id="cusfrm" name="cusfrm" method="post" action="ReserveOperationSvl" onsubmit="return check();">
					<input id="rsv_charter" type="hidden" name="charter" value="<%=charter%>" />
					<label>人数</label>
					<div>
						<select id="rsv_person" name="person">
							<%
								String checked = "";
								int max = charter != 1 ? 6 : 30;
								for (int i = 2; i <= max; i++) {
									checked = i == reserve.getPerson() ? "selected" : "";
							%>
							<option value="<%=i%>" <%=checked%>><%=i%>名
							</option>
							<%
								}
							%>
						</select>
					</div>
					<label>日にち</label>
					<div>
						<input id="rsv_day" name="rsvYyMmDd" type="date" value="<%=dateYyMmDd%>"
							min="<%=request.getAttribute("minYyMmDd")%>"
							max="<%=request.getAttribute("maxYyMmDd")%>" />
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
						<label>時間<br />(ご予約可能時間帯 17:00～21:00)</label>
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
									int mm = 0;
									String mark;
									String disabled;
									for (int i = 0; i < reservableList.size(); i++) {
										if (mm == 60) {
											mm = 0;
											hh = hh + 1;
										}
										num = (Integer) reservableList.get(i);
										mark = "◎";
										disabled = "";
										if (num == 0) {
											disabled = "disabled";
											mark = "×";
										}
								%>
								<td>
									<input type="radio" name="rsvHhMi" id="rsvHhMi_<%=i%>" value="<%=hh%>：<%=String.format("%02d", mm)%>" <%=disabled%> />
									<label style="cursor: hand; cursor: pointer;" for="rsvHhMi_<%=i%>"><%=mark%></label>
								</td>
								<%
									mm = mm + 15;
									}
								%>
							</tr>
						</table>
					</div>
					<label>連絡方法<br />(どちらかは必ず選択してください。)</label>
					<div>
						<input type="checkbox" name="tel" value="1" />電話 <input type="checkbox"
							name="mail" value="1" />メール
					</div>
					<label>ご要望</label>
					<div>
						<textarea name="exp" cols="80" rows="5"></textarea>
					</div>
					<div>
						<input type="hidden" name="mode" value="<%=ReserveOperationSvl.INSERT%>" />
						<input type="submit" class="btn" value="予約" />
					</div>
				</form>
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