package org.fruct.oss.kareliafishing;

import com.nokia.maps.common.GeoCoordinate;
import com.nokia.maps.map.MapObject;
import java.util.Vector;
import java.util.Stack;
import javax.microedition.lcdui.Display;
import org.fruct.oss.kareliafishing.models.*;
import org.fruct.oss.kareliafishing.views.*;

/**
 *
 * @author davidson
 */
public class MainController {
    
    private MapView mapView;
    private AboutFormView aboutView;
    private CategoryListView categoryListView;
    private ShopsListView shopsListView;
    private ShopChoiceListView shopChoiseListView;
    private ShopInfoFormView shopInfoFormView;
    private HostelsListView hostelsListView;
    private HostelChoiceListView hostelChoiseListView;
    private HostelInfoFormView hostelInfoFormView;
    private LakesListView lakesListView;
    private LakeChoiceListView lakeChoiceListView;
    private LakeInfoFormView lakeInfoFormView;
    private FishesListView fishesListView;
    private FishInfoFormView fishInfoFormView;
    private SplashScreenWrapperView splashScreen;
    private ObjectTypeSelectorView objectTypeSelectorView;
     
    private Display display;
       
    private Localization strings;
    private DataController model;
    
    // List of views
    public static final int ABOUT_VIEW = 0;
    public static final int MAP_VIEW = 1;
    public static final int CATEGORY_LIST_VIEW = 2;
    public static final int SHOPS_LIST_VIEW = 3;
    public static final int SHOP_CHOISE_LIST_VIEW = 4;
    public static final int SHOP_INFO_FORM_VIEW = 5;
    public static final int HOSTELS_LIST_VIEW = 6;
    public static final int HOSTEL_CHOISE_LIST_VIEW = 7;
    public static final int HOSTEL_INFO_FORM_VIEW = 8;
    public static final int LAKES_LIST_VIEW = 9;
    public static final int LAKE_CHOISE_LIST_VIEW = 10;
    public static final int LAKE_INFO_FORM_VIEW = 11;
    public static final int FISHES_LIST_VIEW = 12;
    public static final int FISH_INFO_FORM_VIEW = 13;
    public static final int SPLASH_SCREEN_VIEW = 14;
    public static final int SELECTOR_VIEW = 15;
    
    
    private final int SPLASH_SCREEN_TIMEOUT = 2000;
    
    private Stack pageStack;
    private int previousState;
    private int currentState;
    
    private int currentObjectsType = -1;
          
    public MainController(Display display) {
        this.display = display;
        
        strings = new Localization();
        model = new DataController(strings);
        model.loadData();
        
        pageStack = new Stack();
        
        // Initialize all views
        initialize();
        
        System.out.println(strings.localize("maintitle", 
                "Title with error"));
       
        // And set the first view that user will see on the screen
        //previousState = SPLASH_SCREEN_VIEW;
        //currentState = SPLASH_SCREEN_VIEW;
        pageStack.push(new Integer(SPLASH_SCREEN_VIEW));
        display.setCurrent(splashScreen);
    }
    
    /*
     * Initialize all components
     */
    private void initialize() {
        if (splashScreen == null) {
            createSplashScreen();
        }
        if (mapView == null) {
            createMapView();
        }
        if (aboutView == null) {
            createAboutView();
        }
        if (categoryListView == null) {
            createCategoryListView();
        }
        if (shopsListView == null) {
            createShopsListView();
        }
        if (shopChoiseListView == null) {
            createShopChoiseListView();
        }
        if (shopInfoFormView == null) {
            createShopInfoFormView();
        }
        if (hostelsListView == null) {
            createHostelsListView();
        }
        if (hostelChoiseListView == null) {
            createHostelChoiseListView();
        }
        if (hostelInfoFormView == null) {
            createHostelInfoFormView();
        }
        if (lakesListView == null) {
            createLakesListView();
        }
        if (lakeChoiceListView == null) {
            createLakeChoiceListView();
        }
        if (lakeInfoFormView == null) {
            createLakeInfoFormView();
        }
        if (fishesListView == null) {
            createFishesListView();
        }
        if (fishInfoFormView == null) {
            createFishInfoFormView();
        }
        if (objectTypeSelectorView == null) {
            createObjectTypeSelectorView();
        }
    }
    
