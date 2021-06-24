package repairthings.command.result;

public class ExecutionResult {
	private String page;
	private RedirectType redirectType; 
	
	public ExecutionResult(String page, RedirectType redirectType) {
		this.page = page;
		this.redirectType = redirectType;
	}
	public ExecutionResult(String page) {
		this.page = page;
		this.redirectType = RedirectType.FORWARD;
	}
	public String getPage() {
		return page;
	}
	public RedirectType getRedirectType() {
		return redirectType;
	}
}
