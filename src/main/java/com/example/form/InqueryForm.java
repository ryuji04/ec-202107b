package com.example.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * お問い合わせ内容のフォームクラス
 * 
 * @author adachiryuji
 *
 */
public class InqueryForm {
	
	/** 名前 */
	@NotBlank(message="名前は必須です")
	private String name;
	/**　メールアドレス */
	@NotBlank(message="メールアドレスは必須です")
	@Email(message="Eメールの形式が不正です")
	private String mail;
	/**　電話番号 */
	@NotBlank(message="電話番号は必須です")
	@Pattern(regexp = "^0\\d{2,3}-\\d{1,4}-\\d{4}$", message = "電話番号はXXXX-XXXX-XXXXの形式で入力してください")
	private String tell;
	/**　お問い合わせ内容 */
	@NotBlank(message="お問い合わせ内容は必須です")
	private String inquery;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getTell() {
		return tell;
	}
	public void setTell(String tell) {
		this.tell = tell;
	}
	public String getInquery() {
		return inquery;
	}
	public void setInquery(String inquery) {
		this.inquery = inquery;
	}
	@Override
	public String toString() {
		return "Inquery [name=" + name + ", mail=" + mail + ", tell=" + tell + ", inquery=" + inquery + "]";
	}
	
}
