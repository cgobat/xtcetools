/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.omg.space.xtce.ui;

import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import org.omg.space.xtce.toolkit.XTCEAlias;
import org.omg.space.xtce.toolkit.XTCEDatabase;
import org.omg.space.xtce.toolkit.XTCEFunctions;
import org.omg.space.xtce.toolkit.XTCESpaceSystem;
import org.omg.space.xtce.toolkit.XTCETelecommand;

/**
 *
 * @author David Overeem
 *
 */

public class XTCEViewerTelecommandFindDialog extends javax.swing.JFrame {

    /**
     * Creates new form XTCEViewerContainerFindDialog
     */
    public XTCEViewerTelecommandFindDialog( XTCEViewer            parent,
                                            XTCEViewerPreferences prefs,
                                            XTCEDatabase          dbFile ) {
        initComponents();
        xtceViewer_ = parent;
        prefs_      = prefs;
        dbFile_     = dbFile;
        populatePreviousSearches( true );
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

        findByButtonGroup = new javax.swing.ButtonGroup();
        buttonGroup1 = new javax.swing.ButtonGroup();
        containerFindPanel = new javax.swing.JPanel();
        searchTextLabel = new javax.swing.JLabel();
        searchTextComboBox = new javax.swing.JComboBox();
        optionsPanel = new javax.swing.JPanel();
        containerFindByAliasRadioButton = new javax.swing.JRadioButton();
        containerFindByNameRadioButton = new javax.swing.JRadioButton();
        executeButton = new javax.swing.JButton();
        resultsScrollPane = new javax.swing.JScrollPane();
        resultsTable = new javax.swing.JTable();
        buttonPanel = new javax.swing.JPanel();
        goToContainerButton = new javax.swing.JButton();
        dismissButton = new javax.swing.JButton();
        resultsText = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("org/omg/space/xtce/toolkit/MessagesBundle"); // NOI18N
        setTitle(bundle.getString("dialog_findtelecommand_title")); // NOI18N

        searchTextLabel.setText(bundle.getString("dialog_findparameter_searchtext_label")); // NOI18N

        searchTextComboBox.setEditable(true);
        searchTextComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchTextComboBoxActionPerformed(evt);
            }
        });

        findByButtonGroup.add(containerFindByAliasRadioButton);
        containerFindByAliasRadioButton.setText(bundle.getString("dialog_findparameter_asalias_radiobuttom")); // NOI18N

        findByButtonGroup.add(containerFindByNameRadioButton);
        containerFindByNameRadioButton.setSelected(true);
        containerFindByNameRadioButton.setText(bundle.getString("dialog_findparameter_asname_radiobutton")); // NOI18N

        javax.swing.GroupLayout optionsPanelLayout = new javax.swing.GroupLayout(optionsPanel);
        optionsPanel.setLayout(optionsPanelLayout);
        optionsPanelLayout.setHorizontalGroup(
            optionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(optionsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(containerFindByNameRadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(containerFindByAliasRadioButton)
                .addContainerGap())
        );
        optionsPanelLayout.setVerticalGroup(
            optionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(optionsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(optionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(containerFindByNameRadioButton)
                    .addComponent(containerFindByAliasRadioButton))
                .addContainerGap())
        );

        executeButton.setText(bundle.getString("dialog_findparameter_execute_button")); // NOI18N
        executeButton.setMaximumSize(new java.awt.Dimension(80, 25));
        executeButton.setMinimumSize(new java.awt.Dimension(80, 25));
        executeButton.setPreferredSize(new java.awt.Dimension(80, 25));
        executeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                executeButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout containerFindPanelLayout = new javax.swing.GroupLayout(containerFindPanel);
        containerFindPanel.setLayout(containerFindPanelLayout);
        containerFindPanelLayout.setHorizontalGroup(
            containerFindPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(containerFindPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(containerFindPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(containerFindPanelLayout.createSequentialGroup()
                        .addComponent(searchTextLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(searchTextComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(executeButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(containerFindPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(optionsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        containerFindPanelLayout.setVerticalGroup(
            containerFindPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(containerFindPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(containerFindPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchTextLabel)
                    .addComponent(searchTextComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(executeButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(optionsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        resultsTable.setAutoCreateRowSorter(true);
        resultsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Space System", "Telecommand Name", "Telecommand Alias(s)", "Description"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        resultsTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                resultsTableMouseClicked(evt);
            }
        });
        resultsScrollPane.setViewportView(resultsTable);
        if (resultsTable.getColumnModel().getColumnCount() > 0) {
            resultsTable.getColumnModel().getColumn(0).setHeaderValue(bundle.getString("find_menu_spacesystem_label")); // NOI18N
            resultsTable.getColumnModel().getColumn(1).setHeaderValue(bundle.getString("dialog_findtelecommand_tcname_table_text")); // NOI18N
            resultsTable.getColumnModel().getColumn(2).setHeaderValue(bundle.getString("dialog_findtelecommand_tcalias_table_text")); // NOI18N
            resultsTable.getColumnModel().getColumn(3).setHeaderValue(bundle.getString("table_parameters_desc_col_label")); // NOI18N
        }

        goToContainerButton.setText(bundle.getString("dialog_findparameter_goto_button")); // NOI18N
        goToContainerButton.setMaximumSize(new java.awt.Dimension(125, 25));
        goToContainerButton.setMinimumSize(new java.awt.Dimension(125, 25));
        goToContainerButton.setPreferredSize(new java.awt.Dimension(125, 25));
        goToContainerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                goToContainerButtonActionPerformed(evt);
            }
        });

        dismissButton.setText(bundle.getString("dialog_findparameter_dismiss_button")); // NOI18N
        dismissButton.setMaximumSize(new java.awt.Dimension(125, 25));
        dismissButton.setMinimumSize(new java.awt.Dimension(125, 25));
        dismissButton.setPreferredSize(new java.awt.Dimension(125, 25));
        dismissButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dismissButtonActionPerformed(evt);
            }
        });

        resultsText.setText(bundle.getString("dialog_findparameter_initialresults_text")); // NOI18N

        javax.swing.GroupLayout buttonPanelLayout = new javax.swing.GroupLayout(buttonPanel);
        buttonPanel.setLayout(buttonPanelLayout);
        buttonPanelLayout.setHorizontalGroup(
            buttonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buttonPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(goToContainerButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(dismissButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(buttonPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(resultsText)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        buttonPanelLayout.setVerticalGroup(
            buttonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buttonPanelLayout.createSequentialGroup()
                .addComponent(resultsText)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(buttonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(goToContainerButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addComponent(containerFindPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(resultsScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 580, Short.MAX_VALUE)
                    .addComponent(buttonPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(containerFindPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(resultsScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        getAccessibleContext().setAccessibleName("Find Telecommands");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void dismissButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dismissButtonActionPerformed
        this.dispatchEvent( new WindowEvent( this, WindowEvent.WINDOW_CLOSING ) );
    }//GEN-LAST:event_dismissButtonActionPerformed

    private void executeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_executeButtonActionPerformed
        String searchText = updatePreviousSearches();
        populateTelecommandTable( searchText );
    }//GEN-LAST:event_executeButtonActionPerformed

    private void goToContainerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_goToContainerButtonActionPerformed

        int row = resultsTable.getSelectedRow();
        if ( row == -1 ) {
            return;
        }

        String spaceSystemName = (String)resultsTable.getValueAt( row, 0 );
        String telecommandName = (String)resultsTable.getValueAt( row, 1 );

        xtceViewer_.goToTelecommand( telecommandName, spaceSystemName );

    }//GEN-LAST:event_goToContainerButtonActionPerformed

    private void searchTextComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchTextComboBoxActionPerformed
        if ( isVisible() == true ) {
            executeButtonActionPerformed( evt );
        }
    }//GEN-LAST:event_searchTextComboBoxActionPerformed

    private void resultsTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_resultsTableMouseClicked
        if ( evt.getClickCount() == 2 ) {
            goToContainerButtonActionPerformed( null );
            dismissButtonActionPerformed( null );
        }
    }//GEN-LAST:event_resultsTableMouseClicked

    private void populatePreviousSearches( boolean setEmptyRow ) {
        searchTextComboBox.removeAllItems();
        List<String> itemList = prefs_.getRecentFindTelecommandSearches();
        if ( setEmptyRow == true ) {
            searchTextComboBox.addItem( "" );
        }
        for ( String searchItem : itemList ) {
            if ( setEmptyRow == false ) {
                if ( searchItem.isEmpty() == true ) {
                    continue;
                }
            }
            searchTextComboBox.addItem( searchItem );
        }
        searchTextComboBox.setSelectedIndex( 0 );
    }

    private String updatePreviousSearches() {
        String searchText = (String)searchTextComboBox.getSelectedItem();
        if ( searchText == null ) {
            searchText = "";
        }
        prefs_.addFindTelecommandSearch( searchText );
        populatePreviousSearches( false );
        return searchText;
    }

    private void populateTelecommandTable( String searchText ) {

        String preferredNamespace = prefs_.getPreferredAliasNamespaceOption();

        List<XTCESpaceSystem>      spaceSystems = dbFile_.getSpaceSystemTree();
        ArrayList<XTCETelecommand> results      = new ArrayList<>();

        for ( XTCESpaceSystem spaceSystem : spaceSystems ) {

            List<XTCETelecommand> containers = spaceSystem.getTelecommands();

            for ( XTCETelecommand container : containers ) {
                if ( containerFindByNameRadioButton.isSelected() == true ) {
                    if ( XTCEFunctions.matchesUsingGlob( container.getName(), searchText ) == true ) {
                        results.add( container );
                    }
                } else {
                    if ( preferredNamespace.isEmpty() == false ) {
                        String searchName = container.getAlias( preferredNamespace );
                        if ( searchName != null ) {
                            if ( XTCEFunctions.matchesUsingGlob( searchName, searchText ) == true ) {
                                results.add( container );
                            }
                        }
                    } else {
                        List<XTCEAlias> aliases = container.getAliasSet();
                        for ( XTCEAlias alias : aliases ) {
                            if ( XTCEFunctions.matchesUsingGlob( alias.getAliasName(), searchText) == true ) {
                                results.add( container );
                                break;
                            }
                        }
                    }
                }
            }

        }

        updateTelecommandTable( results, searchText );

    }

    private void updateTelecommandTable( List<XTCETelecommand> results,
                                         String                searchText ) {

        boolean showAllNamespaces   = prefs_.getShowAllAliasNamespacesOption();
        boolean showAliasNamespaces = prefs_.getShowAliasNamespacesOption();
        String  preferredNamespace  = prefs_.getPreferredAliasNamespaceOption();

        DefaultTableModel tableModel = (DefaultTableModel)resultsTable.getModel();
        tableModel.setRowCount( 0 );

        if ( results.size() > 0 ) {
            resultsText.setText( "Found " + Long.toString( results.size() ) + " Container(s)" );
        } else {
            resultsText.setText( "No Results For Search '" + searchText + "'" );
            return;
        }

        for ( XTCETelecommand container : results ) {

            String aliasString =
                XTCEFunctions.makeAliasDisplayString( container,
                                                      showAllNamespaces,
                                                      showAliasNamespaces,
                                                      preferredNamespace );

            Object rowData[] = { container.getSpaceSystemPath(),
                                 container.getName(),
                                 aliasString,
                                 container.getDescription() };

            tableModel.addRow( rowData );

        }

    }

    // Private Data Members

    private XTCEViewer            xtceViewer_ = null;
    private XTCEViewerPreferences prefs_      = null;
    private XTCEDatabase          dbFile_     = null;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JRadioButton containerFindByAliasRadioButton;
    private javax.swing.JRadioButton containerFindByNameRadioButton;
    private javax.swing.JPanel containerFindPanel;
    private javax.swing.JButton dismissButton;
    private javax.swing.JButton executeButton;
    private javax.swing.ButtonGroup findByButtonGroup;
    private javax.swing.JButton goToContainerButton;
    private javax.swing.JPanel optionsPanel;
    private javax.swing.JScrollPane resultsScrollPane;
    private javax.swing.JTable resultsTable;
    private javax.swing.JLabel resultsText;
    private javax.swing.JComboBox searchTextComboBox;
    private javax.swing.JLabel searchTextLabel;
    // End of variables declaration//GEN-END:variables
}
