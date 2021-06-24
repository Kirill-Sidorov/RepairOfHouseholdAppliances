package repairthings.command;

import javax.servlet.http.HttpServletRequest;

import repairthings.command.result.ExecutionResult;

public interface ActionCommand {
	ExecutionResult execute(HttpServletRequest request);
}
