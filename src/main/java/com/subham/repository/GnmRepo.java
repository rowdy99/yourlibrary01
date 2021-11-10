package com.subham.repository;


import com.subham.model.Gnm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GnmRepo extends JpaRepository<Gnm,Integer> {
    public List<Gnm> findAllByBookNameContaining(String name);
}
