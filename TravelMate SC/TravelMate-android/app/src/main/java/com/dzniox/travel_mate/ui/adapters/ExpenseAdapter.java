package com.dzniox.travel_mate.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
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

public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.ViewHolder> {

    LayoutInflater inflater;
    private Context context;
    private JSONArray data;
    private LayoutInflater layoutInflater;
    APIInterface apiInterface;
    PrefUtils prefrences;

    public ExpenseAdapter(Context context, JSONArray data) {
        this.context = context;
        this.data = data;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = layoutInflater.inflate(R.layout.expense_item, viewGroup, false);
        apiInterface = APIClient.getClient().create(APIInterface.class);
        prefrences = PrefUtils.getInstance(context);

        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        try {
            JSONObject currentObject = data.getJSONObject(position);
            holder.name.setText(currentObject.getString("name"));
            holder.amount.setText(currentObject.getString("amount") + " Rs");
            holder.desc.setText(currentObject.getString("description"));

        }catch (Exception e){}
    }

    @Override
    public int getItemCount() {
        return data.length();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView name,desc,amount;

        public ViewHolder(View itemView) {
            super(itemView);

            amount = itemView.findViewById(R.id.amount);
            name = itemView.findViewById(R.id.name);
            desc = itemView.findViewById(R.id.desc);

        }
    }
}

