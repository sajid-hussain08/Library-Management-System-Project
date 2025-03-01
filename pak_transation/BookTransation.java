package pak_transation;

import java.io.Serializable;

public class BookTransation implements Serializable {
    private int isbn;
    private int rollNo;
    private String issuDate;
    private String returnDate;

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public int getRollNo() {
        return rollNo;
    }

    public void setRollNo(int rollNo) {
        this.rollNo = rollNo;
    }

    public String getIssuDate() {
        return issuDate;
    }

    public void setIssuDate(String issuDate) {
        this.issuDate = issuDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public BookTransation(int isbn,int rollNo,String issuDate,String returnDate) {
        this.isbn = isbn;
        this.rollNo = rollNo;
        this.issuDate = issuDate;
        this.returnDate = returnDate;
    }

    @Override
    public String toString() {
        return "BookTransation{" +
                "isbn=" + isbn +
                ", rollNo=" + rollNo +
                ", issuDate='" + issuDate + '\'' +
                ", returnDate='" + returnDate + '\'' +
                '}';
    }
}
