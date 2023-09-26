package com.dzniox.travel_mate.ui.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.dzniox.travel_mate.R;
import com.dzniox.travel_mate.network.APIClient;
import com.dzniox.travel_mate.network.APIInterface;
import com.dzniox.travel_mate.sharedPref.PrefUtils;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddSpotFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddSpotFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    APIInterface apiInterface;
    private Context context;
    private View rootView;
    PrefUtils prefrences;

    private EditText name,location,location_desc,image,map_url,petrol,atm;
    private Spinner sp_district,sp_type;
    private Button add_btn;

    private ArrayList<String> DISTRICTS = new ArrayList<>();
    private ArrayList<String> TYPE = new ArrayList<>();

    private int SELECTED_DISTRICT = 0;
    private int SELECTED_TYPE = 1;

    public AddSpotFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddSpotFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddSpotFragment newInstance(String param1, String param2) {
        AddSpotFragment fragment = new AddSpotFragment();
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
        rootView = inflater.inflate(R.layout.fragment_add_spot, container, false);
        context = getContext();
        prefrences = PrefUtils.getInstance(context);
        apiInterface = APIClient.getClient().create(APIInterface.class);

        add_btn = rootView.findViewById(R.id.add_btn);

        sp_district = rootView.findViewById(R.id.sp_district);
        sp_type = rootView.findViewById(R.id.sp_type);

        name = rootView.findViewById(R.id.name);
        location = rootView.findViewById(R.id.location);
        location_desc = rootView.findViewById(R.id.location_desc);
        image = rootView.findViewById(R.id.image);
        map_url = rootView.findViewById(R.id.map_url);
        petrol = rootView.findViewById(R.id.petrol);
        atm = rootView.findViewById(R.id.atm);

        setState();
        setType();

        sp_district.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                try {
                    SELECTED_DISTRICT = position;
                }catch (Exception e){}
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {}
        });

        sp_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                try {
                    SELECTED_TYPE = position+1;
                }catch (Exception e){}
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {}
        });

        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (name.getText().toString().trim().equals("") ||
                        location.getText().toString().trim().equals("") ||
                        location_desc.getText().toString().trim().equals("") ||
                        image.getText().toString().trim().equals("") ||
                        map_url.getText().toString().trim().equals("") ||
                        petrol.getText().toString().trim().equals("") ||
                        atm.getText().toString().trim().equals("")) {
                    Toast.makeText(context,"Check all fields",Toast.LENGTH_SHORT).show();
                } else {
                    AddSpot(name.getText().toString().trim(),
                            location.getText().toString().trim(),
                            location_desc.getText().toString().trim(),
                            image.getText().toString().trim(),
                            map_url.getText().toString().trim(),
                            petrol.getText().toString().trim(),
                            atm.getText().toString().trim());
                }
            }
        });

        return rootView;
    }

    private void AddSpot(String name, String location, String location_desc, String image,
                         String map_url, String petrol, String atm) {

        Call<String> call = apiInterface.addSpot(name, location, location_desc, image, map_url,
                petrol, atm,SELECTED_DISTRICT+"",SELECTED_TYPE+"");
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.code() == 200){
                    Toast.makeText(context,"Spot Added.",Toast.LENGTH_SHORT).show();
                    HomeFragment homeFragment = new HomeFragment();
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, homeFragment, "findThisFragment")
                            .addToBackStack(null)
                            .commit();
                }
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
            }
        });
    }

    private void setState() {
        try {
            DISTRICTS.add("Thrissur");
            DISTRICTS.add("Wayanad");
            DISTRICTS.add("Palakad");
            DISTRICTS.add("Pathanamthitta");
            DISTRICTS.add("Alappuzha");
            DISTRICTS.add("Ernamkulam");
            DISTRICTS.add("Idukki");
            DISTRICTS.add("Malappuram");
            DISTRICTS.add("Kannur");
            DISTRICTS.add("Kasaragod");
            DISTRICTS.add("Kollam");
            DISTRICTS.add("Kottayam");
            DISTRICTS.add("Kozhikode");
            DISTRICTS.add("Thiruvananthapuram");

            ArrayAdapter adapter = new ArrayAdapter(context, android.R.layout.simple_spinner_item, DISTRICTS);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            sp_district.setAdapter(adapter);
        }catch (Exception e){
            Toast.makeText(context,e.toString(),Toast.LENGTH_SHORT).show();
        }
    }
    private void setType() {
        try {
            TYPE.add("Trucking");
            TYPE.add("Historical");
            TYPE.add("Adventure");
            TYPE.add("Forest");

            ArrayAdapter adapter = new ArrayAdapter(context, android.R.layout.simple_spinner_item, TYPE);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            sp_type.setAdapter(adapter);
        }catch (Exception e){
            Toast.makeText(context,e.toString(),Toast.LENGTH_SHORT).show();
        }
    }
}