package repairthings.command.admin;

import javax.servlet.http.HttpServletRequest;

import repairthings.command.ActionCommand;
import repairthings.command.result.ExecutionResult;
import repairthings.datalayer.User;
import repairthings.datalayer.data.UserDAO;
import repairthings.helper.UserAccessHelper;
import repairthings.logic.UserType;

public class DeleteUserCommand implements ActionCommand {

	@Override
	public ExecutionResult execute(HttpServletRequest request) {
		User sessionUser = (User) request.getSession().getAttribute("user");
		if (UserAccessHelper.checkUser(sessionUser, UserType.ADMIN)) {
			int id = Integer.parseInt(request.getParameter("id"));
			UserDAO userDAO = (UserDAO) request.getServletContext().getAttribute("UserDAO");
			userDAO.deleteUserById(id);
		} 
		return sessionUser.getUserType().getMainPage();
	}

}
