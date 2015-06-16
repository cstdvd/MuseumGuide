<?xml version="1.0" standalone="yes"?>
<!--IndoorGML Editor (Sinedit)-->
<!--http://IndoorGML.net-->
<IndoorFeatures gml:id="IFS" xmlns:gml="http://www.opengis.net/gml/3.2" xmlns:xlink="http://www.w3.org/1999/xlink" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="indoorCore http://stem.cs.pusan.ac.kr/schema/0_9_1/IndoorGMLCore.xsd" xmlns="indoorCore">
  <MultiLayeredGraph gml:id="MLG">
    <spaceLayers gml:id="SLs1">
      <spaceLayerMember>
        <SpaceLayer gml:id="SL1">
          <nodes gml:id="N">
		    <stateMember>
              <State gml:id="EXT">
                <gml:name>EXIT</gml:name>
                <connects gml:id="CONEXT">
                  <transitionMember xlink:href="#TREXT_0" />
                </connects>
                <geometry>
                  <gml:Point gml:id="PT6">
                    <gml:pos>1768 0 0</gml:pos>
                  </gml:Point>
                </geometry>
              </State>
            </stateMember>
            <stateMember>
              <State gml:id="ST0">
                <gml:name>HALLWAY1</gml:name>
				<duality gml:id="DUA0">
				  <cellSpaceMember xlink:href="#CS0" />
				</duality>
                <connects gml:id="CON0">
                  <transitionMember xlink:href="#TREXT_0" />
                  <transitionMember xlink:href="#TR0_2" />
                  <transitionMember xlink:href="#TR0_1" />
				  <transitionMember xlink:href="#TR0_6" />
                </connects>
                <geometry>
                  <gml:Point gml:id="PT0">
                    <gml:pos>1746 871 0</gml:pos>
                  </gml:Point>
                </geometry>
              </State>
            </stateMember>
            <stateMember>
              <State gml:id="ST1">
                <gml:name>HALLWAY2</gml:name>
				<duality gml:id="DUA1">
				  <cellSpaceMember xlink:href="#CS1" />
				</duality>
                <connects gml:id="CON1">
                  <transitionMember xlink:href="#TR0_1" />
                  <transitionMember xlink:href="#TR1_2" />
                  <transitionMember xlink:href="#TR1_3" />
                  <transitionMember xlink:href="#TR1_5" />
                  <transitionMember xlink:href="#TR1_4" />
				  <transitionMember xlink:href="#TR1_7" />
                </connects>
                <geometry>
                  <gml:Point gml:id="PT1">
                    <gml:pos>1126 1624 0</gml:pos>
                  </gml:Point>
                </geometry>
              </State>
            </stateMember>
            <stateMember>
              <State gml:id="ST2">
                <gml:name>HALLWAY3</gml:name>
				<duality gml:id="DUA2">
				  <cellSpaceMember xlink:href="#CS2" />
				</duality>
                <connects gml:id="CON2">
                  <transitionMember xlink:href="#TR0_2" />
                  <transitionMember xlink:href="#TR1_2" />
                </connects>
                <geometry>
                  <gml:Point gml:id="PT2">
                    <gml:pos>2136 1620 0</gml:pos>
                  </gml:Point>
                </geometry>
              </State>
            </stateMember>
            <stateMember>
              <State gml:id="ST3">
                <gml:name>HALLWAY4</gml:name>
				<duality gml:id="DUA3">
				  <cellSpaceMember xlink:href="#CS3" />
				</duality>
                <connects gml:id="CON3">
                  <transitionMember xlink:href="#TR1_3" />
                </connects>
                <geometry>
                  <gml:Point gml:id="PT3">
                    <gml:pos>317 1954 0</gml:pos>
                  </gml:Point>
                </geometry>
              </State>
            </stateMember>
            <stateMember>
              <State gml:id="ST4">
                <gml:name>TOILET</gml:name>
				<duality gml:id="DUA4">
				  <cellSpaceMember xlink:href="#CS4" />
				</duality>
                <connects gml:id="CON4">
                  <transitionMember xlink:href="#TR1_4" />
                </connects>
                <geometry>
                  <gml:Point gml:id="PT4">
                    <gml:pos>718 2015 0</gml:pos>
                  </gml:Point>
                </geometry>
              </State>
            </stateMember>
            <stateMember>
              <State gml:id="ST5">
                <gml:name>EMERGENCY STAIRS</gml:name>
				<duality gml:id="DUA5">
				  <cellSpaceMember xlink:href="#CS5" />
				</duality>
                <connects gml:id="CON5">
                  <transitionMember xlink:href="#TR1_5" />
                </connects>
                <geometry>
                  <gml:Point gml:id="PT5">
                    <gml:pos>869 1257 0</gml:pos>
                  </gml:Point>
                </geometry>
              </State>
            </stateMember>
			<stateMember>
              <State gml:id="ST6">
                <gml:name>ROOM1</gml:name>
				<duality gml:id="DUA6">
				  <cellSpaceMember xlink:href="#CS6" />
				</duality>
                <connects gml:id="CON6">
                  <transitionMember xlink:href="#TR0_6" />
                </connects>
                <geometry>
                  <gml:Point gml:id="PT6">
                    <gml:pos>2100 998 0</gml:pos>
                  </gml:Point>
                </geometry>
              </State>
            </stateMember>
			<stateMember>
              <State gml:id="ST7">
                <gml:name>ROOM2</gml:name>
				<duality gml:id="DUA7">
				  <cellSpaceMember xlink:href="#CS7" />
				</duality>
                <connects gml:id="CON7">
                  <transitionMember xlink:href="#TR1_7" />
                </connects>
                <geometry>
                  <gml:Point gml:id="PT7">
                    <gml:pos>1400 2018 0</gml:pos>
                  </gml:Point>
                </geometry>
              </State>
            </stateMember>
          </nodes>
          <edges gml:id="E">
            <transitionMember>
              <Transition gml:id="TREXT_0">
                <gml:name>TREXT_0</gml:name>
                <weight>1</weight>
				<duality gml:id="DUA6">
				  <cellSpaceBoundaryMember xlink:href="#CSBEXT_0" />
				</duality>
                <start xlink:href="#EXT" />
                <end xlink:href="#ST0" />
                <geometry>
                  <gml:LineString gml:id="LS0">
                    <gml:pos>1768 0 0</gml:pos>
                    <gml:pos>1746 871 0</gml:pos>
                  </gml:LineString>
                </geometry>
              </Transition>
            </transitionMember>
            <transitionMember>
              <Transition gml:id="TR0_2">
                <gml:name>TR0_2</gml:name>
                <weight>1</weight>
				<duality gml:id="DUA7">
				  <cellSpaceBoundaryMember xlink:href="#CSB0_2" />
				</duality>
                <start xlink:href="#ST0" />
                <end xlink:href="#ST2" />
                <geometry>
                  <gml:LineString gml:id="LS1">
                    <gml:pos>1746 871 0</gml:pos>
                    <gml:pos>2136 1620 0</gml:pos>
                  </gml:LineString>
                </geometry>
              </Transition>
            </transitionMember>
            <transitionMember>
              <Transition gml:id="TR0_1">
                <gml:name>TR0_1</gml:name>
                <weight>1</weight>
				<duality gml:id="DUA8">
				  <cellSpaceBoundaryMember xlink:href="#CSB0_1" />
				</duality>
                <start xlink:href="#ST0" />
                <end xlink:href="#ST1" />
                <geometry>
                  <gml:LineString gml:id="LS2">
                    <gml:pos>1746 871 0</gml:pos>
                    <gml:pos>1126 1624 0</gml:pos>
                  </gml:LineString>
                </geometry>
              </Transition>
            </transitionMember>
            <transitionMember>
              <Transition gml:id="TR1_2">
                <gml:name>TR1_2</gml:name>
                <weight>1</weight>
				<duality gml:id="DUA9">
				  <cellSpaceBoundaryMember xlink:href="#CSB1_2" />
				</duality>
                <start xlink:href="#ST1" />
                <end xlink:href="#ST2" />
                <geometry>
                  <gml:LineString gml:id="LS3">
                    <gml:pos>1126 1624 0</gml:pos>
                    <gml:pos>2136 1620 0</gml:pos>
                  </gml:LineString>
                </geometry>
              </Transition>
            </transitionMember>
            <transitionMember>
              <Transition gml:id="TR1_3">
                <gml:name>TR1_3</gml:name>
                <weight>1</weight>
				<duality gml:id="DUA10">
				  <cellSpaceBoundaryMember xlink:href="#CSB1_3" />
				</duality>
                <start xlink:href="#ST1" />
                <end xlink:href="#ST3" />
                <geometry>
                  <gml:LineString gml:id="LS4">
                    <gml:pos>1126 1624 0</gml:pos>
                    <gml:pos>317 1954 0</gml:pos>
                  </gml:LineString>
                </geometry>
              </Transition>
            </transitionMember>
            <transitionMember>
              <Transition gml:id="TR1_5">
                <gml:name>TR1_5</gml:name>
                <weight>1</weight>
				<duality gml:id="DUA11">
				  <cellSpaceBoundaryMember xlink:href="#CSB1_5" />
				</duality>
                <start xlink:href="#ST1" />
                <end xlink:href="#ST5" />
                <geometry>
                  <gml:LineString gml:id="LS5">
                    <gml:pos>1126 1624 0</gml:pos>
                    <gml:pos>869 1257 0</gml:pos>
                  </gml:LineString>
                </geometry>
              </Transition>
            </transitionMember>
            <transitionMember>
              <Transition gml:id="TR1_4">
                <gml:name>TR1_4</gml:name>
                <weight>1</weight>
				<duality gml:id="DUA12">
				  <cellSpaceBoundaryMember xlink:href="#CSB1_4" />
				</duality>
                <start xlink:href="#ST1" />
                <end xlink:href="#ST4" />
                <geometry>
                  <gml:LineString gml:id="LS6">
                    <gml:pos>1126 1624 0</gml:pos>
                    <gml:pos>718 2015 0</gml:pos>
                  </gml:LineString>
                </geometry>
              </Transition>
            </transitionMember>
			<transitionMember>
              <Transition gml:id="TR0_6">
                <gml:name>TR0_6</gml:name>
                <weight>1</weight>
				<duality gml:id="DUA13">
				  <cellSpaceBoundaryMember xlink:href="#CSB0_6" />
				</duality>
                <start xlink:href="#ST0" />
                <end xlink:href="#ST6" />
                <geometry>
                  <gml:LineString gml:id="LS6">
                    <gml:pos>1746 871 0</gml:pos>
                    <gml:pos>2100 998 0</gml:pos>
                  </gml:LineString>
                </geometry>
              </Transition>
            </transitionMember>
			<transitionMember>
              <Transition gml:id="TR1_7">
                <gml:name>TR1_7</gml:name>
                <weight>1</weight>
				<duality gml:id="DUA13">
				  <cellSpaceBoundaryMember xlink:href="#CSB1_7" />
				</duality>
                <start xlink:href="#ST1" />
                <end xlink:href="#ST7" />
                <geometry>
                  <gml:LineString gml:id="LS6">
                    <gml:pos>1126 1624 0</gml:pos>
                    <gml:pos>1400 2018 0</gml:pos>
                  </gml:LineString>
                </geometry>
              </Transition>
            </transitionMember>
          </edges>
        </SpaceLayer>
      </spaceLayerMember>
      <spaceLayerMember>
        <SpaceLayer gml:id="SL2">
          <nodes gml:id="N1">
            <stateMember>
              <State gml:id="SS0">
                <gml:name>SS0</gml:name>
                <connects gml:id="CONS0">
                  <transitionMember xlink:href="#TRS0_1" />
                </connects>
                <geometry>
                  <gml:Point gml:id="PTS0">
                    <gml:pos>1750 288 0</gml:pos>
                  </gml:Point>
                </geometry>
              </State>
            </stateMember>
            <stateMember>
              <State gml:id="SS1">
                <gml:name>SS1</gml:name>
                <connects gml:id="CONS1">
                  <transitionMember xlink:href="#TRS0_1" />
                  <transitionMember xlink:href="#TRS1_2" />
				  <transitionMember xlink:href="#TRS1_6" />
                </connects>
                <geometry>
                  <gml:Point gml:id="PTS1">
                    <gml:pos>1750 738 0</gml:pos>
                  </gml:Point>
                </geometry>
              </State>
            </stateMember>
            <stateMember>
              <State gml:id="SS2">
                <gml:name>SS2</gml:name>
                <connects gml:id="CONS2">
                  <transitionMember xlink:href="#TRS1_2" />
                  <transitionMember xlink:href="#TRS2_3" />
                  <transitionMember xlink:href="#TRS2_4" />
                </connects>
                <geometry>
                  <gml:Point gml:id="PTS2">
                    <gml:pos>1750 1288 0</gml:pos>
                  </gml:Point>
                </geometry>
              </State>
            </stateMember>
            <stateMember>
              <State gml:id="SS3">
                <gml:name>SS3</gml:name>
                <connects gml:id="CONS3">
                  <transitionMember xlink:href="#TRS2_3" />
                  <transitionMember xlink:href="#TRS4_3" />
                </connects>
                <geometry>
                  <gml:Point gml:id="PTS3">
                    <gml:pos>2200 1636 0</gml:pos>
                  </gml:Point>
                </geometry>
              </State>
            </stateMember>
            <stateMember>
              <State gml:id="SS4">
                <gml:name>SS4</gml:name>
                <connects gml:id="CONS4">
                  <transitionMember xlink:href="#TRS2_4" />
                  <transitionMember xlink:href="#TRS4_5" />
                  <transitionMember xlink:href="#TRS4_3" />
				  <transitionMember xlink:href="#TRS4_7" />
                </connects>
                <geometry>
                  <gml:Point gml:id="PTS4">
                    <gml:pos>1290 1628 0</gml:pos>
                  </gml:Point>
                </geometry>
              </State>
            </stateMember>
            <stateMember>
              <State gml:id="SS5">
                <gml:name>SS5</gml:name>
                <connects gml:id="CONS5">
                  <transitionMember xlink:href="#TRS4_5" />
				  <transitionMember xlink:href="#TRS5_8" />
				  <transitionMember xlink:href="#TRS5_9" />
                </connects>
                <geometry>
                  <gml:Point gml:id="PTS5">
                    <gml:pos>590 1636 0</gml:pos>
                  </gml:Point>
                </geometry>
              </State>
            </stateMember>
			<stateMember>
              <State gml:id="SS6">
                <gml:name>SS6</gml:name>
                <connects gml:id="CONS6">
                  <transitionMember xlink:href="#TRS1_6" />
                </connects>
                <geometry>
                  <gml:Point gml:id="PTS6">
                    <gml:pos>2100 998 0</gml:pos>
                  </gml:Point>
                </geometry>
              </State>
            </stateMember>
			<stateMember>
              <State gml:id="SS7">
                <gml:name>SS7</gml:name>
                <connects gml:id="CONS7">
                  <transitionMember xlink:href="#TRS4_7" />
                </connects>
                <geometry>
                  <gml:Point gml:id="PTS7">
                    <gml:pos>1400 2018 0</gml:pos>
                  </gml:Point>
                </geometry>
              </State>
            </stateMember>
			<stateMember>
              <State gml:id="SS8">
                <gml:name>SS8</gml:name>
                <connects gml:id="CONS8">
                  <transitionMember xlink:href="#TRS5_8" />
                </connects>
                <geometry>
                  <gml:Point gml:id="PTS8">
                    <gml:pos>718 2015 0</gml:pos>
                  </gml:Point>
                </geometry>
              </State>
            </stateMember>
			<stateMember>
              <State gml:id="SS9">
                <gml:name>SS9</gml:name>
                <connects gml:id="CONS9">
                  <transitionMember xlink:href="#TRS5_9" />
                </connects>
                <geometry>
                  <gml:Point gml:id="PTS9">
                    <gml:pos>317 1954 0</gml:pos>
                  </gml:Point>
                </geometry>
              </State>
            </stateMember>
          </nodes>
          <edges gml:id="E1">
            <transitionMember>
              <Transition gml:id="TRS0_1">
                <gml:name>TRS0_1</gml:name>
                <weight>1</weight>
                <start xlink:href="#SS0" />
                <end xlink:href="#SS1" />
                <geometry>
                  <gml:LineString gml:id="LSS0">
                    <gml:pos>1761 52 0</gml:pos>
                    <gml:pos>1754 521 0</gml:pos>
                  </gml:LineString>
                </geometry>
              </Transition>
            </transitionMember>
            <transitionMember>
              <Transition gml:id="TRS1_2">
                <gml:name>TRS1_2</gml:name>
                <weight>1</weight>
                <start xlink:href="#SS1" />
                <end xlink:href="#SS2" />
                <geometry>
                  <gml:LineString gml:id="LSS1">
                    <gml:pos>1754 521 0</gml:pos>
                    <gml:pos>1746 1066 0</gml:pos>
                  </gml:LineString>
                </geometry>
              </Transition>
            </transitionMember>
            <transitionMember>
              <Transition gml:id="TRS2_3">
                <gml:name>TRS2_3</gml:name>
                <weight>1</weight>
                <start xlink:href="#SS2" />
                <end xlink:href="#SS3" />
                <geometry>
                  <gml:LineString gml:id="LSS2">
                    <gml:pos>1746 1066 0</gml:pos>
                    <gml:pos>2200 1636 0</gml:pos>
                  </gml:LineString>
                </geometry>
              </Transition>
            </transitionMember>
            <transitionMember>
              <Transition gml:id="TRS2_4">
                <gml:name>TRS2_4</gml:name>
                <weight>1</weight>
                <start xlink:href="#SS2" />
                <end xlink:href="#SS4" />
                <geometry>
                  <gml:LineString gml:id="LSS3">
                    <gml:pos>1746 1066 0</gml:pos>
                    <gml:pos>1290 1628 0</gml:pos>
                  </gml:LineString>
                </geometry>
              </Transition>
            </transitionMember>
            <transitionMember>
              <Transition gml:id="TRS4_5">
                <gml:name>TRS4_5</gml:name>
                <weight>1</weight>
                <start xlink:href="#SS4" />
                <end xlink:href="#SS5" />
                <geometry>
                  <gml:LineString gml:id="LSS4">
                    <gml:pos>1407 1628 0</gml:pos>
                    <gml:pos>580 1636 0</gml:pos>
                  </gml:LineString>
                </geometry>
              </Transition>
            </transitionMember>
            <transitionMember>
              <Transition gml:id="TRS4_3">
                <gml:name>TRS4_3</gml:name>
                <weight>1</weight>
                <start xlink:href="#SS4" />
                <end xlink:href="#SS3" />
                <geometry>
                  <gml:LineString gml:id="LSS5">
                    <gml:pos>1407 1628 0</gml:pos>
                    <gml:pos>2114 1636 0</gml:pos>
                  </gml:LineString>
                </geometry>
              </Transition>
            </transitionMember>
			<transitionMember>
              <Transition gml:id="TRS1_6">
                <gml:name>TRS1_6</gml:name>
                <weight>1</weight>
                <start xlink:href="#SS1" />
                <end xlink:href="#SS6" />
                <geometry>
                  <gml:LineString gml:id="LSS6">
                    <gml:pos>1750 738 0</gml:pos>
                    <gml:pos>2100 998 0</gml:pos>
                  </gml:LineString>
                </geometry>
              </Transition>
            </transitionMember>
			<transitionMember>
              <Transition gml:id="TRS4_7">
                <gml:name>TRS4_7</gml:name>
                <weight>1</weight>
                <start xlink:href="#SS4" />
                <end xlink:href="#SS7" />
                <geometry>
                  <gml:LineString gml:id="LSS7">
                    <gml:pos>1407 1628 0</gml:pos>
                    <gml:pos>1400 2018 0</gml:pos>
                  </gml:LineString>
                </geometry>
              </Transition>
            </transitionMember>
			<transitionMember>
              <Transition gml:id="TRS5_8">
                <gml:name>TRS5_8</gml:name>
                <weight>1</weight>
                <start xlink:href="#SS5" />
                <end xlink:href="#SS8" />
                <geometry>
                  <gml:LineString gml:id="LSS8">
                    <gml:pos>590 1636 0</gml:pos>
                    <gml:pos>718 2015 0</gml:pos>
                  </gml:LineString>
                </geometry>
              </Transition>
            </transitionMember>
			<transitionMember>
              <Transition gml:id="TRS5_9">
                <gml:name>TRS5_9</gml:name>
                <weight>1</weight>
                <start xlink:href="#SS5" />
                <end xlink:href="#SS9" />
                <geometry>
                  <gml:LineString gml:id="LSS9">
                    <gml:pos>590 1636 0</gml:pos>
                    <gml:pos>317 1954 0</gml:pos>
                  </gml:LineString>
                </geometry>
              </Transition>
            </transitionMember>
          </edges>
        </SpaceLayer>
      </spaceLayerMember>
	</spaceLayers>
	<interEdges gml:id="IEs1">
	  <InterLayerConnection gml:id="ILC1">
	    <interConnects>
		  <stateMember xlink:href="#ST0" />
		</interConnects>
		<interConnects>
		  <stateMember xlink:href="#SS0" />
		</interConnects>
		<ConnectedLayers>
		  <spaceLayerMember xlink:href="#SL1" />
		</ConnectedLayers>
		<ConnectedLayers>
		  <spaceLayerMember xlink:href="#SL2" />
		</ConnectedLayers>
		<typeOfTopoExpression>CONTAINS</typeOfTopoExpression>
	  </InterLayerConnection>
	  <InterLayerConnection gml:id="ILC2">
	    <interConnects>
		  <stateMember xlink:href="#ST0" />
		</interConnects>
		<interConnects>
		  <stateMember xlink:href="#SS1" />
		</interConnects>
		<ConnectedLayers>
		  <spaceLayerMember xlink:href="#SL1" />
		</ConnectedLayers>
		<ConnectedLayers>
		  <spaceLayerMember xlink:href="#SL2" />
		</ConnectedLayers>
		<typeOfTopoExpression>CONTAINS</typeOfTopoExpression>
	  </InterLayerConnection>
	  <InterLayerConnection gml:id="ILC3">
	    <interConnects>
		  <stateMember xlink:href="#ST0" />
		</interConnects>
		<interConnects>
		  <stateMember xlink:href="#SS2" />
		</interConnects>
		<ConnectedLayers>
		  <spaceLayerMember xlink:href="#SL1" />
		</ConnectedLayers>
		<ConnectedLayers>
		  <spaceLayerMember xlink:href="#SL2" />
		</ConnectedLayers>
		<typeOfTopoExpression>CONTAINS</typeOfTopoExpression>
	  </InterLayerConnection>
	  <InterLayerConnection gml:id="ILC4">
	    <interConnects>
		  <stateMember xlink:href="#ST2" />
		</interConnects>
		<interConnects>
		  <stateMember xlink:href="#SS3" />
		</interConnects>
		<ConnectedLayers>
		  <spaceLayerMember xlink:href="#SL1" />
		</ConnectedLayers>
		<ConnectedLayers>
		  <spaceLayerMember xlink:href="#SL2" />
		</ConnectedLayers>
		<typeOfTopoExpression>CONTAINS</typeOfTopoExpression>
	  </InterLayerConnection>
	  <InterLayerConnection gml:id="ILC5">
	    <interConnects>
		  <stateMember xlink:href="#ST1" />
		</interConnects>
		<interConnects>
		  <stateMember xlink:href="#SS4" />
		</interConnects>
		<ConnectedLayers>
		  <spaceLayerMember xlink:href="#SL1" />
		</ConnectedLayers>
		<ConnectedLayers>
		  <spaceLayerMember xlink:href="#SL2" />
		</ConnectedLayers>
		<typeOfTopoExpression>CONTAINS</typeOfTopoExpression>
	  </InterLayerConnection>
	  <InterLayerConnection gml:id="ILC6">
	    <interConnects>
		  <stateMember xlink:href="#ST1" />
		</interConnects>
		<interConnects>
		  <stateMember xlink:href="#SS5" />
		</interConnects>
		<ConnectedLayers>
		  <spaceLayerMember xlink:href="#SL1" />
		</ConnectedLayers>
		<ConnectedLayers>
		  <spaceLayerMember xlink:href="#SL2" />
		</ConnectedLayers>
		<typeOfTopoExpression>CONTAINS</typeOfTopoExpression>
	  </InterLayerConnection>
	  <InterLayerConnection gml:id="ILC7">
	    <interConnects>
		  <stateMember xlink:href="#ST6" />
		</interConnects>
		<interConnects>
		  <stateMember xlink:href="#SS6" />
		</interConnects>
		<ConnectedLayers>
		  <spaceLayerMember xlink:href="#SL1" />
		</ConnectedLayers>
		<ConnectedLayers>
		  <spaceLayerMember xlink:href="#SL2" />
		</ConnectedLayers>
		<typeOfTopoExpression>CONTAINS</typeOfTopoExpression>
	  </InterLayerConnection>
	  <InterLayerConnection gml:id="ILC8">
	    <interConnects>
		  <stateMember xlink:href="#ST7" />
		</interConnects>
		<interConnects>
		  <stateMember xlink:href="#SS7" />
		</interConnects>
		<ConnectedLayers>
		  <spaceLayerMember xlink:href="#SL1" />
		</ConnectedLayers>
		<ConnectedLayers>
		  <spaceLayerMember xlink:href="#SL2" />
		</ConnectedLayers>
		<typeOfTopoExpression>CONTAINS</typeOfTopoExpression>
	  </InterLayerConnection>
	  <InterLayerConnection gml:id="ILC9">
	    <interConnects>
		  <stateMember xlink:href="#ST4" />
		</interConnects>
		<interConnects>
		  <stateMember xlink:href="#SS8" />
		</interConnects>
		<ConnectedLayers>
		  <spaceLayerMember xlink:href="#SL1" />
		</ConnectedLayers>
		<ConnectedLayers>
		  <spaceLayerMember xlink:href="#SL2" />
		</ConnectedLayers>
		<typeOfTopoExpression>CONTAINS</typeOfTopoExpression>
	  </InterLayerConnection>
	  <InterLayerConnection gml:id="ILC10">
	    <interConnects>
		  <stateMember xlink:href="#ST3" />
		</interConnects>
		<interConnects>
		  <stateMember xlink:href="#SS9" />
		</interConnects>
		<ConnectedLayers>
		  <spaceLayerMember xlink:href="#SL1" />
		</ConnectedLayers>
		<ConnectedLayers>
		  <spaceLayerMember xlink:href="#SL2" />
		</ConnectedLayers>
		<typeOfTopoExpression>CONTAINS</typeOfTopoExpression>
	  </InterLayerConnection>
	</interEdges>
  </MultiLayeredGraph>
  <PrimalSpaceFeatures gml:id="PSF">
    <cellSpaceMember gml:id="CSM1">
	  <CellSpace gml:id="CS0">
	    <gml:name>CS0</gml:name>
	    <geometry>
			<gml:Polygon gml:id="PO0">
			  <gml:pos>1650 0 0</gml:pos>
              <gml:pos>1853 0 0</gml:pos>
			  <gml:pos>1650 1538 0</gml:pos>
              <gml:pos>1853 1538 0</gml:pos>
			</gml:Polygon>
		</geometry>
	  </CellSpace>
	  <CellSpace gml:id="CS1">
	    <gml:name>CS1</gml:name>
		<geometry>
			<gml:Polygon gml:id="PO1">
			  <gml:pos>1760 1538 0</gml:pos>
              <gml:pos>1760 1713 0</gml:pos>
			  <gml:pos>465 1538 0</gml:pos>
              <gml:pos>465 1713 0</gml:pos>
			</gml:Polygon>
		</geometry>
	  </CellSpace>
	  <CellSpace gml:id="CS2">
	    <gml:name>CS2</gml:name>
	    <geometry>
			<gml:Polygon gml:id="PO2">
			  <gml:pos>1760 1538 0</gml:pos>
              <gml:pos>1760 1713 0</gml:pos>
			  <gml:pos>2350 1538 0</gml:pos>
              <gml:pos>2350 1713 0</gml:pos>
			</gml:Polygon>
		</geometry>
	  </CellSpace>
	  <CellSpace gml:id="CS3">
	   <gml:name>CS3</gml:name>
	    <geometry>
			<gml:Polygon gml:id="PO3">
			  <gml:pos>465 1538 0</gml:pos>
              <gml:pos>190 1538 0</gml:pos>
			  <gml:pos>190 2238 0</gml:pos>
              <gml:pos>465 2238 0</gml:pos>
			</gml:Polygon>
		</geometry>
	  </CellSpace>
	  <CellSpace gml:id="CS4">
	    <gml:name>CS4</gml:name>
	    <geometry>
			<gml:Polygon gml:id="PO4">
			  <gml:pos>515 1763 0</gml:pos>
              <gml:pos>515 2238 0</gml:pos>
			  <gml:pos>911 2238 0</gml:pos>
              <gml:pos>911 1763 0</gml:pos>
			</gml:Polygon>
		</geometry>
	  </CellSpace>
	  <CellSpace gml:id="CS5">
	    <gml:name>CS5</gml:name>
	    <geometry>
			<gml:Polygon gml:id="PO5">
			  <gml:pos>755 1498 0</gml:pos>
              <gml:pos>985 1498 0</gml:pos>
			  <gml:pos>985 979 0</gml:pos>
              <gml:pos>755 979 0</gml:pos>
			</gml:Polygon>
		</geometry>
	  </CellSpace>
	  <CellSpace gml:id="CS6">
	    <gml:name>CS6</gml:name>
	    <geometry>
			<gml:Polygon gml:id="PO6">
			  <gml:pos>1853 1498 0</gml:pos>
              <gml:pos>1853 518 0</gml:pos>
			  <gml:pos>2350 1498 0</gml:pos>
              <gml:pos>2350 518 0</gml:pos>
			</gml:Polygon>
		</geometry>
	  </CellSpace>
	  <CellSpace gml:id="CS7">
	    <gml:name>CS7</gml:name>
	    <geometry>
			<gml:Polygon gml:id="PO7">
			  <gml:pos>930 1738 0</gml:pos>
              <gml:pos>2050 1738 0</gml:pos>
			  <gml:pos>2050 2258 0</gml:pos>
              <gml:pos>930 2258 0</gml:pos>
			</gml:Polygon>
		</geometry>
	  </CellSpace>
	</cellSpaceMember>
	<cellSpaceBoundaryMember gml:id="CSBM">
	  <CellSpaceBoundary gml:id="CSBEXT_0">
	    <geometry>
		  <LineString gml:id="LS7">
		    <gml:pos>1650 0 0</gml:pos>
            <gml:pos>1853 0 0</gml:pos>
		  </LineString>
		</geometry>
	  </CellSpaceBoundary>
	  <CellSpaceBoundary gml:id="CSB0_1">
	    <geometry>
		  <LineString gml:id="LS8">
		    <gml:pos>1650 1538 0</gml:pos>
            <gml:pos>1755 1538 0</gml:pos>
		  </LineString>
		</geometry>
	  </CellSpaceBoundary>
	  <CellSpaceBoundary gml:id="CSB0_2">
	    <geometry>
		  <LineString gml:id="LS9">
		    <gml:pos>1755 1538 0</gml:pos>
            <gml:pos>1853 1538 0</gml:pos>
		  </LineString>
		</geometry>
	  </CellSpaceBoundary>
	  <CellSpaceBoundary gml:id="CSB1_2">
	    <geometry>
		  <LineString gml:id="LS10">
		    <gml:pos>1755 1538 0</gml:pos>
            <gml:pos>1755 1713 0</gml:pos>
		  </LineString>
		</geometry>
	  </CellSpaceBoundary>
	  <CellSpaceBoundary gml:id="CSB1_3">
	    <geometry>
		  <LineString gml:id="LS11">
		    <gml:pos>465 1538 0</gml:pos>
            <gml:pos>146 1713 0</gml:pos>
		  </LineString>
		</geometry>
	  </CellSpaceBoundary>
	  <CellSpaceBoundary gml:id="CSB1_4">
	    <geometry>
		  <LineString gml:id="LS12">
		    <gml:pos>805 1713 0</gml:pos>
            <gml:pos>870 1713 0</gml:pos>
		  </LineString>
		</geometry>
	  </CellSpaceBoundary>
	  <CellSpaceBoundary gml:id="CSB1_5">
	    <geometry>
		  <LineString gml:id="LS13">
		    <gml:pos>812 1538 0</gml:pos>
            <gml:pos>925 1538 0</gml:pos>
		  </LineString>
		</geometry>
	  </CellSpaceBoundary>
	  <CellSpaceBoundary gml:id="CSB0_6">
	    <geometry>
		  <LineString gml:id="LS14">
		    <gml:pos>1853 790 0</gml:pos>
            <gml:pos>1853 690 0</gml:pos>
		  </LineString>
		</geometry>
	  </CellSpaceBoundary>
	  <CellSpaceBoundary gml:id="CSB1_7">
	    <geometry>
		  <LineString gml:id="LS15">
		    <gml:pos>1400 1738 0</gml:pos>
            <gml:pos>1500 1738 0</gml:pos>
		  </LineString>
		</geometry>
	  </CellSpaceBoundary>
	</cellSpaceBoundaryMember>
  </PrimalSpaceFeatures>
</IndoorFeatures>