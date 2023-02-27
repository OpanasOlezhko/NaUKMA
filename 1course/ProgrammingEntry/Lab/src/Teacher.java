
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

    public Teacher(TeacherList chair, String status, String name) {
        this.name = name;
        this.status = status;
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
        boolean state = true;
        String str = "";
        for (int i = 1; i < name.length(); i++) {
            if(!state) {
                if (name.charAt(i) == 32)
                    state = true;
                str += name.charAt(i);
            }
            if(state){
                if(name.charAt(i)>=97&&name.charAt(i)<=122)
                    str += (char) (name.charAt(i) - 32);
                else
                    str += name.charAt(i);
                state = false;
            }
        }
        return str;
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