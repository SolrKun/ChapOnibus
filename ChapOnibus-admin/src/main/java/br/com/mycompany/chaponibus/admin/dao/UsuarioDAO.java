/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.mycompany.chaponibus.admin.dao;

import br.com.mycompany.chaponibus.admin.model.Usuario;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ce498
 */
public class UsuarioDAO {
    private static List<Usuario> bancoEmMemoria = new ArrayList<>();
    
    // Método para simular o INSERT
    public void salvar(Usuario usuario) {
        bancoEmMemoria.add(usuario);
        System.out.println("Usuário salvo com sucesso no Array!");
    }

    // Método para simular o SELECT *
    public List<Usuario> listarTodos() {
        return bancoEmMemoria;
    }
    
    public void atualizar(Usuario usuario) {
        for (int i = 0; i < bancoEmMemoria.size(); i++) {
            Usuario u = bancoEmMemoria.get(i);

            if (u.getUsername().equals(usuario.getUsername())) {
                bancoEmMemoria.set(i, usuario);
                return;
            }
        }
        System.out.println("Usuário não encontrado para atualização.");
    }
    
    public void excluir(String username) {
        bancoEmMemoria.removeIf(u -> u.getUsername().equals(username));
    }
    
    public Usuario procurarUsuario(String username) {
        for (Usuario u : bancoEmMemoria) {
            if (u.getUsername().equals(username)) {
                return u;
            }
        }
        return null;
    }

    // Método para simular o SELECT com WHERE (útil para o Login)
    public Usuario validarLogin(String username, String senha) {
        for (Usuario u : bancoEmMemoria) {
            if (u.getUsername().equals(username) && u.getPassword().equals(senha)) {
                return u;
            }
        }
        return null; // Usuário não encontrado
    }
}
