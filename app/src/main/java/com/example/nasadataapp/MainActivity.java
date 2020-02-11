package com.example.nasadataapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    DataAdaper recyclerAdapter;
    private String jsonResponse;
    private List<MyPojo> listitem = new ArrayList<MyPojo>();
    ProgressDialog prgDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.mRecyclerview);

        prgDialog = new ProgressDialog(this);
        prgDialog.setCancelable(false);

        Getfiles();

        recyclerAdapter = new DataAdaper(getApplicationContext(),listitem);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(recyclerAdapter);



    }

    private void Getfiles() {
        prgDialog.setMessage("Please wait...");
        prgDialog.show();
        JsonArrayRequest req = new JsonArrayRequest("https://latestjsondata.000webhostapp.com/data.json",
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        prgDialog.hide();
                        // Log.d(TAG, response.toString());

                        try {
                            // pullToRefresh.setRefreshing(false);
                            // Parsing json array response
                            // loop through each json object
                            // swipeLayout.setRefreshing(false);
                            jsonResponse = "";
                            for (int i = 0; i < response.length(); i++) {

                                JSONObject jsonObject = (JSONObject) response
                                        .get(i);

                                String doc_Seq = jsonObject.optString("title");
                                String doUploadDate = jsonObject.optString("url");
                                String des_Cription = jsonObject.optString("explanation");


                                MyPojo item = new MyPojo();
                                item.setTitle(doc_Seq);
                                item.setUrl(doUploadDate);
                                item.setExplanation(des_Cription);

                                listitem.add(item);
                                recyclerAdapter.notifyDataSetChanged();




                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(),
                                    "Error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                        // adapter.notifyDataSetChanged();
                        // dismissProgressDialog();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // pullToRefresh.setRefreshing(false);
                //  VolleyLog.d(TAG, "Error: " + error.getMessage());
                //dismissProgressDialog();
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
        AGApplication.getInstance().addToRequestQueue(req);
    }

}