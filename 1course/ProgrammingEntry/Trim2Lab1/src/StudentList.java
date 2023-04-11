public class StudentList {
    public TeacherList chair;
    public String groupName;
    public int studentsCount = 0;
    Student[] students;
    public int course;
    public  String chairName;

    public StudentList(TeacherList chair) {
        this.chair = chair;
        this.groupName = name();
        this.course = course();
        this.chairName = chair.chairName;
        students = new Student[studentsCount];
    }

    public StudentList(TeacherList chair, int students, int course, String groupName) {
        this.chair = chair;
        this.groupName = groupName;
        int numStudents = students;
        this.students = new Student[numStudents];
        this.chairName = chair.chairName;
        this.course = course;
    }
    private String name(){
        String name = DataInput.getStr("Enter the name of group: ");
        for (int i = 0; i< chair.groupsCount; i++){
            if(name.equals(chair.groups[i].groupName)){
                System.out.println("This name is already taken!");
                name();
            }
        }
        return name;
    }
    private int course(){
        int course = DataInput.getInt("Enter the course of the '" +groupName+"': ");
        if(course>4||course<1){
            System.out.println("The course is [1; 4] only!");
            course();
        }
        return course;
    }

    private void swap(int a, int b) {
        Student temp = this.students[a];
        this.students[a]=this.students[b];
        this.students[b]=temp;
    }

    public void quickSortNames(int low, int high){
        if(low<high){
            int j=partitionChar(low,high);
            quickSortNames(low,j-1);
            quickSortNames(j+1,high);
        }
    }

    private int partitionChar(int low, int high){
        String pivot = this.students[low].name;
        int i =low;
        int j =high;
        while(i<j) {
            while(compare(pivot, this.students[i].name)&&i<this.students.length-1)
                i++;
            while(compare(pivot, this.students[j].name)==false&&j>0)
                j--;
            if(i<j)
                swap(i,j);
        }
        swap(low,j);
        return j;
    }
    private boolean compare(String pivot, String word){
        String comp="";
        int res = 0;
        if(pivot.length()>word.length())
            res=1;
        else if(pivot.length()<word.length())
            res=-1;
        if(res==0||res==-1)
            comp=pivot;
        else if (res==1)
            comp=word;
        for(int i=0; i<comp.length(); i++) {
            if (word.charAt(i) < pivot.charAt(i))
                return true;
            else if(word.charAt(i) > pivot.charAt(i))
                return false;
        }
        return true;
    }
    public Student chooseStudent(){
        String names ="";
        for (int i=0; i< students.length; i++){
            names+=i+"-----"+students[i];
            if(i!=students.length-1)
                names+="\n";
        }
        int ans = DataInput.getInt("Choose the student:\n"+names);
        return students[ans];
    }

    public void setGroupName(){
        this.groupName = DataInput.getStr("Enter the new name of the group '"+groupName+"' :");
    }

    public String toString() {
        String sb = "";
        for (int i = 0; i < students.length; i++) {
            sb+=(students[i]);
        }
        return "The students in the group are: \n"+sb;
    }
}
