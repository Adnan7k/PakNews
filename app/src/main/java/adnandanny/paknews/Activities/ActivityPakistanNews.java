package adnandanny.paknews.Activities;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.List;

import adnandanny.paknews.Adapters.PakistanNewsAdapter;
import adnandanny.paknews.Constants.Constants;
import adnandanny.paknews.R;

public class ActivityPakistanNews extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private ProgressDialog progressDialog;
    List<Constants> myListItems;

    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pakistannews);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerLatestNews);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        myListItems = new ArrayList<>();
        progressDialog = new ProgressDialog(this);

        queue = Volley.newRequestQueue(this);

        getStringObject(Constants.BBC_PAKISTAN);
        progressDialog.setMessage("Fetching Results....");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();


    }

    private void getStringObject(String url) {

        StringRequest getstring = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                parsing(response.toString());

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ActivityPakistanNews.this, "Error in JSON", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();

            }
        });

        queue.add(getstring);

    }

    private void parsing(String html) {

        Document doc = Jsoup.parse(html);

        Element pakistannews = doc.getElementById("comp-recent-media");


        for (int i = 0; i < 10; i++) {
            Constants constants = new Constants();
            Element titles = pakistannews.getElementsByClass("title-link__title-text").get(i);
            Element description = pakistannews.getElementsByClass("eagle-item__summary").get(i);
            Element date = pakistannews.getElementsByClass("mini-info-list__item").get(i);
            Element pic = doc.getElementsByClass("responsive-image").get(i);
            Element links = doc.getElementsByClass("eagle-item__body").select("a[href]").get(i);

            constants.setTitlepakistan(titles.text());
            constants.setDescriptionpakistan(description.text());
            constants.setDatepakistan(date.text());
            constants.setPicspakistan(pic.tagName("img").select("div").attr("data-src"));
            constants.setPostlinkpakistan("http://www.bbc.com"+links.attr("href"));

            myListItems.add(constants);


            Log.d("Links" , "http://www.bbc.com"+links.attr("href"));


//             Log.d("Images Src" , testingimage.attr("src"));
//             Log.d("Images2 Src" , testingimage.text());
//             Log.d("Images3 Src" , String.valueOf(testingimage.getAllElements()));
//             Log.d("Images4 Src" , testingimage.toString());
//             Log.d("Images5 Src" , testingimage.attr("img[src]"));
//               Log.d("Images6 Src" , pic.tagName("img").select("div").attr("data-src"));

        }

        progressDialog.dismiss();
        adapter = new PakistanNewsAdapter(ActivityPakistanNews.this, myListItems);
        recyclerView.setAdapter(adapter);
    }
}
