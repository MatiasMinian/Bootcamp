package com.bootcamp.app;

import java.math.BigDecimal;
import java.util.Calendar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.bootcamp.app.model.Category;
import com.bootcamp.app.model.Product;
import com.bootcamp.app.model.Reservation;
import com.bootcamp.app.model.User;
import com.bootcamp.app.persistence.managers.CategoryManager;
import com.bootcamp.app.persistence.managers.ProductManager;
import com.bootcamp.app.persistence.managers.ReservationManager;
import com.bootcamp.app.persistence.managers.UserManager;

@SpringBootApplication
public class IBuyApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(IBuyApplication.class, args);
		
		UserManager userManager = context.getBean(UserManager.class);
		CategoryManager categoryManager = context.getBean(CategoryManager.class);
		ProductManager productManager = context.getBean(ProductManager.class);
		ReservationManager reservationManager = context.getBean(ReservationManager.class);
		
		/*
		Calendar birthDate = Calendar.getInstance();
		birthDate.set(1992, 10, 10);
		User matias = new User("matias", "minian", "mminian", birthDate, "minian@gmail.com");
		
		birthDate.set(1993, 8, 21);
		User alejo = new User("alejo", "kozicki", "alek", birthDate, "alejo@gmail.com");
		
		Category technology = new Category("technology", "All products related with technology");
		
		Product nexusPhone = new Product("Nexus 6", "Great phone", alejo, technology, "url", new BigDecimal(5000), true);
		
		alejo.addProduct(nexusPhone);
		userManager.saveNewUser(matias);
		userManager.saveNewUser(alejo);
		*/
		
		/*
		Category cars = new Category("cars", "All cars");
		Product nexus = productManager.findProductById(new Long(3));
		nexus.setCategory(cars);
		productManager.updateProduct(nexus);
		*/
		
		/*
		User matias = userManager.findUserById(new Long(1));
		Product nexus = productManager.findProductById(new Long(3));
		Reservation matiasRes = new Reservation(matias, nexus);
		reservationManager.saveNewReservation(matiasRes);
		*/
		
		
		/*
		Reservation matiasRes = reservationManager.findReservationById(new Long(1));
		reservationManager.deleteReservation(matiasRes);
		*/
		
		/*
		User alejo = userManager.findUserById(new Long(2));
		userManager.deleteUser(alejo);
		*/	
	}
}
