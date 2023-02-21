public class Student {

    String name;
    double grade;
    String groupName;
    String chair;

    public Student(StudentList group) {
        this.name = DataInput.getStr("Enter the name of a student: ");
        this.grade = DataInput.getDouble("Enter the average grade of the "+this.name+":");
        this.groupName = group.groupName;
        this.chair = group.chair;
    }

//    public Student(String name, double grade) throws IOException {
//        this.name=name;
//        this.grade=grade;
//    }

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

    public String getGroupName() {
        return groupName;
    }

    public String getChair() {
        return chair;
    }

    public String toString(){
        String s =this.name+"---(average grade: "+this.grade+")\n";
        return s;
    }
}