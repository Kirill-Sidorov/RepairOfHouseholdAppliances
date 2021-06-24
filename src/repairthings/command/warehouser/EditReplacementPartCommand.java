package repairthings.command.warehouser;

import javax.servlet.http.HttpServletRequest;

import repairthings.command.ActionCommand;
import repairthings.command.result.ExecutionResult;
import repairthings.datalayer.ReplacementPart;
import repairthings.datalayer.User;
import repairthings.datalayer.data.ReplacementPartDAO;
import repairthings.helper.UserAccessHelper;
import repairthings.logic.UserType;

public class EditReplacementPartCommand implements ActionCommand {

	@Override
	public ExecutionResult execute(HttpServletRequest request) {
		User sessionUser = (User) request.getSession().getAttribute("user");
		if (UserAccessHelper.checkUser(sessionUser, UserType.WAREHOUSER)) {
			ReplacementPart part = new ReplacementPart();
			
			part.setId(Integer.parseInt(request.getParameter("partId")));
			part.setName(request.getParameter("partName"));
			part.setCost(Integer.parseInt(request.getParameter("cost")));
			part.setInStock(Integer.parseInt(request.getParameter("inStock")));
			ReplacementPartDAO replacementPartDAO = (ReplacementPartDAO) request.getServletContext().getAttribute("ReplacementPartDAO");
			replacementPartDAO.updatePart(part);
		}
		return sessionUser.getUserType().getMainPage();
	}

}
