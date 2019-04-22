package com.mtg.projectevaluator;

import android.annotation.SuppressLint;
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

public class ViewMarksFragment extends Fragment {
    DatabaseHelper myDb;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_view_marks, container, false);
        Button btnGetMarks = view.findViewById(R.id.btnGetMarks);
        btnGetMarks.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                String projNo = ((EditText) view.findViewById(R.id.projNoEditText)).getText().toString();
                myDb = new DatabaseHelper(getContext());
                Cursor cursor = myDb.execQ("SELECT * FROM student_table WHERE ID="
                        + projNo + ";");
                TextView marksView = view.findViewById(R.id.marksView);
                if (cursor.getCount() == 0) {
                    marksView.setText("No Rows Exist");
                } else {
                    StringBuilder buffer = new StringBuilder();
                    while (cursor.moveToNext()) {
                        buffer.append(" ID: ").append(cursor.getString(0)).append("\n");
                        buffer.append(" Project Name: ").append(cursor.getString(1)).append("\n");
                        buffer.append(" Student 1 Name: ").append(cursor.getString(2)).append("\n").append("  USN1: ").append(cursor.getString(3)).append("\n");
                        if (!cursor.getString(4).equals("NULL")) {
                            buffer.append(" Student 2 Name: ").append(cursor.getString(4)).append("\n").append("  USN2: ").append(cursor.getString(5)).append("\n");
                        }
                        if (!cursor.getString(6).equals("NULL")) {
                            buffer.append(" Student 3 Name: ").append(cursor.getString(6)).append("\n").append("  USN3: ").append(cursor.getString(7)).append("\n");
                        }
                        if (!cursor.getString(8).equals("NULL")) {
                            buffer.append(" Student 4 Name: ").append(cursor.getString(8)).append("\n").append("  USN4: ").append(cursor.getString(9)).append("\n");
                        }
                        buffer.append(" ---- Marks Distribution ----\n");
                        buffer.append(" Idea: ").append(cursor.getString(10)).append("\n");
                        buffer.append(" Implementation: ").append(cursor.getString(11)).append("\n");
                        buffer.append(" Extra: ").append(cursor.getString(12)).append("\n");
                        buffer.append(" Total Marks: ").append(cursor.getString(13)).append("\n");
                    }
                    marksView.setText(buffer.toString());
                }
            }
        });
        return view;
    }
}
