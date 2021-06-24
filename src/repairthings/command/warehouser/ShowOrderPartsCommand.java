package repairthings.command.warehouser;

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

public class ShowOrderPartsCommand implements ActionCommand {

	@Override
	public ExecutionResult execute(HttpServletRequest request) {
		ExecutionResult result;
		User sessionUser = (User) request.getSession().getAttribute("user");
		if (UserAccessHelper.checkUser(sessionUser, UserType.WAREHOUSER)) {
			result = new ExecutionResult(ConfigurationManager.getProperty("path.page.warehouserOrderParts"));
			int orderId = Integer.parseInt(request.getParameter("id"));
			OrderDAO orderDAO = (OrderDAO) request.getServletContext().getAttribute("OrderDAO");
			Order order = orderDAO.getOrderById(orderId);
			if (order != null) {
				request.setAttribute("order", order);
				OrderReplacementPartDAO orderReplacementPartDAO = (OrderReplacementPartDAO) request.getServletContext().getAttribute("OrderReplacementPartDAO");
				List<OrderReplacementPart> parts = orderReplacementPartDAO.getOrderPartsWithNumberPartsInStock(orderId);
				request.setAttribute("replacementParst", parts);
				request.setAttribute("isAllowedConfirm", checkAllowedConfirm(parts));
			}
		} else {
			result = sessionUser.getUserType().getMainPage();
		}
		return result;
	}
	
	public boolean checkAllowedConfirm(List<OrderReplacementPart> parts) {
		for (OrderReplacementPart part : parts) {
			if (part.getNumberParts() > part.getInStock()) {
				return true;
			}
		}
		return false;
	}
}
