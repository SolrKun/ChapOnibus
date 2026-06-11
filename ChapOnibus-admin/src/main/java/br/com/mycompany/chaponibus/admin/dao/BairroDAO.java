package br.com.mycompany.chaponibus.admin.dao;

import br.com.mycompany.chaponibus.admin.util.ConexaoBanco;
import br.com.mycompany.chaponibus.admin.model.Bairro;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BairroDAO {

    public void salvar(Bairro bairro) {

        String sql =
        "INSERT INTO bairro(nome_bairro, fronteira_geo) " +
        "VALUES (?, ?)";

        try(
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ){

            stmt.setString(1, bairro.getNomeBairro());
            stmt.setString(2, bairro.getFronteiraGeo());

            stmt.executeUpdate();

        }catch(Exception e){

            e.printStackTrace();
        }
    }

    public void atualizar(Bairro bairro) {

        String sql =
        "UPDATE bairro SET " +
        "nome_bairro=?, " +
        "fronteira_geo=? " +
        "WHERE id_bairro=?";

        try(
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ){

            stmt.setString(1, bairro.getNomeBairro());
            stmt.setString(2, bairro.getFronteiraGeo());
            stmt.setInt(3, bairro.getIdBairro());

            stmt.executeUpdate();

        }catch(Exception e){

            e.printStackTrace();
        }
    }

    public void excluir(int id) {

        String sql =
        "DELETE FROM bairro WHERE id_bairro=?";

        try(
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ){

            stmt.setInt(1, id);

            stmt.executeUpdate();

        }catch(Exception e){

            e.printStackTrace();
        }
    }

    public Bairro buscarPorId(int id) {

        String sql =
        "SELECT * FROM bairro WHERE id_bairro=?";

        try(
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ){

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if(rs.next()){

                Bairro bairro = new Bairro();

                bairro.setIdBairro(
                        rs.getInt("id_bairro"));

                bairro.setNomeBairro(
                        rs.getString("nome_bairro"));

                bairro.setFronteiraGeo(
                        rs.getString("fronteira_geo"));

                return bairro;
            }

        }catch(Exception e){

            e.printStackTrace();
        }

        return null;
    }

    public List<Bairro> listarTodos() {

        List<Bairro> lista =
                new ArrayList<>();

        String sql =
        "SELECT * FROM bairro " +
        "ORDER BY nome_bairro";

        try(
            Connection conn = Conexao.conectar();
            PreparedStatement stmt =
                    conn.prepareStatement(sql);
            ResultSet rs =
                    stmt.executeQuery()
        ){

            while(rs.next()){

                Bairro bairro =
                        new Bairro();

                bairro.setIdBairro(
                        rs.getInt("id_bairro"));

                bairro.setNomeBairro(
                        rs.getString("nome_bairro"));

                bairro.setFronteiraGeo(
                        rs.getString("fronteira_geo"));

                lista.add(bairro);
            }

        }catch(Exception e){

            e.printStackTrace();
        }

        return lista;
    }
}
