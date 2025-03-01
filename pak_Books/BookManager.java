package pak_Books;

import java.io.*;
import java.util.ArrayList;
import java.util.ListIterator;


public class BookManager {
    ObjectOutputStream oos = null;
    ObjectInputStream ois = null;
    File book_file = new File("Books.dat");

    ArrayList <Book> Book_list = null;
  public BookManager()  {
      Book_list = new ArrayList<Book>();
      if (book_file.exists()){
          try {
              ois = new ObjectInputStream(new FileInputStream(book_file));
              Book_list = (ArrayList<Book>) ois.readObject();
          }
          catch (IOException e) {
              e.printStackTrace();
          }
          catch (ClassNotFoundException cne) {
              cne.printStackTrace();
          }
          }
      }
      public void listBookBySubject(String subject){
       for (Book book : Book_list){
           if (book.getSubject().equals(subject))
               System.out.println(book);
       }
      }
      public void addABook(Book book) {
      Book_list.add(book);
  }
  public void viewAllBook(){
      for (Book book :Book_list)
          System.out.println(book);
  }
  public Book searchBookByIsbn(int search_isbn){
      for(Book book: Book_list)
          if (book.getIsbn()== search_isbn) {
              return book;
          }
      return null;
  }
  public boolean upDateBook(int update_isbn, String title,String author,String publisher,int edition,String subject,int available_Quantity){
      ListIterator<Book> book_iterator = (ListIterator<Book>) Book_list.listIterator();
      while (book_iterator.hasNext()){
          Book book = book_iterator.next();
          if (book.getIsbn()==update_isbn){
              book.setAuthor(author);
              book.setEdition(edition);
              book.setPublisher(publisher);
              book.setSubject(subject);
              book.setTitle(title);
              book.setAvailable_Quantity(available_Quantity);
              return true;
          }
      }
      return false;
  }
    public void writeToFile() throws IOException {
      oos = new ObjectOutputStream(new FileOutputStream(book_file));
      oos.writeObject(Book_list);
    }

    public boolean deletBook(int delet_isbn){
        ListIterator<Book> book_iterator = (ListIterator<Book>) Book_list.listIterator();
        while (book_iterator.hasNext()){
            Book book = book_iterator.next();
            if (book.getIsbn()==delet_isbn){
                Book_list.remove(book);
                return true;
            }
        }
        return false;
    }

}
