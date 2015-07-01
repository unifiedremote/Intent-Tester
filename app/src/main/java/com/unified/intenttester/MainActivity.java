package com.unified.intenttester;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends ActionBarActivity implements View.OnClickListener
{
    final int REQUEST_CODE = 1337;

    EditText uriText;
    Button configureButton;
    Button sendButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        uriText = (EditText)findViewById(R.id.uri);
        configureButton = (Button)findViewById(R.id.configure);
        sendButton = (Button)findViewById(R.id.send);

        configureButton.setOnClickListener(this);
        sendButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.configure:
                URInterop.configure(this, REQUEST_CODE);
                break;
            case R.id.send:
                URInterop.send(this, uriText.getText().toString());
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                uriText.setText(URInterop.result(data));
            }
        }
    }
}
