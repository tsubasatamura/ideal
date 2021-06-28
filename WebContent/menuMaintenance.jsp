<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import= "java.util.* ,java.text.*,model.*,controller.*"%>
<%@ include file="incFile.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<!-- メタ -->
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, user-scalable=yes, maximum-scale=1.0, minimum-scale=1.0">
<meta name="description" content="■Restaurante IDEALLE■">
<meta name="keywords" content="">
<!-- メタ終わり -->

<title>Menu Maintenance</title>
<!-- / CSSリンク（アドミン側） -->
<link rel="stylesheet" href="css/style.css" type="text/css" media="screen">

<link rel="stylesheet" href="css/menu.css" type="text/css" media="screen">

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
div#type3{width:100%;display:inline-block;margin:10px auto;max-width: 650px;}

div#type3 li a {
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
<div id="type3">
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
<jsp:useBean id="adminInfo" class="model.Admin" scope="session"/>

<h1 id="data">メニューマスターメンテナンス</h1>

<%
String[]order={"不可","可"};

NumberFormat cf=NumberFormat.getCurrencyInstance(new Locale("ja","JP"));int typeID;
String style="id='type1'";

try{
typeID=Integer.parseInt(request.getParameter("typeID"));
}catch (NumberFormatException e){
	typeID=100;
}
%>
<jsp:useBean id= "mType" class="java.util.ArrayList"  scope="request"/>
<jsp:useBean id= "menu" class="java.util.ArrayList"  scope="request"/>
<div class="div1">
<table>
<tr>
<td id="outer1">
<%
for(int i=0 ; i < mType.size() ; i++){
	MenuType mt = (MenuType)mType.get(i);
	if(typeID==mt.getTypeID()){
		style= "id='type2n'";
	}else{
		style= "id='type1n'";
	}

%>
<div class="menumainte"  >
<form name="frm<%=i %>" action="MenuMaintenanceSvl" method="post">
<input type="hidden" name="mode" value="0"/>
<input type="hidden" name="typeID" value="<%= mt.getTypeID() %>"/>
<div <%= style %> onclick="document.frm<%= i %>.submit();"><%= mt.getTypeName() %>
</div>
</form>
</div>
<% } %>
</tr>
<tr>
<td id="outer4">
<table>
<caption>&lt;&lt;&lt;
<% try{ %>
<%=((Menu)menu.get(0)).getTypeName() %>
<% }catch(Exception e){ %>
メニューがありません。
<% } %>
&gt;&gt;&gt;</caption>
<tr>
<th id= "coden">ID</th>
<th id= "menun">メニュー</th>
<th id= "pricen">価格</th>
<th id= "commn">コメント</th>
<th id= "ordn" >オーダー可</th>

</tr>

<%
	for(int i=0 ; i < menu.size() ; i++){
	Menu m=(Menu)menu.get(i);

%>

<!-- <tr>
<th id= "coden">ID</th>
<th id= "menun">メニュー</th>
<th id= "pricen">価格</th>
<th id= "commn">コメント</th>
<th id= "ordn" >オーダー可</th>

</tr> -->

	<tr id="data<%= i % 2 %>" >
		<td id="coden" rowspan="3"><%= m.getMenuId()%></td>
		<td id="menu" rowspan="3"><%= m.getMenuName()%></td>
		<td id="pricen" rowspan="3"><%= cf.format(m.getPrice())%></td>
		<td id="comm"rowspan="3"><%= fmtNull(m.getDetail())%></td>

		<td id="ord"  ><div align="center"><%= order[m.getOrderFlg()]%></div></td>

<tr>
	<td>	<form action= "MenuUpdateSvl" method= "post">

			<input type= "submit" value="更新"
 			onclick="this.form.mode.value= '<%= MenuOperationSvl.UPDATE %>'"/>

 		<input type="hidden" name="mode"/>
		<input type="hidden" name="menuID" value="<%= m.getMenuId() %>"/>
		<input type="hidden" name="typeID" value="<%= typeID %>"/>
 		</form>
 		</td>
</tr>
<tr>
<td>
		<form action= "MenuDeleteSvl" method= "post" >
			<input type= "submit" value="削除"
 			onclick="this.form.mode.value= '<%= MenuOperationSvl.DELETE %>'"/>

		<input type="hidden" name="mode"/>
		<input type="hidden" name="menuID" value="<%= m.getMenuId() %>"/>
		<input type="hidden" name="typeID" value="<%= typeID %>"/>
		</form>
		</td>
</tr>
</td>
	</tr>
	<% } %>

	<tr class="td1">
		<form action ="MenuInsertSvl" method="post">
		<th colspan="7">

			<input type="hidden"  name="typeID" value="<%= typeID %>"/>

			<input  type="hidden"  name="mode" value="<%= MenuOperationSvl.INSERT %>"/>
			<p id="center"><input id="newmenu" type="submit"  value="新しいメニューの追加"/></p>

		</th>
		</form>
	</tr>

