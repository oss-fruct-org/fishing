package oss.fruct.org.fishing.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import oss.fruct.org.fishing.R;
import oss.fruct.org.fishing.geoobjects.Lake;

public class LakeInfoFragment extends Fragment {

    static Lake lake;
    TextView lakeInfoText;
    TextView coordText;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       View v =  (LinearLayout) inflater.inflate(R.layout.fragment_lakeinfo, container, false);

        lakeInfoText = (TextView) v.findViewById(R.id.lakeInfoText);
        coordText = (TextView) v.findViewById(R.id.coordinatesText);

        if(lake!=null){
            lakeInfoText.setText(lake.getDescription());
            coordText.setText(lake.getLatitude() + ", " + lake.getLongitude());
        }
        setHasOptionsMenu(true);
        return v;
    }

    public LakeInfoFragment(){
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    }

    public void setLake(Lake lake){
        this.lake = lake;
    }
}
