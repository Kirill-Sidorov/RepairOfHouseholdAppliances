package repairthings.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import repairthings.command.CommandEnum;
import repairthings.datalayer.User;
import repairthings.resource.ConfigurationManager;

public class LoginFilter implements Filter  {
	
	@Override
	public void init(FilterConfig config) throws ServletException {
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		String action = request.getParameter("command");
		if (action != null) {
			CommandEnum currentEnum = CommandEnum.valueOf(action.toUpperCase());
			User sessionUser = (User) ((HttpServletRequest) request).getSession().getAttribute("user");
			if (currentEnum != CommandEnum.LOGIN && sessionUser == null) {
				((HttpServletRequest) request).getRequestDispatcher(ConfigurationManager.getProperty("path.page.login"))
					.forward(request, response);
			} else {
				chain.doFilter(request, response);
			}
		} else {
			chain.doFilter(request, response);
		}
	}
	
	@Override
	public void destroy() {
	}
}
