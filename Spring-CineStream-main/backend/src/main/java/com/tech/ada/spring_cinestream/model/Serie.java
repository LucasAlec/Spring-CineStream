package com.tech.ada.spring_cinestream.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Serie {

    @Id
    @GeneratedValue (strategy =GenerationType.IDENTITY)
    private Long id;

    private String genero;
    private int ano;
    private double avaliacao;
    private String imdbId;
    private String imageUrl;
    private int temporadas;

    public Serie() {}

    public Serie(String genero, int ano, double avaliacao, String imdbId, String imageUrl, int temporadas) {
        this.genero = genero;
        this.ano = ano;
        this.avaliacao = avaliacao;
        this.imdbId = imdbId;
        this.imageUrl = imageUrl;
        this.temporadas = temporadas;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public double getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(double avaliacao) {
        this.avaliacao = avaliacao;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getTemporadas() {
        return temporadas;
    }

    public void setTemporadas(int temporadas) {
        this.temporadas = temporadas;
    }
}
