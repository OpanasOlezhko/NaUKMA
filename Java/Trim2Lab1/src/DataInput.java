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
    public static char getChar() throws IOException {
        String s = getStringGlyba();
        while (s.length() == 0) {
            System.out.println("No character entered. Please try again.");
            s = getStringGlyba();
        }
        return s.charAt(0);
    }

    public static double getDouble(String wr){
        writeText(wr);
        double value = 0;
        try {
            value = Double.parseDouble(getStringGlyba());

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
            value = Integer.valueOf(getStringGlyba());

        } catch (IOException var3) {
            var3.printStackTrace();
        }  catch (NumberFormatException e) {
            System.out.println("Invalid input, enter an integer value.");
            value=getInt(wr);
        }
        return value;
    }
    public static String getString(String wr){
            Scanner scanner = new Scanner(System.in);
            System.out.print(wr);
            String input = scanner.nextLine();
            return input;
    }
    public static String getStringGlyba()
            throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String s = br.readLine();
        if(s == "") {
            System.out.print("Введіть дані: ");
            getStringGlyba();
        }
        return s;
    }
}
