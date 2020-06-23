package com.google.sps.servlets;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** Servlet responsible for creating new tasks. */
@WebServlet("/new")
public class NewMessage extends HttpServlet {

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String text = request.getParameter("text");
    String comment_author = request.getParameter("comment_author");
    String languageCode = request.getParameter("language");
    long timestamp = System.currentTimeMillis();

    // Do the translation.
    Translate translate = TranslateOptions.getDefaultInstance().getService();
    Translation translation =
        translate.translate(text, Translate.TranslateOption.targetLanguage(languageCode));
    String translatedText = translation.getTranslatedText();
    // Translation translationAuthor =
    //     translate.translate(comment_author, Translate.TranslateOption.targetLanguage(languageCode));
    // String translatedCA = translationAuthor.getTranslatedText();

    Entity messageEntity = new Entity("Comment");
    messageEntity.setProperty("text", translatedText);
    messageEntity.setProperty("comment_author", comment_author);
    messageEntity.setProperty("timestamp", System.currentTimeMillis());

    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    datastore.put(messageEntity);

    System.out.println(translatedText);
    System.out.println(languageCode);

    response.sendRedirect("/index.html");
  }
}