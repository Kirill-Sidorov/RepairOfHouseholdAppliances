package repairthings.command.warehouser;

import javax.servlet.http.HttpServletRequest;

import repairthings.command.ActionCommand;
import repairthings.command.result.ExecutionResult;
import repairthings.datalayer.User;
import repairthings.datalayer.data.OrderDAO;
import repairthings.datalayer.data.ReplacementPartDAO;
import repairthings.helper.UserAccessHelper;
import repairthings.logic.OrderStatus;
import repairthings.logic.UserType;
import repairthings.resource.ConfigurationManager;

public class ShowWarehouserMainPageCommand implements ActionCommand {

	@Override
	public ExecutionResult execute(HttpServletRequest request) {
		ExecutionResult result;
		User sessionUser = (User) request.getSession().getAttribute("user");
		if (UserAccessHelper.checkUser(sessionUser, false, UserType.WAREHOUSER)) {
			result = new ExecutionResult(ConfigurationManager.getProperty("path.page.warehouserMain"));
			
			OrderDAO orderDAO = (OrderDAO) request.getServletContext().getAttribute("OrderDAO");
			
			ReplacementPartDAO replacementPartDAO = (ReplacementPartDAO) request.getServletContext().getAttribute("ReplacementPartDAO");
			
			request.setAttribute("replacementParst", replacementPartDAO.getReplacementPartsList());
			request.setAttribute("orderWaitParts", orderDAO.getListOrdersByStatus(OrderStatus.WAIT_REPLACEMENT_PARTS.getId()));
		} else {
			result = sessionUser.getUserType().getMainPage();
		}
		return result;
	}

}
