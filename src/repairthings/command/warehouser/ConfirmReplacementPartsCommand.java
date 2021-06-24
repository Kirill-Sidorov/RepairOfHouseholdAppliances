package repairthings.command.warehouser;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import repairthings.command.ActionCommand;
import repairthings.command.result.ExecutionResult;
import repairthings.datalayer.OrderReplacementPart;
import repairthings.datalayer.User;
import repairthings.datalayer.data.OrderDAO;
import repairthings.datalayer.data.OrderReplacementPartDAO;
import repairthings.datalayer.data.ReplacementPartDAO;
import repairthings.helper.UserAccessHelper;
import repairthings.logic.OrderStatus;
import repairthings.logic.UserType;

public class ConfirmReplacementPartsCommand implements ActionCommand {

	@Override
	public ExecutionResult execute(HttpServletRequest request) {
		User sessionUser = (User) request.getSession().getAttribute("user");
		if (UserAccessHelper.checkUser(sessionUser, UserType.WAREHOUSER)) {
			int orderId = Integer.parseInt(request.getParameter("orderId"));
			
			OrderReplacementPartDAO orderReplacementPartDAO = (OrderReplacementPartDAO) request.getServletContext().getAttribute("OrderReplacementPartDAO");
			
			ReplacementPartDAO replacementPartDAO = (ReplacementPartDAO) request.getServletContext().getAttribute("ReplacementPartDAO");
			List<OrderReplacementPart> orderParts = orderReplacementPartDAO.getOrderPartsWithNumberPartsInStock(orderId);
			System.out.println(orderParts.size());
			for (OrderReplacementPart part : orderParts) {
				int numberParts = part.getInStock() - part.getNumberParts();
				System.out.print(part.getPartId());
				replacementPartDAO.updateInStock(part.getPartId(), numberParts);;
			}
			OrderDAO orderDAO = (OrderDAO) request.getServletContext().getAttribute("OrderDAO");
			orderDAO.setOrderStatus(OrderStatus.REPAIR_PROCESS, orderId);
		}
		return sessionUser.getUserType().getMainPage();
	}
}
