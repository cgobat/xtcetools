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

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import org.xtce.toolkit.XTCEFunctions;

/** This class implements an exception handler of last resort to prevent the
 * graphical user interface from crashing or dropping unintelligible text to
 * STDOUT.
 *
 * @author David Overeem
 *
 */

public final class XTCEViewerUncaughtExceptionHandler
    implements Thread.UncaughtExceptionHandler {

    /** Constructor
     *
     * The constructor attaches this handler to the JFrame of the XTCE Viewer
     * user interface.
     *
     * @param parent JFrame containing the main window of the application.
     *
     */

    XTCEViewerUncaughtExceptionHandler( JFrame parent ) {
        frame = parent;
    }

    /** Method that is called by Java when no exception handler is defined.
     *
     * This methods changes the behavior such that the uncaught exception will
     * be caught and the localized message displayed to a JOptionPane dialog
     * box with ERROR_MESSAGE specialization.  The message can be acknowledged
     * and processing will continue.
     *
     * @param id Thread that generated the exception, not otherwise used here.
     *
     * @param ex Throwable base class for all Java exceptions, which is used
     * to extract any kind of useful error message that might be there.
     *
     */

    @Override
    public void uncaughtException( Thread id, Throwable ex ) {

        final OutputStream ostream = new ByteArrayOutputStream();
        final PrintStream  pstream = new PrintStream( ostream );

        ex.printStackTrace( pstream );

        //System.out.println( ex.toString() + "\n\n" + ostream.toString() );

        JOptionPane.showMessageDialog( frame,
                                       XTCEFunctions.generalErrorPrefix() +
                                            ex.toString() +
                                            System.lineSeparator() +
                                            System.lineSeparator() +
                                            ostream.toString(),
                                       XTCEFunctions.getText( "uncaught_exception_error_title" ),
                                       JOptionPane.ERROR_MESSAGE );

    }

    // Private Data Members

    private JFrame frame = null;

}
