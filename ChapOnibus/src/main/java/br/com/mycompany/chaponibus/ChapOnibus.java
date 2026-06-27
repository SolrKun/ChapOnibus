/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package br.com.mycompany.chaponibus;

import br.com.mycompany.chaponibus.view.JFEstruturaCelular;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import javax.swing.UIManager;

/**
 *
 * @author ce498
 */
public class ChapOnibus {

    public static void main(String[] args) {
        FlatMacLightLaf.setup();
        
        UIManager.put("Component.arc", 15);
        UIManager.put("Component.focusWidth", 3);
//        UIManager.put("Component.focusedBorderColor", "fade(#FF8C00, 70%)");
        UIManager.put("Component.focusColor", "fade(#FF8C00, 25%)");
        UIManager.put("Button.arc", 30);
        UIManager.put("TextComponent.arc", 20);
        UIManager.put("Button.margin", "6, 6, 6, 6");
        
        java.awt.EventQueue.invokeLater(() -> {
            new JFEstruturaCelular().setVisible(true);
        });
    }
}
