package com.foodForHungry.repo;

import com.foodForHungry.entity.Food;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by mashara on 1/12/17.
 */
@Repository
public interface FoodDao extends CrudRepository<Food,Long> {
}
