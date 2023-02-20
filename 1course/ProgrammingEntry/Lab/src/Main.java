public class Main {
    private static Screen screen;
    private static  int facultiesCount;
    Faculty[] faculties;

    public static void main(String[] args) {
        screen = Screen.Faculty;
        action();
    }


    private static void action(){
        int ans = 0;
        switch (screen){
            case Main -> {

            }
            case Faculty -> {
                ans = DataInput.getInt("Enter the number of faculties: ");
                facultiesCount = ans;
            }
        }
    }

//    private void createFaculties(){
//        faculties = new Faculty[facultiesCount];
//
//    }
    enum Screen{
        Main,
        Faculty,
        Chair,
        Teacher,
        Group,
        Student
    }
}
