package repairthings.datalayer.data;

import java.util.List;

import repairthings.datalayer.User;

/**
 * Data access object for user entity 
 * @author Sidorov Kirill
 *
 */
public interface UserDAO {
	/**
	 * Create new record in user entity table
	 * @param user
	 */
	void createUser(User user);
	/**
	 * Delete user by id from user entity table
	 * @param userId
	 */
	void deleteUserById(int userId);
	/**
	 * Get list users in user entity table
	 * @return
	 */
	List<User> getListUsers();
	/** Get list users in user entity table by status
	 * @param status
	 * @return list users
	 */
	List<User> getListUsersByStatus(String status);
	/** 
	 * Get user by login from user entity table
	 * @param login
	 * @return user
	 */
	User getUserByLogin(String login);
	/** 
	 * Get user by id from user entity table
	 * @param userId
	 * @return
	 */
	User getUserById(int userId);
	/** 
	 * Update user entity
	 * @param user
	 */
	void updateUser(User user);
	/** 
	 * Update user entity authorization field
	 * @param isAuthorized
	 * @param userId
	 */
	void setAuthorized(boolean isAuthorized, int userId);
	/** 
	 * Update user entity status filed
	 * @param status
	 * @param userId
	 */
	void setStatus(String status, int userId);
}
