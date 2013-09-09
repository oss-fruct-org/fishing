package org.fruct.oss.kareliafishing.parsers;

import org.fruct.oss.kareliafishing.models.*;
import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Nikita Davydovskii
 * date: 22.07.2013
 * This class is a XML SAX parser for geo objects data. 
 */
public class GeoObjectsParser extends DefaultHandler {
    
    private DataModel model;
    private String currentElement;
    private int currentPlaceMarkType;
    
    private final int LAKE = 0;
    private final int SHOP = 1;
    private final int HOSTEL = 2;
    
    private boolean processingLake;
    private boolean processingShop;
    private boolean processingHostel;
    
    private Lake lake;
    private Shop shop;
    private Hostel hostel;

    public GeoObjectsParser(DataModel model) {
        this.model = model;
        processingLake = false;
        processingShop = false;
        processingHostel = false;
    }
    
    public void startDocument() throws SAXException {
        //System.out.println("Start parse lakes.xml...");
    }
    
    public void startElement(String namespaceURI, String localName, 
            String qName, Attributes atts) throws SAXException {
        currentElement = qName;
        if (atts.getLength() > 0) {
            String attributeName = atts.getValue(0);
            if (attributeName.equals("lake")) {
                if (processingLake) {
                    model.getLakes().addElement(lake);
                }
                currentPlaceMarkType = LAKE;
                lake = new Lake();
                processingLake = true;
            } else if (attributeName.equals("shop")) {
                if (processingShop) {
                    model.getShops().addElement(shop);
                }
                currentPlaceMarkType = SHOP;
                shop = new Shop();
                processingShop = true;
            } else if (attributeName.equals("hostel")) {
                if (processingHostel) {
                    model.getHostels().addElement(hostel);
                }
                currentPlaceMarkType = HOSTEL;
                hostel = new Hostel();
                processingHostel = true;
            }
        } 
            
    }
    
    public void endElement(String namespaceURI, String localName, 
            String qName) throws SAXException {
        currentElement = "";
    }
    
    public void characters(char[] ch, int start, int length) 
            throws SAXException {
        if (currentPlaceMarkType == LAKE) {
            if (currentElement.equals("name")) {
                lake.setName(new String(ch, start, length));
            } else if (currentElement.equals("latitude")) {
                String s = new String(ch, start, length);
                lake.setLatitude(Double.parseDouble(s));
            } else if (currentElement.equals("longitude")) {
                String s = new String(ch, start, length);
                lake.setLongitude(Double.parseDouble(s));
            } else if (currentElement.equals("description")) {
                lake.setDescription(new String(ch, start, length));
            }
        } else if (currentPlaceMarkType == SHOP) {
            if (currentElement.equals("name")) {
                shop.setName(new String(ch, start, length));
            } else if (currentElement.equals("latitude")) {
                String s = new String(ch, start, length);
                shop.setLatitude(Double.parseDouble(s));
            } else if (currentElement.equals("longitude")) {
                String s = new String(ch, start, length);
                shop.setLongitude(Double.parseDouble(s));
            } else if (currentElement.equals("address")) {
                shop.setAddress(new String(ch, start, length));
            } else if (currentElement.equals("phone")) {
                shop.setPhone(new String(ch, start, length));
            }         
        } else if (currentPlaceMarkType == HOSTEL) {
            if (currentElement.equals("name")) {
                hostel.setName(new String(ch, start, length));
            } else if (currentElement.equals("latitude")) {
                String s = new String(ch, start, length);
                hostel.setLatitude(Double.parseDouble(s));
            } else if (currentElement.equals("longitude")) {
                String s = new String(ch, start, length);
                hostel.setLongitude(Double.parseDouble(s));
            } else if (currentElement.equals("description")) {
                hostel.setDescription(new String(ch, start, length));
            } else if (currentElement.equals("phone")) {
                hostel.setPhone(new String(ch, start, length));
            } else if (currentElement.equals("site")) {
                hostel.setSite(new String(ch, start, length));
            }
        }
    }
    
    public void endDocument() {
        model.getLakes().addElement(lake);
        model.getShops().addElement(shop);
        model.getHostels().addElement(hostel);
        //System.out.println("Stop parse lakes.xml...");
    }     
}
