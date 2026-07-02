/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package br.com.mycompany.chaponibus.admin.view.rotas;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Painter;
import javax.swing.SwingUtilities;
import javax.swing.event.MouseInputListener;
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.OSMTileFactoryInfo;
import org.jxmapviewer.input.PanMouseInputListener;
import org.jxmapviewer.input.ZoomMouseWheelListenerCenter;
import org.jxmapviewer.painter.CompoundPainter;
import org.jxmapviewer.viewer.DefaultTileFactory;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.TileFactoryInfo;

/**
 *
 * @author ce498
 */
public class JPRotas extends javax.swing.JPanel {
    
    public enum ModoEdicao {
        ROTA, ONIBUS
    }
    
    private JXMapViewer mapViewer;
    private ModoEdicao modoAtual = ModoEdicao.ROTA;
    private boolean editando = false;
    private List<GeoPosition> pontosTrajeto = new ArrayList<>();
    private List<GeoPosition> pontosOnibus = new ArrayList<>();

    /**
     * Creates new form JPRotas
     */
    public JPRotas() {
        initComponents();
        
        CardLayout cl = (CardLayout) jPDetalhes.getLayout();
        cl.show(jPDetalhes, "cardTabelaRotas");
        
        configurarMapa();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPCabecalho = new javax.swing.JPanel();
        jBCadastrarRota = new javax.swing.JButton();
        jPTelaPrincipal = new javax.swing.JPanel();
        jPPrincipal = new javax.swing.JPanel();
        jPMapa = new javax.swing.JPanel();
        jPDetalhes = new javax.swing.JPanel();
        jPTabelaRotas = new br.com.mycompany.chaponibus.admin.view.rotas.JPTabelaRotas();
        jPDescricaoRotas = new br.com.mycompany.chaponibus.admin.view.rotas.JPDescricaoRotas();

        setBackground(new java.awt.Color(244, 247, 249));
        setLayout(new java.awt.BorderLayout());

        jPCabecalho.setBackground(new java.awt.Color(244, 247, 249));
        jPCabecalho.setOpaque(false);
        jPCabecalho.setPreferredSize(new java.awt.Dimension(1110, 120));

        jBCadastrarRota.setBackground(new java.awt.Color(21, 80, 150));
        jBCadastrarRota.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jBCadastrarRota.setForeground(new java.awt.Color(255, 255, 255));
        jBCadastrarRota.setText("Nova rota");
        jBCadastrarRota.addActionListener(this::jBCadastrarRotaActionPerformed);

        javax.swing.GroupLayout jPCabecalhoLayout = new javax.swing.GroupLayout(jPCabecalho);
        jPCabecalho.setLayout(jPCabecalhoLayout);
        jPCabecalhoLayout.setHorizontalGroup(
            jPCabecalhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPCabecalhoLayout.createSequentialGroup()
                .addContainerGap(968, Short.MAX_VALUE)
                .addComponent(jBCadastrarRota, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );
        jPCabecalhoLayout.setVerticalGroup(
            jPCabecalhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPCabecalhoLayout.createSequentialGroup()
                .addGap(0, 93, Short.MAX_VALUE)
                .addComponent(jBCadastrarRota))
        );

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
            .addGap(0, 560, Short.MAX_VALUE)
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
                .addComponent(jPMapa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
        
        MouseInputListener mia = new PanMouseInputListener(mapViewer);
        mapViewer.addMouseListener(mia);
        mapViewer.addMouseMotionListener(mia);
        mapViewer.addMouseWheelListener(new ZoomMouseWheelListenerCenter(mapViewer));
        
        mapViewer.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!editando) return; 

                if (SwingUtilities.isLeftMouseButton(e)) {
                    
                    if (modoAtual == ModoEdicao.ROTA && !pontosTrajeto.isEmpty()) {
                        GeoPosition primeiroPonto = pontosTrajeto.get(0);
                        
                        java.awt.geom.Point2D telaPrimeiroPonto = mapViewer.convertGeoPositionToPoint(primeiroPonto);
                        
                        if (telaPrimeiroPonto != null) {
                            double distancia = e.getPoint().distance(telaPrimeiroPonto);
                            
                            if (distancia <= 15.0) {
                                pontosTrajeto.add(primeiroPonto);
                                atualizarPainters();
                                return;
                            }
                        }
                    }

                    GeoPosition geo = mapViewer.convertPointToGeoPosition(e.getPoint());

                    if (modoAtual == ModoEdicao.ROTA) {
                        pontosTrajeto.add(geo);
                    } else if (modoAtual == ModoEdicao.ONIBUS) {
                        pontosOnibus.add(geo);
                    }
                    
                    atualizarPainters();
                }
            }
        });
        
        mapViewer.getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW)
                 .put(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Z, java.awt.event.InputEvent.CTRL_DOWN_MASK), "desfazer");
                 
        mapViewer.getActionMap().put("desfazer", new javax.swing.AbstractAction() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                desfazerUltimoPonto();
            }
        });
        
        jPMapa.setLayout(new BorderLayout());
        jPMapa.add(mapViewer);
        jPMapa.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(21,80,150), 1));
    }
    
    public void setModoAtual(ModoEdicao modo) {
        this.modoAtual = modo;
    }

    private void atualizarPainters() {
        List<org.jxmapviewer.painter.Painter<JXMapViewer>> painters = new ArrayList<>();
        
        painters.add(new RotaPainter(pontosTrajeto));
        painters.add(new ParadasPainter(pontosOnibus));
        
        CompoundPainter<JXMapViewer> compoundPainter = new CompoundPainter<>(painters);
        mapViewer.setOverlayPainter(compoundPainter);
        mapViewer.repaint();
    }
    
    private class RotaPainter implements org.jxmapviewer.painter.Painter<JXMapViewer> {
        private final List<GeoPosition> pontos;

        public RotaPainter(List<GeoPosition> pontos) {
            this.pontos = pontos;
        }

        @Override
        public void paint(Graphics2D g, JXMapViewer map, int w, int h) {
            if (pontos == null || pontos.size() < 2) return;

            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g.setColor(new Color(255, 0, 0, 180)); // Vermelho com leve transparência
            g.setStroke(new BasicStroke(4));

            for (int i = 0; i < pontos.size() - 1; i++) {
                Point2D p1 = map.convertGeoPositionToPoint(pontos.get(i));
                Point2D p2 = map.convertGeoPositionToPoint(pontos.get(i + 1));
                g.drawLine((int) p1.getX(), (int) p1.getY(), (int) p2.getX(), (int) p2.getY());
            }
        }
    }
    
    private class ParadasPainter implements org.jxmapviewer.painter.Painter<JXMapViewer> {
        private final List<GeoPosition> paradas;

        public ParadasPainter(List<GeoPosition> paradas) {
            this.paradas = paradas;
        }

        @Override
        public void paint(Graphics2D g, JXMapViewer map, int w, int h) {
            if (paradas == null) return;

            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g.setColor(Color.BLUE);

            for (GeoPosition p : paradas) {
                Point2D pixel = map.convertGeoPositionToPoint(p);
                g.fillOval((int) pixel.getX() - 6, (int) pixel.getY() - 6, 12, 12);
            }
        }
    }
    
    public void exibirTabela() {
        editando = false;
        
        pontosTrajeto.clear();
        pontosOnibus.clear();
        atualizarPainters();
        
        CardLayout cl = (CardLayout) jPDetalhes.getLayout();
        cl.show(jPDetalhes, "cardTabelaRotas");
    }
    
    public List<GeoPosition> getPontosTrajeto() {
        return pontosTrajeto;
    }

    public List<GeoPosition> getPontosOnibus() {
        return pontosOnibus;
    }
    
    public void desfazerUltimoPonto() {
        if (!editando) return;

        if (modoAtual == ModoEdicao.ROTA && !pontosTrajeto.isEmpty()) {
            pontosTrajeto.remove(pontosTrajeto.size() - 1);
        } else if (modoAtual == ModoEdicao.ONIBUS && !pontosOnibus.isEmpty()) {
            pontosOnibus.remove(pontosOnibus.size() - 1);
        }
        
        atualizarPainters();
    }
    
    private void jBCadastrarRotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBCadastrarRotaActionPerformed
        editando = true;
        CardLayout cl = (CardLayout) jPDetalhes.getLayout();
        cl.show(jPDetalhes, "cardDescricaoRota");
    }//GEN-LAST:event_jBCadastrarRotaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBCadastrarRota;
    private javax.swing.JPanel jPCabecalho;
    private br.com.mycompany.chaponibus.admin.view.rotas.JPDescricaoRotas jPDescricaoRotas;
    private javax.swing.JPanel jPDetalhes;
    private javax.swing.JPanel jPMapa;
    private javax.swing.JPanel jPPrincipal;
    private br.com.mycompany.chaponibus.admin.view.rotas.JPTabelaRotas jPTabelaRotas;
    private javax.swing.JPanel jPTelaPrincipal;
    // End of variables declaration//GEN-END:variables
}
