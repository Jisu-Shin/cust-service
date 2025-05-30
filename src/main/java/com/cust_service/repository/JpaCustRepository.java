package com.cust_service.repository;

import com.cust_service.domain.Cust;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface JpaCustRepository extends JpaRepository<Cust, Long> {
    List<Cust> findAll();
    Optional<Cust> findByName(String name);
    List<Cust> findByNameLike(String name);
    List<Cust> findByIdIn(List<Long> idList);
}
