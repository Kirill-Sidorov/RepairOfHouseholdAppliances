package repairthings.command.warehouser;

import javax.servlet.http.HttpServletRequest;

import repairthings.command.ActionCommand;
import repairthings.command.result.ExecutionResult;
import repairthings.datalayer.User;
import repairthings.datalayer.data.ReplacementPartDAO;
import repairthings.helper.UserAccessHelper;
import repairthings.logic.UserType;

public class DeleteReplacementPartCommand implements ActionCommand {

	@Override
	public ExecutionResult execute(HttpServletRequest request) {
		User sessionUser = (User) request.getSession().getAttribute("user");
		if (UserAccessHelper.checkUser(sessionUser, UserType.WAREHOUSER)) {
			int id = Integer.parseInt(request.getParameter("id"));
			ReplacementPartDAO replacementPartDAO = (ReplacementPartDAO) request.getServletContext().getAttribute("ReplacementPartDAO");
			replacementPartDAO.deletePart(id);
		}
		return sessionUser.getUserType().getMainPage();
	}

}
