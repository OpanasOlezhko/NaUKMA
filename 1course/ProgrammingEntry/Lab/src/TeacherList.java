import java.io.IOException;

public class TeacherList {
    public static int teachersCount;
    public static int groupsCount;
    public static String chairName;

    Teacher[] teachers;
    StudentList[] groups;

    public TeacherList(){
        this.chairName = DataInput.getStr("Enter the name of chair: ");
        this.teachersCount = DataInput.getInt("Enter the amount of teachers in "+chairName+" chair: ");
        this.teachersCount = DataInput.getInt("Enter the amount of students in "+chairName+" chair: ");
        this.teachers = new Teacher[teachersCount];
        this.groups = new StudentList[groupsCount];
        addTeachers();
        addStudents();
    }
    public void addTeachers(){
        for (int i=0; i<teachersCount; i++){
            System.out.print("Creating teacher "+i);
                Teacher teacher = new Teacher();
                teachers[i] = teacher;
        }
    }

    public void addStudents(){
        for (int i=0; i<groupsCount; i++){
            System.out.print("Creating group "+i);
                StudentList group = new StudentList();
                groups[i] = group;
        }
    }

    public void addTeacher(){
        Teacher teacher = new Teacher();
        Teacher[] arr = new Teacher[teachersCount+1];
        for (int i=0; i<teachersCount; i++){
            arr[i] = teachers[i];
        }
        arr[teachersCount]=teacher;
        teachers=arr;
    }

    public void addGroup(){
        StudentList group = new StudentList();
        StudentList[] arr = new StudentList[groupsCount+1];
        for (int i=0; i<groupsCount; i++){
            arr[i] = groups[i];
        }
        arr[groupsCount]=group;
        groups=arr;
    }
}
