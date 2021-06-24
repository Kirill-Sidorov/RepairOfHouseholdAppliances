package repairthings.command.customer;

import javax.servlet.http.HttpServletRequest;

import repairthings.command.ActionCommand;
import repairthings.command.result.ExecutionResult;
import repairthings.datalayer.User;
import repairthings.datalayer.data.OrderDAO;
import repairthings.helper.UserAccessHelper;
import repairthings.logic.UserType;
import repairthings.resource.ConfigurationManager;

public class ShowCustomerMainPageCommand implements ActionCommand {

	@Override
	public ExecutionResult execute(HttpServletRequest request) {
		ExecutionResult result;
		User sessionUser = (User) request.getSession().getAttribute("user");
		if (UserAccessHelper.checkUser(sessionUser, false, UserType.CUSTOMER)) {
			result = new ExecutionResult(ConfigurationManager.getProperty("path.page.customerMain"));
			OrderDAO orderDAO = (OrderDAO) request.getServletContext().getAttribute("OrderDAO");
			request.setAttribute("orders", orderDAO.getListOrdersByCustomerId(sessionUser.getId(), false));
		} else {
			result = sessionUser.getUserType().getMainPage();
		}
		return result;
	}

}
