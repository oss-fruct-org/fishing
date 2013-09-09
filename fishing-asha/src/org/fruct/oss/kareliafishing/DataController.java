package org.fruct.oss.kareliafishing;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import javax.microedition.lcdui.Image;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.fruct.oss.kareliafishing.models.*;
import org.fruct.oss.kareliafishing.parsers.*;
import org.xml.sax.SAXException;

/**
 *
 * @author Nikita Davydovskii
 * date: 24.07.2013
 * This class loads data and resources.
 */
public class DataController {
    
    private DataModel model;
    
    public Image fishingMarker;
    public Image shopMarker;
    public Image hostelMarker;
    public Image splashScreenLogo;

    public DataController(Localization strings) {
        model = new DataModel(strings);        
    }
    
    public void loadData() {
        InputStream is = null;
        try {
            fishingMarker = Image.createImage("/fishing.png");
            shopMarker = Image.createImage("/shop.png");
            hostelMarker = Image.createImage("/hostel.png");
            splashScreenLogo = Image.createImage("/company-logo.png");
                      
            // parse lake.xml
            is = getClass().getResourceAsStream("/lakes.xml");
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            GeoObjectsParser saxp = new GeoObjectsParser(model);
            parser.parse(is, saxp);
            
        } catch (SAXException e) {
            System.out.println("SAXException");
            e.printStackTrace();
        } catch (NullPointerException e) {
            System.out.println("NullPointerException");
        } catch (Exception e) {
            System.out.println("Something went wrong with loading data");
            e.printStackTrace();
        }
    }
    
    public void loadFish(Lake lake) {
        InputStream is = null;
        try {
            is = getClass().getResourceAsStream("/fishinfo.xml");
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            FishesParser fishesParser = new FishesParser(lake);
            parser.parse(is, fishesParser);

            Fish fish;
            InputStream imageStream;
            for (int i = 0; i < lake.getFishesInfo().size(); i++) {
                fish = (Fish)lake.getFishesInfo().elementAt(i);

                //tempNameString = new String(fish.getName().getBytes("UTF-8"),"UTF-8");
                //fish.setPicture(Image.createImage(namePrefix + tempNameString + namePostfix));
                imageStream = getClass().getResourceAsStream("/fishes/" + fish.getId() + ".jpg");
                if (imageStream != null) {
                    fish.setPicture(Image.createImage(imageStream));
                    //System.out.println("loaded image: " + "/fishes/" + fish.getId() + ".jpg");
                }
            }
        } catch (SAXException e) {
            System.out.println("SAXException");
            e.printStackTrace();
        } catch (NullPointerException e) {
            System.out.println("NullPointerException");
        } catch (IOException e) {
            System.out.println("IOException");
        } catch (Exception e) {
            System.out.println("Something went wrong with loading fish data " + e.toString());
            //e.printStackTrace();
        }
    }
    
    public void deleteFish(Lake lake) {
        lake.setFishesInfo(null);
    }

    public DataModel getModel() {
        return model;
    }
    
}
