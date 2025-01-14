/* Copyright 2015 David Overeem (dovereem@startmail.com)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * 
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */

package org.xtce.apps.editor.ui;

/**
 *
 * @author David Overeem
 *
 */

public class XTCEViewerAuthor extends javax.swing.JPanel {

    /**
     * Creates new form XTCEViewerAuthor TODO
     * 
     * @param authorSet TODO
     * 
     * @param text TODO
     * 
     * @param idx TODO
     * 
     */
    public XTCEViewerAuthor( XTCEViewerAuthorSet authorSet, String text, int idx ) {
        initComponents();
        authorText.setText( text );
        authorText.setCaretPosition( 0 );
        authorSet_ = authorSet;
        idx_       = idx;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        authorText = new javax.swing.JTextField();
        removeButton = new javax.swing.JButton();

        authorText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                authorTextActionPerformed(evt);
            }
        });

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("org/xtce/toolkit/MessagesBundle"); // NOI18N
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
                .addComponent(authorText, javax.swing.GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(removeButton)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(authorText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(removeButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void removeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeButtonActionPerformed
        authorSet_.removeAuthor( idx_ );
    }//GEN-LAST:event_removeButtonActionPerformed

    private void authorTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_authorTextActionPerformed
        if ( isVisible() == true ) {
            authorSet_.editAuthor( authorText.getText(), idx_ );
        }
    }//GEN-LAST:event_authorTextActionPerformed

    public void setEditable( boolean editEnabled ) {
        removeButton.setEnabled( editEnabled );
        authorText.setEditable( editEnabled );
    }

    // Private Data Members

    private XTCEViewerAuthorSet authorSet_ = null;
    private int                 idx_       = 0;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField authorText;
    private javax.swing.JButton removeButton;
    // End of variables declaration//GEN-END:variables
}
