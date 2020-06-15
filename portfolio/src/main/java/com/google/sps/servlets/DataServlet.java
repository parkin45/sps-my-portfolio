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

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
   
    List<String> quotes = new ArrayList<String>();
    String quoteOne = "I am in a charming state of confusion. - Ada Lovelace";
    String quoteTwo = "It is much easier to apologise than it is to get permission. - Grace Hopper";
    String quoteThree = "You can do anything you want to, but you have to work at it - Annie Easley";
    quotes.add(quoteOne);
    quotes.add(quoteTwo);
    quotes.add(quoteThree);
    
    
    String json = convertToJsonUsingGson(quotes);
    response.setContentType("application/json;");
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
}
