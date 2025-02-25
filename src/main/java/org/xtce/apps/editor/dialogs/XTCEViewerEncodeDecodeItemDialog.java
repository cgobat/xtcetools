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

import java.awt.event.WindowEvent;
import java.math.BigInteger;
import java.util.BitSet;
import org.xtce.apps.editor.ui.XTCEViewerFunctions;
import org.xtce.toolkit.XTCEFunctions;
import org.xtce.toolkit.XTCEItemValue;
import org.xtce.toolkit.XTCETypedObject;

/**
 *
 * @author David Overeem
 *
 */

public class XTCEViewerEncodeDecodeItemDialog extends javax.swing.JDialog {

    /** Constructor
     *
     * @param parent Frame object that owns this dialog box.
     *
     * @param modal Modality of the dialog box, which can be true if it blocks
     * all other window operations or false if this dialog can stand alone.
     *
     * @param item XTCETypedObject item to evaluate.
     *
     * @param aliases String containing the aliases generated on the table
     * based on the user preferences.  These are used for the title bar of the
     * dialog.
     *
     */

    public XTCEViewerEncodeDecodeItemDialog( java.awt.Frame  parent,
                                             boolean         modal,
                                             XTCETypedObject item,
                                             String          aliases ) {

        super( parent, modal );
        initComponents();

        itemValueObj_ = new XTCEItemValue( item );

        if ( aliases.isEmpty() == false ) {
            setTitle( item.getName() + " (" + aliases + ")" ); // NOI18N
        } else {
            setTitle( item.getName() );
        }

        if ( item.isValid() == false ) {
            warningsText.setText(
                XTCEFunctions.getText( "error_invalid_parametertype" ) ); // NOI18N
            makeFromRawButton.setEnabled( false );
            makeFromUncalibratedButton.setEnabled( false );
            makeFromCalibratedButton.setEnabled( false );
        }

        if ( itemValueObj_.isValid() == false ) {
            for ( String warning : itemValueObj_.getWarnings() ) {
                warningsText.append( warning );
                warningsText.append( System.getProperty( "line.separator" ) ); // NOI18N
            }
        }

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

        encodeDecodePanel = new javax.swing.JPanel();
        titleLabel = new javax.swing.JLabel();
        calibratedFieldLabel = new javax.swing.JLabel();
        orLabel = new javax.swing.JLabel();
        rawFieldLabel = new javax.swing.JLabel();
        binaryFieldLabel = new javax.swing.JLabel();
        warningsLabel = new javax.swing.JLabel();
        calibratedValueField = new javax.swing.JTextField();
        uncalibratedValueField = new javax.swing.JTextField();
        rawValueField = new javax.swing.JTextField();
        binaryValueField = new javax.swing.JTextField();
        makeFromCalibratedButton = new javax.swing.JButton();
        makeFromUncalibratedButton = new javax.swing.JButton();
        makeFromRawButton = new javax.swing.JButton();
        warningsScrollPane = new javax.swing.JScrollPane();
        warningsText = new javax.swing.JTextArea();
        dismissPanel = new javax.swing.JPanel();
        dismissButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(515, 360));

        encodeDecodePanel.setMinimumSize(new java.awt.Dimension(500, 332));

        titleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("org/xtce/toolkit/MessagesBundle"); // NOI18N
        titleLabel.setText(bundle.getString("dialog_param_encdec_title")); // NOI18N

        calibratedFieldLabel.setText(bundle.getString("dialog_param_encdec_eucal")); // NOI18N

        orLabel.setText(bundle.getString("dialog_param_encdec_uncal")); // NOI18N

        rawFieldLabel.setText(bundle.getString("dialog_param_encdec_raw")); // NOI18N

        binaryFieldLabel.setText(bundle.getString("dialog_param_encdec_rawbin")); // NOI18N

        warningsLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        warningsLabel.setText(bundle.getString("general_warnings")); // NOI18N

