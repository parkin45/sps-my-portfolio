package com.google.sps.data;

/** A message. */
public final class Message{

  private final String text;
  private final String comment_author;
  private final long timestamp;
  private final long id;

  public Message(String text, String comment_author, long timestamp, long id) {
    this.text = text;
    this.comment_author = comment_author;
    this.timestamp = timestamp;
    this.id = id;
  }
}