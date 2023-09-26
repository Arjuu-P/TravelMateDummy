package com.dzniox.travel_mate.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.dzniox.travel_mate.R;
import com.dzniox.travel_mate.network.APIClient;
import com.dzniox.travel_mate.network.APIInterface;
import com.dzniox.travel_mate.sharedPref.PrefUtils;
import com.dzniox.travel_mate.ui.adapters.ReviewsAdapter;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ReviewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ReviewFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private View rootView;
    private Context context;

    APIInterface apiInterface;
    PrefUtils prefrences;
    private RecyclerView rv_ideas;
    private CardView card_add;

    private Button add_btn,cancel_btn;
    private EditText title;
    private ImageView add_review;

    public ReviewFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ReviewFragment.
     */
    // TODO: Rename and change types and number of parameters

    public static ReviewFragment newInstance(String param1, String param2) {
        ReviewFragment fragment = new ReviewFragment();
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
        rootView = inflater.inflate(R.layout.fragment_review, container, false);
        context = getContext();

        apiInterface = APIClient.getClient().create(APIInterface.class);
        prefrences = PrefUtils.getInstance(context);

        rv_ideas = rootView.findViewById(R.id.rv_ideas);
        add_btn = rootView.findViewById(R.id.add_btn);
        cancel_btn = rootView.findViewById(R.id.cancel_btn);
        title = rootView.findViewById(R.id.title);
        add_review = rootView.findViewById(R.id.add_review);
        card_add = rootView.findViewById(R.id.card_add_ideas);

        add_review.setOnClickListener(view -> card_add.setVisibility(View.VISIBLE));
        cancel_btn.setOnClickListener(view -> card_add.setVisibility(View.GONE));

        GetReview();
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (title.getText().toString().trim().equals("")) {
                    Toast.makeText(context,"Fill All the fields",Toast.LENGTH_SHORT).show();
                }else {
                    AddReview(title.getText().toString().trim());
                }
            }
        });

        return rootView;
    }

    private void GetReview() {
        try {
            Call<String> call = apiInterface.getReviews();
            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    if (response.code() == 200){
                        try {
                            JSONObject data = new JSONObject(response.body());
                            if (data.getJSONArray("data").length() > 0){
                                ReviewsAdapter adapter = new ReviewsAdapter(context, data.getJSONArray("data"));
                                rv_ideas.setLayoutManager(new LinearLayoutManager(context));
                                rv_ideas.setAdapter(adapter);
                            }
                        }catch (Exception e){}
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

    private void AddReview(String review) {
        try {
            Call<String> call = apiInterface.insertReview(review,
                    prefrences.getStringValue("id",""));
            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    if (response.code() == 200){
                        try {
                            JSONObject data = new JSONObject(response.body());
                            if (data.getBoolean("data")){
                                card_add.setVisibility(View.GONE);
                                Toast.makeText(context,"Review Added",Toast.LENGTH_SHORT).show();
                                GetReview();
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
}