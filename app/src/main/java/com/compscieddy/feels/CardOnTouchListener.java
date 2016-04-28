package com.compscieddy.feels;

import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.compscieddy.eddie_utils.Etils;
import com.compscieddy.eddie_utils.Lawg;
import com.facebook.rebound.SimpleSpringListener;
import com.facebook.rebound.Spring;
import com.facebook.rebound.SpringSystem;

import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by elee on 4/27/16.
 */
public class CardOnTouchListener implements View.OnTouchListener {

  private static final Lawg lawg = Lawg.newInstance(CardOnTouchListener.class.getSimpleName());

  boolean isDragging;
  List<ViewGroup> mCards;
  int currentIndex = 0;
  float viewStartX, viewStartY;
  float startX, startY;
  float currentX, currentY;
  float differenceY;
  float maxY;
  final float THRESHOLD = Etils.dpToPx(200);
  private ViewGroup mCurrentCard;
  private TextView debugText;

  public CardOnTouchListener(List<ViewGroup> cards) {
    mCards = cards;
  }

  @Override
  public boolean onTouch(View v, MotionEvent event) {

    currentX = event.getX();
    currentY = event.getY();
    maxY = v.getHeight();
    differenceY = currentY - startY;

    debugText = ButterKnife.findById(v, R.id.debug_text);
    mCurrentCard = mCards.get(currentIndex);

    switch (event.getAction()) {

      case MotionEvent.ACTION_DOWN:
        isDragging = true;
        startX = event.getX();
        startY = event.getY();
        return true;


      case MotionEvent.ACTION_MOVE:
        debugText.setText("(" + startX + ", " + startY + ", " + differenceY + ")");
        updateCardLocation();
        return true;


      case MotionEvent.ACTION_UP:
        isDragging = false;
        resetCardLocation();
        return true;

    }
    return false;
  }

  public void updateCardLocation() {

    lawg.d("Y:" + mCurrentCard.getY() + " Y being translated: " + differenceY);
    float centerY = maxY / 2;
    // TODO: this is highly dependent on the state of the current card position (or wait,
    // no, I just need to update the current index I guess
    mCurrentCard.setY(centerY + differenceY);

  }

  public void resetCardLocation() {

    SpringSystem springSystem = SpringSystem.create();
    Spring springReset = springSystem.createSpring();
    Spring springUp = springSystem.createSpring();
    Spring springDown = springSystem.createSpring();

    springReset.addListener(new SimpleSpringListener() {
      @Override
      public void onSpringUpdate(Spring spring) {
        float value = (float) spring.getCurrentValue();
        float centerY = maxY / 2;
        float distanceFromReset = currentY - centerY;
        float translationY = (1 - value) * distanceFromReset;
        mCurrentCard.setY(centerY + translationY);
      }
    });

    springUp.addListener(new SimpleSpringListener() {
      @Override
      public void onSpringUpdate(Spring spring) {
        float value = (float) spring.getCurrentValue();
        float distanceFromTop = currentY;
        float translationY = (1 - value) * distanceFromTop;
        mCurrentCard.setY(translationY);
      }
    });

    springDown.addListener(new SimpleSpringListener() {
      @Override
      public void onSpringUpdate(Spring spring) {
        float value = (float) spring.getCurrentValue();
        float distanceBottom = maxY - currentY;
        float translationY = (1 - value) * distanceBottom;
        mCurrentCard.setTranslationY(translationY);
      }
    });

    if (differenceY < -THRESHOLD) {
      lawg.d("SPRING_UP ");
      springUp.setEndValue(1.0f);
    } else if (differenceY > THRESHOLD) {
      lawg.d("SPRING_DOWN ");
      springDown.setEndValue(1.0f);
    } else {
      lawg.d("SPRING_RESET ");
      springReset.setEndValue(1.0f);
    }

  }




}
