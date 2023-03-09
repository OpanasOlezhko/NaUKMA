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

     public Faculty(int chairs, String name) {
         this.facultyName = name;
         this.chairCount = chairs;
         this.chairs = new TeacherList[chairs];
     }

    // 6 завдання
    private Teacher[] teachersHere = new Teacher[1000];


    private void gatherAllTheTeachers() {
        int p = 0;
        for(int k = 0; k < chairs.length; k++) {
            for(int l = 0; l < chairs[k].teachers.length; l++) {
                  teachersHere[p++] = chairs[k].teachers[l];
            }
        }
        Teacher[] withoutNull = new Teacher[p];
        int index = 0;
        for(int i = 0; i < teachersHere.length; i++) {
            if(teachersHere[i] != null) {
                withoutNull[index++] = teachersHere[i];
            } else {
                break;
            }
        }
        teachersHere = withoutNull;
    }

    public String allTeachersFromFacultySorted() {
        gatherAllTheTeachers();
        for(int j = 0; j < teachersHere.length - 1; j++) {
            for(int i = j + 1; i < teachersHere.length; i++) {
                if(teachersHere[j].getName().compareTo(teachersHere[i].getName()) > 0) {
                    Teacher temp = teachersHere[j];
                    teachersHere[j] = teachersHere[i];
                    teachersHere[i] = temp;
                }
            }
        }
        return teachersToString();
    }


    private String teachersToString() {
        StringBuilder str = new StringBuilder();
//        reverse(teachersHere);
        for(Teacher s: teachersHere) {
            str.append(s.toString());
        }
        return str.toString();
    }

    public Teacher[] reverse(Teacher[] teachers){
        for(int i=0; i<teachers.length/2; i++){
            swapT(i,teachers.length-1-i);
        }
        return teachers;
    }

    private void swapT(int a, int b) {
        Teacher temp = teachersHere[a];
        teachersHere[a] = teachersHere[b];
        teachersHere[b]=temp;
    }


    boolean alreadyTogether = false;
    private void gatherAllTheStudents() {
        int p = 0;
        for(int i = 0; i < chairs.length; i++) {
            for(int j = 0; j < chairs[i].students.students.length; j++) {
                studentsHere[p++] = chairs[i].students.students[j];
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
                if(studentsHere[j].getName().compareTo(studentsHere[i].getName()) > 0) {
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
//        reverse(studentsHere);
        for(Student s: studentsHere) {
            str.append(s.toString());
        }
        return str.toString();
    }

     public Student[] reverse(Student[] students){
         for(int i=0; i<students.length/2; i++){
             swapS(i,students.length-1-i);
         }
         return students;
     }

     private void swapS(int a, int b) {
         Student temp = studentsHere[a];
         studentsHere[a] = studentsHere[b];
         studentsHere[b]=temp;
     }

    private void addChairs() {
        for (int i=1; i<=chairCount; i++){
            System.out.println("Creating chair "+i+":");
            TeacherList chair = new TeacherList(this);
            this.chairs[i-1] = chair;
        }
    }

    public void addChair(){
        TeacherList chair = new TeacherList(this);
        TeacherList[] arr = new TeacherList[chairCount+1];
        for (int i=0; i<chairCount; i++){
            arr[i] = chairs[i];
        }
    }

    public void removeChair(){
        TeacherList chair = chooseChair();
        TeacherList[] arr = new TeacherList[chairCount-1];
        for (int i=0; i<chairCount; i++){
            if(chairs[i]!=chair)
                arr[i] = chairs[i];
        }
        chairs=arr;
    }

    public void changeChairName(){
        TeacherList chair = chooseChair();
        chair.setChairName();
    }

    public void setFacultyName(){
        this.facultyName = DataInput.getStr("Enter the new name of the faculty '"+facultyName+"' :");
    }

     public TeacherList chooseChair(){
         String names ="";
         for (int i=0; i< chairCount; i++){
             names+=i+"-----"+chairs[i].chairName+"\n";
         }
         int ans = DataInput.getInt("Choose the chair:\n"+names);
         return chairs[ans];
     }

     public void editTeacher(){
         TeacherList chair = chooseChair();
         Teacher teacher = chair.chooseTeacher();
         int ans = DataInput.getInt("What to change about "+teacher.name+"?\n0-----Name\n1-----Status\n2-----Chair");
         if(ans ==0){
             String name = DataInput.getStr("Enter new name: ");
             teacher.setName(name);
         }
         else if (ans ==1){
             String status = DataInput.getStr("Enter new status: ");
             teacher.setStatus(status);
         }
         else if (ans ==2){
             System.out.println("Choose new chair: ");
             chair = chooseChair();
             teacher.changeChair(chair);
         }
     }
}
