public class Student {
    StudentList group;
    String name;
    double grade;
    String groupName;
    String chair;
    int course;

    public Student(StudentList group) {
        this.name = spellingCheck(DataInput.getStr("Enter the name of a student: "));
        this.grade = DataInput.getDouble("Enter the average grade of the "+this.name+":");
        this.groupName = group.groupName;
        this.chair = group.chair;
        this.course = group.course;
        this.group = group;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setGrade(double grade){
        this.grade= grade;
    }

    public void changeGroup(StudentList groupNew){
        group.removeStudent(this);
        groupNew.addStudent(this);
        this.groupName = groupNew.groupName;
        this.chair = groupNew.chair;
        this.course = groupNew.course;
        this.group = groupNew;
    }

    public String spellingCheck(String name){
        String str = "";
        if(name.charAt(0)>=97&&name.charAt(0)<=122) {
            str += (char) (name.charAt(0) - 32);
            for (int i = 1; i < name.length(); i++)
                str += name.charAt(i);
            return str;
        }
        return name;
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
        String s =this.name+"---(average grade: "+this.grade+" group: "+groupName+" chair: "+chair+" course: "+course+")\n";
        return s;
    }
}