package com.dzniox.travel_mate.ui.adapters;

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
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.dzniox.travel_mate.R;
import com.dzniox.travel_mate.network.APIClient;
import com.dzniox.travel_mate.network.APIInterface;
import com.dzniox.travel_mate.network.MessageClient;
import com.dzniox.travel_mate.network.MessageInterface;
import com.dzniox.travel_mate.sharedPref.PrefUtils;
import com.dzniox.travel_mate.ui.activities.MainActivity;
import com.dzniox.travel_mate.ui.fragments.HomeFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BusAdapter extends RecyclerView.Adapter<BusAdapter.ViewHolder> {

    LayoutInflater inflater;
    private Context context;
    private JSONArray data;
    private LayoutInflater layoutInflater;
    APIInterface apiInterface;
    MessageInterface messageInterface;

    PrefUtils prefrences;

    public BusAdapter(Context context, JSONArray data) {
        this.context = context;
        this.data = data;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = layoutInflater.inflate(R.layout.hotel_item, viewGroup, false);
        apiInterface = APIClient.getClient().create(APIInterface.class);
        messageInterface = MessageClient.getClient().create(MessageInterface.class);
        prefrences = PrefUtils.getInstance(context);

        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        try {
            JSONObject currentObject = data.getJSONObject(position);

            holder.name.setText(currentObject.getString("name"));
            holder.desc.setText(currentObject.getString("description"));
            holder.link.setVisibility(View.GONE);
            Glide.with(context)
                    .load(currentObject.getString("image"))
                    .into(holder.picture);

            holder.date.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final Calendar c = Calendar.getInstance();
                    int mYear = c.get(Calendar.YEAR); // current year
                    int mMonth = c.get(Calendar.MONTH); // current month
                    int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                    // date picker dialog
                    DatePickerDialog datePickerDialog = new DatePickerDialog(context,
                            new DatePickerDialog.OnDateSetListener() {
                                @Override
                                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                    holder.date.setText(dayOfMonth + "/"+ (monthOfYear + 1) + "/" + year);
                                }
                            }, mYear, mMonth, mDay);
                    datePickerDialog.show();
                }
            });

            holder.submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (holder.date.getText().toString().trim().equals("Select date") ||
                            holder.peoples.getText().toString().trim().equals("")){
                        Toast.makeText(context, "Date and No of peoples required", Toast.LENGTH_SHORT).show();
                    }else {
                        BookNow(holder.date.getText().toString().trim(),holder.peoples.getText().toString().trim(),currentObject,holder);
                    }
                }
            });
        }catch (Exception e){}
    }

    private void BookNow(String date, String peoples, JSONObject currentObject, ViewHolder holder) {
        Call<String> call = null;
        try {
            call = apiInterface.insertBooking(
                    "0","1", prefrences.getStringValue("id",""),
                    currentObject.getString("id"),peoples,date
            );
        } catch (JSONException e) {}
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.code() == 200){
                    Toast.makeText(context,"Booked",Toast.LENGTH_SHORT).show();
                    holder.date.setText("Select Date");
                    holder.peoples.setText("");
//                    SendMessage();
                    sendSms();
                }
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
            }
        });
    }

    private void SendMessage() {
        Call<String> call =  messageInterface.sendMessage(
                "amaldz",
                "P@ssw0rd",
                prefrences.getStringValue("phone",""),
                "Thankyou for Booking Bus with Travel Mate, Your booking is confirmed.",
                "TESTKK",
                "1",
                "1507161717524942102"
        );
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.code() == 200){
                    try {
                        JSONObject data = new JSONObject(response.body());
                        if (data.getString("status").equals("success")){
                            Toast.makeText(context,"Confirmation Message send",Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(context,"Confirmation Message not send",Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {}

                }
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
            }
        });
    }


    public String sendSms() {
        try {
            // Construct data
            String apiKey = "apikey=" + URLEncoder.encode("NmU3NjY4NDgzMDZhNGQ3YTc4NDg1MzMxNTQ3OTc2MzY=", "UTF-8");
            String message = "&message=" + URLEncoder.encode("This is your message", "UTF-8");
            String sender = "&sender=" + URLEncoder.encode("TXTLCL", "UTF-8");
            String numbers = "&numbers=" + URLEncoder.encode("919961498198", "UTF-8");

            // Send data
            String data = "https://api.textlocal.in/send/?" + apiKey + numbers + message + sender;
            URL url = new URL(data);
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);

            // Get the response
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            String sResult = "";
            while ((line = rd.readLine()) != null) {
                // Process line...
                sResult = sResult + line + " ";
            }
            rd.close();
            Toast.makeText(context,"Confirmation Message send" + sResult,Toast.LENGTH_SHORT).show();

            return sResult;
        } catch (Exception e) {
            Toast.makeText(context,"Confirmation Message Send Error" + e,Toast.LENGTH_SHORT).show();
            return e.toString();
        }
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

