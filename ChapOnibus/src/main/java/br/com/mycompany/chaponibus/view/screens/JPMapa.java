/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package br.com.mycompany.chaponibus.view.screens;

import br.com.mycompany.chaponibus.dao.RotaDAO;
import br.com.mycompany.chaponibus.model.Onibus;
import br.com.mycompany.chaponibus.model.PontoOnibus;
import br.com.mycompany.chaponibus.model.PontoRota;
import br.com.mycompany.chaponibus.model.Rota;
import br.com.mycompany.chaponibus.util.Sessao;
import br.com.mycompany.chaponibus.view.JFEstruturaCelular;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Point2D;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.event.MouseInputListener;
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
public class JPMapa extends javax.swing.JPanel {
    
    private static class OnibusAtivo {
        br.com.mycompany.chaponibus.model.Onibus dadosOnibus;
        Rota rota;
        List<PontoRota> trajeto;
        int indiceAtual;
        int subIndice;
        GeoPosition posicaoAtual;
    }
    
    private JXMapViewer mapViewer;
    private JFEstruturaCelular telaPrincipal;
    private Sessao sessaoAtual = new Sessao();
    
    private List<PontoRota> pontosTrajeto = new ArrayList<>();
    private List<PontoOnibus> pontosOnibus = new ArrayList<>();
    private List<Rota> listaDeRotasDisponiveis = new ArrayList<>();
    
    private List<OnibusAtivo> listaOnibusAtivos = new java.util.ArrayList<>();
    private javax.swing.Timer timerSimulacaoCentral;
    
    public JPMapa(JFEstruturaCelular tela) {        
        this.telaPrincipal = tela;
        initComponents();
        
        jBPerfil.putClientProperty("FlatLaf.style", "arc: 999");
        
        jPAbaInferior.putClientProperty("FlatLaf.style", "arc: 30; background: #FFFFFF;");
        jPAbaInferior.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        TileFactoryInfo info = new OSMTileFactoryInfo("OpenStreetMap", "https://tile.openstreetmap.org");
        DefaultTileFactory tileFactory = new DefaultTileFactory(info);
        
        mapViewer = new JXMapViewer();
        mapViewer.setTileFactory(tileFactory);
        
        GeoPosition chapeco = new GeoPosition(-27.1016667, -52.6147263);
        mapViewer.setZoom(5);
        mapViewer.setAddressLocation(chapeco);
        
        MouseInputListener mia = new PanMouseInputListener(mapViewer);
        mapViewer.addMouseListener(mia);
        mapViewer.addMouseMotionListener(mia);
        mapViewer.addMouseWheelListener(new ZoomMouseWheelListenerCenter(mapViewer));
        
        map.setLayout(new BorderLayout());
        map.add(mapViewer);
        
        recarregarMapaComDadosDoBanco();
        
        jCBFiltroRotas.addActionListener(e -> filtrarRotasNoMapa());
    }
    
