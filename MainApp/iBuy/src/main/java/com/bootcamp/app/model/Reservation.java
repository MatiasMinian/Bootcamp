package com.bootcamp.app.model;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "RESERVATIONS")
public class Reservation {
	
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;
	
	// TODO Should it be lazy ?
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User buyer;
	
	// TODO Should it be lazy ?
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id", nullable = false)
	private Product product;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date", nullable = false)
	private Calendar date = Calendar.getInstance();
	
	/* *** CONSTRUCTORS *** */
	
	public Reservation() {
	}
	
	public Reservation(User buyer, Product product) {
		this.buyer = buyer;
		this.product = product;
	}
	
	/* *** GETTERS & SETTERS *** */
	
	public Long getId() {
		return id;
	}

	public User getBuyer() {
		return buyer;
	}

	public void setBuyer(User buyer) {
		this.buyer = buyer;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}
}