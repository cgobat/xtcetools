/* Copyright 2015-2016 David Overeem (dovereem@startmail.com)
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

import org.xtce.apps.editor.dialogs.XTCEViewerFileUpgradeProgressDialog;
import org.xtce.apps.editor.dialogs.XTCEViewerCreateEditSpaceSystemDialog;
import org.xtce.apps.editor.dialogs.XTCEViewerXpathQueryDialog;
import org.xtce.apps.editor.dialogs.XTCEViewerTelecommandFindDialog;
import org.xtce.apps.editor.dialogs.XTCEViewerParameterXmlDialog;
import org.xtce.apps.editor.dialogs.XTCEViewerParameterUsageDialog;
import org.xtce.apps.editor.dialogs.XTCEViewerParameterFindDialog;
import org.xtce.apps.editor.dialogs.XTCEViewerParameterDetailDialog;
import org.xtce.apps.editor.dialogs.XTCEViewerHelpMenuAboutDialog;
import org.xtce.apps.editor.dialogs.XTCEViewerHelpBrowserDialog;
import org.xtce.apps.editor.dialogs.XTCEViewerEncodeDecodeItemDialog;
import org.xtce.apps.editor.dialogs.XTCEViewerContainerXmlDialog;
import org.xtce.apps.editor.dialogs.XTCEViewerContainerFindDialog;
import org.xtce.apps.editor.dialogs.XTCEViewerContainerContentDialog;
import org.xtce.apps.editor.dialogs.XTCEViewerAliasNamespaceDialog;
import org.xtce.apps.editor.dialogs.XTCEViewerDatabaseMetricsDialog;
import java.awt.BorderLayout;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.Rectangle;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.xtce.toolkit.XTCEDatabaseException;
import org.xtce.toolkit.XTCEParameter;
import org.xtce.toolkit.XTCEDatabase;
import org.xtce.toolkit.XTCEDatabaseExporterCsv;
import org.xtce.toolkit.XTCEDatabaseExporter;
import org.xtce.toolkit.XTCESpaceSystem;
import org.xtce.toolkit.XTCETMContainer;
import org.xtce.toolkit.XTCEContainerContentEntry;
import org.xtce.toolkit.XTCEContainerContentModel;
import java.io.File;
import javax.swing.JFileChooser;
import java.awt.event.WindowEvent;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.ProgressMonitorInputStream;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;
import org.xtce.apps.editor.dialogs.XTCEViewerArgumentXmlDialog;
import org.xtce.apps.editor.dialogs.XTCEViewerContainerEncodingDialog;
import org.xtce.apps.editor.dialogs.XTCEViewerDatabaseCompressorDialog;
import org.xtce.apps.editor.dialogs.XTCEViewerFileCompressProgressDialog;
import org.xtce.toolkit.XTCEAbsoluteTimeType;
import org.xtce.toolkit.XTCEContainerContentEntry.FieldType;
import org.xtce.toolkit.XTCEContainerEntryValue;
import org.xtce.toolkit.XTCEFunctions;
import org.xtce.toolkit.XTCENamedObject;
import org.xtce.toolkit.XTCETMStream;
import org.xtce.toolkit.XTCETelecommand;
import org.xtce.toolkit.XTCETelecommandContentModel;
import org.xtce.apps.editor.ui.XTCEViewerContainerDrawing.Orientation;
import org.xtce.toolkit.XTCEArgument;
import org.xtce.toolkit.XTCETCContainer;

/** This class contains the XTCEViewer application with an executable main()
 * function.
 *
 * @author David Overeem
 *
 */

public class XTCEViewer extends javax.swing.JFrame {

    /** Creates a new XTCEViewer application Main Window.
     *
     *
     */

