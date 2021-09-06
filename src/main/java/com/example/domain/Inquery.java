package com.example.domain;
/**
 * お問い合わせ内容の情報
 * 
 * @author adachiryuji
 *
 */
public class Inquery {
	/** 名前 */
	private String name;
	/**　メールアドレス */
	private String mail;
	/**　電話番号 */
	private String tell;
	/**　お問い合わせ内容 */
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
