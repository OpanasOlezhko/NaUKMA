package FilesOperations;

import Structure.Goods;
import Structure.GoodsGroup;

import java.io.*;
import java.util.ArrayList;

/** @author Oleh Khodko */

public class FilesOperator {

    private static String pathToWarehouse = "src\\warehouse";

    /**
     * Файли, з якими працює програма, зберігаються у теці warehouse,
     * розташування якої вказано вище
     *
     * Усередині теки зберігаються файли txt, які відповідають групам товарів.
     * Загальний вигляд назви такого файлу:
     * назва групи товарів!опис групи товарів!.txt
     *
     * У цих файлах зберігаються записи про товари групи.
     * Для кожного окремого товару — окремий рядок.
     * Загальний вигляд такого рядка:
     * назва товару!опис товару!виробник товару!кількість на складі!ціна за одиницю
     *
     * Символ '!' використовується як роздільник, забезпечуючи коректне зчитування інформації
     * */

    public static ArrayList<Goods> getGoodsArrayFromFile(GoodsGroup group){
        String fileName = pathToWarehouse + "\\" + groupToString(group) + ".txt";
        ArrayList<Goods> goods = new ArrayList<Goods>();
        String input = textFromFile(fileName);
        String[] lines = input.split("\n");
        for (String line : lines) {
            if (line.contains("!")){
                String[] fields = line.split("!");
                Goods good = getGoodFromStringArray (fields);
                goods.add(good);
            }

        }
        return goods;
    }

    public static ArrayList<GoodsGroup> getGoodsGroupsArrayFromFile(){
        String fileName = pathToWarehouse;
        ArrayList<GoodsGroup> groups = new ArrayList<GoodsGroup>();
        File folder = new File(fileName);
        String[] groupLines = folder.list();
        for (String line : groupLines) {
            if (line.contains("!")) {
                String[] fields = line.split("!");
                GoodsGroup group = getGoodsGroupFromStringArray (fields);
                groups.add(group);
            }

        }
        return groups;
    }

    public static ArrayList<GoodsGroup> getGoodsGroupsWithGoodsArraysIncludedArrayFromFile(){
        String fileName = pathToWarehouse;
        ArrayList<GoodsGroup> groups = new ArrayList<GoodsGroup>();
        File folder = new File(fileName);
        String[] groupLines = folder.list();
        for (String line : groupLines) {
            if (line.contains("!")) {
                String[] fields = line.split("!");
                GoodsGroup group = getGoodsGroupFromStringArray (fields);
                group.goods = getGoodsArrayFromFile(group);
                groups.add(group);
            }

        }
        return groups;
    }

    public static void addGoodToFile (Goods good, GoodsGroup group){//без перевірки, чи вже є такий товар
        String fileName = pathToWarehouse + "\\" + groupToString(group) + ".txt";
        String goodLine = goodToString(good);
        try {
            FileWriter fw = new FileWriter(fileName, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("\n" + goodLine);
            bw.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void editGoodInFile (Goods oldGood, Goods newGood, GoodsGroup group){
        String fileName = pathToWarehouse + "\\" + groupToString(group) + ".txt";
        try {
            File inputFile = new File(fileName);
            File outputFile = new File("newFile.txt");
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith(oldGood.getName())) {
                    String newLine = goodToString(newGood);
                    writer.write(newLine + "\n");
                } else {
                    writer.write(line + "\n");
                }
            }
            reader.close();
            writer.close();
            if (inputFile.delete()) {
                if (!outputFile.renameTo(inputFile)) {
                    throw new IOException("Could not rename " + outputFile.getAbsolutePath() + " to " + inputFile.getAbsolutePath());
                }
            } else {
                throw new IOException("Could not delete " + inputFile.getAbsolutePath());
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void deleteGoodFromFile (Goods good, GoodsGroup group){
        String fileName = pathToWarehouse + "\\" + groupToString(group) + ".txt";
        try {
            File inputFile = new File(fileName);
            File outputFile = new File("newFile.txt");
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith(good.getName())) {
                } else {
                    writer.write(line + "\n");
                }
            }
            reader.close();
            writer.close();
            if (inputFile.delete()) {
                if (!outputFile.renameTo(inputFile)) {
                    throw new IOException("Could not rename " + outputFile.getAbsolutePath() + " to " + inputFile.getAbsolutePath());
                }
            } else {
                throw new IOException("Could not delete " + inputFile.getAbsolutePath());
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void addGoodsGroupFile (GoodsGroup group){
        String fullPath = pathToWarehouse + "\\" + groupToString(group) + ".txt";
        File file = new File(fullPath);

        try {
            if (file.createNewFile()) {
                System.out.println("File created successfully.");
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void editGoodsGroupFile (GoodsGroup oldGroup, GoodsGroup newGroup){
        String filePath = pathToWarehouse + "\\" + groupToString(oldGroup) + ".txt";
        File file = new File(filePath); // створюємо об'єкт файлу

        if (file.exists()) { // перевіряємо, чи файл існує
            String newFilePath = pathToWarehouse + "\\" + groupToString(newGroup) + ".txt"; // шлях до нового файлу
            File newFile = new File(newFilePath); // створюємо об'єкт нового файлу
            if (file.renameTo(newFile)) { // перейменовуємо файл
                System.out.println("File renamed successfully.");
            } else {
                System.out.println("File could not be renamed.");
            }

        } else {
            System.out.println("File does not exist.");
            System.out.println(filePath);
        }
    }

    public static void deleteGoodsGroupFile (GoodsGroup group){
        String filePath = pathToWarehouse + "\\" + groupToString(group) + ".txt";
        File file = new File(filePath);

        // виконуємо перевірку, чи файл існує
        if (file.exists()) {
            // виконуємо видалення
            if (file.delete()) {
                System.out.println(file.getName() + " видалений успішно.");
            } else {
                System.out.println("Не вдалося видалити " + file.getName() + ".");
            }
        } else {
            System.out.println("Файл " + file.getName() + " не існує.");
        }
    }

    public static void addGoodsGroupWithGoodsIncludedFile (GoodsGroup group){
        addGoodsGroupFile(group);
        for (Goods good : group.goods) {
            addGoodToFile(good, group);

        }
    }

    private static String groupToString(GoodsGroup group) {
        return group.getName() + "!" + group.getDescription() + "!";
    }

    private static String goodToString(Goods good){
        return good.getName() + "!" + good.getDescription() + "!" + good.getProvider() + "!"
                + good.getAmount() + "!" + good.getPrice();
    }

    private static Goods getGoodFromStringArray(String[] field){
        Goods good = new Goods (field[0], field[1], field[2], Integer.parseInt(field [3]), Double.parseDouble(field[4]));
        return good;
    }

    private static GoodsGroup getGoodsGroupFromStringArray(String[] field){
        GoodsGroup group = new GoodsGroup (field[0], field[1]);
        return group;
    }

    private static String textFromFile(String fileName){
        try(BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            String everything = sb.toString();
            return everything;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
