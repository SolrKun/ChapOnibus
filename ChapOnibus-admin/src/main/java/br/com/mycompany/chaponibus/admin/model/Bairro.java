package br.com.mycompany.chaponibus.admin.model;

public class Bairro {

    private int idBairro;
    private String nomeBairro;
    private String fronteiraGeo;

    public Bairro() {
    }

    public Bairro(String nomeBairro, String fronteiraGeo) {
        this.nomeBairro = nomeBairro;
        this.fronteiraGeo = fronteiraGeo;
    }

    public int getIdBairro() {
        return idBairro;
    }

    public void setIdBairro(int idBairro) {
        this.idBairro = idBairro;
    }

    public String getNomeBairro() {
        return nomeBairro;
    }

    public void setNomeBairro(String nomeBairro) {
        this.nomeBairro = nomeBairro;
    }

    public String getFronteiraGeo() {
        return fronteiraGeo;
    }

    public void setFronteiraGeo(String fronteiraGeo) {
        this.fronteiraGeo = fronteiraGeo;
    }
    @Override
    public String toString() {
        return nomeBairro;
    }
}