    private void createSplashScreen() {
        splashScreen = new SplashScreenWrapperView(display, 
                new SplashScreenWrapperView.Listener() {

            public void startMainView() {
                pageStack.pop();
                pageStack.push(new Integer(MAP_VIEW));
                changeViewController(MAP_VIEW);
            }
        });
        splashScreen.setTitle("splashScreen");
        splashScreen.setFullScreenMode(true);
        splashScreen.setImage(model.splashScreenLogo);
        splashScreen.setTimeout(SPLASH_SCREEN_TIMEOUT);
    }
    
    private void createMapView() {
        mapView = new MapView(display, strings, new MapView.Listener() {

            public void changeView(int view) {
                pageStack.push(new Integer(view));
                changeViewController(view);
            }
            
            public void sendClickedObject(MapObject mapObject) {
                searchClickedObject(mapObject);
            }

            public void showViewList() {
                switch (currentObjectsType) {
                    case CategoryListView.LAKE_ITEM:
                        pageStack.push(new Integer(LAKES_LIST_VIEW));
                        showLakesListView();
                        break;
                    case CategoryListView.SHOP_ITEM:
                        pageStack.push(new Integer(SHOPS_LIST_VIEW));
                        showShopsListView();
                        break;
                    case CategoryListView.HOSTEL_ITEM:
                        pageStack.push(new Integer(HOSTELS_LIST_VIEW));
                        showHostelsListView();
                        break;
                    default:
                        pageStack.push(new Integer(CATEGORY_LIST_VIEW));
                        showCategoryListView();
                }
            }
            
        });
        placeLakesOnMap();
        placeShopsOnMap();
        placeHostelsOnMap();
        mapView.setCenter();
    }
    
    private void createAboutView() {
        aboutView = new AboutFormView(strings, new BaseFormView.Listener() {

            public void changeView(int view) {
                changeViewController(view);
            }
            
            public void back() {
                backController();
            }
        });
    }
    
    private void createCategoryListView() {
        categoryListView = new CategoryListView(strings, model.getModel(), new 
                BaseListView.Listener() {

            public void changeView(int view) {
                pageStack.push(new Integer(view));
                changeViewController(view);
            }
            
            public void back() {
                backController();
            }
        });
    }
    
    private void createShopsListView() {
        shopsListView = new ShopsListView(strings, model.getModel().getShops(), 
                new BaseListView.Listener() {

            public void changeView(int view) {
                model.getModel().setCurrentShop(shopsListView.getSelectedIndex());
                pageStack.push(new Integer(view));
                changeViewController(view);
            }
            
            public void back() {
                backController();
            }
        });
    }
    
    private void createShopChoiseListView() {
        shopChoiseListView = new ShopChoiceListView(null, strings, 
                new BaseListView.Listener() {

            public void changeView(int view) {
                if (view == MAP_VIEW) {
                    pageStack.removeAllElements();
                    Shop shop = (Shop)model.getModel().getShops().elementAt(
                            model.getModel().getCurrentShop());
                    setMapCenterToObject(shop.getGeoCoordinate());
                }
                pageStack.push(new Integer(view));
                changeViewController(view);
            }
            
            public void back() {
                backController();
            }
        });
    }
    
    private void createShopInfoFormView() {
        shopInfoFormView = new ShopInfoFormView(strings, 
                new BaseFormView.Listener() {

            public void changeView(int view) {
                changeViewController(view);
            }
            
            public void back() {
                backController();
            }
        });
    }
    
