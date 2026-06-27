/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.mycompany.chaponibus.admin.model;

/**
 *
 * @author ce498
 */
public class Sessao {
    private static Usuario usuarioLogado;

    public static void conectar(Usuario usuario) {
        usuarioLogado = usuario;
    }

    public static void desconectar() {
        usuarioLogado = null;
    }

    public static Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public static boolean isLogado() {
        return usuarioLogado != null;
    }
}
