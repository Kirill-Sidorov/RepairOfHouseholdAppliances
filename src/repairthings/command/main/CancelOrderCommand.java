package repairthings.command.main;

import javax.servlet.http.HttpServletRequest;

import repairthings.command.ActionCommand;
import repairthings.command.result.ExecutionResult;
import repairthings.datalayer.User;
import repairthings.datalayer.data.OrderDAO;
import repairthings.helper.UserAccessHelper;
import repairthings.logic.UserType;

public class CancelOrderCommand implements ActionCommand {

	@Override
	public ExecutionResult execute(HttpServletRequest request) {
		ExecutionResult result;
		User sessionUser = (User) request.getSession().getAttribute("user");
		if (UserAccessHelper.checkUser(sessionUser, UserType.CUSTOMER, UserType.MASTER)) {
			int id = Integer.parseInt(request.getParameter("id"));
			OrderDAO orderDAO = (OrderDAO) request.getServletContext().getAttribute("OrderDAO");
			orderDAO.setDeleted(true, id);
			result = sessionUser.getUserType().getMainPage();
		} else {
			result = sessionUser.getUserType().getMainPage();
		}
		return result;
	}

}
