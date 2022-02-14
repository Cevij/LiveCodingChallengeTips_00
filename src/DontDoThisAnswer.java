import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class DontDoThisAnswer {
  public static class User{
    
   public User(String name, String email){
     this._name = name;
     this._email = email;
     this.list = new ArrayList<Books>();
     this.map = new HashMap<Books, String>();
     
   }
    
    private HashMap<Books, String> map;
    private List<Books> list;
    private String _name;
    private String _email;
    
    public String getName(){
      return this._name;
    }
    
    public String getEmail(){
      return this._email;
    }
    
    public void hasRead(Books book, String data){
     this.list.add(book);
      this.map.put(book, data);
      
    }
    
    public List<Books> listOfBooks(){
     return this.list; 
    }
    
    public String getDate(Books book){
     return this.map.get(book); 
    }
  }
  
  public static class Books{
    
   public Books(String title, String author){
     this._title = title;
     this._author = author;
   }
    
    private String _title;
    private String _author;
    
    public String getTitle(){
      return this._title;
    }
    
    public String getAuthor(){
      return this._author;
    }
    
  }
  
  public static void listBooks(User user){
    for(Books book: user.listOfBooks()){
     System.out.println(book.getTitle() + " - " + book.getAuthor()); 
    }
  }
  
  public static void main(String[] args) {
    HashMap<String, User> map = new HashMap<String, User>();
    HashMap<String, Books> mapBooks = new HashMap<String, Books>();
    
    User user_1 = new User("Bob", "gmail");
    User user_2 = new User("Bob Kane", ".gmail.com");
    Books book = new Books("big boook","Sam");
    Books book2 = new Books("big boook 2","Sam putman");
    user_1.hasRead(book, "2022-01-22");
    
    map.put(user_1.getEmail(),user_1);
    map.put(user_2.getEmail(),user_2);
    mapBooks.put(book.getTitle() + " - " + book.getAuthor(),book);
    mapBooks.put(book2.getTitle() + " - " + book2.getAuthor(),book2);

    listBooks(user_1);
    
    for(User entry : map.values()) {
      System.out.println(entry.getName());
    }
    
    for(Books entry : mapBooks.values()) {
      System.out.println(entry.getTitle());
    }
  }
}