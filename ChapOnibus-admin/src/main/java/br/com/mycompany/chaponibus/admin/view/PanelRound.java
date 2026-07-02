package br.com.mycompany.chaponibus.admin.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JPanel;

public class PanelRound extends JPanel {

    private int round = 30;

    public PanelRound(int round) {
        this.round = round;
        setOpaque(false);
    }

    @Override
    public void paint(Graphics g) {
        // 1. Deixa o JXMapViewer desenhar o mapa retangular normalmente primeiro
        super.paint(g);

        // 2. Agora criamos a máscara para cobrir os cantos sobressalentes
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Cor de fundo do seu JPRotas (Cinza bem claro)
        // Isso fará com que os cantos "sumam" integrando-se ao fundo da tela
        g2.setColor(new Color(244, 247, 249));

        int width = getWidth();
        int height = getHeight();

        // Criamos uma área que representa o retângulo total do painel
        Area area = new Area(new Rectangle2D.Float(0, 0, width, height));
        
        // Criamos a forma arredondada desejada
        RoundRectangle2D roundRect = new RoundRectangle2D.Float(0, 0, width, height, round, round);
        
        // O truque mágico: subtraímos a forma arredondada do retângulo total. 
        // O que sobra são EXATAMENTE as 4 quinas pontudas!
        area.subtract(new Area(roundRect));

        // Pintamos apenas as quinas com a cor do fundo
        g2.fill(area);

        g2.dispose();
    }
}