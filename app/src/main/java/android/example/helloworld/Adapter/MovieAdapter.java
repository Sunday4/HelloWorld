package android.example.helloworld.Adapter;

import android.content.Context;
import android.content.Intent;
import android.example.helloworld.DetailActivity;
import android.example.helloworld.Model.ResultsItem;
import android.example.helloworld.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;


import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
    private ArrayList<ResultsItem> resultsItems;
    private Context context;



    public MovieAdapter(ArrayList<ResultsItem> resultsItems, Context context) {
        this.resultsItems = resultsItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.tvTitle.setText(resultsItems.get(position).getTitle());
        holder.tvRealeseDate.setText(resultsItems.get(position).getReleaseDate());
        String url = "https://image.tmdb.org/t/p/w200" + resultsItems.get(position).getPosterPath();
        Glide.with(context)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .error(R.drawable.ic_launcher_background)
                .into(holder.imgPhoto);
        holder.cvFilm.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("title", resultsItems.get(position).getTitle());
                intent.putExtra("tv_name", resultsItems.get(position).getOriginalTitle());
                intent.putExtra("sinopsis", resultsItems.get(position).getOverview());
                intent.putExtra("rilis", resultsItems.get(position).getReleaseDate());
                intent.putExtra("popularity", resultsItems.get(position).getPopularity().toString());
                intent.putExtra("gambar", resultsItems.get(position).getBackdropPath());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return resultsItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cvFilm;
        private TextView tvTitle;
        private TextView tvRealeseDate;
        private ImageView imgPhoto;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cvFilm = itemView.findViewById(R.id.cvFilm);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvRealeseDate = itemView.findViewById(R.id.tvRealeseDate);
            imgPhoto = itemView.findViewById(R.id.imgPhoto);
            cvFilm = itemView.findViewById(R.id.cvFilm);
        }
    }
}
