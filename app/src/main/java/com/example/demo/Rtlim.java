package com.example.demo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.aminography.choosephotohelper.ChoosePhotoHelper;
import com.aminography.choosephotohelper.callback.ChoosePhotoCallback;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.ErrorRequestCoordinator;
import com.bumptech.glide.request.RequestOptions;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;
import static android.provider.SyncStateContract.*;

public class Rtlim extends AppCompatActivity {

    Button submit;
    ImageView imageView;
    EditText summary, description;
    ChoosePhotoHelper choosePhotoHelper;

    String[] category = {"Select: ", "APIs", "Data Syncing", "ERP", "Features", "functional","General","Integration","Master Data","Razor Pay","RMQ","Security","Session","UI"};
    String[] reproduce= {"Select: ","Always","Sometimes","Random","Not Tried","Unable to reproduce","N/A"};
    int[] reproduceValue={0,10,30,50,70,90,100};

    String[] severity= {"Select: ","Trivial","Text","tweak","Minor","Major","Crash","Block"};
    int[] severityValue={0,10,30,50,70,90,100};

    String[] appsImpct={"Select: ","1","2","3","4",">5"};
    String[] userImpct={"Select: ","1","2","3","4-10",">10"};
    String[] financeImpct={"Select: ","0","50k","1L","2L","3L"};
    String[] repdamage={"Select: ","Yes","NO"};
    String[] tatmissed={"Select: ","Missed","Likely","NO"};

    String category_item = "";

    String url="http://103.197.122.135:1111/mantis/api/rest/issues";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rtlim);

        Spinner catog = (Spinner) findViewById(R.id.spinner);
        Spinner repos= (Spinner) findViewById(R.id.reproduce_spinner);
        Spinner severe= (Spinner) findViewById(R.id.severity_spinner);
        Spinner appsimp=(Spinner) findViewById(R.id.appsimpct_spinner);
        Spinner userimp=(Spinner) findViewById(R.id.userimpct_spinner);
        Spinner finance=(Spinner) findViewById(R.id.financeimpct_spinner);
        Spinner damage=(Spinner) findViewById(R.id.damagerepu_spinner);
        Spinner t_missed=(Spinner) findViewById(R.id.tatmissed_spinner);

        summary=findViewById(R.id.edit_summary);
        description=findViewById(R.id.edit_desc);
        imageView=(ImageView) findViewById(R.id.image);
        submit=findViewById(R.id.submit);

       // catog.setOnItemSelectedListener(this);
      /*  repos.setOnItemSelectedListener(this);
        severe.setOnItemSelectedListener(this);
        appsimp.setOnItemSelectedListener(this);
        userimp.setOnItemSelectedListener(this);
        finance.setOnItemSelectedListener(this);
        damage.setOnItemSelectedListener(this);
        t_missed.setOnItemSelectedListener(this);*/

        catog.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                category_item = parent.getItemAtPosition(position).toString();
                Toast.makeText(Rtlim.this, "Item " +category_item, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        ArrayAdapter cat = new ArrayAdapter(this,android.R.layout.simple_spinner_item,category);
        cat.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        catog.setAdapter(cat);

        ArrayAdapter repo = new ArrayAdapter(this,android.R.layout.simple_spinner_item,reproduce);
        repo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        repos.setAdapter(repo);

        ArrayAdapter sev = new ArrayAdapter(this,android.R.layout.simple_spinner_item,severity);
        sev.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        severe.setAdapter(sev);

        ArrayAdapter apps = new ArrayAdapter(this,android.R.layout.simple_spinner_item,appsImpct);
        apps.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        appsimp.setAdapter(apps);

        ArrayAdapter user = new ArrayAdapter(this,android.R.layout.simple_spinner_item,userImpct);
        user.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        userimp.setAdapter(user);

        ArrayAdapter fina = new ArrayAdapter(this,android.R.layout.simple_spinner_item,financeImpct);
        fina.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        finance.setAdapter(fina);

        ArrayAdapter dmg = new ArrayAdapter(this,android.R.layout.simple_spinner_item,repdamage);
        dmg.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        damage.setAdapter(dmg);

        ArrayAdapter tat = new ArrayAdapter(this,android.R.layout.simple_spinner_item,tatmissed);
        tat.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        t_missed.setAdapter(tat);


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                choosePhotoHelper.showChooser(R.style.mAlertDialog);
            }
        });


        choosePhotoHelper = ChoosePhotoHelper.with(this).
                asFilePath().
                withState(savedInstanceState).
                build(new ChoosePhotoCallback<String>() {
                    @Override
                    public void onChoose(String photo) {
                        Glide.with(imageView).load(photo)
                                .apply(RequestOptions.placeholderOf(R.drawable.ic_launcher_background))
                                .into(imageView);
                    }
                });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jsonObjectPost();
            }

            private void jsonObjectPost() {

                JSONObject requestJsonSumDisc = new JSONObject();

                try{
                    requestJsonSumDisc.put("summary",summary);
                    requestJsonSumDisc.put("description",description);

                    JSONObject jsonObjectCatSever=new JSONObject();
                    try {
                        jsonObjectCatSever.put("category",category);
                        jsonObjectCatSever.put("severity",severity);
                        jsonObjectCatSever.put("reproducibility",reproduceValue);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                }catch (Exception ev){
                    System.out.print(ev.getMessage());
                }

                JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                        url, requestJsonSumDisc,
                        new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {
                                Log.d(TAG, response.toString());
                                Toast.makeText(getApplicationContext(),"Data Submitted",Toast.LENGTH_LONG).show();
                                //pDialog.hide();
                            }
                        }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        VolleyLog.d(TAG, "Error: " + error.getMessage());

                    }
                });