    public void recarregarMapaComDadosDoBanco() {
        try {
            RotaDAO dao = new RotaDAO();
            List<Rota> rotasSimples = dao.listarTodas(); 
            
            listaDeRotasDisponiveis.clear();
            jCBFiltroRotas.removeAllItems();
            jCBFiltroRotas.addItem("TODAS AS ROTAS");

            listaOnibusAtivos.clear();
            java.util.Random random = new java.util.Random();

            for (Rota r : rotasSimples) {
                Rota detalhada = dao.buscarCompleta(r.getId());
                
                if (detalhada != null) {
                    listaDeRotasDisponiveis.add(detalhada); 
                    jCBFiltroRotas.addItem(detalhada.getNomeRota());

                    if (detalhada.getListaCliquesTrajeto() != null && !detalhada.getListaCliquesTrajeto().isEmpty()) {
                        String placa = "MCO-" + (random.nextInt(8999) + 1000);
                        String modelo = detalhada.getNomeRota().toLowerCase().contains("efapi") ? "Marcopolo Torino" : "Mercedes-Benz Padron";
                        br.com.mycompany.chaponibus.model.Onibus veiculo = new br.com.mycompany.chaponibus.model.Onibus(placa, modelo, 60);
                        
                        OnibusAtivo novoOnibus = new OnibusAtivo();
                        novoOnibus.dadosOnibus = veiculo;
                        novoOnibus.rota = detalhada;
                        novoOnibus.trajeto = detalhada.getListaCliquesTrajeto();
                        
                        if (novoOnibus.trajeto.size() > 1) {
                            novoOnibus.indiceAtual = random.nextInt(novoOnibus.trajeto.size() - 1);
                            novoOnibus.subIndice = random.nextInt(15);
                            PontoRota pRef = novoOnibus.trajeto.get(novoOnibus.indiceAtual);
                            novoOnibus.posicaoAtual = new GeoPosition(pRef.getLatitude(), pRef.getLongitude());
                        }
                        listaOnibusAtivos.add(novoOnibus);
                    }
                }
            }

            iniciarSimulacaoGlobal();
            jCBFiltroRotas.setSelectedIndex(0);
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar rotas no mapa: " + e.getMessage());
        }
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
            g.setStroke(new BasicStroke(3));
            for (int i = 0; i < pontos.size() - 1; i++) {
                Point2D p1 = map.convertGeoPositionToPoint(new GeoPosition(pontos.get(i).getLatitude(), pontos.get(i).getLongitude()));
                Point2D p2 = map.convertGeoPositionToPoint(new GeoPosition(pontos.get(i + 1).getLatitude(), pontos.get(i + 1).getLongitude()));
                g.drawLine((int) p1.getX(), (int) p1.getY(), (int) p2.getX(), (int) p2.getY());
            }

//            g.setColor(new Color(248, 158, 49));;
//            for (PontoRota pr : pontos) {
//                Point2D pixel = map.convertGeoPositionToPoint(new GeoPosition(pr.getLatitude(), pr.getLongitude()));
//                g.fillOval((int) pixel.getX() - 6, (int) pixel.getY() - 6, 12, 12);
//            }
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
                g.fillOval(x - 4, y - 4, 7, 7);
            }
        }
    }
    
    private void filtrarRotasNoMapa() {
        int indexSelecionado = jCBFiltroRotas.getSelectedIndex();

        if (indexSelecionado <= 0) {
            desenharRotasNoMapa(null);
            return;
        }

        Rota rotaEscolhida = listaDeRotasDisponiveis.get(indexSelecionado - 1);
        desenharRotasNoMapa(rotaEscolhida);

        if (rotaEscolhida.getListaCliquesTrajeto() != null && !rotaEscolhida.getListaCliquesTrajeto().isEmpty()) {
            java.util.Set<GeoPosition> todasAsPosicoes = new java.util.HashSet<>();
            for (PontoRota p : rotaEscolhida.getListaCliquesTrajeto()) {
                todasAsPosicoes.add(new GeoPosition(p.getLatitude(), p.getLongitude()));
            }
            mapViewer.zoomToBestFit(todasAsPosicoes, 0.7);
        }
    }
    
    private void desenharRotasNoMapa(Rota rotaFiltro) {
        List<Painter<JXMapViewer>> painters = new java.util.ArrayList<>();

        if (rotaFiltro != null) {
            painters.add(new RotaPainter(rotaFiltro.getListaCliquesTrajeto()));
            painters.add(new ParadaPainter(rotaFiltro.getListaPontosOnibus()));
        } else {
            for (Rota r : listaDeRotasDisponiveis) {
                painters.add(new RotaPainter(r.getListaCliquesTrajeto()));
                painters.add(new ParadaPainter(r.getListaPontosOnibus()));
            }
        }

        for (OnibusAtivo bus : listaOnibusAtivos) {
            if (rotaFiltro == null || bus.rota.getId() == rotaFiltro.getId()) {
                if (bus.posicaoAtual != null) {
                    painters.add(new OnibusSimuladoPainter(bus.posicaoAtual, bus.dadosOnibus));
                }
            }
        }

        CompoundPainter<JXMapViewer> compoundPainter = new CompoundPainter<>(painters);
        mapViewer.setOverlayPainter(compoundPainter);
        mapViewer.repaint();
    }
    
    private static class OnibusSimuladoPainter implements Painter<JXMapViewer> {
        private final GeoPosition posicaoOnibus;
        private final br.com.mycompany.chaponibus.model.Onibus dadosOnibus;
        private static java.awt.Image imgOnibus = null;

        public OnibusSimuladoPainter(GeoPosition posicaoOnibus, br.com.mycompany.chaponibus.model.Onibus dadosOnibus) {
            this.posicaoOnibus = posicaoOnibus;
            this.dadosOnibus = dadosOnibus;
            
            if (imgOnibus == null) {
                java.net.URL imgUrl = getClass().getResource("/br/com/mycompany/chaponibus/assets/bus-side-solid.png");
                if (imgUrl != null) {
                    imgOnibus = new javax.swing.ImageIcon(imgUrl).getImage();
                }
            }
        }

        @Override
        public void paint(Graphics2D g, JXMapViewer map, int w, int h) {
            if (posicaoOnibus == null) return;

            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            Point2D pixel = map.convertGeoPositionToPoint(posicaoOnibus);
            int x = (int) pixel.getX();
            int y = (int) pixel.getY();

            int tamanhoFundo = 24;
            g.setColor(new Color(130, 61, 0));
            g.fillOval(x - (tamanhoFundo / 2), y - (tamanhoFundo / 2), tamanhoFundo, tamanhoFundo);

//            g.setColor(Color.WHITE);
//            g.setStroke(new BasicStroke(1.5f));
//            g.drawOval(x - (tamanhoFundo / 2), y - (tamanhoFundo / 2), tamanhoFundo, tamanhoFundo);

            if (imgOnibus != null) {
                int tamanhoIcone = 16;
                g.drawImage(imgOnibus, x - (tamanhoIcone / 2), y - (tamanhoIcone / 2), tamanhoIcone, tamanhoIcone, null);
            } else {
                g.setColor(Color.WHITE);
                g.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 10));
                g.drawString("BUS", x - 10, y + 4);
            }

