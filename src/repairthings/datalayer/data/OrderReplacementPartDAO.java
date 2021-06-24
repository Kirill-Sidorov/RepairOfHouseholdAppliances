package repairthings.datalayer.data;

import java.util.List;

import repairthings.datalayer.OrderReplacementPart;

/**
 * Data access object for order replacement part entity
 * @author Sidorov Kirill
 *
 */
public interface OrderReplacementPartDAO {
	/** 
	 * Get all replacement parts for order by id
	 * @param orderId
	 * @return
	 */
	List<OrderReplacementPart> getPartsListByOrderId(int orderId);
	/** 
	 * Get order replacement parts with all part in warehouse by order id
	 * @param orderId
	 * @return
	 */
	List<OrderReplacementPart> getOrderPartsWithAllParts(int orderId);
	/** 
	 * Get order replacement parts with number parts in stock by
	 * @param orderId
	 * @return
	 */
	List<OrderReplacementPart> getOrderPartsWithNumberPartsInStock(int orderId);
	/**
	 * Get order replacement parts list
	 * @param orderId
	 * @return
	 */
	List<OrderReplacementPart> getOrderPartsList(int orderId);
	/**
	 * Update order replacement part
	 * @param orderId
	 * @param partId
	 * @param numberParts
	 */
	void updateOrderPart(int orderId, int partId, int numberParts);
	/**
	 * Create order replacement part
	 * @param orderId
	 * @param partId
	 * @param numberParts
	 */
	void createOrderPart(int orderId, int partId, int numberParts);
	/**
	 * Delete order replacement part
	 * @param orderId
	 * @param partId
	 */
	void deleteOrderPart(int orderId, int partId);
}
