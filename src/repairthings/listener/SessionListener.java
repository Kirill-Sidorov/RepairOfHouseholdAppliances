package repairthings.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import repairthings.datalayer.User;
import repairthings.datalayer.data.UserDAO;

public class SessionListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent event) {
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		User sessionUser = (User) event.getSession().getAttribute("user");
		UserDAO userDAO = (UserDAO) event.getSession().getServletContext().getAttribute("UserDAO");
		userDAO.setAuthorized(false, sessionUser.getId());
	}

}
