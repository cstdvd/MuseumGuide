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

        public Marker(ImageView imageView, String imageBaseName, double coordX, double coordY)
        {
            this.imageView = imageView;
            this.imageBaseName = imageBaseName;
            coords = new double[2];
            coords[0] = coordX;
            coords[1] = coordY;
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
            String zoomLevel;
            int resId;
            int shiftX, shiftY;

            // get the current zoom level
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
            // change all zoomable markers
            for(Iterator<Marker> iterator = extObj.markerList.iterator(); iterator.hasNext(); )
            {
                tmp = iterator.next();
                resId = getResources().getIdentifier(tmp.imageBaseName + "_" + zoomLevel, "drawable", Parameters.PACKAGE_NAME);
                tmp.imageView.setImageResource(resId);
                BitmapDrawable bd=(BitmapDrawable) getResources().getDrawable(resId);
                shiftX = (int) ((bd.getBitmap().getWidth()) * (int)scale % 0.25);
                shiftY = (int) ((bd.getBitmap().getHeight()) * (int)scale % 0.25);
                extObj.moveMarker(tmp.imageView, tmp.coords[0] - shiftX, tmp.coords[1] - shiftY);
            }
        }
    }


    // XTileView members
    private List<Marker> markerList;
    private XTileViewEventListener xListener;

    // XTileView methods
    public XTileView(Context context)
    {
        super(context);
        markerList = new ArrayList<Marker>();
        xListener = new XTileViewEventListener();
        super.addTileViewEventListener(xListener);
    }

    public void addZoomableMarker(View view, String imageBaseName, double x, double y)
    {
        markerList.add(new XTileView.Marker((ImageView) view, imageBaseName, x, y));
        super.addMarker(view, x, y);
    }

    public void removeZoomableMarker(View view, String imageBaseName)
    {
        Marker tmp;
        for(Iterator<Marker> iterator = markerList.iterator(); iterator.hasNext(); )
        {
            tmp = iterator.next();
            if(tmp.imageBaseName.equals(imageBaseName))
                markerList.remove(tmp);
        }
        super.removeMarker(view);
    }

    public void forceZoom(double scale)
    {
        super.setScale(scale);
        xListener.onZoomComplete(scale);
    }
}
