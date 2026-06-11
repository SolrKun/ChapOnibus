package br.com.mycompany.chaponibus.admin.dao;

import br.com.mycompany.chaponibus.admin.util.ConexaoBanco;
import br.com.mycompany.chaponibus.admin.model.PontoOnibus;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PontoOnibusDAO {

    public boolean salvar(PontoOnibus ponto){

        String sql =
        "INSERT INTO ponto_onibus " +
        "(nome_ponto, referencia, latitude, longitude) " +
        "VALUES (?, ?, ?, ?)";

        try(
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ){

            stmt.setString(1, ponto.getNomePonto());
            stmt.setString(2, ponto.getReferencia());
            stmt.setDouble(3, ponto.getLatitude());
            stmt.setDouble(4, ponto.getLongitude());

            stmt.executeUpdate();

            return true;

        }catch(Exception e){

            e.printStackTrace();
            return false;
        }
    }

    public void atualizar(PontoOnibus ponto){

        String sql =
        "UPDATE ponto_onibus SET " +
        "nome_ponto=?, referencia=?, latitude=?, longitude=? " +
        "WHERE id_ponto=?";

        try(
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ){

            stmt.setString(1, ponto.getNomePonto());
            stmt.setString(2, ponto.getReferencia());
            stmt.setDouble(3, ponto.getLatitude());
            stmt.setDouble(4, ponto.getLongitude());
            stmt.setInt(5, ponto.getIdPonto());

            stmt.executeUpdate();

        }catch(Exception e){

            e.printStackTrace();
        }
    }

    public void excluir(int id){

        String sql =
        "DELETE FROM ponto_onibus WHERE id_ponto=?";

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

    public List<PontoOnibus> listarTodos(){

        List<PontoOnibus> lista =
                new ArrayList<>();

        String sql =
        "SELECT * FROM ponto_onibus ORDER BY nome_ponto";

        try(
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()
        ){

            while(rs.next()){

                PontoOnibus p =
                        new PontoOnibus();

                p.setIdPonto(
                        rs.getInt("id_ponto"));

                p.setNomePonto(
                        rs.getString("nome_ponto"));

                p.setReferencia(
                        rs.getString("referencia"));

                p.setLatitude(
                        rs.getDouble("latitude"));

                p.setLongitude(
                        rs.getDouble("longitude"));

                lista.add(p);
            }

        }catch(Exception e){

            e.printStackTrace();
        }

        return lista;
    }
    public PontoOnibus buscarPorId(int id) {

    String sql =
        "SELECT * FROM ponto_onibus WHERE id_ponto = ?";

    try (
        Connection conn = Conexao.conectar();
        PreparedStatement stmt =
                conn.prepareStatement(sql)
    ) {

        stmt.setInt(1, id);

        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {

            PontoOnibus p = new PontoOnibus();

            p.setIdPonto(
                    rs.getInt("id_ponto"));

            p.setNomePonto(
                    rs.getString("nome_ponto"));

            p.setReferencia(
                    rs.getString("referencia"));

            p.setLatitude(
                    rs.getDouble("latitude"));

            p.setLongitude(
                    rs.getDouble("longitude"));

            return p;
        }

    } catch (Exception e) {

        e.printStackTrace();
    }

    return null;
}
}
