package com.mtg.projectevaluator;

import android.content.ContentValues;
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
                if(!projNo.equals("")) {
                    String ideaMarks = ((EditText) view.findViewById(R.id.ideaMarksEditText)).getText().toString();
                    String implementationMarks = ((EditText) view.findViewById(R.id.designMarksEditText)).getText().toString();
                    String extraMarks = ((EditText) view.findViewById(R.id.extraMarksEditText)).getText().toString();
                    //String totalMarks = ((TextView) view.findViewById(R.id.totalMarksUpdateFrag)).getText().toString();
                    int idm = Integer.parseInt(ideaMarks);
                    int imm = Integer.parseInt(implementationMarks);
                    int exm = Integer.parseInt(extraMarks);
                    int totalMarks = (3 * idm + 8 * imm + 5 * exm) / (3 + 8 + 5);
//                String query = "UPDATE student_table SET " +
//                        "IDEA = " + idm +
//                        ", IMPLEMENTATION = " + imm +
//                        ", EXTRA = " + exm +
//                        ", TOTAL = " + totalMarks +
//                        " WHERE ID = " + projNo + ";";
//                Cursor cursor = myDb.execQ(query);
//                Log.i("dbquery", query);
                    ContentValues cv = new ContentValues();
                    cv.put("IDEA", idm);
                    cv.put("IMPLEMENTATION", imm);
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
