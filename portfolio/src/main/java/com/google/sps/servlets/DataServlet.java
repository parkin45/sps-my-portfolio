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
import com.google.sps.data.Message;
import java.io.IOException;
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
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    Query query = new Query("Comment").addSort("timestamp", SortDirection.DESCENDING);
    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    PreparedQuery results = datastore.prepare(query);

    List<Message> comments = new ArrayList<>();
    for (Entity entity : results.asIterable()) {
      String text = (String) entity.getProperty("text");
      String comment_author = (String) entity.getProperty("comment_author");
      long timestamp = (long) entity.getProperty("timestamp");
      long id = entity.getKey().getId();

      Message post = new Message(text, comment_author, timestamp, id);
      comments.add(post);
    }
    Gson gson = new Gson();
    response.setContentType("application/json;");
    response.getWriter().println(gson.toJson(comments));

    List<String> quotes = new ArrayList<>();
    String quoteOne = "I am in a charming state of confusion. - Ada Lovelace";
    String quoteTwo = "It is much easier to apologise than it is to get permission. - Grace Hopper";
    String quoteThree = "You can do anything you want to, but you have to work at it - Annie Easley";
    quotes.add(quoteOne);
    quotes.add(quoteTwo);
    quotes.add(quoteThree);
  }
  /**
   * Converts a  instance into a JSON string using GSON
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

