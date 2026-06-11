package br.com.mycompany.chaponibus.admin.dao;

import br.com.mycompany.chaponibus.admin.util.ConexaoBanco;
import br.com.mycompany.chaponibus.admin.model.RotaPonto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RotaPontoDAO {

    public boolean salvar(RotaPonto rp) {

        String sql =
        "INSERT INTO rota_ponto " +
        "(id_rota, id_ponto, ordem_ponto) " +
        "VALUES (?, ?, ?)";

        try (
            Connection conn = ConexaoBanco.conectar();
            PreparedStatement stmt =
                    conn.prepareStatement(sql)
        ) {

            stmt.setInt(1, rp.getIdRota());
            stmt.setInt(2, rp.getIdPonto());
            stmt.setInt(3, rp.getOrdemPonto());

            stmt.executeUpdate();

            return true;

        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }

    public void atualizar(RotaPonto rp) {

        String sql =
        "UPDATE rota_ponto SET " +
        "ordem_ponto=? " +
        "WHERE id_rota=? " +
        "AND id_ponto=?";

        try (
            Connection conn = ConexaoBanco.conectar();
            PreparedStatement stmt =
                    conn.prepareStatement(sql)
        ) {

            stmt.setInt(1, rp.getOrdemPonto());
            stmt.setInt(2, rp.getIdRota());
            stmt.setInt(3, rp.getIdPonto());

            stmt.executeUpdate();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public void excluir(int idRota,
                        int idPonto) {

        String sql =
        "DELETE FROM rota_ponto " +
        "WHERE id_rota=? " +
        "AND id_ponto=?";

        try (
            Connection conn = ConexaoBanco.conectar();
            PreparedStatement stmt =
                    conn.prepareStatement(sql)
        ) {

            stmt.setInt(1, idRota);
            stmt.setInt(2, idPonto);

            stmt.executeUpdate();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public List<RotaPonto> listarTodos() {

        List<RotaPonto> lista =
                new ArrayList<>();

        String sql =
        "SELECT * FROM rota_ponto " +
        "ORDER BY id_rota, ordem_ponto";

        try (
            Connection conn = ConexaoBanco.conectar();
            PreparedStatement stmt =
                    conn.prepareStatement(sql);
            ResultSet rs =
                    stmt.executeQuery()
        ) {

            while (rs.next()) {

                RotaPonto rp =
                        new RotaPonto();

                rp.setIdRota(
                        rs.getInt("id_rota"));

                rp.setIdPonto(
                        rs.getInt("id_ponto"));

                rp.setOrdemPonto(
                        rs.getInt("ordem_ponto"));

                lista.add(rp);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return lista;
    }
}
