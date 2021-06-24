package repairthings.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import repairthings.command.factory.ActionFactory;
import repairthings.command.result.ExecutionResult;
import repairthings.datalayer.DAOFactory;
import repairthings.datalayer.DBType;
import repairthings.datalayer.User;
import repairthings.datalayer.data.UserDAO;
/**
 * Main application controller
 * 
 * @author Sidorov Kirill
 *
 */
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ActionFactory client;
	
	@Override
	public void init() throws ServletException { 
		client = new ActionFactory();
		DAOFactory factory = DBType.ORACLE.getDAOFactory();
		getServletContext().setAttribute("OrderDAO", factory.getOrderDAO());
		getServletContext().setAttribute("OrderReplacementPartDAO", factory.getOrderReplacementPartDAO());
		getServletContext().setAttribute("ReplacementPartDAO", factory.getReplacementPartDAO());
		getServletContext().setAttribute("UserDAO", factory.getUserDAO());
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	
	/**
	 * Process the received request 
	 * and create a response
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ExecutionResult result = client.defineCommand(request).execute(request);
		
		switch (result.getRedirectType()) {
			case FORWARD:
				getServletContext().getRequestDispatcher(result.getPage()).forward(request, response);
				break;
			case REDIRECT:
				response.sendRedirect(result.getPage());
				break;
		}
	}
	@Override
	public void destroy() {
		UserDAO userDAO = (UserDAO) getServletContext().getAttribute("UserDAO");
		List<User> users = userDAO.getListUsers();
		for (User user : users) {
			userDAO.setAuthorized(false, user.getId());
		}
	}
}
