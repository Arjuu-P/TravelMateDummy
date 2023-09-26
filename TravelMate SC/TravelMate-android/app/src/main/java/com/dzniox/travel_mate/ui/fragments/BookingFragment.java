package com.dzniox.travel_mate.ui.fragments;

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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dzniox.travel_mate.R;
import com.dzniox.travel_mate.network.APIClient;
import com.dzniox.travel_mate.network.APIInterface;
import com.dzniox.travel_mate.sharedPref.PrefUtils;
import com.dzniox.travel_mate.ui.adapters.BusAdapter;
import com.dzniox.travel_mate.ui.adapters.HotelAdapter;
import com.dzniox.travel_mate.ui.adapters.SpotAdapter;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BookingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BookingFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Context context;
    private View rootView;

    APIInterface apiInterface;
    PrefUtils prefrences;
    private RecyclerView rv_data;

    private LinearLayout add_bus, add_hotel;

    private TextView bus_booking,hotel_booking;

    private String SELECTED_PAGE = "bus";

    public BookingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BookingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BookingFragment newInstance(String param1, String param2) {
        BookingFragment fragment = new BookingFragment();
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

        rootView = inflater.inflate(R.layout.fragment_booking, container, false);
        context = getContext();
        apiInterface = APIClient.getClient().create(APIInterface.class);
        prefrences = PrefUtils.getInstance(context);

        hotel_booking = rootView.findViewById(R.id.hotel_booking);
        bus_booking = rootView.findViewById(R.id.bus_booking);
        rv_data = rootView.findViewById(R.id.rv_data);
        add_bus = rootView.findViewById(R.id.add_bus);
        add_hotel = rootView.findViewById(R.id.add_hotel);

        if (prefrences.getStringValue("id","").equals("1")){
            add_bus.setVisibility(View.VISIBLE);
            add_hotel.setVisibility(View.VISIBLE);
        }else {
            add_bus.setVisibility(View.GONE);
            add_hotel.setVisibility(View.GONE);
        }

        add_bus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddBusHotelFragment addBusHotelFragment = new AddBusHotelFragment();
                Bundle bundle = new Bundle();
                bundle.putString("title","Add Bus");
                addBusHotelFragment.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, addBusHotelFragment, "findThisFragment")
                        .addToBackStack(null)
                        .commit();
            }
        });
        add_hotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddBusHotelFragment addBusHotelFragment = new AddBusHotelFragment();
                Bundle bundle = new Bundle();
                bundle.putString("title","Add Hotel");
                addBusHotelFragment.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, addBusHotelFragment, "findThisFragment")
                        .addToBackStack(null)
                        .commit();
            }
        });

        GetBus();
        hotel_booking.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                SELECTED_PAGE = "hotel";
                hotel_booking.setTextColor(context.getColor(R.color.app_color));
                bus_booking.setTextColor(context.getColor(R.color.text_color));
                GetHotels();
            }
        });

        bus_booking.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                SELECTED_PAGE = "bus";
                hotel_booking.setTextColor(context.getColor(R.color.text_color));
                bus_booking.setTextColor(context.getColor(R.color.app_color));
                GetBus();
            }
        });

        return rootView;
    }

    private void GetBus() {
        Call<String> call = apiInterface.getBus();
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.code() == 200){
                    try {
                        JSONObject data = new JSONObject(response.body());
                        if (data.getJSONArray("data").length() > 0){
                            BusAdapter adapter = new BusAdapter(context, data.getJSONArray("data"));
                            rv_data.setLayoutManager(new LinearLayoutManager(context));
                            rv_data.setAdapter(adapter);
                        }
                    }catch (Exception e){
                        Toast.makeText(context,"No Data Found",Toast.LENGTH_SHORT).show();
                    }
                }
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {}
        });
    }

    private void GetHotels() {
        Call<String> call = apiInterface.getHotel();
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.code() == 200){
                    try {
                        JSONObject data = new JSONObject(response.body());
                        if (data.getJSONArray("data").length() > 0){
                            HotelAdapter adapter = new HotelAdapter(context, data.getJSONArray("data"));
                            rv_data.setLayoutManager(new LinearLayoutManager(context));
                            rv_data.setAdapter(adapter);
                        }
                    }catch (Exception e){
                        Toast.makeText(context,"No Data Found",Toast.LENGTH_SHORT).show();
                    }
                }
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {}
        });
    }
}