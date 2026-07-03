package br.com.mycompany.chaponibus.dao;

import br.com.mycompany.chaponibus.model.*;
import br.com.mycompany.chaponibus.util.ConexaoBanco;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RotaDAO {

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

    public List<Rota> listarTodas() throws SQLException {
        String sql = "SELECT id, nome, ST_AsText(trajeto_geom) as linha FROM rotas ORDER BY id";
        List<Rota> lista = new ArrayList<>();
        try (Connection conn = ConexaoBanco.conectar();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(new Rota(rs.getInt("id"), rs.getString("nome"), rs.getString("linha")));
            }
        }
        return lista;
    }
}