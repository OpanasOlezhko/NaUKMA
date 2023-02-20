public class Main {
    private static Screen screen;
    private static University university = new University();

    public static void main(String[] args) {
        //var a=university.faculties[1].chairs[1].groups[1].students[1];
    }


    private static void action() {
        int ans = 0;
        switch (screen) {
            case Main -> {
                ans = DataInput.getInt("What would you like to work with?\n0-----Faculty\n1-----Chair\n2-----Group" +
                        "\n3-----Teacher\n4-----Student");
                if (ans == 0)
                    screen = Screen.Faculty;
                else if (ans == 1)
                    screen = Screen.Chair;
                else if (ans == 2)
                    screen = Screen.Group;
                else if (ans == 3)
                    screen = Screen.Teacher;
                else if (ans == 4)
                    screen = Screen.Student;
                action();
            }
            case Faculty -> {
                ans = DataInput.getInt("How would you like to interact with faculty?\n0-----Add\n1-----Remove" +
                        "\n2-----Edit");
                if (ans == 0)
                    university.addFaculty();
                else if (ans == 1)
                    university.removeFaculty();
                else if (ans == 2)
                    editFaculty();
            }
            case Chair -> {

            }
        }
    }

    private static void editFaculty() {
        String names ="";
        for (int i=0; i< university.facultiesCount; i++){
            names+=i+"-----"+university.faculties[i].facultyName+"\n";
        }
        int ans = DataInput.getInt("Which faculty would you like to rename:\n"+names);
        university.faculties[ans].editName();
    }

    enum Screen {
        Main,
        Faculty,
        Chair,
        Teacher,
        Group,
        Student
    }

    enum Choice {
        None,
        Create,
        Add,
        Edit,
        Remove
    }
}
