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
import java.util.ArrayList;
import java.util.Arrays;
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

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

     response.setContentType("application/json;");
     
    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    Query query = new Query("Message").addSort("timestamp", SortDirection.DESCENDING);
    PreparedQuery results = datastore.prepare(query);

    ArrayList<String> comment = new ArrayList<String>();
    for (Entity entity : results.asIterable()) {
      String message = (String) entity.getProperty("text");
      String name = (String) entity.getProperty("comment_author");
      response.getOutputStream().println(name + ": " + message);
    }
   
    String json = new Gson().toJson(comment);
    //response.getWriter().println(json);

  } 

}

  //private ArrayList<String> quotes;
//   ArrayList<String> comment_text = new ArrayList<String>();
//   ArrayList<String> author_name = new ArrayList<String>();

// @Override
//  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
//     /**Picks a quote randomly based on the number of quotes in the ArrayList*/
//     //String quote = quotes.get((int) (Math.random() * quotes.size()));
//     //response.setContentType("text/html;");
//     //String json = convertToJSON(quotes);
//     //response.setContentType("application/json;");
//     //response.getWriter().println(json);

//     //     Query query = new Query("Task").addSort("timestamp", SortDirection.DESCENDING);
//     //     DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
//     //     PreparedQuery results = datastore.prepare(query);

//     //     List<Task> commentsListed = new ArrayList<>();
//     //     for (Entity entity : results.asIterable()) {
//     //       long id = entity.getKey().getId();
//     //       String name = (String) entity.getProperty("name");
//     //       String commentText = (String) entity.getProperty("comment-text");
//     //       long timestamp = (long) entity.getProperty("timestamp");

//     //       Task post = new Task(id, name, commentText, timestamp);
//     //       commentsListed.add(post);
//     //     }

//     //     Gson gson = new Gson();

//     //     response.setContentType("application/json;");
//     //     response.getWriter().println(gson.toJson(post));
//     //   }
    

 /**
    * Converts a ServerStats instance into a JSON string using manual String concatentation.
    */
    /*private String convertToJSON(ArrayList<String> quotes) {
        String json = "{";
        json += "\"firstQuote\": ";
        json += "\"" + quotes.get(0) + "\"";
        json += ", ";
        json += "\"secondQuote\": ";
        json += "\"" + quotes.get(1) + "\"";
        json += ", ";
        json += "\"thirdQuote\": ";
        json += "\"" + quotes.get(2) + "\"";
        json += ", ";
        json += "\"fourthQuote\": ";
        json += "\"" + quotes.get(3) + "\"";
        json += ", ";
        json += "\"fifthQuote\": ";
        json += quotes.get(4);
        json += "}";
        return json;
    }
    private String convertToJsonUsingGson(ArrayList<String> quotes) {
        Gson gson = new Gson();
        String json = gson.toJson(quotes);
        return json;
    }*/

    /**
    * @return the request parameter, or the default value if the parameter
    *         was not specified by the client*/
    
    /*private String getParameter(HttpServletRequest request, String name, String defaultValue) {
        String value = request.getParameter(name);
        if (value == null) {
        return defaultValue;
        }
        return value;
    }*/
 // }
//   public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
//     // Get the input from the form.
//     String text = request.getParameter("text-input");
//     String comment_author = request.getParameter("comment_author");

//     comment_text.add(text);
//     author_name.add(comment_author);

//     // Respond with the result.
//     response.setContentType("text/html;");
//     response.getWriter().println(author_name.get(0) + ": "+ comment_text.get(0));
    
//     Entity taskEntity = new Entity("Task");
//     taskEntity.setProperty("name", comment_author);
//     taskEntity.setProperty("comment-text", text);
//     //taskEntity.setProperty("timestamp", timestamp);

//     DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
//     datastore.put(taskEntity);

//     // Redirect back to the HTML page.
//     response.sendRedirect("/index.html");

//     //deletes stuff 
//     // long id = Long.parseLong(request.getParameter("id"));
//     // Key commentEntityKey = KeyFactory.createKey("Task", id);
//     // datastore.delete(commentEntityKey);
// }


