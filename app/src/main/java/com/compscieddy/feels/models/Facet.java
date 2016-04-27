package com.compscieddy.feels.models;

import android.support.annotation.Nullable;

import com.orm.SugarRecord;

import java.util.List;

/**
 * Created by elee on 4/27/16.
 */
public class Facet extends SugarRecord {

  String title;

  public Facet() {}

  public Facet(String title) {
    this.title = title;
    save();
  }

  public void setTitle(String title) {
    this.title = title;
    save();
  }

  @Nullable
  public Rating getRating() {
    List<Rating> ratings = Rating.find(Rating.class, "facet = ?", getId().toString());
    Rating rating = null;
    if (ratings.size() == 1) {
      rating = ratings.get(0);
    }
    return rating;
  }

  @Nullable
  public Comment getComment() {
    List<Comment> comments = Comment.find(Comment.class, "facet = ?", getId().toString());
    Comment comment = null;
    if (comments.size() == 1) {
      comment = comments.get(0);
    }
    return comment;
  }


}
