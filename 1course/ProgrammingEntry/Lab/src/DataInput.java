import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public final class DataInput {
    public DataInput() {
    }

    private static void writeText(String wr) {
        if (wr == null) {
            System.out.print("Введіть дані: ");
        } else {
            System.out.println(wr);
        }

    }

    public static Long getLong() throws IOException {
        String s = getString();
        Long value = Long.valueOf(s);
        return value;
    }

    public static char getChar() throws IOException {
        String s = getString();
        while (s.length() == 0) {
            System.out.println("No character entered. Please try again.");
            s = getString();
        }
        return s.charAt(0);
    }

    public static double getDouble(String wr){
        writeText(wr);
        double value = 0;
        try {
            value = Double.parseDouble(getString());

        } catch (IOException var3) {
            var3.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println("Invalid input, enter a 'double' value.");
            value = getDouble(wr);
        }
        return value;
    }

    public static Integer getInt(String wr) {
        writeText(wr);
        Integer value = null;
        try {
            value = Integer.valueOf(getString());

        } catch (IOException var3) {
            var3.printStackTrace();
        }  catch (NumberFormatException e) {
            System.out.println("Invalid input, enter an integer value.");
            value=getInt(wr);
        }
        if(value<0){
            System.out.println("Invalid input, enter correct value.");
            value=getInt(wr);
        }
        return value;
    }
    public static String getStr(String wr){
            Scanner scanner = new Scanner(System.in);
            System.out.print(wr);
            String input = scanner.nextLine();
            return input;
    }
    public static String getString()
            throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String s = br.readLine();
        if(s == "") {
            System.out.print("Введіть дані: ");
            getString();
        }
        return s;
    }
}
