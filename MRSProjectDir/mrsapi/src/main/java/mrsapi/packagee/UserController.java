package mrsapi.packagee;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public abstract class UserController {
	
	@RequestMapping(method=RequestMethod.POST, value="/login")
	public Customer login(@RequestBody User user) {
		return UserService.validateUser(user.getUsername(), user.getPassword());
	}
	
	@RequestMapping("/isLoggedIn/{custID}")
	public Boolean isLoggedIn(@PathVariable String custID) {
		return UserService.isLoggedIn(custID);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/signup")
	public Boolean signUp(@RequestBody Customer customer) {
		CustomerService customerService = (CustomerService) UserServiceFactory.getUserService(customer.getUserType());
		return customerService.signUpUser(customer);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/updatePassword/{newPassword}")
	public boolean updatePassword(Customer customer, @PathVariable String newPassword) {
		Customer validUser = UserService.validateUser(customer.getUsername(), customer.getPassword());
		if (validUser != null) {
			return UserService.updatePassword(customer.getUsername(), newPassword);
		}
		return false;
	}
	
	abstract String getHomePage();
	abstract String getUserAccountPage(Customer customer);
}
