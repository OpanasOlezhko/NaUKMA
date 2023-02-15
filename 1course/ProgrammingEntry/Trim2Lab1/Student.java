import javax.xml.crypto.Data;
import java.io.IOException;

public class Student {

    String name;

    double grade;

    public Student() throws IOException {
        System.out.println("Enter the name of a student:");
        this.name = DataInput.getString();
        this.grade = DataInput.getDouble("Enter the average grade of the "+this.name+":");

    }

    public Student(String name, double grade) throws IOException {
        this.name=name;
        this.grade=grade;
    }

    public void setName(String name){
        this.name=name;
    }

    public void setGrade(double grade){
        this.grade=grade;
    }

    public String getName(){
        return this.name;
    }

    public double getGrade(){
        return this.grade;
    }

    public String toString(){
        String s =this.name+"---(average grade: "+this.grade+")\n";
        return s;
    }
}