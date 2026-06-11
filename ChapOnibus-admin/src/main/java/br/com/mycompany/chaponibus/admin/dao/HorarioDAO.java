package br.com.mycompany.chaponibus.admn.dao;

import br.com.mycompany.chaponibus.admin.util.ConexaoBanco;
import br.com.mycompany.chaponibus.admin.model.Horario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class HorarioDAO {

    public boolean salvar(Horario horario) {

        String sql =
                "INSERT INTO horario " +
                "(hora_saida, id_rota) " +
                "VALUES (?, ?)";

        try (
                Connection conn = Conexao.conectar();
                PreparedStatement stmt =
                        conn.prepareStatement(sql)
        ) {

            stmt.setTime(1,
                    horario.getHoraSaida());

            stmt.setInt(2,
                    horario.getIdRota());

            stmt.executeUpdate();

            return true;

        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }

    public void atualizar(Horario horario) {

        String sql =
                "UPDATE horario SET " +
                "hora_saida=?, " +
                "id_rota=? " +
                "WHERE id_horario=?";

        try (
                Connection conn = Conexao.conectar();
                PreparedStatement stmt =
                        conn.prepareStatement(sql)
        ) {

            stmt.setTime(1,
                    horario.getHoraSaida());

            stmt.setInt(2,
                    horario.getIdRota());

            stmt.setInt(3,
                    horario.getIdHorario());

            stmt.executeUpdate();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public void excluir(int id) {

        String sql =
                "DELETE FROM horario " +
                "WHERE id_horario=?";

        try (
                Connection conn = Conexao.conectar();
                PreparedStatement stmt =
                        conn.prepareStatement(sql)
        ) {

            stmt.setInt(1, id);

            stmt.executeUpdate();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public List<Horario> listarTodos() {

        List<Horario> lista =
                new ArrayList<>();

        String sql =
                "SELECT * FROM horario " +
                "ORDER BY hora_saida";

        try (
                Connection conn = Conexao.conectar();
                PreparedStatement stmt =
                        conn.prepareStatement(sql);
                ResultSet rs =
                        stmt.executeQuery()
        ) {

            while (rs.next()) {

                Horario h = new Horario();

                h.setIdHorario(
                        rs.getInt("id_horario"));

                h.setHoraSaida(
                        rs.getTime("hora_saida"));

                h.setIdRota(
                        rs.getInt("id_rota"));

                lista.add(h);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return lista;
    }

    public Horario buscarPorId(int id) {

        String sql =
                "SELECT * FROM horario " +
                "WHERE id_horario=?";

        try (
                Connection conn = Conexao.conectar();
                PreparedStatement stmt =
                        conn.prepareStatement(sql)
        ) {

            stmt.setInt(1, id);

            ResultSet rs =
                    stmt.executeQuery();

            if (rs.next()) {

                Horario h = new Horario();

                h.setIdHorario(
                        rs.getInt("id_horario"));

                h.setHoraSaida(
                        rs.getTime("hora_saida"));

                h.setIdRota(
                        rs.getInt("id_rota"));

                return h;
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return null;
    }
}
