package com.dzniox.travel_mate.ui.adapters;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.dzniox.travel_mate.R;
import com.dzniox.travel_mate.network.APIClient;
import com.dzniox.travel_mate.network.APIInterface;
import com.dzniox.travel_mate.sharedPref.PrefUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.ViewHolder> {

    LayoutInflater inflater;
    private Context context;
    private JSONArray data;
    private LayoutInflater layoutInflater;
    APIInterface apiInterface;
    PrefUtils prefrences;

    public BookingAdapter(Context context, JSONArray data) {
        this.context = context;
        this.data = data;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = layoutInflater.inflate(R.layout.hotel_item, viewGroup, false);
        apiInterface = APIClient.getClient().create(APIInterface.class);
        prefrences = PrefUtils.getInstance(context);

        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        try {
            JSONObject currentObject = data.getJSONObject(position);


            if (currentObject.getString("hotel_booking").equals("0")){
                GetHotel(currentObject.getString("booking_id"),holder);
                holder.link.setVisibility(View.VISIBLE);
            }else {
                GetBus(currentObject.getString("booking_id"),holder);
                holder.link.setVisibility(View.GONE);
            }
            holder.submit.setText("Cancel");
            holder.peoples.setEnabled(false);
            holder.peoples.setText(currentObject.getString("day") + " Peoples");
            holder.date.setText(currentObject.getString("from_date"));

            holder.submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        CancelBooking(position,currentObject.getString("id"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });

        }catch (Exception e){}
    }

    private void CancelBooking(int position, String id) {
        Call<String> call = apiInterface.deleteBooking(id);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                try {
                    JSONObject datal = new JSONObject(response.body());
                    data.remove(position);
                    notifyItemRemoved(position);

                } catch (JSONException e) {}

            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
            }
        });
    }

    private void GetHotel(String booking_id, ViewHolder holder) {
        Call<String> call = apiInterface.getHotelId(booking_id);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                try {
                    JSONObject data = new JSONObject(response.body());
                    holder.name.setText(data.getJSONArray("data").getJSONObject(0).getString("name"));
                    holder.desc.setText(data.getJSONArray("data").getJSONObject(0).getString("description"));
                    Glide.with(context)
                            .load(data.getJSONArray("data").getJSONObject(0).getString("image"))
                            .into(holder.picture);

                } catch (JSONException e) {}

            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
            }
        });
    }

    private void GetBus(String booking_id, ViewHolder holder) {
        Call<String> call = apiInterface.getBusId(booking_id);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                try {
                    JSONObject data = new JSONObject(response.body());
                    holder.name.setText(data.getJSONArray("data").getJSONObject(0).getString("name"));
                    holder.desc.setText(data.getJSONArray("data").getJSONObject(0).getString("description"));
                    Glide.with(context)
                            .load(data.getJSONArray("data").getJSONObject(0).getString("image"))
                            .into(holder.picture);

                } catch (JSONException e) {}

            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
            }
        });
    }


    @Override
    public int getItemCount() {
        return data.length();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView name,desc,link,date;
        private ImageView picture;
        private Button submit;
        private EditText peoples;

        public ViewHolder(View itemView) {
            super(itemView);

            picture = itemView.findViewById(R.id.picture);
            name = itemView.findViewById(R.id.name);
            desc = itemView.findViewById(R.id.desc);
            link = itemView.findViewById(R.id.link);
            submit = itemView.findViewById(R.id.submit);
            date = itemView.findViewById(R.id.date);
            peoples = itemView.findViewById(R.id.peoples);

        }
    }
}

