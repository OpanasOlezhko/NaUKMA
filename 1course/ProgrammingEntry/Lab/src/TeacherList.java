import java.io.IOException;
import java.util.Random;

public class TeacherList {
    public int teachersCount;
    public int studentsCount;
    public int groupsCount;
    public String chairName;
    public String faculty;

    Teacher[] teachers;
    StudentList students;
    StudentList[] groups;
    public static final Random RANDOM = new Random();


    public TeacherList(Faculty faculty){
        this.chairName = DataInput.getStr("Enter the name of chair: ");
        this.faculty = faculty.facultyName;
        this.teachersCount = DataInput.getInt("Enter the amount of teachers in "+chairName+" chair: ");
        this.groupsCount = 5;//DataInput.getInt("Enter the amount of student groups in "+chairName+" chair: ");
        this.teachers = new Teacher[teachersCount];
        this.groups = new StudentList[groupsCount];
        addTeachers();
        addStudents();
    }

    public TeacherList(Faculty faculty, String chairName, int teachersCount, int studentsCount){
        this.chairName = chairName;
        this.faculty = faculty.facultyName;
        this.teachersCount = teachersCount;
        this.groupsCount = 5;
        this.studentsCount = studentsCount;
        this.teachers = new Teacher[teachersCount];
        this.groups = new StudentList[groupsCount];
    }

    public void arrangeIntoGroups() {
        students.quickSortNames(0, students.students.length - 1);
        int capacity = students.students.length / 5;
        for (int i = 0; i < 4; i++) {
            groups[i] = new StudentList(this, capacity, 1 + RANDOM.nextInt(3), "Группа " + (i + 1));
            for (int j = 0; j < capacity; j++) {
                groups[i].addStudent(students.students[i * capacity + j]);
                students.students[i * capacity + j].groupName = groups[i].groupName;
            }
        }
        groups[4] = new StudentList(this, students.students.length-capacity*4, 1, "Група 5");
        for (int i = capacity * 4; i < students.students.length; i++) {
            groups[4].addStudent(students.students[i]);
            students.students[i].groupName = "Група 5";
        }
    }



