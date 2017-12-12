package com.williamjin.celebrityfragment.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.williamjin.celeberity.R;
import com.williamjin.celeberity.model.Celebrity;
import com.williamjin.celeberity.model.DatabaseHelper;

public class ModifyCelebrityActivity extends AppCompatActivity {
    private Integer celebrityId = -1;
    private Boolean favorite = false;
    private DatabaseHelper db = new DatabaseHelper(this);
    private EditText etSearchName;
    private EditText etUpdateName;
    private EditText etUpdateGender;
    private EditText etUpdateType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_celebrity);
        etSearchName = findViewById(R.id.et_search_name);
        etUpdateName = findViewById(R.id.et_updated_name);
        etUpdateGender = findViewById(R.id.et_updated_gender);
        etUpdateType = findViewById(R.id.et_updated_type);
    }

    public void handleSearchCelebrity(View view) {

        switch (view.getId()) {
            case R.id.btn_search_celebrity:
                Celebrity c = db.getCelebrity(null, etSearchName.getText().toString());
                if (c == null) {
                    Toast.makeText(this, "No record in database", Toast.LENGTH_SHORT).show();
                    return;
                }
                celebrityId = c.getId();
                favorite = c.getFavorite();
                etUpdateName.setText(c.getName());
                etUpdateGender.setText(c.getGender().toString());
                etUpdateType.setText(c.getType());
                break;
            default:
                break;
        }
    }

    public void handleModifyCelebrity(View view) {
        switch (view.getId()) {
            case R.id.btn_modify_celebrity:
                if (celebrityId == -1) {
                    Toast.makeText(this, "invalid update!", Toast.LENGTH_SHORT).show();
                    return;
                }
                String gender = etUpdateGender.getText().toString();
                Character genderChar;
                if (gender.length() == 0)
                    genderChar = 'N';
                else
                    genderChar = gender.charAt(0);
                int resCode = db.modifyCelebrity(new Celebrity(
                        celebrityId,
                        etUpdateName.getText().toString(),
                        genderChar,
                        etUpdateType.getText().toString(),
                        favorite
                ));
                if (resCode > 0) {
                    Toast.makeText(this, "update successfully!!!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "update fails!", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }

    public void handleFavoriteButton(View view) {
        switch (view.getId()) {
            case R.id.btn_favorite:
                favorite = false;
                break;
            case R.id.btn_unfavorite:
                favorite = true;
                break;
            default:
                break;
        }
    }
}
