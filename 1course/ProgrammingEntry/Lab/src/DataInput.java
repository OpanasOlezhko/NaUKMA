
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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

    public static double getDouble(String wr) throws IOException {
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
        return value;
    }

    public static String getStr(String wr){
        int counter = 0;
        String finalStr;
        do {
            counter = 0;
            writeText(wr);
            String s = "";
            try {
                s = getString();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            finalStr = s;
            for(int i = 0; i < finalStr.length(); i++) {
                if(finalStr.charAt(i) >= '0' && finalStr.charAt(i) <= '9') {
                    counter++;
                } else if(finalStr.charAt(i) == '-') {
                    counter++;
                }
            }
        } while(counter != 0);
        return finalStr;

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
