package br.com.mycompany.chaponibus.admin.dao;

import br.com.mycompany.chaponibus.admin.util.ConexaoBanco;
import br.com.mycompany.chaponibus.admin.model.RotaBairro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RotaBairroDAO {

    public void salvar(RotaBairro rb) {

        String sql =
            "INSERT INTO rota_bairro " +
            "(id_rota,id_bairro) " +
            "VALUES (?,?)";

        try (
            Connection conn = Conexao.conectar();
            PreparedStatement stmt =
                    conn.prepareStatement(sql)
        ) {

            stmt.setInt(1, rb.getIdRota());
            stmt.setInt(2, rb.getIdBairro());

            stmt.executeUpdate();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public void excluir(int idRota,
                        int idBairro) {

        String sql =
            "DELETE FROM rota_bairro " +
            "WHERE id_rota=? " +
            "AND id_bairro=?";

        try (
            Connection conn = Conexao.conectar();
            PreparedStatement stmt =
                    conn.prepareStatement(sql)
        ) {

            stmt.setInt(1, idRota);
            stmt.setInt(2, idBairro);

            stmt.executeUpdate();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public List<RotaBairro> listarTodos() {

        List<RotaBairro> lista =
                new ArrayList<>();

        String sql =
            "SELECT * FROM rota_bairro";

        try (
            Connection conn = Conexao.conectar();
            PreparedStatement stmt =
                    conn.prepareStatement(sql);
            ResultSet rs =
                    stmt.executeQuery()
        ) {

            while (rs.next()) {

                RotaBairro rb =
                        new RotaBairro();

                rb.setIdRota(
                        rs.getInt("id_rota"));

                rb.setIdBairro(
                        rs.getInt("id_bairro"));

                lista.add(rb);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return lista;
    }
}
