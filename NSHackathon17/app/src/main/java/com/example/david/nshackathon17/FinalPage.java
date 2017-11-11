package com.example.david.nshackathon17;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.net.URI;

public class FinalPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        float finalAmount = getIntent().getFloatExtra ("FinalAmount", 0);
        int correctAnswers = getIntent().getIntExtra("CorrectAnswers", 0);
        String chosenCharity = getIntent().getStringExtra("Charity");
        TextView result = findViewById(R.id.tvFinalResult);
        result.setText("You got " + correctAnswers + "/5 questions correct.\n\nYour total to donate is $" + finalAmount
                        + "\n\nYour chosen charity was " + chosenCharity);
        final Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/intent/tweet?text=I scored " + correctAnswers + "/5 on %23DoNext."));

        Button button = findViewById(R.id.bTweet);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(browserIntent);
            }
        });

        String donationLink = "";
        switch (chosenCharity)
        {
            case "American Diabetes Association":
                donationLink = "https://www.paypal.com/fundraiser/charity/1378356";
                break;
            case "Lupus Foundation Of America":
                donationLink = "https://www.paypal.com/fundraiser/charity/1536322";
                break;
            case "National MS Society":
                donationLink = "https://www.paypal.com/fundraiser/charity/1375518";
                break;
            case "American Cancer Society":
                donationLink = "https://www.paypal.com/fundraiser/charity/12854";
                break;
            case "UNICEF":
                donationLink = "https://www.paypal.com/fundraiser/charity/1443";
                break;
        }
        final Intent donateIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(donationLink));

        Button donate = findViewById(R.id.bDonate);
        donate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(donateIntent);
            }
        });

        TextView conclusion = findViewById(R.id.tvConclusion);
        conclusion.setText("Thank you for your generous donation!\n" +
                "\n" +
                "Every donation takes us another step closer to helping find a cure to a myriad of deadly diseases.\n" +
                "\n" +
                "We wish to continue to make it simple for everyone to contribute and raise awareness.\n" +
                "\n" +
                "Remember to let your friends and family know about DoNext. As more people continue to contribute, \n" +
                "we will continue to share more statistics and details of how victims are affected by these debilitated illnesses.\n" +
                "\n" +
                "Thank you for choosing DoNext!!");

    }

}
