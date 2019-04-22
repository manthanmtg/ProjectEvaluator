package com.mtg.projectevaluator;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.ajts.androidmads.library.SQLiteToExcel;

import static android.support.v4.content.ContextCompat.checkSelfPermission;

public class ExportDatabaseFragment extends Fragment {
    Button btnExport;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_export, container, false);
        btnExport = view.findViewById(R.id.btnExport);
        btnExport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteToExcel sqliteToExcel = new SQLiteToExcel(getContext(), "student.db");
                if(checkSelfPermission(getContext(),Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    Log.v("PG", "Permission Granted");
                } else {
                    ActivityCompat.requestPermissions(getActivity(),  new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                }
                ActivityCompat.requestPermissions(getActivity(),  new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                sqliteToExcel.exportSingleTable("student_table", "table1.xls", new SQLiteToExcel.ExportListener() {
                    @Override
                    public void onStart() {

                    }
                    @Override
                    public void onCompleted(String filePath) {
                        Log.i("exportPath", filePath);
                        Toast.makeText(getContext(), "Successful\nPath:" + filePath, Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onError(Exception e) {
                        Log.i("exportError", e.toString());
                    }
                });

            }
        });
        return view;
    }
}
