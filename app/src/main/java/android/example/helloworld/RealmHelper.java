package android.example.helloworld;

import android.content.Context;

import android.example.helloworld.ModelMovie;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

public class RealmHelper {

    private Context mContext;
    private Realm realm;
    private RealmResults<ModelMovie> modelMovie;
//    private RealmResults<ModelTV> modelTV;

    public RealmHelper(Context mContext) {
        this.mContext = mContext;
        Realm.init(mContext);
        realm = Realm.getDefaultInstance();
    }

    public ArrayList<ModelMovie> showFavoriteMovie() {
        ArrayList<ModelMovie> data = new ArrayList<>();
        modelMovie = realm.where(ModelMovie.class).findAll();

        if (modelMovie.size() > 0) {
            for (int i = 0; i < modelMovie.size(); i++) {
                ModelMovie movie = new ModelMovie();
                movie.setId(modelMovie.get(i).getId());
                movie.setTitle(modelMovie.get(i).getTitle());
                movie.setVoteAverage(modelMovie.get(i).getVoteAverage());
                movie.setOverview(modelMovie.get(i).getOverview());
                movie.setReleaseDate(modelMovie.get(i).getReleaseDate());
                movie.setPosterPath(modelMovie.get(i).getPosterPath());
                movie.setBackdropPath(modelMovie.get(i).getBackdropPath());
                movie.setPopularity(modelMovie.get(i).getPopularity());
                data.add(movie);
            }
        }
        return data;
    }


    public void addFavoriteMovie(int Id, String Title, double VoteAverage, String Overview,
                                 String ReleaseDate, String PosterPath, String BackdropPath, String Popularity) {
        ModelMovie movie = new ModelMovie();
        movie.setId(Id);
        movie.setTitle(Title);
        movie.setVoteAverage(VoteAverage);
        movie.setOverview(Overview);
        movie.setReleaseDate(ReleaseDate);
        movie.setPosterPath(PosterPath);
        movie.setBackdropPath(BackdropPath);
        movie.setPopularity(Popularity);

        realm.beginTransaction();
        realm.copyToRealm(movie);
        realm.commitTransaction();

        //Toast.makeText(mContext, "Data berhasil ditambah", Toast.LENGTH_SHORT).show();
    }


    public void deleteFavoriteMovie(int id) {
        realm.beginTransaction();
        RealmResults<ModelMovie> modelMovie = realm.where(ModelMovie.class).findAll();
        modelMovie.deleteAllFromRealm();
        realm.commitTransaction();

        //Toast.makeText(mContext, "Data berhasil dihapus", Toast.LENGTH_SHORT).show();
    }


}
