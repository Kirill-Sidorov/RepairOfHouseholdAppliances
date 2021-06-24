package repairthings.command.main;

import javax.servlet.http.HttpServletRequest;

import repairthings.command.ActionCommand;
import repairthings.command.result.ExecutionResult;
import repairthings.datalayer.User;
import repairthings.datalayer.data.UserDAO;
import repairthings.resource.ConfigurationManager;
import repairthings.resource.MessageManager;

public class LoginCommand implements ActionCommand {
	@Override
	public ExecutionResult execute(HttpServletRequest request) {
		ExecutionResult result;

		String login = request.getParameter("login");
		String password = request.getParameter("password");

		UserDAO userDAO = (UserDAO) request.getServletContext().getAttribute("UserDAO");
		User user = userDAO.getUserByLogin(login);

		if (user != null) {
			if (user.getPassword().equals(password)) {
				user.setAuthorized(true);
				userDAO.setAuthorized(true, user.getId());
				request.getSession().setAttribute("user", user);
				result = user.getUserType().getMainPage();
			} else {
				result = new ExecutionResult(ConfigurationManager.getProperty("path.page.login"));
				request.setAttribute("errorLoginPassMessage", MessageManager.getProperty("message.loginerror"));
				request.setAttribute("login", login);
			}
		} else {
			result = new ExecutionResult(ConfigurationManager.getProperty("path.page.login"));
			request.setAttribute("errorLoginPassMessage", MessageManager.getProperty("message.loginerror"));
			request.setAttribute("login", login);
		}
		return result;
	}
}
