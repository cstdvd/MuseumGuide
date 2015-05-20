package com.dcpdr.museumguide;

// eXtended TileView class: it allows to add markers whose images change according to
// the current zoom level. You must define the thresholds and puts the images in the drawable
// folder with their corresponding names.

import android.content.Context;
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

        public Marker(ImageView imageView, String imageBaseName)
        {
            this.imageView = imageView;
            this.imageBaseName = imageBaseName;
        }
    }


    // **************************
    // * XTileViewEventListener *
    // **************************
    private class XTileViewEventListener extends TileViewEventListenerImplementation
    {
        @Override
        public void onZoomComplete(double scale)
        {
            XTileView extObj = XTileView.this;
            Marker tmp;
            String zoomLevel;
            int resId;

            // get the current zoom level
            if(scale <= Parameters.ZOOM_S)
                zoomLevel = "s";
            else if(scale <= Parameters.ZOOM_M)
                zoomLevel = "m";
            else if(scale <= Parameters.ZOOM_L)
                zoomLevel = "l";
            else
                zoomLevel = "xl";
            // change all zoomable markers
            for(Iterator<Marker> iterator = extObj.markerList.iterator(); iterator.hasNext(); )
            {
                tmp = iterator.next();
                resId = getResources().getIdentifier(tmp.imageBaseName + "_" + zoomLevel, "drawable",Parameters.PACKAGE_NAME);
                tmp.imageView.setImageResource(resId);
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
        markerList.add(new XTileView.Marker((ImageView) view, imageBaseName));
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
