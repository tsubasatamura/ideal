<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.sql.*" %>
<%@ page import="javax.sql.*" %>
<%@ page import="javax.naming.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<!-- メタ -->
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, user-scalable=yes, maximum-scale=1.0, minimum-scale=1.0">
<meta name="description" content="■Restaurante IDEALLE■">
<meta name="keywords" content="">
<!-- メタ終わり -->
<title>お知らせ管理</title>
<link rel="stylesheet" href="css/style.css" type="text/css" media="screen">
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
function frmCheck(){
	window.confirm("削除してもよろしいですか？");
	return true;
	}
//-->
</script>
<noscript>
	<meta http-equiv="refresh" content="0;URL=noscript.jsp"/>
</noscript>
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

<div id="wrapper">
<section class="content">
<article class="post">
<h1>お知らせ管理</h1>
<p><a href="newsInsert.jsp" >新規投稿</a></p>
<%
request.setCharacterEncoding("utf-8");

InitialContext ic=new InitialContext();
DataSource ds=(DataSource)ic.lookup("java:comp/env/mysql");
Connection con=ds.getConnection();

Statement st=con.createStatement();
String sql="SELECT * FROM news ORDER BY n_date DESC";
st=con.createStatement();
ResultSet rs=st.executeQuery(sql);

while(rs.next()){
%>
<form id="frm1" name="frm1" method="post" action="NewsSvl" onsubmit="return frmCheck()">
<table>
<tr><td bgcolor="#866326"><font color="#fff">タイトル:<%=rs.getString("title")%></font></td>
<td width="35%">投稿日時：<%=rs.getString("n_date").substring(0, 16)%></td></tr>
<tr><td align="left"><%=rs.getString("n_message")%></td>
<td><button name="id" value="<%=rs.getInt("n_id")%>">削除</button></td></tr>
</table>
<%}
	ic.close();
	con.close();
	rs.close();
%>
<br />
<input type="hidden" name="mode" value="16"/>
</form>
<td colspan="2" id="button"><a href="adminIndex.jsp"> 戻る</a></td>
</article>
</section>
</div>
</body>
</html>