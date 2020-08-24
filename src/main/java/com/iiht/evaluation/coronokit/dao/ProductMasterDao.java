package com.iiht.evaluation.coronokit.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.iiht.evaluation.coronokit.model.ProductMaster;



public class ProductMasterDao {

	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private String jdbcDriver;
	private Connection jdbcConnection;

	public ProductMasterDao(String jdbcDriver, String jdbcURL, String jdbcUsername, String jdbcPassword) {
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
	
	public List<ProductMaster> getProductRecords() throws ClassNotFoundException, SQLException {
		String sql = "select * from productmaster";
		this.connect();
		
		Statement stmt = this.jdbcConnection.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		
		// map it to model
		List<ProductMaster> productMaster = new ArrayList<ProductMaster>();
		while(rs.next()) {
			ProductMaster product = new ProductMaster(rs.getInt("id"), 
											 rs.getString("productName"), 
											 rs.getFloat("cost"), 
											 rs.getString("productDescription"));
			productMaster.add(product);		
		}
		
		rs.close();
		stmt.close();
		this.disconnect();
		
		return productMaster;
	}

	public boolean addNewProduct(String productName, String cost, String productDescription) throws ClassNotFoundException, SQLException {
		String sql = "insert into productmaster (productName,cost,productDescription) values(?,?,?)";
		this.connect();
		
		PreparedStatement pstmt = this.jdbcConnection.prepareStatement(sql);
		pstmt.setString(1, productName);
		pstmt.setFloat(2, Float.parseFloat(cost));
		pstmt.setString(3, productDescription );
		
		boolean added = pstmt.executeUpdate() > 0;
		
		pstmt.close();
		this.disconnect();
		return added;
	}
	
	public ProductMaster getProductRecordsById(int id) throws ClassNotFoundException, SQLException {
		String sql = "select * from productmaster where id ="+id;
		this.connect();
		
		PreparedStatement pstmt = this.jdbcConnection.prepareStatement(sql);
		//pstmt.setInt(1, id);
		ResultSet rs = pstmt.executeQuery(sql);
		
		// map it to model
		ProductMaster productMaster = null;
		while(rs.next()) {
			String productName = rs.getString("productName");
			float cost = rs.getFloat("cost");
			String productDescription = rs.getString("productDescription");
			productMaster = new ProductMaster(id, productName, cost, productDescription);
		}
		
		rs.close();
		pstmt.close();
		this.disconnect();
		
		return productMaster;
	}
	
	public boolean updateProduct( ProductMaster product) throws ClassNotFoundException, SQLException {
		String sql="Update productmaster set productName=?,cost=?,productDescription=? where id=?";
		this.connect();
		
		PreparedStatement pstmt = this.jdbcConnection.prepareStatement(sql);
		pstmt.setString(1, product.getProductName());
		pstmt.setFloat(2, product.getCost());
		pstmt.setString(3, product.getProductDescription() );
		pstmt.setInt(4, product.getId());
		
		boolean updated = pstmt.executeUpdate() > 0;
		
		pstmt.close();
		this.disconnect();
		return updated;
	}

	public boolean deleteProduct(String id) throws ClassNotFoundException, SQLException {
		
		String sql = "delete from productmaster where id=?";
		this.connect();
		
		PreparedStatement pstmt = this.jdbcConnection.prepareStatement(sql);
		pstmt.setInt(1, Integer.parseInt(id));
		
		boolean deleted = pstmt.executeUpdate() > 0;
		
		pstmt.close();
		this.disconnect();
		return deleted;
	}

	

	// add DAO methods as per requirements
}