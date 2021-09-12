package com.example.common;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.domain.UserAndOrder;

public class BadgeTestFromDB  {
	public static void main(String[] args) {
		
		Connection conn = null;
		String url = "jdbc:postgresql://localhost:5432/student";
		String user = "postgres";
		String password = "postgres";
		String sql="SELECT o.order_date o_orderDate,o.total_price o_totalPrice,u.email u_email,\r\n"
				+ "u.telephone u_telephone,u.address u_address FROM orders o\r\n"
				+ " LEFT OUTER JOIN users u ON o.user_id=u.id;";
		
		try {
			//DBへ接続
		try {
			conn = DriverManager.getConnection(url, user, password);
			PreparedStatement pstmt=conn.prepareStatement(sql);
			ResultSet rs=pstmt.executeQuery();
			
			List<UserAndOrder>dataList=new ArrayList();
			while(rs.next()) {
				UserAndOrder data=new UserAndOrder();
				data.setOrderDate(rs.getDate("o_orderDate"));
				data.setTotalPrice(rs.getInt("o_totalPrice"));
				data.setEmail(rs.getString("u_email"));
				data.setTelephone(rs.getString("u_telephone"));
				data.setAddress(rs.getString("u_address"));
				dataList.add(data);
				
			}
			
			FileWriter csvWriter=new FileWriter("test.csv",false);
			PrintWriter pw=new PrintWriter(new BufferedWriter(csvWriter));
			
			System.out.println("dataList"+dataList);
			System.out.println("test.csv"+csvWriter);
			for(UserAndOrder data2:dataList) {
				pw.print(data2.getOrderDate());
				pw.print(",");
				pw.print(data2.getTotalPrice());
				pw.print(",");
				pw.print(data2.getEmail());
				pw.print(",");
				pw.print(data2.getTelephone());
				pw.print(",");
				pw.print(data2.getAddress());
				pw.println();
			}
			
			pw.close();
			
			
		}catch(SQLException e) {
			System.out.println("SQLExceptionの例外が発生しました");
		}
		}catch(Exception ex) {
			System.out.println("Exceptionの例外が発生しました");
		}
}
}
