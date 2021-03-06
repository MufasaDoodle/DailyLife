# Daily life App

## Demonstration:
https://www.youtube.com/watch?v=4tzyhXJb_HA

## Description:

Essentially this app is supposed to help with daily things in life.

The app has three main functions:
* A todo-list. Adding todos with checkboxes to 'cross-out' the text
* A note taking function. Allows users to create and write new notes
* A grocery list manager. Similar to the todo list, but also allows for inputting amounts. User can swipe a listing to remove it.

Technically speaking, the backend contains an SQLite database for local storage, and if the user logs in to their Google account, they gain the option to upload the data to the cloud through my (existing) web API. The user would then be able to retrieve their data from the cloud.

The frontend contains a navigation drawer or something similar with the three functions able to be selected. You can then go to the selected view and you're able to interact with it. The app saves the data to local storage if the user exits the app or presses the 'back' button to a different view.

## Requirements:

### Must have:
- [x] As a user, I want to be able to view my todo-lists, so that I can easily find the one I’m looking for.
- [x] As a user, I want to be able to add a todo-list, so that I can compartmentalize my lists.
- [x] As a user, I want to be able to add todo entries to a selected todo-list, so that I can help remember my tasks.
- [x] As a user, I want the app to save my todos to local storage when exiting the app, so that my data is saved when I am done.
- [x] As a user, I want the app to save my todos to local storage when I press the back button, so that my data is saved when I am done.

### Should have:
- [x] As a user, I want to be able to edit info about todo entries in a selected list, so that I can change my todos.
- [x] As a user, I want to be able to edit the name of a todolist, so that I can better organize.
- [x] As a user, I want to be able to view my list of notes, so that I can easily find the one I’m looking for.
- [x] As a user, I want to be able to remove todos from a todo list so that I can remove unnecessary items.
- [x] As a user, I want to be able to remove todolists so that I can remove unnecessary items.
- [x] As a user, I want to be able to add a new note, so that I can remember something for the future.
- [x] As a user, I want to be able to view and edit a selected note, so that I can always read and update my notes.
- [x] As a user, I want to be able to remove notes so that I can remove unnecessary items.
- [x] As a user, I want the app to save my notes to local storage when exiting the app, so that my data is saved when I am done.
- [x] As a user, I want the app to save my notes to local storage when I press the back button, so that my data is saved when I am done.
- [x] As a user, I want to be able to add a new grocery list, so that I can remember what I need to buy.
- [x] As a user, I want to be able to view my grocery lists, so that I can easily find the one I'm looking for.
- [x] As a user, I want to be able to add items to my grocery list, so that I can update my list.
- [x] As a user, I want to be able to remove items from my grocery list, so that I can update my list.
- [x] As a user, I want the app to save my grocery list to local storage when exiting the app, so that my data is saved when I am done.
- [x] As a user, I want the app to save my grocery list to local storage when I press the back button, so that my data is saved when I am done.

### Could have:
- [x] As a user, I want to be able to log into my Google account, so that the system knows whose data to retrieve.
- [x] As a user, I want to be able to back up my data on the cloud, so that if something happens to my phone, I can restore my data.
- [x] As a user, I want to be able to retrieve my backup data from the cloud and have it overwrite existing data, so that I can easily get my data back.
- [x] As a user, I want to be able to tap a button to wipe all entries in local storage, so that I can quickly delete all my data.
