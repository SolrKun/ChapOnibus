package br.com.mycompany.chaponibus.admin.dao;

import br.com.mycompany.chaponibus.admin.util.ConexaoBanco;
import br.com.mycompany.chaponibus.admin.model.Rota;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RotaDAO {

    public boolean salvar(Rota rota) {

        String sql =
            "INSERT INTO rota " +
            "(nome_rota, descricao, ativa, id_onibus) " +
            "VALUES (?, ?, ?, ?)";

        try (
            Connection conn = ConexaoBanco.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setString(1, rota.getNomeRota());
            stmt.setString(2, rota.getDescricao());
            stmt.setBoolean(3, rota.isAtiva());
            stmt.setInt(4, rota.getIdOnibus());

            stmt.executeUpdate();

            return true;

        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }

    public void atualizar(Rota rota) {

        String sql =
            "UPDATE rota SET " +
            "nome_rota=?, " +
            "descricao=?, " +
            "ativa=?, " +
            "id_onibus=? " +
            "WHERE id_rota=?";

        try (
            Connection conn = ConexaoBanco.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setString(1, rota.getNomeRota());
            stmt.setString(2, rota.getDescricao());
            stmt.setBoolean(3, rota.isAtiva());
            stmt.setInt(4, rota.getIdOnibus());
            stmt.setInt(5, rota.getIdRota());

            stmt.executeUpdate();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public void excluir(int idRota) {

        String sql =
            "DELETE FROM rota " +
            "WHERE id_rota=?";

        try (
            Connection conn = ConexaoBanco.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setInt(1, idRota);

            stmt.executeUpdate();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public List<Rota> listarTodos() {

        List<Rota> lista = new ArrayList<>();

        String sql =
            "SELECT * FROM rota " +
            "ORDER BY nome_rota";

        try (
            Connection conn = ConexaoBanco.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()
        ) {

            while (rs.next()) {

                Rota rota = new Rota();

                rota.setIdRota(
                    rs.getInt("id_rota"));

                rota.setNomeRota(
                    rs.getString("nome_rota"));

                rota.setDescricao(
                    rs.getString("descricao"));

                rota.setAtiva(
                    rs.getBoolean("ativa"));

                rota.setIdOnibus(
                    rs.getInt("id_onibus"));

                lista.add(rota);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return lista;
    }

    public Rota buscarPorId(int idRota) {

        String sql =
            "SELECT * FROM rota " +
            "WHERE id_rota=?";

        try (
            Connection conn = ConexaoBanco.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setInt(1, idRota);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                Rota rota = new Rota();

                rota.setIdRota(
                    rs.getInt("id_rota"));

                rota.setNomeRota(
                    rs.getString("nome_rota"));

                rota.setDescricao(
                    rs.getString("descricao"));

                rota.setAtiva(
                    rs.getBoolean("ativa"));

                rota.setIdOnibus(
                    rs.getInt("id_onibus"));

                return rota;
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return null;
    }
}
