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

package org.xtce.apps.editor.dialogs;

import org.xtce.toolkit.XTCESpaceSystem;
import java.awt.event.WindowEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import org.omg.space.xtce.HeaderType;

/** Dialog form for creating/editing Space Systems in the XTCE data model.
 *
 * @author David Overeem
 *
 */

public class XTCEViewerCreateEditSpaceSystemDialog extends javax.swing.JDialog {

    /** Creates new form XTCEViewerCreateEditSpaceSystemDialog
     *
     * @param parent Java Frame of the parent window
     *
     * @param modal boolean indicating if this dialog should have modal
     * behavior
     *
     * @param spaceSystem XTCESpaceSystem object to create or edit
     *
     */
    @SuppressWarnings("unchecked")
    public XTCEViewerCreateEditSpaceSystemDialog( java.awt.Frame  parent,
                                                  boolean         modal,
                                                  XTCESpaceSystem spaceSystem ) {

        super(parent, modal);
        initComponents();

        validationStatusText.setModel( new DefaultComboBoxModel(
            new String[] { "Unknown", // NOI18N
                           "Working", // NOI18N
                           "Draft", // NOI18N
                           "Test", // NOI18N
                           "Validated", // NOI18N
                           "Released", // NOI18N
                           "Withdrawn" } ) ); // NOI18N
        validationStatusText.setSelectedIndex( 0 );
        DateFormat dateFormat = new SimpleDateFormat( "yyyy-MM-dd" ); // NOI18N
        dateText.setText( dateFormat.format( new Date() ) );
        pack();
        setLocationRelativeTo( parent );

        if ( spaceSystem != null ) {
            this.setTitle( "Edit SpaceSystem " + spaceSystem.getReference().getName() );
            spaceSystemNameText.setText( spaceSystem.getReference().getName() );
            shortDescriptionText.setText( spaceSystem.getReference().getShortDescription() );
            operationalStatusText.setText( spaceSystem.getReference().getOperationalStatus() );
            longDescriptionText.setText( spaceSystem.getReference().getLongDescription() );
            HeaderType header = spaceSystem.getReference().getHeader();
            if ( header != null ) {
                versionText.setText( header.getVersion() );
                dateText.setText( header.getDate() );
                classificationText.setText( header.getClassification() );
                classificationInstructionsText.setText( header.getClassificationInstructions() );
                validationStatusText.setSelectedItem( header.getValidationStatus() );
            }
        } else {
            this.setTitle( "Create Space System" );
        }

    }

    public boolean isAccepted() {
        return accepted;
    }

    public String getSpaceSystemName() {
        return spaceSystemNameText.getText();
    }

    public String getShortDescription() {
        return shortDescriptionText.getText();
    }

    public String getLongDescription() {
        return longDescriptionText.getText();
    }

    public String getOperationalStatus() {
        return operationalStatusText.getText();
    }

    public String getVersion() {
        return versionText.getText();
    }

    public String getDate() {
        return dateText.getText();
    }

    public String getClassification() {
        return classificationText.getText();
    }

    public String getClassificationInstructions() {
        return classificationInstructionsText.getText();
    }

    public String getValidationStatus() {
        return (String)validationStatusText.getSelectedItem();
    }

    private boolean accepted = false;

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        spaceSystemNameLabel = new javax.swing.JLabel();
        shortDescriptionLabel = new javax.swing.JLabel();
        operationalStatusLabel = new javax.swing.JLabel();
        versionLabel = new javax.swing.JLabel();
        dateLabel = new javax.swing.JLabel();
        classificationLabel = new javax.swing.JLabel();
        classificationInstructionsLabel = new javax.swing.JLabel();
        validationStatusLabel = new javax.swing.JLabel();
        longDescriptionLabel = new javax.swing.JLabel();
        spaceSystemNameText = new javax.swing.JTextField();
        shortDescriptionText = new javax.swing.JTextField();
        operationalStatusText = new javax.swing.JTextField();
        versionText = new javax.swing.JTextField();
        dateText = new javax.swing.JTextField();
        classificationText = new javax.swing.JTextField();
        classificationInstructionsText = new javax.swing.JTextField();
        validationStatusText = new javax.swing.JComboBox<>();
        createButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        longDescriptionScrollPane = new javax.swing.JScrollPane();
        longDescriptionText = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Create/Edit Space System");

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("org/xtce/toolkit/MessagesBundle"); // NOI18N
        spaceSystemNameLabel.setText(bundle.getString("ss_name_text")); // NOI18N

        shortDescriptionLabel.setText(bundle.getString("tab_ssdetail_shortdesc_text")); // NOI18N

