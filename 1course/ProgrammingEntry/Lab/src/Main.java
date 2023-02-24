public class Main {
    private static Screen screen;
    static University university = new University();


    public static void main(String[] args) {
        screen = Screen.Main;
        action();
    }


    private static void action() {
        int ans = 0;
        switch (screen) {
            case Main -> {
                ans = DataInput.getInt("What would you like to work with?\n0-----Faculty\n1-----Chair\n2-----Group" +
                        "\n3-----Teacher\n4-----Student");
                if (ans == 0)
                    screen = Screen.Faculty;
                else if (ans == 1)
                    screen = Screen.Chair;
                else if (ans == 2)
                    screen = Screen.Group;
                else if (ans == 3)
                    screen = Screen.Teacher;
                else if (ans == 4)
                    screen = Screen.Student;
                action();
            }
            case Faculty -> {
                ans = DataInput.getInt("How would you like to interact with faculty?\n0-----Add\n1-----Remove" +
                        "\n2-----Edit\n3-----Back");
                if (ans == 0)
                    university.addFaculty();
                else if (ans == 1)
                    university.removeFaculty();
                else if (ans == 2)
                    university.changeFacultyName();
                else if (ans == 3)
                    screen = Screen.Main;
                action();
            }
            case Chair -> {
                ans = DataInput.getInt("How would you like to interact with chair?\n0-----Add\n1-----Remove" +
                        "\n2-----Edit\n3-----Back");
                Faculty faculty = university.chooseFaculty();
                if (ans == 0)
                    faculty.addChair();
                else if (ans == 1)
                    faculty.removeChair();
                else if (ans == 2)
                    faculty.changeChairName();
                else if (ans == 3)
                    screen = Screen.Main;
                action();
            }
            case Group -> {
                ans = DataInput.getInt("How would you like to interact with group?\n0-----Add\n1-----Remove" +
                        "\n2-----Edit\n3-----Back");
                Faculty faculty = university.chooseFaculty();
                TeacherList chair = faculty.chooseChair();
                if (ans == 0){
                    StudentList group = new StudentList(chair);
                    chair.addGroup(group);
                }
                else if (ans == 1)
                    chair.removeGroup();
                else if (ans == 2)
                    chair.changeGroupName();
                else if (ans == 3)
                    screen = Screen.Main;
                action();
            }
            case Teacher -> {
                ans = DataInput.getInt("How would you like to interact with teacher?\n0-----Add\n1-----Remove" +
                        "\n2-----Edit\n3-----Back");
                Faculty faculty = university.chooseFaculty();
                TeacherList chair = faculty.chooseChair();
                if (ans == 0) {
                    Teacher teacher = new Teacher(chair);
                    chair.addTeacher(teacher);
                }
                else if (ans == 1) {
                    Teacher teacher = chair.chooseTeacher();
                    chair.removeTeacher(teacher);
                }
                else if (ans == 2)
                    faculty.editTeacher();
                else if (ans == 3)
                    screen = Screen.Main;
                action();
            }
            case Student -> {
                ans = DataInput.getInt("How would you like to interact with student?\n0-----Add\n1-----Remove" +
                        "\n2-----Edit\n3-----Back");
                Faculty faculty = university.chooseFaculty();
                TeacherList chair = faculty.chooseChair();
                StudentList group = chair.chooseGroup();
                if(ans == 0){
                    Student student = new Student(group);
                    group.addStudent(student);
                }
                else if (ans == 1){
                    Student student = group.chooseStudent();
                    group.removeStudent(student);
                }
                else if (ans == 2)
                    chair.editStudent();
                else if (ans == 3)
                    screen = Screen.Main;
                action();
            }
        }
    }



    enum Screen {
        Main,
        Faculty,
        Chair,
        Teacher,
        Group,
        Student
    }

    enum Choice {
        None,
        Create,
        Add,
        Edit,
        Remove
    }
}
