/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.omg.space.xtce.tests;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.BitSet;
import java.util.List;
import junit.framework.Assert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.omg.space.xtce.toolkit.XTCEContainerContentEntry;
import org.omg.space.xtce.toolkit.XTCEContainerContentModel;
import org.omg.space.xtce.toolkit.XTCEDatabase;
import org.omg.space.xtce.toolkit.XTCEDatabaseException;
import org.omg.space.xtce.toolkit.XTCEFunctions;
import org.omg.space.xtce.toolkit.XTCESpaceSystem;
import org.omg.space.xtce.toolkit.XTCETMContainer;
import org.omg.space.xtce.toolkit.XTCETMStream;

/**
 *
 * @author dovereem
 */

public class StreamProcessingTest {
    
    public StreamProcessingTest()  {

        try {
            loadDocument();
        } catch ( Throwable ex ) {
            Assert.fail( "Cannot start test: " + ex.getLocalizedMessage() );
        }

    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void lookupAllStreamsFromDatabase() {

        final String methodName =
            Thread.currentThread().getStackTrace()[1].getMethodName();

        System.out.println( "Test Case: " + methodName + "()" );

        try {

            List<XTCETMStream> streams = db_.getStreams();

            long expected = 2;

            Assert.assertTrue( "Should have found " +
                Long.toString( expected ) + " streams, but found instead " +
                Long.toString( streams.size() ),
                streams.size() == expected );

            long found = 0;

            String root1 =
                "/BogusSAT/SC001/Onboard_Tables/Calibration_Offsets";
            String root2 =
                "/BogusSAT/CCSDSTelemetryPacket";

            for ( XTCETMStream stream : streams ) {
                if ( stream.getName().equals( "CALTABLE" ) == true ) {
                    Assert.assertTrue( "Stream CALTABLE should have root " + root1,
                                       stream.getStreamRootContainer().getFullPath().equals( root1 ) );
                    ++found;
                } else if ( stream.getName().equals( "CCSDS-TM" ) == true ) {
                    Assert.assertTrue( "Stream CCSDS-TM should have root " + root2,
                                       stream.getStreamRootContainer().getFullPath().equals( root2 ) );
                    ++found;
                }
            }

            Assert.assertTrue( "Verified " + Long.toString( found ) + " of " +
                               Long.toString( expected ) + " streams",
                               found == expected );

        } catch ( Exception ex ) {
            //ex.printStackTrace();
            Assert.fail( ex.getLocalizedMessage() );
        }

    }

    @Test
    public void lookupAllStreamsFromSpaceSystem() {

        final String methodName =
            Thread.currentThread().getStackTrace()[1].getMethodName();

        System.out.println( "Test Case: " + methodName + "()" );

        try {

            XTCESpaceSystem ss = db_.getSpaceSystem( "/BogusSAT/SC001/Onboard_Tables" );

            List<XTCETMStream> streams = ss.getStreams();

            long expected = 1;

            Assert.assertTrue( "Should have found " +
                Long.toString( expected ) + " streams in " + ss.getName() +
                ", but found instead " +
                Long.toString( streams.size() ),
                streams.size() == expected );

            long found = 0;

            String root1 =
                "/BogusSAT/SC001/Onboard_Tables/Calibration_Offsets";

            for ( XTCETMStream stream : streams ) {
                if ( stream.getName().equals( "CALTABLE" ) == true ) {
                    Assert.assertTrue( "Stream CALTABLE should have root " + root1,
                                       stream.getStreamRootContainer().getFullPath().equals( root1 ) );
                    ++found;
                }
            }

            Assert.assertTrue( "Verified " + Long.toString( found ) + " of " +
                               Long.toString( expected ) + " streams",
                               found == expected );

            expected = 0;

            ss = db_.getSpaceSystem( "/BogusSAT/SC001/BusElectronics" );

            streams = ss.getStreams();

            Assert.assertTrue( "Should have found " +
                Long.toString( expected ) + " streams in " + ss.getName() +
                ", but found instead " +
                Long.toString( streams.size() ),
                streams.size() == expected );

        } catch ( Exception ex ) {
            //ex.printStackTrace();
            Assert.fail( ex.getLocalizedMessage() );
        }

    }

    @Test
    public void checkSimpleInheritancePath() {

        final String methodName =
            Thread.currentThread().getStackTrace()[1].getMethodName();

        System.out.println( "Test Case: " + methodName + "()" );

        String containerName =
            "/BogusSAT/SC001/Onboard_Tables/Calibration_Offsets";

        String expectedPath =
            "/Calibration_Offsets";

        try {

            XTCETMContainer container = db_.getContainer( containerName );

            String iPath = container.getInheritancePath();

            Assert.assertTrue( "Incorrect inheritance path '" + iPath + "' " +
                               "expected '" + expectedPath + "' for " +
                               containerName,
                               iPath.equals( expectedPath ) == true );

        } catch ( Exception ex ) {
            //ex.printStackTrace();
            Assert.fail( ex.getLocalizedMessage() );
        }

    }

    @Test
    public void checkLongerInheritancePath() {

        final String methodName =
            Thread.currentThread().getStackTrace()[1].getMethodName();

        System.out.println( "Test Case: " + methodName + "()" );

        String containerName =
            "/BogusSAT/SC001/ECSS_SpacePacket1";

        String expectedPath =
            "/CCSDSPacket/CCSDSTelemetryPacket/CCSDSPUSTelemetryPacket/" +
            "ECSS_Service_3_Subservice_25/ECSS_SpacePacket1";

        try {

            XTCETMContainer container = db_.getContainer( containerName );

            String iPath = container.getInheritancePath();

            Assert.assertTrue( "Incorrect inheritance path '" + iPath + "' " +
                               "expected '" + expectedPath + "' for " +
                               containerName,
                               iPath.equals( expectedPath ) == true );

        } catch ( Exception ex ) {
            //ex.printStackTrace();
            Assert.fail( ex.getLocalizedMessage() );
        }

    }

    @Test
    public void checkStreamContainers() {

        final String methodName =
            Thread.currentThread().getStackTrace()[1].getMethodName();

        System.out.println( "Test Case: " + methodName + "()" );

        try {

            XTCETMStream stream = db_.getStream( "CCSDS-TM" );

            long expected = 12;
            long found    = 0;

            Assert.assertTrue( "Should have found CCSDS-TM stream",
                               stream != null );

            List<XTCETMContainer> containers = stream.getContainers();
            for ( XTCETMContainer container : containers ) {
                System.out.println( "CCSDS-TM Contains: " +
                                    container.getInheritancePath() );
                ++found;
            }

            Assert.assertTrue( "Should have found " +
                Long.toString( expected ) + " containers in CCSDS-TM " +
                ", but found instead " + Long.toString( found ),
                found == expected );

        } catch ( Exception ex ) {
            //ex.printStackTrace();
            Assert.fail( ex.getLocalizedMessage() );
        }

    }

    @Test
    public void processSimpleStream() {

        final String methodName =
            Thread.currentThread().getStackTrace()[1].getMethodName();

        System.out.println( "Test Case: " + methodName + "()" );

        String containerName =
            "/BogusSAT/SC001/Onboard_Tables/Calibration_Offsets";

        String binFilename =
            "src/org/omg/space/xtce/database/Container-Calibration_Offsets.bin";

        try {

            XTCETMStream stream = db_.getStream( "CALTABLE" );

            BitSet rawBits = readBytesFromFile( binFilename );

            XTCEContainerContentModel model = stream.processStream( rawBits );

            Assert.assertTrue( "Processing CALTABLE should have been valid " +
                               "with file " + binFilename,
                               model.isValid() == true );

            Assert.assertTrue( "Stream should have found container " +
                               containerName + ", but it found instead " +
                               model.getContainerReference().getFullPath(),
                               model.getContainerReference().getFullPath().equals( containerName ) );

            List<XTCEContainerContentEntry> entries = model.getContentList();

            long items = 0;

            for ( XTCEContainerContentEntry entry : entries ) {

                if ( entry.getName().equals( "Battery_Voltage_Offset" ) ) {
                    ++items;
                    checkEntry( entry, "32", "0", "==3.5{cal}", "2.5", "", "" );
                } else if ( entry.getName().equals( "Solar_Array_Voltage_1_Offset" ) ) {
                    ++items;
                    checkEntry( entry, "32", "48", "==9{cal}", "2", "", "" );
                } else if ( entry.getName().equals( "Solar_Array_Voltage_2_Offset" ) ) {
                    ++items;
                    checkEntry( entry, "32", "80", "==-5{cal}", "1", "", "" );
                } else if ( entry.getName().equals( "Battery_Current_Offset" ) ) {
                    ++items;
                    checkEntry( entry, "64", "112", "==14.25{cal}", "-1.5", "", "" );
                } else if ( entry.getName().equals( "Default_CPU_Start_Mode" ) ) {
                    ++items;
                    checkEntry( entry, "8", "176", "==SAFEHOLD{cal}", "NORMAL", "", "" );
                }

            }

            Assert.assertTrue( "Container parameter count of " + containerName + " is " +
                Long.toString( items ) + " but should be 5 items",
                items == 5 );

            assertOnWarnings( model );

        } catch ( Exception ex ) {
            //ex.printStackTrace();
            Assert.fail( ex.getLocalizedMessage() );
        }

    }

    @Test
    public void calculateCompatibilitySimpleStream() {

        final String methodName =
            Thread.currentThread().getStackTrace()[1].getMethodName();

        System.out.println( "Test Case: " + methodName + "()" );

        String containerName =
            "/BogusSAT/SC001/Onboard_Tables/Calibration_Offsets";

        String binFilename =
            "src/org/omg/space/xtce/database/Container-Calibration_Offsets.bin";

        try {

            BitSet packetBits = readBytesFromFile( binFilename );

            XTCETMContainer container = db_.getContainer( containerName );

            XTCEContainerContentModel model =
                new XTCEContainerContentModel( container,
                                               db_.getSpaceSystemTree(),
                                               null,
                                               false );

            Assert.assertTrue( "Processing " + containerName +
                               " should have been valid ",
                               model.isValid() == true );

            Assert.assertTrue( "Binary should have been compatible (true)",
                               model.isProcessingCompatible( packetBits ) );

            assertOnWarnings( model );

        } catch ( Exception ex ) {
            //ex.printStackTrace();
            Assert.fail( ex.getLocalizedMessage() );
        }

    }

    @Test
    public void processContainerWithInheritance() {

        final String methodName =
            Thread.currentThread().getStackTrace()[1].getMethodName();

        System.out.println( "Test Case: " + methodName + "()" );

        String containerName = "/BogusSAT/SC001/CCSDS_SpacePacket1";

        String binFilename =
            "src/org/omg/space/xtce/database/Container-CCSDS_SpacePacket1.bin";

        try {

            XTCETMStream stream = db_.getStream( "CCSDS-TM" );

            BitSet rawBits = readBytesFromFile( binFilename );

            XTCEContainerContentModel model = stream.processStream( rawBits );

            Assert.assertTrue( "Processing CCSDS-TM should have been valid " +
                               "with file " + binFilename,
                               model.isValid() == true );

            Assert.assertTrue( "Stream should have found container " +
                               containerName + ", but it found instead " +
                               model.getContainerReference().getFullPath(),
                               model.getContainerReference().getFullPath().equals( containerName ) );

            List<XTCEContainerContentEntry> entries = model.getContentList();

            long items = 0;

            for ( XTCEContainerContentEntry entry : entries ) {

                if ( entry.getName().equals( "CCSDS_Packet_ID.Version" ) ) {
                    ++items;
                    checkEntry( entry, "3", "0", "==0{cal}", "0", "", "" );
                } else if ( entry.getName().equals( "CCSDS_Packet_ID.Type" ) ) {
                    ++items;
                    checkEntry( entry, "1", "3", "==TM{cal}", "", "", "" );
                } else if ( entry.getName().equals( "CCSDS_Packet_ID.SecHdrFlag" ) ) {
                    ++items;
                    checkEntry( entry, "1", "4", "==NotPresent{cal}", "", "", "" );
                } else if ( entry.getName().equals( "CCSDS_Packet_ID.APID" ) ) {
                    ++items;
                    checkEntry( entry, "11", "5", "==1{cal}", "2047", "", "" );
                } else if ( entry.getName().equals( "CCSDS_Packet_Sequence.GroupFlags" ) ) {
                    ++items;
                    checkEntry( entry, "2", "16", "==3{cal}", "3", "", "" );
                } else if ( entry.getName().equals( "CCSDS_Packet_Sequence.Count" ) ) {
                    ++items;
                    checkEntry( entry, "14", "18", "==3783{cal}", "", "", "" );
                } else if ( entry.getName().equals( "CCSDS_Packet_Length" ) ) {
                    ++items;
                    checkEntry( entry, "16", "32", "==11{cal}", "", "", "" );
                } else if ( entry.getName().equals( "Battery_Voltage" ) ) {
                    ++items;
                    checkEntry( entry, "32", "48", "==12.3{cal}", "", "", "" );
                } else if ( entry.getName().equals( "Battery_Current" ) ) {
                    ++items;
                    checkEntry( entry, "32", "80", "==0.5{cal}", "", "", "" );
                } else if ( entry.getName().equals( "Battery_Charge_Mode" ) ) {
                    ++items;
                    checkEntry( entry, "1", "112", "==CHARGE{cal}", "", "", "" );
                } else if ( entry.getName().equals( "SomeParameter" ) ) {
                    ++items;
                    checkEntry( entry, "7", "113", "==5{cal}", "", "", "" );
                } else if ( entry.getName().equals( "Solar_Array_Voltage_1" ) ) {
                    ++items;
                    checkEntry( entry, "12", "120", "==23.0{cal}", "", "", "" );
                } else if ( entry.getName().equals( "Solar_Array_Voltage_2" ) ) {
                    ++items;
                    checkEntry( entry, "12", "132", "==23.0{cal}", "", "", "" );
                }

            }

            Assert.assertTrue( "Container parameter count of " + containerName + " is " +
                Long.toString( items ) + " but should be 13 items",
                items == 13 );

            assertOnWarnings( model );

        } catch ( Exception ex ) {
            //ex.printStackTrace();
            Assert.fail( ex.getLocalizedMessage() );
        }

    }

    @Test
    public void calculateCompatibilityContainerWithInheritance() {

        final String methodName =
            Thread.currentThread().getStackTrace()[1].getMethodName();

        System.out.println( "Test Case: " + methodName + "()" );

        String containerName = "/BogusSAT/SC001/CCSDS_SpacePacket1";

        String binFilename =
            "src/org/omg/space/xtce/database/Container-CCSDS_SpacePacket1.bin";

        try {

            BitSet packetBits = readBytesFromFile( binFilename );

            XTCETMContainer container = db_.getContainer( containerName );

            XTCEContainerContentModel model =
                new XTCEContainerContentModel( container,
                                               db_.getSpaceSystemTree(),
                                               null,
                                               false );

            Assert.assertTrue( "Processing " + containerName +
                               " should have been valid ",
                               model.isValid() == true );

            Assert.assertTrue( "Binary should have been compatible (true)",
                               model.isProcessingCompatible( packetBits ) );

            assertOnWarnings( model );

        } catch ( Exception ex ) {
            //ex.printStackTrace();
            Assert.fail( ex.getLocalizedMessage() );
        }

    }

    @Test
    public void processContainerWithWrongInheritance() {

        final String methodName =
            Thread.currentThread().getStackTrace()[1].getMethodName();

        System.out.println( "Test Case: " + methodName + "()" );

        String containerName = "/BogusSAT/SC001/CCSDS_SpacePacket1";

        String binFilename =
            "src/org/omg/space/xtce/database/Container-CCSDS_SpacePacket1-Bad.bin";

        try {

            XTCETMStream stream = db_.getStream( "CCSDS-TM" );

            BitSet rawBits = readBytesFromFile( binFilename );

            XTCEContainerContentModel model = stream.processStream( rawBits );

            Assert.fail( "Processing CCSDS-TM should have been invalid " +
                         "with file " + binFilename );

        } catch ( Exception ex ) {
            // expect to get an exception here
        }

    }

    @Test
    public void calculateCompatibilityContainerWithWrongInheritance() {

        final String methodName =
            Thread.currentThread().getStackTrace()[1].getMethodName();

        System.out.println( "Test Case: " + methodName + "()" );

        String containerName = "/BogusSAT/SC001/CCSDS_SpacePacket1";

        String binFilename =
            "src/org/omg/space/xtce/database/Container-CCSDS_SpacePacket1-Bad.bin";

        try {

            BitSet packetBits = readBytesFromFile( binFilename );

            XTCETMContainer container = db_.getContainer( containerName );

            XTCEContainerContentModel model =
                new XTCEContainerContentModel( container,
                                               db_.getSpaceSystemTree(),
                                               null,
                                               false );

            Assert.assertTrue( "Processing " + containerName +
                               " should have been valid ",
                               model.isValid() == true );

            Assert.assertFalse( "Binary should NOT have been compatible (false)",
                                model.isProcessingCompatible( packetBits ) );

            assertOnWarnings( model );

        } catch ( Exception ex ) {
            // expect to get an exception here
        }

    }

    @Test
    public void processContainerWithInheritanceAndIncludesFalse() {

        final String methodName =
            Thread.currentThread().getStackTrace()[1].getMethodName();

        System.out.println( "Test Case: " + methodName + "()" );

        String containerName = "/BogusSAT/SC001/ECSS_SpacePacket2";

        String binFilename =
            "src/org/omg/space/xtce/database/Container-ECSS_3_25_HK-ECSS_SpacePacket2-NoInc.bin";

        try {

            XTCETMStream stream = db_.getStream( "CCSDS-TM" );

            BitSet rawBits = readBytesFromFile( binFilename );

            XTCEContainerContentModel model = stream.processStream( rawBits );

            Assert.assertTrue( "Processing CCSDS-TM should have been valid " +
                               "with file " + binFilename,
                               model.isValid() == true );

            Assert.assertTrue( "Stream should have found container " +
                               containerName + ", but it found instead " +
                               model.getContainerReference().getFullPath(),
                               model.getContainerReference().getFullPath().equals( containerName ) );

            List<XTCEContainerContentEntry> entries = model.getContentList();

            for ( String warning : model.getWarnings() ) {
                System.out.println( "Container Model Warning: " + warning );
            }

            long items = 0;

            for ( XTCEContainerContentEntry entry : entries ) {

                if ( entry.getName().equals( "CCSDS_Packet_ID.Version" ) ) {
                    ++items;
                    checkEntry( entry, "3", "0", "==0{cal}", "0", "", "" );
                } else if ( entry.getName().equals( "CCSDS_Packet_ID.Type" ) ) {
                    ++items;
                    checkEntry( entry, "1", "3", "==TM{cal}", "", "", "" );
                } else if ( entry.getName().equals( "CCSDS_Packet_ID.SecHdrFlag" ) ) {
                    ++items;
                    checkEntry( entry, "1", "4", "==Present{cal}", "", "", "" );
                } else if ( entry.getName().equals( "CCSDS_Packet_ID.APID" ) ) {
                    ++items;
                    checkEntry( entry, "11", "5", "==100{cal}", "2047", "", "" );
                } else if ( entry.getName().equals( "CCSDS_Packet_Sequence.GroupFlags" ) ) {
                    ++items;
                    checkEntry( entry, "2", "16", "==3{cal}", "3", "", "" );
                } else if ( entry.getName().equals( "CCSDS_Packet_Sequence.Count" ) ) {
                    ++items;
                    checkEntry( entry, "14", "18", "==3784{cal}", "", "", "" );
                } else if ( entry.getName().equals( "CCSDS_Packet_Length" ) ) {
                    ++items;
                    checkEntry( entry, "16", "32", "==17{cal}", "", "", "" );
                } else if ( entry.getName().equals( "PUS_Data_Field_Header.Spare1" ) ) {
                    ++items;
                    checkEntry( entry, "1", "48", "==0{cal}", "0", "", "" );
                } else if ( entry.getName().equals( "PUS_Data_Field_Header.Version" ) ) {
                    ++items;
                    checkEntry( entry, "3", "49", "==1{cal}", "1", "", "" );
                } else if ( entry.getName().equals( "PUS_Data_Field_Header.Spare4" ) ) {
                    ++items;
                    checkEntry( entry, "4", "52", "==0{cal}", "0", "", "" );
                } else if ( entry.getName().equals( "PUS_Data_Field_Header.Service" ) ) {
                    ++items;
                    checkEntry( entry, "8", "56", "==3{cal}", "", "", "" );
                } else if ( entry.getName().equals( "PUS_Data_Field_Header.Subservice" ) ) {
                    ++items;
                    checkEntry( entry, "8", "64", "==25{cal}", "", "", "" );
                } else if ( entry.getName().equals( "PUS_Data_Field_Header.SeqCount" ) ) {
                    ++items;
                    checkEntry( entry, "8", "72", "==241{cal}", "", "", "" );
                } else if ( entry.getName().equals( "PUS_Data_Field_Header.Destination" ) ) {
                    ++items;
                    checkEntry( entry, "8", "80", "==3{cal}", "", "", "" );
                } else if ( entry.getName().equals( "PUS_Time" ) ) {
                    ++items;
                    checkEntry( entry, "64", "", "", "", "APPL_TIME_CODE!=NotUsed{cal}", "" );
                } else if ( entry.getName().equals( "PUS_Error_Control_Field" ) ) {
                    ++items;
                    checkEntry( entry, "16", "", "", "", "TM_CHECKSUM_TYPE!=NotUsed{cal}", "" );
                } else if ( entry.getName().equals( "PUS_Structure_ID" ) ) {
                    ++items;
                    checkEntry( entry, "8", "88", "==ECSS_SpacePacket2{cal}", "", "", "" );
                } else if ( entry.getName().equals( "Battery_Voltage" ) ) {
                    ++items;
                    checkEntry( entry, "32", "96", "==12.3{cal}", "", "", "" );
                } else if ( entry.getName().equals( "Battery_Current" ) ) {
                    ++items;
                    checkEntry( entry, "32", "128", "==0.5{cal}", "", "", "" );
                } else if ( entry.getName().equals( "Flag_Parameter" ) ) {
                    ++items;
                    checkEntry( entry, "8", "160", "==5{cal}", "", "", "" );
                } else if ( entry.getName().equals( "Optional_Parameter" ) ) {
                    ++items;
                    checkEntry( entry, "16", "", "", "", "Flag_Parameter==1{cal}", "" );
                } else if ( entry.getName().equals( "Solar_Array_Voltage_1" ) ) {
                    ++items;
                    checkEntry( entry, "12", "168", "==5.0{cal}", "", "", "" );
                } else if ( entry.getName().equals( "Solar_Array_Voltage_2" ) ) {
                    ++items;
                    checkEntry( entry, "12", "180", "==23.0{cal}", "", "", "" );
                }

            }

            Assert.assertTrue( "Container parameter count of " + containerName + " is " +
                Long.toString( items ) + " but should be 23 items",
                items == 23 );

            assertOnWarnings( model );

        } catch ( Exception ex ) {
            //ex.printStackTrace();
            Assert.fail( ex.getLocalizedMessage() );
        }

    }

    @Test
    public void calculateCompatibilityContainerWithInheritanceAndIncludesFalse() {

        final String methodName =
            Thread.currentThread().getStackTrace()[1].getMethodName();

        System.out.println( "Test Case: " + methodName + "()" );

        String containerName = "/BogusSAT/SC001/ECSS_SpacePacket2";

        String binFilename =
            "src/org/omg/space/xtce/database/Container-ECSS_3_25_HK-ECSS_SpacePacket2-NoInc.bin";

        try {

            BitSet packetBits = readBytesFromFile( binFilename );

            XTCETMContainer container = db_.getContainer( containerName );

            XTCEContainerContentModel model =
                new XTCEContainerContentModel( container,
                                               db_.getSpaceSystemTree(),
                                               null,
                                               false );

            Assert.assertTrue( "Processing " + containerName +
                               " should have been valid ",
                               model.isValid() == true );

            Assert.assertTrue( "Binary should have been compatible (true)",
                               model.isProcessingCompatible( packetBits ) );

            assertOnWarnings( model );

        } catch ( Exception ex ) {
            //ex.printStackTrace();
            Assert.fail( ex.getLocalizedMessage() );
        }

    }

    @Test
    public void processContainerWithInheritanceAndIncludesTrue() {

        final String methodName =
            Thread.currentThread().getStackTrace()[1].getMethodName();

        System.out.println( "Test Case: " + methodName + "()" );

        String containerName = "/BogusSAT/SC001/ECSS_SpacePacket2";

        String binFilename =
            "src/org/omg/space/xtce/database/Container-ECSS_3_25_HK-ECSS_SpacePacket2-Inc.bin";

        try {

            XTCETMStream stream = db_.getStream( "CCSDS-TM" );

            BitSet rawBits = readBytesFromFile( binFilename );

            XTCEContainerContentModel model = stream.processStream( rawBits );

            Assert.assertTrue( "Processing CCSDS-TM should have been valid " +
                               "with file " + binFilename,
                               model.isValid() == true );

            Assert.assertTrue( "Stream should have found container " +
                               containerName + ", but it found instead " +
                               model.getContainerReference().getFullPath(),
                               model.getContainerReference().getFullPath().equals( containerName ) );

            List<XTCEContainerContentEntry> entries = model.getContentList();

            for ( String warning : model.getWarnings() ) {
                System.out.println( "Container Model Warning: " + warning );
            }

            long items = 0;

            for ( XTCEContainerContentEntry entry : entries ) {

                if ( entry.getName().equals( "CCSDS_Packet_ID.Version" ) ) {
                    ++items;
                    checkEntry( entry, "3", "0", "==0{cal}", "0", "", "" );
                } else if ( entry.getName().equals( "CCSDS_Packet_ID.Type" ) ) {
                    ++items;
                    checkEntry( entry, "1", "3", "==TM{cal}", "", "", "" );
                } else if ( entry.getName().equals( "CCSDS_Packet_ID.SecHdrFlag" ) ) {
                    ++items;
                    checkEntry( entry, "1", "4", "==Present{cal}", "", "", "" );
                } else if ( entry.getName().equals( "CCSDS_Packet_ID.APID" ) ) {
                    ++items;
                    checkEntry( entry, "11", "5", "==100{cal}", "2047", "", "" );
                } else if ( entry.getName().equals( "CCSDS_Packet_Sequence.GroupFlags" ) ) {
                    ++items;
                    checkEntry( entry, "2", "16", "==3{cal}", "3", "", "" );
                } else if ( entry.getName().equals( "CCSDS_Packet_Sequence.Count" ) ) {
                    ++items;
                    checkEntry( entry, "14", "18", "==3784{cal}", "", "", "" );
                } else if ( entry.getName().equals( "CCSDS_Packet_Length" ) ) {
                    ++items;
                    checkEntry( entry, "16", "32", "==19{cal}", "", "", "" );
                } else if ( entry.getName().equals( "PUS_Data_Field_Header.Spare1" ) ) {
                    ++items;
                    checkEntry( entry, "1", "48", "==0{cal}", "0", "", "" );
                } else if ( entry.getName().equals( "PUS_Data_Field_Header.Version" ) ) {
                    ++items;
                    checkEntry( entry, "3", "49", "==1{cal}", "1", "", "" );
                } else if ( entry.getName().equals( "PUS_Data_Field_Header.Spare4" ) ) {
                    ++items;
                    checkEntry( entry, "4", "52", "==0{cal}", "0", "", "" );
                } else if ( entry.getName().equals( "PUS_Data_Field_Header.Service" ) ) {
                    ++items;
                    checkEntry( entry, "8", "56", "==3{cal}", "", "", "" );
                } else if ( entry.getName().equals( "PUS_Data_Field_Header.Subservice" ) ) {
                    ++items;
                    checkEntry( entry, "8", "64", "==25{cal}", "", "", "" );
                } else if ( entry.getName().equals( "PUS_Data_Field_Header.SeqCount" ) ) {
                    ++items;
                    checkEntry( entry, "8", "72", "==241{cal}", "", "", "" );
                } else if ( entry.getName().equals( "PUS_Data_Field_Header.Destination" ) ) {
                    ++items;
                    checkEntry( entry, "8", "80", "==3{cal}", "", "", "" );
                } else if ( entry.getName().equals( "PUS_Time" ) ) {
                    ++items;
                    checkEntry( entry, "64", "", "", "", "APPL_TIME_CODE!=NotUsed{cal}", "" );
                } else if ( entry.getName().equals( "PUS_Error_Control_Field" ) ) {
                    ++items;
                    checkEntry( entry, "16", "", "", "", "TM_CHECKSUM_TYPE!=NotUsed{cal}", "" );
                } else if ( entry.getName().equals( "PUS_Structure_ID" ) ) {
                    ++items;
                    checkEntry( entry, "8", "88", "==ECSS_SpacePacket2{cal}", "", "", "" );
                } else if ( entry.getName().equals( "Battery_Voltage" ) ) {
                    ++items;
                    checkEntry( entry, "32", "96", "==12.3{cal}", "", "", "" );
                } else if ( entry.getName().equals( "Battery_Current" ) ) {
                    ++items;
                    checkEntry( entry, "32", "128", "==0.5{cal}", "", "", "" );
                } else if ( entry.getName().equals( "Flag_Parameter" ) ) {
                    ++items;
                    checkEntry( entry, "8", "160", "==1{cal}", "", "", "" );
                } else if ( entry.getName().equals( "Optional_Parameter" ) ) {
                    ++items;
                    checkEntry( entry, "16", "168", "==61455{cal}", "", "Flag_Parameter==1{cal}", "" );
                } else if ( entry.getName().equals( "Solar_Array_Voltage_1" ) ) {
                    ++items;
                    checkEntry( entry, "12", "184", "==5.0{cal}", "", "", "" );
                } else if ( entry.getName().equals( "Solar_Array_Voltage_2" ) ) {
                    ++items;
                    checkEntry( entry, "12", "196", "==23.0{cal}", "", "", "" );
                }

            }

            Assert.assertTrue( "Container parameter count of " + containerName + " is " +
                Long.toString( items ) + " but should be 23 items",
                items == 23 );

            assertOnWarnings( model );

        } catch ( Exception ex ) {
            //ex.printStackTrace();
            Assert.fail( ex.getLocalizedMessage() );
        }

    }

    @Test
    public void calculateCompatibilityContainerWithInheritanceAndIncludesTrue() {

        final String methodName =
            Thread.currentThread().getStackTrace()[1].getMethodName();

        System.out.println( "Test Case: " + methodName + "()" );

        String containerName = "/BogusSAT/SC001/ECSS_SpacePacket2";

        String binFilename =
            "src/org/omg/space/xtce/database/Container-ECSS_3_25_HK-ECSS_SpacePacket2-Inc.bin";

        try {

            BitSet packetBits = readBytesFromFile( binFilename );

            XTCETMContainer container = db_.getContainer( containerName );

            XTCEContainerContentModel model =
                new XTCEContainerContentModel( container,
                                               db_.getSpaceSystemTree(),
                                               null,
                                               false );

            Assert.assertTrue( "Processing " + containerName +
                               " should have been valid ",
                               model.isValid() == true );

            Assert.assertTrue( "Binary should have been compatible (true)",
                               model.isProcessingCompatible( packetBits ) );

            assertOnWarnings( model );

        } catch ( Exception ex ) {
            //ex.printStackTrace();
            Assert.fail( ex.getLocalizedMessage() );
        }

    }

    @Test
    public void process40000Packets() {

        final String methodName =
            Thread.currentThread().getStackTrace()[1].getMethodName();

        System.out.println( "Test Case: " + methodName + "()" );

        String binFilename =
            "src/org/omg/space/xtce/database/Container-10000Packets.bin";

        long startTime = System.currentTimeMillis();

        try {

            XTCETMStream stream = db_.getStream( "CCSDS-TM" );

            File binFile = new File( binFilename );

            InputStream fstream = new FileInputStream( binFile );

            int byteValue;

            XTCEContainerContentModel model;

            for ( int iii = 0; iii < 10000; ++iii ) {

                ByteArrayOutputStream buffer1 = new ByteArrayOutputStream();
                ByteArrayOutputStream buffer2 = new ByteArrayOutputStream();
                ByteArrayOutputStream buffer3 = new ByteArrayOutputStream();
                ByteArrayOutputStream buffer4 = new ByteArrayOutputStream();

                for ( int bbb = 0; bbb < 18; ++bbb ) {
                    if ( ( byteValue = fstream.read() ) != -1 ) {
                        buffer1.write( byteValue );
                    }
                }

                for ( int bbb = 0; bbb < 15; ++bbb ) {
                    if ( ( byteValue = fstream.read() ) != -1 ) {
                        buffer2.write( byteValue );
                    }
                }

                for ( int bbb = 0; bbb < 24; ++bbb ) {
                    if ( ( byteValue = fstream.read() ) != -1 ) {
                        buffer3.write( byteValue );
                    }
                }

                for ( int bbb = 0; bbb < 26; ++bbb ) {
                    if ( ( byteValue = fstream.read() ) != -1 ) {
                        buffer4.write( byteValue );
                    }
                }

                model = stream.processStream( buffer1.toByteArray() );

                Assert.assertTrue( "Processing CCSDS-TM should have been valid " +
                                   "with file " + binFilename + " packet 1 " +
                                   " on iteration " + Integer.toString( iii ),
                                   model.isValid() == true );

                assertOnWarnings( model );

                model = stream.processStream( buffer2.toByteArray() );

                Assert.assertTrue( "Processing CCSDS-TM should have been valid " +
                                   "with file " + binFilename + " packet 2 " +
                                   " on iteration " + Integer.toString( iii ),
                                   model.isValid() == true );

                assertOnWarnings( model );

                model = stream.processStream( buffer3.toByteArray() );

                Assert.assertTrue( "Processing CCSDS-TM should have been valid " +
                                   "with file " + binFilename + " packet 3 " +
                                   " on iteration " + Integer.toString( iii ),
                                   model.isValid() == true );

                assertOnWarnings( model );

                model = stream.processStream( buffer4.toByteArray() );

                Assert.assertTrue( "Processing CCSDS-TM should have been valid " +
                                   "with file " + binFilename + " packet 4 " +
                                   " on iteration " + Integer.toString( iii ),
                                   model.isValid() == true );

                assertOnWarnings( model );

                if ( iii % 1000 == 0 ) {
                    System.out.println( String.format( "%06d", iii * 4 ) );
                }

            }

        } catch ( Exception ex ) {
            //ex.printStackTrace();
            Assert.fail( ex.getLocalizedMessage() );
        }

        long estimatedTime = System.currentTimeMillis() - startTime;

        System.out.println( "Elapsed Time " +
            Double.toString( estimatedTime / 1000.0 ) + " seconds" );

    }

    private void checkEntry( XTCEContainerContentEntry entry,
                             String                    sizeInBits,
                             String                    startBit,
                             String                    value,
                             String                    initialValue,
                             String                    condition,
                             String                    repeat ) {

        if ( entry.getRawSizeInBits().equals( sizeInBits ) == false ) {
            Assert.fail( "Container parameter " + entry.getName() +
                " should be " + sizeInBits + " bits, but it is " +
                entry.getRawSizeInBits() + " bits instead" );
        }

        if ( entry.getStartBit().equals( startBit ) == false ) {
            Assert.fail( "Container parameter " + entry.getName() +
                " should start at bit " + startBit + " but it starts at bit " +
                entry.getStartBit() + " instead" );
            if ( startBit.isEmpty() == true ) {
                Assert.assertTrue( "Container parameter " + entry.getName() +
                    " should report 'not in use' if expected start bit is " +
                    "empty",
                    entry.isCurrentlyInUse() == false );
            }
        }

        String itemValue = "";
        if ( entry.getValue() != null ) {
            itemValue = entry.getValue().toStringWithoutParameter();
        }

        if ( itemValue.equals( value ) == false ) {
            Assert.fail( "Container parameter " + entry.getName() +
                " should have value of '" + value + "' but it is '" +
                itemValue + "' instead" );
        }

        if ( entry.getInitialValue().equals( initialValue ) == false ) {
            Assert.fail( "Container parameter " + entry.getName() +
                " should have initial value of '" + initialValue +
                "' but it is '" + entry.getInitialValue() + "' instead" );
        }

        if ( entry.getConditions().equals( condition ) == false ) {
            Assert.fail( "Container parameter " + entry.getName() +
                " should have conditions of '" + condition +
                "' but it is '" + entry.getConditions() + "' instead" );
        }

        if ( entry.getRepeatParameterInfo().equals( repeat ) == false ) {
            Assert.fail( "Container parameter " + entry.getName() +
                " should have repeat info of '" + repeat +
                "' but it is '" + entry.getRepeatParameterInfo() +
                "' instead" );
        }

        StringBuilder sb = new StringBuilder();
        sb.append( "PASSED: " );
        sb.append( entry.getName() );
        sb.append( " Size: '" );
        sb.append( sizeInBits );
        sb.append( "' StartBit: '" );
        sb.append( startBit );
        sb.append( "' Value: '" );
        sb.append( value );
        sb.append( "' Initial Value: '" );
        sb.append( initialValue );
        sb.append( "' Condition: '" );
        sb.append( condition );
        sb.append( "' Repeat: '" );
        sb.append( repeat );
        sb.append( "'" );

        System.out.println( sb.toString() );

    }

    private void assertOnWarnings( XTCEContainerContentModel model ) {

        List<String> warnings = model.getWarnings();

        if ( warnings.isEmpty() == false ) {
            Assert.fail( "First warning: " + warnings.get( 0 ) );
        }

    }

    private BitSet readBytesFromFile( String binFilename ) {

        ByteArrayOutputStream buffer = new ByteArrayOutputStream();

        try {

            File binFile = new File( binFilename );

            InputStream stream = new FileInputStream( binFile );

            int byteValue;

            StringBuilder sb = new StringBuilder( "Read From File 0x" );

            while ( ( byteValue = stream.read() ) != -1 ) {
                buffer.write( byteValue );
                sb.append( String.format( "%02x", byteValue ) );
            }

            System.out.println( sb.toString() );

        } catch ( FileNotFoundException ex ) {
            Assert.fail( "Must be able to read sample binary file: " +
                         binFilename );
        } catch ( IOException ex ) {
            Assert.fail( "Trouble reading file: " + binFilename +
                         " because " + ex.getLocalizedMessage() );
        }

        byte[] bytes = buffer.toByteArray();
        BitSet bits  = XTCEFunctions.getBitSetFromStreamByteArray( bytes );

        //for ( int iii = 0; iii < bits.size(); ++iii ) {
        //    System.out.println( String.format( "Bit %03d %d", iii, bits.get( iii ) ? 1 : 0 ) );

        return bits;

    }

    private void loadDocument() throws XTCEDatabaseException {

        //System.out.println( "Loading the BogusSat-2.xml demo database" );

        String file = "src/org/omg/space/xtce/database/BogusSat-2.xml";

        db_ = new XTCEDatabase( new File( file ), false, false, true );

    }

    // Private Data Members

    private XTCEDatabase  db_  = null;

}
