<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.*"%>
<%@ page import="java.time.LocalDateTime"%>
<%@ page import="java.time.format.DateTimeFormatter"%>
<%@ page import="java.util.Calendar"%>
<%
	String yobi[] = { "日", "月", "火", "水", "木", "金", "土" };
	LocalDateTime nowDate = LocalDateTime.now();
	DateTimeFormatter fmt01 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	String now_date = fmt01.format(nowDate);
	String now_dateYy = now_date.substring(0, 4);
	String now_dateMm = now_date.substring(5, 7);
	String now_dateDd = now_date.substring(8, 10);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>本日から１週間の予約状況</title>
<link rel="stylesheet" type="text/css" media="all" href="css/style.css">
	<link rel="stylesheet" type="text/css" media="all" href="css/reserve.css" />
</head>
<body>
	<div class="post">
		<table>
			<tr>
				<%
					int y = Integer.parseInt(now_date.substring(0, 4));
					int m = Integer.parseInt(now_date.substring(5, 7));
					int d = Integer.parseInt(now_date.substring(8, 10));
					Calendar cal = Calendar.getInstance();
					for (int i = 0; i < 7; i++) {
						cal.set(y, m - 1, d);
				%>
				<th><%=m + "/" + d%>(<%=yobi[cal.get(Calendar.DAY_OF_WEEK) - 1]%>)
				</th>
				<%
					d++;
					}
				%>
			</tr>
			<tr>
				<%
					String date;
					String mark;
					for (int i = 0; i < 7; i++) {
						date = now_date.substring(0, 7) + "-" + String.format("%02d", (Integer.parseInt(now_dateDd) + i));
						mark = "◎";
						int status = (int) Reserve.getReservationStatus(date);
						if (status == 2) {
							mark = "〇";
						} else if (status == 1) {
							mark = "△";
						} else if (status == 0) {
							mark = "×";
						}
				%>
				<th><%=mark%></th>
				<%
					}
				%>
			</tr>
		</table>
	</div>
</body>
</html>