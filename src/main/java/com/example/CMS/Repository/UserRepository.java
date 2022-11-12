package com.example.CMS.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.CMS.Model.Users;

public interface UserRepository extends JpaRepository<Users, Integer>{

}
