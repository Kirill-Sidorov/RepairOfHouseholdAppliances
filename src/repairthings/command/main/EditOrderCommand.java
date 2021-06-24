package repairthings.command.main;

import javax.servlet.http.HttpServletRequest;

import repairthings.command.ActionCommand;
import repairthings.command.result.ExecutionResult;
import repairthings.datalayer.Order;
import repairthings.datalayer.User;
import repairthings.datalayer.data.OrderDAO;
import repairthings.helper.UserAccessHelper;
import repairthings.logic.OrderStatus;
import repairthings.logic.UserType;

public class EditOrderCommand implements ActionCommand {

	@Override
	public ExecutionResult execute(HttpServletRequest request) {
		User sessionUser = (User) request.getSession().getAttribute("user");
		if (UserAccessHelper.checkUser(sessionUser, UserType.CUSTOMER, UserType.MODERATOR)) {
			int orderId = Integer.parseInt(request.getParameter("orderId"));
			Order order = new Order();
			
			order.setId(orderId);
			order.setThingName(request.getParameter("thingName"));
			order.setDescription(request.getParameter("description"));
			
			OrderDAO orderDAO = (OrderDAO) request.getServletContext().getAttribute("OrderDAO");
			orderDAO.updateOrder(order);
			
			if (sessionUser.getUserType() == UserType.MODERATOR) {
				orderDAO.setOrderStatus(OrderStatus.WAIT_MASTER, orderId);
			}
		} 
		return sessionUser.getUserType().getMainPage();
	}

}
