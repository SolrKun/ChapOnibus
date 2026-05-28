/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package br.com.mycompany.chaponibus.admin;

import br.com.mycompany.chaponibus.admin.view.JFLogin;
import br.com.mycompany.chaponibus.admin.view.JFLoginTeste;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.UIManager;

/**
 *
 * @author ce498
 */
public class ChapOnibusAdmin {

    public static void main(String[] args) {
        FlatRobotoFont.install();
        FlatLaf.registerCustomDefaultsSource("br.com.mycompany.chaponibus.admin.themes");
        FlatMacLightLaf.setup();
        UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 13));
        EventQueue.invokeLater(() -> {new JFLoginTeste().setVisible(true);});
    }
}
