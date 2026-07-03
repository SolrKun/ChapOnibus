package br.com.mycompany.chaponibus.model;

public class PontoOnibus {

    private int id;
    private String referencia;
    private double latitude;
    private double longitude;
    private int posicao;

    public PontoOnibus() {
    }
    
    public PontoOnibus(String referencia, double latitude, double longitude, int posicao) {
        this.referencia = referencia;
        this.latitude = latitude;
        this.longitude = longitude;
        this.posicao = posicao;
    }

    public PontoOnibus(int id, String referencia, double latitude, double longitude, int posicao) {
        this.id = id;
        this.referencia = referencia;
        this.latitude = latitude;
        this.longitude = longitude;
        this.posicao = posicao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getPosicao() {
        return posicao;
    }

    public void setPosicao(int posicao) {
        this.posicao = posicao;
    }
    
    @Override
    public String toString() {
        return referencia;
    }
}
