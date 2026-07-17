/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.hydroponicgarden1;
import javax.swing.ImageIcon;
import java.awt.Image;
import com.mycompany.hydroponicgarden1.dao.UserDAO;
import javax.swing.JOptionPane;
import com.mycompany.hydroponicgarden1.model.User;
import com.mycompany.hydroponicgarden1.session.session;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

/**
 *
 * @author valer
 */
public class Login extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Login.class.getName());
 
    /**
     * Creates new form Login
     */
    public Login() {
        initComponents();
       // Logo principal
ImageIcon logo = new ImageIcon(getClass().getResource("/img/iconito.jpg"));
jLabel9.setIcon(new ImageIcon(
    logo.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH)
));

// Imagen de usuario
ImageIcon pass = new ImageIcon(getClass().getResource("/img/passimg.png"));
jLabel11.setIcon(new ImageIcon(
    pass.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)
));
 
ImageIcon userimg = new ImageIcon(getClass().getResource("/img/userimg.png"));
jLabel10.setIcon(new ImageIcon(
    userimg.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)
));
// Placeholder para correo o teléfono
txtEmailOrPhone.addFocusListener(new FocusAdapter() {
    @Override
    public void focusGained(FocusEvent e) {
        if (txtEmailOrPhone.getText().equals("Ingresa tu correo o télefono")) {
            txtEmailOrPhone.setText("");
            txtEmailOrPhone.setForeground(java.awt.Color.BLACK);
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if (txtEmailOrPhone.getText().trim().isEmpty()) {
            txtEmailOrPhone.setText("Ingresa tu correo o télefono");
            txtEmailOrPhone.setForeground(java.awt.Color.GRAY);
        }
    }
});

// Placeholder para contraseña
txtPassword.setEchoChar((char)0); // Mostrar texto al inicio

txtPassword.addFocusListener(new FocusAdapter() {
    @Override
    public void focusGained(FocusEvent e) {
        if (String.valueOf(txtPassword.getPassword()).equals("Ingresa la contraseña")) {
            txtPassword.setText("");
            txtPassword.setEchoChar('•'); // Oculta la contraseña
            txtPassword.setForeground(java.awt.Color.BLACK);
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if (String.valueOf(txtPassword.getPassword()).isEmpty()) {
            txtPassword.setEchoChar((char)0); // Mostrar placeholder
            txtPassword.setText("Ingresa la contraseña");
            txtPassword.setForeground(java.awt.Color.GRAY);
        }
    }
});
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtEmailOrPhone = new javax.swing.JTextField();
        txtPassword = new javax.swing.JPasswordField();
        btnLogin = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblForgotPassword = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblRegister = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setForeground(new java.awt.Color(204, 255, 204));

        jPanel1.setBackground(new java.awt.Color(0, 51, 0));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel3.setBackground(new java.awt.Color(0, 51, 0));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel1.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 60)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(13, 111, 13));
        jLabel1.setText("HydroFarm");

        jLabel2.setFont(new java.awt.Font("Serif", 0, 48)); // NOI18N
        jLabel2.setText("Inicia sesión para continuar");

        txtEmailOrPhone.setBackground(new java.awt.Color(204, 204, 204));
        txtEmailOrPhone.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        txtEmailOrPhone.setForeground(new java.awt.Color(102, 102, 102));
        txtEmailOrPhone.setText("Ingresa tu correo o télefono");
        txtEmailOrPhone.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 0)));
        txtEmailOrPhone.addActionListener(this::txtEmailOrPhoneActionPerformed);

        txtPassword.setBackground(new java.awt.Color(204, 204, 204));
        txtPassword.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        txtPassword.setForeground(new java.awt.Color(102, 102, 102));
        txtPassword.setText("Ingresa la contraseña");
        txtPassword.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 0)));

        btnLogin.setBackground(new java.awt.Color(51, 153, 0));
        btnLogin.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btnLogin.setForeground(new java.awt.Color(0, 51, 0));
        btnLogin.setText("Iniciar sesión");
        btnLogin.addActionListener(this::btnLoginActionPerformed);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 51, 0));
        jLabel3.setText("Correo o teléfono");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 51, 0));
        jLabel4.setText("Contraseña");

        lblForgotPassword.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblForgotPassword.setForeground(new java.awt.Color(102, 204, 0));
        lblForgotPassword.setText("¿Olvidaste tu contraseña?");
        lblForgotPassword.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblForgotPasswordMouseClicked(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 51, 0));
        jLabel6.setText("¿No tienes cuenta?");

        lblRegister.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblRegister.setForeground(new java.awt.Color(102, 204, 0));
        lblRegister.setText("Registrate aquí");
        lblRegister.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblRegisterMouseClicked(evt);
            }
        });

        jLabel8.setBackground(new java.awt.Color(0, 102, 102));
        jLabel8.setForeground(new java.awt.Color(0, 102, 0));
        jLabel8.setMaximumSize(new java.awt.Dimension(460, 430));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(238, 238, 238)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(48, 48, 48)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(157, 157, 157)
                                .addComponent(jLabel2))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(237, 237, 237)
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(257, 257, 257)
                                .addComponent(jLabel1))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(61, 61, 61)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 708, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtEmailOrPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 708, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(320, 320, 320)
                                .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40)
                                .addComponent(jLabel4)
                                .addGap(378, 378, 378))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(lblForgotPassword)
                                .addGap(110, 110, 110))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(57, 57, 57)
                                .addComponent(lblRegister)
                                .addGap(280, 280, 280)))))
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtEmailOrPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblForgotPassword)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(122, 190, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(94, 94, 94)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(lblRegister))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        // TODO add your handling code here:
        // Get user input
