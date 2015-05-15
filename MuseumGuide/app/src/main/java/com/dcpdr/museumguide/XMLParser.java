package com.dcpdr.museumguide;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;

//  XMLParser class: parse an XML File given as input and build the corresponding DOM tree

public class XMLParser
{
    private String FilePath;
    private String FileName;
    private Document document;

    public XMLParser(String FilePath, String FileName)
    {
        this.FilePath = FilePath;
        this.FileName = FileName;

        // Read the XML file and construct the DOM tree
        try
        {
            File XmlFile = new File(FilePath + FileName);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            document = dBuilder.parse(XmlFile);
            // Puts all Text nodes in the full depth of the sub-tree underneath this Node
            // into a "normal" form where only structure separates Text nodes, i.e., there are
            // neither adjacent Text nodes nor empty Text nodes.
            document.getDocumentElement().normalize();
        }
        catch(Exception e)
        {
            // Exception handler
        }
    }

    public Document getDocument()
    {
        return document;
    }
}
