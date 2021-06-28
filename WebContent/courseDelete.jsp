<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*,java.text.*,controller.*,model.*"%>
<%@ include file="incFile.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<!-- メタ -->
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, user-scalable=yes, maximum-scale=1.0, minimum-scale=1.0">
<meta name="description" content="■Restaurante IDEALLE■">
<meta name="keywords" content="">
<!-- メタ終わり -->

<title>Course Delete</title>

<!-- / CSSリンク（アドミン側） -->
<link rel="stylesheet" href="css/style.css" type="text/css" media="screen">
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

<%
request.setCharacterEncoding("UTF-8");
%>
<jsp:useBean id="msg" class="model.IdealException" scope="request"/>
<jsp:useBean id="typeMemuList" type="java.util.List<model.MenuType>" class="java.util.ArrayList" scope="request"/>
<jsp:useBean id="oneCourse" type="java.util.List<model.Course>" class="java.util.ArrayList" scope="request"/>
<%
NumberFormat cf=NumberFormat.getCurrencyInstance(new Locale("ja","JP"));
int typeID=100;
int menuID=oneCourse.get(0).getMenuId();
int courseID=oneCourse.get(0).getCourseId();
%>

<div id="wrapper">
		<article class="post">
			<section class="content">
<table border>
<h3>コースの削除</h3>
<% if(msg.getErrmsg()!=null){%>
	<p><jsp:getProperty name="msg" property="errmsg"/></p>
<% } %>
<form action="CourseOperationSvl" method="post">
<tr>
<th>コース名</th>
<td>
<%= oneCourse.get(0).getCourseName() %>
</td>
</tr>
<tr>
<th>価格</th>
<td>
<%= cf.format(oneCourse.get(0).getPrice())%>
</td>
</tr>
<tr>
<th>オーダー可</th>
<td>
<% if(oneCourse.get(0).getOrderFlg()==1){%>
オーダー可
<% }else{%>
オーダー不可
<% }%>
</td>
</tr>
<tr>
<th>コメント</th>
<td>
<%= fmtNull(oneCourse.get(0).getDetail())%>
</td>
</tr>
<tr>
<th>前菜</th>
<td>
<%for(int i=0;i<oneCourse.size();i++){
if(oneCourse.get(i).getTypeId()==200){%>
<%=oneCourse.get(i).getMenuName() %>
<%}else{ %>
<%} %>
<%} %>
</td>
</tr>
<tr>
<th>スープ</th>
<td>
<%for(int i=0;i<oneCourse.size();i++){
if(oneCourse.get(i).getTypeId()==210){%>
<%=oneCourse.get(i).getMenuName() %>
<%}else{ %>
<%} %>
<%} %>
</td>
</tr>
<tr>
<th>パスタ</th>
<td>
<%for(int i=0;i<oneCourse.size();i++){
if(oneCourse.get(i).getTypeId()==220){%>
<%=oneCourse.get(i).getMenuName() %>
<%}else{ %>
<%} %>
<%} %>
</td>
</tr>
<tr>
<th>肉料理</th>
<td>
<%for(int i=0;i<oneCourse.size();i++){
if(oneCourse.get(i).getTypeId()==300){%>
<%=oneCourse.get(i).getMenuName() %>
<%}else{ %>
<%} %>
<%} %>
</td>
</tr>
<tr>
<th>魚料理</th>
<td>
<%for(int i=0;i<oneCourse.size();i++){
if(oneCourse.get(i).getTypeId()==310){%>
<%=oneCourse.get(i).getMenuName() %>
<%}else{ %>
<%} %>
<%} %>
</td>
</tr>
<tr>
<th>デザート</th>
<td>
<%for(int i=0;i<oneCourse.size();i++){
if(oneCourse.get(i).getTypeId()==400){%>
<%=oneCourse.get(i).getMenuName() %>
<%}else{ %>
<%} %>
<%} %>
</td>
</tr>

<input type="hidden" name="mode" value="13"/>
<input type="hidden" name="menuID" value='<%=menuID %>' />
<input type="hidden" name="typeID" value='<%=typeID %>' />
<input type="hidden" name="courseID" value='<%=courseID %>' />
<tr>
<th colspan="2" id="bottom"><input type="submit" value="コースを削除"/>
</th>
</tr>
</form>
</table>
<p><a href="MenuMaintenanceSvl?typeID=<%=100%>">一覧表示へ戻る</a></p>
</section>
</article>
</div>
</body>
</html>