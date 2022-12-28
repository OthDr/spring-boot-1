package com.example.demo1.repository;

import com.example.demo1.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersDataAccess extends JpaRepository<User, Integer> {

    //Custom Queries:
    @Query(value = "SELECT `id`,`firstname`, `lastname` FROM users WHERE `firstname` = :fName ", nativeQuery = true)
    List<User> getByFirstName(@Param("fName") String firstName);
}