//            if (dadosOnibus != null) {
//                String textoInformativo = dadosOnibus.getModelo() + " [" + dadosOnibus.getPlaca() + "]";
//
//                g.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 10));
//                int larguraDoTexto = g.getFontMetrics().stringWidth(textoInformativo);
//                int alturaDoTexto = 16;
//
//                g.setColor(new Color(30, 30, 30, 200));
//                g.fillRoundRect(x - (larguraDoTexto / 2) - 6, y - 38, larguraDoTexto + 12, alturaDoTexto, 6, 6);
//
//                g.setColor(Color.WHITE);
//                g.drawString(textoInformativo, x - (larguraDoTexto / 2), y - 26);
//            }
        }
    }
    
    private void iniciarSimulacaoGlobal() {
        if (timerSimulacaoCentral != null && timerSimulacaoCentral.isRunning()) {
            timerSimulacaoCentral.stop();
        }

        int passosPorSegmento = 300;

        timerSimulacaoCentral = new javax.swing.Timer(50, e -> {
            for (OnibusAtivo bus : listaOnibusAtivos) {
                if (bus.trajeto == null || bus.trajeto.size() < 2) continue;

                PontoRota pontoAtual = bus.trajeto.get(bus.indiceAtual);
                PontoRota pontoProximo = bus.trajeto.get(bus.indiceAtual + 1);

                double t = (double) bus.subIndice / passosPorSegmento;
                double lat = pontoAtual.getLatitude() + (pontoProximo.getLatitude() - pontoAtual.getLatitude()) * t;
                double lon = pontoAtual.getLongitude() + (pontoProximo.getLongitude() - pontoAtual.getLongitude()) * t;

                bus.posicaoAtual = new GeoPosition(lat, lon);
                bus.subIndice++;

                if (bus.subIndice > passosPorSegmento) {
                    bus.subIndice = 0;
                    bus.indiceAtual++;

                    if (bus.indiceAtual >= bus.trajeto.size() - 1) {
                        bus.indiceAtual = 0;
                    }
                }
            }

            int indexSelecionado = jCBFiltroRotas.getSelectedIndex();
            Rota rotaFiltrada = null;
            if (indexSelecionado > 0) {
                rotaFiltrada = listaDeRotasDisponiveis.get(indexSelecionado - 1);
            }
            desenharRotasNoMapa(rotaFiltrada);
        });

        timerSimulacaoCentral.start();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLayeredPane1 = new javax.swing.JLayeredPane();
        jPAbaSuperior = new javax.swing.JPanel();
        jBPerfil = new javax.swing.JButton();
        jPAbaInferior = new javax.swing.JPanel();
        jCBFiltroRotas = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        map = new javax.swing.JPanel();

        jLayeredPane1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPAbaSuperior.setOpaque(false);

        jBPerfil.setBackground(new java.awt.Color(244, 247, 249));
        jBPerfil.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/mycompany/chaponibus/assets/perfil.png"))); // NOI18N
        jBPerfil.addActionListener(this::jBPerfilActionPerformed);

        javax.swing.GroupLayout jPAbaSuperiorLayout = new javax.swing.GroupLayout(jPAbaSuperior);
        jPAbaSuperior.setLayout(jPAbaSuperiorLayout);
        jPAbaSuperiorLayout.setHorizontalGroup(
            jPAbaSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPAbaSuperiorLayout.createSequentialGroup()
                .addContainerGap(314, Short.MAX_VALUE)
                .addComponent(jBPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPAbaSuperiorLayout.setVerticalGroup(
            jPAbaSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPAbaSuperiorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jBPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(74, Short.MAX_VALUE))
        );

        jLayeredPane1.setLayer(jPAbaSuperior, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.add(jPAbaSuperior, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 370, 130));

        jPAbaInferior.setBackground(new java.awt.Color(255, 255, 255));
        jPAbaInferior.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPAbaInferior.setOpaque(false);
        jPAbaInferior.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPAbaInferior.add(jCBFiltroRotas, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 340, 30));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Rota");
        jPAbaInferior.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, -1));

        jLayeredPane1.setLayer(jPAbaInferior, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.add(jPAbaInferior, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 620, 375, 150));

        javax.swing.GroupLayout mapLayout = new javax.swing.GroupLayout(map);
        map.setLayout(mapLayout);
        mapLayout.setHorizontalGroup(
            mapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 380, Short.MAX_VALUE)
        );
        mapLayout.setVerticalGroup(
            mapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
        );

        jLayeredPane1.add(map, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 380, 800));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jBPerfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBPerfilActionPerformed
        if (sessaoAtual.isLogado()) {
            telaPrincipal.mudarTela("cardPerfil");
            telaPrincipal.atualizarPerfil();
        } else {
            telaPrincipal.mudarTela("cardLogin");
        }
    }//GEN-LAST:event_jBPerfilActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBPerfil;
    private javax.swing.JComboBox<String> jCBFiltroRotas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPAbaInferior;
    private javax.swing.JPanel jPAbaSuperior;
    private javax.swing.JPanel map;
    // End of variables declaration//GEN-END:variables
}
