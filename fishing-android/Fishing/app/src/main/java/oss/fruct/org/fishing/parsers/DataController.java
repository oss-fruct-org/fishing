package oss.fruct.org.fishing.parsers;

import android.content.Context;
import android.util.Log;

import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import oss.fruct.org.fishing.App;
import oss.fruct.org.fishing.geoobjects.DataModel;
import oss.fruct.org.fishing.geoobjects.Fish;
import oss.fruct.org.fishing.geoobjects.Lake;

public class DataController {
    DataModel dataModel;
    Context context;
    static DataController instance;

    double targetLat = 0, targetLon = 0;
    int [] activeTypes = new int [3];

    public static final int LAKES_ID = 0;
    public static final int HOSTELS_ID = 1;
    public static final int SHOPS_ID = 2;

    private DataController(){
        this.context = App.get();
        dataModel = new DataModel();

        for(int i = 0; i <3; i++)
            activeTypes[i] = 1;
        loadData();
        for(int i = 0; i < dataModel.getLakes().size();i++){
            loadFish((Lake)dataModel.getLakes().get(i));
        }
    }

    public int[] getActiveTypes(){
        return activeTypes;
    }

    public void setActiveTypes(int[] at){
        activeTypes = at;
    }

    public double getTargetLat(){return targetLat;}
    public double getTargetLon(){return targetLon;}
    public void setTarget(double lat, double lon){
        targetLat = lat;
        targetLon = lon;
    }
    public static synchronized  DataController getInstance() {
        if( instance==null ){
            instance = new DataController();
        }
        return instance;
    }

    public void loadData(){
        try {
            InputStream is = context.getAssets().open("data.xml");
             SAXParserFactory factory = SAXParserFactory.newInstance();
             SAXParser parser = factory.newSAXParser();
             GeoObjectsParser saxp = new GeoObjectsParser(dataModel);
             parser.parse(is, saxp);
        }catch (SAXException e) {
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
            is = context.getAssets().open("fishinfo.xml");
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            FishParser fishesParser = new FishParser(lake);
            parser.parse(is, fishesParser);

            Fish fish;
            if(lake.getFishesInfo()==null)
                return;
            for (int i = 0; i < lake.getFishesInfo().size(); i++) {
                fish = (Fish)lake.getFishesInfo().elementAt(i);
                fish.setPicturePath("images/" + fish.getId() + ".jpg");
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

    public void loadAllFish(Vector lakes){
        InputStream is = null;
        try {
            is = context.getAssets().open("fishinfo.xml");
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            Lake lake;
            for(int j = 0; j < lakes.size(); j++) {
                lake = (Lake)lakes.get(j);
                is.reset();
                FishParser fishesParser = new FishParser(lake);
                parser.parse(is, fishesParser);

                Fish fish;
                for (int i = 0; i < lake.getFishesInfo().size(); i++) {
                    fish = (Fish) lake.getFishesInfo().elementAt(i);
                    fish.setPicturePath("images/" + fish.getId() + ".jpg");
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

    public DataModel getDataModel(){
        return dataModel;
    }
}
