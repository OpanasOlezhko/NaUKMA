import javax.xml.crypto.Data;

public class University {
    public int facultiesCount;
    Faculty[] faculties;

    public University(){
        this.facultiesCount = DataInput.getInt("Enter the amount of faculties in the university: ");
        this.faculties = new Faculty[facultiesCount];
        addFaculties();
    }

    public University(int facultiesCount) {
        this.facultiesCount = facultiesCount;
        this.faculties = new Faculty[facultiesCount];
    }

    private void addFaculties(){
        for (int i=0; i<facultiesCount; i++){
            Faculty faculty = new Faculty();
            faculties[i]=faculty;
        }
    }

    public void addFaculty(){
        Faculty faculty = new Faculty();
        Faculty[] arr = new Faculty[facultiesCount+1];
        for (int i=0; i<facultiesCount; i++){
            arr[i] = faculties[i];
        }
        arr[facultiesCount]=faculty;
        faculties=arr;
    }

    public void changeFacultyName(){
        Faculty faculty = chooseFaculty();
        faculty.setFacultyName();
    }

    public void removeFaculty(){
        Faculty faculty = chooseFaculty();
        Faculty[] arr = new Faculty[facultiesCount-1];
        for (int i=0; i<facultiesCount; i++){
            if(faculties[i]!=faculty)
                arr[i] = faculties[i];
        }
        faculties=arr;
    }

    public Student searchStudent(){
        String request = DataInput.getStr("Enter the name of a student you are looking for: ");
        spellingCheck(request);
        for (int i=0; i<facultiesCount; i++)
            for (int j=0; j<faculties[i].chairCount; j++)
                for (int l=0; l<faculties[i].chairs[j].groupsCount; l++)
                    for (int k=0; k<faculties[i].chairs[j].groups[l].students.length; k++)
                        if (faculties[i].chairs[j].groups[l].students[k].name.equals(request))
                            return faculties[i].chairs[j].groups[l].students[k];
        return null;
    }

    public Teacher searchTeacher(){
        String request = DataInput.getStr("Enter the name of a teacher you are looking for: ");
        spellingCheck(request);
        for (int i=0; i<facultiesCount; i++)
            for (int j=0; j<faculties[i].chairCount; j++)
                for (int l=0; l<faculties[i].chairs[j].teachersCount; l++)
                        if (faculties[i].chairs[j].teachers[l].name.equals(request))
                            return faculties[i].chairs[j].teachers[l];
        return null;
    }

    public void searchStudentByCourse(){
        int search = DataInput.getInt("Enter the number of course of a student you want to find: ");
        for (int i=0; i<facultiesCount; i++)
            for (int j=0; j<faculties[i].chairCount; j++)
                for (int l=0; l<faculties[i].chairs[j].groupsCount; l++)
                    if(faculties[i].chairs[j].groups[l].course==search){
                        for (int c=0; c<faculties[i].chairs[j].groups[l].students.length; c++)
                            System.out.println(faculties[i].chairs[j].groups[l].students[c]);
                    }
    }

    public String spellingCheck(String name){
        boolean state = true;
        String str = "";
        for (int i = 1; i < name.length(); i++) {
            if(!state) {
                if (name.charAt(i) == 32)
                    state = true;
                str += name.charAt(i);
            }
            if(state){
                if(name.charAt(i)>=97&&name.charAt(i)<=122)
                    str += (char) (name.charAt(i) - 32);
                else
                    str += name.charAt(i);
                state = false;
            }
        }
        return str;
    }
    public Faculty chooseFaculty(){
        String names ="";
        for (int i=0; i< facultiesCount; i++){
            names+=i+"-----"+faculties[i].facultyName+"\n";
        }
        int ans = DataInput.getInt("Choose the faculty:\n"+names);
        return faculties[ans];
    }


}

