package repairthings.command.main;

import javax.servlet.http.HttpServletRequest;

import repairthings.command.ActionCommand;
import repairthings.command.result.ExecutionResult;
import repairthings.datalayer.Order;
import repairthings.datalayer.User;
import repairthings.datalayer.data.OrderDAO;
import repairthings.helper.UserAccessHelper;
import repairthings.logic.UserType;
import repairthings.resource.ConfigurationManager;

public class ShowOrderEditor implements ActionCommand {

	@Override
	public ExecutionResult execute(HttpServletRequest request) {
		ExecutionResult result;
		User sessionUser = (User) request.getSession().getAttribute("user");
		if (UserAccessHelper.checkUser(sessionUser, UserType.CUSTOMER, UserType.MODERATOR)) {
			result = new ExecutionResult(ConfigurationManager.getProperty("path.page.orderEditor"));
			int id = Integer.parseInt(request.getParameter("id"));
			OrderDAO orderDAO = (OrderDAO) request.getServletContext().getAttribute("OrderDAO");
			Order order = orderDAO.getOrderById(id);
			if (order != null) {
				request.setAttribute("order", order);
			}
		} else {
			result = sessionUser.getUserType().getMainPage();
		}
		return result;
	}

}
