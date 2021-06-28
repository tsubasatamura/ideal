package model;

import java.io.Serializable;
import java.util.Calendar;

public class HCalendar implements Serializable{
	private int year;
	//カレンダーの月
	private int month;
	//カレンダーの日付を保持する配列
	private String[][] data;

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public String[][] getData() {
		return data;
	}

	public void setData(String[][] data) {
		this.data = data;
	}

	public HCalendar(){
		super();
	}

	public static HCalendar createCalendar(int... args) {
		//マイカレンダーインスタンス生成
		HCalendar mc=new HCalendar();
		//現在日時でカレンダーインスタンス生成
		Calendar cal=Calendar.getInstance();
		//2つの引数が来ていたら
		if(args.length==2) {
			//最初の引数で年を設定
			cal.set(Calendar.YEAR, args[0]);
			//次の引数で月を設定
			cal.set(Calendar.MONTH, args[1]-1);
		}
		//マイカレンダーに年を設定
		mc.setYear(cal.get(Calendar.YEAR));
		//マイカレンダーの元号の設定

		//マイカレンダーに月の設定
		mc.setMonth(cal.get(Calendar.MONTH)+1);
		//その月の1日が何曜日かを調べる為に日付を1日にする
		cal.set(Calendar.DATE, 1);
		//カレンダーの最初の空白の数
		int before=cal.get(Calendar.DAY_OF_WEEK)-1;
		//カレンダーの日付の数
		int daysCount=cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		//その月の最後の日が何曜日かを調べるために日付を最終日にする
		cal.set(Calendar.DATE, daysCount);
		//最後の日後の空白の数
		int after=7-cal.get(Calendar.DAY_OF_WEEK);
		//すべての要素数
		int total=before+daysCount+after;
		//その要素数を幅7個の配列に入れていった場合何行になるか
		int rows=total/7;
		//その行数で2次元配列を生成
		String[][] data=new String[rows][7];
		//今見ているカレンダーが今月かどうかを調べるために、この瞬間の日付情報をもつもう一つのインスタンス作成しておく
		Calendar now=Calendar.getInstance();
		for(int i=0;i<rows;i++) {
			for(int j=0;j<7;j++) {
				if(i==0 && j<before || i==rows-1 && j>=(7-after)) {
					//カレンダーの前後に入る空白の部分は空文字
					data[i][j]="";
				}else {
					//カウンター変数と実際の日付の変換
					int date=i*7+j+1 - before;
					//配列に日付を入れる
					data[i][j]=String.valueOf(date);
					//今作業しているマイカレンダーが今月のカレンダーだったら今日の日付の先頭に*を付与する
					if(now.get(Calendar.DATE)== date && now.get(Calendar.MONTH)==mc.getMonth()-1  && now.get(Calendar.YEAR)==mc.getYear()) {
						data[i][j]="*"+data[i][j];
					}
				}
			}
		}
		//作成した2次元配列をマイカレンダーにセットする。
		mc.setData(data);
		return mc;
	}

}
