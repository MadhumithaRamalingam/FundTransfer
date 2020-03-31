package com.stock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stock.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}
