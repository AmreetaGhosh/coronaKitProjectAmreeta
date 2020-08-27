package com.iiht.evaluation.coronokit.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.iiht.evaluation.coronokit.model.CoronaKit;
import com.iiht.evaluation.coronokit.model.KitDetail;
import com.iiht.evaluation.coronokit.model.ProductMaster;



public class KitDao {

	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private String jdbcDriver;
	private Connection jdbcConnection;

	public KitDao(String jdbcDriver, String jdbcURL, String jdbcUsername, String jdbcPassword) {
		this.jdbcDriver = jdbcDriver;
        this.jdbcURL = jdbcURL;
        this.jdbcUsername = jdbcUsername;
        this.jdbcPassword = jdbcPassword;
    }

	protected void connect() throws SQLException {
		if (jdbcConnection == null || jdbcConnection.isClosed()) {
			try {
				Class.forName(jdbcDriver);
			} catch (ClassNotFoundException e) {
				throw new SQLException(e);
			}
			jdbcConnection = DriverManager.getConnection(this.jdbcURL, this.jdbcUsername, this.jdbcPassword);
		}
	}

	protected void disconnect() throws SQLException {
		if (jdbcConnection != null && !jdbcConnection.isClosed()) {
			jdbcConnection.close();
		}
	}
	
	public boolean saveVisitorDetails(CoronaKit VisitorBillingDetails, List<KitDetail> productList) throws SQLException, ClassNotFoundException 
	{
		
		this.connect();
		PreparedStatement pstmt=null;;
		int recordsAdded=0;
		
		for(int i=0;i<productList.size();i++)
		{
		pstmt=jdbcConnection.prepareStatement("insert into VisitorOrderSummary(Kitid,VisitorName,VistorEmail,VisitorPhone,pId,ProductId,ProductName,ProductQuantity,ProductAmount,VisitorDeliverAddress,BillingDate ) values(?,?,?,?,?,?,?,?,?,?,?)");
		
		pstmt.setInt(1, VisitorBillingDetails.getId());
		pstmt.setString(2, VisitorBillingDetails.getPersonName());
		pstmt.setString(3, VisitorBillingDetails.getEmail());
		pstmt.setLong(4, Long.parseLong(VisitorBillingDetails.getContactNumber()));
		pstmt.setInt(5, productList.get(i).getId());
		pstmt.setInt(6, productList.get(i).getProductId());
		pstmt.setString(7, productList.get(i).getProductName());
		pstmt.setInt(8, productList.get(i).getQuantity());
		pstmt.setFloat(9, productList.get(i).getAmount());
		pstmt.setString(10, VisitorBillingDetails.getDeliveryAddress());
		pstmt.setString(11, VisitorBillingDetails.getOrderDate());
		recordsAdded+=pstmt.executeUpdate();
	}
		
		pstmt.close();
		this.disconnect();
		
		if(recordsAdded>0)
			return true;
		else 
			return false;
		
	
		
}

	// add DAO methods as per requirements
	
	
}