package com.mtg.projectevaluator;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class UpdateMarksFragment extends Fragment {
    Button btnSet;
    Button btnUpdate;
    DatabaseHelper myDb;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_update_marks, container, false);
        btnSet = view.findViewById(R.id.btnSet);
        btnUpdate = view.findViewById(R.id.btnUpdate);
        btnSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String projNo = ((EditText) view.findViewById(R.id.projNoUpdateFrag)).getText().toString();
                myDb = new DatabaseHelper(getContext());
                if (!projNo.equals("")) {
                    Cursor cursor = myDb.execQ("SELECT IDEA, IMPLEMENTATION, EXTRA, TOTAL" +
                            " FROM student_table WHERE ID="
                            + projNo + ";");
                    EditText ideaMarks = view.findViewById(R.id.ideaMarksEditText);
                    EditText implementationMarks = view.findViewById(R.id.designMarksEditText);
                    EditText extraMarks = view.findViewById(R.id.extraMarksEditText);
                    TextView totalMarks = view.findViewById(R.id.totalMarksUpdateFrag);
                    while (cursor.moveToNext()) {
                        ideaMarks.setText(cursor.getString(0));
                        implementationMarks.setText(cursor.getString(1));
                        extraMarks.setText(cursor.getString(2));
                        totalMarks.setText(cursor.getString(3));
                    }
                } else {
                    Toast.makeText(getContext(), "Please enter Project No", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String projNo = ((EditText) view.findViewById(R.id.projNoUpdateFrag)).getText().toString();
                if (!projNo.equals("")) {
                    String ideaMarks = ((EditText) view.findViewById(R.id.ideaMarksEditText)).getText().toString();
                    String implementationMarks = ((EditText) view.findViewById(R.id.designMarksEditText)).getText().toString();
                    String extraMarks = ((EditText) view.findViewById(R.id.extraMarksEditText)).getText().toString();
                    //String totalMarks = ((TextView) view.findViewById(R.id.totalMarksUpdateFrag)).getText().toString();
                    int idm = Integer.parseInt(ideaMarks);
                    int dem = Integer.parseInt(implementationMarks);
                    int exm = Integer.parseInt(extraMarks);
                    SharedPreferences mPref = Objects.requireNonNull(getContext()).getSharedPreferences("mySharedPref", Context.MODE_PRIVATE);
                    int ids = mPref.getInt("idea", 3);
                    int des = mPref.getInt("design", 3);
                    int exs = mPref.getInt("extra", 3);
                    int totalMarks = (ids * idm + des * dem + exs * exm) / (ids + des + exs);
//                String query = "UPDATE student_table SET " +
//                        "IDEA = " + idm +
//                        ", IMPLEMENTATION = " + dem +
//                        ", EXTRA = " + exm +
//                        ", TOTAL = " + totalMarks +
//                        " WHERE ID = " + projNo + ";";
//                Cursor cursor = myDb.execQ(query);
//                Log.i("dbquery", query);
                    // DESIGN and IMPLEMENTATION are same
                    ContentValues cv = new ContentValues();
                    cv.put("IDEA", idm);
                    cv.put("IMPLEMENTATION", dem);
                    cv.put("EXTRA", exm);
                    cv.put("TOTAL", totalMarks);
                    myDb.updateCV(cv, projNo);
                    Toast.makeText(getContext(), "Update Successful", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Please enter Project No", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }
}
