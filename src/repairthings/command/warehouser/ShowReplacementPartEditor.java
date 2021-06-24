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

public class ShowReplacementPartEditor implements ActionCommand  {

	@Override
	public ExecutionResult execute(HttpServletRequest request) {
		ExecutionResult result;
		User sessionUser = (User) request.getSession().getAttribute("user");
		if (UserAccessHelper.checkUser(sessionUser, UserType.WAREHOUSER)) {
			result = new ExecutionResult(ConfigurationManager.getProperty("path.page.warehouserEditPart"));
			int partId = Integer.parseInt(request.getParameter("id"));
			ReplacementPartDAO replacementPartDAO = (ReplacementPartDAO) request.getServletContext().getAttribute("ReplacementPartDAO");
			ReplacementPart part = replacementPartDAO.getPartById(partId);
			if (part != null) {
				request.setAttribute("part", part);
			}
		} else {
			result = sessionUser.getUserType().getMainPage();
		}
		return result;
	}

}
