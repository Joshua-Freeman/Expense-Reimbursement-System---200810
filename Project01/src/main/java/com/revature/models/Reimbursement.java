package com.revature.models;

import java.io.InputStream;
import java.sql.Timestamp;

public class Reimbursement {
	
	public static enum ReimbursementType {
		LODGING, TRAVEL, FOOD, OTHER
	}
	public static enum ReimbursementStatus {
		APPROVED, REJECTED, PENDING 
	}
	
	private int id;
	private double amount;
	private Timestamp submitted;
	private Timestamp resolved;
	private String description;
	private InputStream receipt;
	private int author;
	private int resolver;
	private ReimbursementType type;
	private ReimbursementStatus status;
	
	
	public Reimbursement() {
		submitted = new Timestamp(System.currentTimeMillis());
	}


	public Reimbursement(int id, double amount, Timestamp submitted, Timestamp resolved, String description,
			InputStream receipt, int author, int resolver, ReimbursementType type, ReimbursementStatus status) {
		this.id = id;
		this.amount = amount;
		this.submitted = submitted;
		this.resolved = resolved;
		this.description = description;
		this.receipt = receipt;
		this.author = author;
		this.resolver = resolver;
		this.type = type;
		this.status = status;
	}

	

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public double getAmount() {
		return amount;
	}


	public void setAmount(double amount) {
		this.amount = amount;
	}


	public Timestamp getSubmitted() {
		return submitted;
	}


	public void setSubmitted(Timestamp submitted) {
		this.submitted = submitted;
	}


	public Timestamp getResolved() {
		return resolved;
	}


	public void setResolved(Timestamp resolved) {
		this.resolved = resolved;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public InputStream getReceipt() {
		return receipt;
	}


	public void setReceipt(InputStream receipt) {
		this.receipt = receipt;
	}


	public int getAuthor() {
		return author;
	}


	public void setAuthor(int author) {
		this.author = author;
	}


	public int getResolver() {
		return resolver;
	}


	public void setResolver(int resolver) {
		this.resolver = resolver;
	}


	public ReimbursementType getType() {
		return type;
	}


	public void setType(ReimbursementType type) {
		this.type = type;
	}


	public ReimbursementStatus getStatus() {
		return status;
	}


	public void setStatus(ReimbursementStatus status) {
		this.status = status;
	}


	@Override
	public String toString() {
		return "Reimbursement [id=" + id + ", amount=" + amount + ", submitted=" + submitted + ", resolved=" + resolved
				+ ", description=" + description + ", receipt=" + receipt + ", author=" + author + ", resolver="
				+ resolver + ", type=" + type + ", status=" + status + "]";
	}
	
	
	

}
