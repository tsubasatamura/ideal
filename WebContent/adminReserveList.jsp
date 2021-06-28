<?xml version="1.0" encoding="UTF-8" ?>
<%@page import="controller.ReserveOperationSvl"%>
<%@page import="java.util.*"%>
<%@page import="model.*"%>
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
<title>管理者予約情報一覧</title>

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
	$(function() {
		$('#rsv_day').change(function() {
			$('#cusfrm').submit();
		});
	});

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
<jsp:useBean id="adminInfo" class="model.Admin" scope="session" />
<jsp:useBean id="reserveList" type="java.util.ArrayList" scope="request" />
<jsp:useBean id="reserveMap" type="java.util.HashMap" scope="request" />
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

<a name="top"></a>
	<div id="wrapper">
		<section class="content">
			<article class="post">
				<h1 align="center">予約管理</h1>
				<div class="message" align="center">
					<%
						if (request.getAttribute("msg") != null) {
							request.getAttribute("msg");
						}
					%>
				</div>
				<div class="reservationStatus">
					<div>
						<form id="cusfrm" name="cusfrm" method="post" action="AdminReserveListSvl">
							<label>確認したい日にちを選択： <input id="rsv_day" name="rsvYyMmDd" type="date"
								value="<%=request.getAttribute("rsvYyMmDd")%>"
								max="<%=request.getAttribute("maxYyMmDd")%>" />
							</label>
						</form>
					</div>
					<div>
						<h3><%=request.getAttribute("rsvYyMmDd")%>の予約状況</h3>
						<div class="scroll-table">
						<table border="1" cellspacing="0" cellpadding="0" class="checkTime">
							<tr>
								<td class="reservationTime">時</td>
								<%
									for (int i = 17; i < 24; i++) {
								%>
								<td colspan="4" class="reservationTime"><%=i%></td>
								<%
									}
								%>
							</tr>
							<tr>
								<td class="reservationTime">分</td>
								<%
									for (int i = 17 * 60; i < 24 * 60; i += 15) {
								%>
								<td class="reservationTime"><%=String.format("%02d", i % 60)%></td>
								<%
									}
								%>
							</tr>
							<tr>
								<%
									String start_end = "";
									String tdTag = "";
									int cnt = 0;
									Reserve rs = new Reserve();

									ArrayList<TableLoc> tbl = Reserve.getTableLoc();
									for (int i = 0; i < tbl.size(); i++) {
										String name = ((TableLoc) tbl.get(i)).getTableName();
										int tableId = ((TableLoc) tbl.get(i)).getTable_id();
								%>
								<th><%=name%></th>
								<%
									for (int j = 17 * 60; j < 24 * 60; j += 15) {
											tdTag = "<td></td>";
											int h = j / 60;
											int m = j % 60;
											String key = tableId + "/" + h + ":" + m;
											if (reserveMap.containsKey(key)) {
												String courseName = ((Reserve) reserveMap.get(key)).getCourseName();
												int person = ((Reserve) reserveMap.get(key)).getPerson();
												String charter = ((Reserve) reserveMap.get(key)).getCharter() == 1 ? "【貸切】" : "";
												if(((Reserve) reserveMap.get(key)).getCharter() == 1){
													tdTag = "<td colspan=\"12\" class=\"contents charter\"><a href=\"#rsv_" + ((Reserve) reserveMap.get(key)).getRsvId() + "\"><p>[" + ((Reserve) reserveMap.get(key)).getRsvId() + "]" + courseName + "(" + person + "名)【貸切】</p></a></td>";
												}else{
													tdTag = "<td colspan=\"12\" class=\"contents\"><a href=\"#rsv_" + ((Reserve) reserveMap.get(key)).getRsvId() + "\"><p>[" + ((Reserve) reserveMap.get(key)).getRsvId() + "]" + courseName + "(" + person + "名)</p></a></td>";
												}
												cnt = 1;
											} else if (0 < cnt && cnt < 11) {
												tdTag = "";
												cnt++;
											} else if (cnt == 11) {
												tdTag = "";
												cnt = 0;
											}
								%>
								<%=tdTag%>
								<%
									}
								%>
							</tr>
							<%
								}
							%>
						</table>
					</div>
					</div>
					<%
						for (int i = 0; i < reserveList.size(); i++) {
							rs = (Reserve) reserveList.get(i);
							String charter = rs.getCharter() == 1 ? "貸切" : rs.getTableName();
							String tel = rs.getTel() == 1 ? "電話" : "‐";
							String mail = rs.getMail() == 1 ? "メール" : "‐";
					%>
					<div class="reserveList">
					<p class="toTop" id="rsv_<%=rs.getRsvId()%>"><a href="#top">TOPへ戻る</a></p>
						<table border="1" align="center" width="60%">
							<tr>
								<th width="30%">予約番号</th>
								<td><%=rs.getRsvId()%></td>
							</tr>
							<tr>
								<th>予約者名</th>
								<td><%=rs.getUsrName()%> 様</td>
							</tr>
							<tr>
								<th>ご予約日時</th>
								<td><%=rs.getRsvYy() + "-" + rs.getRsvMm() + "-" + rs.getRsvDd() + " " + rs.getRsvHh() + ":" + String.format("%02d", rs.getRsvMi())%></td>
							</tr>
							<tr>
								<th>席名</th>
								<td><%=charter%></td>
							</tr>
							<tr>
								<th>最終更新日</th>
								<td><%=rs.getAppDate().substring(0, 16)%></td>
							</tr>
							<tr>
								<th>コース名</th>
								<td><%=rs.getCourseName()%></td>
							</tr>
							<tr>
								<th>人数</th>
								<td><%=rs.getPerson()%>名
								</td>
							</tr>
							<tr>
								<th>連絡方法</th>
								<td><%=tel%>／<%=mail%></td>
							</tr>
							<tr>
								<th>電話番号</th>
								<td><%=rs.getUsrPhone()%></td>
							</tr>
							<tr>
								<th>メールアドレス</th>
								<td><%=rs.getUsrMail()%></td>
							</tr>
							<tr>
								<th>ご要望</th>
								<td><%=rs.getExp()%></td>
							</tr>
							<tr>
								<td colspan="2" class="subumitTd">
									<input type="submit" value="変更"
										onclick="updateRsv('<%=rs.getRsvId()%>')"></input> <input
										type="submit" value="取消" onclick="deleteRsv('<%=rs.getRsvId()%>')"></input>
								</td>
							</tr>
						</table>
					</div>
					<%
						}
					%>
				</div>

				<form id="frm1" name="frm1" method="post" action="AdminReserveDeleteSvl">
					<input type="hidden" name="rsvId" />
				</form>
				<form id="frm2" name="frm2" method="post" action="AdminReserveUpdateSvl">
					<input type="hidden" name="rsvId" />
				</form>
			</article>
		</section>
	</div>
</body>
</html>