import java.util.Random;

public class TeacherList {
    Faculty faculty;
    public int teachersCount = 0;
    public int studentsCount = 0;
    public int groupsCount = 0;
    public String chairName;
    public String facultyName;

    Teacher[] teachers;
    StudentList students;
    StudentList[] groups;
    public static final Random RANDOM = new Random();

    public TeacherList(Faculty faculty){
        this.faculty = faculty;
        this.chairName = name();
        this.facultyName = faculty.facultyName;
        this.teachers = new Teacher[teachersCount];
        this.groups = new StudentList[groupsCount];
        this.students = new StudentList(this, 0, 0, "STUDENTS");
    }

    public TeacherList(Faculty faculty, String chairName, int teachersCount, int studentsCount){
        this.faculty = faculty;
        this.chairName = chairName;
        this.facultyName = faculty.facultyName;
        this.teachersCount = teachersCount;
        this.groupsCount = 5;
        this.studentsCount = studentsCount;
        this.teachers = new Teacher[teachersCount];
        this.groups = new StudentList[groupsCount];
    }
    private String name(){
        String name = DataInput.getStr("Enter the name of chair: ");
        for (int i = 0; i< faculty.chairCount; i++){
            if(name.equals(faculty.chairs[i].chairName)){
                System.out.println("This name is already taken!");
                name();
            }
        }
        return name;
    }

    public void arrangeIntoGroups() {
        students.quickSortNames(0, students.students.length - 1);
        int capacity = students.students.length / 5;
        for (int i = 0; i < 4; i++) {
            groups[i] = new StudentList(this, capacity, 1 + RANDOM.nextInt(3), "Group " + (i + 1));
            for (int j = 0; j < capacity; j++) {
                groups[i].students[j] = students.students[i * capacity + j];
                students.students[i * capacity + j].groupName = groups[i].groupName;
                students.students[i * capacity + j].course = groups[i].course;
            }
        }
        groups[4] = new StudentList(this, students.students.length-capacity*4, 1, "Group 5");
        for (int i = capacity * 4; i < students.students.length; i++) {
            groups[4].students[i-capacity*4] = students.students[i];
            students.students[i].groupName = "Group 5";
            students.students[i].course = 1;
        }
    }

    public void addStudent(Student student, StudentList group){
        Student[] arr = new Student[group.students.length+1];
        Student[] arr1 = new Student[students.students.length+1];
        for (int i=0; i<group.students.length; i++){
            arr[i] = group.students[i];
        }
        for (int i=0; i<students.students.length; i++)
            arr1[i] = students.students[i];
        arr[group.students.length]=student;
        arr1[students.students.length] = student;
        group.students=arr;
        students.students = arr1;
    }
    public void removeStudent(Student student, StudentList group){
        int j=0;
        int f=0;
        Student[] arr = new Student[group.students.length-1];
        Student[] arr1 = new Student[students.students.length-1];
        for (int i=0; i<group.students.length; i++){
            if(group.students[i]==student)
                j=1;
            else
                arr[i-j] = group.students[i];
        }
        for (int i = 0; i < students.students.length; i++) {
            if (students.students[i] == student)
                f = 1;
            else
                arr1[i - f] = students.students[i];
        }
        students.students = arr1;
        group.students=arr;

    }

    // 8 завдання
    public String allTeachersOfChairSorted() {
        for(int j = 0; j < teachers.length - 1; j++) {
            for(int i = j + 1; i < teachers.length; i++) {
                if(teachers[j].getName().compareTo(teachers[i].getName()) > 0) {
                    Teacher temp = teachers[j];
                    teachers[j] = teachers[i];
                    teachers[i] = temp;
                }
            }
        }
        return teachersToString();
    }

    private String teachersToString() {
        StringBuilder str = new StringBuilder();
        for(Teacher t: teachers) {
            str.append(t.toString());
        }
        return str.toString();
    }
    // 8 завдання
    public String allStudentsFromChairSorted() {
        for (int j = 0; j < students.students.length - 1; j++) {
            for (int i = j + 1; i < students.students.length; i++) {
                if (students.students[j].getName().compareTo(students.students[i].getName()) > 0) {
                    Student temp = students.students[j];
                    students.students[j] = students.students[i];
                    students.students[i] = temp;
                }
            }
        }
        return studentsToString(students.students);
    }

    // 7 завдання
    public String allStudentsOfChairSortedByCourses() {
        boolean isFinished = false;
        while(!isFinished) {
            isFinished = true;
            for (int i = 1; i < students.students.length; i++) {
                if (students.students[i].getCourse() <
                        students.students[i - 1].getCourse()) {
                    Student temp = students.students[i];
                    students.students[i] = students.students[i - 1];
                    students.students[i - 1] = temp;
                    isFinished = false;
                }
            }
        }
        return studentsToString(students.students);
    }

    // 9 завдання
    public String allStudentsOfChairOfSpecifiedCourse() {
        int curCourse;
        do {
            curCourse = DataInput.getInt("Enter the course from which" +
                    " you want to receive students of the chair: ");
        } while(curCourse < 1 || curCourse > 4);
        Student[] studentsWithSpecifiedCourse = new Student[1000];
        int i = 0;
        for(Student ss : students.students) {
            if(ss.getCourse() == curCourse) {
                studentsWithSpecifiedCourse[i++] = ss;
            }
        }
        if(studentsWithSpecifiedCourse[0] == null) {
            return "There are no students with this course\n";
        }
        Student[] withoutNull = new Student[i];
        int index = 0;
        for(int k = 0; k < studentsWithSpecifiedCourse.length; k++) {
            if(studentsWithSpecifiedCourse[k] != null) {
                withoutNull[index++] = studentsWithSpecifiedCourse[k];
            } else {
                break;
            }
        }
        studentsWithSpecifiedCourse = withoutNull;
        System.out.println("Students in the "+curCourse+" course: ");
        return studentsToString(studentsWithSpecifiedCourse);
    }


