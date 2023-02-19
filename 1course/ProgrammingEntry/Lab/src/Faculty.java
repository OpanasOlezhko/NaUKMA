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
