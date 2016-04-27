package com.compscieddy.feels.models;

import com.orm.SugarRecord;

/**
 * Created by elee on 4/27/16.
 */
public class Rating extends SugarRecord {

  public enum RatingLevel {
    NO_FEELS, SOME_FEELS, MANY_FEELS
  }

  Facet facet;
  RatingLevel ratingLevel;

  public Rating() {}

  public Rating(RatingLevel level) {
    ratingLevel = level;
    save();
  }

  public void setRatingLevel(RatingLevel level) {
    ratingLevel = level;
    save();
  }

}
