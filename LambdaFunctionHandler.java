package com.amazonaws.lambda.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class LambdaFunctionHandler implements RequestHandler<Void,List<ResponseRoute>> {

	List<ResponseRoute> li=new ArrayList<ResponseRoute>();
	
  List<ResponseRoute> ReList=new ArrayList<ResponseRoute>();
  
	public List<ResponseRoute> handleRequest(Void request,Context arg1) {
	
		// TODO Auto-generated method stub
	
				try {
					ReList=getDetails();
				} catch (SQLException sqlException) {
					System.out.println(sqlException);
				}
				return ReList;
				
	}

	private List<ResponseRoute> getDetails() throws SQLException {
		
		Connection connection = getConnection();
		Statement statement = connection.createStatement();
//		
		
		ResultSet rs = statement.executeQuery("Select * from pktravels.routerequirement");
		
	        while(rs.next()){
	        	
	        	ResponseRoute responseRoute=new ResponseRoute();
	        	responseRoute.setId(rs.getString("id"));
	        	responseRoute.setName(rs.getString("name"));
	        	responseRoute.setMobilenumber(rs.getString("mobilenumber"));
	        	responseRoute.setModel(rs.getString("model"));
	        	responseRoute.setFromlocation(rs.getString("fromlocation"));
	        	responseRoute.setTolocation(rs.getString("tolocation"));
	        	responseRoute.setVehicletype(rs.getString("vehicletype"));
	        	responseRoute.setTimings(rs.getString("timings"));
	        	li.add(responseRoute);
	        }
	        rs.close();
	        
	        return li;
	       
	}

	public Connection getConnection() throws SQLException {
		// TODO Auto-generated method stub
				String url = "jdbc:mysql://pktravels.cku3de1beg1n.ap-south-1.rds.amazonaws.com:3306";
				String username = "modicum";
				String password = "iworkformodicum";
				Connection connection = DriverManager.getConnection(url, username, password);
				return connection;
	}


}
