package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.User;
import com.example.repository.UserRepository;

/**
 * 登録ユーザー情報を操作するサービス.
 * 
 * @author okahikari
 *
 */
@Service
@Transactional
public class RegisterUserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	
	/**
	 * ユーザー情報を登録します.
	 * 
	 * @param user ユーザー情報
	 */
	public void insert(User user) {
		
		//パスワードをハッシュ化する
		String encodePassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodePassword);
		
		userRepository.insert(user);
	}
	
	/**
	 * Eメールからユーザー情報を取得します.
	 * 
	 * @param email Eメール
	 * @return ユーザー情報 存在しない場合はnullを返します
	 */
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}
}