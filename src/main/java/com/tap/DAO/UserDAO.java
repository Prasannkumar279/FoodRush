package com.tap.DAO;

import java.util.List;

import com.tap.model.User;

public interface UserDAO {
	
	int addUser(User u); // accepting User Object  and we will pass it to database.
	void updateUser(User u); // while updating u will get to see all the details(name,ph.no,email,pwd)  which u want to update so its Object.
	void deleteUser(int  id); // through id we can remove the user
	User getUser(int id); // if admin wants to see ur profile he will  get it by id
	List<User> getAllUser();
	User getUserByUserName(String userName);

}
