# Welcome to E-Library

I present to you my first web application that implements the basic and most necessary functionality for electronic libraries, which I will describe below.


## Main page

Home page, allows you to move between user and book pages

![Main Page](https://od.lk/s/NDZfMzI1NDU1NTBf/MainPage.png)


## Book list page
If there are more than 6 books in the database, the application will automatically split them into several pages.
Here you can:
* Go to the book search page by author or title
* Sort books by year of publication, and unsort, it if necessary
* Add new book

![Main Page](https://od.lk/s/NDZfMzI1NDU1MDFf/BookMainPage.png)

Result on request 'Me'          |  Not found result 			| 
:-------------------------:			|:-------------------------:	|
![Here you can see the owner of the book, and if he is not there](https://od.lk/s/NDZfMzI1NDgzMThf/Search.png)  |  ![result if book not found](https://od.lk/s/NDZfMzI1NDgzMTVf/NotFound.png)|


Empty input result       |   Sorted book list
:-------------------------:|:-------------------------:
![Result of null input](https://od.lk/s/NDZfMzI1NDgzMTRf/EmptyInput.png)  |  ![Sorted list, there is a button to cancel sorting](https://od.lk/s/NDZfMzI1NDgxNjBf/SortedBookList.png)





## Personal page of the book

The personal page of the book shows its title, the author of the book and when it was published. Here you can see the current owner of the book, and a button to return the book if someone has it now.
If the book is free now - you can assign the book to someone.
There is also an edit and a delete buttons.

#### Edit Book Page
![The application will not allow you to enter empty lines or invalid values, the year of publication can be any numeric value or "Unknown"](https://od.lk/s/NDZfMzI1ODk2Nzhf/editBookPage.png)

Book with owner         |  Book without owner 	| 
:-------------------------:|:-------------------------:	|
![Hyperlink leads to the user's personal page, If you delete a book that has an owner, the book will be automatically deleted from the user's account](https://od.lk/s/NDZfMzI1ODk2ODBf/personalWithOwner.png)  |  ![result if book not found](https://od.lk/s/NDZfMzI1ODk2Nzlf/personalWithoutOwner.png)|

## List of users

This page shows all registered users, it contains hyperlinks to clients' personal pages and a button to add a user.

List of users         | Adding a new user 	| 
:-------------------------:|:-------------------------:	|
![](https://od.lk/s/NDZfMzI1ODk2OTNf/ListOfUsers.png)  |  ![The full name must necessarily contain the First Name and Last Name, and the patronymic - is optional, everything is checked by the regex expression.](https://od.lk/s/NDZfMzI1ODk2OTJf/AddUser.png)|




## User's personal page

The personal page contains all information about the user, the books that are assigned to him and their status.

* The title of the book is highlighted in  ![#c5f015](https://via.placeholder.com/15/c5f015/c5f015.png) green if the book is not expired.
* And it would be highlighted in ![#f03c15](https://via.placeholder.com/15/f03c15/f03c15.png) red, if a person has a book for more than 10 days.

User's personal page with an expired book       | User's personal page with an unexpired book 	| 
:-------------------------:|:-------------------------:	|
![If a user is removed from the database, all his books will automatically become free.](https://od.lk/s/NDZfMzI1ODk2OTZf/PersonalPageWithEspiredBook.png)  |  ![As you can see, ALL of his books are visible on the user's page.](https://od.lk/s/NDZfMzI1ODk2OTVf/PersonalPage.png)|
