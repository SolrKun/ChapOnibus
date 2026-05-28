/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package br.com.mycompany.chaponibus.admin.view;

import com.formdev.flatlaf.FlatClientProperties;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author ce498
 */
public class JPLogin extends javax.swing.JPanel {

    /**
     * Creates new form JPLogin
     */
    public JPLogin() {
        initComponents();
        init();
    }
    
    private void init() {
        setLayout(new MigLayout("wrap,gapy 3", "[fill,300]"));
        
        add(new JLabel(iconFormatter("/br/com/mycompany/chaponibus/admin/assets/logo.png", 100, 100)));
        
        JLabel lbTitle=new JLabel("Bem-vindo de volta", JLabel.CENTER);
        lbTitle.putClientProperty(FlatClientProperties.STYLE, "" + 
                "font:bold  +15;");
        add(lbTitle, "gapy 8 8");
        
        add(new JLabel("Se conecte para acessar o painel,",JLabel.CENTER));
        add(new JLabel("dashboard e rotas.",JLabel.CENTER));

        JButton cmdFacebook=new JButton("Conecte-se com o Facebook", iconFormatter("/br/com/mycompany/chaponibus/admin/assets/Icon_Facebook.png", 18, 18));
        cmdFacebook.putClientProperty(FlatClientProperties.STYLE, "" + 
                "focusWidth:0;" + 
                "font:+1;");
        add(cmdFacebook, "gapy 15 10");
        
        JLabel lbSeparator = new JLabel("Ou entre com um email");
        lbSeparator.putClientProperty(FlatClientProperties.STYLE, "" + 
                "foreground:$Label.disabledForeground;" + 
                "font:-1;");
        
        // sizegroup to make group component are same size
        
        add(createSeparator(), "split 3, sizegroup g1");
        add(lbSeparator, "sizegroup g1");
        add(createSeparator(), "split 3, sizegroup g1");
        
        JLabel lbEmail = new JLabel("Email");
        lbEmail.putClientProperty(FlatClientProperties.STYLE, "" + 
                "font:bold;");
        add(lbEmail, "gapy 10 5");
        
        JTextField txtEmail = new JTextField();
        txtEmail.putClientProperty(FlatClientProperties.STYLE, "" + 
                "iconTextGap: 10;" +
                "margin: 4, 6, 4, 6;" + 
                "showClearButton: true;");
        txtEmail.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Digite seu email");
        txtEmail.putClientProperty(FlatClientProperties.TEXT_FIELD_LEADING_ICON, iconFormatter("/br/com/mycompany/chaponibus/admin/assets/email.png", 18, 18));
        
        add(txtEmail);
        
        JLabel lbPassword = new JLabel("Senha");
        lbPassword.putClientProperty(FlatClientProperties.STYLE, "" + 
                "font:bold;");
        add(lbPassword, "gapy 10 5, split 2");
        
        JButton cmdForgotPassword = createNoBorderButton("Esqueceu sua senha?");
        add(cmdForgotPassword, "grow 0, gapy 10 5");
        
        JPasswordField txtPassword = new JPasswordField();
        txtPassword.putClientProperty(FlatClientProperties.STYLE, "" + 
                "iconTextGap: 10;" + 
                "showRevealButton: true;");
        txtPassword.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Digite sua senha");
        txtPassword.putClientProperty(FlatClientProperties.TEXT_FIELD_LEADING_ICON, iconFormatter("/br/com/mycompany/chaponibus/admin/assets/password.png", 18, 18));
        add(txtPassword);
        
        add(new JCheckBox("Lembre-me por 30 dias"), "gapy 10 10");
        
        JButton cmdSignIn = new JButton("Entrar", iconFormatter("/br/com/mycompany/chaponibus/admin/assets/arrow.png", 18, 18)) {
            @Override
            public boolean isDefaultButton() {
                return true;
            }
        };
        cmdSignIn.putClientProperty(FlatClientProperties.STYLE, "" + 
                "foreground: #FFFFFF;" + 
                "iconTextGap: 10;");
        cmdSignIn.setHorizontalTextPosition(JButton.LEADING);
        add(cmdSignIn, "gapy n 10");
        
        JLabel lbNoAccount = new JLabel("Sem conta?");
        lbNoAccount.putClientProperty(FlatClientProperties.STYLE, "" + 
                "foreground: $Label.disabledForeground;");
        add(lbNoAccount, "split 2, gapx push n");
        
        JButton cmdCreateAccount = createNoBorderButton("Criar uma conta");
        
        add(cmdCreateAccount, "gapx n push");
    }
    
    private JSeparator createSeparator() {
        JSeparator separator = new JSeparator();
        separator.putClientProperty(FlatClientProperties.STYLE, "" + 
                "stripeIndent: 8;");
        return separator;
    }
    
    private ImageIcon iconFormatter(String imageUrl, int width, int height) {
        java.net.URL iconeOriginal = getClass().getResource(imageUrl);
        ImageIcon imagemRedimensionada = new ImageIcon(iconeOriginal);
        Image icon = imagemRedimensionada.getImage().getScaledInstance(width, height, Image.SCALE_AREA_AVERAGING);
        return new ImageIcon(icon);
    }
    
    private JButton createNoBorderButton(String text) {
        JButton button = new JButton(text);
        button.putClientProperty(FlatClientProperties.STYLE, "" + 
                "foreground: $Component.accentColor;" + 
                "margin: 1, 5, 1, 5;" + 
                "borderWidth: 0;" + 
                "focusWidth: 0;" +
                "innerFocusWidth: 0;" + 
                "background: null;");
        return button;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
