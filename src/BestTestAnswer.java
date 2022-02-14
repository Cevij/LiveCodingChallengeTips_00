import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


enum Profile{
	Profile_1,Profile_2
}

class User{
	   public User(String name, String email, Profile profile){
	     this._name = name;
	     this._email = email;
	     this._profile = profile;
	     this._dateAndBookList = new TreeMap<LocalDate, List<Book>>();
	     
	   }
	    
	   	private TreeMap<LocalDate, List<Book>> _dateAndBookList;
	    private String _name;
	    private String _email;
	    private Profile _profile;
	    
	    public String GetName(){
	      return this._name;
	    }
	    
	    public String GetEmail(){
	      return this._email;
	    }
	    
	    public void DisplayProfile() {
	    	switch(_profile) {
	    		case Profile_1:
	    			System.out.printf("%n %s has read %d books! %n",this._name, GetNumberOfBooksRead());
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
	    	return "Finished making list and adding book was successfully";
	    }
	    
	    public String ListOfBooksAndDates(){
	    	if(this._dateAndBookList.isEmpty()) {
	    		return "No books found!";
	    	}
	    	
	    	for(Map.Entry<LocalDate, List<Book>> bookEntries: this._dateAndBookList.entrySet()) {
	    		for(Book book: bookEntries.getValue()) {
	    			System.out.printf("%n Book %s was finished on %s %n",book.GetTitle()+" - "+book.GetAuthor(), bookEntries.getKey().toString());
	    		}
	    	}
	    	return "All books found!"; 
	    }
	    
	    private int GetNumberOfBooksRead(){
	    	if(this._dateAndBookList.isEmpty()) {
	    		return 0;
	    	}
	    	int numOfBooks = 0;
	    	
	    	for(Map.Entry<LocalDate, List<Book>> bookEntries: this._dateAndBookList.entrySet()) {
	    		numOfBooks += bookEntries.getValue().size();
	    	}
	    	return numOfBooks; 
	    }
	  }

class Book{
    
	   public Book(String title, String author){
	     this._title = title;
	     this._author = author;
	   }
	    
	    private String _title;
	    private String _author;
	    
	    public String GetTitle(){
	      return this._title;
	    }
	    
	    public String GetAuthor(){
	      return this._author;
	    }
	  }

class UserService{
	
	  public UserService() {
		  this._userList = new ArrayList<User>();
		  this._bookList = new ArrayList<Book>();
	  }
	  
	  private List<User> _userList;
	  private List<Book> _bookList;
	  
	  public boolean CreateUser(String name, String email, Profile profile) {
		  User newUser = new User(name, email, profile);
		  
		  if(FindUserByEmail(newUser.GetEmail()) == null) {
			  AddUserToList(newUser);
			  return true;
		  }else {
			  return false;
		  }
	  }
	  
	  public String AddDateOfBookCompletionToUser(String email, String title, String author, int year, int month, int day) {
		  if(FindUserByEmail(email) == null) {
			  return "User not found";
		  }else if(FindBookByTitleAndAuthor(title, author) == null){
			  return "Book not found";
		  }
		  
		  User foundUser = FindUserByEmail(email);
		  
		  Book foundBook = FindBookByTitleAndAuthor(title, author);
		  
		  foundUser.CompletedBookAndDate(foundBook, year, month, day);
		  
		  return "Completeion date and book was added to user history";
	  }
	  
	  public String ListBooksOfUser(String email) {
		  if(FindUserByEmail(email) == null) {
			  return "User not found";
		  }
		  
		  User foundUser = FindUserByEmail(email);
		  
		  foundUser.ListOfBooksAndDates();
		  
		  return "Completed the return of list";
	  }
	  
	  public String ShowUserProfile(String email) {
		  if(FindUserByEmail(email) == null) {
			  return "User not found";
		  }
		  
		  User foundUser = FindUserByEmail(email);
		  
		  foundUser.DisplayProfile();
		  
		  return "Completed the return of User Profile";
	  }
	  
