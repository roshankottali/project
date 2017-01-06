package com.example.user.sprint3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Registeration extends AppCompatActivity {

    public static final String LOGIN_URL = "http://services.trainees.baabtra.com/BM-42879/Register.php";
    public static final String FNAME="firstname";
    public static final String SNAME="srname";
    public static final String EMAIL="email";
    public static final String GENDER="gnder";
    public static final String DAY="day";
    public static final String MONTH="month";
    public static final String YEAR="year";
    public static final String PASSWORD="password";



    EditText fname;
    EditText Sname;
    EditText email;
    Spinner gendr;
    EditText day;
    EditText mnth;
    EditText year;
    EditText psdd;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeration);

        fname=(EditText)findViewById(R.id.editTextFname);
        Sname=(EditText)findViewById(R.id.editTextSname);
        email=(EditText)findViewById(R.id.editTextemail);
        gendr=(Spinner)findViewById(R.id.spinnerGender);
        day=(EditText)findViewById(R.id.editTextday);
        mnth=(EditText)findViewById(R.id.editTextmonth);
        year=(EditText)findViewById(R.id.editTextyear);
        psdd=(EditText)findViewById(R.id.editTextpwd);

        Button btreg=(Button)findViewById(R.id.buttonREG);
        btreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String Semail=email.getText().toString();
                final String Spsd=psdd.getText().toString();

                if (!isValidEmail(Semail)) {
                    email.setError("Invalid Email");
                }
                else
                if (!isValidPassword(Spsd)) {
                    psdd.setError("Invalid Password");
                }
                else
                {
                    regfun();
                }
            }
        });
    }
    private  void regfun()
    {
        final String Sfname=fname.getText().toString();
        final String SSname=Sname.getText().toString();
        final String Semail=email.getText().toString();
        final String Sday=day.getText().toString();
        final String Smnth=mnth.getText().toString();
        final String Syear=year.getText().toString();
        final String Spsd=psdd.getText().toString();





                    StringRequest stringRequest=new StringRequest(Request.Method.POST, LOGIN_URL, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response)
                        {
                            if(response.contains("REGISTERATION_SUCCESSFULL"))
                            {
                                Intent intent=new Intent(Registeration.this,Reglanding.class);
                               // intent.putExtra(KEY_USERNAME,eml);
                                startActivity(intent);
                                //Toast.makeText(Registeration.this,response,Toast.LENGTH_LONG).show();

                            }
                            else
                            {
                                Toast.makeText(Registeration.this, response, Toast.LENGTH_SHORT).show();
                            }

                        }
                    },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(Registeration.this, error.toString(), Toast.LENGTH_SHORT).show();
                                }
                            }


                    ){
                        @Override

                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String,String> map = new HashMap<String,String>();



                            map.put(FNAME,Sfname);
                            map.put(SNAME,SSname);
                            map.put(EMAIL,Semail);
                            map.put(GENDER,Sday);
                            map.put(DAY,Sday);
                            map.put(MONTH,Smnth);
                            map.put(YEAR,Syear);
                            map.put(PASSWORD,Spsd);
                            return map;
                        }
                    };

                    RequestQueue requestQueue = Volley.newRequestQueue(this);
                    requestQueue.add(stringRequest);



    }

    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean isValidPassword(String pass) {
        if (pass != null && pass.length() > 6) {
            return true;
        }
        return false;
    }
}
