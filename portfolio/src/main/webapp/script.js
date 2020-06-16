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

/**
 * Adds a random greeting to the page.
 */
function addRandomGreeting() {
  const greetings =
      ['I started skating at 2 years old and started figure skating at 7 years old', 'My favorite color is blue', 'Undertale is one of my fav PC games','I want to be a Jeopardy! contestant one day', 'I love voting', 'I like a lot of different generes of music','I like to read and draw', 'I really like Anime, but you probably suspected that from the button that revealed this message'];

  // Pick a random greeting.
  const greeting = greetings[Math.floor(Math.random() * greetings.length)];

  // Add it to the page.
  const greetingContainer = document.getElementById('greeting-container');
  greetingContainer.innerText = greeting;
}

/**
 * The above code is organized to show each individual step, but we can use an
 * ES6 feature called arrow functions to shorten the code. This function
 * combines all of the above code into a single Promise chain. You can use
 * whichever syntax makes the most sense to you.
 */
function getRandomQuoteUsingArrowFunctions() {
  fetch('/data').then(response => response.text()).then((quote) => {
    document.getElementById('quote-container').innerText = quote;
  });
}

function getComment() {
    fetch('/data').then(response => response.json()).then((comment_author) => {
    document.getElementById('comment_author').innerText = comment_author;
  });
  fetch('/data').then(response => response.json()).then((comment) => {
    document.getElementById('text-input').innerText = comment;
  });
}

/**
 * Fetches quotes from the servers and adds them to the DOM.
 */

function getQuotesFromServer() {
//   fetch('/data').then(response => response.json()).then((quotesToGson) => {
//     const statsListElement = document.getElementById('quote-container');
//     statsListElement.innerHTML = '';
//     statsListElement.appendChild(
//         createListElement('First Quote: ' + quotesToGson[0]));
//     statsListElement.appendChild(
//         createListElement('Second Quote: ' + quotesToGson[1]));
//     statsListElement.appendChild(
//         createListElement('Third Quote: ' + quotesToGson[2]));
//   });
  const serverQuotes = ["I am in a charming state of confusion. - Ada Lovelace","It is much easier to apologise than it is to get permission. - Grace Hopper", "You can do anything you want to, but you have to work at it - Annie Easley"]
  const quoteContainer = document.getElementById('quote-container');
  quoteContainer.innerHTML = '';
    quoteContainer.appendChild(
        createListElement('First Quote: ' + serverQuotes[0]));
    quoteContainer.appendChild(
        createListElement('Second Quote: ' + serverQuotes[1]));
    quoteContainer.appendChild(
        createListElement('Third Quote: ' + serverQuotes[2]));
}

/** Creates an <p> element containing text. */

function createListElement(text) {
  const pElement = document.createElement('p');
  pElement.innerText = text;
  return pElement;
}