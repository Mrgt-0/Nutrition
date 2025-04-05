package org.example.Mapper;

import org.example.DTO.DishDTO;
import org.example.Model.Dish;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DishMapper {
    Dish toEntity(DishDTO dishDTO);
    DishDTO toDTO(Dish dish);
}
