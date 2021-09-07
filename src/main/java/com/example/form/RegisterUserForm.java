package com.example.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * ユーザー情報登録時に使用するクラス.
 * 
 * @author okahikari
 * 
 */
public class RegisterUserForm {

	/** 名前 */
	@NotBlank(message = "名前を入力してください")
	private String name;
	/** Eメール */
	@NotBlank(message = "メールアドレスを入力してください")
	@Email(message = "メールアドレスの形式が不正です")
	private String email;
	/** パスワード (Pattern/Matcherで確認済み) */
	@Size(min=8, max=16, message = "8文字以上16文字以内のパスワードにしてください。")
	@Pattern(regexp="^[0-9].*[a-z].*[A-Z]|[0-9].*[A-Z].*[a-z]|[a-z].*[0-9].*[A-Z]|[a-z].*[A-Z].*[0-9]|[A-Z].*[0-9].*[a-z]|[A-Z].*[a-z].*[0-9]$", message = "パスワードは半角数字、半角英字大文字、半角英字小文字を合わせてください。")
	private String password;
	/** 確認用パスワード */
	@NotBlank(message = "確認用パスワードを入力してください")
	private String confirmPassword;
	/** 郵便番号 */
	@Pattern(regexp = "^[0-9]{3}-[0-9]{4}$", message = "郵便番号はXXX-XXXXの形式で入力してください")
	private String zipcode;
	/** 住所 */
	@NotBlank(message = "住所を入力してください")
	private String address;
	/** 電話番号 */
	@Pattern(regexp = "^0\\d{2,3}-\\d{1,4}-\\d{4}$", message = "電話番号はXXXX-XXXX-XXXXの形式で入力してください")
	private String telephone;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	@Override
	public String toString() {
		return "RegisterUserForm [name=" + name + ", email=" + email + ", password=" + password + ", confirmPassword="
				+ confirmPassword + ", zipcode=" + zipcode + ", address=" + address + ", telephone=" + telephone + "]";
	}
}
