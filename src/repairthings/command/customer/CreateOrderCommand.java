package repairthings.command.customer;

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
import repairthings.resource.MessageManager;

public class CreateOrderCommand implements ActionCommand {

	@Override
	public ExecutionResult execute(HttpServletRequest request) {
		ExecutionResult result;
		User sessionUser = (User) request.getSession().getAttribute("user");
		if (UserAccessHelper.checkUser(sessionUser, UserType.CUSTOMER)) {
			String orderCreationPage = ConfigurationManager.getProperty("path.page.customerOrderCreation");
			
			Order order = new Order();
			
			order.setCustomerId(sessionUser.getId());
			order.setThingName(request.getParameter("thingName"));
			order.setDescription(request.getParameter("description"));
			order.setOrderStatusValue(OrderStatus.CHECK_BY_MODERATOR);
			
			if (!checkTextFields(order)) {
				result = new ExecutionResult(orderCreationPage); 
				request.setAttribute("errorOrderCreationMessage", MessageManager.getProperty("message.allFieldsNeedFilled"));
			} else {
				OrderDAO orderDAO = (OrderDAO) request.getServletContext().getAttribute("OrderDAO");
				orderDAO.createOrder(order);
				result = sessionUser.getUserType().getMainPage(); 
			}
		} else {
			result = sessionUser.getUserType().getMainPage();
		}
		return result;
	}
	
	private boolean checkTextFields(Order order) {
		return order.getThingName().length() != 0 && order.getDescription().length() != 0;
	}
}
