package net.crunchdroid.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import net.crunchdroid.entity.User;

@Repository
public interface UserRepo extends CrudRepository<User, Long> {

	User findById(long id);
	
	public User findByUserName(String userName);



}
