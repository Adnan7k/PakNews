package adnandanny.paknews.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import adnandanny.paknews.Constants.Constants;
import adnandanny.paknews.Activities.DetailedPakistanNews;
import adnandanny.paknews.R;

/**
 * Created by Adnan Danny on 8/28/2017.
 */

public class PakistanNewsAdapter extends RecyclerView.Adapter<PakistanNewsAdapter.ViewHolder> {

    private Context context;
    private List<Constants> myListitem;


    public PakistanNewsAdapter(Context context, List<Constants> myListitem) {
        this.context = context;
        this.myListitem = myListitem;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_pakistannews, parent, false);

        return new ViewHolder(v, context);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Constants constants = myListitem.get(position);

        holder.titlePakistan.setText(constants.getTitlepakistan());
        holder.descriptionPakistan.setText(constants.getDescriptionpakistan());
        holder.datePakistan.setText(constants.getDatepakistan());
        Picasso.with(context).load(constants.getPicspakistan()).into(holder.picPakistan);

    }

    @Override
    public int getItemCount() {
        return myListitem.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView picPakistan;
        private TextView titlePakistan;
        private TextView descriptionPakistan;
        private TextView datePakistan;

        public ViewHolder(View view, Context ctx) {
            super(view);

            context = ctx;
            picPakistan = (ImageView) view.findViewById(R.id.picturePakistan);
            titlePakistan = (TextView) view.findViewById(R.id.titlePakistan);
            descriptionPakistan = (TextView) view.findViewById(R.id.descriptionPakistan);
            datePakistan = (TextView) view.findViewById(R.id.datePakistan);


            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Constants gettingposition = myListitem.get(getAdapterPosition());
                    Intent intent = new Intent(context, DetailedPakistanNews.class);
                    intent.putExtra("title", gettingposition.getTitlepakistan());
                    intent.putExtra("link", gettingposition.getPostlinkpakistan());
                    intent.putExtra("image", gettingposition.getPicspakistan());
                    context.startActivity(intent);
                    Toast.makeText(context , gettingposition.getPostlinkpakistan(), Toast.LENGTH_SHORT).show();

                }
            });

        }
    }
}
