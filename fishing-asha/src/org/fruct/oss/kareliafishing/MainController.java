package org.fruct.oss.kareliafishing;

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
    
    private int previousState;
    private int currentState;
          
    public MainController(Display display) {
        this.display = display;
        
        strings = new Localization();
        model = new DataController(strings);
        model.loadData();
        
        // Initialize all views
        initialize();
        
        System.out.println(strings.localize("maintitle", 
                "Title with error"));
       
        // And set the first view that user will see on the screen
        previousState = CATEGORY_LIST_VIEW;
        currentState = CATEGORY_LIST_VIEW;
        display.setCurrent(categoryListView);
    }
    
    /*
     * Initialize all components
     */
    private void initialize() {
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
    }
    
    private void createMapView() {
        mapView = new MapView(display, strings, new MapView.Listener() {

            public void back() {
                changeViewController(previousState);
            }
        });
    }
    
    private void createAboutView() {
        aboutView = new AboutFormView(strings, new BaseFormView.Listener() {

            public void changeView(int view) {
                changeViewController(view);
            }
        });
    }
    
    private void createCategoryListView() {
        categoryListView = new CategoryListView(strings, model.getModel(), new 
                BaseListView.Listener() {

            public void changeView(int view) {
                changeViewController(view);
            }
        });
    }
    
    private void createShopsListView() {
        shopsListView = new ShopsListView(strings, model.getModel().getShops(), 
                new BaseListView.Listener() {

            public void changeView(int view) {
                changeViewController(view);
            }
        });
    }
    
    private void createShopChoiseListView() {
        shopChoiseListView = new ShopChoiceListView(null, strings, 
                new BaseListView.Listener() {

            public void changeView(int view) {
                changeViewController(view);
            }
        });
    }
    
    private void createShopInfoFormView() {
        shopInfoFormView = new ShopInfoFormView(strings, 
                new BaseFormView.Listener() {

            public void changeView(int view) {
                changeViewController(view);
            }
        });
    }
    
    private void createHostelsListView() {
        hostelsListView = new HostelsListView(strings, 
                model.getModel().getHostels(), new BaseListView.Listener() {

            public void changeView(int view) {
                changeViewController(view);
            }
        });
    }
    
    private void createHostelChoiseListView() {
        hostelChoiseListView = new HostelChoiceListView(null, strings, 
                new BaseListView.Listener() {

            public void changeView(int view) {
                changeViewController(view);
            }
        });
    }
    
    private void createHostelInfoFormView() {
        hostelInfoFormView = new HostelInfoFormView(strings, 
                new BaseFormView.Listener() {

            public void changeView(int view) {
                changeViewController(view);
            }
        });
    }
    
    private void createLakesListView() {
        lakesListView = new LakesListView(strings, model.getModel().getLakes(), 
                new BaseListView.Listener() {

            public void changeView(int view) {
                changeViewController(view);
            }
        });
    }
    
    private void createLakeChoiceListView() {
        lakeChoiceListView = new LakeChoiceListView(null, strings, 
                new BaseListView.Listener() {

            public void changeView(int view) {
                changeViewController(view);
            }
        });
    }
    
    private void createLakeInfoFormView() {
        lakeInfoFormView = new LakeInfoFormView(strings, 
                new BaseFormView.Listener() {

            public void changeView(int view) {
                changeViewController(view);
            }
        });
    }
    
    private void createFishesListView() {
        fishesListView = new FishesListView(strings, new BaseListView.Listener() {

            public void changeView(int view) {
                changeViewController(view);
            }
        });
    }
    
    private void createFishInfoFormView() {
        fishInfoFormView = new FishInfoFormView(strings, 
                new BaseFormView.Listener() {

            public void changeView(int view) {
                changeViewController(view);
            }
        });
    }
    
    private void changeViewController(int view) {
        previousState = currentState;
        switch (view) {
            case MAP_VIEW:
                currentState = MAP_VIEW;
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
                break;
            case ABOUT_VIEW:
                currentState = ABOUT_VIEW;
                display.setCurrent(aboutView);
                break;
            case CATEGORY_LIST_VIEW:
                currentState = CATEGORY_LIST_VIEW;
                display.setCurrent(categoryListView);
                break;
            case SHOPS_LIST_VIEW:
                currentState = SHOPS_LIST_VIEW;
                display.setCurrent(shopsListView);
                break;
            case SHOP_CHOISE_LIST_VIEW:
                currentState = SHOP_CHOISE_LIST_VIEW;
                Shop shopNameChoice;
                shopNameChoice = (Shop)model.getModel().getShops().elementAt(
                        shopsListView.getSelectedIndex()); 
                shopChoiseListView.setTitle(shopNameChoice.getName());
                display.setCurrent(shopChoiseListView);
                break;
            case SHOP_INFO_FORM_VIEW:
                currentState = SHOP_INFO_FORM_VIEW;
                Shop shopInfo;
                shopInfo = (Shop)model.getModel().getShops().elementAt(
                        shopsListView.getSelectedIndex());
                shopInfoFormView.deleteAll();
                shopInfoFormView.setInfo(shopInfo);
                display.setCurrent(shopInfoFormView);
                break;
            case HOSTELS_LIST_VIEW:
                currentState = HOSTELS_LIST_VIEW;
                display.setCurrent(hostelsListView);
                break;
            case HOSTEL_CHOISE_LIST_VIEW:
                currentState = HOSTEL_CHOISE_LIST_VIEW;
                Hostel hostelNameChoice;
                hostelNameChoice = (Hostel)model.getModel().getHostels().elementAt(
                        hostelsListView.getSelectedIndex()); 
                hostelChoiseListView.setTitle(hostelNameChoice.getName());
                display.setCurrent(hostelChoiseListView);
                break;
            case HOSTEL_INFO_FORM_VIEW:
                currentState = HOSTEL_INFO_FORM_VIEW;
                Hostel hostelInfo;
                hostelInfo = (Hostel)model.getModel().getHostels().elementAt(
                        hostelsListView.getSelectedIndex());
                hostelInfoFormView.deleteAll();
                hostelInfoFormView.setInfo(hostelInfo);
                display.setCurrent(hostelInfoFormView);
                break;
            case LAKES_LIST_VIEW:
                currentState = LAKES_LIST_VIEW;
                display.setCurrent(lakesListView);
                break;
            case LAKE_CHOISE_LIST_VIEW:
                currentState = LAKE_CHOISE_LIST_VIEW;
                Lake lakeNameChoice;
                lakeNameChoice = (Lake)model.getModel().getLakes().elementAt(
                        lakesListView.getSelectedIndex());
                lakeNameChoice.setFishesInfo(null);
                lakeChoiceListView.setTitle(lakeNameChoice.getName());
                display.setCurrent(lakeChoiceListView);
                break;
            case LAKE_INFO_FORM_VIEW:
                currentState = LAKE_INFO_FORM_VIEW;
                Lake lakeInfo;
                lakeInfo = (Lake)model.getModel().getLakes().elementAt(
                        lakesListView.getSelectedIndex());
                lakeInfoFormView.deleteAll();
                lakeInfoFormView.setInfo(lakeInfo);
                display.setCurrent(lakeInfoFormView);
                break;
            case FISHES_LIST_VIEW:
                currentState = FISHES_LIST_VIEW;
                Lake lakeFishes;
                lakeFishes = (Lake)model.getModel().getLakes().elementAt(
                        lakesListView.getSelectedIndex());
                model.loadFish(lakeFishes);
                fishesListView.deleteAll();
                fishesListView.setTitle(lakeFishes.getName());
                fishesListView.setInfo(lakeFishes.getFishesInfo());
                display.setCurrent(fishesListView);
                break;
            case FISH_INFO_FORM_VIEW:
                currentState = FISHES_LIST_VIEW;
                Lake lakeFishInfo;
                lakeFishInfo = (Lake)model.getModel().getLakes().elementAt(
                        lakesListView.getSelectedIndex());
                Fish fish;
                fish = (Fish)lakeFishInfo.getFishesInfo().elementAt(
                        fishesListView.getSelectedIndex());
                fishInfoFormView.deleteAll();
                fishInfoFormView.setTitle(fish.getName());
                fishInfoFormView.setInfo(fish);
                display.setCurrent(fishInfoFormView);
            default:
                System.err.println("View that need to be change was not "
                        + "recognized");
        }
    }
   
}
