import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
/*
 * Let's say we're building a social service for people who like to read books. 
 * With this service, people will want to be able to find books that they've read, mark that they've finished those books, and find other people who read the same books.

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
 */

public class BookUserSocialService {
	enum Profile{
		Profile_1,Profile_2
	}

	public static class User{
	    
		   public User(String name, String email, Profile profile){
		     this._name = name;
		     this._email = email;
		     this._profile = profile;
		     this._bookHistory = new ArrayList<Book>();
		     this._dateAndBookList = new TreeMap<LocalDate, List<Book>>();
		     
		   }
		    
		   	private TreeMap<LocalDate, List<Book>> _dateAndBookList;
		    private List<Book> _bookHistory;
		    private String _name;
		    private String _email;
		    private Profile _profile;
		    
		    public String GetName(){
		      return this._name;
		    }
		    
		    public String GetEmail(){
		      return this._email;
		    }
		    
		    public boolean AddToBookHistory(Book book){
		    	if(this._bookHistory.contains(book)) {
		    		return false;
		    	}
		    	
		    	this._bookHistory.add(book);
		    	return true;
		    }
		    
		    public void DisplayProfile() {
		    	switch(_profile) {
		    		case Profile_1:
		    			System.out.printf("%n %s has read %d books! %n",this._name, this._bookHistory.size());
		    			break;
		    		case Profile_2:
		    			System.out.printf("%n %s just read these books: ",this._name);
		    			GetLatestBooksForProfile();
		    			break;
		    	}
		    }
		    
		    private String GetLatestBooksForProfile() {
		    	if(this._dateAndBookList.size() == 0 ) {
		    		return "No books found!";
		    	}
		    	
		    	Book[] bookList = new Book[this._dateAndBookList.size()];
		    	
		    	bookList = this._dateAndBookList.get(this._dateAndBookList.lastKey()).toArray(bookList);
		    	
		    	
		    	for(int i = 0; i < bookList.length; i++) {
		    		System.out.print(" " + bookList[i].GetTitle()+" - "+bookList[i].GetAuthor());
		    		if(i != (bookList.length - 1)) {
		    			System.out.print(",");
		    		}
		    	}
		    	
		    	System.out.print(".");
		    	
		    	return "Found all results"; 
				
			}

			public String CompletedBookAndDate(Book book, int year, int month, int day){
		    	LocalDate finishedDate = LocalDate.of(year, month, day);
		    	
		    	if(finishedDate == null) {
		    		return "Date was incorrect or null";
		    	}
		    	
		    	if(this._dateAndBookList.containsKey(finishedDate)) {
		    		this._dateAndBookList.get(finishedDate).add(book);
		    		return "Finished Adding new Book";
		    	}
		    	List<Book> bookList = new ArrayList<Book>();
		    	
		    	bookList.add(book);
		    	
		    	this._dateAndBookList.put(finishedDate, bookList);
		    	return "Finished Book was successfully added";
		    }
		    
		    public String ListOfBooks(){
		    	if(this._bookHistory.isEmpty()) {
		    		return "No books found!";
		    	}
		    	
		    	System.out.println(" Books that has been read: ");
		    	
		    	for(Book book: this._bookHistory) {
		    		System.out.println(" " + book.GetTitle() +" - " + book.GetAuthor());
		    	}
		    	return "All books found!"; 
		    }
		    
		    public String ListOfBooksAndDates(){
		    	if(this._dateAndBookList.size() == 0 ) {
		    		return "No books found!";
		    	}
		    	
		    	for(Map.Entry<LocalDate, List<Book>> bookEntries: this._dateAndBookList.entrySet()) {
		    		for(Book book: bookEntries.getValue()) {
		    			System.out.printf("%n Book %s was finished on %s %n",book.GetTitle()+" - "+book.GetAuthor(), bookEntries.getKey().toString());
		    		}
		    	}
		    	return "All books found!"; 
		    }
		    
