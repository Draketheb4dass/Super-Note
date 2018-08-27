[![Build Status](https://travis-ci.org/Draketheb4dass/Super-Note.svg?branch=master)](https://travis-ci.org/Draketheb4dass/Super-Note)

# Pre-work - *Super-Note*

**Super-Note** is an android app that allows building a todo list and basic todo items management functionality including adding new items, editing and deleting an existing item.

Submitted by: **Jephte Colin**

Time spent: **15** hours spent in total

## User Stories

The following **required** functionality is completed:

* [x] User can **successfully add and remove items** from the todo list
* [x] User can **tap a todo item in the list and bring up an edit screen for the todo item** and then have any changes to the text reflected in the todo list.
* [x] User can **persist todo items** and retrieve them properly on app restart

The following **optional** features are implemented:

* [x] Persist the todo items [into SQLite](http://guides.codepath.com/android/Persisting-Data-to-the-Device#sqlite) instead of a text file
* [ ] Improve style of the todo items in the list [using a custom adapter](http://guides.codepath.com/android/Using-an-ArrayAdapter-with-ListView)
* [ ] Add support for completion due dates for todo items (and display within listview item)
* [ ] Use a [DialogFragment](http://guides.codepath.com/android/Using-DialogFragment) instead of new Activity for editing items
* [ ] Add support for selecting the priority of each todo item (and display in listview item)
* [x] Tweak the style improving the UI / UX, play with colors, images or backgrounds


The following **additional** features are implemented:

* [x] List anything else that you can get done to improve the app functionality!
 * [x] Add a Splash screen

## Video Walkthrough

Here's a walkthrough of implemented user stories:

<img src='https://imgur.com/DEtUwDx.gif' title='Video Walkthrough' width='' alt='Video Walkthrough' />


GIF created with [ffmpeg](https://www.ffmpeg.org).

## Project Analysis

As part of your pre-work submission, please reflect on the app and answer the following questions below:

**Question 1:** "What are your reactions to the Android app development platform so far? Compare and contrast Android's approach to layouts and user interfaces in past platforms you've used."

**Answer:** Well, I really like coding on the Android Platform, having wide API at my disposal and a nice and powerful IDE, which is Android Studio. I haven't done any mobile development before Android, but I really like using the constraint layout, very easy to use for building layouts.

**Question 2:** "Take a moment to reflect on the `ArrayAdapter` used in your pre-work. How would you describe an adapter in this context and what is its function in Android? Why do you think the adapter is important? Explain the purpose of the `convertView` in the `getView` method of the `ArrayAdapter`."

**Answer:** An adapter is the component that act like a bridge between the data and a View component. in the context of the pre-work app, I used an ArrayAdapter, which act as a bridge between an ArrayList and a ListView. The ArrayAdapter take data from the ArrayList display it to the ListView while controling the way it is displayed, and what item to display. It is very important because the View can't be populated without it, and it reuse the views instead of creating new one, it's is both time and ressource efficient.

ConvertView is a parameter of type View used by getView, it define the old view to reuse by the getView method. Instead of deleting the view permanently, they are put in a temporary garbage and they can be used again but updated with new data.

## Notes

My main challenge was implementing the intent, specially sending data back to main activity. I kept using `add.` instead of `set.` when sending data back to the main activity. It was frustrating though, I checked the android developer guide and find out the right method to use.

## License

    Copyright 2018 Jephte Colin

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
