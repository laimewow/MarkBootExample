package com.laimewow.mexample.repository;

import com.laimewow.mexample.domain.Identifiable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.Map;
import java.util.stream.Collectors;

//Базовый репо помечаетс такой аннотацией. Это означает что это "абстрактный" репо.
@NoRepositoryBean
public interface BasicRepository<ID extends Serializable, T extends Identifiable<ID>> extends JpaRepository<T, ID> {
    //Здесь можно делать общие методы для всех репозиториев, например получить мэп id->сущность
    default Map<ID, T> mapById(Iterable<ID> ids) {
        return findAllById(ids) //нашли все
                .stream()  //стрим апи гоез хере
                .collect( //собрать
                        //используем метод getId для получения айдишника сущности (это будет key), и сущность как value для мэпа
                        Collectors.toMap(Identifiable::getId, p -> p)
                );
    }
}
