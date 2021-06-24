package repairthings.command.admin;

import javax.servlet.http.HttpServletRequest;

import repairthings.command.ActionCommand;
import repairthings.command.result.ExecutionResult;
import repairthings.datalayer.User;
import repairthings.datalayer.data.UserDAO;
import repairthings.helper.UserAccessHelper;
import repairthings.logic.UserType;
import repairthings.resource.ConfigurationManager;
import repairthings.resource.MessageManager;

public class RegisterUserCommand implements ActionCommand {

	@Override
	public ExecutionResult execute(HttpServletRequest request) {
		ExecutionResult result;
		User sessionUser = (User) request.getSession().getAttribute("user");
		if (UserAccessHelper.checkUser(sessionUser, UserType.ADMIN)) {
			String registrationPage = ConfigurationManager.getProperty("path.page.registration");
			UserDAO userDAO = (UserDAO) request.getServletContext().getAttribute("UserDAO");
			
			User user = new User();
			
			user.setFirstName(request.getParameter("firstName"));
			user.setLastName(request.getParameter("lastName"));
			user.setPhone(request.getParameter("phoneNumber"));
			user.setUserType(request.getParameter("userType"));
			user.setLogin(request.getParameter("login"));
			user.setPassword(request.getParameter("password"));
			String repeatPass = request.getParameter("passwordRepeat");
			
			User existUser = userDAO.getUserByLogin(user.getLogin());
			
			if (existUser != null) {
				result = new ExecutionResult(registrationPage); 
				request.setAttribute("errorRegistrationMessage", MessageManager.getProperty("message.wrongLogin"));
				user.setLogin("");
				request.setAttribute("createdUser", user);
			} else if (!checkTextFields(user)) {
				result = new ExecutionResult(registrationPage); 
				request.setAttribute("errorRegistrationMessage", MessageManager.getProperty("message.allFieldsNeedFilled"));
				request.setAttribute("createdUser", user);
			} else if (!checkPass(user.getPassword(), repeatPass)) {
				result = new ExecutionResult(registrationPage); 
				request.setAttribute("errorRegistrationMessage", MessageManager.getProperty("message.wrongPassword"));
				request.setAttribute("createdUser", user);
			} else {
				userDAO.createUser(user);
				result = sessionUser.getUserType().getMainPage(); 
			}
		} else {
			result = sessionUser.getUserType().getMainPage();
		}
		return result;
	}
	
	private boolean checkTextFields(User user) {
		return user.getFirstName().length() != 0 
				&& user.getLastName().length() != 0 
				&& user.getPhone().length() != 0 
				&& user.getLogin().length() != 0;
	}
	
	private boolean checkPass(String pass, String repeatPass) {
		return (pass.length() >= 3 && pass.equals(repeatPass));
	}
}
