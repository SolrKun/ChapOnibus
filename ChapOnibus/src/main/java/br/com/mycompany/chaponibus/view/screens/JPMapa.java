/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package br.com.mycompany.chaponibus.view.screens;

import br.com.mycompany.chaponibus.util.Sessao;
import br.com.mycompany.chaponibus.view.JFEstruturaCelular;
import java.awt.BorderLayout;
import javax.swing.event.MouseInputListener;
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.OSMTileFactoryInfo;
import org.jxmapviewer.input.PanMouseInputListener;
import org.jxmapviewer.input.ZoomMouseWheelListenerCenter;
import org.jxmapviewer.viewer.DefaultTileFactory;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.TileFactoryInfo;

/**
 *
 * @author ce498
 */
public class JPMapa extends javax.swing.JPanel {
    
    private JXMapViewer mapViewer;
    private JFEstruturaCelular telaPrincipal;
    private Sessao sessaoAtual = new Sessao();
    
    public JPMapa(JFEstruturaCelular tela) {        
        this.telaPrincipal = tela;
        initComponents();
        
        jBPerfil.putClientProperty("FlatLaf.style", "arc: 999");
        
        jPAbaInferior.putClientProperty("FlatLaf.style", "arc: 30; background: #FFFFFF;");
        jPAbaInferior.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        jPAbaInferior.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        
        TileFactoryInfo info = new OSMTileFactoryInfo("OpenStreetMap", "https://tile.openstreetmap.org");
        DefaultTileFactory tileFactory = new DefaultTileFactory(info);
        
        mapViewer = new JXMapViewer();
        mapViewer.setTileFactory(tileFactory);
        
        GeoPosition chapeco = new GeoPosition(-27.0968, -52.6186);
        mapViewer.setZoom(5);
        mapViewer.setAddressLocation(chapeco);
        
        MouseInputListener mia = new PanMouseInputListener(mapViewer);
        mapViewer.addMouseListener(mia);
        mapViewer.addMouseMotionListener(mia);
        mapViewer.addMouseWheelListener(new ZoomMouseWheelListenerCenter(mapViewer));
        
        map.setLayout(new BorderLayout());
        map.add(mapViewer);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLayeredPane1 = new javax.swing.JLayeredPane();
        jPAbaSuperior = new javax.swing.JPanel();
        jBPerfil = new javax.swing.JButton();
        jPAbaInferior = new javax.swing.JPanel();
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

        javax.swing.GroupLayout jPAbaInferiorLayout = new javax.swing.GroupLayout(jPAbaInferior);
        jPAbaInferior.setLayout(jPAbaInferiorLayout);
        jPAbaInferiorLayout.setHorizontalGroup(
            jPAbaInferiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 373, Short.MAX_VALUE)
        );
        jPAbaInferiorLayout.setVerticalGroup(
            jPAbaInferiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 238, Short.MAX_VALUE)
        );

        jLayeredPane1.setLayer(jPAbaInferior, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.add(jPAbaInferior, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 560, 375, 240));

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
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPAbaInferior;
    private javax.swing.JPanel jPAbaSuperior;
    private javax.swing.JPanel map;
    // End of variables declaration//GEN-END:variables
}
