package repairthings.command.admin;

import javax.servlet.http.HttpServletRequest;

import repairthings.command.ActionCommand;
import repairthings.command.result.ExecutionResult;
import repairthings.datalayer.User;
import repairthings.datalayer.data.UserDAO;
import repairthings.helper.UserAccessHelper;
import repairthings.logic.UserType;

public class UpdateUserCommand implements ActionCommand {

	@Override
	public ExecutionResult execute(HttpServletRequest request) {
		ExecutionResult result;
		User sessionUser = (User) request.getSession().getAttribute("user");
		if (UserAccessHelper.checkUser(sessionUser, UserType.ADMIN)) {
			User user = new User();
			
			user.setId(Integer.parseInt(request.getParameter("userId")));
			user.setFirstName(request.getParameter("firstName"));
			user.setLastName(request.getParameter("lastName"));
			user.setPhone(request.getParameter("phoneNumber"));
			user.setLogin(request.getParameter("login"));
			
			UserDAO userDAO = (UserDAO) request.getServletContext().getAttribute("UserDAO");
			userDAO.updateUser(user);
			result = sessionUser.getUserType().getMainPage();

		} else {
			result = sessionUser.getUserType().getMainPage();
		}
		return result;
	}

}
