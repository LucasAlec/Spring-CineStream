package com.tech.ada.spring_cinestream.service;

import com.tech.ada.spring_cinestream.client.tmdbapi.ApiClient;
import com.tech.ada.spring_cinestream.client.tmdbapi.dto.response.Page;
import com.tech.ada.spring_cinestream.client.tmdbapi.dto.response.TmdbFilme;
import com.tech.ada.spring_cinestream.repository.FilmeFavoritoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmeService {
    private final FilmeFavoritoRepository filmeFavoritoRepository;
    private final ApiClient tmdbClient;

    public FilmeService(FilmeFavoritoRepository filmeFavoritoRepository, ApiClient tmdbClient) {
        this.filmeFavoritoRepository = filmeFavoritoRepository;
        this.tmdbClient = tmdbClient;
    }

    public Page<TmdbFilme> buscarFilmePorTitulo(String titulo, Integer page) {
        return tmdbClient.buscarFilmesPorTitulo(titulo, page);
    }
}