    public XTCEViewer() {

        prefs = XTCEViewerPreferences.getInstance();
        XTCEFunctions.setLocalePreference( prefs.getLanguagePreference() );
        initComponents();
        Thread.setDefaultUncaughtExceptionHandler( new XTCEViewerUncaughtExceptionHandler( this ) );
        buildSpaceSystemTrees(); // clears NetBeans defaults in the trees
        prefs.setParentWindow( this );
        prefs.updateRecentFilesList( mainWindowOpenRecentMenu, null );
        prefs.updateExampleFilesList( mainWindowOpenExampleMenu );
        mainWindowEditDocumentMenuItemActionPerformed( null );

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        spaceSystemDetailPopupMenu = new javax.swing.JPopupMenu();
        addSpaceSystemMenuItem = new javax.swing.JMenuItem();
        deleteSpaceSystemMenuItem = new javax.swing.JMenuItem();
        parameterDetailPopupMenu = new javax.swing.JPopupMenu();
        showXmlElementsMenuItem = new javax.swing.JMenuItem();
        showParameterDetailsMenuItem = new javax.swing.JMenuItem();
        showParameterUsageMenuItem = new javax.swing.JMenuItem();
        showEncodeDecodeDialogMenuItem = new javax.swing.JMenuItem();
        copyParameterCellMenuItem = new javax.swing.JMenuItem();
        copyParameterColumnMenuItem = new javax.swing.JMenuItem();
        copyParameterRowMenuItem = new javax.swing.JMenuItem();
        copyParameterTableMenuItem = new javax.swing.JMenuItem();
        containerTreePopupMenu = new javax.swing.JPopupMenu();
        showContainerXmlMenuItem = new javax.swing.JMenuItem();
        decodeContainerMenuItem = new javax.swing.JMenuItem();
        encodeContainerMenuItem = new javax.swing.JMenuItem();
        containerTablePopupMenu = new javax.swing.JPopupMenu();
        showItemXmlElementsMenuItem = new javax.swing.JMenuItem();
        goToEntryMenuItem = new javax.swing.JMenuItem();
        setConditionTrueMenuItem = new javax.swing.JMenuItem();
        setRepeatCounterMenuItem = new javax.swing.JMenuItem();
        copyContainerCellMenuItem = new javax.swing.JMenuItem();
        copyContainerColumnMenuItem = new javax.swing.JMenuItem();
        copyContainerRowMenuItem = new javax.swing.JMenuItem();
        copyContainerTableMenuItem = new javax.swing.JMenuItem();
        containerDrawingPopupMenu = new javax.swing.JPopupMenu();
        saveContainerDrawingMenuItem = new javax.swing.JMenuItem();
        cloneContainerDrawingMenuItem = new javax.swing.JMenuItem();
        showContainerDrawingXmlMenuItem = new javax.swing.JMenuItem();
        decodeContainerDrawingMenuItem = new javax.swing.JMenuItem();
        encodeContainerDrawingMenuItem = new javax.swing.JMenuItem();
        parameterFieldExclusion = new javax.swing.ButtonGroup();
        parameterLocationExclusion = new javax.swing.ButtonGroup();
        exportParametersButtonGroup = new javax.swing.ButtonGroup();
        exportContainersButtonGroup = new javax.swing.ButtonGroup();
        exportTelecommandsButtonGroup = new javax.swing.ButtonGroup();
        parameterExportPanel = new javax.swing.JPanel();
        exportParametersLabel = new javax.swing.JLabel();
        exportParametersCsvRadioButton = new javax.swing.JRadioButton();
        exportParametersCppRadioButton = new javax.swing.JRadioButton();
        exportParametersInControlRadioButton = new javax.swing.JRadioButton();
        exportParametersCometRadioButton = new javax.swing.JRadioButton();
        exportParametersIncludeHeaderRowCheckbox = new javax.swing.JCheckBox();
        exportParametersUseNamespacesCheckbox = new javax.swing.JCheckBox();
        exportParametersCharSetComboBox = new javax.swing.JComboBox();
        containerExportPanel = new javax.swing.JPanel();
        exportContainersLabel = new javax.swing.JLabel();
        exportContainersCsvRadioButton = new javax.swing.JRadioButton();
        exportContainersCppRadioButton = new javax.swing.JRadioButton();
        exportContainersInControlRadioButton = new javax.swing.JRadioButton();
        exportContainersCometRadioButton = new javax.swing.JRadioButton();
        exportContainersIncludeHeaderRowCheckbox = new javax.swing.JCheckBox();
        exportContainersUseNamespacesCheckbox = new javax.swing.JCheckBox();
        exportContainersCharSetComboBox = new javax.swing.JComboBox();
        telecommandExportPanel = new javax.swing.JPanel();
        exportTelecommandsLabel = new javax.swing.JLabel();
        exportTelecommandsCsvRadioButton = new javax.swing.JRadioButton();
        exportTelecommandsCppRadioButton = new javax.swing.JRadioButton();
        exportTelecommandsInControlRadioButton = new javax.swing.JRadioButton();
        exportTelecommandsCometRadioButton = new javax.swing.JRadioButton();
        exportTelecommandsIncludeHeaderRowCheckbox = new javax.swing.JCheckBox();
        exportTelecommandsUseNamespacesCheckbox = new javax.swing.JCheckBox();
        exportTelecommandsCharSetComboBox = new javax.swing.JComboBox();
        messagesDialogPanel = new javax.swing.JScrollPane();
        messagesDialogText = new javax.swing.JTextArea();
        streamTreePopupMenu = new javax.swing.JPopupMenu();
        showStreamXmlMenuItem = new javax.swing.JMenuItem();
        decodeStreamMenuItem = new javax.swing.JMenuItem();
        telecommandTreePopupMenu = new javax.swing.JPopupMenu();
        showTelecommandXmlMenuItem = new javax.swing.JMenuItem();
        decodeTelecommandMenuItem = new javax.swing.JMenuItem();
        encodeTelecommandMenuItem = new javax.swing.JMenuItem();
        telecommandDrawingPopupMenu = new javax.swing.JPopupMenu();
        saveTelecommandDrawingMenuItem = new javax.swing.JMenuItem();
        cloneTelecommandDrawingMenuItem = new javax.swing.JMenuItem();
        showTelecommandDrawingXmlMenuItem = new javax.swing.JMenuItem();
        decodeTelecommandDrawingMenuItem = new javax.swing.JMenuItem();
        encodeTelecommandDrawingMenuItem = new javax.swing.JMenuItem();
        telecommandTablePopupMenu = new javax.swing.JPopupMenu();
        showTcItemXmlElementsMenuItem = new javax.swing.JMenuItem();
        copyTcCellMenuItem = new javax.swing.JMenuItem();
        copyTcColumnMenuItem = new javax.swing.JMenuItem();
        copyTcRowMenuItem = new javax.swing.JMenuItem();
        copyTcTableMenuItem = new javax.swing.JMenuItem();
        loadedFilenameLabel = new javax.swing.JLabel();
        loadedSchemaLabel = new javax.swing.JLabel();
        mainWindowPrimaryWorkspace = new javax.swing.JTabbedPane();
        spaceSystemOverviewPanel = new javax.swing.JPanel();
        spaceSystemOverviewLabel = new javax.swing.JLabel();
        jSplitPane1 = new javax.swing.JSplitPane();
        detailSpaceSystemTreeScrollPane = new javax.swing.JScrollPane();
        detailSpaceSystemTree = new javax.swing.JTree();
        detailSpaceSystemPanelScrollPane = new javax.swing.JScrollPane();
        tmParametersPanel = new javax.swing.JPanel();
        tmParametersOverviewLabel = new javax.swing.JLabel();
        jSplitPane2 = new javax.swing.JSplitPane();
        tmParameterSpaceSystemTreeScrollPane = new javax.swing.JScrollPane();
        tmParameterSpaceSystemTree = new javax.swing.JTree();
        tmParametersTableScrollPane = new javax.swing.JScrollPane();
        tmParametersTable = new javax.swing.JTable();
        tcParametersPanel = new javax.swing.JPanel();
        tcParametersOverviewLabel = new javax.swing.JLabel();
        jSplitPane3 = new javax.swing.JSplitPane();
        tcParameterSpaceSystemTreeScrollPane = new javax.swing.JScrollPane();
        tcParameterSpaceSystemTree = new javax.swing.JTree();
        tcParametersTableScrollPane = new javax.swing.JScrollPane();
        tcParametersTable = new javax.swing.JTable();
        tmContainersPanel = new javax.swing.JPanel();
        tmContainerDetailsLabel = new javax.swing.JLabel();
        jSplitPane4 = new javax.swing.JSplitPane();
        tmContainerTreeScrollPane = new javax.swing.JScrollPane();
        tmContainerTree = new javax.swing.JTree();
        tmContainerContentSplitPane = new javax.swing.JSplitPane();
        tmContainerTableScrollPane = new javax.swing.JScrollPane();
        tmContainerTable = new javax.swing.JTable();
        tmContainerDrawingScrollPane = new javax.swing.JScrollPane();
        tcDefinitionsPanel = new javax.swing.JPanel();
        tcDefinitionsLabel = new javax.swing.JLabel();
        jSplitPane6 = new javax.swing.JSplitPane();
        tcDefinitionsScrollPane = new javax.swing.JScrollPane();
        tcDefinitionsSpaceSystemTree = new javax.swing.JTree();
        tcSpaceSystemSplitPane = new javax.swing.JSplitPane();
        tcTreeScrollPane = new javax.swing.JScrollPane();
        tcTree = new javax.swing.JTree();
        tcContentSplitPane = new javax.swing.JSplitPane();
        tcContentScrollPane = new javax.swing.JScrollPane();
        tcContentTable = new javax.swing.JTable();
        tcContentDrawingScrollPane = new javax.swing.JScrollPane();
        tmStreamsPanel = new javax.swing.JPanel();
        tmStreamDetailsLabel = new javax.swing.JLabel();
        jSplitPane5 = new javax.swing.JSplitPane();
        tmStreamTreeScrollPane = new javax.swing.JScrollPane();
        tmStreamTree = new javax.swing.JTree();
        jScrollPane1 = new javax.swing.JScrollPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        tmStreamContentTree = new javax.swing.JTree();
        tmTypesPanel = new javax.swing.JPanel();
        tcTypesPanel = new javax.swing.JPanel();
        mainWindowMessageScrollingPane = new javax.swing.JScrollPane();
        messagesText = new javax.swing.JTextArea();
        messagesOutputLabel = new javax.swing.JLabel();
        mainWindowMenuBar = new javax.swing.JMenuBar();
        mainWindowFileMenu = new javax.swing.JMenu();
        mainWindowOpenFileMenuItem = new javax.swing.JMenuItem();
        mainWindowOpenRecentMenu = new javax.swing.JMenu();
        mainWindowOpenExampleMenu = new javax.swing.JMenu();
        mainWindowSaveFileMenuItem = new javax.swing.JMenuItem();
        mainWindowCloseFileMenuItem = new javax.swing.JMenuItem();
        mainWindowCreateFileMenuItem = new javax.swing.JMenuItem();
        mainWindowCompressFileMenuItem = new javax.swing.JMenuItem();
        mainWindowUpgradeFileMenuItem = new javax.swing.JMenuItem();
        mainWindowExitMenuItem = new javax.swing.JMenuItem();
        mainWindowEditMenu = new javax.swing.JMenu();
        mainWindowClearMessagesMenuItem = new javax.swing.JMenuItem();
        mainWindowFindMenu = new javax.swing.JMenu();
        mainWindowFindSpaceSystemMenuItem = new javax.swing.JMenuItem();
        mainWindowFindParameterMenuItem = new javax.swing.JMenuItem();
        mainWindowFindContainerMenuItem = new javax.swing.JMenuItem();
        mainWindowFindTelecommandMenuItem = new javax.swing.JMenuItem();
        mainWindowFindByXPathMenuItem = new javax.swing.JMenuItem();
        mainWindowShowMenu = new javax.swing.JMenu();
        mainWindowExpandAllSpaceSystemTreeMenuItem = new javax.swing.JMenuItem();
        mainWindowExpandContainerTreeMenuItem = new javax.swing.JMenuItem();
        mainWindowMessagesDialogMenuItem = new javax.swing.JMenuItem();
        mainWindowShowMetricsMenuItem = new javax.swing.JMenuItem();
        mainWindowExportMenu = new javax.swing.JMenu();
        mainWindowExportParametersMenuItem = new javax.swing.JMenuItem();
        mainWindowExportContainersMenuItem = new javax.swing.JMenuItem();
        mainWindowExportTelecommandsMenuItem = new javax.swing.JMenuItem();
        mainWindowOptionsMenu = new javax.swing.JMenu();
        mainWindowValidateOnLoadMenuItem = new javax.swing.JCheckBoxMenuItem();
        mainWindowShowAliasNamespaceMenuItem = new javax.swing.JCheckBoxMenuItem();
        mainWindowPreferredNamespaceMenuItem = new javax.swing.JMenuItem();
        mainWindowRecentFilesMaxMenuItem = new javax.swing.JMenuItem();
        mainWindowClearRecentFilesMenuItem = new javax.swing.JMenuItem();
        mainWindowUseXincludeMenuItem = new javax.swing.JCheckBoxMenuItem();
        mainWindowLocaleMenuItem = new javax.swing.JMenuItem();
        mainWindowShowAllConditionalsMenuItem = new javax.swing.JCheckBoxMenuItem();
        containerDrawingOrientationMenu = new javax.swing.JMenu();
        containerDrawingLeftToRight = new javax.swing.JRadioButtonMenuItem();
        containerDrawingTopToBottom = new javax.swing.JRadioButtonMenuItem();
        mainWindowEditDocumentMenuItem = new javax.swing.JCheckBoxMenuItem();
        mainWindowHelpMenu = new javax.swing.JMenu();
        mainWindowHelpSchemaMenuItem = new javax.swing.JMenuItem();
        mainWindowHelpApiMenuItem = new javax.swing.JMenuItem();
        mainWindowHelpToolMenuItem = new javax.swing.JMenuItem();
        mainWindowHelpCurrentMenuItem = new javax.swing.JMenuItem();
        mainWindowHelpAboutMenuItem = new javax.swing.JMenuItem();

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("org/xtce/toolkit/MessagesBundle"); // NOI18N
        addSpaceSystemMenuItem.setText(bundle.getString("options_popup_add_spacesystem")); // NOI18N
        addSpaceSystemMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addSpaceSystemMenuItemActionPerformed(evt);
            }
        });
        spaceSystemDetailPopupMenu.add(addSpaceSystemMenuItem);

        deleteSpaceSystemMenuItem.setText(bundle.getString("options_popup_delete_spacesystem")); // NOI18N
        deleteSpaceSystemMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteSpaceSystemMenuItemActionPerformed(evt);
            }
        });
        spaceSystemDetailPopupMenu.add(deleteSpaceSystemMenuItem);

        showXmlElementsMenuItem.setText(bundle.getString("options_popup_showxmlelements")); // NOI18N
        showXmlElementsMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showXmlElementsMenuItemActionPerformed(evt);
            }
        });
        parameterDetailPopupMenu.add(showXmlElementsMenuItem);

        showParameterDetailsMenuItem.setText(bundle.getString("options_popup_showparameterdetails")); // NOI18N
        showParameterDetailsMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showParameterDetailsMenuItemActionPerformed(evt);
            }
        });
        parameterDetailPopupMenu.add(showParameterDetailsMenuItem);

        showParameterUsageMenuItem.setText(bundle.getString("options_popup_showparameterusage")); // NOI18N
        showParameterUsageMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showParameterUsageMenuItemActionPerformed(evt);
            }
        });
        parameterDetailPopupMenu.add(showParameterUsageMenuItem);

        showEncodeDecodeDialogMenuItem.setText(bundle.getString("options_popup_encdec_values")); // NOI18N
        showEncodeDecodeDialogMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showEncodeDecodeDialogMenuItemActionPerformed(evt);
            }
        });
        parameterDetailPopupMenu.add(showEncodeDecodeDialogMenuItem);

        copyParameterCellMenuItem.setText(bundle.getString("general_copy_cell")); // NOI18N
        copyParameterCellMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                copyParameterCellMenuItemActionPerformed(evt);
            }
        });
        parameterDetailPopupMenu.add(copyParameterCellMenuItem);

        copyParameterColumnMenuItem.setText(bundle.getString("general_copy_column")); // NOI18N
        copyParameterColumnMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                copyParameterColumnMenuItemActionPerformed(evt);
            }
        });
        parameterDetailPopupMenu.add(copyParameterColumnMenuItem);

        copyParameterRowMenuItem.setText(bundle.getString("general_copy_row")); // NOI18N
        copyParameterRowMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                copyParameterRowMenuItemActionPerformed(evt);
            }
        });
        parameterDetailPopupMenu.add(copyParameterRowMenuItem);

        copyParameterTableMenuItem.setText(bundle.getString("general_copy_table")); // NOI18N
        copyParameterTableMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                copyParameterTableMenuItemActionPerformed(evt);
            }
        });
        parameterDetailPopupMenu.add(copyParameterTableMenuItem);

        showContainerXmlMenuItem.setText(bundle.getString("options_popup_showcontainerxml")); // NOI18N
        showContainerXmlMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showContainerXmlMenuItemActionPerformed(evt);
            }
        });
        containerTreePopupMenu.add(showContainerXmlMenuItem);

        decodeContainerMenuItem.setText(bundle.getString("options_popup_decodecontainer")); // NOI18N
        decodeContainerMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                decodeContainerMenuItemActionPerformed(evt);
            }
        });
        containerTreePopupMenu.add(decodeContainerMenuItem);

        encodeContainerMenuItem.setText(bundle.getString("options_popup_encodecontainer")); // NOI18N
        encodeContainerMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                encodeContainerMenuItemActionPerformed(evt);
            }
        });
        containerTreePopupMenu.add(encodeContainerMenuItem);

        showItemXmlElementsMenuItem.setText(bundle.getString("options_popup_showxmlelements")); // NOI18N
        showItemXmlElementsMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showItemXmlElementsMenuItemActionPerformed(evt);
            }
        });
        containerTablePopupMenu.add(showItemXmlElementsMenuItem);

        goToEntryMenuItem.setText(bundle.getString("options_popup_gotoentry")); // NOI18N
        goToEntryMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                goToEntryMenuItemActionPerformed(evt);
            }
        });
        containerTablePopupMenu.add(goToEntryMenuItem);

        setConditionTrueMenuItem.setText(bundle.getString("options_popup_setcondition")); // NOI18N
        setConditionTrueMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setConditionTrueMenuItemActionPerformed(evt);
            }
        });
        containerTablePopupMenu.add(setConditionTrueMenuItem);

        setRepeatCounterMenuItem.setText(bundle.getString("options_popup_setrepeat")); // NOI18N
        setRepeatCounterMenuItem.setEnabled(false);
        setRepeatCounterMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setRepeatCounterMenuItemActionPerformed(evt);
            }
        });
        containerTablePopupMenu.add(setRepeatCounterMenuItem);

        copyContainerCellMenuItem.setText(bundle.getString("general_copy_cell")); // NOI18N
        copyContainerCellMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                copyContainerCellMenuItemActionPerformed(evt);
            }
        });
        containerTablePopupMenu.add(copyContainerCellMenuItem);

        copyContainerColumnMenuItem.setText(bundle.getString("general_copy_column")); // NOI18N
        copyContainerColumnMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                copyContainerColumnMenuItemActionPerformed(evt);
            }
        });
        containerTablePopupMenu.add(copyContainerColumnMenuItem);

        copyContainerRowMenuItem.setText(bundle.getString("general_copy_row")); // NOI18N
        copyContainerRowMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                copyContainerRowMenuItemActionPerformed(evt);
            }
        });
        containerTablePopupMenu.add(copyContainerRowMenuItem);

        copyContainerTableMenuItem.setText(bundle.getString("general_copy_table")); // NOI18N
        copyContainerTableMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                copyContainerTableMenuItemActionPerformed(evt);
            }
        });
        containerTablePopupMenu.add(copyContainerTableMenuItem);

        saveContainerDrawingMenuItem.setText(bundle.getString("options_popup_savedrawing")); // NOI18N
        saveContainerDrawingMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveContainerDrawingMenuItemActionPerformed(evt);
            }
        });
        containerDrawingPopupMenu.add(saveContainerDrawingMenuItem);

        cloneContainerDrawingMenuItem.setText(bundle.getString("options_popup_clonecontainer")); // NOI18N
        cloneContainerDrawingMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cloneContainerDrawingMenuItemActionPerformed(evt);
            }
        });
        containerDrawingPopupMenu.add(cloneContainerDrawingMenuItem);

        showContainerDrawingXmlMenuItem.setText(bundle.getString("options_popup_showcontainerxml")); // NOI18N
        showContainerDrawingXmlMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showContainerDrawingXmlMenuItemActionPerformed(evt);
            }
        });
        containerDrawingPopupMenu.add(showContainerDrawingXmlMenuItem);

        decodeContainerDrawingMenuItem.setText(bundle.getString("options_popup_decodecontainer")); // NOI18N
        decodeContainerDrawingMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                decodeContainerDrawingMenuItemActionPerformed(evt);
            }
        });
        containerDrawingPopupMenu.add(decodeContainerDrawingMenuItem);

        encodeContainerDrawingMenuItem.setText(bundle.getString("options_popup_encodecontainer")); // NOI18N
        encodeContainerDrawingMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                encodeContainerDrawingMenuItemActionPerformed(evt);
            }
        });
        containerDrawingPopupMenu.add(encodeContainerDrawingMenuItem);

        exportParametersLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        exportParametersLabel.setText(bundle.getString("dialog_export_parameters_options_title")); // NOI18N
        exportParametersLabel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        exportParametersButtonGroup.add(exportParametersCsvRadioButton);
        exportParametersCsvRadioButton.setSelected(true);
        exportParametersCsvRadioButton.setText(bundle.getString("dialog_export_parameters_options_csv_text")); // NOI18N

        exportParametersButtonGroup.add(exportParametersCppRadioButton);
        exportParametersCppRadioButton.setText(bundle.getString("dialog_export_parameters_options_cxx_text")); // NOI18N

        exportParametersButtonGroup.add(exportParametersInControlRadioButton);
        exportParametersInControlRadioButton.setText(bundle.getString("dialog_export_parameters_options_incng_text")); // NOI18N

        exportParametersButtonGroup.add(exportParametersCometRadioButton);
        exportParametersCometRadioButton.setText(bundle.getString("dialog_export_parameters_options_osc_text")); // NOI18N

        exportParametersIncludeHeaderRowCheckbox.setSelected(true);
        exportParametersIncludeHeaderRowCheckbox.setText(bundle.getString("dialog_export_parameters_options_header_row_text")); // NOI18N

        exportParametersUseNamespacesCheckbox.setText(bundle.getString("dialog_export_parameters_options_ss_2_ns_text")); // NOI18N

        exportParametersCharSetComboBox.setModel(new DefaultComboBoxModel( Charset.availableCharsets().keySet().toArray() ));
        exportParametersCharSetComboBox.setSelectedItem(Charset.defaultCharset().toString());

        javax.swing.GroupLayout parameterExportPanelLayout = new javax.swing.GroupLayout(parameterExportPanel);
        parameterExportPanel.setLayout(parameterExportPanelLayout);
        parameterExportPanelLayout.setHorizontalGroup(
            parameterExportPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(parameterExportPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(parameterExportPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(parameterExportPanelLayout.createSequentialGroup()
                        .addComponent(exportParametersLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(parameterExportPanelLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(parameterExportPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(exportParametersCppRadioButton)
                            .addComponent(exportParametersCsvRadioButton)
                            .addComponent(exportParametersInControlRadioButton)
                            .addComponent(exportParametersCometRadioButton)
                            .addComponent(exportParametersIncludeHeaderRowCheckbox)
                            .addComponent(exportParametersUseNamespacesCheckbox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(exportParametersCharSetComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        parameterExportPanelLayout.setVerticalGroup(
            parameterExportPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(parameterExportPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(exportParametersLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(exportParametersCsvRadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(exportParametersCppRadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(exportParametersInControlRadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(exportParametersCometRadioButton)
                .addGap(18, 18, 18)
                .addComponent(exportParametersIncludeHeaderRowCheckbox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(exportParametersUseNamespacesCheckbox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(exportParametersCharSetComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        exportContainersLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        exportContainersLabel.setText(bundle.getString("dialog_export_containers_options_title")); // NOI18N
        exportContainersLabel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        exportContainersButtonGroup.add(exportContainersCsvRadioButton);
        exportContainersCsvRadioButton.setSelected(true);
        exportContainersCsvRadioButton.setText(bundle.getString("dialog_export_parameters_options_csv_text")); // NOI18N

        exportContainersButtonGroup.add(exportContainersCppRadioButton);
        exportContainersCppRadioButton.setText(bundle.getString("dialog_export_parameters_options_cxx_text")); // NOI18N

        exportContainersButtonGroup.add(exportContainersInControlRadioButton);
        exportContainersInControlRadioButton.setText(bundle.getString("dialog_export_containers_options_incng_text")); // NOI18N

        exportContainersButtonGroup.add(exportContainersCometRadioButton);
        exportContainersCometRadioButton.setText(bundle.getString("dialog_export_containers_options_osc_text")); // NOI18N

        exportContainersIncludeHeaderRowCheckbox.setSelected(true);
        exportContainersIncludeHeaderRowCheckbox.setText(bundle.getString("dialog_export_parameters_options_header_row_text")); // NOI18N

        exportContainersUseNamespacesCheckbox.setText(bundle.getString("dialog_export_parameters_options_ss_2_ns_text")); // NOI18N

        exportContainersCharSetComboBox.setModel(new DefaultComboBoxModel( Charset.availableCharsets().keySet().toArray() ));
        exportContainersCharSetComboBox.setSelectedItem(Charset.defaultCharset().toString());

        javax.swing.GroupLayout containerExportPanelLayout = new javax.swing.GroupLayout(containerExportPanel);
        containerExportPanel.setLayout(containerExportPanelLayout);
        containerExportPanelLayout.setHorizontalGroup(
            containerExportPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(containerExportPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(containerExportPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(containerExportPanelLayout.createSequentialGroup()
                        .addComponent(exportContainersLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(containerExportPanelLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(containerExportPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(exportContainersCppRadioButton)
                            .addComponent(exportContainersCsvRadioButton)
                            .addComponent(exportContainersInControlRadioButton)
                            .addComponent(exportContainersCometRadioButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(exportContainersIncludeHeaderRowCheckbox)
                            .addComponent(exportContainersUseNamespacesCheckbox)
                            .addComponent(exportContainersCharSetComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        containerExportPanelLayout.setVerticalGroup(
            containerExportPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(containerExportPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(exportContainersLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(exportContainersCsvRadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(exportContainersCppRadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(exportContainersInControlRadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(exportContainersCometRadioButton)
                .addGap(18, 18, 18)
                .addComponent(exportContainersIncludeHeaderRowCheckbox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(exportContainersUseNamespacesCheckbox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(exportContainersCharSetComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        exportTelecommandsLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        exportTelecommandsLabel.setText(bundle.getString("dialog_export_telecommands_options_title")); // NOI18N
        exportTelecommandsLabel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        exportTelecommandsButtonGroup.add(exportTelecommandsCsvRadioButton);
        exportTelecommandsCsvRadioButton.setSelected(true);
        exportTelecommandsCsvRadioButton.setText(bundle.getString("dialog_export_parameters_options_csv_text")); // NOI18N

        exportTelecommandsButtonGroup.add(exportTelecommandsCppRadioButton);
        exportTelecommandsCppRadioButton.setText(bundle.getString("dialog_export_parameters_options_cxx_text")); // NOI18N

        exportTelecommandsButtonGroup.add(exportTelecommandsInControlRadioButton);
        exportTelecommandsInControlRadioButton.setText(bundle.getString("dialog_export_telecommands_options_incng_text")); // NOI18N

        exportTelecommandsButtonGroup.add(exportTelecommandsCometRadioButton);
        exportTelecommandsCometRadioButton.setText(bundle.getString("dialog_export_telecommands_options_osc_text")); // NOI18N

        exportTelecommandsIncludeHeaderRowCheckbox.setSelected(true);
        exportTelecommandsIncludeHeaderRowCheckbox.setText(bundle.getString("dialog_export_parameters_options_header_row_text")); // NOI18N

        exportTelecommandsUseNamespacesCheckbox.setText(bundle.getString("dialog_export_parameters_options_ss_2_ns_text")); // NOI18N

        exportTelecommandsCharSetComboBox.setModel(new DefaultComboBoxModel( Charset.availableCharsets().keySet().toArray() ));
        exportTelecommandsCharSetComboBox.setSelectedItem(Charset.defaultCharset().toString()
        );

        javax.swing.GroupLayout telecommandExportPanelLayout = new javax.swing.GroupLayout(telecommandExportPanel);
        telecommandExportPanel.setLayout(telecommandExportPanelLayout);
        telecommandExportPanelLayout.setHorizontalGroup(
            telecommandExportPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(telecommandExportPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(telecommandExportPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(telecommandExportPanelLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(telecommandExportPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(exportTelecommandsCppRadioButton)
                            .addComponent(exportTelecommandsCsvRadioButton)
                            .addComponent(exportTelecommandsInControlRadioButton)
                            .addComponent(exportTelecommandsCometRadioButton)
                            .addComponent(exportTelecommandsIncludeHeaderRowCheckbox)
                            .addComponent(exportTelecommandsUseNamespacesCheckbox))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(telecommandExportPanelLayout.createSequentialGroup()
                        .addGroup(telecommandExportPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(exportTelecommandsLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(exportTelecommandsCharSetComboBox, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        telecommandExportPanelLayout.setVerticalGroup(
            telecommandExportPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(telecommandExportPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(exportTelecommandsLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(exportTelecommandsCsvRadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(exportTelecommandsCppRadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(exportTelecommandsInControlRadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(exportTelecommandsCometRadioButton)
                .addGap(18, 18, 18)
                .addComponent(exportTelecommandsIncludeHeaderRowCheckbox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(exportTelecommandsUseNamespacesCheckbox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(exportTelecommandsCharSetComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        messagesDialogPanel.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        messagesDialogPanel.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        messagesDialogPanel.setMinimumSize(new java.awt.Dimension(640, 480));
        messagesDialogPanel.setPreferredSize(new java.awt.Dimension(800, 600));

        messagesDialogText.setColumns(20);
        messagesDialogText.setRows(5);
        messagesDialogPanel.setViewportView(messagesDialogText);

        showStreamXmlMenuItem.setText(bundle.getString("options_popup_showstreamxml")); // NOI18N
        showStreamXmlMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showStreamXmlMenuItemActionPerformed(evt);
            }
        });
        streamTreePopupMenu.add(showStreamXmlMenuItem);

        decodeStreamMenuItem.setText(bundle.getString("options_popup_decodestream")); // NOI18N
        decodeStreamMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                decodeStreamMenuItemActionPerformed(evt);
            }
        });
        streamTreePopupMenu.add(decodeStreamMenuItem);

        showTelecommandXmlMenuItem.setText(bundle.getString("options_popup_showtelecommandxml")); // NOI18N
        showTelecommandXmlMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showTelecommandXmlMenuItemActionPerformed(evt);
            }
        });
        telecommandTreePopupMenu.add(showTelecommandXmlMenuItem);

        decodeTelecommandMenuItem.setText(bundle.getString("options_popup_decodetelecommand")); // NOI18N
        decodeTelecommandMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                decodeTelecommandMenuItemActionPerformed(evt);
            }
        });
        telecommandTreePopupMenu.add(decodeTelecommandMenuItem);

        encodeTelecommandMenuItem.setText(bundle.getString("options_popup_encodetelecommand")); // NOI18N
        encodeTelecommandMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                encodeTelecommandMenuItemActionPerformed(evt);
            }
        });
        telecommandTreePopupMenu.add(encodeTelecommandMenuItem);

        saveTelecommandDrawingMenuItem.setText(bundle.getString("options_popup_savedrawing")); // NOI18N
        saveTelecommandDrawingMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveTelecommandDrawingMenuItemActionPerformed(evt);
            }
        });
        telecommandDrawingPopupMenu.add(saveTelecommandDrawingMenuItem);

        cloneTelecommandDrawingMenuItem.setText(bundle.getString("options_popup_clonecontainer")); // NOI18N
        cloneTelecommandDrawingMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cloneTelecommandDrawingMenuItemActionPerformed(evt);
            }
        });
        telecommandDrawingPopupMenu.add(cloneTelecommandDrawingMenuItem);

        showTelecommandDrawingXmlMenuItem.setText(bundle.getString("options_popup_showtelecommandxml")); // NOI18N
        showTelecommandDrawingXmlMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showTelecommandDrawingXmlMenuItemActionPerformed(evt);
            }
        });
        telecommandDrawingPopupMenu.add(showTelecommandDrawingXmlMenuItem);

        decodeTelecommandDrawingMenuItem.setText(bundle.getString("options_popup_decodetelecommand")); // NOI18N
        decodeTelecommandDrawingMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                decodeTelecommandDrawingMenuItemActionPerformed(evt);
            }
        });
        telecommandDrawingPopupMenu.add(decodeTelecommandDrawingMenuItem);

        encodeTelecommandDrawingMenuItem.setText(bundle.getString("options_popup_encodetelecommand")); // NOI18N
        encodeTelecommandDrawingMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                encodeTelecommandDrawingMenuItemActionPerformed(evt);
            }
        });
        telecommandDrawingPopupMenu.add(encodeTelecommandDrawingMenuItem);

        showTcItemXmlElementsMenuItem.setText(bundle.getString("options_popup_showxmlelements")); // NOI18N
        showTcItemXmlElementsMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showTcItemXmlElementsMenuItemActionPerformed(evt);
            }
        });
        telecommandTablePopupMenu.add(showTcItemXmlElementsMenuItem);

        copyTcCellMenuItem.setText(bundle.getString("general_copy_cell")); // NOI18N
        copyTcCellMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                copyTcCellMenuItemActionPerformed(evt);
            }
        });
        telecommandTablePopupMenu.add(copyTcCellMenuItem);

        copyTcColumnMenuItem.setText(bundle.getString("general_copy_column")); // NOI18N
        copyTcColumnMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                copyTcColumnMenuItemActionPerformed(evt);
            }
        });
        telecommandTablePopupMenu.add(copyTcColumnMenuItem);

        copyTcRowMenuItem.setText(bundle.getString("general_copy_row")); // NOI18N
        copyTcRowMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                copyTcRowMenuItemActionPerformed(evt);
            }
        });
        telecommandTablePopupMenu.add(copyTcRowMenuItem);

        copyTcTableMenuItem.setText(bundle.getString("general_copy_table")); // NOI18N
        copyTcTableMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                copyTcTableMenuItemActionPerformed(evt);
            }
        });
        telecommandTablePopupMenu.add(copyTcTableMenuItem);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("xtceview");
        setMinimumSize(new java.awt.Dimension(800, 600));

        loadedFilenameLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        loadedFilenameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        loadedFilenameLabel.setText(bundle.getString("openfile_default_text_label")); // NOI18N

        loadedSchemaLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        loadedSchemaLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        loadedSchemaLabel.setText(bundle.getString("openfile_default_schema_label")); // NOI18N

        spaceSystemOverviewLabel.setText(bundle.getString("tab_desc_spacesystems_label")); // NOI18N

        detailSpaceSystemTreeScrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        detailSpaceSystemTreeScrollPane.setMinimumSize(new java.awt.Dimension(100, 0));
        detailSpaceSystemTreeScrollPane.setPreferredSize(new java.awt.Dimension(200, 0));

        detailSpaceSystemTree.setToolTipText(bundle.getString("general_tree_tooltip")); // NOI18N
        detailSpaceSystemTree.setMaximumSize(new java.awt.Dimension(32767, 32767));
        detailSpaceSystemTree.setMinimumSize(new java.awt.Dimension(100, 0));
        detailSpaceSystemTree.setPreferredSize(new java.awt.Dimension(100, 0));
        detailSpaceSystemTree.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                detailSpaceSystemTreeMousePressed(evt);
            }
        });
        detailSpaceSystemTree.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
            public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {
                detailSpaceSystemTreeValueChanged(evt);
            }
        });
        detailSpaceSystemTreeScrollPane.setViewportView(detailSpaceSystemTree);

        jSplitPane1.setLeftComponent(detailSpaceSystemTreeScrollPane);

        detailSpaceSystemPanelScrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jSplitPane1.setRightComponent(detailSpaceSystemPanelScrollPane);

        javax.swing.GroupLayout spaceSystemOverviewPanelLayout = new javax.swing.GroupLayout(spaceSystemOverviewPanel);
        spaceSystemOverviewPanel.setLayout(spaceSystemOverviewPanelLayout);
        spaceSystemOverviewPanelLayout.setHorizontalGroup(
            spaceSystemOverviewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, spaceSystemOverviewPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(spaceSystemOverviewLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1003, Short.MAX_VALUE)
        );
        spaceSystemOverviewPanelLayout.setVerticalGroup(
            spaceSystemOverviewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(spaceSystemOverviewPanelLayout.createSequentialGroup()
                .addComponent(spaceSystemOverviewLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE))
        );

        mainWindowPrimaryWorkspace.addTab(bundle.getString("tab_spacesystems_label"), spaceSystemOverviewPanel); // NOI18N

        tmParametersOverviewLabel.setText(bundle.getString("tab_desc_tmparameters_label")); // NOI18N

        tmParameterSpaceSystemTreeScrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        tmParameterSpaceSystemTreeScrollPane.setMinimumSize(new java.awt.Dimension(100, 0));
        tmParameterSpaceSystemTreeScrollPane.setPreferredSize(new java.awt.Dimension(200, 0));

        tmParameterSpaceSystemTree.setMaximumSize(new java.awt.Dimension(32767, 32767));
        tmParameterSpaceSystemTree.setMinimumSize(new java.awt.Dimension(100, 0));
        tmParameterSpaceSystemTree.setPreferredSize(new java.awt.Dimension(100, 0));
        tmParameterSpaceSystemTree.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
            public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {
                tmParameterSpaceSystemTreeValueChanged(evt);
            }
        });
        tmParameterSpaceSystemTreeScrollPane.setViewportView(tmParameterSpaceSystemTree);

        jSplitPane2.setLeftComponent(tmParameterSpaceSystemTreeScrollPane);

        tmParametersTableScrollPane.setToolTipText(bundle.getString("general_tree_noselect_tooltip")); // NOI18N

        tmParametersTable.setAutoCreateRowSorter(true);
        tmParametersTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Alias(s)", "Type", "Unit(s)", "Size", "Encoding", "Source", "ReadOnly", "Default Value", "Description"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tmParametersTable.setToolTipText(bundle.getString("general_drawing_tooltip")); // NOI18N
        tmParametersTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tmParametersTableMousePressed(evt);
            }
        });
        tmParametersTableScrollPane.setViewportView(tmParametersTable);
        if (tmParametersTable.getColumnModel().getColumnCount() > 0) {
            tmParametersTable.getColumnModel().getColumn(0).setPreferredWidth(150);
            tmParametersTable.getColumnModel().getColumn(0).setHeaderValue(bundle.getString("table_parameters_name_col_label")); // NOI18N
            tmParametersTable.getColumnModel().getColumn(1).setPreferredWidth(100);
            tmParametersTable.getColumnModel().getColumn(1).setHeaderValue(bundle.getString("table_parameters_aliases_col_label")); // NOI18N
            tmParametersTable.getColumnModel().getColumn(2).setMinWidth(0);
            tmParametersTable.getColumnModel().getColumn(2).setPreferredWidth(100);
            tmParametersTable.getColumnModel().getColumn(2).setMaxWidth(100);
            tmParametersTable.getColumnModel().getColumn(2).setHeaderValue(bundle.getString("table_parameters_type_col_label")); // NOI18N
            tmParametersTable.getColumnModel().getColumn(3).setHeaderValue(bundle.getString("table_parameters_unit_col_label")); // NOI18N
            tmParametersTable.getColumnModel().getColumn(4).setMinWidth(65);
            tmParametersTable.getColumnModel().getColumn(4).setPreferredWidth(65);
            tmParametersTable.getColumnModel().getColumn(4).setMaxWidth(65);
            tmParametersTable.getColumnModel().getColumn(4).setHeaderValue(bundle.getString("table_parameters_size_col_label")); // NOI18N
            tmParametersTable.getColumnModel().getColumn(5).setMinWidth(0);
            tmParametersTable.getColumnModel().getColumn(5).setPreferredWidth(120);
            tmParametersTable.getColumnModel().getColumn(5).setMaxWidth(120);
            tmParametersTable.getColumnModel().getColumn(5).setHeaderValue(bundle.getString("table_parameters_encoding_col_label")); // NOI18N
            tmParametersTable.getColumnModel().getColumn(6).setMinWidth(0);
            tmParametersTable.getColumnModel().getColumn(6).setPreferredWidth(95);
            tmParametersTable.getColumnModel().getColumn(6).setMaxWidth(95);
            tmParametersTable.getColumnModel().getColumn(6).setHeaderValue(bundle.getString("table_parameters_source_col_label")); // NOI18N
            tmParametersTable.getColumnModel().getColumn(7).setMinWidth(0);
            tmParametersTable.getColumnModel().getColumn(7).setPreferredWidth(65);
            tmParametersTable.getColumnModel().getColumn(7).setMaxWidth(65);
            tmParametersTable.getColumnModel().getColumn(7).setHeaderValue(bundle.getString("table_parameters_readonly_col_label")); // NOI18N
            tmParametersTable.getColumnModel().getColumn(8).setMinWidth(0);
            tmParametersTable.getColumnModel().getColumn(8).setPreferredWidth(120);
            tmParametersTable.getColumnModel().getColumn(8).setHeaderValue(bundle.getString("table_parameters_defaultvalue_col_label")); // NOI18N
            tmParametersTable.getColumnModel().getColumn(9).setPreferredWidth(250);
            tmParametersTable.getColumnModel().getColumn(9).setHeaderValue(bundle.getString("table_parameters_desc_col_label")); // NOI18N
        }

        jSplitPane2.setRightComponent(tmParametersTableScrollPane);

        javax.swing.GroupLayout tmParametersPanelLayout = new javax.swing.GroupLayout(tmParametersPanel);
        tmParametersPanel.setLayout(tmParametersPanelLayout);
        tmParametersPanelLayout.setHorizontalGroup(
            tmParametersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tmParametersPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(tmParametersOverviewLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jSplitPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1003, Short.MAX_VALUE)
        );
        tmParametersPanelLayout.setVerticalGroup(
            tmParametersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tmParametersPanelLayout.createSequentialGroup()
                .addComponent(tmParametersOverviewLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSplitPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE))
        );

        mainWindowPrimaryWorkspace.addTab(bundle.getString("tab_tmparameters_label"), tmParametersPanel); // NOI18N

        tcParametersOverviewLabel.setText(bundle.getString("tab_desc_tcparameters_label")); // NOI18N

        tcParameterSpaceSystemTreeScrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        tcParameterSpaceSystemTreeScrollPane.setMinimumSize(new java.awt.Dimension(100, 0));
        tcParameterSpaceSystemTreeScrollPane.setPreferredSize(new java.awt.Dimension(200, 0));

        tcParameterSpaceSystemTree.setMaximumSize(new java.awt.Dimension(32767, 32767));
        tcParameterSpaceSystemTree.setMinimumSize(new java.awt.Dimension(100, 0));
        tcParameterSpaceSystemTree.setPreferredSize(new java.awt.Dimension(100, 0));
        tcParameterSpaceSystemTree.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
            public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {
                tcParameterSpaceSystemTreeValueChanged(evt);
            }
        });
        tcParameterSpaceSystemTreeScrollPane.setViewportView(tcParameterSpaceSystemTree);

        jSplitPane3.setLeftComponent(tcParameterSpaceSystemTreeScrollPane);

        tcParametersTableScrollPane.setToolTipText(bundle.getString("general_tree_noselect_tooltip")); // NOI18N

        tcParametersTable.setAutoCreateRowSorter(true);
        tcParametersTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Alias(s)", "Type", "Unit(s)", "Size", "Encoding", "Source", "ReadOnly", "Default Value", "Description"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tcParametersTable.setToolTipText(bundle.getString("general_drawing_tooltip")); // NOI18N
        tcParametersTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tcParametersTableMousePressed(evt);
            }
        });
        tcParametersTableScrollPane.setViewportView(tcParametersTable);
        if (tcParametersTable.getColumnModel().getColumnCount() > 0) {
            tcParametersTable.getColumnModel().getColumn(0).setPreferredWidth(150);
            tcParametersTable.getColumnModel().getColumn(0).setHeaderValue(bundle.getString("table_parameters_name_col_label")); // NOI18N
            tcParametersTable.getColumnModel().getColumn(1).setPreferredWidth(100);
            tcParametersTable.getColumnModel().getColumn(1).setHeaderValue(bundle.getString("table_parameters_aliases_col_label")); // NOI18N
            tcParametersTable.getColumnModel().getColumn(2).setMinWidth(0);
            tcParametersTable.getColumnModel().getColumn(2).setPreferredWidth(100);
            tcParametersTable.getColumnModel().getColumn(2).setMaxWidth(100);
            tcParametersTable.getColumnModel().getColumn(2).setHeaderValue(bundle.getString("table_parameters_type_col_label")); // NOI18N
            tcParametersTable.getColumnModel().getColumn(3).setHeaderValue(bundle.getString("table_parameters_unit_col_label")); // NOI18N
            tcParametersTable.getColumnModel().getColumn(4).setMinWidth(65);
            tcParametersTable.getColumnModel().getColumn(4).setPreferredWidth(65);
            tcParametersTable.getColumnModel().getColumn(4).setMaxWidth(65);
            tcParametersTable.getColumnModel().getColumn(4).setHeaderValue(bundle.getString("table_parameters_size_col_label")); // NOI18N
            tcParametersTable.getColumnModel().getColumn(5).setMinWidth(0);
            tcParametersTable.getColumnModel().getColumn(5).setPreferredWidth(120);
            tcParametersTable.getColumnModel().getColumn(5).setMaxWidth(120);
            tcParametersTable.getColumnModel().getColumn(5).setHeaderValue(bundle.getString("table_parameters_encoding_col_label")); // NOI18N
            tcParametersTable.getColumnModel().getColumn(6).setMinWidth(0);
            tcParametersTable.getColumnModel().getColumn(6).setPreferredWidth(95);
            tcParametersTable.getColumnModel().getColumn(6).setMaxWidth(95);
            tcParametersTable.getColumnModel().getColumn(6).setHeaderValue(bundle.getString("table_parameters_source_col_label")); // NOI18N
            tcParametersTable.getColumnModel().getColumn(7).setMinWidth(0);
            tcParametersTable.getColumnModel().getColumn(7).setPreferredWidth(65);
            tcParametersTable.getColumnModel().getColumn(7).setMaxWidth(65);
            tcParametersTable.getColumnModel().getColumn(7).setHeaderValue(bundle.getString("table_parameters_readonly_col_label")); // NOI18N
            tcParametersTable.getColumnModel().getColumn(8).setMinWidth(0);
            tcParametersTable.getColumnModel().getColumn(8).setPreferredWidth(120);
            tcParametersTable.getColumnModel().getColumn(8).setHeaderValue(bundle.getString("table_parameters_defaultvalue_col_label")); // NOI18N
            tcParametersTable.getColumnModel().getColumn(9).setPreferredWidth(250);
            tcParametersTable.getColumnModel().getColumn(9).setHeaderValue(bundle.getString("table_parameters_desc_col_label")); // NOI18N
        }

        jSplitPane3.setRightComponent(tcParametersTableScrollPane);

        javax.swing.GroupLayout tcParametersPanelLayout = new javax.swing.GroupLayout(tcParametersPanel);
        tcParametersPanel.setLayout(tcParametersPanelLayout);
        tcParametersPanelLayout.setHorizontalGroup(
            tcParametersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tcParametersPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(tcParametersOverviewLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jSplitPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1003, Short.MAX_VALUE)
        );
        tcParametersPanelLayout.setVerticalGroup(
            tcParametersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tcParametersPanelLayout.createSequentialGroup()
                .addComponent(tcParametersOverviewLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSplitPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE))
        );

        mainWindowPrimaryWorkspace.addTab(bundle.getString("tab_tcparameters_label"), tcParametersPanel); // NOI18N

        tmContainerDetailsLabel.setText(bundle.getString("tab_desc_tmcontainers_label")); // NOI18N

        tmContainerTreeScrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        tmContainerTreeScrollPane.setMinimumSize(new java.awt.Dimension(100, 0));
        tmContainerTreeScrollPane.setPreferredSize(new java.awt.Dimension(200, 0));

        tmContainerTree.setToolTipText(bundle.getString("general_tree_tooltip")); // NOI18N
        tmContainerTree.setMaximumSize(new java.awt.Dimension(32767, 32767));
        tmContainerTree.setMinimumSize(new java.awt.Dimension(100, 0));
        tmContainerTree.setPreferredSize(new java.awt.Dimension(100, 0));
        tmContainerTree.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tmContainerTreeMousePressed(evt);
            }
        });
        tmContainerTree.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
            public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {
                tmContainerTreeValueChanged(evt);
            }
        });
        tmContainerTreeScrollPane.setViewportView(tmContainerTree);

        jSplitPane4.setLeftComponent(tmContainerTreeScrollPane);

        tmContainerContentSplitPane.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        tmContainerTableScrollPane.setToolTipText(bundle.getString("general_tree_noselect_tooltip")); // NOI18N
        tmContainerTableScrollPane.setMinimumSize(new java.awt.Dimension(0, 100));

        tmContainerTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Container Name", "Parameter Name", "Parameter Alias(s)", "Size", "Start Bit", "Value", "Default Value", "Condition", "Repeat"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tmContainerTable.setToolTipText(bundle.getString("general_drawing_tooltip")); // NOI18N
        tmContainerTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tmContainerTableMousePressed(evt);
            }
        });
        tmContainerTableScrollPane.setViewportView(tmContainerTable);
        if (tmContainerTable.getColumnModel().getColumnCount() > 0) {
            tmContainerTable.getColumnModel().getColumn(0).setHeaderValue(bundle.getString("table_containers_contname_label")); // NOI18N
            tmContainerTable.getColumnModel().getColumn(1).setHeaderValue(bundle.getString("table_containers_paramname_label")); // NOI18N
            tmContainerTable.getColumnModel().getColumn(2).setHeaderValue(bundle.getString("table_containers_paramaliases_label")); // NOI18N
            tmContainerTable.getColumnModel().getColumn(3).setMinWidth(65);
            tmContainerTable.getColumnModel().getColumn(3).setPreferredWidth(65);
            tmContainerTable.getColumnModel().getColumn(3).setMaxWidth(65);
            tmContainerTable.getColumnModel().getColumn(3).setHeaderValue(bundle.getString("table_containers_size_label")); // NOI18N
            tmContainerTable.getColumnModel().getColumn(4).setMinWidth(65);
            tmContainerTable.getColumnModel().getColumn(4).setPreferredWidth(65);
            tmContainerTable.getColumnModel().getColumn(4).setMaxWidth(65);
            tmContainerTable.getColumnModel().getColumn(4).setHeaderValue(bundle.getString("table_containers_startbit_label")); // NOI18N
            tmContainerTable.getColumnModel().getColumn(5).setPreferredWidth(75);
            tmContainerTable.getColumnModel().getColumn(5).setHeaderValue(bundle.getString("table_containers_value_label")); // NOI18N
            tmContainerTable.getColumnModel().getColumn(6).setPreferredWidth(75);
            tmContainerTable.getColumnModel().getColumn(6).setHeaderValue(bundle.getString("table_containers_defaultvalue_label")); // NOI18N
            tmContainerTable.getColumnModel().getColumn(7).setHeaderValue(bundle.getString("table_containers_condition_label")); // NOI18N
            tmContainerTable.getColumnModel().getColumn(8).setHeaderValue(bundle.getString("table_containers_repeat_label")); // NOI18N
        }

        tmContainerContentSplitPane.setLeftComponent(tmContainerTableScrollPane);

        tmContainerDrawingScrollPane.setToolTipText(bundle.getString("general_drawing_tooltip")); // NOI18N
        tmContainerDrawingScrollPane.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tmContainerDrawingScrollPaneMousePressed(evt);
            }
        });
        tmContainerContentSplitPane.setRightComponent(tmContainerDrawingScrollPane);

        jSplitPane4.setRightComponent(tmContainerContentSplitPane);

        javax.swing.GroupLayout tmContainersPanelLayout = new javax.swing.GroupLayout(tmContainersPanel);
        tmContainersPanel.setLayout(tmContainersPanelLayout);
        tmContainersPanelLayout.setHorizontalGroup(
            tmContainersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tmContainersPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(tmContainerDetailsLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jSplitPane4)
        );
        tmContainersPanelLayout.setVerticalGroup(
            tmContainersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tmContainersPanelLayout.createSequentialGroup()
                .addComponent(tmContainerDetailsLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSplitPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE))
        );

        mainWindowPrimaryWorkspace.addTab(bundle.getString("tab_tmcontainers_label"), tmContainersPanel); // NOI18N

        tcDefinitionsLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tcDefinitionsLabel.setText(bundle.getString("tab_desc_tcdefinitions_label")); // NOI18N

        tcDefinitionsScrollPane.setMinimumSize(new java.awt.Dimension(100, 0));
        tcDefinitionsScrollPane.setPreferredSize(new java.awt.Dimension(200, 0));

        tcDefinitionsSpaceSystemTree.setMaximumSize(new java.awt.Dimension(32767, 32767));
        tcDefinitionsSpaceSystemTree.setMinimumSize(new java.awt.Dimension(100, 0));
        tcDefinitionsSpaceSystemTree.setPreferredSize(new java.awt.Dimension(100, 0));
        tcDefinitionsSpaceSystemTree.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
            public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {
                tcDefinitionsSpaceSystemTreeValueChanged(evt);
            }
        });
        tcDefinitionsScrollPane.setViewportView(tcDefinitionsSpaceSystemTree);

        jSplitPane6.setLeftComponent(tcDefinitionsScrollPane);

        tcTreeScrollPane.setMinimumSize(new java.awt.Dimension(100, 0));
        tcTreeScrollPane.setPreferredSize(new java.awt.Dimension(200, 0));

        tcTree.setToolTipText(bundle.getString("general_tree_tooltip")); // NOI18N
        tcTree.setMaximumSize(new java.awt.Dimension(32767, 32767));
        tcTree.setMinimumSize(new java.awt.Dimension(100, 0));
        tcTree.setPreferredSize(new java.awt.Dimension(100, 0));
        tcTree.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tcTreeMousePressed(evt);
            }
        });
        tcTree.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
            public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {
                tcTreeValueChanged(evt);
            }
        });
        tcTreeScrollPane.setViewportView(tcTree);

        tcSpaceSystemSplitPane.setLeftComponent(tcTreeScrollPane);

        tcContentSplitPane.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        tcContentScrollPane.setToolTipText(bundle.getString("general_tree_noselect_tooltip")); // NOI18N

        tcContentTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Container Name", "Field Type", "Field Name", "Unit(s)", "Type", "Encoding", "Size", "Start Bit", "Value", "Default Value", "Condition", "Repeat", "Description"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tcContentTable.setToolTipText(bundle.getString("general_drawing_tooltip")); // NOI18N
        tcContentTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tcContentTableMousePressed(evt);
            }
        });
        tcContentScrollPane.setViewportView(tcContentTable);
        if (tcContentTable.getColumnModel().getColumnCount() > 0) {
            tcContentTable.getColumnModel().getColumn(0).setHeaderValue(bundle.getString("table_telecommands_contname_label")); // NOI18N
            tcContentTable.getColumnModel().getColumn(1).setMinWidth(75);
            tcContentTable.getColumnModel().getColumn(1).setPreferredWidth(75);
            tcContentTable.getColumnModel().getColumn(1).setMaxWidth(75);
            tcContentTable.getColumnModel().getColumn(1).setHeaderValue(bundle.getString("table_telecommands_fieldtype_label")); // NOI18N
            tcContentTable.getColumnModel().getColumn(2).setHeaderValue(bundle.getString("table_telecommands_itemname_label")); // NOI18N
            tcContentTable.getColumnModel().getColumn(3).setHeaderValue(bundle.getString("table_parameters_unit_col_label")); // NOI18N
            tcContentTable.getColumnModel().getColumn(4).setPreferredWidth(100);
            tcContentTable.getColumnModel().getColumn(4).setMaxWidth(100);
            tcContentTable.getColumnModel().getColumn(4).setHeaderValue(bundle.getString("table_parameters_type_col_label")); // NOI18N
            tcContentTable.getColumnModel().getColumn(5).setPreferredWidth(120);
            tcContentTable.getColumnModel().getColumn(5).setMaxWidth(120);
            tcContentTable.getColumnModel().getColumn(5).setHeaderValue(bundle.getString("table_parameters_encoding_col_label")); // NOI18N
            tcContentTable.getColumnModel().getColumn(6).setMinWidth(65);
            tcContentTable.getColumnModel().getColumn(6).setPreferredWidth(65);
            tcContentTable.getColumnModel().getColumn(6).setMaxWidth(65);
            tcContentTable.getColumnModel().getColumn(6).setHeaderValue(bundle.getString("table_telecommands_size_label")); // NOI18N
            tcContentTable.getColumnModel().getColumn(7).setMinWidth(65);
            tcContentTable.getColumnModel().getColumn(7).setPreferredWidth(65);
            tcContentTable.getColumnModel().getColumn(7).setMaxWidth(65);
            tcContentTable.getColumnModel().getColumn(7).setHeaderValue(bundle.getString("table_telecommands_startbit_label")); // NOI18N
            tcContentTable.getColumnModel().getColumn(8).setPreferredWidth(75);
            tcContentTable.getColumnModel().getColumn(8).setHeaderValue(bundle.getString("table_telecommands_value_label")); // NOI18N
            tcContentTable.getColumnModel().getColumn(9).setPreferredWidth(75);
            tcContentTable.getColumnModel().getColumn(9).setHeaderValue(bundle.getString("table_telecommands_defaultvalue_label")); // NOI18N
            tcContentTable.getColumnModel().getColumn(10).setHeaderValue(bundle.getString("table_telecommands_condition_label")); // NOI18N
            tcContentTable.getColumnModel().getColumn(11).setHeaderValue(bundle.getString("table_telecommands_repeat_label")); // NOI18N
            tcContentTable.getColumnModel().getColumn(12).setHeaderValue(bundle.getString("table_telecommands_desc_label")); // NOI18N
        }

        tcContentSplitPane.setTopComponent(tcContentScrollPane);

        tcContentDrawingScrollPane.setToolTipText(bundle.getString("general_drawing_tooltip")); // NOI18N
        tcContentDrawingScrollPane.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tcContentDrawingScrollPaneMousePressed(evt);
            }
        });
        tcContentSplitPane.setRightComponent(tcContentDrawingScrollPane);

        tcSpaceSystemSplitPane.setRightComponent(tcContentSplitPane);

        jSplitPane6.setRightComponent(tcSpaceSystemSplitPane);

        javax.swing.GroupLayout tcDefinitionsPanelLayout = new javax.swing.GroupLayout(tcDefinitionsPanel);
        tcDefinitionsPanel.setLayout(tcDefinitionsPanelLayout);
        tcDefinitionsPanelLayout.setHorizontalGroup(
            tcDefinitionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tcDefinitionsPanelLayout.createSequentialGroup()
                .addComponent(jSplitPane6)
                .addGap(10, 10, 10))
            .addGroup(tcDefinitionsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tcDefinitionsLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        tcDefinitionsPanelLayout.setVerticalGroup(
            tcDefinitionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tcDefinitionsPanelLayout.createSequentialGroup()
                .addComponent(tcDefinitionsLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSplitPane6)
                .addGap(0, 0, 0))
        );

        mainWindowPrimaryWorkspace.addTab(bundle.getString("tab_tcdefinitions_label"), tcDefinitionsPanel); // NOI18N

        tmStreamDetailsLabel.setText(bundle.getString("tab_desc_tmstreams_label")); // NOI18N

        tmStreamTreeScrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        tmStreamTreeScrollPane.setMinimumSize(new java.awt.Dimension(100, 0));
        tmStreamTreeScrollPane.setPreferredSize(new java.awt.Dimension(200, 0));

        tmStreamTree.setToolTipText(bundle.getString("general_tree_tooltip")); // NOI18N
        tmStreamTree.setMaximumSize(new java.awt.Dimension(32767, 32767));
        tmStreamTree.setMinimumSize(new java.awt.Dimension(100, 0));
        tmStreamTree.setPreferredSize(new java.awt.Dimension(100, 0));
        tmStreamTree.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tmStreamTreeMousePressed(evt);
            }
        });
        tmStreamTree.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
            public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {
                tmStreamTreeValueChanged(evt);
            }
        });
        tmStreamTreeScrollPane.setViewportView(tmStreamTree);

        jSplitPane5.setLeftComponent(tmStreamTreeScrollPane);

        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jSplitPane5.setRightComponent(jScrollPane1);

        tmStreamContentTree.setToolTipText(bundle.getString("general_tree_doubleclick_go")); // NOI18N
        tmStreamContentTree.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tmStreamContentTreeMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tmStreamContentTree);

        jSplitPane5.setRightComponent(jScrollPane2);

        javax.swing.GroupLayout tmStreamsPanelLayout = new javax.swing.GroupLayout(tmStreamsPanel);
        tmStreamsPanel.setLayout(tmStreamsPanelLayout);
        tmStreamsPanelLayout.setHorizontalGroup(
            tmStreamsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tmStreamsPanelLayout.createSequentialGroup()
                .addContainerGap(415, Short.MAX_VALUE)
                .addComponent(tmStreamDetailsLabel)
                .addContainerGap(405, Short.MAX_VALUE))
            .addGroup(tmStreamsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(tmStreamsPanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jSplitPane5)
                    .addContainerGap()))
        );
        tmStreamsPanelLayout.setVerticalGroup(
            tmStreamsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tmStreamsPanelLayout.createSequentialGroup()
                .addComponent(tmStreamDetailsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(tmStreamsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tmStreamsPanelLayout.createSequentialGroup()
                    .addGap(23, 23, 23)
                    .addComponent(jSplitPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 509, Short.MAX_VALUE)))
        );

        mainWindowPrimaryWorkspace.addTab(bundle.getString("tab_tmstreams_label"), tmStreamsPanel); // NOI18N

        javax.swing.GroupLayout tmTypesPanelLayout = new javax.swing.GroupLayout(tmTypesPanel);
        tmTypesPanel.setLayout(tmTypesPanelLayout);
        tmTypesPanelLayout.setHorizontalGroup(
            tmTypesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1003, Short.MAX_VALUE)
        );
        tmTypesPanelLayout.setVerticalGroup(
            tmTypesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 532, Short.MAX_VALUE)
        );

        mainWindowPrimaryWorkspace.addTab(bundle.getString("tab_tmtypes_label"), tmTypesPanel); // NOI18N

        javax.swing.GroupLayout tcTypesPanelLayout = new javax.swing.GroupLayout(tcTypesPanel);
        tcTypesPanel.setLayout(tcTypesPanelLayout);
        tcTypesPanelLayout.setHorizontalGroup(
            tcTypesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1003, Short.MAX_VALUE)
        );
        tcTypesPanelLayout.setVerticalGroup(
            tcTypesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 532, Short.MAX_VALUE)
        );

        mainWindowPrimaryWorkspace.addTab(bundle.getString("tab_tctypes_label"), tcTypesPanel); // NOI18N

        mainWindowMessageScrollingPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        messagesText.setEditable(false);
        messagesText.setColumns(20);
        messagesText.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        messagesText.setRows(5);
        messagesText.setText(bundle.getString("no_messages_text")); // NOI18N
        mainWindowMessageScrollingPane.setViewportView(messagesText);

        messagesOutputLabel.setText(bundle.getString("messages_label")); // NOI18N

        mainWindowFileMenu.setText(bundle.getString("file_menu_label")); // NOI18N

        mainWindowOpenFileMenuItem.setText(bundle.getString("file_menu_open_database_label")); // NOI18N
        mainWindowOpenFileMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mainWindowOpenFileMenuItemActionPerformed(evt);
            }
        });
        mainWindowFileMenu.add(mainWindowOpenFileMenuItem);

        mainWindowOpenRecentMenu.setText(bundle.getString("file_menu_open_recent_database_label")); // NOI18N
        mainWindowFileMenu.add(mainWindowOpenRecentMenu);

        mainWindowOpenExampleMenu.setText(bundle.getString("file_menu_open_example_label")); // NOI18N
        mainWindowFileMenu.add(mainWindowOpenExampleMenu);

        mainWindowSaveFileMenuItem.setText(bundle.getString("file_menu_save_database_label")); // NOI18N
        mainWindowSaveFileMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mainWindowSaveFileMenuItemActionPerformed(evt);
            }
        });
        mainWindowFileMenu.add(mainWindowSaveFileMenuItem);

        mainWindowCloseFileMenuItem.setText(bundle.getString("file_menu_close_database_label")); // NOI18N
        mainWindowCloseFileMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mainWindowCloseFileMenuItemActionPerformed(evt);
            }
        });
        mainWindowFileMenu.add(mainWindowCloseFileMenuItem);

        mainWindowCreateFileMenuItem.setText(bundle.getString("file_menu_create_database_label")); // NOI18N
        mainWindowCreateFileMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mainWindowCreateFileMenuItemActionPerformed(evt);
            }
        });
        mainWindowFileMenu.add(mainWindowCreateFileMenuItem);

        mainWindowCompressFileMenuItem.setText(bundle.getString("file_menu_compress_database_label")); // NOI18N
        mainWindowCompressFileMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mainWindowCompressFileMenuItemActionPerformed(evt);
            }
        });
        mainWindowFileMenu.add(mainWindowCompressFileMenuItem);

        mainWindowUpgradeFileMenuItem.setText(bundle.getString("file_menu_upgrade_database_label")); // NOI18N
        mainWindowUpgradeFileMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mainWindowUpgradeFileMenuItemActionPerformed(evt);
            }
        });
        mainWindowFileMenu.add(mainWindowUpgradeFileMenuItem);

        mainWindowExitMenuItem.setText(bundle.getString("file_menu_exit_label")); // NOI18N
        mainWindowExitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mainWindowExitMenuItemActionPerformed(evt);
            }
        });
        mainWindowFileMenu.add(mainWindowExitMenuItem);

        mainWindowMenuBar.add(mainWindowFileMenu);

        mainWindowEditMenu.setText(bundle.getString("edit_menu_label")); // NOI18N

        mainWindowClearMessagesMenuItem.setText(bundle.getString("edit_menu_clear_messages_label")); // NOI18N
        mainWindowClearMessagesMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mainWindowClearMessagesMenuItemActionPerformed(evt);
            }
        });
        mainWindowEditMenu.add(mainWindowClearMessagesMenuItem);

        mainWindowMenuBar.add(mainWindowEditMenu);

        mainWindowFindMenu.setText(bundle.getString("find_menu_label")); // NOI18N

        mainWindowFindSpaceSystemMenuItem.setText(bundle.getString("find_menu_spacesystem_label")); // NOI18N
        mainWindowFindSpaceSystemMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mainWindowFindSpaceSystemMenuItemActionPerformed(evt);
            }
        });
        mainWindowFindMenu.add(mainWindowFindSpaceSystemMenuItem);

        mainWindowFindParameterMenuItem.setText(bundle.getString("find_menu_parameters_label")); // NOI18N
        mainWindowFindParameterMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mainWindowFindParameterMenuItemActionPerformed(evt);
            }
        });
        mainWindowFindMenu.add(mainWindowFindParameterMenuItem);

        mainWindowFindContainerMenuItem.setText(bundle.getString("find_menu_containers_label")); // NOI18N
        mainWindowFindContainerMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mainWindowFindContainerMenuItemActionPerformed(evt);
            }
        });
        mainWindowFindMenu.add(mainWindowFindContainerMenuItem);

        mainWindowFindTelecommandMenuItem.setText(bundle.getString("general_telecommand")); // NOI18N
        mainWindowFindTelecommandMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mainWindowFindTelecommandMenuItemActionPerformed(evt);
            }
        });
        mainWindowFindMenu.add(mainWindowFindTelecommandMenuItem);

        mainWindowFindByXPathMenuItem.setText(bundle.getString("find_menu_xpath_label")); // NOI18N
        mainWindowFindByXPathMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mainWindowFindByXPathMenuItemActionPerformed(evt);
            }
        });
        mainWindowFindMenu.add(mainWindowFindByXPathMenuItem);

        mainWindowMenuBar.add(mainWindowFindMenu);

        mainWindowShowMenu.setText(bundle.getString("show_menu_label")); // NOI18N

        mainWindowExpandAllSpaceSystemTreeMenuItem.setText(bundle.getString("show_menu_expand_label")); // NOI18N
        mainWindowExpandAllSpaceSystemTreeMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mainWindowExpandAllSpaceSystemTreeMenuItemActionPerformed(evt);
            }
        });
        mainWindowShowMenu.add(mainWindowExpandAllSpaceSystemTreeMenuItem);

        mainWindowExpandContainerTreeMenuItem.setText(bundle.getString("show_menu_expand_container_tree_label")); // NOI18N
        mainWindowExpandContainerTreeMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mainWindowExpandContainerTreeMenuItemActionPerformed(evt);
            }
        });
        mainWindowShowMenu.add(mainWindowExpandContainerTreeMenuItem);

        mainWindowMessagesDialogMenuItem.setText(bundle.getString("show_menu_messages_label")); // NOI18N
        mainWindowMessagesDialogMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mainWindowMessagesDialogMenuItemActionPerformed(evt);
            }
        });
        mainWindowShowMenu.add(mainWindowMessagesDialogMenuItem);

        mainWindowShowMetricsMenuItem.setText(bundle.getString("show_menu_metrics_label")); // NOI18N
        mainWindowShowMetricsMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mainWindowShowMetricsMenuItemActionPerformed(evt);
            }
        });
        mainWindowShowMenu.add(mainWindowShowMetricsMenuItem);

        mainWindowMenuBar.add(mainWindowShowMenu);

        mainWindowExportMenu.setText(bundle.getString("export_menu_label")); // NOI18N

        mainWindowExportParametersMenuItem.setText(bundle.getString("export_menu_parameters_label")); // NOI18N
        mainWindowExportParametersMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mainWindowExportParametersMenuItemActionPerformed(evt);
            }
        });
        mainWindowExportMenu.add(mainWindowExportParametersMenuItem);

        mainWindowExportContainersMenuItem.setText(bundle.getString("export_menu_containers_label")); // NOI18N
        mainWindowExportContainersMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mainWindowExportContainersMenuItemActionPerformed(evt);
            }
        });
        mainWindowExportMenu.add(mainWindowExportContainersMenuItem);

        mainWindowExportTelecommandsMenuItem.setText(bundle.getString("export_menu_telecommands_label")); // NOI18N
        mainWindowExportTelecommandsMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mainWindowExportTelecommandsMenuItemActionPerformed(evt);
            }
        });
        mainWindowExportMenu.add(mainWindowExportTelecommandsMenuItem);

        mainWindowMenuBar.add(mainWindowExportMenu);

        mainWindowOptionsMenu.setText(bundle.getString("options_menu_label")); // NOI18N

        mainWindowValidateOnLoadMenuItem.setSelected(prefs.getValidateOnLoadOption());
        mainWindowValidateOnLoadMenuItem.setText(bundle.getString("options_menu_validate_schema_label")); // NOI18N
        mainWindowValidateOnLoadMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mainWindowValidateOnLoadMenuItemActionPerformed(evt);
            }
        });
        mainWindowOptionsMenu.add(mainWindowValidateOnLoadMenuItem);

        mainWindowShowAliasNamespaceMenuItem.setSelected(prefs.getShowAliasNamespacesOption());
        mainWindowShowAliasNamespaceMenuItem.setText(bundle.getString("options_menu_show_alias_namespaces_label")); // NOI18N
        mainWindowShowAliasNamespaceMenuItem.setToolTipText("");
        mainWindowShowAliasNamespaceMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mainWindowShowAliasNamespaceMenuItemActionPerformed(evt);
            }
        });
        mainWindowOptionsMenu.add(mainWindowShowAliasNamespaceMenuItem);

        mainWindowPreferredNamespaceMenuItem.setText(bundle.getString("options_menu_set_preferred_namespace_label")); // NOI18N
        mainWindowPreferredNamespaceMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mainWindowPreferredNamespaceMenuItemActionPerformed(evt);
            }
        });
        mainWindowOptionsMenu.add(mainWindowPreferredNamespaceMenuItem);

        mainWindowRecentFilesMaxMenuItem.setText(bundle.getString("options_menu_set_recent_files_max_label")); // NOI18N
        mainWindowRecentFilesMaxMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mainWindowRecentFilesMaxMenuItemActionPerformed(evt);
            }
        });
        mainWindowOptionsMenu.add(mainWindowRecentFilesMaxMenuItem);

        mainWindowClearRecentFilesMenuItem.setText(bundle.getString("options_menu_clear_recent_files_label")); // NOI18N
        mainWindowClearRecentFilesMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mainWindowClearRecentFilesMenuItemActionPerformed(evt);
            }
        });
        mainWindowOptionsMenu.add(mainWindowClearRecentFilesMenuItem);

        mainWindowUseXincludeMenuItem.setSelected(prefs.getUseXIncludeOption());
        mainWindowUseXincludeMenuItem.setText(bundle.getString("file_chooser_xinclude_text")); // NOI18N
        mainWindowUseXincludeMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mainWindowUseXincludeMenuItemActionPerformed(evt);
            }
        });
        mainWindowOptionsMenu.add(mainWindowUseXincludeMenuItem);

        mainWindowLocaleMenuItem.setText(bundle.getString("options_menu_locale_label")); // NOI18N
        mainWindowLocaleMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mainWindowLocaleMenuItemActionPerformed(evt);
            }
        });
        mainWindowOptionsMenu.add(mainWindowLocaleMenuItem);

        mainWindowShowAllConditionalsMenuItem.setSelected(prefs.getShowAllContainerConditionalsOption());
        mainWindowShowAllConditionalsMenuItem.setText(bundle.getString("options_menu_container_conditionals_label")); // NOI18N
        mainWindowShowAllConditionalsMenuItem.setToolTipText("");
        mainWindowShowAllConditionalsMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mainWindowShowAllConditionalsMenuItemActionPerformed(evt);
            }
        });
        mainWindowOptionsMenu.add(mainWindowShowAllConditionalsMenuItem);

        containerDrawingOrientationMenu.setText(bundle.getString("options_menu_container_drawing_orient_label")); // NOI18N

        containerDrawingLeftToRight.setSelected(( prefs.getContainerOrientationOption().equals( "LEFT_TO_RIGHT" ) ));
        containerDrawingLeftToRight.setText(bundle.getString("options_menu_container_drawing_l2r_label")); // NOI18N
        containerDrawingLeftToRight.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                containerDrawingLeftToRightActionPerformed(evt);
            }
        });
        containerDrawingOrientationMenu.add(containerDrawingLeftToRight);

        containerDrawingTopToBottom.setSelected(( prefs.getContainerOrientationOption().equals( "TOP_TO_BOTTOM" ) ));
        containerDrawingTopToBottom.setText(bundle.getString("options_menu_container_drawing_t2b_label")); // NOI18N
        containerDrawingTopToBottom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                containerDrawingTopToBottomActionPerformed(evt);
            }
        });
        containerDrawingOrientationMenu.add(containerDrawingTopToBottom);

        mainWindowOptionsMenu.add(containerDrawingOrientationMenu);

        mainWindowEditDocumentMenuItem.setText(bundle.getString("options_menu_edit_document_label")); // NOI18N
        mainWindowEditDocumentMenuItem.setEnabled(false);
        mainWindowEditDocumentMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mainWindowEditDocumentMenuItemActionPerformed(evt);
            }
        });
        mainWindowOptionsMenu.add(mainWindowEditDocumentMenuItem);

        mainWindowMenuBar.add(mainWindowOptionsMenu);

        mainWindowHelpMenu.setText(bundle.getString("help_menu_label")); // NOI18N

        mainWindowHelpSchemaMenuItem.setText(bundle.getString("help_menu_schema")); // NOI18N
        mainWindowHelpSchemaMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mainWindowHelpSchemaMenuItemActionPerformed(evt);
            }
        });
        mainWindowHelpMenu.add(mainWindowHelpSchemaMenuItem);

        mainWindowHelpApiMenuItem.setText(bundle.getString("help_menu_toolkit")); // NOI18N
        mainWindowHelpApiMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mainWindowHelpApiMenuItemActionPerformed(evt);
            }
        });
        mainWindowHelpMenu.add(mainWindowHelpApiMenuItem);

        mainWindowHelpToolMenuItem.setText(bundle.getString("help_menu_tool")); // NOI18N
        mainWindowHelpToolMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mainWindowHelpToolMenuItemActionPerformed(evt);
            }
        });
        mainWindowHelpMenu.add(mainWindowHelpToolMenuItem);

        mainWindowHelpCurrentMenuItem.setText(bundle.getString("help_menu_current")); // NOI18N
        mainWindowHelpCurrentMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mainWindowHelpCurrentMenuItemActionPerformed(evt);
            }
        });
        mainWindowHelpMenu.add(mainWindowHelpCurrentMenuItem);

        mainWindowHelpAboutMenuItem.setText(bundle.getString("help_menu_about")); // NOI18N
        mainWindowHelpAboutMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mainWindowHelpAboutMenuItemActionPerformed(evt);
            }
        });
        mainWindowHelpMenu.add(mainWindowHelpAboutMenuItem);

        mainWindowMenuBar.add(mainWindowHelpMenu);

        setJMenuBar(mainWindowMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainWindowMessageScrollingPane)
            .addComponent(loadedFilenameLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(messagesOutputLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(loadedSchemaLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(mainWindowPrimaryWorkspace)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(loadedFilenameLabel)
                .addGap(2, 2, 2)
                .addComponent(loadedSchemaLabel)
                .addGap(1, 1, 1)
                .addComponent(mainWindowPrimaryWorkspace)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(messagesOutputLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mainWindowMessageScrollingPane, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        messagesOutputLabel.getAccessibleContext().setAccessibleName(bundle.getString("messages_label")); // NOI18N

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mainWindowOpenFileMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mainWindowOpenFileMenuItemActionPerformed

        XTCEViewerOpenFileChooser chs = new XTCEViewerOpenFileChooser( prefs );

        int status = chs.showOpenDialog( this );

        if (status == JFileChooser.APPROVE_OPTION) {
            boolean found = openFile( chs.getSelectedFile(),
                                      chs.isXIncludeSelected(),
                                      chs.isValidateSelected(),
                                      chs.isReadOnlySelected() );
            if ( found == true ) {
                prefs.updateRecentFilesList( mainWindowOpenRecentMenu,
                                             chs.getSelectedFile() );
            }
        }

    }//GEN-LAST:event_mainWindowOpenFileMenuItemActionPerformed

    private void mainWindowExitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mainWindowExitMenuItemActionPerformed

        dispatchEvent( new WindowEvent( this, WindowEvent.WINDOW_CLOSING ) );

    }//GEN-LAST:event_mainWindowExitMenuItemActionPerformed

    private void mainWindowCloseFileMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mainWindowCloseFileMenuItemActionPerformed

        if ( xtceDatabaseFile == null ) {

            JOptionPane.showMessageDialog( this,
                                           XTCEFunctions.getText( "dialog_nodatabasetoclose_text" ), // NOI18N
                                           XTCEFunctions.getText( "dialog_insignificanterror_title_text" ), // NOI18N
                                           JOptionPane.ERROR_MESSAGE);
            return;

        } else if ( xtceDatabaseFile.getChanged() == true ) {

            int response = JOptionPane.showConfirmDialog( this,
                                                          XTCEFunctions.getText( "dialog_fileisopenchanged_text" ) + "\n" + // NOI18N
                                                          XTCEFunctions.getText( "dialog_savefilequestion_text" ), // NOI18N
                                                          XTCEFunctions.getText( "dialog_closeconfirmation_text" ), // NOI18N
                                                          JOptionPane.YES_NO_OPTION );

            if ( response == JOptionPane.YES_OPTION ) {
                mainWindowSaveFileMenuItemActionPerformed( evt );
                if ( xtceDatabaseFile.getChanged() == true ) {
                    return;
                }
            }

        }

        if ( xpathDialog != null ) {
            xpathDialog.dispose();
            xpathDialog = null;
        }

        if ( findParameterDialog != null ) {
            findParameterDialog.dispose();
            findParameterDialog = null;
        }

        if ( parameterUsageDialog != null ) {
            parameterUsageDialog.dispose();
            parameterUsageDialog = null;
        }

        if ( findContainerDialog != null ) {
            findContainerDialog.dispose();
            findContainerDialog = null;
        }

        if ( findTelecommandDialog != null ) {
            findTelecommandDialog.dispose();
            findTelecommandDialog = null;
        }

        Window[] children = getOwnedWindows();
        for ( Window child : children ) {
            child.dispose();
        }
        
        xtceDatabaseFile = null;
        resetAllDisplays();
        detailSpaceSystemPanelScrollPane.setViewportView( null );

        mainWindowClearMessagesMenuItemActionPerformed( evt );
        loadedFilenameLabel.setText( XTCEFunctions.getText( "openfile_default_text_label" ) ); // NOI18N
        loadedSchemaLabel.setText( XTCEFunctions.getText( "openfile_default_schema_label" ) ); // NOI18N

        System.gc();

    }//GEN-LAST:event_mainWindowCloseFileMenuItemActionPerformed

    private void mainWindowClearMessagesMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mainWindowClearMessagesMenuItemActionPerformed

        messagesText.setText( XTCEFunctions.getText( "no_messages_text" ) ); // NOI18N

    }//GEN-LAST:event_mainWindowClearMessagesMenuItemActionPerformed

    private void mainWindowValidateOnLoadMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mainWindowValidateOnLoadMenuItemActionPerformed

        prefs.setValidateOnLoadOption( mainWindowValidateOnLoadMenuItem.getState() );

    }//GEN-LAST:event_mainWindowValidateOnLoadMenuItemActionPerformed

    private void detailSpaceSystemTreeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_detailSpaceSystemTreeMousePressed

        if (( SwingUtilities.isRightMouseButton( evt ) == true ) || 
                (System.getProperty("os.name").contains("Mac OS X") && 
                (evt.getButton() == java.awt.event.MouseEvent.BUTTON1) && 
                evt.isControlDown())) {
            if ( xtceDatabaseFile != null ) {
               spaceSystemDetailPopupMenu.show( detailSpaceSystemTree, evt.getX(), evt.getY() );
            }
        }

    }//GEN-LAST:event_detailSpaceSystemTreeMousePressed

    private void tmContainerTreeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tmContainerTreeMousePressed

        if (( SwingUtilities.isRightMouseButton( evt ) == true ) || 
                (System.getProperty("os.name").contains("Mac OS X") && 
                (evt.getButton() == java.awt.event.MouseEvent.BUTTON1) && 
                evt.isControlDown())) {
            containerTreePopupMenu.show( tmContainerTree,
                                           evt.getX(),
                                           evt.getY() );
        }

    }//GEN-LAST:event_tmContainerTreeMousePressed

    private void mainWindowSaveFileMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mainWindowSaveFileMenuItemActionPerformed

        if ( xtceDatabaseFile == null ) {
            JOptionPane.showMessageDialog( this,
                                           XTCEFunctions.getText( "dialog_nofileopentosave_text" ), // NOI18N
                                           XTCEFunctions.getText( "general_error" ), // NOI18N
                                           JOptionPane.ERROR_MESSAGE);
            return;
        }

        JFileChooser chooser = new JFileChooser( "." );
        FileFilter fileFilter =
            new FileNameExtensionFilter( XTCEFunctions.getText( "file_chooser_xtcexml_text" ), // NOI18N
                                         "xml" ); // NOI18N
        chooser.addChoosableFileFilter( fileFilter );
        chooser.setFileFilter( fileFilter );

        if ( xtceDatabaseFile.getFilename().getName().isEmpty() == false ) {
            chooser.setSelectedFile( xtceDatabaseFile.getFilename() );
        } else if ( prefs.getCurrentWorkingDirectory().isEmpty() == false ) {
            chooser.setCurrentDirectory( new File( prefs.getCurrentWorkingDirectory() ) );
        }

        int status = chooser.showSaveDialog( this );
        if (status == JFileChooser.APPROVE_OPTION) {

            File dbFile = chooser.getSelectedFile();
            if ( ( dbFile.getName().endsWith( ".xml" ) == false ) && // NOI18N
                 ( dbFile.getName().contains( "." )    == false ) ) { // NOI18N
                dbFile = new File ( dbFile.getAbsolutePath() + ".xml" ); // NOI18N
                while ( dbFile.exists() == true ) {
                    dbFile = new File( dbFile.getAbsolutePath() + "-new.xml" ); // NOI18N
                }
            }

            try {

                long startTime = System.currentTimeMillis();

                xtceDatabaseFile.save( dbFile );

                prefs.updateRecentFilesList( mainWindowOpenRecentMenu, dbFile );

                if ( dbFile.getParent() != null ) {
                    prefs.setCurrentWorkingDirectory( dbFile.getParent() );
                }

                long estimatedTime = System.currentTimeMillis() - startTime;

                logMsg( XTCEFunctions.getText( "file_chooser_save_time_text" ) +
                        " " +
                        Long.toString( estimatedTime / 1000 ) +
                        " " +
                        XTCEFunctions.getText( "file_chooser_load_time_unit_text" ) );

                loadedFilenameLabel.setText( dbFile.getAbsolutePath() );

            } catch ( XTCEDatabaseException ex ) {

                logMsg( XTCEFunctions.generalErrorPrefix() +
                        XTCEFunctions.getText( "file_save_error_message" ) + // NOI18N
                        " " +
                        dbFile.getAbsolutePath() );
                logMsg( XTCEFunctions.generalErrorPrefix() +
                        ex.getLocalizedMessage() );

            }

        }

    }//GEN-LAST:event_mainWindowSaveFileMenuItemActionPerformed

    private void mainWindowShowAliasNamespaceMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mainWindowShowAliasNamespaceMenuItemActionPerformed

        prefs.setShowAliasNamespacesOption( mainWindowShowAliasNamespaceMenuItem.getState() );

        drawTelemetryParameterTable();
        drawTelecommandParameterTable();
        drawTelecommandTree();

        XTCEViewerContainerTreeNode node =
            (XTCEViewerContainerTreeNode)tmContainerTree.getLastSelectedPathComponent();

        if ( node != null) {
            XTCEContainerContentModel model = node.getContentModel();
            if ( model != null ) {
                List<XTCEContainerEntryValue> values = model.getUserValues();
                drawContainerContentTable( values );
            } else {
                drawContainerContentTable( null );
            }
        }

    }//GEN-LAST:event_mainWindowShowAliasNamespaceMenuItemActionPerformed

    private void mainWindowPreferredNamespaceMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mainWindowPreferredNamespaceMenuItemActionPerformed

        XTCEViewerAliasNamespaceDialog dialog = new XTCEViewerAliasNamespaceDialog( this, true );
        dialog.setShowAll( prefs.getShowAllAliasNamespacesOption() );
        dialog.setPreferredNamespace( prefs.getPreferredAliasNamespaceOption() );
        dialog.setupFieldStates();
        dialog.setVisible( true );
        if ( dialog.getModified() == true ) {
            prefs.setShowAllAliasNamespacesOption( dialog.getShowAll() );
            prefs.setPreferredAliasNamespaceOption( dialog.getPreferredNamespace() );
            drawTelemetryParameterTable();
            drawTelecommandParameterTable();
            drawTelecommandTree();
            XTCEViewerContainerTreeNode node =
                (XTCEViewerContainerTreeNode)tmContainerTree.getLastSelectedPathComponent();
            if ( node != null) {
                XTCEContainerContentModel model = node.getContentModel();
                if ( model != null ) {
                    List<XTCEContainerEntryValue> values = model.getUserValues();
                    drawContainerContentTable( values );
                } else {
                    drawContainerContentTable( null );
                }
            }
        }

    }//GEN-LAST:event_mainWindowPreferredNamespaceMenuItemActionPerformed

    private void mainWindowCreateFileMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mainWindowCreateFileMenuItemActionPerformed

        // in the event that a file is already open, we should attempt to
        // close the file before creating a new database.

        if ( xtceDatabaseFile != null ) {
            mainWindowCloseFileMenuItemActionPerformed( evt );
        }

        // this extra test captures the case where a file is open and the user
        // chose to cancel the save and close, leaving the original file open.

        if ( xtceDatabaseFile != null ) {
            return;
        }

        // the following dialog allows the user to create a new SpaceSystem or
        // just cancel, and in that case is it "not accepted", so we just
        // return and do nothing.

        XTCEViewerCreateEditSpaceSystemDialog dialog =
            new XTCEViewerCreateEditSpaceSystemDialog( this, true, null );
        dialog.setVisible( true );
        if ( dialog.isAccepted() == false ) {
            return;
        }

        // Create the new minimum SpaceSystem element with an empty file as it
        // has not yet been saved.

        try {
            xtceDatabaseFile = new XTCEDatabase( dialog.getSpaceSystemName() );
            loadedFilenameLabel.setText( dialog.getSpaceSystemName() );
            loadedSchemaLabel.setText( XTCEFunctions.getText( "openfile_default_schema_label" ) ); // NOI18N
            XTCESpaceSystem ss = xtceDatabaseFile.getRootSpaceSystem();
            ss.setShortDescription( dialog.getShortDescription() );
            ss.setLongDescription( dialog.getLongDescription() );
            ss.setOperationalStatus( dialog.getOperationalStatus() );
            ss.setHeaderAttributes( dialog.getVersion(),
                                    dialog.getDate(),
                                    dialog.getClassification(),
                                    dialog.getClassificationInstructions(),
                                    dialog.getValidationStatus() );
            buildSpaceSystemTrees();
            logMsg( XTCEFunctions.getText( "file_create_message" ) + // NOI18N
                    dialog.getSpaceSystemName() );
        } catch ( XTCEDatabaseException ex ) {
            logMsg( XTCEFunctions.getText( "file_create_error_message" ) + // NOI18N
                    ex.getLocalizedMessage() );
        }

    }//GEN-LAST:event_mainWindowCreateFileMenuItemActionPerformed

    private void addSpaceSystemMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addSpaceSystemMenuItemActionPerformed

        XTCEViewerSpaceSystemTreeNode node =
            (XTCEViewerSpaceSystemTreeNode)detailSpaceSystemTree.getLastSelectedPathComponent();

        if ( node == null ) {
            logMsg( XTCEFunctions.generalErrorPrefix() +
                    XTCEFunctions.getText( "dialog_nospacesystemtoadd_text" )); // NOI18N
            return;
        }

        XTCEViewerCreateEditSpaceSystemDialog dialog =
            new XTCEViewerCreateEditSpaceSystemDialog( this, true, null );
        dialog.setVisible( true );

        if ( dialog.isAccepted() == true ) {

            try {
                String path = node.getFullPath() + "/" + dialog.getSpaceSystemName();
                xtceDatabaseFile.addSpaceSystem( dialog.getSpaceSystemName(),
                                                 node.getFullPath() );
                XTCESpaceSystem ss = xtceDatabaseFile.getSpaceSystem( path );
                ss.setShortDescription( dialog.getShortDescription() );
                ss.setLongDescription( dialog.getLongDescription() );
                ss.setOperationalStatus( dialog.getOperationalStatus() );
                ss.setHeaderAttributes( dialog.getVersion(),
                                        dialog.getDate(),
                                        dialog.getClassification(),
                                        dialog.getClassificationInstructions(),
                                        dialog.getValidationStatus() );
                resetAllDisplays();
                logMsg( XTCEFunctions.getText( "dialog_addedspacesystem_text" ) + // NOI18N
                        ": " + // NOI18N
                        node.getFullPath() +
                        "/" + // NOI18N
                        dialog.getSpaceSystemName() );
            } catch ( XTCEDatabaseException ex ) {
                logMsg( XTCEFunctions.generalErrorPrefix() +
                        XTCEFunctions.getText( "dialog_failedtoaddspacesystem_text" ) + // NOI18N
                        ": " + // NOI18N
                        ex.getLocalizedMessage() );
            }

        }

    }//GEN-LAST:event_addSpaceSystemMenuItemActionPerformed

    private void deleteSpaceSystemMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteSpaceSystemMenuItemActionPerformed

        XTCEViewerSpaceSystemTreeNode node =
            (XTCEViewerSpaceSystemTreeNode)detailSpaceSystemTree.getLastSelectedPathComponent();
        if ( node == null ) {
            logMsg( XTCEFunctions.generalErrorPrefix() +
                    XTCEFunctions.getText( "dialog_nospacesystemtodelete_text" ) ); // NOI18N
            return;
        }

        int response = JOptionPane.showConfirmDialog( this,
                                                      XTCEFunctions.getText( "dialog_confirmdelete_yoursure_text" ), // NOI18N
                                                      XTCEFunctions.getText( "dialog_confirmdelete_spacesystem_text" ), // NOI18N
                                                      JOptionPane.YES_NO_OPTION );
        if ( response == JOptionPane.YES_OPTION ) {
            try {
                xtceDatabaseFile.deleteSpaceSystem( node.getFullPath() );
                resetAllDisplays();
                logMsg( XTCEFunctions.getText( "spacesystem_remove_message" ) + // NOI18N
                        " " +
                        node.getFullPath() );
            } catch ( XTCEDatabaseException ex ) {
                logMsg( XTCEFunctions.generalErrorPrefix() +
                        XTCEFunctions.getText( "spacesystem_remove_error_message" ) + // NOI18N
                        " " + // NOI18N
                        ex.getLocalizedMessage() );
            }
        }

    }//GEN-LAST:event_deleteSpaceSystemMenuItemActionPerformed

    private void tmParametersTableMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tmParametersTableMousePressed

        if ( fileOpenWarning() == true ) return;

        XTCEViewerFunctions.showRightClickTableMenu( evt,
                                                     tmParametersTable,
                                                     parameterDetailPopupMenu );

    }//GEN-LAST:event_tmParametersTableMousePressed

    private void tcParametersTableMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tcParametersTableMousePressed

        if ( fileOpenWarning() == true ) return;

        XTCEViewerFunctions.showRightClickTableMenu( evt,
                                                     tcParametersTable,
                                                     parameterDetailPopupMenu );

    }//GEN-LAST:event_tcParametersTableMousePressed

    private void showXmlElementsMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showXmlElementsMenuItemActionPerformed

        int idx = mainWindowPrimaryWorkspace.getSelectedIndex();

        if ( idx == 1 ) { // telemetry parameters
            XTCEViewerSpaceSystemTreeNode node =
                (XTCEViewerSpaceSystemTreeNode)tmParameterSpaceSystemTree.getLastSelectedPathComponent();
            if ( node == null ) {
                logMsg( XTCEFunctions.generalErrorPrefix() +
                        XTCEFunctions.getText( "rightclick_xml_no_tm_spacesystem_error_message" ) ); // NOI18N
                return;
            }
            int row = tmParametersTable.getSelectedRow();
            if ( row != -1 ) {
                String pname = (String)tmParametersTable.getValueAt( row, 0 );
                XTCESpaceSystem ss = node.getSpaceSystemReference();
                try {
                    XTCEParameter parameter = ss.getTelemetryParameter( pname );
                    XTCEViewerParameterXmlDialog dialog = new XTCEViewerParameterXmlDialog( this, true, parameter );
                    dialog.setVisible( true );
                } catch ( XTCEDatabaseException ex ) {
                    logMsg( XTCEFunctions.generalErrorPrefix() + ex.getLocalizedMessage() );
                }
            }
        } else if ( idx == 2 ) { // telecommand parameters
            XTCEViewerSpaceSystemTreeNode node =
                (XTCEViewerSpaceSystemTreeNode)tcParameterSpaceSystemTree.getLastSelectedPathComponent();
            if ( node == null ) {
                logMsg( XTCEFunctions.generalErrorPrefix() +
                        XTCEFunctions.getText( "rightclick_xml_no_tc_spacesystem_error_message" ) ); // NOI18N
                return;
            }
            int row = tcParametersTable.getSelectedRow();
            if ( row != -1 ) {
                String pname = (String)tcParametersTable.getValueAt( row, 0 );
                XTCESpaceSystem ss = node.getSpaceSystemReference();
                try {
                    XTCEParameter parameter = ss.getTelecommandParameter( pname );
                    XTCEViewerParameterXmlDialog dialog = new XTCEViewerParameterXmlDialog( this, true, parameter );
                    dialog.setVisible( true );
                } catch ( XTCEDatabaseException ex ) {
                    logMsg( XTCEFunctions.generalErrorPrefix() + ex.getLocalizedMessage() );
                }
            }
        } else if ( idx == 3 ) {
            XTCEViewerContainerTreeNode node =
                (XTCEViewerContainerTreeNode)tmContainerTree.getLastSelectedPathComponent();
            if ( node == null ) {
                logMsg( XTCEFunctions.generalErrorPrefix() +
                        XTCEFunctions.getText( "rightclick_xml_no_tm_container_error_message" ) ); // NOI18N
                return;
            }
            int row = tmContainerTable.getSelectedRow();
            if ( row != -1 ) {
                try {
                    if ( node.getContentModel().getContentList().get( row ).getEntryType() == FieldType.CONTAINER ) {
                        XTCETMContainer container = node.getContentModel().getContentList().get( row ).getTelemetryContainer();
                        XTCEViewerContainerXmlDialog dialog = new XTCEViewerContainerXmlDialog( this, true, container );
                        dialog.setVisible( true );
                    } else if ( node.getContentModel().getContentList().get( row ).getEntryType() == FieldType.PARAMETER ) {
                        XTCEParameter parameter = node.getContentModel().getContentList().get( row ).getParameter();
                        XTCEViewerParameterXmlDialog dialog = new XTCEViewerParameterXmlDialog( this, true, parameter );
                        dialog.setVisible( true );
                    } else {
                        logMsg( XTCEFunctions.generalErrorPrefix() +
                                XTCEFunctions.getText( "rightclick_container_table_error_message" ) ); // NOI18N
                    }
                } catch ( XTCEDatabaseException ex ) {
                    logMsg( XTCEFunctions.generalErrorPrefix() + ex.getLocalizedMessage() );
                } catch ( NullPointerException ex ) {
                    JOptionPane.showMessageDialog( this,
                                                   XTCEFunctions.getText( "rightclick_container_table_null_error_message" ), // NOI18N
                                                   XTCEFunctions.getText( "general_error" ), // NOI18N
                                                   JOptionPane.ERROR_MESSAGE);
                }
            }
        }

    }//GEN-LAST:event_showXmlElementsMenuItemActionPerformed

    private void showParameterDetailsMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showParameterDetailsMenuItemActionPerformed

        int idx = mainWindowPrimaryWorkspace.getSelectedIndex();

        if ( idx == 1 ) { // telemetry parameters
            XTCEViewerSpaceSystemTreeNode node =
                (XTCEViewerSpaceSystemTreeNode)tmParameterSpaceSystemTree.getLastSelectedPathComponent();
            if ( node == null ) {
                logMsg( XTCEFunctions.generalErrorPrefix() +
                        XTCEFunctions.getText( "rightclick_xml_no_tm_spacesystem_error_message" ) ); // NOI18N
                return;
            }
            int row = tmParametersTable.getSelectedRow();
            if ( row != -1 ) {
                String pname = (String)tmParametersTable.getValueAt( row, 0 );
                XTCESpaceSystem ss = node.getSpaceSystemReference();
                try {
                    XTCEParameter parameter = ss.getTelemetryParameter( pname );
                    XTCEViewerParameterDetailDialog dialog = new XTCEViewerParameterDetailDialog( this, true, parameter );
                    dialog.setVisible( true );
                } catch ( XTCEDatabaseException ex ) {
                    logMsg( XTCEFunctions.generalErrorPrefix() + ex.getLocalizedMessage() );
                }
            }
        } else if ( idx == 2 ) { // telecommand parameters
            XTCEViewerSpaceSystemTreeNode node =
                (XTCEViewerSpaceSystemTreeNode)tcParameterSpaceSystemTree.getLastSelectedPathComponent();
            if ( node == null ) {
                logMsg( XTCEFunctions.generalErrorPrefix() +
                        XTCEFunctions.getText( "rightclick_xml_no_tc_spacesystem_error_message" ) ); // NOI18N
                return;
            }
            int row = tcParametersTable.getSelectedRow();
            if ( row != -1 ) {
                String pname = (String)tcParametersTable.getValueAt( row, 0 );
                XTCESpaceSystem ss = node.getSpaceSystemReference();
                try {
                    XTCEParameter parameter = ss.getTelecommandParameter( pname );
                    XTCEViewerParameterDetailDialog dialog = new XTCEViewerParameterDetailDialog( this, true, parameter );
                    dialog.setVisible( true );
                } catch ( XTCEDatabaseException ex ) {
                    logMsg( XTCEFunctions.generalErrorPrefix() + ex.getLocalizedMessage() );
                }
            }
        } else if ( idx == 3 ) {
            XTCEViewerContainerTreeNode node =
                (XTCEViewerContainerTreeNode)tmContainerTree.getLastSelectedPathComponent();
            if ( node == null ) {
                logMsg( XTCEFunctions.generalErrorPrefix() +
                        XTCEFunctions.getText( "rightclick_xml_no_tm_container_error_message" ) ); // NOI18N
                return;
            }
            int row = tmContainerTable.getSelectedRow();
            if ( row != -1 ) {
                try {
                    XTCEParameter parameter = node.getContentModel().getContentList().get( row ).getParameter();
                    XTCEViewerParameterDetailDialog dialog = new XTCEViewerParameterDetailDialog( this, true, parameter );
                    dialog.setVisible( true );
                } catch ( XTCEDatabaseException ex ) {
                    logMsg( XTCEFunctions.generalErrorPrefix() + ex.getLocalizedMessage() );
                } catch ( NullPointerException ex ) {
                    JOptionPane.showMessageDialog( this,
                                                   XTCEFunctions.getText( "rightclick_container_table_null_error_message" ), // NOI18N
                                                   XTCEFunctions.getText( "general_error" ), // NOI18N
                                                   JOptionPane.ERROR_MESSAGE);
                }
            }
        }

    }//GEN-LAST:event_showParameterDetailsMenuItemActionPerformed

    private void mainWindowFindSpaceSystemMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mainWindowFindSpaceSystemMenuItemActionPerformed

        if ( fileOpenWarning() == true ) return;

        String name = JOptionPane.showInputDialog( this,
                                                   XTCEFunctions.getText( "ss_name_text" ) ); // NOI18N
        if ( name == null ) {
            return;
        }

        JTree tree = getCurrentSpaceSystemTree();
        if ( tree == null ) {
            return;
        }

        if ( XTCEViewerFunctions.selectSpaceSystemFromTree( tree, name, true ) == true ) {
            return;
        }

        JOptionPane.showMessageDialog( this,
                                       XTCEFunctions.getText( "dialog_nolocatespacesystem_text" ) + ":\n" + name, // NOI18N
                                       XTCEFunctions.getText( "general_error" ), // NOI18N
                                       JOptionPane.ERROR_MESSAGE);

    }//GEN-LAST:event_mainWindowFindSpaceSystemMenuItemActionPerformed

    private void mainWindowFindParameterMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mainWindowFindParameterMenuItemActionPerformed

        if ( fileOpenWarning() == true ) return;

        if ( findParameterDialog == null ) {
            findParameterDialog =
                new XTCEViewerParameterFindDialog( this,
                                                   prefs,
                                                   xtceDatabaseFile );
        } else {
            findParameterDialog.setVisible( true );
            findParameterDialog.toFront();
        }

    }//GEN-LAST:event_mainWindowFindParameterMenuItemActionPerformed

    private void tmParameterSpaceSystemTreeValueChanged(javax.swing.event.TreeSelectionEvent evt) {//GEN-FIRST:event_tmParameterSpaceSystemTreeValueChanged

        drawTelemetryParameterTable();

    }//GEN-LAST:event_tmParameterSpaceSystemTreeValueChanged

    private void tcParameterSpaceSystemTreeValueChanged(javax.swing.event.TreeSelectionEvent evt) {//GEN-FIRST:event_tcParameterSpaceSystemTreeValueChanged

        drawTelecommandParameterTable();

    }//GEN-LAST:event_tcParameterSpaceSystemTreeValueChanged

    private void tmContainerTreeValueChanged(javax.swing.event.TreeSelectionEvent evt) {//GEN-FIRST:event_tmContainerTreeValueChanged

        drawContainerContentTable( null );

    }//GEN-LAST:event_tmContainerTreeValueChanged

    private void mainWindowExpandAllSpaceSystemTreeMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mainWindowExpandAllSpaceSystemTreeMenuItemActionPerformed

        XTCEViewerFunctions.expandAllTreeNodes( getCurrentSpaceSystemTree() );

    }//GEN-LAST:event_mainWindowExpandAllSpaceSystemTreeMenuItemActionPerformed

    private void mainWindowExportParametersMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mainWindowExportParametersMenuItemActionPerformed

        if ( fileOpenWarning() == true ) return;

        int value = JOptionPane.showConfirmDialog( this,
                                                   parameterExportPanel,
                                                   XTCEFunctions.getText( "dialog_exportparameters_text" ), // NOI18N
                                                   JOptionPane.OK_CANCEL_OPTION );
        if ( value == JOptionPane.CANCEL_OPTION ) {
            return;
        }

        String fileExtensionDescription = XTCEFunctions.getText( "file_chooser_csv_text" ); // NOI18N
        String fileExtensionPattern     = "csv"; // NOI18N
        if ( exportParametersCppRadioButton.isSelected() == true ) {
            fileExtensionDescription = XTCEFunctions.getText( "file_chooser_cpp_text" ); // NOI18N
            fileExtensionPattern     = "h"; // NOI18N
        } else if ( exportParametersCometRadioButton.isSelected() == true ) {
            fileExtensionDescription = XTCEFunctions.getText( "file_chooser_oscomet_text" ); // NOI18N
            fileExtensionPattern     = "def"; // NOI18N
        } else if ( exportParametersInControlRadioButton.isSelected() == true ) {
            fileExtensionDescription = XTCEFunctions.getText( "file_chooser_incontrol_text" ); // NOI18N
            fileExtensionPattern     = "xml"; // NOI18N
        }
        JFileChooser chooser = new JFileChooser( "." ); // NOI18N
        FileFilter fileFilter = new FileNameExtensionFilter( fileExtensionDescription,
                                                             fileExtensionPattern );
        chooser.addChoosableFileFilter( fileFilter );
        chooser.setFileFilter( fileFilter );
        int status = chooser.showSaveDialog( this );
        if (status == JFileChooser.APPROVE_OPTION) {
            File exportFile = chooser.getSelectedFile();
            if ( exportParametersCsvRadioButton.isSelected() == true ) {
                if ( exportFile.getName().endsWith( ".csv" ) == false ) { // NOI18N
                    exportFile = new File( exportFile.getAbsolutePath() + ".csv" ); // NOI18N
                }
            } else if ( exportParametersCppRadioButton.isSelected() == true ) {
                if ( exportFile.getName().endsWith( ".h" ) == false ) { // NOI18N
                    exportFile = new File( exportFile.getAbsolutePath() + ".h" ); // NOI18N
                }
            } else if ( exportParametersCometRadioButton.isSelected() == true ) {
                if ( exportFile.getName().endsWith( ".def" ) == false ) { // NOI18N
                    exportFile = new File( exportFile.getAbsolutePath() + ".def" ); // NOI18N
                }
            } else if ( exportParametersInControlRadioButton.isSelected() == true ) {
                if ( exportFile.getName().endsWith( ".xml" ) == false ) { // NOI18N
                    exportFile = new File( exportFile.getAbsolutePath() + ".xml" ); // NOI18N
                }
            }
            logMsg( XTCEFunctions.getText( "general_exporting" ) + // NOI18N
                    " " + // NOI18N
                    fileExtensionDescription +
                    " " + // NOI18N
                    XTCEFunctions.getText( "file_menu_label" ) + // NOI18N
                    " '" + // NOI18N
                    exportFile.getName() +
                    "'" ); // NOI18N
            Properties configProperties = new Properties();
            configProperties.setProperty( "file_extension_description", fileExtensionDescription ); // NOI18N
            configProperties.setProperty( "file_extension_pattern", fileExtensionPattern ); // NOI18N
            configProperties.setProperty( "use_header_row", ( exportParametersIncludeHeaderRowCheckbox.isSelected() == true ? "true" : "false" ) ); // NOI18N
            configProperties.setProperty( "use_namespaces", ( exportParametersUseNamespacesCheckbox.isSelected() == true ? "true" : "false" ) ); // NOI18N
            configProperties.setProperty( "show_all_alias_namespaces", ( prefs.getShowAllAliasNamespacesOption() == true ? "true" : "false" ) ); // NOI18N
            configProperties.setProperty( "show_alias_namespaces", ( prefs.getShowAliasNamespacesOption() == true ? "true" : "false" ) ); // NOI18N
            configProperties.setProperty( "preferred_alias_namespace", prefs.getPreferredAliasNamespaceOption() ); // NOI18N
            configProperties.setProperty( "show_all_conditions", ( prefs.getShowAllContainerConditionalsOption() == true ? "true" : "false" ) ); // NOI18N
            try {
                XTCEDatabaseExporter dbExport;
                if ( exportParametersCsvRadioButton.isSelected() == true ) {
                    dbExport = new XTCEDatabaseExporterCsv( xtceDatabaseFile,
                                                            configProperties,
                                                            Charset.forName( exportParametersCharSetComboBox.getSelectedItem().toString() ) );
                    dbExport.exportParameters( exportFile );
                } else if ( exportParametersCppRadioButton.isSelected() == true ) {
                    logMsg( XTCEFunctions.generalWarningPrefix() +
                            XTCEFunctions.getText( "dialog_export_notyetimplemented_text" ) + // NOI18N
                            " " + // NOI18N
                            fileExtensionDescription );
                } else if ( exportParametersCometRadioButton.isSelected() == true ) {
                    //dbExport = new XTCEDatabaseExporterOSComet( xtceDatabaseFile,
                    //                                            configProperties,
                    //                                            Charset.forName( exportParametersCharSetComboBox.getSelectedItem().toString() ) );
                    //dbExport.exportParameters( exportFile );
                    logMsg( XTCEFunctions.generalWarningPrefix() +
                            XTCEFunctions.getText( "dialog_export_notyetimplemented_text" ) + // NOI18N
                            " " + // NOI18N
                            fileExtensionDescription );
                } else if ( exportParametersInControlRadioButton.isSelected() == true ) {
                    logMsg( XTCEFunctions.generalWarningPrefix() +
                            XTCEFunctions.getText( "dialog_export_notyetimplemented_text" ) + // NOI18N
                            " " + // NOI18N
                            fileExtensionDescription );
                }
            } catch ( XTCEDatabaseException ex ) {
                logMsg( XTCEFunctions.generalErrorPrefix() +
                        XTCEFunctions.getText( "dialog_export_exporting_text" ) + // NOI18N
                        " " + // NOI18N
                        fileExtensionDescription +
                        ": " + // NOI18N
                        ex.getLocalizedMessage() );
            }
        }

    }//GEN-LAST:event_mainWindowExportParametersMenuItemActionPerformed

    private void mainWindowMessagesDialogMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mainWindowMessagesDialogMenuItemActionPerformed

        if ( fileOpenWarning() == true ) return;

        Object[] selectionValues = { XTCEFunctions.getText( "general_save_text" ), // NOI18N
                                     XTCEFunctions.getText( "general_dismiss_text" ) }; // NOI18N
        messagesDialogText.setText( messagesText.getText() );
        int value = JOptionPane.showOptionDialog( this,
                                                  messagesDialogPanel,
                                                  XTCEFunctions.getText( "dialog_messageslist_text" ), // NOI18N
                                                  JOptionPane.PLAIN_MESSAGE,
                                                  JOptionPane.DEFAULT_OPTION,
                                                  null,
                                                  selectionValues,
                                                  XTCEFunctions.getText( "general_dismiss_text" ) ); // NOI18N

        if ( value == 0 ) {

            JFileChooser chooser = new JFileChooser( "." ); // NOI18N
            FileFilter fileFilter =
                new FileNameExtensionFilter( XTCEFunctions.getText( "file_chooser_text_text" ), // NOI18N
                                             "txt" ); // NOI18N
            chooser.addChoosableFileFilter( fileFilter );
            chooser.setFileFilter( fileFilter );
            chooser.setSelectedFile( new File( "messages.txt" ) ); // NOI18N
            int status = chooser.showSaveDialog( this );
            if (status == JFileChooser.APPROVE_OPTION) {
                try {
                    FileOutputStream stream =
                        new FileOutputStream( chooser.getSelectedFile() );
                    stream.write( messagesDialogText.getText().getBytes() );
                } catch ( Exception ex ) {
                    logMsg( XTCEFunctions.generalErrorPrefix() +
                            XTCEFunctions.getText( "dialog_export_error_writing" ) + // NOI18N
                            " " + // NOI18N
                            chooser.getSelectedFile() );
                }
            }

        }      

    }//GEN-LAST:event_mainWindowMessagesDialogMenuItemActionPerformed

    private void tcDefinitionsSpaceSystemTreeValueChanged(javax.swing.event.TreeSelectionEvent evt) {//GEN-FIRST:event_tcDefinitionsSpaceSystemTreeValueChanged

        drawTelecommandTree();
        updateTelecommandTable( null );

    }//GEN-LAST:event_tcDefinitionsSpaceSystemTreeValueChanged

    private void tmContainerTableMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tmContainerTableMousePressed

        if ( fileOpenWarning() == true ) return;

        XTCEViewerFunctions.showRightClickTableMenu( evt,
                                                     tmContainerTable,
                                                     containerTablePopupMenu );

    }//GEN-LAST:event_tmContainerTableMousePressed

    private void showContainerXmlMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showContainerXmlMenuItemActionPerformed

        XTCEViewerContainerTreeNode node =
           (XTCEViewerContainerTreeNode)tmContainerTree.getLastSelectedPathComponent();

        if ( node == null ) {
            JOptionPane.showMessageDialog( this,
                                           XTCEFunctions.getText( "rightclick_container_noselection_message" ), // NOI18N
                                           XTCEFunctions.getText( "general_error" ), // NOI18N
                                           JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            XTCETMContainer container = node.getContainerReference();
            XTCEViewerContainerXmlDialog dialog = new XTCEViewerContainerXmlDialog( this, true, container );
            dialog.setVisible( true );
        } catch ( XTCEDatabaseException ex ) {
            logMsg( XTCEFunctions.generalErrorPrefix() + ex.getLocalizedMessage() );
        } catch ( NullPointerException ex ) {
            JOptionPane.showMessageDialog( this,
                                           XTCEFunctions.getText( "dialog_selectedrownocontainer_text" ), // NOI18N
                                           XTCEFunctions.getText( "general_error" ), // NOI18N
                                           JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_showContainerXmlMenuItemActionPerformed

    private void mainWindowRecentFilesMaxMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mainWindowRecentFilesMaxMenuItemActionPerformed

        String current  = Integer.toString(prefs.getRecentFilesMaxCountOption() );
        Object message  = XTCEFunctions.getText( "dialog_entermaxfiles_text" ); // NOI18N
        String response = (String)JOptionPane.showInputDialog( this,
                                                               message,
                                                               XTCEFunctions.getText( "dialog_recentfilesmax_text" ), // NOI18N
                                                               JOptionPane.INFORMATION_MESSAGE,
                                                               null,
                                                               null,
                                                               current );
        if ( ( response != null ) && ( response.equals( current ) == false ) ) {
            try {
                int value = Integer.parseInt( response );
                if ( value >= 0 ) {
                    prefs.setRecentFilesMaxCountOption( value );
                } else {
                    throw new NumberFormatException( XTCEFunctions.getText( "dialog_nonegativenumber_text" ) ); // NOI18N
                }
            } catch ( NumberFormatException ex ) {
                JOptionPane.showMessageDialog( this,
                                               XTCEFunctions.getText( "dialog_invalidinteger_text" ), // NOI18N
                                               XTCEFunctions.getText( "general_error" ), // NOI18N
                                               JOptionPane.ERROR_MESSAGE);
            }
        }

    }//GEN-LAST:event_mainWindowRecentFilesMaxMenuItemActionPerformed

    private void mainWindowClearRecentFilesMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mainWindowClearRecentFilesMenuItemActionPerformed

        prefs.clearRecentFilesList( mainWindowOpenRecentMenu );

    }//GEN-LAST:event_mainWindowClearRecentFilesMenuItemActionPerformed

    private void mainWindowShowMetricsMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mainWindowShowMetricsMenuItemActionPerformed

        if ( fileOpenWarning() == true ) return;

        XTCEViewerDatabaseMetricsDialog dialog =
            new XTCEViewerDatabaseMetricsDialog( this,
                                                 false,
                                                 xtceDatabaseFile );

        dialog.setVisible( true );

    }//GEN-LAST:event_mainWindowShowMetricsMenuItemActionPerformed

    private void mainWindowLocaleMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mainWindowLocaleMenuItemActionPerformed

        Locale currentLocale = prefs.getLanguagePreference();

        Locale[] locales = { new Locale( "en", "US" ),   // NOI18N - English
                             new Locale( "fr", "FR" ),   // NOI18N - French
                             new Locale( "tr", "TR" ) }; // NOI18N - Turkish

        List<Locale> xtceViewerLocales = new ArrayList<>();

        for ( int iii = 0; iii < locales.length; ++iii ) {
            if ( XTCEFunctions.checkLocaleAvailable( locales[iii] ) == true ) {
                xtceViewerLocales.add( locales[iii] );
            }
        }

        Object[] options = new Object[xtceViewerLocales.size()];

        for ( int iii = 0; iii < xtceViewerLocales.size(); ++iii ) {
            options[iii] =
                xtceViewerLocales.get( iii ).getDisplayName( currentLocale );
        }

        Object selected = JOptionPane.showInputDialog( this,
                                                       XTCEFunctions.getText( "dialog_chooselocale_text" ), // NOI18N
                                                       XTCEFunctions.getText( "dialog_intlpreference_text" ), // NOI18N
                                                       JOptionPane.INFORMATION_MESSAGE,
                                                       null,
                                                       options,
                                                       currentLocale.getDisplayName() );

        if ( selected == null ) {
            return;
        }

        for ( int iii = 0; iii < xtceViewerLocales.size(); ++iii ) {
            if ( xtceViewerLocales.get( iii ).getDisplayName( currentLocale ).equals(selected) == true ) {
                XTCEFunctions.setLocalePreference( xtceViewerLocales.get( iii ) );
                prefs.setLanguagePreference( xtceViewerLocales.get( iii ) );
                JOptionPane.showMessageDialog( this,
                                               XTCEFunctions.getText( "dialog_intlprefchange_text" ), // NOI18N
                                               XTCEFunctions.getText( "dialog_restartrequired_text" ), // NOI18N
                                               JOptionPane.WARNING_MESSAGE);
                return;
            }
        }

    }//GEN-LAST:event_mainWindowLocaleMenuItemActionPerformed

    private void tcTreeValueChanged(javax.swing.event.TreeSelectionEvent evt) {//GEN-FIRST:event_tcTreeValueChanged

        drawTelecommandContentTable( null );

    }//GEN-LAST:event_tcTreeValueChanged

    private void mainWindowShowAllConditionalsMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mainWindowShowAllConditionalsMenuItemActionPerformed

        prefs.setShowAllContainerConditionalsOption( mainWindowShowAllConditionalsMenuItem.getState() );

        XTCEViewerContainerTreeNode node =
            (XTCEViewerContainerTreeNode)tmContainerTree.getLastSelectedPathComponent();

        if ( node != null) {
            XTCEContainerContentModel model = node.getContentModel();
            if ( model != null ) {
                model.setShowAllConditionals( mainWindowShowAllConditionalsMenuItem.getState() );
                List<XTCEContainerEntryValue> values = model.getUserValues();
                drawContainerContentTable( values );
            } else {
                drawContainerContentTable( null );
            }
        }

        XTCEViewerTelecommandTreeNode node2 =
            (XTCEViewerTelecommandTreeNode)tcTree.getLastSelectedPathComponent();

        if ( node2 != null ) {
            XTCETelecommandContentModel model = node2.getContentModel();
            if ( model != null ) {
                model.setShowAllConditionals( mainWindowShowAllConditionalsMenuItem.getState() );
                List<XTCEContainerEntryValue> values = model.getUserValues();
                drawTelecommandContentTable( values );
            } else {
                drawTelecommandContentTable( null );
            }
        }

    }//GEN-LAST:event_mainWindowShowAllConditionalsMenuItemActionPerformed

    private void showItemXmlElementsMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showItemXmlElementsMenuItemActionPerformed

        XTCEViewerContainerTreeNode node =
            (XTCEViewerContainerTreeNode)tmContainerTree.getLastSelectedPathComponent();

        if ( node == null ) {
            logMsg( XTCEFunctions.generalErrorPrefix() +
                    XTCEFunctions.getText( "rightclick_xml_no_tm_container_error_message" ) ); // NOI18N
            return;
        }

        int row = tmContainerTable.getSelectedRow();
        if ( row == -1 ) {
            return;
        }

        try {
            if ( node.getContentModel().getContentList().get( row ).getEntryType() == FieldType.CONTAINER ) {
                XTCETMContainer container = node.getContentModel().getContentList().get( row ).getTelemetryContainer();
                JDialog dialog = new XTCEViewerContainerXmlDialog( this, true, container );
                dialog.setVisible( true );
            } else if ( node.getContentModel().getContentList().get( row ).getEntryType() == FieldType.PARAMETER ) {
                XTCEParameter parameter = node.getContentModel().getContentList().get( row ).getParameter();
                JDialog dialog = new XTCEViewerParameterXmlDialog( this, true, parameter );
                dialog.setVisible( true );
            } else {
                logMsg( XTCEFunctions.generalErrorPrefix() +
                        XTCEFunctions.getText( "rightclick_container_table_error_message" ) ); // NOI18N
            }
        } catch ( XTCEDatabaseException ex ) {
            logMsg( XTCEFunctions.generalErrorPrefix() + ex.getLocalizedMessage() );
        } catch ( NullPointerException ex ) {
            JOptionPane.showMessageDialog( this,
                                           XTCEFunctions.getText( "rightclick_container_table_null_error_message" ), // NOI18N
                                           XTCEFunctions.getText( "general_error" ), // NOI18N
                                           JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_showItemXmlElementsMenuItemActionPerformed

    private void goToEntryMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_goToEntryMenuItemActionPerformed

        XTCEViewerContainerTreeNode node =
            (XTCEViewerContainerTreeNode)tmContainerTree.getLastSelectedPathComponent();

        if ( node == null ) {
            logMsg( XTCEFunctions.generalErrorPrefix() +
                    XTCEFunctions.getText( "rightclick_xml_no_tm_container_error_message" ) ); // NOI18N
            return;
        }

        int row = tmContainerTable.getSelectedRow();
        if ( row == -1 ) {
            return;
        }

        try {
            if ( node.getContentModel().getContentList().get( row ).getEntryType() == FieldType.CONTAINER ) {
                String containerPath =
                    node.getContentModel().getContentList().get( row ).getTelemetryContainer().getInheritancePath();
                selectContainerFromTree( containerPath );
            } else if ( node.getContentModel().getContentList().get( row ).getEntryType() == FieldType.PARAMETER ) {
                String parameterPath =
                    node.getContentModel().getContentList().get( row ).getParameter().getSpaceSystemPath();
                String parameterName =
                    node.getContentModel().getContentList().get( row ).getParameter().getName();
                mainWindowPrimaryWorkspace.setSelectedIndex( 1 );
                XTCEViewerFunctions.selectSpaceSystemFromTree( tmParameterSpaceSystemTree,
                                                               parameterPath,
                                                               false );
                // this is for ARRAY support only
                if ( parameterName.contains( "[" ) == true ) {
                    parameterName = parameterName.replaceAll( "\\[[0-9]+\\]", "" ); 
                }
                selectParameterFromTable( tmParametersTable,
                                          parameterName,
                                          true );
            } else {
                logMsg( XTCEFunctions.generalErrorPrefix() +
                        XTCEFunctions.getText( "rightclick_container_table_error_message" ) ); // NOI18N
            }
        } catch ( NullPointerException ex ) {
            JOptionPane.showMessageDialog( this,
                                           XTCEFunctions.getText( "rightclick_container_table_null_error_message" ), // NOI18N
                                           XTCEFunctions.getText( "general_error" ), // NOI18N
                                           JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_goToEntryMenuItemActionPerformed

    private void setConditionTrueMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setConditionTrueMenuItemActionPerformed

        XTCEViewerContainerTreeNode node =
            (XTCEViewerContainerTreeNode)tmContainerTree.getLastSelectedPathComponent();
        if ( node == null ) {
            logMsg( XTCEFunctions.generalErrorPrefix() +
                    XTCEFunctions.getText( "rightclick_xml_no_tm_container_error_message" ) ); // NOI18N
            return;
        }

        int row = tmContainerTable.getSelectedRow();
        if ( row == -1 ) {
            return;
        }

        if ( node.getContentModel().getContentList().get( row ).getConditionList().isEmpty() == true ) {
            JOptionPane.showMessageDialog( this,
                                           XTCEFunctions.getText( "rightclick_container_table_noconditions_message" ), // NOI18N
                                           XTCEFunctions.getText( "general_error" ), // NOI18N
                                           JOptionPane.ERROR_MESSAGE);
        }

        List<XTCEContainerEntryValue> conditions =
            node.getContentModel().getContentList().get( row ).getConditionList();

        List<XTCEContainerEntryValue> valueList =
            node.getContentModel().getUserValues();

        for ( XTCEContainerEntryValue condition : conditions ) {
            boolean found = false;
            for ( XTCEContainerEntryValue value : valueList ) {
                if ( value.equals( condition ) == true ) {
                    found = true;
                }
                if ( value.getItemFullPath().equals( condition.getItemFullPath() ) == true ) {
                    valueList.remove( value );
                    break;
                }
            }
            if ( found == false ) {
                valueList.add( condition );
            }
        }

        drawContainerContentTable( valueList );

    }//GEN-LAST:event_setConditionTrueMenuItemActionPerformed

    private void containerDrawingLeftToRightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_containerDrawingLeftToRightActionPerformed

        prefs.setContainerOrientationOption( "LEFT_TO_RIGHT" ); // NOI18N
        containerDrawingLeftToRight.setSelected( true );
        containerDrawingTopToBottom.setSelected( false );

        if ( tmContainerDrawingScrollPane.getViewport() != null ) {
            XTCEViewerContainerDrawing drawing = (XTCEViewerContainerDrawing)
                tmContainerDrawingScrollPane.getViewport().getView();
            if ( drawing != null ) {
                drawing.reOrient( Orientation.LEFT_TO_RIGHT );
                drawing.repaint();
            }
        }

        if ( tcContentDrawingScrollPane.getViewport() != null ) {
            XTCEViewerContainerDrawing drawing = (XTCEViewerContainerDrawing)
                tcContentDrawingScrollPane.getViewport().getView();
            if ( drawing != null ) {
                drawing.reOrient( Orientation.LEFT_TO_RIGHT );
                drawing.repaint();
            }
        }

    }//GEN-LAST:event_containerDrawingLeftToRightActionPerformed

    private void containerDrawingTopToBottomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_containerDrawingTopToBottomActionPerformed

        prefs.setContainerOrientationOption( "TOP_TO_BOTTOM" ); // NOI18N
        containerDrawingLeftToRight.setSelected( false );
        containerDrawingTopToBottom.setSelected( true );

        if ( tmContainerDrawingScrollPane.getViewport() != null ) {
            XTCEViewerContainerDrawing drawing = (XTCEViewerContainerDrawing)
                tmContainerDrawingScrollPane.getViewport().getView();
            if ( drawing != null ) {
                drawing.reOrient( Orientation.TOP_TO_BOTTOM );
                drawing.repaint();
            }
        }

        if ( tcContentDrawingScrollPane.getViewport() != null ) {
            XTCEViewerContainerDrawing drawing = (XTCEViewerContainerDrawing)
                tcContentDrawingScrollPane.getViewport().getView();
            if ( drawing != null ) {
                drawing.reOrient( Orientation.TOP_TO_BOTTOM );
                drawing.repaint();
            }
        }

    }//GEN-LAST:event_containerDrawingTopToBottomActionPerformed

    private void tmContainerDrawingScrollPaneMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tmContainerDrawingScrollPaneMousePressed

        if (( SwingUtilities.isRightMouseButton( evt ) == true ) || 
                (System.getProperty("os.name").contains("Mac OS X") && 
                (evt.getButton() == java.awt.event.MouseEvent.BUTTON1) && 
                evt.isControlDown())) {
            if ( xtceDatabaseFile != null ) {
                containerDrawingPopupMenu.show( tmContainerDrawingScrollPane, evt.getX(), evt.getY() );
            }
        }

    }//GEN-LAST:event_tmContainerDrawingScrollPaneMousePressed

    private void saveContainerDrawingMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveContainerDrawingMenuItemActionPerformed

        if ( ( tmContainerDrawingScrollPane.getViewport().getView() == null ) ||
             ( tmContainerTree.getLastSelectedPathComponent()       == null ) ) {
            JOptionPane.showMessageDialog( this,
                                           XTCEFunctions.getText( "rightclick_container_noselection_message" ), // NOI18N
                                           XTCEFunctions.getText( "general_error" ), // NOI18N
                                           JOptionPane.ERROR_MESSAGE);
            return;
        }

        XTCEViewerContainerDrawing drawing = (XTCEViewerContainerDrawing)
            tmContainerDrawingScrollPane.getViewport().getView();

        XTCEViewerContainerTreeNode node =
           (XTCEViewerContainerTreeNode)tmContainerTree.getLastSelectedPathComponent();

        XTCENamedObject container = node.getContainerReference();

        JFileChooser chooser = new JFileChooser( "." ); // NOI18N

        FileFilter fileFilter =
            new FileNameExtensionFilter( "Portable Network Graphics (png)", "png" ); // NOI18N
        chooser.addChoosableFileFilter( fileFilter );
        chooser.addChoosableFileFilter( new FileNameExtensionFilter( "Windows Bitmap (bmp)", "bmp" ) ); // NOI18N
        chooser.addChoosableFileFilter( new FileNameExtensionFilter( "Joint Photographic Experts Group (jpg)", "jpg" ) ); // NOI18N
        chooser.addChoosableFileFilter( new FileNameExtensionFilter( "Joint Photographic Experts Group (jpeg)", "jpeg" ) ); // NOI18N
        chooser.addChoosableFileFilter( new FileNameExtensionFilter( "Graphics Interchange Format (gif)", "gif" ) ); // NOI18N
        chooser.setFileFilter( fileFilter );
        chooser.setSelectedFile( new File ( container.getName() + ".png" ) ); // NOI18N
        chooser.setCurrentDirectory( new File( prefs.getCurrentWorkingDirectory() ) );

        int status = chooser.showSaveDialog( this );
        if (status == JFileChooser.APPROVE_OPTION) {
            try {
                drawing.save( chooser.getSelectedFile()  );
            } catch ( Exception ex ) {
                JOptionPane.showMessageDialog( this,
                                               ex.getLocalizedMessage(),
                                               XTCEFunctions.getText( "general_error" ), // NOI18N
                                               JOptionPane.ERROR_MESSAGE);
            }
        }

    }//GEN-LAST:event_saveContainerDrawingMenuItemActionPerformed

    private void cloneContainerDrawingMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cloneContainerDrawingMenuItemActionPerformed

        if ( ( tmContainerDrawingScrollPane.getViewport().getView() == null ) ||
             ( tmContainerTree.getLastSelectedPathComponent()       == null ) ) {
            JOptionPane.showMessageDialog( this,
                                           XTCEFunctions.getText( "rightclick_container_noselection_message" ), // NOI18N
                                           XTCEFunctions.getText( "general_error" ), // NOI18N
                                           JOptionPane.ERROR_MESSAGE);
            return;
        }

        XTCEViewerContainerDrawing drawing = (XTCEViewerContainerDrawing)
            tmContainerDrawingScrollPane.getViewport().getView();

        XTCEViewerContainerDrawing newDrawing = drawing.deepCopy();

        XTCEViewerContainerTreeNode node =
           (XTCEViewerContainerTreeNode)tmContainerTree.getLastSelectedPathComponent();

        XTCENamedObject container = node.getContainerReference();

        final JDialog newWindow = new JDialog( this, false );
        newWindow.setTitle( container.getFullPath() );
        JScrollPane graphScrollPane = new JScrollPane();
        graphScrollPane.setViewportView( newDrawing );
        newWindow.getContentPane().add( graphScrollPane, BorderLayout.CENTER );
        JButton dismissButton =
            new JButton( XTCEFunctions.getText( "general_dismiss_text" ) ); // NOI18N
        dismissButton.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent evt ) {
                newWindow.dispose();
            }
        } );
        newWindow.getContentPane().add( dismissButton, BorderLayout.SOUTH );
        newWindow.pack();
        newWindow.setVisible( true );

    }//GEN-LAST:event_cloneContainerDrawingMenuItemActionPerformed

    private void mainWindowFindContainerMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mainWindowFindContainerMenuItemActionPerformed

        if ( fileOpenWarning() == true ) return;

        if ( findContainerDialog == null ) {
            findContainerDialog =
                new XTCEViewerContainerFindDialog( this,
                                                   prefs,
                                                   xtceDatabaseFile );
        } else {
            findContainerDialog.setVisible( true );
            findContainerDialog.toFront();
        }

    }//GEN-LAST:event_mainWindowFindContainerMenuItemActionPerformed

    private void mainWindowFindTelecommandMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mainWindowFindTelecommandMenuItemActionPerformed

        if ( fileOpenWarning() == true ) return;

        if ( findTelecommandDialog == null ) {
            findTelecommandDialog =
                new XTCEViewerTelecommandFindDialog( this,
                                                     prefs,
                                                     xtceDatabaseFile );
        } else {
            findTelecommandDialog.setVisible( true );
            findTelecommandDialog.toFront();
        }

    }//GEN-LAST:event_mainWindowFindTelecommandMenuItemActionPerformed

    private void detailSpaceSystemTreeValueChanged(javax.swing.event.TreeSelectionEvent evt) {//GEN-FIRST:event_detailSpaceSystemTreeValueChanged

        detailSpaceSystemPanelScrollPane.setViewportView( null );

        XTCEViewerSpaceSystemTreeNode node =
            (XTCEViewerSpaceSystemTreeNode)detailSpaceSystemTree.getLastSelectedPathComponent();
        if ( node == null ) {
            return;
        }

        XTCEViewerSpaceSystemDetails detailDataPanel =
            new XTCEViewerSpaceSystemDetails( node.getSpaceSystemReference(),
                                              detailSpaceSystemPanelScrollPane,
                                              mainWindowEditDocumentMenuItem.isSelected() );
        detailSpaceSystemPanelScrollPane.setViewportView( detailDataPanel );
        detailSpaceSystemPanelScrollPane.revalidate();
        detailSpaceSystemPanelScrollPane.repaint();

    }//GEN-LAST:event_detailSpaceSystemTreeValueChanged

    private void mainWindowEditDocumentMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mainWindowEditDocumentMenuItemActionPerformed

        boolean editEnabled = mainWindowEditDocumentMenuItem.isSelected();

        mainWindowCreateFileMenuItem.setEnabled( editEnabled );
        addSpaceSystemMenuItem.setEnabled( editEnabled );
        deleteSpaceSystemMenuItem.setEnabled( editEnabled );

        XTCEViewerSpaceSystemDetails detailDataPanel = (XTCEViewerSpaceSystemDetails)
            detailSpaceSystemPanelScrollPane.getViewport().getView();
        if ( detailDataPanel != null ) {
            detailDataPanel.setEditable( editEnabled );
        }

    }//GEN-LAST:event_mainWindowEditDocumentMenuItemActionPerformed

    private void showEncodeDecodeDialogMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showEncodeDecodeDialogMenuItemActionPerformed

        int idx = mainWindowPrimaryWorkspace.getSelectedIndex();
        int row;
        if ( idx == 1 ) { // telemetry parameters
            XTCEViewerSpaceSystemTreeNode node =
                (XTCEViewerSpaceSystemTreeNode)tmParameterSpaceSystemTree.getLastSelectedPathComponent();
            if ( node == null ) {
                logMsg( XTCEFunctions.generalErrorPrefix() +
                        XTCEFunctions.getText( "rightclick_xml_no_tm_spacesystem_error_message" ) ); // NOI18N
                return;
            }
            row = tmParametersTable.getSelectedRow();
            if ( row != -1 ) {
                String pname = (String)tmParametersTable.getValueAt( row, 0 );
                String alias = (String)tmParametersTable.getValueAt( row, 1 );
                XTCESpaceSystem ss = node.getSpaceSystemReference();
                try {
                    XTCEParameter parameter = ss.getTelemetryParameter( pname );
                    XTCEViewerEncodeDecodeItemDialog dialog =
                        new XTCEViewerEncodeDecodeItemDialog( this, false, parameter, alias );
                } catch ( XTCEDatabaseException ex ) {
                    logMsg( XTCEFunctions.generalErrorPrefix() + ex.getLocalizedMessage() );
                }
            }
        } else if ( idx == 2 ) { // telecommand parameters
            XTCEViewerSpaceSystemTreeNode node =
                (XTCEViewerSpaceSystemTreeNode)tcParameterSpaceSystemTree.getLastSelectedPathComponent();
            if ( node == null ) {
                logMsg( XTCEFunctions.generalErrorPrefix() +
                        XTCEFunctions.getText( "rightclick_xml_no_tc_spacesystem_error_message" ) ); // NOI18N
                return;
            }
            row = tcParametersTable.getSelectedRow();
            if ( row != -1 ) {
                String pname = (String)tcParametersTable.getValueAt( row, 0 );
                String alias = (String)tcParametersTable.getValueAt( row, 1 );
                XTCESpaceSystem ss = node.getSpaceSystemReference();
                try {
                    XTCEParameter parameter = ss.getTelecommandParameter( pname );
                    XTCEViewerEncodeDecodeItemDialog dialog =
                        new XTCEViewerEncodeDecodeItemDialog( this,
                                                              false,
                                                              parameter,
                                                              alias );
                } catch ( XTCEDatabaseException ex ) {
                    logMsg( XTCEFunctions.generalErrorPrefix() + ex.getLocalizedMessage() );
                }
            }
        }

    }//GEN-LAST:event_showEncodeDecodeDialogMenuItemActionPerformed

    private void mainWindowExpandContainerTreeMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mainWindowExpandContainerTreeMenuItemActionPerformed

        XTCEViewerFunctions.expandAllTreeNodes( tmContainerTree );

    }//GEN-LAST:event_mainWindowExpandContainerTreeMenuItemActionPerformed

    private void mainWindowHelpSchemaMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mainWindowHelpSchemaMenuItemActionPerformed

        openHelp( "org/omg/space/xtce/schema/doc/SpaceSystemV1.2-27Feb2014-mods.html" ); // NOI18N

    }//GEN-LAST:event_mainWindowHelpSchemaMenuItemActionPerformed

    private void mainWindowHelpApiMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mainWindowHelpApiMenuItemActionPerformed

        openHelp( "apidocs/index.html" ); // NOI18

    }//GEN-LAST:event_mainWindowHelpApiMenuItemActionPerformed

    private void mainWindowHelpToolMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mainWindowHelpToolMenuItemActionPerformed

        logMsg( XTCEFunctions.generalWarningPrefix() +
                XTCEFunctions.getText( "general_not_implemented" ) ); // NOI18N

    }//GEN-LAST:event_mainWindowHelpToolMenuItemActionPerformed

    private void mainWindowHelpCurrentMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mainWindowHelpCurrentMenuItemActionPerformed

        logMsg( XTCEFunctions.generalWarningPrefix() +
                XTCEFunctions.getText( "general_not_implemented" ) ); // NOI18N

    }//GEN-LAST:event_mainWindowHelpCurrentMenuItemActionPerformed

    private void mainWindowHelpAboutMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mainWindowHelpAboutMenuItemActionPerformed

        XTCEViewerHelpMenuAboutDialog dialog =
            new XTCEViewerHelpMenuAboutDialog( this, true );

        dialog.setVisible( true );

    }//GEN-LAST:event_mainWindowHelpAboutMenuItemActionPerformed

    private void mainWindowUseXincludeMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mainWindowUseXincludeMenuItemActionPerformed

        prefs.setUseXIncludeOption( mainWindowUseXincludeMenuItem.isSelected() );

    }//GEN-LAST:event_mainWindowUseXincludeMenuItemActionPerformed

    private void mainWindowFindByXPathMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mainWindowFindByXPathMenuItemActionPerformed

        if ( fileOpenWarning() == true ) return;

        if ( xtceDatabaseFile.isReadOnly() == true ) {
            JOptionPane.showMessageDialog(
                this,
                XTCEFunctions.getText( "error_isreadonly" ), // NOI18N
                XTCEFunctions.getText( "general_error" ), // NOI18N
                JOptionPane.ERROR_MESSAGE );
            return;
        }

        if ( xpathDialog == null ) {
            xpathDialog = new XTCEViewerXpathQueryDialog( this,
                                                          prefs,
                                                          xtceDatabaseFile );
        } else {
            xpathDialog.setVisible( true );
            xpathDialog.toFront();
        }

    }//GEN-LAST:event_mainWindowFindByXPathMenuItemActionPerformed

    private void copyContainerCellMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_copyContainerCellMenuItemActionPerformed

        if ( xtceDatabaseFile == null ) return;

        XTCEViewerFunctions.copyCell( tmContainerTable );

    }//GEN-LAST:event_copyContainerCellMenuItemActionPerformed

    private void copyParameterCellMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_copyParameterCellMenuItemActionPerformed

        if ( xtceDatabaseFile == null ) return;

        int idx = mainWindowPrimaryWorkspace.getSelectedIndex();

        if ( idx == 1 ) {
            XTCEViewerFunctions.copyCell( tmParametersTable );
        } else if ( idx == 2 ) {
            XTCEViewerFunctions.copyCell( tcParametersTable );
        }

    }//GEN-LAST:event_copyParameterCellMenuItemActionPerformed

    private void tmStreamTreeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tmStreamTreeMousePressed

        if (( SwingUtilities.isRightMouseButton( evt ) == true ) || 
                (System.getProperty("os.name").contains("Mac OS X") && 
                (evt.getButton() == java.awt.event.MouseEvent.BUTTON1) && 
                evt.isControlDown())) {
            streamTreePopupMenu.show( tmStreamTree,
                                      evt.getX(),
                                      evt.getY() );
        }

    }//GEN-LAST:event_tmStreamTreeMousePressed

    private void tmStreamTreeValueChanged(javax.swing.event.TreeSelectionEvent evt) {//GEN-FIRST:event_tmStreamTreeValueChanged

        drawStreamContentTree();

    }//GEN-LAST:event_tmStreamTreeValueChanged

    private void tmStreamContentTreeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tmStreamContentTreeMouseClicked

        if ( ( evt.getClickCount() == 2 ) && ( xtceDatabaseFile != null ) ) {

            XTCEViewerContainerTreeNode selectedNode =
                (XTCEViewerContainerTreeNode)tmStreamContentTree.getLastSelectedPathComponent();

            if ( selectedNode == null ) {
                return;
            }

            XTCETMContainer container = selectedNode.getContainerReference();

            if ( container == null ) {
                return;
            }

            String completePath  = container.getFullPath();
            String containerName =
                XTCEFunctions.getNameFromPathReferenceString( completePath );
            String containerPath =
                XTCEFunctions.getPathNameFromReferenceString( completePath );

            goToContainer( containerName, containerPath );

        }

    }//GEN-LAST:event_tmStreamContentTreeMouseClicked

    private void showParameterUsageMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showParameterUsageMenuItemActionPerformed

        int idx = mainWindowPrimaryWorkspace.getSelectedIndex();
        int row;
        if ( idx == 1 ) { // telemetry parameters
            XTCEViewerSpaceSystemTreeNode node =
                (XTCEViewerSpaceSystemTreeNode)tmParameterSpaceSystemTree.getLastSelectedPathComponent();
            if ( node == null ) {
                logMsg( XTCEFunctions.generalErrorPrefix() +
                        XTCEFunctions.getText( "rightclick_xml_no_tm_spacesystem_error_message" ) ); // NOI18N
                return;
            }
            row = tmParametersTable.getSelectedRow();
            if ( row != -1 ) {
                String pname = (String)tmParametersTable.getValueAt( row, 0 );
                XTCESpaceSystem ss = node.getSpaceSystemReference();
                try {
                    XTCEParameter parameter = ss.getTelemetryParameter( pname );
                    if ( parameterUsageDialog != null ) {
                        parameterUsageDialog.setVisible( false );
                    }
                    parameterUsageDialog = 
                        new XTCEViewerParameterUsageDialog( this, xtceDatabaseFile, parameter );
                } catch ( XTCEDatabaseException ex ) {
                    logMsg( XTCEFunctions.generalErrorPrefix() + ex.getLocalizedMessage() );
                }
            }
        } else if ( idx == 2 ) { // telecommand parameters
            XTCEViewerSpaceSystemTreeNode node =
                (XTCEViewerSpaceSystemTreeNode)tcParameterSpaceSystemTree.getLastSelectedPathComponent();
            if ( node == null ) {
                logMsg( XTCEFunctions.generalErrorPrefix() +
                        XTCEFunctions.getText( "rightclick_xml_no_tc_spacesystem_error_message" ) ); // NOI18N
                return;
            }
            row = tcParametersTable.getSelectedRow();
            if ( row != -1 ) {
                String pname = (String)tcParametersTable.getValueAt( row, 0 );
                XTCESpaceSystem ss = node.getSpaceSystemReference();
                try {
                    XTCEParameter parameter = ss.getTelecommandParameter( pname );
                    if ( parameterUsageDialog != null ) {
                        parameterUsageDialog.setVisible( false );
                    }
                    parameterUsageDialog = 
                        new XTCEViewerParameterUsageDialog( this, xtceDatabaseFile, parameter );
                } catch ( XTCEDatabaseException ex ) {
                    logMsg( XTCEFunctions.generalErrorPrefix() + ex.getLocalizedMessage() );
                }
            }
        }

    }//GEN-LAST:event_showParameterUsageMenuItemActionPerformed

    private void copyContainerRowMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_copyContainerRowMenuItemActionPerformed

        if ( xtceDatabaseFile == null ) return;

        XTCEViewerFunctions.copyRow( tmContainerTable );

    }//GEN-LAST:event_copyContainerRowMenuItemActionPerformed

    private void copyContainerTableMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_copyContainerTableMenuItemActionPerformed

        if ( xtceDatabaseFile == null ) return;

        XTCEViewerFunctions.copyTable( tmContainerTable );

    }//GEN-LAST:event_copyContainerTableMenuItemActionPerformed

    private void copyParameterRowMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_copyParameterRowMenuItemActionPerformed

        if ( xtceDatabaseFile == null ) return;

        int idx = mainWindowPrimaryWorkspace.getSelectedIndex();

        if ( idx == 1 ) {
            XTCEViewerFunctions.copyRow( tmParametersTable );
        } else if ( idx == 2 ) {
            XTCEViewerFunctions.copyRow( tcParametersTable );
        }

    }//GEN-LAST:event_copyParameterRowMenuItemActionPerformed

    private void copyParameterTableMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_copyParameterTableMenuItemActionPerformed

        if ( xtceDatabaseFile == null ) return;

        int idx = mainWindowPrimaryWorkspace.getSelectedIndex();

        if ( idx == 1 ) {
            XTCEViewerFunctions.copyTable( tmParametersTable );
        } else if ( idx == 2 ) {
            XTCEViewerFunctions.copyTable( tcParametersTable );
        }

    }//GEN-LAST:event_copyParameterTableMenuItemActionPerformed

    private void mainWindowExportContainersMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mainWindowExportContainersMenuItemActionPerformed

        if ( fileOpenWarning() == true ) return;

        int value = JOptionPane.showConfirmDialog( this,
                                                   containerExportPanel,
                                                   XTCEFunctions.getText( "dialog_exportcontainers_text" ), // NOI18N
                                                   JOptionPane.OK_CANCEL_OPTION );
        if ( value == JOptionPane.CANCEL_OPTION ) {
            return;
        }

        String fileExtensionDescription = XTCEFunctions.getText( "file_chooser_csv_text" ); // NOI18N
        String fileExtensionPattern     = "csv"; // NOI18N
        if ( exportContainersCppRadioButton.isSelected() == true ) {
            fileExtensionDescription = XTCEFunctions.getText( "file_chooser_cpp_text" ); // NOI18N
            fileExtensionPattern     = "h"; // NOI18N
        } else if ( exportContainersCometRadioButton.isSelected() == true ) {
            fileExtensionDescription = XTCEFunctions.getText( "file_chooser_oscomet_text" ); // NOI18N
            fileExtensionPattern     = "def"; // NOI18N
        } else if ( exportContainersInControlRadioButton.isSelected() == true ) {
            fileExtensionDescription = XTCEFunctions.getText( "file_chooser_incontrol_text" ); // NOI18N
            fileExtensionPattern     = "xml"; // NOI18N
        }
        JFileChooser chooser = new JFileChooser( "." ); // NOI18N
        FileFilter fileFilter = new FileNameExtensionFilter( fileExtensionDescription,
                                                             fileExtensionPattern );
        chooser.addChoosableFileFilter( fileFilter );
        chooser.setFileFilter( fileFilter );
        int status = chooser.showSaveDialog( this );
        if (status == JFileChooser.APPROVE_OPTION) {
            File exportFile = chooser.getSelectedFile();
            if ( exportContainersCsvRadioButton.isSelected() == true ) {
                if ( exportFile.getName().endsWith( ".csv" ) == false ) { // NOI18N
                    exportFile = new File( exportFile.getAbsolutePath() + ".csv" ); // NOI18N
                }
            } else if ( exportContainersCppRadioButton.isSelected() == true ) {
                if ( exportFile.getName().endsWith( ".h" ) == false ) { // NOI18N
                    exportFile = new File( exportFile.getAbsolutePath() + ".h" ); // NOI18N
                }
            } else if ( exportContainersCometRadioButton.isSelected() == true ) {
                if ( exportFile.getName().endsWith( ".def" ) == false ) { // NOI18N
                    exportFile = new File( exportFile.getAbsolutePath() + ".def" ); // NOI18N
                }
            } else if ( exportContainersInControlRadioButton.isSelected() == true ) {
                if ( exportFile.getName().endsWith( ".xml" ) == false ) { // NOI18N
                    exportFile = new File( exportFile.getAbsolutePath() + ".xml" ); // NOI18N
                }
            }
            logMsg( XTCEFunctions.getText( "general_exporting" ) + // NOI18N
                    " " + // NOI18N
                    fileExtensionDescription +
                    " " + // NOI18N
                    XTCEFunctions.getText( "file_menu_label" ) + // NOI18N
                    " '" + // NOI18N
                    exportFile.getName() +
                    "'" ); // NOI18N
            Properties configProperties = new Properties();
            configProperties.setProperty( "file_extension_description", fileExtensionDescription ); // NOI18N
            configProperties.setProperty( "file_extension_pattern", fileExtensionPattern ); // NOI18N
            configProperties.setProperty( "use_header_row", ( exportContainersIncludeHeaderRowCheckbox.isSelected() == true ? "true" : "false" ) ); // NOI18N
            configProperties.setProperty( "use_namespaces", ( exportContainersUseNamespacesCheckbox.isSelected() == true ? "true" : "false" ) ); // NOI18N
            configProperties.setProperty( "show_all_alias_namespaces", ( prefs.getShowAllAliasNamespacesOption() == true ? "true" : "false" ) ); // NOI18N
            configProperties.setProperty( "show_alias_namespaces", ( prefs.getShowAliasNamespacesOption() == true ? "true" : "false" ) ); // NOI18N
            configProperties.setProperty( "preferred_alias_namespace", prefs.getPreferredAliasNamespaceOption() ); // NOI18N
            configProperties.setProperty( "show_all_conditions", ( prefs.getShowAllContainerConditionalsOption() == true ? "true" : "false" ) ); // NOI18N
            try {
                if ( exportContainersCsvRadioButton.isSelected() == true ) {
                    XTCEDatabaseExporter dbExport =
                        new XTCEDatabaseExporterCsv( xtceDatabaseFile,
                                                     configProperties,
                                                     Charset.forName( exportContainersCharSetComboBox.getSelectedItem().toString() ) );
                    List<String> msgs = dbExport.exportContainers( exportFile );
                    for ( String msg : msgs ) {
                        logMsg( XTCEFunctions.generalWarningPrefix() + msg );
                    }
                } else if ( exportContainersCppRadioButton.isSelected() == true ) {
                    logMsg( XTCEFunctions.generalWarningPrefix() +
                            XTCEFunctions.getText( "dialog_export_notyetimplemented_text" ) + // NOI18N
                            " " + // NOI18N
                            fileExtensionDescription );
                } else if ( exportContainersCometRadioButton.isSelected() == true ) {
                    logMsg( XTCEFunctions.generalWarningPrefix() +
                            XTCEFunctions.getText( "dialog_export_notyetimplemented_text" ) + // NOI18N
                            " " + // NOI18N
                            fileExtensionDescription );
                } else if ( exportContainersInControlRadioButton.isSelected() == true ) {
                    logMsg( XTCEFunctions.generalWarningPrefix() +
                            XTCEFunctions.getText( "dialog_export_notyetimplemented_text" ) + // NOI18N
                            " " + // NOI18N
                            fileExtensionDescription );
                }
            } catch ( XTCEDatabaseException ex ) {
                logMsg( XTCEFunctions.generalErrorPrefix() +
                        XTCEFunctions.getText( "dialog_export_exporting_text" ) + // NOI18N
                        " " + // NOI18N
                        fileExtensionDescription +
                        ": " + // NOI18N
                        ex.getLocalizedMessage() );
            }
        }

    }//GEN-LAST:event_mainWindowExportContainersMenuItemActionPerformed

    private void setRepeatCounterMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setRepeatCounterMenuItemActionPerformed

        XTCEViewerContainerTreeNode node =
            (XTCEViewerContainerTreeNode)tmContainerTree.getLastSelectedPathComponent();
        if ( node == null ) {
            logMsg( XTCEFunctions.generalErrorPrefix() +
                    XTCEFunctions.getText( "rightclick_xml_no_tm_container_error_message" ) ); // NOI18N
            return;
        }

        int row = tmContainerTable.getSelectedRow();
        if ( row == -1 ) {
            return;
        }

        if ( ( node.getContentModel().getContentList().get( row ).getRepeatParameterInfo().isEmpty()          == true ) ||
             ( node.getContentModel().getContentList().get( row ).getRepeatParameterInfo().startsWith( "==" ) == false ) ) {
            JOptionPane.showMessageDialog( this,
                                           XTCEFunctions.getText( "rightclick_container_table_nocounter_message" ), // NOI18N
                                           XTCEFunctions.getText( "general_error" ), // NOI18N
                                           JOptionPane.ERROR_MESSAGE);
        }

        // TODO do dialog here
