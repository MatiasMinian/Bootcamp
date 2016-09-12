package com.bootcamp.app;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
		SpringApplication.run(IBuyApplication.class, args);
		
		/*
		ApplicationContext context = SpringApplication.run(IBuyApplication.class, args);
		UserManager userManager = context.getBean(UserManager.class);
		ProductManager productManager = context.getBean(ProductManager.class);
		CategoryManager categoryManager = context.getBean(CategoryManager.class);
		ReservationManager reservationManager = context.getBean(ReservationManager.class);
		
		
		Calendar matiasBirthDate = Calendar.getInstance();
		matiasBirthDate.set(1992, 10, 10);
		User matias = new User("Matias", "Minian", "mminian", matiasBirthDate, "minian@gmail.com");
		
		Calendar pabloBirthDate = Calendar.getInstance();
		pabloBirthDate.set(1995, 3, 4);
		User pablo = new User("Pablo", "Alice", "pablo", pabloBirthDate, "pablo.a@gmail.com");
		
		Calendar alejoBirthDate = Calendar.getInstance();
		alejoBirthDate.set(1993, 8, 21);
		User alejo = new User("alejo", "kozicki", "alek", alejoBirthDate, "alejo@gmail.com");
		
		Category technology = new Category("technology", "All products related with technology");
		Category cars = new Category("cars", "Hybrid, deluxe and pickups");
		
		Product nexusPhone = new Product("Nexus 6", "Great phone", alejo, technology, "url", new BigDecimal(5000), true);
		Product fordFiesta = new Product("Fiesta", "Ford Fiesta almost new", alejo, cars, "url", new BigDecimal(150000), false);
		Product iphone = new Product("iphone", "Apple", pablo, technology, "url", new BigDecimal(7500), true);
		
		userManager.saveNewUser(matias);
		userManager.saveNewUser(alejo);
		userManager.saveNewUser(pablo);
		
		categoryManager.saveNewCategory(cars);
		categoryManager.saveNewCategory(technology);		
		
		productManager.saveNewProduct(iphone);
		productManager.saveNewProduct(fordFiesta);
		productManager.saveNewProduct(nexusPhone);
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
