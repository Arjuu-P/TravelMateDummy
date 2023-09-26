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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddExpenseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddExpenseFragment extends Fragment {

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

    private EditText amount,name,desc;
    private Button add_btn;



    public AddExpenseFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddExpenseFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddExpenseFragment newInstance(String param1, String param2) {
        AddExpenseFragment fragment = new AddExpenseFragment();
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
        rootView = inflater.inflate(R.layout.fragment_add_expense, container, false);
        context = getContext();
        apiInterface = APIClient.getClient().create(APIInterface.class);
        prefrences = PrefUtils.getInstance(context);

        name = rootView.findViewById(R.id.name);
        amount = rootView.findViewById(R.id.amount);
        desc = rootView.findViewById(R.id.desc);
        add_btn = rootView.findViewById(R.id.add_btn);

        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (amount.getText().toString().trim().equals("") ||
                        name.getText().toString().trim().equals("") ||
                        desc.getText().toString().trim().equals("")) {
                    Toast.makeText(context, "Check all fields.", Toast.LENGTH_SHORT).show();
                }else {
                    AddExpense();
                }
            }
        });

        return rootView;
    }

    private void AddExpense() {
        Call<String> call = null;
        try {
            call = apiInterface.addExpense(
                    prefrences.getStringValue("id",""),
                    name.getText().toString().trim(),
                    amount.getText().toString().trim(),
                    desc.getText().toString().trim()
            );
        } catch (Exception e) {}
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.code() == 200){
                    Toast.makeText(context,"Expense Added",Toast.LENGTH_SHORT).show();
                    ProfileFragment profileFragment = new ProfileFragment();
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, profileFragment, "findThisFragment")
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