String emailOrPhone = txtEmailOrPhone.getText().trim();
String password = new String(txtPassword.getPassword());

if (emailOrPhone.isEmpty()
        || emailOrPhone.equals("Ingresa tu correo o télefono")
        || password.isEmpty()
        || password.equals("Ingresa la contraseña")) {
}

// Authenticate user
UserDAO userDAO = new UserDAO();

if (userDAO.login(emailOrPhone, password)) {

    // Get the logged-in user
    User user = userDAO.getUser(emailOrPhone);

    // Save user in session
    session.setCurrentUser(user);

    JOptionPane.showMessageDialog(
            this,
            "Bienvenido.");

    // Open home window
   new Inicio().setVisible(true);

    // Close login window
    dispose();

} else {

    JOptionPane.showMessageDialog(
            this,
            "Correo, teléfono o contraseña incorrectos.");

}
    
    }//GEN-LAST:event_btnLoginActionPerformed

    private void txtEmailOrPhoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailOrPhoneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailOrPhoneActionPerformed

    private void lblForgotPasswordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblForgotPasswordMouseClicked
        // TODO add your handling code here:
    String emailOrPhone = txtEmailOrPhone.getText().trim();

if (emailOrPhone.isEmpty()
        || emailOrPhone.equals("Ingresa tu correo o télefono")) {

    JOptionPane.showMessageDialog(
            this,
            "Ingresa tu correo o teléfono.");

    return;
}

UserDAO dao = new UserDAO();

User user = dao.getUser(emailOrPhone);

if (user == null) {

    JOptionPane.showMessageDialog(
            this,
            "El usuario no existe.");

    return;
}

new new_password(user).setVisible(true);

dispose();
    }//GEN-LAST:event_lblForgotPasswordMouseClicked

    private void lblRegisterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblRegisterMouseClicked
        // TODO add your handling code here
    Record registro = new Record();
    registro.setVisible(true);
    this.dispose();
    }//GEN-LAST:event_lblRegisterMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new Login().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lblForgotPassword;
    private javax.swing.JLabel lblRegister;
    private javax.swing.JTextField txtEmailOrPhone;
    private javax.swing.JPasswordField txtPassword;
    // End of variables declaration//GEN-END:variables
}
