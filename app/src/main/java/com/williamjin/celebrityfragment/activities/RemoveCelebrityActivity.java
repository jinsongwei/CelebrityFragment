package com.williamjin.celebrityfragment.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.williamjin.celeberity.R;
import com.williamjin.celeberity.model.DatabaseHelper;

public class RemoveCelebrityActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_celebrity);
    }

    public void handleRemoveCelebrity(View view) {
        DatabaseHelper db = new DatabaseHelper(this);
        EditText etRemoveName = findViewById(R.id.et_remove_name);
        switch (view.getId()) {
            case R.id.btn_remove_celebrity:
                long resCode = db.removeCelebrity(etRemoveName.getText().toString());
                if(resCode > 0){
                    Toast.makeText(this, "Delete Successfully!", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this, "Name Not Found!!!", Toast.LENGTH_SHORT).show();
                }
                etRemoveName.setText("");
                break;
            default:
                break;
        }
    }
}
