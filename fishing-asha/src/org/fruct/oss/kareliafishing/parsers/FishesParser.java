package org.fruct.oss.kareliafishing.parsers;

import java.util.Vector;
import org.fruct.oss.kareliafishing.models.*;
import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Nikita Davydovskii
 * date: 29.07.2013
 * This class is a XML SAX parser for fishes data. 
 */
public class FishesParser extends DefaultHandler {
    
    private Lake lake;
    private String currentElement;
    
    private Fish fish;
    private Vector fishesInfo;
    
    private boolean processingLake;

    public FishesParser(Lake lake) {
        this.lake = lake;
        fishesInfo = new Vector();
        processingLake = false;
    }
    
    public void startDocument() throws SAXException {
        //System.out.println("Start parse fishinfo.xml...");        
    }
    
    public void startElement(String namespaceURI, String localName, 
            String qName, Attributes atts) throws SAXException {
        currentElement = qName;
        if (atts.getLength() > 0) {
            if (atts.getQName(0).equals("lake")) {
                if (atts.getValue(0).equals(lake.getName())) {
                    //System.out.println("Processing lake: ");
                    /*try {
                        System.out.print(new String(lake.getName().getBytes("Cp1251"), 
                            0, lake.getName().getBytes().length, "Cp1251"));
                    } catch (UnsupportedEncodingException ex) {
                        ex.printStackTrace();
                    }*/
                    processingLake = true;
                } else if (processingLake) {
                    processingLake = false;
                }
            }
            if (atts.getLength() > 1) {
                    if (atts.getQName(0).equals("name") && processingLake) {
                        fish = new Fish();
                        fish.setId(atts.getValue(1));
                        fish.setName(atts.getValue(0));
                    }
            }
        }
    }
    
    public void characters(char[] ch, int start, int length) 
            throws SAXException {
        if (processingLake) {
            if (currentElement.equals("fish")) {
                String fishesInfoString = new String(ch, start, length);
                fish.setFishInfo(fishesInfoString);
            
                fishesInfo.addElement(fish);
            }
        }
    }
    
    public void endElement(String namespaceURI, String localName, 
            String qName) throws SAXException {
        currentElement = "";
    }
    
    public void endDocument() {
        lake.setFishesInfo(fishesInfo);
        //System.out.println("Stop parse fishinfo.xml...");
    }
}
