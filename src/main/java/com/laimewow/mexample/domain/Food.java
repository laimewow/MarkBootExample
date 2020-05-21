package com.laimewow.mexample.domain;

import javax.persistence.*;

@Entity
@Table(name = "foods")
public class Food extends BasicEntity{
    @Column(name = "food_name")
    private String foodName;

    @Column(name = "condition")
    private Double condition;


    //СВЯЗЬ ВСЕГДА ДОЛЖНА БЫТЬ ДВУСТОРОННЯЯ.
    //ManyToOne здесь -> OneToMany там!
    @ManyToOne(fetch = FetchType.LAZY)//Тип загрузки всегда должен быть Lazy
    @JoinColumn(name = "fridge_id", referencedColumnName = "id")
    private Fridge fridge;

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public Double getCondition() {
        return condition;
    }

    public void setCondition(Double condition) {
        this.condition = condition;
    }

    public Fridge getFridge() {
        return fridge;
    }

    public void setFridge(Fridge fridge) {
        this.fridge = fridge;
    }
}
