package com.compscieddy.feels;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import com.compscieddy.eddie_utils.Lawg;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements ViewTreeObserver.OnGlobalLayoutListener {

  private static final Lawg lawg = Lawg.newInstance(MainActivity.class.getSimpleName());

  @Bind(R.id.card_work) ViewGroup mCardWork;
  @Bind(R.id.card_fitness) ViewGroup mCardFitness;
  private View mRootView;
  ArrayList<ViewGroup> mCards = new ArrayList<>();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mRootView = getLayoutInflater().inflate(R.layout.activity_main, null);
    setContentView(mRootView);
    ButterKnife.bind(this);

    mCards.add(mCardWork);
    mCards.add(mCardFitness);
    mRootView.setOnTouchListener(new CardOnTouchListener(mCards));

    mRootView.getViewTreeObserver().addOnGlobalLayoutListener(MainActivity.this);
  }

  @Override
  public void onGlobalLayout() {
    initCards();
    mRootView.getViewTreeObserver().removeOnGlobalLayoutListener(MainActivity.this);
  }

  public void initCards() {
    lawg.d("Set Y: " + mRootView.getHeight() / 2);
    mCardWork.setY(mRootView.getHeight() / 2);
  }

}
