package repairthings.command;

import repairthings.command.admin.DeleteUserCommand;
import repairthings.command.admin.RegisterUserCommand;
import repairthings.command.admin.ShowAdminMainPageCommand;
import repairthings.command.admin.ShowRegistrationCommand;
import repairthings.command.admin.ShowUserEditorCommand;
import repairthings.command.admin.UpdateUserCommand;
import repairthings.command.customer.CreateOrderCommand;
import repairthings.command.customer.ShowCustomerMainPageCommand;
import repairthings.command.customer.ShowFullCostCommand;
import repairthings.command.customer.ShowOrderCreationCommand;
import repairthings.command.main.CancelOrderCommand;
import repairthings.command.main.EditOrderCommand;
import repairthings.command.main.LoginCommand;
import repairthings.command.main.LogoutCommand;
import repairthings.command.main.ShowOrderEditor;
import repairthings.command.master.ConfirmOrderExecutionCommand;
import repairthings.command.master.EditOrderMasterCommand;
import repairthings.command.master.ShowMasterMainPageCommand;
import repairthings.command.master.ShowMasterOrderEditorCommand;
import repairthings.command.master.TakeOrderCommand;
import repairthings.command.moderator.ChangeUserStatusCommand;
import repairthings.command.moderator.CheckOrderCommand;
import repairthings.command.moderator.DeleteOrderCommand;
import repairthings.command.moderator.ShowModeratorMainPageCommand;
import repairthings.command.warehouser.ConfirmReplacementPartsCommand;
import repairthings.command.warehouser.CreateReplacementPartCommand;
import repairthings.command.warehouser.DeleteReplacementPartCommand;
import repairthings.command.warehouser.EditReplacementPartCommand;
import repairthings.command.warehouser.ShowOrderPartsCommand;
import repairthings.command.warehouser.ShowReplacementPartCreationCommand;
import repairthings.command.warehouser.ShowReplacementPartEditor;
import repairthings.command.warehouser.ShowWarehouserMainPageCommand;

public enum CommandEnum {
	LOGIN {
		{this.command = new LoginCommand();}
	},
	LOGOUT {
		{this.command = new LogoutCommand();}
	},
	SHOW_REGISTRATION {
		{this.command = new ShowRegistrationCommand();}
	},
	SHOW_ORDER_CREATION {
		{this.command = new ShowOrderCreationCommand();}
	},
	SHOW_USER_EDITOR {
		{this.command = new ShowUserEditorCommand();}
	},
	REGISTER_USER {
		{this.command = new RegisterUserCommand();}
	},
	CREATE_ORDER {
		{this.command = new CreateOrderCommand();}
	},
	SHOW_MASTER_ORDER_EDITOR {
		{this.command = new ShowMasterOrderEditorCommand();}
	},
	CHANGE_USER_STATUS {
		{this.command = new ChangeUserStatusCommand();}
	},
	UPDATE_USER {
		{this.command = new UpdateUserCommand();}
	},
	DELETE_USER {
		{this.command = new DeleteUserCommand();}
	},
	SHOW_ORDER_EDITOR {
		{this.command = new ShowOrderEditor();}
	},
	EDIT_ORDER {
		{this.command = new EditOrderCommand();}
	},
	CANCEL_ORDER {
		{this.command = new CancelOrderCommand();}
	},
	DELETE_ORDER {
		{this.command = new DeleteOrderCommand();}
	},
	CHECK_ORDER {
		{this.command = new CheckOrderCommand();}
	},
	TAKE_ORDER {
		{this.command = new TakeOrderCommand();}
	},
	EDIT_ORDER_MASTER {
		{this.command = new EditOrderMasterCommand();}
	},
	SHOW_ORDER_PARTS {
		{this.command = new ShowOrderPartsCommand();}
	},
	SHOW_REPLACEMENT_PART_CREATION {
		{this.command = new ShowReplacementPartCreationCommand();}
	},
	CREATE_REPLACEMENT_PART {
		{this.command = new CreateReplacementPartCommand();}
	},
	SHOW_REPLACEMENT_PART_EDITOR {
		{this.command = new ShowReplacementPartEditor();}
	},
	EDIT_REPLACEMENT_PART {
		{this.command = new EditReplacementPartCommand();}
	},
	DELETE_REPLACEMENT_PART {
		{this.command = new DeleteReplacementPartCommand();}
	},
	CONFIRM_REPLACEMENT_PARTS {;
		{this.command = new ConfirmReplacementPartsCommand();}
	},
	CONFIRM_ORDER_EXECUTION {
		{this.command = new ConfirmOrderExecutionCommand();}
	},
	SHOW_FULL_COST {
		{this.command = new ShowFullCostCommand();}
	},
	SHOW_ADMIN_MAIN_PAGE {
		{this.command = new ShowAdminMainPageCommand();}
	},
	SHOW_CUSTOMER_MAIN_PAGE {
		{this.command = new ShowCustomerMainPageCommand();}
	}, 
	SHOW_MASTER_MAIN_PAGE {
		{this.command = new ShowMasterMainPageCommand();}
	},
	SHOW_MODERATOR_MAIN_PAGE {
		{this.command = new ShowModeratorMainPageCommand();}
	},
	SHOW_WAREHOUSER_MAIN_PAGE {
		{this.command = new ShowWarehouserMainPageCommand();}
	};
	protected ActionCommand command;
	public ActionCommand getCurrentCommand() { 
		return command;
	}
}
