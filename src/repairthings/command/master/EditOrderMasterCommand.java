package repairthings.command.master;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import repairthings.command.ActionCommand;
import repairthings.command.result.ExecutionResult;
import repairthings.datalayer.OrderReplacementPart;
import repairthings.datalayer.User;
import repairthings.datalayer.data.OrderDAO;
import repairthings.datalayer.data.OrderReplacementPartDAO;
import repairthings.helper.UserAccessHelper;
import repairthings.logic.OrderStatus;
import repairthings.logic.UserType;

public class EditOrderMasterCommand implements ActionCommand {

	@Override
	public ExecutionResult execute(HttpServletRequest request) {
		User sessionUser = (User) request.getSession().getAttribute("user");
		if (UserAccessHelper.checkUser(sessionUser, UserType.MASTER)) {
			int orderId = Integer.parseInt(request.getParameter("orderId"));
			int costWork = Integer.parseInt(request.getParameter("costWork"));
			OrderDAO orderDAO = (OrderDAO) request.getServletContext().getAttribute("OrderDAO");
			String[] selectedParts = request.getParameterValues("selectedParts");
			String[] numberParts = request.getParameterValues("numberParts");
			if (selectedParts != null && numberParts != null) {
				OrderReplacementPartDAO orderReplacementPartDAO = (OrderReplacementPartDAO) request.getServletContext().getAttribute("OrderReplacementPartDAO");
				updateOrderPartsList(orderId, selectedParts, numberParts, orderReplacementPartDAO);
				orderDAO.setOrderStatus(OrderStatus.WAIT_REPLACEMENT_PARTS, orderId);
			}
			orderDAO.setOrderCostWork(orderId, costWork);
		} 
		return sessionUser.getUserType().getMainPage();
	}

	
	public void updateOrderPartsList(int orderId, String[] selectedParts, String[] numberParts, OrderReplacementPartDAO dao) {
		
		List<OrderReplacementPart> lastPartsList = dao.getPartsListByOrderId(orderId);
		
		Map <Integer, OrderReplacementPart> lastPartsMap = new HashMap<>();
		for (OrderReplacementPart part : lastPartsList) {
			lastPartsMap.put(part.getPartId(), part);
		}
		
		for (int i = 0; i < selectedParts.length; i++) {
			int partId = Integer.parseInt(selectedParts[i]);
			int number = Integer.parseInt(numberParts[i]);
			if (lastPartsMap.containsKey(partId)) {
				if (lastPartsMap.get(partId).getNumberParts() != number) {
					dao.updateOrderPart(orderId, partId, number);
				}
				lastPartsMap.remove(partId);
			} else {
				dao.createOrderPart(orderId, partId, number);
			}
		}
		
		if(!lastPartsMap.isEmpty()) {
			lastPartsMap.forEach((k, v) -> dao.deleteOrderPart(v.getOrderId(), v.getPartId()));
		}
	}
}
