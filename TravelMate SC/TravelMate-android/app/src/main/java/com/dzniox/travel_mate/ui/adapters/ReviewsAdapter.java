package com.dzniox.travel_mate.ui.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.dzniox.travel_mate.R;
import com.dzniox.travel_mate.network.APIClient;
import com.dzniox.travel_mate.network.APIInterface;
import com.dzniox.travel_mate.sharedPref.PrefUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsAdapter.ViewHolder> {

    LayoutInflater inflater;
    private Context context;
    private JSONArray data;
    private LayoutInflater layoutInflater;
    APIInterface apiInterface;
    PrefUtils prefrences;

    public ReviewsAdapter(Context context, JSONArray data) {
        this.context = context;
        this.data = data;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_review, viewGroup, false);
        apiInterface = APIClient.getClient().create(APIInterface.class);
        prefrences = PrefUtils.getInstance(context);

        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        try {
            JSONObject currentObject = data.getJSONObject(position);

            if (prefrences.getStringValue("id","").equals(currentObject.getString("user_id"))){
                holder.delete.setVisibility(View.VISIBLE);
            }else  {}
            holder.title.setText(currentObject.getString("description"));
            holder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    data.remove(position);
                    notifyItemRemoved(position);
                    try {
                        DeleteReview(currentObject.getString("id"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });

        }catch (Exception e){}
    }

    private void DeleteReview(String id) {
        try {
            Call<String> call = apiInterface.deleteReview(id);
            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    if (response.code() == 200) {
                        try {
                            JSONObject data = new JSONObject(response.body());
                            if (data.getBoolean("data")){
                                Toast.makeText(context,"Review Deleted",Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(context,"Try Again",Toast.LENGTH_SHORT).show();
                            }
                        }catch (Exception e){

                        }
                    }
                }
                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    String a = t.toString();
                }
            });
        }catch (Exception e){
            Toast.makeText(context,e.toString(),Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public int getItemCount() {
        return data.length();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView title,delete;

        public ViewHolder(View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            delete = itemView.findViewById(R.id.delete);

        }
    }

}

