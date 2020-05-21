package com.laimewow.mexample.repository;

import com.laimewow.mexample.domain.Fridge;
import org.springframework.stereotype.Repository;

@Repository //спринг сам создаст импл этого интефрейса.
public interface FridgeRepository extends BasicRepository<Integer, Fridge> {
}
