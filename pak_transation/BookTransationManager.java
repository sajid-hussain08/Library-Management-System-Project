package pak_transation;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class BookTransationManager{
    ObjectOutputStream oos_tran = null;
    ObjectInputStream ois = null;
    File book_transaction_file = null ;
    ArrayList<BookTransation> book_transaction_list = null;
  @SuppressWarnings("unchecked")
    public BookTransationManager() {
        book_transaction_file = new File("book_transaction.dat");
        book_transaction_list = new ArrayList<BookTransation>();
        if (book_transaction_file.exists()) {
            try {
                ois = new ObjectInputStream(new FileInputStream(book_transaction_file));
                book_transaction_list = (ArrayList<BookTransation>) ois.readObject();
            } catch (ClassNotFoundException ce) {
            } catch (IOException Ioe) {}

        }
    }
    public boolean issueBook(int rollNo,int isbn){
        int total_books_issued = 0;

        for (BookTransation book_transaction : book_transaction_list){
            if ((book_transaction.getRollNo() == rollNo) && (book_transaction.getReturnDate() == null))
                total_books_issued +=1;
            if (total_books_issued >=3)
                return false;
        }
        String issue_Date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        BookTransation book_transaction = new BookTransation(isbn,rollNo,issue_Date,null);
        book_transaction_list.add(book_transaction);
        return true;
    }
    public boolean returnBook(int rollNo ,int isbn) {
        for (BookTransation book_transaction : book_transaction_list) {
            if ((book_transaction.getRollNo() == rollNo) && (book_transaction.getReturnDate() == null) && (book_transaction.getIsbn() == isbn)) {
                String return_Date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
                book_transaction.setReturnDate(return_Date);
                return true;
            }
        }
        return false;
    }
    public void writeToFile() {
        try {
            oos_tran = new ObjectOutputStream(new FileOutputStream(book_transaction_file));
            oos_tran.writeObject(book_transaction_list);
        }
        catch (IOException e){
            System.out.println(e);
        }
    }
    public void showAll(){
        for (BookTransation book_transaction : book_transaction_list)
            System.out.println(book_transaction);
    }

}
