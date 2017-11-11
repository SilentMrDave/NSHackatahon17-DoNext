package com.example.david.nshackathon17;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Random random = new Random(System.currentTimeMillis());
    TextView question;
    RadioGroup rGroup;
    ProgressBar progressBar;
    TextView total;
    int questionProgress = 1;
    float donatePerQuestion = .25f;
    int correctAnswers = 0;
    PopupWindow mPopupWindow = new PopupWindow();
    Context mContext;
    Activity mActivity;
    ConstraintLayout mConstraintLayout;
    ArrayList<Integer> previousQuestions = new ArrayList<Integer>();
    int chosenQuestion = 0;

    String[] questions = {
            "How many more times does lupus affect women more than men?",
            "Smoking attributes to what percentage of all lung cancer cases?",
            "Which of these symptoms is the most common in Multiple Sclerosis?",
            "Approximately how many people worldwide are affected by diabetes?",
            "Which one of these is not a symptom of parkinson's disease?",
            "Symptoms for Parkinsons disease usually occur around what age?",
            "Which country has the largest diabetes population?",
            "How many types of Lupus is/are there?",
            "Over the past three decades, what form of cancer were people most stricken with?",
            "There are four types of Multiple Sclerosis. Which type is the most diagnosed?",
            "What is the number one cause of diabetes?",
            "Which is not a cognitive side of Multiple Sclerosis?"
    };

    String[] info = {
                    "Lupus affects nine times more women than men, and more women of color than white women.\n" +
                    "Men, senior citizens and toddlers have been diagnosed with with lupus but women of childbearing age — 13 to 49 — are far more likely to be affected."
                    + "Genetics also plays a role. If you’re a woman with no family history of lupus, your chances of getting lupus are about one in 400. If your parents or a sibling has lupus, your chances jump to one in 25"
                    + "African-American and Latina women with no family history of lupus have about a one in 250 chance of developing the disease.",

                    "Smoking causes an estimated 90% of lung cancer. Tobacco has killed 50 million people in the last decade. If trends continue, a billion people will die from tobacco use and exposure this century, which equates to one person every six seconds",

                    "Fatigue is the most common and potentially most disabling symptom, affecting between 75% and 90% of people who have MS. Another common, yet less understood symptom of MS is pain, and this pain exists in many different forms.",

                    "Approximately 370,000,000 million are affected by diabetes worldwide with only 5% of those case being Type 1 diabetes.",

                    "Yellowing of skin is not a symptom. The main symptoms of Parkinson’s disease are tremor, slowness of movement (bradykinesia) and muscle stiffness or rigidity."
                    + "Approximately 4 million people worldwide suffer from Parkinson’s disease.",

                    "The risk of developing Parkinson’s disease increases with age.  Symptoms usually occur after the age of 50."
                    + "There is no cure for Parkinson’s disease, but treatments can help control the symptoms and maintain quality of life.",

                    "China has the largest diabetes population, with 90 million diabetes sufferers, followed by India (61.3m) and the USA (23.7m)"
                     + "Africa is projected to see the largest growth in diabetes prevalence between now and 2030, with rates forecast to rise from 14.7 million to 28 million (90% increase).",

                    "There are four types of lupus: systemic lupus erythematosus (SLE), cutaneous lupus, drug-induced lupus  and neonatal lupus."
                    + " 70% of all lupus cases are Systemic/SLE. Systemic lupus erythematosus (SLE) is the form of the disease that most people refer to when they say “lupus.”",

                    "Over the past three decades, more people have had skin cancer than all other cancers combined."
                    + "One in five Americans will develop skin cancer in the course of a lifetime",

                    "Approximately 85% of people with multiple sclerosis are diagnosed with Relapsing-Remitting Multiple Sclerosis. In RRMS, people have clearly defined periods that alternate between remission and relapse. A relapse is also called an exacerbation, an attack, or a flare-up. During the relapse period, MS symptoms worsen and new ones may appear. These relapses are followed by a long or short period of remission when symptoms partially or completely go away. There is no way to predict the remission/relapse time frames.",

                    "The actual number one cause of Diabetes is Genetics & Environmental factors, Diabetes has many other causes such as bad dieting, increase of age, and sometimes even pregnancy.",

                    "Multiple Sclerosis(MS) is a chronic condition, which means that it can last a lifetime and there is no known cure for it."
    };

    String[][] answers = {
            {"9", "2", "None", "20"},
            {"90%", "30%", "50%", "75%"},
            {"Fatigue", "Pain", "Numbness/Tingling", "Rash"},
            {"370,000,000", "145,000", "1,003,100", "150,000,000"},
            {"Yellowing of skin", "Tremor", "Slowness of movement", "Muscle Stiffness"},
            {"50", "35", "17", "40"},
            {"China", "United States", "India", "Zimbabwe"},
            {"4", "1", "2", "3"},
            {"Skin Cancer", "Breast Cancer", "Brain Cancer", "Lung Cancer"},
            {"Relapsing-Remitting Multiple Sclerosis ", "Secondary-Progressive MS", "Primary-Progressive MS", "Progressive-Relapsing MS"},
            {"Genetics & Environment Factors", "Sugar", "Greasy Foods", "Exercise"},
            {"Being Super Physically Fit", "Inability to Concentrate", "Impaired Problem-Solving Skills", "Trouble With Knowing Where Your Body Is"}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        rGroup = findViewById(R.id.radioGroup);
        progressBar = findViewById(R.id.progressBar);
        question = findViewById(R.id.tvQuestion);
        total = findViewById(R.id.tvTotal);
        mConstraintLayout = (ConstraintLayout) findViewById(R.id.clMain);
        mContext = getApplicationContext();
        mActivity = MainActivity.this;
        donatePerQuestion = getIntent().getFloatExtra("DonationPer", 0);


        final Button button = (Button) findViewById(R.id.button);
        initQuestions();
        final Intent intent = new Intent(this, FinalPage.class);
        intent.putExtra("Charity", getIntent().getStringExtra("Charity"));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!mPopupWindow.isShowing()) {
                    RadioButton rButton = findViewById(rGroup.getCheckedRadioButtonId());
                    String result = "";
                    if (rButton.getTag() == "Correct") {
                        result += "Correct.";
                        correctAnswers++;
                    } else result += "Incorrect.";
                    result += "\n" + info[chosenQuestion];
                    total.setText("Total: $" + correctAnswers * donatePerQuestion);
                    //Snackbar.make(view, result, Snackbar.LENGTH_LONG).setAction("Action", null).show();
                    initQuestions();

                    progressBar.setProgress(questionProgress);
                    LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);

                    // Inflate the custom layout/view
                    View customView = inflater.inflate(R.layout.popup, null);
                    mPopupWindow = new PopupWindow(customView, RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

                    if (Build.VERSION.SDK_INT >= 21) {
                        mPopupWindow.setElevation(5.0f);
                    }
                    // Get a reference for the custom view close button
                    ImageButton closeButton = (ImageButton) customView.findViewById(R.id.ib_close);
                    TextView tvText = (TextView) customView.findViewById(R.id.tv);
                    tvText.setText(result);
                    closeButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            // Dismiss the popup window
                            mPopupWindow.dismiss();
                            if (questionProgress > 5) {
                                finish();
                                intent.putExtra("FinalAmount", correctAnswers * donatePerQuestion);
                                intent.putExtra("CorrectAnswers", correctAnswers);
                                startActivity(intent);
                            }
                        }
                    });

                    mPopupWindow.showAtLocation(mConstraintLayout, Gravity.CENTER, 0, 0);

                    questionProgress++;
                }

            }
        });

    }

    public void initQuestions() {

        do {
            chosenQuestion = random.nextInt(questions.length);
        } while (previousQuestions.contains(chosenQuestion));
        previousQuestions.add(chosenQuestion);

        question.setText(questions[chosenQuestion]);
        ArrayList<String> currentAnswers = new ArrayList(Arrays.asList(answers[chosenQuestion]));
        Boolean correctAnswerChosen = false;
        for (int i = 0; i < rGroup.getChildCount(); i++) {
            Button button = (Button) rGroup.getChildAt(i);
            button.setTag(null);
            int chosenAnswer = random.nextInt(currentAnswers.size());

            if (chosenAnswer == 0 && !correctAnswerChosen) {
                button.setTag("Correct");
                correctAnswerChosen = true;
            }
            button.setText(currentAnswers.get(chosenAnswer));
            currentAnswers.remove(chosenAnswer);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
