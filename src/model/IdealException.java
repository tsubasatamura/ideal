package model;
import java.io.Serializable;
public class IdealException extends Exception implements Serializable{
	private static final long serialVersionUID=1L;
	private int errCd;
	private String[] ERR_MSG={null,"障害が発生しました。","データベース処理で例外が発生しました。","お客様ID、パスワードを確認してください。","管理者名、パスワードを確認してください。","ご指定された日時に空席がございませんでした。","予約されているコースなので削除できません。","コースに登録されているメニューなので削除できません。"};
	private String errmsg;
	public static final int ERR_NO_EXCEPTION=1;
	public static final int ERR_NO_DB_EXCEPTION=2;
	public static final int ERR_NO_NOT_MEMBER_EXCEPTION=3;
	public static final int ERR_NO_ADMIN_EXCEPTION=4;
	public static final int ERR_NO_NOT_VACANCY_EXCEPTION=5;
	public static final int ERR_NO_NOT_RESERV_DELETE=6;
	public static final int ERR_NO_NOT_MENU_DELETE=7;

	public IdealException(){
		super();
	}

	public IdealException(int errCd){
		this.errCd=errCd;
	}

	public int getErrCd() {
		return errCd;
	}


	public String getErrmsg() {
		this.errmsg=ERR_MSG[this.errCd];
		return this.errmsg;
	}


	/*public String getMsg(){
		this.err_msg=ERR_MSG[this.errCd];
		return this.err_msg;
	}*/

}
