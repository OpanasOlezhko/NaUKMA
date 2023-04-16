import FilesOperations.FilesOperator;
import Frames.GoodsGroupFrame;
import Structure.Goods;
import Structure.GoodsGroup;
import Structure.Warehouse;

import javax.swing.*;

public class Main {

//    ПЕРШ НІЖ ЗАПУСТИТИ ПРОГРАМУ, переконайтеся, що у класі FilesOperator
//    записаний шлях, який відповідає розташуванню файлів магазину на вашому комп'ютері,
//    після чого розкоментуйте один з методів у методі init (див. нижче)

    private static Warehouse warehouse;
    public static void main(String[] args) {
        init();
        GoodsGroupFrame frame = new GoodsGroupFrame(warehouse);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    public static void init(){
        warehouse = new Warehouse();
//        writeInFiles(); // записати інформацію про товари та їх групи у теку warehouse, якщо її там ще немає
//        writeFromFiles(); // щоразу брати інформацію про товари та їх групи з теки warehouse (зміни зберігаються)
    }

    private static void writeFromFiles(){
        warehouse.goodsGroups = FilesOperator.getGoodsGroupsWithGoodsArraysIncludedArrayFromFile();
    }

    private static void writeInFiles(){

//        Якщо у вас немає теки "warehouse", створіть одноіменну,
//        вкажіть її розташування у класі FilesOperator і розкоментуйте
//        виконання цього методу у методі init. Таким чином дані про наведені
//        вище товари та їх групи збережуться у вказану теку. Після того
//        закоментуйте цей метод та розкоментуйте метод writeFromFile у методі init.
//        Надалі програма при кожному запуску братиме інформацію про товари та їх групи
//        з теки warehouse, отже всі зміни, внесені через програму, зберігатимуться

        GoodsGroup fruits = new GoodsGroup("Фрукти", "Свіжі та смачні фрукти");
        GoodsGroup electronics = new GoodsGroup("Електроніка", "Електронні гаджети та пристрої");
        GoodsGroup books = new GoodsGroup("Книги", "Найбільш популярні книги та журнали");

        Goods apple = new Goods("яблуко", "Червоне та соковите", "Farm Fresh", 200, 2.50);
        Goods orange = new Goods("апельсин", "Солодкий та кислий", "Farm Fresh", 150, 3.00);
        Goods banana = new Goods("банан", "Жовтий та солодкий", "Farm Fresh", 100, 2.00);
        Goods grape = new Goods("виноград", "Дрібний та смачний", "Farm Fresh", 120, 4.00);
        Goods pineapple = new Goods("ананас", "Солодкий та кислий", "Farm Fresh", 80, 5.00);

        Goods laptop = new Goods("ноутбук", "Тонкий та легкий", "Dell", 10, 900.00);
        Goods phone = new Goods("телефон", "Швидкий та надійний", "Samsung", 20, 500.00);
        Goods headphones = new Goods("навушники", "Шумозаглушуючі", "Sony", 15, 150.00);
        Goods camera = new Goods("камера", "Висока роздільна здатність", "Nikon", 5, 800.00);
        Goods smartwatch = new Goods("смарт-годинник", "Фітнес-трекер", "Fitbit", 30, 200.00);

        Goods novel = new Goods("роман", "Художня книга", "Random House", 50, 20.00);
        Goods biography = new Goods("біографія", "Життєпис людини", "Simon & Schuster", 30, 25.00);
        Goods cookbook = new Goods("кулінарна книга", "Рецепти та поради по готуванню", "Penguin Random House", 20, 15.00);
        Goods magazine = new Goods("журнал", "Щомісячне видання", "Hearst Magazines", 40, 5.00);
        Goods historyBook = new Goods("історична книга", "Нехудожня і повчальна", "Hearst Magazines", 25, 40);
        warehouse.goodsGroups.add(fruits);
        warehouse.goodsGroups.add(electronics);
        warehouse.goodsGroups.add(books);

        fruits.goods.add(apple);
        fruits.goods.add(orange);
        fruits.goods.add(banana);
        fruits.goods.add(grape);
        fruits.goods.add(pineapple);

        electronics.goods.add(laptop);
        electronics.goods.add(phone);
        electronics.goods.add(headphones);
        electronics.goods.add(camera);
        electronics.goods.add(smartwatch);

        books.goods.add(novel);
        books.goods.add(biography);
        books.goods.add(cookbook);
        books.goods.add(magazine);
        books.goods.add(historyBook);


        FilesOperator.addGoodsGroupWithGoodsIncludedFile(fruits);
        FilesOperator.addGoodsGroupWithGoodsIncludedFile(electronics);
        FilesOperator.addGoodsGroupWithGoodsIncludedFile(books);
    }
}
