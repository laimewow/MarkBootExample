package com.laimewow.mexample.controller;

import com.laimewow.mexample.domain.dto.FoodDTO;
import com.laimewow.mexample.domain.dto.FridgeDTO;
import com.laimewow.mexample.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/")
public class SampleController {

    //==============================================INJECTION PART======================================================
    private final FoodService foodService;

    @Autowired
    public SampleController(FoodService foodService) {
        this.foodService = foodService;
    }
    //=======================================END OF INJECTION PART======================================================


    //region GET
    @GetMapping(path = "food/{id}")
    public ResponseEntity<FoodDTO> getFoodById(@PathVariable Integer id)
    {
        FoodDTO food = foodService.findFood(id);
        return ResponseEntity.ok(food);
    }

    @GetMapping(path = "fridge/{id}")
    public ResponseEntity<FridgeDTO> getFridgeById(@PathVariable Integer id)
    {
        FridgeDTO fridge = foodService.findFridge(id);
        return ResponseEntity.ok(fridge);
    }
    //endregion


    //region POST
    @PostMapping(path = "food")
    public ResponseEntity<FoodDTO> createFood(@RequestBody FoodDTO dto)
    {
        FoodDTO food = foodService.createFood(dto);
        return ResponseEntity.ok(food);
    }

    @PostMapping(path = "fridge")
    public ResponseEntity<FridgeDTO> createFridge(@RequestBody FridgeDTO dto)
    {
        FridgeDTO food = foodService.createFridge(dto);
        return ResponseEntity.ok(food);
    }
    //endregion


}
