/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.mycompany.chaponibus.util;

import br.com.mycompany.chaponibus.model.Usuario;

/**
 *
 * @author ce498
 */
public class Sessao {
    private static Usuario usuarioLogado = null;

    public static boolean isLogado() {
        return usuarioLogado != null;
    }
    
    public static Usuario getUsuarioAtual() {
        return usuarioLogado;
    }

    public static void login(Usuario usuario) {
        usuarioLogado = usuario;
    }

    public static void logout() {
        usuarioLogado = null;
    }
}
