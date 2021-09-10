package com.example.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;




public class BatchProcessing {
	
	
	public static void main(String[] args) {
		// 入力値のバイト(容量)を取得するクラス
				FileInputStream fi = null;
				// 受け取ったバイトから文字を復元
				InputStreamReader is = null;
				// 文字列を効率的に読むクラス
				BufferedReader br = null;

		try {
		Class.forName("org.postgresql.Driver");	
		String url = "jdbc:postgresql://localhost:5432/student";
		String user = "postgres";
		String password = "postgres";
		
		Connection con = DriverManager.getConnection(url, user, password);
		Statement stmt = con.createStatement();
		try {
			fi=new FileInputStream("item.csv");
			is=new InputStreamReader(fi);
			br=new BufferedReader(is);
			System.out.println(br);
		      // 最終行まで読み込む
		      String line;
		      String[] data=null; 
		      while ((line = br.readLine()) != null) {
		    	  data=line.split(",",0);
		    	  System.out.print(data[0]);
		    	  String sql="INSERT INTO items(id,name,description,price_m,price_l,image_path,deleted) VALUES("+ data[0] +",'" + data[1] +"','" +data[2] +"','" +data[3] +"','" +data[4] +"','" +data[5] +"','" +data[6]+ "')";                 
		    	  
		    	  stmt.executeUpdate(sql);
		      }
		      
		      br.close();
		}catch(Exception ex) {
			System.out.println(ex);
			}
		stmt.close();
		con.close();
		
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
		
		
	   
	

