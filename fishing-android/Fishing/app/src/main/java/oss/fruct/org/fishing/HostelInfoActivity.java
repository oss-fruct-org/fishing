package oss.fruct.org.fishing;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import oss.fruct.org.fishing.geoobjects.Hostel;
import oss.fruct.org.fishing.parsers.DataController;

public class HostelInfoActivity extends ActionBarActivity {

    TextView info;
    TextView phone;
    TextView site;
    TextView coords;
    Hostel hostel;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hostel_activity);

        final ActionBar actionBar = getSupportActionBar();
        info = (TextView) findViewById(R.id.hostelInfoText);
        phone = (TextView) findViewById(R.id.phoneText);
        site = (TextView) findViewById(R.id.siteText);
        coords = (TextView) findViewById(R.id.hostelCoordsText);

        coords.setText("keeposdf");
        hostel = getIntent().getExtras().getParcelable("selectedHostel");

        if(hostel!=null) {
            coords.setText(hostel.getLatitude() + ", " + hostel.getLongitude());
            info.setText(hostel.getDescription());
            phone.setText(hostel.getPhone());
            site.setText(hostel.getSite());
            actionBar.setTitle(hostel.getName());
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.geo_object_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle item selection
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.action_jumpto:
                if(hostel!=null)
                    DataController.getInstance().setTarget(hostel.getLatitude(), hostel.getLongitude());
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
