package com.gasbooking.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.gasbooking.app.entity.Cylinder;

@Repository
public interface CylinderRepository extends JpaRepository<Cylinder,Integer>{

}
