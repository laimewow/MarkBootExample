package com.laimewow.mexample.repository;

import com.laimewow.mexample.domain.Food;
import org.springframework.stereotype.Repository;

@Repository //спринг сам создаст импл этого интефрейса.
public interface FoodRepository extends BasicRepository<Integer, Food> {
}