    // 10 завдання
    public String allSortedStudentsOfChairOfSpecifiedCourse() {
        int curCourse;
        do {
            curCourse = DataInput.getInt("Enter the course from which" +
                    "you want to receive students of the chair: ");
        } while(curCourse < 1 || curCourse > 4);
        Student[] studentsWithSpecifiedCourse = new Student[1000];
        int i = 0;
        for(Student ss : students.students) {
            if(ss.getCourse() == curCourse) {
                studentsWithSpecifiedCourse[i++] = ss;
            }
        }
        if(studentsWithSpecifiedCourse[0] == null) {
            return "There are no students with this course\n";
        }
        Student[] withoutNull = new Student[i];
        int index = 0;
        for(int k = 0; k < studentsWithSpecifiedCourse.length; k++) {
            if(studentsWithSpecifiedCourse[k] != null) {
                withoutNull[index++] = studentsWithSpecifiedCourse[k];
            } else {
                break;
            }
        }
        studentsWithSpecifiedCourse = withoutNull;
        for(int j = 0; j < studentsWithSpecifiedCourse.length - 1; j++) {
            for(int k = j + 1; k < studentsWithSpecifiedCourse.length; k++) {
                if(studentsWithSpecifiedCourse[j].getName().
                        compareTo(studentsWithSpecifiedCourse[k].getName()) > 0) {
                    Student temp = studentsWithSpecifiedCourse[j];
                    studentsWithSpecifiedCourse[j] = studentsWithSpecifiedCourse[k];
                    studentsWithSpecifiedCourse[k] = temp;
                }
            }
        }
        return studentsToString(studentsWithSpecifiedCourse);
    }

    private String studentsToString(Student[] student) {
        StringBuilder sb = new StringBuilder();
        for(Student s: student) {
            sb.append(s.toString());
        }
        return sb.toString();
    }
    public void addTeacher(Teacher teacher){
        Teacher[] arr = new Teacher[teachersCount+1];
        for (int i=0; i<teachersCount; i++){
            arr[i] = teachers[i];
        }
        arr[teachersCount]=teacher;
        teachers=arr;
        teachersCount++;
    }

    public void removeTeacher(Teacher teacher){
        int j=0;
        Teacher[] arr = new Teacher[teachersCount-1];
        for (int i=0; i<teachersCount; i++){
            if(teachers[i]==teacher)
                j=1;
            else arr[i-j] = teachers[i];
        }
        teachers=arr;
        teachersCount--;
    }

    public void addGroup(StudentList group){
        StudentList[] arr = new StudentList[groupsCount+1];
        for (int i=0; i<groupsCount; i++){
            arr[i] = groups[i];
        }
        arr[groupsCount]=group;
        groups=arr;
        groupsCount++;
    }

    public void removeGroup(){
        int j=0;
        StudentList group = chooseGroup();
        int k=group.students.length;
        StudentList[] arr = new StudentList[groupsCount-1];
        for (int i=0; i<k; i++){
            removeStudent(group.students[0], group);
        }
        for (int i=0; i<groupsCount; i++){
            if(groups[i]==group)
                j=1;
            else
                arr[i-j] = groups[i];
        }
        groups=arr;
        groupsCount--;
    }
    public void editStudent(){
        StudentList group = chooseGroup();
        Student student = group.chooseStudent();
        int ans = DataInput.getInt("What to change about "+student.name+"?\n0-----Name\n1-----Grade\n2-----Group");
        if (ans == 0){
            String name = DataInput.getStr("Enter new name: ");
                    student.setName(name);
        }
        else if (ans == 1) {
            double grade = DataInput.getDouble("Enter new average grade: ");
            student.setGrade(grade);
        }
        else if (ans ==2){
            System.out.println("Choose new group:");
            group = chooseGroup();
            student.changeGroup(group);
        }
    }
    public StudentList chooseGroup(){
        String names ="";
        for (int i=0; i< groups.length; i++){
            names+=i+"-----"+groups[i].groupName;
            if(i!=groups.length-1)
                names+="\n";
        }
        int ans = DataInput.getInt("Choose the group:\n"+names);
        return groups[ans];
    }
    public Teacher chooseTeacher(){
        String names ="";
        for (int i=0; i< teachersCount; i++){
            names+=i+"-----"+teachers[i];
            if(i!=teachersCount-1)
                names+="\n";
        }
        int ans = DataInput.getInt("Choose the teacher:\n"+names);
        return teachers[ans];
    }
    public void changeGroupName(){
        StudentList group = chooseGroup();
        group.setGroupName();
    }
    public void changeTeacherName(){
        Teacher teacher = chooseTeacher();
        String name = DataInput.getStr("Enter new name of "+teacher.name+": ");
        teacher.setName(name);
    }
    public void setChairName(){
        this.chairName = DataInput.getStr("Enter the new name of the faculty '"+chairName+"' :");
    }
}
