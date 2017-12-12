package com.williamjin.celebrityfragment;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReviewCelebrityActivity extends AppCompatActivity {
    private static final String TAG = "ReviewCelebrityActivity";
    EditText etReviewName;
    EditText etWriteReview;

    TextView tvReadReviewContent;
    Button btnSendReview;
    final String appFileDirName = "celebrityDoc";  // save in external storage /docs dir
    File appDocDir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_celebrity);
        etReviewName = findViewById(R.id.et_review_name);
        etWriteReview = findViewById(R.id.et_write_review);
        tvReadReviewContent = findViewById(R.id.tv_read_review_content);

        etWriteReview.setVisibility(EditText.INVISIBLE);
        tvReadReviewContent.setVisibility(TextView.INVISIBLE);
        btnSendReview = findViewById(R.id.btn_send_review);
        btnSendReview.setVisibility(Button.INVISIBLE);

        //check permission
        if (!isExternalStorageAvailable()) {
            Toast.makeText(this, "Permission Error", Toast.LENGTH_SHORT).show();
        } else {
            appDocDir = new File(
                    Environment.getExternalStorageDirectory()
                    , appFileDirName);
            if (!appDocDir.exists()) {
                Log.e(TAG, "onCreate: appFileDir" + "Creating ... ");
                appDocDir.mkdir();
            }
            if(!appDocDir.exists()){
                Log.e(TAG, "onCreate: appFileDir" + "Not created ... ");
            }
        }
    }

    public void handleReviewOption(View view) {
        String name;
        File file;
        switch (view.getId()) {
            case R.id.btn_read_review:
                etWriteReview.setVisibility(EditText.INVISIBLE);
                btnSendReview.setVisibility(Button.INVISIBLE);
                tvReadReviewContent.setVisibility(TextView.VISIBLE);
                name = etReviewName.getText().toString().trim();
                file = new File(appDocDir.getAbsolutePath() + "/" + name + ".txt");
                Log.d(TAG, "handleReviewOption: Celebrity path: " + file.getAbsolutePath());
                if (!file.exists()) {
                    Toast.makeText(this, "Celebrity's review not found", Toast.LENGTH_SHORT).show();
                    return;
                }
                BufferedReader input;
                try {
                    input = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
                    String line;
                    StringBuffer buffer = new StringBuffer();
                    while ((line = input.readLine()) != null) {
                        buffer.append(line);
                    }
                    tvReadReviewContent.setText(buffer.toString());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

                // visible write view section
            case R.id.btn_write_review:
                tvReadReviewContent.setVisibility(TextView.INVISIBLE);
                etWriteReview.setVisibility(EditText.VISIBLE);
                btnSendReview.setVisibility(Button.VISIBLE);
                break;

                // sending review
            case R.id.btn_send_review:
                String content = etWriteReview.getText().toString();
                Log.e(TAG, "handleReviewOption: content value: " + content);
                name = etReviewName.getText().toString().trim();

                FileOutputStream outputStream;
                file = new File(Environment.getExternalStorageDirectory(),
                        appFileDirName + "/" + name + ".txt");
                File fileDir = new File(Environment.getExternalStorageDirectory(),
                        appFileDirName);
                if (!fileDir.exists()) {
                    Log.e(TAG, "handleReviewOption: file dir not exist , path " + fileDir.getAbsolutePath());
                    return;
                }

                try {
//                    if (!file.exists())
//                        file.;
                    outputStream = new FileOutputStream(file);
                    outputStream.write(content.getBytes());
                    outputStream.close();
                    etWriteReview.setText("");
                } catch (IOException e) {
                    Log.e(TAG, "handleReviewOption: write to file error");
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
    }

    private static boolean isExternalStorageAvailable() {
        String extStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(extStorageState)) {
            return true;
        }
        return false;
    }
}
