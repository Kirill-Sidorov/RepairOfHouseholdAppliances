package repairthings.logic;

import repairthings.command.result.ExecutionResult;
import repairthings.command.result.RedirectType;

public enum UserType {
	ADMIN {
		{this.id = 1;}
		@Override
		public ExecutionResult getMainPage() {
			return new ExecutionResult("repairthings?command=show_admin_main_page", RedirectType.REDIRECT);
		}
	},	
	MODERATOR {
		{this.id = 2;}
		@Override
		public ExecutionResult getMainPage() {
			return new ExecutionResult("repairthings?command=show_moderator_main_page", RedirectType.REDIRECT);
		}
	},	
	CUSTOMER {
		{this.id = 3;}
		@Override
		public ExecutionResult getMainPage() {
			return new ExecutionResult("repairthings?command=show_customer_main_page", RedirectType.REDIRECT);
		}
	},
	MASTER {
		{this.id = 4;}
		@Override
		public ExecutionResult getMainPage() {
			return new ExecutionResult("repairthings?command=show_master_main_page", RedirectType.REDIRECT);
		}
	},
	WAREHOUSER {
		{this.id = 5;}
		@Override
		public ExecutionResult getMainPage() {
			return new ExecutionResult("repairthings?command=show_warehouser_main_page", RedirectType.REDIRECT);
		}
	};
	
	protected int id;
	
	public abstract ExecutionResult getMainPage();
	public int getId() {
		return id;
	};
}
