<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<!-- メタ -->
<meta name="viewport" content="width=device-width, user-scalable=yes, maximum-scale=1.0, minimum-scale=1.0"/>
<meta name="description" content="■Restaurante IDEALLE■"/>
<meta name="keywords" content=""/>
<!-- メタ終わり -->
<title>顧客情報変更画面</title>
<meta name="viewport" content="width=device-width, user-scalable=yes, maximum-scale=1.0, minimum-scale=1.0"/>
<link rel="stylesheet" type="text/css" media="all" href="css/tamura.css"/>
<link rel="stylesheet" type="text/css" media="all" href="css/style.css"/>
<link rel="stylesheet" type="text/css" media="all" href="css/tamurauser.css"/>
<!-- スクリプト・JS -->
<!--[if lt IE 9]>
<script src="js/html5.js"></script>
<script src="js/css3-mediaqueries.js"></script>
<![endif]-->
<script src="js/jquery1.4.4.min.js"></script>
<script src="js/script.js"></script>
<!-- スクリプト・JS -->
<script src="https://yubinbango.github.io/yubinbango/yubinbango.js" charset="UTF-8"></script>
<script type="text/javascript">
<!--
	function check(){
		if(document.cusfrm.phone.value==""){
			window.alert("電話番号が未入力です。");
			return false;
		}else if(document.cusfrm.mail.value==""){
				window.alert("メールアドレスが未入力です。");
				return false;
		}else if(!document.cusfrm.phone.value.match(/^[0-9-]*$/)){
			window.alert("電話番号は半角数字を入力ください。");
			return false;
		}else if(!document.cusfrm.mail.value.match(/^[A-Za-z0-9]{1}[A-Za-z0-9_.-]*@{1}[A-Za-z0-9_.-]{1,}\.[A-Za-z0-9]{1,}$/)){
			window.alert("半角を用い、正しいメールアドレスを入力ください。");
			return false;
		}else{
			if(window.confirm("これでよろしいですか？")){
				return true;
			}else{
				return false;
			}
		}
	}
//-->
</script>

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
<noscript>
	<meta http-equiv="refresh" content="0;URL=noscript.jsp"/>
</noscript>
</head>
<body>
<jsp:useBean id="userInfo" class="model.User" scope="session"/>
<%
String userName=userInfo.getUsr_name();
System.out.println(userName);
if(userName==null){%>
<!-- メニューバー -->
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
					<li class="active"><a href="ShowMenuSvl">メニュー紹介<br><span>Menu</span></a></li>
					<li><a href="ReservationStatusSvl">予約状況<br><span>Reserve</span></a></li>
					<li><a href="newsDisplay.jsp">お知らせ<br><span>News</span></a></li>
					<li><a href="UserSvl">ログイン<br><span>Login</span></a></li>
				</ul>
      </div>
    </nav>
		<!-- メインナビゲーション -->

  </div>
</div>
<% }else{%>
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
					<li  class="active"><a href="UserSvl"><jsp:getProperty name="userInfo" property="usr_name"/>様マイページ<br><span>MyPage</span></a></li>
					<li><a href="UserLogoffSvl">ログアウト<br><span>Logout</span></a></li>
				</ul>
      </div>
    </nav>
		<!-- メインナビゲーション -->

  </div>
</div>
<%} %>
<!-- / メニューバー終わり（ホーム） -->
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
<!-- / ユーザーメニューバー始まり-->
<div id="wrapper">
<article class="post">
<section class="content">
<h1 align="center">お客様情報変更</h1>
<jsp:useBean id="msg" class="model.IdealException" scope="request"/>
<% if(msg.getErrmsg()!=null){%>
		<p><jsp:getProperty name="msg" property="errmsg"/></p>
<% } %>
<div class="h-adr">
<form id="cusfrm" name="cusfrm" action="UserOperationSvl" method="post"
				onsubmit="return check();">
<span class="p-country-name" style="display:none;">Japan</span>
				<table id="usertable">
					<tr>
						<td id="index">お名前</td>
						<td>
							<jsp:getProperty name="userInfo" property="usr_name"/>
						</td>
					</tr>
					<tr>
						<td id="index">郵便番号</td>
						<td >
							<input type="text" class="p-postal-code" name="postcode" value="<jsp:getProperty name="userInfo" property="postcode"/>" />
						</td>
					</tr>
					<tr>
						<td id="index">住所</td>
						<td >
							<input id ="address" type="text" class="p-region p-locality p-street-address p-extended-address" name="address" value="<jsp:getProperty name="userInfo" property="address"/>"/>
						</td>
					</tr>
					<tr>
						<td id="index">電話番号※</td>
						<td>
							<input type="text" name="phone" value="<jsp:getProperty name="userInfo" property="phone"/>"/>
						</td>
					</tr>
					<tr>
						<td id="index">e-mail※</td>
						<td>
							<input type="text" name="mail" value="<jsp:getProperty name="userInfo" property="mail"/>"/>
						</td>
					</tr>
					<tr>
						<td id="index">パスワード</td>
						<td>
							<input type="password" name="newpassword" />
							※変更時のみ入力してください。
						</td>
					</tr>
					<tr>
						<td colspan="2" class="submit">
							※は必修入力です。<input type="submit" value="変更" />
						</td>
					</tr>
				</table>
				<input type="hidden" name="mode" value="12"/>
				</form>
				</div>
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
  				<a href="home.jsp">■Restaurante IDEALLE■<br/><span>Italian Restaurante</span></a>
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