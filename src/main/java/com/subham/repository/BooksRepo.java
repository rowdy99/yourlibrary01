package com.subham.repository;


import com.subham.model.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BooksRepo extends JpaRepository<Books,Integer> {

   public List<Books> findAllByBookNameContaining(String name);


}
