package repairthings.command.warehouser;

import javax.servlet.http.HttpServletRequest;

import repairthings.command.ActionCommand;
import repairthings.command.result.ExecutionResult;
import repairthings.datalayer.ReplacementPart;
import repairthings.datalayer.User;
import repairthings.datalayer.data.ReplacementPartDAO;
import repairthings.helper.UserAccessHelper;
import repairthings.logic.UserType;
import repairthings.resource.ConfigurationManager;
import repairthings.resource.MessageManager;

public class CreateReplacementPartCommand implements ActionCommand  {

	@Override
	public ExecutionResult execute(HttpServletRequest request) {
		ExecutionResult result;
		User sessionUser = (User) request.getSession().getAttribute("user");
		if (UserAccessHelper.checkUser(sessionUser, UserType.WAREHOUSER)) {
			String partCreationPage = ConfigurationManager.getProperty("path.page.warehouserCreatePart");
			
			ReplacementPart part = new ReplacementPart();
			
			part.setName(request.getParameter("partName"));
			part.setCost(Integer.parseInt(request.getParameter("cost")));
			part.setInStock(Integer.parseInt(request.getParameter("inStock")));
			
			if (!checkTextFields(part)) {
				result = new ExecutionResult(partCreationPage); 
				request.setAttribute("errorPartCreationMessage", MessageManager.getProperty("message.allFieldsNeedFilled"));
				request.setAttribute("part", part);
			} else {
				ReplacementPartDAO replacementPartDAO = (ReplacementPartDAO) request.getServletContext().getAttribute("ReplacementPartDAO");
				replacementPartDAO.createPart(part);;
				result = sessionUser.getUserType().getMainPage(); 
			}
		} else {
			result = sessionUser.getUserType().getMainPage();
		}
		return result;
	}
	
	private boolean checkTextFields(ReplacementPart part) {
		return part.getName().length() != 0 && part.getCost() > 0;
	}

}
