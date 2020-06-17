// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.sps.servlets;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.SortDirection;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** Servlet that returns some example content. TODO: modify this file to handle comments data */
@WebServlet("/data")
public class DataServlet extends HttpServlet {
  
  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String text = request.getParameter("text-input");
    String comment_author = request.getParameter("comment_author");
    comment_text.add(text);
    author_name.add(comment_author);
    // Respond with the result.
    response.setContentType("text/html;");
    response.getWriter().println(author_name.get(0) + ": "+ comment_text.get(0));
    
    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    Entity messageEntity = new Entity("Message");
    messageEntity.setProperty("text", text);
    messageEntity.setProperty("comment_author", comment_author);
    messageEntity.setProperty("timestamp", System.currentTimeMillis());
    datastore.put(messageEntity);

    response.sendRedirect("/index.html");

    //deletes stuff 
    // long id = Long.parseLong(request.getParameter("id"));
    // Key messageEntityKey = KeyFactory.createKey("Message", id);
    // datastore.delete(messageEntityKey);
  }

  //private ArrayList<String> quotes;
  ArrayList<String> comment_text = new ArrayList<String>();
  ArrayList<String> author_name = new ArrayList<String>();

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    Query query = new Query("Message").addSort("timestamp", SortDirection.DESCENDING);
    PreparedQuery results = datastore.prepare(query);

    List<String> comment = new ArrayList<String>();
    for (Entity entity : results.asIterable()) {
      String message = (String) entity.getProperty("text");
      String name = (String) entity.getProperty("comment_author");
      response.getOutputStream().println(name + ": " + message);
    }
    
    response.setContentType("application/json");
    //converts Json comment to Gson
    String json_comment = convertToJsonUsingGson(comment_text);
    response.getWriter().println(json_comment);
    //converts Json author name to Gson
    String json_authorName = convertToJsonUsingGson(author_name);
    response.getWriter().println(json_authorName);
    
    List<String> quotes = new ArrayList<String>();
    String quoteOne = "I am in a charming state of confusion. - Ada Lovelace";
    String quoteTwo = "It is much easier to apologise than it is to get permission. - Grace Hopper";
    String quoteThree = "You can do anything you want to, but you have to work at it - Annie Easley";
    quotes.add(quoteOne);
    quotes.add(quoteTwo);
    quotes.add(quoteThree);
    //converts quotes to Gson
    String json = convertToJsonUsingGson(quotes);
    response.getWriter().println(json);
  }
  /**
   * Converts a ServerStats instance into a JSON string using GSON
   */
  private String convertToJsonUsingGson(List<String> quotesToGson) {
    Gson gson = new Gson();
    String json = gson.toJson(quotesToGson);
    return json;
  }
  /**
   * @return the request parameter, or the default value if the parameter
   *         was not specified by the client*/  
  private String getParameter(HttpServletRequest request, String name, String defaultValue) {
    String value = request.getParameter(name);
    if (value == null) {
      return defaultValue;
    }
    return value;
  }
}

