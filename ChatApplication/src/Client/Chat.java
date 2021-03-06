/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Client;

import Common.Message;
import Common.Utils;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class Chat extends javax.swing.JFrame implements InReceive {
    ServerListener serverListener;
    String nickName;
    /**
     * Creates new form Chat
     */
    public Chat(ServerListener serverListener, String thatUser, String nickName) {
        initComponents();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        this.serverListener = serverListener;
        this.serverListener.receive = this;
        this.nickName = nickName;
        jLbThatUser.setText(thatUser);
        setVisible(true);
    }
    
    public Chat() {
        initComponents();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
//        this.serverListener = serverListener;
//        this.serverListener.receive = this;
        setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jBtnSend = new javax.swing.JButton();
        jLbThatUser = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaInput = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        chatBox = new javax.swing.JTextArea();
        jBtnThoat = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jBtnSend.setBackground(new java.awt.Color(102, 153, 255));
        jBtnSend.setText("G???i");
        jBtnSend.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jBtnSend.setContentAreaFilled(false);
        jBtnSend.setFocusable(false);
        jBtnSend.setOpaque(true);
        jBtnSend.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jBtnSendMousePressed(evt);
            }
        });
        jBtnSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnSendActionPerformed(evt);
            }
        });

        jLbThatUser.setBackground(new java.awt.Color(255, 255, 255));
        jLbThatUser.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLbThatUser.setText("T??n ?????i ph????ng");

        jTextAreaInput.setColumns(20);
        jTextAreaInput.setRows(5);
        jScrollPane1.setViewportView(jTextAreaInput);

        chatBox.setEditable(false);
        chatBox.setColumns(20);
        chatBox.setRows(5);
        jScrollPane2.setViewportView(chatBox);

        jBtnThoat.setBackground(new java.awt.Color(255, 102, 102));
        jBtnThoat.setText("Tho??t");
        jBtnThoat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jBtnThoat.setContentAreaFilled(false);
        jBtnThoat.setFocusable(false);
        jBtnThoat.setOpaque(true);
        jBtnThoat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jBtnThoatMousePressed(evt);
            }
        });
        jBtnThoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnThoatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtnSend, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jScrollPane2)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jBtnThoat, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLbThatUser, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLbThatUser, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBtnThoat, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jBtnSend, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBtnSendMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnSendMousePressed
        // TODO add your handling code here:
        jBtnSend.setBackground(new Color(81,122,204));
    }//GEN-LAST:event_jBtnSendMousePressed

    private void jBtnSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnSendActionPerformed
        //SendButton.setBackground(Color.yellow);
        String chat = jTextAreaInput.getText().trim();
        if (!chat.equals("")) {
            chatBox.append("B???n: " + chat + "\n");
            serverListener.send(Utils.CHAT, chat);
            jTextAreaInput.setText("");
        }
    }//GEN-LAST:event_jBtnSendActionPerformed

    private void jBtnThoatMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnThoatMousePressed
        // TODO add your handling code here:
        jBtnThoat.setBackground(new Color(204,81,81));
    }//GEN-LAST:event_jBtnThoatMousePressed

    private void jBtnThoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnThoatActionPerformed
        // TODO add your handling code here:
        int confirm = JOptionPane.showConfirmDialog(this, "B???n ch???c ch???n mu???n tho??t kh???i ph??ng chat?",
                "X??c nh???n", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            serverListener.send(Utils.OUTCHAT, "");
        }
    }//GEN-LAST:event_jBtnThoatActionPerformed

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Chat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Chat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Chat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Chat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        new Chat();
        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new Chat().setVisible(true);
//            }
//        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea chatBox;
    private javax.swing.JButton jBtnSend;
    private javax.swing.JButton jBtnThoat;
    private javax.swing.JLabel jLbThatUser;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextAreaInput;
    // End of variables declaration//GEN-END:variables

    @Override
    public void Receive(Message mess) {
        switch (mess.type) {
            case Utils.RECEIVECHAT: {
//                dispose();
//                new Waiting(serverListener);
                chatBox.append((String) mess.obj);
                break;
            }
            case Utils.BEDENIED: {
                JOptionPane.showMessageDialog(this, "?????i ph????ng tho??t kh???i ph??ng chat.");
                serverListener.send(Utils.FINDOTHER, "");
                break;
            }
            case Utils.WAIT: {
                new Waiting(serverListener, (String) mess.obj);
//                JOptionPane.showMessageDialog(this, (String) mess.obj);
                dispose();
                break;
            }
            case Utils.REQUEST: {
                new Request(serverListener, (String) mess.obj, this.nickName);
                dispose();
                break;
            }
        }
    }
}
