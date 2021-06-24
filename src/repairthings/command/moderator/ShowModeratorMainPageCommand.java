package repairthings.command.moderator;

import javax.servlet.http.HttpServletRequest;

import repairthings.command.ActionCommand;
import repairthings.command.result.ExecutionResult;
import repairthings.datalayer.User;
import repairthings.datalayer.data.OrderDAO;
import repairthings.datalayer.data.UserDAO;
import repairthings.helper.UserAccessHelper;
import repairthings.logic.OrderStatus;
import repairthings.logic.UserType;
import repairthings.resource.ConfigurationManager;

public class ShowModeratorMainPageCommand implements ActionCommand {

	@Override
	public ExecutionResult execute(HttpServletRequest request) {
		ExecutionResult result;
		User sessionUser = (User) request.getSession().getAttribute("user");
		if (UserAccessHelper.checkUser(sessionUser, UserType.MODERATOR)) {
			result = new ExecutionResult(ConfigurationManager.getProperty("path.page.moderatorMain"));
			UserDAO userDAO = (UserDAO) request.getServletContext().getAttribute("UserDAO");
			request.setAttribute("unlockedUsers", userDAO.getListUsers());
			OrderDAO orderDAO = (OrderDAO) request.getServletContext().getAttribute("OrderDAO");
			request.setAttribute("pendingOrders", orderDAO.getListOrdersByStatus(OrderStatus.CHECK_BY_MODERATOR.getId()));
			request.setAttribute("canceledOrders", orderDAO.getListOrdersByDeleted(true));
		} else {
			result = sessionUser.getUserType().getMainPage();
		}
		return result;
	}

}
