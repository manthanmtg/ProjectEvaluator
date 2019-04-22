package com.mtg.projectevaluator;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;

public class ChangePasswordFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_change_password, container, false);
        Button pwdChangeBtn = view.findViewById(R.id.pwdChangeBtn);
        pwdChangeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currPwd = ((EditText) view.findViewById(R.id.currPwd)).getText().toString();
                String newPwd = ((EditText) view.findViewById(R.id.newPwd)).getText().toString();
                String newPwdRe = ((EditText) view.findViewById(R.id.newPwdRe)).getText().toString();
                SharedPreferences mPref = (SharedPreferences) Objects.requireNonNull(getActivity()).getSharedPreferences("mySharedPref", Context.MODE_PRIVATE);
                String existPwd = mPref.getString("password", "12345");
                assert existPwd != null;
                if (existPwd.equals(currPwd)) {
                    // correct password entered
                    if (newPwd.equals(newPwdRe)) {
                        SharedPreferences.Editor editor = mPref.edit();
                        editor.remove("password");
                        editor.putString("password", newPwd);
                        editor.apply();
                        Toast.makeText(getContext(), "Password change Successful", Toast.LENGTH_SHORT).show();
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                new ImportFragment()).commit();
                        ((NavigationView) view.getRootView().findViewById(R.id.nav_view)).setCheckedItem(R.id.nav_import);

                    } else {
                        Toast.makeText(getContext(), "Password's don't match\nTry Again", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getContext(), "Please enter Correct Password\nTry Again", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Toast.makeText(getContext(), "Fragment: Change Password created", Toast.LENGTH_SHORT).show();
    }
}