    private void createHostelsListView() {
        hostelsListView = new HostelsListView(strings, 
                model.getModel().getHostels(), new BaseListView.Listener() {

            public void changeView(int view) {
                model.getModel().setCurrentHostel(hostelsListView.getSelectedIndex());
                pageStack.push(new Integer(view));
                changeViewController(view);
            }
            
            public void back() {
                backController();
            }
        });
    }
    
    private void createHostelChoiseListView() {
        hostelChoiseListView = new HostelChoiceListView(null, strings, 
                new BaseListView.Listener() {

            public void changeView(int view) {
                if (view == MAP_VIEW) {
                    pageStack.removeAllElements();
                    Hostel hostel = (Hostel)model.getModel().getHostels().elementAt(
                            model.getModel().getCurrentHostel());
                    setMapCenterToObject(hostel.getGeoCoordinate());
                }
                pageStack.push(new Integer(view));
                changeViewController(view);
            }
            
            public void back() {
                backController();
            }
        });
    }
    
    private void createHostelInfoFormView() {
        hostelInfoFormView = new HostelInfoFormView(strings, 
                new BaseFormView.Listener() {

            public void changeView(int view) {
                changeViewController(view);
            }
            
            public void back() {
                backController();
            }
        });
    }
    
    private void createLakesListView() {
        lakesListView = new LakesListView(strings, model.getModel().getLakes(), 
                new BaseListView.Listener() {

            public void changeView(int view) {
                model.getModel().setCurrentLake(lakesListView.getSelectedIndex());
                pageStack.push(new Integer(view));
                changeViewController(view);
            }
            
            public void back() {
                backController();
            }
        });
    }
    
    private void createLakeChoiceListView() {
        lakeChoiceListView = new LakeChoiceListView(null, strings, 
                new BaseListView.Listener() {

            public void changeView(int view) {
                if (view == MAP_VIEW) {
                    pageStack.removeAllElements();
                    Lake lake = (Lake)model.getModel().getLakes().elementAt(
                            model.getModel().getCurrentLake());
                    setMapCenterToObject(lake.getGeoCoordinate());
                }
                pageStack.push(new Integer(view));
                changeViewController(view);
            }
            
            public void back() {
                backController();
            }
        });
    }
    
    private void createLakeInfoFormView() {
        lakeInfoFormView = new LakeInfoFormView(strings, 
                new BaseFormView.Listener() {

            public void changeView(int view) {
                changeViewController(view);
            }
            
            public void back() {
                backController();
            }
        });
    }
    
    private void createFishesListView() {
        fishesListView = new FishesListView(strings, new BaseListView.Listener() {

            public void changeView(int view) {
                pageStack.push(new Integer(view));
                changeViewController(view);
            }
            
            public void back() {
                backController();
            }
        });
    }
    
    private void createFishInfoFormView() {
        fishInfoFormView = new FishInfoFormView(strings, 
                new BaseFormView.Listener() {

            public void changeView(int view) {
                changeViewController(view);
            }
            
            public void back() {
                backController();
            }
        });
    }
    
    private void createObjectTypeSelectorView() {
        objectTypeSelectorView = new ObjectTypeSelectorView(strings, 
                model.getModel(), new ObjectTypeSelectorView.Listener() {

            public void chosenType(int type) {
                int temp = type - 1;
                currentObjectsType = temp;
                setVisibleAll(false);
                switch(temp) {
                    case CategoryListView.LAKE_ITEM:
                        setVisibleLakes(true);
                        break;
                    case CategoryListView.SHOP_ITEM:
                        setVisibleShops(true);
                        break;
                    case CategoryListView.HOSTEL_ITEM:
                        setVisibleHostels(true);
                        break;
                    default:
                        setVisibleAll(true);
                }
                backController();
            }

            public void back() {
                backController();
            }
        });
    }
    