        calibratedValueField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                calibratedValueFieldKeyPressed(evt);
            }
        });

        uncalibratedValueField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                uncalibratedValueFieldKeyPressed(evt);
            }
        });

        rawValueField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                rawValueFieldKeyPressed(evt);
            }
        });

        binaryValueField.setEditable(false);

        makeFromCalibratedButton.setText(bundle.getString("dialog_param_encdec_gen_uncalraw")); // NOI18N
        makeFromCalibratedButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                makeFromCalibratedButtonActionPerformed(evt);
            }
        });

        makeFromUncalibratedButton.setText(bundle.getString("dialog_param_encdec_gen_eucalraw")); // NOI18N
        makeFromUncalibratedButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                makeFromUncalibratedButtonActionPerformed(evt);
            }
        });

        makeFromRawButton.setText(bundle.getString("dialog_oaram_encdec_gen_eucaluncal")); // NOI18N
        makeFromRawButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                makeFromRawButtonActionPerformed(evt);
            }
        });

        warningsScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        warningsText.setColumns(20);
        warningsText.setLineWrap(true);
        warningsText.setRows(5);
        warningsText.setWrapStyleWord(true);
        warningsScrollPane.setViewportView(warningsText);

        dismissButton.setText(bundle.getString("general_dismiss_text")); // NOI18N
        dismissButton.setMaximumSize(new java.awt.Dimension(80, 25));
        dismissButton.setMinimumSize(new java.awt.Dimension(80, 25));
        dismissButton.setPreferredSize(new java.awt.Dimension(80, 25));
        dismissButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dismissButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout dismissPanelLayout = new javax.swing.GroupLayout(dismissPanel);
        dismissPanel.setLayout(dismissPanelLayout);
        dismissPanelLayout.setHorizontalGroup(
            dismissPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dismissPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(dismissButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        dismissPanelLayout.setVerticalGroup(
            dismissPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dismissPanelLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(dismissButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout encodeDecodePanelLayout = new javax.swing.GroupLayout(encodeDecodePanel);
        encodeDecodePanel.setLayout(encodeDecodePanelLayout);
        encodeDecodePanelLayout.setHorizontalGroup(
            encodeDecodePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(titleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(encodeDecodePanelLayout.createSequentialGroup()
                .addGroup(encodeDecodePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(orLabel)
                    .addComponent(rawFieldLabel)
                    .addComponent(binaryFieldLabel)
                    .addComponent(calibratedFieldLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(encodeDecodePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(encodeDecodePanelLayout.createSequentialGroup()
                        .addGroup(encodeDecodePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(calibratedValueField, javax.swing.GroupLayout.DEFAULT_SIZE, 308, Short.MAX_VALUE)
                            .addComponent(rawValueField, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(uncalibratedValueField, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(encodeDecodePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(makeFromRawButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(makeFromCalibratedButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(makeFromUncalibratedButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(binaryValueField))
                .addContainerGap())
            .addComponent(warningsLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(warningsScrollPane)
            .addComponent(dismissPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        encodeDecodePanelLayout.setVerticalGroup(
            encodeDecodePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(encodeDecodePanelLayout.createSequentialGroup()
                .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(encodeDecodePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(calibratedFieldLabel)
                    .addComponent(calibratedValueField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(makeFromCalibratedButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(encodeDecodePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(orLabel)
                    .addComponent(uncalibratedValueField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(makeFromUncalibratedButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(encodeDecodePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rawFieldLabel)
                    .addComponent(rawValueField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(makeFromRawButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(encodeDecodePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(binaryFieldLabel)
                    .addComponent(binaryValueField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(warningsLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(warningsScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dismissPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(encodeDecodePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(encodeDecodePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void dismissButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dismissButtonActionPerformed

        this.dispatchEvent( new WindowEvent(this, WindowEvent.WINDOW_CLOSING) );

    }//GEN-LAST:event_dismissButtonActionPerformed

    private void makeFromCalibratedButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_makeFromCalibratedButtonActionPerformed

        itemValueObj_.clearWarnings();

        String calValue = calibratedValueField.getText();

        String uncalValue = itemValueObj_.getUncalibratedFromCalibrated( calValue );

        showWarnings();
        itemValueObj_.clearWarnings();

        BitSet rawBits    = itemValueObj_.getRawFromUncalibrated( uncalValue );

        appendWarnings();

        String rawValue   = itemValueObj_.bitSetToHex( rawBits );
        String binValue   = itemValueObj_.bitSetToBinary( rawBits );

        rawValueField.setText( rawValue );
        uncalibratedValueField.setText( uncalValue );
        binaryValueField.setText( binValue );

        resetCaretPositions();

    }//GEN-LAST:event_makeFromCalibratedButtonActionPerformed

    private void makeFromRawButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_makeFromRawButtonActionPerformed

        itemValueObj_.clearWarnings();

        String rawValue = rawValueField.getText();

        BigInteger rawInteger = itemValueObj_.integerStringToBigInteger( rawValue);
        BitSet     bits       = itemValueObj_.encodeRawBits( rawInteger );
        String     binValue   = itemValueObj_.bitSetToBinary( bits );
        String     uncalValue = itemValueObj_.getUncalibratedFromRaw( bits );

        showWarnings();

        String     calValue   = itemValueObj_.getCalibratedFromUncalibrated( uncalValue );

        appendWarnings();

        calibratedValueField.setText( calValue );
        uncalibratedValueField.setText( uncalValue );
        binaryValueField.setText( binValue );

        resetCaretPositions();

    }//GEN-LAST:event_makeFromRawButtonActionPerformed

    private void makeFromUncalibratedButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_makeFromUncalibratedButtonActionPerformed

        itemValueObj_.clearWarnings();

        String uncalValue = uncalibratedValueField.getText();

        String calValue   = itemValueObj_.getCalibratedFromUncalibrated( uncalValue );

        showWarnings();
        itemValueObj_.clearWarnings();

        BitSet rawBits    = itemValueObj_.getRawFromUncalibrated( uncalValue );

        appendWarnings();

        String rawValue   = itemValueObj_.bitSetToHex( rawBits );
        String binValue   = itemValueObj_.bitSetToBinary( rawBits );

        calibratedValueField.setText( calValue );
        rawValueField.setText( rawValue );
        binaryValueField.setText( binValue );

        resetCaretPositions();

    }//GEN-LAST:event_makeFromUncalibratedButtonActionPerformed

    private void calibratedValueFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_calibratedValueFieldKeyPressed

        XTCEViewerFunctions.copyPasteTextArea( evt, calibratedValueField );

    }//GEN-LAST:event_calibratedValueFieldKeyPressed

    private void uncalibratedValueFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_uncalibratedValueFieldKeyPressed

        XTCEViewerFunctions.copyPasteTextArea( evt, uncalibratedValueField );

    }//GEN-LAST:event_uncalibratedValueFieldKeyPressed

    private void rawValueFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rawValueFieldKeyPressed

        XTCEViewerFunctions.copyPasteTextArea( evt, rawValueField );

    }//GEN-LAST:event_rawValueFieldKeyPressed

    private void showWarnings() {

        warningsText.setText( "" );
        appendWarnings();

    }

    private void appendWarnings() {

        for ( String warning : itemValueObj_.getWarnings() ) {
            warningsText.append( warning +
                                 System.getProperty( "line.separator" ) ); // NOI18N
        }

    }

    private void resetCaretPositions() {

        rawValueField.setCaretPosition( 0 );
        calibratedValueField.setCaretPosition( 0 );
        uncalibratedValueField.setCaretPosition( 0 );
        binaryValueField.setCaretPosition( 0 );
        warningsText.setCaretPosition( 0 );

    }

    // Private Data Members

    XTCEItemValue itemValueObj_ = null;


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel binaryFieldLabel;
    private javax.swing.JTextField binaryValueField;
    private javax.swing.JLabel calibratedFieldLabel;
    private javax.swing.JTextField calibratedValueField;
    private javax.swing.JButton dismissButton;
    private javax.swing.JPanel dismissPanel;
    private javax.swing.JPanel encodeDecodePanel;
    private javax.swing.JButton makeFromCalibratedButton;
    private javax.swing.JButton makeFromRawButton;
    private javax.swing.JButton makeFromUncalibratedButton;
    private javax.swing.JLabel orLabel;
    private javax.swing.JLabel rawFieldLabel;
    private javax.swing.JTextField rawValueField;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JTextField uncalibratedValueField;
    private javax.swing.JLabel warningsLabel;
    private javax.swing.JScrollPane warningsScrollPane;
    private javax.swing.JTextArea warningsText;
    // End of variables declaration//GEN-END:variables
}
