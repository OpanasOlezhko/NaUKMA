import java.sql.SQLOutput;

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
                if (ans == 3)
                    screen = Screen.Main;
                else {
                    Faculty faculty = university.chooseFaculty();
                    if (ans == 0)
                        faculty.addChair();
                    else if (ans == 1)
                        faculty.removeChair();
                    else if (ans == 2)
                        faculty.changeChairName();
                }
                action();

            }
            case Group -> {
                ans = DataInput.getInt("How would you like to interact with group?\n0-----Add\n1-----Remove" +
                        "\n2-----Edit\n3-----Back");
                if (ans == 3)
                    screen = Screen.Main;
                else {
                    Faculty faculty = university.chooseFaculty();
                    TeacherList chair = faculty.chooseChair();
                    if (ans == 0) {
                        StudentList group = new StudentList(chair);
                        chair.addGroup(group);
                    } else if (ans == 1)
                        chair.removeGroup();
                    else if (ans == 2)
                        chair.changeGroupName();
                }
                action();
            }
            case Teacher -> {
                ans = DataInput.getInt("How would you like to interact with teacher?\n0-----Add\n1-----Remove" +
                        "\n2-----Edit\n3-----Display all teachers of a faculty alphabetically" +
                        "\n4-----Display all teachers of certain chair alphabetically\n5-----Back");
                if (ans == 5)
                    screen = Screen.Main;
                else {
                    Faculty faculty = university.chooseFaculty();
                    TeacherList chair = faculty.chooseChair();
                    if (ans == 0) {
                        Teacher teacher = new Teacher(chair);
                        chair.addTeacher(teacher);
                    } else if (ans == 1) {
                        Teacher teacher = chair.chooseTeacher();
                        chair.removeTeacher(teacher);
                    } else if (ans == 2)
                        faculty.editTeacher();
                    else if (ans == 3)
                        faculty.allTeachersFromFacultySorted();
                    else if (ans == 4)
                        chair.allTeachersOfChairSorted();
                }
                action();
            }
            case Student -> {
                ans = DataInput.getInt("How would you like to interact with student?\n0-----Add\n1-----Remove" +
                        "\n2-----Edit\n3-----Display all students by courses\n4-----Display all students of a faculty alphabetically" +
                        "\n5-----Display all students of chair by courses\n6-----Display all students of chair alphabetically" +
                        "\n7-----Display all students of chair of certain course\n8-----Display all students of chair of certain course alphabetically" +
                        "\n9-----Back");
                if (ans == 9)
                    screen = Screen.Main;
                else {
                    Faculty faculty;
                    TeacherList chair;
                    StudentList group;
                    if(ans<2){
                        faculty = university.chooseFaculty();
                        chair = faculty.chooseChair();
                        group = chair.chooseGroup();
                        if (ans == 0) {
                            Student student = new Student(group);
                            group.addStudent(student);
                        } else if (ans == 1) {
                            Student student = group.chooseStudent();
                            group.removeStudent(student);
                        }
                    }
                    else if(ans > 2 &&ans <= 4){
                            faculty = university.chooseFaculty();
                            if (ans == 3)
                                faculty.allStudentsFromFacultySortedByCourses();
                            else if (ans == 4)
                                faculty.allStudentsFromFacultySorted();
                        }
                    else{
                        faculty = university.chooseFaculty();
                        chair = faculty.chooseChair();
                            if (ans == 2)
                                chair.editStudent();
                            else if (ans == 5)
                                chair.allStudentsOfChairSortedByCourses();
                            else if (ans == 6)
                                chair.allStudentsFromChairSorted();
                            else if (ans == 7)
                                chair.allStudentsOfChairOfSpecifiedCourse();
                            else if (ans == 8)
                                chair.allSortedStudentsOfChairOfSpecifiedCourse();
                        }
                    }
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
