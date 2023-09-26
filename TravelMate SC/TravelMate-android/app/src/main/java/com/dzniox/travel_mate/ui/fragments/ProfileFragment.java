package com.dzniox.travel_mate.ui.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dzniox.travel_mate.R;
import com.dzniox.travel_mate.network.APIClient;
import com.dzniox.travel_mate.network.APIInterface;
import com.dzniox.travel_mate.sharedPref.PrefUtils;
import com.dzniox.travel_mate.ui.adapters.BookingAdapter;
import com.dzniox.travel_mate.ui.adapters.ExpenseAdapter;
import com.dzniox.travel_mate.ui.adapters.SpotAdapter;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private TextView Name,Email,no_data,Phone,liked_spot,my_bookings,expences,mark_exp;

    APIInterface apiInterface;
    private Context context;
    private View rootView;
    private RecyclerView rv_spot;

    PrefUtils prefrences;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */

    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_profile, container, false);
        context = getContext();
        prefrences = PrefUtils.getInstance(context);
        apiInterface = APIClient.getClient().create(APIInterface.class);

        Name = rootView.findViewById(R.id.name);
        Email = rootView.findViewById(R.id.email);
        Phone = rootView.findViewById(R.id.phone);
        rv_spot = rootView.findViewById(R.id.rv_spot);
        liked_spot = rootView.findViewById(R.id.liked_spot);
        my_bookings = rootView.findViewById(R.id.my_bookings);
        no_data = rootView.findViewById(R.id.no_data);
        expences = rootView.findViewById(R.id.expences);
        mark_exp = rootView.findViewById(R.id.mark_exp);

        mark_exp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddExpenseFragment addExpenseFragment = new AddExpenseFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, addExpenseFragment, "findThisFragment")
                        .addToBackStack(null)
                        .commit();
            }
        });

        getMyBookings();
        liked_spot.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                getLikedSpotList();
                my_bookings.setTextColor(context.getColor(R.color.text_color));
                liked_spot.setTextColor(context.getColor(R.color.app_color));
                expences.setTextColor(context.getColor(R.color.text_color));
            }
        });
        my_bookings.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NewApi")
            @Override
            public void onClick(View view) {
                getMyBookings();
                expences.setTextColor(context.getColor(R.color.text_color));
                my_bookings.setTextColor(context.getColor(R.color.app_color));
                liked_spot.setTextColor(context.getColor(R.color.text_color));
            }
        });
        expences.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NewApi")
            @Override
            public void onClick(View view) {
                getMyExpences();
                expences.setTextColor(context.getColor(R.color.app_color));
                my_bookings.setTextColor(context.getColor(R.color.text_color));
                liked_spot.setTextColor(context.getColor(R.color.text_color));
            }
        });

        Name.setText("Name : " + prefrences.getStringValue("name",""));
        Phone.setText("Phone : " + prefrences.getStringValue("phone",""));
        Email.setText("Email : " + prefrences.getStringValue("email",""));

        return rootView;
    }

    private void getMyExpences() {
        Call<String> call = apiInterface.getMyExpences(prefrences.getStringValue("id",""));
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.code() == 200){
                    rv_spot.setVisibility(View.VISIBLE);
                    try {
                        JSONObject data = new JSONObject(response.body());

                        if (data.getJSONArray("data").length() > 0){
                            no_data.setVisibility(View.GONE);
                            rv_spot.setVisibility(View.VISIBLE);
                            ExpenseAdapter adapter = new ExpenseAdapter(context, data.getJSONArray("data"));
                            rv_spot.setLayoutManager(new LinearLayoutManager(context));
                            rv_spot.setAdapter(adapter);
                        }else {
                            no_data.setVisibility(View.VISIBLE);
                            rv_spot.setVisibility(View.GONE);
                        }
                    }catch (Exception e){
                        no_data.setVisibility(View.VISIBLE);
                        rv_spot.setVisibility(View.GONE);
                    }

                } else {
                    no_data.setVisibility(View.VISIBLE);
                    rv_spot.setVisibility(View.GONE);
                }
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                no_data.setVisibility(View.VISIBLE);
                rv_spot.setVisibility(View.GONE);
            }
        });
    }

    private void getMyBookings() {
        Call<String> call = apiInterface.getMyBookings(prefrences.getStringValue("id",""));
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.code() == 200){
                    rv_spot.setVisibility(View.VISIBLE);

                    try {
                        JSONObject data = new JSONObject(response.body());

                        if (data.getJSONArray("data").length() > 0){
                            no_data.setVisibility(View.GONE);
                            rv_spot.setVisibility(View.VISIBLE);
                            BookingAdapter adapter = new BookingAdapter(context, data.getJSONArray("data"));
                            rv_spot.setLayoutManager(new LinearLayoutManager(context));
                            rv_spot.setAdapter(adapter);
                        }else {
                            no_data.setVisibility(View.VISIBLE);
                            rv_spot.setVisibility(View.GONE);
                        }
                    }catch (Exception e){
                        no_data.setVisibility(View.VISIBLE);
                        rv_spot.setVisibility(View.GONE);
                    }

                } else {
                    no_data.setVisibility(View.VISIBLE);
                    rv_spot.setVisibility(View.GONE);
                }
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                no_data.setVisibility(View.VISIBLE);
                rv_spot.setVisibility(View.GONE);
            }
        });
    }

    private void getLikedSpotList() {
        try {
            Call<String> call = apiInterface.getLikedSpot(prefrences.getStringValue("id",""));
            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    if (response.code() == 200){
                        rv_spot.setVisibility(View.VISIBLE);
                        try {
                            JSONObject data = new JSONObject(response.body());
                            if (data.getJSONArray("data").length() > 0){
                                no_data.setVisibility(View.GONE);
                                rv_spot.setVisibility(View.VISIBLE);
                                SpotAdapter adapter = new SpotAdapter(context, data.getJSONArray("data"));
                                rv_spot.setLayoutManager(new LinearLayoutManager(context));
                                rv_spot.setAdapter(adapter);
                            }
                        }catch (Exception e){
                            no_data.setVisibility(View.VISIBLE);
                            rv_spot.setVisibility(View.GONE);
                        }
                    }else {
                        no_data.setVisibility(View.VISIBLE);
                        rv_spot.setVisibility(View.GONE);
                    }
                }
                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    no_data.setVisibility(View.VISIBLE);
                    rv_spot.setVisibility(View.GONE);
                }
            });
        }catch (Exception e){
            rv_spot.setVisibility(View.GONE);
            Toast.makeText(requireContext(),e.toString(),Toast.LENGTH_SHORT).show();
        }
    }
}