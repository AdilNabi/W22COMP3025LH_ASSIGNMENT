<h1>COMP3025 ASSIGNMENT</h1>
<h2>BY ADIL NABI    1156212</h2>


This application is an assignment for the Course COMP3025 at Lakehead-Georgian

In this assignment I created an application called "Books and Reviews!"

The purpose of this application is to allow users to read and write reviews on various types of books,
allowing people to share their opinions on a list of books that can be expanded by users themselves.

This application works by storing book objects into a firebase database which is then called to populate the book list 
and also the review list of each book.

This application contains various activities with each activity designed for each specific task mind.

The application can only be accessed by an authenticated user via google or email.

<h3>UPDATES COMPARED TO ASSIGNMENT 2</h3>

After feedback taken after assignment 2. The application UI was redesigned to be more navigation friendly.
The application now has a navigation top bar that contains icons for easy navigation to
add book, view book list and user profile screens.

Along with that, the application also now contains a navigation bottom bar with two buttons, a back button for 
a convenient way to navigate and alongside that, a home button that will take the user directly to home activity.

STATIC REVIEWS NO MORE! The book detail page now contains a live recycler view that showcases reviews which can be updated live.
This was done using modelViewFactory in order to make it work.

The application launcher icon was updated with a custom graphic.

Firebase is now fully secure only allowing authenticated users to access it.