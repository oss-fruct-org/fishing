package oss.fruct.org.fishing.fragments;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import oss.fruct.org.fishing.AboutActivity;
import oss.fruct.org.fishing.AllGeoObjectsActivity;
import oss.fruct.org.fishing.GeoObjectsListActivity;
import oss.fruct.org.fishing.HelpActivity;
import oss.fruct.org.fishing.HostelInfoActivity;
import oss.fruct.org.fishing.LakeInfoActivity;
import oss.fruct.org.fishing.R;
import oss.fruct.org.fishing.ShopInfoActivity;
import oss.fruct.org.fishing.geoobjects.GeoObject;
import oss.fruct.org.fishing.geoobjects.Hostel;
import oss.fruct.org.fishing.geoobjects.Lake;
import oss.fruct.org.fishing.geoobjects.Shop;
import oss.fruct.org.fishing.parsers.DataController;

public class MapFragment extends Fragment {
    private GoogleMap map; // Might be null if Google Play services APK is not available.
    MapView mMapView;
    Marker lastOpened = null;
    boolean dataReady = false;
    public static DataController dataController;

    private Map<Marker, Lake> lakesMap = new HashMap<Marker, Lake>();
    private Map<Marker, Hostel> hostelsMap = new HashMap<Marker, Hostel>();
    private Map<Marker, Shop> shopsMap = new HashMap<Marker, Shop>();
    SparseArray<BitmapDescriptor> icons;
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment MapFragment.
     */
    public static MapFragment newInstance() {
        return new MapFragment();
    }
    public MapFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        icons = new SparseArray<BitmapDescriptor>();
        icons.put(R.drawable.fishing, BitmapDescriptorFactory.fromResource(R.drawable.fishing));
        icons.put(R.drawable.shop, BitmapDescriptorFactory.fromResource(R.drawable.shop));
        icons.put(R.drawable.hostel, BitmapDescriptorFactory.fromResource(R.drawable.hostel));

        Log.e("map", "map fragment created");
       // setUpMapIfNeeded();
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
        jumpToLocation();
      //  setUpMapIfNeeded();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_map, container, false);
        assert view != null;

        mMapView = (MapView) view.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);



        map = mMapView.getMap();

        mMapView.onResume();// needed to get the map to display immediately
        setUpMap();

        map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            public boolean onMarkerClick(Marker marker) {
                if(lakesMap.get(marker)!=null){
                    dataController.getDataModel().setCurrentLake(dataController.getDataModel().getLakes().indexOf(lakesMap.get(marker)));
                    Intent intent = new Intent(getActivity(), LakeInfoActivity.class);
                    startActivity(intent);
                    return true;
                }
                if(hostelsMap.get(marker)!=null){
                    Intent intent = new Intent(getActivity(), HostelInfoActivity.class).putExtra("selectedHostel", hostelsMap.get(marker));
                    startActivity(intent);
                    return true;
                }
                if(shopsMap.get(marker)!=null){
                    Intent intent = new Intent(getActivity(), ShopInfoActivity.class).putExtra("selectedShop", shopsMap.get(marker));
                    startActivity(intent);
                    return true;
                }
                // Check if there is an open info window
                if (lastOpened != null) {
                    // Close the info window
                    lastOpened.hideInfoWindow();

                    // Is the marker the same marker that was already open
                    if (lastOpened.equals(marker)) {
                        // Nullify the lastOpened object
                        lastOpened = null;
                        // Return so that the info window isn't opened again
                        return true;
                    }
                }

                // Open the info window for the marker
                marker.showInfoWindow();
                // Re-assign the last opened such that we can close it later
                lastOpened = marker;

                // Event was handled by our code do not launch default behaviour.
                return true;
            }
        });
        map.moveCamera( CameraUpdateFactory.newLatLngZoom(new LatLng(62.294272, 33.429519), 7.0f) );
        new loadDataTask().execute();
        setHasOptionsMenu(true);

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.main_menu, menu);
        Log.e("menus","inflated menu");

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle item selection
        Intent intent;
        switch (item.getItemId()) {
            case R.id.action_allpoints:
                intent = new Intent(getActivity(),AllGeoObjectsActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_filter:
                FilterDialog dialog = new FilterDialog();
                dialog.setListener(new FilterDialog.Listener() {
                    @Override
                    public void filterChanged() {
                        updateMap();
                    }
                });
                dialog.show(getFragmentManager(), "filter-dialog");
                return true;
            case R.id.action_help:
                intent = new Intent(getActivity(),HelpActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_about:
                intent = new Intent(getActivity(),AboutActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private void updateMap(){
        map.clear();
        drawMarkers();
    }


    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p>
     * This should only be called once and when we are sure that {@link #map} is not null.
     */
    private void setUpMap() {
        UiSettings mapSettings;
        mapSettings = map.getUiSettings();
        mapSettings.setZoomControlsEnabled(true);
        mapSettings.setRotateGesturesEnabled(false);
        mapSettings.setTiltGesturesEnabled(false);
    }

    private void drawMarkers(){
        if(dataReady){
            Vector lakes = dataController.getDataModel().getLakes();
            if(dataController.getActiveTypes()[DataController.LAKES_ID] != 0) {
                for (int i = 0; i < lakes.size(); i++) {
                    Lake l = (Lake) lakes.get(i);
                    Marker m = map.addMarker(new MarkerOptions().position(new LatLng(l.getLatitude(), l.getLongitude())).icon(icons.get(R.drawable.fishing)));
                    lakesMap.put(m, l);
                }
            }
            if(dataController.getActiveTypes()[DataController.SHOPS_ID] != 0) {
                Vector shops = dataController.getDataModel().getShops();
                for (int i = 0; i < shops.size(); i++) {
                    Shop s = (Shop) shops.get(i);
                    Marker m = map.addMarker(new MarkerOptions().position(new LatLng(s.getLatitude(), s.getLongitude())).icon(icons.get(R.drawable.shop)));
                    shopsMap.put(m, s);
                }
            }

            if(dataController.getActiveTypes()[DataController.HOSTELS_ID] != 0) {
                Vector hostels = dataController.getDataModel().getHostels();
                for (int i = 0; i < hostels.size(); i++) {
                    Hostel h = (Hostel) hostels.get(i);
                    Marker m = map.addMarker(new MarkerOptions().position(new LatLng(h.getLatitude(), h.getLongitude())).icon(icons.get(R.drawable.hostel)));
                    hostelsMap.put(m, h);
                }
            }

        }
    }

    private void jumpToLocation(){
        double lat = DataController.getInstance().getTargetLat();
        double lon = DataController.getInstance().getTargetLon();

        if(lat == 0 && lon == 0)
            return;

        map.moveCamera( CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lon), 12.0f) );
        DataController.getInstance().setTarget(0,0);
    }

    class loadDataTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            dataController = DataController.getInstance();
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            //Log.e("dmloading", dataController.getDataModel().getLakes().size() +"");
            if(dataController.getDataModel()!= null){
                dataReady = true;
                drawMarkers();
                jumpToLocation();
            }
        }
    }
}
