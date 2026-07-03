package br.com.mycompany.chaponibus.admin.dao;

import br.com.mycompany.chaponibus.admin.model.Usuario;
import br.com.mycompany.chaponibus.admin.util.ConexaoBanco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    
    public void salvar(Usuario u) {
        String sql = "INSERT INTO usuarios(usuario, senha, perfil, primeiro_acesso) VALUES (?, ?, ?, ?)";
        
        try {
            Connection conexao = ConexaoBanco.conectar();
            PreparedStatement stmt = conexao.prepareStatement(sql);
            
            stmt.setString(1, u.getUsername());
            stmt.setString(2, u.getPassword());
            stmt.setString(3, u.getRole());
            stmt.setBoolean(4, true);
            
            stmt.executeUpdate();
            stmt.close();
            conexao.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar: " + e.getMessage(), e);
        }
    }

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
                boolean primeiroAcesso = resultado.getBoolean("primeiro_acesso"); 
                
                Usuario u = new Usuario(usuario, senha, perfil, primeiroAcesso);
                usuarios.add(u);
            }
            
            resultado.close();
            stmt.close();
            conexao.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar: " + e.getMessage(), e);
        }
        return usuarios;
    }
    
    public void atualizar(Usuario u, String usernameAntigo) {
        String sql = "UPDATE usuarios SET usuario=?, senha=?, perfil=?, primeiro_acesso=? WHERE usuario=?";
        
        try {
            Connection conexao = ConexaoBanco.conectar();
            PreparedStatement stmt = conexao.prepareStatement(sql);
            
            stmt.setString(1, u.getUsername());
            stmt.setString(2, u.getPassword());
            stmt.setString(3, u.getRole());
            stmt.setBoolean(4, u.isPrimeiroAcesso());
            stmt.setString(5, usernameAntigo);
            
            stmt.executeUpdate();
            stmt.close();
            conexao.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar: " + e.getMessage(), e);
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
            throw new RuntimeException("Erro ao excluir: " + e.getMessage(), e);
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
                        resultado.getString("perfil"),
                        resultado.getBoolean("primeiro_acesso")
                );
            }
            
            resultado.close();
            stmt.close();
            conexao.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao procurar: " + e.getMessage(), e);
        }
        return u;
    }

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
                        resultado.getString("perfil"),
                        resultado.getBoolean("primeiro_acesso") 
                );
            }
            
            resultado.close();
            stmt.close();
            conexao.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao validar login: " + e.getMessage(), e);
        }
        return u;
    }
}