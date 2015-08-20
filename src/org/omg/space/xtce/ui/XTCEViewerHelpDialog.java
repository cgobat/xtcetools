/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.omg.space.xtce.ui;

import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.URL;
import javax.swing.JEditorPane;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.text.Document;
import org.omg.space.xtce.toolkit.XTCEFunctions;

/**
 *
 * @author David Overeem
 *
 */

public class XTCEViewerHelpDialog extends javax.swing.JDialog {

    /**
     * Creates new form XTCEViewerHelpDialog
     */
    public XTCEViewerHelpDialog(java.awt.Frame parent,
                                boolean        modal,
                                URL            pageUrl ) {

        super(parent, modal);
        initComponents();
        jEditorPane1.setEditable( false );
        ActivatedHyperlinkListener hlisten =
            new ActivatedHyperlinkListener( jEditorPane1 );

        try {
            jEditorPane1.setPage( pageUrl );
            jEditorPane1.addHyperlinkListener( hlisten );
        } catch ( Exception ex ) {
            jEditorPane1.setText( XTCEFunctions.generalErrorPrefix() +
                                  ex.getLocalizedMessage() );
        }

        backButton.setEnabled( false );
        forwardButton.setEnabled( false );

        pack();
        setLocationRelativeTo( parent );
        setVisible( true );

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        editorScrollPane = new javax.swing.JScrollPane();
        jEditorPane1 = new javax.swing.JEditorPane();
        dismissButton = new javax.swing.JButton();
        backButton = new javax.swing.JButton();
        forwardButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(640, 480));
        setPreferredSize(new java.awt.Dimension(1024, 768));

        editorScrollPane.setViewportView(jEditorPane1);

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("org/omg/space/xtce/toolkit/MessagesBundle"); // NOI18N
        dismissButton.setText(bundle.getString("general_dismiss_text")); // NOI18N
        dismissButton.setMaximumSize(new java.awt.Dimension(90, 25));
        dismissButton.setMinimumSize(new java.awt.Dimension(90, 25));
        dismissButton.setPreferredSize(new java.awt.Dimension(90, 25));
        dismissButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dismissButtonActionPerformed(evt);
            }
        });

        backButton.setText(bundle.getString("general_back")); // NOI18N
        backButton.setMaximumSize(new java.awt.Dimension(90, 25));
        backButton.setMinimumSize(new java.awt.Dimension(90, 25));
        backButton.setPreferredSize(new java.awt.Dimension(90, 25));
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        forwardButton.setText(bundle.getString("general_forward")); // NOI18N
        forwardButton.setMaximumSize(new java.awt.Dimension(90, 25));
        forwardButton.setMinimumSize(new java.awt.Dimension(90, 25));
        forwardButton.setPreferredSize(new java.awt.Dimension(90, 25));
        forwardButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                forwardButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(editorScrollPane)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(57, Short.MAX_VALUE)
                .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dismissButton, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(forwardButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(57, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(editorScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dismissButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(forwardButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void dismissButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dismissButtonActionPerformed
        this.dispatchEvent( new WindowEvent(this, WindowEvent.WINDOW_CLOSING) );
    }//GEN-LAST:event_dismissButtonActionPerformed

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_backButtonActionPerformed

    private void forwardButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_forwardButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_forwardButtonActionPerformed

    class ActivatedHyperlinkListener implements HyperlinkListener {

        ActivatedHyperlinkListener( JEditorPane editorPane ) {
            this.editorPane = editorPane;
        }

        @Override
        public void hyperlinkUpdate(HyperlinkEvent hyperlinkEvent) {

            HyperlinkEvent.EventType type = hyperlinkEvent.getEventType();
            final URL url = hyperlinkEvent.getURL();
            if (type == HyperlinkEvent.EventType.ENTERED) {
                //System.out.println( "URL: " + url );
            } else if (type == HyperlinkEvent.EventType.ACTIVATED) {
                //System.out.println( "Activated" );
                Document doc = editorPane.getDocument();
                try {
                    editorPane.setPage( url );
                } catch ( IOException ioException ) {
                    //System.out.println("Error following link, Invalid link");
                    editorPane.setDocument( doc );
                }
            }
        }

        private final JEditorPane editorPane;

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JButton dismissButton;
    private javax.swing.JScrollPane editorScrollPane;
    private javax.swing.JButton forwardButton;
    private javax.swing.JEditorPane jEditorPane1;
    // End of variables declaration//GEN-END:variables
}
