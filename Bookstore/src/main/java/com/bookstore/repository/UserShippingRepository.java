package com.bookstore.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.bookstore.domain.User;
import com.bookstore.domain.UserShipping;

public interface UserShippingRepository extends CrudRepository<UserShipping, Long> {

	List<UserShipping> findByUser(User user);

}
