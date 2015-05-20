package com.dcpdr.museumguide;

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

    // XTileView zoom thresholds
    public static final double ZOOM_XL = 0.9;
    public static final double ZOOM_L = 0.75;
    public static final double ZOOM_M = 0.60;
    public static final double ZOOM_S = 0.40;

    // General parameters
    public static final String PACKAGE_NAME = "com.dcpdr.museumguide";
}