	  public boolean CreateBook(String title, String author) {
		  Book newBook = new Book(title, author);
		  
		  if(FindBookByTitleAndAuthor(newBook.GetTitle(), newBook.GetAuthor()) == null) {
			  AddBookToList(newBook);
			  return true;
		  }else {
			  return false;
		  }
	  }
	  
	  public String ListUsers() {
		  if(this._userList.isEmpty()) {
	    		return "No users found!";
	    	}
	    	
		  System.out.println(" Users in Service: ");
	    	for(User user: this._userList) {
	    		System.out.println("\tName: " + 
	    				user.GetName() +" Email: " + user.GetEmail());
	    	}
	    	return "All users found!"; 
	    }
	  	
	  public String ListBooks() {
		  if(this._bookList.isEmpty()) {
	    		return "No books found!";
	    	}
		  
		  System.out.println(" Books in Service: ");
	    	for(Book book: this._bookList) {
	    		System.out.println("\tTitle: " + 
	    				book.GetTitle() +" Author: " + book.GetAuthor());
	    	}
	    	return "All users found!"; 
	    }
	  
	  private boolean AddUserToList(User user) {
		  if(FindUserByEmail(user.GetEmail()) != null) {
			  return false;
		  }
		  
		  this._userList.add(user);
		  return true;
	  }
	  
	  private User FindUserByEmail(String email) {
		  User foundUser = this._userList.stream()
		  	.filter(user -> email.equalsIgnoreCase(user.GetEmail()))
		  	.findAny().orElse(null);
		  
		  if(foundUser == null) {
			  return foundUser;
		  }
		  
		  return foundUser;
	  }
	  
	  private Book FindBookByTitleAndAuthor(String title, String author) {
		  String bookTitle = title + " - " + author;
		  
		  Book foundBook = this._bookList.stream()
		  	.filter(book -> bookTitle.equalsIgnoreCase(book.GetTitle()+" - "+book.GetAuthor()))
		  	.findAny().orElse(null);
		  
		  if(foundBook == null) {
			  return foundBook;
		  }
		  
		  return foundBook;
	  }
	  
	  private boolean AddBookToList(Book book) {
		  if(FindBookByTitleAndAuthor(book.GetTitle(), book.GetAuthor()) != null) {
			  return false;
		  }
		  
		  this._bookList.add(book);
		  return true;
	  }
	  
}

public class BestTestAnswer {
		  
		  public static void main(String[] args) {
			UserService service = new UserService();
			service.CreateUser("Tester", "test@gmail",Profile.Profile_1);
		    service.CreateUser("Tester2", "test2@gmail",Profile.Profile_2);
		    service.CreateBook("Test1","Sam");
		    service.CreateBook("Test2","Sam putman");
		    service.CreateBook("Test1","Sam putman");
		    service.CreateBook("Test4","Sam Cook");
		    
		    service.ListBooks();

		    service.ListUsers();
		    

		    service.AddDateOfBookCompletionToUser("test@gmail", "Test1", "Sam", 2022, 5, 17);
		    service.AddDateOfBookCompletionToUser("test@gmail", "Test2", "Sam putman", 2021, 5, 17);
		    service.AddDateOfBookCompletionToUser("test2@gmail", "Test1", "Sam", 2022, 5, 17);
		    service.AddDateOfBookCompletionToUser("test2@gmail", "Test2", "Sam putman", 2021, 5, 17);
		    service.AddDateOfBookCompletionToUser("test2@gmail", "Test1", "Sam putman", 2022, 5, 17);
		    service.AddDateOfBookCompletionToUser("test2@gmail", "Test4", "Sam Cook", 2021, 5, 17);
		    System.out.println("");
		    System.out.println("List books of users");
		    System.out.println("");
		    System.out.println("Tester-----");
		    service.ListBooksOfUser("test@gmail");
		    System.out.println("");
		    System.out.println("Tester2-------");
		    service.ListBooksOfUser("test2@gmail");
		    System.out.println("");
		    System.out.println("Show profile");
		    service.ShowUserProfile("test@gmail");
		    service.ShowUserProfile("test2@gmail");
		    
		  }

}