    // 8 завдання
    public String allTeachersOfChairSorted() {
        for(int j = 0; j < teachers.length - 1; j++) {
            for(int i = j + 1; i < teachers.length; i++) {
                if(teachers[j].getName().compareTo(teachers[i].getName()) < 0) {
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
        str.append("[");
        for (int i = 0; i < teachers.length; i++) {
            str.append(teachers[i].getName()+" - "+ teachers[i].getChairName()
                    +" - "+teachers[i].getStatus());
            if (i < teachers.length -1) {
                str.append(", ");
            }
        }
        str.append("]");
        return str.toString();
    }


    // 8 завдання
    private Student[] studentsHere = new Student[1000];

    boolean alreadyTogether = false;
    private void gatherAllTheStudents() {
        int p = 0;
        for(int i = 0; i < groups.length; i++) {
            for(int j = 0; j < groups[i].students.length; j++) {
                studentsHere[p++] = groups[i].students[j];
            }
        }
        Student[] withoutNull = new Student[p];
        int index = 0;
        for(int i = 0; i < studentsHere.length; i++) {
            if(studentsHere[i] != null) {
                withoutNull[index++] = studentsHere[i];
            } else {
                 break;
            }
        }
        studentsHere = withoutNull;
        alreadyTogether = true;
    }

    public String allStudentsFromChairSorted() {
        if(!alreadyTogether) {
            gatherAllTheStudents();
        }
        for(int j = 0; j < studentsHere.length - 1; j++) {
            for(int i = j + 1; i < studentsHere.length; i++) {
                if(studentsHere[j].getName().compareTo(studentsHere[i].getName()) < 0) {
                    Student temp = studentsHere[j];
                    studentsHere[j] = studentsHere[i];
                    studentsHere[i] = temp;
                }
            }
        }
        return studentsToString(studentsHere);
    }

    // 7 завдання
    public String allStudentsOfChairSortedByCourses() {
        if(!alreadyTogether) {
            gatherAllTheStudents();
        }
        boolean isFinished = false;
        while(!isFinished) {
            isFinished = true;
            for (int i = 1; i < studentsHere.length; i++) {
                if (studentsHere[i].getGrade() <
                        studentsHere[i - 1].getGrade()) {
                    Student temp = studentsHere[i];
                    studentsHere[i] = studentsHere[i - 1];
                    studentsHere[i - 1] = temp;
                    isFinished = false;
                }
            }
        }
        return studentsToString(studentsHere);
    }

    // 9 завдання
    public String allStudentsOfChairOfSpecifiedCourse() {
        int curCourse;
        do {
            curCourse = DataInput.getInt("Enter the course from which" +
                    "you want to receive students of the chair: ");
        } while(curCourse < 1 || curCourse > 4);
        StudentList[] groupWithSpecifiedCourse = new StudentList[1000];
        int i = 0;
        for(StudentList group : groups) {
            if(group.getCourse() == curCourse) {
                groupWithSpecifiedCourse[i++] = group;
            }
        }
        StudentList[] withoutNull = new StudentList[i];
        int index = 0;
        for(int k = 0; k < groupWithSpecifiedCourse.length; k++) {
            if(groupWithSpecifiedCourse[k] != null) {
                withoutNull[index++] = groupWithSpecifiedCourse[k];
            } else {
                break;
            }
        }
        groupWithSpecifiedCourse = withoutNull;
        int length = 0;
        for(StudentList group : groupWithSpecifiedCourse) {
            length += group.students.length;
        }
        Student[] studentsWithSpecifiedCourse = new Student[length];
        int ind = 0;
        for(int j = 0; j < groupWithSpecifiedCourse.length; j++) {
            for(int l = 0; l < groupWithSpecifiedCourse[j].students.length; l++) {
                studentsWithSpecifiedCourse[ind++] = groupWithSpecifiedCourse[j].students[l];
            }
        }
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
        Student[] ourStudents = allStudentsOfChairOfSpecifiedCourse(curCourse);
        for(int j = 0; j < ourStudents.length - 1; j++) {
            for(int i = j + 1; i < ourStudents.length; i++) {
                if(ourStudents[j].getName().compareTo(ourStudents[i].getName()) < 0) {
                    Student temp = ourStudents[j];
                    ourStudents[j] = ourStudents[i];
                    ourStudents[i] = temp;
                }
            }
        }
        return studentsToString(ourStudents);
    }

    private String studentsToString(Student[] student) {
        StringBuilder str = new StringBuilder();
        str.append("[");
        for (int i = 0; i < student.length; i++) {
            str.append(student[i].getName()+" - "+ student[i].getChair()
                    +" - "+student[i].getGroupName());
            if (i < student.length -1) {
                str.append(", ");
            }
        }
        str.append("]");
        return str.toString();
    }


    private Student[] allStudentsOfChairOfSpecifiedCourse(int curCourse) {
        StudentList[] groupWithSpecifiedCourse = new StudentList[1000];
        int i = 0;
        for(StudentList group : groups) {
            if(group.getCourse() == curCourse) {
                groupWithSpecifiedCourse[i++] = group;
            }
        }
        StudentList[] withoutNull = new StudentList[i];
        int index = 0;
        for(int k = 0; k < groupWithSpecifiedCourse.length; k++) {
            if(groupWithSpecifiedCourse[k] != null) {
                withoutNull[index++] = groupWithSpecifiedCourse[k];
            } else {
                break;
            }
        }
        groupWithSpecifiedCourse = withoutNull;
        int length = 0;
        for(StudentList group : groupWithSpecifiedCourse) {
            length += group.students.length;
        }
        Student[] studentsWithSpecifiedCourse = new Student[length];
        int ind = 0;
        for(int j = 0; j < groupWithSpecifiedCourse.length; j++) {
            for(int l = 0; l < groupWithSpecifiedCourse[j].students.length; l++) {
                studentsWithSpecifiedCourse[ind++] = groupWithSpecifiedCourse[j].students[l];
            }
        }
        return studentsWithSpecifiedCourse;
    }
    private void addTeachers(){
        for (int i=0; i<teachersCount; i++){
            System.out.println("Creating teacher "+(i+1));
                Teacher teacher = new Teacher(this);
                teachers[i] = teacher;
        }
    }

    private void addStudents(){
        for (int i=0; i<groupsCount; i++){
            System.out.println("Creating group "+(i+1));
                StudentList group = new StudentList(this);
                groups[i] = group;
        }
    }

    public void addTeacher(Teacher teacher){
        Teacher[] arr = new Teacher[teachersCount+1];
        for (int i=0; i<teachersCount; i++){
            arr[i] = teachers[i];
        }
        arr[teachersCount]=teacher;
        teachers=arr;
    }

    public void removeTeacher(Teacher teacher){
        Teacher[] arr = new Teacher[teachersCount-1];
        for (int i=0; i<teachersCount; i++){
            if(teachers[i]!=teacher)
                arr[i] = teachers[i];
        }
        teachers=arr;
    }

    public void addGroup(StudentList group){
        StudentList[] arr = new StudentList[groupsCount+1];
        for (int i=0; i<groupsCount; i++){
            arr[i] = groups[i];
        }
        arr[groupsCount]=group;
        groups=arr;
    }

    public void removeGroup(){
        StudentList group = chooseGroup();
        StudentList[] arr = new StudentList[groupsCount-1];
        for (int i=0; i<groupsCount; i++){
            if(groups[i]!=group)
                arr[i] = groups[i];
        }
        groups=arr;
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

    public void searchByFirstLetter() throws IOException {
        int counter = 0;
        System.out.println("What letter would you like to search for?");
        char searchLetter = DataInput.getChar();
        System.out.println("The students in the group with names starting with '" + searchLetter + "' are: ");
        for (int i = 0; i < teachers.length; i++) {
            if (teachers[i].name.charAt(0) == searchLetter|| teachers[i].name.charAt(0)+32 == searchLetter){
                System.out.println(teachers[i]);
            }
            else if(teachers[i].name.charAt(0) != searchLetter)
                counter++;
        }
        if(counter == teachers.length)
            System.out.println("There are no students starting with first letter: "+searchLetter);
    }

    public StudentList chooseGroup(){
        String names ="";
        for (int i=0; i< groups.length; i++){
            names+=i+"-----"+groups[i].groupName+"\n";
        }
        int ans = DataInput.getInt("Choose the group:\n"+names);
        return groups[ans];
    }
    public Teacher chooseTeacher(){
        String names ="";
        for (int i=0; i< teachersCount; i++){
            names+=i+"-----"+teachers[i]+"\n";
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
