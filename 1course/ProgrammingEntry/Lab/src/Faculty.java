public class Faculty {
    public String facultyName;
    public int chairCount;
    TeacherList[] chairs;

    public Faculty(){
        this.facultyName = DataInput.getStr("Enter the name of faculty: ");
        this.chairCount = DataInput.getInt("Enter the amount of chairs on "+ facultyName +" faculty");
        this.chairs = new TeacherList[chairCount];
        addChairs();
    }

    // 6 завдання
    private Teacher[] teachers = new Teacher[1000];


    private void gatherAllTheTeachers() {
        int p = 0;
        for(int k = 0; k < chairs.length; k++) {
            for(int l = 0; l < teachers.length; l++) {
                teachers[p++] = chairs[k].teachers[l];
            }
        }
        Teacher[] withoutNull = new Teacher[p];
        int index = 0;
        for(int i = 0; i < teachers.length; i++) {
            if(teachers[i] != null) {
                withoutNull[index++] = teachers[i];
            } else {
                break;
            }
        }
        teachers = withoutNull;
    }

    public String allTeachersFromFacultySorted() {
        gatherAllTheTeachers();
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

    boolean alreadyTogether = false;
    private void gatherAllTheStudents() {
        int p = 0;
        for(int i = 0; i < chairs.length; i++) {
            for(int j = 0; j < chairs[i].groups.length; j++) {
                for(int k = 0; k <chairs[i].groups[j].students.length; k++) {
                    studentsHere[p++] = chairs[i].groups[j].students[k];
                }
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


    // 6 завдання
    private Student[] studentsHere = new Student[1000];
    public String allStudentsFromFacultySorted() {
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
        return studentsToString();
    }

    // 5 завдання
    public String allStudentsFromFacultySortedByCourses() {
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
        return studentsToString();
    }

    private String studentsToString() {
        StringBuilder str = new StringBuilder();
        str.append("[");
        for (int i = 0; i < studentsHere.length; i++) {
            str.append(studentsHere[i].getName()+" - "+ studentsHere[i].getChair()
                    +" - "+studentsHere[i].getGroup()+" - "+ studentsHere[i].getGrade());
            if (i < studentsHere.length -1) {
                str.append(", ");
            }
        }
        str.append("]");
        return str.toString();
    }



    private void addChairs() {
        for (int i=0; i<chairCount; i++){
            TeacherList chair = new TeacherList();
            this.chairs[i] = chair;
        }
    }

    public void addChair(){
        TeacherList chair = new TeacherList();
        TeacherList[] arr = new TeacherList[chairCount+1];
        for (int i=0; i<chairCount; i++){
            arr[i] = chairs[i];
        }
    }
}
