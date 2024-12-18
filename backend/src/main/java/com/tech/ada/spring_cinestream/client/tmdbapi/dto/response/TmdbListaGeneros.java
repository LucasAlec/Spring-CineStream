package com.tech.ada.spring_cinestream.client.tmdbapi.dto.response;

import java.util.List;

public class TmdbListaGeneros {
    private List<TmdbGenero> genres;

    public TmdbListaGeneros () {}

    public TmdbListaGeneros(List<TmdbGenero> genres) {
        this.genres = genres;
    }

    public List<TmdbGenero> getGenres() {
        return genres;
    }

    public void setGenres(List<TmdbGenero> genres) {
        this.genres = genres;
    }
}
