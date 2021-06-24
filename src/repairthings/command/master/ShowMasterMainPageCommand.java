package repairthings.command.master;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import repairthings.command.ActionCommand;
import repairthings.command.result.ExecutionResult;
import repairthings.datalayer.Order;
import repairthings.datalayer.User;
import repairthings.datalayer.data.OrderDAO;
import repairthings.helper.UserAccessHelper;
import repairthings.logic.OrderStatus;
import repairthings.logic.UserType;
import repairthings.resource.ConfigurationManager;

public class ShowMasterMainPageCommand implements ActionCommand {

	@Override
	public ExecutionResult execute(HttpServletRequest request) {
		ExecutionResult result;
		User sessionUser = (User) request.getSession().getAttribute("user");
		if (UserAccessHelper.checkUser(sessionUser, false, UserType.MASTER)) {
			result = new ExecutionResult(ConfigurationManager.getProperty("path.page.masterMain"));
			OrderDAO orderDAO = (OrderDAO) request.getServletContext().getAttribute("OrderDAO");
			request.setAttribute("availableOrders", orderDAO.getListOrdersByStatus(OrderStatus.WAIT_MASTER.getId()));
			List<Order> orders = orderDAO.getListOrdersByMasterId(sessionUser.getId(), OrderStatus.WAIT_REPLACEMENT_PARTS);
			orders.addAll(orderDAO.getListOrdersByMasterId(sessionUser.getId(), OrderStatus.CREATION_PARTS_LIST));
			orders.addAll(orderDAO.getListOrdersByMasterId(sessionUser.getId(), OrderStatus.REPAIR_PROCESS));
			request.setAttribute("uncompletedOrders", orders);
			request.setAttribute("completedOrders", orderDAO.getListOrdersByMasterId(sessionUser.getId(), OrderStatus.COMPLETED));
		} else {
			result = sessionUser.getUserType().getMainPage();
		}
		return result;
	}
}
