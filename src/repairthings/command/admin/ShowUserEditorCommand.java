package repairthings.command.admin;

import javax.servlet.http.HttpServletRequest;

import repairthings.command.ActionCommand;
import repairthings.command.result.ExecutionResult;
import repairthings.datalayer.User;
import repairthings.datalayer.data.UserDAO;
import repairthings.helper.UserAccessHelper;
import repairthings.logic.UserType;
import repairthings.resource.ConfigurationManager;

public class ShowUserEditorCommand implements ActionCommand {

	@Override
	public ExecutionResult execute(HttpServletRequest request) {
		ExecutionResult result;
		User sessionUser = (User) request.getSession().getAttribute("user");
		if (UserAccessHelper.checkUser(sessionUser, UserType.ADMIN)) {
			result = new ExecutionResult(ConfigurationManager.getProperty("path.page.adminEditUser"));
			int id = Integer.parseInt(request.getParameter("id"));
			UserDAO userDAO = (UserDAO) request.getServletContext().getAttribute("UserDAO");
			User user = userDAO.getUserById(id);
			if (user != null) {
				request.setAttribute("user", user);
			}
		} else {
			result = sessionUser.getUserType().getMainPage();
		}
		return result;
	}

}
