package br.com.mycompany.chaponibus.model;

import java.util.ArrayList;
import java.util.List;

public class Rota {

    private int id;
    private String nomeRota;
    private String trajetoGeom;
    private List<PontoRota> listaCliquesTrajeto = new ArrayList<>();
    private List<PontoOnibus> listaPontosOnibus = new ArrayList<>();
    private Onibus onibus;

    public Rota() {
    }

    public Rota(int id, String nomeRota, String trajetoGeom) {
        this.id = id;
        this.nomeRota = nomeRota;
        this.trajetoGeom = trajetoGeom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeRota() {
        return nomeRota;
    }

    public void setNomeRota(String nomeRota) {
        this.nomeRota = nomeRota;
    }

    public String getTrajetoGeom() {
        return trajetoGeom;
    }

    public void setTrajetoGeom(String trajetoGeom) {
        this.trajetoGeom = trajetoGeom;
    }

    public List<PontoRota> getListaCliquesTrajeto() {
        return listaCliquesTrajeto;
    }

    public void setListaCliquesTrajeto(List<PontoRota> listaCliquesTrajeto) {
        this.listaCliquesTrajeto = listaCliquesTrajeto;
    }

    public List<PontoOnibus> getListaPontosOnibus() {
        return listaPontosOnibus;
    }

    public void setListaPontosOnibus(List<PontoOnibus> listaPontosOnibus) {
        this.listaPontosOnibus = listaPontosOnibus;
    }

    public Onibus getOnibus() {
        return onibus;
    }

    public void setOnibus(Onibus onibus) {
        this.onibus = onibus;
    }

    @Override
    public String toString() {
        return nomeRota;
    }
}