    private void searchClickedObject(MapObject mapObject) {
        Vector lakes = model.getModel().getLakes();
        for (int i = 0; i < lakes.size(); i++) {
            Lake lake = (Lake)lakes.elementAt(i);
            if (lake.getMarker().equals(mapObject)) {
                model.getModel().setCurrentLake(i);
                pageStack.push(new Integer(LAKE_CHOISE_LIST_VIEW));
                showLakeChoiceListView();
                return;
            }
        }
        Vector shops = model.getModel().getShops();
        for (int i = 0; i < shops.size(); i++) {
            Shop shop = (Shop)shops.elementAt(i);
            if (shop.getMarker().equals(mapObject)) {
                model.getModel().setCurrentShop(i);
                pageStack.push(new Integer(SHOP_CHOISE_LIST_VIEW));
                showShopChoiceListView();
                return;
            }
        }
        Vector hostels = model.getModel().getHostels();
        for (int i = 0; i < hostels.size(); i++) {
            Hostel hostel = (Hostel)hostels.elementAt(i);
            if (hostel.getMarker().equals(mapObject)) {
                model.getModel().setCurrentHostel(i);
                pageStack.push(new Integer(HOSTEL_CHOISE_LIST_VIEW));
                showHostelChoiceListView();
                return;
            }
        }
    }
    
    private void setVisibleAll(boolean visibility) {
        Vector lakes = model.getModel().getLakes();
        for (int i = 0; i < lakes.size(); i++) {
            Lake lake = (Lake)lakes.elementAt(i);
            lake.getMarker().setVisible(visibility);
        }
        
        Vector shops = model.getModel().getShops();
        for (int i = 0; i < shops.size(); i++) {
            Shop shop = (Shop)shops.elementAt(i);
            shop.getMarker().setVisible(visibility);
        }
        
        Vector hostels = model.getModel().getHostels();
        for (int i = 0; i < hostels.size(); i++) {
            Hostel hostel = (Hostel)hostels.elementAt(i);
            hostel.getMarker().setVisible(visibility);
        }
    }
    
    private void setVisibleLakes(boolean visibility) {
        Vector lakes = model.getModel().getLakes();
        for (int i = 0; i < lakes.size(); i++) {
            Lake lake = (Lake)lakes.elementAt(i);
            lake.getMarker().setVisible(visibility);
        }
    }
    
    private void setVisibleShops(boolean visibility) {
        Vector shops = model.getModel().getShops();
        for (int i = 0; i < shops.size(); i++) {
            Shop shop = (Shop)shops.elementAt(i);
            shop.getMarker().setVisible(visibility);
        }
    }
    
    private void setVisibleHostels(boolean visibility) {
        Vector hostels = model.getModel().getHostels();
        for (int i = 0; i < hostels.size(); i++) {
            Hostel hostel = (Hostel)hostels.elementAt(i);
            hostel.getMarker().setVisible(visibility);
        }
    }
    
    private void showMapView() {
        currentState = MAP_VIEW;
        display.setCurrent(mapView);
    }
    
    private void showAboutView() {
        //currentState = ABOUT_VIEW;
        display.setCurrent(aboutView);
    }
    
    private void showCategoryListView() {                  
        //currentState = CATEGORY_LIST_VIEW;
        display.setCurrent(categoryListView);    
    }
    
    private void showShopsListView() {
        //currentState = SHOPS_LIST_VIEW;
        display.setCurrent(shopsListView);
    }
    
    private void showShopChoiceListView() {
        //currentState = SHOP_CHOISE_LIST_VIEW;
        Shop shop;
        shop = (Shop) model.getModel().getShops().elementAt(
                model.getModel().getCurrentShop());
        shopChoiseListView.setTitle(shop.getName());
        display.setCurrent(shopChoiseListView);
    }
    
    private void showShopInfoFormView() {
        //currentState = SHOP_INFO_FORM_VIEW;
        Shop shop;
        shop = (Shop) model.getModel().getShops().elementAt(
                model.getModel().getCurrentShop());
        shopInfoFormView.deleteAll();
        shopInfoFormView.setInfo(shop);
        display.setCurrent(shopInfoFormView);
    }
    
