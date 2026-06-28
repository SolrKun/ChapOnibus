package br.com.mycompany.chaponibus.admin.view;

import java.awt.*;
import javax.swing.*;

public class PanelTop extends JPanel {

    public PanelTop() {
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {

        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        // COR AZUL
        g2.setColor(new Color(50,50,134));

        // RETÂNGULO ARREDONDADO
        g2.fillRoundRect(
                0,
                0,
                getWidth(),
                getHeight() + 40,
                40,
                40);

        g2.dispose();

        super.paintComponent(g);
    }
}