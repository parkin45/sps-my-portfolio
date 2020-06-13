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

  private ArrayList<String> quotes;

  @Override
  public void init() {
    quotes = new ArrayList<>();
    quotes.add("They told me computers could only do arithmetic. - Grace Hopper");
    quotes.add("A ship in port is safe, but that's not what ships are built for. - Grace Hopper");
    quotes.add("It is much easier to apologise than it is to get permission. - Grace Hopper");
    quotes.add("If you can't give me poetry, can't you give me poetical science? - Ada Lovelace");
    quotes.add("I am in a charming state of confusion. - Ada Lovelace");
  }
  
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    /**Picks a quote randomly based on the number of quotes in the ArrayList*/
    //String quote = quotes.get((int) (Math.random() * quotes.size()));
    String json = convertToJSON(quotes);
    response.setContentType("application/json;");
    response.getWriter().println(json);
  }

  /**
   * Converts a ServerStats instance into a JSON string using manual String concatentation.
   */
  private String convertToJSON(ArrayList<String> quotes) {
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
}
