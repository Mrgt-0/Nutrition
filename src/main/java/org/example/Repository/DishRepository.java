package org.example.Repository;
import org.example.DTO.DishDTO;
import org.example.Model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DishRepository extends JpaRepository<Dish, Long> {
//    @Query("SELECT d FROM Dish d WHERE (d.id = :ids)
//    List<DishDTO> findDishesByIds(@Param("ids") List<Long> ids);
//    @Query("select d from Dish d where (d.name = :name)")
//    Optional<Dish> findByName(@Param("name") String name);
List<Dish> findByName(String name);
}
