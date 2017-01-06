package com.example.user.sprint3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import com.android.volley.toolbox.Volley;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.AuthFailureError;
import android.widget.RelativeLayout.LayoutParams;
import com.android.volley.RequestQueue;
import android.view.Gravity;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.widget.RelativeLayout;



public class MainActivity extends AppCompatActivity {
    String eml;
    String psd;
    EditText editemail;
    EditText editpsd;
    Button btlog;
    TextView textview;
    LayoutParams layoutparams;
    ActionBar actionbar;

    public static final String LOGIN_URL = "http://services.trainees.baabtra.com/BM-42879/Loginservice.php";
    public static final String KEY_USERNAME="email";
    public static final String KEY_PASSWORD="password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        //CUSTOM ACTION BAR START
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.mycustumactionbar);
        View view=getSupportActionBar().getCustomView();

            Toolbar parent=(Toolbar)view.getParent();
        parent.setPadding(0,0,0,0);
        parent.setContentInsetsAbsolute(0,0);

        //END
        editemail=(EditText)findViewById(R.id.editTextEML);
        editpsd=(EditText)findViewById(R.id.editText2);
        btlog=(Button)findViewById(R.id.button);

        btlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userLogin();
            }
        });



        Button bt2=(Button)findViewById(R.id.button2);
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intnt=new Intent(MainActivity.this,Registeration.class);
                startActivity(intnt);
            }
        });


    }





    private void userLogin()
    {
            eml=editemail.getText().toString();
            psd=editpsd.getText().toString();

        StringRequest stringRequest=new StringRequest(Request.Method.POST, LOGIN_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {
                if((response.contains("200")) && (response.contains("success")))
                {
                    Intent intent=new Intent(MainActivity.this,Homew.class);
                    intent.putExtra(KEY_USERNAME,eml);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(MainActivity.this, response, Toast.LENGTH_SHORT).show();
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }


        ){
            @Override

            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<String,String>();
                map.put(KEY_USERNAME,eml);
                map.put(KEY_PASSWORD,psd);
                return map;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

//    private void openProfile(){
//        Intent intent=new Intent(this,Homew.class);
//        intent.putExtra(KEY_USERNAME,eml);
//        startActivity(intent);
//    }


    }
