package oss.fruct.org.fishing;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import oss.fruct.org.fishing.geoobjects.Shop;
import oss.fruct.org.fishing.parsers.DataController;

public class ShopInfoActivity extends ActionBarActivity {


    TextView phone;
    TextView address;
    TextView coords;
    Shop shop;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop_activity);

        final ActionBar actionBar = getSupportActionBar();
        phone = (TextView) findViewById(R.id.phoneText);
        address = (TextView) findViewById(R.id.addressText);
        coords = (TextView) findViewById(R.id.shopCoordsText);

        shop = getIntent().getExtras().getParcelable("selectedShop");

        if(shop!=null) {
            coords.setText(shop.getLatitude() + ", " + shop.getLongitude());
            phone.setText(shop.getPhone());
            address.setText(shop.getAddress());
            actionBar.setTitle(shop.getName());
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
                if(shop!=null)
                    DataController.getInstance().setTarget(shop.getLatitude(), shop.getLongitude());
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
