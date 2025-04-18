package com.tafa.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tafa.entity.Coffee;


@Repository
public interface CoffeeRepository extends JpaRepository<Coffee, Long>{

}
