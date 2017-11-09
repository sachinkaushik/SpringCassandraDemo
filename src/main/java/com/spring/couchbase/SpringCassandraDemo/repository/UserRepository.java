
package com.spring.couchbase.SpringCassandraDemo.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.spring.couchbase.SpringCassandraDemo.document.User;


public interface UserRepository extends CrudRepository<User, String> {
	
	@Query("Select * from users where firstname=?0 allow filtering")
	public List<User> findByFirstName(String firstName);

	@Query("Select * from users where lastname=?0 allow filtering")
	public List<User> findByLastName(String lastName);

	@Query("Select * from users where id=?0 allow filtering")
	public User findById(UUID id);
	
}
