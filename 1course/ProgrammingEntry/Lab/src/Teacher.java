
public class Teacher {
    TeacherList chair;
    String name;
    String chairName;
    String status;

    String facultyName;

    public Teacher(TeacherList chair){
        this.name = spellingCheck(DataInput.getStr("Enter teacher's name: "));
        this.status = DataInput.getStr("Status: ");
        this.chairName = chair.chairName;
        this.facultyName = chair.faculty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChairName() {
        return chairName;
    }

    public void changeChair(TeacherList chairNew) {
        chair.removeTeacher(this);
        chairNew.addTeacher(this);
        this.chairName = chairNew.chairName;
        this.facultyName = chairNew.faculty;
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


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String toString() {
        return "Teacher(name='" + name + "', chair='" + chairName + "', status='" + status + "')";
    }
}