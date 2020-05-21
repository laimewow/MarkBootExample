package com.laimewow.mexample.domain.dto;

import com.laimewow.mexample.domain.Identifiable;

import java.util.ArrayList;
import java.util.List;


public class FridgeDTO implements Identifiable<Integer> {

    private Integer id;
    private String producer;
    private Integer temperature;
    private List<FoodDTO> foods = new ArrayList<>();

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public Integer getTemperature() {
        return temperature;
    }

    public void setTemperature(Integer temperature) {
        this.temperature = temperature;
    }

    public List<FoodDTO> getFoods() {
        return foods;
    }

    public void setFoods(List<FoodDTO> foods) {
        this.foods = foods;
    }
}
