package oss.fruct.org.fishing;


import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Vector;

import oss.fruct.org.fishing.fragments.GeoObjectAdapter;
import oss.fruct.org.fishing.fragments.MergeAdapter;
import oss.fruct.org.fishing.geoobjects.Hostel;
import oss.fruct.org.fishing.geoobjects.Lake;
import oss.fruct.org.fishing.geoobjects.Shop;
import oss.fruct.org.fishing.parsers.DataController;

public class GeoObjectsListActivity extends ActionBarActivity  implements TextWatcher {

    ListView list;
    String searchText="";
    TextView searchField;
    LayoutInflater inflater;
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.geo_list_activity);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(getResources().getString(R.string.allpoints));
        list = (ListView) findViewById(R.id.listView);
        searchField = (TextView) findViewById(R.id.search_field);
        searchField.addTextChangedListener(this);
        inflater = (LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        updateList();
    }

    private GeoObjectAdapter[] getGeoAdapters(){
        GeoObjectAdapter[] adapters = new GeoObjectAdapter[3];

        ArrayList<GeoObjectItem> items = new ArrayList<>();
        ArrayList<GeoObjectItem> items2 = new ArrayList<>();
        ArrayList<GeoObjectItem> items3 = new ArrayList<>();
        //lakes
        Vector lakes = DataController.getInstance().getDataModel().getLakes();
        Lake l;
        for(int i =0; i < lakes.size(); i++){
            l = (Lake) lakes.get(i);
            if (searchText != null
                    && searchText.length() > 0
                    && !l.getName().toLowerCase(Locale.getDefault()).contains(searchText)){

            }else
                        items.add(new GeoObjectItem(l.getName(),l,GeoObjectItem.IS_LAKE));
        }
        adapters[0] = new GeoObjectAdapter(this,items);
        adapters[0].setName(getResources().getString(R.string.lakes));

        Vector hostels = DataController.getInstance().getDataModel().getHostels();
        Hostel h;
        for(int i =0; i < hostels.size(); i++){
            h = (Hostel) hostels.get(i);
            if (searchText != null
                    && searchText.length() > 0
                    && !h.getName().toLowerCase(Locale.getDefault()).contains(searchText)) {

            }else
                items2.add(new GeoObjectItem(h.getName(),h,GeoObjectItem.IS_HOSTEL));
        }
        adapters[1] = new GeoObjectAdapter(this,items2);
        adapters[1].setName(getResources().getString(R.string.hostels));

        Vector shops = DataController.getInstance().getDataModel().getShops();
        Shop s;
        for(int i = 0; i < shops.size(); i ++){
            s = (Shop)shops.get(i);
            if (searchText != null
                    && searchText.length() > 0
                    && !s.getName().toLowerCase(Locale.getDefault()).contains(searchText)){

            }else
            items3.add(new GeoObjectItem(s.getName(),s,GeoObjectItem.IS_SHOP));
        }

        adapters[2] = new GeoObjectAdapter(this,items3);
        adapters[2].setName(getResources().getString(R.string.shops));

        return adapters;
    }

    private void updateList(){
        MergeAdapter madapter = new MergeAdapter();
        GeoObjectAdapter[] adapters = getGeoAdapters();
        for(int i = 0; i < adapters.length; i ++){
            View header = inflater.inflate(R.layout.header_item, null);
            TextView text = (TextView)header.findViewById(R.id.textView);
            text.setText(adapters[i].getName());
            madapter.addView(header);
            madapter.addAdapter(adapters[i]);

        }

        list.setAdapter(madapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                GeoObjectItem clickedItem = (GeoObjectItem) parent.getAdapter().getItem(position);
                Intent intent;
                switch(clickedItem.getItemType()){
                    case GeoObjectItem.IS_LAKE:
                        DataController.getInstance().getDataModel().setCurrentLake(DataController.getInstance().getDataModel().getLakes().indexOf(clickedItem.getObject()));
                        intent = new Intent(App.get(), LakeInfoActivity.class);
                        startActivity(intent);
                        break;
                    case GeoObjectItem.IS_HOSTEL:
                        intent = new Intent(App.get(), HostelInfoActivity.class).putExtra("selectedHostel", (Hostel)clickedItem.getObject());
                        startActivity(intent);
                        break;
                    case GeoObjectItem.IS_SHOP:
                        intent = new Intent(App.get(), ShopInfoActivity.class).putExtra("selectedShop", (Shop)clickedItem.getObject());
                        startActivity(intent);
                        break;

                }
            }
        });
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        searchText = s.toString().toLowerCase(Locale.getDefault());
        updateList();
    }

    @Override
    public void afterTextChanged(Editable s) {    }


}
