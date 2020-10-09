package com.example.latihandatabase;


import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.latihandatabase.helper.DbHelper;

import androidx.appcompat.app.AppCompatActivity;

public class AddEdit extends AppCompatActivity {
    EditText txt_id, txt_name, txt_address;
    Button submit, cancel;
    DbHelper SQLite = new DbHelper(this);
    String id, name, address;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_edit);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txt_id = findViewById(R.id.txt_id);
        txt_name = findViewById(R.id.txt_nama);
        txt_address = findViewById(R.id.txt_alamat);
        submit = findViewById(R.id.submit);
        cancel = findViewById(R.id.cancel);

        id = getIntent().getStringExtra(MainActivity.TAG_ID);
        name = getIntent().getStringExtra(MainActivity.TAG_NAME);
        address = getIntent().getStringExtra(MainActivity.TAG_ADDRESS);

        if (id == null || id ==""){
            setTitle("Add Data");
        }else{
            setTitle("Edit Data");
            txt_id.setText(id);
            txt_name.setText(name);
            txt_address.setText(address);
        }
    }

    public void submit(View view) {
        try{
            if(txt_id.getText().toString().equals("")) {
                save();
            }else {
                edit();
            }
        }catch (Exception e){
            Log.e("submit", e.toString());
        }
    }


    public void cancel(View view) {
        blank();
        finish();
    }
    @Override
    public void onBackPressed(){
        finish();
    }
    @Override
     public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                blank();
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private void blank(){
        txt_name.requestFocus();
        txt_id.setText(null);
        txt_address.setText(null);
        txt_name.setText(null);
    }
    private void save(){
        if(String.valueOf(txt_name.getText()).equals(null) || String.valueOf(txt_name.getText()).equals("")
                || String.valueOf(txt_address.getText()).equals(null) || String.valueOf(txt_address.getText()).equals("")){
            Toast.makeText(getApplicationContext(), "please input name or address", Toast.LENGTH_SHORT).show();
        }else {
            SQLite.addData(txt_name.getText().toString().trim(), txt_address.getText().toString().trim());
            blank();
            finish();
        }
    }
    private void edit(){
        if(String.valueOf(txt_name.getText()).equals(null) || String.valueOf(txt_name.getText()).equals("")
                || String.valueOf(txt_address.getText()).equals(null) || String.valueOf(txt_address.getText()).equals("")){
            Toast.makeText(getApplicationContext(), "please input name or address", Toast.LENGTH_SHORT).show();
        }else {
            SQLite.update(Integer.parseInt(txt_id.getText().toString().trim()),txt_name.getText().toString().trim(),
                    txt_address.getText().toString().trim());
            blank();
            finish();
        }
    }
}
