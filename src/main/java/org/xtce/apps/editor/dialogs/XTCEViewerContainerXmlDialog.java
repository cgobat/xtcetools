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

import java.awt.Frame;
import org.xtce.toolkit.XTCEDatabaseException;
import java.awt.event.WindowEvent;
import org.xtce.toolkit.XTCEFunctions;
import org.xtce.toolkit.XTCETCContainer;
import org.xtce.toolkit.XTCETMContainer;
import org.xtce.toolkit.XTCETMStream;
import org.xtce.toolkit.XTCETelecommand;

/** This class creates a dialog box to view the XML contents of a container
 * or a stream.
 *
 * This dialog is compatible with any XTCENamedObject and will probably be
 * renamed and migrated for that purpose at some point.
 *
 * @author David Overeem
 *
 */

public class XTCEViewerContainerXmlDialog extends javax.swing.JDialog {

    /** Creates new dialog window for showing the XTCE SequenceContainer
     * element XML contents.
     *
     * @param parent Frame containing the parent window.
     *
     * @param modal Boolean indicating if this dialog should block the parent
     * window interactions until dismissed.
     *
     * @param container XTCETMContainer object that will be used to extract the
     * raw XML ASCII for display to the user in the dialog.
     *
     * @throws XTCEDatabaseException in the event that the XML cannot be
     * marshaled from the JAXB internal model.
     *
     */

    public XTCEViewerContainerXmlDialog( Frame           parent,
                                         boolean         modal,
                                         XTCETMContainer container )
        throws XTCEDatabaseException {

        super( parent, modal );
        initComponents();

        if ( container == null ) {
            return;
        }

        containerDefinitionText.setText( container.toXml() );
        containerLabel.setText(
            XTCEFunctions.getText( "dialog_xmlsingle_container" ) ); // NOI18N

        setTitle( XTCEFunctions.getText( "dialog_container_xml_title" ) + // NOI18N
                  ": " + // NOI18N
                  container.getName() );

        pack();
        setLocationRelativeTo( parent );

    }
    /** Creates new dialog window for showing the XTCE SequenceContainer
     * element XML contents.
     *
     * @param parent Frame containing the parent window.
     *
     * @param modal Boolean indicating if this dialog should block the parent
     * window interactions until dismissed.
     *
     * @param container XTCETMContainer object that will be used to extract the
     * raw XML ASCII for display to the user in the dialog.
     *
     * @throws XTCEDatabaseException in the event that the XML cannot be
     * marshaled from the JAXB internal model.
     *
     */

    public XTCEViewerContainerXmlDialog( Frame           parent,
                                         boolean         modal,
                                         XTCETCContainer container )
        throws XTCEDatabaseException {

        super( parent, modal );
        initComponents();

        if ( container == null ) {
            return;
        }

        containerDefinitionText.setText( container.toXml() );
        containerLabel.setText(
            XTCEFunctions.getText( "dialog_xmlsingle_container" ) ); // NOI18N

        setTitle( XTCEFunctions.getText( "dialog_container_xml_title" ) + // NOI18N
                  ": " + // NOI18N
                  container.getName() );

        pack();
        setLocationRelativeTo( parent );

    }

    /** Creates new dialog window for showing the XTCE PCMStreamType element
     * XML contents.
     *
     * @param parent Frame containing the parent window.
     *
     * @param modal Boolean indicating if this dialog should block the parent
     * window interactions until dismissed.
     *
     * @param stream XTCETMStream object that will be used to extract the
     * raw XML ASCII for display to the user in the dialog.
     *
     * @throws XTCEDatabaseException in the event that the XML cannot be
     * marshaled from the JAXB internal model.
     *
     */

    public XTCEViewerContainerXmlDialog( Frame           parent,
                                         boolean         modal,
                                         XTCETMStream    stream )
        throws XTCEDatabaseException {

        super( parent, modal );
        initComponents();

        if ( stream == null ) {
            return;
        }

        containerDefinitionText.setText( stream.toXml() );
        containerLabel.setText(
            XTCEFunctions.getText( "dialog_xmlsingle_stream" ) ); // NOI18N

        setTitle( XTCEFunctions.getText( "dialog_stream_xml_title" ) + // NOI18N
                  ": " + // NOI18N
                  stream.getName() );

        pack();
        setLocationRelativeTo( parent );

    }

