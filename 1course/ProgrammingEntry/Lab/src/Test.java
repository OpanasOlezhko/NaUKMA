//import java.util.Random;
//public class Test {
//    private static final int MIN_LENGTH = 5;
//    private static final String LETTERS = "abcdefghijklmnopqrstuvwxyz";
//    private static final Random RANDOM = new Random();
//
//
//
//    public static University setup(){
//        String name;
//        University university = new University(2);
//        for(int i=0; i<university.facultiesCount; i++){
//            if(i==0)
//                name = "Informatics";
//            else name = "Computer science";
//            university.faculties[i]=new Faculty(2, name);
//            for(int j = 0; j<university.faculties[i].chairCount; j++){
//                university.faculties[i].chairs[j]= new TeacherList(university.faculties[i], randomName(), randomNumber(1, 3), randomNumber(1,2));
//                for(int l = 0; l<university.faculties[i].chairs[j].teachersCount; l++){
//                    university.faculties[i].chairs[j].teachers[l]= new Teacher(university.faculties[i].chairs[j], randomName(), randomName());
//                }
//                for (int f=0; f<university.faculties[i].chairs[j].groupsCount; f++){
//                    university.faculties[i].chairs[j].groups[f] = new StudentList(university.faculties[i].chairs[j], randomNumber(5, 10), randomNumber(1, 4), randomName());
//                    for (int t=0; t<university.faculties[i].chairs[j].groups[f].students.length; t++){
//                        university.faculties[i].chairs[j].groups[f].students[t]= new Student(university.faculties[i].chairs[j].groups[f], randomName(), randomNumber(60, 100));
//                    }
//                }
//            }
//        }
//        return university;
//    }
//    public static String randomName() {
//        int length = RANDOM.nextInt(6) + MIN_LENGTH;
//        StringBuilder sb = new StringBuilder(length);
//
//        for (int i = 0; i < length; i++) {
//            int index = RANDOM.nextInt(LETTERS.length());
//            char randomChar = LETTERS.charAt(index);
//            sb.append(randomChar);
//        }
//        return sb.toString();
//    }
//    public static int randomNumber(int min, int max) {
//        return RANDOM.nextInt(max - min + 1) + min;
//    }
//}
