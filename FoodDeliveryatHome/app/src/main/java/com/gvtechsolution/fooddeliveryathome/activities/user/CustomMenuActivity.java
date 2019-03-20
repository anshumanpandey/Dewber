package com.gvtechsolution.fooddeliveryathome.activities.user;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.gvtechsolution.fooddeliveryathome.R;

public class CustomMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_menu);
        getSupportActionBar().setTitle("Custom menu");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Button goToSave = (Button)findViewById(R.id.custom_menu_save_button);
        goToSave.setOnClickListener(custom_menu_listener);
    }
    private View.OnClickListener custom_menu_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          finish();
        }
    };
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
