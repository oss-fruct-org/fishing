package oss.fruct.org.fishing.fragments;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.DialogFragment;

import java.util.ArrayList;
import java.util.List;

import oss.fruct.org.fishing.R;
import oss.fruct.org.fishing.parsers.DataController;

public class FilterDialog extends DialogFragment {
    private String[] filterNames;
    private boolean[] filterChecked;
    Listener listener;

    interface Listener{
        void filterChanged();
    }

    public void setListener(Listener l){
        listener = l;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putBooleanArray("filterChecked", filterChecked);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final int [] categories = DataController.getInstance().getActiveTypes();


        // Fill list data
        filterNames = new String[categories.length];

        filterNames[DataController.LAKES_ID] = getResources().getString(R.string.lakes);
        filterNames[DataController.HOSTELS_ID] = getResources().getString(R.string.hostels);
        filterNames[DataController.SHOPS_ID] = getResources().getString(R.string.shops);


        if (savedInstanceState != null) {
            filterChecked = savedInstanceState.getBooleanArray("filterChecked");
        } else {
            filterChecked = new boolean[categories.length];
        }


        for(int i =0; i < categories.length; i ++){
            filterChecked[i] = categories[i] == 1;
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(getResources().getString(R.string.filter))
                .setMultiChoiceItems(filterNames,
                        filterChecked, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                filterChecked[which] = isChecked;
                            }
                        });

        builder.setPositiveButton(getResources().getString(android.R.string.ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                for(int i =0; i < categories.length; i++)
                    categories[i] = filterChecked[i] ? 1 : 0;

                DataController.getInstance().setActiveTypes(categories);
                if(listener!=null)
                    listener.filterChanged();
            }
        });

        builder.setNegativeButton(getResources().getString(android.R.string.cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        return builder.create();
    }
}
