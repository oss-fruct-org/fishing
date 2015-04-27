package oss.fruct.org.fishing.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import oss.fruct.org.fishing.R;

public class FishListFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return (LinearLayout) inflater.inflate(R.layout.fragment_fishlist, container, false);
    }
}



