package com.sidney.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.support.ReflectionHelper.ArgsMatchKind;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSON;


public class ForumDao {
	

	private JdbcTemplate jdbcTemplate;
	
	
	
	
	public void init(){
		/* 1.创建数据源*/
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName("com.mysql.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost:3306/test");
		ds.setUsername("sidney");
		ds.setPassword("sidney");
		/* 2.生成jdbcTemplate实例*/
		jdbcTemplate = new JdbcTemplate();
		jdbcTemplate.setDataSource(ds);
	}
	
	
	public void addUser(User user){
		String sql = "insert into t_user(user_id,user_name) values (?,?);";
		Object[]params = new Object[]{user.getUserid(),user.getUserName()};
		
		int result = jdbcTemplate.update(sql,params,new int[]{Types.INTEGER,Types.VARCHAR});
		
		System.out.println("addUser values "+ JSON.toJSONString(user) + " result=" + result);
	}
	
	
	public void addUser1(final User user){
		String sql = "insert into t_user(user_id,user_name) values (?,?);";	
		
		int result = jdbcTemplate.update(sql,new PreparedStatementSetter(){
			@Override
			public void setValues(PreparedStatement args) throws SQLException {
				args.setInt(1, user.getUserid());
				args.setString(2, user.getUserName());
			}
			
		});
		
		System.out.println("addUser values "+ JSON.toJSONString(user) + " result=" + result);
	}
	
	
	public void addUser2(final User user){
		/* sql字符串需要在匿名内部类中使用，定义为final*/
		final String sql = "insert into t_user(user_id,user_name) values (?,?)";		
		int result = jdbcTemplate.update(new PreparedStatementCreator() {			
			@Override
			public PreparedStatement createPreparedStatement(Connection arg0)
					throws SQLException {
				PreparedStatement ps = arg0.prepareStatement(sql);
				ps.setInt(1, user.getUserid());
				ps.setString(2, user.getUserName());
				return ps;
			}
		});	
		
		
		System.out.println("addUser values "+ JSON.toJSONString(user) + " result=" + result);
	}
	
	/*使用返回主键ID的插入方法*/
	public void addUser3(final User user){
		final String sql = "insert into t_user(user_name) values (?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		int result = jdbcTemplate.update(new PreparedStatementCreator() {			
			@Override
			public PreparedStatement createPreparedStatement(Connection arg0)
					throws SQLException {
				PreparedStatement ps = arg0.prepareStatement(sql);	
				ps.setString(1, user.getUserName());
				return ps;
			}
		});	
		
		//user.setUserid(keyHolder.getKey().intValue());
		
		System.out.println("addUser values "+ JSON.toJSONString(user) + " result=" + result);
	}
	
	public void addUsers(final List<User> list){
		if(list == null || list.size() < 1){
			System.out.println("no element. return");
			return;
		}
		final String sql = "insert into t_user(user_name) values (?)";
		jdbcTemplate.batchUpdate(sql,new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int index) throws SQLException {
				User user = list.get(index);
				ps.setString(1, user.getUserName());
			}
			
			@Override
			public int getBatchSize() {

				return list.size();
			}
		});
		
	}
	
	public User getUser(final int id){
		String sql = "select user_name,user_id from t_user where user_id=?";
		final User user = new User();
		
		jdbcTemplate.query(sql, new Object[]{id},new RowCallbackHandler() {		
			@Override
			public void processRow(ResultSet arg0) throws SQLException {			
				user.setUserid(id);
				user.setUserName(arg0.getString("user_name"));
			}
		});
		
		return user;
	}
	
	
	public List<User> getUsers(int idStart,int idEnd){
		String sql = "select user_id,user_name from t_user where user_id between ? and ?;";
		final List<User> list = new ArrayList<User>();
		jdbcTemplate.query(sql, new Object[]{idStart,idEnd}, new RowCallbackHandler(){

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				User user = new User();
				user.setUserid(rs.getInt("user_id"));
				user.setUserName(rs.getString("user_name"));
				list.add(user);				
			}
			
		});
		
		return list;
	}
	
	/* 使用RowMapper映射多行数据*/
	public List<User> getUsers1(int idStart,int idEnd){
		String sql = "select user_id,user_name from t_user where user_id between ? and ?;";
		
		return jdbcTemplate.query(sql, new Object[]{idStart,idEnd}, new RowMapper<User>(){
			@Override
			public User mapRow(ResultSet rs,int index) throws SQLException {
				User user = new User();
				user.setUserid(rs.getInt("user_id"));
				user.setUserName(rs.getString("user_name"));
				return user;		
			}
		});	
	}
	
	public static void main(String[] args) {
		
		
		/* 3.执行sql */
		/*String sql = "create table t_user (user_id int primary key, user_name varchar(60))";
		jdbcTemplate.execute(sql);*/
		
		ForumDao dao = new ForumDao();
		dao.init();
		
		/*List<User> list = new ArrayList<User>();
		for(int i = 0;i < 10;i++){
			User user = new User();
			user.setUserName(getRandomString(10));
			list.add(user);
		}
		
		dao.addUsers(list);*/

		
		System.out.println(JSON.toJSONString(dao.getUsers(1,15)));
		
		
	}
	
	
	
	
	public static String getRandomString(int length) { //length表示生成字符串的长度
	    String base = "abcdefghijklmnopqrstuvwxyz0123456789";   
	    Random random = new Random();
	    StringBuffer sb = new StringBuffer();   
	    for (int i = 0; i < length; i++) {   
	        int number = random.nextInt(base.length());   
	        sb.append(base.charAt(number));   
	    }   
	    return sb.toString();   
	 }  
	

}
