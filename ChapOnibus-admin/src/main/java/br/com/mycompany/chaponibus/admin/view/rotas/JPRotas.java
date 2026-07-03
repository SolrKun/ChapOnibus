/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package br.com.mycompany.chaponibus.admin.view.rotas;

import br.com.mycompany.chaponibus.admin.dao.RotaDAO;
import br.com.mycompany.chaponibus.admin.model.PontoOnibus;
import br.com.mycompany.chaponibus.admin.model.PontoRota;
import br.com.mycompany.chaponibus.admin.model.Rota;
import java.awt.BasicStroke;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.OSMTileFactoryInfo;
import org.jxmapviewer.input.PanMouseInputListener;
import org.jxmapviewer.input.ZoomMouseWheelListenerCenter;
import org.jxmapviewer.painter.CompoundPainter;
import org.jxmapviewer.painter.Painter;
import org.jxmapviewer.viewer.DefaultTileFactory;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.TileFactoryInfo;

/**
 *
 * @author ce498
 */
public class JPRotas extends javax.swing.JPanel {
    
    public enum ModoEdicao { ROTA, ONIBUS }

    private JXMapViewer mapViewer;
    private ModoEdicao modoAtual = ModoEdicao.ROTA;
    private boolean editando = false;

    private List<PontoRota> pontosTrajeto = new ArrayList<>();
    private List<PontoOnibus> pontosOnibus = new ArrayList<>();

    private int indicePontoArrastando = -1;
    private ModoEdicao modoArrastado = null;
    private PanMouseInputListener panListener;
    private int idRotaEmEdicao = 0;

