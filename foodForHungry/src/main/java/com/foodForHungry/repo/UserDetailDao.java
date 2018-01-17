package com.foodForHungry.repo;

import com.foodForHungry.entity.Food;
import com.foodForHungry.entity.UserDetail;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by mashara on 1/12/17.
 */
@Repository
public interface UserDetailDao extends CrudRepository<UserDetail,Long> {
	 UserDetail findByEmail(String email);
}
