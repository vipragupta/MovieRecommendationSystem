package mrsapi.packagee;

import org.springframework.web.bind.annotation.RequestMapping;

public abstract class UserController {
	
	@RequestMapping("/login")	//TODO
	public Customer login(String username, String password) {
		return UserService.validateUser(username, password);
	}
	
	@RequestMapping("/isLoggedIn/{custID}")
	public Boolean isLoggedIn(String customerID) {
		return UserService.isLoggedIn(customerID);
	}
	
	public Boolean signUp(Customer customer) {
		CustomerService customerService = (CustomerService) UserServiceFactory.getUserService(customer.getUserType());
		return customerService.signUpUser(customer);
	}
	
	public boolean updatePassword(String username, String oldPassword, String newPassword) {
		Customer validUser = UserService.validateUser(username, oldPassword);
		if (validUser != null) {
			return UserService.updatePassword(username, newPassword);
		}
		return false;
	}
	
	abstract String getHomePage();
	abstract String getUserAccountPage();
}
