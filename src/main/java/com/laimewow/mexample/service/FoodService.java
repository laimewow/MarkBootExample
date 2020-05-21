package com.laimewow.mexample.service;

import com.laimewow.mexample.domain.dto.FoodDTO;
import com.laimewow.mexample.domain.dto.FridgeDTO;

public interface FoodService {
    FoodDTO findFood(Integer foodId);

    FridgeDTO findFridge(Integer fridgeId);

    //Я обычно возвращаю созданный объект обратно, для удобства
    FoodDTO createFood(FoodDTO dto);

    FridgeDTO createFridge(FridgeDTO fridgeDTO);
}
