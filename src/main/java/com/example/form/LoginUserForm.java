package com.example.form;

public class LoginUserForm {
	/* 
	 *ユーザー情報フォームのクラス.
	 * 
	 * @author adachiryuji
	 *
	 */
		/**　メールアドレス */
		private String email;
		//* パスワード　*/
		private String password;
		
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
		@Override
		public String toString() {
			return "LoginUserForm [email=" + email + ", password=" + password + "]";
		}
		
}
