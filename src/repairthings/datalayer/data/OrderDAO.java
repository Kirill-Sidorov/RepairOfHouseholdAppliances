package repairthings.datalayer.data;

import java.util.List;

import repairthings.datalayer.Order;
import repairthings.logic.OrderStatus;

/**
 * Data access object for order entity
 * @author user
 *
 */
public interface OrderDAO {
	/** 
	 * Get all orders from order entity table
	 * @return
	 */
	List<Order> getListOrders();
	/**
	 * Get customer orders from order entity table
	 * @param customerId
	 * @param isDeleted
	 * @return
	 */
	List<Order> getListOrdersByCustomerId(int customerId, boolean isDeleted);
	/**
	 * Get master orders from order entity table
	 * @param masterId
	 * @param status
	 * @return
	 */
	List<Order> getListOrdersByMasterId(int masterId, OrderStatus status);
	/**
	 * Get orders by status from order entity table
	 * @param statusId
	 * @return
	 */
	List<Order> getListOrdersByStatus(int statusId);
	/**
	 * Get orders by deleted from order entity table
	 * @param isDeleted
	 * @return
	 */
	List<Order> getListOrdersByDeleted(boolean isDeleted);
	/**
	 * Get order by id from order entity table
	 * @param orderId
	 * @return
	 */
	Order getOrderById(int orderId);
	/**
	 * Create new record in order entity table
	 * @param order
	 */
	void createOrder(Order order);
	/**
	 * Delete order by id from order entity table
	 * @param orderId
	 */
	void deleteOrderById(int orderId);
	/**
	 * Update order entity
	 * @param order
	 */
	void updateOrder(Order order);
	/**
	 * Update order entity status field
	 * @param status
	 * @param orderId
	 */
	void setOrderStatus(OrderStatus status, int orderId);
	/**
	 * Update order entity deleted field
	 * @param isDeleted
	 * @param orderId
	 */
	void setDeleted(boolean isDeleted, int orderId);
	/**
	 * Update order entity master field
	 * @param orderId
	 * @param masterId
	 */
	void setOrderMaster(int orderId, int masterId);
	/**
	 * Update order entity costWork field
	 * @param orderId
	 * @param costWork
	 */
	void setOrderCostWork(int orderId, int costWork);
}
