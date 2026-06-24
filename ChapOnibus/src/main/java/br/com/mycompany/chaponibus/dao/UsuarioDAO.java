/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.mycompany.chaponibus.dao;

import br.com.mycompany.chaponibus.model.Usuario;
import br.com.mycompany.chaponibus.util.ConexaoBanco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ce498
 */
public class UsuarioDAO {
    public void salvar(Usuario u) {
        String sql = "INSERT INTO usuarios(usuario, senha, perfil) VALUES (?, ?, ?)";
        
        try {
            Connection conexao = ConexaoBanco.conectar();
            
            PreparedStatement stmt = conexao.prepareStatement(sql);
            
            String usuario = u.getUsername();
            String senha = u.getPassword();
            String perfil = u.getRole();
            
            stmt.setString(1, usuario);
            stmt.setString(2, senha);
            stmt.setString(3, perfil);
            
            stmt.executeUpdate();
            stmt.close();
            conexao.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro: " + e.getMessage(), e);
        }
    }

    // Método para simular o SELECT *
    public List<Usuario> listarTodos() {
        String sql = "SELECT * FROM usuarios";
        List<Usuario> usuarios = new ArrayList<>();
        
        try {
            Connection conexao = ConexaoBanco.conectar();
            
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            
            while(resultado.next()) {
                String usuario = resultado.getString("usuario");
                String senha = resultado.getString("senha");
                String perfil = resultado.getString("perfil");
                
                Usuario u = new Usuario(usuario, senha, perfil);
                usuarios.add(u);
            }
            
            resultado.close();
            stmt.close();
            conexao.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro: " + e.getMessage(), e);
        }
        return usuarios;
    }
    
    public void atualizar(Usuario u, String usernameAntigo) {
        String sql = "UPDATE usuarios SET usuario=?, senha=?, perfil=? WHERE usuario=?";
        
        try {
            Connection conexao = ConexaoBanco.conectar();
            
            PreparedStatement stmt = conexao.prepareStatement(sql);
            
            stmt.setString(1, u.getUsername());
            stmt.setString(2, u.getPassword());
            stmt.setString(3, u.getRole());
            stmt.setString(4, usernameAntigo);
            
            stmt.executeUpdate();
            stmt.close();
            conexao.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro: " + e.getMessage(), e);
        }
    }
    
    public void excluir(String username) {
        String sql = "DELETE FROM usuarios WHERE usuario=?";
        
        try {
            Connection conexao = ConexaoBanco.conectar();
            
            PreparedStatement stmt = conexao.prepareStatement(sql);
            
            stmt.setString(1, username);
            
            stmt.executeUpdate();
            stmt.close();
            conexao.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro: " + e.getMessage(), e);
        }
    }
    
    public Usuario procurarUsuario(String username) {
        String sql = "SELECT * FROM usuarios WHERE usuario=?";
        Usuario u = null;
        
        try {
            Connection conexao = ConexaoBanco.conectar();
            
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, username);
            ResultSet resultado = stmt.executeQuery();
            
            if (resultado.next()) {
                u = new Usuario(
                        resultado.getString("usuario"), 
                        resultado.getString("senha"), 
                        resultado.getString("perfil")
                );
            }
            
            resultado.close();
            stmt.close();
            conexao.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro: " + e.getMessage(), e);
        }
        return u;
    }

    // Método para simular o SELECT com WHERE (útil para o Login)
    public Usuario validarLogin(String username, String senha) {
        String sql = "SELECT * FROM usuarios WHERE usuario=? AND senha=?";
        Usuario u = null;
        
        try {
            Connection conexao = ConexaoBanco.conectar();
            
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, senha);
            ResultSet resultado = stmt.executeQuery();
            
            if (resultado.next()) {
                u = new Usuario(
                        resultado.getString("usuario"), 
                        resultado.getString("senha"), 
                        resultado.getString("perfil")
                );
            }
            
            resultado.close();
            stmt.close();
            conexao.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro: " + e.getMessage(), e);
        }
        return u;
    }
}