//                JsonObjectRequest req = new JsonObjectRequest(requestJsonSumDisc,jsonObjectCatSever,new Response.Listener<JSONObject>() {
//                            @Override
//                            public void onResponse(JSONObject response) {
//                                try {
//                                    Toast.makeText(Rtlim.this, "Your query submitted sucessfully", Toast.LENGTH_LONG).show();
//                                    summary.setText("");
//                                    description.setText("");
//
//                                } catch (Exception e) {
//                                    //CustomProgressBar.removeCustomProgressDialog();
//                                    e.printStackTrace();
//                                }
//                            }
//                        }, new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                       // CustomProgressBar.removeCustomProgressDialog();
//                        VolleyLog.e("Error: ", error.getMessage());
//                    }
//                }){
//                    @Override
//                    public Map<String, String> getHeaders() {
//                        Map<String, String> params = new HashMap<String, String>();
//                        params.put("X-Requested-With", "com.app.commuter");
//                        // params.put("content-type", "application/json");
//                        return params;
//                    }
//                    @Override
//                    public String getBodyContentType() {
//                        return "application/json; charset=utf-8";
//                    }
//              };
           }
      });

//        submit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                HttpResponse<String> response = Unirest.post("http://103.197.122.135:1111/mantis/api/rest/issues")
//                        .header("content-type", "application/json")
//                        .header("authorization", "Q96tP0VmK7dT0NnzwLIMzO_dtCvT8EH0")
//                        .body("{\n    \"summary\": \"Sample REST issue by Prabhat Pandit\",\n    \"description\": \"Description for sample REST issue with Priority in Project V2 .\",\n    \"additional_information\": \"More info about the issue\",\n    \"project\": {\n        \"id\": 1,\n        \"name\": \"HSRP_v2\"\n    },\n    \"category\": {\n        \"id\": 5,\n        \"name\": \"bugtracker\"\n    },\n    \"handler\": {\n        \"name\": \"shail\"\n    },\n    \"view_state\": {\n        \"id\": 10,\n        \"name\": \"public\"\n    },\n    \"priority\": {\n        \"name\": \"normal\"\n    },\n    \"severity\": {\n        \"name\": \"trivial\"\n    },\n    \"reproducibility\": {\n        \"name\": \"always\"\n    },\n    \"sticky\": false,\n    \"tags\": [\n        {\n            \"name\": \"mantishub\"\n        }\n    ]\n}")
//                        .asString();
//            }
//        });
//       submit.setOnClickListener(new View.OnClickListener() {
//
//
//           @Override
//           public void onClick(View v) {
//               Summary summary = new Summary();
//               Project project = new Project();
//               Category category = new Category();
//               summary.setSummary("Test Summary");
//               summary.setDescription("Test Discription");
//                project.setName("Test project Name");
//                category.setName("Test Category");
//
//
//                   Map<String, String> MyData = new HashMap<String, String>();
//                   MyData.put("summary", summary.getSummary());
//                   MyData.put("description", summary.getDescription());
//                   MyData.put("name", category.getName());
//                   MyData.put("name", project.getName());
//
//
//
//               RequestQueue MyRequestQueue = Volley.newRequestQueue(Rtlim.this);
//               String url = "http://103.197.122.135:1111/mantis/api/rest/issues/"; // <----enter your post url here
//              // StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
//               JsonObjectRequest request_json = new JsonObjectRequest(url, new JSONObject(MyData),
//                       new Response.Listener<JSONObject>() {
//                           @Override
//                           public void onResponse(JSONObject response) {
//
//                           }
//
//
//               }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
//                   @Override
//                   public void onErrorResponse(VolleyError error) {
//                       //This code is executed if there is an error.
//                   }
//               }) {
//
//
//                   @Override
//                   public Map<String, String> getHeaders() throws AuthFailureError {
//                       Map<String,String> MyData = new HashMap<String, String>();
//                       MyData.put("Content-Type","application/x-www-form-urlencoded");
//                       MyData.put("Authorization","Q96tP0VmK7dT0NnzwLIMzO_dtCvT8EH0");
//                       return MyData;
//                   }
//               };
//
//
//               MyRequestQueue.add(request_json);
//
//
//
//           }
//       });


    }


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        choosePhotoHelper.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        choosePhotoHelper.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        choosePhotoHelper.onSaveInstanceState(outState);
    }



}
