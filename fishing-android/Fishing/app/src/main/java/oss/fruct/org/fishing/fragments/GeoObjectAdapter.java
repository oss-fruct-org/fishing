package oss.fruct.org.fishing.fragments;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import oss.fruct.org.fishing.GeoObjectItem;
import oss.fruct.org.fishing.R;

public class GeoObjectAdapter extends ArrayAdapter {
    Context context;
    String name;
    public GeoObjectAdapter(Context context, List items) {
        super(context, R.layout.fish_list_item, items);
        this.context = context;
    }

    public void setName(String name){this.name = name;}
    public String getName(){return name;}
    /**
     * Holder for the list items.
     */
    private class ViewHolder{
        TextView titleText;
    }

    /**
     *
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        GeoObjectItem item = (GeoObjectItem)getItem(position);
        View viewToUse = null;

        // This block exists to inflate the settings list item conditionally based on whether
        // we want to support a grid or list view.
        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            viewToUse = mInflater.inflate(R.layout.fish_list_item, null);

            holder = new ViewHolder();
            holder.titleText = (TextView)viewToUse.findViewById(R.id.listItemText);
            viewToUse.setTag(holder);
        } else {
            viewToUse = convertView;
            holder = (ViewHolder) viewToUse.getTag();
        }

        holder.titleText.setText(item.getItemTitle());
        return viewToUse;
    }




}
