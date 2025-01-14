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

package org.xtce.toolkit;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author dovereem
 */

public class ContainerProcessingTest {
    
    public ContainerProcessingTest() {

        try {
            loadDocuments();
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
    public void lookupSingleContainerFromDatabase() {

        final String methodName =
            Thread.currentThread().getStackTrace()[1].getMethodName();

        System.out.println( "Test Case: " + methodName + "()" );

        String containerName;

        containerName = "/BogusSAT/SC001/CCSDS_SpacePacket1";

        try {

            XTCETMContainer container = db_.getContainer( containerName );

            Assert.assertTrue( "Container found '" + container.getFullPath() +
                "' should have been '" + containerName + "'",
                container.getFullPath().equals( containerName ) == true );

        } catch ( Exception ex ) {
            //ex.printStackTrace();
            Assert.fail( ex.getLocalizedMessage() );
        }

        containerName = "/BogusSAT/SC001/CCSDS_SpacePacketX";

        try {

            XTCETMContainer container = db_.getContainer( containerName );

            Assert.fail( "Exception should have been thrown on '" +
                containerName + "', which does not exist" );

        } catch ( Exception ex ) {
            // expected
        }

    }

    @Test
    public void lookupSingleContainerFromSpaceSystem() {

        final String methodName =
            Thread.currentThread().getStackTrace()[1].getMethodName();

        System.out.println( "Test Case: " + methodName + "()" );

        try {

            XTCESpaceSystem ss = db_.getSpaceSystem( "/BogusSAT/SC001" );

            String containerName;

            containerName = "CCSDS_SpacePacket2";

            try {

                XTCETMContainer container = ss.getContainer( containerName );

                Assert.assertTrue( "Container found '" + container.getName() +
                    "' should have been '" + containerName + "'",
                    container.getName().equals( containerName ) == true );

            } catch ( Exception ex ) {
                //ex.printStackTrace();
                Assert.fail( ex.getLocalizedMessage() );
            }

            containerName = "CCSDS_SpacePacketX";

            try {

                XTCETMContainer container = ss.getContainer( containerName );

                Assert.fail( "Exception should have been thrown on '" +
                    containerName + "', which does not exist" );

            } catch ( Exception ex ) {
                // expected
            }

        } catch ( Exception ex ) {
            //ex.printStackTrace();
            Assert.fail( ex.getLocalizedMessage() );
        }

    }

    @Test
    public void lookupAllContainersFromDatabase() {

        final String methodName =
            Thread.currentThread().getStackTrace()[1].getMethodName();

        System.out.println( "Test Case: " + methodName + "()" );

        try {

            List<XTCETMContainer> containers = db_.getContainers();

            long expected = 31;

            Assert.assertTrue( "Should have found " +
                Long.toString( expected ) + " containers, but found instead " +
                Long.toString( containers.size() ),
                containers.size() == expected );

        } catch ( Exception ex ) {
            //ex.printStackTrace();
            Assert.fail( ex.getLocalizedMessage() );
        }

    }

    @Test
    public void lookupAllContainersFromSpaceSystem() {

        final String methodName =
            Thread.currentThread().getStackTrace()[1].getMethodName();

        System.out.println( "Test Case: " + methodName + "()" );

        try {

            XTCESpaceSystem ss = db_.getSpaceSystem( "/BogusSAT/SC001" );

            List<XTCETMContainer> containers = ss.getContainers();

            long expected = 13;

            Assert.assertTrue( "Should have found " +
                Long.toString( expected ) + " containers, but found instead " +
                Long.toString( containers.size() ),
                containers.size() == expected );

        } catch ( Exception ex ) {
            //ex.printStackTrace();
            Assert.fail( ex.getLocalizedMessage() );
        }

    }

    @Test
    public void lookupMultipleContainersFromDatabase() {

        final String methodName =
            Thread.currentThread().getStackTrace()[1].getMethodName();

        System.out.println( "Test Case: " + methodName + "()" );

        try {

            List<XTCETMContainer> containers = db_.getContainers( "*ECSS*" );

            long expected = 7;

            Assert.assertTrue( "'*ECSS*' Should have found " +
                Long.toString( expected ) + " containers, but found instead " +
                Long.toString( containers.size() ),
                containers.size() == expected );

            expected = 0;

            containers = db_.getContainers( "*foo*" );

            Assert.assertTrue( "'*foo*' Should have found " +
                Long.toString( expected ) + " containers, but found instead " +
                Long.toString( containers.size() ),
                containers.size() == expected );

        } catch ( Exception ex ) {
            //ex.printStackTrace();
            Assert.fail( ex.getLocalizedMessage() );
        }

    }

    @Test
    public void lookupMultipleContainersFromSpaceSystem() {

        final String methodName =
            Thread.currentThread().getStackTrace()[1].getMethodName();

        System.out.println( "Test Case: " + methodName + "()" );

        try {

            XTCESpaceSystem ss = db_.getSpaceSystem( "/BogusSAT/SC001" );

            List<XTCETMContainer> containers = ss.getContainers( "*Service*" );

            long expected = 4;

            Assert.assertTrue( "'*Service*' Should have found " +
                Long.toString( expected ) + " containers, but found instead " +
                Long.toString( containers.size() ),
                containers.size() == expected );

            expected = 0;

            containers = ss.getContainers( "*foo*" );

            Assert.assertTrue( "'*foo*' Should have found " +
                Long.toString( expected ) + " containers, but found instead " +
                Long.toString( containers.size() ),
                containers.size() == expected );

        } catch ( Exception ex ) {
            //ex.printStackTrace();
            Assert.fail( ex.getLocalizedMessage() );
        }

    }

    @Test
    public void processSimpleContainer() {

        final String methodName =
            Thread.currentThread().getStackTrace()[1].getMethodName();

        System.out.println( "Test Case: " + methodName + "()" );

        String containerName =
            "/BogusSAT/SC001/Onboard_Tables/Calibration_Offsets";

        try {

            XTCETMContainer container = db_.getContainer( containerName );

            XTCEContainerContentModel model =
                db_.processContainer( container, null, false );

            long sizeInBytes = model.getTotalSize();

            Assert.assertTrue( "Container size of " + containerName + " is " +
                Long.toString( sizeInBytes ) + " but should be 184 bits",
                sizeInBytes == 184 );

            List<XTCEContainerContentEntry> entries = model.getContentList();

            long items = 0;

            for ( XTCEContainerContentEntry entry : entries ) {

                switch (entry.getName()) {
                    case "Battery_Voltage_Offset":
                        ++items;
                        checkEntry( entry, "32", "0", "", "2.5", "", "" );
                        break;
                    case "Solar_Array_Voltage_1_Offset":
                        ++items;
                        checkEntry( entry, "32", "48", "", "2", "", "" );
                        break;
                    case "Solar_Array_Voltage_2_Offset":
                        ++items;
                        checkEntry( entry, "32", "80", "", "1", "", "" );
                        break;
                    case "Battery_Current_Offset":
                        ++items;
                        checkEntry( entry, "64", "112", "", "-1.5", "", "" );
                        break;
                    case "Default_CPU_Start_Mode":
                        ++items;
                        checkEntry( entry, "8", "176", "", "NORMAL", "", "" );
                        break;
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
    public void processContainerWithInheritance() {

        final String methodName =
            Thread.currentThread().getStackTrace()[1].getMethodName();

        System.out.println( "Test Case: " + methodName + "()" );

        String containerName = "/BogusSAT/SC001/CCSDS_SpacePacket1";

        try {

            XTCETMContainer container = db_.getContainer( containerName );

            XTCEContainerContentModel model =
                db_.processContainer( container, null, false );

            long sizeInBytes = model.getTotalSize();

            Assert.assertTrue( "Container size of " + containerName + " is " +
                Long.toString( sizeInBytes ) + " but should be 144 bits",
                sizeInBytes == 144 );

            List<XTCEContainerContentEntry> entries = model.getContentList();

            long items = 0;

            for ( XTCEContainerContentEntry entry : entries ) {

                switch (entry.getName()) {
                    case "CCSDS_Packet_ID.Version":
                        ++items;
                        checkEntry( entry, "3", "0", "==0{cal}", "0", "", "" );
                        break;
                    case "CCSDS_Packet_ID.Type":
                        ++items;
                        checkEntry( entry, "1", "3", "==TM{cal}", "", "", "" );
                        break;
                    case "CCSDS_Packet_ID.SecHdrFlag":
                        ++items;
                        checkEntry( entry, "1", "4", "==NotPresent{cal}", "", "", "" );
                        break;
                    case "CCSDS_Packet_ID.APID":
                        ++items;
                        checkEntry( entry, "11", "5", "==1{cal}", "2047", "", "" );
                        break;
                    case "CCSDS_Packet_Sequence.GroupFlags":
                        ++items;
                        checkEntry( entry, "2", "16", "", "Standalone", "", "" );
                        break;
                    case "CCSDS_Packet_Sequence.Count":
                        ++items;
                        checkEntry( entry, "14", "18", "", "", "", "" );
                        break;
                    case "CCSDS_Packet_Length":
                        ++items;
                        checkEntry( entry, "16", "32", "", "", "", "" );
                        break;
                    case "Battery_Voltage":
                        ++items;
                        checkEntry( entry, "32", "48", "", "", "", "" );
                        break;
                    case "Battery_Current":
                        ++items;
                        checkEntry( entry, "32", "80", "", "", "", "" );
                        break;
                    case "Battery_Charge_Mode":
                        ++items;
                        checkEntry( entry, "1", "112", "", "", "", "" );
                        break;
                    case "SomeParameter":
                        ++items;
                        checkEntry( entry, "7", "113", "", "", "", "" );
                        break;
                    case "Solar_Array_Voltage_1":
                        ++items;
                        checkEntry( entry, "12", "120", "", "", "", "" );
                        break;
                    case "Solar_Array_Voltage_2":
                        ++items;
                        checkEntry( entry, "12", "132", "", "", "", "" );
                        break;
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
    public void processContainerWithInheritanceAndIncludesFalse() {

        final String methodName =
            Thread.currentThread().getStackTrace()[1].getMethodName();

        System.out.println( "Test Case: " + methodName + "()" );

        String containerName = "/BogusSAT/SC001/ECSS_SpacePacket2";

        try {

            XTCETMContainer container = db_.getContainer( containerName );

            XTCEContainerContentModel model =
                db_.processContainer( container, null, false );

            long sizeInBytes = model.getTotalSize();

            Assert.assertTrue( "Container size of " + containerName + " is " +
                Long.toString( sizeInBytes ) + " but should be 192 bits",
                sizeInBytes == 192 );

            List<XTCEContainerContentEntry> entries = model.getContentList();

            long items = 0;

            for ( XTCEContainerContentEntry entry : entries ) {

                switch (entry.getName()) {
                    case "CCSDS_Packet_ID.Version":
                        ++items;
                        checkEntry( entry, "3", "0", "==0{cal}", "0", "", "" );
                        break;
                    case "CCSDS_Packet_ID.Type":
                        ++items;
                        checkEntry( entry, "1", "3", "==TM{cal}", "", "", "" );
                        break;
                    case "CCSDS_Packet_ID.SecHdrFlag":
                        ++items;
                        checkEntry( entry, "1", "4", "==Present{cal}", "", "", "" );
                        break;
                    case "CCSDS_Packet_ID.APID":
                        ++items;
                        checkEntry( entry, "11", "5", "==100{cal}", "2047", "", "" );
                        break;
                    case "CCSDS_Packet_Sequence.GroupFlags":
                        ++items;
                        checkEntry( entry, "2", "16", "", "Standalone", "", "" );
                        break;
                    case "CCSDS_Packet_Sequence.Count":
                        ++items;
                        checkEntry( entry, "14", "18", "", "", "", "" );
                        break;
                    case "CCSDS_Packet_Length":
                        ++items;
                        checkEntry( entry, "16", "32", "", "", "", "" );
                        break;
                    case "PUS_Data_Field_Header.Spare1":
                        ++items;
                        checkEntry( entry, "1", "48", "==0{cal}", "0", "", "" );
                        break;
                    case "PUS_Data_Field_Header.Version":
                        ++items;
                        checkEntry( entry, "3", "49", "==1{cal}", "1", "", "" );
                        break;
                    case "PUS_Data_Field_Header.Spare4":
                        ++items;
                        checkEntry( entry, "4", "52", "==0{cal}", "0", "", "" );
                        break;
                    case "PUS_Data_Field_Header.Service":
                        ++items;
                        checkEntry( entry, "8", "56", "==3{cal}", "", "", "" );
                        break;
                    case "PUS_Data_Field_Header.Subservice":
                        ++items;
                        checkEntry( entry, "8", "64", "==25{cal}", "", "", "" );
                        break;
                    case "PUS_Data_Field_Header.SeqCount":
                        ++items;
                        checkEntry( entry, "8", "72", "", "", "", "" );
                        break;
                    case "PUS_Data_Field_Header.Destination":
                        ++items;
                        checkEntry( entry, "8", "80", "", "", "", "" );
                        break;
                    case "PUS_Time":
                        ++items;
                        checkEntry( entry, "64", "", "", "", "APPL_TIME_CODE!=NotUsed{cal}", "" );
                        break;
                    case "PUS_Error_Control_Field":
                        ++items;
                        checkEntry( entry, "16", "", "", "", "TM_CHECKSUM_TYPE!=NotUsed{cal}", "" );
                        break;
                    case "PUS_Structure_ID":
                        ++items;
                        checkEntry( entry, "8", "88", "==ECSS_SpacePacket2{cal}", "", "", "" );
                        break;
                    case "Battery_Voltage":
                        ++items;
                        checkEntry( entry, "32", "96", "", "", "", "" );
                        break;
                    case "Battery_Current":
                        ++items;
                        checkEntry( entry, "32", "128", "", "", "", "" );
                        break;
                    case "Flag_Parameter":
                        ++items;
                        checkEntry( entry, "8", "160", "", "", "", "" );
                        break;
                    case "Optional_Parameter":
                        ++items;
                        checkEntry( entry, "16", "", "", "", "Flag_Parameter==1{cal}", "" );
                        break;
                    case "Solar_Array_Voltage_1":
                        ++items;
                        checkEntry( entry, "12", "168", "", "", "", "" );
                        break;
                    case "Solar_Array_Voltage_2":
                        ++items;
                        checkEntry( entry, "12", "180", "", "", "", "" );
                        break;
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
    public void processContainerWithInheritanceAndIncludesTrue() {

        final String methodName =
            Thread.currentThread().getStackTrace()[1].getMethodName();

        System.out.println( "Test Case: " + methodName + "()" );

        String containerName = "/BogusSAT/SC001/ECSS_SpacePacket2";

        try {

            XTCETMContainer container = db_.getContainer( containerName );

            List<XTCEParameter> swParameters =
                db_.getTelemetryParameters( "Flag_Parameter" );

            Assert.assertTrue( "Should find 1 parameter named 'Flag_Parameter'",
                               swParameters.size() == 1 );

            XTCEContainerEntryValue valueObj =
               new XTCEContainerEntryValue( swParameters.get( 0 ),
                                            "1",
                                            "==",
                                            "Calibrated" );

            ArrayList<XTCEContainerEntryValue> values = new ArrayList<>();
            values.add( valueObj );

            XTCEContainerContentModel model =
                db_.processContainer( container, values, false );

            long sizeInBytes = model.getTotalSize();

            Assert.assertTrue( "Container size of " + containerName + " is " +
                Long.toString( sizeInBytes ) + " but should be 208 bits",
                sizeInBytes == 208 );

            List<XTCEContainerContentEntry> entries = model.getContentList();

            long items = 0;

            for ( XTCEContainerContentEntry entry : entries ) {

                switch (entry.getName()) {
                    case "CCSDS_Packet_ID.Version":
                        ++items;
                        checkEntry( entry, "3", "0", "==0{cal}", "0", "", "" );
                        break;
                    case "CCSDS_Packet_ID.Type":
                        ++items;
                        checkEntry( entry, "1", "3", "==TM{cal}", "", "", "" );
                        break;
                    case "CCSDS_Packet_ID.SecHdrFlag":
                        ++items;
                        checkEntry( entry, "1", "4", "==Present{cal}", "", "", "" );
                        break;
                    case "CCSDS_Packet_ID.APID":
                        ++items;
                        checkEntry( entry, "11", "5", "==100{cal}", "2047", "", "" );
                        break;
                    case "CCSDS_Packet_Sequence.GroupFlags":
                        ++items;
                        checkEntry( entry, "2", "16", "", "Standalone", "", "" );
                        break;
                    case "CCSDS_Packet_Sequence.Count":
                        ++items;
                        checkEntry( entry, "14", "18", "", "", "", "" );
                        break;
                    case "CCSDS_Packet_Length":
                        ++items;
                        checkEntry( entry, "16", "32", "", "", "", "" );
                        break;
                    case "PUS_Data_Field_Header.Spare1":
                        ++items;
                        checkEntry( entry, "1", "48", "==0{cal}", "0", "", "" );
                        break;
                    case "PUS_Data_Field_Header.Version":
                        ++items;
                        checkEntry( entry, "3", "49", "==1{cal}", "1", "", "" );
                        break;
                    case "PUS_Data_Field_Header.Spare4":
                        ++items;
                        checkEntry( entry, "4", "52", "==0{cal}", "0", "", "" );
                        break;
                    case "PUS_Data_Field_Header.Service":
                        ++items;
                        checkEntry( entry, "8", "56", "==3{cal}", "", "", "" );
                        break;
                    case "PUS_Data_Field_Header.Subservice":
                        ++items;
                        checkEntry( entry, "8", "64", "==25{cal}", "", "", "" );
                        break;
                    case "PUS_Data_Field_Header.SeqCount":
                        ++items;
                        checkEntry( entry, "8", "72", "", "", "", "" );
                        break;
                    case "PUS_Data_Field_Header.Destination":
                        ++items;
                        checkEntry( entry, "8", "80", "", "", "", "" );
                        break;
                    case "PUS_Time":
                        ++items;
                        checkEntry( entry, "64", "", "", "", "APPL_TIME_CODE!=NotUsed{cal}", "" );
                        break;
                    case "PUS_Error_Control_Field":
                        ++items;
                        checkEntry( entry, "16", "", "", "", "TM_CHECKSUM_TYPE!=NotUsed{cal}", "" );
                        break;
                    case "PUS_Structure_ID":
                        ++items;
                        checkEntry( entry, "8", "88", "==ECSS_SpacePacket2{cal}", "", "", "" );
                        break;
                    case "Battery_Voltage":
                        ++items;
                        checkEntry( entry, "32", "96", "", "", "", "" );
                        break;
                    case "Battery_Current":
                        ++items;
                        checkEntry( entry, "32", "128", "", "", "", "" );
                        break;
                    case "Flag_Parameter":
                        ++items;
                        checkEntry( entry, "8", "160", "==1{cal}", "", "", "" );
                        break;
                    case "Optional_Parameter":
                        ++items;
                        checkEntry( entry, "16", "168", "", "", "Flag_Parameter==1{cal}", "" );
                        break;
                    case "Solar_Array_Voltage_1":
                        ++items;
                        checkEntry( entry, "12", "184", "", "", "", "" );
                        break;
                    case "Solar_Array_Voltage_2":
                        ++items;
                        checkEntry( entry, "12", "196", "", "", "", "" );
                        break;
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
    public void processContainerWithInheritanceFixedRepeatParameter() {

        final String methodName =
            Thread.currentThread().getStackTrace()[1].getMethodName();

        System.out.println( "Test Case: " + methodName + "()" );

        String containerName = "/BogusSAT/SC001/CCSDS_SpacePacket3";

        try {

            XTCETMContainer container = db_.getContainer( containerName );

            XTCEContainerContentModel model =
                db_.processContainer( container, null, false );

            long sizeInBytes = model.getTotalSize();

            Assert.assertTrue( "Container size of " + containerName + " is " +
                Long.toString( sizeInBytes ) + " but should be 208 bits",
                sizeInBytes == 208 );

            List<XTCEContainerContentEntry> entries = model.getContentList();

            long items = 0;

            for ( XTCEContainerContentEntry entry : entries ) {

                switch (entry.getName()) {
                    case "CCSDS_Packet_ID.Version":
                        ++items;
                        checkEntry( entry, "3", "0", "==0{cal}", "0", "", "" );
                        break;
                    case "CCSDS_Packet_ID.Type":
                        ++items;
                        checkEntry( entry, "1", "3", "==TM{cal}", "", "", "" );
                        break;
                    case "CCSDS_Packet_ID.SecHdrFlag":
                        ++items;
                        checkEntry( entry, "1", "4", "==NotPresent{cal}", "", "", "" );
                        break;
                    case "CCSDS_Packet_ID.APID":
                        ++items;
                        checkEntry( entry, "11", "5", "==3{cal}", "2047", "", "" );
                        break;
                    case "CCSDS_Packet_Sequence.GroupFlags":
                        ++items;
                        checkEntry( entry, "2", "16", "", "Standalone", "", "" );
                        break;
                    case "CCSDS_Packet_Sequence.Count":
                        ++items;
                        checkEntry( entry, "14", "18", "", "", "", "" );
                        break;
                    case "CCSDS_Packet_Length":
                        ++items;
                        checkEntry( entry, "16", "32", "", "", "", "" );
                        break;
                    case "Basic_string_uint32":
                        ++items;
                        if ( items == 8 ) {
                            checkEntry( entry, "32", "48", "", "", "", XTCEFunctions.makeRepeatString( 1, 5 ) );
                        } else if ( items == 9 ) {
                            checkEntry( entry, "32", "80", "", "", "", XTCEFunctions.makeRepeatString( 2, 5 ) );
                        } else if ( items == 10 ) {
                            checkEntry( entry, "32", "112", "", "", "", XTCEFunctions.makeRepeatString( 3, 5 ) );
                        } else if ( items == 11 ) {
                            checkEntry( entry, "32", "144", "", "", "", XTCEFunctions.makeRepeatString( 4, 5 ) );
                        } else if ( items == 12 ) {
                            checkEntry( entry, "32", "176", "", "", "", XTCEFunctions.makeRepeatString( 5, 5 ) );
                        }   break;
                }

            }

            Assert.assertTrue( "Container parameter count of " + containerName + " is " +
                Long.toString( items ) + " but should be 12 items",
                items == 12 );

            assertOnWarnings( model );

        } catch ( Exception ex ) {
            //ex.printStackTrace();
            Assert.fail( ex.getLocalizedMessage() );
        }

    }

    @Test
    public void processContainerWithInheritanceDynamicRepeatParameter() {

        final String methodName =
            Thread.currentThread().getStackTrace()[1].getMethodName();

        System.out.println( "Test Case: " + methodName + "()" );

        String containerName = "/BogusSAT/SC001/ECSS_Service_1_Subservice_2";

        try {

            XTCETMContainer container = db_.getContainer( containerName );

            List<XTCEParameter> repParameters =
                db_.getTelemetryParameters( "TC_Parameter_Count" );

            Assert.assertTrue( "Should find 1 parameter named 'TC_Parameter_Count'",
                               repParameters.size() == 1 );

            XTCEContainerEntryValue valueObj =
               new XTCEContainerEntryValue( repParameters.get( 0 ),
                                            "3",
                                            "==",
                                            "Calibrated" );

            ArrayList<XTCEContainerEntryValue> values = new ArrayList<>();
            values.add( valueObj );

            XTCEContainerContentModel model =
                db_.processContainer( container, values, false );

            long sizeInBytes = model.getTotalSize();

            //Assert.assertTrue( "Container size of " + containerName + " is " +
            //    Long.toString( sizeInBytes ) + " but should be 168 bits",
            //    sizeInBytes == 168 );

            List<XTCEContainerContentEntry> entries = model.getContentList();

            long items = 0;

            for ( XTCEContainerContentEntry entry : entries ) {

                switch (entry.getName()) {
                    case "CCSDS_Packet_ID.Version":
                        ++items;
                        checkEntry( entry, "3", "0", "==0{cal}", "0", "", "" );
                        break;
                    case "CCSDS_Packet_ID.Type":
                        ++items;
                        checkEntry( entry, "1", "3", "==TM{cal}", "", "", "" );
                        break;
                    case "CCSDS_Packet_ID.SecHdrFlag":
                        ++items;
                        checkEntry( entry, "1", "4", "==Present{cal}", "", "", "" );
                        break;
                    case "CCSDS_Packet_ID.APID":
                        ++items;
                        checkEntry( entry, "11", "5", "==100{cal}", "2047", "", "" );
                        break;
                    case "CCSDS_Packet_Sequence.GroupFlags":
                        ++items;
                        checkEntry( entry, "2", "16", "", "Standalone", "", "" );
                        break;
                    case "CCSDS_Packet_Sequence.Count":
                        ++items;
                        checkEntry( entry, "14", "18", "", "", "", "" );
                        break;
                    case "CCSDS_Packet_Length":
                        ++items;
                        checkEntry( entry, "16", "32", "", "", "", "" );
                        break;
                    case "PUS_Data_Field_Header.Spare1":
                        ++items;
                        checkEntry( entry, "1", "48", "==0{cal}", "0", "", "" );
                        break;
                    case "PUS_Data_Field_Header.Version":
                        ++items;
                        checkEntry( entry, "3", "49", "==1{cal}", "1", "", "" );
                        break;
                    case "PUS_Data_Field_Header.Spare4":
                        ++items;
                        checkEntry( entry, "4", "52", "==0{cal}", "0", "", "" );
                        break;
                    case "PUS_Data_Field_Header.Service":
                        ++items;
                        checkEntry( entry, "8", "56", "==1{cal}", "", "", "" );
                        break;
                    case "PUS_Data_Field_Header.Subservice":
                        ++items;
                        checkEntry( entry, "8", "64", "==2{cal}", "", "", "" );
                        break;
                    case "PUS_Data_Field_Header.SeqCount":
                        ++items;
                        checkEntry( entry, "8", "72", "", "", "", "" );
                        break;
                    case "PUS_Data_Field_Header.Destination":
                        ++items;
                        checkEntry( entry, "8", "80", "", "", "", "" );
                        break;
                    case "PUS_Time":
                        ++items;
                        checkEntry( entry, "64", "", "", "", "APPL_TIME_CODE!=NotUsed{cal}", "" );
                        break;
                    case "PUS_Error_Control_Field":
                        ++items;
                        checkEntry( entry, "16", "", "", "", "TM_CHECKSUM_TYPE!=NotUsed{cal}", "" );
                        break;
                    case "TC_Packet_ID":
                        ++items;
                        checkEntry( entry, "16", "88", "", "", "", "" );
                        break;
                    case "TC_Packet_Sequence_Control":
                        ++items;
                        checkEntry( entry, "16", "104", "", "", "", "" );
                        break;
                    case "TC_Accept_Failure_Code":
                        ++items;
                        checkEntry( entry, "16", "120", "", "", "", "" );
                        break;
                    case "TC_Parameter_Count":
                        ++items;
                        checkEntry( entry, "8", "136", "==3{cal}", "", "", "" );
                        break;
                    case "TC_Parameter_Data":
                        ++items;
                        if ( items == 21 ) {
                            checkEntry( entry, "8", "144", "", "", "", XTCEFunctions.makeRepeatString( 1, 3 ) );
                        } else if ( items == 22 ) {
                            checkEntry( entry, "8", "152", "", "", "", XTCEFunctions.makeRepeatString( 2, 3 ) );
                        } else if ( items == 23 ) {
                            checkEntry( entry, "8", "160", "", "", "", XTCEFunctions.makeRepeatString( 3, 3 ) );
                        }   break;
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
    public void processContainerWithInheritanceDynamicRepeatNoCountParameter() {

        final String methodName =
            Thread.currentThread().getStackTrace()[1].getMethodName();

        System.out.println( "Test Case: " + methodName + "()" );

        String containerName = "/BogusSAT/SC001/ECSS_Service_1_Subservice_2";

        try {

            XTCETMContainer container = db_.getContainer( containerName );

            XTCEContainerContentModel model =
                db_.processContainer( container, null, false );

            long sizeInBytes = model.getTotalSize();

            Assert.assertTrue( "Container size of " + containerName + " is " +
                Long.toString( sizeInBytes ) + " but should be 152 bits",
                sizeInBytes == 152 );

            List<XTCEContainerContentEntry> entries = model.getContentList();

            long items = 0;

            for ( XTCEContainerContentEntry entry : entries ) {

                switch (entry.getName()) {
                    case "CCSDS_Packet_ID.Version":
                        ++items;
                        checkEntry( entry, "3", "0", "==0{cal}", "0", "", "" );
                        break;
                    case "CCSDS_Packet_ID.Type":
                        ++items;
                        checkEntry( entry, "1", "3", "==TM{cal}", "", "", "" );
                        break;
                    case "CCSDS_Packet_ID.SecHdrFlag":
                        ++items;
                        checkEntry( entry, "1", "4", "==Present{cal}", "", "", "" );
                        break;
                    case "CCSDS_Packet_ID.APID":
                        ++items;
                        checkEntry( entry, "11", "5", "==100{cal}", "2047", "", "" );
                        break;
                    case "CCSDS_Packet_Sequence.GroupFlags":
                        ++items;
                        checkEntry( entry, "2", "16", "", "Standalone", "", "" );
                        break;
                    case "CCSDS_Packet_Sequence.Count":
                        ++items;
                        checkEntry( entry, "14", "18", "", "", "", "" );
                        break;
                    case "CCSDS_Packet_Length":
                        ++items;
                        checkEntry( entry, "16", "32", "", "", "", "" );
                        break;
                    case "PUS_Data_Field_Header.Spare1":
                        ++items;
                        checkEntry( entry, "1", "48", "==0{cal}", "0", "", "" );
                        break;
                    case "PUS_Data_Field_Header.Version":
                        ++items;
                        checkEntry( entry, "3", "49", "==1{cal}", "1", "", "" );
                        break;
                    case "PUS_Data_Field_Header.Spare4":
                        ++items;
                        checkEntry( entry, "4", "52", "==0{cal}", "0", "", "" );
                        break;
                    case "PUS_Data_Field_Header.Service":
                        ++items;
                        checkEntry( entry, "8", "56", "==1{cal}", "", "", "" );
                        break;
                    case "PUS_Data_Field_Header.Subservice":
                        ++items;
                        checkEntry( entry, "8", "64", "==2{cal}", "", "", "" );
                        break;
                    case "PUS_Data_Field_Header.SeqCount":
                        ++items;
                        checkEntry( entry, "8", "72", "", "", "", "" );
                        break;
                    case "PUS_Data_Field_Header.Destination":
                        ++items;
                        checkEntry( entry, "8", "80", "", "", "", "" );
                        break;
                    case "PUS_Time":
                        ++items;
                        checkEntry( entry, "64", "", "", "", "APPL_TIME_CODE!=NotUsed{cal}", "" );
                        break;
                    case "PUS_Error_Control_Field":
                        ++items;
                        checkEntry( entry, "16", "", "", "", "TM_CHECKSUM_TYPE!=NotUsed{cal}", "" );
                        break;
                    case "TC_Packet_ID":
                        ++items;
                        checkEntry( entry, "16", "88", "", "", "", "" );
                        break;
                    case "TC_Packet_Sequence_Control":
                        ++items;
                        checkEntry( entry, "16", "104", "", "", "", "" );
                        break;
                    case "TC_Accept_Failure_Code":
                        ++items;
                        checkEntry( entry, "16", "120", "", "", "", "" );
                        break;
                    case "TC_Parameter_Count":
                        ++items;
                        checkEntry( entry, "8", "136", "", "", "", "" );
                        break;
                    case "TC_Parameter_Data":
                        ++items;
                        checkEntry( entry, "8", "144", "", "", "", "==TC_Parameter_Count{cal}" );
                        break;
                }

            }

            Assert.assertTrue( "Container parameter count of " + containerName + " is " +
                Long.toString( items ) + " but should be 21 items",
                items == 21 );

            //assertOnWarnings( model ); // Warning Expected Now

        } catch ( Exception ex ) {
            //ex.printStackTrace();
            Assert.fail( ex.getLocalizedMessage() );
        }

    }

    @Test
    public void processContainerWithIncludesNotTrueNotExpanded() {

        final String methodName =
            Thread.currentThread().getStackTrace()[1].getMethodName();

        System.out.println( "Test Case: " + methodName + "()" );

        String containerName = "/BogusSAT/SC001/CCSDS_SpacePacket4";

        try {

            XTCETMContainer container = db_.getContainer( containerName );

            XTCEContainerContentModel model =
                db_.processContainer( container, null, false );

            long sizeInBytes = model.getTotalSize();

            Assert.assertTrue( "Container size of " + containerName + " is " +
                Long.toString( sizeInBytes ) + " but should be 196 bits",
                sizeInBytes == 196 );

            List<XTCEContainerContentEntry> entries = model.getContentList();

            long items = 0;

            for ( XTCEContainerContentEntry entry : entries ) {

                switch (entry.getName()) {
                    case "CCSDS_Packet_ID.Version":
                        ++items;
                        checkEntry( entry, "3", "0", "==0{cal}", "0", "", "" );
                        break;
                    case "CCSDS_Packet_ID.Type":
                        ++items;
                        checkEntry( entry, "1", "3", "==TM{cal}", "", "", "" );
                        break;
                    case "CCSDS_Packet_ID.SecHdrFlag":
                        ++items;
                        checkEntry( entry, "1", "4", "==NotPresent{cal}", "", "", "" );
                        break;
                    case "CCSDS_Packet_ID.APID":
                        ++items;
                        checkEntry( entry, "11", "5", "==4{cal}", "2047", "", "" );
                        break;
                    case "CCSDS_Packet_Sequence.GroupFlags":
                        ++items;
                        checkEntry( entry, "2", "16", "", "Standalone", "", "" );
                        break;
                    case "CCSDS_Packet_Sequence.Count":
                        ++items;
                        checkEntry( entry, "14", "18", "", "", "", "" );
                        break;
                    case "CCSDS_Packet_Length":
                        ++items;
                        checkEntry( entry, "16", "32", "", "", "", "" );
                        break;
                    case "Payload_1_State":
                        ++items;
                        checkEntry( entry, "4", "48", "", "", "", "" );
                        break;
                    case "Basic_Float32":
                        ++items;
                        checkEntry( entry, "32", "52", "", "", "", "" );
                        break;
                    case "Basic_Float64":
                        ++items;
                        checkEntry( entry, "64", "84", "", "", "", "" );
                        break;
                    case "enum_int16_signmag":
                        ++items;
                        checkEntry( entry, "16", "148", "", "", "", "" );
                        break;
                    case "enum_int16_twoscomp":
                        ++items;
                        checkEntry( entry, "16", "164", "", "", "", "" );
                        break;
                    case "enum_int16_onescomp":
                        ++items;
                        checkEntry( entry, "16", "180", "", "", "", "" );
                        break;
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
    public void processContainerWithIncludesNotTrueExpanded() {

        final String methodName =
            Thread.currentThread().getStackTrace()[1].getMethodName();

        System.out.println( "Test Case: " + methodName + "()" );

        String containerName = "/BogusSAT/SC001/CCSDS_SpacePacket4";

        try {

            XTCETMContainer container = db_.getContainer( containerName );

            XTCEContainerContentModel model =
                db_.processContainer( container, null, true );

            long sizeInBytes = model.getTotalSize();

            Assert.assertTrue( "Container size of " + containerName + " is " +
                Long.toString( sizeInBytes ) + " but should be 196 bits",
                sizeInBytes == 196 );

            List<XTCEContainerContentEntry> entries = model.getContentList();

            long items = 0;

            for ( XTCEContainerContentEntry entry : entries ) {

                switch (entry.getName()) {
                    case "CCSDS_Packet_ID.Version":
                        ++items;
                        checkEntry( entry, "3", "0", "==0{cal}", "0", "", "" );
                        break;
                    case "CCSDS_Packet_ID.Type":
                        ++items;
                        checkEntry( entry, "1", "3", "==TM{cal}", "", "", "" );
                        break;
                    case "CCSDS_Packet_ID.SecHdrFlag":
                        ++items;
                        checkEntry( entry, "1", "4", "==NotPresent{cal}", "", "", "" );
                        break;
                    case "CCSDS_Packet_ID.APID":
                        ++items;
                        checkEntry( entry, "11", "5", "==4{cal}", "2047", "", "" );
                        break;
                    case "CCSDS_Packet_Sequence.GroupFlags":
                        ++items;
                        checkEntry( entry, "2", "16", "", "Standalone", "", "" );
                        break;
                    case "CCSDS_Packet_Sequence.Count":
                        ++items;
                        checkEntry( entry, "14", "18", "", "", "", "" );
                        break;
                    case "CCSDS_Packet_Length":
                        ++items;
                        checkEntry( entry, "16", "32", "", "", "", "" );
                        break;
                    case "Payload_1_State":
                        ++items;
                        checkEntry( entry, "4", "48", "", "", "", "" );
                        break;
                    case "Basic_uint32":
                        ++items;
                        checkEntry( entry, "32", "", "", "", "Payload_1_State==ON{cal}", "" );
                        break;
                    case "Basic_int32_signmag":
                        ++items;
                        checkEntry( entry, "32", "", "", "", "Payload_1_State==ON{cal}", "" );
                        break;
                    case "Basic_int32_twoscomp":
                        ++items;
                        checkEntry( entry, "32", "", "", "", "Payload_1_State==ON{cal}", "" );
                        break;
                    case "Basic_int32_onescomp":
                        ++items;
                        checkEntry( entry, "32", "", "", "", "Payload_1_State==ON{cal}", "" );
                        break;
                    case "Basic_Float32":
                        ++items;
                        checkEntry( entry, "32", "52", "", "", "", "" );
                        break;
                    case "Basic_Float64":
                        ++items;
                        checkEntry( entry, "64", "84", "", "", "", "" );
                        break;
                    case "enum_int16_signmag":
                        ++items;
                        checkEntry( entry, "16", "148", "", "", "", "" );
                        break;
                    case "enum_int16_twoscomp":
                        ++items;
                        checkEntry( entry, "16", "164", "", "", "", "" );
                        break;
                    case "enum_int16_onescomp":
                        ++items;
                        checkEntry( entry, "16", "180", "", "", "", "" );
                        break;
                }

            }

            Assert.assertTrue( "Container parameter count of " + containerName + " is " +
                Long.toString( items ) + " but should be 17 items",
                items == 17 );

            assertOnWarnings( model );

        } catch ( Exception ex ) {
            //ex.printStackTrace();
            Assert.fail( ex.getLocalizedMessage() );
        }

    }

    @Test
    public void processContainerWithIncludesTrue() {

        final String methodName =
            Thread.currentThread().getStackTrace()[1].getMethodName();

        System.out.println( "Test Case: " + methodName + "()" );

        String containerName = "/BogusSAT/SC001/CCSDS_SpacePacket4";

        try {

            XTCETMContainer container = db_.getContainer( containerName );

            List<XTCEParameter> repParameters =
                db_.getTelemetryParameters( "Payload_1_State" );

            Assert.assertTrue( "Should find 1 parameter named 'Payload_1_State'",
                               repParameters.size() == 1 );

            XTCEContainerEntryValue valueObj =
               new XTCEContainerEntryValue( repParameters.get( 0 ),
                                            "ON",
                                            "==",
                                            "Calibrated" );

            ArrayList<XTCEContainerEntryValue> values = new ArrayList<>();
            values.add( valueObj );

            XTCEContainerContentModel model =
                db_.processContainer( container, values, true );

            long sizeInBytes = model.getTotalSize();

            Assert.assertTrue( "Container size of " + containerName + " is " +
                Long.toString( sizeInBytes ) + " but should be 324 bits",
                sizeInBytes == 324 );

            List<XTCEContainerContentEntry> entries = model.getContentList();

            long items = 0;

            for ( XTCEContainerContentEntry entry : entries ) {

                switch (entry.getName()) {
                    case "CCSDS_Packet_ID.Version":
                        ++items;
                        checkEntry( entry, "3", "0", "==0{cal}", "0", "", "" );
                        break;
                    case "CCSDS_Packet_ID.Type":
                        ++items;
                        checkEntry( entry, "1", "3", "==TM{cal}", "", "", "" );
                        break;
                    case "CCSDS_Packet_ID.SecHdrFlag":
                        ++items;
                        checkEntry( entry, "1", "4", "==NotPresent{cal}", "", "", "" );
                        break;
                    case "CCSDS_Packet_ID.APID":
                        ++items;
                        checkEntry( entry, "11", "5", "==4{cal}", "2047", "", "" );
                        break;
                    case "CCSDS_Packet_Sequence.GroupFlags":
                        ++items;
                        checkEntry( entry, "2", "16", "", "Standalone", "", "" );
                        break;
                    case "CCSDS_Packet_Sequence.Count":
                        ++items;
                        checkEntry( entry, "14", "18", "", "", "", "" );
                        break;
                    case "CCSDS_Packet_Length":
                        ++items;
                        checkEntry( entry, "16", "32", "", "", "", "" );
                        break;
                    case "Payload_1_State":
                        ++items;
                        checkEntry( entry, "4", "48", "==ON{cal}", "", "", "" );
                        break;
                    case "Basic_uint32":
                        ++items;
                        checkEntry( entry, "32", "52", "", "", "Payload_1_State==ON{cal}", "" );
                        break;
                    case "Basic_int32_signmag":
                        ++items;
                        checkEntry( entry, "32", "84", "", "", "Payload_1_State==ON{cal}", "" );
                        break;
                    case "Basic_int32_twoscomp":
                        ++items;
                        checkEntry( entry, "32", "116", "", "", "Payload_1_State==ON{cal}", "" );
                        break;
                    case "Basic_int32_onescomp":
                        ++items;
                        checkEntry( entry, "32", "148", "", "", "Payload_1_State==ON{cal}", "" );
                        break;
                    case "Basic_Float32":
                        ++items;
                        checkEntry( entry, "32", "180", "", "", "", "" );
                        break;
                    case "Basic_Float64":
                        ++items;
                        checkEntry( entry, "64", "212", "", "", "", "" );
                        break;
                    case "enum_int16_signmag":
                        ++items;
                        checkEntry( entry, "16", "276", "", "", "", "" );
                        break;
                    case "enum_int16_twoscomp":
                        ++items;
                        checkEntry( entry, "16", "292", "", "", "", "" );
                        break;
                    case "enum_int16_onescomp":
                        ++items;
                        checkEntry( entry, "16", "308", "", "", "", "" );
                        break;
                }

            }

            Assert.assertTrue( "Container parameter count of " + containerName + " is " +
                Long.toString( items ) + " but should be 17 items",
                items == 17 );

            assertOnWarnings( model );

        } catch ( Exception ex ) {
            //ex.printStackTrace();
            Assert.fail( ex.getLocalizedMessage() );
        }

    }

    @Test
    public void processMultiIncludesCase1() {

        // container with include, inside is parameters with includes of both
        // == and !=, here we set no user values but check both processing
        // the container with and without showing all conditionals

        final String methodName =
            Thread.currentThread().getStackTrace()[1].getMethodName();

        System.out.println( "Test Case: " + methodName + "()" );

        String containerName = "/BogusSAT/SC001/CCSDS_SpacePacket5";

        XTCETMContainer container;
        XTCEContainerContentModel model;
        List<XTCEContainerContentEntry> entries;
        long sizeInBytes;
        long items;

        try {

            container = db_.getContainer( containerName );

            model = db_.processContainer( container, null, true );

            sizeInBytes = model.getTotalSize();

            Assert.assertTrue( "Expanded Container size of " + containerName +
                " is " +
                Long.toString( sizeInBytes ) + " but should be 188 bits",
                sizeInBytes == 188 );

            entries = model.getContentList();

            items = 0;

            for ( XTCEContainerContentEntry entry : entries ) {

                switch (entry.getName()) {
                    case "CCSDS_Packet_ID.Version":
                        ++items;
                        checkEntry( entry, "3", "0", "==0{cal}", "0", "", "" );
                        break;
                    case "CCSDS_Packet_ID.Type":
                        ++items;
                        checkEntry( entry, "1", "3", "==TM{cal}", "", "", "" );
                        break;
                    case "CCSDS_Packet_ID.SecHdrFlag":
                        ++items;
                        checkEntry( entry, "1", "4", "==NotPresent{cal}", "", "", "" );
                        break;
                    case "CCSDS_Packet_ID.APID":
                        ++items;
                        checkEntry( entry, "11", "5", "==5{cal}", "2047", "", "" );
                        break;
                    case "CCSDS_Packet_Sequence.GroupFlags":
                        ++items;
                        checkEntry( entry, "2", "16", "", "Standalone", "", "" );
                        break;
                    case "CCSDS_Packet_Sequence.Count":
                        ++items;
                        checkEntry( entry, "14", "18", "", "", "", "" );
                        break;
                    case "CCSDS_Packet_Length":
                        ++items;
                        checkEntry( entry, "16", "32", "", "", "", "" );
                        break;
                    case "Payload_1_State":
                        ++items;
                        checkEntry( entry, "4", "48", "", "", "", "" );
                        break;
                    case "Solar_Array_Voltage_1_State":
                        ++items;
                        checkEntry( entry, "8", "52", "", "OFF", "", "" );
                        break;
                    case "enum_binary":
                        ++items;
                        checkEntry( entry, "32", "", "", "", "Payload_1_State==ON{cal}", "" );
                        break;
                    case "enum_float32":
                        ++items;
                        checkEntry( entry, "32", "", "", "", "Payload_1_State==ON{cal}", "" );
                        break;
                    case "enum_float64":
                        ++items;
                        checkEntry( entry, "64", "", "", "", "Payload_1_State==ON{cal},Solar_Array_Voltage_1_State==ON{cal}", "" );
                        break;
                    case "enum_int16_signmag":
                        ++items;
                        checkEntry( entry, "16", "", "", "", "Payload_1_State==ON{cal},Solar_Array_Voltage_1_State!=ON{cal}", "" );
                        break;
                    case "enum_int16_twoscomp":
                        ++items;
                        checkEntry( entry, "16", "", "", "", "Payload_1_State==ON{cal}", "" );
                        break;
                    case "enum_int16_onescomp":
                        ++items;
                        checkEntry( entry, "16", "", "", "", "Payload_1_State==ON{cal}", "" );
                        break;
                    case "Basic_uint32":
                        ++items;
                        checkEntry( entry, "32", "60", "", "", "", "" );
                        break;
                    case "Basic_int32_signmag":
                        ++items;
                        checkEntry( entry, "32", "92", "", "", "", "" );
                        break;
                    case "Basic_int32_twoscomp":
                        ++items;
                        checkEntry( entry, "32", "124", "", "", "", "" );
                        break;
                    case "Basic_int32_onescomp":
                        ++items;
                        checkEntry( entry, "32", "156", "", "", "", "" );
                        break;
                }

            }

            Assert.assertTrue( "Container parameter count of " + containerName + " is " +
                Long.toString( items ) + " but should be 19 items",
                items == 19 );

            assertOnWarnings( model );

            container = db_.getContainer( containerName );

            model = db_.processContainer( container, null, false );

            sizeInBytes = model.getTotalSize();

            Assert.assertTrue( "Container size of " + containerName +
                " is " +
                Long.toString( sizeInBytes ) + " but should be 188 bits",
                sizeInBytes == 188 );

            entries = model.getContentList();

            items = 0;

            for ( XTCEContainerContentEntry entry : entries ) {

                switch (entry.getName()) {
                    case "CCSDS_Packet_ID.Version":
                        ++items;
                        checkEntry( entry, "3", "0", "==0{cal}", "0", "", "" );
                        break;
                    case "CCSDS_Packet_ID.Type":
                        ++items;
                        checkEntry( entry, "1", "3", "==TM{cal}", "", "", "" );
                        break;
                    case "CCSDS_Packet_ID.SecHdrFlag":
                        ++items;
                        checkEntry( entry, "1", "4", "==NotPresent{cal}", "", "", "" );
                        break;
                    case "CCSDS_Packet_ID.APID":
                        ++items;
                        checkEntry( entry, "11", "5", "==5{cal}", "2047", "", "" );
                        break;
                    case "CCSDS_Packet_Sequence.GroupFlags":
                        ++items;
                        checkEntry( entry, "2", "16", "", "Standalone", "", "" );
                        break;
                    case "CCSDS_Packet_Sequence.Count":
                        ++items;
                        checkEntry( entry, "14", "18", "", "", "", "" );
                        break;
                    case "CCSDS_Packet_Length":
                        ++items;
                        checkEntry( entry, "16", "32", "", "", "", "" );
                        break;
                    case "Payload_1_State":
                        ++items;
                        checkEntry( entry, "4", "48", "", "", "", "" );
                        break;
                    case "Solar_Array_Voltage_1_State":
                        ++items;
                        checkEntry( entry, "8", "52", "", "OFF", "", "" );
                        break;
                    case "Basic_uint32":
                        ++items;
                        checkEntry( entry, "32", "60", "", "", "", "" );
                        break;
                    case "Basic_int32_signmag":
                        ++items;
                        checkEntry( entry, "32", "92", "", "", "", "" );
                        break;
                    case "Basic_int32_twoscomp":
                        ++items;
                        checkEntry( entry, "32", "124", "", "", "", "" );
                        break;
                    case "Basic_int32_onescomp":
                        ++items;
                        checkEntry( entry, "32", "156", "", "", "", "" );
                        break;
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
    public void processMultiIncludesCase2() {

        // container with include, inside is parameters with includes of both
        // == and !=, here we set no user values but check both processing
        // the container with and without showing all conditionals

        final String methodName =
            Thread.currentThread().getStackTrace()[1].getMethodName();

        System.out.println( "Test Case: " + methodName + "()" );

        String containerName = "/BogusSAT/SC001/CCSDS_SpacePacket5";

        XTCETMContainer container;
        XTCEContainerContentModel model;
        List<XTCEContainerContentEntry> entries;
        long sizeInBytes;
        long items;

        try {

            container = db_.getContainer( containerName );

            List<XTCEParameter> repParameters =
                db_.getTelemetryParameters( "Payload_1_State" );

            Assert.assertTrue( "Should find 1 parameter named 'Payload_1_State'",
                               repParameters.size() == 1 );

            XTCEContainerEntryValue valueObj =
               new XTCEContainerEntryValue( repParameters.get( 0 ),
                                            "ON",
                                            "==",
                                            "Calibrated" );

            ArrayList<XTCEContainerEntryValue> values = new ArrayList<>();
            values.add( valueObj );

            model = db_.processContainer( container, values, true );

            sizeInBytes = model.getTotalSize();

            Assert.assertTrue( "Expanded Container size of " + containerName +
                " is " +
                Long.toString( sizeInBytes ) + " but should be 284 bits",
                sizeInBytes == 284 );

            entries = model.getContentList();

            items = 0;

            for ( XTCEContainerContentEntry entry : entries ) {

                switch (entry.getName()) {
                    case "CCSDS_Packet_ID.Version":
                        ++items;
                        checkEntry( entry, "3", "0", "==0{cal}", "0", "", "" );
                        break;
                    case "CCSDS_Packet_ID.Type":
                        ++items;
                        checkEntry( entry, "1", "3", "==TM{cal}", "", "", "" );
                        break;
                    case "CCSDS_Packet_ID.SecHdrFlag":
                        ++items;
                        checkEntry( entry, "1", "4", "==NotPresent{cal}", "", "", "" );
                        break;
                    case "CCSDS_Packet_ID.APID":
                        ++items;
                        checkEntry( entry, "11", "5", "==5{cal}", "2047", "", "" );
                        break;
                    case "CCSDS_Packet_Sequence.GroupFlags":
                        ++items;
                        checkEntry( entry, "2", "16", "", "Standalone", "", "" );
                        break;
                    case "CCSDS_Packet_Sequence.Count":
                        ++items;
                        checkEntry( entry, "14", "18", "", "", "", "" );
                        break;
                    case "CCSDS_Packet_Length":
                        ++items;
                        checkEntry( entry, "16", "32", "", "", "", "" );
                        break;
                    case "Payload_1_State":
                        ++items;
                        checkEntry( entry, "4", "48", "==ON{cal}", "", "", "" );
                        break;
                    case "Solar_Array_Voltage_1_State":
                        ++items;
                        checkEntry( entry, "8", "52", "", "OFF", "", "" );
                        break;
                    case "enum_binary":
                        ++items;
                        checkEntry( entry, "32", "60", "", "", "Payload_1_State==ON{cal}", "" );
                        break;
                    case "enum_float32":
                        ++items;
                        checkEntry( entry, "32", "92", "", "", "Payload_1_State==ON{cal}", "" );
                        break;
                    case "enum_float64":
                        ++items;
                        checkEntry( entry, "64", "", "", "", "Payload_1_State==ON{cal},Solar_Array_Voltage_1_State==ON{cal}", "" );
                        break;
                    case "enum_int16_signmag":
                        ++items;
                        checkEntry( entry, "16", "", "", "", "Payload_1_State==ON{cal},Solar_Array_Voltage_1_State!=ON{cal}", "" );
                        break;
                    case "enum_int16_twoscomp":
                        ++items;
                        checkEntry( entry, "16", "124", "", "", "Payload_1_State==ON{cal}", "" );
                        break;
                    case "enum_int16_onescomp":
                        ++items;
                        checkEntry( entry, "16", "140", "", "", "Payload_1_State==ON{cal}", "" );
                        break;
                    case "Basic_uint32":
                        ++items;
                        checkEntry( entry, "32", "156", "", "", "", "" );
                        break;
                    case "Basic_int32_signmag":
                        ++items;
                        checkEntry( entry, "32", "188", "", "", "", "" );
                        break;
                    case "Basic_int32_twoscomp":
                        ++items;
                        checkEntry( entry, "32", "220", "", "", "", "" );
                        break;
                    case "Basic_int32_onescomp":
                        ++items;
                        checkEntry( entry, "32", "252", "", "", "", "" );
                        break;
                }

            }

            Assert.assertTrue( "Container parameter count of " + containerName + " is " +
                Long.toString( items ) + " but should be 19 items",
                items == 19 );

            assertOnWarnings( model );

        } catch ( Exception ex ) {
            //ex.printStackTrace();
            Assert.fail( ex.getLocalizedMessage() );
        }

    }

    @Test
    public void processMultiIncludesCase3() {

        // container with include, inside is parameters with includes of both
        // == and !=, here we set no user values but check both processing
        // the container with and without showing all conditionals

        final String methodName =
            Thread.currentThread().getStackTrace()[1].getMethodName();

        System.out.println( "Test Case: " + methodName + "()" );

        String containerName = "/BogusSAT/SC001/CCSDS_SpacePacket5";

        XTCETMContainer container;
        XTCEContainerContentModel model;
        List<XTCEContainerContentEntry> entries;
        long sizeInBytes;
        long items;

        try {

            container = db_.getContainer( containerName );

            List<XTCEParameter> repParameters =
                db_.getTelemetryParameters( "Payload_1_State" );

            Assert.assertTrue( "Should find 1 parameter named 'Payload_1_State'",
                               repParameters.size() == 1 );

            XTCEContainerEntryValue valueObj1 =
               new XTCEContainerEntryValue( repParameters.get( 0 ),
                                            "ON",
                                            "==",
                                            "Calibrated" );

            repParameters =
                db_.getTelemetryParameters( "Solar_Array_Voltage_1_State" );

            Assert.assertTrue( "Should find 1 parameter named 'Solar_Array_Voltage_1_State'",
                               repParameters.size() == 1 );

            XTCEContainerEntryValue valueObj2 =
               new XTCEContainerEntryValue( repParameters.get( 0 ),
                                            "ON",
                                            "==",
                                            "Calibrated" );

            ArrayList<XTCEContainerEntryValue> values = new ArrayList<>();
            values.add( valueObj1 );
            values.add( valueObj2 );

            model = db_.processContainer( container, values, true );

            sizeInBytes = model.getTotalSize();

            Assert.assertTrue( "Expanded Container size of " + containerName +
                " is " +
                Long.toString( sizeInBytes ) + " but should be 348 bits",
                sizeInBytes == 348 );

            entries = model.getContentList();

            items = 0;

            for ( XTCEContainerContentEntry entry : entries ) {

                switch (entry.getName()) {
                    case "CCSDS_Packet_ID.Version":
                        ++items;
                        checkEntry( entry, "3", "0", "==0{cal}", "0", "", "" );
                        break;
                    case "CCSDS_Packet_ID.Type":
                        ++items;
                        checkEntry( entry, "1", "3", "==TM{cal}", "", "", "" );
                        break;
                    case "CCSDS_Packet_ID.SecHdrFlag":
                        ++items;
                        checkEntry( entry, "1", "4", "==NotPresent{cal}", "", "", "" );
                        break;
                    case "CCSDS_Packet_ID.APID":
                        ++items;
                        checkEntry( entry, "11", "5", "==5{cal}", "2047", "", "" );
                        break;
                    case "CCSDS_Packet_Sequence.GroupFlags":
                        ++items;
                        checkEntry( entry, "2", "16", "", "Standalone", "", "" );
                        break;
                    case "CCSDS_Packet_Sequence.Count":
                        ++items;
                        checkEntry( entry, "14", "18", "", "", "", "" );
                        break;
                    case "CCSDS_Packet_Length":
                        ++items;
                        checkEntry( entry, "16", "32", "", "", "", "" );
                        break;
                    case "Payload_1_State":
                        ++items;
                        checkEntry( entry, "4", "48", "==ON{cal}", "", "", "" );
                        break;
                    case "Solar_Array_Voltage_1_State":
                        ++items;
                        checkEntry( entry, "8", "52", "==ON{cal}", "OFF", "", "" );
                        break;
                    case "enum_binary":
                        ++items;
                        checkEntry( entry, "32", "60", "", "", "Payload_1_State==ON{cal}", "" );
                        break;
                    case "enum_float32":
                        ++items;
                        checkEntry( entry, "32", "92", "", "", "Payload_1_State==ON{cal}", "" );
                        break;
                    case "enum_float64":
                        ++items;
                        checkEntry( entry, "64", "124", "", "", "Payload_1_State==ON{cal},Solar_Array_Voltage_1_State==ON{cal}", "" );
                        break;
                    case "enum_int16_signmag":
                        ++items;
                        checkEntry( entry, "16", "", "", "", "Payload_1_State==ON{cal},Solar_Array_Voltage_1_State!=ON{cal}", "" );
                        break;
                    case "enum_int16_twoscomp":
                        ++items;
                        checkEntry( entry, "16", "188", "", "", "Payload_1_State==ON{cal}", "" );
                        break;
                    case "enum_int16_onescomp":
                        ++items;
                        checkEntry( entry, "16", "204", "", "", "Payload_1_State==ON{cal}", "" );
                        break;
                    case "Basic_uint32":
                        ++items;
                        checkEntry( entry, "32", "220", "", "", "", "" );
                        break;
                    case "Basic_int32_signmag":
                        ++items;
                        checkEntry( entry, "32", "252", "", "", "", "" );
                        break;
                    case "Basic_int32_twoscomp":
                        ++items;
                        checkEntry( entry, "32", "284", "", "", "", "" );
                        break;
                    case "Basic_int32_onescomp":
                        ++items;
                        checkEntry( entry, "32", "316", "", "", "", "" );
                        break;
                }

            }

            Assert.assertTrue( "Container parameter count of " + containerName + " is " +
                Long.toString( items ) + " but should be 19 items",
                items == 19 );

            assertOnWarnings( model );

        } catch ( Exception ex ) {
            //ex.printStackTrace();
            Assert.fail( ex.getLocalizedMessage() );
        }

    }

    @Test
    public void processMultiIncludesCase4() {

        // container with include, inside is parameters with includes of both
        // == and !=, here we set no user values but check both processing
        // the container with and without showing all conditionals

        final String methodName =
            Thread.currentThread().getStackTrace()[1].getMethodName();

        System.out.println( "Test Case: " + methodName + "()" );

        String containerName = "/BogusSAT/SC001/CCSDS_SpacePacket5";

        XTCETMContainer container;
        XTCEContainerContentModel model;
        List<XTCEContainerContentEntry> entries;
        long sizeInBits;
        long items;

        try {

            container = db_.getContainer( containerName );

            List<XTCEParameter> repParameters =
                db_.getTelemetryParameters( "Payload_1_State" );

            Assert.assertTrue( "Should find 1 parameter named 'Payload_1_State'",
                               repParameters.size() == 1 );

            XTCEContainerEntryValue valueObj1 =
               new XTCEContainerEntryValue( repParameters.get( 0 ),
                                            "ON",
                                            "==",
                                            "Calibrated" );

            repParameters =
                db_.getTelemetryParameters( "Solar_Array_Voltage_1_State" );

            Assert.assertTrue( "Should find 1 parameter named 'Solar_Array_Voltage_1_State'",
                               repParameters.size() == 1 );

            XTCEContainerEntryValue valueObj2 =
               new XTCEContainerEntryValue( repParameters.get( 0 ),
                                            "OFF",
                                            "==",
                                            "Calibrated" );

            ArrayList<XTCEContainerEntryValue> values = new ArrayList<>();
            values.add( valueObj1 );
            values.add( valueObj2 );

            model = db_.processContainer( container, values, true );

            sizeInBits = model.getTotalSize();

            Assert.assertTrue( "Expanded Container size of " + containerName +
                " is " +
                Long.toString( sizeInBits ) + " but should be 300 bits",
                sizeInBits == 300 );

            Assert.assertTrue( "Container size of " + containerName + " is " +
                model.getBytesRequired() + " but should be 38 bytes " +
                "(bits check ok)",
                model.getBytesRequired() == 38 );

            entries = model.getContentList();

            items = 0;

            for ( XTCEContainerContentEntry entry : entries ) {

                switch (entry.getName()) {
                    case "CCSDS_Packet_ID.Version":
                        ++items;
                        checkEntry( entry, "3", "0", "==0{cal}", "0", "", "" );
                        break;
                    case "CCSDS_Packet_ID.Type":
                        ++items;
                        checkEntry( entry, "1", "3", "==TM{cal}", "", "", "" );
                        break;
                    case "CCSDS_Packet_ID.SecHdrFlag":
                        ++items;
                        checkEntry( entry, "1", "4", "==NotPresent{cal}", "", "", "" );
                        break;
                    case "CCSDS_Packet_ID.APID":
                        ++items;
                        checkEntry( entry, "11", "5", "==5{cal}", "2047", "", "" );
                        break;
                    case "CCSDS_Packet_Sequence.GroupFlags":
                        ++items;
                        checkEntry( entry, "2", "16", "", "Standalone", "", "" );
                        break;
                    case "CCSDS_Packet_Sequence.Count":
                        ++items;
                        checkEntry( entry, "14", "18", "", "", "", "" );
                        break;
                    case "CCSDS_Packet_Length":
                        ++items;
                        checkEntry( entry, "16", "32", "", "", "", "" );
                        break;
                    case "Payload_1_State":
                        ++items;
                        checkEntry( entry, "4", "48", "==ON{cal}", "", "", "" );
                        break;
                    case "Solar_Array_Voltage_1_State":
                        ++items;
                        checkEntry( entry, "8", "52", "==OFF{cal}", "OFF", "", "" );
                        break;
                    case "enum_binary":
                        ++items;
                        checkEntry( entry, "32", "60", "", "", "Payload_1_State==ON{cal}", "" );
                        break;
                    case "enum_float32":
                        ++items;
                        checkEntry( entry, "32", "92", "", "", "Payload_1_State==ON{cal}", "" );
                        break;
                    case "enum_float64":
                        ++items;
                        checkEntry( entry, "64", "", "", "", "Payload_1_State==ON{cal},Solar_Array_Voltage_1_State==ON{cal}", "" );
                        break;
                    case "enum_int16_signmag":
                        ++items;
                        checkEntry( entry, "16", "124", "", "", "Payload_1_State==ON{cal},Solar_Array_Voltage_1_State!=ON{cal}", "" );
                        break;
                    case "enum_int16_twoscomp":
                        ++items;
                        checkEntry( entry, "16", "140", "", "", "Payload_1_State==ON{cal}", "" );
                        break;
                    case "enum_int16_onescomp":
                        ++items;
                        checkEntry( entry, "16", "156", "", "", "Payload_1_State==ON{cal}", "" );
                        break;
                    case "Basic_uint32":
                        ++items;
                        checkEntry( entry, "32", "172", "", "", "", "" );
                        break;
                    case "Basic_int32_signmag":
                        ++items;
                        checkEntry( entry, "32", "204", "", "", "", "" );
                        break;
                    case "Basic_int32_twoscomp":
                        ++items;
                        checkEntry( entry, "32", "236", "", "", "", "" );
                        break;
                    case "Basic_int32_onescomp":
                        ++items;
                        checkEntry( entry, "32", "268", "", "", "", "" );
                        break;
                }

            }

            Assert.assertTrue( "Container parameter count of " + containerName + " is " +
                Long.toString( items ) + " but should be 19 items",
                items == 19 );

            assertOnWarnings( model );

        } catch ( Exception ex ) {
            //ex.printStackTrace();
            Assert.fail( ex.getLocalizedMessage() );
        }

    }

    @Test
    public void processContainerWithContainerRefAndReferenceLocation() {

        final String methodName =
            Thread.currentThread().getStackTrace()[1].getMethodName();

        System.out.println( "Test Case: " + methodName + "()" );

        String containerName = "/BogusSAT/LOG_MSGS/LOG_ALL";

        try {

            XTCETMContainer container = db_.getContainer( containerName );

            XTCEContainerContentModel model =
                db_.processContainer( container, null, false );

            long sizeInBytes = model.getTotalSize();

            Assert.assertTrue( "Container size of " + containerName + " is " +
                Long.toString( sizeInBytes ) + " but should be 2496 bits",
                sizeInBytes == 2496 );

            List<XTCEContainerContentEntry> entries = model.getContentList();

            long items = 0;

            for ( XTCEContainerContentEntry entry : entries ) {

                if ( entry.getName().equals( "DYNAMIC_REGION_1" ) ) {
                    ++items;
                    checkEntry( entry, "128", "0", "", "", "", "" );
                } else if ( entry.getName().equals( "DYNAMIC_REGION_2" ) ) {
                    ++items;
                    checkEntry( entry, "256", "128", "", "", "", "" );
                } else if ( items == 6 && entry.getName().equals( "RECORDFLAG" ) ) {
                    ++items;
                    checkEntry( entry, "32", "384", "", "3735928559", "", "" );
                } else if ( items == 7 && entry.getName().equals( "LOGLEVEL" ) ) {
                    ++items;
                    checkEntry( entry, "4", "416", "", "", "", "" );
                } else if ( items == 8 && entry.getName().equals( "FACILITY" ) ) {
                    ++items;
                    checkEntry( entry, "4", "420", "", "", "", "" );
                } else if ( items == 9 && entry.getName().equals( "ERRORCODE" ) ) {
                    ++items;
                    checkEntry( entry, "16", "424", "", "", "", "" );
                } else if ( items == 10 && entry.getName().equals( "DATAWORDS" ) ) {
                    ++items;
                    checkEntry( entry, "8", "440", "", "", "", "" );
                } else if ( items == 11 && entry.getName().equals( "LOGTIME" ) ) {
                    ++items;
                    checkEntry( entry, "64", "448", "", "", "", "" );
                // skipping the repeats because they are tested elsewhere
                } else if ( entry.getName().equals( "WRAP_FLAG" ) ) {
                    ++items;
                    checkEntry( entry, "16", "2432", "", "", "", "" );
                } else if ( entry.getName().equals( "START_POINTER" ) ) {
                    ++items;
                    checkEntry( entry, "16", "2448", "", "", "", "" );
                } else if ( entry.getName().equals( "END_POINTER" ) ) {
                    ++items;
                    checkEntry( entry, "16", "2464", "", "", "", "" );
                } else if ( entry.getName().equals( "SIZE_USED" ) ) {
                    ++items;
                    checkEntry( entry, "16", "2480", "", "", "", "" );
                } else {
                    ++items;
                }

            }

            Assert.assertTrue( "Container parameter count of " + containerName + " is " +
                Long.toString( items ) + " but should be 139 items",
                items == 139 );

            //assertOnWarnings( model ); // Warning Expected Now

        } catch ( Exception ex ) {
            //ex.printStackTrace();
            Assert.fail( ex.getLocalizedMessage() );
        }

    }

    @Test
    public void processContainerWithFixedSize() {

        final String methodName =
            Thread.currentThread().getStackTrace()[1].getMethodName();

        System.out.println( "Test Case: " + methodName + "()" );

        String containerName =
            "/Payload_Diagnostic_Buffer/Diag_Formats/FMT1_HIST_REC_SNAPSHOT";

        try {

            XTCETMContainer container = db2_.getContainer( containerName );

            XTCEContainerContentModel model =
                db2_.processContainer( container, null, false );

            long sizeInBytes = model.getTotalSize();

            Assert.assertTrue( "Container size of " + containerName + " is " +
                Long.toString( sizeInBytes ) + " but should be 104 bits",
                sizeInBytes == 104 );

            List<XTCEContainerContentEntry> entries = model.getContentList();

            long items = 0;

            for ( XTCEContainerContentEntry entry : entries ) {

                switch (entry.getName()) {
                    case "REC_TIME":
                        ++items;
                        checkEntry( entry, "64", "0", "", "", "", "" );
                        break;
                    case "FLEETID":
                        ++items;
                        checkEntry( entry, "6", "66", "", "", "", "" );
                        break;
                    case "CONTINUATION_FLAG":
                        ++items;
                        checkEntry( entry, "1", "72", "", "", "", "" );
                        break;
                    case "LAST_REC_FLAG":
                        ++items;
                        checkEntry( entry, "1", "73", "", "", "", "" );
                        break;
                    case "ANT_BEAMID":
                        ++items;
                        checkEntry( entry, "6", "74", "", "", "", "" );
                        break;
                    case "REUSE_TYPE":
                        ++items;
                        checkEntry( entry, "1", "80", "", "", "", "" );
                        break;
                    case "RF_TIMESLOT":
                        ++items;
                        checkEntry( entry, "2", "81", "", "", "", "" );
                        break;
                    case "RX_MODEM_PORTID":
                        ++items;
                        checkEntry( entry, "5", "86", "", "", "", "" );
                        break;
                    case "TX_MODEM_PORTID":
                        ++items;
                        checkEntry( entry, "5", "94", "", "", "", "" );
                        break;
                }

            }

            Assert.assertTrue( "Container parameter count of " + containerName + " is " +
                Long.toString( items ) + " but should be 9 items",
                items == 9 );

            assertOnWarnings( model );

        } catch ( Exception ex ) {
            //ex.printStackTrace();
            Assert.fail( ex.getLocalizedMessage() );
        }

    }

    @Test
    public void processContainerWithFixedSizeIncluded() {

        final String methodName =
            Thread.currentThread().getStackTrace()[1].getMethodName();

        System.out.println( "Test Case: " + methodName + "()" );

        String containerName =
            "/Payload_Diagnostic_Buffer/Diag_Formats/FORMAT1";

        try {

            XTCETMContainer container = db2_.getContainer( containerName );

            XTCEContainerContentModel model =
                db2_.processContainer( container, null, false );

            long sizeInBytes = model.getTotalSize();

            Assert.assertTrue( "Container size of " + containerName + " is " +
                Long.toString( sizeInBytes ) + " but should be 136 bits",
                sizeInBytes == 136 );

            List<XTCEContainerContentEntry> entries = model.getContentList();

            long items = 0;

            for ( XTCEContainerContentEntry entry : entries ) {

                switch (entry.getName()) {
                    case "LOG_RECORD_LENGTH":
                        ++items;
                        checkEntry( entry, "16", "0", "", "", "", "" );
                        break;
                    case "FMT1_REC_COUNT":
                        ++items;
                        checkEntry( entry, "16", "0", "", "", "", "" );
                        break;
                    case "FSW_SUBSYS_NAME":
                        ++items;
                        checkEntry( entry, "8", "16", "", "", "", "" );
                        break;
                    case "FORMAT_ID":
                        ++items;
                        checkEntry( entry, "8", "24", "==FMT1{cal}", "", "", "" );
                        break;
                    case "REC_TIME":
                        ++items;
                        checkEntry( entry, "64", "32", "", "", "", "" );
                        break;
                    case "FLEETID":
                        ++items;
                        checkEntry( entry, "6", "98", "", "", "", "" );
                        break;
                    case "CONTINUATION_FLAG":
                        ++items;
                        checkEntry( entry, "1", "104", "", "", "", "" );
                        break;
                    case "LAST_REC_FLAG":
                        ++items;
                        checkEntry( entry, "1", "105", "", "", "", "" );
                        break;
                    case "ANT_BEAMID":
                        ++items;
                        checkEntry( entry, "6", "106", "", "", "", "" );
                        break;
                    case "REUSE_TYPE":
                        ++items;
                        checkEntry( entry, "1", "112", "", "", "", "" );
                        break;
                    case "RF_TIMESLOT":
                        ++items;
                        checkEntry( entry, "2", "113", "", "", "", "" );
                        break;
                    case "RX_MODEM_PORTID":
                        ++items;
                        checkEntry( entry, "5", "118", "", "", "", "" );
                        break;
                    case "TX_MODEM_PORTID":
                        ++items;
                        checkEntry( entry, "5", "126", "", "", "", "" );
                        break;
                }

            }

            Assert.assertTrue( "Container parameter count of " + containerName + " is " +
                Long.toString( items ) + " but should be 13 items",
                items == 13 );

            for( String warning : model.getWarnings() ) {
                if ( warning.contains( "overlaps item" ) == false ) {
                    assertOnWarnings( model );
                }
            }

        } catch ( Exception ex ) {
            //ex.printStackTrace();
            Assert.fail( ex.getLocalizedMessage() );
        }

    }

    @Test
    public void processContainerWithFixedSizeIncludedMiddle() {

        final String methodName =
            Thread.currentThread().getStackTrace()[1].getMethodName();

        System.out.println( "Test Case: " + methodName + "()" );

        String containerName =
            "/Payload_Diagnostic_Buffer/Diag_Formats/FORMAT2";

        try {

            XTCETMContainer container = db2_.getContainer( containerName );

            XTCEContainerContentModel model =
                db2_.processContainer( container, null, false );

            long sizeInBytes = model.getTotalSize();

            Assert.assertTrue( "Container size of " + containerName + " is " +
                Long.toString( sizeInBytes ) + " but should be 184 bits",
                sizeInBytes == 184 );

            List<XTCEContainerContentEntry> entries = model.getContentList();

            long items = 0;

            for ( XTCEContainerContentEntry entry : entries ) {

                switch (entry.getName()) {
                    case "LOG_RECORD_LENGTH":
                        ++items;
                        checkEntry( entry, "16", "0", "", "", "", "" );
                        break;
                    case "FMT2_REC_COUNT":
                        ++items;
                        checkEntry( entry, "16", "0", "", "", "", "" );
                        break;
                    case "FSW_SUBSYS_NAME":
                        ++items;
                        checkEntry( entry, "8", "16", "", "", "", "" );
                        break;
                    case "FORMAT_ID":
                        ++items;
                        checkEntry( entry, "8", "24", "==FMT2{cal}", "", "", "" );
                        break;
                    case "REC_TIME":
                        ++items;
                        checkEntry( entry, "64", "32", "", "", "", "" );
                        break;
                    case "FLEETID":
                        ++items;
                        checkEntry( entry, "6", "98", "", "", "", "" );
                        break;
                    case "CONTINUATION_FLAG":
                        ++items;
                        checkEntry( entry, "1", "104", "", "", "", "" );
                        break;
                    case "LAST_REC_FLAG":
                        ++items;
                        checkEntry( entry, "1", "105", "", "", "", "" );
                        break;
                    case "ANT_BEAMID":
                        ++items;
                        checkEntry( entry, "6", "106", "", "", "", "" );
                        break;
                    case "REUSE_TYPE":
                        ++items;
                        checkEntry( entry, "1", "112", "", "", "", "" );
                        break;
                    case "RF_TIMESLOT":
                        ++items;
                        checkEntry( entry, "2", "113", "", "", "", "" );
                        break;
                    case "RX_MODEM_PORTID":
                        ++items;
                        checkEntry( entry, "5", "118", "", "", "", "" );
                        break;
                    case "TX_MODEM_PORTID":
                        ++items;
                        checkEntry( entry, "5", "126", "", "", "", "" );
                        break;
                    case "FMT2_EXTRA_DATA_16":
                        ++items;
                        checkEntry( entry, "16", "136", "", "", "", "" );
                        break;
                    case "FMT2_EXTRA_DATA_32":
                        ++items;
                        checkEntry( entry, "32", "152", "", "", "", "" );
                        break;
                }

            }

            Assert.assertTrue( "Container parameter count of " + containerName + " is " +
                Long.toString( items ) + " but should be 15 items",
                items == 15 );

            for( String warning : model.getWarnings() ) {
                if ( warning.contains( "overlaps item" ) == false ) {
                    assertOnWarnings( model );
                }
            }

        } catch ( Exception ex ) {
            //ex.printStackTrace();
            Assert.fail( ex.getLocalizedMessage() );
        }

    }

    @Test
    public void processContainerWithParameterAtEnd() {

        final String methodName =
            Thread.currentThread().getStackTrace()[1].getMethodName();

        System.out.println( "Test Case: " + methodName + "()" );

        String containerName =
            "/BogusSAT/SC001/Onboard_Processor_Config/Processor_Config_Table";

        try {

            XTCETMContainer container = db_.getContainer( containerName );

            XTCEContainerContentModel model =
                db_.processContainer( container, null, false );

            long sizeInBytes = model.getTotalSize();

            Assert.assertTrue( "Container size of " + containerName + " is " +
                Long.toString( sizeInBytes ) + " but should be 172 bits",
                sizeInBytes == 172 );

            List<XTCEContainerContentEntry> entries = model.getContentList();

            long items = 0;

            for ( XTCEContainerContentEntry entry : entries ) {

                switch (entry.getName()) {
                    case "Table_Processor_ID":
                        ++items;
                        checkEntry( entry, "8", "0", "", "NOT_YET_SET", "", "" );
                        break;
                    case "Config_Log_Levels":
                        ++items;
                        checkEntry( entry, "", "", "", "", "", "" );
                        break;
                    case "Config_Log_Levels.COREINF":
                        ++items;
                        checkEntry( entry, "4", "8", "", "warning", "", "" );
                        break;
                    case "Config_Log_Levels.ANTCTRL":
                        ++items;
                        checkEntry( entry, "4", "12", "", "warning", "", "" );
                        break;
                    case "Config_Log_Levels.TCMGMNT":
                        ++items;
                        checkEntry( entry, "4", "16", "", "warning", "", "" );
                        break;
                    case "Config_Log_Levels.TIME":
                        ++items;
                        checkEntry( entry, "4", "20", "", "warning", "", "" );
                        break;
                    case "Config_Log_Levels.TMMGMNT":
                        ++items;
                        checkEntry( entry, "4", "24", "", "warning", "", "" );
                        break;
                    case "Config_Log_Levels.INTERFACE":
                        ++items;
                        checkEntry( entry, "4", "28", "", "warning", "", "" );
                        break;
                    case "Config_Log_Levels.MW1553":
                        ++items;
                        checkEntry( entry, "4", "32", "", "warning", "", "" );
                        break;
                    case "Config_Watchdog":
                        ++items;
                        checkEntry( entry, "16", "44", "", "5", "", "" );
                        break;
                    case "Config_PROM_Access":
                        ++items;
                        checkEntry( entry, "", "", "", "", "", "" );
                        break;
                    case "Config_PROM_Access.Read":
                        ++items;
                        checkEntry( entry, "16", "60", "", "100", "", "" );
                        break;
                    case "Config_PROM_Access.Write":
                        ++items;
                        checkEntry( entry, "16", "76", "", "100", "", "" );
                        break;
                    case "Config_PROM_Access.Erase":
                        ++items;
                        checkEntry( entry, "16", "92", "", "100", "", "" );
                        break;
                    case "Config_PROM_Access.Lock":
                        ++items;
                        checkEntry( entry, "16", "108", "", "100", "", "" );
                        break;
                    case "Config_Self_Test":
                        ++items;
                        checkEntry( entry, "", "", "", "", "", "" );
                        break;
                    case "Config_Self_Test.pad_2bits":
                        ++items;
                        checkEntry( entry, "2", "124", "", "0x00", "", "" );
                        break;
                    case "Config_Self_Test.Antenna":
                        ++items;
                        checkEntry( entry, "1", "126", "", "ENABLED", "", "" );
                        break;
                    case "Config_Self_Test.CPU":
                        ++items;
                        checkEntry( entry, "1", "127", "", "ENABLED", "", "" );
                        break;
                    case "Config_Self_Test.RAM":
                        ++items;
                        checkEntry( entry, "1", "128", "", "ENABLED", "", "" );
                        break;
                    case "Config_Self_Test.EEPROM":
                        ++items;
                        checkEntry( entry, "1", "129", "", "ENABLED", "", "" );
                        break;
                    case "Config_Self_Test.Flash":
                        ++items;
                        checkEntry( entry, "1", "130", "", "ENABLED", "", "" );
                        break;
                    case "Config_Self_Test.Modem":
                        ++items;
                        checkEntry( entry, "1", "131", "", "ENABLED", "", "" );
                        break;
                    case "Config_Clock_ID":
                        ++items;
                        checkEntry( entry, "8", "132", "", "PRIMARY", "", "" );
                        break;
                    case "Config_Ant_Power_Level":
                        ++items;
                        checkEntry( entry, "16", "140", "", "-0.6", "", "" );
                        break;
                    case "Table_CRC_Value":
                        ++items;
                        checkEntry( entry, "16", "156", "", "", "", "" );
                        break;
                }

            }

            Assert.assertTrue( "Container parameter count of " + containerName + " is " +
                Long.toString( items ) + " but should be 26 items",
                items == 26 );

            assertOnWarnings( model );

        } catch ( Exception ex ) {
            //ex.printStackTrace();
            Assert.fail( ex.getLocalizedMessage() );
        }

    }

    @Test
    public void processContainerWithParameterAtEndInheritance1() {

        final String methodName =
            Thread.currentThread().getStackTrace()[1].getMethodName();

        System.out.println( "Test Case: " + methodName + "()" );

        String containerName =
            "/BogusSAT/SC001/Onboard_Processor_Config/Processor_1_Config_Table";

        try {

            XTCETMContainer container = db_.getContainer( containerName );

            XTCEContainerContentModel model =
                db_.processContainer( container, null, false );

            long sizeInBytes = model.getTotalSize();

            Assert.assertTrue( "Container size of " + containerName + " is " +
                Long.toString( sizeInBytes ) + " but should be 180 bits",
                sizeInBytes == 180 );

            List<XTCEContainerContentEntry> entries = model.getContentList();

            long items = 0;

            for ( XTCEContainerContentEntry entry : entries ) {

                switch (entry.getName()) {
                    case "Table_Processor_ID":
                        ++items;
                        checkEntry( entry, "8", "0", "==PROCESSOR1{cal}", "NOT_YET_SET", "", "" );
                        break;
                    case "Config_Log_Levels":
                        ++items;
                        checkEntry( entry, "", "", "", "", "", "" );
                        break;
                    case "Config_Log_Levels.COREINF":
                        ++items;
                        checkEntry( entry, "4", "8", "", "warning", "", "" );
                        break;
                    case "Config_Log_Levels.ANTCTRL":
                        ++items;
                        checkEntry( entry, "4", "12", "", "warning", "", "" );
                        break;
                    case "Config_Log_Levels.TCMGMNT":
                        ++items;
                        checkEntry( entry, "4", "16", "", "warning", "", "" );
                        break;
                    case "Config_Log_Levels.TIME":
                        ++items;
                        checkEntry( entry, "4", "20", "", "warning", "", "" );
                        break;
                    case "Config_Log_Levels.TMMGMNT":
                        ++items;
                        checkEntry( entry, "4", "24", "", "warning", "", "" );
                        break;
                    case "Config_Log_Levels.INTERFACE":
                        ++items;
                        checkEntry( entry, "4", "28", "", "warning", "", "" );
                        break;
                    case "Config_Log_Levels.MW1553":
                        ++items;
                        checkEntry( entry, "4", "32", "", "warning", "", "" );
                        break;
                    case "Config_Watchdog":
                        ++items;
                        checkEntry( entry, "16", "44", "", "5", "", "" );
                        break;
                    case "Config_PROM_Access":
                        ++items;
                        checkEntry( entry, "", "", "", "", "", "" );
                        break;
                    case "Config_PROM_Access.Read":
                        ++items;
                        checkEntry( entry, "16", "60", "", "100", "", "" );
                        break;
                    case "Config_PROM_Access.Write":
                        ++items;
                        checkEntry( entry, "16", "76", "", "100", "", "" );
                        break;
                    case "Config_PROM_Access.Erase":
                        ++items;
                        checkEntry( entry, "16", "92", "", "100", "", "" );
                        break;
                    case "Config_PROM_Access.Lock":
                        ++items;
                        checkEntry( entry, "16", "108", "", "100", "", "" );
                        break;
                    case "Config_Self_Test":
                        ++items;
                        checkEntry( entry, "", "", "", "", "", "" );
                        break;
                    case "Config_Self_Test.pad_2bits":
                        ++items;
                        checkEntry( entry, "2", "124", "", "0x00", "", "" );
                        break;
                    case "Config_Self_Test.Antenna":
                        ++items;
                        checkEntry( entry, "1", "126", "", "ENABLED", "", "" );
                        break;
                    case "Config_Self_Test.CPU":
                        ++items;
                        checkEntry( entry, "1", "127", "", "ENABLED", "", "" );
                        break;
                    case "Config_Self_Test.RAM":
                        ++items;
                        checkEntry( entry, "1", "128", "", "ENABLED", "", "" );
                        break;
                    case "Config_Self_Test.EEPROM":
                        ++items;
                        checkEntry( entry, "1", "129", "", "ENABLED", "", "" );
                        break;
                    case "Config_Self_Test.Flash":
                        ++items;
                        checkEntry( entry, "1", "130", "", "ENABLED", "", "" );
                        break;
                    case "Config_Self_Test.Modem":
                        ++items;
                        checkEntry( entry, "1", "131", "", "ENABLED", "", "" );
                        break;
                    case "Config_Clock_ID":
                        ++items;
                        checkEntry( entry, "8", "132", "", "PRIMARY", "", "" );
                        break;
                    case "Config_Ant_Power_Level":
                        ++items;
                        checkEntry( entry, "16", "140", "", "-0.6", "", "" );
                        break;
                    case "Config_Payload_IF_ID":
                        ++items;
                        checkEntry( entry, "4", "156", "", "LINK1", "", "" );
                        break;
                    case "Config_Payload_Perf":
                        ++items;
                        checkEntry( entry, "4", "160", "", "NORMAL", "", "" );
                        break;
                    case "Table_CRC_Value":
                        ++items;
                        checkEntry( entry, "16", "164", "", "", "", "" );
                        break;
                }

            }

            Assert.assertTrue( "Container parameter count of " + containerName + " is " +
                Long.toString( items ) + " but should be 28 items",
                items == 28 );

            assertOnWarnings( model );

        } catch ( Exception ex ) {
            //ex.printStackTrace();
            Assert.fail( ex.getLocalizedMessage() );
        }

    }

    @Test
    public void processContainerWithParameterAtEndInheritance2() {

        final String methodName =
            Thread.currentThread().getStackTrace()[1].getMethodName();

        System.out.println( "Test Case: " + methodName + "()" );

        String containerName =
            "/BogusSAT/SC001/Onboard_Processor_Config/Processor_2_Config_Table";

        try {

            XTCETMContainer container = db_.getContainer( containerName );

            XTCEContainerContentModel model =
                db_.processContainer( container, null, false );

            long sizeInBytes = model.getTotalSize();

            Assert.assertTrue( "Container size of " + containerName + " is " +
                Long.toString( sizeInBytes ) + " but should be 172 bits",
                sizeInBytes == 172 );

            List<XTCEContainerContentEntry> entries = model.getContentList();

            long items = 0;

            for ( XTCEContainerContentEntry entry : entries ) {

                switch (entry.getName()) {
                    case "Table_Processor_ID":
                        ++items;
                        checkEntry( entry, "8", "0", "==PROCESSOR2{cal}", "NOT_YET_SET", "", "" );
                        break;
                    case "Config_Log_Levels":
                        ++items;
                        checkEntry( entry, "", "", "", "", "", "" );
                        break;
                    case "Config_Log_Levels.COREINF":
                        ++items;
                        checkEntry( entry, "4", "8", "", "warning", "", "" );
                        break;
                    case "Config_Log_Levels.ANTCTRL":
                        ++items;
                        checkEntry( entry, "4", "12", "", "warning", "", "" );
                        break;
                    case "Config_Log_Levels.TCMGMNT":
                        ++items;
                        checkEntry( entry, "4", "16", "", "warning", "", "" );
                        break;
                    case "Config_Log_Levels.TIME":
                        ++items;
                        checkEntry( entry, "4", "20", "", "warning", "", "" );
                        break;
                    case "Config_Log_Levels.TMMGMNT":
                        ++items;
                        checkEntry( entry, "4", "24", "", "warning", "", "" );
                        break;
                    case "Config_Log_Levels.INTERFACE":
                        ++items;
                        checkEntry( entry, "4", "28", "", "warning", "", "" );
                        break;
                    case "Config_Log_Levels.MW1553":
                        ++items;
                        checkEntry( entry, "4", "32", "", "warning", "", "" );
                        break;
                    case "Config_Watchdog":
                        ++items;
                        checkEntry( entry, "16", "44", "", "5", "", "" );
                        break;
                    case "Config_PROM_Access":
                        ++items;
                        checkEntry( entry, "", "", "", "", "", "" );
                        break;
                    case "Config_PROM_Access.Read":
                        ++items;
                        checkEntry( entry, "16", "60", "", "100", "", "" );
                        break;
                    case "Config_PROM_Access.Write":
                        ++items;
                        checkEntry( entry, "16", "76", "", "100", "", "" );
                        break;
                    case "Config_PROM_Access.Erase":
                        ++items;
                        checkEntry( entry, "16", "92", "", "100", "", "" );
                        break;
                    case "Config_PROM_Access.Lock":
                        ++items;
                        checkEntry( entry, "16", "108", "", "100", "", "" );
                        break;
                    case "Config_Self_Test":
                        ++items;
                        checkEntry( entry, "", "", "", "", "", "" );
                        break;
                    case "Config_Self_Test.pad_2bits":
                        ++items;
                        checkEntry( entry, "2", "124", "", "0x00", "", "" );
                        break;
                    case "Config_Self_Test.Antenna":
                        ++items;
                        checkEntry( entry, "1", "126", "", "ENABLED", "", "" );
                        break;
                    case "Config_Self_Test.CPU":
                        ++items;
                        checkEntry( entry, "1", "127", "", "ENABLED", "", "" );
                        break;
                    case "Config_Self_Test.RAM":
                        ++items;
                        checkEntry( entry, "1", "128", "", "ENABLED", "", "" );
                        break;
                    case "Config_Self_Test.EEPROM":
                        ++items;
                        checkEntry( entry, "1", "129", "", "ENABLED", "", "" );
                        break;
                    case "Config_Self_Test.Flash":
                        ++items;
                        checkEntry( entry, "1", "130", "", "ENABLED", "", "" );
                        break;
                    case "Config_Self_Test.Modem":
                        ++items;
                        checkEntry( entry, "1", "131", "", "ENABLED", "", "" );
                        break;
                    case "Config_Clock_ID":
                        ++items;
                        checkEntry( entry, "8", "132", "", "PRIMARY", "", "" );
                        break;
                    case "Config_Ant_Power_Level":
                        ++items;
                        checkEntry( entry, "16", "140", "", "-0.6", "", "" );
                        break;
                    case "Table_CRC_Value":
                        ++items;
                        checkEntry( entry, "16", "156", "", "", "", "" );
                        break;
                }

            }

            Assert.assertTrue( "Container parameter count of " + containerName + " is " +
                Long.toString( items ) + " but should be 26 items",
                items == 26 );

            assertOnWarnings( model );

        } catch ( Exception ex ) {
            //ex.printStackTrace();
            Assert.fail( ex.getLocalizedMessage() );
        }

    }

    @Test
    public void processContainerWithParameterAtEndInheritanceAndGapContainer() {

        final String methodName =
            Thread.currentThread().getStackTrace()[1].getMethodName();

        System.out.println( "Test Case: " + methodName + "()" );

        String containerName =
            "/BogusSAT/SC001/Onboard_Processor_Config/Processor_3_Config_Table";

        try {

            XTCETMContainer container = db_.getContainer( containerName );

            XTCEContainerContentModel model =
                db_.processContainer( container, null, false );

            long sizeInBytes = model.getTotalSize();

            Assert.assertTrue( "Container size of " + containerName + " is " +
                Long.toString( sizeInBytes ) + " but should be 204 bits",
                sizeInBytes == 204 );

            List<XTCEContainerContentEntry> entries = model.getContentList();

            long items = 0;

            for ( XTCEContainerContentEntry entry : entries ) {

                switch (entry.getName()) {
                    case "Table_Processor_ID":
                        ++items;
                        checkEntry( entry, "8", "0", "==PROCESSOR3{cal}", "NOT_YET_SET", "", "" );
                        break;
                    case "Config_Log_Levels":
                        ++items;
                        checkEntry( entry, "", "", "", "", "", "" );
                        break;
                    case "Config_Log_Levels.COREINF":
                        ++items;
                        checkEntry( entry, "4", "8", "", "warning", "", "" );
                        break;
                    case "Config_Log_Levels.ANTCTRL":
                        ++items;
                        checkEntry( entry, "4", "12", "", "warning", "", "" );
                        break;
                    case "Config_Log_Levels.TCMGMNT":
                        ++items;
                        checkEntry( entry, "4", "16", "", "warning", "", "" );
                        break;
                    case "Config_Log_Levels.TIME":
                        ++items;
                        checkEntry( entry, "4", "20", "", "warning", "", "" );
                        break;
                    case "Config_Log_Levels.TMMGMNT":
                        ++items;
                        checkEntry( entry, "4", "24", "", "warning", "", "" );
                        break;
                    case "Config_Log_Levels.INTERFACE":
                        ++items;
                        checkEntry( entry, "4", "28", "", "warning", "", "" );
                        break;
                    case "Config_Log_Levels.MW1553":
                        ++items;
                        checkEntry( entry, "4", "32", "", "warning", "", "" );
                        break;
                    case "Config_Watchdog":
                        ++items;
                        checkEntry( entry, "16", "44", "", "5", "", "" );
                        break;
                    case "Config_PROM_Access":
                        ++items;
                        checkEntry( entry, "", "", "", "", "", "" );
                        break;
                    case "Config_PROM_Access.Read":
                        ++items;
                        checkEntry( entry, "16", "60", "", "100", "", "" );
                        break;
                    case "Config_PROM_Access.Write":
                        ++items;
                        checkEntry( entry, "16", "76", "", "100", "", "" );
                        break;
                    case "Config_PROM_Access.Erase":
                        ++items;
                        checkEntry( entry, "16", "92", "", "100", "", "" );
                        break;
                    case "Config_PROM_Access.Lock":
                        ++items;
                        checkEntry( entry, "16", "108", "", "100", "", "" );
                        break;
                    case "Config_Self_Test":
                        ++items;
                        checkEntry( entry, "", "", "", "", "", "" );
                        break;
                    case "Config_Self_Test.pad_2bits":
                        ++items;
                        checkEntry( entry, "2", "124", "", "0x00", "", "" );
                        break;
                    case "Config_Self_Test.Antenna":
                        ++items;
                        checkEntry( entry, "1", "126", "", "ENABLED", "", "" );
                        break;
                    case "Config_Self_Test.CPU":
                        ++items;
                        checkEntry( entry, "1", "127", "", "ENABLED", "", "" );
                        break;
                    case "Config_Self_Test.RAM":
                        ++items;
                        checkEntry( entry, "1", "128", "", "ENABLED", "", "" );
                        break;
                    case "Config_Self_Test.EEPROM":
                        ++items;
                        checkEntry( entry, "1", "129", "", "ENABLED", "", "" );
                        break;
                    case "Config_Self_Test.Flash":
                        ++items;
                        checkEntry( entry, "1", "130", "", "ENABLED", "", "" );
                        break;
                    case "Config_Self_Test.Modem":
                        ++items;
                        checkEntry( entry, "1", "131", "", "ENABLED", "", "" );
                        break;
                    case "Config_Clock_ID":
                        ++items;
                        checkEntry( entry, "8", "132", "", "PRIMARY", "", "" );
                        break;
                    case "Config_Ant_Power_Level":
                        ++items;
                        checkEntry( entry, "16", "140", "", "-0.6", "", "" );
                        break;
                    case "Config_Payload_IF_ID":
                        ++items;
                        checkEntry( entry, "4", "180", "", "LINK1", "", "" );
                        break;
                    case "Config_Payload_Perf":
                        ++items;
                        checkEntry( entry, "4", "184", "", "NORMAL", "", "" );
                        break;
                    case "Table_CRC_Value":
                        ++items;
                        checkEntry( entry, "16", "188", "", "", "", "" );
                        break;
                }

            }

            Assert.assertTrue( "Container parameter count of " + containerName + " is " +
                Long.toString( items ) + " but should be 28 items",
                items == 28 );

            assertOnWarnings( model );

        } catch ( Exception ex ) {
            //ex.printStackTrace();
            Assert.fail( ex.getLocalizedMessage() );
        }

    }

    @Test
    public void processDummyEmptyContainer() {

        final String methodName =
            Thread.currentThread().getStackTrace()[1].getMethodName();

        System.out.println( "Test Case: " + methodName + "()" );

        String containerName =
            "/BogusSAT/SC001/Onboard_Processor_Config/Dummy_24bit_Container";

        try {

            XTCETMContainer container = db_.getContainer( containerName );

            XTCEContainerContentModel model =
                db_.processContainer( container, null, false );

            long sizeInBytes = model.getTotalSize();

            Assert.assertTrue( "Container size of " + containerName + " is " +
                Long.toString( sizeInBytes ) + " but should be 24 bits",
                sizeInBytes == 24 );

            List<XTCEContainerContentEntry> entries = model.getContentList();

            long items = entries.size() - 1;

            Assert.assertTrue( "Container parameter count of " + containerName + " is " +
                Long.toString( items ) + " but should be 0 items",
                items == 0 );

            assertOnWarnings( model );

        } catch ( Exception ex ) {
            //ex.printStackTrace();
            Assert.fail( ex.getLocalizedMessage() );
        }

    }

    @Test
    public void testContainmentCheck() {

        final String methodName =
            Thread.currentThread().getStackTrace()[1].getMethodName();

        System.out.println( "Test Case: " + methodName + "()" );

        String containerName = "/BogusSAT/CCSDSPacket";

        try {

            XTCESpaceSystem ss = db_.getSpaceSystem( "/BogusSAT" );

            XTCEParameter ppp_not_in  = ss.getTelemetryParameter( "PUS_Time" );
            XTCEParameter ppp_is_in_1 = ss.getTelemetryParameter( "CCSDS_Packet_Length" );
            XTCEParameter ppp_is_in_2 = ss.getTelemetryParameter( "CCSDS_Packet_ID.APID" );

            XTCETMContainer container = db_.getContainer( containerName );

            if ( container.contains( ppp_not_in ) == true ) {
                Assert.fail( "Parameter " + ppp_not_in.getName() + " should not be in " + containerName );
            }

            if ( container.contains( ppp_is_in_1 ) == false ) {
                Assert.fail( "Parameter " + ppp_is_in_1.getName() + " should be in " + containerName );
            }

            if ( container.contains( ppp_is_in_2 ) == false ) {
                Assert.fail( "Parameter " + ppp_is_in_2.getName() + " should be in " + containerName );
            }

        } catch ( Exception ex ) {
            //ex.printStackTrace();
            Assert.fail( ex.getLocalizedMessage() );
        }

    }

    @Test
    public void testDescriptions() {

        final String methodName =
            Thread.currentThread().getStackTrace()[1].getMethodName();

        System.out.println( "Test Case: " + methodName + "()" );

        try {

            XTCESpaceSystem ss = db_.getSpaceSystem( "/BogusSAT/SC001" );

            XTCETMContainer container1 = ss.getContainer( "ECSS_Service_1_Subservice_1" );
            XTCETMContainer container2 = ss.getContainer( "ECSS_Service_3_Subservice_25" );
            XTCETMContainer container3 = ss.getContainer( "CCSDS_SpacePacket1" );

            if ( container3.getShortDescription().isEmpty() == false ) {
                Assert.fail( "Should not have shortDescription on 'CCSDS_SpacePacket1'" );
            }
            if ( container3.getLongDescription().isEmpty() == false ) {
                Assert.fail( "Should not have LongDescription on 'CCSDS_SpacePacket1'" );
            }
            if ( container3.getDescription().isEmpty() == false ) {
                Assert.fail( "Should not have Combined Description on 'CCSDS_SpacePacket1'" );
            }

            if ( container1.getShortDescription().equals( "Telecommand Acceptance Report - Success (1,1)" ) == false ) {
                Assert.fail( "Should have shortDescription on 'ECSS_Service_1_Subservice_1'" );
            }
            if ( container1.getLongDescription().isEmpty() == false ) {
                Assert.fail( "Should not have LongDescription on 'ECSS_Service_1_Subservice_1'" );
            }
            if ( container1.getDescription().equals( "Telecommand Acceptance Report - Success (1,1)" ) == false ) {
                Assert.fail( "Should have Combined Description on 'ECSS_Service_1_Subservice_1'" );
            }

            if ( container2.getShortDescription().equals( "Housekeeping Parameter Report (3,25)" ) == false ) {
                Assert.fail( "Should have shortDescription on 'ECSS_Service_3_Subservice_25'" );
            }
            if ( container2.getLongDescription().startsWith( "The service 3,25 packet of the ECSS PUS" ) == false ) {
                Assert.fail( "Should have LongDescription on 'ECSS_Service_3_Subservice_25'" );
            }
            if ( container2.getDescription().equals( "Housekeeping Parameter Report (3,25)" ) == false ) {
                Assert.fail( "Should have Combined Description on 'ECSS_Service_3_Subservice_25'" );
            }

            // check the missed branch for the compareTo method

            if ( container1.compareTo( "/CCSDSPacket/CCSDSTelemetryPacket/CCSDSPUSTelemetryPacket/ECSS_Service_1_Subservice_1" ) != 0 ) {
                Assert.fail( "Check comparison of container to '/CCSDSPacket/CCSDSTelemetryPacket/CCSDSPUSTelemetryPacket/ECSS_Service_1_Subservice_1' in compareTo" );
            }

        } catch ( Exception ex ) {
            //ex.printStackTrace();
            Assert.fail( ex.getLocalizedMessage() );
        }

    }

    @Test
    public void checkInvalidContainerPathName() {

        final String methodName =
            Thread.currentThread().getStackTrace()[1].getMethodName();

        System.out.println( "Test Case: " + methodName + "()" );

        try {

            XTCETMContainer container =
                db_.getContainer( "/FOOBAR/ECSS_Service_1_Subservice_1" );

            Assert.fail( "Should have gotten an exception on container lookup for /FOOBAR/ECSS_Service_1_Subservice_1" );

        } catch ( Exception ex ) {
            // do nothing, expect an exception
        }

    }

    @Test
    public void checkFindingContainersWithParameter() {

        final String methodName =
            Thread.currentThread().getStackTrace()[1].getMethodName();

        System.out.println( "Test Case: " + methodName + "()" );

        List<XTCETMContainer> result;

        List<XTCEParameter> parameter1list =
            db_.getTelemetryParameters( "Solar_Array_Voltage_*_Offset" );

        if ( parameter1list.size() != 2 ) {
            Assert.fail( "Should have found 2 for 'Solar_Array_Voltage_*_Offset'" );
        }

        result = db_.findContainers( parameter1list.get( 0 ) );

        if ( result.size() != 1 || result.get( 0 ).getName().equals( "Calibration_Offsets" ) == false ) {
            Assert.fail( "Did not locate Calibration_Offsets for Solar Array Voltage Parameter" );
        }

        List<XTCEParameter> parameter2list =
            db_.getTelecommandParameters( "*CHECK*" );

        if ( parameter2list.size() != 1 ) {
            Assert.fail( "Should have found 1 for '*CHECK*'" );
        }

        result = db_.findContainers( parameter2list.get( 0 ) );

        if ( result.isEmpty() == false ) {
            Assert.fail( "Should have found no containers for *CHECK* parameters" );
        }

    }

    @Test
    public void testXmlOutput() {

        final String methodName =
            Thread.currentThread().getStackTrace()[1].getMethodName();

        System.out.println( "Test Case: " + methodName + "()" );

        String containerName = "/BogusSAT/LOG_MSGS/LOG_ALL";

        try {
            XTCETMContainer container = db_.getContainer( containerName );
            String containerXml = container.toXml();
            System.out.println( containerXml );
        } catch ( XTCEDatabaseException ex ) {
            Assert.fail( "Got exception for TM container XML generation on " +
                         "'" +
                         containerName +
                         "' with '" +
                         ex.getLocalizedMessage() +
                         "'" );
        }

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

    private void loadDocuments() throws XTCEDatabaseException {

        String path = "src/main/resources/org/xtce/toolkit/database/examples";

        File file1 = new File ( path + "/BogusSAT-2.xml" );
        File file2 = new File ( path + "/Diagnostic_Log_Packets.xml" );

        System.out.println( "Loading the " + file1.getName() + " demo database" );

        db_  = new XTCEDatabase( file1, false, false, true );

        System.out.println( "Loading the " + file2.getName() + " demo database" );

        db2_ = new XTCEDatabase( file2, false, false, true );

    }

    // Private Data Members

    private XTCEDatabase db_;
    private XTCEDatabase db2_;

}
