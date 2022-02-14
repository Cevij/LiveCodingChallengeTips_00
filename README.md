# LiveCodingChallengeTips_00
 I'm creating this to help future engineers understand how to do better during live coding challenges.
 
 Question:
 
Let's say we're building a social service for people who like to read books. 
With this service, people will want to be able to find books that they've read, mark that they've finished those books, and find other people who read the same books.

1) Let's start with the ability to add users, and list users. 
	Make a function to add a user to the service with their name and email. 
	Make a separate function to list out the names of the users in the service.

2) Next, let's do something similar for books: 
	Make a function to add a book to the service with its title and name of its author, 
	and a separate function to list out all of the books in the service.

3) Now let's make it so that users can keep track of the books that they've read. 
	Make a function that lets a specific user mark a book when they finish reading it, 
	and keep track of the date when they finished reading it. 
	Make a separate function that lists all the books that a given user has read.

4) Now let's add different types of profiles. 
	When a user gets made, we should be able to choose between two different types of profiles. 
	When you view a user's profile, it should display one of two formats for information about the user and the books they've read:

  		a) In the first one, when viewing a profile, it will output: "<name> has read <number> books!".

  		b) In the second one, when viewing a profile, it will output: "<name> just read <book>",
  			where <book> is the title of the last book that they finished reading.

How to tackle the question:

Read the question THOROUGHLY!!
 - I constantly make the mistake of trying to work as I'm working through the question. The proper approach is to read the question through, pick it apart by looking for nouns and phases like Users, Item, List of items, and etc.

First paragraph:
- I see the nouns Social Service, Users, and Books. 
  - I now need to determine how we will use these nouns. I decided to have them all as classes

First Question:
 - I see the phases add users and list users. 
  So I have to decide on a data structure two things come to mind: a list or a hashmap were my first thoughts. I chose to have a list in the service to     hold all the users. I chose the list because there is no need for a key as of now since all I need to do is list all users in service.
 - Users need to have an email and username. 
  So it should only have getters where you can access the name and email. Email should always be unique so if you want to search for a certain user.
 - List all the users in the service. 
  So in the service you will need a collection to hold all the users. I just used a list again because it says nothing about searching for a certain       element. 

Second Question:
 - I see something similar to question 1. 
  So I repeat what I did for users but to books this time. 
 - List all the books in the service.
 - Books need to have a title and author. 
  So it should only have getters where you can access the title and author.
 
Third Question:
 - Needs to keep track of the books that the user read while keeping track of the date it was read. 
  So I used a TreeMap in the user class because with the key being LocalDate and the value being a list of books. This is a good set up for me getting the most recent date or the oldest date by using lastKey() or firstKey()
 - List all the books a user has read. 
  This will just print out the entire treemap.

Fourth Question:
 - When the user is created should be able to choose between 2 different profiles.
  So when reading this I usually will think oh i can just use a bool or int or string to select the different profiles. WRONG, don't do that! Use an enum  to choose the profile. It's cleaner, it's more organized, and it prevents inconsistent code.
 - Profile 1 should show the user name and the number of books that were read.
  Make a function to count all the books and return a int
 - Profile 2 should show user name and latest book read
  This is where I would use firstKey() in TreeMap to get the latest list of books


Always make your code TESTABLE!!
 - I know while you are on that 30, 45, 60 min time limit you are probably thinking I just have to get to the answer. WRONG TAKE YOUR TIME! Make sure your code is testable. It's ok to move slowly to set up for the right answer rather than hacking your way to the solution and skipping over important code etiquette. 

Watch your NAMING!!!
 - Be cautious of the way you name things! Don't use names like vaule1, value2, and temp. Also be careful that singular and plural nouns do not name a class book if it only holds the title and author it should be called book.
 - For examples:
  -- Not to do it!!!
   find(string id){
   Var temp = clients.Find(client => client.id == id)
   }

  -- To do!!!
   GetClientById(string id){
   Var foundClient = clients.Find(client => client.id == id)

   Return foundClient;
   }

 - For examples:
  -- Not to do it!!
   class Books{
       public Books(String title, String author){
         this._title = title;
         this._author = author;
       }
   }


  -- To do!!!
   class Book{
       public Book(String title, String author){
         this._title = title;
         this._author = author;
       }
   }



Always check for null or wrong values!!
 - Examples:
  -- Not to do it!!!
   GetClientById(string id, List clients){
   Var foundClient = clients.Find(client => client.id == id)

   Return foundClient;
   }

  -- To do!!!!
   GetClientById(string id, List clients){
    if(id == string.empty)
     Return
    if(clients == null)
     Return

   Var foundClient = clients.Find(client => client.id == id)

   Return foundClient;
   }

Being super talkative with the interviewers is NOT always a good idea!

 - Don't make the same mistake as me. The interviewer is TESTING you! Some questions like how to cast or is this the right syntax? Will be points against you!! Keep the conversation to clearing up questions and nothing more! Please don't make the mistake of talking to them as if there is a teacher or a friend you're working with to get to a solution.
