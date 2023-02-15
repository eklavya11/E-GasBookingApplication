package com.gasbooking.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gasbooking.app.entity.Admin;

public interface AdminRepository extends JpaRepository<Admin,Integer>{

}
