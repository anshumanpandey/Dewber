package com.gvtechsolution.fooddeliveryathome.activities;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.gvtechsolution.fooddeliveryathome.R;
import com.gvtechsolution.fooddeliveryathome.activities.business.BusinessRegistrationActivity;
import com.gvtechsolution.fooddeliveryathome.activities.user.HomeActivity;
import com.gvtechsolution.fooddeliveryathome.activities.user.RegistrationActivity;

public class LoginActivity extends AppCompatActivity {
    final Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button user_registration = (Button)findViewById(R.id.login_register_button);
        user_registration.setOnClickListener(clickListener);
        Button business_registration = (Button)findViewById(R.id.login_business_link_button);
        business_registration.setOnClickListener(business_registration_clickListener);
        Button user_home = (Button)findViewById(R.id.login_button);
        user_home.setOnClickListener(login_clickListener);
        TextView goToForgotPassword = (TextView) findViewById(R.id.login_forgot_password_link);
        goToForgotPassword.setOnClickListener(forgot_password_listener);
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
            startActivity(intent);
        }
    };
    private View.OnClickListener business_registration_clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(LoginActivity.this, BusinessRegistrationActivity.class);
            startActivity(intent);
        }
    };
    private View.OnClickListener login_clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(intent);
        }
    };


    private View.OnClickListener forgot_password_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            final Dialog forgot_password_dialog = new Dialog(context);
            forgot_password_dialog.setContentView(R.layout.forgot_password);

            WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
            lp.copyFrom(forgot_password_dialog.getWindow().getAttributes());
            lp.width = WindowManager.LayoutParams.MATCH_PARENT;
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;

            Button cancel = (Button) forgot_password_dialog.findViewById(R.id.forgot_password_cancel_button);
            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    forgot_password_dialog.dismiss();
                }
            });
            Button confirm = (Button) forgot_password_dialog.findViewById(R.id.forgot_password_confirm_button);
            confirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    forgot_password_dialog.dismiss();
                }
            });
            forgot_password_dialog.show();
            forgot_password_dialog.getWindow().setAttributes(lp);
        }
    };
}
