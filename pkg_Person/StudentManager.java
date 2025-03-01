package pkg_Person;

import java.io.*;
import java.util.ArrayList;
import java.util.ListIterator;

public class StudentManager {
    ObjectOutputStream oos_student = null;
    ObjectInputStream ois_student = null;
    File student_file = new File("Student.dat");

    ArrayList<Student> student_list = null;

    public StudentManager() {
        student_list = new ArrayList<Student>();
        if (student_file.exists()) {
            try {
                ois_student = new ObjectInputStream(new FileInputStream(student_file));
                student_list = (ArrayList<Student>) ois_student.readObject();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException f) {
                f.printStackTrace();
            }
        }
    }

    public void addAStudent(Student student) {
        student_list.add(student);
    }

    public Student get(int rollNo) {
        for (Student student : student_list) {
            if (student.getRollNo() == rollNo)
                return student;
        }
        return null;
    }

    public void viewAllStudents() {
        for (Student student : student_list)
            System.out.println(student);
    }

    public boolean deletStudent(int delet_rollNo) {
        ListIterator<Student> student_iterator = (ListIterator<Student>) student_list.listIterator();
        while (student_iterator.hasNext()) {
            Student student = student_iterator.next();
            if (student.getRollNo() == delet_rollNo) {
                student_list.remove(student);
                return true;
            }
        }
        return false;
    }

    public boolean updateStudent(int update_rollNo, String name, String emailId, String phoneNo, String address, String dob, int std, String division) {
        ListIterator<Student> student_iterator = (ListIterator<Student>) student_list.listIterator();
        while (student_iterator.hasNext()) {
            Student student = student_iterator.next();
            if (student.getRollNo() == update_rollNo) {
                student.setAddress(address);
                student.setDivision(division);
                student.setStd(std);
                student.setDob(dob);
                student.setEmailId(emailId);
                student.setPhoneNo(phoneNo);
                student.setName(name);
                return true;
            }
        }
        return false;
    }
    public void writeToFile(){
        try{
            oos_student = new ObjectOutputStream(new FileOutputStream(student_file));
            oos_student.writeObject(student_list);
        }
        catch(IOException ie){
            ie.printStackTrace();
        }
    }
}
