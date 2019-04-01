package ca.bcit.comp7082.Assignment1;

import android.os.Bundle;
import android.widget.GridView;

import org.json.JSONArray;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

public class GetImageFromServer extends AppCompatActivity {

    //Web api url
    public static final String DATA_URL = "http://192.168.0.107/AndroidUploadImage/getImages.php";

    //Tag values to read from json
    public static final String TAG_IMAGE_URL = "url";
    public static final String TAG_NAME = "name";

    //GridView Object
    private GridView gridView;

    //ArrayList for Storing image urls and titles
    private ArrayList<String> images;
    private ArrayList<String> names;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = findViewById(R.id.gridView);

        images = new ArrayList<>();
        names = new ArrayList<>();

        //Calling the getData method
        getData();
    }

    private void getData(){
//        //Showing a progress dialog while our app fetches the data from url
//        final ProgressDialog loading = ProgressDialog.show(this, "Please wait...","Fetching data...",false,false);
//
//        //Creating a json array request to get the json from our api
//        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(DATA_URL,
//                new Response.Listener<JSONArray>() {
//                    @Override
//                    public void onResponse(JSONArray response) {
//                        //Dismissing the progressdialog on response
//                        loading.dismiss();
//
//                        //Displaying our grid
//                        showGrid(response);
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//
//                    }
//                }
//        );

//        //Creating a request queue
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//        //Adding our request to the queue
//        requestQueue.add(jsonArrayRequest);
        JSONArray js =  null;
        showGrid();
    }


    private void showGrid(){
//        //Looping through all the elements of json array
//        for(int i = 0; i<jsonArray.length(); i++){
//            //Creating a json object of the current index
//            JSONObject obj = null;
//            try {
//                //getting json object from current index
//                obj = jsonArray.getJSONObject(i);
//
//                //getting image url and title from json object
//                images.add(obj.getString(TAG_IMAGE_URL));
//                names.add(obj.getString(TAG_NAME));
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
        images.add("http://192.168.0.107/AndroidUploadImage/uploads/16.jpg");
        names.add("Roger");
        //Creating GridViewAdapter Object
        GridViewAdapter gridViewAdapter = new GridViewAdapter(this,images,names);

        //Adding adapter to gridview
        gridView.setAdapter(gridViewAdapter);
    }

}