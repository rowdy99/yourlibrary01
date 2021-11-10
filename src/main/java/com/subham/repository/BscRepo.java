package com.subham.repository;


import com.subham.model.Bsc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BscRepo extends JpaRepository<Bsc,Integer> {
    public List<Bsc> findAllByBookNameContaining(String name);
}
