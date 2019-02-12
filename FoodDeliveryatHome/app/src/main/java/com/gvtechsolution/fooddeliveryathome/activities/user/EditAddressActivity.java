package com.gvtechsolution.fooddeliveryathome.activities.user;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.gvtechsolution.fooddeliveryathome.R;
import com.gvtechsolution.fooddeliveryathome.classes.AddAddressBottomSheet;

public class EditAddressActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_address);
        Button close_address_button =findViewById(R.id.address_close_button);
        close_address_button.setOnClickListener(close_address_clicklistener);

        /*Bottom sheet start*/
        TextView openAddAddress = findViewById(R.id.add_address_textview);
        openAddAddress.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                AddAddressBottomSheet bottomSheet = new AddAddressBottomSheet();
                bottomSheet.show(getSupportFragmentManager(),"Add Address");

            }
        });
        /*Bottom sheet end*/
    }
    private View.OnClickListener close_address_clicklistener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(EditAddressActivity.this, HomeActivity.class);
            startActivity(intent);
        }
    };

}
