package repairthings.datalayer.oracledb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import repairthings.datalayer.ReplacementPart;
import repairthings.datalayer.data.ReplacementPartDAO;
import repairthings.resource.ConfigurationManager;

public class OracleReplacementPartDAO implements ReplacementPartDAO {
	
	private Connection connection;
	
	public OracleReplacementPartDAO(Connection connection) {
		this.connection = connection;
	}
	
	@Override
	public List<ReplacementPart> getReplacementPartsList() {
		List<ReplacementPart> parts = new ArrayList<ReplacementPart>();
		Statement statement = null;
		try {
			statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(ConfigurationManager.getProperty("query.replacementPart.allParts"));
			while (resultSet.next()) {
				ReplacementPart part = new ReplacementPart();
				setReplacementPart(part, resultSet);
				parts.add(part);
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
		return parts;
	}
	
	@Override
	public ReplacementPart getPartById(int id) {
		ReplacementPart part = null;
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection
					.prepareStatement(ConfigurationManager.getProperty("query.replacementPart.getPartById"));
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				part = new ReplacementPart();
				setReplacementPart(part, resultSet);
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
		return part;
	}
	
	@Override
	public void updatePart(ReplacementPart part) {
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection
					.prepareStatement(ConfigurationManager.getProperty("query.replacementPart.update"));
			preparedStatement.setString(1, part.getName());
			preparedStatement.setInt(2, part.getInStock());
			preparedStatement.setInt(3, part.getCost());
			preparedStatement.setInt(4, part.getId());
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
	public void deletePart(int partId) {
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection
					.prepareStatement(ConfigurationManager.getProperty("query.replacementPart.delete"));
			preparedStatement.setInt(1, partId);
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
	public void createPart(ReplacementPart part) {
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection
					.prepareStatement(ConfigurationManager.getProperty("query.replacementPart.create"));
			preparedStatement.setString(1, part.getName());
			preparedStatement.setInt(2, part.getInStock());
			preparedStatement.setInt(3, part.getCost());
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
	public void updateInStock(int partId, int inStock) {
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection
					.prepareStatement(ConfigurationManager.getProperty("query.replacementPart.updateInStock"));
			preparedStatement.setInt(1, inStock);
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
	
	private void setReplacementPart(ReplacementPart part, ResultSet resultSet) throws SQLException {
		part.setId(resultSet.getInt(ConfigurationManager.getProperty("column.replacementPart.id")));
		part.setName(resultSet.getString(ConfigurationManager.getProperty("column.replacementPart.name")));
		part.setInStock(resultSet.getInt(ConfigurationManager.getProperty("column.replacementPart.inStock")));
		part.setCost(resultSet.getInt(ConfigurationManager.getProperty("column.replacementPart.cost")));
	}
}
