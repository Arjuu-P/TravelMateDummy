package com.dzniox.travel_mate.ui.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.dzniox.travel_mate.R;
import com.dzniox.travel_mate.network.APIClient;
import com.dzniox.travel_mate.network.APIInterface;
import com.dzniox.travel_mate.sharedPref.PrefKeys;
import com.dzniox.travel_mate.sharedPref.PrefUtils;
import com.dzniox.travel_mate.ui.adapters.SpotAdapter;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private Context context;
    private View rootView;

    private int Thrissur = 0;
    private int Wayanad = 1;
    private int Palakad = 2;
    private int Pathanamthitta = 3;
    private int Alappuzha = 4;
    private int Ernamkulam = 5;
    private int Idukki = 6;
    private int Malappuram = 7;
    private int Kannur = 8;
    private int Kasaragod = 9;
    private int Kollam = 10;
    private int Kottayam = 11;
    private int Kozhikode = 12;
    private int Thiruvananthapuram = 13;

    private String SELECTED_TAB = "0";
    private String SELECTED_TYPE = "";

    private EditText search;
    private TabLayout tabLayout;
    private RecyclerView rv_spot;
    private ProgressBar pb;

    private CardView forest,historical,adventure,trucking;
    private LinearLayout ll_type;

    APIInterface apiInterface;
    PrefUtils prefrences;

    public HomeFragment() {}
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        rootView = inflater.inflate(R.layout.fragment_home, container, false);
        context = getContext();
        apiInterface = APIClient.getClient().create(APIInterface.class);
        prefrences = PrefUtils.getInstance(context);

        search = rootView.findViewById(R.id.search);
        tabLayout = rootView.findViewById(R.id.tabLayout);
        rv_spot = rootView.findViewById(R.id.rv_spot);
        pb = rootView.findViewById(R.id.pb);
        ll_type = rootView.findViewById(R.id.ll_type);

        trucking = rootView.findViewById(R.id.trucking);
        historical = rootView.findViewById(R.id.historical);
        adventure = rootView.findViewById(R.id.adventure);
        forest= rootView.findViewById(R.id.forest);

        rv_spot.setVisibility(View.GONE);
        tabLayout.setVisibility(View.GONE);

        trucking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SELECTED_TYPE = "1";
                getSpotList();
            }
        });

        historical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SELECTED_TYPE = "2";
                getSpotList();
            }
        });
        adventure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SELECTED_TYPE = "3";
                getSpotList();
            }
        });
        forest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SELECTED_TYPE = "4";
                getSpotList();
            }
        });

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().length()>2){
                    searchSpot();
                }
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void afterTextChanged(Editable s) {}
        });

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                SELECTED_TAB = tab.getPosition() + "";
                getSpotList();
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}
            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });

        return rootView;
    }

    private void getSpotList() {
        try {
            pb.setVisibility(View.VISIBLE);
            Call<String> call = apiInterface.getSpot(SELECTED_TAB,SELECTED_TYPE);
            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    pb.setVisibility(View.GONE);
                    ll_type.setVisibility(View.GONE);
                    if (response.code() == 200){
                        rv_spot.setVisibility(View.VISIBLE);
                        tabLayout.setVisibility(View.VISIBLE);
                        try {
                            JSONObject data = new JSONObject(response.body());
                            if (data.getJSONArray("data").length() > 0){
                                SpotAdapter adapter = new SpotAdapter(context, data.getJSONArray("data"));
                                rv_spot.setLayoutManager(new LinearLayoutManager(context));
                                rv_spot.setAdapter(adapter);
                            }
                        }catch (Exception e){
                            Toast.makeText(context,"No Data Found",Toast.LENGTH_SHORT).show();
                            ll_type.setVisibility(View.VISIBLE);
                            rv_spot.setVisibility(View.GONE);
                            tabLayout.setVisibility(View.GONE);
                            SELECTED_TAB = "0";
                            SELECTED_TYPE = "";
                        }
                    }else {
                        ll_type.setVisibility(View.VISIBLE);
                        rv_spot.setVisibility(View.GONE);
                        tabLayout.setVisibility(View.GONE);
                        SELECTED_TAB = "0";
                        SELECTED_TYPE = "";
                    }
                }
                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    rv_spot.setVisibility(View.GONE);
                    tabLayout.setVisibility(View.GONE);
                    pb.setVisibility(View.GONE);
                    ll_type.setVisibility(View.VISIBLE);
                    SELECTED_TAB = "0";
                    SELECTED_TYPE = "";
                }
            });
        }catch (Exception e){
            ll_type.setVisibility(View.VISIBLE);
            rv_spot.setVisibility(View.GONE);
            tabLayout.setVisibility(View.GONE);
            pb.setVisibility(View.GONE);
            SELECTED_TAB = "0";
            SELECTED_TYPE = "";
            Toast.makeText(requireContext(),e.toString(),Toast.LENGTH_SHORT).show();
        }
    }
    private void searchSpot() {
        try {
            pb.setVisibility(View.VISIBLE);
            Call<String> call = apiInterface.searchSpot(search.getText().toString().trim()+"%");
            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    pb.setVisibility(View.GONE);
                    ll_type.setVisibility(View.GONE);

                    if (response.code() == 200){
                        rv_spot.setVisibility(View.VISIBLE);
                        tabLayout.setVisibility(View.VISIBLE);
                        try {
                            JSONObject data = new JSONObject(response.body());
                            if (data.getJSONArray("data").length() > 0){
                                SpotAdapter adapter = new SpotAdapter(context, data.getJSONArray("data"));
                                rv_spot.setLayoutManager(new LinearLayoutManager(context));
                                rv_spot.setAdapter(adapter);
                            }
                        }catch (Exception e){
                            ll_type.setVisibility(View.VISIBLE);
                            rv_spot.setVisibility(View.GONE);
                            tabLayout.setVisibility(View.GONE);
                        }
                    }else {
                        ll_type.setVisibility(View.VISIBLE);
                        rv_spot.setVisibility(View.GONE);
                        tabLayout.setVisibility(View.GONE);
                    }
                }
                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    rv_spot.setVisibility(View.GONE);
                    tabLayout.setVisibility(View.GONE);
                    ll_type.setVisibility(View.VISIBLE);
                    pb.setVisibility(View.GONE);
                }
            });
        }catch (Exception e){
            ll_type.setVisibility(View.VISIBLE);
            rv_spot.setVisibility(View.GONE);
            tabLayout.setVisibility(View.GONE);
            pb.setVisibility(View.GONE);
            Toast.makeText(requireContext(),e.toString(),Toast.LENGTH_SHORT).show();
        }
    }
}