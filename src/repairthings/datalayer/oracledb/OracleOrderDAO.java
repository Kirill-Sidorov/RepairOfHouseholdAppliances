package repairthings.datalayer.oracledb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import repairthings.datalayer.Order;
import repairthings.datalayer.data.OrderDAO;
import repairthings.logic.OrderStatus;
import repairthings.resource.ConfigurationManager;

public class OracleOrderDAO implements OrderDAO {
	
	private Connection connection;
	
	public OracleOrderDAO(Connection connection) {
		this.connection = connection;
	}

	@Override
	public List<Order> getListOrders() {
		List<Order> orders = new ArrayList<Order>();
		Statement statement = null;
		try {
			statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(ConfigurationManager.getProperty("query.order.allOrders"));
			while (resultSet.next()) {
				Order order = new Order();
				setOrder(order, resultSet);
				orders.add(order);
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
		return orders;
	}
	
	@Override
	public void deleteOrderById(int orderId) {
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection
					.prepareStatement(ConfigurationManager.getProperty("query.order.delete"));
			preparedStatement.setInt(1, orderId);
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
	public void createOrder(Order order) {
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection
					.prepareStatement(ConfigurationManager.getProperty("query.order.create"));
			preparedStatement.setString(1, order.getThingName());
			preparedStatement.setString(2, order.getDescription());
			preparedStatement.setInt(3, order.getOrderStatus().getId());
			preparedStatement.setInt(4, order.getCustomerId());
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
	public List<Order> getListOrdersByCustomerId(int customerId, boolean isDeleted) {
		List<Order> orders = new ArrayList<Order>();
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection
					.prepareStatement(ConfigurationManager.getProperty("quety.order.getOrdersByCustomerId"));
			preparedStatement.setInt(1, customerId);
			preparedStatement.setInt(2, isDeleted ? 1 : 0);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Order order = new Order();
				setOrder(order, resultSet);
				orders.add(order);
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
		return orders;
	}
	
	@Override
	public List<Order> getListOrdersByMasterId(int masterId, OrderStatus status) {
		List<Order> orders = new ArrayList<Order>();
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection
					.prepareStatement(ConfigurationManager.getProperty("quety.order.getOrdersByMasterIdAndOrderStatus"));
			preparedStatement.setInt(1, masterId);
			preparedStatement.setInt(2, status.getId());
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Order order = new Order();
				setOrder(order, resultSet);
				orders.add(order);
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
		return orders;
	}
	
	@Override
	public List<Order> getListOrdersByStatus(int statusId) {
		List<Order> orders = new ArrayList<Order>();
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection
					.prepareStatement(ConfigurationManager.getProperty("query.order.getOrdersByStatus"));
			preparedStatement.setInt(1, statusId);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Order order = new Order();
				setOrder(order, resultSet);
				orders.add(order);
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
		return orders;
	}
	
	
	@Override
	public List<Order> getListOrdersByDeleted(boolean isDeleted) {
		List<Order> orders = new ArrayList<Order>();
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection
					.prepareStatement(ConfigurationManager.getProperty("query.order.getOrdersByDeleted"));
			preparedStatement.setInt(1, isDeleted ? 1 : 0);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Order order = new Order();
				setOrder(order, resultSet);
				orders.add(order);
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
		return orders;
	}
	@Override
	public Order getOrderById(int orderId) {
		Order order = null;
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection
					.prepareStatement(ConfigurationManager.getProperty("query.order.getOrderById"));
			preparedStatement.setInt(1, orderId);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				order = new Order();
				setOrder(order, resultSet);
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
		return order;
	}
	
	@Override
	public void updateOrder(Order order) {
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection
					.prepareStatement(ConfigurationManager.getProperty("query.order.update"));
			preparedStatement.setString(1, order.getThingName());
			preparedStatement.setString(2, order.getDescription());
			preparedStatement.setInt(3, order.getId());
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
	public void setOrderStatus(OrderStatus status, int orderId) {
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection
					.prepareStatement(ConfigurationManager.getProperty("query.order.updateOrderStatus"));
			preparedStatement.setInt(1, status.getId());
			preparedStatement.setInt(2, orderId);
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
	public void setDeleted(boolean isDeleted, int orderId) {
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection
					.prepareStatement(ConfigurationManager.getProperty("query.order.updateDeleted"));
			preparedStatement.setInt(1, isDeleted ? 1 : 0);
			preparedStatement.setInt(2, orderId);
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
	public void setOrderMaster(int orderId, int masterId) {
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection
					.prepareStatement(ConfigurationManager.getProperty("query.order.updateMaster"));
			preparedStatement.setInt(1, masterId);
			preparedStatement.setInt(2, orderId);
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
	public void setOrderCostWork(int orderId, int costWork) {
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection
					.prepareStatement(ConfigurationManager.getProperty("query.order.updateCostWork"));
			preparedStatement.setInt(1, costWork);
			preparedStatement.setInt(2, orderId);
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
	
	private void setOrder(Order order, ResultSet resultSet) throws SQLException {
		order.setId(resultSet.getInt(ConfigurationManager.getProperty("column.order.id")));
		order.setThingName(resultSet.getString(ConfigurationManager.getProperty("column.order.thingName")));
		order.setDescription(resultSet.getString(ConfigurationManager.getProperty("column.order.description")));
		order.setOrderStatus(resultSet.getString(ConfigurationManager.getProperty("column.order.orderStatus")));
		order.setCustomerName(resultSet.getString(ConfigurationManager.getProperty("column.order.customerName")));
		order.setMasterName(resultSet.getString(ConfigurationManager.getProperty("column.order.masterName")));
		order.setCustomerId(resultSet.getInt(ConfigurationManager.getProperty("column.order.customerId")));
		order.setMasterId(resultSet.getInt(ConfigurationManager.getProperty("column.order.masterId")));
		order.setCostWork(resultSet.getInt(ConfigurationManager.getProperty("column.order.costWork")));
		order.setDeleted(resultSet.getInt(ConfigurationManager.getProperty("column.order.deleted")) == 1);
	}
}
