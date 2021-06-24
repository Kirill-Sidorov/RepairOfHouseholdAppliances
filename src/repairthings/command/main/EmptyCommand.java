package repairthings.command.main;

import javax.servlet.http.HttpServletRequest;

import repairthings.command.ActionCommand;
import repairthings.command.result.ExecutionResult;
import repairthings.resource.ConfigurationManager;

public class EmptyCommand implements ActionCommand {

	@Override
	public ExecutionResult execute(HttpServletRequest request) {
		return new ExecutionResult(ConfigurationManager.getProperty("path.page.login"));
	}

}
