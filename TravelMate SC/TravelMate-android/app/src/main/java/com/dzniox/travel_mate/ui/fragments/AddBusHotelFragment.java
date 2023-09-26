package com.dzniox.travel_mate.ui.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dzniox.travel_mate.R;
import com.dzniox.travel_mate.network.APIClient;
import com.dzniox.travel_mate.network.APIInterface;
import com.dzniox.travel_mate.sharedPref.PrefUtils;

import org.json.JSONException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddBusHotelFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddBusHotelFragment extends Fragment {

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

    private String Title;

    private TextView title;
    private EditText name,desc,image,location;
    private Button add_btn;

    public AddBusHotelFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddBusHotelFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddBusHotelFragment newInstance(String param1, String param2) {
        AddBusHotelFragment fragment = new AddBusHotelFragment();
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
        rootView = inflater.inflate(R.layout.fragment_add_bus_hotel, container, false);
        context = getContext();
        apiInterface = APIClient.getClient().create(APIInterface.class);
        prefrences = PrefUtils.getInstance(context);

        title = rootView.findViewById(R.id.title);
        name = rootView.findViewById(R.id.name);
        desc = rootView.findViewById(R.id.desc);
        image = rootView.findViewById(R.id.image);
        location = rootView.findViewById(R.id.location);
        add_btn = rootView.findViewById(R.id.add_btn);


        Bundle bundle = getArguments();
        Title = bundle.getString("title");

        title.setText(Title);

        if (Title.equals("Add Bus")){
            location.setVisibility(View.GONE);
        }else {
            location.setVisibility(View.VISIBLE);
        }

        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Title.equals("Add Bus")){
                    if (name.getText().toString().trim().equals("") ||
                            name.getText().toString().trim().equals("") ||
                            desc.getText().toString().trim().equals("") ||
                            image.getText().toString().trim().equals("")){
                        Toast.makeText(context, "Check all the fields", Toast.LENGTH_SHORT).show();
                    }else {
                        AddBus();
                    }

                }else {
                    if (name.getText().toString().trim().equals("") ||
                            name.getText().toString().trim().equals("") ||
                            desc.getText().toString().trim().equals("") ||
                            image.getText().toString().trim().equals("") ||
                            location.getText().toString().trim().equals("")){
                        Toast.makeText(context, "Check all the fields", Toast.LENGTH_SHORT).show();
                    }else {
                        AddHotel();
                    }
                }
            }
        });

        return rootView;
    }

    private void AddHotel() {
        Call<String> call = null;
        try {
            call = apiInterface.addHotel(name.getText().toString().trim(),desc.getText().toString().trim(),
                    image.getText().toString().trim(),location.getText().toString().trim());
        } catch (Exception e) {}
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.code() == 200){
                    Toast.makeText(context,"Hotel Added",Toast.LENGTH_SHORT).show();
                    BookingFragment bookingFragment = new BookingFragment();
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, bookingFragment, "findThisFragment")
                            .addToBackStack(null)
                            .commit();
                }
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
            }
        });
    }

    private void AddBus() {
        Call<String> call = null;
        try {
            call = apiInterface.addBus(name.getText().toString().trim(),desc.getText().toString().trim(),
                    image.getText().toString().trim());
        } catch (Exception e) {}
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.code() == 200){
                    Toast.makeText(context,"Bus Added",Toast.LENGTH_SHORT).show();
                    BookingFragment bookingFragment = new BookingFragment();
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, bookingFragment, "findThisFragment")
                            .addToBackStack(null)
                            .commit();
                }
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
            }
        });
    }
}