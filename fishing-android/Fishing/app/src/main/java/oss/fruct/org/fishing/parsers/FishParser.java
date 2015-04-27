package oss.fruct.org.fishing.parsers;

import android.util.Log;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.Vector;

import oss.fruct.org.fishing.geoobjects.Fish;
import oss.fruct.org.fishing.geoobjects.Lake;

public class FishParser extends DefaultHandler {

    private Lake lake;
    private String currentElement;
    private static int fishCount =0;

    private Fish fish;
    private Vector fishesInfo;
    private StringBuilder sb;

    private boolean processingLake;

    public FishParser(Lake lake) {
        this.lake = lake;
        fishCount = 0;
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
                if (atts.getQName(1).equals("name") && processingLake) {
                    fish = new Fish();
                    fish.setId(atts.getValue(0));
                    fish.setName(atts.getValue(1));
                }
            }
        }
        sb = new StringBuilder();
    }

    public void characters(char[] ch, int start, int length)
            throws SAXException {
        if (processingLake) {
            if (currentElement.equals("fish")) {
                sb.append(ch, start, length);
            }
        }
    }

    public void endElement(String namespaceURI, String localName,
                           String qName) throws SAXException {
        if (currentElement.equals("fish")) {
            if(processingLake) {
                String fishesInfoString = sb.toString();
                fish.setFishInfo(fishesInfoString);
                fishesInfo.addElement(fish);
            }fishCount++;
        }
        currentElement = "";
    }

    public void endDocument() {
        lake.setFishesInfo(fishesInfo);
        //System.out.println("Stop parse fishinfo.xml...");
    }
}
