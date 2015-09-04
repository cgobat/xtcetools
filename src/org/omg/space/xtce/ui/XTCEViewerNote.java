/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.omg.space.xtce.ui;

/**
 *
 * @author David Overeem
 *
 */

public class XTCEViewerNote extends javax.swing.JPanel {

    /**
     * Creates new form XTCEViewerNote
     */
    public XTCEViewerNote( XTCEViewerNoteSet noteSet, String text, int idx ) {
        initComponents();
        noteText.setText( text );
        noteText.setCaretPosition( 0 );
        noteSet_ = noteSet;
        idx_     = idx;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        noteText = new javax.swing.JTextField();
        removeButton = new javax.swing.JButton();

        noteText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noteTextActionPerformed(evt);
            }
        });

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("org/omg/space/xtce/toolkit/MessagesBundle"); // NOI18N
        removeButton.setText(bundle.getString("tab_ssdetail_remove_text")); // NOI18N
        removeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(noteText, javax.swing.GroupLayout.DEFAULT_SIZE, 333, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(removeButton)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(noteText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(removeButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void removeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeButtonActionPerformed
        noteSet_.removeNote( idx_ );
    }//GEN-LAST:event_removeButtonActionPerformed

    private void noteTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noteTextActionPerformed
        if ( isVisible() == true ) {
            noteSet_.editNote( noteText.getText(), idx_ );
        }
    }//GEN-LAST:event_noteTextActionPerformed

    public void setEditable( boolean editEnabled ) {
        removeButton.setEnabled( editEnabled );
        noteText.setEditable( editEnabled );
    }

    //public void setNoteText( String text ) {
    //    noteText.setText( text );
    //}

    // Private Data Members

    private XTCEViewerNoteSet noteSet_ = null;
    private int               idx_     = 0;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField noteText;
    private javax.swing.JButton removeButton;
    // End of variables declaration//GEN-END:variables
}