        operationalStatusLabel.setText(bundle.getString("tab_ssdetail_opstatus_text")); // NOI18N

        versionLabel.setText(bundle.getString("tab_ssdetail_version_text")); // NOI18N

        dateLabel.setText(bundle.getString("tab_ssdetail_date_text")); // NOI18N

        classificationLabel.setText(bundle.getString("tab_ssdetail_classification_text")); // NOI18N

        classificationInstructionsLabel.setText(bundle.getString("tab_ssdetail_classinstruction_text")); // NOI18N

        validationStatusLabel.setText(bundle.getString("tab_ssdetail_validationstatus_text")); // NOI18N

        longDescriptionLabel.setText(bundle.getString("tab_ssdetail_longdesc_text")); // NOI18N

        spaceSystemNameText.setText("MySpaceSystem");

        shortDescriptionText.setText("Fill Me In");

        versionText.setText("1");

        classificationText.setText("NotClassified");

        createButton.setText(bundle.getString("general_accept_text")); // NOI18N
        createButton.setMaximumSize(new java.awt.Dimension(80, 25));
        createButton.setMinimumSize(new java.awt.Dimension(80, 25));
        createButton.setPreferredSize(new java.awt.Dimension(80, 25));
        createButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createButtonActionPerformed(evt);
            }
        });

        cancelButton.setText(bundle.getString("general_dismiss_text")); // NOI18N
        cancelButton.setMaximumSize(new java.awt.Dimension(80, 25));
        cancelButton.setMinimumSize(new java.awt.Dimension(80, 25));
        cancelButton.setPreferredSize(new java.awt.Dimension(80, 25));
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        longDescriptionText.setColumns(20);
        longDescriptionText.setRows(5);
        longDescriptionScrollPane.setViewportView(longDescriptionText);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(spaceSystemNameLabel)
                            .addComponent(shortDescriptionLabel)
                            .addComponent(operationalStatusLabel)
                            .addComponent(versionLabel)
                            .addComponent(dateLabel)
                            .addComponent(classificationLabel)
                            .addComponent(classificationInstructionsLabel)
                            .addComponent(validationStatusLabel))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(spaceSystemNameText)
                                    .addComponent(shortDescriptionText)
                                    .addComponent(operationalStatusText)
                                    .addComponent(versionText)
                                    .addComponent(dateText)
                                    .addComponent(classificationText)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(classificationInstructionsText)
                                    .addComponent(validationStatusText, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(longDescriptionScrollPane)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(createButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(longDescriptionLabel)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spaceSystemNameLabel)
                    .addComponent(spaceSystemNameText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(shortDescriptionLabel)
                    .addComponent(shortDescriptionText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(operationalStatusLabel)
                    .addComponent(operationalStatusText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(versionLabel)
                    .addComponent(versionText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dateLabel)
                    .addComponent(dateText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(classificationText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(classificationLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(classificationInstructionsText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(classificationInstructionsLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(validationStatusLabel)
                    .addComponent(validationStatusText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(longDescriptionLabel)
                    .addComponent(longDescriptionScrollPane))
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(createButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void createButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createButtonActionPerformed
        // do validation of fields here
        accepted = true;
        this.dispatchEvent( new WindowEvent(this, WindowEvent.WINDOW_CLOSING) );
    }//GEN-LAST:event_createButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        this.dispatchEvent( new WindowEvent(this, WindowEvent.WINDOW_CLOSING) );
    }//GEN-LAST:event_cancelButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JLabel classificationInstructionsLabel;
    private javax.swing.JTextField classificationInstructionsText;
    private javax.swing.JLabel classificationLabel;
    private javax.swing.JTextField classificationText;
    private javax.swing.JButton createButton;
    private javax.swing.JLabel dateLabel;
    private javax.swing.JTextField dateText;
    private javax.swing.JLabel longDescriptionLabel;
    private javax.swing.JScrollPane longDescriptionScrollPane;
    private javax.swing.JTextArea longDescriptionText;
    private javax.swing.JLabel operationalStatusLabel;
    private javax.swing.JTextField operationalStatusText;
    private javax.swing.JLabel shortDescriptionLabel;
    private javax.swing.JTextField shortDescriptionText;
    private javax.swing.JLabel spaceSystemNameLabel;
    private javax.swing.JTextField spaceSystemNameText;
    private javax.swing.JLabel validationStatusLabel;
    private javax.swing.JComboBox<String> validationStatusText;
    private javax.swing.JLabel versionLabel;
    private javax.swing.JTextField versionText;
    // End of variables declaration//GEN-END:variables
}
