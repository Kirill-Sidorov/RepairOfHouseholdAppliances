package repairthings.datalayer.oracledb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import repairthings.datalayer.DAOFactory;
import repairthings.datalayer.data.OrderDAO;
import repairthings.datalayer.data.OrderReplacementPartDAO;
import repairthings.datalayer.data.ReplacementPartDAO;
import repairthings.datalayer.data.UserDAO;
import repairthings.resource.ConfigurationManager;

public class OracleDBDAOFactory implements DAOFactory {
	private static volatile OracleDBDAOFactory instance;
	private Connection connection;

	private OracleDBDAOFactory() {
	}

	public static OracleDBDAOFactory getInstance() throws ClassNotFoundException, SQLException {
		OracleDBDAOFactory factory = instance;
		if (instance == null) {
			synchronized (OracleDBDAOFactory.class) {
				instance = factory = new OracleDBDAOFactory();
				factory.connected();
			}
		}
		return factory;
	}

	private void connected() throws ClassNotFoundException, SQLException {
		String drivername = ConfigurationManager.getProperty("jdbc.driver.drivername");
		String url = ConfigurationManager.getProperty("jdbc.driver.url");
		String user = ConfigurationManager.getProperty("jdbc.user");
		String password = ConfigurationManager.getProperty("jdbc.password");
		
		Class.forName(drivername);
		connection = DriverManager.getConnection(url, user, password);
		System.out.println("Connected to oracle DB!");
	}

	@Override
	public OrderDAO getOrderDAO() {
		return new OracleOrderDAO(connection);
	}

	@Override
	public OrderReplacementPartDAO getOrderReplacementPartDAO() {
		return new OracleOrderReplacementPartDAO(connection);
	}

	@Override
	public ReplacementPartDAO getReplacementPartDAO() {
		return new OracleReplacementPartDAO(connection);
	}

	@Override
	public UserDAO getUserDAO() {
		return new OracleUserDAO(connection);
	}
}
