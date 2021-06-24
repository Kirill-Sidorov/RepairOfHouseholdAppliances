package repairthings.command.main;

import javax.servlet.http.HttpServletRequest;

import repairthings.command.ActionCommand;
import repairthings.command.result.ExecutionResult;
import repairthings.datalayer.User;
import repairthings.datalayer.data.UserDAO;
import repairthings.resource.ConfigurationManager;

public class LogoutCommand implements ActionCommand {

	@Override
	public ExecutionResult execute(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		if (user != null) {
			UserDAO userDAO = (UserDAO) request.getServletContext().getAttribute("UserDAO");
			userDAO.setAuthorized(false, user.getId());
			request.getSession().invalidate();
		}
		return new ExecutionResult(ConfigurationManager.getProperty("path.page.login"));
	}

}
