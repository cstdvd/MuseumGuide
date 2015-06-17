package com.dcpdr.museumguide;

// eXtended TileView class: it allows to add markers whose images change according to
// the current zoom level. You must define the thresholds and puts the images in the drawable
// folder with their corresponding names.

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.widget.ImageView;

import com.qozix.tileview.TileView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class XTileView extends TileView
{
    // ***********************
    // * Marker nested class *
    // ***********************
    private class Marker
    {
        public ImageView imageView;
        public String imageBaseName;
        public double[] coords;
        public double[] shifts;

        public Marker(ImageView imageView, String imageBaseName, double coordX, double coordY)
        {
            this.imageView = imageView;
            this.imageBaseName = imageBaseName;
            coords = new double[2];
            coords[0] = coordX;
            coords[1] = coordY;
            shifts = new double[2];
        }

    }


    // ******************
    // * Navigable Path *
    // ******************
    public static class NavigablePath
    {
        public List<double[]> points;

        public NavigablePath(List<double[]> points)
        {
            this.points = points;
        }
    }


    // **************************
    // * XTileViewEventListener *
    // **************************
    private class XTileViewEventListener extends TileViewEventListenerImplementation
    {
        @Override
        public void onScaleChanged(double scale)
        {
            XTileView extObj = XTileView.this;
            Marker tmp;
            int resId;

            // get the current zoom level
            String zoomLevel = XTileView.getZoomLevel(scale);

            // change all zoomable markers
            for(String key : extObj.markerList.keySet())
            {
                tmp = extObj.markerList.get(key);
                resId = getResources().getIdentifier(tmp.imageBaseName + "_" + zoomLevel, "drawable", Parameters.PACKAGE_NAME);
                tmp.imageView.setImageResource(resId);
                BitmapDrawable bd=(BitmapDrawable) getResources().getDrawable(resId);
                tmp.shifts[0] = ((bd.getBitmap().getWidth()/2));
                tmp.shifts[1] = ((bd.getBitmap().getHeight()/2));
                extObj.moveMarker(tmp.imageView, tmp.coords[0], tmp.coords[1]);
            }
        }
    }

    // XTileView members
    private Context context;
    private HashMap<String, Marker> markerList;
    private XTileViewEventListener xListener;
    private HashMap<String, NavigablePath> paths;

    // XTileView methods
    public XTileView(Context context)
    {
        super(context);
        this.context = context;
        markerList = new HashMap<>();
        xListener = new XTileViewEventListener();
        paths = new HashMap<>();
        super.addTileViewEventListener(xListener);
    }

    private static String getZoomLevel(double scale)
    {
        String zoomLevel;
        if(scale <= Parameters.ZOOM_3)
            zoomLevel = "0";
        else if(scale <= Parameters.ZOOM_4)
            zoomLevel = "1";
        else if(scale <= Parameters.ZOOM_5)
            zoomLevel = "2";
        else if(scale <= Parameters.ZOOM_6)
            zoomLevel = "3";
        else if(scale <= Parameters.ZOOM_7)
            zoomLevel = "4";
        else if(scale <= Parameters.ZOOM_8)
            zoomLevel = "5";
        else
            zoomLevel = "6";

        return zoomLevel;
    }

    public void addZoomableMarker(View view, String imageBaseName, double x, double y)
    {
        markerList.put(imageBaseName, new XTileView.Marker((ImageView) view, imageBaseName, x, y));
        super.addMarker(view, x, y);
    }

    public void addZoomableMarker(View view, String imageBaseName, String markerKey, double x, double y)
    {
        markerList.put(markerKey, new XTileView.Marker((ImageView) view, imageBaseName, x, y));
        super.addMarker(view, x, y);
    }

    public void removeZoomableMarker(String markerKey)
    {
        Marker tmp = markerList.remove(markerKey);
        super.removeMarker(tmp.imageView);
    }

    public void forceZoom(double scale)
    {
        super.setScale(scale);
        xListener.onScaleChanged(scale);
    }

    // for compatibility reasons (inefficient)
    @Override
    public void moveMarker(View view, double x, double y)
    {
        Marker tmp = null;

        for(String key : markerList.keySet())
        {
            tmp = markerList.get(key);
            if(tmp.imageView == view)
                break;
        }

        if(tmp != null)
        {
            tmp.coords[0] = x;
            tmp.coords[1] = y;
            super.moveMarker(view, x - tmp.shifts[0], y - tmp.shifts[1]);
        }
    }

    public void moveMarker(String markerKey, double x, double y)
    {
        Marker tmp = markerList.get(markerKey);
        if(tmp != null)
        {
            tmp.coords[0] = x;
            tmp.coords[1] = y;
            super.moveMarker(tmp.imageView, x - tmp.shifts[0], y - tmp.shifts[1]);
        }
    }

    private void getPathDirections(NavigablePath navigablePath, String pathId)
    {
        ImageView imageView;
        String s;
        // relevance = 0 -> relevant movement along X
        // relevance = 1 -> relevant movement along Y
        int relevance;
        // direction = 0 -> direction on SX/UP (DOWN)
        // direction = 1 -> direction on DX/DOWN (UP)
        int direction, resId;
        double[] current, next, diff;
        double scale = getScale();
        diff = new double[2];

        for(int i=0; i<(navigablePath.points.size() - 1); i++)
        {
            imageView =  new ImageView(context);
            relevance = 1;
            direction = 1;
            // get next pair of point
            current = navigablePath.points.get(i);
            next = navigablePath.points.get(i + 1);
            // compute differences
            diff[0] = current[0] - next[0];
            diff[1] = current[1] - next[1];
            // evaluate the relevance
            if(Math.abs(diff[0]) > Math.abs(diff[1]))
                relevance = 0;
            // evaluate the direction
            if(diff[relevance] > 0)
                direction = 0;
            // compute directions
            // LEFT
            if(relevance == 0 && direction == 0)
                s = "arrow_left";
            // RIGHT
            else if(relevance == 0 && direction == 1)
                s = "arrow_right";
            // UP (DOWN)
            else if(relevance == 1 && direction == 0)
                s = "arrow_up";
            // DOWN (UP)
            else
                s = "arrow_down";

            resId = getResources().getIdentifier(s + "_" + XTileView.getZoomLevel(scale),
                    "drawable", Parameters.PACKAGE_NAME);
            imageView.setImageResource(resId);
            addZoomableMarker(imageView, s, pathId + "_" + "arrow" + i, current[0], current[1]);
        }
    }

    public void drawNavigablePath(String pathId, NavigablePath navigablePath)
    {
        Marker tmp = null;
        int itemNum = navigablePath.points.size();
        double scale = getScale();

        // remove position marker for visibility issues
        tmp = markerList.remove("blue_dot");
        removeMarker(tmp.imageView);

        // add the navigablePath to the HashMap containing all navigablePath obj
        paths.put(pathId, navigablePath);

        // add pin marker to destination
        ImageView destination = new ImageView(context);
        double[] destCords = navigablePath.points.get(itemNum - 1);
        int resId = getResources().getIdentifier("pin_" + XTileView.getZoomLevel(scale),
                "drawable", Parameters.PACKAGE_NAME);
        destination.setImageResource(resId);
        addZoomableMarker(destination, "pin", pathId + "_pin", destCords[0], destCords[1]);

        // add marker for the other points in the path according the right direction
        getPathDirections(navigablePath, pathId);

        // draw again the position marker
        addZoomableMarker(tmp.imageView, "blue_dot", tmp.coords[0], tmp.coords[1]);
    }

    public void removeNavigablePath(String pathId)
    {
        // remove destination pin marker
        removeZoomableMarker(pathId + "_pin");
        // remove arrow markers
        for(int i=0; i<(paths.get(pathId).points.size() - 1); i++)
            removeZoomableMarker(pathId + "_" + "arrow" + i);
        // remove NavigablePath pathId from the list of the available ones
        paths.remove(pathId);
    }
}
