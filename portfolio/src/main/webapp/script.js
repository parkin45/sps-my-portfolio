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
//Adds comments
function getComment() {
    fetch('/data').then(response => response.json()).then((comment_author) => {
    document.getElementById('comment_author').innerText = comment_author;
  });
  fetch('/data').then(response => response.json()).then((comment) => {
    document.getElementById('text-input').innerText = comment;
  });
}
/** Fetches tasks from the server and adds them to the DOM. */
function loadComments() {
  fetch('/data').then(response => response.json()).then((comments) => {
    const commentListElement = document.getElementById('comments-list');
    comments.forEach((post) => {
      commentListElement.appendChild(createTaskElement(post));
    })
  });
}
/** Creates an element that represents a task, including its delete button. */
function createCommentElement(post) {
  const commentElement = document.createElement('li');
  commentElement.className = 'post';

  const textElement = document.createElement('span');
  textElement.innerText = post.text;

  const deleteButtonElement = document.createElement('button');
  deleteButtonElement.innerText = 'Delete';
  deleteButtonElement.addEventListener('click', () => {
    deleteComment(post);

    // Remove the task from the DOM.
    commentElement.remove();
  });

  commentElement.appendChild(textElement);
  commentElement.appendChild(deleteButtonElement);
  return commentElement;
}
/** Tells the server to delete the task. */
function deleteComment(post) {
  const params = new URLSearchParams();
  params.append('id', post.id);
  fetch('/data', {method: 'POST', body: params});
}
/**
 * Fetches quotes from the servers and adds them to the DOM.
 */
function getQuotesFromServer() {
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