</table><!-- インナーテーブルの終了 -->
</td>
</td>

</tr>
<tr>
</tr>
<tr>
<td id="outer2">
<%
for(int i=0 ; i < mType.size() ; i++){
	MenuType mt = (MenuType)mType.get(i);
	if(typeID==mt.getTypeID()){
		style= "id='type2'";
	}else{
		style= "id='type1'";
	}

%>
<div class="menumainte"  >

<input type="hidden" name="mode" value="0"/>
<input type="hidden" name="typeID" value="<%= mt.getTypeID() %>"/>
<div <%= style %> onclick="document.frm<%= i %>.submit();"><%= mt.getTypeName() %>
</div>

</div>
<% } %>
<td id="outer2">
<table>
<caption>&lt;&lt;&lt;
<% try{ %>
<%=((Menu)menu.get(0)).getTypeName() %>
<% }catch(Exception e){ %>
メニューがありません。
<% } %>
&gt;&gt;&gt;</caption>

<tr>
<th id= "code">ID</th>
<th id= "menu">メニュー</th>
<th id= "price">価格</th>
<th id= "comm">コメント</th>
<th id= "ord">オーダー可</th>
<th id= "btn"colspan="2"></th>
</tr>

<%
	for(int i=0 ; i < menu.size() ; i++){
	Menu m=(Menu)menu.get(i);

%>

	<tr id="data<%= i % 2 %>" >
		<td id="code"><%= m.getMenuId()%></td>
		<td id="menu"><%= m.getMenuName()%></td>
		<td id="price"><%= cf.format(m.getPrice())%></td>
		<td id="comm"><%= fmtNull(m.getDetail())%></td>
		<td id="ord"  ><div align="center"><%= order[m.getOrderFlg()]%></div></td>
		<form action= "MenuUpdateSvl" method= "post">
		<td>
			<input type= "submit" value="更新"
 			onclick="this.form.mode.value= '<%= MenuOperationSvl.UPDATE %>'"/>
 		</td>
 		<input type="hidden" name="mode"/>
		<input type="hidden" name="menuID" value="<%= m.getMenuId() %>"/>
		<input type="hidden" name="typeID" value="<%= typeID %>"/>
 		</form>
		<td>

		<form action= "MenuDeleteSvl" method= "post" >
			<input type= "submit" value="削除"
 			onclick="this.form.mode.value= '<%= MenuOperationSvl.DELETE %>'"/>
		</td>
		<input type="hidden" name="mode"/>
		<input type="hidden" name="menuID" value="<%= m.getMenuId() %>"/>
		<input type="hidden" name="typeID" value="<%= typeID %>"/>
		</form>

	</tr>
	<% } %>

	<tr class="td1">
		<form action ="MenuInsertSvl" method="post">
		<th colspan="7">

			<input type="hidden"  name="typeID" value="<%= typeID %>"/>

			<input  type="hidden"  name="mode" value="<%= MenuOperationSvl.INSERT %>"/>
			<p id="center"><input id="newmenu" type="submit"  value="新しいメニューの追加"/></p>

		</th>
		</form>
	</tr>

</table><!-- インナーテーブルの終了 -->
</td>
</tr>
</table>
</div>
</div>
</div>
</div>
</article>
</section>
</div>
</body>
</html>