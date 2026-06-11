package br.com.mycompany.chaponibus.admin.dao;

import br.com.mycompany.chaponibus.admin.util.ConexaoBanco;
import br.com.mycompany.chaponibus.admin.model.Onibus;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OnibusDAO {

    public boolean salvar(Onibus onibus) {

        String sql =
            "INSERT INTO onibus " +
            "(placa, modelo, capacidade) " +
            "VALUES (?, ?, ?)";

        try (
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setString(1, onibus.getPlaca());
            stmt.setString(2, onibus.getModelo());
            stmt.setInt(3, onibus.getCapacidade());

            stmt.executeUpdate();

            return true;

        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }

    public List<Onibus> listarTodos() {

        List<Onibus> lista = new ArrayList<>();

        String sql =
            "SELECT * FROM onibus " +
            "ORDER BY placa";

        try (
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()
        ) {

            while (rs.next()) {

                Onibus o = new Onibus();

                o.setIdOnibus(rs.getInt("id_onibus"));
                o.setPlaca(rs.getString("placa"));
                o.setModelo(rs.getString("modelo"));
                o.setCapacidade(rs.getInt("capacidade"));

                lista.add(o);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    public void atualizar(Onibus onibus) {

        String sql =
            "UPDATE onibus SET " +
            "placa=?, modelo=?, capacidade=? " +
            "WHERE id_onibus=?";

        try (
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setString(1, onibus.getPlaca());
            stmt.setString(2, onibus.getModelo());
            stmt.setInt(3, onibus.getCapacidade());
            stmt.setInt(4, onibus.getIdOnibus());

            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void excluir(int id) {

        String sql =
            "DELETE FROM onibus WHERE id_onibus=?";

        try (
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setInt(1, id);

            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
