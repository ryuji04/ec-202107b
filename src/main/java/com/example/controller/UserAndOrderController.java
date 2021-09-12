package com.example.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Item;
import com.example.domain.UserAndOrder;
import com.example.repository.ItemRepository;
import com.example.repository.UserAndOrderRepository;

import ch.qos.logback.core.net.SyslogOutputStream;

@Controller
@RequestMapping("badge")
public class UserAndOrderController {
	@Autowired
	public UserAndOrderRepository repository;
	
	@Autowired
	public ItemRepository itemRepository;
	
	@RequestMapping("")
	public String index() {
		// 入力値のバイト(容量)を取得するクラス
		FileInputStream fi = null;
		// 受け取ったバイトから文字を復元
		InputStreamReader is = null;
		// 文字列を効率的に読むクラス
		BufferedReader br = null;
		try {
			fi=new FileInputStream("item.csv");
			System.out.println("ファイルを読み込めてるかテスト(fi)"+fi);
			is=new InputStreamReader(fi);
			br=new BufferedReader(is);
			System.out.println("ファイルが読み込めてるかテスト(br)"+br);
			// 最終行まで読み込む
		      String line;
		      String[] data=null; 
		       Item item=new Item();
		      while ((line = br.readLine()) != null) {
		    	  data=line.split(",",0);
		    	  System.out.print(data[0]);
		    	 System.out.print(data[1]);
		    	 System.out.print(data[2]);
		    	  System.out.print(data[3]);
		    	  System.out.print(data[4]);
		    	  System.out.print(data[5]);
		    	  System.out.print(data[6]);
		    	  
		    	 // String sql="INSERT INTO items(id,name,description,price_m,price_l,image_path,deleted) VALUES("+ data[0] +",'" + data[1] +"','" +data[2] +"','" +data[3] +"','" +data[4] +"','" +data[5] +"','" +data[6]+ "')";                 
		    	  
		    	  item.setId(Integer.parseInt(data[0]));
		    	  item.setName(data[1]);
		    	  item.setDescription(data[2]);
		    	  item.setPriceM(Integer.parseInt(data[3]));
		    	  item.setPriceL(Integer.parseInt(data[4]));
		    	  item.setImagePath(data[5]);
		    	  item.setDeleted(Boolean.valueOf(data[6]));
		    	  System.out.print(item);
		    	  
		    	  
		    	  itemRepository.csvInsert(item);
		    	  
		    	  
		      }
		      
		    	  
		    	  
		      return "dbUploadClear";
		}catch(Exception ex) {
			System.out.println("処理に失敗しました");
			return "failure";
			}
		
		
		
	}
	
	@RequestMapping("test")
	public String test() {
		try {
			
			
			FileWriter csvWriter=new FileWriter("test.csv",false);
			PrintWriter pw=new PrintWriter(new BufferedWriter(csvWriter));
			List<UserAndOrder>list=repository.userAndOrderFindAll();
			System.out.println("list"+list);
			System.out.println("test.csv"+csvWriter);
			for(UserAndOrder data2:list) {
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
			
			return "done";
		}catch(Exception e) {
			System.out.println("SQLExceptionの例外が発生しました");
			return "failure";
		}
		
		
}
	}