    /**
     * Creates new form JPRotas
     */
    public JPRotas() {
        initComponents();
        
        CardLayout cl = (CardLayout) jPDetalhes.getLayout();
        cl.show(jPDetalhes, "cardTabelaRotas");
        
        configurarMapa();
        
        recarregarMapaComDadosDoBanco();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPCabecalho = new javax.swing.JPanel();
        jBCadastrarRota = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPTelaPrincipal = new javax.swing.JPanel();
        jPPrincipal = new javax.swing.JPanel();
        jPMapa = new javax.swing.JPanel();
        jPDetalhes = new javax.swing.JPanel();
        jPTabelaRotas = new br.com.mycompany.chaponibus.admin.view.rotas.JPTabelaRotas();
        jPDescricaoRotas = new br.com.mycompany.chaponibus.admin.view.rotas.JPDescricaoRotas();

        setBackground(new java.awt.Color(244, 247, 249));
        setLayout(new java.awt.BorderLayout());

        jPCabecalho.setBackground(new java.awt.Color(244, 247, 249));
        jPCabecalho.setMinimumSize(new java.awt.Dimension(1090, 50));
        jPCabecalho.setOpaque(false);
        jPCabecalho.setPreferredSize(new java.awt.Dimension(1110, 80));
        jPCabecalho.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jBCadastrarRota.setBackground(new java.awt.Color(21, 80, 150));
        jBCadastrarRota.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jBCadastrarRota.setForeground(new java.awt.Color(255, 255, 255));
        jBCadastrarRota.setText("Nova rota");
        jBCadastrarRota.addActionListener(this::jBCadastrarRotaActionPerformed);
        jPCabecalho.add(jBCadastrarRota, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 20, 210, 50));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(21, 80, 150));
        jLabel1.setText("Gestão de Rotas");
        jPCabecalho.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 310, -1));

        add(jPCabecalho, java.awt.BorderLayout.PAGE_START);

        jPTelaPrincipal.setOpaque(false);
        jPTelaPrincipal.setLayout(new java.awt.BorderLayout());

        jPPrincipal.setBackground(new java.awt.Color(244, 247, 249));
        jPPrincipal.setMinimumSize(new java.awt.Dimension(660, 600));
        jPPrincipal.setOpaque(false);
        jPPrincipal.setPreferredSize(new java.awt.Dimension(660, 600));

        jPMapa.setBackground(new java.awt.Color(244, 247, 249));
        jPMapa.setPreferredSize(new java.awt.Dimension(650, 560));
        jPMapa.setVerifyInputWhenFocusTarget(false);

        javax.swing.GroupLayout jPMapaLayout = new javax.swing.GroupLayout(jPMapa);
        jPMapa.setLayout(jPMapaLayout);
        jPMapaLayout.setHorizontalGroup(
            jPMapaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 650, Short.MAX_VALUE)
        );
        jPMapaLayout.setVerticalGroup(
            jPMapaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPPrincipalLayout = new javax.swing.GroupLayout(jPPrincipal);
        jPPrincipal.setLayout(jPPrincipalLayout);
        jPPrincipalLayout.setHorizontalGroup(
            jPPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPPrincipalLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jPMapa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPPrincipalLayout.setVerticalGroup(
            jPPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPPrincipalLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jPMapa, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );

        jPTelaPrincipal.add(jPPrincipal, java.awt.BorderLayout.CENTER);

        jPDetalhes.setBackground(new java.awt.Color(255, 0, 51));
        jPDetalhes.setMinimumSize(new java.awt.Dimension(440, 600));
        jPDetalhes.setOpaque(false);
        jPDetalhes.setPreferredSize(new java.awt.Dimension(440, 600));
        jPDetalhes.setLayout(new java.awt.CardLayout());
        jPDetalhes.add(jPTabelaRotas, "cardTabelaRotas");
        jPDetalhes.add(jPDescricaoRotas, "cardDescricaoRota");

        jPTelaPrincipal.add(jPDetalhes, java.awt.BorderLayout.EAST);

        add(jPTelaPrincipal, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void configurarMapa() {
        TileFactoryInfo info = new OSMTileFactoryInfo("OpenStreetMap", "https://tile.openstreetmap.org");
        DefaultTileFactory tileFactory = new DefaultTileFactory(info);

        mapViewer = new JXMapViewer();
        mapViewer.setTileFactory(tileFactory);
        mapViewer.setOpaque(true);

        GeoPosition chapeco = new GeoPosition(-27.0968, -52.6186);
        mapViewer.setZoom(5);
        mapViewer.setAddressLocation(chapeco);
        mapViewer.addMouseWheelListener(new ZoomMouseWheelListenerCenter(mapViewer));

        panListener = new PanMouseInputListener(mapViewer);

        MouseAdapter customMouseAdapter = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                tratarPressionarMouse(e);
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                tratarArrastarMouse(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (indicePontoArrastando != -1) {
                    indicePontoArrastando = -1;
                    modoArrastado = null;
                    return;
                }
                panListener.mouseReleased(e);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                tratarCliqueMapa(e);
            }
        };

        mapViewer.addMouseListener(customMouseAdapter);
        mapViewer.addMouseMotionListener(customMouseAdapter);

        mapViewer.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                 .put(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_DOWN_MASK), "desfazer");
        mapViewer.getActionMap().put("desfazer", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                desfazerUltimoPonto();
            }
        });

        jPMapa.setLayout(new java.awt.BorderLayout());
        jPMapa.add(mapViewer, java.awt.BorderLayout.CENTER);
        jPMapa.setBorder(BorderFactory.createLineBorder(new Color(21, 80, 150), 1));
        
        jPMapa.revalidate();
        jPMapa.repaint();
    }
    
    private void tratarPressionarMouse(MouseEvent e) {
        if (!editando || !SwingUtilities.isLeftMouseButton(e)) {
            panListener.mousePressed(e);
            return;
        }

        indicePontoArrastando = -1;
        modoArrastado = null;

        for (int i = 0; i < pontosTrajeto.size(); i++) {
            PontoRota pr = pontosTrajeto.get(i);
            Point2D pTela = mapViewer.convertGeoPositionToPoint(new GeoPosition(pr.getLatitude(), pr.getLongitude()));
            if (e.getPoint().distance(pTela) <= 12.0) {
                indicePontoArrastando = i;
                modoArrastado = ModoEdicao.ROTA;
                return;
            }
        }

        for (int i = 0; i < pontosOnibus.size(); i++) {
            PontoOnibus po = pontosOnibus.get(i);
            Point2D pTela = mapViewer.convertGeoPositionToPoint(new GeoPosition(po.getLatitude(), po.getLongitude()));
            if (e.getPoint().distance(pTela) <= 12.0) {
                indicePontoArrastando = i;
                modoArrastado = ModoEdicao.ONIBUS;
                return;
            }
        }

        panListener.mousePressed(e);
    }

    private void tratarArrastarMouse(MouseEvent e) {
        if (indicePontoArrastando != -1 && modoArrastado != null) {
            GeoPosition novaGeo = mapViewer.convertPointToGeoPosition(e.getPoint());

            if (modoArrastado == ModoEdicao.ROTA) {
                boolean rotaFechada = pontosTrajeto.size() > 2 &&
                        pontosTrajeto.get(0).getLatitude() == pontosTrajeto.get(pontosTrajeto.size() - 1).getLatitude() &&
                        pontosTrajeto.get(0).getLongitude() == pontosTrajeto.get(pontosTrajeto.size() - 1).getLongitude();

                PontoRota pr = pontosTrajeto.get(indicePontoArrastando);
                pr.setLatitude(novaGeo.getLatitude());
                pr.setLongitude(novaGeo.getLongitude());

                if (rotaFechada && indicePontoArrastando == 0) {
                    PontoRota fim = pontosTrajeto.get(pontosTrajeto.size() - 1);
                    fim.setLatitude(novaGeo.getLatitude());
                    fim.setLongitude(novaGeo.getLongitude());
                } else if (rotaFechada && indicePontoArrastando == pontosTrajeto.size() - 1) {
                    PontoRota inicio = pontosTrajeto.get(0);
                    inicio.setLatitude(novaGeo.getLatitude());
                    inicio.setLongitude(novaGeo.getLongitude());
                }
            } else if (modoArrastado == ModoEdicao.ONIBUS) {
                PontoOnibus po = pontosOnibus.get(indicePontoArrastando);
                po.setLatitude(novaGeo.getLatitude());
                po.setLongitude(novaGeo.getLongitude());
            }

            atualizarPainters();
        } else {
            panListener.mouseDragged(e);
        }
    }

    private void tratarCliqueMapa(MouseEvent e) {
        if (!editando || !SwingUtilities.isLeftMouseButton(e)) return;

        if (modoAtual == ModoEdicao.ROTA && pontosTrajeto.size() >= 2) {
            PontoRota primeiro = pontosTrajeto.get(0);
            Point2D pTela = mapViewer.convertGeoPositionToPoint(new GeoPosition(primeiro.getLatitude(), primeiro.getLongitude()));

            if (e.getPoint().distance(pTela) <= 15.0) {
                int novaOrdem = pontosTrajeto.size() + 1;
                pontosTrajeto.add(new PontoRota(primeiro.getLatitude(), primeiro.getLongitude(), novaOrdem));
                atualizarPainters();
                return;
            }
        }

        for (PontoRota pr : pontosTrajeto) {
            if (e.getPoint().distance(mapViewer.convertGeoPositionToPoint(new GeoPosition(pr.getLatitude(), pr.getLongitude()))) <= 12.0) return;
        }
        for (PontoOnibus po : pontosOnibus) {
            if (e.getPoint().distance(mapViewer.convertGeoPositionToPoint(new GeoPosition(po.getLatitude(), po.getLongitude()))) <= 12.0) return;
        }

        if (modoAtual == ModoEdicao.ROTA && pontosTrajeto.size() >= 2) {
            for (int i = 0; i < pontosTrajeto.size() - 1; i++) {
                Point2D p1 = mapViewer.convertGeoPositionToPoint(new GeoPosition(pontosTrajeto.get(i).getLatitude(), pontosTrajeto.get(i).getLongitude()));
                Point2D p2 = mapViewer.convertGeoPositionToPoint(new GeoPosition(pontosTrajeto.get(i + 1).getLatitude(), pontosTrajeto.get(i + 1).getLongitude()));

                double distancia = Line2D.ptSegDist(p1.getX(), p1.getY(), p2.getX(), p2.getY(), e.getX(), e.getY());
                if (distancia <= 8.0) {
                    GeoPosition novoGeo = mapViewer.convertPointToGeoPosition(e.getPoint());
                    pontosTrajeto.add(i + 1, new PontoRota(novoGeo.getLatitude(), novoGeo.getLongitude(), i + 2));
                    reordenarPontosTrajeto();
                    atualizarPainters();
                    return;
                }
            }
        }

        GeoPosition geo = mapViewer.convertPointToGeoPosition(e.getPoint());
        if (modoAtual == ModoEdicao.ROTA) {
            int ordem = pontosTrajeto.size() + 1;
            pontosTrajeto.add(new PontoRota(geo.getLatitude(), geo.getLongitude(), ordem));
            atualizarPainters();
        } else if (modoAtual == ModoEdicao.ONIBUS) {
            String referencia = JOptionPane.showInputDialog(
                    this,
                    "Digite a referência do Ponto de Ônibus:",
                    "Adicionar Parada",
                    JOptionPane.QUESTION_MESSAGE
            );

            if (referencia != null && !referencia.trim().isEmpty()) {
                int posicao = pontosOnibus.size() + 1;
                pontosOnibus.add(new PontoOnibus(referencia.trim(), geo.getLatitude(), geo.getLongitude(), posicao));
                atualizarPainters();
                atualizarListaLateral();
            }
        }
    }

    private void reordenarPontosTrajeto() {
        for (int i = 0; i < pontosTrajeto.size(); i++) {
            pontosTrajeto.get(i).setOrdem(i + 1);
        }
    }

    public void desfazerUltimoPonto() {
        if (!editando) return;

        if (modoAtual == ModoEdicao.ROTA && !pontosTrajeto.isEmpty()) {
            pontosTrajeto.remove(pontosTrajeto.size() - 1);
        } else if (modoAtual == ModoEdicao.ONIBUS && !pontosOnibus.isEmpty()) {
            pontosOnibus.remove(pontosOnibus.size() - 1);
        }

        atualizarPainters();
        atualizarListaLateral();
    }

    private void atualizarListaLateral() {
        for (Component comp : jPDetalhes.getComponents()) {
            if (comp instanceof JPDescricaoRotas) {
                ((JPDescricaoRotas) comp).atualizarListaPontosOnibus(pontosOnibus);
                break;
            }
        }
    }

    public void atualizarPainters() {
        List<Painter<JXMapViewer>> painters = new ArrayList<>();
        
        painters.add(new RotaPainter(pontosTrajeto));
        painters.add(new ParadaPainter(pontosOnibus));

        CompoundPainter<JXMapViewer> compoundPainter = new CompoundPainter<>(painters);
        mapViewer.setOverlayPainter(compoundPainter);
        mapViewer.repaint();
    }

    public Rota montarObjetoRota(String nomeRota) {
        Rota rota = new Rota();
        rota.setId(this.idRotaEmEdicao); 
        rota.setNomeRota(nomeRota);
        rota.setListaCliquesTrajeto(new ArrayList<>(pontosTrajeto));
        rota.setListaPontosOnibus(new ArrayList<>(pontosOnibus)); 
        return rota;
    }

    public void setModoAtual(ModoEdicao modo) {
        this.modoAtual = modo;
    }
    
    public void recarregarMapaComDadosDoBanco() {
        try {
            RotaDAO dao = new RotaDAO();
            List<Rota> todasAsRotas = dao.listarTodas();

            List<Painter<JXMapViewer>> painters = new ArrayList<>();

            for (Rota r : todasAsRotas) {
                Rota detalhada = dao.buscarCompleta(r.getId());

                if (detalhada != null) {
                    painters.add(new RotaPainter(detalhada.getListaCliquesTrajeto()));
                    painters.add(new ParadaPainter(detalhada.getListaPontosOnibus()));
                }
            }

            CompoundPainter<JXMapViewer> compoundPainter = new CompoundPainter<>(painters);
            mapViewer.setOverlayPainter(compoundPainter);
            mapViewer.repaint();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar rotas no mapa: " + e.getMessage());
        }
    }

    public void finalizarEdicao() {
        editando = false;
        this.idRotaEmEdicao = 0;
        pontosTrajeto.clear();
        pontosOnibus.clear();

        atualizarPainters();

        CardLayout cl = (CardLayout) jPDetalhes.getLayout();
        cl.show(jPDetalhes, "cardTabelaRotas");
        jBCadastrarRota.setVisible(true);

        jPDescricaoRotas.setVisible(false);

        jPTabelaRotas.carregarTabela(); 

        recarregarMapaComDadosDoBanco();

        this.revalidate();
        this.repaint();
    }

    private static class RotaPainter implements Painter<JXMapViewer> {
        private final List<PontoRota> pontos;

        public RotaPainter(List<PontoRota> pontos) {
            this.pontos = pontos;
        }

        @Override
        public void paint(Graphics2D g, JXMapViewer map, int w, int h) {
            if (pontos == null || pontos.isEmpty()) return;

            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            g.setColor(new Color(248, 158, 49));
            g.setStroke(new BasicStroke(4));
            for (int i = 0; i < pontos.size() - 1; i++) {
                Point2D p1 = map.convertGeoPositionToPoint(new GeoPosition(pontos.get(i).getLatitude(), pontos.get(i).getLongitude()));
                Point2D p2 = map.convertGeoPositionToPoint(new GeoPosition(pontos.get(i + 1).getLatitude(), pontos.get(i + 1).getLongitude()));
                g.drawLine((int) p1.getX(), (int) p1.getY(), (int) p2.getX(), (int) p2.getY());
            }

            g.setColor(new Color(248, 158, 49));
            for (PontoRota pr : pontos) {
                Point2D pixel = map.convertGeoPositionToPoint(new GeoPosition(pr.getLatitude(), pr.getLongitude()));
                g.fillOval((int) pixel.getX() - 6, (int) pixel.getY() - 6, 12, 12);
            }
        }
    }

    private static class ParadaPainter implements Painter<JXMapViewer> {
        private final List<PontoOnibus> paradas;

        public ParadaPainter(List<PontoOnibus> paradas) {
            this.paradas = paradas;
        }

        @Override
        public void paint(Graphics2D g, JXMapViewer map, int w, int h) {
            if (paradas == null || paradas.isEmpty()) return;

            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            for (PontoOnibus po : paradas) {
                Point2D pixel = map.convertGeoPositionToPoint(new GeoPosition(po.getLatitude(), po.getLongitude()));
                int x = (int) pixel.getX();
                int y = (int) pixel.getY();

                g.setColor(new Color(21, 80, 150));
                g.fillOval(x - 7, y - 7, 14, 14);
            }
        }
    }
    
    public void removerPontoOnibus(int indice) {
        if (!editando || indice < 0 || indice >= pontosOnibus.size()) return;
            pontosOnibus.remove(indice);

            atualizarPainters();
            atualizarListaLateral();
        }

    public void iniciarEdicao(int idRota) {
        this.editando = true;
        this.idRotaEmEdicao = idRota;

        try {
            RotaDAO dao = new RotaDAO();
            Rota rota = dao.buscarCompleta(idRota);

            if (rota != null) {
                this.pontosTrajeto = new ArrayList<>(rota.getListaCliquesTrajeto());
                this.pontosOnibus = new ArrayList<>(rota.getListaPontosOnibus());

                atualizarPainters();

                jPDescricaoRotas.carregarDadosDaRota(idRota);

                CardLayout cl = (CardLayout) jPDetalhes.getLayout();
                cl.show(jPDetalhes, "cardDescricaoRota");

                jBCadastrarRota.setVisible(false);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar rota para edição: " + e.getMessage());
        }
    }
    
    private void jBCadastrarRotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBCadastrarRotaActionPerformed
        this.editando = true;
        this.idRotaEmEdicao = 0;
    
        pontosTrajeto.clear();
        pontosOnibus.clear();
        atualizarPainters();
        
        CardLayout cl = (CardLayout) jPDetalhes.getLayout();
        cl.show(jPDetalhes, "cardDescricaoRota");
        jBCadastrarRota.setVisible(false);
        
        this.revalidate();
        this.repaint();
    }//GEN-LAST:event_jBCadastrarRotaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBCadastrarRota;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPCabecalho;
    private br.com.mycompany.chaponibus.admin.view.rotas.JPDescricaoRotas jPDescricaoRotas;
    private javax.swing.JPanel jPDetalhes;
    private javax.swing.JPanel jPMapa;
    private javax.swing.JPanel jPPrincipal;
    private br.com.mycompany.chaponibus.admin.view.rotas.JPTabelaRotas jPTabelaRotas;
    private javax.swing.JPanel jPTelaPrincipal;
    // End of variables declaration//GEN-END:variables
}
