import java.sql.SQLOutput;
import java.util.Random;

public class Main {
    static Screen screen;
    private static final int MIN_LENGTH = 5;
    private static final String LETTERS = "abcdefghijklmnopqrstuvwxyz";
    private static final Random RANDOM = new Random();
    static University university = setup();

    public static void main(String[] args) {
        screen = Screen.Main;
        action();
    }


    static void action() {
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
                        "\n4-----Display all teachers of certain chair alphabetically\n5-----Search teacher by his full name" +
                        "\n6-----Back");
                if (ans == 6)
                    screen = Screen.Main;
                else if(ans == 5){
                    if(university.searchTeacher()!=null)
                        System.out.println(university.searchTeacher());
                    else System.out.println("There are no teachers with such name");
                }
                else {
                    Faculty faculty = university.chooseFaculty();
                    TeacherList chair;
                    if (ans == 0) {
                        chair = faculty.chooseChair();
                        Teacher teacher = new Teacher(chair);
                        chair.addTeacher(teacher);
                    } else if (ans == 1) {
                        chair = faculty.chooseChair();
                        Teacher teacher = chair.chooseTeacher();
                        chair.removeTeacher(teacher);
                    } else if (ans == 2)
                        faculty.editTeacher();
                    else if (ans == 3)
                        System.out.println(faculty.allTeachersFromFacultySorted());
                    else if (ans == 4) {
                        chair  = faculty.chooseChair();
                        System.out.println(chair.allTeachersOfChairSorted());
                    }
                }
                action();
            }
            case Student -> {
                ans = DataInput.getInt("How would you like to interact with student?\n0-----Add\n1-----Remove" +
                        "\n2-----Edit\n3-----Display all students by courses\n4-----Display all students of a faculty alphabetically" +
                        "\n5-----Display all students of chair by courses\n6-----Display all students of chair alphabetically" +
                        "\n7-----Display all students of chair of certain course\n8-----Display all students of chair of certain course alphabetically" +
                        "\n9-----Search student by his full name\n10-----Search student by course\n11-----Search student by group\n12-----Back");
                if (ans == 12)
                    screen = Screen.Main;
                else {
                    Faculty faculty;
                    TeacherList chair;
                    StudentList group;
                    if(ans<2&&ans==11){
                        faculty = university.chooseFaculty();
                        chair = faculty.chooseChair();
                        group = chair.chooseGroup();
                        if (ans == 0) {
                            Student student = new Student(group);
                            group.addStudent(student);
                        } else if (ans == 1) {
                            Student student = group.chooseStudent();
                            group.removeStudent(student);
                        } else if (ans == 11) {
                            for (int i=0; i<group.students.length; i++)
                                System.out.println(group.students[i]);
                        }
                    }
                    else if(ans > 2 &&ans < 5){
                            faculty = university.chooseFaculty();
                            if (ans == 3)
                                System.out.println(faculty.allStudentsFromFacultySortedByCourses());
                            else if (ans == 4)
                                System.out.println(faculty.allStudentsFromFacultySorted());
                        }
                    else if (ans == 9){
                        if(university.searchStudent()!=null)
                            System.out.println(university.searchStudent());
                        else System.out.println("There are no students with such name");
                    } else if (ans == 10) {
                        university.searchStudentByCourse();
                    } else{
                        faculty = university.chooseFaculty();
                        chair = faculty.chooseChair();
                            if (ans == 2)
                                chair.editStudent();
                            else if (ans == 5)
                                System.out.println(chair.allStudentsOfChairSortedByCourses());
                            else if (ans == 6)
                                System.out.println(chair.allStudentsFromChairSorted());
                            else if (ans == 7)
                                System.out.println(chair.allStudentsOfChairOfSpecifiedCourse());
                            else if (ans == 8)
                                System.out.println(chair.allSortedStudentsOfChairOfSpecifiedCourse());
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


    public static University setup(){
        String name;
        University university = new University(2);
        for(int i=0; i<university.facultiesCount; i++){
            if(i==0)
                name = "Informatics";
            else name = "Computer science";
            university.faculties[i]=new Faculty(2, name);
            for(int j = 0; j<university.faculties[i].chairCount; j++){
                university.faculties[i].chairs[j]= new TeacherList(university.faculties[i], randomName(), randomNumber(1, 3), randomNumber(1,2));
                for(int l = 0; l<university.faculties[i].chairs[j].teachersCount; l++){
                    university.faculties[i].chairs[j].teachers[l]= new Teacher(university.faculties[i].chairs[j], randomName(), randomName());
                }
                for (int f=0; f<university.faculties[i].chairs[j].groupsCount; f++){
                    university.faculties[i].chairs[j].groups[f] = new StudentList(university.faculties[i].chairs[j], randomNumber(5, 10), randomNumber(1, 4), randomName());
                    for (int t=0; t<university.faculties[i].chairs[j].groups[f].students.length; t++){
                        university.faculties[i].chairs[j].groups[f].students[t]= new Student(university.faculties[i].chairs[j].groups[f], randomName(), randomNumber(60, 100));
                    }
                }
            }
        }
        return university;
    }
    public static String randomName() {
        int length = RANDOM.nextInt(6) + MIN_LENGTH;
        StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int index = RANDOM.nextInt(LETTERS.length());
            char randomChar = LETTERS.charAt(index);
            sb.append(randomChar);
        }
        return sb.toString();
    }
    public static int randomNumber(int min, int max) {
        return RANDOM.nextInt(max - min + 1) + min;
    }
}

