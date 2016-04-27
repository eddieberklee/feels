package com.compscieddy.feels.models;

import com.orm.SugarRecord;

/**
 * Created by elee on 4/27/16.
 */
public class Comment extends SugarRecord {

  String commentText;

  public Comment() {}

  public Comment(String text) {
    commentText = text;
    save();
  }

  public void setCommentText(String text) {
    commentText = text;
    save();
  }

}
