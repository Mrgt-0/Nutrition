package org.example.Mapper;

import org.example.DTO.MealDTO;
import org.example.Model.Meal;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MealMapper {
    Meal toEntity(MealDTO mealDTO);
    MealDTO toDto(Meal meal);
}
