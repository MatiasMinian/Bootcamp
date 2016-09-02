package cucumber;

import static org.junit.Assert.*;

import com.bootcamp.app.SubscriptionsService;
import com.bootcamp.app.model.User;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class SubscribeSteps {
	
	User matias;
	User pablo;
	
	@Given("^a user and a blogger$")
	public void newUserAndBlogger() {
		matias = new User("matias", "matias@gmail.com");
		pablo = new User("pablo", "pablo@gmail.com");
	}
	
	@When("^the user subscribes to the blogger$")
	public void subscribeToBlogger() {
		new SubscriptionsService().subscribeToUser(matias, pablo);
	}
	
	@Then("^the blogger has a new subscriber$")
	public void verifyBloggerHasNewSubscriber() {
		assertTrue(pablo.getSubscribers().contains(matias));
	}
}
