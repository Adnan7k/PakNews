package adnandanny.paknews.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import adnandanny.paknews.Adapters.PakistanNewsAdapter;
import adnandanny.paknews.Constants.Constants;
import adnandanny.paknews.R;

public class DetailedPakistanNews extends AppCompatActivity {
    
    private RequestQueue queue;
    private ImageView storyImage;
    private TextView storyTitle;
    private TextView storyDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_pakistan_news);

        storyImage = (ImageView) findViewById(R.id.pictureStory);
        storyTitle = (TextView) findViewById(R.id.storytitleID);
        storyDetails = (TextView) findViewById(R.id.storyDeatils);

        queue = Volley.newRequestQueue(this);
        
        Bundle bundle = getIntent().getExtras();
        
        if (bundle != null) {
            Constants constants = new Constants();
            storyTitle.setText(bundle.getString("title"));
            //Picasso.with(this).load(bundle.getString("image")).into(storyImage);
            getStringObject(bundle.getString("link"));

            //Toast.makeText(this, constants.getPostlinkpakistan(), Toast.LENGTH_SHORT).show();
            
        }
    }

    private void getStringObject(String url) {

        StringRequest getstring = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Document doc = Jsoup.parse(response);

                Elements storydetails = doc.getElementsByClass("story-body__inner");
                Element storyPicWeb = doc.getElementsByClass("js-image-replace").tagName("img").get(0);

                storyDetails.setText(storydetails.text());
                Log.d("Detail", storydetails.text());
                Picasso.with(DetailedPakistanNews.this).load(storyPicWeb.attr("src")).into(storyImage);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(DetailedPakistanNews.this, "Slow Internet Connection, Try Again.", Toast.LENGTH_SHORT).show();
                

            }
        });

        queue.add(getstring);

    }

}
