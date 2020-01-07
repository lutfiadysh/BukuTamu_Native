package com.example.bukutamu;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

    public class FirebaseDBCreateActivity extends AppCompatActivity {

        private DatabaseReference database;

        private Button btSubmit;
        private EditText etNama;
        private EditText etAsal;
        private EditText etTujuan;

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_input);

            etNama = findViewById(R.id.nama);
            etAsal = findViewById(R.id.asal);
            etTujuan = findViewById(R.id.tujuan);
            btSubmit = findViewById(R.id.bt_submit);

            database = FirebaseDatabase.getInstance().getReference();

            btSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(!isEmpty(etNama.getText().toString()) && !isEmpty(etAsal.getText().toString()) && !isEmpty(etTujuan.getText().toString()))
                        submitBarang(new Barang(etNama.getText().toString(), etAsal.getText().toString(), etTujuan.getText().toString()));
                    else
                        Snackbar.make(findViewById(R.id.bt_submit), "Data barang tidak boleh kosong", Snackbar.LENGTH_LONG).show();

                    InputMethodManager imm = (InputMethodManager)
                            getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(
                            etNama.getWindowToken(), 0);
                }
            });

        }

        private boolean isEmpty(String s) {
            return TextUtils.isEmpty(s);
        }

        private void updateBarang(Barang barang) {
        }

        private void submitBarang(Barang barang) {

            database.child("tamu").push().setValue(barang).addOnSuccessListener(this, new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    etNama.setText("");
                    etAsal.setText("");
                    etTujuan.setText("");
                    Snackbar.make(findViewById(R.id.bt_submit), "Data berhasil ditambahkan", Snackbar.LENGTH_LONG).show();
                }
            });
        }

        public static Intent getActIntent(Activity activity) {
            // kode untuk pengambilan Intent
            return new Intent(activity, FirebaseDBCreateActivity.class);
        }
    }

