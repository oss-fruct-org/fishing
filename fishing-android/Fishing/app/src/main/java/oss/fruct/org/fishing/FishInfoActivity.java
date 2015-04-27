package oss.fruct.org.fishing;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

import oss.fruct.org.fishing.geoobjects.Fish;

public class FishInfoActivity extends ActionBarActivity {

    ImageView imageView;
    TextView infoText;
    Fish fish;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fish_activity);
        imageView = (ImageView) findViewById(R.id.fishImageView);
        infoText = (TextView) findViewById(R.id.fishInfoView);
        fish = getIntent().getExtras().getParcelable("selectedFish");
        infoText.setText("Keepo!wsfsdf");
        Log.e("fish", "fish getinfo ={" + fish.getFishInfo() + "}");
        if(fish != null){
            infoText.setText(fish.getFishInfo());
            if(fish.getFishInfo() == null || fish.getFishInfo().length() < 5)
                infoText.setText(getResources().getString(R.string.no_info));
            getSupportActionBar().setTitle(fish.getName());

        try {
            // get input stream
            InputStream is = getAssets().open(fish.getPicturePath());
            // load image as Drawable
            Drawable d = Drawable.createFromStream(is, null);
            // set image to ImageView
            imageView.setImageDrawable(d);
        }
        catch(IOException ex) {
            ex.printStackTrace();
            return;
        }
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
