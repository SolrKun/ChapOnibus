package br.com.mycompany.chaponibus.admin.dao;

import br.com.mycompany.chaponibus.admin.model.*;
import br.com.mycompany.chaponibus.admin.util.ConexaoBanco;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RotaDAO {

    public void salvar(Rota r) {
        String sql = "INSERT INTO rotas(nome) VALUES (?)";
        
        try {
            Connection conexao = ConexaoBanco.conectar();
            
            PreparedStatement stmt = conexao.prepareStatement(sql);
            
            String nomeRota = r.getNomeRota();
            
            stmt.setString(1, nomeRota);
            
            stmt.executeUpdate();
            stmt.close();
            conexao.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro: " + e.getMessage(), e);
        }
    }

    public void sincronizarPontosDaRota(int rotaId, List<PontoRota> pontos) throws SQLException {
        String deleteSql = "DELETE FROM pontos_rota WHERE rota_id = ?";
        String insertSql = "INSERT INTO pontos_rota (rota_id, latitude, longitude, ordem_sequencia) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = ConexaoBanco.conectar()) {
            conn.setAutoCommit(false);
            try (PreparedStatement psDel = conn.prepareStatement(deleteSql);
                 PreparedStatement psIns = conn.prepareStatement(insertSql)) {
                
                psDel.setInt(1, rotaId);
                psDel.executeUpdate();
                
                for (PontoRota p : pontos) {
                    psIns.setInt(1, rotaId);
                    psIns.setDouble(2, p.getLatitude());
                    psIns.setDouble(3, p.getLongitude());
                    psIns.setInt(4, p.getOrdem());
                    psIns.executeUpdate();
                }
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        }
    }

    public void sincronizarParadas(int rotaId, List<PontoOnibus> paradas) throws SQLException {
        String deleteSql = "DELETE FROM rotas_pontos WHERE rota_id = ?";
        String insertSql = "INSERT INTO rotas_pontos (rota_id, ponto_id, ordem_sequencia) VALUES (?, ?, ?)";
        
        try (Connection conn = ConexaoBanco.conectar()) {
            conn.setAutoCommit(false);
            try (PreparedStatement psDel = conn.prepareStatement(deleteSql);
                 PreparedStatement psIns = conn.prepareStatement(insertSql)) {
                
                psDel.setInt(1, rotaId);
                psDel.executeUpdate();
                
                for (PontoOnibus p : paradas) {
                    psIns.setInt(1, rotaId);
                    psIns.setInt(2, p.getId());
                    psIns.setInt(3, p.getPosicao());
                    psIns.executeUpdate();
                }
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        }
    }

    public Rota buscarCompleta(int id) throws SQLException {
        String sql = "SELECT id, nome, ST_AsText(trajeto_geom) as linha FROM rotas WHERE id = ?";
        
        try (Connection conn = ConexaoBanco.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Rota r = new Rota(rs.getInt("id"), rs.getString("nome"), rs.getString("linha"));
                    r.setListaCliquesTrajeto(buscarCliquesDaRota(id));
                    r.setListaPontosOnibus(buscarParadasDaRota(id));
                    return r;
                }
            }
        }
        return null;
    }

    private List<PontoRota> buscarCliquesDaRota(int rotaId) throws SQLException {
        String sql = "SELECT latitude, longitude, ordem_sequencia FROM pontos_rota WHERE rota_id = ? ORDER BY ordem_sequencia";
        List<PontoRota> pontos = new ArrayList<>();
        try (Connection conn = ConexaoBanco.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, rotaId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    pontos.add(new PontoRota(rs.getDouble("latitude"), rs.getDouble("longitude"), rs.getInt("ordem_sequencia")));
                }
            }
        }
        return pontos;
    }

    private List<PontoOnibus> buscarParadasDaRota(int rotaId) throws SQLException {
        String sql = "SELECT p.id, p.referencia, ST_Y(p.localizacao::geometry) as lat, ST_X(p.localizacao::geometry) as lon, rp.ordem_sequencia " +
                     "FROM pontos_onibus p JOIN rotas_pontos rp ON p.id = rp.ponto_id " +
                     "WHERE rp.rota_id = ? ORDER BY rp.ordem_sequencia";
        List<PontoOnibus> paradas = new ArrayList<>();
        try (Connection conn = ConexaoBanco.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, rotaId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    paradas.add(new PontoOnibus(rs.getInt("id"), rs.getString("referencia"), rs.getDouble("lat"), rs.getDouble("lon"), rs.getInt("ordem_sequencia")));
                }
            }
        }
        return paradas;
    }
}