		    public String GetMostRecentBooksRead(){
		    	if(this._dateAndBookList.size() == 0 ) {
		    		return "No books found!";
		    	}
		    	
		    	LocalDate latestDate = this._dateAndBookList.lastKey();
		    	
		    	List<Book> bookList = this._dateAndBookList.get(this._dateAndBookList.lastKey());
		    	
		    	System.out.printf("%n As of the most recent date of %s these books were read: ", latestDate.toString());
		    	
		    	for(Book book: bookList) {
		    		System.out.printf("%n %s",book.GetTitle()+" - "+book.GetAuthor());
		    	}
		    	return "All books found!"; 
		    }
		  }
		  
		  public static class Book{
		    
		   public Book(String title, String author){
		     this._title = title;
		     this._author = author;
		     this._userHistory = new ArrayList<User>();
		   }
		    
		    private String _title;
		    private String _author;
		    private List<User> _userHistory;
		    
		    public String GetTitle(){
		      return this._title;
		    }
		    
		    public String GetAuthor(){
		      return this._author;
		    }
		  
		  public boolean AddToUserHistory(User user){
			  if(this._userHistory.contains(user)) {
		    		return false;
		    	}
		    	
		    	this._userHistory.add(user);
		    	return true;
		    }
		    
		    public String ListHistoryOfUsers(){
		    	if(this._userHistory.isEmpty()) {
		    		return "No users found!";
		    	}
		    	
		    	for(User user: this._userHistory) {
		    		System.out.println(" User that has read this book: " + 
		    				user.GetName() +" - " + user.GetEmail());
		    	}
		    	return "All users found!"; 
		    }
		    
		  }
		  
		  public static class UserService{
			  public UserService() {
				  this._userList = new ArrayList<User>();
			  }
			  
			  List<User> _userList;
			  
			  public boolean AddUserToService(User user) {
				  if(this._userList.contains(user)) {
					  return false;
				  }
				  
				  this._userList.add(user);
				  return true;
			  }
			  
			  public String ListUsers() {
				  if(this._userList.isEmpty()) {
			    		return "No users found!";
			    	}
			    	
			    	for(User user: this._userList) {
			    		System.out.println(" User in Service: " + 
			    				user.GetName() +" Email: " + user.GetEmail());
			    	}
			    	return "All users found!"; 
			    }
			  }
		  
		  
		  
		  
		  
		  public static void main(String[] args) {
			UserService service = new UserService();
			User user_1 = new User("Bob", "gmail",Profile.Profile_1);
		    User user_2 = new User("Bob Kane", ".gmail.com",Profile.Profile_2);
		    Book book = new Book("big boook","Sam");
		    Book book2 = new Book("big boook 2","Sam putman");
		    Book book3 = new Book("big boook 3","Sam putman");
		    Book book4 = new Book("big boook 4","Sam Cook");
		    
		    user_1.AddToBookHistory(book);
		    user_1.AddToBookHistory(book2);
		    user_1.AddToBookHistory(book3);
		    user_1.CompletedBookAndDate(book, 2022, 02, 12);
		    user_1.CompletedBookAndDate(book2, 2020, 02, 12);
		    user_1.CompletedBookAndDate(book3, 2024, 02, 12);
		    
		    user_2.AddToBookHistory(book3);
		    user_2.AddToBookHistory(book4);
		    user_2.AddToBookHistory(book);
		    user_2.CompletedBookAndDate(book3, 1992, 1, 23);
		    user_2.CompletedBookAndDate(book, 1992, 1, 23);
		    user_2.CompletedBookAndDate(book4, 1990, 1, 23);
		    
		    System.out.println(" User 1----------------");
		    user_1.DisplayProfile();
		    user_1.GetMostRecentBooksRead();
		    System.out.println("");
		    System.out.println("");
		    user_1.ListOfBooks();
		    System.out.println("");
		    user_1.ListOfBooksAndDates();
		    System.out.println("");
		    System.out.println(" User 2----------------");
		    user_2.DisplayProfile();
		    user_2.GetMostRecentBooksRead();
		    System.out.println("");
		    System.out.println("");
		    user_2.ListOfBooks();
		    System.out.println("");
		    user_2.ListOfBooksAndDates();
		    System.out.println("");
		    System.out.println(" Service----------------");
		    service.AddUserToService(user_1);
		    service.AddUserToService(user_2);
		    service.ListUsers();
		    
		  }

}
