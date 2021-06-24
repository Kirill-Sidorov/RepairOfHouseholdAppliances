package repairthings.command.master;

import javax.servlet.http.HttpServletRequest;

import repairthings.command.ActionCommand;
import repairthings.command.result.ExecutionResult;
import repairthings.datalayer.Order;
import repairthings.datalayer.User;
import repairthings.datalayer.data.OrderDAO;
import repairthings.datalayer.data.OrderReplacementPartDAO;
import repairthings.helper.UserAccessHelper;
import repairthings.logic.UserType;
import repairthings.resource.ConfigurationManager;

public class ShowMasterOrderEditorCommand implements ActionCommand {

	@Override
	public ExecutionResult execute(HttpServletRequest request) {
		ExecutionResult result;
		User sessionUser = (User) request.getSession().getAttribute("user");
		if (UserAccessHelper.checkUser(sessionUser, UserType.MASTER)) {
			result = new ExecutionResult(ConfigurationManager.getProperty("path.page.masterOrderEditor"));
			int orderId = Integer.parseInt(request.getParameter("id"));
			OrderDAO orderDAO = (OrderDAO) request.getServletContext().getAttribute("OrderDAO");
			Order order = orderDAO.getOrderById(orderId);
			if (order != null) {
				request.setAttribute("order", order);
				OrderReplacementPartDAO orderReplacementPartDAO = (OrderReplacementPartDAO) request.getServletContext().getAttribute("OrderReplacementPartDAO");
				request.setAttribute("replacementParst", orderReplacementPartDAO.getOrderPartsWithAllParts(orderId));
			}
		} else {
			result = sessionUser.getUserType().getMainPage();
		}
		return result;
	}
}