/*
        String repeatCondition =
            node.getContentModel().getContentList().get( row ).getRepeatParameterInfo();

        List<XTCEContainerEntryValue> valueList =
            node.getContentModel().getUserValues();

        repeatCondition = repeatCondition.replaceFirst( "==", "" );
        String form = "Calibrated";
        if ( repeatCondition.endsWith( "{uncal}" ) == true ) {
            form = "Uncalibrated";
            repeatCondition = repeatCondition.replaceAll( "{uncal}", "" );
        } else {
            repeatCondition = repeatCondition.replaceAll( "{cal}", "" );
        }

        // need parameter

        boolean found = false;
        for ( XTCEContainerEntryValue value : valueList ) {
            if ( value.getName().equals( repeatCondition ) == true ) {
                found = true;
                value.setOperator( "==" );
                value.setComparisonForm( form );
                value.setValue( newValue );
            }
        }

        if ( found == false ) {
            XTCEContainerEntryValue value =
                new XTCEContainerEntryValue( parameter, newValue, "==", form );
            valueList.add( value );
        }

        drawContainerContentTable( valueList );
*/
    }//GEN-LAST:event_setRepeatCounterMenuItemActionPerformed

    private void decodeContainerMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_decodeContainerMenuItemActionPerformed

        XTCEViewerContainerTreeNode node =
           (XTCEViewerContainerTreeNode)tmContainerTree.getLastSelectedPathComponent();

        if ( node == null ) {
            JOptionPane.showMessageDialog( this,
                                           XTCEFunctions.getText( "rightclick_container_noselection_message" ), // NOI18N
                                           XTCEFunctions.getText( "general_error" ), // NOI18N
                                           JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            XTCETMContainer container = node.getContainerReference();
            XTCEViewerContainerContentDialog dialog =
                new XTCEViewerContainerContentDialog( this,
                                                      false,
                                                      container,
                                                      xtceDatabaseFile,
                                                      prefs );
            dialog.setVisible( true );
        } catch ( NullPointerException ex ) {
            JOptionPane.showMessageDialog( this,
                                           XTCEFunctions.getText( "dialog_selectedrownocontainer_text" ), // NOI18N
                                           XTCEFunctions.getText( "general_error" ), // NOI18N
                                           JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_decodeContainerMenuItemActionPerformed

    private void showStreamXmlMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showStreamXmlMenuItemActionPerformed

        XTCEViewerStreamTreeNode node =
           (XTCEViewerStreamTreeNode)tmStreamTree.getLastSelectedPathComponent();

        if ( node == null ) {
            JOptionPane.showMessageDialog( this,
                                           XTCEFunctions.getText( "rightclick_stream_noselection_message" ), // NOI18N
                                           XTCEFunctions.getText( "general_error" ), // NOI18N
                                           JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            XTCETMStream stream = node.getStreamReference();
            XTCEViewerContainerXmlDialog dialog =
                new XTCEViewerContainerXmlDialog( this, true, stream );
            dialog.setVisible( true );
        } catch ( XTCEDatabaseException ex ) {
            logMsg( XTCEFunctions.generalErrorPrefix() + ex.getLocalizedMessage() );
        } catch ( NullPointerException ex ) {
            JOptionPane.showMessageDialog( this,
                                           XTCEFunctions.getText( "dialog_selectedrownocontainer_text" ), // NOI18N
                                           XTCEFunctions.getText( "general_error" ), // NOI18N
                                           JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_showStreamXmlMenuItemActionPerformed

    private void decodeStreamMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_decodeStreamMenuItemActionPerformed

        XTCEViewerStreamTreeNode node =
           (XTCEViewerStreamTreeNode)tmStreamTree.getLastSelectedPathComponent();

        if ( node == null ) {
            JOptionPane.showMessageDialog( this,
                                           XTCEFunctions.getText( "rightclick_stream_noselection_message" ), // NOI18N
                                           XTCEFunctions.getText( "general_error" ), // NOI18N
                                           JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            XTCETMStream stream = node.getStreamReference();
            XTCEViewerContainerContentDialog dialog =
                new XTCEViewerContainerContentDialog( this,
                                                      false,
                                                      stream,
                                                      xtceDatabaseFile,
                                                      prefs );
            dialog.setVisible( true );
        } catch ( NullPointerException ex ) {
            JOptionPane.showMessageDialog( this,
                                           XTCEFunctions.getText( "dialog_selectedrownocontainer_text" ), // NOI18N
                                           XTCEFunctions.getText( "general_error" ), // NOI18N
                                           JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_decodeStreamMenuItemActionPerformed

    private void showContainerDrawingXmlMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showContainerDrawingXmlMenuItemActionPerformed

        showContainerXmlMenuItemActionPerformed( evt );

    }//GEN-LAST:event_showContainerDrawingXmlMenuItemActionPerformed

    private void decodeContainerDrawingMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_decodeContainerDrawingMenuItemActionPerformed

        decodeContainerMenuItemActionPerformed( evt );

    }//GEN-LAST:event_decodeContainerDrawingMenuItemActionPerformed

    private void copyParameterColumnMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_copyParameterColumnMenuItemActionPerformed

        if ( xtceDatabaseFile == null ) return;

        int idx = mainWindowPrimaryWorkspace.getSelectedIndex();

        if ( idx == 1 ) {
            XTCEViewerFunctions.copyColumn( tmParametersTable );
        } else if ( idx == 2 ) {
            XTCEViewerFunctions.copyColumn( tcParametersTable );
        }

    }//GEN-LAST:event_copyParameterColumnMenuItemActionPerformed

    private void copyContainerColumnMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_copyContainerColumnMenuItemActionPerformed

        if ( xtceDatabaseFile == null ) return;

        XTCEViewerFunctions.copyColumn( tmContainerTable );

    }//GEN-LAST:event_copyContainerColumnMenuItemActionPerformed

    private void showTelecommandXmlMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showTelecommandXmlMenuItemActionPerformed

        XTCEViewerTelecommandTreeNode node =
           (XTCEViewerTelecommandTreeNode)tcTree.getLastSelectedPathComponent();

        if ( node == null ) {
            JOptionPane.showMessageDialog( this,
                                           XTCEFunctions.getText( "rightclick_telecommand_noselection_message" ), // NOI18N
                                           XTCEFunctions.getText( "general_error" ), // NOI18N
                                           JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            XTCETelecommand tc = node.getTelecommandReference();
            XTCEViewerContainerXmlDialog dialog =
                new XTCEViewerContainerXmlDialog( this, true, tc );
            dialog.setVisible( true );
        } catch ( XTCEDatabaseException ex ) {
            logMsg( XTCEFunctions.generalErrorPrefix() + ex.getLocalizedMessage() );
        } catch ( NullPointerException ex ) {
            JOptionPane.showMessageDialog( this,
                                           XTCEFunctions.getText( "dialog_selectedrownocontainer_text" ), // NOI18N
                                           XTCEFunctions.getText( "general_error" ), // NOI18N
                                           JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_showTelecommandXmlMenuItemActionPerformed

    private void tcTreeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tcTreeMousePressed

        if ( fileOpenWarning() == true ) return;

        if (( SwingUtilities.isRightMouseButton( evt ) == true ) || 
                (System.getProperty("os.name").contains("Mac OS X") && 
                (evt.getButton() == java.awt.event.MouseEvent.BUTTON1) && 
                evt.isControlDown())) {
            telecommandTreePopupMenu.show( tcTree, evt.getX(), evt.getY() );
        }

    }//GEN-LAST:event_tcTreeMousePressed

    private void tcContentDrawingScrollPaneMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tcContentDrawingScrollPaneMousePressed

        if (( SwingUtilities.isRightMouseButton( evt ) == true ) || 
                (System.getProperty("os.name").contains("Mac OS X") && 
                (evt.getButton() == java.awt.event.MouseEvent.BUTTON1) && 
                evt.isControlDown())) {
            if ( xtceDatabaseFile != null ) {
                telecommandDrawingPopupMenu.show( tcContentDrawingScrollPane, evt.getX(), evt.getY() );
            }
        }

    }//GEN-LAST:event_tcContentDrawingScrollPaneMousePressed

    private void saveTelecommandDrawingMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveTelecommandDrawingMenuItemActionPerformed

        if ( ( tcContentDrawingScrollPane.getViewport().getView() == null ) ||
             ( tcTree.getLastSelectedPathComponent()              == null ) ) {
            JOptionPane.showMessageDialog( this,
                                           XTCEFunctions.getText( "rightclick_telecommand_noselection_message" ), // NOI18N
                                           XTCEFunctions.getText( "general_error" ), // NOI18N
                                           JOptionPane.ERROR_MESSAGE);
            return;
        }

        XTCEViewerContainerDrawing drawing = (XTCEViewerContainerDrawing)
            tcContentDrawingScrollPane.getViewport().getView();

        XTCEViewerTelecommandTreeNode node =
           (XTCEViewerTelecommandTreeNode)tcTree.getLastSelectedPathComponent();

        XTCENamedObject container = node.getTelecommandReference();

        JFileChooser chooser = new JFileChooser( "." ); // NOI18N

        FileFilter fileFilter =
            new FileNameExtensionFilter( "Portable Network Graphics (png)", "png" ); // NOI18N
        chooser.addChoosableFileFilter( fileFilter );
        chooser.addChoosableFileFilter( new FileNameExtensionFilter( "Windows Bitmap (bmp)", "bmp" ) ); // NOI18N
        chooser.addChoosableFileFilter( new FileNameExtensionFilter( "Joint Photographic Experts Group (jpg)", "jpg" ) ); // NOI18N
        chooser.addChoosableFileFilter( new FileNameExtensionFilter( "Joint Photographic Experts Group (jpeg)", "jpeg" ) ); // NOI18N
        chooser.addChoosableFileFilter( new FileNameExtensionFilter( "Graphics Interchange Format (gif)", "gif" ) ); // NOI18N
        chooser.setFileFilter( fileFilter );
        chooser.setSelectedFile( new File ( container.getName() + ".png" ) ); // NOI18N
        chooser.setCurrentDirectory( new File( prefs.getCurrentWorkingDirectory() ) );

        int status = chooser.showSaveDialog( this );
        if (status == JFileChooser.APPROVE_OPTION) {
            try {
                drawing.save( chooser.getSelectedFile()  );
            } catch ( Exception ex ) {
                JOptionPane.showMessageDialog( this,
                                               ex.getLocalizedMessage(),
                                               XTCEFunctions.getText( "general_error" ), // NOI18N
                                               JOptionPane.ERROR_MESSAGE);
            }
        }

    }//GEN-LAST:event_saveTelecommandDrawingMenuItemActionPerformed

    private void cloneTelecommandDrawingMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cloneTelecommandDrawingMenuItemActionPerformed

        if ( ( tcContentDrawingScrollPane.getViewport().getView() == null ) ||
             ( tcTree.getLastSelectedPathComponent()              == null ) ) {
            JOptionPane.showMessageDialog( this,
                                           XTCEFunctions.getText( "rightclick_telecommand_noselection_message" ), // NOI18N
                                           XTCEFunctions.getText( "general_error" ), // NOI18N
                                           JOptionPane.ERROR_MESSAGE);
            return;
        }

        XTCEViewerContainerDrawing drawing = (XTCEViewerContainerDrawing)
            tcContentDrawingScrollPane.getViewport().getView();

        XTCEViewerContainerDrawing newDrawing = drawing.deepCopy();

        XTCEViewerTelecommandTreeNode node =
           (XTCEViewerTelecommandTreeNode)tcTree.getLastSelectedPathComponent();

        XTCENamedObject container = node.getTelecommandReference();

        final JDialog newWindow = new JDialog( this, false );
        newWindow.setTitle( container.getFullPath() );
        JScrollPane graphScrollPane = new JScrollPane();
        graphScrollPane.setViewportView( newDrawing );
        newWindow.getContentPane().add( graphScrollPane, BorderLayout.CENTER );
        JButton dismissButton =
            new JButton( XTCEFunctions.getText( "general_dismiss_text" ) ); // NOI18N
        dismissButton.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent evt ) {
                newWindow.dispose();
            }
        } );
        newWindow.getContentPane().add( dismissButton, BorderLayout.SOUTH );
        newWindow.pack();
        newWindow.setVisible( true );

    }//GEN-LAST:event_cloneTelecommandDrawingMenuItemActionPerformed

    private void showTelecommandDrawingXmlMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showTelecommandDrawingXmlMenuItemActionPerformed

        showTelecommandXmlMenuItemActionPerformed( evt );

    }//GEN-LAST:event_showTelecommandDrawingXmlMenuItemActionPerformed

    private void tcContentTableMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tcContentTableMousePressed

        if ( fileOpenWarning() == true ) return;

        XTCEViewerFunctions.showRightClickTableMenu( evt,
                                                     tcContentTable,
                                                     telecommandTablePopupMenu );

    }//GEN-LAST:event_tcContentTableMousePressed

    private void showTcItemXmlElementsMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showTcItemXmlElementsMenuItemActionPerformed

        XTCEViewerTelecommandTreeNode node =
            (XTCEViewerTelecommandTreeNode)tcTree.getLastSelectedPathComponent();

        if ( node == null ) {
            logMsg( XTCEFunctions.generalErrorPrefix() +
                    XTCEFunctions.getText( "rightclick_xml_no_tm_container_error_message" ) ); // NOI18N
            return;
        }

        int row = tcContentTable.getSelectedRow();
        if ( row == -1 ) {
            return;
        }

        // add a case for FixedValueEntry later

        try {
            if ( node.getContentModel().getContentList().get( row ).getEntryType() == FieldType.CONTAINER ) {
                XTCETCContainer container = node.getContentModel().getContentList().get( row ).getTelecommandContainer();
                JDialog dialog = new XTCEViewerContainerXmlDialog( this, true, container );
                dialog.setVisible( true );
            } else if ( node.getContentModel().getContentList().get( row ).getEntryType() == FieldType.PARAMETER ) {
                XTCEParameter parameter = node.getContentModel().getContentList().get( row ).getParameter();
                JDialog dialog = new XTCEViewerParameterXmlDialog( this, true, parameter );
                dialog.setVisible( true );
            } else if ( node.getContentModel().getContentList().get( row ).getEntryType() == FieldType.ARGUMENT ) {
                XTCEArgument argument = node.getContentModel().getContentList().get( row ).getArgument();
                JDialog dialog = new XTCEViewerArgumentXmlDialog( this, true, argument );
                dialog.setVisible( true );
            } else {
                logMsg( XTCEFunctions.generalErrorPrefix() +
                        XTCEFunctions.getText( "rightclick_container_table_error_message" ) ); // NOI18N
            }
        } catch ( XTCEDatabaseException ex ) {
            logMsg( XTCEFunctions.generalErrorPrefix() + ex.getLocalizedMessage() );
        } catch ( NullPointerException ex ) {
            JOptionPane.showMessageDialog( this,
                                           XTCEFunctions.getText( "rightclick_container_table_null_error_message" ), // NOI18N
                                           XTCEFunctions.getText( "general_error" ), // NOI18N
                                           JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_showTcItemXmlElementsMenuItemActionPerformed

    private void copyTcCellMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_copyTcCellMenuItemActionPerformed

        if ( xtceDatabaseFile == null ) return;

        XTCEViewerFunctions.copyCell( tcContentTable );

    }//GEN-LAST:event_copyTcCellMenuItemActionPerformed

    private void copyTcColumnMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_copyTcColumnMenuItemActionPerformed

        if ( xtceDatabaseFile == null ) return;

        XTCEViewerFunctions.copyColumn( tcContentTable );

    }//GEN-LAST:event_copyTcColumnMenuItemActionPerformed

    private void copyTcRowMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_copyTcRowMenuItemActionPerformed

        if ( xtceDatabaseFile == null ) return;

        XTCEViewerFunctions.copyRow( tcContentTable );

    }//GEN-LAST:event_copyTcRowMenuItemActionPerformed

    private void copyTcTableMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_copyTcTableMenuItemActionPerformed

        if ( xtceDatabaseFile == null ) return;

        XTCEViewerFunctions.copyTable( tcContentTable );

    }//GEN-LAST:event_copyTcTableMenuItemActionPerformed

    private void mainWindowExportTelecommandsMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mainWindowExportTelecommandsMenuItemActionPerformed

        if ( fileOpenWarning() == true ) return;

        int value = JOptionPane.showConfirmDialog( this,
                                                   telecommandExportPanel,
                                                   XTCEFunctions.getText( "dialog_exporttelecommands_text" ), // NOI18N
                                                   JOptionPane.OK_CANCEL_OPTION );
        if ( value == JOptionPane.CANCEL_OPTION ) {
            return;
        }

        String fileExtensionDescription = XTCEFunctions.getText( "file_chooser_csv_text" ); // NOI18N
        String fileExtensionPattern     = "csv"; // NOI18N
        if ( exportTelecommandsCppRadioButton.isSelected() == true ) {
            fileExtensionDescription = XTCEFunctions.getText( "file_chooser_cpp_text" ); // NOI18N
            fileExtensionPattern     = "h"; // NOI18N
        } else if ( exportTelecommandsCometRadioButton.isSelected() == true ) {
            fileExtensionDescription = XTCEFunctions.getText( "file_chooser_oscomet_text" ); // NOI18N
            fileExtensionPattern     = "def"; // NOI18N
        } else if ( exportTelecommandsInControlRadioButton.isSelected() == true ) {
            fileExtensionDescription = XTCEFunctions.getText( "file_chooser_incontrol_text" ); // NOI18N
            fileExtensionPattern     = "xml"; // NOI18N
        }
        JFileChooser chooser = new JFileChooser( "." ); // NOI18N
        FileFilter fileFilter = new FileNameExtensionFilter( fileExtensionDescription,
                                                             fileExtensionPattern );
        chooser.addChoosableFileFilter( fileFilter );
        chooser.setFileFilter( fileFilter );
        int status = chooser.showSaveDialog( this );
        if (status == JFileChooser.APPROVE_OPTION) {
            File exportFile = chooser.getSelectedFile();
            if ( exportTelecommandsCsvRadioButton.isSelected() == true ) {
                if ( exportFile.getName().endsWith( ".csv" ) == false ) { // NOI18N
                    exportFile = new File( exportFile.getAbsolutePath() + ".csv" ); // NOI18N
                }
            } else if ( exportTelecommandsCppRadioButton.isSelected() == true ) {
                if ( exportFile.getName().endsWith( ".h" ) == false ) { // NOI18N
                    exportFile = new File( exportFile.getAbsolutePath() + ".h" ); // NOI18N
                }
            } else if ( exportTelecommandsCometRadioButton.isSelected() == true ) {
                if ( exportFile.getName().endsWith( ".def" ) == false ) { // NOI18N
                    exportFile = new File( exportFile.getAbsolutePath() + ".def" ); // NOI18N
                }
            } else if ( exportTelecommandsInControlRadioButton.isSelected() == true ) {
                if ( exportFile.getName().endsWith( ".xml" ) == false ) { // NOI18N
                    exportFile = new File( exportFile.getAbsolutePath() + ".xml" ); // NOI18N
                }
            }
            logMsg( XTCEFunctions.getText( "general_exporting" ) + // NOI18N
                    " " + // NOI18N
                    fileExtensionDescription +
                    " " + // NOI18N
                    XTCEFunctions.getText( "file_menu_label" ) + // NOI18N
                    " '" + // NOI18N
                    exportFile.getName() +
                    "'" ); // NOI18N
            Properties configProperties = new Properties();
            configProperties.setProperty( "file_extension_description", fileExtensionDescription ); // NOI18N
            configProperties.setProperty( "file_extension_pattern", fileExtensionPattern ); // NOI18N
            configProperties.setProperty( "use_header_row", ( exportTelecommandsIncludeHeaderRowCheckbox.isSelected() == true ? "true" : "false" ) ); // NOI18N
            configProperties.setProperty( "use_namespaces", ( exportTelecommandsUseNamespacesCheckbox.isSelected() == true ? "true" : "false" ) ); // NOI18N
            configProperties.setProperty( "show_all_alias_namespaces", ( prefs.getShowAllAliasNamespacesOption() == true ? "true" : "false" ) ); // NOI18N
            configProperties.setProperty( "show_alias_namespaces", ( prefs.getShowAliasNamespacesOption() == true ? "true" : "false" ) ); // NOI18N
            configProperties.setProperty( "preferred_alias_namespace", prefs.getPreferredAliasNamespaceOption() ); // NOI18N
            configProperties.setProperty( "show_all_conditions", ( prefs.getShowAllContainerConditionalsOption() == true ? "true" : "false" ) ); // NOI18N
            try {
                if ( exportTelecommandsCsvRadioButton.isSelected() == true ) {
                    XTCEDatabaseExporter dbExport =
                        new XTCEDatabaseExporterCsv( xtceDatabaseFile,
                                                     configProperties,
                                                     Charset.forName( exportTelecommandsCharSetComboBox.getSelectedItem().toString() ) );
                    List<String> msgs = dbExport.exportTelecommands( exportFile );
                    for ( String msg : msgs ) {
                        logMsg( XTCEFunctions.generalWarningPrefix() + msg );
                    }
                } else if ( exportTelecommandsCppRadioButton.isSelected() == true ) {
                    logMsg( XTCEFunctions.generalWarningPrefix() +
                            XTCEFunctions.getText( "dialog_export_notyetimplemented_text" ) + // NOI18N
                            " " + // NOI18N
                            fileExtensionDescription );
                } else if ( exportTelecommandsCometRadioButton.isSelected() == true ) {
                    logMsg( XTCEFunctions.generalWarningPrefix() +
                            XTCEFunctions.getText( "dialog_export_notyetimplemented_text" ) + // NOI18N
                            " " + // NOI18N
                            fileExtensionDescription );
                } else if ( exportTelecommandsInControlRadioButton.isSelected() == true ) {
                    logMsg( XTCEFunctions.generalWarningPrefix() +
                            XTCEFunctions.getText( "dialog_export_notyetimplemented_text" ) + // NOI18N
                            " " + // NOI18N
                            fileExtensionDescription );
                }
            } catch ( XTCEDatabaseException ex ) {
                logMsg( XTCEFunctions.generalErrorPrefix() +
                        XTCEFunctions.getText( "dialog_export_exporting_text" ) + // NOI18N
                        " " + // NOI18N
                        fileExtensionDescription +
                        ": " + // NOI18N
                        ex.getLocalizedMessage() );
            }
        }

    }//GEN-LAST:event_mainWindowExportTelecommandsMenuItemActionPerformed

    private void encodeContainerDrawingMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_encodeContainerDrawingMenuItemActionPerformed

        encodeContainerMenuItemActionPerformed( evt );

    }//GEN-LAST:event_encodeContainerDrawingMenuItemActionPerformed

    private void encodeContainerMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_encodeContainerMenuItemActionPerformed

        XTCEViewerContainerTreeNode node =
           (XTCEViewerContainerTreeNode)tmContainerTree.getLastSelectedPathComponent();

        if ( node == null ) {
            JOptionPane.showMessageDialog( this,
                                           XTCEFunctions.getText( "rightclick_container_noselection_message" ), // NOI18N
                                           XTCEFunctions.getText( "general_error" ), // NOI18N
                                           JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            XTCETMContainer container = node.getContainerReference();
            XTCEViewerContainerEncodingDialog dialog =
                new XTCEViewerContainerEncodingDialog( this,
                                                       false,
                                                       container,
                                                       xtceDatabaseFile,
                                                       prefs );
            dialog.setVisible( true );
        } catch ( NullPointerException ex ) {
            JOptionPane.showMessageDialog( this,
                                           XTCEFunctions.getText( "dialog_selectedrownocontainer_text" ), // NOI18N
                                           XTCEFunctions.getText( "general_error" ), // NOI18N
                                           JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_encodeContainerMenuItemActionPerformed

    private void decodeTelecommandMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_decodeTelecommandMenuItemActionPerformed

        XTCEViewerTelecommandTreeNode node =
           (XTCEViewerTelecommandTreeNode)tcTree.getLastSelectedPathComponent();

        if ( node == null ) {
            JOptionPane.showMessageDialog( this,
                                           XTCEFunctions.getText( "rightclick_telecommand_noselection_message" ), // NOI18N
                                           XTCEFunctions.getText( "general_error" ), // NOI18N
                                           JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            XTCETelecommand container = node.getTelecommandReference();
            XTCEViewerContainerContentDialog dialog =
                new XTCEViewerContainerContentDialog( this,
                                                      false,
                                                      container,
                                                      xtceDatabaseFile,
                                                      prefs );
            dialog.setVisible( true );
        } catch ( NullPointerException ex ) {
            JOptionPane.showMessageDialog( this,
                                           XTCEFunctions.getText( "dialog_selectedrownotelecommand_text" ), // NOI18N
                                           XTCEFunctions.getText( "general_error" ), // NOI18N
                                           JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_decodeTelecommandMenuItemActionPerformed

    private void encodeTelecommandMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_encodeTelecommandMenuItemActionPerformed

        XTCEViewerTelecommandTreeNode node =
           (XTCEViewerTelecommandTreeNode)tcTree.getLastSelectedPathComponent();

        if ( node == null ) {
            JOptionPane.showMessageDialog( this,
                                           XTCEFunctions.getText( "rightclick_telecommand_noselection_message" ), // NOI18N
                                           XTCEFunctions.getText( "general_error" ), // NOI18N
                                           JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            XTCETelecommand container = node.getTelecommandReference();
            XTCEViewerContainerEncodingDialog dialog =
                new XTCEViewerContainerEncodingDialog( this,
                                                       false,
                                                       container,
                                                       xtceDatabaseFile,
                                                       prefs );
            dialog.setVisible( true );
        } catch ( NullPointerException ex ) {
            JOptionPane.showMessageDialog( this,
                                           XTCEFunctions.getText( "dialog_selectedrownotelecommand_text" ), // NOI18N
                                           XTCEFunctions.getText( "general_error" ), // NOI18N
                                           JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_encodeTelecommandMenuItemActionPerformed

    private void decodeTelecommandDrawingMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_decodeTelecommandDrawingMenuItemActionPerformed

        decodeTelecommandMenuItemActionPerformed( evt );

    }//GEN-LAST:event_decodeTelecommandDrawingMenuItemActionPerformed

    private void encodeTelecommandDrawingMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_encodeTelecommandDrawingMenuItemActionPerformed

        encodeTelecommandMenuItemActionPerformed( evt );

    }//GEN-LAST:event_encodeTelecommandDrawingMenuItemActionPerformed

    private void mainWindowUpgradeFileMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mainWindowUpgradeFileMenuItemActionPerformed

        final int messageNoticeResult =
            JOptionPane.showConfirmDialog( this,
                                           XTCEFunctions.getText( "file_upgrade_info" ), // NOI18N
                                           XTCEFunctions.getText( "general_information" ), // NOI18N
                                           JOptionPane.OK_CANCEL_OPTION,
                                           JOptionPane.INFORMATION_MESSAGE );

        if ( messageNoticeResult == JOptionPane.CANCEL_OPTION ) {
            return;
        }

        final XTCEViewerUpgradeFileChooser chs =
            new XTCEViewerUpgradeFileChooser( prefs );

        if ( chs.showOpenDialog( this ) != JFileChooser.APPROVE_OPTION ) {
            return;
        }

        XTCEViewerFileUpgradeProgressDialog pbar =
            new XTCEViewerFileUpgradeProgressDialog( chs.getSelectedFile(),
                                                     chs.isXIncludeSelected(),
                                                     this,
                                                     true );

        pbar.upgrade();
        pbar.setVisible( true );

        for ( final String message : pbar.getMessages() ) {
            logMsg( message );
        }

        final int loadQuestionResult =
            JOptionPane.showConfirmDialog( this,
                                           XTCEFunctions.getText( "file_upgrade_load" ), // NOI18N
                                           XTCEFunctions.getText( "file_menu_upgrade_database_label" ), // NOI18N
                                           JOptionPane.YES_NO_OPTION,
                                           JOptionPane.QUESTION_MESSAGE );

        if ( loadQuestionResult == JOptionPane.YES_OPTION ) {
            File savedFile = pbar.getSavedFile();
            pbar = null; // free the memory from the conversion before loading
            boolean found = openFile( savedFile,
                                      chs.isXIncludeSelected(),
                                      false,  // no validation on load
                                      true ); // read only
            if ( found == true ) {
                prefs.updateRecentFilesList( mainWindowOpenRecentMenu,
                                             chs.getSelectedFile() );
            }
        }

    }//GEN-LAST:event_mainWindowUpgradeFileMenuItemActionPerformed

    private void mainWindowCompressFileMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mainWindowCompressFileMenuItemActionPerformed

        final XTCEViewerDatabaseCompressorDialog dialog =
            new XTCEViewerDatabaseCompressorDialog( this, true );

        dialog.setVisible( true );

        if ( dialog.proceed() == false ) {
            return;
        }

        final Properties options = dialog.getSelectedOptions();

        final XTCEViewerCompressFileChooser chs =
            new XTCEViewerCompressFileChooser( prefs );

        if ( chs.showOpenDialog( this ) != JFileChooser.APPROVE_OPTION ) {
            return;
        }

        options.put( "XINCLUDE", chs.isXIncludeSelected() );

        XTCEViewerFileCompressProgressDialog pbar =
            new XTCEViewerFileCompressProgressDialog( chs.getSelectedFile(),
                                                      options,
                                                      this,
                                                      true );

        pbar.compress();
        pbar.setVisible( true );

        for ( final String message : pbar.getMessages() ) {
            logMsg( message );
        }

    }//GEN-LAST:event_mainWindowCompressFileMenuItemActionPerformed

    public void goToParameter( String  parameterName,
                               String  spaceSystemName,
                               boolean isTelemetryParameter ) {

        int idx = mainWindowPrimaryWorkspace.getSelectedIndex();

        if ( isTelemetryParameter == true ) {
            if ( idx != 1 ) {
                mainWindowPrimaryWorkspace.setSelectedIndex( 1 );
            }
            XTCEViewerSpaceSystemTreeNode selectedNode =
                (XTCEViewerSpaceSystemTreeNode)tmParameterSpaceSystemTree.getLastSelectedPathComponent();
            if ( ( selectedNode == null ) || ( selectedNode.getFullPath().equals( spaceSystemName ) == false ) ) {
                XTCEViewerFunctions.selectSpaceSystemFromTree( tmParameterSpaceSystemTree,
                                                               spaceSystemName,
                                                               false );
            }
            selectParameterFromTable( tmParametersTable,
                                      parameterName,
                                      true );
        } else {
            if ( idx != 2 ) {
                mainWindowPrimaryWorkspace.setSelectedIndex( 2 );
            }
            XTCEViewerSpaceSystemTreeNode selectedNode =
                (XTCEViewerSpaceSystemTreeNode)tcParameterSpaceSystemTree.getLastSelectedPathComponent();
            if ( ( selectedNode == null ) || ( selectedNode.getFullPath().equals( spaceSystemName ) == false ) ) {
                XTCEViewerFunctions.selectSpaceSystemFromTree( tcParameterSpaceSystemTree,
                                                               spaceSystemName,
                                                               false );
            }
            selectParameterFromTable( tcParametersTable,
                                      parameterName,
                                      true );
        }

    }

    public void goToContainer( String  containerName,
                               String  spaceSystemName ) {

        String containerFullPath = spaceSystemName + "/" + containerName; // NOI18N

        if ( mainWindowPrimaryWorkspace.getSelectedIndex() != 3 ) {
            mainWindowPrimaryWorkspace.setSelectedIndex( 3 );
        }
        XTCEViewerContainerTreeNode selectedNode =
            (XTCEViewerContainerTreeNode)tmContainerTree.getLastSelectedPathComponent();
        if ( ( selectedNode == null ) || ( selectedNode.getContainerReference().getFullPath().equals( containerFullPath ) == false ) ) {
            try {
                XTCETMContainer container = xtceDatabaseFile.getSpaceSystem( spaceSystemName ).getContainer( containerName );
                selectContainerFromTree( container.getInheritancePath() );
            } catch ( Exception ex ) {
                // do nothing?
            }
        }

    }

    public void goToTelecommand( String  telecommandName,
                                 String  spaceSystemName ) {

        if ( mainWindowPrimaryWorkspace.getSelectedIndex() != 4 ) {
            mainWindowPrimaryWorkspace.setSelectedIndex( 4 );
        }
        XTCEViewerSpaceSystemTreeNode selectedNode =
            (XTCEViewerSpaceSystemTreeNode)tcDefinitionsSpaceSystemTree.getLastSelectedPathComponent();
        if ( ( selectedNode == null ) || ( selectedNode.getFullPath().equals( spaceSystemName ) == false ) ) {
            XTCEViewerFunctions.selectSpaceSystemFromTree( tcDefinitionsSpaceSystemTree,
                                                           spaceSystemName,
                                                           false );
        }
        selectTelecommandFromTree( telecommandName );

    }

    @SuppressWarnings("unchecked")
    private void buildContainerTree() {

        DefaultTreeModel tmodel = (DefaultTreeModel)tmContainerTree.getModel();

        // sort function below results in an unchecked warning on type
        // XTCETMContainer from List<T>, not sure how to check it yet...

        List<XTCETMContainer> containers = xtceDatabaseFile.getContainers();
        Collections.sort( containers );

        XTCEViewerContainerTreeNode rootObj =
            new XTCEViewerContainerTreeNode( XTCEFunctions.getText( "general_containers" ), // NOI18N
                                             null );
        tmodel.setRoot( rootObj );

        tmContainerTree.setCellRenderer( new XTCEViewerContainerTreeCellRenderer() );

        for ( XTCETMContainer container : containers ) {
            String[] fields = container.getInheritancePath().split( "/" ); // NOI18N
            XTCEViewerContainerTreeNode obj = rootObj;
            for ( int jjj = 1; jjj < fields.length; ++jjj ) {
                obj = setContainerTreeNode( obj, container, false );
            }
        }

        tmContainerTree.setRootVisible( false );
        tmContainerTree.getSelectionModel().setSelectionMode( TreeSelectionModel.SINGLE_TREE_SELECTION );
        tmodel.reload();

    }

    private void buildStreamTree() {

        DefaultTreeModel tmodel = (DefaultTreeModel)tmStreamTree.getModel();

        List<XTCETMStream> streams = xtceDatabaseFile.getStreams();

        XTCEViewerStreamTreeNode rootObj =
            new XTCEViewerStreamTreeNode( XTCEFunctions.getText( "general_streams" ), null ); // NOI18N
        tmodel.setRoot( rootObj );

        for ( XTCETMStream stream : streams ) {
            XTCEViewerStreamTreeNode node =
                new XTCEViewerStreamTreeNode( stream.getName(), stream );
            rootObj.add( node );
        }

        tmStreamTree.setRootVisible( false );
        tmStreamTree.getSelectionModel().setSelectionMode( TreeSelectionModel.SINGLE_TREE_SELECTION );
        tmodel.reload();

    }

    private void resetAllDisplays() {

        updateParameterTable( tmParametersTable, null, null );
        updateParameterTable( tcParametersTable, null, null );
        updateContainerTable( null );
        updateTelecommandTable( null );
        buildSpaceSystemTrees();

    }
    
    private void buildSpaceSystemTrees() {

        XTCEViewerFunctions.clearTree( detailSpaceSystemTree );
        XTCEViewerFunctions.clearTree( tmParameterSpaceSystemTree );
        XTCEViewerFunctions.clearTree( tcParameterSpaceSystemTree );
        XTCEViewerFunctions.clearTree( tcDefinitionsSpaceSystemTree );
        XTCEViewerFunctions.clearTree( tmContainerTree );
        XTCEViewerFunctions.clearTree( tcTree );
        XTCEViewerFunctions.clearTree( tmStreamTree );
        XTCEViewerFunctions.clearTree( tmStreamContentTree );

        if ( xtceDatabaseFile == null ) {
            return;
        }

        XTCEViewerFunctions.buildSpaceSystemTree( detailSpaceSystemTree,
                                                  xtceDatabaseFile.getSpaceSystemTree() );
        XTCEViewerFunctions.buildSpaceSystemTree( tmParameterSpaceSystemTree,
                                                  xtceDatabaseFile.getSpaceSystemTree() );
        XTCEViewerFunctions.buildSpaceSystemTree( tcParameterSpaceSystemTree,
                                                  xtceDatabaseFile.getSpaceSystemTree() );
        XTCEViewerFunctions.buildSpaceSystemTree( tcDefinitionsSpaceSystemTree,
                                                  xtceDatabaseFile.getSpaceSystemTree() );

        buildContainerTree();
        buildStreamTree();

    }

    private XTCEViewerContainerTreeNode setContainerTreeNode( XTCEViewerContainerTreeNode obj,
                                                              XTCETMContainer             container,
                                                              boolean                     addDesc ) {

        // TODO there is a minor bug in this somewhere for cases where a stream
        // does not start at the lowest container in the inheritance tree.

        for ( int iii = 0; iii < obj.getChildCount(); ++iii ) {
            XTCEViewerContainerTreeNode child =
                (XTCEViewerContainerTreeNode)(obj.getChildAt( iii ));
            String existingPath = child.getContainerReference().getInheritancePath();
            if ( existingPath.equals( container.getInheritancePath() ) == true ) {
                //System.out.println( "matched " + spaceSystem.getFullPath() );
                return child;
            } else {
                existingPath += "/"; // NOI18N
                if ( container.getInheritancePath().startsWith( existingPath ) == true ) {
                    //System.out.println( "matched starts " + spaceSystem.getFullPath() );
                    return child;
                }
            }
        }

        String label = container.getName();

        if ( addDesc == true ) {
            if ( container.getDescription().isEmpty() == false ) {
                StringBuilder extendedText = new StringBuilder();
                extendedText.append( label );
                extendedText.append( " ( " );
                extendedText.append( container.getDescription() );
                extendedText.append( " )" );
                label = extendedText.toString();
            }
        }

        XTCEViewerContainerTreeNode newchild =
            new XTCEViewerContainerTreeNode( label, container );
        obj.add( newchild );

        return newchild;

    }

    private XTCEViewerTelecommandTreeNode setTelecommandTreeNode( XTCEViewerTelecommandTreeNode obj, XTCETelecommand telecommand ) {

        for ( int iii = 0; iii < obj.getChildCount(); ++iii ) {
            XTCEViewerTelecommandTreeNode child = (XTCEViewerTelecommandTreeNode)(obj.getChildAt( iii ));
            String existingPath = child.getTelecommandReference().getInheritancePath();
            //System.out.println( "Inheritance Path for TC: " + existingPath );
            if ( existingPath.equals( telecommand.getInheritancePath() ) == true ) {
                //System.out.println( "matched " + existingPath );
                return child;
            } else {
                existingPath += "/"; // NOI18N
                if ( telecommand.getInheritancePath().startsWith( existingPath ) == true ) {
                    //System.out.println( "matched starts " + existingPath );
                    return child;
                }
            }
        }

        boolean showAllNamespaces   = prefs.getShowAllAliasNamespacesOption();
        boolean showAliasNamespaces = prefs.getShowAliasNamespacesOption();
        String  preferredNamespace  = prefs.getPreferredAliasNamespaceOption();

        String aliasString =
            XTCEFunctions.makeAliasDisplayString( telecommand,
                                                  showAllNamespaces,
                                                  showAliasNamespaces,
                                                  preferredNamespace );
        String telecommandLabel;
        if ( aliasString.isEmpty() == false ) {
            telecommandLabel = telecommand.getName() + " (" + aliasString + ")"; // NOI18N
        } else {
            telecommandLabel = telecommand.getName();
        }
        XTCEViewerTelecommandTreeNode newchild =
            new XTCEViewerTelecommandTreeNode( telecommandLabel, telecommand );
        obj.add( newchild );

        return newchild;

    }

    private void drawTelemetryParameterTable() {

        XTCEViewerSpaceSystemTreeNode node =
           (XTCEViewerSpaceSystemTreeNode)tmParameterSpaceSystemTree.getLastSelectedPathComponent();

        if ( node != null ) {

            List<XTCEParameter> parameters =
                node.getSpaceSystemReference().getTelemetryParameters();
            updateParameterTable( tmParametersTable,
                                  parameters,
                                  node.getSpaceSystemReference() );

            logMsg( XTCEFunctions.getText( "ss_processed" ) + // NOI18N
                    " '" + // NOI18N
                    node.getSpaceSystemReference().getName() +
                    "' " + // NOI18N
                    XTCEFunctions.getText( "ss_processed_contains" ) + // NOI18N
                    " " + // NOI18N
                    Integer.toString( parameters.size() ) +
                    " " + // NOI18N
                    XTCEFunctions.getText( "ss_tmparameters" ) ); // NOI18N
        }

    }

    private void drawTelecommandParameterTable() {

        XTCEViewerSpaceSystemTreeNode node =
           (XTCEViewerSpaceSystemTreeNode)tcParameterSpaceSystemTree.getLastSelectedPathComponent();

        if ( node != null ) {

            List<XTCEParameter> parameters =
                node.getSpaceSystemReference().getTelecommandParameters();
            updateParameterTable( tcParametersTable,
                                  parameters,
                                  node.getSpaceSystemReference() );

            logMsg( XTCEFunctions.getText( "ss_processed" ) + // NOI18N
                    " '" + // NOI18N
                    node.getSpaceSystemReference().getName() +
                    "' " + // NOI18N
                    XTCEFunctions.getText( "ss_processed_contains" ) + // NOI18N
                    " " + // NOI18N
                    Integer.toString( parameters.size() ) +
                    " " + // NOI18N
                    XTCEFunctions.getText( "ss_tcparameters" ) ); // NOI18N
        }

    }

    private void drawContainerContentTable( List<XTCEContainerEntryValue> values ) {

        XTCEViewerContainerTreeNode node =
           (XTCEViewerContainerTreeNode)tmContainerTree.getLastSelectedPathComponent();

        if ( ( node != null ) && ( node.getContainerReference() != null ) ) {

            XTCETMContainer container = node.getContainerReference();
            boolean showAllConditionals = prefs.getShowAllContainerConditionalsOption();

            try {
                // @todo this might be eligible to short circuit
                XTCEContainerContentModel containerModel =
                    xtceDatabaseFile.processContainer( container,
                                                       values,
                                                       showAllConditionals );
                updateContainerTable( containerModel );
                node.setContentModel( containerModel );

                String orientationOption =
                    prefs.getContainerOrientationOption();

                XTCEViewerContainerDrawing.Orientation orientDrawingFlag;
                if ( orientationOption.equals( "LEFT_TO_RIGHT" ) == true ) { // NOI18N
                    orientDrawingFlag = Orientation.LEFT_TO_RIGHT;
                } else if ( orientationOption.equals( "TOP_TO_BOTTOM" ) == true ) { // NOI18N
                    orientDrawingFlag = Orientation.TOP_TO_BOTTOM;
                } else {
                    orientDrawingFlag = Orientation.LEFT_TO_RIGHT;
                }

                boolean showAllNamespaces   = prefs.getShowAllAliasNamespacesOption();
                boolean showAliasNamespaces = prefs.getShowAliasNamespacesOption();
                String  preferredNamespace  = prefs.getPreferredAliasNamespaceOption();

                XTCEViewerContainerDrawing drawing =
                    new XTCEViewerContainerDrawing( containerModel,
                                                    orientDrawingFlag,
                                                    showAllNamespaces,
                                                    showAliasNamespaces,
                                                    preferredNamespace );
         
                tmContainerDrawingScrollPane.setViewportView( drawing );

                // TODO: it would be nice here to set the vertical scrollbar to
                // the bottom of the drawing that is presented

                logMsg( XTCEFunctions.getText( "ss_cont_processed" ) + // NOI18N
                        " '" + // NOI18N
                        containerModel.getContainerReference().getName() +
                        "' " + // NOI18N
                        XTCEFunctions.getText( "ss_processed_contains" ) + // NOI18N
                        " " + // NOI18N
                        Long.toString( containerModel.getContentList().size() ) +
                        " " + // NOI18N
                        XTCEFunctions.getText( "ss_cont_rowsoccupied" ) + // NOI18N
                        " " + // NOI18N
                        Long.toString( containerModel.getTotalSize() ) +
                        " " + // NOI18N
                        XTCEFunctions.getText( "ss_cont_bits" ) ); // NOI18N

            } catch ( XTCEDatabaseException ex ) {
                logMsg( XTCEFunctions.generalErrorPrefix() + ex.getLocalizedMessage() );
            }

        }

    }

    private void drawStreamContentTree() {

        XTCEViewerStreamTreeNode node =
           (XTCEViewerStreamTreeNode)tmStreamTree.getLastSelectedPathComponent();

        XTCEViewerFunctions.clearTree( tmStreamContentTree );

        if ( node == null || node.getStreamReference() == null ) {
            return;
        }

        tmStreamContentTree.putClientProperty( "JTree.lineStyle", // NOI18N
                                               "Horizontal" ); // NOI18N

        DefaultTreeModel tmodel =
            (DefaultTreeModel)tmStreamContentTree.getModel();

        //System.out.println( "Stream selected " +
        //    node.getName() +
        //    " root path " +
        //    node.getStreamReference().getStreamContainerPath() );

        List<XTCETMContainer> containers =
            node.getStreamReference().getContainers();

        XTCEViewerContainerTreeNode rootObj =
            new XTCEViewerContainerTreeNode( XTCEFunctions.getText( "general_containers" ), // NOI18N
                                             null );

        tmodel.setRoot( rootObj );

        tmStreamContentTree.setCellRenderer( new XTCEViewerContainerTreeCellRenderer() );

        for ( XTCETMContainer container : containers ) {
            //System.out.println( "Container " +
            //                    container.getName() +
            //                    " IPath " +
            //                    container.getInheritancePath() +
            //                    " CPath " +
            //                    container.getFullPath() );
            String[] fields =
                container.getInheritancePath().split( "/" ); // NOI18N
            XTCEViewerContainerTreeNode obj = rootObj;
            for ( int jjj = 1; jjj < fields.length; ++jjj ) {
                obj = setContainerTreeNode( obj, container, true );
            }
        }

        logMsg( XTCEFunctions.getText( "ss_processed_stream" ) + // NOI18N
            " '" + node.getStreamReference().getName() + // NOI18N
            "' " + XTCEFunctions.getText( "ss_stream_containing" ) + // NOI18N
            " " + Long.toString( containers.size() ) + // NOI18N
            " " + // NOI18N
            XTCEFunctions.getText( "general_containers" ) ); // NOI18N

        tmStreamContentTree.setRootVisible( false );

        tmodel.reload();

        XTCEViewerFunctions.expandAllTreeNodes( tmStreamContentTree );

    }

    private void drawTelecommandTree() {

        XTCEViewerSpaceSystemTreeNode node =
           (XTCEViewerSpaceSystemTreeNode)tcDefinitionsSpaceSystemTree.getLastSelectedPathComponent();

        if ( node != null ) {

            List<XTCETelecommand> telecommands =
                node.getSpaceSystemReference().getTelecommands();

            updateTelecommandTree( telecommands,
                                   node.getSpaceSystemReference() );

            XTCEViewerFunctions.expandAllTreeNodes( tcTree );

            List<String> warnings =
                node.getSpaceSystemReference().getWarningsFromLastOperation();

            for ( String warning : warnings ) {
                logMsg( warning );
            }

        }

    }

    private void drawTelecommandContentTable( List<XTCEContainerEntryValue> values ) {

        XTCEViewerTelecommandTreeNode node =
            (XTCEViewerTelecommandTreeNode)tcTree.getLastSelectedPathComponent();

        if ( ( node != null ) && ( node.getTelecommandReference() != null ) ) {

            XTCETelecommand tcObject  = node.getTelecommandReference();

            boolean showAllConditionals =
                prefs.getShowAllContainerConditionalsOption();

            try {

                // TODO this might be eligible to short circuit
                XTCETelecommandContentModel containerModel =
                    xtceDatabaseFile.processTelecommand( tcObject,
                                                         values,
                                                         showAllConditionals );
                updateTelecommandTable( containerModel );
                node.setContentModel( containerModel );

                String orientationOption =
                    prefs.getContainerOrientationOption();

                XTCEViewerContainerDrawing.Orientation orientDrawingFlag;
                if ( orientationOption.equals( "LEFT_TO_RIGHT" ) == true ) { // NOI18N
                    orientDrawingFlag = Orientation.LEFT_TO_RIGHT;
                } else if ( orientationOption.equals( "TOP_TO_BOTTOM" ) == true ) { // NOI18N
                    orientDrawingFlag = Orientation.TOP_TO_BOTTOM;
                } else {
                    orientDrawingFlag = Orientation.LEFT_TO_RIGHT;
                }

                boolean showAllNamespaces   = prefs.getShowAllAliasNamespacesOption();
                boolean showAliasNamespaces = prefs.getShowAliasNamespacesOption();
                String  preferredNamespace  = prefs.getPreferredAliasNamespaceOption();

                XTCEViewerContainerDrawing drawing =
                    new XTCEViewerContainerDrawing( containerModel,
                                                    orientDrawingFlag,
                                                    showAllNamespaces,
                                                    showAliasNamespaces,
                                                    preferredNamespace );
         
                tcContentDrawingScrollPane.setViewportView( drawing );

                logMsg( XTCEFunctions.getText( "ss_processed_tc" ) + // NOI18N
                        " '" + // NOI18N
                        containerModel.getContainerReference().getName() +
                        "' " + // NOI18N
                        XTCEFunctions.getText( "ss_processed_contains" ) + // NOI18N
                        " " + // NOI18N
                        Long.toString( containerModel.getContentList().size() ) +
                        " " + // NOI18N
                        XTCEFunctions.getText( "ss_cont_rowsoccupied" ) + // NOI18N
                        " " + // NOI18N
                        Long.toString( containerModel.getTotalSize() ) +
                        " " + // NOI18N
                        XTCEFunctions.getText( "ss_cont_bits" ) ); // NOI18N

            } catch ( XTCEDatabaseException ex ) {
                logMsg( XTCEFunctions.generalErrorPrefix() + ex.getLocalizedMessage() );
            }

        }

    }

    private void updateParameterTable( JTable              table,
                                       List<XTCEParameter> parameters,
                                       XTCESpaceSystem     spaceSystem ) {

        DefaultTableModel tableModel = (DefaultTableModel)table.getModel();
        tableModel.setRowCount( 0 );

        if ( parameters == null ) {
            return;
        }

        table.setDefaultRenderer( String.class,
                                  new XTCEViewerParameterTableCellRenderer( parameters ) );

        boolean showAllNamespaces   = prefs.getShowAllAliasNamespacesOption();
        boolean showAliasNamespaces = prefs.getShowAliasNamespacesOption();
        String  preferredNamespace  = prefs.getPreferredAliasNamespaceOption();

        for ( XTCEParameter parameter : parameters ) {

            String aliasString =
                XTCEFunctions.makeAliasDisplayString( parameter,
                                                      showAllNamespaces,
                                                      showAliasNamespaces,
                                                      preferredNamespace );

            Object rowData[] = { parameter.getName(),
                                 aliasString,
                                 parameter.getEngineeringTypeString(),
                                 parameter.getUnits(),
                                 parameter.getRawSizeInBits(),
                                 parameter.getRawTypeString(),
                                 parameter.getDataSource(),
                                 parameter.isReadOnly(),
                                 parameter.getInitialValue(),
                                 parameter.getDescription() };

            tableModel.addRow( rowData );

            if ( parameter.isValid() == false ) {
                logMsg( XTCEFunctions.getText( "error_invalid_parametertype" ) + // NOI18N
                        " '" + // NOI18N
                        parameter.getName() +
                        "' (" + // NOI18N
                        XTCEFunctions.getText( "find_menu_spacesystem_label" ) + // NOI18N
                        " " + // NOI18N
                        spaceSystem.getFullPath() +
                        ")" ); // NOI18N
            }

        }

        for ( String warning : spaceSystem.getWarningsFromLastOperation() ) {
            logMsg( XTCEFunctions.generalWarningPrefix() + warning ); // NOI18N
        }

    }

    private void updateContainerTable( XTCEContainerContentModel containerModel ) {

        DefaultTableModel tableModel =
            (DefaultTableModel)tmContainerTable.getModel();

        tableModel.setRowCount( 0 );

        if ( containerModel == null ) {
            tmContainerDrawingScrollPane.setViewportView( null );
            return;
        }

        List<String> warnings = containerModel.getWarnings();
        for ( String warning : warnings ) {
            logMsg( XTCEFunctions.generalWarningPrefix() + warning );
        }

        List<XTCEContainerContentEntry> entries =
            containerModel.getContentList();

        tmContainerTable.setDefaultRenderer(
            String.class,
            new XTCEViewerContainerTableCellRenderer( entries ) );

        boolean showAllNamespaces   = prefs.getShowAllAliasNamespacesOption();
        boolean showAliasNamespaces = prefs.getShowAliasNamespacesOption();
        String  preferredNamespace  = prefs.getPreferredAliasNamespaceOption();

        for ( XTCEContainerContentEntry entry : entries ) {

            String aliasString   = ""; // NOI18N
            String name          = ""; // NOI18N
            String containerName;

            if ( entry.getEntryType() == FieldType.PARAMETER ) {
                aliasString =
                    XTCEFunctions.makeAliasDisplayString( entry.getParameter(),
                                                          showAllNamespaces,
                                                          showAliasNamespaces,
                                                          preferredNamespace );
                name          = entry.getParameter().getName();
                containerName = entry.getHoldingContainer().getName();
            } else if ( entry.getEntryType() == FieldType.CONTAINER ) {
                containerName = entry.getTelemetryContainer().getName();
            } else {
                containerName = "UNDEFINED"; // NOI18N
            }

            String itemValue = null;
            if ( entry.getValue() != null ) {
                itemValue = entry.getValue().toStringWithoutParameter();
            }

            Object rowData[] = { containerName,
                                 name,
                                 aliasString,
                                 entry.getRawSizeInBits(),
                                 entry.getStartBit(),
                                 itemValue,
                                 entry.getInitialValue(),
                                 entry.getConditions(),
                                 entry.getRepeatParameterInfo() };

            tableModel.addRow( rowData );

        }

    }

    private void updateTelecommandTable( XTCETelecommandContentModel containerModel ) {

        DefaultTableModel tableModel = (DefaultTableModel)tcContentTable.getModel();
        tableModel.setRowCount( 0 );

        if ( containerModel == null ) {
            tcContentDrawingScrollPane.setViewportView( null );
            return;
        }

        List<String> warnings = containerModel.getWarnings();
        for ( String warning : warnings ) {
            logMsg( XTCEFunctions.generalWarningPrefix() + warning );
        }

        List<XTCEContainerContentEntry> entries = containerModel.getContentList();

        tcContentTable.setDefaultRenderer( String.class,
                                           new XTCEViewerContainerTableCellRenderer( entries ) );

        boolean showAllNamespaces   = prefs.getShowAllAliasNamespacesOption();
        boolean showAliasNamespaces = prefs.getShowAliasNamespacesOption();
        String  preferredNamespace  = prefs.getPreferredAliasNamespaceOption();

        for ( XTCEContainerContentEntry entry : entries ) {

            String aliasString   = ""; // NOI18N
            String name          = ""; // NOI18N
            String containerName = "UNDEFINED"; // NOI18N
            String fieldType     = ""; // NOI18N
            String defaultValue  = ""; // NOI18N
            String description   = ""; // NOI18N
            String units         = ""; // NOI18N
            String typeName      = ""; // NOI18N
            String encodingName  = ""; // NOI18N

            // re-evaluate which container names should be used here

            if ( entry.getEntryType() == FieldType.PARAMETER ) {
                aliasString =
                    XTCEFunctions.makeAliasDisplayString( entry.getParameter(),
                                                          showAllNamespaces,
                                                          showAliasNamespaces,
                                                          preferredNamespace );
                name = entry.getParameter().getName();
                if ( entry.getHoldingContainer() != null ) {
                    containerName = entry.getHoldingContainer().getName();
                } else if ( entry.getTelecommand() != null ) {
                    containerName = entry.getTelecommand().getName();
                }
                fieldType = "Parameter"; // NOI18N
                description = entry.getParameter().getDescription();
                units = entry.getParameter().getUnits();
                typeName = entry.getParameter().getEngineeringType().toString();
                encodingName = entry.getParameter().getRawTypeString();
            } else if ( entry.getEntryType() == FieldType.ARGUMENT ) {
                aliasString =
                    XTCEFunctions.makeAliasDisplayString( entry.getArgument(),
                                                          showAllNamespaces,
                                                          showAliasNamespaces,
                                                          preferredNamespace );
                name = entry.getArgument().getName();
                containerName = entry.getTelecommand().getName();
                fieldType = "Argument"; // NOI18N
                description = entry.getArgument().getDescription();
                units = entry.getArgument().getUnits();
                typeName = entry.getArgument().getEngineeringType().toString();
                encodingName = entry.getArgument().getRawTypeString();
            } else if ( entry.getEntryType() == FieldType.CONTAINER ) {
                if ( entry.getTelecommand() != null ) {
                    containerName = entry.getTelecommand().getName();
                } else if ( entry.getTelecommandContainer() != null ) {
                    containerName = entry.getTelecommandContainer().getName();
                }
            } else if ( entry.getEntryType() == FieldType.CONSTANT ) {
                if ( entry.getTelecommand() != null ) {
                    containerName = entry.getTelecommand().getName();
                } else if ( entry.getTelecommandContainer() != null ) {
                    containerName = entry.getTelecommandContainer().getName();
                }
                fieldType = "Constant"; // NOI18N
            }

            if ( entry.getValue() != null ) {
                defaultValue = entry.getValue().toStringWithoutParameter();
            }

            Object rowData[] = { containerName,
                                 fieldType,
                                 name,
                                 units,
                                 typeName,
                                 encodingName,
                                 //aliasString,
                                 entry.getRawSizeInBits(),
                                 entry.getStartBit(),
                                 defaultValue,
                                 entry.getInitialValue(),
                                 entry.getConditions(),
                                 entry.getRepeatParameterInfo(),
                                 description };

            tableModel.addRow( rowData );

        }

    }

    @SuppressWarnings("unchecked")
    private void updateTelecommandTree( List<XTCETelecommand> telecommands,
                                        XTCESpaceSystem       spaceSystem ) {

        if ( telecommands == null ) {
            return;
        }

        DefaultTreeModel tmodel = (DefaultTreeModel)tcTree.getModel();

        // sort function results in an unchecked warnings at XTCETelecommand in
        // List<T>, not sure how to check it yet...

        Collections.sort( telecommands );

        XTCEViewerTelecommandTreeNode rootObj =
            new XTCEViewerTelecommandTreeNode( XTCEFunctions.getText( "dialog_metrics_telecommands_text" ), // NOI18N
                                               null );
        tmodel.setRoot( rootObj );

        tcTree.setCellRenderer( new XTCEViewerTelecommandTreeCellRenderer() );

        boolean showAllNamespaces   = prefs.getShowAllAliasNamespacesOption();
        boolean showAliasNamespaces = prefs.getShowAliasNamespacesOption();
        String  preferredNamespace  = prefs.getPreferredAliasNamespaceOption();

        for ( XTCETelecommand telecommand : telecommands ) {

            String alias =
                XTCEFunctions.makeAliasDisplayString( telecommand,
                                                      showAllNamespaces,
                                                      showAliasNamespaces,
                                                      preferredNamespace );

            String telecommandLabel;
            if ( alias.isEmpty() == false ) {
                telecommandLabel = telecommand.getName() + " (" + alias + ")"; // NOI18N
            } else {
                telecommandLabel = telecommand.getName();
            }

            XTCEViewerTelecommandTreeNode newchild =
                new XTCEViewerTelecommandTreeNode( telecommandLabel,
                                                   telecommand );

            rootObj.add( newchild );

        }

        tcTree.setRootVisible( false );

        tmodel.reload();

    }

    /** Private method to retrieve the JTree for the SpaceSystem that is in the
     * current working tab on the GUI screen.
     *
     * This function is useful because several tabs have some similar menu
     * items, such as to expand all the Space Systems in the tree.  This makes
     * a single implementation generic to all tabs.
     *
     * @return JTree from the current working tab, or null if there is no
     * SpaceSystem tree on the current tab.
     *
     */

    private JTree getCurrentSpaceSystemTree() {

        JTree tree = null;
        int idx = mainWindowPrimaryWorkspace.getSelectedIndex();
        if ( idx == 0 ) {
            tree = detailSpaceSystemTree;
        } else if ( idx == 1 ) {
            tree = tmParameterSpaceSystemTree;
        } else if ( idx == 2 ) {
            tree = tcParameterSpaceSystemTree;
        } else if ( idx == 4 ) {
            tree = tcDefinitionsSpaceSystemTree;
        }

        return tree;

    }

    private XTCESpaceSystem getCurrentSpaceSystemFromTree() {

        JTree tree = getCurrentSpaceSystemTree();
        if ( tree == null ) {
            return null;
        }

        XTCEViewerSpaceSystemTreeNode node =
            (XTCEViewerSpaceSystemTreeNode)tree.getLastSelectedPathComponent();
        if ( node == null ) {
            return null;
        }

        return node.getSpaceSystemReference();

    }

    private boolean selectContainerFromTree( String searchString ) {

        DefaultTreeModel tmodel = (DefaultTreeModel)tmContainerTree.getModel();
        XTCEViewerContainerTreeNode obj = (XTCEViewerContainerTreeNode)tmodel.getRoot();
        Enumeration eee = obj.breadthFirstEnumeration();
        while ( eee.hasMoreElements() == true ) { 
            XTCEViewerContainerTreeNode node = (XTCEViewerContainerTreeNode)eee.nextElement();
            if ( node.getContainerReference() == null ) {
                continue;
            }
            if ( node.getContainerReference().getInheritancePath().equals( searchString ) == true ) {
                TreeNode[] nodes = tmodel.getPathToRoot( node );
                TreePath path = new TreePath( nodes );
                tmContainerTree.setSelectionPath( path );
                tmContainerTree.scrollPathToVisible( path );
                tmodel.nodeChanged( node );
                return true;
            }
        }
        return false;

    }

    private boolean selectTelecommandFromTree( String searchString ) {

        DefaultTreeModel tmodel = (DefaultTreeModel)tcTree.getModel();
        XTCEViewerTelecommandTreeNode obj = (XTCEViewerTelecommandTreeNode)tmodel.getRoot();
        Enumeration eee = obj.breadthFirstEnumeration();
        while ( eee.hasMoreElements() == true ) { 
            XTCEViewerTelecommandTreeNode node = (XTCEViewerTelecommandTreeNode)eee.nextElement();
            if ( node.getTelecommandReference() == null ) {
                continue;
            }
            if ( node.getTelecommandReference().getName().equals( searchString ) == true ) {
                TreeNode[] nodes = tmodel.getPathToRoot( node );
                TreePath path = new TreePath( nodes );
                tcTree.setSelectionPath( path );
                tcTree.scrollPathToVisible( path );
                tmodel.nodeChanged( node );
                return true;
            }
        }
        return false;

    }

    private boolean selectParameterFromTable( JTable table, String searchString, boolean findByName ) {

        boolean showAliasNamespaces = prefs.getShowAliasNamespacesOption();
        String  preferredNamespace  = prefs.getPreferredAliasNamespaceOption();
        TableModel tm = table.getModel();
        for ( int iii = 0; iii < tm.getRowCount(); ++iii ) {
            if ( findByName == true ) {
                if ( ((String)tm.getValueAt( iii, 0 )).equals( searchString ) == true ) {
                    int viewportRow = table.getRowSorter().convertRowIndexToView( iii );
                    table.setRowSelectionInterval( viewportRow, viewportRow );
                    table.scrollRectToVisible( new Rectangle( table.getCellRect( viewportRow, 0, true ) ) );
                    return true;
                }
            } else {
                if ( ( preferredNamespace.isEmpty() == false ) && ( showAliasNamespaces == false ) ) {
                    if ( ((String)tm.getValueAt( iii, 1 )).equals( searchString ) == true ) {
                        int viewportRow = table.getRowSorter().convertRowIndexToView( iii );
                        table.setRowSelectionInterval( viewportRow, viewportRow );
                        table.scrollRectToVisible( new Rectangle( table.getCellRect( viewportRow, 0, true ) ) );
                        return true;
                    }
                } else if ( ( preferredNamespace.isEmpty() == false ) && ( showAliasNamespaces == true ) ) {
                    if ( ((String)tm.getValueAt( iii, 1 )).endsWith( "::" + searchString ) == true ) { // NOI18N
                        int viewportRow = table.getRowSorter().convertRowIndexToView( iii );
                        table.setRowSelectionInterval( viewportRow, viewportRow );
                        table.scrollRectToVisible( new Rectangle( table.getCellRect( viewportRow, 0, true ) ) );
                        return true;
                    }
                } else {
                    String aliasField = (String)tm.getValueAt( iii, 1 );
                    String fields[] = aliasField.split( " " ); // NOI18N
                    for ( String field : fields ) {
                        if ( showAliasNamespaces == true ) {
                            if ( field.endsWith( "::" + searchString ) == true ) { // NOI18N
                                int viewportRow = table.getRowSorter().convertRowIndexToView( iii );
                                table.setRowSelectionInterval( viewportRow, viewportRow );
                                table.scrollRectToVisible( new Rectangle( table.getCellRect( viewportRow, 0, true ) ) );
                                return true;
                            }
                        } else {
                            if ( field.equals( searchString ) == true ) {
                                int viewportRow = table.getRowSorter().convertRowIndexToView( iii );
                                table.setRowSelectionInterval( viewportRow, viewportRow );
                                table.scrollRectToVisible( new Rectangle( table.getCellRect( viewportRow, 0, true ) ) );
                                return true;
                            }
                        }
                    }
                }
            }
        }

        return false;

    }

    /** Method to load a new XTCE database file, taking into account that one
     * might already be loaded and offering the user an opportunity to save
     * this or cancel before closing an already open file.
     *
     * @param <T> Generic type parameter
     *
     * @param dbFile T object in Java to use to open the file.  It must
     * be either a File or URL object.
     *
     * @param applyXInclude Boolean indicating if the file should be opened
     * with XInclude processing enabled.
     *
     * @param validateOnLoad Boolean indicating if the file should be validated
     * against schema on load.
     *
     * @param readOnly Boolean indicating if the file should be opened in
     * read-only state for performance.
     *
     * @return indicating if the stream opened on a found file.
     *
     */

    public <T> boolean openFile( final T       dbFile,
                                 final boolean applyXInclude,
                                 final boolean validateOnLoad,
                                 final boolean readOnly ) {

        // in the event that a file is already open, we should attempt to
        // close the file before creating a new database.

        if ( xtceDatabaseFile != null ) {
            mainWindowCloseFileMenuItemActionPerformed( null );
        }

        // this extra test captures the case where a file is open and the user
        // chose to cancel the save and close, leaving the original file open.

        if ( xtceDatabaseFile != null ) {
            return false;
        }

        try {

            logMsg( XTCEFunctions.getMemoryUsageStatistics() );

            final InputStream fileInputStream;
            final File        filePath;

            if ( dbFile instanceof File ) {
                fileInputStream = new FileInputStream( (File)dbFile );
                filePath        = (File)dbFile;
            } else if ( dbFile instanceof URL ) {
                fileInputStream = ((URL)dbFile).openStream();
                filePath        = new File( ((URL)dbFile).getPath() );
            } else {
                // incompatible type - should not happen in GUI
                return false;
            }

            final String loadText =
                XTCEFunctions.getText( "file_upgrade_step01" ) +
                " " + // NOI18N
                filePath.getName();

            final ProgressMonitorInputStream progressDialog =
                new ProgressMonitorInputStream( this,
                                                loadText,
                                                fileInputStream );

            final Thread ttt = new Thread( new Runnable() {

                @Override
                public void run() {

                    performFileLoad( progressDialog,
                                     filePath,
                                     ( dbFile instanceof File ),
                                     applyXInclude,
                                     validateOnLoad,
                                     readOnly );
                }

            } );

            ttt.start();

        } catch ( Exception ex ) {

            logMsg( XTCEFunctions.generalErrorPrefix() +
                    ex.getLocalizedMessage() );

            return false;

        }

        return true;

    }

    /** Private Method to perform the load XML processing.
     *
     * This function is meant to be called in a Thread from the openFile
     * method.
     *
     * @param pDialog ProgressMonitorInputStream that is hosting the dialog
     * for the progress monitor.
     *
     * @param filePath File object in Java to use to open the file.
     *
     * @param setCurrentWorkingDir Boolean indicating if the current working
     * directory should be setup following the load.  This is only for the load
     * when it is a File object, not a URL object.
     *
     * @param applyXInclude Boolean indicating if the file should be opened
     * with XInclude processing enabled.
     *
     * @param validateOnLoad Boolean indicating if the file should be validated
     * against schema on load.
     *
     * @param readOnly Boolean indicating if the file should be opened in
     * read-only state for performance.
     *
     * @return boolean indicating if the stream opened on a found file.
     *
     */

    private void performFileLoad( final ProgressMonitorInputStream pDialog,
                                  final File                       filePath,
                                  final boolean                    setCurrentWorkingDir,
                                  final boolean                    applyXInclude,
                                  final boolean                    validateOnLoad,
                                  final boolean                    readOnly ) {

        try {

            long startTime = System.currentTimeMillis();

            InputStream progressIStream = new BufferedInputStream( pDialog );

            xtceDatabaseFile = new XTCEDatabase( progressIStream,
                                                 filePath,
                                                 validateOnLoad,
                                                 applyXInclude,
                                                 readOnly );

            mainWindowEditDocumentMenuItem.setSelected( ! readOnly );
            mainWindowEditDocumentMenuItemActionPerformed( null );

            for ( String message : xtceDatabaseFile.getDocumentWarnings() ) {
                logMsg( message );
            }

            if ( xtceDatabaseFile.getErrorCount() > 0 ) {
                throw new XTCEDatabaseException( XTCEFunctions.getText( "dialog_unabletoload_text" ) + // NOI18N
                                                 " " + // NOI18N
                                                 filePath.getAbsolutePath() );
            }

            loadedFilenameLabel.setText( filePath.getAbsolutePath() );

            if ( validateOnLoad == true ) {
                loadedSchemaLabel.setText( xtceDatabaseFile.getSchemaFromDocument() );
            }

            buildSpaceSystemTrees();

            if ( ( setCurrentWorkingDir == true ) &&
                 ( filePath.getParent() != null ) ) {
                prefs.setCurrentWorkingDirectory( filePath.getParent() );
            }

            detailSpaceSystemTree.setSelectionRow( 0 );
            mainWindowPrimaryWorkspace.setSelectedIndex( 0 );

            long estimatedTime = System.currentTimeMillis() - startTime;

            logMsg( XTCEFunctions.getText( "file_chooser_load_time_text" ) + // NOI18N
                    " " + // NOI18N
                    Long.toString( estimatedTime / 1000 ) +
                    " " + // NOI18N
                    XTCEFunctions.getText( "file_chooser_load_time_unit_text" ) + // NOI18N
                    " " + // NOI18N
                    ( validateOnLoad == true ? "" : " (" + XTCEFunctions.getText( "file_chooser_schema_disable_text" ) + ")" ) ); // NOI18N

            logMsg( XTCEFunctions.getMemoryUsageStatistics() );

        } catch ( Exception ex ) {

            logMsg( XTCEFunctions.generalErrorPrefix() +
                    ex.getLocalizedMessage() );

        } finally {

            try {
                pDialog.close();
            } catch ( Exception ex ) {
                // do nothing
            }

        }

    }

    /** Private method to open a help dialog browser.
     *
     * The help dialog browser is not limited to just having a single one open
     * for each case, unlike some other dialogs that are kept track of by this
     * GUI class.  As a result, it is possible to open several of the same help
     * type screen.  This can be convenient for the user.  In addition, this
     * is not blocked by whether or not a database file is loaded.  Help
     * browsers are independent of the data.
     *
     * @param helpFile String containing the system resource file path to use
     * for the browser root.
     *
     */

    private void openHelp( String helpFile ) {

        // this method no longer uses the XTCEViewerHelpDialog class in favor
        // of a version that uses a Java FX Panel that does a nicer job at the
        // html rendering.  the old class is kept around so that I can edit the
        // form to add things and copy the generated code over to the new class
        // since NetBeans is not configured for FX in this project right now.

        URL helpURL = ClassLoader.getSystemResource( helpFile );

        if ( helpURL != null ) {
            //XTCEViewerHelpDialog dialog =
            //    new XTCEViewerHelpDialog( this, false, helpURL );
            XTCEViewerHelpBrowserDialog dialog =
                  new XTCEViewerHelpBrowserDialog( this, false, helpURL );
        } else {
            logMsg( XTCEFunctions.generalErrorPrefix() +
                    XTCEFunctions.getText( "dialog_unabletoload_text" ) + // NOI18N
                    ": " + // NOI18N
                    helpFile );
        }

    }

    /** Private method to test if a database file is loaded in memory and
     * return true if the warning dialog was displayed to the user.
     *
     * This method is used to shortcut selections of menu items that cannot
     * reasonably be used when there is no file loaded.  Callbacks use this to
     * return prematurely when the return value is true.
     *
     * @return boolean indicating if the user was warned that there is not a
     * file open.
     *
     */

    private boolean fileOpenWarning() {

        if ( xtceDatabaseFile == null ) {
            JOptionPane.showMessageDialog(
                this,
                XTCEFunctions.getText( "dialog_nofileisopen_text" ), // NOI18N
                XTCEFunctions.getText( "general_error" ), // NOI18N
                JOptionPane.ERROR_MESSAGE );
            return true;
        } else {
            return false;
        }

    }

    /** Private method to append a log message to the Messages portion of the
     * GUI main window.
     *
     * @param msg String containing the message to append.
     *
     */

    private void logMsg( String msg ) {
        
        if ( messagesText.getText().equals( XTCEFunctions.getText( "no_messages_text" ) ) == true ) { // NOI18N
            messagesText.setText( "" ); // NOI18N
        }

        // since this is a text widget, we don't do the line separator because
        // this is internal to Java.  If there is later a copy/paste issue with
        // Windoze, then this is likely what would need to be adjusted for that
        // platform.

        messagesText.append( msg + "\n" ); // NOI18N

        messagesText.setCaretPosition( messagesText.getText().length() );

    }

    private static void applyTimeHandlers( String arg ) {

        String argvalue = arg.replaceFirst( "--timehandlers=", "" );
        String[] pair   = argvalue.split( "," );

        if ( pair.length == 2 ) {
            String[] extlibs = pair[1].split( ":" );
            for ( String extlib : extlibs ) {
                try {
                    System.loadLibrary( extlib );
                } catch ( Throwable ex ) {
                    System.err.println( "Unable to load requested library: " +
                                        extlib +
                                        " because " +
                                        ex.getLocalizedMessage() );
                }
            }
        }

        String[] handlers = pair[0].split( ":" );

        for ( String handler : handlers ) {

            try {
                XTCEAbsoluteTimeType timeHandler =
                    (XTCEAbsoluteTimeType)Class.forName( handler ).getDeclaredConstructor().newInstance();
                XTCEFunctions.registerAbsoluteTimeHandler( timeHandler );
            } catch ( Throwable ex ) {
                System.err.println( "Unable to register requested time handler: " +
                                    handler +
                                    " because " +
                                    ex.getLocalizedMessage() );
            }

        }

    }

    /** Display a usage message to the user when the user is using this tool
     * with command line arguments.
     *
     */

    private static void usage() {

        StringBuilder msg = new StringBuilder();

        msg.append( XTCEFunctions.getText( "usage01" ) ); // NOI18N
        msg.append( System.getProperty( "line.separator" ) ); // NOI18N
        msg.append( XTCEFunctions.getText( "usage02" ) ); // NOI18N
        msg.append( System.getProperty( "line.separator" ) ); // NOI18N
        msg.append( "   --file=NAME " ); // NOI18N
        msg.append( XTCEFunctions.getText( "usage03" ) ); // NOI18N
        msg.append( System.getProperty( "line.separator" ) ); // NOI18N
        msg.append( "   --xinclude " ); // NOI18N
        msg.append( XTCEFunctions.getText( "usage04" ) ); // NOI18N
        msg.append( System.getProperty( "line.separator" ) ); // NOI18N
        msg.append( "   --no-xinclude " ); // NOI18N
        msg.append( XTCEFunctions.getText( "usage05" ) ); // NOI18N
        msg.append( System.getProperty( "line.separator" ) ); // NOI18N
        msg.append( "   --validate " ); // NOI18N
        msg.append( XTCEFunctions.getText( "usage06" ) ); // NOI18N
        msg.append( System.getProperty( "line.separator" ) ); // NOI18N
        msg.append( "   --no-validate " ); // NOI18N
        msg.append( XTCEFunctions.getText( "usage07" ) ); // NOI18N
        msg.append( System.getProperty( "line.separator" ) ); // NOI18N
        msg.append( "   --readonly " ); // NOI18N
        msg.append( XTCEFunctions.getText( "usage08" ) ); // NOI18N
        msg.append( System.getProperty( "line.separator" ) ); // NOI18N
        msg.append( "   --writable " ); // NOI18N
        msg.append( XTCEFunctions.getText( "usage09" ) ); // NOI18N
        msg.append( System.getProperty( "line.separator" ) ); // NOI18N
        msg.append( "   --maximize " ); // NOI18N
        msg.append( XTCEFunctions.getText( "usage11" ) ); // NOI18N
        msg.append( System.getProperty( "line.separator" ) ); // NOI18N
        msg.append( XTCEFunctions.getText( "usage10" ) ); // NOI18N
        msg.append( System.getProperty( "line.separator" ) ); // NOI18N
        msg.append( "   --timehandlers=CLASS[:solib][,CLASS[:solib],...]" ); // NOI18N
        msg.append( System.getProperty( "line.separator" ) ); // NOI18N

        System.err.println( msg );

    }

    /** This is the main function that launches the XTCE Viewer/Browser/Editor
     * application.
     *
     * @param args the command line arguments, of which none are used for this
     * application.
     *
     */

    public static void main( final String args[] ) {
    
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
            java.util.logging.Logger.getLogger(XTCEViewer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(XTCEViewer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(XTCEViewer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(XTCEViewer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Properties props = System.getProperties();

        //Enumeration<?> keys = props.propertyNames();
        //while ( keys.hasMoreElements() == true ) {
        //    String key = (String)keys.nextElement();
        //    System.out.println( key + " = " + props.getProperty( key ) ); // NOI18N
        //}

        try {
            if ( System.getProperty( "os.name" ).equals( "Mac OS X" ) == true ) { // NOI18N
                System.setProperty( "apple.laf.useScreenMenuBar", "true" ); // NOI18N
                //System.setProperty( "apple.awt.brushMetalLook", "true" ); // NOI18N
                //InputMap im = (InputMap) UIManager.get( "TextField.focusInputMap" );
                //im.put( KeyStroke.getKeyStroke( KeyEvent.VK_C, KeyEvent.META_DOWN_MASK ), DefaultEditorKit.copyAction );
                //im.put( KeyStroke.getKeyStroke( KeyEvent.VK_V, KeyEvent.META_DOWN_MASK ), DefaultEditorKit.pasteAction );
                //im.put( KeyStroke.getKeyStroke( KeyEvent.VK_X, KeyEvent.META_DOWN_MASK ), DefaultEditorKit.cutAction );
                //System.out.println( "setting mac" );
            }
        } catch ( Throwable ex ) {
            // do nothing and proceed in default mode
        } */

        /* Create and display the form */
        java.awt.EventQueue.invokeLater( new Runnable() {

            @Override
            public void run() {

                XTCEViewer app = new XTCEViewer();

                String  fileToOpen     = null;
                boolean useXInclude    = true;
                boolean validateOnLoad = false;
                boolean readOnly       = true;
                boolean maxOnStart     = false;

                for ( int iii = 0; iii < args.length; ++iii ) {

                    if ( args[iii].equals( "--no-xinclude" ) == true ) {
                        useXInclude = false;
                    } else if ( args[iii].equals( "--xinclude" ) == true ) {
                        useXInclude = true;
                    } else if ( args[iii].equals( "--no-validate" ) == true ) {
                        validateOnLoad = false;
                    } else if ( args[iii].equals( "--validate" ) == true ) {
                        validateOnLoad = true;
                    } else if ( args[iii].equals( "--writable" ) == true ) {
                        readOnly = false;
                    } else if ( args[iii].equals( "--readonly" ) == true ) {
                        readOnly = true;
                    } else if ( args[iii].startsWith( "--timehandlers=" ) == true ) {
                        applyTimeHandlers( args[iii] );
                    } else if ( args[iii].startsWith( "--file=" ) == true ) {
                        fileToOpen = args[iii].substring( 7 );
                    } else if ( args[iii].equals( "--maximize" ) == true ) {
                        maxOnStart = true;
                    } else {
                        System.err.println(
                            XTCEFunctions.getText( "general_unrecognizedarg" ) +
                            ": " +
                            args[iii] );
                        usage();
                        System.exit( -1 );
                    }

                }

                if ( fileToOpen != null ) {
                    File argFile = new File( fileToOpen );
                    if ( argFile.isFile() && argFile.canRead() ) {
                        app.openFile( argFile,
                                      useXInclude,
                                      validateOnLoad,
                                      readOnly );
                    } else {
                        System.err.println(
                            XTCEFunctions.getText( "file_chooser_noload_text" ) +
                            " " +
                            fileToOpen );
                        usage();
                        System.exit( -1 );
                    }
                }

                app.setLocationByPlatform( true );
                app.setVisible( true );

                if ( maxOnStart == true ) {
                    // this is here because some platforms must do this after
                    // the setVisible() has been invoked
                    app.setExtendedState( MAXIMIZED_BOTH );
                }

            }

        });

    }

    // Private Data Members

    private XTCEViewerPreferences           prefs                 = null;
    private XTCEDatabase                    xtceDatabaseFile      = null;
    private XTCEViewerXpathQueryDialog      xpathDialog           = null;
    private XTCEViewerParameterFindDialog   findParameterDialog   = null;
    private XTCEViewerParameterUsageDialog  parameterUsageDialog  = null;
    private XTCEViewerContainerFindDialog   findContainerDialog   = null;
    private XTCEViewerTelecommandFindDialog findTelecommandDialog = null;


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem addSpaceSystemMenuItem;
    private javax.swing.JMenuItem cloneContainerDrawingMenuItem;
    private javax.swing.JMenuItem cloneTelecommandDrawingMenuItem;
    private javax.swing.JRadioButtonMenuItem containerDrawingLeftToRight;
    private javax.swing.JMenu containerDrawingOrientationMenu;
    private javax.swing.JPopupMenu containerDrawingPopupMenu;
    private javax.swing.JRadioButtonMenuItem containerDrawingTopToBottom;
    private javax.swing.JPanel containerExportPanel;
    private javax.swing.JPopupMenu containerTablePopupMenu;
    private javax.swing.JPopupMenu containerTreePopupMenu;
    private javax.swing.JMenuItem copyContainerCellMenuItem;
    private javax.swing.JMenuItem copyContainerColumnMenuItem;
    private javax.swing.JMenuItem copyContainerRowMenuItem;
    private javax.swing.JMenuItem copyContainerTableMenuItem;
    private javax.swing.JMenuItem copyParameterCellMenuItem;
    private javax.swing.JMenuItem copyParameterColumnMenuItem;
    private javax.swing.JMenuItem copyParameterRowMenuItem;
    private javax.swing.JMenuItem copyParameterTableMenuItem;
    private javax.swing.JMenuItem copyTcCellMenuItem;
    private javax.swing.JMenuItem copyTcColumnMenuItem;
    private javax.swing.JMenuItem copyTcRowMenuItem;
    private javax.swing.JMenuItem copyTcTableMenuItem;
    private javax.swing.JMenuItem decodeContainerDrawingMenuItem;
    private javax.swing.JMenuItem decodeContainerMenuItem;
    private javax.swing.JMenuItem decodeStreamMenuItem;
    private javax.swing.JMenuItem decodeTelecommandDrawingMenuItem;
    private javax.swing.JMenuItem decodeTelecommandMenuItem;
    private javax.swing.JMenuItem deleteSpaceSystemMenuItem;
    private javax.swing.JScrollPane detailSpaceSystemPanelScrollPane;
    private javax.swing.JTree detailSpaceSystemTree;
    private javax.swing.JScrollPane detailSpaceSystemTreeScrollPane;
    private javax.swing.JMenuItem encodeContainerDrawingMenuItem;
    private javax.swing.JMenuItem encodeContainerMenuItem;
    private javax.swing.JMenuItem encodeTelecommandDrawingMenuItem;
    private javax.swing.JMenuItem encodeTelecommandMenuItem;
    private javax.swing.ButtonGroup exportContainersButtonGroup;
    private javax.swing.JComboBox exportContainersCharSetComboBox;
    private javax.swing.JRadioButton exportContainersCometRadioButton;
    private javax.swing.JRadioButton exportContainersCppRadioButton;
    private javax.swing.JRadioButton exportContainersCsvRadioButton;
    private javax.swing.JRadioButton exportContainersInControlRadioButton;
    private javax.swing.JCheckBox exportContainersIncludeHeaderRowCheckbox;
    private javax.swing.JLabel exportContainersLabel;
    private javax.swing.JCheckBox exportContainersUseNamespacesCheckbox;
    private javax.swing.ButtonGroup exportParametersButtonGroup;
    private javax.swing.JComboBox exportParametersCharSetComboBox;
    private javax.swing.JRadioButton exportParametersCometRadioButton;
    private javax.swing.JRadioButton exportParametersCppRadioButton;
    private javax.swing.JRadioButton exportParametersCsvRadioButton;
    private javax.swing.JRadioButton exportParametersInControlRadioButton;
    private javax.swing.JCheckBox exportParametersIncludeHeaderRowCheckbox;
    private javax.swing.JLabel exportParametersLabel;
    private javax.swing.JCheckBox exportParametersUseNamespacesCheckbox;
    private javax.swing.ButtonGroup exportTelecommandsButtonGroup;
    private javax.swing.JComboBox exportTelecommandsCharSetComboBox;
    private javax.swing.JRadioButton exportTelecommandsCometRadioButton;
    private javax.swing.JRadioButton exportTelecommandsCppRadioButton;
    private javax.swing.JRadioButton exportTelecommandsCsvRadioButton;
    private javax.swing.JRadioButton exportTelecommandsInControlRadioButton;
    private javax.swing.JCheckBox exportTelecommandsIncludeHeaderRowCheckbox;
    private javax.swing.JLabel exportTelecommandsLabel;
    private javax.swing.JCheckBox exportTelecommandsUseNamespacesCheckbox;
    private javax.swing.JMenuItem goToEntryMenuItem;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JSplitPane jSplitPane2;
    private javax.swing.JSplitPane jSplitPane3;
    private javax.swing.JSplitPane jSplitPane4;
    private javax.swing.JSplitPane jSplitPane5;
    private javax.swing.JSplitPane jSplitPane6;
    private javax.swing.JLabel loadedFilenameLabel;
    private javax.swing.JLabel loadedSchemaLabel;
    private javax.swing.JMenuItem mainWindowClearMessagesMenuItem;
    private javax.swing.JMenuItem mainWindowClearRecentFilesMenuItem;
    private javax.swing.JMenuItem mainWindowCloseFileMenuItem;
    private javax.swing.JMenuItem mainWindowCompressFileMenuItem;
    private javax.swing.JMenuItem mainWindowCreateFileMenuItem;
    private javax.swing.JCheckBoxMenuItem mainWindowEditDocumentMenuItem;
    private javax.swing.JMenu mainWindowEditMenu;
    private javax.swing.JMenuItem mainWindowExitMenuItem;
    private javax.swing.JMenuItem mainWindowExpandAllSpaceSystemTreeMenuItem;
    private javax.swing.JMenuItem mainWindowExpandContainerTreeMenuItem;
    private javax.swing.JMenuItem mainWindowExportContainersMenuItem;
    private javax.swing.JMenu mainWindowExportMenu;
    private javax.swing.JMenuItem mainWindowExportParametersMenuItem;
    private javax.swing.JMenuItem mainWindowExportTelecommandsMenuItem;
    private javax.swing.JMenu mainWindowFileMenu;
    private javax.swing.JMenuItem mainWindowFindByXPathMenuItem;
    private javax.swing.JMenuItem mainWindowFindContainerMenuItem;
    private javax.swing.JMenu mainWindowFindMenu;
    private javax.swing.JMenuItem mainWindowFindParameterMenuItem;
    private javax.swing.JMenuItem mainWindowFindSpaceSystemMenuItem;
    private javax.swing.JMenuItem mainWindowFindTelecommandMenuItem;
    private javax.swing.JMenuItem mainWindowHelpAboutMenuItem;
    private javax.swing.JMenuItem mainWindowHelpApiMenuItem;
    private javax.swing.JMenuItem mainWindowHelpCurrentMenuItem;
    private javax.swing.JMenu mainWindowHelpMenu;
    private javax.swing.JMenuItem mainWindowHelpSchemaMenuItem;
    private javax.swing.JMenuItem mainWindowHelpToolMenuItem;
    private javax.swing.JMenuItem mainWindowLocaleMenuItem;
    private javax.swing.JMenuBar mainWindowMenuBar;
    private javax.swing.JScrollPane mainWindowMessageScrollingPane;
    private javax.swing.JMenuItem mainWindowMessagesDialogMenuItem;
    private javax.swing.JMenu mainWindowOpenExampleMenu;
    private javax.swing.JMenuItem mainWindowOpenFileMenuItem;
    private javax.swing.JMenu mainWindowOpenRecentMenu;
    private javax.swing.JMenu mainWindowOptionsMenu;
    private javax.swing.JMenuItem mainWindowPreferredNamespaceMenuItem;
    private javax.swing.JTabbedPane mainWindowPrimaryWorkspace;
    private javax.swing.JMenuItem mainWindowRecentFilesMaxMenuItem;
    private javax.swing.JMenuItem mainWindowSaveFileMenuItem;
    private javax.swing.JCheckBoxMenuItem mainWindowShowAliasNamespaceMenuItem;
    private javax.swing.JCheckBoxMenuItem mainWindowShowAllConditionalsMenuItem;
    private javax.swing.JMenu mainWindowShowMenu;
    private javax.swing.JMenuItem mainWindowShowMetricsMenuItem;
    private javax.swing.JMenuItem mainWindowUpgradeFileMenuItem;
    private javax.swing.JCheckBoxMenuItem mainWindowUseXincludeMenuItem;
    private javax.swing.JCheckBoxMenuItem mainWindowValidateOnLoadMenuItem;
    private javax.swing.JScrollPane messagesDialogPanel;
    private javax.swing.JTextArea messagesDialogText;
    private javax.swing.JLabel messagesOutputLabel;
    private javax.swing.JTextArea messagesText;
    private javax.swing.JPopupMenu parameterDetailPopupMenu;
    private javax.swing.JPanel parameterExportPanel;
    private javax.swing.ButtonGroup parameterFieldExclusion;
    private javax.swing.ButtonGroup parameterLocationExclusion;
    private javax.swing.JMenuItem saveContainerDrawingMenuItem;
    private javax.swing.JMenuItem saveTelecommandDrawingMenuItem;
    private javax.swing.JMenuItem setConditionTrueMenuItem;
    private javax.swing.JMenuItem setRepeatCounterMenuItem;
    private javax.swing.JMenuItem showContainerDrawingXmlMenuItem;
    private javax.swing.JMenuItem showContainerXmlMenuItem;
    private javax.swing.JMenuItem showEncodeDecodeDialogMenuItem;
    private javax.swing.JMenuItem showItemXmlElementsMenuItem;
    private javax.swing.JMenuItem showParameterDetailsMenuItem;
    private javax.swing.JMenuItem showParameterUsageMenuItem;
    private javax.swing.JMenuItem showStreamXmlMenuItem;
    private javax.swing.JMenuItem showTcItemXmlElementsMenuItem;
    private javax.swing.JMenuItem showTelecommandDrawingXmlMenuItem;
    private javax.swing.JMenuItem showTelecommandXmlMenuItem;
    private javax.swing.JMenuItem showXmlElementsMenuItem;
    private javax.swing.JPopupMenu spaceSystemDetailPopupMenu;
    private javax.swing.JLabel spaceSystemOverviewLabel;
    private javax.swing.JPanel spaceSystemOverviewPanel;
    private javax.swing.JPopupMenu streamTreePopupMenu;
    private javax.swing.JScrollPane tcContentDrawingScrollPane;
    private javax.swing.JScrollPane tcContentScrollPane;
    private javax.swing.JSplitPane tcContentSplitPane;
    private javax.swing.JTable tcContentTable;
    private javax.swing.JLabel tcDefinitionsLabel;
    private javax.swing.JPanel tcDefinitionsPanel;
    private javax.swing.JScrollPane tcDefinitionsScrollPane;
    private javax.swing.JTree tcDefinitionsSpaceSystemTree;
    private javax.swing.JTree tcParameterSpaceSystemTree;
    private javax.swing.JScrollPane tcParameterSpaceSystemTreeScrollPane;
    private javax.swing.JLabel tcParametersOverviewLabel;
    private javax.swing.JPanel tcParametersPanel;
    private javax.swing.JTable tcParametersTable;
    private javax.swing.JScrollPane tcParametersTableScrollPane;
    private javax.swing.JSplitPane tcSpaceSystemSplitPane;
    private javax.swing.JTree tcTree;
    private javax.swing.JScrollPane tcTreeScrollPane;
    private javax.swing.JPanel tcTypesPanel;
    private javax.swing.JPopupMenu telecommandDrawingPopupMenu;
    private javax.swing.JPanel telecommandExportPanel;
    private javax.swing.JPopupMenu telecommandTablePopupMenu;
    private javax.swing.JPopupMenu telecommandTreePopupMenu;
    private javax.swing.JSplitPane tmContainerContentSplitPane;
    private javax.swing.JLabel tmContainerDetailsLabel;
    private javax.swing.JScrollPane tmContainerDrawingScrollPane;
    private javax.swing.JTable tmContainerTable;
    private javax.swing.JScrollPane tmContainerTableScrollPane;
    private javax.swing.JTree tmContainerTree;
    private javax.swing.JScrollPane tmContainerTreeScrollPane;
    private javax.swing.JPanel tmContainersPanel;
    private javax.swing.JTree tmParameterSpaceSystemTree;
    private javax.swing.JScrollPane tmParameterSpaceSystemTreeScrollPane;
    private javax.swing.JLabel tmParametersOverviewLabel;
    private javax.swing.JPanel tmParametersPanel;
    private javax.swing.JTable tmParametersTable;
    private javax.swing.JScrollPane tmParametersTableScrollPane;
    private javax.swing.JTree tmStreamContentTree;
    private javax.swing.JLabel tmStreamDetailsLabel;
    private javax.swing.JTree tmStreamTree;
    private javax.swing.JScrollPane tmStreamTreeScrollPane;
    private javax.swing.JPanel tmStreamsPanel;
    private javax.swing.JPanel tmTypesPanel;
    // End of variables declaration//GEN-END:variables
}
