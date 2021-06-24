package repairthings.datalayer.oracledb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import repairthings.datalayer.OrderReplacementPart;
import repairthings.datalayer.data.OrderReplacementPartDAO;
import repairthings.resource.ConfigurationManager;

public class OracleOrderReplacementPartDAO implements OrderReplacementPartDAO {
	
	private Connection connection;
	
	public OracleOrderReplacementPartDAO(Connection connection) {
		this.connection = connection;
	}

	@Override
	public List<OrderReplacementPart> getPartsListByOrderId(int orderId) {
		List<OrderReplacementPart> parts = new ArrayList<OrderReplacementPart>();
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection
					.prepareStatement(ConfigurationManager.getProperty("query.partsList.getPartsByOrderId"));
			preparedStatement.setInt(1, orderId);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				OrderReplacementPart orderPart = new OrderReplacementPart();
				setOrderPart(orderPart, resultSet);
				parts.add(orderPart);
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
		return parts;
	}
	
	@Override
	public List<OrderReplacementPart> getOrderPartsWithAllParts(int orderId) {
		List<OrderReplacementPart> parts = new ArrayList<OrderReplacementPart>();
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection
					.prepareStatement(ConfigurationManager.getProperty("query.partsList.getOrderPartsWithAllParts"));
			preparedStatement.setInt(1, orderId);
			preparedStatement.setInt(2, orderId);
			preparedStatement.setInt(3, orderId);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				OrderReplacementPart orderPart = new OrderReplacementPart();
				setOrderPart(orderPart, resultSet);
				orderPart.setPartName(resultSet.getString(ConfigurationManager.getProperty("column.replacementPart.name")));
				orderPart.setCost(resultSet.getInt(ConfigurationManager.getProperty("column.replacementPart.cost")));
				parts.add(orderPart);
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
		return parts;
	}
	
	@Override
	public List<OrderReplacementPart> getOrderPartsWithNumberPartsInStock(int orderId) {
		List<OrderReplacementPart> parts = new ArrayList<OrderReplacementPart>();
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection
					.prepareStatement(ConfigurationManager.getProperty("query.partsList.getOrderPartsWithNumberPartsInStock"));
			preparedStatement.setInt(1, orderId);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				OrderReplacementPart orderPart = new OrderReplacementPart();
				setOrderPart(orderPart, resultSet);
				orderPart.setPartName(resultSet.getString(ConfigurationManager.getProperty("column.replacementPart.name")));
				orderPart.setInStock(resultSet.getInt(ConfigurationManager.getProperty("column.replacementPart.inStock")));
				parts.add(orderPart);
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
		return parts;
	}

	@Override
	public void updateOrderPart(int orderId, int partId, int numberParts) {
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection
					.prepareStatement(ConfigurationManager.getProperty("query.partsList.updateOrderPart"));
			preparedStatement.setInt(1, numberParts);
			preparedStatement.setInt(2, orderId);
			preparedStatement.setInt(3, partId);
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
	public void createOrderPart(int orderId, int partId, int numberParts) {
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection
					.prepareStatement(ConfigurationManager.getProperty("query.partsList.createOrderPart"));
			preparedStatement.setInt(1, orderId);
			preparedStatement.setInt(2, partId);
			preparedStatement.setInt(3, numberParts);
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
	public void deleteOrderPart(int orderId, int partId) {
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection
					.prepareStatement(ConfigurationManager.getProperty("query.partsList.deleteOrderPart"));
			preparedStatement.setInt(1, orderId);
			preparedStatement.setInt(2, partId);
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
	public List<OrderReplacementPart> getOrderPartsList(int orderId) {
		List<OrderReplacementPart> parts = new ArrayList<OrderReplacementPart>();
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection
					.prepareStatement(ConfigurationManager.getProperty("query.partsList.getOrderPartsList"));
			preparedStatement.setInt(1, orderId);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				OrderReplacementPart orderPart = new OrderReplacementPart();
				setOrderPart(orderPart, resultSet);
				orderPart.setPartName(resultSet.getString(ConfigurationManager.getProperty("column.replacementPart.name")));
				orderPart.setCost(resultSet.getInt(ConfigurationManager.getProperty("column.replacementPart.cost")));
				parts.add(orderPart);
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
		return parts;
	}
	
	private void setOrderPart(OrderReplacementPart orderPart, ResultSet resultSet) throws SQLException {
		orderPart.setOrderId(resultSet.getInt(ConfigurationManager.getProperty("column.partsList.orderId")));
		orderPart.setPartId(resultSet.getInt(ConfigurationManager.getProperty("column.partsList.replacementPart")));
		orderPart.setNumberParts(resultSet.getInt(ConfigurationManager.getProperty("column.partsList.numberParts")));
	}
}
