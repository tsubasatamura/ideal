<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*,controller.*,model.*"%>
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

<title>Menu Update</title>

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
<script type="text/javascript">
<!--
function dataCheck(obj){
	var msg="";
	if(obj.menuName.value.length<1){
		msg+="メニュー名を入力してください。\n";
	}
	if(!obj.price.value.match(/^[0-9]+$/g)){
		msg+="価格を数値で入力してください。\n";
	}
	var i;
	for(i =0;i<obj.orderFlg.length;i++){
		if(obj.orderFlg[i].checked)break;
	}
	if(i>=obj.orderFlg.length){
		msg+="オーダーの可否をチェックしてください。\n";
	}
	if(msg.length>0){
		alert(msg);
		return false;
	}else{
		return true;
	}
}
//-->
</script>
<noscript>
	<meta http-equiv="refresh" content="0;URL=noscript.jsp"/>
</noscript>
</head>
<script type="text/javascript">
<!--
function dataCheck(obj){
	var msg="";
	if(obj.menuName.value.length<1){
		msg+="メニュー名を入力してください。\n";
	}
	if(!obj.price.value.match(/^[0-9]+$/g)){
		msg+="価格を数値で入力してください。\n";
	}
	var i;
	for(i =0;i<obj.orderFlg.length;i++){
		if(obj.orderFlg[i].checked)break;
	}
	if(i>=obj.orderFlg.length){
		msg+="オーダーの可否をチェックしてください。\n";
	}
	if(msg.length>0){
		alert(msg);
		return false;
	}else{
		return true;
	}
}
//-->
</script>
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

 int typeID;
 try{
	typeID=Integer.parseInt(request.getParameter("typeID"));
	}catch(NumberFormatException e){
		typeID=200;
	}
%>

<jsp:useBean id="oneMenu"  class="model.Menu" scope="request"/>
<jsp:useBean id="mType"  class="java.util.ArrayList" scope="request"/>

<div id="wrapper">
		<article class="post">
			<section class="content">
<table border>
 <h3>メニューの更新</h3>
 <form action="MenuOperationSvl" method="post" onsubmit="return dataCheck(this);" enctype="multipart/form-data">
 <tr>
 <th>メニュー名</th>
 <td>
 <input type="text" name="menuName" size="30" value='<jsp:getProperty name="oneMenu" property="menuName"/>' />
 </td>
 </tr>
<tr>
<th>価格</th>
<td>
<input type="text" name="price" size="10"
value='<jsp:getProperty name="oneMenu" property="price"/>'/>
</td>
</tr>
<tr>
<th>オーダー可</th>
<td>
<% if(oneMenu.getOrderFlg()==1){%>
<input type="radio" name="orderFlg" value="1" checked/>可
<input type="radio" name="orderFlg" value="0"/>不可
<% }else{%>
<input type="radio" name="orderFlg" value="1"/>可
<input type="radio" name="orderFlg" value="0" checked/>不可
<% }%>
</td>
</tr>
<tr>
<th>分類</th>
<td>
<select name="typeID">
<%
for(Object o:mType){
	MenuType mt=(MenuType)o;

	String selected="";
	if(typeID==mt.getTypeID()){
		selected="selected";
	}else{
		selected="";
	}
%>
<%if(((MenuType)o).getTypeID()!=100){ %>
	<option value="<%= mt.getTypeID()%>" <%= selected %>>
	<%= ((MenuType)o).getTypeName() %></option>
<% } %>
<% } %>
</select>
</td>
</tr>
<tr>
<th>画像</th>
<%if(oneMenu.getMenuPhotoName()==null){ %>
<td><input type="file" name="menuPhotoName" accept="image/*" /></td>
<%}else{ %>
<td><input type="file" name="menuPhotoName" accept="image/*" /><img width="150" src="upload/<%= oneMenu.getMenuPhotoName() %>" /></td>
<%} %>
</tr>
<tr>
<th>コメント</th>
<td>
<textarea name="detail" cols="30" rows="4"><%= fmtNull(oneMenu.getDetail()) %></textarea>
</td>
</tr>
<input type="hidden" name="mode" value="<%= MenuOperationSvl.UPDATE %>" />
<input type="hidden" name="menuID" value='<jsp:getProperty name="oneMenu" property="menuId"/>' />
<input type="hidden" name="typeID" value='<jsp:getProperty name="oneMenu" property="typeId"/>' />

<tr>
<th colspan="2" id="bottom"><input type="submit" value="メニューを更新"/></th>
</tr>
</form>
</table>
<p><a href="MenuMaintenanceSvl?typeID=<%= typeID %>">一覧表示へ戻る</a></p>
</section>
</article>
</div>
</body>
</html>