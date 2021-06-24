package repairthings.datalayer;

import java.sql.SQLException;

import repairthings.datalayer.oracledb.OracleDBDAOFactory;

public enum DBType {
	ORACLE {
		@Override
		public DAOFactory getDAOFactory() {
			DAOFactory oracleDBDAOFactory = null;
			try {
				oracleDBDAOFactory = OracleDBDAOFactory.getInstance();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return oracleDBDAOFactory;
		}
	};
	public abstract DAOFactory getDAOFactory();
}
