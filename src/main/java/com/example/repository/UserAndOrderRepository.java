package com.example.repository;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.UserAndOrder;



/**ユーザー情報と注文情報に関するリポジトリ.
 * @author adachiryuji
 *
 */
@Repository
public class UserAndOrderRepository {
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	private static final RowMapper<UserAndOrder>USERANDORDER_ROW_MAPPER
	=(rs,i)->{
				Date date=rs.getDate("o_orderdate");
				int totalPrice=rs.getInt("o_totalprice");
				String email=rs.getString("u_email");
				String telephone=rs.getString("u_telephone");
				String address=rs.getString("u_address");
				return new UserAndOrder(date,totalPrice,email,telephone,address);
			};
			
			public List<UserAndOrder>userAndOrderFindAll(){
				System.out.println(1);
				String sql
				="SELECT o.order_date o_orderdate,o.total_price o_totalprice,u.email u_email,u.telephone u_telephone,u.address u_address FROM orders o LEFT OUTER JOIN users u ON o.user_id=u.id;";
				
				List<UserAndOrder>userAndOrderList=template.query(sql,USERANDORDER_ROW_MAPPER);
				System.out.println(1);
				System.out.println("userAndOrderList"+userAndOrderList);
				return userAndOrderList;
				
			}
			
			public void csvInsert(UserAndOrder userAndOrder) {
				String sql
				="INSERT INTO items(id,name,description,price_m,price_l,image_path,deleted) VALUES( :id,:name ,:description,:priceM,:priceL,imagePath,deleted)";
			
				SqlParameterSource param
				=new BeanPropertySqlParameterSource(userAndOrder);
				
				template.update(sql, param);
			}
			
			
}





