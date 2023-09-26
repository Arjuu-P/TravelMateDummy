package com.dzniox.travel_mate.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.dzniox.travel_mate.R;
import com.dzniox.travel_mate.network.APIClient;
import com.dzniox.travel_mate.network.APIInterface;
import com.dzniox.travel_mate.sharedPref.PrefUtils;
import com.dzniox.travel_mate.ui.activities.MainActivity;
import com.dzniox.travel_mate.ui.activities.SplashScreenActivity;
import com.dzniox.travel_mate.ui.activities.WebViewActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SpotAdapter extends RecyclerView.Adapter<SpotAdapter.ViewHolder> {

    LayoutInflater inflater;
    private Context context;
    private JSONArray data;
    private LayoutInflater layoutInflater;
    APIInterface apiInterface;
    PrefUtils prefrences;

    public SpotAdapter(Context context, JSONArray data) {
        this.context = context;
        this.data = data;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = layoutInflater.inflate(R.layout.spot_item, viewGroup, false);
        apiInterface = APIClient.getClient().create(APIInterface.class);
        prefrences = PrefUtils.getInstance(context);

        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        try {
            JSONObject currentObject = data.getJSONObject(position);

            final String wishllistId = "";

            GetWishList(currentObject,prefrences.getStringValue("id",""),holder);
            holder.name.setText(currentObject.getString("name"));
            holder.location.setText(currentObject.getString("location_name"));
            holder.location_desc.setText(currentObject.getString("location_desc"));

            Glide.with(context)
                    .load(currentObject.getString("image"))
                    .into(holder.picture);

            holder.map.setOnClickListener(view -> {
                try {
                    Intent intent = new Intent(context, WebViewActivity.class);
                    intent.putExtra("title",currentObject.getString("name"));
                    intent.putExtra("link",currentObject.getString("map_link"));
                    context.startActivity(intent);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            });

            holder.petrol.setOnClickListener(view -> {
                try {
                    Intent intent = new Intent(context, WebViewActivity.class);
                    intent.putExtra("title","Nearby Petrol Pump");
                    intent.putExtra("link",currentObject.getString("pump_location"));
                    context.startActivity(intent);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            });

            holder.atm.setOnClickListener(view -> {
                try {
                    Intent intent = new Intent(context, WebViewActivity.class);
                    intent.putExtra("title","Nearby ATM");
                    intent.putExtra("link",currentObject.getString("atm_link"));
                    context.startActivity(intent);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            });


        }catch (Exception e){}
    }

    private void AddWishlist(String user_id, String spot_id, ViewHolder holder) {
        Call<String> call = apiInterface.addWishlist(user_id,spot_id);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.code() == 200){
                    try {
                        JSONObject data = new JSONObject(response.body());
                        if (data.getBoolean("data")){
                            holder.like.setImageDrawable(context.getDrawable(R.drawable.ic_like_fill));
                        }
                    }catch (Exception e){}
                }else {}
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {}
        });
    }

    private void RemoveWishlist(String wishlist_id, ViewHolder holder) {
        Call<String> call = apiInterface.removeWishlist(wishlist_id);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.code() == 200){
                    try {
                        JSONObject data = new JSONObject(response.body());
                        if (data.getBoolean("data")){
                            holder.like.setImageDrawable(context.getDrawable(R.drawable.ic_like_unfill));
                        }
                    }catch (Exception e){}
                }else {}
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {}
        });
    }

    private void GetWishList(JSONObject currentObject, String id, ViewHolder holder) throws JSONException {
        Call<String> call = apiInterface.getWishlist(id,currentObject.getString("id"));
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.code() == 200){
                    try {
                        JSONObject data = new JSONObject(response.body());
                        if (data.getJSONArray("data").length() > 0){
                            holder.like_update.setOnClickListener(view -> {
                                try {
                                    RemoveWishlist(data.getJSONArray("data").getJSONObject(0).getString("id"),holder);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            });
                            holder.like.setImageDrawable(context.getDrawable(R.drawable.ic_like_fill));
                        }else {
                            holder.like_update.setOnClickListener(view -> {
                                try {
                                    AddWishlist(prefrences.getStringValue("id",""),currentObject.getString("id"),holder);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            });
                            holder.like.setImageDrawable(context.getDrawable(R.drawable.ic_like_unfill));
                        }
                    }catch (Exception e){
                        holder.like_update.setOnClickListener(view -> {
                            try {
                                AddWishlist(prefrences.getStringValue("id",""),currentObject.getString("id"),holder);
                            } catch (JSONException e1) {
                                e.printStackTrace();
                            }
                        });
                    }
                }else {}
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {}
        });
    }

    @Override
    public int getItemCount() {
        return data.length();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView name,location_desc,location;
        private ImageView picture,like;
        private LinearLayout map,petrol,atm;
        private LinearLayout like_update;

        public ViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            picture = itemView.findViewById(R.id.picture);
            location_desc = itemView.findViewById(R.id.location_desc);
            location = itemView.findViewById(R.id.location);
            map = itemView.findViewById(R.id.map);
            petrol = itemView.findViewById(R.id.petrol);
            atm = itemView.findViewById(R.id.atm);
            like = itemView.findViewById(R.id.like);
            like_update = itemView.findViewById(R.id.like_update);

        }
    }
}

