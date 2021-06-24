package repairthings.datalayer.oracledb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import repairthings.datalayer.User;
import repairthings.datalayer.data.UserDAO;
import repairthings.resource.ConfigurationManager;

public class OracleUserDAO implements UserDAO {
	
	private Connection connection;
	
	public OracleUserDAO(Connection connection) {
		this.connection = connection;
	}
	
	@Override
	public void createUser(User user) {
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection
					.prepareStatement(ConfigurationManager.getProperty("query.user.create"));
			preparedStatement.setString(1, user.getLogin());
			preparedStatement.setString(2, user.getPassword());
			preparedStatement.setInt(3, user.getUserType().getId());
			preparedStatement.setString(4, user.getFirstName());
			preparedStatement.setString(5, user.getLastName());
			preparedStatement.setString(6, user.getPhone());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.err.println("SQL exception (request or table failed): " + e);
		} finally {
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					System.err.println("Prepared statement can not close: " + e);
				}
			}
		}
	}

	@Override
	public List<User> getListUsers() {
		List<User> users = new ArrayList<User>();
		Statement statement = null;
		try {
			statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(ConfigurationManager.getProperty("query.user.allUsers"));
			while (resultSet.next()) {
				User user = new User();
				setUser(user, resultSet);
				users.add(user);
			}
		} catch (SQLException e) {
			System.err.println("SQL exception (request or table failed): " + e);
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					System.err.println("Statement can not close: " + e);
				}
			}
		}
		return users;
	}
	
	@Override
	public List<User> getListUsersByStatus(String status) {
		List<User> users = new ArrayList<User>();
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection
					.prepareStatement(ConfigurationManager.getProperty("query.user.getListUsersByStatus"));
			preparedStatement.setString(1, status);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				User user = new User();
				setUser(user, resultSet);
				users.add(user);
			}
		} catch (SQLException e) {
			System.err.println("SQL exception (request or table failed): " + e);
		} finally {
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					System.err.println("Prepared statement can not close: " + e);
				}
			}
		}
		return users;
	}

	@Override
	public User getUserByLogin(String login) {
		User user = null;
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection
					.prepareStatement(ConfigurationManager.getProperty("query.user.getUserByLogin"));
			preparedStatement.setString(1, login);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				user = new User();
				setUser(user, resultSet);
			}
		} catch (SQLException e) {
			System.err.println("SQL exception (request or table failed): " + e);
		} finally {
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					System.err.println("Prepared statement can not close: " + e);
				}
			}
		}
		return user;
	}
	
	@Override
	public void setAuthorized(boolean isAuthorized, int userId) {
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection
					.prepareStatement(ConfigurationManager.getProperty("query.user.setAuthorized"));
			preparedStatement.setInt(1, isAuthorized ? 1 : 0);
			preparedStatement.setInt(2, userId);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.err.println("SQL exception (request or table failed): " + e);
		} finally {
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					System.err.println("Prepared statement can not close: " + e);
				}
			}
		}
	}
	
	@Override
	public void setStatus(String status, int userId) {
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection
					.prepareStatement(ConfigurationManager.getProperty("query.user.setStatus"));
			preparedStatement.setString(1, status);
			preparedStatement.setInt(2, userId);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.err.println("SQL exception (request or table failed): " + e);
		} finally {
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					System.err.println("Prepared statement can not close: " + e);
				}
			}
		}	
	}

	@Override
	public void deleteUserById(int userId) {
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection
					.prepareStatement(ConfigurationManager.getProperty("query.user.delete"));
			preparedStatement.setInt(1, userId);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.err.println("SQL exception (request or table failed): " + e);
		} finally {
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					System.err.println("Prepared statement can not close: " + e);
				}
			}
		}
	}

	@Override
	public User getUserById(int userId) {
		User user = null;
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection
					.prepareStatement(ConfigurationManager.getProperty("query.user.getUserById"));
			preparedStatement.setInt(1, userId);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				user = new User();
				setUser(user, resultSet);
			}
		} catch (SQLException e) {
			System.err.println("SQL exception (request or table failed): " + e);
		} finally {
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					System.err.println("Prepared statement can not close: " + e);
				}
			}
		}
		return user;
	}

	@Override
	public void updateUser(User user) {
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection
					.prepareStatement(ConfigurationManager.getProperty("query.user.update"));
			preparedStatement.setString(1, user.getLogin());
			preparedStatement.setString(2, user.getFirstName());
			preparedStatement.setString(3, user.getLastName());
			preparedStatement.setString(4, user.getPhone());
			preparedStatement.setInt(5, user.getId());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.err.println("SQL exception (request or table failed): " + e);
		} finally {
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					System.err.println("Prepared statement can not close: " + e);
				}
			}
		}
	}
	
	private void setUser(User user, ResultSet resultSet) throws SQLException {
		user.setId(resultSet.getInt(ConfigurationManager.getProperty("column.user.id")));
		user.setLogin(resultSet.getString(ConfigurationManager.getProperty("column.user.login")));
		user.setPassword(resultSet.getString(ConfigurationManager.getProperty("column.user.password")));
		user.setStatus(resultSet.getString(ConfigurationManager.getProperty("column.user.status")));
		user.setAuthorized(resultSet.getInt(ConfigurationManager.getProperty("column.user.authorized")) == 1);
		user.setUserType(resultSet.getString(ConfigurationManager.getProperty("column.user.userType")));
		user.setFirstName(resultSet.getString(ConfigurationManager.getProperty("column.user.firstName")));
		user.setLastName(resultSet.getString(ConfigurationManager.getProperty("column.user.lastName")));
		user.setPhone(resultSet.getString(ConfigurationManager.getProperty("column.user.phone")));
	}
}
