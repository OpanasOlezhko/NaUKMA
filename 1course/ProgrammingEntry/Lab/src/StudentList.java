

import java.io.IOException;
import static javax.swing.UIManager.getString;
/**
 * Написати програму, що: зчитує студентів групи і записує в масив
 * виводить на екран інформацію про студентів
 * виводить на екран всіх студентів які починаються на вказану літеру (літеру прочитати з клавіатури)
 * @author Max Loshak
 */
public class StudentList {
    public String groupName;
    Student[] students;
    /**
     * пустий конструктор з вводом з консолі
     */
    public StudentList() {
        this.groupName = DataInput.getStr("Enter the name of the group: ");
        int numStudents = DataInput.getInt("Enter the number of students: ");
        students = new Student[numStudents];
        for (int i=0; i<numStudents;i++){
            students[i]=new Student();
        }
    }

    /**
     * Повний конструктор
     * @param students
    //     * @param faculty
    //     * @param age
     */
    public StudentList(Student[] students){
        this.students = students;
    }

    /**
     * пошук студентів за першою літерою
     */
    public void searchByFirstLetter() throws IOException {
        int counter = 0;
        System.out.println("What letter would you like to search for?");
        char searchLetter = DataInput.getChar();
        System.out.println("The students in the group with names starting with '" + searchLetter + "' are: ");
        for (int i = 0; i < students.length; i++) {
            if (students[i].name.charAt(0) == searchLetter|| students[i].name.charAt(0)+32 == searchLetter || students[i].name.charAt(0)-32 == searchLetter) {
                System.out.println(students[i] + " (average grade: " + students[i].grade+ ")");// + ", faculty: " + faculty[i] + ", age: " + age[i] + ")");
            }
            else if(students[i].name.charAt(0) != searchLetter)
                counter++;
        }
        if(counter== students.length)
            System.out.println("There are no students starting with: "+searchLetter);
        int answer=DataInput.getInt("Try again?\n1----Yes\n0----No");
        if(answer==1)
            searchByFirstLetter();
    }

    private void swap(int a, int b) {
        Student temp = this.students[a];
        this.students[a]=this.students[b];
        this.students[b]=temp;
    }


    public Student[] getStudents(){
        return students;
    }

    public void quickSortGrades(int low, int high){
        if(low<high){
            int j= partition(low,high);
            quickSortGrades(low,j-1);
            quickSortGrades(j+1,high);
        }
    }

    public void quickSortNames(int low, int high){
        if(low<high){
            int j=partitionChar(low,high);
            quickSortNames(low,j-1);
            quickSortNames(j+1,high);
        }
    }

    private int partition(int low, int high){
        double pivot = this.students[low].grade;
        int i =low;
        int j =high;
        while(i<j) {
            while(this.students[i].grade<=pivot&&i<this.students.length-1)
                i++;
            while(this.students[j].grade>pivot&&j>0)
                j--;
            if(i<j)
                swap(i,j);
        }
        swap(low,j);
        return j;
    }

    private int partitionChar(int low, int high){
        String pivot = this.students[low].name;
        int i =low;
        int j =high;
        while(i<j) {
            while(compare(pivot, this.students[i].name)&&i<this.students.length-1)
                i++;
            while(compare(pivot, this.students[j].name)==false&&j>0)
                j--;
            if(i<j)
                swap(i,j);
        }
        swap(low,j);
        return j;
    }

    public void reverse(){
        for(int i=0; i<this.students.length/2; i++){
            swap(i,this.students.length-1-i);
        }
    }

    private boolean compare(String pivot, String word){
        String comp="";
        int res = 0;
        if(pivot.length()>word.length())
            res=1;
        else if(pivot.length()<word.length())
            res=-1;
        if(res==0||res==-1)
            comp=pivot;
        else if (res==1)
            comp=word;
        for(int i=0; i<comp.length(); i++) {
            if (word.charAt(i) < pivot.charAt(i))
                return true;
            else if(word.charAt(i) > pivot.charAt(i))
                return false;
        }
        return true;
    }

//    public void printStudents(){
//        for (int i = 0; i < students.length; i++) {
//            System.out.print(students[i] + " (average grade: " + averageGrades[i]+ ")\n");
//        }
//    }
    /**
     * Вивід інформації про всіх студентів в консоль
     * @return toString
     */
    public String toString() {
        String sb = "";
        for (int i = 0; i < students.length; i++) {
            sb+=(students[i]);// + ", faculty: " + faculty[i] + ", age: " + age[i] + ")\n");
        }
        return "The students in the group are: \n"+sb;
    }
}
