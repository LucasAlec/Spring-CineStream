// SerieService.java
package com.tech.ada.spring_cinestream.service;

import com.tech.ada.spring_cinestream.model.Serie;
import com.tech.ada.spring_cinestream.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SerieService {

    @Autowired
    private SerieRepository serieRepository;

    public List<Serie> listarTodos() {
        return serieRepository.findAll();
    }

    public Optional<Serie> buscarPorId(Long id) {
        return serieRepository.findById(id);
    }

    public Serie salvar(Serie serie) {
        return serieRepository.save(serie);
    }

    public void deletar(Long id) {
        serieRepository.deleteById(id);
    }
}
