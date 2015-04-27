package oss.fruct.org.fishing.fragments;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Vector;

import oss.fruct.org.fishing.App;
import oss.fruct.org.fishing.FishInfoActivity;
import oss.fruct.org.fishing.GeoObjectItem;
import oss.fruct.org.fishing.HostelInfoActivity;
import oss.fruct.org.fishing.LakeInfoActivity;
import oss.fruct.org.fishing.R;
import oss.fruct.org.fishing.ShopInfoActivity;
import oss.fruct.org.fishing.geoobjects.Fish;
import oss.fruct.org.fishing.geoobjects.Hostel;
import oss.fruct.org.fishing.geoobjects.Lake;
import oss.fruct.org.fishing.geoobjects.Shop;
import oss.fruct.org.fishing.parsers.DataController;

public class FragmentGeoList extends Fragment implements AdapterView.OnItemClickListener, TextWatcher{


    ArrayList<GeoObjectItem> items;

    private GeoObjectAdapter adapter;
    private int objectsType = 0;
    private ListView list;
    private TextView searchField;
    private String searchText;

    private OnFragmentInteractionListener mListener;

    // TODO: Rename and change types of parameters
    public static FragmentFishList newInstance(String param1, String param2) {
        FragmentFishList fragment = new FragmentFishList();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public FragmentGeoList() {
    }

    public void setObjectsType(int ot){
        objectsType = ot;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
        }

        adapter = getGeoAdapter();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.geo_objects_fragment,null);
        list = (ListView) v.findViewById(R.id.listView);
        list.setAdapter(adapter);
        list.setOnItemClickListener(this);

        searchField = (TextView) v.findViewById(R.id.search_field);
        searchField.addTextChangedListener(this);
        return v;
    }

    private GeoObjectAdapter getGeoAdapter(){
        GeoObjectAdapter adapter = null;

        ArrayList<GeoObjectItem> items = new ArrayList<>();

        switch(objectsType) {
            case DataController.LAKES_ID:
            Vector lakes = DataController.getInstance().getDataModel().getLakes();
            Lake l;
            for (int i = 0; i < lakes.size(); i++) {
                l = (Lake) lakes.get(i);
                if (searchText != null
                        && searchText.length() > 0
                        && !l.getName().toLowerCase(Locale.getDefault()).contains(searchText)) {

                } else
                    items.add(new GeoObjectItem(l.getName(), l, GeoObjectItem.IS_LAKE));
            }
            adapter = new GeoObjectAdapter(getActivity(), items);
            adapter.setName(getResources().getString(R.string.lakes));

                break;
            case DataController.HOSTELS_ID:
            Vector hostels = DataController.getInstance().getDataModel().getHostels();
            Hostel h;
            for (int i = 0; i < hostels.size(); i++) {
                h = (Hostel) hostels.get(i);
                if (searchText != null
                        && searchText.length() > 0
                        && !h.getName().toLowerCase(Locale.getDefault()).contains(searchText)) {

                } else
                    items.add(new GeoObjectItem(h.getName(), h, GeoObjectItem.IS_HOSTEL));
            }
            adapter = new GeoObjectAdapter(getActivity(), items);
            adapter.setName(getResources().getString(R.string.hostels));
                break;
            case DataController.SHOPS_ID:

            Vector shops = DataController.getInstance().getDataModel().getShops();
            Shop s;
            for (int i = 0; i < shops.size(); i++) {
                s = (Shop) shops.get(i);
                if (searchText != null
                        && searchText.length() > 0
                        && !s.getName().toLowerCase(Locale.getDefault()).contains(searchText)) {

                } else
                    items.add(new GeoObjectItem(s.getName(), s, GeoObjectItem.IS_SHOP));
            }

            adapter = new GeoObjectAdapter(getActivity(), items);
            adapter.setName(getResources().getString(R.string.shops));
                break;
        }
        return adapter;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    private void updateList(){
        adapter = getGeoAdapter();
        list.setAdapter(adapter);
    }



    public void onListItemClick(ListView l, View v, int position, long id) {
        Intent intent;
        switch (objectsType) {
            case DataController.LAKES_ID:
                DataController.getInstance().getDataModel().setCurrentLake(position);
                intent = new Intent(App.get(), LakeInfoActivity.class);
                startActivity(intent);
                break;
            case DataController.HOSTELS_ID:
                intent = new Intent(App.get(), HostelInfoActivity.class).putExtra("selectedHostel",(Hostel)DataController.getInstance().getDataModel().getHostels().get(position));
                startActivity(intent);
                break;
            case DataController.SHOPS_ID:
                intent = new Intent(App.get(), ShopInfoActivity.class).putExtra("selectedShop",(Shop)DataController.getInstance().getDataModel().getShops().get(position));
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        onListItemClick(null, view, position,id);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        searchText = s.toString().toLowerCase(Locale.getDefault());
        updateList();
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(String id);
    }

}
