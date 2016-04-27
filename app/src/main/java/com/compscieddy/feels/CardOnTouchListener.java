package com.compscieddy.feels;

import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by elee on 4/27/16.
 */
public class CardOnTouchListener implements View.OnTouchListener {

  boolean isDragging;
  List<ViewGroup> mCards;
  int currentIndex = 0;


  public CardOnTouchListener(List<ViewGroup> cards) {
    mCards = cards;
  }

  @Override
  public boolean onTouch(View v, MotionEvent event) {
    switch (event.getAction()) {


      case MotionEvent.ACTION_DOWN:
        isDragging = true;
        return true;


      case MotionEvent.ACTION_MOVE:
        updateCardLocation(event);
        return true;


      case MotionEvent.ACTION_UP:
        isDragging = false;
        return true;


    }
    return false;
  }

  public void updateCardLocation(MotionEvent event) {
    float x = event.getRawX();
    float y = event.getRawY();
    ViewGroup currentCard = mCards.get(currentIndex);
    currentCard.setY(y);

  }




}
