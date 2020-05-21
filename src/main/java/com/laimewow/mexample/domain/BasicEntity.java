package com.laimewow.mexample.domain;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.id.enhanced.SequenceStyleGenerator;

import javax.persistence.*;

@MappedSuperclass
public abstract class BasicEntity implements Identifiable<Integer> {

    @Id //первичный ключ
    @Column(name = "id") //имя колонки
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @GenericGenerator(name = "sequenceGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    //Для каждой сущности своя последовательность
                    @org.hibernate.annotations.Parameter(name = SequenceStyleGenerator.CONFIG_PREFER_SEQUENCE_PER_ENTITY, value = "true"),
                    //Суффикс для последовательности (для сущности с именем Food будет food_id_seq)
                    @org.hibernate.annotations.Parameter(name = SequenceStyleGenerator.CONFIG_SEQUENCE_PER_ENTITY_SUFFIX, value = "_id_seq")
            })
    //Тип генерации айдишника. Будет использован Сиквенс
    @Access(AccessType.PROPERTY) //Хитрость для быстрого доступа к айдишнику без загрузки данных из БД
    private Integer id;


    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }
}
