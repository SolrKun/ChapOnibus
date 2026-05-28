package br.com.mycompany.chaponibus.admin.view;

import java.awt.*;
import javax.swing.*;
import java.awt.Color;

public class PanelRound extends JPanel {

    private int cornerRadius = 30;

    public PanelRound() {
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {

        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        // sombra
        g2.setColor(new Color(0, 0, 0, 20));
        g2.fillRoundRect(
                8,
                8,
                getWidth() - 10,
                getHeight() - 10,
                cornerRadius,
                cornerRadius);

        // painel branco
        g2.setColor(Color.WHITE);

        g2.fillRoundRect(
                0,
                0,
                getWidth() - 10,
                getHeight() - 10,
                cornerRadius,
                cornerRadius);

        g2.dispose();

        super.paintComponent(g);
    }
}