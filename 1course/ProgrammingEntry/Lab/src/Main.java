import java.sql.SQLOutput;
import java.text.DecimalFormat;
import java.util.Random;

public class Main {
    static Screen screen;
    public static final String[] STATUS = {
            "Head teacher",
            "Dean",
            "Practice teacher",
            "Trainee teacher",
            "Lector"
    };
    public static final String[] SURNAMES = {
            "Andrushko",
            "Baranovskyi",
            "Boiko",
            "Bondarenko",
            "Borysenko",
            "Butko",
            "Vasylenko",
            "Havryliuk",
            "Honcharenko",
            "Hordiienko",
            "Danylenko",
            "Demyanenko",
            "Derevyanko",
            "Zakharchuk",
            "Zakharenko",
            "Kovalenko",
            "Kovalchuk",
            "Korniienko",
            "Kravchenko",
            "Kuzmenko",
            "Lysenko",
            "Lysychenko",
            "Melnychenko",
            "Ostapchuk",
            "Petrenko",
            "Polishchuk",
            "Ponomarenko",
            "Romanenko",
            "Savchenko",
            "Sydorenko",
            "Solomko",
            "Tarasenko",
            "Tkachenko",
            "Fedorenko",
            "Kharchenko",
            "Shevchenko"
    };
    public static final String[] NAMES = {
            "Andrii",
            "Anzhelika",
            "Bohdan",
            "Valerii",
            "Vasyl",
            "Victor",
            "Halyna",
            "Hanna",
            "Daria",
            "Ivan",
            "Iryna",
            "Kateryna",
            "Kostiantyn",
            "Liubov",
            "Mariiana",
            "Marina",
            "Mykola",
            "Nataliia",
            "Oksana",
            "Oleksandr",
    };
    public static final Random RANDOM = new Random();
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
                    if(ans<2||ans==11){
                        faculty = university.chooseFaculty();
                        chair = faculty.chooseChair();
                        group = chair.chooseGroup();
                        if (ans == 0) {
                            Student student = new Student(group);
                            chair.addStudent(student, group);
                        }if (ans == 1) {
                            Student student = group.chooseStudent();
                            chair.removeStudent(student, group);
                        } else if (ans == 11) {
                            for (int i=0; i<group.students.length; i++)
                                System.out.print(group.students[i]);
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
                        System.out.println(university.searchStudent());
                    } else if (ans == 10) {
                        System.out.println(university.searchStudentByCourse());
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
            if(i==0) name = "Informatics";
            else name = "Math";
            university.faculties[i]=new Faculty(2, name);
            for(int j = 0; j<university.faculties[i].chairCount; j++){
                if(i==0) {
                    if(j==0)name = "Software Engineering";
                    else if(j==1) name = "Computer science";
                }
                else {
                    if(j==0)name = "Applied Math";
                    if(j==1)name = "Statistics";
                }
                university.faculties[i].chairs[j]= new TeacherList(university.faculties[i], name, randomNumber(2, 4), 50);
                for(int l = 0; l<university.faculties[i].chairs[j].teachersCount; l++){
                    university.faculties[i].chairs[j].teachers[l]= new Teacher(university.faculties[i].chairs[j], randomStatus(), randomName());
                }
                university.faculties[i].chairs[j].students = new StudentList(university.faculties[i].chairs[j], university.faculties[i].chairs[j].studentsCount, randomNumber(1, 4), "Група");
                for (int k=0; k<university.faculties[i].chairs[j].studentsCount; k++)
                    university.faculties[i].chairs[j].students.students[k] = new Student(university.faculties[i].chairs[j].students, randomName(), randomDouble(60, 100));
                university.faculties[i].chairs[j].arrangeIntoGroups();
            }
        }
        return university;
    }
    public static String randomName() {
        String randomSurname = SURNAMES[RANDOM.nextInt(35)];
        String randomName = NAMES[RANDOM.nextInt(20)];
        String res = randomSurname + " " + randomName;
        return res;
    }
    public static String randomStatus(){
        return STATUS[RANDOM.nextInt(5)];
    }
    public static int randomNumber(int min, int max) {
        return RANDOM.nextInt(max - min + 1) + min;
    }
    public static double randomDouble(int min, int max) {
        double value = RANDOM.nextDouble(max-min+1)+min;
        DecimalFormat df = new DecimalFormat("# #");
        String formattedValue = df.format(value);
        double roundedValue = Double.parseDouble(formattedValue);
        return roundedValue;
    }
}

