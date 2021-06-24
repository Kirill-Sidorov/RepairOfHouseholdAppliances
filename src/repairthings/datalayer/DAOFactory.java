package repairthings.datalayer;

import repairthings.datalayer.data.OrderDAO;
import repairthings.datalayer.data.OrderReplacementPartDAO;
import repairthings.datalayer.data.ReplacementPartDAO;
import repairthings.datalayer.data.UserDAO;

public interface DAOFactory {
	OrderDAO getOrderDAO();
	OrderReplacementPartDAO getOrderReplacementPartDAO();
	ReplacementPartDAO getReplacementPartDAO();
	UserDAO getUserDAO();
}
