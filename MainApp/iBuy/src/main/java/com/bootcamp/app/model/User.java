package com.bootcamp.app.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "USERS")
public class User {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;

	@Column(name = "first_name", nullable = false)
	private String firstName;

	@Column(name = "last_name", nullable = false)
	private String lastName;

	@Column(name = "username", nullable = false, unique = true)
	private String username;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "birth_date")
	private Calendar birthDate;

	@Column(name = "email", nullable = false, unique = true)
	private String email;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_login")
	private Calendar lastLogin = Calendar.getInstance();
	
	@OneToMany(mappedBy = "buyer", fetch = FetchType.LAZY)
	@Cascade({CascadeType.DELETE})
	private List<Reservation> reservations = new ArrayList<>();
	
	@OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
	@Cascade({CascadeType.ALL})
	private List<Product> products = new ArrayList<>();

	/* *** CONSTRUCTORS *** */

	public User() {
	}

	public User(String firstName, String lastName, String username, Calendar birthDate, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.birthDate = birthDate;
		this.email = email;
	}

	/* *** GETTERS & SETTERS *** */
	
	public void addReservation(Reservation reservation) {
		reservations.add(reservation);
	}
	
	public void removeReservation(Reservation reservation) {
		reservations.remove(reservation);
	}
	
	public void addProduct(Product product) {
		products.add(product);
	}
	
	public void removeProduct(Product product) {
		products.remove(product);
	}

	public Long getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Calendar getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Calendar birthDate) {
		this.birthDate = birthDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Calendar getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Calendar lastLogin) {
		this.lastLogin = lastLogin;
	}
	
	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
}