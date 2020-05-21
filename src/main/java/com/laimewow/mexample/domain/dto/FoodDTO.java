package com.laimewow.mexample.domain.dto;

import com.laimewow.mexample.domain.Identifiable;

public class FoodDTO implements Identifiable<Integer> {
    private Integer id;
    private String name;
    private Double condition;
    private Integer fridgeId;

    public Integer getFridgeId() {
        return fridgeId;
    }

    public void setFridgeId(Integer fridgeId) {
        this.fridgeId = fridgeId;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getCondition() {
        return condition;
    }

    public void setCondition(Double condition) {
        this.condition = condition;
    }
}
