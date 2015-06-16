package com.dcpdr.museumguide;


import java.util.HashMap;

// Parameters class: it defines application parameters
public class Parameters {
    
    // Tiles parameters
    public static final int TILE_HEIGHT = 2288;
    public static final int TILE_WIDTH = 2512;
    public static final int TILE_SIZE = 128;
    public static final String TILES_PATH = "tiles/planimetria/";
    public static final String SAMPLE_IMAGE = "samples/planimetria/planimetria.jpg";
    public static final String TILE_EXTENSION = ".jpg";
    public static final float[] DETAIL_SCALES = {1.00f, 0.5f, 0.25f, 0.125f};

    // GML map parameters
    public static final String MAP_FILE = "maps/museum.gml";
    // GML elements
    public static final String GML_MLG = "MultiLayeredGraph";
    public static final String GML_SLS = "spaceLayers";
    public static final String GML_SLM = "spaceLayerMember";
    public static final String GML_SL = "SpaceLayer";
    public static final String GML_NODES = "nodes";
    public static final String GML_EDGES = "edges";
    public static final String GML_STMEMB = "stateMember";
    public static final String GML_STATE = "State";
    public static final String GML_ID = "gml:id";
    public static final String GML_NAME = "gml:name";
    public static final String GML_GEOM = "geometry";
    public static final String GML_POINT = "gml:Point";
    public static final String GML_POS = "gml:pos";
    public static final String GML_TRMEMB = "transitionMember";
    public static final String GML_TRANS = "Transition";
    public static final String GML_STARTNODE = "start";
    public static final String GML_ENDNODE = "end";
    public static final String GML_ATTR_XREF = "xlink:href";

    // Items parameters
    public static final String ITEM_FILE = "maps/items.xml";

    // XTileView zoom thresholds
    public static final double ZOOM_3 = 0.35;
    public static final double ZOOM_4 = 0.40;
    public static final double ZOOM_5 = 0.5;
    public static final double ZOOM_6 = 0.6;
    public static final double ZOOM_7 = 0.7;
    public static final double ZOOM_8 = 0.8;
    
    // MapGraphMain parameters
    public static final int ROOMS = 0;
    public static final int SENSORS = 1;

    // General parameters
    public static final String PACKAGE_NAME = "com.dcpdr.museumguide";

    // Kontakt parameters
    public static final int REQUEST_ENABLE_BT = 1234;

    // Search Activity parameters
    public static final int REQUEST_SEARCH = 666;

    // HashMap mapping
    public static final HashMap<String,String> sensorIdMap = new HashMap<String,String>(){
        {
            put("hbcm","SS0");
            put("aUZK","SS1");
            put("LmGw","SS2");
            put("SC3Y","SS3");
            put("Tvvr","SS4");
            put("6Ls6","SS5");
            put("hWkm","SS6");
            put("3hI4","SS7");
            put("bpGG","SS8");
            put("wLL9","SS9");
        }
    };

}
