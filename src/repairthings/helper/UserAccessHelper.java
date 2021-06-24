package repairthings.helper;

import repairthings.datalayer.User;
import repairthings.logic.UserType;

public class UserAccessHelper {
	
	private UserAccessHelper() {}
	
	public static boolean checkUser(User user, UserType ...userTypes) { 
		boolean isAllowed = false;
		for (UserType type : userTypes) {
			if (user.getUserType() == type) {
				isAllowed = true;
				break;
			}
		}
		if (user.getStatus().equals("locked")) {
			isAllowed = false;
		}
		return isAllowed;
	}
	
	public static boolean checkUser(User user, boolean isLockCheck, UserType ...userTypes) { 
		boolean isAllowed = false;
		for (UserType type : userTypes) {
			if (user.getUserType() == type) {
				isAllowed = true;
				break;
			}
		}
		if (isLockCheck) {
			if (user.getStatus().equals("locked")) {
				isAllowed = false;
			}
		}
		return isAllowed;
	}
}
