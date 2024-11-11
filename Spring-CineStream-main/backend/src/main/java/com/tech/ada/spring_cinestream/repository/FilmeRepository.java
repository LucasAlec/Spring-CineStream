package com.tech.ada.spring_cinestream.repository;

import com.tech.ada.spring_cinestream.model.Filme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmeRepository extends JpaRepository<Filme, Long> {
}
