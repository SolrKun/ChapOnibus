/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package br.com.mycompany.chaponibus.admin;

import br.com.mycompany.chaponibus.admin.view.JFLogin;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import javax.swing.UIManager;

/**
 *
 * @author ce498
 */
public class ChapOnibusAdmin {

    public static void main(String[] args) {
        FlatMacLightLaf.setup();
        
        UIManager.put("TextComponent.arc", 10);
                
        java.awt.EventQueue.invokeLater(() -> {
            new JFLogin().setVisible(true);
        });
    }
}
