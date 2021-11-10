package com.subham.repository;


import com.subham.model.Anm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnmRepo extends JpaRepository<Anm,Integer> {
    public List<Anm> findAllByBookNameContaining(String name);
}
