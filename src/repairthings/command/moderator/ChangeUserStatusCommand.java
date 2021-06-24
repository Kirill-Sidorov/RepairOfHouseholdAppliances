package repairthings.command.moderator;

import javax.servlet.http.HttpServletRequest;

import repairthings.command.ActionCommand;
import repairthings.command.result.ExecutionResult;
import repairthings.datalayer.User;
import repairthings.datalayer.data.UserDAO;
import repairthings.helper.UserAccessHelper;
import repairthings.logic.UserType;

public class ChangeUserStatusCommand implements ActionCommand {

	@Override
	public ExecutionResult execute(HttpServletRequest request) {
		User sessionUser = (User) request.getSession().getAttribute("user");
		if (UserAccessHelper.checkUser(sessionUser, UserType.MODERATOR)) {
			int id = Integer.parseInt(request.getParameter("id"));
			String status = request.getParameter("status");
			if (status.equals("unlocked")) {
				status = "locked";
			} else {
				status = "unlocked";
			}
			UserDAO userDAO = (UserDAO) request.getServletContext().getAttribute("UserDAO");
			userDAO.setStatus(status, id);
		}
		return sessionUser.getUserType().getMainPage();
	}

}
