package com.subham.repository;

import com.subham.model.Computer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComputerRepo extends JpaRepository<Computer,Integer> {
    public List<Computer> findAllByBookNameContaining(String name);
}
