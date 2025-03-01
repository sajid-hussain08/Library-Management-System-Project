package pak_Main;

import pak_Books.Book;
import pak_Books.BookManager;
import pak_exception.BookNotFoundException;
import pak_exception.StudentNotFoundException;
import pak_transation.BookTransationManager;
import pkg_Person.Student;
import pkg_Person.StudentManager;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String [] args) throws StudentNotFoundException, IOException {
        int choice ;
        Scanner sc = new Scanner(System.in);
        BookManager bm = new BookManager();
        StudentManager sm = new StudentManager();
        BookTransationManager btm = new BookTransationManager();

        do{
            System.out.println("Enter 1 if Student \n Enter 2 if librarian \n Enter 3 if you want to exit ");
           choice = sc.nextInt();

           if (choice == 1) // if user is Student.
           {
               System.out.println("Enter your rollNo ");
               int rollNo = sc.nextInt();
               try{
                   Student s = sm.get(rollNo);
                   if (s == null)
                       throw new StudentNotFoundException();
                   int stud_choice;
                   do {
                       System.out.println("Enter 1 to view All Books \n Enter 2 to Search Book By isbn\n Enter 3 to List Book By Subject\n Enter 4 to issue a Book\n Enter 5 to Return A Book\nEnter 99 to exit ");
                       stud_choice = sc.nextInt();
                       switch (stud_choice){
                           case 1:
                               System.out.println("All the Book record are ");
                               bm.viewAllBook();
                               break;
                           case 2:
                               System.out.println("Please Search to Isbn ");
                               int search_isbn;
                               System.out.println("Enter search Book by isbn ");
                               search_isbn = sc.nextInt();
                               Book book = bm.searchBookByIsbn(search_isbn);
                               if (book == null)
                                   System.out.println("No book by search isbn in our library");
                               else
                                   System.out.println(book);
                               break;
                           case 3:
                               System.out.println("Enter the Subject to search ");
                               sc.nextLine();
                               String search_subject = sc.nextLine();
                               bm.listBookBySubject(search_subject);
                               break;
                           case 4:
                               System.out.println("Please Enter Isbn to Book issue ");
                               int issue_isbn = sc.nextInt();
                               book = bm.searchBookByIsbn(issue_isbn);
                               try {
                                   if (book == null) {
                                       throw new BookNotFoundException();
                                   }
                                   if (book.getAvailable_Quantity() > 0){
                                       if (btm.issueBook(rollNo,issue_isbn)){
                                           book.setAvailable_Quantity(book.getAvailable_Quantity()-1);
                                           System.out.println("Book has been issued");
                                       }
                                   }
                                   else{
                                       System.out.println("The book has been issued");
                                   }
                               }
                               catch (BookNotFoundException bnfe){
                                   System.out.println(bnfe);
                               }

                               break;
                           case 5:
                               System.out.println(" Please Enter the Isbn to Return a book ");

                               int return_isbn = sc.nextInt();
                               book = bm.searchBookByIsbn(return_isbn);
                               if (book !=null){
                                   if (btm.returnBook(rollNo,return_isbn)){
                                       book.setAvailable_Quantity(book.getAvailable_Quantity()+1);
                                       System.out.println("Thank you for returning this Book");
                                   }
                                   else
                                       System.out.println("Could not return the Book");
                               }
                               else
                                   System.out.println("Book with this isbn does not exists");

                               break;
                           case 99:
                               System.out.println("Thank you for using library ");
                               break;
                           default:
                               System.out.println("invalid choice ");
                       }
                   } while (stud_choice != 99);
                   }
               catch (StudentNotFoundException snfe){
                   System.out.println(snfe);
               }

           }
           else if (choice == 2){// user for librarian.
               int lib_choice;
               do {
                   System.out.println("Enter 11 to view All Student\nEnter 12 to print a Student By RollNo\nEnter 13 to register a student\nEnter 14 to Update a student\nEnter 15 to delete a student");
                   System.out.println("Enter 21 to view All Books\nEnter 22 to print a Books By Isbn\nEnter 23 to Add a new Book\nEnter 24 to Update a Books\nEnter 25 to delete a Books");
                   System.out.println("Enter 31 to view All Transaction");
                   System.out.println("Enter 99 to Exit ");
                   lib_choice = sc.nextInt();
                   switch (lib_choice) {
                       case 11:  // View to all student.
                           System.out.println("View All student records");
                           sm.viewAllStudents();
                           break;
                       case 12:  // student by based on roll no.
                           System.out.println("Enter RollNo to Fetch Student's Record");
                           int get_rollNo = sc.nextInt();
                           Student student = sm.get(get_rollNo);
                           if (student == null) {
                               System.out.println("No Record Match This Roll No");
                           } else
                               System.out.println(student);
                           break;
                       case 13:  // Students Adding.
                           System.out.println("Enter student  details to add");
                           String name;
                           String emailId;
                           String phoneNo;
                           String address;
                           String dob;
                           int rollNo;
                           int std;
                           String division;
                           sc.nextLine();
                           System.out.println("name");
                           name = sc.nextLine();
                           System.out.println("emailId");
                           emailId = sc.nextLine();
                           System.out.println("Phone No");
                           phoneNo = sc.nextLine();
                           System.out.println("Address");
                           address = sc.nextLine();
                           System.out.println("Date of Birth");
                           dob = sc.nextLine();
                           System.out.println("Roll No");
                           rollNo = sc.nextInt();
                           System.out.println("standard");
                           std = sc.nextInt();
                           System.out.println("Division");
                           division = sc.next();

                           student = new Student(name, emailId, phoneNo, address, dob, rollNo, std, division);
                           sm.addAStudent(student);
                           System.out.println("students is Added");
                           break;

                       case 14: // Update A student.
                           System.out.println("Enter the Roll no to Modify the Student");
                           int modify_rollNo;
                           modify_rollNo = sc.nextInt();
                           student = sm.get(modify_rollNo);
                           try {
                               if (student == null)
                                   throw new StudentNotFoundException();

                               sc.nextLine();
                               System.out.println("Name");
                               name = sc.nextLine();
                               System.out.println("emailId");
                               emailId = sc.nextLine();
                               System.out.println("Phone No");
                               phoneNo = sc.nextLine();
                               System.out.println("Address");
                               address = sc.nextLine();
                               System.out.println("Date of Birth");
                               dob = sc.nextLine();

                               System.out.println("standard");
                               std = sc.nextInt();
                               System.out.println("Division");
                               division = sc.next();
                               sm.updateStudent(modify_rollNo, name, emailId, phoneNo, address, dob, std, division);
                               System.out.println("Student Record updated");
                           } catch (StudentNotFoundException snfe) {
                               System.out.println(snfe);
                           }
                           break;
                       case 15: // To Delete A student.
                           System.out.println("Enter the Roll no to Delete the Student");
                           int delete_rollNo;
                           delete_rollNo = sc.nextInt();
                           if (sm.deletStudent(delete_rollNo))
                               System.out.println("Student Record is removed");
                           else
                               System.out.println("No Record with Roll No exists");
                           break;
                       case 21: // To view All Books.
                           bm.viewAllBook();
                           break;
                       case 22: // Search Books by ISBN
                           int search_isbn;
                           System.out.println("Enter search Book by isbn ");
                           search_isbn = sc.nextInt();
                           Book book = bm.searchBookByIsbn(search_isbn);
                           if (book == null)
                               System.out.println("No book by search isbn in our library");
                           else
                               System.out.println(book);
                           break;
                       case 23: // Add a new Book.
                           System.out.println("Enter the add Book details ");
                            int isbn;
                            String title;
                            String author;
                            String publisher;
                            int edition;
                            String subject;
                            int available_Quantity;
                            System.out.println("Enter ISBN");
                            isbn = sc.nextInt();
                            sc.nextLine();
                           System.out.println("Enter Title");
                           title = sc.nextLine();
                           System.out.println("Enter Author");
                           author = sc.nextLine();
                           System.out.println("Enter publisher");
                           publisher = sc.nextLine();
                           System.out.println("Enter edition");
                           edition = sc.nextInt();
                           sc.nextLine();
                           System.out.println("Enter Subject");
                           subject = sc.nextLine();
                           System.out.println("Enter Available Quantity");
                           available_Quantity = sc.nextInt();
                           book = new Book(isbn,title,author,publisher,edition,subject,available_Quantity);
                           bm.addABook(book);
                           System.out.println("A Book record is added ");
                           break;
                       case 24: // Update A Book.
                           int update_isbn;
                           System.out.println("please Enter the isbn and modify the Book");
                           update_isbn = sc.nextInt();
                           try{
                               book = bm.searchBookByIsbn(update_isbn);
                               if (book == null)
                                   throw new BookNotFoundException();
                               sc.nextLine();
                               System.out.println("Please Enter the Book Details to modify ");
                               System.out.println("Enter Title");
                               title = sc.nextLine();
                               System.out.println("Enter Author");
                               author = sc.nextLine();
                               System.out.println("Enter publisher");
                               publisher = sc.nextLine();
                               System.out.println("Enter edition");
                               edition = sc.nextInt();
                               sc.nextLine();
                               System.out.println("Enter Subject");
                               subject = sc.nextLine();
                               System.out.println("Enter Available Quantity");
                               available_Quantity = sc.nextInt();
                               bm.upDateBook(update_isbn,title,author,publisher,edition,subject,available_Quantity);
                           }
                           catch (BookNotFoundException bnfe){
                               System.out.println(bnfe);
                           }
                           break;
                       case 25:
                           int delete_isbn;
                           System.out.println("please Enter the isbn and Delete the Book");
                           delete_isbn = sc.nextInt();
                           try {
                               book = bm.searchBookByIsbn(delete_isbn);
                               if (book == null)
                                   throw new BookNotFoundException();
                               bm.deletBook(delete_isbn);
                           }
                           catch (BookNotFoundException bnfe){
                               System.out.println(bnfe);
                           }
                           System.out.println("Books record deleted successfully");
                           break;
                       case 31: // view All Transaction.
                           System.out.println("All the Transaction are");
                           btm.showAll();
                           break;
                       case 99:
                           System.out.println("Thank you for using library ");
                           break;
                       default:
                           System.out.println("invalid choice ");
                   }
               }while(lib_choice != 99);
           }
        }while (choice !=3);
        sm.writeToFile();
        bm.writeToFile();
        btm.writeToFile();
    }
}