    private void showHostelsListView() {
        //currentState = HOSTELS_LIST_VIEW;
        display.setCurrent(hostelsListView);
    }
    
    private void showHostelChoiceListView() {
        //currentState = HOSTEL_CHOISE_LIST_VIEW;
        Hostel hostel;
        hostel = (Hostel) model.getModel().getHostels().elementAt(
                model.getModel().getCurrentHostel());
        hostelChoiseListView.setTitle(hostel.getName());
        display.setCurrent(hostelChoiseListView);
    }
    
    private void showHostelInfoFormView() {
        //currentState = HOSTEL_INFO_FORM_VIEW;
        Hostel hostel;
        hostel = (Hostel) model.getModel().getHostels().elementAt(
                model.getModel().getCurrentHostel());
        hostelInfoFormView.deleteAll();
        hostelInfoFormView.setInfo(hostel);
        display.setCurrent(hostelInfoFormView);
    }
    
    private void showLakesListView() {
        //currentState = LAKES_LIST_VIEW;
        display.setCurrent(lakesListView);
    }
    
    private void showLakeChoiceListView() {
        //currentState = LAKE_CHOISE_LIST_VIEW;
        Lake lake;
        lake = (Lake) model.getModel().getLakes().elementAt(
                model.getModel().getCurrentLake());
        lake.setFishesInfo(null);
        lakeChoiceListView.setTitle(lake.getName());
        display.setCurrent(lakeChoiceListView);
    }
    
    private void showLakeInfoFormView() {
        //currentState = LAKE_INFO_FORM_VIEW;
        Lake lake;
        lake = (Lake) model.getModel().getLakes().elementAt(
                model.getModel().getCurrentLake());
        lakeInfoFormView.deleteAll();
        lakeInfoFormView.setInfo(lake);
        display.setCurrent(lakeInfoFormView);
    }
    
    private void showFishesListView() {
        //currentState = FISHES_LIST_VIEW;
        Lake lake;
        lake = (Lake) model.getModel().getLakes().elementAt(
                model.getModel().getCurrentLake());
        model.loadFish(lake);
        fishesListView.deleteAll();
        fishesListView.setTitle(lake.getName());
        fishesListView.setInfo(lake.getFishesInfo());
        display.setCurrent(fishesListView);
    }
    
    private void showFisheInfoFormView() {
        //currentState = FISH_INFO_FORM_VIEW;
        Lake lake;
        lake = (Lake) model.getModel().getLakes().elementAt(
                model.getModel().getCurrentLake());
        Fish fish;
        fish = (Fish) lake.getFishesInfo().elementAt(
                fishesListView.getSelectedIndex());
        fishInfoFormView.deleteAll();
        fishInfoFormView.setTitle(fish.getName());
        fishInfoFormView.setInfo(fish);
        display.setCurrent(fishInfoFormView);
    }
    
    private void showSelectorView() {
        //currentState = SELECTOR_VIEW;
        display.setCurrent(objectTypeSelectorView);
    }
    
    private void setMapCenterToObject(GeoCoordinate geoCoordinate) {
        mapView.setMapCenterToObject(geoCoordinate);
    }
    
    private void backController() {
        if (!pageStack.empty()) {
            //System.out.println("backController() stackb: " + pageStack);
            /*Integer prevView = (Integer)*/pageStack.pop();
            //System.out.println("backController() stacka: " + pageStack);
            //System.out.println("backController() prev: " + prevView.intValue());
            if (!pageStack.empty()) {
                Integer currentView = (Integer)pageStack.peek();
                //System.out.println("backController() current: " + currentView.intValue());
                changeViewController(currentView.intValue());
            }
        }
    }
    
