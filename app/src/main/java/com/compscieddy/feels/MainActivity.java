package com.compscieddy.feels;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.Bind;

public class MainActivity extends AppCompatActivity {

  @Bind(R.id.card_work) ViewGroup mCardWork;
  @Bind(R.id.card_fitness) ViewGroup mCardFitness;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    View rootView = getLayoutInflater().inflate(R.layout.activity_main, null);
    setContentView(rootView);

    ArrayList<ViewGroup> mCards = new ArrayList<>();
    mCards.add(mCardWork);
    mCards.add(mCardFitness);
    rootView.setOnTouchListener(new CardOnTouchListener(mCards));
  }
}
