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
        UIManager.put("Button.arc", 15);
        UIManager.put("Component.shadow", true);
        
        java.awt.EventQueue.invokeLater(() -> {
            new JFEstruturaCelular().setVisible(true);
        });
    }
}
