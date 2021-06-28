<?xml version="1.0" encoding="UTF-8" ?>
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
<title>お客様情報登録</title>
<!-- リンク -->
<link rel="stylesheet" href="css/style.css" type="text/css" media="screen"/>
<link rel="stylesheet" type="text/css" media="all" href="css/tamurauser.css"/>
<!-- リンク終わり -->

<!-- スクリプト・JS -->
<!--[if lt IE 9]>
<script src="js/html5.js"></script>
<script src="js/css3-mediaqueries.js"></script>
<![endif]-->
<script src="js/jquery1.4.4.min.js"></script>
<script src="js/script.js"></script>
<!-- スクリプト・JS終わり -->
<script src="https://yubinbango.github.io/yubinbango/yubinbango.js" charset="UTF-8"></script>
<script type="text/javascript">
<!--
	function check(){
		if(document.cusfrm.usrName.value==""){
			window.alert("お名前が未入力です。");
			return false;
		}else if(document.cusfrm.gender.value==""){
			window.alert("性別が未入力です。");
			return false;
		}else if(document.cusfrm.phone.value==""){
			window.alert("電話番号が未入力です。");
			return false;
		}else if(document.cusfrm.mail.value==""){
				window.alert("メールアドレスが未入力です。");
				return false;
		}else if(document.cusfrm.password.value==""){
			window.alert("パスワードが未入力です。");
			return false;
		}else if(!document.cusfrm.usrName.value.match(/^[ぁ-んァ-ン一-龥]*$/)){
			window.alert("お名前は英数字記号以外を入力ください。");
			return false;
		}else if(!document.cusfrm.password.value.match(/^[a-zA-Z0-9]*$/)){
			window.alert("パスワードは半角英数字を入力ください。");
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
					<li><a href="UserSvl"><jsp:getProperty name="userInfo" property="usr_name"/>様マイページ<br><span>MyPage</span></a></li>
					<li><a href="UserLogoffSvl">ログアウト<br><span>Logout</span></a></li>
				</ul>
      </div>
    </nav>
		<!-- メインナビゲーション -->

  </div>
</div>
<%} %>
<!-- / メニューバー終わり（ホーム） -->
<jsp:useBean id="msg" class="model.IdealException" scope="request"/>
		<!-- コンテンツ -->
<div id="wrapper" align="center">
<article class="post">
<section class="content">
		<h1>お客様情報登録</h1>
		<div>
			当店をご利用いただき、誠にありがとうございます。
			<br/>
			下記の項目にご記入をお願いいたします。
			<br/>
			なお、お名前とe-mailとパスワードは必ず入力してください。
		</div>
		 <% if(msg.getErrmsg()!=null){%>
			<p><font color="red">メールアドレスが使用されておりますので別のメールアドレスでご登録ください。</font></p>
		<% } %>
		<!-- メッセージ終了 --> <!-- フォーム開始 -->
		<div class="h-adr">
			<form id="cusfrm" name="cusfrm" action="UserOperationSvl" method="post"
				onsubmit="return check();">
				<span class="p-country-name" style="display:none;">Japan</span>
				<table>
					<tr>
						<td id="index">お名前※</td>
						<td>
							<input type="text" name="usrName" />
						</td>
					</tr>
					<tr>
						<td id="index">性別※</td>
						<td>
							<input type="radio" name="gender" value="1" />男性
							<input type="radio" name="gender" value="2" />女性
						</td>
					</tr>
					<tr>
						<td id="index">生年月日</td>
						<td>
							<input type="text" name="birthday" />
						</td>
					</tr>
					<tr>
						<td id="index">郵便番号</td>
						<td >
							<input type="text" class="p-postal-code" name="postcode" />
						</td>
					</tr>
					<tr>
						<td id="index">住所</td>
						<td >
							<input type="text" class="p-region p-locality p-street-address p-extended-address" name="address" />
						</td>
					</tr>
					<tr>
						<td id="index">電話番号※</td>
						<td>
							<input type="text" name="phone" />
						</td>
					</tr>
					<tr>
						<td id="index">e-mail※</td>
						<td>
							<input type="text" name="mail" />
						</td>
					</tr>
					<tr>
						<td id="index">パスワード※</td>
						<td>
							<input type="password" name="password" />
						</td>
					</tr>
					<tr>
						<td colspan="2" class="submit">
							※は必修入力です。<input type="submit" value="登録" />
						</td>
					</tr>
				</table>
				<input type="hidden" name="mode" value="11"/>
			</form>
			</div>
			<div><a href="./home.jsp">ホーム</a></div>
</section>
</article>
</div>

		<!-- フォーム終了 -->
		<!-- / コンテンツ -->
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