package com.laimewow.mexample.service.impl;

import com.laimewow.mexample.domain.Food;
import com.laimewow.mexample.domain.Fridge;
import com.laimewow.mexample.domain.dto.FoodDTO;
import com.laimewow.mexample.domain.dto.FridgeDTO;
import com.laimewow.mexample.repository.FoodRepository;
import com.laimewow.mexample.repository.FridgeRepository;
import com.laimewow.mexample.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service //Сервайс пишется тут, а не на интерфейсе
public class FoodServiceImpl implements FoodService {


    //==============================================INJECTION PART======================================================
    private final FoodRepository foodRepository;
    private final FridgeRepository fridgeRepository;

    @Autowired
    public FoodServiceImpl(FoodRepository foodRepository, FridgeRepository fridgeRepository) {
        this.foodRepository = foodRepository;
        this.fridgeRepository = fridgeRepository;
    }
    //=======================================END OF INJECTION PART======================================================


    @Override
    public FridgeDTO findFridge(Integer fridgeId) {
        Fridge fridge = getFridge(fridgeId);
        return mapFridge(fridge);
    }


    @Override
    public FoodDTO findFood(Integer foodId) {
        Food food = getFood(foodId);
        return mapFood(food);
    }


    @Override
    public FoodDTO createFood(FoodDTO dto) {
        //для новой записи айдишника быть не должно быть. Если он есть, его можно либо занулить, либо кинуть эксепшен.
        dto.setId(null);
        //Размотали
        Food food = unmapFood(dto);
        //Сохранили
        food = foodRepository.save(food);
        //Замотали
        dto = mapFood(food);
        //Итс зет симпл
        return dto;
    }

    @Override
    public FridgeDTO createFridge(FridgeDTO dto) {
        //для новой записи айдишника быть не должно быть. Если он есть, его можно либо занулить, либо кинуть эксепшен.
        dto.setId(null);
        //Размотали
        Fridge fridge = unmapFridge(dto);
        //Сохранили
        fridge = fridgeRepository.save(fridge);
        //Замотали
        dto = mapFridge(fridge);
        return dto;
    }


    //============================================PRIVATE UTIL METHODS==================================================

    //region mappers
    private FridgeDTO mapFridge(Fridge fridge) {
        FridgeDTO result = new FridgeDTO();
        result.setId(fridge.getId());
        result.setProducer(fridge.getProducer());
        result.setTemperature(fridge.getTemperature());
        List<FoodDTO> foodDTOS = fridge.getFoodList().stream().map(this::mapFood).collect(Collectors.toList());
        result.setFoods(foodDTOS);
        return result;
    }

    private Fridge unmapFridge(FridgeDTO fridgeDTO) {
        Fridge result;
        if (fridgeDTO.getId() != null) {
            result = getFridge(fridgeDTO.getId());
        } else {
            result = new Fridge();
        }
        result.setProducer(fridgeDTO.getProducer());
        result.setTemperature(fridgeDTO.getTemperature());

        List<Food> foodList = fridgeDTO.getFoods().stream().map(this::unmapFood).collect(Collectors.toList());
        result.setFoodList(foodList);
        return result;
    }

    private FoodDTO mapFood(Food food) {
        FoodDTO result = new FoodDTO();
        result.setId(food.getId());
        result.setName(food.getFoodName());
        result.setCondition(food.getCondition());
        result.setFridgeId(food.getFridge().getId());
        return result;
    }

    private Food unmapFood(FoodDTO foodDTO) {
        Food result;
        if (foodDTO.getId() != null) {
            result = getFood(foodDTO.getId());
        } else {
            result = new Food();
        }

        result.setCondition(foodDTO.getCondition());
        result.setFoodName(foodDTO.getName());
        result.setFridge(getFridge(foodDTO.getFridgeId()));
        return result;
    }

    //endregion


    //region raw data access
    private Fridge getFridge(Integer fridgeId) {
        Fridge fridge = fridgeRepository.findById(fridgeId).orElseThrow(() -> new RuntimeException("No such id"));
        //        Эквивалент строчке выше, просто чтобы понимать что происходит
//        Fridge f = fridgeRepository.findById(foodId).orElse(null);
//        if(f == null)
//        {
//            throw new RuntimeException("No such id");
//        }
        return fridge;
    }


    private Food getFood(Integer foodId) {
        Food food = foodRepository.findById(foodId).orElseThrow(() -> new RuntimeException("No such id"));
        return food;
    }
    //endregion
    //=====================================END OF PRIVATE UTIL METHODS==================================================
}
