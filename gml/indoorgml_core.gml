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
                <gml:name>EXT</gml:name>
                <connects gml:id="CONEXT">
                  <transitionMember xlink:href="#TREXT_0" />
                </connects>
                <geometry>
                  <gml:Point gml:id="PT6">
                    <gml:pos>1768,50574712644 0 0</gml:pos>
                  </gml:Point>
                </geometry>
              </State>
            </stateMember>
            <stateMember>
              <State gml:id="ST0">
                <gml:name>ST0</gml:name>
                <connects gml:id="CON0">
                  <transitionMember xlink:href="#TREXT_0" />
                  <transitionMember xlink:href="#TR0_2" />
                  <transitionMember xlink:href="#TR0_1" />
                </connects>
                <geometry>
                  <gml:Point gml:id="PT0">
                    <gml:pos>1746,85057471264 871,231316725979 0</gml:pos>
                  </gml:Point>
                </geometry>
              </State>
            </stateMember>
            <stateMember>
              <State gml:id="ST1">
                <gml:name>ST1</gml:name>
                <connects gml:id="CON1">
                  <transitionMember xlink:href="#TR0_1" />
                  <transitionMember xlink:href="#TR1_2" />
                  <transitionMember xlink:href="#TR1_3" />
                  <transitionMember xlink:href="#TR1_5" />
                  <transitionMember xlink:href="#TR1_4" />
                </connects>
                <geometry>
                  <gml:Point gml:id="PT1">
                    <gml:pos>1126,06896551724 1624,39857651246 0</gml:pos>
                  </gml:Point>
                </geometry>
              </State>
            </stateMember>
            <stateMember>
              <State gml:id="ST2">
                <gml:name>ST2</gml:name>
                <connects gml:id="CON2">
                  <transitionMember xlink:href="#TR0_2" />
                  <transitionMember xlink:href="#TR1_2" />
                </connects>
                <geometry>
                  <gml:Point gml:id="PT2">
                    <gml:pos>2136,64367816092 1620,32740213523 0</gml:pos>
                  </gml:Point>
                </geometry>
              </State>
            </stateMember>
            <stateMember>
              <State gml:id="ST3">
                <gml:name>ST3</gml:name>
                <connects gml:id="CON3">
                  <transitionMember xlink:href="#TR1_3" />
                </connects>
                <geometry>
                  <gml:Point gml:id="PT3">
                    <gml:pos>317,609195402299 1954,16370106762 0</gml:pos>
                  </gml:Point>
                </geometry>
              </State>
            </stateMember>
            <stateMember>
              <State gml:id="ST4">
                <gml:name>ST4</gml:name>
                <connects gml:id="CON4">
                  <transitionMember xlink:href="#TR1_4" />
                </connects>
                <geometry>
                  <gml:Point gml:id="PT4">
                    <gml:pos>718,229885057472 2015,23131672598 0</gml:pos>
                  </gml:Point>
                </geometry>
              </State>
            </stateMember>
            <stateMember>
              <State gml:id="ST5">
                <gml:name>ST5</gml:name>
                <connects gml:id="CON5">
                  <transitionMember xlink:href="#TR1_5" />
                </connects>
                <geometry>
                  <gml:Point gml:id="PT5">
                    <gml:pos>869,816091954023 1257,99288256228 0</gml:pos>
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
                <start xlink:href="#EXT" />
                <end xlink:href="#ST0" />
                <geometry>
                  <gml:LineString gml:id="LS0">
                    <gml:pos>1768,50574712644 4,07117437722425 0</gml:pos>
                    <gml:pos>1746,85057471264 871,231316725979 0</gml:pos>
                  </gml:LineString>
                </geometry>
              </Transition>
            </transitionMember>
            <transitionMember>
              <Transition gml:id="TR0_2">
                <gml:name>TR0_2</gml:name>
                <weight>1</weight>
                <start xlink:href="#ST0" />
                <end xlink:href="#ST2" />
                <geometry>
                  <gml:LineString gml:id="LS1">
                    <gml:pos>1746,85057471264 871,231316725979 0</gml:pos>
                    <gml:pos>2136,64367816092 1620,32740213523 0</gml:pos>
                  </gml:LineString>
                </geometry>
              </Transition>
            </transitionMember>
            <transitionMember>
              <Transition gml:id="TR0_1">
                <gml:name>TR0_1</gml:name>
                <weight>1</weight>
                <start xlink:href="#ST0" />
                <end xlink:href="#ST1" />
                <geometry>
                  <gml:LineString gml:id="LS2">
                    <gml:pos>1746,85057471264 871,231316725979 0</gml:pos>
                    <gml:pos>1126,06896551724 1624,39857651246 0</gml:pos>
                  </gml:LineString>
                </geometry>
              </Transition>
            </transitionMember>
            <transitionMember>
              <Transition gml:id="TR1_2">
                <gml:name>TR1_2</gml:name>
                <weight>1</weight>
                <start xlink:href="#ST1" />
                <end xlink:href="#ST2" />
                <geometry>
                  <gml:LineString gml:id="LS3">
                    <gml:pos>1126,06896551724 1624,39857651246 0</gml:pos>
                    <gml:pos>2136,64367816092 1620,32740213523 0</gml:pos>
                  </gml:LineString>
                </geometry>
              </Transition>
            </transitionMember>
            <transitionMember>
              <Transition gml:id="TR1_3">
                <gml:name>TR1_3</gml:name>
                <weight>1</weight>
                <start xlink:href="#ST1" />
                <end xlink:href="#ST3" />
                <geometry>
                  <gml:LineString gml:id="LS4">
                    <gml:pos>1126,06896551724 1624,39857651246 0</gml:pos>
                    <gml:pos>317,609195402299 1954,16370106762 0</gml:pos>
                  </gml:LineString>
                </geometry>
              </Transition>
            </transitionMember>
            <transitionMember>
              <Transition gml:id="TR1_5">
                <gml:name>TR1_5</gml:name>
                <weight>1</weight>
                <start xlink:href="#ST1" />
                <end xlink:href="#ST5" />
                <geometry>
                  <gml:LineString gml:id="LS5">
                    <gml:pos>1126,06896551724 1624,39857651246 0</gml:pos>
                    <gml:pos>869,816091954023 1257,99288256228 0</gml:pos>
                  </gml:LineString>
                </geometry>
              </Transition>
            </transitionMember>
            <transitionMember>
              <Transition gml:id="TR1_4">
                <gml:name>TR1_4</gml:name>
                <weight>1</weight>
                <start xlink:href="#ST1" />
                <end xlink:href="#ST4" />
                <geometry>
                  <gml:LineString gml:id="LS6">
                    <gml:pos>1126,06896551724 1624,39857651246 0</gml:pos>
                    <gml:pos>718,229885057472 2015,23131672598 0</gml:pos>
                  </gml:LineString>
                </geometry>
              </Transition>
            </transitionMember>
          </edges>
        </SpaceLayer>
      </spaceLayerMember>
    </spaceLayers>
  </MultiLayeredGraph>
</IndoorFeatures>