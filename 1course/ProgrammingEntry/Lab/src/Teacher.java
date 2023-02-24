
public class Teacher {
    TeacherList chair;
    String name;
    String chairName;
    String status;

    String facultyName;

    public Teacher(TeacherList chair){
        this.name = DataInput.getStr("Name: ");
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