    private void changeViewController(int view) {
        //previousState = currentState;
        switch (view) {
            case MAP_VIEW:
                /*currentState = MAP_VIEW;
                mapView.clearMap();
                switch (previousState) {
                    case SHOP_CHOISE_LIST_VIEW:
                        Shop shopMap;
                        shopMap = (Shop)model.getModel().getShops().elementAt(
                                shopsListView.getSelectedIndex());
                        mapView.setTitle(shopMap.getName());
                        mapView.addMarker(model.shopMarker, shopMap.getLatitude(), 
                                shopMap.getLongitude());
                        break;
                    case HOSTEL_CHOISE_LIST_VIEW:
                        Hostel hostelMap;
                        hostelMap = (Hostel)model.getModel().getHostels().elementAt(
                                hostelsListView.getSelectedIndex());
                        mapView.setTitle(hostelMap.getName());
                        mapView.addMarker(model.hostelMarker, hostelMap.getLatitude(), 
                                hostelMap.getLongitude());
                        break;
                    case LAKE_CHOISE_LIST_VIEW:
                        Lake lakeMap;
                        lakeMap = (Lake)model.getModel().getLakes().elementAt(
                                lakesListView.getSelectedIndex());
                        mapView.setTitle(lakeMap.getName());
                        mapView.addMarker(model.fishingMarker, lakeMap.getLatitude(), 
                                lakeMap.getLongitude());
                        break;
                }             
                display.setCurrent(mapView);
                break;*/
                showMapView();
                break;
            case ABOUT_VIEW:
                showAboutView();
                break;
            case CATEGORY_LIST_VIEW:
                showCategoryListView();
                break;
            case SHOPS_LIST_VIEW:
                showShopsListView();
                break;
            case SHOP_CHOISE_LIST_VIEW:
                showShopChoiceListView();
                break;
            case SHOP_INFO_FORM_VIEW:
                showShopInfoFormView();
                break;
            case HOSTELS_LIST_VIEW:
                showHostelsListView();
                break;
            case HOSTEL_CHOISE_LIST_VIEW:
                showHostelChoiceListView();
                break;
            case HOSTEL_INFO_FORM_VIEW:
                showHostelInfoFormView();
                break;
            case LAKES_LIST_VIEW:
                showLakesListView();
                break;
            case LAKE_CHOISE_LIST_VIEW:
                showLakeChoiceListView();
                break;
            case LAKE_INFO_FORM_VIEW:
                showLakeInfoFormView();
                break;
            case FISHES_LIST_VIEW:
                showFishesListView();
                break;
            case FISH_INFO_FORM_VIEW:
                showFisheInfoFormView();       
                break;
            case SELECTOR_VIEW:
                showSelectorView();
                break;    
            default:
                System.err.println("View that need to be change was not "
                        + "recognized");
        }
    }
    
    private void placeLakesOnMap() {
        Vector lakes = model.getModel().getLakes();
        for (int i = 0; i < lakes.size(); i++) {
            Lake lake = (Lake)lakes.elementAt(i);
            lake.setMarker(mapView.createMarker(model.fishingMarker, 
                    lake.getGeoCoordinate()));
            mapView.addMarker(lake.getMarker());
        }
    }
    
    private void placeShopsOnMap() {
        Vector shops = model.getModel().getShops();
        for (int i = 0; i < shops.size(); i++) {
            Shop shop = (Shop)shops.elementAt(i);
            shop.setMarker(mapView.createMarker(model.shopMarker, 
                    shop.getGeoCoordinate()));
            mapView.addMarker(shop.getMarker());
        }
    }
    
    private void placeHostelsOnMap() {
        Vector hostels = model.getModel().getHostels();
        for (int i = 0; i < hostels.size(); i++) {
            Hostel hostel = (Hostel)hostels.elementAt(i);
            hostel.setMarker(mapView.createMarker(model.hostelMarker, 
                    hostel.getGeoCoordinate()));
            mapView.addMarker(hostel.getMarker());
        }
    }
}
