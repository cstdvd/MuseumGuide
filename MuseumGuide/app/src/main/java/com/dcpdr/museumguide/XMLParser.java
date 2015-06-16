package com.dcpdr.museumguide;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import java.io.File;
import java.io.InputStream;

//  XMLParser class: it parses an XML File given as input and builds the corresponding DOM tree
//  Root Normalization ->  document.getDocumentElement().normalize():
//  Puts all Text nodes in the full depth of the sub-tree underneath this Node
//  into a "normal" form where only structure separates Text nodes, i.e., there are
//  neither adjacent Text nodes nor empty Text nodes.

public class XMLParser
{
      private Document document;


    // Construct the DOM tree given a file
    public XMLParser(String filePath, String fileName)
    {
        // Read the XML file and construct the DOM tree
        try
        {
            File XmlFile = new File(filePath + fileName);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            document = dBuilder.parse(XmlFile);
            document.getDocumentElement().normalize();
        }
        catch(Exception e)  { /*Exception handler*/ }
    }


    // Construct the DOM tree given an InputStream
    public XMLParser(InputStream xmlInputStream)
    {
        // Read the XML file and construct the DOM tree
        try
        {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            document = dBuilder.parse(xmlInputStream);
            document.getDocumentElement().normalize();
        }
        catch(Exception e)  { /*Exception handler*/ }
    }


    public Document getDocument()
    {
        return document;
    }
}
