package repairthings.command.customer;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import repairthings.command.ActionCommand;
import repairthings.command.result.ExecutionResult;
import repairthings.datalayer.Order;
import repairthings.datalayer.OrderReplacementPart;
import repairthings.datalayer.User;
import repairthings.datalayer.data.OrderDAO;
import repairthings.datalayer.data.OrderReplacementPartDAO;
import repairthings.helper.UserAccessHelper;
import repairthings.logic.UserType;
import repairthings.resource.ConfigurationManager;

public class ShowFullCostCommand implements ActionCommand {

	@Override
	public ExecutionResult execute(HttpServletRequest request) {
		ExecutionResult result;
		User sessionUser = (User) request.getSession().getAttribute("user");
		if (UserAccessHelper.checkUser(sessionUser, UserType.CUSTOMER)) {
			result = new ExecutionResult(ConfigurationManager.getProperty("path.page.customerFullCost"));
			int orderId = Integer.parseInt(request.getParameter("id"));
			OrderDAO orderDAO = (OrderDAO) request.getServletContext().getAttribute("OrderDAO");
			Order order = orderDAO.getOrderById(orderId);
			OrderReplacementPartDAO orderReplacementPartDAO = (OrderReplacementPartDAO) request.getServletContext().getAttribute("OrderReplacementPartDAO");
			List<OrderReplacementPart> orderParts = orderReplacementPartDAO.getOrderPartsList(orderId);
			int fullCost = order.getCostWork();
			for (OrderReplacementPart part : orderParts) {
				fullCost += part.getNumberParts() * part.getCost();
			}
			request.setAttribute("order", order);
			request.setAttribute("parts", orderParts);
			request.setAttribute("fullCost", fullCost);
		} else {
			result = sessionUser.getUserType().getMainPage();
		}
		return result;
	}

}
