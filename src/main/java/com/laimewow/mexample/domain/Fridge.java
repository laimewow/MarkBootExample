package com.laimewow.mexample.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity //сущность. Можно задать явно
@Table(name = "fridges") //имя таблицы. я всегда задаю явно
public class Fridge extends BasicEntity {
    @Column(name = "producer") //я всегда задаю имена колонок явно
    private String producer;

    @Column(name = "temperature")
    private Integer temperature;

    //          имя поля с той стороны лучше всегда каскадить     и удлять зависимые сущности
    @OneToMany(
            //СВЯЗЬ ВСЕГДА ДОЛЖНА БЫТЬ ДВУСТОРОННЯЯ.
            //OneToMany здесь -> ManyToOne там!
            mappedBy = "fridge", //Имя поля с той стороны
            cascade = CascadeType.ALL, //Лучше всегда каскадить операции для целостности бд
            orphanRemoval = true //удалять зависимые сущности при удалении родителя
    )
    private List<Food> foodList = new ArrayList<>(); //всегда инициализируй OneToMany новым пустым списком

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

    public List<Food> getFoodList() {
        return foodList;
    }

    //Обрати внимание на хитрый сеттер. Он автоматически проставляет обратную связь
    public void setFoodList(List<Food> foodList) {
        //Здесь нет операции присваивания из-за одной хитрости работы JPA, напиши ней - я расскажу о ней
        this.foodList.clear();
        if (foodList != null) {
            this.foodList.addAll(foodList);
        }
        //Вот здесь. Каждому продукту сопоставляется зыс холодильник
        this.foodList.forEach(p -> p.setFridge(this));
    }
}
