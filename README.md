# Daily life App

## Description:

Essentially this app is supposed to help with daily things in life.

The app has three main functions:
* A todo-list. Adding a todos with a checkboxes to 'cross-out' the text
* A note taking function. Allows users to create and write new notes
* A grocery list manager. Similar to the todo list, but also allows for inputting amounts. User can swipe a listing to remove it.

Technically speaking, the backend would contain a SQLite database for local storage, and if I have time, an option to upload the data to the cloud through my (existing) web API.

The frontend will contain a dropdown list or something similar with the three functions able to be selected. You will then go to the selected view and you're able to interact with it. The app saves the data to local storage if the user exits the app or presses the 'back' button to a different view.

## Requirements:

### Must have:
- [ ] As a user, I want to be able to view my todo-lists, so that I can easily find the one I’m looking for.
- [ ] As a user, I want to be able to add a todo-list, so that I can compartmentalize my lists.
- [ ] As a user, I want to be able to add todo entries to a selected todo-list, so that I can help remember my tasks.
- [ ] As a user, I want the app to save my data to local storage when exiting the app, so that my data is saved when I am done.
- [ ] As a user, I want the app to save my data to local storage when I press the back button, so that my data is saved when I am done.

### Should have:
- [ ] As a user, I want to be able to view my list of notes, so that I can easily find the one I’m looking for.
- [ ] As a user, I want to be able to add a new note, so that I can remember something for the future.
- [ ] As a user, I want to be able to view and edit a selected note, so that I can always read and update my notes.
- [ ] As a user, I want to be able to view my grocery list, so that I can help remember what I am supposed to buy.
- [ ] As a user, I want to be able to add items to my grocery list.
- [ ] As a user, I want to be able to remove items from my grocery list.

### Could have:
- [ ] As a user, I want to be able to back up my data on the cloud, so that if something happens to my phone, I know my data is secure.
- [ ] As a user, I want to be able to retrieve my backup data from the cloud and have it overwrite existing data, so that I can easily get my data back.
- [ ] As a user, I want to be able to tap a button to wipe all entries in local storage, so that I can quickly delete all my data.

### Will not have:
*	A user login system
