package com.gasbooking.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.gasbooking.app.entity.SurrenderCylinder;

@Repository
public interface SurrenderCylinderRepository extends JpaRepository<SurrenderCylinder,Integer>{

}
