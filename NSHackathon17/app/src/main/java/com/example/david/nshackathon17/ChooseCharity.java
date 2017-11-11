package com.example.david.nshackathon17;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class ChooseCharity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_charity);
        final Spinner Charities = findViewById(R.id.sChooseCharity);
        String[] charitiesArray = { "American Diabetes Association", "Lupus Foundation Of America", "National MS Society", "American Cancer Society", "UNICEF"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, charitiesArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Charities.setAdapter(adapter);
        final Spinner donationAmount = findViewById(R.id.sChooseDonation);
        Float[] donationAmounts = { 0.25f, 0.5f, 0.75f, 1f};
        ArrayAdapter<Float> donationAdapter = new ArrayAdapter<Float>(this, android.R.layout.simple_spinner_item, donationAmounts);
        donationAmount.setAdapter(donationAdapter);

        Button button = findViewById(R.id.button3);
        final Intent intent = new Intent(this, MainActivity.class);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("DonationPer", (Float)donationAmount.getSelectedItem());
                intent.putExtra("Charity", (String)Charities.getSelectedItem());
                startActivity(intent);
            }
        });
    }
}
