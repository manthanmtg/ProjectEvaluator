package com.mtg.projectevaluator;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.Objects;

public class SetPointsFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_set_points, container, false);
        Button setPoint = view.findViewById(R.id.btnSetPoint);
        setPoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences mPref = Objects.requireNonNull(getContext()).getSharedPreferences("mySharedPre", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = mPref.edit();
                editor.putInt("idea", Integer.parseInt(((EditText) view.findViewById(R.id.ideaMarksSetPointEditText)).getText().toString()));
                editor.putInt("design", Integer.parseInt(((EditText) view.findViewById(R.id.designMarksSetPointEditText)).getText().toString()));
                editor.putInt("extra", Integer.parseInt(((EditText) view.findViewById(R.id.extraMarksSetPointEditText)).getText().toString()));
                editor.apply();
            }
        });
        return view;
    }
}
