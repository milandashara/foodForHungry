package com.foodForHungry.repo;

import com.foodForHungry.entity.UserDetail;
import com.foodForHungry.entity.VerificationToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by mashara on 1/12/17.
 */
@Repository
public interface VerificationTokenDao extends CrudRepository<VerificationToken,Long> {
	 VerificationToken findByToken(String token);
//	 VerificationToken findByUserDetail(UserDetail userDetail);
}
