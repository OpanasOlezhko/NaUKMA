public class Student {
    StudentList group;
    String name;
    double grade;
    String groupName;
    String chair;
    int course;

    public Student(StudentList group) {
        this.name = spellingCheck(DataInput.getString("Enter the name of a student: "));
        this.grade = grade();
        this.groupName = group.groupName;
        this.chair = group.chairName;
        this.course = group.course;
        this.group = group;
    }

    public Student(StudentList group, String name, double grade) {
        this.name = name;
        this.grade = grade;
        this.groupName = group.groupName;
        this.chair = group.chairName;
        this.course = group.course;
        this.group = group;
    }

    private double grade(){
        double grade = DataInput.getDouble("Enter the average grade of the "+this.name+":");
        if(grade>100||grade<0){
            System.out.println("The grade ca be [0; 100] only!");
            grade();
        }
        return grade;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setGrade(double grade){
        this.grade= grade;
    }

    public void changeGroup(StudentList groupNew){
        group.chair.removeStudent(this, group);
        group.chair.addStudent(this, groupNew);
        this.groupName = groupNew.groupName;
        this.chair = groupNew.chairName;
        this.course = groupNew.course;
        this.group = groupNew;
    }

    public String spellingCheck(String name){
        boolean state = true;
        String str = "";
        for (int i = 0; i < name.length(); i++) {
            if(!state) {
                if (name.charAt(i) == 32)
                    state = true;
                str += name.charAt(i);
            }
            else if(state){
                if(name.charAt(i)>=97&&name.charAt(i)<=122)
                    str += (char) (name.charAt(i) - 32);
                else
                    str += name.charAt(i);
                state = false;
            }
        }
        return str;
    }

    public int getCourse() {
        return course;
    }

    public String getName(){
        return this.name;
    }
    public String toString(){
        String s =this.name+"---(average grade: "+this.grade+" group: "+groupName+" chair: "+chair+" course: "+course+")\n";
        return s;
    }
}