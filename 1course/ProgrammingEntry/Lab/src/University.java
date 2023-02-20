public class University {
    public int facultiesCount;
    Faculty[] faculties;

    public University(){
        this.facultiesCount = DataInput.getInt("Enter the amount of faculties in the university: ");
        this.faculties = new Faculty[facultiesCount];
        addFaculties();
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

    public void removeFaculty(){
        Faculty[] arr = new Faculty[facultiesCount-1];
        for (int i=0; i<facultiesCount-1; i++){
            arr[i] = faculties[i];
        }
        faculties=arr;
    }


}

