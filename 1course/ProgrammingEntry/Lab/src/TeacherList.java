public class TeacherList extends Faculty{
    public int teachersCount;
    public int groupsCount;
    public String chairName;
    public String faculty;

    Teacher[] teachers;
    StudentList[] groups;

    public TeacherList(){
        this.chairName = DataInput.getStr("Enter the name of chair: ");
        this.faculty = super.facultyName;
        this.teachersCount = DataInput.getInt("Enter the amount of teachers in "+chairName+" chair: ");
        this.teachersCount = DataInput.getInt("Enter the amount of students in "+chairName+" chair: ");
        this.teachers = new Teacher[teachersCount];
        this.groups = new StudentList[groupsCount];
        addTeachers();
        addStudents();
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
            str.append(teachers[i].getName()+" - "+ teachers[i].getChair()
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
        for(int j = 0; j < studentsWithSpecifiedCourse.length; j++) {
            for(int l = 0; l < studentsWithSpecifiedCourse[j].students.length; l++) {
                studentsWithSpecifiedCourse[ind++] = studentsWithSpecifiedCourse[j].students[l];
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
                    +" - "+student[i].getGroup());
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
        for(int j = 0; j < studentsWithSpecifiedCourse.length; j++) {
            for(int l = 0; l < studentsWithSpecifiedCourse[j].students.length; l++) {
                studentsWithSpecifiedCourse[ind++] = studentsWithSpecifiedCourse[j].students[l];
            }
        }
        return studentsWithSpecifiedCourse;
    }
    private void addTeachers(){
        for (int i=0; i<teachersCount; i++){
            System.out.print("Creating teacher "+i);
                Teacher teacher = new Teacher();
                teachers[i] = teacher;
        }
    }

    private void addStudents(){
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
