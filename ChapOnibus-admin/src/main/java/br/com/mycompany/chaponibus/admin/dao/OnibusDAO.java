package br.com.mycompany.chaponibus.admin.dao;

import br.com.mycompany.chaponibus.admin.util.ConexaoBanco;
import br.com.mycompany.chaponibus.admin.model.Onibus;
import br.com.mycompany.chaponibus.admin.model.Rota;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OnibusDAO {

    public boolean salvar(Onibus onibus) {
        String sql =
            "INSERT INTO onibus " +
            "(placa, modelo, capacidade, id_rota) " +
            "VALUES (?, ?, ?, ?)";

        try (
            Connection conn = ConexaoBanco.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {

            stmt.setString(1, onibus.getPlaca());
            stmt.setString(2, onibus.getModelo());
            stmt.setInt(3, onibus.getCapacidade());

            if (onibus.getRotaAtual() != null) {
                stmt.setInt(4, onibus.getRotaAtual().getId());
            } else {
                stmt.setNull(4, Types.INTEGER);
            }

            int linhasAfetadas = stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    onibus.setIdOnibus(generatedKeys.getInt(1));
                }
            }

            return linhasAfetadas > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Onibus> listarTodos() {
        List<Onibus> lista = new ArrayList<>();

        String sql =
            "SELECT o.id_onibus, o.placa, o.modelo, o.capacidade, o.id_rota, r.nome AS nome_rota " +
            "FROM onibus o " +
            "LEFT JOIN rotas r ON o.id_rota = r.id " +
            "ORDER BY o.placa";

        try (
            Connection conn = ConexaoBanco.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()
        ) {

            while (rs.next()) {
                Onibus o = new Onibus();
                o.setIdOnibus(rs.getInt("id_onibus"));
                o.setPlaca(rs.getString("placa"));
                o.setModelo(rs.getString("modelo"));
                o.setCapacidade(rs.getInt("capacidade"));

                int idRota = rs.getInt("id_rota");
                if (!rs.wasNull()) { 
                    Rota r = new Rota();
                    r.setId(idRota);
                    r.setNomeRota(rs.getString("nome_rota"));
                    o.setRotaAtual(r);
                } else {
                    o.setRotaAtual(null);
                }

                lista.add(o);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    public boolean atualizar(Onibus onibus) {
        String sql =
            "UPDATE onibus SET " +
            "placa=?, modelo=?, capacidade=?, id_rota=? " +
            "WHERE id_onibus=?";

        try (
            Connection conn = ConexaoBanco.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setString(1, onibus.getPlaca());
            stmt.setString(2, onibus.getModelo());
            stmt.setInt(3, onibus.getCapacidade());

            if (onibus.getRotaAtual() != null) {
                stmt.setInt(4, onibus.getRotaAtual().getId());
            } else {
                stmt.setNull(4, Types.INTEGER);
            }

            stmt.setInt(5, onibus.getIdOnibus());

            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean excluir(int id) {
        String sql = "DELETE FROM onibus WHERE id_onibus=?";

        try (
            Connection conn = ConexaoBanco.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setInt(1, id);
            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean atualizarRota(int idOnibus, Rota novaRota) {
        String sql = "UPDATE onibus SET id_rota = ? WHERE id_onibus = ?";

        try (
            Connection conn = ConexaoBanco.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            if (novaRota != null) {
                stmt.setInt(1, novaRota.getId());
            } else {
                stmt.setNull(1, Types.INTEGER);
            }

            stmt.setInt(2, idOnibus);

            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public Onibus procurarOnibus(int id) {
        String sql = "SELECT o.id_onibus, o.placa, o.modelo, o.capacidade, o.id_rota, r.nome AS nome_rota " +
                     "FROM onibus o " +
                     "LEFT JOIN rotas r ON o.id_rota = r.id " +
                     "WHERE o.id_onibus = ?";
        
        try (
            Connection conn = ConexaoBanco.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setInt(1, id);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Onibus o = new Onibus();
                    o.setIdOnibus(rs.getInt("id_onibus"));
                    o.setPlaca(rs.getString("placa"));
                    o.setModelo(rs.getString("modelo"));
                    o.setCapacidade(rs.getInt("capacidade"));

                    int idRota = rs.getInt("id_rota");
                    if (!rs.wasNull()) {
                        Rota r = new Rota();
                        r.setId(idRota);
                        r.setNomeRota(rs.getString("nome_rota"));
                        o.setRotaAtual(r);
                    }
                    return o;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}