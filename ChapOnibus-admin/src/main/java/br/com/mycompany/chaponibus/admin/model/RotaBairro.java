package br.com.mycompany.chaponibus.admin.model;

public class RotaBairro {

    private int idRota;
    private int idBairro;

    public RotaBairro() {
    }

    public RotaBairro(int idRota, int idBairro) {
        this.idRota = idRota;
        this.idBairro = idBairro;
    }

    public int getIdRota() {
        return idRota;
    }

    public void setIdRota(int idRota) {
        this.idRota = idRota;
    }

    public int getIdBairro() {
        return idBairro;
    }

    public void setIdBairro(int idBairro) {
        this.idBairro = idBairro;
    }
}
