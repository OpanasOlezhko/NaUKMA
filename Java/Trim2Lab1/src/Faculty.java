public class Faculty {
    public University university;
    public String facultyName;
    public int chairCount;
    TeacherList[] chairs;

    public Faculty(University university){
        this.university = university;
        this.facultyName = name();
        this.chairCount = 0;
        this.chairs = new TeacherList[chairCount];
    }

     public Faculty(University university, int chairs, String name) {
        this.university = university;
        this.facultyName = name;
        this.chairCount = chairs;
        this.chairs = new TeacherList[chairs];
     }

     private String name(){
        String name = DataInput.getString("Enter the name of faculty: ");
        for (int i=0; i<university.facultiesCount; i++){
            if(name.equals(university.faculties[i].facultyName)){
                System.out.println("This name is already taken!");
                name();
            }
        }
        return name;
     }

    // 6 завдання
    private Teacher[] teachersHere = new Teacher[1000];


    private void gatherAllTheTeachers() {
        teachersHere = new Teacher[1000];
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
    boolean alreadyTogether = false;
    private void gatherAllTheStudents() {
        studentsHere = new Student[1000];
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
        gatherAllTheStudents();
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
        gatherAllTheStudents();
        boolean isFinished = false;
        while(!isFinished) {
            isFinished = true;
            for (int i = 1; i < studentsHere.length; i++) {
                if (studentsHere[i].getCourse() <
                        studentsHere[i - 1].getCourse()) {
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
        for(Student s: studentsHere) {
            str.append(s.toString());
        }
        return str.toString();
    }
    public void addChair(){
        TeacherList chair = new TeacherList(this);
        TeacherList[] arr = new TeacherList[chairCount+1];
        for (int i=0; i<chairCount; i++){
            arr[i] = chairs[i];
        }
        arr[chairCount]=chair;
        chairs=arr;
        chairCount++;
    }

    public void removeChair(){
        int j=0;
        TeacherList chair = chooseChair();
        TeacherList[] arr = new TeacherList[chairCount-1];
        for (int i=0; i<chairCount; i++){
            if(chairs[i]==chair)
                j=1;
            else
                arr[i-j] = chairs[i];
        }
        chairs=arr;
        chairCount--;
    }

    public void changeChairName(){
        TeacherList chair = chooseChair();
        chair.setChairName();
    }

    public void setFacultyName(){
        this.facultyName = DataInput.getString("Enter the new name of the faculty '"+facultyName+"' :");
    }

     public TeacherList chooseChair(){
         String names ="";
         for (int i=0; i< chairCount; i++){
             names+=i+"-----"+chairs[i].chairName;
             if(i!=chairCount-1)
                 names+="\n";
         }
         int ans = DataInput.getInt("Choose the chair:\n"+names);
         return chairs[ans];
     }

     public void editTeacher(){
         TeacherList chair = chooseChair();
         Teacher teacher = chair.chooseTeacher();
         int ans = DataInput.getInt("What to change about "+teacher.name+"?\n0-----Name\n1-----Status\n2-----Chair");
         if(ans ==0){
             String name = DataInput.getString("Enter new name: ");
             teacher.setName(name);
         }
         else if (ans ==1){
             String status = DataInput.getString("Enter new status: ");
             teacher.setStatus(status);
         }
         else if (ans ==2){
             System.out.println("Choose new chair: ");
             chair = chooseChair();
             teacher.changeChair(chair);
         }
     }
}
