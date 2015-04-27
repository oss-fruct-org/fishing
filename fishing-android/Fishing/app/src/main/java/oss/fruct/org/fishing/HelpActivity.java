package oss.fruct.org.fishing;


import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

public class HelpActivity extends ActionBarActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help_activity);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
