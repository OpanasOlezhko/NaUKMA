public class University {
    public int facultiesCount;
    Faculty[] faculties;

    public University(){
        this.facultiesCount = DataInput.getInt("Enter the amount of faculties in the university: ");
        this.faculties = new Faculty[facultiesCount];
        addFaculties();
    }

    public University(Faculty[] faculties) {
        this.facultiesCount = faculties.length;
        this.faculties = faculties;
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

    public Faculty chooseFaculty(){
        String names ="";
        for (int i=0; i< facultiesCount; i++){
            names+=i+"-----"+faculties[i].facultyName+"\n";
        }
        int ans = DataInput.getInt("Choose the faculty:\n"+names);
        return faculties[ans];
    }


}

