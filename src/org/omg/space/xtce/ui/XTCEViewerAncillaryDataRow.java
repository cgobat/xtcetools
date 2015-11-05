/* Copyright 2015 David Overeem (dovereem@cox.net)
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

package org.omg.space.xtce.ui;

import javax.swing.JOptionPane;
import org.omg.space.xtce.database.DescriptionType.AncillaryDataSet.AncillaryData;
import org.omg.space.xtce.toolkit.XTCEFunctions;

/**
 *
 * @author dovereem
 */

public class XTCEViewerAncillaryDataRow extends javax.swing.JPanel {

    /**
     * Creates new form XTCEViewerAncillaryDataRow
     */

    public XTCEViewerAncillaryDataRow( AncillaryData ancData ) {

        initComponents();
        textFieldName.setText( ancData.getName() );
        textFieldValue.setText( ancData.getValue() );
        textFieldLink.setText( ancData.getHref() );
        textFieldMime.setText( ancData.getMimeType() );

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelName = new javax.swing.JLabel();
        labelValue = new javax.swing.JLabel();
        labelLink = new javax.swing.JLabel();
        labelMime = new javax.swing.JLabel();
        textFieldName = new javax.swing.JTextField();
        textFieldValue = new javax.swing.JTextField();
        textFieldLink = new javax.swing.JTextField();
        textFieldMime = new javax.swing.JTextField();
        buttonEdit = new javax.swing.JButton();
        buttonRemove = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("org/omg/space/xtce/toolkit/MessagesBundle"); // NOI18N
        labelName.setText(bundle.getString("general_name")); // NOI18N

        labelValue.setText(bundle.getString("general_value")); // NOI18N

        labelLink.setText(bundle.getString("general_href")); // NOI18N

        labelMime.setText(bundle.getString("general_mime")); // NOI18N

        buttonEdit.setText(bundle.getString("general_edit")); // NOI18N
        buttonEdit.setMaximumSize(new java.awt.Dimension(75, 25));
        buttonEdit.setMinimumSize(new java.awt.Dimension(75, 25));
        buttonEdit.setPreferredSize(new java.awt.Dimension(75, 25));
        buttonEdit.setSize(new java.awt.Dimension(75, 25));
        buttonEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEditActionPerformed(evt);
            }
        });

        buttonRemove.setText(bundle.getString("general_remove")); // NOI18N
        buttonRemove.setMaximumSize(new java.awt.Dimension(75, 25));
        buttonRemove.setMinimumSize(new java.awt.Dimension(75, 25));
        buttonRemove.setPreferredSize(new java.awt.Dimension(75, 25));
        buttonRemove.setSize(new java.awt.Dimension(75, 25));
        buttonRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRemoveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelName)
                    .addComponent(labelValue)
                    .addComponent(labelLink)
                    .addComponent(labelMime))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textFieldMime, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(textFieldLink)
                    .addComponent(textFieldName)
                    .addComponent(textFieldValue, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(125, Short.MAX_VALUE)
                .addComponent(buttonEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonRemove, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 96, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelName)
                    .addComponent(textFieldName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textFieldValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelValue))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelLink)
                    .addComponent(textFieldLink, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textFieldMime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelMime))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonRemove, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void buttonEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEditActionPerformed

        JOptionPane.showMessageDialog( this,
                                       XTCEFunctions.getText( "not_implemented_edit_feature" ), // NOI18N
                                       XTCEFunctions.getText( "general_warning" ), // NOI18N
                                       JOptionPane.INFORMATION_MESSAGE );

    }//GEN-LAST:event_buttonEditActionPerformed

    private void buttonRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRemoveActionPerformed

        JOptionPane.showMessageDialog( this,
                                       XTCEFunctions.getText( "not_implemented_edit_feature" ), // NOI18N
                                       XTCEFunctions.getText( "general_warning" ), // NOI18N
                                       JOptionPane.INFORMATION_MESSAGE );

    }//GEN-LAST:event_buttonRemoveActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonEdit;
    private javax.swing.JButton buttonRemove;
    private javax.swing.JLabel labelLink;
    private javax.swing.JLabel labelMime;
    private javax.swing.JLabel labelName;
    private javax.swing.JLabel labelValue;
    private javax.swing.JTextField textFieldLink;
    private javax.swing.JTextField textFieldMime;
    private javax.swing.JTextField textFieldName;
    private javax.swing.JTextField textFieldValue;
    // End of variables declaration//GEN-END:variables
}
