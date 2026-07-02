/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.mycompany.chaponibus.admin.model;

/**
 *
 * @author carlos62347296
 */
public class PontoRota {
    private double latitude;
    private double longitude;
    private int ordem;

    public PontoRota() {
    }

    public PontoRota(double latitude, double longitude, int ordem) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.ordem = ordem;
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

    public int getOrdem() {
        return ordem;
    }

    public void setOrdem(int ordem) {
        this.ordem = ordem;
    }
}
