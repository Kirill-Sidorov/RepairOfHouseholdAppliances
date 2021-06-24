package repairthings.datalayer.data;

import java.util.List;

import repairthings.datalayer.ReplacementPart;

/**
 * Data access object for replacement part entity
 * @author Sidorov Kirill
 *
 */
public interface ReplacementPartDAO {
	/** 
	 * Get all parts from replacement part table
	 * @return
	 */
	List<ReplacementPart> getReplacementPartsList();
	/**
	 * Get replacement part from 
	 * replacement part table by id
	 * @param id
	 * @return
	 */
	ReplacementPart getPartById(int id);
	/** 
	 * Update replacement part in replacement part table
	 * @param part
	 */
	void updatePart(ReplacementPart part);
	/**
	 * Delete replacement part in replacement part table
	 * @param partId
	 */
	void deletePart(int partId);
	/** 
	 * Create new record in replacement part table
	 * @param part
	 */
	void createPart(ReplacementPart part);
	/**
	 * Update number part in stock in replacement part table
	 * @param partId
	 * @param inStock
	 */
	void updateInStock(int partId, int inStock);
}
