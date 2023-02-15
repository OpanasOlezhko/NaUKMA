import utils.DataInput;

public class Tester {
    public static void main(String[] args) {
        int maxLength;
        do {
            maxLength = DataInput.getInt("Enter the maximum " +
                    "number of teachers: ");
        } while (maxLength < 1);
        Teacher[] teachers = new Teacher[maxLength];
        int i = 0;
        while (i < maxLength) {
            String name = DataInput.getStr("Enter the name of "+(i+1)+" Teacher: ");
            // тут треба ще буде багато чого проработати
            String chair = DataInput.getStr("Chair: ");
            String status = DataInput.getStr("Status: ");
            Teacher tempTeacher = new Teacher(name, chair, status);
            teachers[i] = tempTeacher;
            i++;
        }
        TeacherList listTeachers = new TeacherList(teachers);
        String str;
        do {
            str = DataInput.getStr("Do you want to add new " +
                    "Teacher('yes' or 'no')? ");
        } while (!str.equals("yes") && !str.equals("no"));
        if(str.equals("yes")) {
            String str2;
            do {
                listTeachers.adder();
                str2 = DataInput.getStr("Do you want to try again" +
                        "('yes' or 'no')? ");
            } while (!str2.equals("no"));
        }
        String str3;
        do {
            str3 = DataInput.getStr("Do you want to remove a " +
                    "Teacher('yes' or 'no')? ");
        } while (!str3.equals("yes") && !str3.equals("no"));
        if(str.equals("yes")) {
            String str2;
            do {
                listTeachers.delete();
                str2 = DataInput.getStr("Do you want to do it again" +
                        "('yes' or 'no')? ");
            } while (!str2.equals("no"));
        }


    }
}