    /** Creates new dialog window for showing the XTCE PCMStreamType element
     * XML contents.
     *
     * @param parent Frame containing the parent window.
     *
     * @param modal Boolean indicating if this dialog should block the parent
     * window interactions until dismissed.
     *
     * @param tc XTCETelecommand object that will be used to extract the
     * raw XML ASCII for display to the user in the dialog.
     *
     * @throws XTCEDatabaseException in the event that the XML cannot be
     * marshaled from the JAXB internal model.
     *
     */

    public XTCEViewerContainerXmlDialog( Frame           parent,
                                         boolean         modal,
                                         XTCETelecommand tc )
        throws XTCEDatabaseException {

        super( parent, modal );
        initComponents();

        if ( tc == null ) {
            return;
        }

        containerDefinitionText.setText( tc.toXml() );
        containerLabel.setText(
            XTCEFunctions.getText( "dialog_xmlsingle_telecommand" ) ); // NOI18N

        setTitle( XTCEFunctions.getText( "dialog_telecommand_xml_title" ) + // NOI18N
                  ": " + // NOI18N
                  tc.getName() );

        pack();
        setLocationRelativeTo( parent );

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        containerLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        containerDefinitionText = new javax.swing.JTextArea();
        buttonPanel = new javax.swing.JPanel();
        acceptButton = new javax.swing.JButton();
        dismissButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(500, 400));
        setModalExclusionType(java.awt.Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
        setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        setName("XML Elements"); // NOI18N

        containerLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("org/xtce/toolkit/MessagesBundle"); // NOI18N
        containerLabel.setText(bundle.getString("dialog_xmlsingle_container")); // NOI18N

        jScrollPane1.setViewportView(containerDefinitionText);

        buttonPanel.setMaximumSize(new java.awt.Dimension(32767, 47));
        buttonPanel.setMinimumSize(new java.awt.Dimension(0, 47));

        acceptButton.setText(bundle.getString("general_accept_text")); // NOI18N
        acceptButton.setMaximumSize(new java.awt.Dimension(100, 25));
        acceptButton.setMinimumSize(new java.awt.Dimension(100, 25));
        acceptButton.setPreferredSize(new java.awt.Dimension(100, 25));
        acceptButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acceptButtonActionPerformed(evt);
            }
        });

        dismissButton.setText(bundle.getString("general_dismiss_text")); // NOI18N
        dismissButton.setMaximumSize(new java.awt.Dimension(100, 25));
        dismissButton.setMinimumSize(new java.awt.Dimension(100, 25));
        dismissButton.setPreferredSize(new java.awt.Dimension(100, 25));
        dismissButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dismissButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout buttonPanelLayout = new javax.swing.GroupLayout(buttonPanel);
        buttonPanel.setLayout(buttonPanelLayout);
        buttonPanelLayout.setHorizontalGroup(
            buttonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buttonPanelLayout.createSequentialGroup()
                .addContainerGap(101, Short.MAX_VALUE)
                .addComponent(acceptButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(dismissButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(113, Short.MAX_VALUE))
        );
        buttonPanelLayout.setVerticalGroup(
            buttonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buttonPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(buttonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(acceptButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dismissButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(buttonPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(containerLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(containerLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void dismissButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dismissButtonActionPerformed
        this.dispatchEvent( new WindowEvent(this, WindowEvent.WINDOW_CLOSING) );
    }//GEN-LAST:event_dismissButtonActionPerformed

    private void acceptButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acceptButtonActionPerformed
        this.dispatchEvent( new WindowEvent(this, WindowEvent.WINDOW_CLOSING) );
    }//GEN-LAST:event_acceptButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton acceptButton;
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JTextArea containerDefinitionText;
    private javax.swing.JLabel containerLabel;
    private javax.swing.JButton dismissButton;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

}
