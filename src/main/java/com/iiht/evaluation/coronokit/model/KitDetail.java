package com.iiht.evaluation.coronokit.model;

public class KitDetail {

	private int id;
	private int coronaKitId;
	
	private int productId;
	private String productName;
	
	private int quantity;
	private float amount;
	
	
	
	/*
	 * public KitDetail(int id, int coronaKitId, int product, int quantity, float f)
	 * { this.id = id; this.coronaKitId = coronaKitId; this.productId = product;
	 * this.quantity = quantity; this.amount = f; }
	 */
	

	public KitDetail(int id, int coronaKitId, int productId, String productName, int quantity, float amount) {
		this.id=id;
		this.coronaKitId=coronaKitId;
		this.productId=productId;
		this.productName=productName;
		this.quantity=quantity;
		this.amount=amount;
		
		
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCoronaKitId() {
		return coronaKitId;
	}
	public void setCoronaKitId(int coronaKitId) {
		this.coronaKitId = coronaKitId;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float f) {
		this.amount = f;
	}
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	
}
